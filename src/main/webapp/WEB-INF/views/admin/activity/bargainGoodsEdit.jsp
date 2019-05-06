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
	var images = [];
	//删除轮播图
	function delImg(f,i){
		images[i] = null;
		$(f).parent().remove();
	}
    $(function() {
    	var image0 = '${activityGoodsBargain.image0}';
    	if(image0 != null && image0.length > 0){
    		var result = image0;
    		var i = 0;
			images[i] = result;
			var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this,"+i+")' value='删除' /><li>";
			$("#image"+i+"Desc").html(imgHtml);
    	}
    	var image1 = '${activityGoodsBargain.image1}';
    	if(image1 != null && image1.length > 0){
    		var result = image1;
    		var i = 1;
			images[i] = result;
			var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this,"+i+")' value='删除' /><li>";
			$("#image"+i+"Desc").html(imgHtml);
    	}
    	var image2 = '${activityGoodsBargain.image2}';
    	if(image2 != null && image2.length > 0){
    		var result = image2;
    		var i = 2;
			images[i] = result;
			var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this,"+i+")' value='删除' /><li>";
			$("#image"+i+"Desc").html(imgHtml);
    	}
    	$("#image0")
		.uploadify(
			{
				swf : "${staticPath }/static/uploadify/uploadify.swf",
				uploader : "${path}/fileUpload/upload",
				fileObjName : "uploadFile", // 控制器中参数名称  
				auto : true,
				fileSizeLimit : "5120KB",
				fileTypeExts : "*.jpg;*.gif;*.png;",
				onUploadSuccess : function(file, result, response) {
					if (result) {
						var i = 0;
						images[i] = result;
						var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this,"+i+")' value='删除' /><li>";
						$("#image"+i+"Desc").html(imgHtml);
					}
				}
			});
    	$("#image1")
		.uploadify(
			{
				swf : "${staticPath }/static/uploadify/uploadify.swf",
				uploader : "${path}/fileUpload/upload",
				fileObjName : "uploadFile", // 控制器中参数名称  
				auto : true,
				fileSizeLimit : "5120KB",
				fileTypeExts : "*.jpg;*.gif;*.png;",
				onUploadSuccess : function(file, result, response) {
					if (result) {
						var i = 1;
						images[i] = result;
						var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this,"+i+")' value='删除' /><li>";
						$("#image"+i+"Desc").html(imgHtml);
					}
				}
			});
    	$("#image2")
		.uploadify(
			{
				swf : "${staticPath }/static/uploadify/uploadify.swf",
				uploader : "${path}/fileUpload/upload",
				fileObjName : "uploadFile", // 控制器中参数名称  
				auto : true,
				fileSizeLimit : "5120KB",
				fileTypeExts : "*.jpg;*.gif;*.png;",
				onUploadSuccess : function(file, result, response) {
					if (result) {
						var i = 2;
						images[i] = result;
						var imgHtml = "<li><a href='"+result+"' target='_blank'><img src='"+result+"' width='120' height='120'/></a><input type='button' onclick='delImg(this,"+i+")' value='删除' /><li>";
						$("#image"+i+"Desc").html(imgHtml);
					}
				}
			});
    	
        $('#bargainParameterEditForm').form({
            url : '${path }/activity/editBargainParameter',
            onSubmit : function() {
                progressLoad();
                if(images[0] != null)
                $("#image0Img").val(images[0]);
                if(images[1] != null)
                $("#image1Img").val(images[1]);
                if(images[2] != null)
                $("#image2Img").val(images[2]);
                
                if (endEditing()){
        			var index = $('#dg').datagrid('getRows').length;
        			if(index > 0){
        				var fullNums = "";
        				var goodsIds = "";
        				var goodsNames = "";
        				var goodsNums = "";
        				for(var i = 0; i < index; i++){
        					var row = $('#dg').datagrid('getRows')[i];
        					var fullNum = row['fullNum'];
        					var goodsId = row['goodsId'];
        					var goodsName = row['goodsName'];
        					var goodsNum = row['goodsNum'];
        					if(fullNum != null && fullNum != '' && goodsId != null && goodsId != ''){
        						fullNums = fullNums + fullNum +",";
        						goodsIds = goodsIds + goodsId +",";
        						goodsNames = goodsNames + goodsName+",";
        						goodsNums = goodsNums + goodsNum+",";
        					}
        				}
        				if(fullNums.length > 0 && goodsIds.length > 0){
        					fullNums = fullNums.substring(0,fullNums.length-1);
        					goodsIds = goodsIds.substring(0,goodsIds.length-1);
        					goodsNames = goodsNames.substring(0,goodsNames.length-1);
        					goodsNums = goodsNums.substring(0,goodsNums.length-1);
        				}
        				$("#fullNums").val(fullNums);
        				$("#goodsIds").val(goodsIds);
        				$("#goodsNames").val(goodsNames);
        				$("#goodsNums").val(goodsNums);
        			}
        		}
                
                
                if (endEditing1()){
        			var index = $('#dg1').datagrid('getRows').length;
        			if(index > 0){
        				var prices = "";
        				var useNums = "";
        				for(var i = 0; i < index; i++){
        					var row = $('#dg1').datagrid('getRows')[i];
        					var price = row['price'];
        					var useNum = row['useNum'];
        					if(price != null && price != '' && useNum != null && useNum != ''){
        						prices = prices + price +",";
        						useNums = useNums + useNum +",";
        					}
        				}
        				if(prices.length > 0 && useNums.length > 0){
        					prices = prices.substring(0,prices.length-1);
        					useNums = useNums.substring(0,useNums.length-1);
        				}
        				$("#prices").val(prices);
        				$("#useNums").val(useNums);
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

    $("#type").combobox({
		onChange: function (n,o) {
			if(n == 0){
				  $("#couponId").hide();
				  $("#integral").hide();
				  $("#givingNum").hide();
			  }else if(n == 1){
				  $("#couponId").hide();
				  $("#integral").show();
				  $("#givingNum").show();
			  }else if(n == 2){
				  $("#couponId").show();
				  $("#integral").hide();
				  $("#givingNum").show();
			  }
		}
	});
    var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){return true}
		if ($('#dg').datagrid('validateRow', editIndex)){
			var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'goodsId'});
			var goodsName = $(ed.target).combobox('getText');
			$('#dg').datagrid('getRows')[editIndex]['goodsName'] = goodsName;
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
        <form id="bargainParameterEditForm" method="post">
        <input name="id" type="hidden" value="${activityGoodsBargain.id }"/>
        	<input name="activityGoodsId" type="hidden" value="${id }"/>
        	<input type="hidden" name="image0" id="image0Img" />
        	<input type="hidden" name="image1" id="image1Img">
            <input type="hidden" name="image2" id="image2Img" />
            <input type="hidden" name="fullNums" id="fullNums" />
            <input type="hidden" name="goodsIds" id="goodsIds" />
            <input type="hidden" name="goodsNames" id="goodsNames"/>
            <input type="hidden" name="goodsNums" id="goodsNums"/>
            <input type="hidden" name="prices" id="prices" />
            <input type="hidden" name="useNums" id="useNums" />
            <table class="grid">
            	<tr>
                    <td align="right" bgcolor="#fafafa">助力砍价提示图片:</td>
                    <td colspan="3" >
                    	<input type="file" name="file_upload" id="image0" />
                    	<div id="image0Desc"></div> 
					</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#fafafa">砍价成功提示图片:</td>
                    <td colspan="3" >
                    	<input type="file" name="file_upload" id="image1" />
                    	<div id="image1Desc"></div> 
					</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#fafafa">赠送提示图片:</td>
                    <td colspan="3" >
                    	<input type="file" name="file_upload" id="image2" />
                    	<div id="image2Desc"></div> 
					</td>
                </tr>
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">助力砍价次数:</td>
                    <td colspan="3">
                    	<input name="bargainNum"  type="number" class="easyui-validatebox span2" data-options="required:true" value="${activityGoodsBargain.bargainNum }">
                    	<span>*每个用户单商品允许最大发起助力砍价次数（0不限制）</span>
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">帮砍次数:</td>
                    <td colspan="3">
                    	<input name="helpBargainNum"  type="number" class="easyui-validatebox span2" data-options="required:true" value="${activityGoodsBargain.helpBargainNum }">
                    	<span>*每个用户单商品允许最大帮砍次数（0不限制）</span>
                    </td>
                </tr>
            	<tr>
                    <td align="right" bgcolor="#f8f8f8">优惠单价:</td>
                    <td colspan="3">
                    	<input name="price" type="number" placeholder="请输入活动价格" class="easyui-validatebox span2" data-options="required:true" value="${activityGoodsBargain.price }">
                    	<span>*每次砍价实际优惠价格</span>
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">砍价时长:</td>
                    <td colspan="3">
                    	<input name="time" type="number" placeholder="请输入砍价时长" class="easyui-validatebox span2" data-options="required:true" value="${activityGoodsBargain.time }">
                    	<span>*发起砍价后的限时时长,默认24小时</span>
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">赠送类型:</td>
                    <td colspan="3">
                    	<select id="type" name="type" style="width: 140px; height: 29px;" class="easyui-combobox" data-options="required:true">
	                    	<option value="0" <c:if test="${activityGoodsBargain.type== 0}">selected="selected"</c:if>>不赠送</option>
	                    	<option value="1" <c:if test="${activityGoodsBargain.type== 1}">selected="selected"</c:if>>赠送积分</option>
	                    	<option value="2" <c:if test="${activityGoodsBargain.type== 2}">selected="selected"</c:if>>赠送优惠券</option>
	                    </select>
                    </td>
                </tr>
                <tr id="couponId" style="<c:if test='${activityGoodsBargain.type != 2}'>display: none;</c:if>">
                    <td align="right" bgcolor="#f8f8f8">优惠券:</td>
                    <td colspan="3">
                    	<select name="couponId" style="width: 140px; height: 29px;" class="easyui-combobox">
	                    	<c:forEach items="${couponList }" var="item">
	                    		<option value="${item.id }" <c:if test="${activityGoodsBargain.couponId== item.id}">selected="selected"</c:if>>${item.goodsName }</option>
	                    	</c:forEach>
	                    </select>
                    </td>
                </tr>
                <tr id="integral" style="<c:if test='${activityGoodsBargain.type != 1}'>display: none;</c:if>">
                    <td align="right" bgcolor="#f8f8f8">积分:</td>
                    <td colspan="3"><input name="integral"  type="number" class="easyui-validatebox span2" value="${activityGoodsBargain.integral}"></td>
                </tr>
                <tr id="givingNum" style="<c:if test='${activityGoodsBargain.type== 0}'>display: none;</c:if>">
                    <td align="right" bgcolor="#f8f8f8">赠送次数:</td>
                    <td colspan="3">
                    	<input name="givingNum"  type="number" class="easyui-validatebox span2"  value="${activityGoodsBargain.givingNum}">
                    	<span>*每个用户单商品允许最大赠送次数（0不限制）</span>
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">类型:</td>
                    <td colspan="3">
                    	<input name="bargainType" value="0" type="radio" <c:if test="${activityGoodsBargain.bargainType== 0}">checked="checked"</c:if>>普通砍价
                    	<input name="bargainType" value="1" type="radio" <c:if test="${activityGoodsBargain.bargainType== 1}">checked="checked"</c:if>>阶梯砍价
                    </td>
                </tr>
            </table>
            <div style="padding-left: 20%;" id="bargainType">
	            <table id="dg" class="easyui-datagrid" title="阶梯商品编辑" style="width:80%;height:auto;"
				data-options="
					iconCls: 'icon-edit',
					singleSelect: true,
					toolbar: '#tb',
					url: 'activity/ladderDataGrid?bargainId=${activityGoodsBargain.id }',
					method: 'post',
					onClickRow: onClickRow
				">
					<thead>
						<tr>
							<th data-options="field:'fullNum',width:100,align:'left',editor:{type:'numberbox'}">条件</th>
							<th data-options="field:'goodsId',width:220,align:'left',
								formatter:function(value,row){
									return row.goodsName;
								},
								editor:{
									type:'combogrid',
									options:{
										panelWidth: 500,
										panelHeight: 300,
										required:true,
										idField: 'id',
										textField: 'goodsName',
										url: 'goodsOther/dataGrid',
										method: 'post',
										nowrap: false,
										columns: [[
											{field:'id',title:'id',width:80,hidden:true},
							    			{field:'goodsImage',title:'商品图片',width:80,align:'center',
							    				formatter:function(value,row,index){
							    					return '<img src='+value+' width=80 height=80/>';
							    				}
							    			},
							    			{field:'goodsName',title:'商品名称',width:200,align:'center'},
							    			{field:'specName',title:'规格名称',width:80,align:'center'},
							    			{field:'brandName',title:'品牌',width:80,align:'center'},
							    			{field:'goodsPrice',title:'价格',width:80,align:'center'}
										]],
										fitColumns: true
									}
								}
							
						">商品</th>
						<th data-options="field:'goodsNum',width:80,align:'left',editor:{type:'numberbox'}">数量</th>
						</tr>
					</thead>
				</table>
				<div id="tb" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
				</div>
			</div>
			<div style="padding-left: 20%;" id="priceList">
	            <table id="dg1" class="easyui-datagrid" title="优惠价格配置" style="width:80%;height:auto;"
				data-options="
					iconCls: 'icon-edit',
					singleSelect: true,
					toolbar: '#tb1',
					url: 'activity/priceDataGrid?bargainId=${activityGoodsBargain.id }',
					method: 'post',
					onClickRow: onClickRow1
				">
					<thead>
						<tr>
							<th data-options="field:'price',width:150,align:'left',editor:{type:'numberbox',options:{precision:2}}">价格</th>
							<th data-options="field:'useNum',width:150,align:'left',editor:{type:'numberbox'}">数量</th>
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