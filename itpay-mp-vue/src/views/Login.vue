<template>
    <div class="login-div">
        <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px"
                 class=" login-container">
            <h3 class="title">系统登录</h3>
            <el-form-item prop="account">
                <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="账号" ></el-input>
            </el-form-item>
            <el-form-item prop="checkPass">
                <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="密码" ></el-input>
            </el-form-item>
            <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
            <el-form-item style="width:100%;">
                <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2" :loading="logining">
                    登录
                </el-button>
                <!--<el-button @click.native.prevent="handleReset2">找回密码</el-button>-->
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    import {requestLogin} from '../api/api';
    import md5 from '../common/js/md5';
    //import NProgress from 'nprogress'
    export default {
        data() {
            return {
                logining: false,
                ruleForm2: {
                    account: '',
                    checkPass: ''
                },
                rules2: {
                    account: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        //{ validator: validaePass }
                    ],
                    checkPass: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        //{ validator: validaePass2 }
                    ]
                },
                checked: true
            };
        },
        methods: {
            handleReset2() {
                this.$refs.ruleForm2.resetFields();
            },
            handleSubmit2(ev) {
                var _this = this;
                this.$refs.ruleForm2.validate((valid) => {
                    if (valid) {
                        //_this.$router.replace('/table');
                        this.logining = true;
                        //NProgress.start();
                        let passWord = md5.getMd5(this.ruleForm2.checkPass);
                        passWord = md5.getMd5(passWord+this.ruleForm2.account);
                        let loginParams = {userName: this.ruleForm2.account, passWord: passWord};
                        requestLogin(loginParams).then(resp => {
                            this.logining = false;
                            //NProgress.done();
                            let {meta,data} = resp;
                            if (meta.code != 200) {
                                this.$message({
                                    message: meta.message,
                                    type: 'error'
                                });
                            } else {
                                sessionStorage.setItem('user', data);
                                this.$router.push({path: '/table'});
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    }

</script>

<style lang="scss" scoped>
    .login-container {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        background-clip: padding-box;
        margin: 15% auto;
        width: 320px;
        padding: 35px 35px 15px 35px;
        background: rgba(0, 0, 0, 0.23);
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        position: absolute;
        right: 10%;
        .title {
            margin: 0px auto 40px auto;
            text-align: center;
            color: #fff;
        }
        .remember {
            margin: 0px 0px 35px 0px;
            color: #fff;
        }
    }

    .login-div {
        background: url("../assets/background.jpg") no-repeat center;
        background-size: 100%;
        position:absolute;
        /*margin-top:60px;*/
        width: 100%;
        height: 100%;
    }

</style>