<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var storeGoodsDataGrid;
    var storeTree;
    var storeId = 0;
    var storeName = null;
    $(function() {
    	storeTree = $('#storeTree').tree({
             url : '${path }/storeGoods/allTree',
             parentField : 'pid',
             lines : true,
             onClick : function(node) {
            	 storeId = node.id;
            	 storeName = node.text;
            	 storeGoodsDataGrid.datagrid('load', {
            		 storeId: storeId
                 });
             }
         });
    	
    	storeGoodsDataGrid = $('#storeGoodsDataGrid').datagrid({
        url : '${path}/storeGoods/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        nowrap: false,
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
            width : '10%',
            title : '序号',
            field : 'id',
            hidden:true
        }, {
            width : '20%',
            title : '门店名称',
            field : 'storeName',
            align : 'center'
        }, {
            width : '20%',
            title : '商品名称',
            field : 'goodsName',
            align : 'center'
        },{
            width : '10%',
            title : '规格名称',
            field : 'specName',
            align : 'center'
        },{
            width : '10%',
            title : '商品图片',
            field : 'goodsImage',
            align : 'center',
            formatter : function(value, row, index) {
            	return '<img src="'+value+'" width="80" height="80"/>'
            }
        },{
            width : '10%',
            title : '商品品牌',
            field : 'brandName',
            align : 'center'
        }, {
            width : '15%',
            title : '创建时间',
            field : 'createdTime',
            align : 'center'
        },{
            width : '5%',
            title : '库存',
            field : 'goodsAmount',
            align : 'center'
        }, {
            field : 'action',
            title : '操作',
            align : 'center',
            width : '10%',
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/storeGoods/edit">
                	str += $.formatString('<a href="javascript:void(0)" class="storeGoods-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="storeGoodsEditFun(\'{0}\');" >分配库存</a>', row.id);
                </shiro:hasPermission>
                <shiro:hasPermission name="/storeGoods/delete">
                	str += $.formatString('<a href="javascript:void(0)" class="storeGoods-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="storeGoodsDeleteFun(\'{0}\');" >删除</a>', row.id);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.storeGoods-easyui-linkbutton-edit').linkbutton({text:'分配库存'});
            $('.storeGoods-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#storeGoodsToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function storeGoodsAddFun() {
	if(storeId == null || storeId == 0){
		showMsg('请选择门店');
		return;
	}
    parent.$.modalDialog({
        title : '添加',
        width : '50%',
        height : '60%',
        href : '${path }/storeGoods/addPage?storeId='+storeId+'&storeName='+encodeURI(encodeURI(storeName)),
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = storeGoodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#storeGoodsAddForm');
                f.submit();
            }
        } ]
    });
}

/**
 * 编辑框
 * @param url
 */
function storeGoodsEditFun(id) {
    parent.$.modalDialog({
        title : '添加',
        width : '50%',
        height : '60%',
        href : '${path }/storeGoods/editPage?id='+id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = storeGoodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#storeGoodsEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function storeGoodsDeleteFun(id) {
     parent.$.messager.confirm('询问', '您是否要删除当前门店商品？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path }/storeGoods/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     showMsg(result.msg);
                     storeGoodsDataGrid.datagrid('reload');
                     storeTree.tree('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function storeGoodsCleanFun() {
    $('#storeGoodsSearchForm input').val('');
    storeGoodsDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function storeGoodsSearchFun() {
	var obj = $.serializeObject($('#storeGoodsSearchForm'));
	obj.storeId = storeId;
	storeGoodsDataGrid.datagrid('load', obj);
}

/**
 * 商品下载
 */
function storeGoodsDownFun(){
	 var param ='?storeId='+storeId+'&goodsName='+encodeURIComponent(encodeURIComponent($("#goodsName").val()));
	window.location.href = '${path }/storeGoods/downLoadStoreGoods'+param;
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="storeGoodsSearchForm">
            <table>
                <tr>
                    <th>商品名称：</th>
                    <td><input name="goodsName" id="goodsName" class="form-control" placeholder="输入搜索关键字"/></td>
                    <td>
                    	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="storeGoodsSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="storeGoodsCleanFun();">清空</a>
                        <shiro:hasPermission name="/storeGoods/add">
                    		<a onclick="storeGoodsAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
                    	</shiro:hasPermission>
                    	<shiro:hasPermission name="/storeGoods/goodsDown">
                    		<a onclick="storeGoodsDownFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">门店商品导出</a>
                    	</shiro:hasPermission>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:true,title:'商品列表'" style="height:100px;">
        <table id="storeGoodsDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'west',border:true,split:true,title:'门店'" style="width:160px;overflow: hidden; ">
        <ul id="storeTree" style="margin: 10px;"></ul>
    </div>
</div>
