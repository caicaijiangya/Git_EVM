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
        <input name="id" type="hidden"  value="${order.id}">
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">订单号：</td>
                    <td>${order.orderNo }</td>
                    <td align="right" bgcolor="#f8f8f8">下单人：</td>
                    <td>${order.userName }</td>
                </tr> 
                <tr>
                    <td align="right" bgcolor="#f8f8f8">订单类型：</td>
                    <td>
                    	<c:choose>
                    		<c:when test="${order.orderType ==0 }">普通</c:when>
                    		<c:when test="${order.orderType ==1 }">秒杀</c:when>
                    		<c:when test="${order.orderType ==2 }">拼团</c:when>
                    		<c:when test="${order.orderType ==3 }">特价</c:when>
                    		<c:when test="${order.orderType ==4 }">积分兑换</c:when>
                    		<c:when test="${order.orderType ==5 }">砍价</c:when>
                    		<c:when test="${order.orderType ==6 }">满减</c:when>
                    	</c:choose>
                    </td>
                    <td align="right" bgcolor="#f8f8f8">订单状态：</td>
                    <td>
                    	<c:choose>
                    		<c:when test="${order.status == 0}"><span style="color:red;">待付款</span></c:when>
                    		<c:when test="${order.status == 1}">待发货</c:when>
                    		<c:when test="${order.status == 2}">待取货</c:when>
                    		<c:when test="${order.status == 3}">待退款</c:when>
                    		<c:when test="${order.status == 4}">已退款</c:when>
                    		<c:when test="${order.status == 5}">交易取消</c:when>
                    		<c:when test="${order.status == 6}">交易完成</c:when>
                    		<c:when test="${order.status == 7}">退货待审核</c:when>
                    		<c:when test="${order.status == 8}">退货待收货</c:when>
                   		</c:choose>
                    </td>
                </tr> 
                <tr>
                	<td align="right" bgcolor="#f8f8f8">取货方式：</td>
                    <td>
                      	<c:choose>
                    		<c:when test="${order.takeStyle == 0}">物流配送</c:when>
                    		<c:when test="${order.takeStyle == 1}">到店取货</c:when>
                    		<c:otherwise>其他</c:otherwise>
                    	</c:choose>
                 	</td>
                    <td align="right" bgcolor="#f8f8f8">支付方式：</td>
                    <td>
                      	<c:choose>
                    		<c:when test="${order.payMoneyStyle == 0}">微信安全支付</c:when>
                    		<c:when test="${order.payMoneyStyle == 1}">到店现付</c:when>
                    		<c:otherwise>其他</c:otherwise>
                    	</c:choose>
                 	</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8" width="20%">订单总额(元)：</td>
                    <td>
                      <fmt:formatNumber value="${order.totalBalances }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>
                    </td>
                    <td align="right" bgcolor="#f8f8f8" width="20%">优惠金额(元)：</td>
                    <td>
                      <fmt:formatNumber value="${order.disountPrice }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>
                      <c:choose>
                      	<c:when test="${order.preList != null}">
                      		<c:forEach items="${order.preList}" var="item" varStatus="status">
			                	/ <fmt:formatNumber value="${item.prePrice }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber> 
			                </c:forEach>
                      	</c:when>
                      	<c:otherwise>/ 0.00 </c:otherwise>
                      </c:choose>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8" width="20%">实付款(元)：</td>
                    <td>
                      <fmt:formatNumber value="${order.trans.balances }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>
                    </td>
                    <c:choose>
                    	<c:when test="${order.address != null}">
                    		<td align="right" bgcolor="#f8f8f8">快递单号</td>
                    		<td>${order.address.expressNo }</td>
                    	</c:when>
                    </c:choose>
                    
                </tr>
                <c:if test="${order.address != null}">
                <tr>
                	<td align="right" bgcolor="#f8f8f8" width="20%">收货人：</td>
                    <td>
                      ${order.address.name }[${order.address.mobileNo }]
                    </td>
                    <td align="right" bgcolor="#f8f8f8">
                    	收货地址：
                    </td>
                    <td>${order.address.provinceName }-${order.address.cityName }-${order.address.areaName }-${order.address.addressDetail }</td>
                </tr>
                </c:if>
                <c:if test="${order.store != null}">
                <tr>
                	<td align="right" bgcolor="#f8f8f8" width="20%">门店：</td>
                    <td>
                      ${order.store.storeName }
                    </td>
                    <td align="right" bgcolor="#f8f8f8">
                    	门店地址：
                    </td>
                    <td>${order.store.provinceName }-${order.store.cityName }-${order.store.areaName }-${order.store.addressDetail }</td>
                </tr>
                </c:if>
            </table>
            
            <!-- 具体商品列表 -->
            <table class="grid">
            	<tr>
                    <td align="center" bgcolor="#f8f8f8" width="5%">序号</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">商品缩略图</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">商品名称</td>
                    <td align="center" bgcolor="#f8f8f8" width="10%">商品单价(元)</td>
                    <td align="center" bgcolor="#f8f8f8" width="7%">购买数量</td>
                    <td align="center" bgcolor="#f8f8f8" width="10%">规格</td>
                </tr> 
                <c:forEach items="${order.details}" var="item" varStatus="status">
                	<tr>
	                    <td align="center">${status.index + 1}</td>
	                    <td align="center"><img class="imgclass" src="${item.goodsImages}" /></td>
	                    <td align="center">${item.goodsName }</td>
	                    <td align="center">
	                       <fmt:formatNumber value="${item.goodsPrice }" pattern="0.00" maxFractionDigits="2"></fmt:formatNumber>
	                    </td>
	                    <td align="center">${item.goodsNum }</td>
	                    <td align="center">${item.goodsSpec }</td>
                	</tr> 
                </c:forEach>
            </table>
            <!-- 具体赠品列表 -->
            <table class="grid" style="${order.giftList == null || order.giftList.size() == 0?'display: none;':''}">
            	<tr>
                    <td align="center" bgcolor="#f8f8f8" width="5%">序号</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">赠品缩略图</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">赠品名称</td>
                    <td align="center" bgcolor="#f8f8f8" width="7%">赠品数量</td>
                </tr> 
                <c:forEach items="${order.giftList}" var="item" varStatus="status">
                	<tr>
	                    <td align="center">${status.index + 1}</td>
	                    <td align="center"><img class="imgclass" src="${item.goodsImage}" /></td>
	                    <td align="center">${item.goodsName }</td>
	                    <td align="center">${item.goodsNum }</td>
                	</tr> 
                </c:forEach>
            </table>
            
            <!-- 具体折扣列表 -->
            <table class="grid" style="${order.discountList == null || order.discountList.size() == 0?'display: none;':''}">
            	<tr>
                    <td align="center" bgcolor="#f8f8f8" width="5%">序号</td>
                    <td align="center" bgcolor="#f8f8f8" width="12%">打折商品名称</td>
                    <td align="center" bgcolor="#f8f8f8" width="7%">折扣</td>
                    <td align="center" bgcolor="#f8f8f8" width="7%">优惠金额</td>
                </tr> 
                <c:forEach items="${order.discountList}" var="item" varStatus="status">
                	<tr>
	                    <td align="center">${status.index + 1}</td>
	                    <td align="center">${item.goodsName }</td>
	                    <td align="center">${item.prePrice*10 }折</td>
	                    <td align="center">${item.price }</td>
                	</tr> 
                </c:forEach>
            </table>
    </div>
</div>