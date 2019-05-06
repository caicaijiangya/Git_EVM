<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var messageDataGrid;
    $(function () {
    	messageDataGrid = $('#messageDataGrid').datagrid({
            url: '${path }/message/dataGrid',
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
                title: '门店名称',
                align: 'center',
                field: 'storeName'
            },{
                width: '10%',
                title: '发布人',
                align: 'center',
                field: 'nickName'
            },{
                width: '10%',
                title: '订单编号',
                align: 'center',
                field: 'orderNo'
            },{
                width: '20%',
                title: '消息标题',
                align: 'center',
                field: 'title'
            },{
                width: '20%',
                title: '消息内容',
                align : 'center',
                field: 'contents'
            },{
                width: '10%',
                title: '消息类型',
                align: 'center',
                field: 'type',
                formatter:function(value,row,index){
                	if(value==0){
                		return "后台发送";
                	}else{
                		return "小程序端发送";
                	}         	
    			}
            },{
                width: '5%',
                title: '浏览量',
                align : 'center',
                field: 'nums'
            },{
                width: '15%',
                title: '消息发送时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '15%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/message/detail">
	                    str += $.formatString('<a href="javascript:void(0)" class="message-easyui-linkbutton-detail" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="messageDetailFun(\'{0}\');" >通知详情</a>', row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/message/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="message-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="messageDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.message-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.message-easyui-linkbutton-detail').linkbutton({text:'通知详情'});
            },
            toolbar : '#messageToolbar'
        });
    });
    
  //查看消息通知详情
    function messageDetailFun(id) {
        parent.$.modalDialog({
            title : '查看消息通知详情',
            width : '60%',
            height : '60%',
            href : '${path }/message/messageDetail?id='+id,
        });
    }
    
    /**
	 * 删除
	 */
	function messageDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = messageDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			messageDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/message/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						messageDataGrid.datagrid('reload');
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
		$('#tMessageSearchForm input').val('');
		messageDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function storeSearchFun() {
		messageDataGrid.datagrid('load', $.serializeObject($('#tMessageSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tMessageSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="storeName" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th> 消息类型：</th>
                    <td>
                   		<select name="type" id="type">
                   			<option value="">请选择</option>
                   			<option value="0">后台发送</option>
                   			<option value="1">小程序端发送</option>
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
        <table id="messageDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

