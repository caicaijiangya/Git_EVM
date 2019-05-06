<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#activityEditForm').form({
            url : '${path }/activity/edit',
            onSubmit : function() {
                progressLoad();
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
                	activityDataGrid.datagrid('reload');
                	parent.$.modalDialog.handler.dialog('close');
                	showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
        
        $('input[type=radio][name=activityType]').change(function() {
        	if(this.value == 5 || this.value == 6){
        		$('#isOverlay').show();
        	}else{
        		$('#isOverlay').hide();
        	}
        });
    })

</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scroll;padding: 3px;" >
        <form id="activityEditForm" method="post">
        	<input name="id" type="hidden" value="${activity.id }"/>
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">活动标题</td>
                    <td><input name="title" type="text" placeholder="请输入活动标题" class="easyui-validatebox span2" data-options="required:true" value="${activity.title }"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动类型</td>
                    <td>
                    	<input name="activityType" value="1" type="radio" ${activity.activityType==1?'checked':'' }>秒杀
                    	<input name="activityType" value="2" type="radio" ${activity.activityType==2?'checked':'' }>拼团
                    	<input name="activityType" value="3" type="radio" ${activity.activityType==3?'checked':'' }>特价
                    	<input name="activityType" value="4" type="radio" ${activity.activityType==4?'checked':'' }>砍价
                    	<input name="activityType" value="5" type="radio" ${activity.activityType==5?'checked':'' }>满减
                    	<input name="activityType" value="6" type="radio" ${activity.activityType==6?'checked':'' }>满赠
                    	<input name="activityType" value="7" type="radio" ${activity.activityType==7?'checked':'' }>打折
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动状态</td>
                    <td>
                    	<input name="status" value="0" type="radio" ${activity.status==0?'checked':'' }>待审核
                    	<input name="status" value="1" type="radio" ${activity.status==1?'checked':'' }>已开始
                    	<input name="status" value="2" type="radio" ${activity.status==2?'checked':'' }>已结束
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动开始时间</td>
                    <td><input id="activityStartTime" type="text" name="activityStartTime" value="<fmt:formatDate value="${activity.activityStartTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'activityEndTime\')}'})"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动结束时间</td>
                    <td><input id="activityEndTime" type="text" name="activityEndTime" value="<fmt:formatDate value="${activity.activityEndTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'activityStartTime\')}'})"></td>
                </tr>
                <tr id="isOverlay" style="${activity.activityType==5 || activity.activityType==6?'':'display: none;' }">
                	<td align="right" bgcolor="#f8f8f8">是否叠加</td>
                    <td>
                    	<input name="isOverlay" value="0" type="radio" ${activity.isOverlay==0?'checked':'' }>不叠加
                    	<input name="isOverlay" value="1" type="radio" ${activity.isOverlay==1?'checked':'' }>叠加
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>