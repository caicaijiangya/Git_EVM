<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

$(function() {
	$('#editClassifyForm').form({
        url : '${path }/goodsOther/editClassify',
        onSubmit : function() {
            progressLoad();
            var isValid = $(this).form('validate');
            if (!isValid) {
                progressClose();
            }
            return isValid;
        },
        success : function(result) {
            progressClose();
            result = $.parseJSON(result);
            if (result.success) {
            	classifyDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="editClassifyForm" method="post">
        	<input type="hidden" name="id" value="${obj.id }"/>
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">名称</td>
                    <td><input name="name" value="${obj.name }" type="text" placeholder="请输入名称" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">序号</td>
                    <td>
                    	<input name="seq" value="${obj.seq }" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true">
                    </td>
                </tr>
                
            </table>
        </form>
    </div>
</div>
