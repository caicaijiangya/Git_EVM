<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var cityFreightDataGrid;
    $(function () {
    	cityFreightDataGrid = $('#cityFreightDataGrid').datagrid({
            url: '${path }/cityFreight/dataGrid',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '10%',
                title: 'ID',
                field: 'id',
                hidden:true
            },{
                width: '20%',
                title: '省份',
                align : 'center',
                field: 'provinceName'
            },{
                width: '20%',
                title: '运费',
                align : 'center',
                field: 'money'
            },{
                width: '10%',
                title: '超出运费',
                align : 'center',
                field: 'exceedMoney'
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
                    <shiro:hasPermission name="/cityFreight/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="cityFreight-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editCityFreightFun(\'{0}\');" >编辑</a>', row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/cityFreight/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="cityFreight-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="cityFreightDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.cityFreight-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.cityFreight-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#cityFreightToolbar'
        });
    });
    
    //添加字段内容
    function addCityFreightFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '50%',
            href : '${path }/cityFreight/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = cityFreightDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#cityFreightAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editCityFreightFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/cityFreight/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = cityFreightDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#cityFreightEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function cityFreightDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = cityFreightDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			cityFreightDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否要删除吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/cityFreight/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						cityFreightDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanCityFreightFun() {
		$('#cityFreightSearchForm input').val('');
		cityFreightDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function cityFreightSearchFun() {
		cityFreightDataGrid.datagrid('load', $.serializeObject($('#cityFreightSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="cityFreightSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="provinceName" placeholder="条件搜索"/></td>
                    <td>
                         <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="cityFreightSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanCityFreightFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="cityFreightDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="cityFreightToolbar" style="display: none;">
    	<shiro:hasPermission name="/cityFreight/add">
	    	<a onclick="addCityFreightFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
    	</shiro:hasPermission>
    </div>
</div>
