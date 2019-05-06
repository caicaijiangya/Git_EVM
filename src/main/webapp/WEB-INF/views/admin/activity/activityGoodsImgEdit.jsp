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
	var suoImg = '${imageArr}';	//缩略图
		suoImg = suoImg.split(",");
	var lunboImg = '${advertImgArr}';	//轮播图
		lunboImg = lunboImg.split(",");		
	var xqImg = '${detailImgArr}';	//详情图
		xqImg = xqImg.split(",");
	var listImg = '${listImgArr}';//列表图
	showAdvertImg(lunboImg);
	function showAdvertImg(lunboImg){
		var html = '';
		var length = lunboImg.length;
		if(length>0){
			for(var i = 0 ;i<length;i++){
				var goodsLunbo = lunboImg[i];
				html += '<li>';
				html += '<img onclick="changeLunboPos('+i+')" src="'+goodsLunbo+'" width="120" height="120" />';
				html += '<input	type="button" onclick="delLunBoImg(this,'+i+')" value="删除">';
				html += '</li>';
			}
			$("#lunboImgDesc").html(html);
		}
	}
	
	showDetailImg(xqImg);
	function showDetailImg(xqImg){
		var html = '';
		var length = xqImg.length;
		if(length>0){
			for(var i = 0 ;i<length;i++){
				var goodsDetail = xqImg[i];
				html += '<li>';
				html += '<img onclick="changeDetailPos('+i+')" src="'+goodsDetail+'" width="120" height="120" />';
				html += '<input	type="button" onclick="delDetailImg(this,'+i+')" value="删除">';
				html += '</li>';
			}
			$("#xqImgDesc").html(html);
		}
	}
	
	showImage(suoImg);
	function showImage(suoImg){
		var html = '';
		var length = suoImg.length;
		if(length>0){
			for(var i = 0 ;i<length;i++){
				var suo = suoImg[i];
				html += '<li>';
				html += '<img onclick="changeSuoPos('+i+')" src="'+suo+'" width="120" height="120" />';
				html += '</li>';
			}
			$("#suoImgDesc").html(html);
		}
	}
	
	showListImage(listImg);
	function showListImage(listImg){
		if(listImg != null && listImg.length > 0){
			var html = '';
			html += '<li>';
			html += '<img  src="'+listImg+'" width="120" height="120" />';
			html += '</li>';
			$("#listImgDesc").html(html);
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
	 * 改变轮播图位置
	 */
	function changeLunboPos(index){
		$.messager.prompt('位置','请输入要调整的位置',function(r){
			if(r){
				if(isNaN(r)){
					alert('请输入数字!');
				}else{
					var length = lunboImg.length;
					if(r<=0 || r>length){
						alert('请输入1~'+length+'之间!');
					}else{
						var imgPath = lunboImg[index];
						RemoveValByIndex(lunboImg,index);
						lunboImg.insert(r-1,imgPath);
						showAdvertImg(lunboImg);
					}
				}
			}
		});
	}
	
	/**
	 * 改变详情图位置
	 */
	function changeDetailPos(index){
		$.messager.prompt('位置','请输入要调整的位置',function(r){
			if(r){
				if(isNaN(r)){
					alert('请输入数字!');
				}else{
					var length = xqImg.length;
					if(r<=0 || r>length){
						alert('请输入1~'+length+'之间!');
					}else{
						var imgPath = xqImg[index];
						RemoveValByIndex(xqImg,index);
						xqImg.insert(r-1,imgPath);
						showDetailImg(xqImg);
					}
				}
			}
		});
	}
	
	//删除轮播图
	function delLunBoImg(f,index){
		RemoveValByIndex(lunboImg,index);
		$(f).parent().remove();
		showAdvertImg(lunboImg);
	}

	//删除详情图
	function delDetailImg(f,index){
		RemoveValByIndex(xqImg,index);
		$(f).parent().remove();
		showDetailImg(xqImg);
	}
	
	//删除缩略图
	function delSuoImg(f,index){
		RemoveValByIndex(suoImg,index);
		$(f).parent().remove();
		showSuoImg(suoImg);
	}
	
	//删除列表图
	function delListImg(f,index){
		RemoveValByIndex(ListImg,index);
		$(f).parent().remove();
		showListImg(ListImg);
	}
$(function() {
	//商品缩略图
	$("#suoImg")
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
						$("#image").val(result);
							imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
						$("#suoImgDesc").html(imgHtml);
					}
				}
			});
	//轮播图
	$("#lunboImg")
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
						lunboImg.push(result);
						var imgUrl = '"'+result+'"';
						imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delLunboImg(this,"+imgUrl+")' value='删除' /><li>";
						$("#lunboImgDesc").append(imgHtml);
					}
				}
			});
	//商品详情图
	$("#xqImg")
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
						xqImg.push(result);
						var imgUrl = '"'+result+'"';
						imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delXqImg(this,"+imgUrl+")' value='删除' /><li>";
						$("#xqImgDesc").append(imgHtml);
					}
				}
			});
	//活动列表图
	$("#listImg1")
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
						listImg = result;
						var imgUrl = '"'+result+'"';
						imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delListImg(this,"+imgUrl+")' value='删除' /><li>";
						$("#listImgDesc").html(imgHtml);
					}
				}
			});
	
	
	
    $('#imgEditForm').form({
        url : '${path }/activity/editGoodsImg?goodsId=${goodsId}&activityType=${activityType}',
        onSubmit : function() {
            progressLoad();
            
            var detailImg = xqImg.join(",");
            $("#detailImg").val(detailImg);
            
            var advertImg = lunboImg.join(",");
		    $("#advertImg").val(advertImg);
		    $("#listImg").val(listImg);
		    
            
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
            	agoodsDataGrid.datagrid('reload');
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
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="imgEditForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="image" id="image" value="${imageArr}" />
			<input type="hidden" name="advertImg" id="advertImg" /> 
			<input type="hidden" name="detailImg" id="detailImg" />
			<input type="hidden" name="listImg" id="listImg" value="${listImgArr}"/>
            <table class="grid">
            	<tr>
					<td align="right" bgcolor="#fafafa">商品缩略图:</td>
					<td colspan="3"><input type="file" name="file_upload" id="suoImg" />
						<div id="suoImgDesc">
						</div></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#fafafa">商品轮播图:</td>
					<td colspan="3"><input type="file" name="file_upload" id="lunboImg" />
						<div id="lunboImgDesc">
						</div></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#fafafa">商品详情图:</td>
					<td colspan="3"><input type="file" name="file_upload" id="xqImg" />
						<div id="xqImgDesc">
						</div></td>
				</tr>
				<tr>
					<td align="right" bgcolor="#fafafa">活动列表图:</td>
					<td colspan="3"><input type="file" name="file_upload" id="listImg1" />
						<div id="listImgDesc">
						</div></td>
				</tr>
            </table>
        </form>
    </div>
</div>