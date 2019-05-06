<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<style type="text/css">
li {
	list-style: none;
	float: left;
	margin-left: 10px;
}
</style>
<script type="text/javascript">
	var bannerImg = '${imageArr}';
	var bannerSeq = '${imageSeq}';
	bannerImg = bannerImg.split(",");
	bannerSeq = bannerSeq.split(",");
	showImage(bannerImg,bannerSeq);
	function showImage(bannerImg,bannerSeq){
		var html = '';
		var length = bannerImg.length;
		if(length>0){
			for(var i = 0 ;i<length;i++){
				var result = bannerImg[i];
				var seqIds = result.split("/");
            	var seqId = seqIds[seqIds.length-1].split(".")[0];
				var seq = bannerSeq[i];
				var imgUrl = '"'+result+'"';
				html += "<li style='overflow: hidden;'><a href='"+result+"' target='_blank' style='float: left;'><img src='"+result+"' width='120' height='120'/></a>";
				html += "<input type='number' id='"+seqId+"' style='width: 40px;float: left;' value='"+seq+"' /></br></br>";
				html += "<input type='button' style='float: left;' onclick='delBannerImg(this,"+imgUrl+")' value='删除' />";
				html += "<li>";
			}
			$("#bannerImgDesc").html(html);
		}
	}
	
	//重写数组删除方法
	Array.prototype.remove = function(val) {
		var index = this.indexOf(val);
		if (index > -1) {
			this.splice(index, 1);
		}
	};

	/**
	 * 删除元素
	 */
	function RemoveValByIndex(arr, index) {
		arr.splice(index, 1);
	}

	//插入指定位置元素
	Array.prototype.insert = function (index, item) {
		this.splice(index, 0, item);
	};

	/**
	 * 改变图片位置
	 */
	function changeImagePos(index){
		$.messager.prompt('位置','请输入要调整的位置',function(r){
			if(r){
				if(isNaN(r)){
					alert('请输入数字!');
				}else{
					var length = bannerImg.length;
					if(r<=0 || r>length){
						alert('请输入1~'+length+'之间!');
					}else{
						var imgPath = bannerImg[index];
						RemoveValByIndex(bannerImg,index);
						bannerImg.insert(r-1,imgPath);
						showImage(bannerImg);
					}
				}
			}
		});
	}
	
	//删除门店图
	function delBannerImg(f,index){
		RemoveValByIndex(bannerImg,index);
		$(f).parent().remove();
		showImage(bannerImg);
	}
	
    $(function() {
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
        $('#bannerEditForm').form({
            url : '${path }/banner/edit',
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
                	bannerDataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                    showMsg(result.msg);
                } else {
                    showMsg(result.msg);
                }
            }
        });
        
    });
  
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scroll;padding: 3px;" >
        <form id="bannerEditForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="filePath" id="filePath" />
        	<input type="hidden" name="fileSeq" id="fileSeq" />
        	<input name="id" type="hidden" value="${banner.id}"/>
            <table class="grid">
            	<tr>
					<td align="right" bgcolor="#fafafa">图片:</td>
					<td colspan="3"><input type="file" name="file_upload" id="bannerImg" />
						<div id="bannerImgDesc">
						</div></td>
				</tr>    
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">标题</td>
                    <td><input name="title" type="text" placeholder="请输入标题" class="easyui-validatebox span2" data-options="required:true" value="${banner.title }"></td>
               		<td align="right" bgcolor="#f8f8f8">类型</td>
                    <td>
                    	<input name="type" value="0" type="radio" ${banner.type==0?'checked':''}>首页
                    	<input name="type" value="1" type="radio" ${banner.type==1?'checked':''}>优惠券
                    	<input name="type" value="2" type="radio" ${banner.type==2?'checked':''}>活动专区
                    	<input name="type" value="3" type="radio" ${banner.type==3?'checked':''}>积分商城
                    </td>
                </tr>
                <tr>
					<td align="right" bgcolor="#f8f8f8">图片宽度</td>
                    <td><input name="width" type="text" placeholder="请输入图片宽度" class="easyui-validatebox span2" data-options="required:true" value="${banner.width }"></td>
                	<td align="right" bgcolor="#f8f8f8">图片高度</td>
                    <td><input name="height" type="text" placeholder="请输入图片高度" class="easyui-validatebox span2" data-options="required:true" value="${banner.height }"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">跳转地址</td>
                    <td><input name="url" type="text" placeholder="请输入跳转地址" class="easyui-validatebox span2" value="${banner.url }"></td>
                </tr>
            </table>
        </form>
    </div>
</div>