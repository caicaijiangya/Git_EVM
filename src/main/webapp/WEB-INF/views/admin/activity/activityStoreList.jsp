<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var activityStoreDataGrid;
    $(function () {
    	activityStoreDataGrid = $('#activityStoreDataGrid').datagrid({
            url: '${path }/activity/storeDataGrid?id=${id}',
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
                width: '20%',
                title: '活动标题',
                align : 'center',
                field: 'title'
            },{
                width: '25%',
                title: '门店名称',
                align : 'center',
                field: 'name'
            },{
                width: '15%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '20%',
                align : 'center',
                formatter : function(value, row, index) {
                	var str="";
                	var activityType = '${activityType}';
                	if(activityType == 6){
	                	str += $.formatString('<a href="javascript:void(0)" class="activityStore-easyui-linkbutton-fullgift" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editActivityFullgift(\'{0}\',\'{1}\');" >编辑赠品</a>', row.id,row.storeId);
	                	str += '<br/>';
                	}
                	str += $.formatString('<a href="javascript:void(0)" class="activityStore-easyui-linkbutton-assist" data-options="plain:true,iconCls:\'glyphicon-hand-right icon-blue\'" onclick="appointGoodsFun(\'{0}\',\'{1}\');" >指派商品</a>', row.id,row.storeId);
            		str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			str += $.formatString('<a href="javascript:void(0)" class="activityStore-easyui-linkbutton-search" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="searchActivityGoodsFun(\'{0}\',\'{1}\');" >查看指派</a>',row.id,row.storeId);
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.activityStore-easyui-linkbutton-fullgift').linkbutton({text:'编辑赠品'});
                $('.activityStore-easyui-linkbutton-assist').linkbutton({text:'指派商品'});
                $('.activityStore-easyui-linkbutton-search').linkbutton({text:'查看指派'});
            },
            toolbar : '#activityStoreToolbar'
        });
    });
    
  //编辑
    function editActivityStoreFun() {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '60%',
            href : '${path }/activity/editStorePage?id=${id}',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityStoreDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#activityStoreEditForm');
                    f.submit();
                }
            } ]
        });
    }
   
    /**
	 * 删除
	 */
	function deleteActivityStoreFun(id) {
		parent.$.messager.confirm('温馨提示', '您确定要删除吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/activity/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						activityStoreDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
    
	 //编辑赠品
    function editActivityFullgift(id,storeId) {
        var opts = {
	       		title : '活动赠品',
	       		border : false,
	       		closable : true,
	       		fit : true,
	       	};
	       	var url = '/activity/fullgiftPage?id='+id+'&storeId='+storeId;
	       	if (url && url.indexOf("http") == -1) {
	       		url = '${path }' + url;
	       	}
	       	opts.href = url;
	       	addCloseTab(opts);
    }
	/**
	 * 指派商品展示页
	 */
    function appointGoodsFun(id,storeId) {
        parent.$.modalDialog({
            title : '指派商品',
            width : '45%',
            height : '60%',
            href : '${path }/activity/appointGoods?id='+id+'&activityType=${activityType}&storeId='+storeId
        });
    }
	
    /**
	 * 查看已指派
	 */
	    function searchActivityGoodsFun(id,storeId) {
	        var opts = {
	       		title : '已指派的商品',
	       		border : false,
	       		closable : true,
	       		fit : true,
	       	};
	       	var url = '/activity/searchGoods?id='+id+'&activityType=${activityType}&storeId='+storeId;
	       	if (url && url.indexOf("http") == -1) {
	       		url = '${path }' + url;
	       	}
	       	opts.href = url;
	       	addCloseTab(opts);
	    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <table id="activityStoreDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="activityStoreToolbar" style="display: none;">
	<a onclick="editActivityStoreFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">编辑门店</a>
</div>