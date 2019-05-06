<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
	$('#activityFullgiftAddForm').form({
        url : '${path }/activity/addFullgift',
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
	var storeId = '${storeId}';
	initGoods(storeId);
	function initGoods(storeId){
		var url = 'goodsOther/dataGrid';
		var selectUrl = '${path }/storeGoods/amount';
		if(storeId != 0){
			url = 'goodsOther/storeDataGrid?storeId='+storeId;
			selectUrl = '${path }/storeGoods/storeAmount';
		}
		$("#goodsId").combogrid({
			panelWidth: 500,
			panelHeight: 300,
			required:true,
			idField: 'id',
			textField:	'goodsName',
			mode: 'remote',
			url: url,
			method: 'post',
			nowrap: false,
			columns: [[
				{field:'id',title:'id',width:80,hidden:true},
				{field:'goodsImage',title:'商品图片',width:80,align:'center',
					formatter:function(value,row,index){
						return '<img src='+value+' width=80 height=80/>';
					}
				},
				{field:'goodsName',title:'商品名称',width:200,align:'center'},
				{field:'specName',title:'规格名称',width:80,align:'center'},
				{field:'brandName',title:'品牌',width:80,align:'center'},
				{field:'goodsPrice',title:'价格',width:80,align:'center'}
			]],
			fitColumns: true,
			onSelect: function (rowIndex, rowData) {
				$.post(selectUrl, {
					id : rowData.id,
					storeId : storeId
				}, function(result) {
					$("#fpa").html(result);
				}, 'JSON');
			}
		});
	}
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="padding: 3px;" >
        <form id="activityFullgiftAddForm" method="post">
        	<input type="hidden" name="activityId" value="${activityId }"/>
        	<input type="hidden" name="storeId" value="${storeId }"/>
            <table class="grid">
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">活动门店</td>
                    <td>
                    	${store.storeName }
                    </td>
                </tr>
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td>
                    	<select name="goodsId" id="goodsId" class="easyui-combogrid" style="width:250px;" data-options="required:true"></select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">条件</td>
                    <td>
                    	<input name="fullPrice" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="0">
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">数量</td>
                    <td>
                    	<input name="goodsNum" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="1">
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">可分配库存</td>
                    <td id="fpa">
                    	0
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
