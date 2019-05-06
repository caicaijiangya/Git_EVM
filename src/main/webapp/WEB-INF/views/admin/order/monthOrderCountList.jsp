<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var monthOrderCountDataGrid;
    $(function () {
    	monthOrderCountDataGrid = $('#monthOrderCountDataGrid').datagrid({
            url: '${path }/order/sumDataGrid',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            showFooter: true,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '10%',
                title: 'ID',
                field: 'id',
                align : 'center',
                hidden:true
            },{
                width: '25%',
                title: '日期',
                align : 'center',
                field: 'months'
            },{
                width: '15%',
                title: '销售额',
                align : 'center',
                field: 'realPrice'
            },{
                width: '15%',
                title: '销量',
                align : 'center',
                field: 'orderQuantity'
            },{
                width: '15%',
                title: '订单量',
                align : 'center',
                field: 'goodsNum'
            },{
                width: '15%',
                title: '客单价',
                align : 'center',
                field: 'goodsPrice'
            },{
                width: '15%',
                title: '客单件',
                align : 'center',
                field: 'goodsAver'
            }] ],
            onLoadSuccess:function(data){
            },
        });
    	
    	$("#monthOrderStoreId").combogrid({
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
	function cleanMonthFun() {
		$('#tCountSearchForm input').val('');
		monthOrderCountDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function monthSearchFun() {
		monthOrderCountDataGrid.datagrid('load', $.serializeObject($('#tCountSearchForm')));
	}
	
	/**
	 * 订单下载
	 */
	function monthOrderDownFun(){
		 var param ='?orderType='+$("#orderType").val()		 
		 +'&takeStyle='+$("#takeStyle").val()
		 + '&timeStart='+$("#timeStart").val()
		 + '&timeEnd='+$("#timeEnd").val()
		 +'&selectedStoreId='+$("#monthOrderStoreId").combogrid("getValues").toString();
		window.location.href = '${path }/order/exportMonthOrderExcelList'+param;
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 70px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tCountSearchForm">
           <table>
                <tr>
                	<th> 门店: </th>	 
                   	<td>
                   		<select name="selectedStoreId" id="monthOrderStoreId" class="easyui-combogrid" style="width: 200px; height: 29px;"></select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	
                	<th>日期筛选：</th>
                    <td>
                    	 <input type="text" class="easyui-validatebox"  id="timeStart"
					 		name="timeStart" placeholder="选择开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM',onpicked:function(){timeEnd.focus();}})" readonly="readonly"/>
					 		&nbsp;&nbsp;至&nbsp;&nbsp;
					</td>		
                    <td>
				 		<input type="text" class="easyui-validatebox"  id="timeEnd"
				 		name="timeEnd" placeholder="选择结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'timeStart\')}'})" readonly="readonly"/>
                    </td>	
                  </tr>
                  <tr>
                    <th> 订单类型: </th>	 
                   	<td>
                   		<select name="orderType" id="orderType">
                   			<option value="">请选择</option>
                   			<option value="0">普通</option>
                   			<option value="1">秒杀</option>
                   			<option value="2">拼团</option>
                   			<option value="3">特价</option>
                   			<option value="4">积分商城</option>
                   			<option value="5">砍价</option>
                   			<option value="6">满减</option>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 取货方式: </th>	 
                   	<td>
                   		<select name="takeStyle" id="takeStyle">
                   			<option value="">请选择</option>
                   			<option value="0">物流配送</option>
                   			<option value="1">到店取货</option>
                   		</select>
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="monthSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanMonthFun();">清空</a>
                    	<shiro:hasPermission name="/order/monthOrderDown">
                    	<a onclick="monthOrderDownFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出订单</a>
                    	</shiro:hasPermission>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="monthOrderCountDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
