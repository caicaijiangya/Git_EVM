<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#storeAreaAddForm').form({
            url : '${path }/storeArea/add',
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
                	storeAreaDataGrid.datagrid('reload');
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
             }
             });
    }
  
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="storeAreaAddForm" method="post" >
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">所在省:</td>
                    <td>
                		<select name="province" onchange="selectedProvince();" data-placeholder="请选择" style="width:210px;" id="province" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${provinceList }" var="provinceList">
    							<option value="${provinceList.id }">${provinceList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">所在市:</td>
                    <td>
                		<select name="areaId" data-placeholder="请选择" style="width:210px;" id="city" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${cityList }" var="cityList">
    							<option value="${cityList.id }">${cityList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">排序:</td>
                    <td><input name="seq" type="number" placeholder="请输入排序序号" class="easyui-validatebox span2" data-options="required:true" value=""></td>
                </tr>
            </table>
        </form>
    </div>
</div>