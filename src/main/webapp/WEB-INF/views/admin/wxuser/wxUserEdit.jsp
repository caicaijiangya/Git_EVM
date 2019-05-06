<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#userInfoEditForm').form({
            url : '${path }/wxUserInfo/edit',
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
                	tXyUserDataGrid.datagrid('reload');
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
        <form id="userInfoEditForm" method="post">
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">编号</td>
                    <td>
                		<input name="code" type="text" value="${wxUserInfo.code }" placeholder="请输入编号" class="easyui-validatebox span2" data-options="required:true">
                	</td>
                </tr>
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">姓名</td>
                    <td>
                		<input name="userName" value="${wxUserInfo.userName }" type="text" placeholder="请输入姓名" class="easyui-validatebox span2" data-options="required:true">
                	</td>
                </tr>
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">所属门店</td>
                    <td>
                		<select name="storeId" data-placeholder="选择门店" style="width:210px;" id="storeName" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${storeList }" var="storeList">
    							<option value="${storeList.id}" <c:if test="${storeList.id== wxUserInfo.storeId}">selected="selected"</c:if>>${storeList.storeName }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">用户类型</td>
                    <td>
                    	<input type="hidden" name="id" value="${wxUserInfo.id }"/>
                    	<input name="userType" value="1" type="radio" ${wxUserInfo.userType==1?'checked':''}>店主
                    	<input name="userType" value="3" type="radio" ${wxUserInfo.userType==3?'checked':''}>店员
                    	<input name="userType" value="4" type="radio" ${wxUserInfo.userType==4?'checked':''}>消费者
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>