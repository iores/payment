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
    <%--<div class="content-header">--%>
        <%--<div class="col-md-12">--%>
            <%--<div class="box box-solid">--%>
                <%--<!-- /.box-header -->--%>
                <%--<div class="box-body">--%>
                    <%--<table class="table table-bordered">--%>
                        <%--<tr>--%>
                            <%--<td>用户名称</td>--%>
                            <%--<td>--%>
                                <%--${user.name}--%>
                            <%--</td>--%>
                            <%--<td>用户编号</td>--%>
                            <%--<td>--%>
                                <%--${user.id}--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                        <%--<tr>--%>
                            <%--<td>用户手机号</td>--%>
                            <%--<td>--%>
                                <%--${user.phone}--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--用户邮箱--%>
                            <%--</td>--%>
                            <%--<td>--%>
                                <%--${user.email}--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                    <%--</table>--%>
                <%--</div>--%>

            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <section class="invoice">
        <!-- title row -->
        <div class="row">
            <div class="col-xs-12">
                <h2 class="page-header">
                    用户信息
                </h2>
            </div>
        </div>
        <!-- /.row -->

        <div class="row" >
            <div class="col-xs-6">
                <div class="table-responsive">
                    <table class="table " >
                        <tbody><tr>
                            <th >用户手机号:</th>
                            <td >${user.phone}</td>
                        </tr>
                        <tr>
                            <th >用户编号</th>
                            <td >${user.id}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.col -->
            <div class="col-xs-6">
                <div class="table-responsive">
                    <table class="table">
                        <tbody><tr>
                            <th >用户名称:</th>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <th>用户邮箱</th>
                            <td>${user.email}</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.col -->
        </div>
        <!-- /.row -->

        <!-- title row -->
        <div class="row">
            <div class="col-xs-12">
                <h2 class="page-header">
                    登录账号
                </h2>
            </div>
        </div>


        <!-- title row -->
        <div class="row">
            <div class="col-xs-12">
                <h2 class="page-header">
                    所属角色
                </h2>
            </div>
        </div>
        <!-- Table row -->
        <div class="row">
            <div class="col-xs-12 table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>Qty</th>
                        <th>Product</th>
                        <th>Serial #</th>
                        <th>Description</th>
                        <th>Subtotal</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>Call of Duty</td>
                        <td>455-981-221</td>
                        <td>El snort testosterone trophy driving gloves handsome</td>
                        <td>$64.50</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Need for Speed IV</td>
                        <td>247-925-726</td>
                        <td>Wes Anderson umami biodiesel</td>
                        <td>$50.00</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Monsters DVD</td>
                        <td>735-845-642</td>
                        <td>Terry Richardson helvetica tousled street art master</td>
                        <td>$10.70</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>Grown Ups Blue Ray</td>
                        <td>422-568-642</td>
                        <td>Tousled lomo letterpress</td>
                        <td>$25.99</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <!-- /.col -->
        </div>

        <!-- this row will not appear when printing -->
        <div class="row no-print">
            <div class="col-xs-12">
                <a href="invoice-print.html" target="_blank" class="btn btn-default"><i class="fa fa-print"></i> Print</a>
                <button type="button" class="btn btn-success pull-right"><i class="fa fa-credit-card"></i> Submit Payment
                </button>
                <button type="button" class="btn btn-primary pull-right" style="margin-right: 5px;">
                    <i class="fa fa-download"></i> Generate PDF
                </button>
            </div>
        </div>
    </section>

</div>

