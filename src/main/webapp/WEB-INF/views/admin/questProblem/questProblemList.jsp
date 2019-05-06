<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var questProblemDataGrid;
    $(function () {
    	questProblemDataGrid = $('#questProblemDataGrid').datagrid({
            url: '${path }/questProblem/dataGrid',
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
                title: '维度名称',
                align: 'center',
                field: 'name'
            },{
                width: '30%',
                title: '问题内容',
                align: 'center',
                field: 'title'
            },{
                width : '15%',
                title : '问题类型',
                align : 'center',
                field : 'type',
                formatter:function(value,row,index){
                	if(value==0){
                		return '默认'
                	}else if(value==1){
                		return "皮肤问题";
                	}else if(value==2){
                		return "花费问题";
                	}else if(value==3){
                		return "职业问题";
                	}else if(value==4){
                		return "地区问题";
                	}else if(value==5){
                		return "年龄问题";
                	}
    			}
            },{
                width: '10%',
                title: '顺序',
                align: 'center',
                field: 'seq'
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
                    <shiro:hasPermission name="/questProblem/answer">
	                    str += $.formatString('<a href="javascript:void(0)" class="questProblem-easyui-linkbutton-answer" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="answerFun(\'{0}\');" >查看答案</a>', row.id);
	                    str += '<br/>';
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/questProblem/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="questProblem-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editProblemFun(\'{0}\');" >编辑</a>', row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/questProblem/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="questProblem-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="problemDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.questProblem-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.questProblem-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.questProblem-easyui-linkbutton-answer').linkbutton({text:'查看答案'});
            },
            toolbar : '#questProblemToolbar'
        });
    });
    
    
    //查看答案
     function answerFun(id) {
	        var opts = {
	       		title : '答案',
	       		border : false,
	       		closable : true,
	       		fit : true,
	       	};
	       	var url = '/questProblem/answer?id='+id;
	       	if (url && url.indexOf("http") == -1) {
	       		url = '${path }' + url;
	       	}
	       	opts.href = url;
	       	addCloseTab(opts);
	    }
    
    //添加字段内容
    function addProblemFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '50%',
            href : '${path }/questProblem/addPage',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = questProblemDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#problemAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editProblemFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/questProblem/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = questProblemDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#problemEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function problemDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = questProblemDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			questProblemDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/questProblem/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						questProblemDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanProblemFun() {
		$('#tProblemSearchForm input').val('');
		questProblemDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function problemSearchFun() {
		questProblemDataGrid.datagrid('load', $.serializeObject($('#tProblemSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tProblemSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="title" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 类型: </th>	 
                   	<td>
                   		<select name="type" id="type">
                   			<option value="">请选择</option>
                   			<option value="0">默认</option>
                   			<option value="1">皮肤问题</option>
                   			<option value="2">花费问题</option>
                   			<option value="3">职业问题</option>
                   			<option value="4">地区问题</option>
                   		</select>
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="problemSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanProblemFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="questProblemDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="questProblemToolbar" style="display: none;">
	<shiro:hasPermission name="/questProblem/add">
  		<a onclick="addProblemFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>
