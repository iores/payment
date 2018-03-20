//import babelpolyfill from 'babel-polyfill'
import Vue from 'vue'
import ElementUI from 'element-ui'
//import { Button, Select ,Main,Container,Header,Form,FormItem,Input,Footer,Col} from 'element-ui';

import App from './App'

import store from './vuex/store'
import Vuex from 'vuex'
import router from './router'
import 'font-awesome/css/font-awesome.min.css'
import './styles/vars.scss'

Vue.use(ElementUI);
// Vue.use(Button);
// Vue.use(Select);
// Vue.use(Main);
// Vue.use(Container);
// Vue.use(Header);
// Vue.use(Form);
// Vue.use(FormItem);
// Vue.use(Input);
// Vue.use(Footer);
// Vue.use(Col);
Vue.use(Vuex);

//NProgress.configure({ showSpinner: false });


//router.afterEach(transition => {
//NProgress.done();
//});

new Vue({
    // el: '#app',
    // template: '<App/>',
    router,
    store,
    //components: { App }
    render: h => h(App)
}).$mount('#app');

