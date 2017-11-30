<%--
<%@ include file="/WEB-INF/views/base/libs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<script type="application/javascript">
    $(function () {
        $('#example1').DataTable({
            'bSort': false,
            "dom": 'rt<"bottom"lip><"clear">',
            "language": {
                "lengthMenu": "每页 _MENU_ 条记录",
                "zeroRecords": "没有找到记录",
                "info": "第 _PAGE_ 页 ( 总共 _PAGES_ 页 )",
                "infoEmpty": "无记录",
                'paginate': {
                    'first':      '第一页',
                    'last':       '最后一页',
                    'next':       '下一页',
                    'previous':   '上一页'
                }
            }
        });
    });

</script>

<div class="content-header ">

    <div style="width: 20px; ">
        &nbsp;
    </div>
    <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> 用户管理</a></li>
        <li class="active">用户列表</li>
    </ol>
</div>
<!-- Main content -->
<div class="content-header">
    <div class="box">
        <div class="box-header">
            <h3 class="box-title">Data Table With Full Features</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <table id="example1" class="  table table-bordered table-striped  table-hover table-condensed " >
                <thead>
                <tr>
                    <th>序号</th>
                    <th>用户名称</th>
                    <th>用户编号</th>
                    <th>用户手机号</th>
                    <th>用户邮箱</th>
                    <th>性别</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.list}" var="user" varStatus="vs">
                    <tr>
                        <td>${vs.count + (page.pageNum-1)*page.pageSize}</td>
                        <td>${user.name}</td>
                        <td>${user.id}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>${user.sex.displayName}</td>
                        <td>${user.status.displayName}</td>
                        <td style="width: 10%">
                            <div class="btn-group">
                                <span class="glyphicon glyphicon-cog  dropdown-toggle" data-toggle="dropdown" aria-expanded="false"></span>
                                <ul class="dropdown-menu" style="min-width: 1px;">
                                    <li><a href="#">查看详情</a></li>
                                    <li><a href="#">修改</a></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- /.box-body -->
    </div>
    <!-- /.box -->
</div>




--%>
