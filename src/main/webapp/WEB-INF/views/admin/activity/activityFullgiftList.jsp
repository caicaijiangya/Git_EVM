<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var activityFullgiftDataGrid;
    $(function () {
    	activityFullgiftDataGrid = $('#activityFullgiftDataGrid').datagrid({
            url: '${path }/activity/fullgiftDataGrid?activityId=${activityId }&storeId=${storeId}',
            striped: true,
            nowrap:false,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
            sortName : 'id',
            sortOrder : 'desc',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '5%',
                title: '序号',
                align: 'center',
                field: 'id',
                hidden:true
            },{
                width: '16%',
                title: '门店',
                align : 'center',
                field: 'storeName'
            },{
                width: '8%',
                title: '条件',
                align : 'center',
                field: 'fullPrice'
            },{
                width: '10%',
                title: '商品图片',
                align : 'center',
                field: 'goodsImage',
                formatter:function(value,row,index){
                	if(value==null){
                		return "暂无图片";
                	}else{
                		var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                    	html += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-big" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun(\'{0}\');" >查看大图</ a>', row.goodsImage);
                    	return html; 
                	}
    			}
            },{
                width: '20%',
                title: '赠品名称',
                align : 'center',
                field: 'goodsName'
            },{
                width: '8%',
                title: '赠品数量',
                align : 'center',
                field: 'goodsNum'
            },{
                width: '8%',
                title: '赠品库存',
                align : 'center',
                field: 'amount'
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
                	var str="";
                	str += $.formatString('<a href="javascript:void(0)" class="activityFullgift-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editActivityFullgiftFun(\'{0}\');" >编辑</a>', row.id);
        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			str += $.formatString('<a href="javascript:void(0)" class="activityFullgift-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteActivityFullgiftFun(\'{0}\');" >删除</a>', row.id);
                	
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.activityFullgift-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.activityFullgift-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#activityFullgiftToolbar'
        });
    });
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(simg){
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
  //添加
    function addActivityFullgiftFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '30%',
            height : '50%',
            href : '${path }/activity/addFullgiftPage?activityId=${activityId}&storeId=${storeId}',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityFullgiftDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#activityFullgiftAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //编辑
    function editActivityFullgiftFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '30%',
            height : '50%',
            href : '${path }/activity/editFullgiftPage?activityId=${activityId}&id='+id+'&storeId=${storeId}',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityFullgiftDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#activityFullgiftEditForm');
                    f.submit();
                }
            } ]
        });
    }
   
    /**
	 * 删除
	 */
	function deleteActivityFullgiftFun(id) {
		parent.$.messager.confirm('温馨提示', '您确定要删除吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/activity/delFullgift', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						activityFullgiftDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <table id="activityFullgiftDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
    <img id="simg"  width="100%" height="100%"></img>
</div>
<div id="activityFullgiftToolbar" style="display: none;">
	<a onclick="addActivityFullgiftFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
</div>