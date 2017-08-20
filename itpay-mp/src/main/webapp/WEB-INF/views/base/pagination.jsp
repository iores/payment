<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: feng
  Date: 2016/7/3 0003
  Time: 11:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="<%= request.getContextPath() %>/plug/pagination/jquery.twbsPagination.js"></script>


<script type="text/javascript">
    $(function(){
        $('#pagination').twbsPagination({
            totalPages: parseInt( '<s:property value="page.allPage" />'),
            visiblePages: 7,
            first:"首页",
            prev:"上一页",
            next:"下一页",
            last:"尾页",
            startPage:parseInt( '<s:property value="page.number" />'),
            initiateStartPageClick:false,
            onPageClick: function (event,page) {
                var pageSize=$("#pageSize").val();
                page_from(page,pageSize);
            }
        });
    });

</script>


<div class=" no-margin-top">


    <div class=" col-sm-2 bigger-110 bolder" style="text-align: center">
        总共<s:property value="page.allSize"  />条,
        共<s:property value="page.allPage" /> 页
    </div>

    <div class=" col-sm-8  " style="text-align: right">
        <ul id="pagination" class="pagination no-margin  ">

        </ul>
    </div>

    <div class="col-sm-2  pull-right" >
        每页
        <s:select id="pageSize" onchange="onloadByPageSize(this)" list="{1,15,50,100}" name="page.size" value="page.size"   >
            
        </s:select>
        条
    </div>


   
</div>