<%--
  Created by IntelliJ IDEA.
  User: feng
  Date: 2017/5/28 0028
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>登陆</title>
    <%@ include file="/WEB-INF/views/base/title.jsp" %>
</head>
<body class="hold-transition login-page">
    <div class="login-box">
        <div class="login-logo test-color">
            <b>管理平台</b>
        </div>
        <!-- /.login-logo -->
        <div class="login-box-body">
            <p class="login-box-msg test-color">欢迎登陆</p>

            <form action="doLogin.html" method="post">
                <div class="form-group has-feedback ">
                    <input type="text" class="form-control" placeholder="用户名"/>
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback ">
                    <input type="password" class="form-control" placeholder="密码"/>
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="row">
                    <div class="form-group  col-xs-8 ">
                        <input type="text" class="form-control" placeholder="验证码"/>
                    </div>
                    <div class="form-group  col-xs-4">
                        <input type="text" class="form-control" value="11111" placeholder="验证码"/>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-8">
                        <a href="#">忘记密码?</a>
                    </div>
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-primary btn-block btn-flat">登陆</button>
                    </div>
                </div>
            </form>


        </div>
    </div>



</body>
</html>
