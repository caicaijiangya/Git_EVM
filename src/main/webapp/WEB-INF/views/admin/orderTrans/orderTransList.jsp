<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var orderTransDataGrid;
    $(function () {
    	orderTransDataGrid = $('#orderTransDataGrid').datagrid({
            url: '${path }/orderTrans/dataGrid',
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
                title: '交易人',
                align : 'center',
                field: 'nickName'
            },{
                width: '25%',
                title: '订单号',
                align : 'center',
                field: 'orderNo'
            },{
                width: '25%',
                title: '交易单号',
                align : 'center',
                field: 'transNo'
            },{
                width: '25%',
                title: '退款单号',
                align : 'center',
                field: 'refundNo'
            },{
                width: '20%',
                title: '交易名称',
                align : 'center',
                field: 'transName'
            },{
                width: '10%',
                title: '交易金额',
                align : 'center',
                field: 'balances'
            },{
                width: '10%',
                title: '支付优惠金额',
                align : 'center',
                field: 'couponFee'
            },{
                width : '10%',
                title : '交易状态',
                align : 'center',
                field : 'status',
                formatter:function(value,row,index){
                	if(value==0){
                		return "未支付";
                	}else if(value==1){
                		return "支付成功";
                	}else if(value==2){
                		return "支付失败";
                	}else if(value==3){
                		return "退款成功";
                	}else if(value==4){
                		return "退款失败";
                	}          	
    			}
            },{
                field : 'action',
                title : '操作',
                width : '10%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/orderTrans/delete">
        			str += $.formatString('<a href="javascript:void(0)" class="trans-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="delTransFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.trans-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#transToolbar'
        });
    });
    /**
	 * 删除
	 */
	function delTransFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = orderTransDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			orderTransDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/orderTrans/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						orderTransDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanTransFun() {
		$('#tTransSearchForm input').val('');
		orderTransDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function transSearchFun() {
		orderTransDataGrid.datagrid('load', $.serializeObject($('#tTransSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tTransSearchForm">
           <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="transName" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th> 交易状态：</th>
                    <td>
                   		<select name="status" id="status">
                   			<option value="">请选择</option>
                   			<option value="0">未支付</option>
                   			<option value="1">支付成功</option>
                   			<option value="2">支付失败</option>
                   			<option value="3">退款成功</option>
                   			<option value="4">退款失败</option>
                   		</select>
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="transSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanTransFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="orderTransDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
