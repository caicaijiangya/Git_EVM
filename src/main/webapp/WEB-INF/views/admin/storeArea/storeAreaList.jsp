<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var storeAreaDataGrid;
    $(function () {
    	storeAreaDataGrid = $('#storeAreaDataGrid').datagrid({
            url: '${path }/storeArea/dataGrid',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '1',
                title: 'ID',
                field: 'id',
                hidden:true
            },{
                width: '25%',
                title: '地市名称',
                align: 'center',
                field: 'name'
            },{
                width: '25%',
                title: '排序',
                align: 'center',
                field: 'seq'
            },{
                width: '25%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '25%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/storeArea/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="storeArea-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editStoreAreaFun(\'{0}\',\'{1}\');" >编辑</a>', row.id,row.pid);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/storeArea/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="storeArea-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="storeAreaDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.storeArea-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.storeArea-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#storeAreaToolbar'
        });
    });
    
    
    //添加字段内容
    function addStoreAreaFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '40%',
            href : '${path }/storeArea/addPage',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = storeAreaDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#storeAreaAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editStoreAreaFun(id,pid) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '40%',
            href : '${path }/storeArea/editPage?id='+id+'&provinceId='+pid,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = storeAreaDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#storeAreaEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function storeAreaDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = storeAreaDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			storeAreaDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/storeArea/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						storeAreaDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanStoreAreaFun() {
		$('#tStoreAreaSearchForm input').val('');
		storeAreaDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function storeAreaSearchFun() {
		storeAreaDataGrid.datagrid('load', $.serializeObject($('#tStoreAreaSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tStoreAreaSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="name" placeholder="条件搜索"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="storeAreaSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanStoreAreaFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="storeAreaDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="storeAreaToolbar" style="display: none;">
	<shiro:hasPermission name="/storeArea/add">
  		<a onclick="addStoreAreaFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>
