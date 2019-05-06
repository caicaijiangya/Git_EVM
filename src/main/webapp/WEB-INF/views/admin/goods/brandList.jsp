<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var brandDataGrid;
    $(function () {
    	brandDataGrid = $('#brandDataGrid').datagrid({
            url: '${path }/goodsOther/dataGridBrand',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            nowrap: false,
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
                width: '30%',
                title: '名称',
                align : 'center',
                field: 'brandName'
            },{
                width: '20%',
                title: '序号',
                align : 'center',
                field: 'brandSeq'
            },{
                width: '25%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '20%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/brand/edit">
                    	str += $.formatString('<a href="javascript:void(0)" class="brand-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="editBrandFun(\'{0}\');" >编辑</a>', row.id);
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/brand/delete">
                    	str += $.formatString('<a href="javascript:void(0)" class="brand-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="delBrandFun(\'{0}\');" >删除</a>', row.id);
                    </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.brand-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.brand-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#brandtoolbar'
        });
    });
    
    /**
	 * 删除
	 */
	function delBrandFun(id) {
		parent.$.messager.confirm('温馨提示', '您是否要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/goodsOther/deleteBrand', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						brandDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	//添加字段内容
    function addBrandFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '30%',
            height : '40%',
            href : '${path }/goodsOther/addBrandPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = brandDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#addBrandForm');
                    f.submit();
                }
            } ]
        });
    }
	function editBrandFun(id){
		parent.$.modalDialog({
            title : '编辑',
            width : '30%',
            height : '40%',
            href : '${path }/goodsOther/editBrandPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = brandDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#editBrandForm');
                    f.submit();
                }
            } ]
        });
    }
	/**
	 * 清除
	 */
	function cleanBrandFun() {
		$('#searchBrandForm input').val('');
		brandDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function searchBrandFun() {
		brandDataGrid.datagrid('load', $.serializeObject($('#searchBrandForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <table id="brandDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="brandtoolbar" style="display: none;">
    	<shiro:hasPermission name="/brand/add">
	    	<a onclick="addBrandFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
    	</shiro:hasPermission>
    </div>
</div>
