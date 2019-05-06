<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#questConclusionEditForm').form({
            url : '${path }/questConclusion/edit',
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
                	questConclusionDataGrid.datagrid('reload');
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
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="questConclusionEditForm" method="post" >
        	<input name="id" type="hidden" value="${questConclusion.id }"/>
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">英文组合标识</td>
                    <td>
                    	<input name="key" type="text" placeholder="请输入英文组合标识" class="easyui-validatebox span2" value="${questConclusion.key }" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">结论名称</td>
                    <td>
                    	<input name="name" type="text" placeholder="请输入结论名称" class="easyui-validatebox span2" value="${questConclusion.name }" data-options="required:true">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>