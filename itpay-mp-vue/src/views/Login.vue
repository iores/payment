<template>
    <el-container style="height: 100%">
        <el-header></el-header>
        <el-main class="login-div">
            <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0"
                     class=" login-container">
                <h3 class="title">系统登录</h3>
                <el-form-item prop="account">
                    <el-input prefix-icon="el-icon-message"  maxlength="20" type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="账号"></el-input>
                </el-form-item>
                <el-form-item prop="checkPass">
                    <el-input prefix-icon="el-icon-tickets" maxlength="20" type="password" v-model="ruleForm2.checkPass" auto-complete="off"
                              placeholder="密码"></el-input>
                </el-form-item>
                <el-form-item prop="captcha">
                        <el-col :span="12">
                            <el-input type="captcha" maxlength="5" v-model="ruleForm2.captcha"
                                      placeholder="验证码"></el-input>
                        </el-col>
                        <el-col :span="10" :offset="2">
                            <img :src="image" id="captcha" onclick="changeCaptcha(this)" alt="验证码"/>
                        </el-col>

                </el-form-item>

                <el-form-item style="width:100%;">
                    <el-button type="primary" style="width:100%;" @click.native.prevent="handleSubmit2"
                               :loading="logining">
                        登录
                    </el-button>
                    <!--<el-button @click.native.prevent="handleReset2">找回密码</el-button>-->
                </el-form-item>
            </el-form>

        </el-main>
        <el-footer>
            <div style="text-align: center">
                <p>
                    <a href="">关于我们</a>
                    |
                    <a href="">联系我们</a>
                    |
                    <a href="">法律声明</a>
                    |
                    <a href="">网站地图 </a>
                    |
                    <a href="">ICP备XXX-XXX</a>
                    <br/>
                    &copy;2017-{{year}} COMPANY
                    TEL:(028)8888-8888 FAX:(028)8888-8888
                    <br/>
                </p>
            </div>
        </el-footer>
    </el-container>
</template>

<script>
    import {requestLogin,getCaptcha} from '../api/api';
    import md5 from '../common/js/md5';

    export default {
        data() {
            return {
                year: new Date().getFullYear(),
                logining: false,
                image:'',
                ruleForm2: {
                    account: '',
                    checkPass: '',
                    captcha: ''
                },
                rules2: {
                    account: [
                        {required: true, message: '请输入账号', trigger: 'blur'},
                        {min: 3, max: 20, message: '长度在 3 到 20个字符', trigger: 'blur'}
                        //{ validator: validaePass }
                    ],
                    checkPass: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {min: 3, max: 20, message: '长度在 3 到 20个字符', trigger: 'blur'}
                        //{ validator: validaePass2 }
                    ],
                    captcha: [
                        {required: true, message: '请输入验证码', trigger: 'blur'},
                        {min: 5, max: 5, message: '长度为5位', trigger: 'blur'}
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
                        passWord = md5.getMd5(passWord + this.ruleForm2.account);
                        let loginParams = {userName: this.ruleForm2.account, passWord: passWord};
                        requestLogin(loginParams).then(resp => {
                            this.logining = false;
                            //NProgress.done();
                            let {meta, data} = resp;
                            if (meta.code != 200) {
                                this.$message({
                                    message: meta.message,
                                    type: 'error'
                                });
                            } else {
                                sessionStorage.setItem('user', this.ruleForm2.account);
                                this.$router.push({path: '/user/list'});
                            }
                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            //获取验证码
            getCaptcha: function () {
                getCaptcha().then(resp => {
                    let {meta, data} = resp;
                    console.info(resp);
                    if (meta.code == 200) {
                        this.image = data.value;
                        sessionStorage.setItem('captcha', data.key);
                    } else {

                    }
                });
            }
        },
        mounted() {
            this.getCaptcha();
        }
    }

</script>

<style lang="scss" scoped>
    .login-container {
        -webkit-border-radius: 5px;
        border-radius: 5px;
        -moz-border-radius: 5px;
        background-clip: padding-box;
        width: 320px;
        padding: 35px 35px 15px 35px;
        background: rgba(0, 0, 0, 0.23);
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        right: 100px;
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
        width: 100%;
        position: relative;
        background-size: cover;
    }
</style>