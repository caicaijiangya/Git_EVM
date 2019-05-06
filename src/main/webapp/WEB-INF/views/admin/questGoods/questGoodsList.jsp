<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var questGoodsDataGrid;
    $(function () {
    	questGoodsDataGrid = $('#questGoodsDataGrid').datagrid({
            url: '${path }/questGoods/dataGrid',
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
                width: '15%',
                title: '分类名称',
                align: 'center',
                field: 'name'
            },{
                width: '30%',
                title: '商品名称',
                align: 'center',
                field: 'goodsName'
            },{
                width: '10%',
                title: '排序',
                align: 'center',
                field: 'seq'
            },{
                width : '15%',
                title : '固定序号',
                align : 'center',
                field : 'nail',
                formatter:function(value,row,index){
                	if(value==0){
                		return '固定'
                	}else if(value==1){
                		return "周期一";
                	}else if(value==2){
                		return "周期二";
                	}else if(value==3){
                		return "基础商品";
                	}
    			}
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
                    <shiro:hasPermission name="/questGoods/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="questGoods-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editQuestGoodsFun(\'{0}\',\'{1}\');" >编辑</a>', row.id,row.classifyId);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/questGoods/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="questGoods-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="questGoodsDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.questGoods-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.questGoods-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#questGoodsToolbar'
        });
    });
    
    
    //添加字段内容
    function addQuestGoodsFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '50%',
            href : '${path }/questGoods/addPage',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = questGoodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#questGoodsAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editQuestGoodsFun(id,classifyId) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/questGoods/editPage?id='+id+'&classifyId='+classifyId,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = questGoodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#questGoodsEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function questGoodsDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = questGoodsDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			questGoodsDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/questGoods/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						questGoodsDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanQuestGoodsFun() {
		$('#tQuestGoodsSearchForm input').val('');
		questGoodsDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function questGoodsSearchFun() {
		questGoodsDataGrid.datagrid('load', $.serializeObject($('#tQuestGoodsSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tQuestGoodsSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="name" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 组合: </th>	 
                   	<td>
                   		<select name="nail" id="nail">
                   			<option value="">请选择</option>
                   			<option value="0">不固定</option>
                   			<option value="1">组合一</option>
                   		</select>
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="questGoodsSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanQuestGoodsFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="questGoodsDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="questGoodsToolbar" style="display: none;">
	<shiro:hasPermission name="/questGoods/add">
  		<a onclick="addQuestGoodsFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>
