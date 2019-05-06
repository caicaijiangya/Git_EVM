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

var lunboImg = [];
var suoImg = '';
var suoVideo = '';
var xqImg = [];
//重写数组删除方法
Array.prototype.remove = function(val) {
	var index = this.indexOf(val);
	if (index > -1) {
		this.splice(index, 1);
	}
};

//删除轮播图
function delLunboImg(f,imgUrl){
	lunboImg.remove(imgUrl);
	$(f).parent().remove();
}

//删除商品详情图
function delXqImg(f,imgUrl){
	xqImg.remove(imgUrl);
	$(f).parent().remove();
}

$(function() {
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
						suoImg = result;
						imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
						$("#suoImgDesc").html(imgHtml);
					}
				}
			});
	//商品视频
	$("#suoVideo")
		.uploadify(
			{
				swf : "${staticPath }/static/uploadify/uploadify.swf",
				uploader : "${path}/fileUpload/upload",
				fileObjName : "uploadFile", // 控制器中参数名称  
				auto : true,
				fileSizeLimit : "51200KB",
				fileTypeExts : "*.mp4;*.rmvb;*.avi;",
				onUploadSuccess : function(file, result, response) {
					if (result) {
						suoVideo = result;
						imgHtml = "<a target='_blank'><img src='static/style/images/video.png' width='120' height='120'/></a>";
						$("#suoVideoDesc").html(imgHtml);
					}
				}
			});
	$('#goodsAddForm').form({
        url : '${path }/goods/add',
        onSubmit : function() {
            progressLoad();
            
            var detailImg = xqImg.join(",");
            $("#detailImg").val(detailImg);
            var advertImg = lunboImg.join(",");
		    $("#advertImg").val(advertImg);
		    
		    $("#goodsImage").val(suoImg);
		    $("#goodsVideo").val(suoVideo);
		    
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
            	goodsDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
	
	$("#classifys").combogrid({
		panelWidth: 300,
		panelHeight: 300,
		required:true,
		multiple: true,
		idField: 'id',
		textField:	'name',
		url: 'goodsOther/goodsClassifyGrid',
		method: 'post',
		columns: [[
			{field:'id',title:'id',width:80,hidden:true},
			{field:'ck',checkbox:true},
			{field:'name',title:'分类名称',width:200,align:'center'}
		]],
		fitColumns: true
	});
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="goodsAddForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="advertImg" id="advertImg" />
        	<input type="hidden" name="detailImg" id="detailImg">
            <input type="hidden" name="goodsImage" id="goodsImage" />
            <input type="hidden" name="goodsVideo" id="goodsVideo" />
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#fafafa">商品缩略图:</td>
                    <td>
                    	<input type="file" name="file_upload" id="suoImg" />
                    	<div id="suoImgDesc"></div> 
					</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#fafafa">商品轮播图:</td>
                    <td>
                        <input type="file" name="file_upload" id="lunboImg" />
                        <div id="lunboImgDesc"></div>  
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#fafafa">商品详情图:</td>
                    <td>
                    	<input type="file" name="file_upload" id="xqImg" />
                    	<div id="xqImgDesc"></div> 
					</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#fafafa">商品视频:</td>
                    <td>
                    	<input type="file" name="file_upload" id="suoVideo" />
                    	<div id="suoVideoDesc"></div> 
					</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td><input name="goodsName" type="text" placeholder="请输入商品名称" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品品牌</td>
                    <td>
                    	<select id="brandId" name="brandId" style="width: 140px; height: 29px;" class="easyui-combobox" data-options="required:true">
                    		<c:forEach items="${brands }" var="item">
	                    		<option value="${item.id }">${item.name }</option>
	                    	</c:forEach>
                    	</select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品分类</td>
                    <td>
                    	<select name="classifys" id="classifys" class="easyui-combogrid" style="width: 50%; height: 29px;"  data-options="required:true"></select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品描述</td>
                    <td><input name="goodsDesc" type="text" style="width: 90%;" placeholder="请输入商品描述" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品标签</td>
                    <td><input name="labels" type="text" style="width: 90%;" placeholder="多个标签请以,号隔开" class="easyui-validatebox span2"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">排序</td>
                    <td><input name="seq" type="number" style="width: 50px;" value="0" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">是否隐藏</td>
                    <td>
                    	<input name="isShow" value="0" type="radio" checked="checked">否
                    	<input name="isShow" value="1" type="radio" >是
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
