<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var modelUserDataGrid;
    $(function () {
    	modelUserDataGrid = $('#modelUserDataGrid').datagrid({
            url: '${path }/wxUserInfo/dataGridDel?openId=${openId}',
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
                title: '模块名称',
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
		$('#stModelUserForm input').val('');
		modelUserDataGrid.datagrid('load', {});
	}
	
	/**
	 * 搜索
	 */
	function searchFun() {
		modelUserDataGrid.datagrid('load', $.serializeObject($('#stModelUserForm')));
	}
	
	/**
	 * 批量取消指派
	 */
	 function batchCleanFun(){
		 var rows =modelUserDataGrid.datagrid('getSelections');
			parent.$.messager.confirm('温馨提示', '您是否要取消指派？', function(b) {
			if (b) {
				var ids = "";
				for(var i=0;i<rows.length;i++){
					ids+="," + rows[i].id;
				}
				if(ids.length>0){
					ids=ids.substring(1);
				}
				progressLoad();
				$.post('${path }/wxUserInfo/deleteList', {
					ids : ids
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						modelUserDataGrid.datagrid('reload');
						modelUserDataGrid.datagrid("clearSelections");
					}else{
						showMsg(result.msg);
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
</script>


<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="stModelUserForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="modelName" placeholder="条件搜索"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanFun();">清空</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-trash icon-red',plain:true" onclick="batchCleanFun();">取消权限</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="modelUserDataGrid" data-options="fit:true,border:false"></table>
    </div>  
</div>
