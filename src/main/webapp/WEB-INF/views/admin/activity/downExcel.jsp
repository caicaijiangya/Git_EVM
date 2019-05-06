<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
	$('#questBargainExportForm').form({
        url : '${path }/activity/downLoadBargain',
        onSubmit : function() {
            var isValid = $(this).form('validate');
            if (!isValid) {
            	
            }
            return isValid;
        }
    });
   
});

</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="margin: 20px;">
	<div class="easyui-panel" title="砍价数据" style="width:600px;">
		<div style="padding:10px 60px 20px 60px">
		    <form id="questBargainExportForm" method="post">
		    	<table class="grid">
		    		<tr>
		    			<td align="right" bgcolor="#f8f8f8">开始日期:</td>
		    			<td><input type="text" name="startDate" id="wj_start_date" class="easyui-datebox" data-options="required:true" value="${startDate }"></input></td>
		    			<td align="right" bgcolor="#f8f8f8">结束日期:</td>
		    			<td><input type="text" name="endDate" id="wj_end_date" class="easyui-datebox" data-options="required:true" value="${endDate }"></input></td>
		    		</tr>
		    	</table>
		    	
		    	<div style="text-align:center;margin-top: 20px;">
		    		<input type="submit" class="easyui-linkbutton" style="width: 100px;height: 30px;" value="导出"/>
			    </div>
		    </form>
		    
	    </div>
	</div>
</div>
