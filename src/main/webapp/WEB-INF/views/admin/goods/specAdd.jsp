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
var specImg = '';
//删除规格图
function delSpecImg(f){
	specImg = '';
	$(f).parent().remove();
}

$(function() {
	//轮播图
	$("#specImg")
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
						specImg = result;
						var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delSpecImg(this)' value='删除' /><li>";
						$("#specImgDesc").html(imgHtml);
					}
				}
			});
	
	$('#goodsSpecAddForm').form({
        url : '${path }/goods/addSpec',
        onSubmit : function() {
            progressLoad();
		    $("#goodsImage").val(specImg);
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
            	goodsSpecDataGrid.datagrid('reload');
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
        <form id="goodsSpecAddForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="id" value="${id }"/>
            <input type="hidden" name="goodsImage" id="goodsImage" />
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#fafafa">规格图:</td>
                    <td colspan="3" >
                    	<input type="file" name="file_upload" id="specImg" />
                    	<div id="specImgDesc"></div> 
					</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">规格名称</td>
                    <td><input name="specName" type="text" placeholder="请输入商品名称" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">SKU</td>
                    <td><input name="goodsCode" type="text" placeholder="请输入商品SKU" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">单价(元)</td>
                    <td>
                    	<input name="goodsPrice" type="number" style="width: 100px; height: 20px;" maxlength="15" value="0" data-options="required:true"></input>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">市场价(元)</td>
                    <td>
                    	<input name="marketPrice" type="number" style="width: 100px; height: 20px;" maxlength="15" value="0" data-options="required:true"></input>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品库存</td>
                    <td>
                    	<input name="goodsAmount" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true" value="0">
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">排序</td>
                    <td><input name="seq" type="number" style="width: 50px;" value="0" data-options="required:true"></td>
                </tr>
            </table>
        </form>
    </div>
</div>
