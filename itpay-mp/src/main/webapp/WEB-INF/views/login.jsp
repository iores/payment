<%--
  Created by IntelliJ IDEA.
  User: feng
  Date: 2017/5/28 0028
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>登陆</title>
    <%@ include file="/WEB-INF/views/base/title.jsp" %>

    <script type="application/javascript">
        //如果有两个html 标签，说明窗口嵌套
        if ($("#system_main_home").length > 0) {
            //如果当前登录页面不在顶级窗口则，顶级窗口返回到登录页面
            window.location.reload();
        }
        //获取验证码
        function changeCaptcha(cap) {
            $(cap).attr("src", "image/captcha.html?" + Math.floor(Math.random() * 10000))
        }
        $(function () {
            $('#iform').bootstrapValidator({
                message: 'This value is not valid',
                fields: {
                    /*验证*/
                    username: {
                        /*键名username和input name值对应*/
                        message: 'The username is not valid',
                        validators: {
                            notEmpty: {
                                /*非空提示*/
                                message: '用户名不能为空'
                            },
                            stringLength: {
                                /*长度提示*/
                                min: 3,
                                max: 30,
                                message: '用户名长度必须在3到30之间'
                            }
                        }
                    },
                    password: {
                        selector:'#pass_word',
                        message: '密码无效',
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            stringLength: {
                                /*长度提示*/
                                min: 3,
                                max: 30,
                                message: '密码长度必须在3到30之间'
                            }
                        }
                    },
                    captcha: {
                        validators: {
                            notEmpty: {
                                /*非空提示*/
                                message: '验证码不能为空'
                            }
                        }
                    }
                },
                onSuccess: function () {
                    passwordHandler();
                }

            })
        });


        /**
         * 处理密码
         */
        function passwordHandler() {
            //处理密码
            var passWord = $("#pass_word").val().trim();
            var username = $("input[name='username']").val().trim();
            var hashPd = MD5(MD5(passWord)+username);
            var form = $("#iform");
            form.append("<input type='hidden'  name='password' value='"+hashPd+"' />");
        }


    </script>
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo test-color">
        <b>管理平台</b>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg test-color">欢迎登陆</p>

        <form action="mplogin.html" method="post" id="iform" autocomplete="off">
            <div class="form-group has-feedback ">
                <input maxlength="60" type="text" required="required" class="form-control login" name="username"
                       placeholder="用户名"/>
                <span class="glyphicon glyphicon-user form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback ">
                <input maxlength="60" type="password" class="form-control login" id="pass_word" placeholder="密码"/>
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="form-group  col-xs-5 ">
                    <input type="text" class="form-control login" maxlength="8" name="captcha" placeholder="验证码"/>
                </div>
                <div class="form-group  col-xs-7">
                    <img src="image/captcha.html" id="captcha" onclick="changeCaptcha(this)"
                         class="form-control captcha " alt="验证码"/>
                </div>
            </div>
            <%--err-messages--%>
            <div class="row">
                <div class="col-xs-12">
                        <span class="error-message" id="login-error">
                            <%@ include file="login_error_msg.jsp" %>
                            <%--<jsp:include page="login_error_msg.jsp"/>--%>
                        </span>
                </div>
            </div>

            <div class="row">
                <div class="col-xs-8">
                    <a href="#">忘记密码?</a>
                </div>
                <div class="col-xs-4">
                    <button type="submit"   class="btn btn-primary btn-block btn-flat">登陆</button>
                </div>
            </div>
        </form>


    </div>
</div>


</body>
</html>
