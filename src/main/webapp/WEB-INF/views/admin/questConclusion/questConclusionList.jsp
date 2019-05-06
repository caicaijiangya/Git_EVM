<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var questConclusionDataGrid;
    $(function () {
    	questConclusionDataGrid = $('#questConclusionDataGrid').datagrid({
            url: '${path }/questConclusion/dataGrid',
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
                title: '结论名称',
                align: 'center',
                field: 'name'
            },{
                width : '15%',
                title : '英文组合标识',
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
                    <shiro:hasPermission name="/questConclusion/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="questConclusion-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editquestConclusionFun(\'{0}\',\'{1}\');" >编辑</a>', row.id,row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/questConclusion/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="questConclusion-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="questConclusionDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.questConclusion-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.questConclusion-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#questConclusionToolbar'
        });
    });
    
    
    //添加字段内容
    function addquestConclusionFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '50%',
            href : '${path }/questConclusion/addPage',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = questConclusionDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#questConclusionAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editquestConclusionFun(id,classifyId) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/questConclusion/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = questConclusionDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#questConclusionEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function questConclusionDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = questConclusionDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			questConclusionDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/questConclusion/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						questConclusionDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanquestConclusionFun() {
		$('#questConclusionSearchForm input').val('');
		questConclusionDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function questConclusionSearchFun() {
		questConclusionDataGrid.datagrid('load', $.serializeObject($('#questConclusionSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="questConclusionSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="name" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="questConclusionSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanquestConclusionFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="questConclusionDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="questConclusionToolbar" style="display: none;">
	<shiro:hasPermission name="/questConclusion/add">
	  <a onclick="addquestConclusionFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>
