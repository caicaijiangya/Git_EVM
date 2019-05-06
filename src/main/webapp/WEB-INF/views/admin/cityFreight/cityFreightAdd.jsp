<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#cityFreightAddForm').form({
            url : '${path }/cityFreight/add',
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
                	cityFreightDataGrid.datagrid('reload');
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
        <form id="cityFreightAddForm" method="post" >
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">省份:</td>
                    <td>
                		<select name="province" style="width:210px;" id="province" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${areaList }" var="area">
    							<option value="${area.id }">${area.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">运费</td>
                    <td>
                    	<input name="money" class="easyui-numberbox" style="width: 150px; height: 30px;" maxlength="15" value="0" data-options="precision:2,groupSeparator:',',required:true"></input>
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">超重（每公斤）</td>
                    <td>
                    	<input name="exceedMoney" class="easyui-numberbox" style="width: 150px; height: 30px;" maxlength="15" value="0" data-options="precision:2,groupSeparator:',',required:true"></input>
                    	<span style="color:red;">*暂不支持</span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>