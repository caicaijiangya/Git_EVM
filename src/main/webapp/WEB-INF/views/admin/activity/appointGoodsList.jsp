<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var activityGoodsDataGrid;
    $(function () {
    	activityGoodsDataGrid = $('#activityGoodsDataGrid').datagrid({
            url: '${path }/activity/dataGridAppoint?id=${id}&storeId=${storeId}&activityType=${activityType}',
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
                width: '30%',
                title: '商品名称',
                align : 'center',
                field: 'goodsName'
            },{
                width: '15%',
                title: '规格名称',
                align : 'center',
                field: 'specName'
            },{
                width: '15%',
                title: '品牌',
                align : 'center',
                field: 'name'          
            },{
                width: '40%',
                title: '图片',
                align: 'center',
                field: 'filePath',
                formatter:function(value,row,index){
                	var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                	html += '<a href="javascript:void(0)" class="activity-easyui-linkbutton-look" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun('+index+');" >查看大图</ a>';
                	return html;           	
    			}
            }] ],
 			onLoadSuccess:function(data){
 				$('.activity-easyui-linkbutton-look').linkbutton({text:'查看大图'});
            }
        });
    });
	
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(index){
		var row = activityGoodsDataGrid.datagrid('getRows')[index];
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
		$('#tPointGoodsSearchForm input').val('');
		activityGoodsDataGrid.datagrid('load', {});
	}
	
	/**
	 * 搜索
	 */
	function searchFun() {
		activityGoodsDataGrid.datagrid('load', $.serializeObject($('#tPointGoodsSearchForm')));
	}
	
	/**
	 * 确认指派商品
	 */
	function appointFun(){
		var rows = activityGoodsDataGrid.datagrid('getSelections');
		var goodsIds = '';
		for(var i = 0;i<rows.length;i++){
			var goodsId = rows[i].id;
			goodsIds+=','+goodsId;
		}
		if(goodsIds.length>0){
			goodsIds = goodsIds.substring(1);
		}
		var activityId = '${id}';
		var storeId = '${storeId}';
		$.post('${path }/activity/appoint',{activityId:activityId,storeId:storeId,goodsIds:goodsIds},function(result){
			if (result.success) {
				showMsg(result.msg);
				activityGoodsDataGrid.datagrid('reload');
				activityGoodsDataGrid.datagrid("clearSelections");
			}else{
				showMsg(result.msg);
			}
		},"json"); 
	}
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tPointGoodsSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="name" placeholder="请输入条件进行搜索"/></td>
                   	<th>品牌: </th>	 
                   	<td>
                   		<select name="brandId" id="brandId">
                   			<option value="">请选择</option>
                   			<c:forEach items="${brands }" var="item">
	                    		<option value="${item.id }">${item.name }</option>
	                    	</c:forEach>
                   		</select>
                   	</td>
                   	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanFun();">清空</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-hand-right icon-blue',plain:true" onclick="appointFun();">确认指派</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="activityGoodsDataGrid" data-options="fit:true,border:false"></table>
    </div>  
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
