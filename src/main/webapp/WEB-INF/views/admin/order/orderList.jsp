<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var orderDataGrid;
    $(function () {
    	orderDataGrid = $('#orderDataGrid').datagrid({
            url: '${path }/order/dataGrid',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
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
                width: '15%',
                title: '订单号',
                align : 'center',
                field: 'orderNo'
            },{
                width: '15%',
                title: '商户交易号',
                align : 'center',
                field: 'transNo'
            },{
                width: '10%',
                title: '下单人',
                align : 'center',
                field: 'nickName'
            },{
                width : '15%',
                title : '取货方式',
                align : 'center',
                field : 'takeStyle',
                formatter:function(value,row,index){
                	if(value==0){
                		return '物流配送'
                	}else{
                		return "到店取货";
                	}
    			}
            },{
                width: '15%',
                title: '订单所属门店',
                align : 'center',
                field: 'storeName',
                formatter:function(value,row,index){
                	if(row.takeStyle==0){
                		return '<font color="red">小程序</font>';;
                	}else{
                		return value;
                	}
    			}
            },{
                width: '5%',
                title: '商品数量',
                align : 'center',
                field: 'goodsNum'
            },{
                width: '10%',
                title: '订单总标准金额',
                align : 'center',
                field: 'goodsPrice'
            },{
                width: '10%',
                title: '订单总市场价',
                align : 'center',
                field: 'totalBalances'
            },{
                width: '10%',
                title: '运费',
                align : 'center',
                field: 'freight'
            },{
                width: '10%',
                title: '优惠金额',
                align : 'center',
                field: 'discountPrice'
            },{
                width: '10%',
                title: '实付总金额',
                align : 'center',
                field: 'payBalances'
            },{
                width: '10%',
                title: '退款金额',
                align : 'center',
                field: 'refundBalances'
            },{
                width : '15%',
                title : '订单类型',
                align : 'center',
                field : 'orderType',
                formatter:function(value,row,index){
                	if(value==0){
                		return "普通";
                	}else if(value==1){
                		return "秒杀";
                	}else if(value==2){
                		return "拼团";
                	}else if(value==3){
                		return "特价";
                	}else if(value==4){
                		return "积分兑换";
                	}else if(value==5){
                		return "砍价";
                	}else if(value==6){
                		return "满减";
                	}
    			}
            },{
                width : '15%',
                title : '订单状态',
                align : 'center',
                field : 'status',
                formatter:function(value,row,index){
                	if(value==0){
                		return '待付款';
                	}else if(value==1){
                		return '<font color="red">待发货</font>';
                	}else if(value==2){
                		return '待取货';
                	}else if(value==3){
                		return '<font color="red">待退款</font>';
                	}else if(value==4){
                		return '已退款';
                	}else if(value==5){
                		return '交易取消';
                	}else if(value==6){
                		return '<font color="blue">交易完成</font>';
                	}else if(value==7){
                		return '退货待审核';
                	}else{
                		return '退货待收货';
                	}
    			}
            },{
                width: '8%',
                title: '拼团状态',
                align : 'center',
                field: 'isCollage'
            },{
                width: '15%',
                title: '订单创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                width: '15%',
                title: '核销人',
                align : 'center',
                field: 'nickName2'
            },{
                field : 'action',
                title : '操作',
                width : '15%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    if(row.storeId != null && row.storeId > 0 && row.status == 6){
                    	<shiro:hasPermission name="/order/jindieDown">
                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-excel" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="downLogFun(\'${EXCEL_JINDIE_MAP }\',4,\'{0}\');" >门店订单导出</a>', row.id);
                        str += '<br/>';
                        </shiro:hasPermission>
                    }
                    <shiro:hasPermission name="/order/status">
	                    str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="editStatusFun(\'{0}\');" >修改状态</a>', row.id);
	                    str += '<br/>';
                    </shiro:hasPermission>
                    if(row.takeStyle==0 && row.status==1){
                    	<shiro:hasPermission name="/order/logistics">
	                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-logistics" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="logisticsFun(\'{0}\');" >发货</a>', row.id);
	                        str += '<br/>';	
                        </shiro:hasPermission>
                    }
                    <shiro:hasPermission name="/order/detail">
	                    str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-select" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="orderDetailFun(\'{0}\');" >订单详情</a>', row.id);
	                    str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
	                    </shiro:hasPermission>
                    <shiro:hasPermission name="/order/delete">
                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="delOrderFun(\'{0}\');" >删除</a>', row.id);
                    </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.order-easyui-linkbutton-select').linkbutton({text:'订单详情'});
            	$('.order-easyui-linkbutton-logistics').linkbutton({text:'发货'});
            	$('.order-easyui-linkbutton-logisticsed').linkbutton({text:'已发货'});
            	$('.order-easyui-linkbutton-del').linkbutton({text:'删除'});
            	$('.order-easyui-linkbutton-edit').linkbutton({text:'修改状态'});
            	$('.order-easyui-linkbutton-excel').linkbutton({text:'门店订单导出'});
            },
            toolbar : '#orderToolbar'
        });
    	
    	$("#orderStoreId").combogrid({
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
    
  //填写运单号
    function logisticsFun(id) {
        parent.$.modalDialog({
            title : '填写运单号',
            width : '20%',
            height : '30%',
            href : '${path }/order/logisticsPage?orderId='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = orderDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#expresaAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
     * 订单详情
     */
    function orderDetailFun(id){
    	parent.$.modalDialog({
            title : '订单详情',
            width : '70%',
            height : '70%',
            href : '${path }/order/toOrderDetail?orderId='+id
        });
    }
    
    /**
	 * 删除
	 */
	function delOrderFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = orderDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			orderDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/order/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						orderDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	//修改字段内容
    function editStatusFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '30%',
            height : '30%',
            href : '${path }/order/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = orderDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#orderEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
	/**
	 * 清除
	 */
	function cleanOrderFun() {
		$('#tOrderSearchForm input').val('');
		orderDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function orderSearchFun() {
		orderDataGrid.datagrid('load', $.serializeObject($('#tOrderSearchForm')));
	}
	
	/**
	 * 订单下载
	 */
	function orderDownFun(){
		 var param ='?orderNo='+encodeURIComponent(encodeURIComponent($("#orderNo").val()))
		 +'&orderType='+encodeURIComponent(encodeURIComponent($("#orderType").val()))
		 +'&takeStyle='+encodeURIComponent(encodeURIComponent($("#takeStyle").val()))
		 +'&status='+encodeURIComponent(encodeURIComponent($("#status").val()))
		 +'&startTime='+encodeURIComponent(encodeURIComponent($("#startTime").val()))
		 +'&endTime='+encodeURIComponent(encodeURIComponent($("#endTime").val()))
		 +'&selectedStoreId='+$("#orderStoreId").combogrid("getValues").toString();
		window.location.href = '${path }/order/downLoadOrder'+selectCheckbox(param);
	}
	/**
	 * 订单明细下载
	 */
	function orderDetailDownFun(){
		 var param ='?orderNo='+encodeURIComponent(encodeURIComponent($("#orderNo").val()))
		 +'&orderType='+encodeURIComponent(encodeURIComponent($("#orderType").val()))
		 +'&takeStyle='+encodeURIComponent(encodeURIComponent($("#takeStyle").val()))
		 +'&status='+encodeURIComponent(encodeURIComponent($("#status").val()))
		 +'&startTime='+encodeURIComponent(encodeURIComponent($("#startTime").val()))
		 +'&endTime='+encodeURIComponent(encodeURIComponent($("#endTime").val()))
		 +'&selectedStoreId='+$("#orderStoreId").combogrid("getValues").toString();
		window.location.href = '${path }/order/downLoadOrderDetail'+selectCheckbox(param);
	}
	function orderGiftDownFun(){
		var param ='?orderNo='+encodeURIComponent(encodeURIComponent($("#orderNo").val()))
		 +'&orderType='+encodeURIComponent(encodeURIComponent($("#orderType").val()))
		 +'&takeStyle='+encodeURIComponent(encodeURIComponent($("#takeStyle").val()))
		 +'&status='+encodeURIComponent(encodeURIComponent($("#status").val()))
		 +'&startTime='+encodeURIComponent(encodeURIComponent($("#startTime").val()))
		 +'&endTime='+encodeURIComponent(encodeURIComponent($("#endTime").val()));
		window.location.href = '${path }/order/downLoadOrderGift'+selectCheckbox(param);
	}
	/**
	 * 物流订单导出
	 */
	function orderGuanyiDownFun(){
		 var param ='?orderNo='+encodeURIComponent(encodeURIComponent($("#orderNo").val()))
		 +'&orderType='+encodeURIComponent(encodeURIComponent($("#orderType").val()))
		 +'&startDate='+encodeURIComponent(encodeURIComponent($("#startTime").val()))
		 +'&endDate='+encodeURIComponent(encodeURIComponent($("#endTime").val()));
		window.location.href = '${path }/excel/guanyi'+selectCheckbox(param);
	}
	
	/**
	 * 门店订单导出
	 */
	function orderJindieDownFun(id){
		 var param ='?id='+id;
		window.location.href = '${path }/excel/jindie'+selectCheckbox(param);
	}
	/**
	 * 获取导出EXCEL选择的字段
	 */
	function selectCheckbox(param){
		var metadata = "";
		$.each($('input[name=expfield]:checked'),function(){
			metadata += $(this).val() + ",";
        });
		param += "&metadata="+metadata;
		return param;
	}
	/**
	 *	导出字段选择器
	 */
	function downLogFun(json,type,excelId){
		var jsonArray = JSON.parse(decodeURIComponent(json));
		$(".agreement_protocol").empty();
		var html = "";
		for (key in jsonArray[0]){
			html += "<div class=\"checkbox\"><input type=\"checkbox\" name=\"expfield\" value=\""+key+"\" checked/>"+jsonArray[0][key]+"</div>";
		}
		$(".agreement_protocol").html(html);
		$("#excelType").val(type);
		$("#excelId").val(excelId);
		$('#dlg').dialog('open');
	}
	
	function orderImportFun(){
		$("#orderExcel").click();
	}
	$('#tOrderSearchForm').submit(function() { 
		progressLoad();
		$(this).ajaxSubmit({
			type: 'post',
			url:'${path }/excel/orderDelivery',
			success:function(result){
				result = JSON.parse(result);
				showMsg(result.msg);
				orderDataGrid.datagrid('reload');
				progressClose();
			}
		}); 
		return false; //阻止表单默认提交 
	});
	$("#orderExcel").change(function(){
		$('#tOrderSearchForm').submit();
	});
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 100px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tOrderSearchForm" method="POST"  enctype="multipart/form-data">
        	<input type="file" name="orderExcel" id="orderExcel" style="display: none;"/>
           <table>
           		<tr>
                	<th>关键字：</th>
                    <td>
                    	<input name="orderNo" id="orderNo" placeholder="条件搜索"/>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 开始日期: </th>	 
                   	<td>
                   		<input type="text" name="startTime" id="startTime"  value=""  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 结束日期: </th>	
                   	<td>
                   		<input type="text" name="endTime" id="endTime" value=""  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
                   	</td>
                   	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="orderSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanOrderFun();">清空</a>
                    </td>
                    <td>
                    	<shiro:hasPermission name="/order/orderDown">
                    		<a onclick="downLogFun('${EXCEL_ORDER_MAP }',1,0);"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出订单</a>&nbsp;&nbsp;
                    	</shiro:hasPermission>
                    	<shiro:hasPermission name="/order/orderDetailDown">
                    		<a onclick="downLogFun('${EXCEL_ORDER_DETAIL_MAP }',2,0);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出订单明细</a>
                    	</shiro:hasPermission>
                    </td>
                </tr>
                <tr>
                    
                   	<th> 取货方式: </th>	 
                   	<td>
                   		<select name="takeStyle" id="takeStyle">
                   			<option value="">请选择</option>
                   			<option value="0">物流配送</option>
                   			<option value="1">到店取货</option>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 订单类型: </th>	 
                   	<td>
                   		<select name="orderType" id="orderType">
                   			<option value="">请选择</option>
                   			<option value="0">普通</option>
                   			<option value="1">秒杀</option>
                   			<option value="2">拼团</option>
                   			<option value="3">特价</option>
                   			<option value="5">砍价</option>
                   			<option value="4">积分兑换</option>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 订单状态: </th>	 
                   	<td>
                   		<select name="status" id="status">
                   			<option value="">请选择</option>
                   			<option value="0">待付款</option>
                   			<option value="1">待发货</option>
                   			<option value="2">待取货</option>
                   			<option value="3">待退款</option>
                   			<option value="4">已退款</option>
                   			<option value="5">交易取消</option>
                   			<option value="6">交易完成</option>
                   			<option value="7">订单退货待审核</option>
                   			<option value="8">订单退货待收货</option>
                   		</select>
                   	</td>
                   	<td>
                   		<shiro:hasPermission name="/order/guanyiDown">
					        <a onclick="downLogFun('${EXCEL_GUANYI_MAP }',3,0);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">物流订单导出</a>
					    </shiro:hasPermission>
                   	</td>
                   	<td>
                   		<shiro:hasPermission name="/order/import">
                   			<a onclick="orderImportFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-upload icon-green'">批量发货</a>
                   		</shiro:hasPermission>
                   	</td>
                </tr>
                <tr>
                	<th> 门店: </th>	 
                   	<td>
                   		<select name="selectedStoreId" id="orderStoreId" class="easyui-combogrid" style="width: 200px; height: 29px;"></select>
                   	</td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="orderDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<style>
.agreement_protocol{
	overflow: hidden;
	width: 100%;
}
.agreement_protocol .checkbox{
	float: left;
	margin-left: 30px;
	margin-top: 10px;
}
.agreement_protocol .checkbox input{
	width: 15px;
	height: 15px;
	vertical-align:middle; 
	margin-top:-2px; 
	margin-bottom:1px;
}
</style>
<div id="dlg" class="easyui-dialog" title="EXCEL导出" style="width:400px;height:400px;padding:10px"
			data-options="
				iconCls: 'icon-save',
				closed:true,
				buttons: [{
					text:'确认下载',
					iconCls:'icon-ok',
					handler:function(){
						var excelId = $('#excelId').val();
						var excelType = $('#excelType').val();
						if(excelType == 1){
							orderDownFun();
						}else if(excelType == 2){
							orderDetailDownFun();
						}else if(excelType == 3){
							orderGuanyiDownFun();
						}else if(excelType == 4){
							orderJindieDownFun(excelId);
						}
					}
				}]
			">
			<input type="hidden" id="excelId" value=""/>
			<input type="hidden" id="excelType" value=""/>
		<div class="agreement_protocol"></div>
	</div>
