<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

$(function() {
	$("#brandId").combobox({
		onChange: function (n,o) {
			initcombogrid(n);
		}
	});
	initcombogrid($('#brandId').combobox('getValue'));
	$('#storeGoodsAddForm').form({
        url : '${path }/storeGoods/add',
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
	
	function initcombogrid(brandId){
		$("#specId").combogrid({
			panelWidth: 500,
			panelHeight: 300,
			required:true,
			idField: 'id',
			mode: 'remote',
			textField:	'goodsName',
			url: 'goodsOther/dataGrid',
			queryParams :{brandId:brandId},
			method: 'post',
			nowrap: false,
			columns: [[
				{field:'id',title:'id',width:80,hidden:true},
				{field:'goodsImage',title:'商品图片',width:80,align:'center',
					formatter:function(value,row,index){
						return '<img src='+value+' width=80 height=80/>';
					}
				},
				{field:'goodsName',title:'商品名称',width:150,align:'center'},
				{field:'sku',title:'商品编码',width:100,align:'center'},
				{field:'specName',title:'规格名称',width:80,align:'center'},
				{field:'brandName',title:'品牌',width:80,align:'center'},
				{field:'goodsPrice',title:'价格',width:80,align:'center'}
			]],
			fitColumns: true,
			onSelect: function (rowIndex, rowData) {
				$.post('${path }/goodsOther/queryGoodsId', {
					id : rowData.id
				}, function(result) {
					$("#goodsId").val(result);
				}, 'JSON');
			}
		});
	}
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="storeGoodsAddForm" method="post">
        	<input type="hidden" name="goodsId" id="goodsId" value=""/>
        	<input type="hidden" name="storeId" value="${storeId }"/>
            <table class="grid">
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">门店名称</td>
                    <td>
                    	${storeName }
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品品牌</td>
                    <td>
                    	<select id="brandId" style="width: 140px; height: 29px;" class="easyui-combobox" data-options="required:true">
                    		<c:forEach items="${brands }" var="item">
	                    		<option value="${item.id }">${item.name }</option>
	                    	</c:forEach>
                    	</select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td>
                    	<select name="specId" id="specId" class="easyui-combogrid" style="width:250px;" data-options="required:true"></select>
                    </td>
                </tr>
                <!-- <tr>
                	<td align="right" bgcolor="#f8f8f8">可分配库存</td>
                    <td id="fpa">
                    	0
                    </td>
                </tr> -->
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
