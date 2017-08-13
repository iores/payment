<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<div class="main-content-inner">

    <div class="content-header ">

        <div style="width: 20px; ">
            &nbsp;
        </div>
        <ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> 用户管理</a></li>
            <li class="active">用户详情</li>
        </ol>
    </div>
    <!-- Main content -->
    <div class="content-header">
        <div class="col-md-12">
            <div class="box box-solid">
                <!-- /.box-header -->
                <div class="box-body">
                    <table class="table table-bordered">
                        <tr>
                            <td>用户名称</td>
                            <td>
                                ${user.name}
                            </td>
                            <td>用户编号</td>
                            <td>
                                ${user.id}
                            </td>
                        </tr>

                        <tr>
                            <td>用户手机号</td>
                            <td>
                                ${user.phone}
                            </td>
                            <td>
                                用户邮箱
                            </td>
                            <td>
                                ${user.email}
                            </td>
                        </tr>

                    </table>
                </div>

            </div>
        </div>
    </div>



</div>


</div>