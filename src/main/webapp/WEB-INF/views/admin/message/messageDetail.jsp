<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var messageDetailDataGrid;
    $(function () {
    	messageDetailDataGrid = $('#messageDetailDataGrid').datagrid({
            url: '${path }/message/queryMessageDetail?id=${id}',
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
                width: '35%',
                title: '接收人',
                align: 'center',
                field: 'nickName'
            },{
                width: '35%',
                title: '接收者类型',
                align: 'center',
                field: 'userType',
                formatter:function(value,row,index){
                	if(value==1){
                		return "店主";
                	}else if(value==2){
                		return "店长";
                	}else if(value==3){
                		return "店员";
                	}else if(value==4){
                		return "消费者";
                	}
    			}
            },{
                width: '30%',
                title: '是否已读',
                align: 'center',
                field: 'isRead',
                formatter:function(value,row,index){
                	if(value==0){
                		return "未读";
                	}else{
                		return "已读";
                	}
    			}
            } ] ],
            onLoadSuccess:function(data){
            },
        });
    });
    

	/**
	 * 清除
	 */
	function cleanStoreFun() {
		$('#tMessageDetailSearchForm input').val('');
		messageDetailDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function storeSearchFun() {
		messageDetailDataGrid.datagrid('load', $.serializeObject($('#tMessageDetailSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tMessageDetailSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="nickName" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th> 接收者类型：</th>
                    <td>
                   		<select name="userType" id="userType">
                   			<option value="">请选择</option>
                   			<option value="1">店主</option>
                   			<option value="2">店长</option>
                   			<option value="3">店员</option>
                   			<option value="4">消费者</option>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th> 消息是否已读：</th>
                    <td>
                   		<select name="isRead" id="isRead">
                   			<option value="">请选择</option>
                   			<option value="0">未读</option>
                   			<option value="1">已读</option>
                   		</select>
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="storeSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanStoreFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="messageDetailDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

