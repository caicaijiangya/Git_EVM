<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var integralGoodsDataGrid;
    $(function () {
    	integralGoodsDataGrid = $('#integralGoodsDataGrid').datagrid({
            url: '${path }/integralGoods/dataGrid',
            striped: true,
            nowrap:false,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
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
                title: '积分商品名称',
                align : 'center',
                field: 'goodsName'
            },{
                width : '15%',
                title : '商品图片',
                align : 'center',
                field : 'filePath',
                formatter:function(value,row,index){
                	if(value==null){
                		return "暂无图片";
                	}else{
                		var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                    	html += '<a href="javascript:void(0)" class="integralGoods-easyui-linkbutton-big" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun('+index+');" >查看大图</ a>';
                    	return html; 
                	}
                	          	
    			}
            },{
                width: '10%',
                title: '商品类型',
                align : 'center',
                field: 'type',
                formatter : function(value, row, index) {
                 	 if(value == 0){
                 		return '商品';
                 	 }else if(value == 1){
                 		return '优惠券';
                 	 }
           		}
            },{
                width: '10%',
                title: '商品库存',
                align : 'center',
                field: 'goodsAmount'
            },{
                width: '10%',
                title: '积分价格',
                align : 'center',
                field: 'goodsPrice'
            },{
                width: '10%',
                title: '开始时间',
                align : 'center',
                field: 'startTime'
            },{
                width: '10%',
                title: '结束时间',
                align : 'center',
                field: 'endTime'
            },{
                field : 'action',
                title : '操作',
                width : '20%',
                align : 'center',
                formatter : function(value, row, index) {
                	var str="";
                	<shiro:hasPermission name="/integralGoods/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="integralGoods-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editFun(\'{0}\',\'{1}\');" >编辑</a>', row.id,row.goodsId);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/integralGoods/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="integralGoods-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteFun(\'{0}\',\'{1}\');" >删除</a>', row.id,row.goodsId);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.integralGoods-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.integralGoods-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.integralGoods-easyui-linkbutton-big').linkbutton({text:'查看大图'});
            },
            toolbar : '#integralGoodsToolbar'
        });
    });
    
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(index){
		var row = integralGoodsDataGrid.datagrid('getRows')[index];
	    var simg =  row.filePath;
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
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '50%',
            height : '60%',
            href : '${path }/integralGoods/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = integralGoodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#integralGoodsAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editFun(id,goodsId) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '60%',
            href : '${path }/integralGoods/editPage?id='+id+'&goodsId='+goodsId,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = integralGoodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#integralGoodsEditForm');
                    f.submit();
                }
            } ]
        });
    }
   
    /**
	 * 删除
	 */
	function deleteFun(id,goodsId) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = integralGoodsDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			integralGoodsDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/integralGoods/delete', {
					id : id,
					goodsId : goodsId
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						integralGoodsDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
    
	
	/**
	 * 清除
	 */
	function cleanFun() {
		$('#tIntegralGoodsSearchForm input').val('');
		integralGoodsDataGrid.datagrid('load', {});
	}
	
	/**
	 * 搜索
	 */
	function searchFun() {
		integralGoodsDataGrid.datagrid('load', $.serializeObject($('#tIntegralGoodsSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tIntegralGoodsSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="goodsName" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 奖品类型: </th>	 
                   	<td>
                   		<select name="type" id="type">
                   			<option value="">请选择</option>
                   			<option value="0">商品</option>
                   			<option value="1">优惠券</option>
                   		</select>
                   	</td>
                   	<td>
                         <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanFun();">清空</a>
                    </td>	
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="integralGoodsDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
<div id="integralGoodsToolbar" style="display: none;">
	<shiro:hasPermission name="/integralGoods/add">
	  <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>
