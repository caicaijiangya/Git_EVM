<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var dictDataGrid;
    $(function () {
    	dictDataGrid = $('#dictDataGrid').datagrid({
            url: '${path }/dict/dataGrid',
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
                width: '20%',
                title: '编码',
                align : 'center',
                field: 'dictCode'
            },{
                width: '20%',
                title: '名称',
                align : 'center',
                field: 'dictValue'
            },{
                width: '10%',
                title: '顺序',
                align : 'center',
                field: 'indexs',
                sortable: true
            },{
                width: '25%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime',
                sortable: true
            },{
                field : 'action',
                title : '操作',
                width : '25%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/dict/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="dict-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editDictFun(\'{0}\');" >编辑</a>', row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/dict/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="dict-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="dictDeleteFun(\'{0}\',\'{1}\');" >删除</a>', row.id, row.dictValue);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.dict-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.dict-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#dictToolbar'
        });
    });
    
    //添加字段内容
    function addDictFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '50%',
            href : '${path }/dict/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dictDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#dictAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editDictFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/dict/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dictDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#dictEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function dictDeleteFun(id, dictValue) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = dictDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			dictDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否要删除【'+dictValue+'】字段？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/dict/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						dictDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanDictFun() {
		$('#tDictSearchForm input').val('');
		dictDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function dictSearchFun() {
		dictDataGrid.datagrid('load', $.serializeObject($('#tDictSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tDictSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="dictValue" placeholder="条件搜索"/></td>
                    <td>
                         <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="dictSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanDictFun();">清空</a>
                    </td>
                   	<td >
                   		<shiro:hasPermission name="/dict/add">
				        	<a onclick="addDictFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
				    	</shiro:hasPermission>
				    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="dictDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
