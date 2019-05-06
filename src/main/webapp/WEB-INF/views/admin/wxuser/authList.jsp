<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var userModelDataGrid;
    $(function () {
    	userModelDataGrid = $('#userModelDataGrid').datagrid({
            url: '${path }/wxUserInfo/dataGridAdd?openId=${openId}',
            striped: true,
            pagination: true,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
            	field:'ck',
            	checkbox:true
            },{
                width: '30%',
                title : '序号',
                align : 'center',
                field : 'id'
            },{
                width: '70%',
                title: '权限模块名称',
                align : 'center',
                field: 'modelName'
            }] ],
 			onLoadSuccess:function(data){
                
            }
        });
    });
	
    /**
	 * 清除
	 */
	function cleanFun() {
		$('#tDelUserModelForm input').val('');
		userModelDataGrid.datagrid('load', {});
	}
	
	/**
	 * 搜索
	 */
	function searchFun() {
		userModelDataGrid.datagrid('load', $.serializeObject($('#tDelUserModelForm')));
	}
	
	/**
	 * 确认赋予权限
	 */
	function appointFun(){
		var rows = userModelDataGrid.datagrid('getSelections');
		var modelIds = '';
		for(var i = 0;i<rows.length;i++){
			var modelId = rows[i].id;
			modelIds+=','+modelId;
		}
		if(modelIds.length>0){
			modelIds = modelIds.substring(1);
		}
		var openId = '${openId}';
		$.post('${path }/wxUserInfo/appoint',{openId:openId,modelIds:modelIds},function(result){
			if (result.success) {
				showMsg(result.msg);
				userModelDataGrid.datagrid('reload');
				userModelDataGrid.datagrid("clearSelections");
			}else{
				showMsg(result.msg);
			}
		},"json"); 
	}
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tDelUserModelForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="modelName" placeholder="条件搜索"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanFun();">清空</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-user icon-blue',plain:true" onclick="appointFun();">确认赋予</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="userModelDataGrid" data-options="fit:true,border:false"></table>
    </div>  
</div>
