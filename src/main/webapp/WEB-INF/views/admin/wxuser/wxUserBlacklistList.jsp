<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tXyUserBlacklistDataGrid;
    $(function() {
    	tXyUserBlacklistDataGrid = $('#tXyUserBlacklistDataGrid').datagrid({
	        url : '${path}/wxUserInfo/blacklistDataGrid',
	        striped : true,
	        rownumbers : true,
	        nowrap:false,
	        pagination : true,
	        singleSelect : true,
	        idField : 'id',
	        sortName : 'id',
	        sortOrder : 'desc',
	        pageSize : 20,
	        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
	        columns : [ [ {
	            width : '60',
	            title : 'ID',
	            align : 'center',
	            field : 'id',
	            hidden:	true
	        },{
	            width : '15%',
	            title : '门店名称',
	            align : 'center',
	            field : 'storeName'
	        },{
	            width : '8%',
	            title : '编号',
	            align : 'center',
	            field : 'code'
	        },{
	        	field:'headImgUrl',
	        	title:'头像',
	        	width:'10%',
	        	align:'center',
	        	formatter:function(value,row,index){
	        		if(value!=null && value!='' && value !='/static/images/headImgUrl.png'){
	        			return '<img style="border-radius:50%" width="50px;" height="50px;" src='+value+'>';
	        		}else{
	        			return '<font color="blue">未授权</font>';
	        		}
				}
	        },{
	            width : '15%',
	            title : '微信昵称',
	            field : 'nickName',
	            align : 'center'
	        },{
	            width : '15%',
	            title : '姓名',
	            field : 'userName',
	            align : 'center'
	        },{
	            width : '15%',
	            title : '手机号码',
	            field : 'mobileNo',
	            align : 'center'
	        },{
	            width : '15%',
	            title : '加入日期',
	            field : 'createdTime',
	            align : 'center'
	        },{
	            field : 'action',
	            title : '操作',
	            width : '10%',
	            align : 'center',
	            formatter : function(value, row, index) {
	                var str = '';
	                str += $.formatString('<a href="javascript:void(0)" class="userBlacklist-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="userBlacklistDeleteFun(\'{0}\');" >移除</a>', row.openId);
	                return str;
	            }
	        } ] ],
	        onLoadSuccess:function(data){
	            $('.userBlacklist-easyui-linkbutton-del').linkbutton({text:'移除'});
	        }
	    });
	});
    
   
	/**
	 * 删除
	 */
	function userBlacklistDeleteFun(openId) {
		parent.$.messager.confirm('温馨提示', '您是否确定要移除吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/wxUserInfo/deleteBlacklist', {
					openId : openId
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						tXyUserBlacklistDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	
	/**
	 * 清除
	 */
	function cleanUserBlacklistFun() {
		$('#tXyUserBlacklistSearchForm input').val('');
		tXyUserBlacklistDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function userBlacklistSearchFun() {
		tXyUserBlacklistDataGrid.datagrid('load', $.serializeObject($('#tXyUserBlacklistSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tXyUserBlacklistSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="nickName" id="nickName" placeholder="条件搜索"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="userBlacklistSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanUserBlacklistFun();">清空</a>&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="tXyUserBlacklistDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>