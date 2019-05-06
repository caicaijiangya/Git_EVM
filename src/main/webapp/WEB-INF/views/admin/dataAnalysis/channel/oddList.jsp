<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var dataAnalysisChannelOddDataGrid;
    $(function () {
    	dataAnalysisChannelOddDataGrid = $('#dataAnalysisChannelOddDataGrid').datagrid({
            url: '${path }/data_channel/oddDataGrid',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
            pageSize: 20,
            pageList: [2, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '10%',
                title: 'ID',
                field: 'id',
                align : 'center',
                hidden:true
            },{
                width: '10%',
                title: '日期',
                align : 'center',
                field: 'dateTime'
            },{
                width: '10%',
                title: '订单标准金额',
                align : 'center',
                field: 'totalBalances'
            },{
                width: '10%',
                title: '优惠金额',
                align : 'center',
                field: 'discoutPrice'
            },{
                width : '10%',
                title : '销售额',
                align : 'center',
                field : 'transBalances'
            },{
                width: '10%',
                title: '销量',
                align : 'center',
                field: 'transGoodsNum'
            },{
                width: '10%',
                title: '订单量',
                align : 'center',
                field: 'orderNum'
            },{
                width: '10%',
                title: '客人数',
                align : 'center',
                field: 'userNum'
            },{
                width: '10%',
                title: '客单件',
                align : 'center',
                field: 'userOddNum'
            },{
                width: '10%',
                title: '客单价',
                align : 'center',
                field: 'userOddPrice'
            },{
                width: '10%',
                title: '实付折扣',
                align : 'center',
                field: 'discount'
            },{
                width: '10%',
                title: '退货数量',
                align : 'center',
                field: 'refundNum'
            },{
                width: '10%',
                title: '退货金额',
                align : 'center',
                field: 'refundBalances'
            },{
                width : '10%',
                title : '退货率',
                align : 'center',
                field : 'refundRate'
            } ] ],
            onLoadSuccess:function(data){
            }
        });
    	
    	$("#storeId_1").combogrid({
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
	 * 查看图表
	 */
    function queryOddChannelGraphics() {
        var opts = {
       		title : '单渠道图表',
       		border : false,
       		closable : true,
       		fit : true,
       	};
        var param ='?format='+encodeURI(encodeURI($("#format").val()))
		 +'&startTime='+$("#startTime").val()
		 +'&endTime='+$("#endTime").val()
		 +'&storeId='+$("#storeId_1").combogrid("getValues").toString();
       	var url = '/data_channel/ollGraphics'+param;
       	if (url && url.indexOf("http") == -1) {
       		url = '${path }' + url;
       	}
       	opts.href = url;
       	addCloseTab(opts);
    }
    
	/**
	 * 清除
	 */
	function cleanDataAnalysisChannelOddFun() {
		$('#dataAnalysisChannelOddSearchForm input').val('');
		dataAnalysisChannelOddDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function dataAnalysisChannelOddSearchFun() {
		dataAnalysisChannelOddDataGrid.datagrid('load', $.serializeObject($('#dataAnalysisChannelOddSearchForm')));
	}
	
	/**
	 * 统计下载
	 */
	function downLoadOddChannel(){
		 var param ='?format='+encodeURI(encodeURI($("#format").val()))
		 +'&startTime='+$("#startTime").val()
		 +'&endTime='+$("#endTime").val()
		 +'&storeId='+$("#storeId_1").combogrid("getValues").toString();
		window.location.href = '${path }/data_channel/downLoadOddChannel'+param;
	}
	var format_ = 'yyyy';
	function randomDate(){
		return format_;
	}
	$("#format").change(function(){
		if(this.value == '%Y'){
			format_ = 'yyyy';
		}else if(this.value == '%Y-%m'){
			format_ = 'yyyy-MM';
		}else if(this.value == '%Y-%m-%d'){
			format_ = 'yyyy-MM-dd';
		}
	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 70px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="dataAnalysisChannelOddSearchForm" method="POST"  enctype="multipart/form-data">
           <table>
           		<tr>
                	<th> 查询格式: </th>	 
                   	<td>
                   		<select name="format" id="format">
                   			<option value="%Y" selected>年</option>
                   			<option value="%Y-%m">月</option>
                   			<option value="%Y-%m-%d">日</option>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 开始日期: </th>	 
                   	<td>
                   		<input type="text" name="startTime" id="startTime"  value=""  required="required" onclick="var date=randomDate();WdatePicker({dateFmt:date,readOnly:true})">
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 结束日期: </th>	
                   	<td>
                   		<input type="text" name="endTime" id="endTime" value=""  required="required" onclick="var date=randomDate();WdatePicker({dateFmt:date,readOnly:true})">
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 门店: </th>	 
                   	<td>
                   		<select name="storeId" id="storeId_1" class="easyui-combogrid" style="width: 200px; height: 29px;"></select>
                   	</td>
                   	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="dataAnalysisChannelOddSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanDataAnalysisChannelOddFun();">清空</a>
                    </td>
                    
                </tr>
                <tr>
                	<td colspan="4">
                        <a onclick="downLoadOddChannel();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出统计</a>
                        <a onclick="queryOddChannelGraphics();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-search icon-blue'">查看图表</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="dataAnalysisChannelOddDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
