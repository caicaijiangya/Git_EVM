<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
        $('#orderEditForm').form({
            url : '${path }/order/edit',
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="orderEditForm" method="post">
        	<input name="id" type="hidden" value="${order.id }"/>
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">订单状态</td>
                    <td>
                		<select name="status"  data-placeholder="请选择" style="width:210px;" id="status" class="select"> 
    						<option value="">请选择</option>
    						<option value="0" <c:if test="${order.status==0 }">selected="selected"</c:if>>待付款</option>
							<option value="1" <c:if test="${order.status==1 }">selected="selected"</c:if>>待发货</option>
							<option value="2" <c:if test="${order.status==2 }">selected="selected"</c:if>>待取货</option>
							<option value="3" <c:if test="${order.status==3 }">selected="selected"</c:if>>待退款</option>
							<option value="4" <c:if test="${order.status==4 }">selected="selected"</c:if>>已退款</option>
							<option value="5" <c:if test="${order.status==5 }">selected="selected"</c:if>>交易取消</option>
							<option value="6" <c:if test="${order.status==6 }">selected="selected"</c:if>>交易完成</option>
							<option value="7" <c:if test="${order.status==7 }">selected="selected"</c:if>>退货待审核</option>
							<option value="8" <c:if test="${order.status==8 }">selected="selected"</c:if>>退货待收货</option>
						</select>
                	</td>
                </tr>
            </table>
        </form>
    </div>
</div>