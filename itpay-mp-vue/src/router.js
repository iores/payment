import Vue from 'vue'
import VueRouter from 'vue-router'


const Login = resolve => require(['./views/Login.vue'], resolve);
const NotFound = resolve => require(['./views/404.vue'], resolve);
const Home = resolve => require(['./views/Home.vue'], resolve);
const Main = resolve => require(['./views/Main.vue'], resolve);
const Table = resolve => require(['./views/nav1/Table.vue'], resolve);
const Form = resolve => require(['./views/nav1/Form.vue'], resolve);
const user = resolve => require(['./views/nav1/user.vue'], resolve);
const Page4 = resolve => require(['./views/nav2/Page4.vue'], resolve);
const Page5 = resolve => require(['./views/nav2/Page5.vue'], resolve);
const Page6 = resolve => require(['./views/nav3/Page6.vue'], resolve);
const echarts = resolve => require(['./views/charts/echarts.vue'], resolve);
const UserList = resolve => require(['./views/user/UserList.vue'], resolve);
const UserDetail = resolve => require(['./views/user/UserDetail.vue'], resolve);

Vue.use(VueRouter);


let routes = [
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    {
        path: '/',
        component: Home,
        name: '导航一',
        iconCls: 'fa el-icon-message',//图标样式class
        children: [
            { path: '/main', component: Main, name: '主页', hidden: false },
            { path: '/table', component: Table, name: 'Table' },
            { path: '/form', component: Form, name: 'Form' },
            { path: '/user', component: user, name: '列表' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '个人信息',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/page4', component: Page4, name: '页面4' },
            { path: '/page5', component: Page5, name: '页面5' },
            { path: '/page6', component: Page5, name: '我的消息' },
            { path: '/page7', component: Page5, name: '代办事项' },
            { path: '/page8', component: Page5, name: '我的流程' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '用户管理',
        iconCls: 'fa fa-id-card-o',
        children: [
            { path: '/user/list', component: UserList, name: '用户列表' },
            { path: '/user/detail/:detailId', component: UserDetail, name: '用户详情',hidden: true },
            { path: '/user2', component: Page5, name: '角色列表' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: '系统管理',
        iconCls: 'fa el-icon-setting',
        leaf: false,//只有一个节点
        children: [
            { path: '/page9', component: Page6, name: '登录日志' },
            { path: '/page10', component: Page6, name: '批处理计划' },
            { path: '/page11', component: Page6, name: '时钟列表' },
            { path: '/page12', component: Page6, name: '系统参数' },
        ]
    },
    {
        path: '/',
        component: Home,
        name: 'Charts',
        iconCls: 'fa fa-bar-chart',
        leaf: true,//只有一个节点
        children: [
            { path: '/echarts', component: echarts, name: 'echarts' }
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

const router = new VueRouter({
    mode: 'history',
    routes: routes
});


router.beforeEach((to, from, next) => {
    //NProgress.start();
    if (to.path == '/login' ) {
        sessionStorage.removeItem('user');
    }
    let user = sessionStorage.getItem('user');
    if (!user && to.path != '/login') {
        next({path: '/login'})
    } else {
        next()
    }
});


export default router;