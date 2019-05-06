var count = 0;
$(function () {
	
	 function loadUploadImgHtml(count){
		 var pics= document.getElementById("addFileImg");  
	     var add=$("<input type=\"file\" name=\"uploadimage"+count+"\" style=\"display: none;\"/><img id=\"show"+count+"\" style=\"height:80px;width:80px;margin-right:10px;\"/>"); 
	     add.appendTo(pics); 
	     
	     $('input[name=uploadimage'+count+']').change(function(){
	  		var objUrl = getObjectURL(this.files[0]);
	      	if (objUrl) 
	      	{
	      		$('#show'+count+'').attr('src', objUrl);
	      	}
		 });
	 };
	 
	 $('#upload').click(function(){
		 count ++;
		 if(count > 9){
			 parent.$.messager.alert('温馨提示', '最多只能上传9张', 'info');
			 return;
		 }
		 loadUploadImgHtml(count);
		 $('input[name=uploadimage'+count+']').click();
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
});