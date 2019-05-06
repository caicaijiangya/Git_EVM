<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var agoodsDataGrid;
    $(function () {
    	agoodsDataGrid = $('#agoodsDataGrid').datagrid({
            url: '${path }/activity/search?id=${id}&storeId=${storeId}&activityType=${activityType}',
            striped: true,
            pagination: true,
            rownumbers : true,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
            	field:'ck',
            	checkbox:true
            },{
                width: '10%',
                title : '序号',
                align : 'center',
                field : 'id',
                hidden:true
            },{
                width: '25%',
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
                title: '品牌名称',
                align : 'center',
                field: 'name'          
            },{
                width: '15%',
                title: '图片',
                align: 'center',
                field: 'filePath',
                formatter:function(value,row,index){
                	if(value==null){
                		return "暂无图片";
                	}else{
                		var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                    	html += '<a href="javascript:void(0)" class="activity-easyui-linkbutton-look" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun('+index+');" >查看大图</ a>';
                    	return html; 
                	}
                	          	
    			}
            },{
                width: '7%',
                title: '活动价格',
                align : 'center',
                field: 'activityPrice'          
            },{
                width: '7%',
                title: '限购数量',
                align : 'center',
                field: 'buyNum'          
            },{
                width: '7%',
                title: '库存',
                align : 'center',
                field: 'activityAmount'          
            },{
                field : 'action',
                title : '操作',
                width : '30%',
                align : 'center',
                formatter : function(value, row, index) {
                	var activityType = '${activityType }';
                	var str="";
                	str += $.formatString('<a href="javascript:void(0)" class="activityGoods-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteActivityGoodsFun(\'{0}\',\'{1}\',\'{2}\');" >取消指派</a>', row.id,row.activityAmount,row.goodsId);
                	str += '<br/>';
                	if(activityType == 2){
                		str += $.formatString('<a href="javascript:void(0)" class="activityGoods-easyui-linkbutton-activityBargainParameter" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="setActivityCollageParameterFun(\'{0}\',\'{1}\',\'{2}\');" >编辑活动商品拼团参数</a>', row.id,row.goodsId,row.activityType);
                    	str += '<br/>';
                	}
                	if(activityType == 4){
                		str += $.formatString('<a href="javascript:void(0)" class="activityGoods-easyui-linkbutton-activityBargainParameter" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="setActivityBargainParameterFun(\'{0}\',\'{1}\',\'{2}\');" >编辑活动商品砍价参数</a>', row.id,row.goodsId,row.activityType);
                    	str += '<br/>';
                	}
                	if(activityType != 5 && activityType != 6 && activityType != 7){
	                	str += $.formatString('<a href="javascript:void(0)" class="activityGoods-easyui-linkbutton-activityParameter" data-options="plain:true,iconCls:\'glyphicon-time icon-blue\'" onclick="setActivityParameterFun(\'{0}\',\'{1}\',\'{2}\');" >编辑活动商品参数</a>',row.id,row.goodsId,row.activityType);
	                	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                		str += $.formatString('<a href="javascript:void(0)" class="activityGoods-easyui-linkbutton-activityGoodsImg" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="setActivityGoodsImgFun(\'{0}\',\'{1}\',\'{2}\');" >编辑活动商品图片</a>',row.id,row.goodsId,row.activityType);
                	}
                	return str;
                }
            }] ],
 			onLoadSuccess:function(data){
 				$('.activityGoods-easyui-linkbutton-activityBargainParameter').linkbutton({text:'编辑活动商品砍价参数'});
 				$('.activityGoods-easyui-linkbutton-activityParameter').linkbutton({text:'编辑活动商品参数'});
 				$('.activityGoods-easyui-linkbutton-activityGoodsImg').linkbutton({text:'编辑活动商品图片'});
 				$('.activityGoods-easyui-linkbutton-del').linkbutton({text:'取消指派'});
 				$('.activityGoods-easyui-linkbutton-look').linkbutton({text:'查看大图'});
 			}
    	
        });
    });
    
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(index){
		var row = agoodsDataGrid.datagrid('getRows')[index];
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
    
    /**
	 * 清除
	 */
	function cleanFun() {
		$('#tSearchAppointForm input').val('');
		agoodsDataGrid.datagrid('load', {});
	}
	
	/**
	 * 搜索
	 */
	function searchFun() {
		agoodsDataGrid.datagrid('load', $.serializeObject($('#tSearchAppointForm')));
	}
	
	/**
	 * 取消指派
	 */
	function deleteActivityGoodsFun(id,activityAmount,goodsId) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = agoodsDataGrid.datagrid('getSelections');
			id = rows[0].id;
			activityAmount = rows[0].activityAmount;
			goodsId=rows[0].goodsId;
		} else {//点击操作里面的删除图标会触发这个
			agoodsDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		if(activityAmount == null || activityAmount == 'null' || activityAmount == ''){
			activityAmount = 0;
		}
		parent.$.messager.confirm('温馨提示', '您确定要取消吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/activity/deleteList', {
					id : id,activityAmount:activityAmount,goodsId:goodsId
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						agoodsDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	//设置拼团参数
	function setActivityCollageParameterFun(id,goodsId,activityType){
		parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '50%',
            href : '${path }/activity/editCollageParameterPage?id='+id+'&goodsId='+goodsId+'&activityType='+activityType,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#collageParameterEditForm');
                    f.submit();
                }
            } ]
        });
	}
	//设置砍价参数
	function setActivityBargainParameterFun(id,goodsId,activityType) {
        parent.$.modalDialog({
            title : '编辑',
            width : '50%',
            height : '80%',
            href : '${path }/activity/editBargainParameterPage?id='+id+'&goodsId='+goodsId+'&activityType='+activityType,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#bargainParameterEditForm');
                    f.submit();
                }
            } ]
        });
    }
	//设置拼团参数
	function setActivityParameterFun(id,goodsId,activityType) {
        parent.$.modalDialog({
            title : '编辑',
            width : '50%',
            height : '60%',
            href : '${path }/activity/editParameterPage?id='+id+'&goodsId='+goodsId+'&activityType='+activityType,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#collageParameterEditForm');
                    f.submit();
                }
            } ]
        });
    }
	
	//编辑活动商品图片
	function setActivityGoodsImgFun(id,goodsId,activityType) {
        parent.$.modalDialog({
            title : '编辑',
            width : '50%',
            height : '60%',
            href : '${path }/activity/editActivityGoodsImg?id='+id+'&goodsId='+goodsId+'&activityType='+activityType,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = agoodsDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#imgEditForm');
                    f.submit();
                }
            } ]
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tSearchAppointForm">
        	<input name="activityType" type="hidden" value="${activityType }"/>
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="name" placeholder="请输入商品名称进行搜索"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="agoodsDataGrid" data-options="fit:true,border:false"></table>
    </div>  
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
