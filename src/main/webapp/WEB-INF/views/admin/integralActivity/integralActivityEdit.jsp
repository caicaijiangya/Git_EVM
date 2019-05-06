<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#integralActivityEditForm').form({
            url : '${path }/integralActivity/edit',
            onSubmit : function() {
                progressLoad();
                var storeId = $("#storeId").combogrid("getValues");
                $("input[name='activityStoreIds']").val(storeId);
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                	integralActivityDataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                    showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
        
        $("#storeId").combogrid({
        	required:true,
    		multiple:true,
    		multiline:true,
    		idField: 'id',
    		textField:	'storeName',
    		url: 'goodsOther/storeGrid',
    		method: 'post',
    		nowrap: false,
			columns: [[
				{field:'ck',checkbox:true},
				{field:'id',title:'ID',width:80,hidden:true},
				{field:'storeName',title:'全选',width:120}
			]],
			fitColumns: true,
			onLoadSuccess: function () {
				var storeIds = '${activity.activityStoreIds }'.split(',');
				$('#storeId').combogrid('setValue', storeIds);
			}
    	});
    });
    
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="integralActivityEditForm" method="post" >
        	<input type="hidden" name="id" value="${activity.id }"/>
        	<input type="hidden" name="activityStoreIds" value=""/>
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">标题</td>
                    <td><input name="title" type="text" placeholder="请输入标题" class="easyui-validatebox span2" data-options="required:true" value="${activity.title }"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">门店</td>
                    <td>
                    	<select class="easyui-combogrid" id="storeId" style="width:300px;height: 80px;"></select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">类型</td>
                    <td>
                    	<input name="type" value="1" type="radio" ${activity.type==1?'checked':'' }>多倍积分
                    	<input name="type" value="2" type="radio" ${activity.type==2?'checked':'' }>邀请好友获得积分
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">开始时间</td>
                    <td><input id="startTime" type="text" name="startTime" value="${activity.startTime }"  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTime\')}'})"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">结束时间</td>
                    <td><input id="endTime" type="text" name="endTime" value="${activity.endTime }" required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}'})"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">积分倍数</td>
                    <td><input name="multiple" type="number" class="easyui-validatebox span2" data-options="required:true" value="${activity.multiple }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">赠送积分</td>
                    <td><input name="integral" type="number" class="easyui-validatebox span2" data-options="required:true" value="${activity.integral }"></td>
                </tr>
            </table>
        </form>
    </div>
</div>