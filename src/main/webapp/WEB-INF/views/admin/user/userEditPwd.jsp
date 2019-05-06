<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#editUserPwdForm').form({
            url : '${path }/user/editUserPwd',
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
                    parent.$.messager.alert('提示', result.msg, 'info');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;">
            <form id="editUserPwdForm" method="post">
                <table class="grid">
                    <tr>
                        <td bgcolor="#f8f8f8" align="right">登录名：</td>
                        <td><shiro:principal></shiro:principal></td>
                    </tr>
                    <tr>
                        <td bgcolor="#f8f8f8" align="right">原密码：</td>
                        <td><input name="oldPwd" type="password" placeholder="请输入原密码" class="easyui-validatebox" data-options="required:true"></td>
                    </tr>
                    <tr>
                        <td bgcolor="#f8f8f8" align="right">新密码：</td>
                        <td><input name="pwd" type="password" placeholder="请输入新密码" class="easyui-validatebox" data-options="required:true"></td>
                    </tr>
                    <tr>
                        <td bgcolor="#f8f8f8" align="right">重复密码：</td>
                        <td><input name="rePwd" type="password" placeholder="请再次输入新密码" class="easyui-validatebox" data-options="required:true,validType:'eqPwd[\'#editUserPwdForm input[name=pwd]\']'"></td>
                    </tr>
                </table>
            </form>
    </div>
</div>