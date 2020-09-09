import Vue from 'vue'
import Router from 'vue-router'
import Layout from '@/views/layout/Layout'
import { formatRoutes } from '@/utils/util'
import store from '@/store'

Vue.use(Router)

/** note: Submenu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    roles: ['admin','editor']     will control the page roles (you can set multiple roles)
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
    noCache: true                if true ,the page will no be cached(default is false)
  }
**/
export const constantRouterMap = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path*',
        component: () => import('@/views/redirect/index')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '/auth-redirect',
    component: () => import('@/views/login/authredirect'),
    hidden: true
  },
  {
    path: '/500',
    component: Layout,
    children: [
      {
        path: '',
        component: () => import('@/views/errorPage/500'),
        name: '500',
        title: '500'
      }
    ],
    hidden: true
  },
  {
    path: '/404',
    component: Layout,
    children: [
      {
        path: '',
        component: () => import('@/views/errorPage/404'),
        name: '404',
        title: '404'
      }
    ],
    hidden: true
  },
  {
    path: '/401',
    component: Layout,
    children: [
      {
        path: '',
        component: () => import('@/views/errorPage/401'),
        name: '401',
        title: '401'
      }
    ],
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: '홈',
        title: '홈',
        icon: 'dashboard',
        noCache: true
      }
    ]
  },
  {
    path: '/iframe',
    component: Layout,
    redirect: '/iframe', // you can set roles in root nav
    children: [{
      path: ':routerPath',
      component: () => import('@/views/iframe/index'),
      name: 'iframe',
      meta: {
        title: 'iframe',
        icon: 'people'
      }
    }]
  },
  {
    path: '/lock',
    name: '화면보호',
    component: () => import('@/views/lock/index')
  }
]

export const constantExamRouterMap = [
  {
    path: '',
    component: Layout,
    children: [
      {
        path: '/exam/subjects/:id',
        component: () => import('@/views/exam/examSubjects'),
        name: '문항관리',
        title: '문항관리',
        noCache: true
      }
    ]
  },
  {
    path: '',
    component: Layout,
    children: [
      {
        path: '/exam/subjects/detail/:id',
        component: () => import('@/views/exam/subjectDetails'),
        name: '문항상세내용',
        title: '문항상세내용',
        noCache: true
      }
    ]
  },
  {
    path: '',
    component: Layout,
    children: [
      {
        path: '/exam/score/detail/:id',
        component: () => import('@/views/exam/scoreDetails'),
        name: '성적상세내용',
        title: '성적상세내용',
        noCache: true
      }
    ]
  },
  {
    path: '',
    component: Layout,
    children: [
      {
        path: '/exam/mark/:id',
        component: () => import('@/views/exam/markExam'),
        name: '성적일괄수정',
        title: '성적일괄수정',
        noCache: true
      }
    ]
  }
]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: [].concat(...formatRoutes(store.state.user.menu), constantRouterMap, constantExamRouterMap)
})
