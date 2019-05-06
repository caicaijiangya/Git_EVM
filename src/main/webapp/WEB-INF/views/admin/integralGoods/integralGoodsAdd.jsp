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
//删除商品缩略图
function delSuoImg(f,imgUrl){
	suoImg.remove(imgUrl);
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
						$("#image").val(result);
						imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
						$("#suoImgDesc").html(imgHtml);
					}
				}
			});
	$("#brandId").combobox({
		onChange: function (n,o) {
			initcombogrid(n);
		}
	});
	initcombogrid($('#brandId').combobox('getValue'));
	$('#goodsName').chosen();
    $('#integralGoodsAddForm').form({
        url : '${path }/integralGoods/add',
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
    
    
    function initcombogrid(brandId){
		$("#goodsId").combogrid({
			panelWidth: 500,
			panelHeight: 300,
			idField: 'id',
			textField:	'goodsName',
			mode: 'remote',
			url: 'goodsOther/dataGrid',
			queryParams :{brandId:brandId},
			method: 'post',
			nowrap: false,
			columns: [[
				{field:'id',title:'id',width:80,hidden:true},
				{field:'goodsImage',title:'商品图片',width:80,align:'center',
					formatter:function(value,row,index){
						return '<img src='+value+' width=80 height=80/>';
					}
				},
				{field:'goodsName',title:'商品名称',width:200,align:'center'},
				{field:'specName',title:'规格名称',width:80,align:'center'},
				{field:'brandName',title:'品牌',width:80,align:'center'},
				{field:'goodsPrice',title:'价格',width:80,align:'center'}
			]],
			fitColumns: true,
			onSelect: function (rowIndex, rowData) {
				$.post('${path }/storeGoods/amount', {
					id : rowData.id
				}, function(result) {
					$('#goodsAmount').html(result);
				}, 'JSON');
			}
		});
	}
});

//选中type时触发onclick传参
function selectGoods() {
	var type= $('input:radio:checked').val();
	if(type==0){
		$(".goods").show();
		$(".coupon").hide();
		$(".amount").show();
		$(".integralGoodsAmount").show();
		$(".goodsAmount").show();
		$(".suoImg").show();
		$(".lunboImg").show();
		$(".xqImg").show();
	}else{
		$(".goods").hide();
		$(".coupon").show();
		$(".amount").show()
		$(".integralGoodsAmount").show()
		$(".goodsAmount").hide()
		$(".suoImg").hide()
		$(".lunboImg").hide()
		$(".xqImg").hide()
	}
}
</script>

<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="integralGoodsAddForm" method="post">
        	<input type="hidden" name="advertImg" id="advertImg" />
        	<input type="hidden" name="detailImg" id="detailImg">
            <input type="hidden" name="image" id="image" value="${imageArr}" />
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品类型</td>
                    <td>
                    	<input name="type" value="0" onclick="selectGoods()" type="radio" checked="checked">商品
                    	<input name="type" value="1" type="radio" onclick="selectGoods()">优惠券
                    </td>
                </tr>
                <tr class="goods">
                	<td align="right" bgcolor="#f8f8f8">商品品牌</td>
                    <td>
                    	<select id="brandId" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<c:forEach items="${brands }" var="item">
	                    		<option value="${item.id }">${item.name }</option>
	                    	</c:forEach>
                    	</select>
                    </td>
                </tr>
                <tr class="goods">
                	<td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td>
                    	<select name="goodsId" id="goodsId" class="easyui-combogrid" style="width:250px;" ></select>
                    </td>
                </tr>
                <tr class="coupon" style="display: none;">
                    <td align="right" bgcolor="#f8f8f8">优惠券</td>
                    <td>
                		<select name="couponId" data-placeholder="请选择" style="width:210px;" class="easyui-combobox"> 
    						<option value="">请选择</option>
    						<c:forEach items="${couponList }" var="coupon">
   								<option value="${coupon.id }">${coupon.goodsName }</option>
   							</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
            		<td align="right" bgcolor="#f8f8f8">积分价格</td>
                    <td><input name="goodsPrice" type="number" placeholder="请输入积分价格" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
            		<td align="right" bgcolor="#f8f8f8">每人限购数量（0不限购）</td>
                    <td><input name="buyNum" type="number" placeholder="请输入限购数量" class="easyui-validatebox span2" data-options="required:true" value="0"></td>
                </tr>
                <tr class="goodsAmount">
            		<td align="right" bgcolor="#f8f8f8">商品可分配库存</td>
                    <td id="goodsAmount" ></td>
                </tr>
                <tr class="integralGoodsAmount">
            		<td align="right" bgcolor="#f8f8f8">积分商品库存</td>
                    <td><input name="goodsAmount" value="0"  type="number" style="border: 0px;outline:none;cursor: pointer;" readonly="readonly"  class="easyui-validatebox span2"  ></td>
                </tr>
                <tr class="amount">
            		<td align="right" bgcolor="#f8f8f8" class="amount">增减库存</td>
                    <td><input name="amount" class="amount" type="number" value="0" placeholder="请输入需增减的库存" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动开始时间</td>
                    <td><input id="startTime" type="text" name="startTime" value=""  required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTime\')}'})"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">活动结束时间</td>
                    <td><input id="endTime" type="text" name="endTime" value="" required="required" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'startTime\')}'})"></td>
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