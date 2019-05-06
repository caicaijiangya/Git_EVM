<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#activityLabelEditForm').form({
            url : '${path }/activity/editLabel',
            onSubmit : function() {
                progressLoad();
                if (endEditing()){
        			var index = $('#dg').datagrid('getRows').length;
        			if(index > 0){
        				var names = "";
        				for(var i = 0; i < index; i++){
        					var row = $('#dg').datagrid('getRows')[i];
        					var name = row['name'];
        					if(name != null && name != ''){
        						names = names + name +",";
        					}
        				}
        				if(names.length > 0){
        					names = names.substring(0,names.length-1);
        				}
        				$("#names").val(names);
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
                }
                showMsg(result.msg);
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
        <form id="activityLabelEditForm" method="post">
        	<input name="id" type="hidden" value="${id }"/>
        	<input type="hidden" name="names" id="names" />
            <table id="dg" class="easyui-datagrid" title="活动标签编辑" style="width:100%;height:auto;"
				data-options="
					iconCls: 'icon-edit',
					singleSelect: true,
					toolbar: '#tb',
					url: 'activity/labelDataGrid?id=${id }',
					method: 'post',
					onClickRow: onClickRow
				">
					<thead>
						<tr>
							<th data-options="field:'name',width:350,align:'center',editor:{type:'validatebox'}">名称</th>
						</tr>
					</thead>
				</table>
				<div id="tb" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
				</div>
        </form>
    </div>
</div>