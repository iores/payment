<%@ include file="/WEB-INF/views/base/libs.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: feng
  Date: 2016/7/3 0003
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="<%= request.getContextPath() %>/plugs/other/pagination/jquery.twbsPagination.js"></script>


<script type="text/javascript">
    $(function () {
        var pages =${page.pages};
        var pageNum =${page.pageNum};
        if (pageNum > pages) {
            if (pages > 0) {
                pageNum = pages;
            } else {
                pages = 1;
                pageNum = 1;
            }
        }
        $('#pagination').twbsPagination({
            totalPages: pages,
            visiblePages: 4,
            first: "首页",
            prev: "上一页",
            next: "下一页",
            last: "尾页",
            startPage: pageNum,
            initiateStartPageClick: false,
            onPageClick: function (event, page) {
                var pageSize = $("#pageSize").val();
                page_from(page, pageSize, this);
            }
        });
    });

</script>

<div class="box box-solid">
    <div class="box-body table-responsive no-padding ">




        <div class=" col-sm-12  " >
            <div class=" col-sm-5 pagination " >
                总共${page.total}条记录,
                共${page.pages} 页
            </div>
            <ul id="pagination" class="pagination col-sm-5" >

            </ul>


            <div class="col-sm-2 pagination " >
                每页
                <select name="size" id="pageSize">

                    <option value="10"
                            <c:if test="${page.pageSize==10}">selected</c:if>  >10
                    </option>
                    <option value="20"
                            <c:if test="${page.pageSize==20}">selected</c:if> >20
                    </option>
                    <option value="100"
                            <c:if test="${page.pageSize==100}">selected</c:if> >100
                    </option>
                </select>
                条
            </div>
        </div>


    </div>
</div>

