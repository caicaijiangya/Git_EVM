<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#organizationEditPid').combotree({
            url : '${path }/organization/tree?flag=false',
            parentField : 'pid',
            panelHeight : '500px',
            value :'${organization.pid}'
        });
        
        $('#organizationEditForm').form({
            url : '${path }/organization/edit',
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
                	organizationTreeGrid.treegrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        
    });
</script>
<div style="padding: 3px;">
    <form id="organizationEditForm" method="post">
        <table class="grid">
            <tr>
                <td align="right">编号</td>
                <td><input name="id" type="hidden"  value="${organization.id}"><input name="code" type="text" value="${organization.code}" /></td>
                <td align="right">资源名称</td>
                <td><input name="name" type="text" value="${organization.name}" placeholder="请输入部门名称" class="easyui-validatebox" data-options="required:true" ></td>
            </tr>
            <tr>
            	<td align="right">菜单图标</td>
                <td ><input name="icon" value="${organization.icon}" onclick='top.window.openIconDialog(this)'/></td>
                <td align="right">排序</td>
                <td><input name="seq"  class="easyui-numberspinner" value="${organization.seq}" style="widtd: 140px; height: 29px;" required="required" data-options="editable:false"></td>
            </tr>
            <tr>
                <td align="right">上级资源</td>
                <td colspan="3"><select id="organizationEditPid" name="pid" style="width: 200px; height: 29px;"></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a></td>
            </tr>
            <tr>
                <td align="right">地址</td>
                <td colspan="3"><textarea rows="4" name="address" style="width:95%;">${organization.address}</textarea></td>
            </tr>
        </table>
    </form>
</div>
