<%@ include file="/WEB-INF/views/base/libs.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


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
            <form action="${contextPath}/user/goList.html" method="post">
                <table class="table table-bordered table-condensed ">
                    <tr>
                        <td>用户名称</td>
                        <td>
                            <input name="name" value="${queryParam.name}"/>
                        </td>
                        <td>用户编号</td>
                        <td>
                            <input name="id" value="${queryParam.id}"/>
                        </td>
                        <td>用户手机号</td>
                        <td>
                            <input name="phone" value="${queryParam.phone}"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            用户邮箱
                        </td>
                        <td>
                            <input name="email" value="${queryParam.email}"/>
                        </td>
                        <td>用户性别</td>
                        <td>
                            <select name="sex">
                                <option value="">所有</option>
                                <c:forEach items="${sexTypes}" var="sex">
                                    <option value="${sex}"  <c:if
                                            test="${queryParam.sex==sex}"> selected</c:if>    >${sex.displayName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>用户状态</td>
                        <td>
                            <select name="status">
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
            <table class="table table-hover table-bordered  table-striped table-condensed ">
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
                                <span class="glyphicon glyphicon-cog  dropdown-toggle" data-toggle="dropdown"
                                      aria-expanded="false"></span>
                                <ul class="dropdown-menu" style="min-width: 1px;">
                                    <li><a href="#">查看详情</a></li>
                                    <li><a href="#">修改</a></li>
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



