<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#questDimensionEditForm').form({
            url : '${path }/questDimension/edit',
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
                	questDimensionDataGrid.datagrid('reload');
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
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="questDimensionEditForm" method="post" >
        	<input name="id" type="hidden" value="${questDimension.id }"/>
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品分类:</td>
                    <td>
                		<select name="classifyId"  data-placeholder="请选择" style="width:210px;" id="classifyId" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${classifyList }" var="classifyList">
    							<option value="${classifyList.id }" <c:if test="${classifyList.id==questDimension.classifyId }">selected="selected"</c:if>>${classifyList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">英文标识</td>
                    <td>
                    	<input name="key" type="text" placeholder="请输入英文标识" class="easyui-validatebox span2" value="${questDimension.key }" data-options="required:true">
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">维度名称</td>
                    <td>
                    	<input name="name" type="text" placeholder="请输入维度名称" class="easyui-validatebox span2" value="${questDimension.name }" data-options="required:true">
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>