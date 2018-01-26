import axios from 'axios';
// 添加请求拦截器
axios.interceptors.request.use(function (config) {
    //自定义请求头
    config.headers['x-user-flag'] = 'your-flag';
    let user = sessionStorage.getItem('user');
    if (user) {
        user = JSON.parse(user);
        config.headers['user-token'] = user.sessionId;
    }
    // 在发送请求之前做些什么
    return config;
}, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
});
let base = '';

export const requestLogin = params => {
    return axios.post(`/api/login`, params).then(res => res.data);
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
    return axios.get(`/api/rest/user/list`, {params: params});
};