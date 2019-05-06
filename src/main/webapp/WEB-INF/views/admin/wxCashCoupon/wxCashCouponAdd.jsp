<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	
    	$('#storeName').chosen();
    	
        $('#tXyWxCashCouponAddForm').form({
            url : '${path}/wxCashCoupon/add',
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
                    //之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#tXyWxCashCouponAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scrool;padding: 3px;" >
        <form id="tXyWxCashCouponAddForm" method="post">
            <input type="hidden" name="canShare" value="0" />
           <input type="hidden" name="canGiveFriend" value="0" />
            <table class="grid">
                <tr>
                   <td align="right" bgcolor="#fafafa">商户名称:</td>
                   <td colspan="3"><input name="brandName" type="text" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">卡劵名:</td>
                    <td colspan="3"><input name="title" type="text" class="easyui-validatebox span2" data-options="required:true" value="" /></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">卡劵金额:</td>
                    <td colspan="3"><input name="money" type="number" class="easyui-validatebox span2" data-options="required:true" value="" /></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">使用门槛:</td>
                    <td colspan="3"><input name="fullMoney" type="number" class="easyui-validatebox span2" data-options="required:true" value="" /></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">社交立减金可分享数量(3-15个):</td>
                    <td colspan="3"><input name="quantity" type="number" class="easyui-validatebox span2" data-options="required:true" value="" /></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">起始时间:</td>
                    <td colspan="3">
                      <input name="beginTimestamp" id="beginTimestamp" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d',maxDate:'#F{$dp.$D(\'endTimestamp\')}'})" readonly="readonly" class="easyui-validatebox span2" data-options="required:true" />
                      <span>起止时间需大于当前时间</span>
                    </td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">结束时间:</td>
                    <td colspan="3">
                      <input name="endTimestamp" id="endTimestamp" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginTimestamp\')}'})" readonly="readonly" class="easyui-validatebox span2" data-options="required:true" />
                      <span>建议结束时间与起始时间相差不超过60天</span>
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#fafafa">单日最大领取次数:</td>
                    <td colspan="3"><input name="getLimit" type="number" class="easyui-validatebox span2" data-options="required:true" value="1" /></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#fafafa">最大领取次数:</td>
                    <td colspan="3"><input name="useLimit" type="number" class="easyui-validatebox span2" data-options="required:true" value="1" /></td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">描述:</td>
                    <td colspan="3">
                      <textarea rows="6" cols="60" name="description"></textarea>
                    </td>
                </tr>
                <tr>
                   <td align="right" bgcolor="#fafafa">备注:</td>
                   <td colspan="3">
                      <textarea rows="6" cols="60" name="note"></textarea>
                   </td>
                </tr> 
            </table>
        </form>
    </div>
</div>