<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<div class="main-content-inner">
    <div class="breadcrumbs" id="breadcrumbs">

        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                用户管理
            </li>
            <li class="active">用户列表</li>
        </ul>


    </div>

    <div class="page-content">

        <div class="widget-box">
            <div class="widget-header">
                <h4 class="widget-title">查询</h4>
            </div>

            <div class="widget-body">
                <div class="widget-main">
                    <form class="form-horizontal" id="frm" role="form" action="userInfo/goMain.do">
                        <div class="form-group">
                            <label class="col-sm-1 control-label bolder" for="user_no" >用户手机号:</label>
                            <div class="col-sm-2">
                                <input maxlength="20" class="form-control" id="user_no" type="text"  name="search.phone" value="<s:property value="search.phone"/>" />
                            </div>
                            <div  class="col-sm-1"></div>
                            <label class="col-sm-1 control-label bolder" for="user_name">用户名称:</label>
                            <div class="col-sm-2">
                                <input maxlength="20" class="form-control" id="user_name" type="text"  name="search.name" value="<s:property value="search.name"/>"  />
                            </div>
                            <div  class="col-sm-1"></div>

                            <div  class="col-sm-1"></div>
                        </div>


                        <div class=" form-group ">
                            <div  class="col-sm-1"></div>
                            <label class="col-sm-1 control-label bolder" for="user_emain">邮箱:</label>
                            <div class="col-sm-2">
                                <input maxlength="120" class="form-control" id="user_emain" type="text"  name="search.email" value="<s:property value="search.email"/>"  />
                            </div>
                            <div class="col-sm-4">
                            </div>
                            <div class="col-sm-1">
                                <button type="button" class="btn btn-purple btn-sm  " onclick="from_onload('frm')" >
                                    <span class="ace-icon fa fa-search icon-on-right bigger-110"></span>
                                    查询
                                </button>
                            </div>
                        </div>



                    </form>

                </div>
            </div>


        </div>





        <!-- PAGE CONTENT BEGINS -->
        <div class="row">
            <div class="col-xs-12">
                <table class="table table-striped table-bordered table-hover "  style="word-break:break-all; word-wrap:break-word;">
                    <thead>
                    <tr>
                        <th class="center bigger-110">序号</th>
                        <th class="center bigger-110">用户手机号</th>
                        <th class="center bigger-110">用户名称</th>
                        <th class="center   bigger-110">性别</th>
                        <th class="center   bigger-110">用户邮箱</th>
                        <th class="center   bigger-110">用户状态</th>

                        <th class="center bigger-110">操作</th>
                    </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td class="center">
                                1
                            </td>

                            <td class="center">
                                1
                            </td>
                            <td class="center">
                              1
                            </td>
                            <td class="center">
                                1
                            </td>
                            <td class="center bigger-100">
                              1
                            </td>
                            <td class=" center">
                                1
                            </td>

                            <td class="center">
                                <div class="btn-group">
                                    <div class="inline pos-rel">
                                        <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown" data-position="auto">
                                            <i class="ace-icon fa fa-cog icon-only bigger-120"></i>
                                        </button>

                                        <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
                                            <li>
                                                <a href="javascript:void(0)" class="tooltip-info" onclick='goOtherView("userInfo/goDetail.do?userInfo.id=${id}")' data-rel="tooltip" title="查看详情">
                                                    <span class="blue">
                                                        <i class="ace-icon fa fa-search-plus bigger-120"></i>
                                                        查看详情
                                                    </span>
                                                </a>
                                            </li>

                                            <li>
                                                <a href="#" class="tooltip-success" data-rel="tooltip" title="修改">
                                                    <span class="green">
                                                        <i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
                                                         修改信息
                                                    </span>
                                                </a>
                                            </li>


                                                <%--        <li>
                                                            <a href="#" class="tooltip-error" data-rel="tooltip" title="Delete">
                                                                <span class="red">
                                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                                    删除
                                                                </span>
                                                            </a>
                                                        </li>--%>
                                        </ul>
                                    </div>
                                </div>
                            </td>
                        </tr>


                    </tbody>
                </table>

            </div><!-- /.span -->

        </div><!-- /.row -->





    </div>
</div>
