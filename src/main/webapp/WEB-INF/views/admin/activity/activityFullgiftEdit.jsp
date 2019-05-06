<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
	$('#activityFullgiftEditForm').form({
        url : '${path }/activity/editFullgift',
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
            	activityFullgiftDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
	var storeId = '${fullgift.storeId}';
	var selectUrl = '${path }/storeGoods/amount';
	if(storeId != null && storeId != 0){
		selectUrl = '${path }/storeGoods/storeAmount';
	}
	$.post(selectUrl, {
		id : '${fullgift.goodsId }',
		storeId : '${fullgift.storeId }'
	}, function(result) {
		$("#fpa").html(result);
	}, 'JSON');
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="padding: 3px;" >
        <form id="activityFullgiftEditForm" method="post">
        <input type="hidden" name="id" value="${fullgift.id }"/>
        	<input type="hidden" name="activityId" value="${activityId }"/>
        	<input type="hidden" name="storeId" value="${fullgift.storeId }"/>
        	<input type="hidden" name="goodsId" value="${fullgift.goodsId }"/>
        	<input type="hidden" name="goodsNum" value="${fullgift.goodsNum }"/>
            <table class="grid">
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">门店名称</td>
                    <td>
                    	${fullgift.storeName }
                    </td>
                </tr>
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td>
                    	${fullgift.goodsName }
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">条件</td>
                    <td>
                    	<input name="fullPrice" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="${fullgift.fullPrice }">
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">数量</td>
                    <td>
                    	${fullgift.goodsNum }
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">可分配库存</td>
                    <td id="fpa">
                    	0
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">当前库存</td>
                    <td>
                    	${fullgift.amount }
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">分配库存</td>
                    <td>
                    	<input name="amount" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="0">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
