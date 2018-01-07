<%--
  Created by IntelliJ IDEA.
  User: feng
  Date: 2016/6/29 0029
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/views/base/libs.jsp" %>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="description" content="overview &amp; stats" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- Tell the browser to be responsive to screen width -->
<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

<link rel="icon" href="<%= request.getContextPath() %>/imgs/tom.ico" type="image/x-icon" />
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet"    href="<%= request.getContextPath() %>/plugs/bootstrap/css/bootstrap.min.css">
<!-- Ionicons -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugs/other/ionicons-2.0.1/css/ionicons.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugs/other/font-awesome/4.2.0/css/font-awesome.min.css" />
<!-- Theme style -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugs/AdminLTE/css/AdminLTE.min.css">
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugs/AdminLTE/css/skins/_all-skins.min.css">
<!--主题样式-->
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugs/AdminLTE/css/skins/skin-blue.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="<%= request.getContextPath() %>/plugs/other/iCheck/square/blue.css">
<!--自定义的css-->
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/my.css">
<!--验证框架-->
<link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/plugs/other/bootstrapValidator/css/bootstrapValidator.css" />


<!-- jQuery 2.2.3 -->
<script src="<%= request.getContextPath() %>/plugs/jquery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="<%= request.getContextPath() %>/plugs/bootstrap/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="<%= request.getContextPath() %>/plugs/other/iCheck/icheck.min.js"></script>
<%--密码加密框架--%>
<script src="<%= request.getContextPath() %>/plugs/other/bcrypt/bcrypt.min.js"></script>
<%--bootstrapValidator--%>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugs/other/bootstrapValidator/js/bootstrapValidator.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/plugs/other/bootstrapValidator/js/language/zh_CN.js"></script>