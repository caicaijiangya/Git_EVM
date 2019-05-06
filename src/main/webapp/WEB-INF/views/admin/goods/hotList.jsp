<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var hotDataGrid;
    $(function () {
    	hotDataGrid = $('#hotDataGrid').datagrid({
            url: '${path }/goodsOther/dataGridHot',
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
                width: '20%',
                title: '商品名称',
                align : 'center',
                field: 'goodsName'
            },{
                width: '10%',
                title: '商品图片',
                align : 'center',
                field: 'goodsImage',
                formatter:function(value,row,index){
                	return "<img src='"+value+"' width='80' height='80'/>";
                	
                }
            },{
                width: '10%',
                title: '商品品牌',
                align : 'center',
                field: 'brandName'
            },{
                width: '10%',
                title: '热门分类',
                align : 'center',
                field: 'type',
                formatter:function(value,row,index){
                	if(value == 0){
                		return "人气精选";
                	}else if(value == 1){
                		return "经典口碑";
                	}
                }
            },{
                width: '5%',
                title: '尺寸',
                align : 'center',
                field: 'size'
            },{
                width: '5%',
                title: '序号',
                align : 'center',
                field: 'seq'
            },{
                width: '15%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '10%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/hot/edit">
                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="editHotFun(\'{0}\');" >编辑</a>', row.id);
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/hot/delete">
                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="delHotFun(\'{0}\');" >删除</a>', row.id);
                    </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.order-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.order-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#hotToolbar'
        });
    	hotDataGrid.datagrid('load', $.serializeObject($('#searchHotForm')));
    });
    
    /**
	 * 删除
	 */
	function delHotFun(id) {
		parent.$.messager.confirm('温馨提示', '您是否要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/goodsOther/deleteHot', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						hotDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	//添加字段内容
    function addHotFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '50%',
            height : '50%',
            href : '${path }/goodsOther/addHotPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = hotDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#addHotForm');
                    f.submit();
                }
            } ]
        });
    }
	function editHotFun(id){
		parent.$.modalDialog({
            title : '编辑',
            width : '50%',
            height : '50%',
            href : '${path }/goodsOther/editHotPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = hotDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#editHotForm');
                    f.submit();
                }
            } ]
        });
    }
	/**
	 * 清除
	 */
	function cleanHotFun() {
		$('#searchHotForm input').val('');
		hotDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function searchHotFun() {
		hotDataGrid.datagrid('load', $.serializeObject($('#searchHotForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="searchHotForm">
           <table>
                <tr>
                   	<th>热门类型: </th>	 
                   	<td>
                   		<select name="type" id="type">
                   			<option value="0" selected="selected">人气精选</option>
                   			<option value="1">经典口碑</option>
                   		</select>
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchHotFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanHotFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="hotDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="hotToolbar" style="display: none;">
    	<shiro:hasPermission name="/hot/add">
	    	<a onclick="addHotFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
    	</shiro:hasPermission>
    </div>
</div>
