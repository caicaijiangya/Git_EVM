<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

$(function() {
	$('#addHotForm').form({
        url : '${path }/goodsOther/addHot',
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
            	hotDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
	
	$("#brandId").combobox({
		onChange: function (n,o) {
			console.log(n);
			initcombogrid(n);
		}
	});
	initcombogrid($('#brandId').combobox('getValue'));
	function initcombogrid(brandId){
		$("#goodsId").combogrid({
			panelWidth: 500,
			panelHeight: 300,
			idField: 'id',
			textField:	'goodsName',
			mode: 'remote',
			url: 'goodsOther/dataGrid',
			queryParams :{brandId:brandId,type:1},
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
				{field:'sku',title:'商品编码',width:200,align:'center'},
				{field:'specName',title:'规格名称',width:80,align:'center'},
				{field:'brandName',title:'品牌',width:80,align:'center'},
				{field:'goodsPrice',title:'价格',width:80,align:'center'}
			]],
			fitColumns: true
		});
	}
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="addHotForm" method="post">
            <table class="grid">
                <tr class="goods">
                	<td align="right" bgcolor="#f8f8f8">商品品牌</td>
                    <td>
                    	<select id="brandId" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<c:forEach items="${brands }" var="item">
	                    		<option value="${item.id }">${item.name }</option>
	                    	</c:forEach>
                    	</select>
                    </td>
                </tr>
                <tr class="goods">
                	<td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td>
                    	<select name="goodsId" id="goodsId" class="easyui-combogrid" style="width:250px;" ></select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">类型</td>
                    <td>
                    	<input type="radio" name="type" value="0" checked="checked"/> 人气精选
                    	<input type="radio" name="type" value="1"/> 经典口碑
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">尺寸</td>
                    <td>
                    	<select name="size" style="width: 100px; height: 29px;" class="easyui-combobox" data-options="required:true">
                    		<option value="264">264</option>
                    		<option value="280">280</option>
                    		<option value="350">350</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">序号</td>
                    <td>
                    	<input name="seq" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="0">
                    </td>
                </tr>
                
            </table>
        </form>
    </div>
</div>
