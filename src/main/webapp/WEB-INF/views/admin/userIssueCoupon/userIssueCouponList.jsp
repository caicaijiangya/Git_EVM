<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var couponActivityDataGrid;
    $(function() {
    	userIssueCouponDataGrid = $('#userIssueCouponDataGrid').datagrid({
        url : '${path}/userIssueCoupon/dataGrid',
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
            width : '20%',
            title : '优惠券',
            align : 'center',
            field : 'title'
        },{
            width : '10%',
            title : '用户昵称',
            align : 'center',
            field : 'nickName'
        },{
            width : '10%',
            title : '用户姓名',
            align : 'center',
            field : 'userName'
        },{
            width : '10%',
            title : '手机号',
            field : 'mobileNo',
            align : 'center'
        },{
            width : '5%',
            title : '会员类型',
            field : 'userType',
            align : 'center'
        },{
            width : '5%',
            title : '会员卡号',
            field : '0',
            align : 'center'
        },{
            width : '5%',
            title : '新老客户',
            field : 'newOld',
            align : 'center'
        },{
            width : '10%',
            title : '所属门店',
            field : 'storeName',
            align : 'center'
        },{
            width : '10%',
            title : '成功数',
            field : 'successNum',
            align : 'center'
        },{
            width : '10%',
            title : '失败数',
            align : 'center',
            field : 'errorNum'
        },{
            width : '10%',
            title : '发放总数',
            field : 'userNum',
            align : 'center'
        },{
            width : '15%',
            title : '发放时间',
            field : 'createdTime',
            align : 'center'
        },{
            width : '10%',
            title : '发放人',
            field : 'name',
            align : 'center'
        } ] ],
        onLoadSuccess:function(data){
        },
        toolbar : '#userIssueCouponToolbar'
    });
});
    
    
    
    //添加内容
    function addUserIssueCouponFun() {
    	var opts = {
           		title : '新增发券',
           		border : false,
           		closable : true,
           		fit : true,
           	};
           	var url = '/userIssueCoupon/addPage'
           	if (url && url.indexOf("http") == -1) {
           		url = '${path }' + url;
           	}
           	opts.href = url;
           	addCloseTab(opts);
    }
    
    /**
	 * 优惠券人工发放下载
	 */
	function userIssueCouponDownFun(){
		 var param ='?title='+encodeURIComponent(encodeURIComponent($("#title").val()))
		 +'&type='+encodeURIComponent(encodeURIComponent($('#type').combobox('getValue')))
		 +'&createdTime='+encodeURIComponent(encodeURIComponent($("#createdTime").val()));
		window.location.href = '${path }/excel/userIssueConpon'+param;
	}
	/**
	 * 清除
	 */
	function cleanUserIssueCouponFun() {
		$('#userIssueCouponSearchForm input').val('');
		userIssueCouponDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function userIssueCouponSearchFun() {
		userIssueCouponDataGrid.datagrid('load', $.serializeObject($('#userIssueCouponSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="userIssueCouponSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input id="title" name="title" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>发放状态：</th>
                    <td>
                    	<select id="type" name="type" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<option value="">全部</option>
                    		<option value="0">发放成功</option>
                    		<option value="1">发放失败</option>
                    	</select>
                    </td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>发放时间：</th>
                    <td><input type="text" id="createdTime" name="createdTime" value=""  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="userIssueCouponSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanUserIssueCouponFun();">清空</a>
                    	<a onclick="userIssueCouponDownFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="userIssueCouponDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="userIssueCouponToolbar" style="display: none;">
	<a onclick="addUserIssueCouponFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
</div>