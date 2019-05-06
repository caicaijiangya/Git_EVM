<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var storeFluxData;
    $(function() {
    	storeFluxData = $('#storeFluxData').datagrid({
        url : '${path}/goodsFlux/storeFluxData?goodsId=${goodsId}&specId=${specId}&salePrice=${salePrice}&format=${format}&startTime=${startTime}&endTime=${endTime}',
        striped : true,
        rownumbers : true,
        nowrap:false,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'desc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
            width : '5%',
            title : '序号',
            align : 'center',
            field : 'id',
            hidden:true
        },{
            width : '20%',
            title : '门店名称',
            field : 'storeName',
            align : 'center'
        },{
            width : '10%',
            title : '销售数量',
            field : 'storeSaleNum',
            align : 'center'
        },{
            width : '10%',
            title : '销售金额',
            field : 'storeSalePrice',
            align : 'center'
        },{
            width : '10%',
            title : '占比',
            align : 'center',
            field : 'proportion'
        }] ],
        onLoadSuccess:function(data){
        },
    });
    	
    	
    	$("#storeId").combogrid({
    		panelWidth: 200,
    		panelHeight: 300,
    		multiple: true,
    		idField: 'id',
    		textField:	'storeName',
    		url: 'store/storeDataGrid',
    		method: 'post',
    		columns: [[
    			{field:'id',title:'id',width:80,hidden:true},
    			{field:'ck',checkbox:true},
    			{field:'storeName',title:'门店名称',width:200,align:'center'}
    		]],
    		fitColumns: true
    	});
});
    
	/**
	 * 清除
	 */
	function cleanStoreFluxFun() {
		$('#storeFluxSearchForm input').val('');
		storeFluxData.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function storeFluxSearchFun() {
		storeFluxData.datagrid('load', $.serializeObject($('#storeFluxSearchForm')));
	}
	
	
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 105px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="storeFluxSearchForm">
            <table>
                <tr>
                   	<th> 门店: </th>	 
                   	<td>
                   		<select name="newStoreId" id="storeId" class="easyui-combogrid" style="width: 200px; height: 29px;"></select>
                   	</td>
                   	
                   	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="storeFluxSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanStoreFluxFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="storeFluxData" data-options="fit:true,border:false"></table>
    </div>
</div>