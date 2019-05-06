<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

	let analyticsDataGrid;

	let columns = [
       	[
            {title:'访问流量统计',colspan:5},
            {title:'访问流量来源',colspan:4},
            {title:'用户活跃度',colspan:5},
            {title:'访问流量城市前五名(从左到右)',colspan:5}
        ],
        [
        	{
                width: '7%',
                title: '访问次数',
                align: 'center',
                field: 'visit_pv'
        	},
        	{
                width: '7%',
                title: '打开次数',
                align: 'center',
                field: 'session_cnt'
        	},
        	{
                width: '7%',
                title: '访问人数',
                align: 'center',
                field: 'visit_uv'
        	},
        	{
                width: '10%',
                title: '人均停留时长(秒)',
                align: 'center',
                field: 'stay_time_uv'
        	},
        	{
                width: '10%',
                title: '次均停留时长(秒)',
                align: 'center',
                field: 'stay_time_session'
        	},
        	{
                width: '7%',
                title: '直接访问',
                align: 'center',
                field: 'session'
        	},
        	{
                width: '7%',
                title: '搜索引擎',
                align: 'center',
                field: 'search'
        	},
        	{
                width: '7%',
                title: '分享',
                align: 'center',
                field: 'share'
        	},
        	{
                width: '7%',
                title: '其他',
                align: 'center',
                field: 'other'
        	},
        	{
                width: '7%',
                title: '分享次数',
                align: 'center',
                field: 'share_pv'
        	},
        	{
                width: '7%',
                title: '分享人数',
                align: 'center',
                field: 'share_uv'
        	},
        	{
                width: '7%',
                title: '活跃用户',
                align: 'center',
                field: 'visit_uv'
        	},
        	{
                width: '7%',
                title: '新用户数',
                align: 'center',
                field: 'visit_uv_new'
        	},
        	{
                width: '7%',
                title: '累计用户数',
                align: 'center',
                field: 'visit_total'
        	}
        ] ];
	
	$(function () {
		selectTop5();
    	analyticsDataGrid = $('#analyticsDataGrid').datagrid({
            url: '${path }/analytics/dataGrid',
            method:"GET",
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'ref_date',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            frozenColumns:[[
            	{
           			width: '7%',
	                title: '日期',
	                align: 'center',
	                field: 'ref_date'
                }
        	]],
        	columns:columns
        });
    });
    
	function selectTop5() {
		$.ajaxSettings.async = false;
		$.get('${path}/analytics/selectTop5', $.serializeObject($('#analyticsSearchForm')),function (data) { 
	    	let array = JSON.parse(data);
	    	for (let i = 0; i < array.length; i++) {  
	    		let o ={
        			width: '7%',
                    align: 'center'
    	    	};
	    		o.title = array[i];
	    		o.field = 'top'+i;
	    		columns[1].push(o);
	        } 
	    	
	    }); 	
		$.ajaxSettings.async = true;
	}
	
	function cleanAnalytics() {
		$('#analyticsSearchForm input').val('');
		analyticsDataGrid.datagrid('load', {});
	}
	
	function searchAnalytics() {
		selectTop5();
		analyticsDataGrid.datagrid('load', $.serializeObject($('#analyticsSearchForm')));
	}
	
	function exportAnalytics(){
		 $('#analyticsSearchForm').form('submit', {
		 	url: "${path }/analytics/exportAnalytics",
		 	params:$('#analyticsSearchForm').serialize()
		 });
	}
	
	function showAnalyticsCharts() {
		openTab("查看图表", '${path }/analytics/showCharts');
	}
	
	function openTab(title, href) {
		var e = $('#mainTabs').tabs('exists', title);
		if (e) {
			$("#mainTabs").tabs('select', title);
			var tab = $("#mainTabs").tabs('getSelected');
			$('#mainTabs')
					.tabs(
							'update',
							{
								tab : tab,
								options : {
									title : title,
									content : '<iframe name="indextab" scrolling="auto" src="'
											+ href
											+ '" frameborder="0" style="width:100%;height:100%;"></iframe>',
									closable : true,
									selected : true
								}
							});
		} else {
			$('#mainTabs')
					.tabs(
							'add',
							{
								title : title,
								content : '<iframe name="indextab" scrolling="auto" src="'
										+ href
										+ '" frameborder="0" style="width:100%;height:100%;"></iframe>',
								iconCls : '',
								closable : true
							});
		}
	}
	
	let format_ = 'yyyy';
	
	function randomDate(){
		return format_;
	}
	
	$("#analyticsformat").change(function(){
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
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="analyticsSearchForm">
             <table>
                <tr>
                	<th> 查询格式: </th>	 
                   	<td>
                   		<select name="format" id="analyticsformat">
                   			<option value="%Y" selected>年</option>
                   			<option value="%Y-%m">月</option>
                   			<option value="%Y-%m-%d">日</option>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 开始日期: </th>	 
                   	<td>
                   		<input type="text" name="startDate" id="startDate"  value=""  required="required" onclick="let date=randomDate();WdatePicker({dateFmt:date,readOnly:true})">
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 结束日期: </th>	
                   	<td>
                   		<input type="text" name="endDate" id="endDate" value=""  required="required" onclick="let date=randomDate();WdatePicker({dateFmt:date,readOnly:true})">
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchAnalytics();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanAnalytics();">清空</a>
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-save icon-gray',plain:true" onclick="exportAnalytics();">导出</a>
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search icon-blue',plain:true" onclick="showAnalyticsCharts();">查看图表</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="analyticsDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>