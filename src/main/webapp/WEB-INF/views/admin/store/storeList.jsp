<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var storeDataGrid;
    $(function () {
    	storeDataGrid = $('#storeDataGrid').datagrid({
            url: '${path }/store/dataGrid',
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
                title: '门店编码',
                align: 'center',
                field: 'storeNo'
            },{
                width: '20%',
                title: '门店名称',
                align: 'center',
                field: 'storeName'
            },{
                width: '10%',
                title: '小程序二维码',
                align : 'center',
                field: 'qrCode',
                formatter:function(value,row,index){
                	if(value==null){
                		var html = $.formatString('<a href="javascript:void(0)" class="store-easyui-linkbutton-qrcode" data-options="plain:true,iconCls:\'glyphicon-repeat icon-blue\'" onclick="createQrCode(\'{0}\');" >生成二维码</ a>', row.id);
                		return html;
                	}else{
                		var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                    	html += $.formatString('</br><a href="javascript:void(0)" class="store-easyui-linkbutton-big" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun(\'{0}\');" >查看大图</ a>', row.qrCode);
                    	return html; 
                	}
    			}
            },{
                width: '10%',
                title: '所在省',
                align: 'center',
                field: 'name1'
            },{
                width: '10%',
                title: '所在市',
                align: 'center',
                field: 'name2'
            },{
                width: '10%',
                title: '所在区',
                align: 'center',
                field: 'name3'
            },{
                width: '20%',
                title: '门店地址',
                align: 'center',
                field: 'addressDetail'
            },{
                width: '10%',
                title: '门店电话',
                align: 'center',
                field: 'mobileNo'
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
                    <shiro:hasPermission name="/store/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="store-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editStoreFun(\'{0}\',\'{1}\',\'{2}\');" >编辑</a>', row.id,row.province,row.city);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/store/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="store-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="storeDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.store-easyui-linkbutton-qrcode').linkbutton({text:'生成二维码'});
            	$('.store-easyui-linkbutton-big').linkbutton({text:'查看大图'});
                $('.store-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.store-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            },
            toolbar : '#storeToolbar'
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
 	/**
	 * 生成二维码
	 */
	function createQrCode(id) {
		progressLoad();
		$.post('${path }/store/qrCode', {
			id : id
		}, function(result) {
			if (result.success) {
				showMsg(result.msg);
				storeDataGrid.datagrid('reload');
			}
			progressClose();
		}, 'JSON');
	}
    
    //添加字段内容
    function addStoreFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '90%',
            href : '${path }/store/addPage',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = storeDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#storeAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editStoreFun(id,province,city) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '90%',
            href : '${path }/store/editPage?id='+id+'&provinceId='+province+'&cityId='+city,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = storeDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#storeEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function storeDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = storeDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			storeDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/store/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						storeDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanStoreFun() {
		$('#tStoreSearchForm input').val('');
		storeDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function storeSearchFun() {
		storeDataGrid.datagrid('load', $.serializeObject($('#tStoreSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tStoreSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="storeName" placeholder="条件搜索"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="storeSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanStoreFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="storeDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
<div id="storeToolbar" style="display: none;">
	<shiro:hasPermission name="/store/add">
  		<a onclick="addStoreFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>
