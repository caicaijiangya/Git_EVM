<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tXyUserDataGrid;
    $(function() {
        tXyUserDataGrid = $('#tXyUserDataGrid').datagrid({
        url : '${path}/wxUserInfo/dataGrid',
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
            width : '15%',
            title : 'OPENID',
            align : 'center',
            field : 'openId'
        },{
        	field:'headImgUrl',
        	title:'头像',
        	width:'8%',
        	align:'center',
        	formatter:function(value,row,index){
        		if(value!=null && value!='' && value !='/static/images/headImgUrl.png'){
        			return '<img style="border-radius:50%" width="50px;" height="50px;" src='+value+'>';
        		}else{
        			return '<font color="blue">未授权</font>';
        		}
			}},{
            width : '10%',
            title : '微信昵称',
            field : 'nickName',
            align : 'center'
        },{
            width : '10%',
            title : '姓名',
            field : 'userName',
            align : 'center'
        },{
            width : '10%',
            title : '手机号码',
            field : 'mobileNo',
            align : 'center',
            formatter:function(value,row,index){
            	return value;
			}
        },{
            width : '5%',
            title : '性别',
            field : 'sex',
            align : 'center',
            formatter:function(value,row,index){
            	switch (value) {
                case 0:
                    return '保密';
                    break;
                case 1:
                    return '男';
                    break;
                case 2:
                    return '女';
                    break;
                default:
                    return '无';
                	break;
                }
			}
        },{
            width : '10%',
            title : '用户类型',
            field : 'userType',
            align : 'center',
            formatter : function(value, row, index) {
                switch (value) {
                case 1:
                    return '店主';
                    break;
                case 2:
                    return '店长';
                    break;
                case 3:
                    return '店员';
                    break;
                case 4:
                    return '消费者';
                    break;    
                default:
                    return '未认证';
                	break;
                }
            }
        },
        {
            width : '15%',
            title : '区域位置',
            field : 'country',
            align : 'center'
        }, {
            width : '15%',
            title : '加入日期',
            field : 'createdTime',
            align : 'center'
        },{
            field : 'action',
            title : '操作',
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
                var str = '';
                var userType = row.userType;
                str += $.formatString('<a href="javascript:void(0)" class="userInfo-easyui-linkbutton-blacklist" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="addBlacklistFun(\'{0}\');" >加入黑名单</a>', row.openId);
                str += '<br/>';
                if(userType==1){
                	<shiro:hasPermission name="/userInfo/addAuth">
	                	str += $.formatString('<a href="javascript:void(0)" class="userInfo-easyui-linkbutton-auth" data-options="plain:true,iconCls:\'glyphicon-hand-right icon-blue\'" onclick="addAuth(\'{0}\',\'{1}\');" >赋予权限</a>', row.openId,row.id);
	                	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                	</shiro:hasPermission>
                	<shiro:hasPermission name="/userInfo/queryAuth">
	                	str += $.formatString('<a href="javascript:void(0)" class="userInfo-easyui-linkbutton-query" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="queryAuth(\'{0}\',\'{1}\');" >查看权限</a>', row.openId,row.id);
                	</shiro:hasPermission>
                	str += '<br/>';
                }
                <shiro:hasPermission name="/userInfo/edit">
		            str += $.formatString('<a href="javascript:void(0)" class="userInfo-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="userInfoDeleteFun(\'{0}\');" >删除</a>', row.id);
		            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
	            </shiro:hasPermission>
	            <shiro:hasPermission name="/userInfo/delete">
	            	str += $.formatString('<a href="javascript:void(0)" class="userInfo-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="userInfoEditFun(\'{0}\');" >修改</a>', row.id);
	            </shiro:hasPermission>
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.userInfo-easyui-linkbutton-blacklist').linkbutton({text:'加入黑名单'});
            $('.userInfo-easyui-linkbutton-del').linkbutton({text:'删除'});
            $('.userInfo-easyui-linkbutton-edit').linkbutton({text:'修改'});
            $('.userInfo-easyui-linkbutton-auth').linkbutton({text:'赋予权限'});
            $('.userInfo-easyui-linkbutton-query').linkbutton({text:'查看权限'});
        },
        toolbar : '#userInfoToolbar'
    });
});
    
    /**
	 * 赋予权限
	 */
     function addAuth(openId,id){
    	parent.$.modalDialog({
    		title:"权限赋予",
    		width:"40%",
    		height:"40%",
    		href:"${path}/wxUserInfo/addAuth?id="+id+'&openId='+openId
    	});
    }
    
     /**
 	 * 查看权限
 	 */
      function queryAuth(openId,id){
     	parent.$.modalDialog({
     		title:"查看权限",
     		width:"40%",
     		height:"40%",
     		href:"${path}/wxUserInfo/queryAuth?id="+id+'&openId='+openId
     	});
     }
     /**
     * 加入黑名单
     */
	function addBlacklistFun(openId){
		parent.$.messager.confirm('温馨提示', '您是否确定要加入黑名单？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/wxUserInfo/addBlacklist', {
					openId : openId
				}, function(result) {
					showMsg(result.msg);
					if (result.success) {
						tXyUserDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	/**
	 * 删除
	 */
	function userInfoDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = tXyUserDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			tXyUserDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/wxUserInfo/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						tXyUserDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	//修改字段内容
    function userInfoEditFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '40%',
            href : '${path }/wxUserInfo/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = tXyUserDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userInfoEditForm');
                    f.submit();
                }
            } ]
        });
    }
	
	/**
	 * 清除
	 */
	function cleanUserFun() {
		$('#tXyUserSearchForm input').val('');
		tXyUserDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function userInfoSearchFun() {
		tXyUserDataGrid.datagrid('load', $.serializeObject($('#tXyUserSearchForm')));
	}
	/**
	 * 用户下载
	 */
	function userInfoDownFun(){
		 var param ='?nickName='+encodeURIComponent(encodeURIComponent($("#nickName").val()))
		 +'&startTime='+$("#startTime").val()
		 +'&endTime='+$("#endTime").val()
		 +'&userType='+$("#userType").val();
		window.location.href = '${path }/wxUserInfo/downLoadUserInfo'+param;
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tXyUserSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="nickName" id="nickName" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;用户类型：</th>
                    <td>
						<select name="userType" id="userType" style="width: 120px; height: 22px;" class="easyui-validatebox" >
							<option value="">全部</option>
							<option value="1">店主</option>
							<option value="3">店员</option>
							<option value="4">消费者</option>
						</select>
					</td>
					<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 开始日期: </th>	 
                   	<td>
                   		<input type="text" name="startTime" id="startTime"  value=""  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                	<th> 结束日期: </th>	
                   	<td>
                   		<input type="text" name="endTime" id="endTime" value=""  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})">
                   	</td>
                    <td>
                         <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="userInfoSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanUserFun();">清空</a>&nbsp;&nbsp;
                        <shiro:hasPermission name="/wxUserInfo/userInfoDown">
                        	<a onclick="userInfoDownFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出用户</a>
                    	</shiro:hasPermission>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="tXyUserDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>