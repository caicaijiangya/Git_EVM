<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
var filePath = '';
$(function() {
	var result = '${coupon.filePath}';
	if(result != null && result != ''){
		filePath = result;
		imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
		$("#fileImgDesc").html(imgHtml);
	}
	
	//优惠券图
	$("#fileImg")
		.uploadify(
			{
				swf : "${staticPath }/static/uploadify/uploadify.swf",
				uploader : "${path}/fileUpload/upload",
				fileObjName : "uploadFile", // 控制器中参数名称  
				auto : true,
				fileSizeLimit : "5120KB",
				fileTypeExts : "*.jpg;*.gif;*.png;",
				onUploadSuccess : function(file, result, response) {
					if (result) {
						filePath = result;
						imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
						$("#fileImgDesc").html(imgHtml);
					}
				}
			});
	
    $('#couponEditForm').form({
        url : '${path }/coupon/edit',
        onSubmit : function() {
            progressLoad();
            $("#filePath").val(filePath);
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
            	couponDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
    $('input[type=radio][name=dueType]').change(function() {
    	if(this.value == 0){
    		$(".dueTime").show();
    		$(".dueDay").hide();
    	}else{
    		$(".dueTime").hide();
    		$(".dueDay").show();
    	}
    });
}); 
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="couponEditForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name='id' value='${coupon.id }'/>
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#fafafa">优惠券图片:</td>
                    <td>
                    	<input type="hidden" name="filePath" id="filePath" />
                    	<input type="file" name="file_upload" id="fileImg" />
                    	<div id="fileImgDesc"></div> 
					</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">优惠券标题</td>
                    <td><input name="title" type="text" placeholder="请输入优惠券标题" class="easyui-validatebox span2" data-options="required:true" value="${coupon.title }"></td>
               		<td align="right" bgcolor="#f8f8f8">优惠券形式</td>
                    <td>
                    	<input name="couponType" value="0" type="radio" ${coupon.couponType==0?'checked':''}>满减券
                    	<input name="couponType" value="1" type="radio" ${coupon.couponType==1?'checked':''}>打折券
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">优惠金额/折扣(如9折0.9)</td>
                    <td><input name="money" type="number" placeholder="请输入金额" class="easyui-validatebox span2" data-options="required:true" value="${coupon.money }"></td>
                	<td align="right" bgcolor="#f8f8f8">使用条件</td>
                    <td><input name="fullMoney" type="number" placeholder="请输入需要购满的金额" class="easyui-validatebox span2" data-options="required:true" value="${coupon.fullMoney }"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">开始时间</td>
                    <td><input id="dateStart" type="text" name="startTime" value="${coupon.startTime}"  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"></td>
                	<td align="right" bgcolor="#f8f8f8">结束时间</td>
                    <td><input id="dateEnd" type="text" name="endTime" value="${coupon.endTime}"  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">是否隐藏</td>
                    <td>
                    	<input type="radio" name="isShow" value="1" <c:if test="${coupon.isShow== 1}">checked="checked"</c:if>/>是
                    	<input type="radio" name="isShow" value="0" <c:if test="${coupon.isShow== 0}">checked="checked"</c:if>/>否
                    </td>
                    <td align="right" bgcolor="#f8f8f8">是否正价为前提</td>
                    <td>
                    	<input type="radio" name="isOriginal" value="0" <c:if test="${coupon.isOriginal== 0}">checked="checked"</c:if>/>是
                    	<input type="radio" name="isOriginal" value="1" <c:if test="${coupon.isOriginal== 1}">checked="checked"</c:if>/>否
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">优惠券类型</td>
                    <td>
                    	<input type="radio" name="type" value="0" <c:if test="${coupon.type== 0}">checked="checked"</c:if>/>日常券
                    	<input type="radio" name="type" value="1" <c:if test="${coupon.type== 1}">checked="checked"</c:if>/>活动券
                    </td>
                    <td align="right" bgcolor="#f8f8f8">是否与活动共享</td>
                    <td>
                    	<input type="radio" name="isActivityShared" value="0" <c:if test="${coupon.isActivityShared== 0}">checked="checked"</c:if>/>不共享
                    	<input type="radio" name="isActivityShared" value="1" <c:if test="${coupon.isActivityShared== 1}">checked="checked"</c:if>/>共享
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">每人限领次数</td>
                    <td><input type="number" name="userNum" class="easyui-validatebox span2" value="${coupon.userNum }"></td>
                	<td align="right" bgcolor="#f8f8f8">每人每天限领次数</td>
                    <td><input type="number" name="userDayNum" class="easyui-validatebox span2"  value="${coupon.userDayNum }"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">是否限量</td>
                    <td colspan="3">
                    	<input type="radio" name="isCouponNum" value="0" <c:if test="${coupon.isCouponNum== 0}">checked="checked"</c:if>/>不限量
                    	<input type="radio" name="isCouponNum" value="1" <c:if test="${coupon.isCouponNum== 1}">checked="checked"</c:if>/>限量
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">发放总量</td>
                    <td>${coupon.couponNum }</td>
                    <td align="right" bgcolor="#f8f8f8">增加发放量</td>
                    <td><input type="number" name="couponNum" class="easyui-validatebox span2"  value="0"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">优惠券有效期</td>
                    <td>
                    	<input type="radio" name="dueType" value="0" <c:if test="${coupon.dueType== 0}">checked="checked"</c:if>/>固定日期
                    	<input type="radio" name="dueType" value="1" <c:if test="${coupon.dueType== 1}">checked="checked"</c:if>/>固定天数
                    </td>
                	<td class="dueTime" style="${coupon.dueType== 1?'display: none;':''}" align="right" bgcolor="#f8f8f8">固定日期</td>
                    <td class="dueTime" style="${coupon.dueType== 1?'display: none;':''}"><input type="text" name="dueTime0" class="easyui-validatebox span2" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${coupon.dueType== 0?coupon.dueTime:'' }"></td>
                    <td class="dueDay" style="${coupon.dueType== 0?'display: none;':''}" align="right" bgcolor="#f8f8f8">固定天数</td>
                    <td class="dueDay" style="${coupon.dueType== 0?'display: none;':''}"><input type="number" name="dueTime1" class="easyui-validatebox span2"  value="${coupon.dueType== 1?coupon.dueTime:''}"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">使用说明</td>
                    <td colspan="3"><textarea name="couponDesc" rows="4" style="width:95%;">${coupon.couponDesc }</textarea></td>
                </tr>
            </table>
        </form>
    </div>
</div>