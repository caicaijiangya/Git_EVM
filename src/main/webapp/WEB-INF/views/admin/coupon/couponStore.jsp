<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#couponStoreEditForm').form({
            url : '${path }/coupon/editCouponStore',
            onSubmit : function() {
                progressLoad();
                var storeId = $("#storeId").combogrid("getValues");
                $("input[name='storeId']").val(storeId);
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
                	couponDataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                    showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
        
        $("#storeId").combogrid({
        	required:true,
    		multiple:true,
    		multiline:true,
    		idField: 'id',
    		textField:	'storeName',
    		url: 'goodsOther/storeGrid',
    		method: 'post',
    		nowrap: false,
			columns: [[
				{field:'ck',checkbox:true},
				{field:'id',title:'ID',width:80,hidden:true},
				{field:'storeName',title:'全选',width:120}
			]],
			fitColumns: true,
			onLoadSuccess: function () {
				var storeIds = eval('(${list })');
				$('#storeId').combogrid('setValue', storeIds);
			}
    	});
    });
    
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="couponStoreEditForm" method="post" >
        	<input type="hidden" name="storeId" value=""/>
        	<input type="hidden" name="id" value="${id }"/>
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">门店</td>
                    <td>
                    	<select class="easyui-combogrid" id="storeId" style="width:300px;height: 150px;"></select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>