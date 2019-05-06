<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#expresaAddForm').form({
            url : '${path }/order/logisticsAdd?orderId=${orderId}',
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
                	orderDataGrid.datagrid('reload');
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
        <form id="expresaAddForm" method="post">
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">运单号</td>
                    <td><input name="expressNo" type="text" placeholder="请输入快递单号" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">物流公司</td>
                    <td><input name="note" type="text" placeholder="请输入物流公司"class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
            </table>
        </form>
    </div>
</div>