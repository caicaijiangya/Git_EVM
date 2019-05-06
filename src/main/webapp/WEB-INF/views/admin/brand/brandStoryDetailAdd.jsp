<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	
    	$("#advertImg")
		.uploadify(
				{
					swf : "${staticPath }/static/uploadify/uploadify.swf",
					uploader : "${path}/fileUpload/upload",
					fileObjName : "uploadFile", // 控制器中参数名称  
					auto : true,
					fileSizeLimit : "2048KB",
					fileTypeExts : "*.jpg;*.gif;*.png;",
					onUploadSuccess : function(file, result, response) {
						if (result) {
							goodsImg  = result;
							imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
							$("#advertImgDesc").html(imgHtml);
							$("#fileUrl").val(result);
						}
					}
				});
    	
    	
        $('#tXyBrandStoryDetailAddForm').form({
            url : '${path}/brandStoryDetail/add',
            onSubmit : function() {
                progressLoad();
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
                    //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scrool;padding: 3px;" >
        <form id="tXyBrandStoryDetailAddForm" method="post">
            <input type="hidden" name="brandStoryId" id="brandStoryId" value="${brandStoryId }" />
            <input type="hidden" name="introImg" id="fileUrl" />
            <table class="grid">
                <tr>
                   <td align="right" bgcolor="#fafafa">标题:</td>
                   <td colspan="3"><input name="title" type="text" class="easyui-validatebox span2" data-options="required:true" value="" style="width:82%;"></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">图片:</td>
                   <td colspan="3">
                      <input type="file" name="file_upload" id="advertImg" />
	                  <div id="advertImgDesc">
	                   	 
	                  </div> 
                   </td>
                </tr> 
                <tr>
                   <td align="right" bgcolor="#fafafa">是否显示:</td>
                   <td colspan="3">
                      <input type="radio" name="isShow" id="yesShow" value="0" checked="checked" />显示
                      <input type="radio" name="isShow" id="noShow" value="1" />不显示
                   </td>
                </tr> 
                <tr>
                   <td align="right" bgcolor="#fafafa">排序:</td>
                    <td colspan="3"><input name="seq" type="number" class="easyui-validatebox span2" data-options="required:true" value="" /></td>
                </tr>
                <tr id="brandDesc">
                   <td align="right" bgcolor="#fafafa">描述:</td>
                   <td colspan="3">
                      <textarea rows="6" cols="60" name="brandDetail"></textarea>
                   </td>
                </tr>  
                <tr>
                   <td align="right" bgcolor="#fafafa">备注:</td>
                   <td colspan="3">
                      <textarea rows="3" cols="60" name="note"></textarea>
                   </td>
                </tr>  
            </table>
        </form>
    </div>
</div>