<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#answerAddForm').form({
            url : '${path }/questProblem/addAnswer',
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
                	answerDataGrid.datagrid('reload');
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
        <form id="answerAddForm" method="post" >
        	<input name="problemId" type="hidden" value="${problemId }"/>
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">答案:</td>
                    <td colspan="2"><input name="title" type="text" placeholder="请输入答案" class="easyui-validatebox span2" value="" data-options="required:true"></td>
                </tr>
                <tr>
            		<td align="right" bgcolor="#f8f8f8">分数:</td>
                    <td><input name="score" type="number" placeholder="请输入分数" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">排序</td>
                    <td><input name="seq2" type="text" placeholder="请输入答案序号" class="easyui-validatebox span2" value="" data-options="required:true"></td>
                </tr>
            </table>
        </form>
    </div>
</div>