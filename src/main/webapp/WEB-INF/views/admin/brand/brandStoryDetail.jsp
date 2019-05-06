<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tXyBrandStoryDetailDataGrid;
    $(function() {
        tXyBrandStoryDetailDataGrid = $('#tXyBrandStoryDetailDataGrid').datagrid({
        url : '${path}/brandStoryDetail/dataGrid?brandStoryId=${storyInfo.id}',
        striped : true,
        rownumbers : true,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'asc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        frozenColumns : [ [ {
            width : '15%',
            title : '标题',
            field : 'title',
            align : 'center'
        }, {
            width : '5%',
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
            width : '45%',
            title : '描述',
            field : 'brandDetail',
            align : 'center'
        },{
            width : '5%',
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
                str += $.formatString('<a href="javascript:void(0)" class="tXyBrandStoryDetail-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="tXyBrandStoryDetailEditFun(\'{0}\');" >编辑</a>', row.id);
                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                str += $.formatString('<a href="javascript:void(0)" class="tXyBrandStoryDetail-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="tXyBrandStoryDetailDeleteFun(\'{0}\');" >删除</a>', row.id);
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.tXyBrandStoryDetail-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.tXyBrandStoryDetail-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#tXyBrandStoryDetailToolbar'
    });
});

/**
 * 添加框
 * @param url
 */
function tXyBrandStoryDetailAddFun() {
    parent.$.modalDialog({
        title : '添加',
        width : 700,
        height : 600,
        href : '${path }/brandStoryDetail/addPage?brandStoryId=${storyInfo.id}',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = tXyBrandStoryDetailDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#tXyBrandStoryDetailAddForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 编辑
 */
function tXyBrandStoryDetailEditFun(id) {
    if (id == undefined) {
        var rows = tXyBrandStoryDetailDataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        tXyBrandStoryDetailDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '编辑',
        width : 700,
        height : 600,
        href :  '${path }/brandStoryDetail/editPage?id=' + id,
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = tXyBrandStoryDetailDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#tXyBrandStoryDetailEditForm');
                f.submit();
            }
        } ]
    });
}


/**
 * 删除
 */
 function tXyBrandStoryDetailDeleteFun(id) {
     if (id == undefined) {//点击右键菜单才会触发这个
         var rows = tXyBrandStoryDetailDataGrid.datagrid('getSelections');
         id = rows[0].id;
     } else {//点击操作里面的删除图标会触发这个
         tXyBrandStoryDetailDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
     }
     parent.$.messager.confirm('询问', '您是否要删除当前内容？', function(b) {
         if (b) {
             progressLoad();
             $.post('${path }/brandStoryDetail/delete', {
                 id : id
             }, function(result) {
                 if (result.success) {
                     parent.$.messager.alert('提示', result.msg, 'info');
                     tXyBrandStoryDetailDataGrid.datagrid('reload');
                 }
                 progressClose();
             }, 'JSON');
         }
     });
}


/**
 * 清除
 */
function tXyBrandStoryDetailCleanFun() {
    $('#tXyBrandStoryDetailSearchForm input').val('');
    tXyBrandStoryDetailDataGrid.datagrid('load', {});
}
/**
 * 搜索
 */
function tXyBrandStoryDetailSearchFun() {
     tXyBrandStoryDetailDataGrid.datagrid('load', $.serializeObject($('#tXyBrandStoryDetailSearchForm')));
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="tXyBrandStoryDetailSearchForm">
            <table>
                <tr>
                    <th>名称:</th>
                    <td><input name="title" placeholder="搜索条件"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="tXyBrandStoryDetailSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="tXyBrandStoryDetailCleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="tXyBrandStoryDetailDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="tXyBrandStoryDetailToolbar" style="display: none;">
      <a onclick="tXyBrandStoryDetailAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-page-add'">添加</a>
</div>