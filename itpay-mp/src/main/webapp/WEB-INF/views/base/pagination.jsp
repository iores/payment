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
    $(function(){
        $('#pagination').twbsPagination({
            totalPages: parseInt( ${page.pages<1?1:page.pages}),
            visiblePages: 7,
            first:"首页",
            prev:"上一页",
            next:"下一页",
            last:"尾页",
            startPage:parseInt( '${page.pageNum}'),
            initiateStartPageClick:false,
            onPageClick: function (event,page) {
                var pageSize=$("#pageSize").val();
                page_from(page,pageSize,this);
            }
        });
    });

</script>

<div class=" no-margin-top">


    <div class=" col-sm-2 bigger-110 bolder" style="text-align: center">
        总共${page.total}条,
        共${page.pages} 页
    </div>

    <div class=" col-sm-9  " style="text-align: right">
        <ul id="pagination" class="pagination no-margin  ">

        </ul>
    </div>

    <div class="col-sm-1 " >
        每页
        <select name="size" id="pageSize"  >
            
            <option value="1"  <c:if test="${page.pageSize==1}">selected</c:if>  >1</option>
            <option value="10" <c:if test="${page.pageSize==15}">selected</c:if>  >15</option>
            <option value="20" <c:if test="${page.pageSize==20}">selected</c:if> >20</option>
            <option value="100" <c:if test="${page.pageSize==100}">selected</c:if> >100</option>
        </select>
        条
    </div>


   
</div>
