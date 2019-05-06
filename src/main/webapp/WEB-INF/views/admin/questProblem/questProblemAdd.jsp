<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#problemAddForm').form({
            url : '${path }/questProblem/add',
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
                	questProblemDataGrid.datagrid('reload');
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
        <form id="problemAddForm" method="post" >
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">维度名称:</td>
                    <td>
                		<select name="dimensionId" onchange="selectedProvince();" data-placeholder="请选择" style="width:210px;" id="dimensionId" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${questDimensionList }" var="questDimensionList">
    							<option value="${questDimensionList.id }">${questDimensionList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">问题内容:</td>
                    <td colspan="2"><input name="title" type="text" placeholder="请输入问题" class="easyui-validatebox span2" value="" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">问题类型</td>
                    <td>
                    	<input name="type" value="0" type="radio" checked="checked">默认
                    	<input name="type" value="1" type="radio" >皮肤问题
                    	<input name="type" value="2" type="radio" >花费问题
                    	<input name="type" value="3" type="radio" >职业问题
                    	<input name="type" value="4" type="radio" >地区问题
                    	<input name="type" value="5" type="radio" >年龄问题
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">排序</td>
                    <td><input name="seq" value="0" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:true"></td>
                </tr>
            </table>
        </form>
    </div>
</div>