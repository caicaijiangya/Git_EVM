<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {
    $('#couponActivityEditForm').form({
        url : '${path }/couponActivity/edit',
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
            	couponActivityDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
    
    $("#couponId").combogrid({
		panelWidth: 400,
		panelHeight: 300,
		required:true,
		idField: 'id',
		textField:	'title',
		url: 'coupon/combogridDataGrid',
		method: 'post',
		queryParams:{type:0},
		columns: [[
			{field:'id',title:'id',width:80,hidden:true},
			{field:'title',title:'优惠券标题',width:200,align:'center'},
			{field:'money',title:'优惠金额',width:60,align:'center'},
			{field:'fullMoney',title:'使用门槛',width:60,align:'center'},
			{field:'couponType',title:'优惠券类型',width:70,align:'center',
				formatter : function(value, row, index) {
	           	 if(value == 0){
	            		return '满减';
	            	 }else if(value == 1){
	            		return '打折';
	            	 }
	      		}},
			{field:'type',title:'分类',width:80,align:'center',
				formatter : function(value, row, index) {
		           	 if(value == 0){
		            		return '普通优惠券';
		            	 }else if(value == 1){
		            		return '活动优惠券';
		            	 }
		      		}}
		]],
		fitColumns: true,
		onLoadSuccess: function () {
			var couponId = '${couponActivity.couponId}';
			$('#couponId').combogrid('setValue', couponId);
		}
	});
    
    $('input[type=radio][name=type]').change(function() {
        if (this.value == 2) {
       	 $("#formType").show();
        } else {
       	 $("#formType").hide();
        }
   });
}); 
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="couponActivityEditForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name='id' value='${couponActivity.id }'/>
            <table class="grid">
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">活动标题</td>
                    <td><input name="title" type="text" placeholder="请输入活动标题" class="easyui-validatebox span2" data-options="required:true" value="${couponActivity.title }"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">优惠券</td>
                    <td>
                    	<select name="couponId" id="couponId" class="easyui-combogrid" style="width: 50%; height: 29px;"  data-options="required:true"></select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动类型</td>
                    <td>
                    	<input type="radio" name="type" value="0" ${couponActivity.type==0?'checked':''}/>扫码领券
                    	<input type="radio" name="type" value="1" ${couponActivity.type==1?'checked':''}/>券链接
                    	<input type="radio" name="type" value="2" ${couponActivity.type==2?'checked':''}/>新用户注册
                    </td>
                </tr>
                <tr id="formType" style="${couponActivity.type!=2?'display: none;':''}">
                	<td align="right" bgcolor="#f8f8f8">新用户注册形式</td>
                    <td>
                    	<input type="radio" name="formType" value="0" ${couponActivity.formType==0?'checked':''}/>绑定赠送
                    	<input type="radio" name="formType" value="1" ${couponActivity.formType==1?'checked':''}/>授权赠送
                    	<input type="radio" name="formType" value="2" ${couponActivity.formType==2?'checked':''}/>完善资料赠送
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">每人限领次数</td>
                    <td><input type="number" name="userNum" class="easyui-validatebox span2"  value="${couponActivity.userNum }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">每人每天限领次数</td>
                    <td><input type="number" name="userDayNum" class="easyui-validatebox span2"  value="${couponActivity.userDayNum }"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">是否限量</td>
                    <td>
                    	<input type="radio" name="isCouponNum" value="0" ${couponActivity.isCouponNum==0?'checked':''}/>不限量
                    	<input type="radio" name="isCouponNum" value="1" ${couponActivity.isCouponNum==1?'checked':''}/>限量
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">发放总量</td>
                    <td>${couponActivity.couponNum }</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">增加发放量</td>
                    <td><input type="number" name="couponNum" class="easyui-validatebox span2"  value="0"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">开始时间</td>
                    <td><input type="text" name="startTime" value="${couponActivity.startTime }"  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">结束时间</td>
                    <td><input type="text" name="endTime" value="${couponActivity.endTime }"  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"></td>
                </tr>
            </table>
        </form>
    </div>
</div>