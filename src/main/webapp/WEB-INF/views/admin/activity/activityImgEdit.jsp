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
	var image = null;
	//删除轮播图
	function delImg(f){
		image = null;
		$(f).parent().remove();
	}
    $(function() {
    	var result = '${files.filePath}';
    	if(result != null && result.length > 0){
			image = result;
			var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this)' value='删除' /><li>";
			$("#imageDesc").html(imgHtml);
    	}
    	$("#image")
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
						image = result;
						var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this)' value='删除' /><li>";
						$("#imageDesc").html(imgHtml);
					}
				}
			});
    	
    	
        $('#activityImgEditForm').form({
            url : '${path }/activity/editImg',
            onSubmit : function() {
                progressLoad();
                if(image != null)
                $("input[name='image']").val(image);
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
        
    })

</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scroll;padding: 3px;" >
        <form id="activityImgEditForm" method="post">
        	<input name="id" type="hidden" value="${id }"/>
        	<input name="image" type="hidden" value=""/>
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#fafafa">活动图片:</td>
                    <td>
                    	<input type="file" name="file_upload" id="image" />
                    	<div id="imageDesc"></div> 
					</td>
                </tr>
            </table>
        </form>
    </div>
</div>