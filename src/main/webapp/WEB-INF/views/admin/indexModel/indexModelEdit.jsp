<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	var modelImg = '${indexModel.modelImg}';
    	if(modelImg == '' || modelImg == null){
    		$('#modelImg').show();
    		$("#previewImg,#replaceImg,#deleteImg").hide();
    	} else {
    		$('#modelImg').hide();
    		$("#previewImg,#replaceImg,#deleteImg").show();
    	}
        $('#indexModelEditForm').form({
            url : '${path }/indexModel/edit',
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
                	indexModelDataGrid.datagrid('reload');
                    parent.$.modalDialog.handler.dialog('close');
                    showMsg(result.msg);
                } else {
                	showMsg(result.msg);
                }
            }
        });
    });
    
  //上传模块图片
    $('#modelImg').change(function(){
    		var objUrl = getObjectURL(this.files[0]);
      	if (objUrl) 
      	{
         $("#previewImg").attr("src", objUrl);
         $("#previewImg,#replaceImg,#deleteImg").show();
         $("#modelImg").hide();
      	}
    });

    //更换
    $('#replaceImg').click(function(){
    	  $("#modelImg").click();
    });
    //删除
    $('#deleteImg').click(function(){
    	  	$("#modelImg").val('');
        $("#modelImg").show();
        $("#previewImg,#replaceImg,#deleteImg").hide();
    });

    //获取本地文本url预览
    function getObjectURL(file) 
    {
      var url = null ;
      if (window.createObjectURL!=undefined) 
      { // basic
        url = window.createObjectURL(file) ;
      }
      else if (window.URL!=undefined) 
      {
        // mozilla(firefox)
        url = window.URL.createObjectURL(file) ;
      } 
      else if (window.webkitURL!=undefined) {
        // webkit or chrome
        url = window.webkitURL.createObjectURL(file) ;
      }
      return url ;
    }
    //校验输入数字并保留2位小数
    function clearNoNum(obj){ 
    obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符  
    obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的  
    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$","."); 
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数  
    if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额 
        obj.value= parseFloat(obj.value); 
    } 
    } 
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="indexModelEditForm" method="post" enctype="multipart/form-data">
            <table class="grid">
                <tr>
                    <td align="right" bgcolor="#f8f8f8">模块图片</td>
                    <td colspan="3">
                    	<input name="id" type="hidden" value="${indexModel.id }"/>
                    	<input id="modelImg" name="uploadImage" type="file">
                    	<img src="${indexModel.modelImg }" id="previewImg" style="height:90px;display:none;"/>
	                    <span id="replaceImg" style="display:none;color:blue;cursor:pointer;">更换</span>
	                    <span id="deleteImg" style="display:none;color:red;cursor:pointer;">删除</span>
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">模块名称</td>
                    <td><input name="modelName" type="text" placeholder="请输入模块名称" class="easyui-validatebox span2" data-options="required:true" value="${indexModel.modelName }"></td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">模块跳转地址</td>
                    <td><input name="pageUrl" type="text" placeholder="请输入模块跳转地址" class="easyui-validatebox span2" value="${indexModel.pageUrl }"></td>
                </tr>
                <tr>
                	<td align="right" bgcolor="#f8f8f8">用户类型</td>
                    <td>
                    	<input name="type" value="0" type="radio" ${indexModel.type==0?'checked':''}>消费者
                    	<input name="type" value="1" type="radio" ${indexModel.type==1?'checked':''}>店面工作人员
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#f8f8f8">模块排序</td>
                    <td><input name="seq" type="number" placeholder="请输入模块排序" class="easyui-validatebox span2" data-options="required:true" value="${indexModel.seq }"></td>
                </tr>
            </table>
        </form>
    </div>
</div>