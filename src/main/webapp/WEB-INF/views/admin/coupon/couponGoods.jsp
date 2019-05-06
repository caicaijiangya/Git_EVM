<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#couponGoodsEditForm').form({
            url : '${path }/coupon/editCouponGoods',
            onSubmit : function() {
                progressLoad();
                var goodsId = $("#goodsId").combogrid("getValues");
                $("input[name='goodsId']").val(goodsId);
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
        
        $("#goodsId").combogrid({
        	required:true,
    		multiple:true,
    		multiline:true,
    		idField: 'id',
    		textField:	'goodsName',
    		mode: 'remote',
    		url: 'goodsOther/dataGrid',
    		method: 'post',
    		queryParams:{type:1},
    		nowrap: false,
			columns: [[
				{field:'ck',checkbox:true},
				{field:'id',title:'ID',width:80,hidden:true},
				{field:'goodsName',title:'全选',width:300},
				{field:'brandName',title:'品牌',width:100}
			]],
			fitColumns: true,
			onLoadSuccess: function () {
				var goodsIds = eval('(${list })');
				$('#goodsId').combogrid('setValue', goodsIds);
			}
    	});
    });
    
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="couponGoodsEditForm" method="post" >
        	<input type="hidden" name="goodsId" value=""/>
        	<input type="hidden" name="id" value="${id }"/>
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品</td>
                    <td>
                    	<select class="easyui-combogrid" id="goodsId" style="width:400px;height: 150px;"></select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>