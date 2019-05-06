<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#storeAreaEditForm').form({
            url : '${path }/storeArea/edit',
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
                	storeAreaDataGrid.datagrid('reload');
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
        <form id="storeAreaEditForm" method="post">
        	<input name='id' type="hidden" value='${storeArea.id }'/>
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">所在市:</td>
                    <td>
                		<select name="areaId" data-placeholder="请选择" style="width:210px;" id="city" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${cityList }" var="cityList">
    							<option value="${cityList.id }" <c:if test='${cityList.id==storeArea.areaId }'>selected="selected"</c:if>>${cityList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">排序:</td>
                    <td><input name="seq" type="number" placeholder="请输入排序序号" class="easyui-validatebox span2" data-options="required:true" value="${storeArea.seq }"></td>
                </tr>
            </table>
        </form>
    </div>
</div>