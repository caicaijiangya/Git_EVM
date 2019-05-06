<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var integralActivityDataGrid;
    $(function () {
    	integralActivityDataGrid = $('#integralActivityDataGrid').datagrid({
            url: '${path }/integralActivity/dataGrid',
            striped: true,
            nowrap:false,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '5%',
                title: '序号',
                align: 'center',
                field: 'id',
                hidden:true
            },{
                width: '20%',
                title: '活动标题',
                align : 'center',
                field: 'title'
            },{
                width: '10%',
                title: '规则类型',
                align : 'center',
                field: 'type',
                formatter : function(value, row, index) {
                	if(value == 1){
                		return "多倍积分";
                	}else if(value==2){
                		return "邀请好友获得积分";
                	}
                }
            },{
                width: '10%',
                title: '倍数',
                align : 'center',
                field: 'multiple'
            },{
                width: '10%',
                title: '赠送积分',
                align : 'center',
                field: 'integral'
            },{
                width: '15%',
                title: '开始时间',
                align : 'center',
                field: 'startTime'
            },{
                width: '15%',
                title: '结束时间',
                align : 'center',
                field: 'endTime'
            },{
                width: '10%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '15%',
                align : 'center',
                formatter : function(value, row, index) {
                	var str="";
                	str += $.formatString('<a href="javascript:void(0)" class="integralActivity-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editIntegralActivityFun(\'{0}\');" >编辑</a>', row.id);
        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			str += $.formatString('<a href="javascript:void(0)" class="integralActivity-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteIntegralActivityFun(\'{0}\');" >删除</a>', row.id);
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.integralActivity-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.integralActivity-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#integralActivityToolbar'
        });
    });
    
  //添加
    function addIntegralActivityFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '70%',
            href : '${path }/integralActivity/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = integralActivityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#integralActivityAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //编辑
    function editIntegralActivityFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '70%',
            href : '${path }/integralActivity/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = integralActivityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#integralActivityEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function deleteIntegralActivityFun(id) {
		parent.$.messager.confirm('温馨提示', '您确定要删除吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/integralActivity/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						integralActivityDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
    
    
	/**
	 * 清除
	 */
	function cleanIntegralActivityFun() {
		$('#integralActivitySearchForm input').val('');
		integralActivityDataGrid.datagrid('load', {});
	}
	
	/**
	 * 搜索
	 */
	function searchIntegralActivityFun() {
		integralActivityDataGrid.datagrid('load', $.serializeObject($('#integralActivitySearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="integralActivitySearchForm">
            <table>
                <tr>
                    <th>条件搜索：</th>
                    <td><input name="title" placeholder="输入条件进行搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 类型: </th>	 
                   	<td>
                   		<select name="type" id="type">
                   			<option value="">请选择</option>
                   			<option value="1">多倍积分</option>
                   			<option value="2">邀请好友获得积分</option>
                   		</select>
                   	</td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <td>
                         <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchIntegralActivityFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanIntegralActivityFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="integralActivityDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="integralActivityToolbar" style="display: none;">
	<a onclick="addIntegralActivityFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
</div>