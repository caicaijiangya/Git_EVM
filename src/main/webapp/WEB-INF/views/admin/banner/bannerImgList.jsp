<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var bannerImgDataGrid;
    $(function () {
    	bannerImgDataGrid = $('#bannerImgDataGrid').datagrid({
            url: '${path }/banner/searchBannerImg?id=${id}',
            striped: true,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '1',
                title: 'ID',
                field: 'id',
                hidden:true
            },{
                width: '100%',
                title: '图片',
                align: 'center',
                field: 'filePath',
                formatter:function(value,row,index){
                	var html = '<img src="'+value+'"  width="100px" height="100px"/>';
                	html += '<a href="javascript:void(0)" class="bannerImg-easyui-linkbutton-search" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun('+index+');" >查看大图</ a>';
                	return html;           	
    			}
            }] ],
            onLoadSuccess:function(data){
                $('.bannerImg-easyui-linkbutton-search').linkbutton({text:'查看大图'});
            },
            toolbar : '#bannerImgToolbar'
        });
    });
    
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(index){
		var row = bannerImgDataGrid.datagrid('getRows')[index];
	    var simg =  row.filePath;
	        $('#bigImg').dialog({    
	            title: '预览',    
	            width: 500,    
	            height:500,    
	            resizable:true,    
	            closed: false,    
	            cache: false,    
	            modal: true    
	        });    
	        $("#simg").attr("src",simg);  
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
     </div>
    <div data-options="region:'center',border:false">
        <table id="bannerImgDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
