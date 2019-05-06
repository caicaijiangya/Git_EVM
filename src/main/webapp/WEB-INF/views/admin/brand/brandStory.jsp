<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tXyBrandStoryDataGrid;
    $(function() {
        tXyBrandStoryDataGrid = $('#tXyBrandStoryDataGrid').datagrid({
        url : '${path}/brandStory/dataGrid',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [{
            width : '20%',
            title : '标题',
            field : 'title',
            align : 'center'
        },{
            width : '10%',
            title : '作者',
            field : 'author',
            align : 'center'
        },{
            width : '20%',
            title : '标签',
            field : 'labelDesc',
            align : 'center'
        }, {
            width : '8%',
            title : '是否置顶',
            field : 'isTop',
            align : 'center',
            formatter : function(value, row, index) {
                switch (value) {
                case 0:
                    return '否';
                case 1:
                    return '是';
                }
            }
        }, {
            width : '8%',
            title : '是否显示',
            field : 'isShow',
            align : 'center',
            formatter : function(value, row, index) {
                switch (value) {
                case 0:
                    return '显示';
                case 1:
                    return '不显示';
                }
            }
        },{
            width : '4%',
            title : '排序',
            field : 'seq',
            align : 'center'
        }, {
            width : '15%',
            title : '创建时间',
            field : 'createdTime',
            align : 'center'
        }, {
            field : 'action',
            title : '操作',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
                var str = '';
                <shiro:hasPermission name="/tXyBrandStory/edit">
	                str += $.formatString('<a href="javascript:void(0)" class="tXyBrandStory-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="tXyBrandStoryEditFun(\'{0}\');" >编辑</a>', row.id);
	                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                </shiro:hasPermission>
                <shiro:hasPermission name="/tXyBrandStory/delete">
                	str += $.formatString('<a href="javascript:void(0)" class="tXyBrandStory-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tXyBrandStoryDeleteFun(\'{0}\');" >删除</a>', row.id);
                </shiro:hasPermission>
                str += '<br />';
                <shiro:hasPermission name="/tXyBrandStory/setBrand">
                	str += $.formatString('<a href="javascript:void(0)" class="tXyBrandStory-easyui-linkbutton-set-detail" data-options="plain:true,iconCls:\'fi-pencil icon-red\'" onclick="setBrandDetail(\'{0}\');" >设置内容</a>', row.id);
                </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.tXyBrandStory-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.tXyBrandStory-easyui-linkbutton-del').linkbutton({text:'删除'});
            $('.tXyBrandStory-easyui-linkbutton-set-detail').linkbutton({text:'设置内容'});
        },
        toolbar : '#tXyBrandStoryToolbar'
    });
});
    
/**
 * 设置内容
 * @param url
 */
function setBrandDetail(id) {
   var opts = {
		title : '设置内容',
		border : false,
		closable : true,
		fit : true,
	};
	var url = '/brandStoryDetail/manager?brandStoryId=' + id;
	if (url && url.indexOf("http") == -1) {
		url = '${path }' + url;
	}
	opts.href = url;
	addCloseTab(opts);
} 
/**
 * 添加框
 * @param url
 */
function tXyBrandStoryAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path }/brandStory/addPage',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = tXyBrandStoryDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#tXyBrandStoryAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function tXyBrandStoryEditFun(id) {
    if (id == undefined) {
        var rows = tXyBrandStoryDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        tXyBrandStoryDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 700,
        height : 600,
        href :  '${path }/brandStory/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = tXyBrandStoryDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#tXyBrandStoryEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function tXyBrandStoryDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = tXyBrandStoryDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         tXyBrandStoryDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前内容？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path }/brandStory/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     tXyBrandStoryDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function tXyBrandStoryCleanFun() {
    $('#tXyBrandStorySearchForm input').val('');
    tXyBrandStoryDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function tXyBrandStorySearchFun() {
     tXyBrandStoryDataGrid.datagrid('load', $.serializeObject($('#tXyBrandStorySearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="tXyBrandStorySearchForm">
            <table>
                <tr>
                    <th>名称:</th>
                    <td><input name="title" placeholder="搜索条件"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tXyBrandStorySearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="tXyBrandStoryCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="tXyBrandStoryDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="tXyBrandStoryToolbar" style="display: none;">
	<shiro:hasPermission name="/tXyBrandStory/add">
    	<a onclick="tXyBrandStoryAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
	</shiro:hasPermission>
</div>