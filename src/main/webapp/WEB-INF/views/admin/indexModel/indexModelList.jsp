<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var indexModelDataGrid;
    $(function () {
    	indexModelDataGrid = $('#indexModelDataGrid').datagrid({
            url: '${path }/indexModel/dataGrid',
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
                title: '模块名称',
                align: 'center',
                field: 'modelName'
            },{
                width: '20%',
                title: '模块图标',
                align: 'center',
                field: 'modelImg',
                formatter:function(value,row,index){
                	var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                	html += '<a href="javascript:void(0)" class="indexModel-easyui-linkbutton-search" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun('+index+');" >查看大图</ a>';
                	return html;           	
    			}
            },{
                width: '15%',
                title: '用户类型',
                align: 'center',
                field: 'type',
                formatter:function(value,row,index){
                	if(value==0){
                		return "消费者";
                	}else{
                		return "店面工作人员";
                	}          	
    			}
            },{
                width: '10%',
                title: '排序',
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
                width : '25%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/indexModel/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="indexModel-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editIndexModelFun(\'{0}\');" >编辑</a>', row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/indexModel/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="indexModel-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="indexModelDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.indexModel-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.indexModel-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.indexModel-easyui-linkbutton-search').linkbutton({text:'查看大图'});
            },
            toolbar : '#indexModelToolbar'
        });
    });
    
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(index){
		var row = indexModelDataGrid.datagrid('getRows')[index];
	    var simg =  row.modelImg;
	        $('#bigImg').dialog({    
	            title: '预览',    
	            width: 500,    
	            height:500,    
	            resizable:true,    
	            closed: false,    
	            cache: false,    
	            modal: true    
	        });    
	        $("#simg").attr("src",simg);  
	}
    
    //添加字段内容
    function addIndexModelFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '50%',
            href : '${path }/indexModel/addPage',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = indexModelDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#indexModelAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editIndexModelFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/indexModel/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = indexModelDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#indexModelEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function indexModelDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = indexModelDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			indexModelDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/indexModel/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						indexModelDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanIndexModelFun() {
		$('#tIndexModelSearchForm input').val('');
		indexModelDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function indexModelSearchFun() {
		indexModelDataGrid.datagrid('load', $.serializeObject($('#tIndexModelSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tIndexModelSearchForm">
            <table>
                <tr>
                    <td>
                    	<shiro:hasPermission name="/indexModel/add">
                    		<a onclick="addIndexModelFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
                    	</shiro:hasPermission>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="indexModelDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
