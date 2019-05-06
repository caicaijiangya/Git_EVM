<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var goodsSpecDataGrid;
    $(function () {
    	goodsSpecDataGrid = $('#goodsSpecDataGrid').datagrid({
            url: '${path }/goods/dataSpecGrid?id=${id}',
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
                align : 'center',
                hidden:true
            },{
                width: '15%',
                title: '商品名称',
                align : 'center',
                field: 'goodsName'
            },{
                width: '10%',
                title: '规格名称',
                align : 'center',
                field: 'specName'
            },{
                width: '10%',
                title: 'SKU',
                align : 'center',
                field: 'goodsCode'
            },{
                width: '10%',
                title: '规格图',
                align : 'center',
                field: 'goodsImage',
                formatter:function(value,row,index){
                	if(value==null){
                		return "暂无图片";
                	}else{
                		var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                    	html += $.formatString('<a href="javascript:void(0)" class="spec-easyui-linkbutton-big" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun(\'{0}\');" >查看大图</ a>', row.goodsImage);
                    	return html; 
                	}
    			}
            },{
                width: '7%',
                title: '商品单价',
                align : 'center',
                field: 'goodsPrice'
            },{
                width: '7%',
                title: '市场价',
                align : 'center',
                field: 'marketPrice'
            },{
                width: '8%',
                title: '物流商品库存',
                align : 'center',
                field: 'goodsAmount'
            },{
                width: '10%',
                title: '商品总剩余库存',
                align : 'center',
                field: 'goodsRemAmount'
            },{
                width : '5%',
                title : '排序',
                align : 'center',
                field : 'seq'
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
                    str += $.formatString('<a href="javascript:void(0)" class="spec-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="editGoodsSpecFun(\'{0}\');" >编辑</a>', row.id);
                	str += $.formatString('<a href="javascript:void(0)" class="spec-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="delGoodsSpecFun(\'{0}\');" >删除</a>', row.id);
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.spec-easyui-linkbutton-big').linkbutton({text:'查看大图'});
            	$('.spec-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.spec-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#goodsSpecToolbar'
        });
    });
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(simg){
 		$("#simg").attr("src",simg); 
	        $('#bigImg').dialog({    
	            title: '预览',    
	            width: 500,    
	            height:500,    
	            resizable:true,    
	            closed: false,    
	            cache: false,    
	            modal: true    
	        });    
	         
	}
    /**
	 * 删除
	 */
	function delGoodsSpecFun(id) {
		parent.$.messager.confirm('温馨提示', '您是否要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/goods/deleteSpec', {
					id : id,
					isDel : 1
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						goodsSpecDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	//添加字段内容
    function addGoodsSpecFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '70%',
            href : '${path }/goods/addSpecPage?id=${id}',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = goodsSpecDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#goodsSpecAddForm');
                    f.submit();
                }
            } ]
        });
    }
	function editGoodsSpecFun(id){
		parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '70%',
            href : '${path }/goods/editSpecPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = goodsSpecDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#goodsSpecEditForm');
                    f.submit();
                }
            } ]
        });
    }
	/**
	 * 清除
	 */
	function cleanGoodsSpecFun() {
		$('#searchGoodsSpecForm input').val('');
		goodsSpecDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function searchGoodsSpecFun() {
		goodsSpecDataGrid.datagrid('load', $.serializeObject($('#searchGoodsSpecForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="searchGoodsSpecForm">
           <table>
                <tr>
                    <th>关键字：</th>
                    <td>
                    	<input name="goodsName" id="goodsName" placeholder="条件搜索"/>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchGoodsSpecFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanGoodsSpecFun();">清空</a>&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="goodsSpecDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
    <div id="goodsSpecToolbar" style="display: none;">
    	<a onclick="addGoodsSpecFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
    </div>
</div>
