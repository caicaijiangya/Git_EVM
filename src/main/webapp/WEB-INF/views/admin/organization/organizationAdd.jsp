<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#organizationAddPid').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            panelHeight : '500px'
        });
        
        $('#organizationAddForm').form({
            url : '${path }/organization/add',
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
                    var form = $('#organizationAddForm');
                    parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        
    });
</script>
<div style="padding: 3px;">
    <form id="organizationAddForm" method="post">
        <table class="grid">
            <tr>
                <td align="right">编号</td>
                <td><input name="code" type="text" placeholder="请输入部门编号" class="easyui-validatebox" data-options="required:true" ></td>
                <td align="right">部门名称</td>
                <td><input name="name" type="text" placeholder="请输入部门名称" class="easyui-validatebox" data-options="required:true" ></td>
                
            </tr>
            <tr>
            	<td align="right">菜单图标</td>
                <td><input name="icon" onclick='top.window.openIconDialog(this)'/></td>
                <td align="right">排序</td>
                <td><input name="seq" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false" value="0"></td>
            </tr>
            <tr>
                <td align="right">上级部门</td>
                <td colspan="3"><select id="organizationAddPid" name="pid" style="width:200px;height: 29px;"></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a></td>
            </tr>
            <tr>
                <td align="right">地址</td>
                <td colspan="3"><textarea rows="4" name="address" style="width:95%;"></textarea></td>
            </tr>
        </table>
    </form>
</div>