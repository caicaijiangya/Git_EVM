<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var goodsDataGrid;
    $(function () {
    	goodsDataGrid = $('#goodsDataGrid').datagrid({
            url: '${path }/goods/dataGrid',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            nowrap: false,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '5%',
                title: 'ID',
                field: 'id',
                align : 'center'
            },{
                width: '15%',
                title: '商品名称',
                align : 'center',
                field: 'goodsName'
            },{
                width: '15%',
                title: '商品分类',
                align : 'center',
                field: 'classifyName'
            },{
                width: '7%',
                title: '商品品牌',
                align : 'center',
                field: 'brandName'
            },{
                width: '5%',
                title: '排序',
                align : 'center',
                field: 'seq'
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
                width: '10%',
                title: '永久二维码',
                align : 'center',
                field: 'qrCode',
                formatter:function(value,row,index){
                	if(value==null){
                		var html = $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-qrcode" data-options="plain:true,iconCls:\'glyphicon-repeat icon-blue\'" onclick="createQrCode(\'{0}\');" >生成二维码</ a>', row.id);
                		return html;
                	}else{
                		var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                    	html += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-big" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun(\'{0}\');" >查看大图</ a>', row.qrCode);
                    	return html; 
                	}
    			}
            },{
                width : '5%',
                title : '状态',
                align : 'center',
                field : 'status',
                formatter:function(value,row,index){
                	if(value==0){
                		return '<font color="red">待上架</font>'
                	}else{
                		return '<font color="blue">已上架</font>';
                	}
    			}
            },{
                width : '5%',
                title : '是否隐藏',
                align : 'center',
                field : 'isShow',
                formatter:function(value,row,index){
                	if(value==0){
                		return '否'
                	}else{
                		return '是';
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
                    str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-spec" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="searcheditGoodsSpecFun(\'{0}\');" >商品规格</a>', row.id);
                    <shiro:hasPermission name="/goods/audit">
	                    if(row.status == 0){
	                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-audit1" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="auditGoodsFun(\'{0}\',\'{1}\');" >上架</a>', row.id,1);
	                    }else{
	                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-audit2" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="auditGoodsFun(\'{0}\',\'{1}\');" >下架</a>', row.id,0);
	                    }
                    str += '<br/>';
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/goods/edit">
                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="editGoodsFun(\'{0}\');" >编辑</a>', row.id);
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/goods/delete">
                    	str += $.formatString('<a href="javascript:void(0)" class="order-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="delGoodsFun(\'{0}\');" >删除</a>', row.id);
                    </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.order-easyui-linkbutton-spec').linkbutton({text:'商品规格'});
            	$('.order-easyui-linkbutton-qrcode').linkbutton({text:'生成二维码'});
            	$('.order-easyui-linkbutton-big').linkbutton({text:'查看大图'});
            	$('.order-easyui-linkbutton-audit1').linkbutton({text:'上架'});
            	$('.order-easyui-linkbutton-audit2').linkbutton({text:'下架'});
            	$('.order-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.order-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#goodsToolbar'
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
		$.post('${path }/goods/qrCode', {
			id : id
		}, function(result) {
			if (result.success) {
				showMsg(result.msg);
				goodsDataGrid.datagrid('reload');
			}
			progressClose();
		}, 'JSON');
	}
    /**
	 * 删除
	 */
	function delGoodsFun(id) {
		parent.$.messager.confirm('温馨提示', '您是否要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/goods/delete', {
					id : id,
					isDel : 1
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						goodsDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	/**
	 * 审核
	 */
	function auditGoodsFun(id,status) {
		var msg = '';
		if(status == 0){
			msg = '您是否要下架该商品？';	
		}else{
			msg = '您是否要上架该商品？';
		}
		parent.$.messager.confirm('温馨提示', msg, function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/goods/delete', {
					id : id,
					status : status
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						goodsDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	//添加字段内容
    function addGoodsFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '60%',
            height : '80%',
            href : '${path }/goods/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = goodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#goodsAddForm');
                    f.submit();
                }
            } ]
        });
    }
	function editGoodsFun(id){
		parent.$.modalDialog({
            title : '编辑',
            width : '60%',
            height : '80%',
            href : '${path }/goods/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = goodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#goodsEditForm');
                    f.submit();
                }
            } ]
        });
    }
	
	/**
	 * 编辑商品规格
	 */
    function searcheditGoodsSpecFun(id) {
        var opts = {
       		title : '商品规格',
       		border : false,
       		closable : true,
       		fit : true,
       	};
       	var url = '/goods/specManager?id='+id;
       	if (url && url.indexOf("http") == -1) {
       		url = '${path }' + url;
       	}
       	opts.href = url;
       	addCloseTab(opts);
    }
	/**
	 * 清除
	 */
	function cleanGoodsFun() {
		$('#searchGoodsForm input').val('');
		goodsDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function searchGoodsFun() {
		goodsDataGrid.datagrid('load', $.serializeObject($('#searchGoodsForm')));
	}
	
	/**
	 * 商品下载
	 */
	function goodsDownFun(){
		 var param ='?goodsName='+encodeURIComponent(encodeURIComponent($("#goodsName").val()))
		 +'&brandId='+encodeURIComponent(encodeURIComponent($("#brandId").val()))
		 +'&status='+encodeURIComponent(encodeURIComponent($("#status").val()));
		window.location.href = '${path }/goods/downLoadGoods'+param;
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="searchGoodsForm">
           <table>
                <tr>
                    <th>关键字：</th>
                    <td>
                    	<input name="goodsName" id="goodsName" placeholder="条件搜索"/>
                   	</td>
                   	<!-- <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th>商品分类: </th>	 
                   	<td>
                   		<select name="classifyId" id="classifyId">
                   			<option value="">请选择</option>
                   			<c:forEach items="${classifys }" var="item">
	                    		<option value="${item.id }">${item.name }</option>
	                    	</c:forEach>
                   		</select>
                   	</td> -->
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th>商品品牌: </th>	 
                   	<td>
                   		<select name="brandId" id="brandId">
                   			<option value="">请选择</option>
                   			<c:forEach items="${brands }" var="item">
	                    		<option value="${item.id }">${item.name }</option>
	                    	</c:forEach>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th>商品状态: </th>	 
                   	<td>
                   		<select name="status" id="status">
                   			<option value="">请选择</option>
                   			<option value="0">待上架</option>
                   			<option value="1">已上架</option>
                   		</select>
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchGoodsFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanGoodsFun();">清空</a>&nbsp;&nbsp;
                        <shiro:hasPermission name="/goods/goodsDown">
                        	<a onclick="goodsDownFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出商品</a>
                    	</shiro:hasPermission>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="goodsDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
    <div id="goodsToolbar" style="display: none;">
    	<shiro:hasPermission name="/goods/add">
	    	<a onclick="addGoodsFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
    	</shiro:hasPermission>
    </div>
</div>
