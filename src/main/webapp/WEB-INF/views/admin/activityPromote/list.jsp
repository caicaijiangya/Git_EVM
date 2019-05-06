<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var activityPromoteDataGrid;
    $(function () {
    	activityPromoteDataGrid = $('#activityPromoteDataGrid').datagrid({
            url: '${path }/activityPromote/activityDataGrid',
            striped: true,pagination: true,rownumbers : true,singleSelect: true,idField: 'id',pageSize: 20,pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '5%',
                title: 'ID',
                field: 'id',
                align : 'center',
                hidden:true
            },{
                width: '15%',
                title: '优惠券名称',
                align : 'center',
                field: 'title'
            },{
                width: '10%',
                title: '价值',
                align : 'center',
                field: 'money'
            },{
                width: '5%',
                title: '领取限制',
                align : 'center',
                field: 'userNum'
            },{
                width: '10%',
                title: '有效期',
                align : 'center',
                field: 'dueTime'
            },{
                width: '10%',
                title: '领取人/次',
                align : 'center',
                field: 'receiveNum'
            },{
                width: '10%',
                title: '已使用',
                align : 'center',
                field: 'useNum'
            },{
                width: '10%',
                title: '领取率/使用率',
                align : 'center',
                field: 'receiveRate'
            },{
                width: '10%',
                title: '用券新客数',
                align : 'center',
                field: 'newUserNum'
            },{
                width: '10%',
                title: '用券老客数',
                align : 'center',
                field: 'oldUserNum'
            },{
                width: '10%',
                title: '购买商品件数',
                align : 'center',
                field: 'goodsNum'
            },{
                width: '10%',
                title: '用券总成交额',
                align : 'center',
                field: 'totleBalances'
            },{
                width: '10%',
                title: '用券笔单价',
                align : 'center',
                field: 'singlePrice'
            } ] ],
            onLoadSuccess:function(data){
            }
        });
    });
    $("#type").change(function(){
    	var columns = [];
    	if(this.value == 0){
    		//优惠券
    		columns = [[{width: '5%',title: 'ID',field: 'id',align : 'center',hidden:true},{width: '15%',title: '优惠券名称',align : 'center',field: 'title'},{ width: '10%',title: '价值',align : 'center',field: 'money'},{ width: '5%',title: '领取限制',align : 'center', field: 'userNum' },{width: '10%',title: '有效期',align : 'center',field: 'dueTime'},{width: '10%',title: '领取人/次',align : 'center',field: 'receiveNum'},{width: '10%',title: '已使用',align : 'center',field: 'useNum' },{width: '10%',title: '领取率/使用率',align : 'center', field: 'receiveRate'},{width: '10%',title: '用券新客数',align : 'center',field: 'newUserNum'},{width: '10%',title: '用券老客数',align : 'center',field: 'oldUserNum'},{width: '10%',title: '购买商品件数',align : 'center',field: 'goodsNum'},{width: '10%',title: '用券总成交额',align : 'center',field: 'totleBalances'},{width: '10%',title: '用券笔单价',align : 'center', field: 'singlePrice'} ] ];
    	}else if(this.value == 1){
    		//秒杀
    		columns = [[{width: '5%',title: 'ID',field: 'id',align : 'center',hidden:true},{width: '15%',title: '活动名称',align : 'center',field: 'title'},{ width: '15%',title: '活动时间',align : 'center',field: 'dateTime'},{ width: '5%',title: '活动状态',align : 'center', field: 'status' },{width: '10%',title: '页面访问数',align : 'center',field: 'accessNum'},{width: '10%',title: '独立访客数',align : 'center',field: 'accessUserNum'},{width: '10%',title: '点击率',align : 'center',field: 'accessRate' },{width: '10%',title: '新访客数',align : 'center', field: 'newAccessNum'},{width: '10%',title: '新成交客户数',align : 'center', field: 'newUserNum'},{width: '10%',title: '老成交客户数',align : 'center', field: 'oldUserNum'},{width: '10%',title: '分享次数',align : 'center',field: 'shareNum'},{width: '10%',title: '订单实付金额',align : 'center', field: 'totleBalances'},{width: '10%',title: '付款订单数',align : 'center', field: 'orderNum'},{width: '10%',title: '优惠总金额',align : 'center', field: 'discoutPrice'} ] ];
    	}else if(this.value == 2){
    		//拼团
    		columns = [[{width: '5%',title: 'ID',field: 'id',align : 'center',hidden:true},{width: '15%',title: '活动名称',align : 'center',field: 'title'},{ width: '15%',title: '活动时间',align : 'center',field: 'dateTime'},{ width: '5%',title: '活动状态',align : 'center', field: 'status' },{width: '10%',title: '页面访问数',align : 'center',field: 'accessNum'},{width: '10%',title: '独立访客数',align : 'center',field: 'accessUserNum'},{width: '10%',title: '点击率',align : 'center',field: 'accessRate' },{width: '10%',title: '新访客数',align : 'center', field: 'newAccessNum'},{width: '10%',title: '发起拼团人数',align : 'center',field: 'collageNum'},{width: '10%',title: '分享次数',align : 'center',field: 'shareNum'},{width: '10%',title: '参团人数',align : 'center',field: 'collageJoinNum'},{width: '10%',title: '成团订单数',align : 'center',field: 'orderNum'},{width: '10%',title: '新成交客户数',align : 'center', field: 'newUserNum'},{width: '10%',title: '老成交客户数',align : 'center', field: 'oldUserNum'},{width: '10%',title: '订单实付金额',align : 'center', field: 'totleBalances'},{width: '10%',title: '优惠总金额',align : 'center', field: 'discoutPrice'} ] ];
    	}else if(this.value == 4){
    		//砍价
    		columns = [[{width: '5%',title: 'ID',field: 'id',align : 'center',hidden:true},{width: '15%',title: '活动名称',align : 'center',field: 'title'},{ width: '15%',title: '活动时间',align : 'center',field: 'dateTime'},{ width: '5%',title: '活动状态',align : 'center', field: 'status' },{width: '10%',title: '页面访问数',align : 'center',field: 'accessNum'},{width: '10%',title: '独立访客数',align : 'center',field: 'accessUserNum'},{width: '10%',title: '点击率',align : 'center',field: 'accessRate' },{width: '10%',title: '新访客数',align : 'center', field: 'newAccessNum'},{width: '10%',title: '发起活动人数',align : 'center',field: 'bargainNum'},{width: '10%',title: '分享次数',align : 'center',field: 'shareNum'},{width: '10%',title: '帮砍人数',align : 'center',field: 'bargainJoinNum'},{width: '10%',title: '付款订单数',align : 'center',field: 'orderNum'},{width: '10%',title: '新成交客户数',align : 'center', field: 'newUserNum'},{width: '10%',title: '老成交客户数',align : 'center', field: 'oldUserNum'},{width: '10%',title: '订单实付金额',align : 'center', field: 'totleBalances'},{width: '10%',title: '优惠总金额',align : 'center', field: 'discoutPrice'} ] ];
    	}else if(this.value == 5){
    		//满减/满赠
    		columns = [[{width: '5%',title: 'ID',field: 'id',align : 'center',hidden:true},{width: '15%',title: '活动名称',align : 'center',field: 'title'},{ width: '15%',title: '活动时间',align : 'center',field: 'dateTime'},{ width: '5%',title: '活动状态',align : 'center', field: 'status' },{width: '10%',title: '页面访问数',align : 'center',field: 'accessNum'},{width: '10%',title: '独立访客数',align : 'center',field: 'accessUserNum'},{width: '10%',title: '点击率',align : 'center',field: 'accessRate' },{width: '10%',title: '新访客数',align : 'center', field: 'newAccessNum'},{width: '10%',title: '新成交客户数',align : 'center', field: 'newUserNum'},{width: '10%',title: '老成交客户数',align : 'center', field: 'oldUserNum'},{width: '10%',title: '订单实付金额',align : 'center', field: 'totleBalances'},{width: '10%',title: '付款订单数',align : 'center', field: 'orderNum'},{width: '10%',title: '活动笔单价',align : 'center', field: 'unitPrice'} ] ];
    	}
    	activityPromoteDataGrid = $('#activityPromoteDataGrid').datagrid({
            url: '${path }/activityPromote/activityDataGrid',
            striped: true,pagination: true,rownumbers : true,singleSelect: true,idField: 'id',pageSize: 20,pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            queryParams:{type:this.value,startTime:$('#startTime').val(),endTime:$('#endTime').val()},
            columns: columns,
            onLoadSuccess:function(data){
            }
        });
	});
	/**
	 * 清除
	 */
	function cleanActivityPromoteFun() {
		$('#activityPromoteSearchForm input').val('');
		activityPromoteDataGrid.datagrid('load', $.serializeObject($('#activityPromoteSearchForm')));
	}
	/**
	 * 搜索
	 */
	function activityPromoteSearchFun() {
		activityPromoteDataGrid.datagrid('load', $.serializeObject($('#activityPromoteSearchForm')));
	}
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="activityPromoteSearchForm" method="POST"  enctype="multipart/form-data">
           <table>
           		<tr>
                	<th>关键字：</th>
                    <td>
                    	<input name="title" id="title" placeholder="条件搜索"/>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th>活动类型: </th>	 
                   	<td>
                   		<select name="type" id="type">
                   			<option value="0">优惠券</option>
                   			<option value="1">秒杀</option>
                   			<option value="2">拼团</option>
                   			<option value="4">砍价</option>
                   			<option value="5">满减/满赠</option>
                   		</select>
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
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="activityPromoteSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanActivityPromoteFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="activityPromoteDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
