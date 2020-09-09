<template>
  <div class="tab-container">
    <div class="filter-container">
      <el-button v-if="dept_btn_add" icon="el-icon-check" type="primary" @click="handlerAdd">添加</el-button>
      <el-button v-if="dept_btn_del" icon="el-icon-delete" type="danger" @click="handleDelete">删除</el-button>

      <el-row>
        <el-col :span="5" style ="margin-top:10px;">
          <el-tree
            :data="treeData"
            :default-expanded-keys="aExpandedKeys"
            :filter-node-method="filterNode"
            :props="defaultProps"
            class="filter-tree"
            node-key="id"
            highlight-current
            @node-click="getNodeData"
            @node-expand="nodeExpand"
            @node-collapse="nodeCollapse"
          />
        </el-col>
        <el-col :span="19" style="margin-top:10px;">
          <el-card class="box-card">
            <el-form ref="form" :rules="rules" :label-position="labelPosition" :model="form" label-width="100px">
              <el-row>
                <el-col :span="12">
                  <el-form-item label="부서명" prop="deptName">
                    <el-input v-model="form.deptName" placeholder="부서명을 입력하세요"/>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="부서설명" prop="deptDesc">
                    <el-input v-model="form.deptDesc" placeholder="부서설명"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="부서장" prop="deptLeader">
                    <el-input v-model="form.deptLeader" placeholder="부서장을 입력하세요"/>
                  </el-form-item>
                </el-col>
                <el-col :span="12">
                  <el-form-item label="일련번호" prop="sort">
                    <el-input v-model="form.sort" placeholder="일련번호를 입력하세요"/>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item>
                <el-button v-if="dept_btn_add || dept_btn_edit" type="primary" @click="create">저장</el-button>
                <el-button @click="onCancel">취소</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import { fetchTree, getObj, addObj, delObj, putObj } from '@/api/admin/dept'
import { mapGetters } from 'vuex'
import { notifySuccess, isNotEmpty } from '@/utils/util'

export default {
  name: 'DeptManagement',
  components: {},
  filters: {
    typeFilter (type) {
      const typeMap = {
        0: '메뉴',
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
      typeOptions: ['0', '1'],
      listQuery: {
        name: undefined
      },
      treeData: [],
      oExpandedKey: {
        // key (from tree id) : expandedOrNot boolean
      },
      oTreeNodeChildren: {
        // id1 : [children] (from tree node id1)
        // id2 : [children] (from tree node id2)
      },
      aExpandedKeys: [],
      defaultProps: {
        children: 'children',
        label: 'deptName'
      },
      labelPosition: 'right',
      form: {
        id: undefined,
        parentId: -1,
        deptName: undefined,
        deptDesc: undefined,
        deptLeader: undefined,
        sort: 30
      },
      currentId: '',
      rules: {
        deptName: [{ required: true, message: '부서이름을 입력하세요', trigger: 'change' }]
      },
      dept_btn_add: false,
      dept_btn_edit: false,
      dept_btn_del: false
    }
  },
  created () {
    this.getList()
    this.dept_btn_add = this.permissions['sys:dept:add']
    this.dept_btn_edit = this.permissions['sys:dept:edit']
    this.dept_btn_del = this.permissions['sys:dept:del']
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
    handlerAdd () {
      this.resetForm()
      this.formStatus = 'create'
    },
    handleDelete () {
      if (!isNotEmpty(this.currentId)) {
        this.$message({
          message: '삭제하시겠어요?',
          type: 'warning'
        })
        return
      }
      this.$confirm('삭제하시겠어요?', '경고', {
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
              notifySuccess(this, '신규추가성공')
            })
          }
        }
      })
    },
    onCancel () {
      this.formStatus = ''
    },
    resetForm () {
      let parentId
      if (this.form.id === undefined) {
        parentId = -1
      } else {
        parentId = this.form.id
      }
      this.form = {
        id: undefined,
        parentId: parentId,
        deptName: undefined,
        deptDesc: undefined,
        deptLeader: undefined,
        sort: 30
      }
    }
  }
}
</script>

<style scoped>
  .tab-container{
    margin: 30px;
  }
</style>
