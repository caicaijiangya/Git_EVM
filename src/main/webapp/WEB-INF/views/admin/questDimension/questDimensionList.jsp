<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var questDimensionDataGrid;
    $(function () {
    	questDimensionDataGrid = $('#questDimensionDataGrid').datagrid({
            url: '${path }/questDimension/dataGrid',
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
                title: '商品分类',
                align: 'center',
                field: 'classifyName'
            },{
                width: '20%',
                title: '维度名称',
                align: 'center',
                field: 'name'
            },{
                width : '15%',
                title : '英文标识',
                align : 'center',
                field : 'key'
            },{
                width: '15%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '15%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/questDimension/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="questDimension-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editQuestDimensionFun(\'{0}\',\'{1}\');" >编辑</a>', row.id,row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/questDimension/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="questDimension-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="questDimensionDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.questDimension-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.questDimension-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#questDimensionToolbar'
        });
    });
    
    
    //添加字段内容
    function addQuestDimensionFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '50%',
            href : '${path }/questDimension/addPage',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = questDimensionDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#questDimensionAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editQuestDimensionFun(id,classifyId) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/questDimension/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = questDimensionDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#questDimensionEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function questDimensionDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = questDimensionDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			questDimensionDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/questDimension/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						questDimensionDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanQuestDimensionFun() {
		$('#questDimensionSearchForm input').val('');
		questDimensionDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function questDimensionSearchFun() {
		questDimensionDataGrid.datagrid('load', $.serializeObject($('#questDimensionSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="questDimensionSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="name" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="questDimensionSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanQuestDimensionFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="questDimensionDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="questDimensionToolbar" style="display: none;">
	<shiro:hasPermission name="/questDimension/add">
	  <a onclick="addQuestDimensionFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>
