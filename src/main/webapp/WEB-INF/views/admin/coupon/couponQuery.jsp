<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var couponQueryDataGrid;
    $(function() {
    	couponQueryDataGrid = $('#couponQueryDataGrid').datagrid({
        url : '${path}/coupon/queryDataGrid',
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
            title : '会员昵称',
            field : 'nickName',
            align : 'center'
        },{
            width : '10%',
            title : '会员姓名',
            field : 'userName',
            align : 'center'
        },{
            width : '10%',
            title : '会员手机号',
            field : 'mobileNo',
            align : 'center'
        },{
            width : '5%',
            title : '会员类型',
            align : 'center',
            field : 'userType'
        },{
            width : '5%',
            title : '会员卡号',
            align : 'center',
            field : '1'
        },{
            width : '5%',
            title : '新老客户',
            align : 'center',
            field : 'newOld'
        },{
            width : '10%',
            title : '所属门店',
            align : 'center',
            field : 'storeName'
        },{
            width : '15%',
            title : '发券日期',
            align : 'center',
            field : 'issueTime'
        },{
            width : '10%',
            title : '券来源',
            field : 'source',
            align : 'center'
        },{
            width : '15%',
            title : '优惠券名称',
            field : 'title',
            align : 'center'
        },{
            width : '10%',
            title : '优惠金额',
            field : 'moneyName',
            align : 'center'
        },{
            width : '10%',
            title : '满额条件',
            field : 'fullMoney',
            align : 'center'
        },{
            width : '12%',
            title : '优惠券券号',
            field : 'couponNo',
            align : 'center'
        },{
            width : '10%',
            title : '优惠券状态',
            field : 'status',
            align : 'center',
            formatter : function(value, row, index) {
           	 if(value == 0){
           		return '正常';
           	 }else{
           		return '禁用';
           	 }
     		}
        },{
            width : '15%',
            title : '开始时间',
            align : 'center',
            field : 'startTime'
        },{
            width : '15%',
            title : '结束时间',
            field : 'endTime',
            align : 'center'
        },{
            width : '5%',
            title : '是否使用',
            field : 'statusName',
            align : 'center'
        },{
            width : '10%',
            title : '使用时间',
            field : 'createdTime',
            align : 'center'
        },{
            width : '15%',
            title : '使用门店',
            field : 'useStoreName',
            align : 'center'
        },{
            width : '10%',
            title : '使用订单号',
            field : 'orderNo',
            align : 'center'
        }] ],
        toolbar : '#couponQueryToolbar'
    });
});
    
    /**
	 * 优惠券查询下载
	 */
	function couponQueryDownFun(){
		 var param ='?title='+encodeURIComponent(encodeURIComponent($("#title").val()))
		 +'&type='+encodeURIComponent(encodeURIComponent($('#type').combobox('getValue')))
		 +'&createdTime='+encodeURIComponent(encodeURIComponent($("#createdTime").val()))
		 +'&couponName='+encodeURIComponent(encodeURIComponent($("#couponName").val()))
		 +'&couponNo='+encodeURIComponent(encodeURIComponent($("#couponNo").val()))
		 +'&storeId='+encodeURIComponent(encodeURIComponent($('#storeId').combobox('getValue')));
		window.location.href = '${path }/excel/couponQuery'+param;
	}
	/**
	 * 清除
	 */
	function cleanQueryCouponFun() {
		$('#couponQuerySearchForm input').val('');
		couponQueryDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function couponQuerySearchFun() {
		couponQueryDataGrid.datagrid('load', $.serializeObject($('#couponQuerySearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 105px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="couponQuerySearchForm">
            <table>
                <tr>
                    <th>所属会员：</th>
                    <td><input id="title" name="title" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>券发放方式：</th>
                    <td>
                    	<select id="type" name="type" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<option value="">全部</option>
                    		<option value="0">日常领券</option>
                    		<option value="1">砍价获得</option>
                    		<option value="2">扫码领券</option>
                    		<option value="3">券链接获得</option>
                    		<option value="4">新用户注册</option>
                    		<option value="5">人工发放</option>
                    		<option value="6">首页领取</option>
                    	</select>
                    </td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>发券时间：</th>
                    <td><input type="text" id="createdTime" name="createdTime" value=""  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></td>
                </tr>
                <tr>
                    <th>优惠券名称：</th>
                    <td><input id="couponName" name="couponName" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>券号：</th>
                    <td><input id="couponNo" name="couponNo" placeholder=""/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>所属门店：</th>
                    <td>
                    	<select id="storeId" name="storeId" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<option value="">全部</option>
                    		<c:forEach items="${storeList }" var="item">
	                    		<option value="${item.id }">${item.storeName }</option>
	                    	</c:forEach>
                    	</select>
                    </td>
                </tr>
                <tr>
                	<th></th>
                	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="couponQuerySearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanQueryCouponFun();">清空</a>
                    	<a onclick="couponQueryDownFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="couponQueryDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>