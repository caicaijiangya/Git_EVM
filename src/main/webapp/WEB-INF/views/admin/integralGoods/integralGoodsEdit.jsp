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

showAdvertImg(lunboImg);
function showAdvertImg(lunboImg){
var html = '';
var length = 0;
if(lunboImg != null){length = lunboImg.length;}
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
var length = 0;
if(xqImg != null){length = xqImg.length;}
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
var length = 0;
if(suoImg != null){length = suoImg.length;}
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
$(function() {
	var type = '${integralGoods.type}';
	selectGoods(type);
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
	
	$('#goodsName').chosen();
    $('#integralGoodsEditForm').form({
        url : '${path }/integralGoods/edit',
        onSubmit : function() {
            progressLoad();
            
            var detailImg = xqImg.join(",");
            $("#detailImg").val(detailImg);
            var advertImg = lunboImg.join(",");
		    $("#advertImg").val(advertImg);
            
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
            	integralGoodsDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
});

function selectGoods(type) {
	type = Number(type);
	if(type==0){
		$(".goodsAmount").show();
		$(".suoImg").show();
		$(".lunboImg").show();
		$(".xqImg").show();
	}else{
		$(".goodsAmount").hide()
		$(".suoImg").hide()
		$(".lunboImg").hide()
		$(".xqImg").hide()
	}
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="integralGoodsEditForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="advertImg" id="advertImg" />
        	<input type="hidden" name="detailImg" id="detailImg" />
            <input type="hidden" name="image" id="image" value="${imageArr}" />
            <input type="hidden" name="id" value="${integralGoods.id }"/>
            <input type="hidden" name="type" value="${integralGoods.type }"/>
            <input type="hidden" name="goodsId" value="${integralGoods.goodsId }"/>
            <table class="grid">
            	
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品类型</td>
                    <td>
                    	${integralGoods.type==0?'商品':'优惠券'}
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td>
                		${integralGoods.goodsName }
                	</td>
                </tr>
                <tr>
            		<td align="right" bgcolor="#f8f8f8">积分价格</td>
                    <td><input name="goodsPrice" type="number" value='${integralGoods.goodsPrice }' placeholder="请输入积分价格" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
            		<td align="right" bgcolor="#f8f8f8">每人限购数量（0不限购）</td>
                    <td><input name="buyNum" type="number" placeholder="请输入限购数量" class="easyui-validatebox span2" data-options="required:true" value="${integralGoods.buyNum }"></td>
                </tr>
                <tr class="goodsAmount">
            		<td align="right" bgcolor="#f8f8f8">商品可分配库存</td>
                    <td id="goodsAmount">${integralGoods.goodsTotalAmount }</td>
                </tr>
                <tr class="integralGoodsAmount">
            		<td align="right" bgcolor="#f8f8f8">积分商品库存</td>
                    <td id="integralGoodsAmount">${integralGoods.goodsAmount }</td>
                </tr>
                <tr class="amount">
            		<td align="right" bgcolor="#f8f8f8">增减库存</td>
                    <td><input name="amount" value="0" type="number" placeholder="请输入需增减的库存" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动开始时间</td>
                    <td><input id="startTime" type="text" name="startTime" value="<fmt:formatDate value="${integralGoods.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTime\')}'})"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动结束时间</td>
                    <td><input id="endTime" type="text" name="endTime" value="<fmt:formatDate value="${integralGoods.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}'})"></td>
                </tr>
                <tr class="suoImg">
					<td align="right" bgcolor="#fafafa">商品缩略图:</td>
					<td colspan="3"><input type="file" name="file_upload" id="suoImg" />
						<div id="suoImgDesc">
						</div></td>
				</tr>
				<tr class="lunboImg">
					<td align="right" bgcolor="#fafafa">商品轮播图:</td>
					<td colspan="3"><input type="file" name="file_upload" id="lunboImg" />
						<div id="lunboImgDesc">
						</div></td>
				</tr>
				<tr class="xqImg">
					<td align="right" bgcolor="#fafafa">商品详情图:</td>
					<td colspan="3"><input type="file" name="file_upload" id="xqImg" />
						<div id="xqImgDesc">
						</div></td>
				</tr>
            </table>
        </form>
    </div>
</div>