<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	
        $('#discountParameterEditForm').form({
            url : '${path }/activity/editDiscount',
            onSubmit : function() {
                progressLoad();
                if (endEditing1()){
        			var index = $('#dg1').datagrid('getRows').length;
        			if(index > 0){
        				var fullPrices = "";
        				var prePrices = "";
        				for(var i = 0; i < index; i++){
        					var row = $('#dg1').datagrid('getRows')[i];
        					var fullPrice = row['fullPrice'];
        					var prePrice = row['prePrice'];
        					if(fullPrice != null && fullPrice != '' && prePrice != null && prePrice != ''){
        						fullPrices = fullPrices + fullPrice +",";
        						prePrices = prePrices + prePrice +",";
        					}
        				}
        				if(fullPrices.length > 0 && prePrices.length > 0){
        					fullPrices = fullPrices.substring(0,fullPrices.length-1);
        					prePrices = prePrices.substring(0,prePrices.length-1);
        				}
        				$("input[name='fullPrices']").val(fullPrices);
        				$("input[name='prePrices']").val(prePrices);
        			}
        		}
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
                	activityDataGrid.datagrid('reload');
                	parent.$.modalDialog.handler.dialog('close');
                	showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
        
    })
	/////////////////////////////////////
	var editIndex1 = undefined;
	function endEditing1(){
		if (editIndex1 == undefined){return true}
		if ($('#dg1').datagrid('validateRow', editIndex1)){
			$('#dg1').datagrid('endEdit', editIndex1);
			editIndex1 = undefined;
			return true;
		} else {
			return false;
		}
	}
	function append1(){
		if (endEditing1()){
			$('#dg1').datagrid('appendRow',{status:'P'});
			editIndex1 = $('#dg1').datagrid('getRows').length-1;
			$('#dg1').datagrid('selectRow', editIndex1)
					.datagrid('beginEdit', editIndex1);
		}
	}
	function removeit1(){
		if (editIndex1 == undefined){return}
		$('#dg1').datagrid('cancelEdit', editIndex1)
				.datagrid('deleteRow', editIndex1);
		editIndex1 = undefined;
	}
	function onClickRow1(index){
		if (editIndex1 != index){
			if (endEditing1()){
				$('#dg1').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				editIndex1 = index;
			} else {
				$('#dg1').datagrid('selectRow', editIndex);
			}
		}
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scroll;padding: 3px;" >
        <form id="discountParameterEditForm" method="post">
        	<input name="activityId" type="hidden" value="${id }"/>
        	<input name="fullPrices" type="hidden" value=""/>
        	<input name="prePrices" type="hidden" value=""/>
			<div id="priceList">
	            <table id="dg1" class="easyui-datagrid" title="折扣条件配置" style="width:100%;height:auto;"
				data-options="
					iconCls: 'icon-edit',
					singleSelect: true,
					toolbar: '#tb1',
					url: 'activity/discountDataGrid?id=${id }',
					method: 'post',
					onClickRow: onClickRow1
				">
					<thead>
						<tr>
							<th data-options="field:'fullPrice',width:150,align:'left',editor:{type:'numberbox'}">条件</th>
							<th data-options="field:'prePrice',width:150,align:'left',editor:{type:'numberbox',options:{precision:2}}">优惠折扣</th>
						</tr>
					</thead>
				</table>
				<div id="tb1" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append1()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit1()">删除</a>
				</div>
			</div>
        </form>
    </div>
</div>