<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#storeEditForm').form({
            url : '${path }/store/edit',
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
                	storeDataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                    showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
    });
  //选省份时触发onchange传参
    function selectedProvince() {
    	var provinceId= $('#province').val();
    	 $.ajax({  
             type : "post",  
             url : "${path }/store/queryCityList", 
             data : {  
                 "provinceId":provinceId
             },
             success:function(result){
            	 var data = JSON.parse(result);
            	 $("#city").empty();
            	 $.each(data,function(i){ 
            		$("<option value='"+data[i].id+"'>"+data[i].name+"</option>").appendTo("#city"); 
            	 }); 
            	 $("#city").trigger("chosen:updated")
            	 selectedCity($("city").val());
             }
             });
    }
  
  //选市时触发onchange传参
    function selectedCity() {
    	var cityId= $('#city').val();
    	 $.ajax({  
             type : "post",  
             url : "${path }/store/queryAreaList", 
             data : {  
                 "cityId":cityId
             },
             success:function(result){
            	 var data = JSON.parse(result);
            	 $("#area").empty();
            	 $.each(data,function(i){ 
            		$("<option value='"+data[i].id+"'>"+data[i].name+"</option>").appendTo("#area"); 
            	 }); 
            	 $("#area").trigger("chosen:updated")
             }
             });
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="storeEditForm" method="post">
        	<input name='id' type="hidden" value='${store.id }'/>
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">门店编码</td>
                    <td><input name="storeNo" type="text" placeholder="请输入门店编码" class="easyui-validatebox span2" data-options="required:true" value="${store.storeNo }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">门店名称</td>
                    <td><input name="storeName" type="text" placeholder="请输入门店名称" class="easyui-validatebox span2" value="${store.storeName }" data-options="required:true"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">门店位置-经度</td>
                    <td><input name="longitude" type="number" placeholder="请输入门店位置经度" class="easyui-validatebox span2" data-options="required:true" value="${store.longitude }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">门店位置-纬度</td>
                    <td><input name="latitude" type="number" placeholder="请输入门店位置纬度" class="easyui-validatebox span2" data-options="required:true" value="${store.latitude }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">门店电话</td>
                    <td><input name="mobileNo" type="text" placeholder="请输入门店联系电话" class="easyui-validatebox span2" value="${store.mobileNo }" data-options="required:true"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">所在省:</td>
                    <td>
                		<select name="province" onchange="selectedProvince();" data-placeholder="请选择" style="width:210px;" id="province" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${provinceList }" var="provinceList">
    							<option value="${provinceList.id }" <c:if test='${provinceList.id==store.province }'>selected="selected"</c:if>>${provinceList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">所在市:</td>
                    <td>
                		<select name="city" onchange="selectedCity();" data-placeholder="请选择" style="width:210px;" id="city" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${cityList }" var="cityList">
    							<option value="${cityList.id }" <c:if test='${cityList.id==store.city }'>selected="selected"</c:if>>${cityList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">所在区:</td>
                    <td>
                		<select name="area"  data-placeholder="请选择" style="width:210px;" id="area" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${areaList }" var="areaList">
    							<option value="${areaList.id }" <c:if test='${areaList.id==store.area }'>selected="selected"</c:if>>${areaList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">门店详细地址</td>
                    <td colspan="3"><textarea name="addressDetail" rows="4" style="width:95%;">${store.addressDetail }</textarea></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">打印机设备号:</td>
                    <td><input name="deviceId" type="text" placeholder="请输入打印机设备号" class="easyui-validatebox span2" value="${store.deviceId }" ></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">打印机设备密钥:</td>
                    <td><input name="deviceSecret" type="text" placeholder="请输入打印机密钥" class="easyui-validatebox span2" value="${store.deviceSecret }" ></td>
                </tr>
            </table>
        </form>
    </div>
</div>