<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var orderRefundDataGrid;
    $(function () {
    	orderRefundDataGrid = $('#orderRefundDataGrid').datagrid({
            url: '${path }/orderRefund/dataGrid',
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
                width: '10%',
                title: '创建日期',
                align : 'center',
                field: 'createdTime'
            },{
                width: '15%',
                title: '订单所属门店',
                align : 'center',
                field: 'storeName'
            },{
                width: '10%',
                title: '会员昵称',
                align : 'center',
                field: 'nickName'
            },{
                width: '10%',
                title: '会员手机号',
                align : 'center',
                field: 'mobileNo'
            },{
                width: '10%',
                title: '会员姓名',
                align : 'center',
                field: 'userName'
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
                width: '15%',
                title: '退款单号',
                align : 'center',
                field: 'refundNo'
            },{
                width: '5%',
                title: '退款类型',
                align : 'center',
                field: 'type',
                formatter:function(value,row,index){
                	if(value==1){
                		return '退款';
                	}else if(value==2){
                		return '退货退款';
                	}
    			}
            },{
                width: '10%',
                title: '退款原因',
                align : 'center',
                field: 'desc'
            },{
                width: '10%',
                title: '退款数量',
                align : 'center',
                field: 'goodsNum'
            },{
                width: '10%',
                title: '退款金额',
                align : 'center',
                field: 'balances'
            },{
                width: '10%',
                title: '退货物流',
                align : 'center',
                field: 'expressName'
            },{
                width: '10%',
                title: '退货单号',
                align : 'center',
                field: 'expressNo'
            },{
                width: '10%',
                title: '审核说明',
                align : 'center',
                field: 'auditDesc'
            },{
                width : '8%',
                title : '状态',
                align : 'center',
                field : 'status',
                formatter:function(value,row,index){
                	if(value==1){
                		return '<font color="red">退货申请</font>';
                	}else if(value==2){
                		return '<font color="red">待收货</font>';
                	}else if(value==3){
                		return '<font color="red">待退款</font>';
                	}else if(value==4){
                		return '已退款';
                	}else if(value==11){
                		return '拒绝退货';
                	}else if(value==12){
                		return '拒绝退款';
                	}
    			}
            },{
                width: '12%',
                title: '退款时间',
                align : 'center',
                field: 'lastModifiedTime'
            },{
                width: '5%',
                title: '是否入库',
                align : 'center',
                field: 'isRk'
            },{
                field : 'action',
                title : '操作',
                width : '15%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    str += $.formatString('<a href="javascript:void(0)" class="orderRefund-easyui-linkbutton-select" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="refundDetailFun(\'{0}\');" >退款详情</a>', row.refundNo);
                    if(row.status == 1 || row.status == 2){
                    	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    	str += $.formatString('<a href="javascript:void(0)" class="orderRefund-easyui-linkbutton-address" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="refundAddressFun(\'{0}\');" >退货地址</a>', row.id);
                    }
                    str += '<br/>';
                    if(row.status==3){
                    	str += $.formatString('<a href="javascript:void(0)" class="orderRefund-easyui-linkbutton-agreeRefund" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="agreeRefundFun(\'{0}\');" >同意退款</a>', row.refundNo);
                    	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    	str += $.formatString('<a href="javascript:void(0)" class="orderRefund-easyui-linkbutton-noRefund" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="openNoRefundFun(\'{0}\');" >拒绝退款</a>', row.refundNo);
                    }
                    if(row.status==1){
                    	str += $.formatString('<a href="javascript:void(0)" class="orderRefund-easyui-linkbutton-agreeRefundGoods" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="agreeRefundFun(\'{0}\');" >同意退货</a>', row.refundNo);
                    	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    	str += $.formatString('<a href="javascript:void(0)" class="orderRefund-easyui-linkbutton-noRefundGoods" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="openNoRefundFun(\'{0}\');" >拒绝退货</a>', row.refundNo);
                    }
                    if(row.status==2){
                    	str += $.formatString('<a href="javascript:void(0)" class="orderRefund-easyui-linkbutton-sure" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="agreeRefundFun(\'{0}\');" >确定收货</a>', row.refundNo);
                    }
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.orderRefund-easyui-linkbutton-select').linkbutton({text:'退款详情'});
            	$('.orderRefund-easyui-linkbutton-address').linkbutton({text:'退货地址'});
            	$('.orderRefund-easyui-linkbutton-agreeRefund').linkbutton({text:'同意退款'});
            	$('.orderRefund-easyui-linkbutton-noRefund').linkbutton({text:'拒绝退款'});
            	$('.orderRefund-easyui-linkbutton-agreeRefundGoods').linkbutton({text:'同意退货'});
            	$('.orderRefund-easyui-linkbutton-noRefundGoods').linkbutton({text:'拒绝退货'});
            	$('.orderRefund-easyui-linkbutton-sure').linkbutton({text:'确定收货'});
            },
            toolbar : '#orderRefundToolbar'
        });
    	
    	$("#storeId1").combogrid({
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
     * 退货详情
     */
    function refundDetailFun(refundNo){
    	parent.$.modalDialog({
            title : '退货详情',
            width : '70%',
            height : '70%',
            href : '${path }/orderRefund/detail?refundNo='+refundNo
        });
    }
    /**
     * 退货地址
     */
    function refundAddressFun(id){
    	parent.$.modalDialog({
            title : '退货地址编辑',
            width : '40%',
            height : '70%',
            href : '${path }/orderRefund/address?refundId='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = orderRefundDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#refundAddressAddForm');
                    f.submit();
                }
            } ]
        });
    }
  //同意退款
    function agreeRefundFun(refundNo){
    	var towards = true;
		parent.$.messager.confirm('温馨提示', '您是否确定同意？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/order/refund', {
					refundNo : refundNo,
					towards:towards
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						orderRefundDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
    }
    
  //拒绝退款
  	function openNoRefundFun(refundNo){
  		$('#dlg').dialog('open');
  		$('#refundNo').val(refundNo);
  	}
    function noRefundFun(){
    	var towards = false;
    	progressLoad();
		$.post('${path }/order/refund', {
			refundNo : $('#refundNo').val(),
			note: $('#note').val(),
			towards:towards
		}, function(result) {
			if (result.success) {
				$('#dlg').dialog('close');
				showMsg(result.msg);
				orderRefundDataGrid.datagrid('reload');
			}
			progressClose();
		}, 'JSON');
    }
    /**
	 * 订单退款下载
	 */
	function orderRefundDownFun(){
		 var param ='?userName='+encodeURIComponent(encodeURIComponent($("#userName").val()))
		 +'&type='+$("#type").val()
		 +'&orderNo='+encodeURIComponent(encodeURIComponent($("#orderNo").val()))
		 +'&status='+$("#status").val()
		 +'&isRk='+$("#isRk").val()
		 +'&storeId='+$("#storeId1").combogrid("getValues").toString();
		window.location.href = '${path }/orderRefund/downLoadOrderRefund'+param;
	}
	/**
	 * 清除
	 */
	function cleanOrderRefundFun() {
		$('#tOrderRefundSearchForm input').val('');
		orderRefundDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function orderRefundSearchFun() {
		orderRefundDataGrid.datagrid('load', $.serializeObject($('#tOrderRefundSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 70px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tOrderRefundSearchForm" method="POST"  enctype="multipart/form-data">
           <table>
           		<tr>
                	<th>姓名：</th>
                    <td>
                    	<input name="userName" id="userName" placeholder="会员姓名/昵称/手机号"/>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 门店: </th>	 
                   	<td>
                   		<select name="storeId" id="storeId1" class="easyui-combogrid" style="width: 200px; height: 29px;"></select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 退款类型: </th>	 
                   	<td>
                   		<select name="type" id="type">
                   			<option value="">请选择</option>
                   			<option value="1">退款</option>
                   			<option value="2">退货退款</option>
                   		</select>
                   	</td>
                   	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="orderRefundSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanOrderRefundFun();">清空</a>
                    </td>
                </tr>
                <tr>
                   	<th> 订单号: </th>	 
                   	<td>
                   		<input name="orderNo" id="orderNo" placeholder="订单号/商户交易号"/>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 状态: </th>	 
                   	<td>
                   		<select name="status" id="status">
                   			<option value="">请选择</option>
                   			<option value="1">申请退货</option>
                   			<option value="2">待收货</option>
                   			<option value="3">待退款</option>
                   			<option value="4">已退款</option>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 是否入库: </th>	 
                   	<td>
                   		<select name="isRk" id="isRk">
                   			<option value="">请选择</option>
                   			<option value="0">是</option>
                   			<option value="1">否</option>
                   		</select>
                   	</td>
                   	<td>
                        <a onclick="orderRefundDownFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">退款导出</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="orderRefundDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="dlg" class="easyui-dialog" title="拒绝说明" data-options="iconCls:'icon-save',closed:true" style="width:400px;height:200px;padding:10px;">
		<div style="padding-left: 10px;padding-bottom: 10px;">您是否确定拒绝？</div>
		<div style="padding-left: 10px;padding-bottom: 10px;">原因说明：</div>
		<input type="hidden" id="refundNo" value=""/>
		<textarea id="note" rows="3" style="width: 350px;"></textarea>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="noRefundFun();">确认提交</a>
	</div>
</div>
