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
var bannerImg = [];
//重写数组删除方法
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

//删除门店图
function delBannerImg(f,imgUrl){
	bannerImg.remove(imgUrl);
	$(f).parent().remove();
}

    $(function() {
    	//轮播图
    	$("#bannerImg")
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
    						bannerImg.push(result);
    						var seqIds = result.split("/");
    	                	var seqId = seqIds[seqIds.length-1].split(".")[0];
    						var imgUrl = '"'+result+'"';
    						imgHtml = "<li style='overflow: hidden;'><a href='"+result+"' target='_blank' style='float: left;'><img src='"+result+"' width='120' height='120'/></a>";
    						imgHtml += "<input type='number' id='"+seqId+"' style='width: 40px;float: left;' value='0' /></br></br>";
    						imgHtml += "<input type='button' style='float: left;' onclick='delBannerImg(this,"+imgUrl+")' value='删除' />";
    						imgHtml += "<li>";
    						$("#bannerImgDesc").append(imgHtml);
    					}
    				}
    			});
    	
        $('#bannerAddForm').form({
            url : '${path }/banner/add',
            onSubmit : function() {
                progressLoad();
                
                var filePath = bannerImg.join(",");
                var fileSeq = [];
                for(var i=0;i<bannerImg.length;i++){
                	var seqIds = bannerImg[i].split("/");
                	var seqId = seqIds[seqIds.length-1].split(".")[0];
                	fileSeq[i] = $("#"+seqId).val();
                }
                $("#fileSeq").val(fileSeq.join(","));
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
                    bannerDataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                    showMsg(result.msg);
                } else {
                    var form = $('#bannerAddForm');
                    showMsg(result.msg);
                }
            }
        });
    });
  
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scroll;padding: 3px;" >
        <form id="bannerAddForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="filePath" id="filePath" />
        	<input type="hidden" name="fileSeq" id="fileSeq" />
            <table class="grid">
                 <tr>
                    <td align="right" bgcolor="#fafafa">图片:</td>
                    <td colspan="3" >
                    	<input type="file" name="file_upload" id="bannerImg" />
                    	<div id="bannerImgDesc"></div> 
					</td>
                </tr>          	
                <tr>
                    <td align="right" bgcolor="#f8f8f8">标题</td>
                    <td><input name="title" type="text" placeholder="请输入标题" class="easyui-validatebox span2" data-options="required:true" value=""></td>
               		<td align="right" bgcolor="#f8f8f8">类型</td>
                    <td>
                    	<input name="type" value="0" type="radio" checked="checked">首页
                    	<input name="type" value="1" type="radio" >优惠券
                    	<input name="type" value="2" type="radio" >活动专区
                    	<input name="type" value="3" type="radio" >积分商城
                    </td>
                </tr>
                <tr>
					<td align="right" bgcolor="#f8f8f8">图片宽度</td>
                    <td><input name="width" type="text" placeholder="请输入图片宽度" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                	<td align="right" bgcolor="#f8f8f8">图片高度</td>
                    <td><input name="height" type="text" placeholder="请输入图片高度" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">跳转地址</td>
                    <td><input name="url" type="text" placeholder="请输入跳转地址" class="easyui-validatebox span2" value=""></td>
                </tr>
            </table>
        </form>
    </div>
</div>