import axios from 'axios';
import router from '../router'

// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    let token = sessionStorage.getItem('token');
    if (token) {
        config.headers['x-access-token'] = token;
    }
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// http响应拦截器
axios.interceptors.response.use(resp => {
    let token = resp.headers['x-access-token'];
    if(token){
        //存在token
        sessionStorage.setItem("token",token);
    }
    //判断响应是否为200
    let {meta} = resp.data;
    if(meta.code == 401){
        router.push({path: '/login'});
      //  return Promise.reject(meta.message)
    }
    return resp
}, error => {
    return Promise.reject(error)
});


let base = '';

export const requestLogin = params => {
    return axios.post(`/api/login`, params).then(res => res.data);
};

export const getCaptcha = params => {
    return axios.post(`/api/captcha`,params).then(res => res.data);
};

export const getUserList = params => {
    return axios.get(`${base}/user/list`, {params: params});
};

export const getUserListPage = params => {
    return axios.get(`${base}/user/listpage`, {params: params});
};

export const removeUser = params => {
    return axios.get(`${base}/user/remove`, {params: params});
};

export const batchRemoveUser = params => {
    return axios.get(`${base}/user/batchremove`, {params: params});
};

export const editUser = params => {
    return axios.get(`${base}/user/edit`, {params: params});
};

export const addUser = params => {
    return axios.get(`${base}/user/add`, {params: params});
};

export const postUserListPage = params => {
    return axios.post(`/api/rest/user/list`, params).then(res => res.data);
};

export const userDetail = params => {
    return axios.get(`/api/rest/user/detail/${params}`).then(res => res.data);
};

export const postLoginListPage = params => {
    return axios.post(`/api/rest/user/UserLoginList`, params).then(res => res.data);
};