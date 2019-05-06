<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tXyWxCashCouponDataGrid;
    $(function() {
        tXyWxCashCouponDataGrid = $('#tXyWxCashCouponDataGrid').datagrid({
        url : '${path}/wxCashCoupon/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
            width : '10%',
            title : '商户名称',
            field : 'brandName',
            align : 'center'
        },{
            width : '10%',
            title : '卡劵名称',
            field : 'title',
            align : 'center'
        },{
            width : '7%',
            title : '创建数量',
            field : 'quantity',
            align : 'center'
        },{
            width : '7%',
            title : '金额',
            field : 'money',
            align : 'center'
        },{
            width : '7%',
            title : '使用门槛',
            field : 'fullMoney',
            align : 'center'
        }, {
            width : '15%',
            title : '开始时间',
            field : 'beginTimestamp',
            align : 'center'
        }, {
            width : '15%',
            title : '结束时间',
            field : 'endTimestamp',
            align : 'center'
        }, {
            width : '15%',
            title : '创建时间',
            field : 'createdTime',
            align : 'center',
        }, {
            field : 'action',
            title : '操作',
            align : 'center',
            width : '15%',
            formatter : function(value, row, index) {
                var str = '';
                var isActivity = row.isActivity;
                if(isActivity && isActivity==1){
                	str += $.formatString('<a href="javascript:void(0)" class="tXyWxCashCoupon-easyui-linkbutton-received" data-options="plain:true,iconCls:\'fi-download icon-blue\'">已领取</a>');
                }else{
                	<shiro:hasPermission name="/tXyWxCashCoupon/receive">
                  		str += $.formatString('<a href="javascript:void(0)" class="tXyWxCashCoupon-easyui-linkbutton-receive" data-options="plain:true,iconCls:\'fi-download icon-blue\'" onclick="receiveCash(\'{0}\',{1});" >领取立减金</a>', row.id,row.isActivity);
                  	</shiro:hasPermission>
                }
                str += $.formatString('<a href="javascript:void(0)" class="tXyWxCashCoupon-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tXyWxCashCouponDeleteFun(\'{0}\');" >删除</a>', row.id);
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.tXyWxCashCoupon-easyui-linkbutton-received').linkbutton({text:'已领取'});
            $('.tXyWxCashCoupon-easyui-linkbutton-receive').linkbutton({text:'领取立减金'});
            $('.tXyWxCashCoupon-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#tXyWxCashCouponToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function tXyWxCashCouponAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path}/wxCashCoupon/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = tXyWxCashCouponDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#tXyWxCashCouponAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function receiveCash(id,isActivity) {
    if (id == undefined) {
        var rows = tXyWxCashCouponDataGrid.datagrid('getSelections');
        id = rows[0].id;
        isActivity = rows[0].isActivity;
    } else {
        tXyWxCashCouponDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    $.post('${path}/wxCashCoupon/receiveCash', {
        id : id,
        isActivity:isActivity
    }, function(result) {
        if (result.success) {
            parent.$.messager.alert('提示', result.msg, 'info');
            tXyWxCashCouponDataGrid.datagrid('reload');
        }else{
        	parent.$.messager.alert('提示', result.msg, 'info');
        }
        progressClose();
    }, 'JSON');
}


/**
 * 删除
 */
 function tXyWxCashCouponDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = tXyWxCashCouponDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         tXyWxCashCouponDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前信息?', function(b) {
         if (b) {
             progressLoad();
             $.post('${path}/wxCashCoupon/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     tXyWxCashCouponDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function tXyWxCashCouponCleanFun() {
    $('#tXyWxCashCouponSearchForm input').val('');
    tXyWxCashCouponDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function tXyWxCashCouponSearchFun() {
     tXyWxCashCouponDataGrid.datagrid('load', $.serializeObject($('#tXyWxCashCouponSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tXyWxCashCouponSearchForm">
            <table>
                <tr>
                    <th>名称:</th>
                    <td><input name="name" placeholder="搜索条件"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="tXyWxCashCouponSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="tXyWxCashCouponCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="tXyWxCashCouponDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="tXyWxCashCouponToolbar" style="display: none;">
	<shiro:hasPermission name="/tXyWxCashCoupon/add">
    	<a onclick="tXyWxCashCouponAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>