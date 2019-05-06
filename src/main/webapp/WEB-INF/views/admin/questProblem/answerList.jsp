<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var answerDataGrid;
    $(function () {
    	answerDataGrid = $('#answerDataGrid').datagrid({
            url: '${path }/questProblem/answerData?id=${id}',
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
                width: '30%',
                title: '答案',
                align: 'center',
                field: 'title'
            },{
                width: '20%',
                title: '分数',
                align: 'center',
                field: 'score'
            },{
                width: '10%',
                title: '排序',
                align: 'center',
                field: 'seq2'
            },{
                width: '20%',
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
                	str += $.formatString('<a href="javascript:void(0)" class="answer-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editAnswerFun(\'{0}\');" >编辑</a>', row.id);
        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			str += $.formatString('<a href="javascript:void(0)" class="answer-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="answerDeleteFun(\'{0}\');" >删除</a>', row.id);
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.answer-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.answer-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#answerToolbar'
        });
    });
    
    //添加字段内容
    function addAnswerFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '50%',
            href : '${path }/questProblem/addAnswerPage?problemId=${id}',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = answerDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#answerAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editAnswerFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/questProblem/editAnswerPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = answerDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#answerEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function answerDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = answerDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			answerDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/questProblem/deleteAnswer', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						answerDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tAnswerSearchForm">
             <table>
                <tr>
                    <td>
                    <a onclick="addAnswerFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="answerDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
