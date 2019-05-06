<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#collageParameterEditForm').form({
            url : '${path }/activity/editParameter',
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
                	agoodsDataGrid.datagrid('reload');
                	parent.$.modalDialog.handler.dialog('close');
                	showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
    })

</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scroll;padding: 3px;" >
        <form id="collageParameterEditForm" method="post">
        	<input name="id" type="hidden" value="${activityGoods.id }"/>
        	<input name="goodsId" type="hidden" value="${activityGoods.goodsId }"/>
        	<input name="goodsRemAmount" type="hidden" value="${activityGoods.goodsRemAmount }"/>
        	<input name="storeId" type="hidden" value="${activityGoods.storeId }"/>
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">活动价格:</td>
                    <td><input name="activityPrice" type="number" placeholder="请输入活动价格" class="easyui-validatebox span2" data-options="required:true" value="${activityGoods.activityPrice }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">限购数量:</td>
                    <td><input name="buyNum" type="number" placeholder="请输入限购数量" class="easyui-validatebox span2" data-options="required:true" value="${activityGoods.buyNum }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">商品可用库存:</td>
                    <td><input name="goodsAmount" style="border: 0px;outline:none;cursor: pointer;" readonly="readonly" type="number" class="easyui-validatebox span2" data-options="required:true" value="${activityGoods.goodsAmount }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">活动库存:</td>
                    <td><input name="activityAmount" style="border: 0px;outline:none;cursor: pointer;" readonly="readonly" type="number" class="easyui-validatebox span2" data-options="required:true" value="${activityGoods.activityAmount }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">增减库存:</td>
                    <td>
                    	<input name="amount" value="0" type="number" placeholder="请输入需改变的库存量" class="easyui-validatebox span2"  value="">
                    	<span>*"增减库存"改变的是"活动库存"的量,活动库存不得大于商品可用库存</span>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">是否免运费</td>
                    <td>
                    	<input name="isFreeFreight" value="0" type="radio" ${activityGoods.isFreeFreight==0?'checked':'' }>否
                    	<input name="isFreeFreight" value="1" type="radio" ${activityGoods.isFreeFreight==1?'checked':'' }>是
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>