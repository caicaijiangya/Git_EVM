<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<style type="text/css">
  li{
    list-style:none;
    float:left;
    margin-left:10px;
  }
</style>
<script type="text/javascript">
$(function() {
	//退货规则
	$("#image0")
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
						$("#dictValue0").val(result);
						var imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
						$("#image0Desc").html(imgHtml);
					}
				}
			});
	//积分规则
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
						$("#dictValue1").val(result);
						var imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
						$("#image1Desc").html(imgHtml);
					}
				}
			});
	
	//首页活动专区图片
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
						$("#dictValue2").val(result);
						var imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
						$("#image2Desc").html(imgHtml);
					}
				}
			});
	
	//砍价活动规则
	$("#image3")
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
						$("#dictValue3").val(result);
						var imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
						$("#image3Desc").html(imgHtml);
					}
				}
			});
	
	$('#rulesForm0,#rulesForm1,#rulesForm2,#rulesForm3').form({
        url : '${path}/dict/edit',
        onSubmit : function() {
            var isValid = $(this).form('validate');
            if (!isValid) {
            	
            }
            return isValid;
        },success : function(result) {
        	result = JSON.parse(result);
        	showMsg(result.msg);
        }
    });
   
});

</script>
<div class="easyui-layout" data-options="fit:true,border:false" style="margin: 20px;overflow-y:scroll;">
	<div class="easyui-panel" title="退货规则" style="width:600px;">
		<div style="padding:10px 60px 20px 60px">
		    <form id="rulesForm0" method="post">
		    	<input type="hidden" name="id" value="${dict0.id }" />
		    	<input type="hidden" name="dictCode" value="${dict0.dictCode }" />
		    	<input type="hidden" name="oldCode" value="${dict0.dictCode }" />
		    	<input type="hidden" name="dictValue" id="dictValue0" value="${dict0.dictValue }"/>
		    	<table class="grid">
		    		<tr>
	                    <td align="right" bgcolor="#fafafa">规则图片:</td>
	                    <td >
	                    	<input type="file" name="file_upload" id="image0" />
	                    	<div id="image0Desc">
	                    		<a href='${dict0.dictValue }' target='_blank'><img src='${dict0.dictValue }' width='120' height='120'/></a>
	                    	</div> 
						</td>
	                </tr>
		    	</table>
		    	
		    	<div style="text-align:center;margin-top: 20px;">
		    		<input type="submit" class="easyui-linkbutton" style="width: 100px;height: 30px;" value="提交"/>
			    </div>
		    </form>
		    
	    </div>
	</div>
	<div style="width: 100%;height: 20px;"> </div>
	<div class="easyui-panel" title="积分规则" style="width:600px;">
		<div style="padding:10px 60px 20px 60px">
		    <form id="rulesForm1" method="post">
		    	<input type="hidden" name="id" value="${dict1.id }" />
		    	<input type="hidden" name="dictCode" value="${dict1.dictCode }" />
		    	<input type="hidden" name="oldCode" value="${dict1.dictCode }" />
		    	<input type="hidden" name="dictValue" id="dictValue1" value="${dict1.dictValue }"/>
		    	<table class="grid">
		    		<tr>
	                    <td align="right" bgcolor="#fafafa">规则图片:</td>
	                    <td >
	                    	<input type="file" name="file_upload" id="image1" />
	                    	<div id="image1Desc">
	                    		<a href='${dict1.dictValue }' target='_blank'><img src='${dict1.dictValue }' width='120' height='120'/></a>
	                    	</div> 
						</td>
	                </tr>
		    	</table>
		    	
		    	<div style="text-align:center;margin-top: 20px;">
		    		<input type="submit" class="easyui-linkbutton" style="width: 100px;height: 30px;" value="提交"/>
			    </div>
		    </form>
		    
	    </div>
	</div>
	<div style="width: 100%;height: 20px;"> </div>
	<div class="easyui-panel" title="砍价活动规则" style="width:600px;">
		<div style="padding:10px 60px 20px 60px">
		    <form id="rulesForm3" method="post">
		    	<input type="hidden" name="id" value="${dict3.id }" />
		    	<input type="hidden" name="dictCode" value="${dict3.dictCode }" />
		    	<input type="hidden" name="oldCode" value="${dict3.dictCode }" />
		    	<input type="hidden" name="dictValue" id="dictValue3" value="${dict3.dictValue }"/>
		    	<table class="grid">
		    		<tr>
	                    <td align="right" bgcolor="#fafafa">图片:</td>
	                    <td >
	                    	<input type="file" name="file_upload" id="image3" />
	                    	<div id="image3Desc">
	                    		<a href='${dict3.dictValue }' target='_blank'><img src='${dict3.dictValue }' width='120' height='120'/></a>
	                    	</div> 
						</td>
	                </tr>
		    	</table>
		    	
		    	<div style="text-align:center;margin-top: 20px;">
		    		<input type="submit" class="easyui-linkbutton" style="width: 100px;height: 30px;" value="提交"/>
			    </div>
		    </form>
		    
	    </div>
	</div>
	<div style="width: 100%;height: 50px;"> </div>
	<div class="easyui-panel" title="首页活动专区图片" style="width:600px;">
		<div style="padding:10px 60px 20px 60px">
		    <form id="rulesForm2" method="post">
		    	<input type="hidden" name="id" value="${dict2.id }" />
		    	<input type="hidden" name="dictCode" value="${dict2.dictCode }" />
		    	<input type="hidden" name="oldCode" value="${dict2.dictCode }" />
		    	<input type="hidden" name="dictValue" id="dictValue2" value="${dict2.dictValue }"/>
		    	<table class="grid">
		    		<tr>
	                    <td align="right" bgcolor="#fafafa">图片:</td>
	                    <td >
	                    	<input type="file" name="file_upload" id="image2" />
	                    	<div id="image2Desc">
	                    		<a href='${dict2.dictValue }' target='_blank'><img src='${dict2.dictValue }' width='120' height='120'/></a>
	                    	</div> 
						</td>
	                </tr>
		    	</table>
		    	
		    	<div style="text-align:center;margin-top: 20px;">
		    		<input type="submit" class="easyui-linkbutton" style="width: 100px;height: 30px;" value="提交"/>
			    </div>
		    </form>
		    
	    </div>
	</div>
	<div style="width: 100%;height: 50px;"> </div>
</div>
