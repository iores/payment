<%@ include file="/WEB-INF/views/base/libs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>


<%--<div class="content-header ">--%>
    <%--<div style="width: 20px; ">--%>
        <%--&nbsp;--%>
    <%--</div>--%>
    <%--<ol class="breadcrumb ">--%>
        <%--<li><a href="#"><i class="fa fa-dashboard"></i> 用户管理</a></li>--%>
        <%--<li class="active">用户列表</li>--%>
    <%--</ol>--%>
<%--</div>--%>
<!-- Main content -->
<div class="content-header">
    <div class="box box-solid">
        <!-- /.box-header -->
        <div class="box-body">
            <form action="${contextPath}/user/goList.html" class="form-horizontal" method="post">
                <table class="table table-bordered table-condensed ">
                    <tr>
                        <td>用户名称</td>
                        <td>
                            <input class="form-control" name="name" value="${queryParam.name}" title="用户名称"/>
                        </td>
                        <td>用户编号</td>
                        <td>
                            <input  class="form-control" name="id" value="${queryParam.id}" title="用户编号"/>
                        </td>
                        <td>用户手机号</td>
                        <td>
                            <input class="form-control" name="phone" value="${queryParam.phone}" title="用户手机号"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            用户邮箱
                        </td>
                        <td>
                            <input class="form-control" name="email" value="${queryParam.email}" title="用户邮箱"/>
                        </td>
                        <td>用户性别</td>
                        <td>
                            <select name="sex" class="form-control" title="用户性别">
                                <option value="">所有</option>
                                <c:forEach items="${sexTypes}" var="sex">
                                    <option value="${sex}"  <c:if
                                            test="${queryParam.sex==sex}"> selected</c:if>    >${sex.displayName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>用户状态</td>
                        <td>
                            <select name="status" class="form-control" title="用户状态">
                                <option value="">所有</option>
                                <c:forEach items="${userStatus}" var="status">
                                    <option value="${status}"  <c:if
                                            test="${queryParam.status==status}"> selected</c:if>    >${status.displayName}</option>
                                </c:forEach>
                            </select>
                        </td>

                    </tr>
                    <tr>
                        <td colspan="8" style="text-align: right">
                            <button type="button" class="btn btn-primary" onclick="searchFrom(this)">查询</button>
                        </td>
                    </tr>

                </table>
            </form>
        </div>

    </div>

    <div class="box box-solid">
        <div class="box-body table-responsive no-padding">
            <table class="table table-hover table-bordered  table-striped table-condensed text-center ">
                <tr>
                    <th style="width: 5%" >序号</th>
                    <th style="width: 15%" >用户编号</th>
                    <th style="width: 15%">用户名称</th>
                    <th style="width: 5%">用户手机号</th>
                    <th style="width: 10%">用户邮箱</th>
                    <th style="width: 5%">性别</th>
                    <th style="width: 10%">状态</th>
                    <th style="width: 10%">操作</th>
                </tr>
                <c:forEach items="${page.list}" var="user" varStatus="vs">
                    <tr>
                        <td>${vs.count + (page.pageNum-1)*page.pageSize}</td>
                        <td>
                            <a href="#" ref="${contextPath}/user/goDetail.html?id=${user.id}" onclick="queryDetail(this)">${user.id}</a>
                        </td>
                        <td>${user.name}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td>${user.sex.displayName}</td>
                        <td>${user.status.displayName}</td>
                        <td>
                            <div class="btn-group">
                                <span class="glyphicon glyphicon-cog  dropdown-toggle" data-toggle="dropdown"
                                      aria-expanded="true"></span>
                                <ul class="dropdown-menu "  style="position:absolute;min-width: 10px" >
                                    <li><a href="#" ref="${contextPath}/user/goDetail.html?id=${user.id}" onclick="loadA(this)">详情</a></li>
                                    <li><a href="#" >修改</a></li>
                                    <li><a href="#" >新增登录账户</a></li>
                                </ul>
                            </div>


                        </td>
                    </tr>
                </c:forEach>

            </table>

        </div>

    </div>
    <%@ include file="/WEB-INF/views/base/pagination.jsp" %>
</div>



