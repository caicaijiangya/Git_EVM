<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#userIntegralAddForm').form({
            url : '${path }/wxUserIntegral/addIntegral',
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
                	tXyUserIntegralDataGrid.datagrid('reload');
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
        <form id="userIntegralAddForm" method="post" >
        	<input type="hidden" name="openId" value="${openId }"/>
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">当前积分:</td>
                    <td>${integral }</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">增加积分:</td>
                    <td><input name="integral" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="0"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">备注:</td>
                    <td><textarea name="note" rows="4" style="width:80%;"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>