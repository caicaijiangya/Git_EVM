<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var popConfigDataGrid;
    $(function () {
    	popConfigDataGrid = $('#popConfigDataGrid').datagrid({
            url: '${path }/popConfig/dataGrid',
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
                width: '10%',
                title: '标题',
                align : 'center',
                field: 'title'
            },{
                width: '10%',
                title: '优惠券',
                align : 'center',
                field: 'couponName'
            },{
                width: '10%',
                title: '图片一',
                align : 'center',
                field: 'image1',
                formatter:function(value,row,index){
                	if(value==null){
                		var html = "无图片";
                		return html;
                	}else{
                		var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                    	html += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-big" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun(\'{0}\');" >查看大图</ a>', row.image1);
                    	return html; 
                	}
    			}
            },{
                width: '10%',
                title: '图片二',
                align : 'center',
                field: 'image2',
                formatter:function(value,row,index){
                	if(value==null){
                		var html = "无图片";
                		return html;
                	}else{
                		var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                    	html += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-big" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun(\'{0}\');" >查看大图</ a>', row.image2);
                    	return html; 
                	}
    			}
            },{
                width: '10%',
                title: '是否跳转',
                align : 'center',
                field: 'url',
                formatter:function(value,row,index){
                	if(value == null || value == ''){
                		return "无跳转";
                	}else{
                		return "有跳转";
                	}
    			}
            },{
                width: '10%',
                title: '自动关闭（秒）',
                align : 'center',
                field: 'timing'
            },{
                width: '10%',
                title: '类型',
                align : 'center',
                field: 'type',
                formatter:function(value,row,index){
                	if(value == 0){
                		return "普通";
                	}else if(value == 1){
                		return "优惠券领取";
                	}
    			}
            },{
                width : '5%',
                title : '状态',
                align : 'center',
                field : 'status',
                formatter:function(value,row,index){
                	if(value==0){
                		return '<font color="blue">正常</font>'
                	}else{
                		return '<font color="red">关闭</font>';
                	}
    			}
            },{
                width: '10%',
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
                    <shiro:hasPermission name="/popConfig/close">
	                    if(row.status == 0){
	                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-audit1" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="closePopConfigFun(\'{0}\',\'{1}\');" >关闭</a>', row.id,1);
	                    }else if(row.status == 1){
	                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-audit2" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="closePopConfigFun(\'{0}\',\'{1}\');" >开启</a>', row.id,0);
	                    }
	                    str += '<br/>';
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/popConfig/edit">
                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="editPopConfigFun(\'{0}\');" >编辑</a>', row.id);
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/popConfig/delete">
                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="delPopConfigFun(\'{0}\');" >删除</a>', row.id);
                    </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.order-easyui-linkbutton-big').linkbutton({text:'查看大图'});
            	$('.order-easyui-linkbutton-audit1').linkbutton({text:'关闭'});
            	$('.order-easyui-linkbutton-audit2').linkbutton({text:'开启'});
            	$('.order-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.order-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#popConfigToolbar'
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
    function addPopConfigFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '70%',
            href : '${path }/popConfig/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = popConfigDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#popConfigAddForm');
                    f.submit();
                }
            } ]
        });
    }
  	//编辑
    function editPopConfigFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '70%',
            href : '${path }/popConfig/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = popConfigDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#popConfigEditForm');
                    f.submit();
                }
            } ]
        });
    }
  	
    /**
	 * 删除
	 */
	function delPopConfigFun(id) {
		parent.$.messager.confirm('温馨提示', '您是否要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/popConfig/status', {
					id : id,
					isDel : 1
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						popConfigDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	/**
	 * 关闭
	 */
	function closePopConfigFun(id,status) {
		parent.$.messager.confirm('温馨提示', '您是否要关闭？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/popConfig/status', {
					id : id,
					status : status
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						popConfigDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	/**
	 * 清除
	 */
	function cleanGoodsFun() {
		$('#searchPopConfigForm input').val('');
		popConfigDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function searchGoodsFun() {
		popConfigDataGrid.datagrid('load', $.serializeObject($('#searchPopConfigForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="searchPopConfigForm">
           <table>
                <tr>
                    <th>关键字：</th>
                    <td>
                    	<input name="goodsName" id="goodsName" placeholder="条件搜索"/>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchGoodsFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanGoodsFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="popConfigDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
    <div id="popConfigToolbar" style="display: none;">
    	<shiro:hasPermission name="/popConfig/add">
	    	<a onclick="addPopConfigFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
    	</shiro:hasPermission>
    </div>
</div>
