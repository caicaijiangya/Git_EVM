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
    			
    	$("#advertVedioFeng")
    	.uploadify(
    			{
    				swf : "${staticPath }/static/uploadify/uploadify.swf",
    				uploader : "${path}/fileUpload/upload",
    				fileObjName : "uploadFile", // 控制器中参数名称  
    				auto : true,
    				fileSizeLimit : "1024KB",
    				fileTypeExts : "*.jpg;*.gif;*.png;",
    				onUploadSuccess : function(file, result, response) {
    					if (result) {
    						goodsImg  = result;
    						imgHtml = "<a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a>";
    						$("#advertVedioFengDesc").html(imgHtml);
    						$("#fileFengUrl").val(result);
    					}
    				}
    			});

    	
        $('#tXyBrandStoryEditForm').form({
            url : '${path}/tXyBrandStory/edit',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
        
        var index = 1;
        //添加描述
        $("#addDesc").bind("click",function(){
        	var html = '';
            index = index + 1;
        	html+='<tr id="brandDesc'+index+'zdy">';
        	html+='<td align="right" bgcolor="#fafafa">描述'+index+':</td>';
        	html+='<td colspan="2">';
        	html+='<textarea rows="5" cols="60" name="storeDesc"></textarea>';
        	html+='</td>';
        	html+='<td>';
        	html+='<input type="button" class="delDescZdy"  id="'+index+'zdy"  value="移除" />';
        	html+='</td>';
        	html+='</tr>';
        	$("#gridTab").append(html);
        });
        
        $("#gridTab").on("click",".delDescZdy",function(){
	       	var id = $(this).attr("id");
	       	$("#gridTab #brandDesc"+id).remove();
        })
        
        $(".delDesc").bind("click",function(){
        	var id = $(this).attr("id");
        	$("#gridTab #brandDesc"+id).remove();
        })
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: scrool;padding: 3px;">
        <form id="tXyBrandStoryEditForm" method="post">
        	<input name="id" type="hidden"  value="${tXyBrandStory.id}">
        	<input type="hidden" name="coverImg" id="fileFengUrl" value="${tXyBrandStory.coverImg}" />
            <input type="hidden" name="introImg" id="fileUrl" value="${tXyBrandStory.introImg}" />
            <table class="grid"  id="gridTab">
                <tr>
                   <td align="right" bgcolor="#fafafa">标题:</td>
                   <td colspan="3"><input name="title" type="text" class="easyui-validatebox span2" data-options="required:true" value="${tXyBrandStory.title}" style="width:82%;"></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">简易描述:</td>
                   <td colspan="3"><input name="simpleDesc" type="text" class="easyui-validatebox span2" data-options="required:true" value="${tXyBrandStory.simpleDesc}" style="width:82%;"></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">作者:</td>
                   <td colspan="3"><input name="author" type="text" class="easyui-validatebox span2" data-options="required:true" value="${tXyBrandStory.author}" style="width:82%;"></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">标签:</td>
                   <td colspan="3"><input name="labelDesc" type="text" class="easyui-validatebox span2" data-options="required:true" value="${tXyBrandStory.labelDesc}" style="width:82%;"></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">封面:</td>
                   <td colspan="3">
                      <input type="file" name="file_upload" id="advertVedioFeng" />
	                  <div id="advertVedioFengDesc">
	                   	  <a href='${tXyBrandStory.coverImg}' target='_blank'><img src='${tXyBrandStory.coverImg}' width='280' height='120'/></a>
	                  </div> 
                   </td>
                </tr> 
                <tr>
                   <td align="right" bgcolor="#fafafa">图片:</td>
                   <td colspan="3">
                      <input type="file" name="file_upload" id="advertImg" />
	                  <div id="advertImgDesc">
	                   	 <a href='${tXyBrandStory.introImg}' target='_blank'><img src='${tXyBrandStory.introImg}' width='280' height='120'/></a>
	                  </div> 
                   </td>
                </tr> 
                <tr>
                   <td align="right" bgcolor="#fafafa">是否显示:</td>
                   <td colspan="3">
                      <input type="radio" name="isShow" id="yesShow" value="0" <c:if test="${tXyBrandStory.isShow==0 }">checked="checked"</c:if> />显示
                      <input type="radio" name="isShow" id="noShow" value="1" <c:if test="${tXyBrandStory.isShow==1 }">checked="checked"</c:if>/>不显示
                   </td>
                </tr> 
                <tr>
                   <td align="right" bgcolor="#fafafa">是否置顶:</td>
                   <td colspan="3">
                      <input type="radio" name="isTop" id="yesShow" value="0" <c:if test="${tXyBrandStory.isTop==0 }">checked="checked"</c:if> />否
                      <input type="radio" name="isTop" id="noShow" value="1" <c:if test="${tXyBrandStory.isTop==1 }">checked="checked"</c:if>/>是
                   </td>
                </tr> 
                <tr>
                   <td align="right" bgcolor="#fafafa">排序:</td>
                    <td colspan="3"><input name="seq" type="number" class="easyui-validatebox span2" data-options="required:true" value="${tXyBrandStory.seq}" /></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">跳转指定商品:</td>
                   <td colspan="3">
                      <select name="goodsId">
                         <option value="">===请选择===</option>
                         <c:forEach items="${goodsList }" var="goods">
                            <option value="${goods.id }" <c:if test="${goods.id == tXyBrandStory.goodsId }">selected='selected'</c:if>>${goods.goodsName }</option>
                         </c:forEach>
                      </select>
                   </td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">备注:</td>
                   <td colspan="3">
                      <textarea rows="3" cols="60" name="note">${tXyBrandStory.note}</textarea>
                   </td>
                </tr>
                <c:if test="${not empty listDesc}">
                <c:forEach items="${listDesc }" var="desc" varStatus="index">
	                 <tr id="brandDesc${index.index }">
	                   <td align="right" bgcolor="#fafafa">描述${index.index + 1 }:</td>
	                   <td colspan="2">
	                      <textarea rows="5" cols="60" name="storeDesc">${desc }</textarea>
	                   </td>
	                   <td>
	                      <c:if test="${index.index==0 }">
	                      	<input type="button" id="addDesc" value="添加描述" />
	                      </c:if>
	                      <c:if test="${index.index>0 }">
	                        <input type="button" class="delDesc" id="${index.index }"  value="移除" />
	                      </c:if>
	                   </td>
	                </tr>
                 </c:forEach>
                </c:if>
                <c:if test="${empty listDesc}">
                   <tr id="brandDesc">
	                   <td align="right" bgcolor="#fafafa">描述1:</td>
	                   <td colspan="2">
	                      <textarea rows="3" cols="60" name="storeDesc"></textarea>
	                   </td>
	                   <td>
	                      <input type="button" id="addDesc" value="添加描述" />
	                   </td>
	                </tr>
                </c:if>
            </table>
        </form>
    </div>
</div>