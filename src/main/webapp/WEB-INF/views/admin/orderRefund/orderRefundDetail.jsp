<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<style type="text/css">
	.imgclass{    
		width: 70px;
	    height: auto;
	    margin: 5px;
	  }
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="padding: 3px;overflow-y:scroll;">
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">订单号：</td>
                    <td>${refund.orderNo }</td>
                    <td align="right" bgcolor="#f8f8f8">下单人：</td>
                    <td>${refund.userName }</td>
                </tr> 
                <tr>
                    <td align="right" bgcolor="#f8f8f8">支付单号：</td>
                    <td>${refund.transNo }</td>
                    <td align="right" bgcolor="#f8f8f8">退款单号：</td>
                    <td>${refund.refundNo }</td>
                </tr> 
                <tr>
                    <td align="right" bgcolor="#f8f8f8">退货状态：</td>
                    <td>
						<c:choose>
                    		<c:when test="${refund.status == 1}">退货申请</c:when>
                    		<c:when test="${refund.status == 2}">待收货</c:when>
                    		<c:when test="${refund.status == 3}">待退款</c:when>
                    		<c:when test="${refund.status == 4}">已退款</c:when>
                    		<c:when test="${refund.status == 11}">拒绝退货</c:when>
                    		<c:when test="${refund.status == 12}">拒绝退款</c:when>
                    	</c:choose>
					</td>
                    <td align="right" bgcolor="#f8f8f8">退货/退款说明：</td>
                    <td>${refund.refundDesc }</td>
                </tr> 
                <tr>
                	<td align="right" bgcolor="#f8f8f8">支付金额：</td>
                    <td><fmt:formatNumber value="${refund.totalBalances }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></td>
                    <td align="right" bgcolor="#f8f8f8">退款金额：</td>
                    <td><fmt:formatNumber value="${refund.balances }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">买家留言：</td>
                    <td>${refund.note }</td>
                    <td align="right" bgcolor="#f8f8f8">卖家留言：</td>
                    <td>${refund.auditDesc }</td>
                </tr>
            </table>
            
            <!-- 具体商品列表 -->
            <table class="grid">
            	<tr>
                    <td align="center" bgcolor="#f8f8f8" width="5%">序号</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">商品缩略图</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">商品名称</td>
                    <td align="center" bgcolor="#f8f8f8" width="10%">商品单价</td>
                    <td align="center" bgcolor="#f8f8f8" width="7%">数量</td>
                </tr> 
                <c:forEach items="${refund.details}" var="item" varStatus="status">
                	<tr>
	                    <td align="center">${status.index + 1}</td>
	                    <td align="center"><img class="imgclass" src="${item.goodsImages}" /></td>
	                    <td align="center">${item.goodsName }</td>
	                    <td align="center">${item.goodsPrice }</td>
	                    <td align="center">${item.goodsNum }</td>
                	</tr> 
                </c:forEach>
            </table>
            
            <!-- 具体赠品列表 -->
            <table class="grid">
            	<tr>
                    <td align="center" bgcolor="#f8f8f8" width="5%">序号</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">赠品缩略图</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">赠品名称</td>
                    <td align="center" bgcolor="#f8f8f8" width="7%">数量</td>
                </tr> 
                <c:forEach items="${refund.gifts}" var="item" varStatus="status">
                	<tr>
	                    <td align="center">${status.index + 1}</td>
	                    <td align="center"><img class="imgclass" src="${item.goodsImages}" /></td>
	                    <td align="center">${item.goodsName }</td>
	                    <td align="center">${item.goodsNum }</td>
                	</tr> 
                </c:forEach>
            </table>
    </div>
</div>