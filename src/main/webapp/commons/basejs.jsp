<%--标签 --%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<title>EVM后台管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="shortcut icon" href="${staticPath }/static/style/images/favicon.ico" />
<%-- [EasyUI] --%>
<link rel="stylesheet" type="text/css" href="${staticPath }/static/js/jquery-easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${staticPath }/static/js/jquery-easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${staticPath }/static/js/jquery-easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/common.css?nocache=<%=new Date().getTime() %>">
<link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/icon.css">
<link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/chosen.css">
<link rel="stylesheet" href="${staticPath}/static/uploadify/uploadify.css" type="text/css" />

<%-- [my97日期时间控件] --%>
<script type="text/javascript" src="${staticPath }/static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${staticPath }/static/js/jquery-easyui/jquery.min.js"></script>
<script type="text/javascript" src="${staticPath }/static/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${staticPath }/static/js/jquery.form.js"></script>

<script type="text/javascript" src="${staticPath }/static/js/jquery-easyui/chosen.jquery.js" ></script>
<script type="text/javascript" src="${staticPath }/static/js/jquery-easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${staticPath }/static/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<%-- [扩展JS] --%>
<script type="text/javascript" src="${staticPath }/static/uploadify/jquery.uploadify.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/js/arrayToTree.js"></script>
<script type="text/javascript" src="${staticPath }/static/js/extJs.js?nocache=<%=new Date().getTime() %>"></script>

<script type="text/javascript" src="${staticPath }/static/js/echarts.min.js"></script>
<script type="text/javascript">
    var basePath = "${staticPath }";
    window.UEDITOR_HOME_URL = "${staticPath }/static/ueditor/";
    window.UEDITOR_SERVER_URL = "${staticPath }/ueditor";
</script>
<script type="text/javascript">
    var basePath = "${staticPath }";
    
    /**
     * @desc TODO(文件上传)
     * @param configuration.params
     *            附加参数
     * @param configuration.fileList
     *            文件列表
     * @param configuration.singlePattern
     *            是否是单文件上传
     * @param configuration.extName
     *            筛选的扩展名,默认允许所有的扩展名上传
     */
    $.fn.simpleFileUpload = function(configuration) {
    	var url = configuration.url;
    	var params = configuration.params;
    	var fileList = configuration.fileList;
    	var singlePattern = configuration.singlePattern == undefined ? true : configuration.singlePattern;
    	var extName = configuration.extName == undefined ? "" : configuration.extName;
    	extName = extName.toLowerCase();
    	$(this).fileupload({
    		url : url,
    		formData : params,
    		autoUpload : true,
    		dataType : 'json',
    		add: function (e, data) {
    			var flag = true;
    			$.each(data.files, function(index, file) {
    				var fileName = file.name;
    				var dotLastIndex = fileName.lastIndexOf(".");
    				if (dotLastIndex > -1 && dotLastIndex + 1 < fileName.length) {
    					var ext = fileName.substring(dotLastIndex + 1);
    					ext = ext.toLowerCase();
    					if (extName != "" && extName.indexOf(ext) == -1) {
    						flag = false;
    					}
    				}
    			});
    			if (flag) {
    				if(configuration.progress && configuration.progress_bar){
    					$("#"+configuration.progress_bar).css('width','0%');
    					$("#"+configuration.progress_bar).text('0%');
    					$("#"+configuration.progress).show();
    				}
    				data.submit();
    			} else {
    				showMessage("文件扩展名错误");
    			}
    	    },
    	    progressall:function(e, data){
    	    	if(configuration.progress_bar){
    	    		var progress = parseInt(data.loaded / data.total * 100, 10);
    		    	$("#"+configuration.progress_bar).css('width',progress+'%');
    		    	$("#"+configuration.progress_bar).text(progress+'%');
    	    	}
    	    },
    		done : function(e, data) {
    			var fileName = data.result.fileName;
    			var fileRelativePath = data.result.fileRelativePath;
    			var fileItem = '<span style="display: block">'
    						      +'<span style="font-size: 12px; color: blue">' + fileName + '</span>&nbsp;&nbsp;';
    			// 如果是文件或视频时显示下载fileRelativePath
    			if(configuration.dir == "file" ||configuration.dir == "media" ){
    				fileItem = fileItem +'<a href="'+ctx+'fileUpload/download?file_path='+fileRelativePath+'" style="font-size: 12px; color: gray;">下载</a>&nbsp;&nbsp;';
    			}
    			// 图片显示预览
    			if(configuration.dir == "image"){
    				fileItem = fileItem +'<a href="javascript:viewImgDg(\'' + fileRelativePath +'\')" style="font-size: 12px; color: gray;">预览</a>&nbsp;&nbsp;';
    			}
    			fileItem = fileItem +'<a href="javascript:void(0)" onclick="deleteFile(this,\'' + fileRelativePath + '\')" style="font-size: 12px; color: red">删除</a>'
    		      +'<input type="hidden" name="'+configuration.name+'" value="' + fileRelativePath + '" />'
    		    +'</span>';
    			if (singlePattern) {
    				$("#" + fileList).empty();
    			} 
    			$("#" + fileList).append(fileItem);
    		}
    	});
    };
</script>