<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#dictAddForm').form({
            url : '${path }/dict/add',
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
                	dictDataGrid.datagrid('reload');
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
        <form id="dictAddForm" method="post">
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">字典编码</td>
                    <td><input name="dictCode" type="text" placeholder="请输入字典 编码" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">字典名称</td>
                    <td><input name="dictValue" type="text" placeholder="请输入字典名称" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">排序</td>
                    <td><input name="indexs" value="0" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:true"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">备注</td>
                    <td colspan="3"><textarea name="note" rows="4" style="width:95%;"></textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>