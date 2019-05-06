<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<style type="text/css">
  li{
    list-style:none;
    float:left;
    margin-left:10px;
  }
</style>
<script type="text/javascript">
    $(function() {
        $('#collageParameterEditForm').form({
            url : '${path }/activity/editCollageParameter',
            onSubmit : function() {
                progressLoad();
                if (endEditing()){
        			var index = $('#dg').datagrid('getRows').length;
        			if(index > 0){
        				var nums = "";
        				var times = "";
        				var prices = "";
        				for(var i = 0; i < index; i++){
        					var row = $('#dg').datagrid('getRows')[i];
        					var collageNum = row['collageNum'];
        					var collageTime = row['collageTime'];
        					var collagePrice = row['collagePrice'];
        					if(collageNum != null && collageNum != '' && collageTime != null && collageTime != ''){
        						nums = nums + collageNum +",";
        						times = times + collageTime +",";
        						prices = prices + collagePrice+",";
        					}
        				}
        				if(nums.length > 0 && times.length > 0){
        					nums = nums.substring(0,nums.length-1);
        					times = times.substring(0,times.length-1);
        					prices = prices.substring(0,prices.length-1);
        				}
        				$("#nums").val(nums);
        				$("#times").val(times);
        				$("#prices").val(prices);
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
                	agoodsDataGrid.datagrid('reload');
                	parent.$.modalDialog.handler.dialog('close');
                	showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
        
    })
    var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#dg').datagrid('validateRow', editIndex)){
			$('#dg').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function append(){
		if (endEditing()){
			$('#dg').datagrid('appendRow',{status:'P'});
			editIndex = $('#dg').datagrid('getRows').length-1;
			$('#dg').datagrid('selectRow', editIndex)
					.datagrid('beginEdit', editIndex);
		}
	}
	function removeit(){
		if (editIndex == undefined){return}
		$('#dg').datagrid('cancelEdit', editIndex)
				.datagrid('deleteRow', editIndex);
		editIndex = undefined;
	}
	function onClickRow(index){
		if (editIndex != index){
			if (endEditing()){
				$('#dg').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				editIndex = index;
			} else {
				$('#dg').datagrid('selectRow', editIndex);
			}
		}
	}
	
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: scroll;padding: 3px;" >
        <form id="collageParameterEditForm" method="post">
        	<input name="activityGoodsId" type="hidden" value="${id }"/>
        	<input type="hidden" name="nums" id="nums" />
            <input type="hidden" name="times" id="times" />
            <input type="hidden" name="prices" id="prices"/>
            <div>
	            <table id="dg" class="easyui-datagrid" title="拼团阶梯编辑" style="width:100%;height:auto;"
				data-options="
					iconCls: 'icon-edit',
					singleSelect: true,
					toolbar: '#tb',
					url: 'activity/collageDataGrid?activityGoodsId=${id }',
					method: 'post',
					onClickRow: onClickRow
				">
					<thead>
						<tr>
							<th data-options="field:'collageNum',width:150,align:'left',editor:{type:'numberbox'}">拼团人数</th>
							<th data-options="field:'collageTime',width:150,align:'left',editor:{type:'numberbox'}">拼团时长</th>
							<th data-options="field:'collagePrice',width:150,align:'left',editor:{type:'numberbox',options:{precision:2}}">拼团价格</th>
						</tr>
					</thead>
				</table>
				<div id="tb" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
				</div>
			</div>
        </form>
    </div>
</div>