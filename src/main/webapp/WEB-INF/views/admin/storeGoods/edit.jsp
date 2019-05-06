<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

$(function() {
	$('#storeGoodsEditForm').form({
        url : '${path }/storeGoods/edit',
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
            	storeGoodsDataGrid.datagrid('reload');
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
        <form id="storeGoodsEditForm" method="post">
        	<input type="hidden" name="id" value="${obj.id }"/>
        	<input type="hidden" name="specId" value="${obj.specId }"/>
        	<input type="hidden" name="goodsId" value="${obj.goodsId }"/>
            <table class="grid">
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">门店名称</td>
                    <td>
                    	${obj.storeName }
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td>
                    	${obj.goodsName }
                    </td>
                </tr>
                <!-- <tr>
                	<td align="right" bgcolor="#f8f8f8">可分配库存</td>
                    <td id="fpa">
                    	${fpa }
                    </td>
                </tr> -->
                <tr>
                	<td align="right" bgcolor="#f8f8f8">当前库存</td>
                    <td id="fpa">
                    	${obj.goodsAmount }
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">分配库存</td>
                    <td>
                    	<input name="goodsAmount" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="0">
                    </td>
                </tr>
                
            </table>
        </form>
    </div>
</div>
