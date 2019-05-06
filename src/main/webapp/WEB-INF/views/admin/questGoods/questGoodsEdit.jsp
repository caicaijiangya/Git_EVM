<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#questGoodsEditForm').form({
            url : '${path }/questGoods/edit',
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
                	questGoodsDataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                    showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
    });
    
  //选商品分类时触发onchange传参
    function selectedGoods() {
    	var classifyId= $('#classifyId').val();
    	 $.ajax({  
             type : "post",  
             url : "${path }/questGoods/queryGoodsByClassifyId", 
             data : {  
                 "classifyId":classifyId
             },
             success:function(result){
            	 var data = JSON.parse(result);
            	 $("#goodsId").empty();
            	 $.each(data,function(i){ 
            		$("<option value='"+data[i].id+"'>"+data[i].goodsName+"</option>").appendTo("#goodsId"); 
            	 }); 
            	 $("#goodsId").trigger("chosen:updated")
             }
             });
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="questGoodsEditForm" method="post" >
        	<input name="id" type="hidden" value="${questGoods.id }"/>
            <table class="grid">
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品分类名称:</td>
                    <td>
                		<select name="classifyId" onchange="selectedGoods();" data-placeholder="请选择" style="width:210px;" id="classifyId" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${classifyList }" var="classifyList">
    							<option value="${classifyList.id }" <c:if test="${classifyList.id==questGoods.classifyId }">selected="selected"</c:if>>${classifyList.name }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">商品名称:</td>
                    <td>
                		<select name="goodsId"  data-placeholder="请选择" style="width:210px;" id="goodsId" class="select"> 
    						<option value="">请选择</option>
    						<c:forEach items="${goodsList }" var="goodsList">
    							<option value="${goodsList.id }" <c:if test="${goodsList.id==questGoods.goodsId }">selected="selected"</c:if>>${goodsList.goodsName }</option>
    						</c:forEach>
						</select>
                	</td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">固定组合</td>
                    <td>
                    	<input name="nail" value="0" type="radio" ${questGoods.nail==0?'checked':'' }>固定
                    	<input name="nail" value="1" type="radio" ${questGoods.nail==1?'checked':'' }>周期一
                    	<input name="nail" value="2" type="radio" ${questGoods.nail==2?'checked':'' }>周期二
                    	<input name="nail" value="3" type="radio" ${questGoods.nail==3?'checked':'' }>基础商品
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">排序</td>
                    <td><input name="seq" value="${questGoods.seq }" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:true"></td>
                </tr>
            </table>
        </form>
    </div>
</div>