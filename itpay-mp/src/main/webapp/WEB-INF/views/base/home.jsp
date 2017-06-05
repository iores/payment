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

    <script type="application/javascript">
        /**
         * 点击该方法加载主页面
         * @param a
         * @param url
         */
        function loadUrl(a) {
            //获取tab_id
            var tab_id=$(a).attr("tab_id");
            //增加导航栏tab
            addTab(a);
            //获取tab_pane
            var tab_pane=$("#"+tab_id);
            if(tab_pane&&tab_pane.length>0){
                return ;
            }
            $("#page_content").append("<div id='"+tab_id+"' class='tab-pane active'>这个是新增的:"+tab_id+"</div>");
            
        }

        /**
         * 在顶部导航栏增加tab
         * @param a 当前点击的菜单栏
         */
        function addTab(a){
            //tab的标题为a标签的内容
            var title = $(a).html();
            //获取url
            var url=$(a).attr("ref");
            var tab_id=$(a).attr("tab_id");
            //获取出顶部导航\
            var nav_tabs= $("#nav-tabs_01");
            //先移除所有激活状态的tab
            nav_tabs.children("li").removeClass("active");
            //取消所有激活的pane
            $(".tab-content>.tab-pane").removeClass("active");

            //判断当前菜单是否已经在tab中存在,如果已经存在那么不新增，直接激活
            var tab=nav_tabs.find(" > li >a[href='#"+tab_id+"']");
            if(tab&&tab.length>0){
                tab.parent("li").addClass("active");
            }else{
                //在最后一个tab上面后面增加tab
                nav_tabs.append("<li class='active'>" +
                    "<a href='#"+tab_id+"' ref='"+url+"' data-toggle='tab'>"+title+"<span class='fa fa-times' onclick='closeTab(this)'></span></a></li>");
                
            }
            //激活点击的tab对应的pane
            $("#"+tab_id).addClass("active");
        }

        /**
         * 关闭顶部tab列表
         * @param close
         */
        function closeTab(close) {
            //获取出tab_id
            var a=$(close).parent("a");
            var tab_id=a.attr("href");
            //删除导航栏
            var li=a.parent("li");
            //获取先一个元素
            var before_li=li.prev("li");
            var has_active=li.hasClass("active");
            //li 移除自身
            li.remove();
            //移除主体pan
            $(""+tab_id+"").remove();
            //如果关闭的不是当前激活的，那么不在激活其他的pane
            if(has_active){
                //取消所有激活的tab
                before_li.parent().children("li").removeClass("active");
                //取消所有激活的pane
                $(".tab-content>.tab-pane").removeClass("active");

                //激活前一个元素
                before_li.addClass("active");
                //获取到前一个的tab_id
                var before_tab_id=before_li.children("a").attr("href");
                //激活前一个tab_id 对应的pane
                $(""+before_tab_id+"").addClass("active");
            }
            
        }
    </script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <!-- Main Header -->
    <jsp:include page="header.jsp"/>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">导航菜单</li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-dashboard"></i>
                        <span>系统信息</span>
                        <span class="pull-right-container">
                            <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#" onclick="loadUrl(this)" tab_id="sys_info_TAB_ID" ref="/dwadawd/wadw/adw/a" ><i class="fa fa-circle-o"></i>系统信息v1</a></li>
                        <li><a href="#" onclick="loadUrl(this)" tab_id="sys_info2_TAB_ID" ref="/dwadawd/wadw/adw/a" ><i class="fa fa-circle-o"></i> 系统信息v2</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#" onclick="loadUrl(this)" tab_id="my_message_TAB_ID" ref="/dwadawd/wadw/adw/a">
                        <i class="fa fa-files-o"></i>
                        <span>我的消息</span>
                    </a>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa fa-files-o"></i>
                        <span>Layout Options</span>
                        <span class="pull-right-container">
                            <span class="label label-primary pull-right">2</span>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="#" onclick="loadUrl(this)" tab_id="Boxed_info1_TAB_ID" ref="/dwadawd/wadw/adw/a" ><i class="fa fa-circle-o"></i> Top Navigation</a></li>
                        <li><a href="#" onclick="loadUrl(this)" tab_id="Boxed_info2_TAB_ID" ><i class="fa fa-circle-o"></i> Boxed</a></li>
                    </ul>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div id="page_content" class="content-wrapper tab-content ">
        <%--tab title--%>
        <ul class="nav nav-tabs" id="nav-tabs_01" >
            <li class="active">
                <a href="#main_pane" data-toggle="tab">
                    <i class="fa fa-dashboard"></i>
                    <span>主页</span>
                </a>
            </li>
            <div style="position: absolute;padding-left: 6px;padding-right:2px;top: 4px">
                <div >
                    <span class="glyphicon glyphicon-menu-left"></span>
                </div>
                <div >
                    <span class="glyphicon glyphicon-menu-right"></span>
                </div>
            </div>
        </ul>
       
            
            
        <div id="main_pane" class="tab-pane active">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                     系统主页
                    <small>Optional description</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i> 系统主页</a></li>
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
