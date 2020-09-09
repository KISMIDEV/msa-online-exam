<template>
  <div class="tab-container">
    <div class="filter-container">
      <el-button v-if="menu_btn_add" icon="el-icon-check" type="primary" @click="handlerAddSuper">대메뉴등록</el-button>
      <el-button v-if="menu_btn_add" icon="el-icon-check" type="primary" @click="handlerAdd">소메뉴등록</el-button>
      <el-button v-if="menu_btn_del" icon="el-icon-delete" type="danger" @click="handleDelete">{{ $t('table.del') }}</el-button>
      <el-button v-if="menu_btn_import" icon="el-icon-upload2" type="success" @click="handleImport">{{ $t('table.import') }}</el-button>
      <el-button v-if="menu_btn_export" icon="el-icon-download" type="success" @click="handleExport">{{ $t('table.export') }}</el-button>

      <el-row>
        <el-col :span="4" style ="margin-top:10px;">
          <el-tree
            ref="tree"
            :data="treeData"
            :filter-node-method="filterNode"
            :props="defaultProps"
            class="filter-tree"
            node-key="id"
            highlight-current
            accordion
            @node-click="getNodeData"
            @node-expand="nodeExpand"
            @node-collapse="nodeCollapse"
          />
        </el-col>
        <el-col :span="20" style="margin-top:10px;">
          <el-card class="box-card">
            <el-form ref="form" :rules="rules" :label-position="labelPosition" :model="form" label-width="100px" style="width: 90%;">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="메뉴명" prop="name">
                    <el-input v-model="form.name" placeholder="메뉴명을 입력하세요"/>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="메뉴레이블" prop="permission">
                    <el-input v-model="form.permission" placeholder="메뉴레이블을 입력하세요"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="메뉴URL" prop="url">
                    <el-input v-model="form.url" placeholder="메뉴URL을 입력하세요"/>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="유형 " prop="type">
                    <el-select v-model="form.type" class="filter-item" placeholder="리소스요청유형을 입력하세요">
                      <el-option v-for="item in typeOptions" :key="item" :label="item | typeFilter" :value="item"/>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="일련번호" prop="sort">
                    <el-input v-model="form.sort" placeholder="일련번호를 입력하세요"/>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="메뉴레이블" prop="icon">
                    <el-input v-model="form.icon" placeholder="선택하세요"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="컴포넌트명" prop="component">
                    <el-input v-model="form.component" placeholder="컴포넌트명을 입력하세요"/>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="컴포넌트주소" prop="component">
                    <el-input v-model="form.path" placeholder="컴포넌트주소"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="24">
                  <el-form-item :label="$t('table.remark')">
                    <el-input :autosize="{ minRows: 4, maxRows: 9}" v-model="form.remark" type="textarea" placeholder="비고"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item>
                <el-button v-if="menu_btn_edit" type="primary" @click="create">저장</el-button>
                <el-button @click="onCancel">취소</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!-- 导出菜单 -->
    <el-dialog :visible.sync="dialogExportVisible" :title="$t('table.export')">
      <el-tree
        ref="menuTree"
        :data="treeData"
        :props="defaultProps"
        show-checkbox
        class="filter-tree"
        node-key="id"
        highlight-current
        @node-click="getNodeData"
      />
      <div slot="footer" class="dialog-footer">
        <el-button v-if="menu_btn_export" type="primary" @click="handleExportMenu()">export</el-button>
      </div>
    </el-dialog>

    <!-- 导入菜单 -->
    <el-dialog :visible.sync="dialogImportVisible" :title="$t('table.import')">
      <el-row>
        <el-col :span="24">
          <el-upload
            drag
            :multiple="false"
            :auto-upload="true"
            :show-file-list="true"
            :before-upload="beforeMenuUpload"
            :on-progress="handleUploadProgress"
            :on-success="handleUploadMenuSuccess"
            :action="importUrl"
            :headers="headers"
            style="text-align: center;">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">파일을 여기로 드래그하거나 <em>여기서 업로드할수 있습니다.</em></div>
            <div slot="tip" class="el-upload__tip">xlsx파일만 업로드하실수 있습니다.</div>
          </el-upload>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { fetchTree, getObj, addObj, delObj, putObj, exportObj } from '@/api/admin/menu'
import { mapGetters } from 'vuex'
import { getToken } from '@/utils/auth'
import { exportExcel, notifySuccess } from '@/utils/util'

export default {
  name: 'MenuManagement',
  components: {},
  filters: {
    typeFilter (type) {
      const typeMap = {
        0: '菜单',
        1: '버튼'
      }
      return typeMap[type]
    }
  },
  data () {
    return {
      list: null,
      total: null,
      formAdd: true,
      formStatus: '',
      showElement: false,
      typeOptions: [0, 1],
      listQuery: {
        name: undefined
      },
      treeData: [],
      oExpandedKey: {
      },
      oTreeNodeChildren: {
      },
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      labelPosition: 'right',
      form: {
        permission: undefined,
        name: undefined,
        id: undefined,
        parentId: -1,
        url: undefined,
        icon: '',
        sort: 30,
        component: undefined,
        type: 0,
        path: undefined,
        remark: undefined
      },
      currentId: '',
      // 表单校验规则
      rules: {
        name: [{ required: true, message: '메뉴명을 입력하세요', trigger: 'change' }],
        permission: [{ required: true, message: '메뉴레이블을 입력하세요', trigger: 'change' }]
      },
      // 버튼权限
      menu_btn_add: false,
      menu_btn_edit: false,
      menu_btn_del: false,
      menu_btn_import: false,
      menu_btn_export: false,
      // 导入窗口状态
      dialogImportVisible: false,
      // 导出窗口状态
      dialogExportVisible: false,
      // 选择的菜单
      multipleSelection: [],
      importUrl: '/api/user/v1/menu/import',
      headers: {
        Authorization: 'Bearer ' + getToken()
      },
      uploading: false,
      percentage: 0
    }
  },
  created () {
    this.getList()
    this.menu_btn_add = this.permissions['sys:menu:add']
    this.menu_btn_edit = this.permissions['sys:menu:edit']
    this.menu_btn_del = this.permissions['sys:menu:del']
    this.menu_btn_import = this.permissions['sys:menu:import']
    this.menu_btn_export = this.permissions['sys:menu:export']
  },
  computed: {
    ...mapGetters([
      'elements',
      'permissions'
    ])
  },
  methods: {
    getList () {
      fetchTree(this.listQuery).then(response => {
        this.treeData = response.data
        // 加载后默认选中第一个菜单
        if (response.data && response.data.length > 0) {
          const firstNode = response.data[0]
          this.$refs.tree.setCheckedNodes([].concat(firstNode))
          this.getNodeData(firstNode)
        }
      })
    },
    filterNode (value, data) {
      if (!value) return true
      return data.label.indexOf(value) !== -1
    },
    nodeExpand (data) {
      const aChildren = data.children
      if (aChildren.length > 0) {
        this.oExpandedKey[data.id] = true
        this.oTreeNodeChildren[data.id] = aChildren
      }
      this.setExpandedKeys()
    },
    nodeCollapse (data) {
      this.oExpandedKey[data.id] = false
      // 如果有子节点
      this.treeRecursion(this.oTreeNodeChildren[data.id], (oNode) => {
        this.oExpandedKey[oNode.id] = false
      })
      this.setExpandedKeys()
    },
    setExpandedKeys () {
      const oTemp = this.oExpandedKey
      this.aExpandedKeys = []
      for (const sKey in oTemp) {
        if (oTemp[sKey]) {
          this.aExpandedKeys.push(parseInt(sKey))
        }
      }
    },
    treeRecursion (aChildren, fnCallback) {
      if (aChildren) {
        for (let i = 0; i < aChildren.length; ++i) {
          const oNode = aChildren[i]
          fnCallback && fnCallback(oNode)
          this.treeRecursion(oNode.children, fnCallback)
        }
      }
    },
    getNodeData (data) {
      this.formStatus = 'update'
      getObj(data.id).then(response => {
        this.form = response.data
      })
      this.currentId = data.id
      this.showElement = true
    },
    handlerEdit () {
      if (this.form.id) {
        this.formStatus = 'update'
      }
    },
    handlerAddSuper () {
      this.resetForm()
      this.form.parentId = -1
      this.form.component = 'Layout'
      this.formStatus = 'create'
    },
    handlerAdd () {
      this.resetForm()
      this.formStatus = 'create'
    },
    handleDelete () {
      if (this.currentId === '') {
        this.$message({
          message: '请选择要删除的记录',
          type: 'warning'
        })
        return
      }
      this.$confirm('삭제하시겠습니까?', '경고', {
        confirmButtonText: '확인',
        cancelButtonText: '취소',
        type: 'warning'
      }).then(() => {
        delObj(this.currentId).then(() => {
          this.getList()
          this.resetForm()
          this.onCancel()
          notifySuccess(this, '삭제성공')
        })
      }).catch(() => {})
    },
    create () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          if (this.form.id) {
            putObj(this.form).then(() => {
              this.getList()
              notifySuccess(this, '수정성공')
            })
          } else {
            addObj(this.form).then(() => {
              this.getList()
              notifySuccess(this, '신규생성성공')
            })
          }
          this.getList()
        }
      })
    },
    onCancel () {
      this.formStatus = ''
    },
    resetForm () {
      this.form = {
        permission: undefined,
        name: undefined,
        id: undefined,
        parentId: this.currentId,
        url: undefined,
        icon: '',
        sort: 30,
        component: undefined,
        type: 0,
        path: undefined,
        remark: undefined
      }
    },
    // 导入
    handleImport () {
      this.dialogImportVisible = true
    },
    // 显示导出弹窗
    handleExport () {
      this.dialogExportVisible = true
    },
    // 导出
    handleExportMenu () {
      // 获取选中节点
      const keys = this.$refs.menuTree.getCheckedKeys(true).concat(this.$refs.menuTree.getHalfCheckedKeys())
      let ids = []
      if (keys.length === 0) {
        this.$confirm('모든 메뉴를 export하시겠습니까?', '경고', {
          confirmButtonText: '확인',
          cancelButtonText: '취소',
          type: 'success'
        }).then(() => {
          exportObj(ids).then(response => {
            // 导出Excel
            exportExcel(response)
          })
        }).catch(() => {})
      } else {
        this.$confirm('선택된 메뉴를 export하시겠습니까?', '경고', {
          confirmButtonText: '확인',
          cancelButtonText: '취소',
          type: 'success'
        }).then(() => {
          for (let i = 0; i < keys.length; i++) {
            ids.push(keys[i])
          }
          exportObj(ids).then(response => {
            // 导出Excel
            exportExcel(response)
          })
        }).catch(() => {})
      }
    },
    // 上传前
    beforeMenuUpload (file) {
      const isExcel = file.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'
      const isLt10M = file.size / 1024 / 1024 < 10
      if (!isExcel) {
        this.$message.error('excel파일만 업로드가능합니다.!')
      }
      if (!isLt10M) {
        this.$message.error('업로드할 첨부파일은 10M를 초과할수 없습니다.!')
      }
      return isExcel && isLt10M
    },
    handleUploadProgress (event, file, fileList) {
      this.uploading = true
      this.percentage = parseInt(file.percentage.toFixed(0))
    },
    // 上传成功
    handleUploadMenuSuccess () {
      notifySuccess(this, 'import성공')
      this.dialogImportVisible = false
      this.getList()
      this.uploading = false
    }
  }
}
</script>

<style scoped>
  .tab-container{
    margin: 30px;
  }
  .filter-tree {
    overflow: hidden;
  }
</style>
