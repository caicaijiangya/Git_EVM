<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">

$(function() {
	$('#editHotForm').form({
        url : '${path }/goodsOther/editHot',
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
            	hotDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
});

</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="editHotForm" method="post">
        	<input type="hidden" name="id" value="${obj.id }"/>
        	<input type="hidden" name="goodsId" value="${obj.goodsId }"/>
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品名称</td>
                    <td>
                    	${obj.goodsName }
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">类型</td>
                    <td>
                    	<input type="radio" name="type" value="0" <c:if test="${obj.type == 0 }">checked="checked"</c:if>/> 人气精选
                    	<input type="radio" name="type" value="1" <c:if test="${obj.type == 1 }">checked="checked"</c:if>/> 经典口碑
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">尺寸</td>
                    <td>
                    	<select name="size" style="width: 100px; height: 29px;" class="easyui-combobox" data-options="required:true">
                    		<option value="264" <c:if test="${obj.size== 264}">selected="selected"</c:if>>264</option>
                    		<option value="280" <c:if test="${obj.size== 280}">selected="selected"</c:if>>280</option>
                    		<option value="350" <c:if test="${obj.size== 350}">selected="selected"</c:if>>350</option>
                    	</select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">序号</td>
                    <td>
                    	<input name="seq" value="${obj.seq }" style="width: 80px; height: 20px;" type="number" maxlength="10" data-options="required:true">
                    </td>
                </tr>
                
            </table>
        </form>
    </div>
</div>