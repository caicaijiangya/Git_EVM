<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tXyUserIntegralDataGrid;
    $(function() {
        tXyUserIntegralDataGrid = $('#tXyUserIntegralDataGrid').datagrid({
        url : '${path}/wxUserIntegral/dataGrid',
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
            width : '10%',
            title : '昵称',
            field : 'nickName',
            align : 'center'
        },{
            width : '10%',
            title : '姓名',
            field : 'userName',
            align : 'center'
        },{
            width : '10%',
            title : '会员卡号',
            field : '1',
            align : 'center'
        },{
            width : '5%',
            title : '会员等级',
            field : '2',
            align : 'center'
        },{
            width : '5%',
            title : '状态',
            field : 'isIntegral',
            align : 'center',
            formatter:function(value,row,index){
            	if(value == 0){
            		return '<font color="blue">正常</font>';
            	}else{
            		return '<font color="red">禁用</font>';
            	}
			}
        },{
            width : '10%',
            title : '总积分',
            field : 'totalIntegral',
            align : 'center'
        },{
            width : '10%',
            title : '总消费积分',
            field : 'conIntegral',
            align : 'center'
        },{
            width : '10%',
            title : '有效积分',
            field : 'integral',
            align : 'center'
        },{
            width : '10%',
            title : '获得积分',
            field : 'currentIntegral',
            align : 'center'
        },{
            field : 'action',
            title : '操作',
            width : '25%',
            align : 'center',
            formatter : function(value, row, index) {
                var str = '';
                var userType = row.userType;
                if(row.isIntegral == 0){
                	str += $.formatString('<a href="javascript:void(0)" class="userIntegral-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="integralIsDisableFun(\'{0}\',\'{1}\');" >禁用</a>', row.id,1);
                }else{
                	str += $.formatString('<a href="javascript:void(0)" class="userIntegral-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="integralIsDisableFun(\'{0}\',\'{1}\');" >启用</a>', row.id,0);
                }
                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                str += $.formatString('<a href="javascript:void(0)" class="userIntegral-easyui-linkbutton-giving" data-options="plain:true,iconCls:\'glyphicon-hand-right icon-blue\'" onclick="addIntegral(\'{0}\',\'{1}\');" >赠送积分</a>', row.openId,row.integral);
	            str += '<br/>';
	            str += $.formatString('<a href="javascript:void(0)" class="userIntegral-easyui-linkbutton-detail" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="queryIntegralDetail(\'{0}\');" >查看积分明细</a>',row.openId);
                str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                str += $.formatString('<a href="javascript:void(0)" class="userIntegral-easyui-linkbutton-detailDown" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="downLoadIntegralDetail(\'{0}\');" >积分明细导出</a>',row.openId);
            	
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
            $('.userIntegral-easyui-linkbutton-del').linkbutton({text:'禁用'});
            $('.userIntegral-easyui-linkbutton-edit').linkbutton({text:'启用'});
            $('.userIntegral-easyui-linkbutton-giving').linkbutton({text:'赠送积分'});
            $('.userIntegral-easyui-linkbutton-detail').linkbutton({text:'查看积分明细'});
            $('.userIntegral-easyui-linkbutton-detailDown').linkbutton({text:'积分明细导出'});
        },
        toolbar : '#userIntegralToolbar'
    });
});
    /**
	 * 查看积分明细
	 */
     function queryIntegralDetail(openId){
    	parent.$.modalDialog({
    		title:"查看积分明细",
    		width:"50%",
    		height:"70%",
    		href:"${path}/wxUserIntegral/detailManager?openId="+openId
    	});
    }
    /**
	 * 禁用、启用
	 */
	function integralIsDisableFun(id,status) {
    	var title = "";
    	if(status == 0){
    		title = "启用";
    	}else{
    		title = "禁用";
    	}
		parent.$.messager.confirm('温馨提示', '您是否确定要'+title+'？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/wxUserIntegral/disable', {
					id : id,
					isIntegral:status
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						tXyUserIntegralDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
    
	//增加积分
    function addIntegral(openId,integral) {
        parent.$.modalDialog({
            title : '增加积分',
            width : '40%',
            height : '40%',
            href : '${path }/wxUserIntegral/addIntegralPage?openId='+openId+'&integral='+integral,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = tXyUserIntegralDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userIntegralAddForm');
                    f.submit();
                }
            } ]
        });
    }
	
	/**
	 * 清除
	 */
	function cleanUserIntegralFun() {
		$('#tXyUserIntegralSearchForm input').val('');
		tXyUserIntegralDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function userIntegralSearchFun() {
		tXyUserIntegralDataGrid.datagrid('load', $.serializeObject($('#tXyUserIntegralSearchForm')));
	}
	
	
	/**
	 * 导出积分
	 */
	function downLoadIntegral(){
		 var param ='?nickName=' + $("#nickName").val();
		 param += '&userType=' + $("#userType").val();
		 param += '&startTime=' + $("#startTime").val();
		 param += '&endTime=' + $("#endTime").val();
		window.location.href = '${path }/wxUserIntegral/downLoadIntegral'+param;
	}
	/**
	 * 导出积分明细
	 */
	function downLoadIntegralDetail(openId){
		 var param ='?openId=' + openId;
		window.location.href = '${path }/wxUserIntegral/downLoadIntegralDetail'+param;
	}
	/**
	 * 导出积分明细
	 */
	function downLoadIntegralDetailAll(openId){
		var param ='?startTime=' + $("#startTime").val();
		 param += '&endTime=' + $("#endTime").val();
		window.location.href = '${path }/wxUserIntegral/downLoadIntegralDetail'+param;
	}
	/**
	*积分批量清理
	*/
	function integralImportFun(){
		$("#integralExcel").click();
	}
	$("#integralExcel").change(function(){
		$('#tXyUserIntegralSearchForm').submit();
	});
	$('#tXyUserIntegralSearchForm').submit(function() { 
		progressLoad();
		$(this).ajaxSubmit({
			type: 'post',
			url:'${path }/excel/integralClear',
			success:function(result){
				result = JSON.parse(result);
				showMsg(result.msg);
				tXyUserIntegralDataGrid.datagrid('reload');
				progressClose();
			}
		}); 
		return false; //阻止表单默认提交 
	});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tXyUserIntegralSearchForm">
        <input type="file" name="integralExcel" id="integralExcel" style="display: none;"/>
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
                   	
                	<th>开始日期：</th>
                    <td>
                    	 <input type="text" class="easyui-validatebox"  id="startTime"
					 		name="startTime" placeholder="选择开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){endTime.focus();}})" readonly="readonly"/>
					</td>	     
					<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>              	
                	<th>结束日期：</th>	
                    <td>
				 		<input type="text" class="easyui-validatebox"  id="endTime"
				 		name="endTime" placeholder="选择结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'startTime\')}'})" readonly="readonly"/>
                    </td>
                    <td>
                         <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="userIntegralSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanUserIntegralFun();">清空</a>&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="tXyUserIntegralDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="userIntegralToolbar" style="display: none;">
    	<a onclick="downLoadIntegral();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出积分</a>
    	<a onclick="downLoadIntegralDetailAll();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-download icon-green'">导出积分明细</a>
    	<a onclick="integralImportFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-upload icon-green'">批量清理积分</a>
    </div>
</div>