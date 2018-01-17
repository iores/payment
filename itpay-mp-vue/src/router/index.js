import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'

const Foo = {template: '<div>foo</div>'};
const Bar = {template: '<div>bar</div>'};
const Profile = {template: '<div>profile</div>'};
const User = {props:['userId'],template: ' <div class="user"><h2>User {{ userId}}</h2><router-view></router-view></div>'};
//const LoadData = r => require.ensure([], () => r(require('@/pages/loadData')), 'LoadData');
const LoadData = () => import('../pages/loadData.vue');
Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/foo',
      component: Foo
    }, {
      path: '/bar',
      component: Bar
    }, {
      path: '/user/:userId',
      name: 'user',
      component: User,
      props: true,
      children: [
        {
          path: 'profile',
          name: 'profile',
          component: Profile
        }
      ]
    }, {
      path: '/all',
      components: {
        a: Foo,
        b: Bar
      }
    },{
      path: '/loadData',
      component: LoadData,
    }
  ]
})
