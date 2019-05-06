<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var userCouponDataGrid;
    $(function() {
    	userCouponDataGrid = $('#userCouponDataGrid').datagrid({
        url : '${path}/coupon/userCouponDataGrid?id=${id}',
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
            width : '35%',
            title : '领取时间',
            align : 'center',
            field : 'createdTime'
        },{
            width : '35%',
            title : '领取人',
            align : 'center',
            field : 'nickName'
        },{
            width : '30%',
            title : '是否使用',
            field : 'status',
            align : 'center',
            formatter : function(value, row, index) {
           	 if(value == 0){
           		return '未使用';
           	 }else{
           		return '已使用';
           	 }
     		}
        } ] ],
    });
});
  
	/**
	 * 清除
	 */
	function cleanFun() {
		$('#tUserCouponSearchForm input').val('');
		userCouponDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function searchFun() {
		userCouponDataGrid.datagrid('load', $.serializeObject($('#tUserCouponSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tUserCouponSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="nickName" placeholder="条件搜索"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="userCouponDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>