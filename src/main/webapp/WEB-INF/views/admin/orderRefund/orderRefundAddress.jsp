<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<style type="text/css">
.imgclass{    
	width: 70px;
    height: auto;
    margin: 5px;
 }
</style>
<script type="text/javascript">
$(function() {
	var address = '${address}';
	address = eval('(' + address + ')');
	if(address != null && address.province != null){
		initAddressFun(address);
	}else{
		queryProvince(0,'');
	}
	$('#refundAddressAddForm').form({
        url : '${path }/orderRefund/add',
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
            	orderRefundDataGrid.datagrid('reload');
                parent.$.modalDialog.handler.dialog('close');
                showMsg(result.msg);
            } else {
            	showMsg(result.msg);
            }
        }
    });
});
//收货地址数据绑定
function initAddressFun(address){
	queryProvince(0,address.province);
	queryCity(address.province,address.city);
	queryArea(address.city,address.area);
	$('input[name=name]').val(address.name);
	$('input[name=mobileNo]').val(address.mobileNo);
	$('#addressDetail').val(address.addressDetail);
	$('#expressNo').html(address.expressNo);
	$('#expressName').html(address.expressName);
}
//添加地址内容
function addAddressFun() {
	progressLoad();
    var isValid = $('#refundAddressAddForm').form('validate');
    if (!isValid) {
        progressClose();
    }
    if(isValid){
    	$.post('${path }/orderRefund/addAddress', {
    		name : $('input[name=name]').val(),
    		mobileNo : $('input[name=mobileNo]').val(),
    		addressDetail : $('#addressDetail').val(),
    		province : $('#province').combobox('getValue'),
    		city : $('#city').combobox('getValue'),
    		area : $('#area').combobox('getValue')
    	}, function(result) {
    		progressClose();
    		$('#addressSelectGrid').datagrid('reload');
    	}, 'JSON');
    }
	
}
function queryProvince(id,value){
	$("#province").combogrid({
		panelWidth: 200,
		panelHeight: 300,
		required:true,
		idField: 'id',
		textField:	'name',
		url: 'orderRefund/areaDataGrid',
		queryParams:{id:id},
		method: 'post',
		columns: [[
			{field:'id',title:'id',width:80,hidden:true},
			{field:'name',title:'名称',width:200,align:'center'}
		]],
		fitColumns: true,
		onSelect: function (rowIndex, rowData) {
			queryCity(rowData.id,'');
		},
		onLoadSuccess: function () {
			$('#province').combogrid('setValue', value);
		}
	});
}
function queryCity(id,value){
	$("#city").combogrid({
		panelWidth: 200,
		panelHeight: 300,
		required:true,
		idField: 'id',
		textField:	'name',
		url: 'orderRefund/areaDataGrid',
		queryParams:{id:id},
		method: 'post',
		columns: [[
			{field:'id',title:'id',width:80,hidden:true},
			{field:'name',title:'名称',width:200,align:'center'}
		]],
		fitColumns: true,
		onSelect: function (rowIndex, rowData) {
			queryArea(rowData.id,'');
		},
		onLoadSuccess: function () {
			$('#city').combogrid('setValue', value);
		}
	});
}
function queryArea(id,value){
	$("#area").combogrid({
		panelWidth: 200,
		panelHeight: 300,
		required:true,
		idField: 'id',
		textField:	'name',
		url: 'orderRefund/areaDataGrid',
		queryParams:{id:id},
		method: 'post',
		columns: [[
			{field:'id',title:'id',width:80,hidden:true},
			{field:'name',title:'名称',width:200,align:'center'}
		]],
		fitColumns: true,
		onLoadSuccess: function () {
			$('#area').combogrid('setValue', value);
		}
	});
}
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow-y:scroll; padding: 3px;" >
        <form id="refundAddressAddForm" method="post" enctype="multipart/form-data">
        	<input type="hidden" name="refundId" value="${refundId }"/>
            <table class="grid">
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">运单号</td>
                    <td id="expressNo"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">快递公司</td>
                    <td id="expressName"></td>
                </tr>
            	<tr>
                	<td align="right" bgcolor="#f8f8f8">联系人</td>
                    <td><input name="name" type="text" placeholder="请输入联系人" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">联系电话</td>
                    <td><input name="mobileNo" type="text" placeholder="请输入联系电话" class="easyui-validatebox span2" data-options="required:true"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">省份</td>
                    <td>
                    	<select name="province" id="province" class="easyui-combogrid" style="width: 50%; height: 29px;"  data-options="required:true"></select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">地市</td>
                    <td>
                    	<select name="city" id="city" class="easyui-combogrid" style="width: 50%; height: 29px;"  data-options="required:true"></select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">区县</td>
                    <td>
                    	<select name="area" id="area" class="easyui-combogrid" style="width: 50%; height: 29px;"  data-options="required:true"></select>
                    </td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">详细地址</td>
                    <td colspan="3"><textarea name="addressDetail" id="addressDetail" rows="4" style="width:95%;"></textarea></td>
                </tr>
            </table>
        </form>
        <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addAddressFun()">保存地址到选择栏</a>
	    </div>
        <table class="easyui-datagrid" id="addressSelectGrid" title="选择地址" style="width:500px;height:250px"
			data-options="
			singleSelect:true,
			collapsible:true,
			url:'${path }/orderRefund/addressDataGrid',
			method:'post',
			onClickRow: function (index, row) {
				initAddressFun(row);
			}
			">
			<thead>
				<tr>
					<th data-options="field:'id',width:80,hidden:true">ID</th>
					<th data-options="field:'name',width:80,align:'center'">联系人</th>
					<th data-options="field:'mobileNo',width:80,align:'center'">联系电话</th>
					<th data-options="field:'provinceName',width:80,align:'center'">省份</th>
					<th data-options="field:'cityName',width:80,align:'center'">地市</th>
					<th data-options="field:'areaName',width:80,align:'center'">区县</th>
					<th data-options="field:'addressDetail',width:100,align:'center'">详细地址</th>
				</tr>
			</thead>
		</table>
    </div>
</div>