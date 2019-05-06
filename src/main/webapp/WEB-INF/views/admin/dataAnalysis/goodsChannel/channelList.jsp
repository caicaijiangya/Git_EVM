<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var channelData;
    $(function() {
    	channelData = $('#channelData').datagrid({
        url : '${path}/goodsFlux/dataGrid',
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
            width : '10%',
            title : '日期',
            field : 'dateTime',
            align : 'center'
        },{
            width : '10%',
            title : '店铺流量',
            field : 'funsNum',
            align : 'center'
        },{
            width : '10%',
            title : '粉丝量',
            field : 'funsNum',
            align : 'center'
        },{
            width : '10%',
            title : '商品规格',
            field : 'specName',
            align : 'center'
        },{
            width : '20%',
            title : '商品名称',
            field : 'goodsName',
            align : 'center'
        },{
            width : '10%',
            title : '点击次数',
            align : 'center',
            field : 'clickNum'
        },{
            width : '10%',
            title : '点击人数',
            align : 'center',
            field : 'clickManNum'
        },{
            width : '10%',
            title : '人均点击次数',
            align : 'center',
            field : 'clickAverage'
        },{
            width : '10%',
            title : '收藏数量',
            field : 'collectNum',
            align : 'center'
        },{
            width : '10%',
            title : '加购数量',
            field : 'cartGoodsNum',
            align : 'center'
        },{
            width : '10%',
            title : '销售数量',
            field : 'saleNum',
            align : 'center'
        },{
            width : '10%',
            title : '购买人数',
            align : 'center',
            field : 'buyManNum'
        },{
            width : '10%',
            title : '人均购买件数',
            field : 'buyAverage',
            align : 'center'
        },{
            width : '10%',
            title : '销售金额',
            field : 'salePrice',
            align : 'center'
        },{
            field : 'action',
            title : '操作',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
            	var str="";
            	str += $.formatString('<a href="javascript:void(0)" class="goodsChannel-easyui-linkbutton-store" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="searchStoreChannelFun(\'{0}\',\'{1}\',\'{2}\');" >门店销售数据</a>',row.id,row.specId,row.salePrice);
    			return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.goodsChannel-easyui-linkbutton-store').linkbutton({text:'门店销售数据'});
        },
    });
    	
    	$("#goodsId").combogrid({
    		panelWidth: 200,
    		panelHeight: 300,
    		multiple: true,
    		idField: 'id',
    		textField:	'goodsName',
    		url: 'goods/selectGoodsInfo',
    		method: 'post',
    		columns: [[
    			{field:'id',title:'id',width:80,hidden:true},
    			{field:'ck',checkbox:true},
    			{field:'goodsName',title:'商品名称',width:200,align:'center'},
    			{field:'specName',title:'商品规格',width:200,align:'center'}
    		]],
    		fitColumns: true
    	});
});
    
    /**
     * 门店销售数据
     */
    function searchStoreChannelFun(id,specId,salePrice){
    	var opts = {
        		title : '门店销售数据',
        		border : false,
        		closable : true,
        		fit : true,
        	};
    		var param ='?format='+encodeURI(encodeURI($("#format").val()))
		 	+'&startTime='+$("#startTime").val()
		 	+'&endTime='+$("#endTime").val()
		 	+'&goodsId='+id
		 	+'&specId='+specId
		 	+'&salePrice='+salePrice;
		 	console.log(param);
        	var url = '/goodsFlux/storeData'+param;
        	if (url && url.indexOf("http") == -1) {
        		url = '${path }' + url;
        	}
        	opts.href = url;
        	addCloseTab(opts);
    }
    
    /**
	 * 查看图表
	 */
    function queryMoreStoreChannelGraphics() {
        var opts = {
       		title : '门店销售统计图表',
       		border : false,
       		closable : true,
       		fit : true,
       	};
        var param ='?format='+encodeURI(encodeURI($("#format").val()))
		 +'&startTime='+$("#startTime").val()
		 +'&endTime='+$("#endTime").val();
       	var url = '/goodsFlux/moreGraphics'+param;
       	if (url && url.indexOf("http") == -1) {
       		url = '${path }' + url;
       	}
       	opts.href = url;
       	addCloseTab(opts);
    }
    
    /**
	 * 统计下载
	 */
	function downLoadMoreChannel(){
		 var param ='?format='+encodeURI(encodeURI($("#goodsformat").val()))
		 +'&startTime='+$("#startTime").val()
		 +'&endTime='+$("#endTime").val();
		 +'&gdId='+$("#goodsId").combogrid("getValues").toString();
		window.location.href = '${path }/goodsFlux/downLoadMoreChannel'+param;
	}
    
    
	/**
	 * 清除
	 */
	function cleanGoodsFluxFun() {
		$('#goodsFluxSearchForm input').val('');
		channelData.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function goodsFluxSearchFun() {
		channelData.datagrid('load', $.serializeObject($('#goodsFluxSearchForm')));
	}
	
	
	var format_ = 'yyyy';
	function randomDate(){
		return format_;
	}
	$("#goodsformat").change(function(){
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
    <div data-options="region:'north',border:false" style="height: 105px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="goodsFluxSearchForm">
            <table>
                <tr>
                	<th> 查询格式: </th>	 
                   	<td>
                   		<select name="format" id="goodsformat">
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
                   	<th> 商品: </th>	 
                   	<td>
                   		<select name="gdId" id="goodsId" class="easyui-combogrid" style="width: 200px; height: 29px;"></select>
                   	</td>
                   	
                   	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="goodsFluxSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanGoodsFluxFun();">清空</a>
                    </td>
                </tr>
                <tr>
                	
                    <td colspan="4">
                        <a onclick="downLoadMoreChannel();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出统计</a>
                        <a onclick="queryMoreStoreChannelGraphics();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-search icon-blue'">查看图表</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="channelData" data-options="fit:true,border:false"></table>
    </div>
</div>