<%--
  Created by IntelliJ IDEA.
  User: feng
  Date: 2017/6/1 0001
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理平台</title>
    <%@ include file="/WEB-INF/views/base/title.jsp" %>
    <script src="<%= request.getContextPath() %>/plugs/AdminLTE/js/app.min.js"></script>
    <%--主题选择的js--%>
    <script src="<%= request.getContextPath() %>/js/skin.js"></script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <jsp:include page="header.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">

        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">

            <!-- Sidebar Menu -->
            <ul class="sidebar-menu">
                <li class="header">菜单</li>
                <!-- Optionally, you can add icons to the links -->
                <li class="active"><a href="#"><i class="fa fa-files-o"></i> <span>qq</span></a></li>
                <li><a href="#"><i class="fa fa-link"></i> <span>wewq</span></a></li>
                <li class="treeview">
                    <a href="#"><i class="fa fa-link"></i> <span>wqeqw</span>
                        <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#">qwewq</a></li>
                        <li><a href="#">qwewqe</a></li>
                    </ul>
                </li>
            </ul>
            <!-- /.sidebar-menu -->
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper tab-content" >
        <ul class="nav nav-tabs" >
            <li><a href="#xsd3" style="color: black" data-toggle="tab">我的消息</a></li>
            <li><a href="#dwadaw2" data-toggle="tab">系统参数</a></li>
        </ul>
        
        <div id="xsd3" class="tab-pane">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    主页1111111111
                    <small>Optional description</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                    <li class="active">Here</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">

                <!-- Your Page Content Here -->

            </section>
        </div>
        <div id="dwadaw2" class="tab-pane">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    主页22222222222222222222222
                    <small>Optional description</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
                    <li class="active">Here</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">

                <!-- Your Page Content Here -->

            </section>
        </div>
      
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <jsp:include page="footer.jsp"/>


    <!-- Control Sidebar -->
    <jsp:include page="control-sidebar.jsp"/>
    <!-- /.control-sidebar -->
    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>
</div>
</body>
</html>
