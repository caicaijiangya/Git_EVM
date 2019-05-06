<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<style type="text/css">
  li{
    list-style:none;
    float:left;
    margin-left:10px;
  }
</style>
<script type="text/javascript">

var image1 = '';
var image2 = '';
//删除图片
function delImg(f,key){
	if(key == 1){
		image1 = '';
	}else if(key == 2){
		image2 = '';
	}
	$(f).parent().remove();
}
$(function() {
	//图片1
	$("#image1")
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
						image1 = result;
						var imgUrl = '"'+result+'"';
						imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this,1)' value='删除' />";
						$("#image1Desc").append(imgHtml);
					}
				}
			});
	//图片2
	$("#image2")
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
						image2 = result;
						imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this,2)' value='删除' />";
						$("#image2Desc").html(imgHtml);
					}
				}
			});
	$('#popConfigAddForm').form({
        url : '${path }/popConfig/add',
        onSubmit : function() {
            progressLoad();
            
		    $("#image1Img").val(image1);
		    $("#image2Img").val(image2);
		    
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
            	popConfigDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
	
	
});
function selectCoupon(key){
	if(key == 0){
		$("#coupon").hide();
	}else if(key = 1){
		$("#coupon").show();
	}
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="popConfigAddForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="image1" id="image1Img" />
        	<input type="hidden" name="image2" id="image2Img">
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#fafafa">展示图片一:</td>
                    <td >
                    	<input type="file" name="file_upload" id="image1" />
                    	<div id="image1Desc"></div> 
					</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#fafafa">展示图片二:</td>
                    <td >
                        <input type="file" name="file_upload" id="image2" />
                        <div id="image2Desc"></div>  
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">标题</td>
                    <td><input name="title" type="text" placeholder="请输入标题" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">类型</td>
                    <td>
                    	<input name="type" value="0" onclick="selectCoupon(0)" type="radio" checked="checked">普通
                    	<input name="type" value="1" type="radio" onclick="selectCoupon(1)">优惠券领取
                    </td>
                </tr>
                <tr id="coupon" style="display: none;">
                	<td align="right" bgcolor="#f8f8f8">优惠券</td>
                    <td>
	                    <select id="couponId" name="couponId" style="width: 200px; height: 29px;" class="easyui-combobox">
	                    	<c:forEach items="${couponList }" var="item">
	                    		<option value="${item.id }">${item.goodsName }</option>
	                    	</c:forEach>
	                    	
	                    </select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">链接地址</td>
                    <td><input name="url" type="text" style="width: 250px; height: 20px;" placeholder="请输入链接地址" class="easyui-validatebox span2" ></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">自动关闭时间（ 秒）</td>
                    <td>
                    	<input name="timing" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="0">
                    	<span style="color:red;">（默认0不自动关闭）</span>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">备注</td>
                    <td><input name="note" type="text"  class="easyui-validatebox span2" ></td>
                </tr>
            </table>
        </form>
    </div>
</div>
