<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var dataAnalysisChannelMoreDataGrid;
    $(function () {
    	dataAnalysisChannelMoreDataGrid = $('#dataAnalysisChannelMoreDataGrid').datagrid({
            url: '${path }/data_channel/moreDataGrid',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
            pageSize: 20,
            pageList: [2, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '5%',
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
                width: '15%',
                title: '粉丝数',
                align : 'center',
                field: 'fans'
            },{
                width: '15%',
                title: '新增粉丝数',
                align : 'center',
                field: 'newFans'
            },{
                width: '25%',
                title: '门店名称',
                align : 'center',
                field: 'storeName'
            },{
                width : '20%',
                title : '销售额',
                align : 'center',
                field : 'balances'
            },{
                width: '20%',
                title: '销售占比',
                align : 'center',
                field: 'balancesRate'
            } ] ],
            onLoadSuccess:function(data){
            }
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
	 * 查看图表
	 */
    function queryMoreChannelGraphics() {
        var opts = {
       		title : '多渠道图表',
       		border : false,
       		closable : true,
       		fit : true,
       	};
        var param ='?format='+encodeURI(encodeURI($("#format").val()))
		 +'&startTime='+$("#startTime").val()
		 +'&endTime='+$("#endTime").val()
		 +'&storeId='+$("#storeId").combogrid("getValues").toString();
       	var url = '/data_channel/moreGraphics'+param;
       	if (url && url.indexOf("http") == -1) {
       		url = '${path }' + url;
       	}
       	opts.href = url;
       	addCloseTab(opts);
    }
    
	/**
	 * 清除
	 */
	function cleanDataAnalysisChannelMoreFun() {
		$('#dataAnalysisChannelMoreSearchForm input').val('');
		dataAnalysisChannelMoreDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function dataAnalysisChannelMoreSearchFun() {
		dataAnalysisChannelMoreDataGrid.datagrid('load', $.serializeObject($('#dataAnalysisChannelMoreSearchForm')));
	}
	
	/**
	 * 统计下载
	 */
	function downLoadMoreChannel(){
		 var param ='?format='+encodeURI(encodeURI($("#format").val()))
		 +'&startTime='+$("#startTime").val()
		 +'&endTime='+$("#endTime").val();
		 +'&storeId='+$("#storeId").combogrid("getValues").toString();
		window.location.href = '${path }/data_channel/downLoadMoreChannel'+param;
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
        <form id="dataAnalysisChannelMoreSearchForm" method="POST"  enctype="multipart/form-data">
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
                   		<select name="storeId" id="storeId" class="easyui-combogrid" style="width: 200px; height: 29px;"></select>
                   	</td>
                   	
                   	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="dataAnalysisChannelMoreSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanDataAnalysisChannelMoreFun();">清空</a>
                    </td>
                </tr>
                <tr>
                	
                    <td colspan="4">
                        <a onclick="downLoadMoreChannel();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出统计</a>
                        <a onclick="queryMoreChannelGraphics();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-search icon-blue'">查看图表</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="dataAnalysisChannelMoreDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
