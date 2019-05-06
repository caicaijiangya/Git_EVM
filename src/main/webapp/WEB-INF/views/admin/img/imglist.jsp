<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var imgGrid = $('#imgGrid');
    $(function() {
    	imgGrid.datagrid({
        url : '${path}/img/dataGrid',
        striped : true,
        rownumbers : false,
        nowrap:false,
        pagination : true,
        idField : 'id',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
        	field:'ck',
        	checkbox:true
        },{
            width : '6%',
            title : '序号',
            align : 'center',
            field : 'id'
        },{
            width : '30%',
            title : '图片',
            field : 'imgPath',
            align : 'center',
            formatter:function(value,row,index){
            	var html = '<img src="'+value+'"  width="100px" height="100px"/>';
            	html += '<a href="javascript:void(0)" class="img-easyui-linkbutton-search" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun('+index+');" >查看</ a>';
            	return html;           	
			}     		
        },{
            width : '15%',
            title : '图片类型',
            align : 'center',
            field : 'imgType',
            formatter:function(value,row,index){
            	var detail = '';
            	if(value==0){
            		detail = '巡店陈列图';
            	}else if(value==1){
            		detail = '巡店活动图';
            	}else if(value==2){
            		detail = '巡店人员图';
            	}else if(value==3){
            		detail = '巡店价格图';
            	}else if(value==4){
            		detail = '门店活动图';
            	}else if(value==5){
            		detail = '销量上报拍照图';
            	}else if(value==6){
            		detail = '业务员对新品拍照图';
            	}else if(value==7) {
            		detail = '柜台陈列图';
            	}else if(value==8) {
            		detail = '临促拍照图';
            	}
            	return detail;
			}
        },{
            width : '15%',
            title : '创建日期',
            align : 'center',
            field : 'createdTime'
        },
        //{
        //    width : '15%',
        //    title : '备注',
        //    align : 'center',
        //    field : 'note'
        //},
        {
            field : 'isDel',
            title : '操作',
            width : '10%',
            align : 'center',
            formatter : function(value, row, index) {
                var str = '';            	
    			str += $.formatString('<a href="javascript:void(0)" class="img-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="imgDeleteFun(\'{0}\',\'{1}\');" >删除</a>', row.id, row.name);
                return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.img-easyui-linkbutton-search').linkbutton({text:'查看'});  
        	$('.img-easyui-linkbutton-del').linkbutton({text:'删除'});
        },
        toolbar : '#imgInfoToolbar'
    });
});
 
	/**
	 * 查看大图
	 */			 
	    function bigImgFun(index){
			var row = imgGrid.datagrid('getRows')[index];
	    	var simg =  row.imgPath;
        	$("#simg").attr("src",simg);  
	        $('#bigImg').dialog({    
	            title: '预览',    
	            width: 500,    
	            height:500,    
	            resizable:true,    
	            closed: false,    
	            cache: false,    
	            modal: true    
	        });    
	    }  
	
	    /**
		 * 删除
		 */
		function imgDeleteFun(id) {
			if (id == undefined) {//点击右键菜单才会触发这个
				var rows = imgGrid.datagrid('getSelections');
				id = rows[0].id;
			} else {//点击操作里面的删除图标会触发这个
				imgGrid.datagrid('unselectAll').datagrid('uncheckAll');
			}
			parent.$.messager.confirm('温馨提示', '您是否要删除？', function(b) {
				if (b) {
					progressLoad();
					$.post('${path }/img/delete', {
						id : id
					}, function(result) {
						if (result.success) {
							showMsg(result.msg);
							imgGrid.datagrid('reload');
						}
						progressClose();
					}, 'JSON');
				}
			});
		}
	
	/**
	 * 查询
	 */
	function imgSearchFun() {
		imgGrid.datagrid('load', $.serializeObject($('#imgSearchForm')));
	}
	
	/**
	 * 下载
	 */
	 function downloadFun(){
		var imgType = $("#imgType option:selected").val();
		$.post('${path }/img/download',{imgType:imgType},function(){
		},"json");
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="imgSearchForm">
            <table>
                <tr>                   
                    <th>按类型搜索：</th>            
                    <td>
						<select name="imgType" id="imgType" style="width: 120px; height: 22px;" class="easyui-validatebox" >
							<option value="">全部</option>						
							<option value="0">陈列图</option>
							<option value="1">活动图</option>
							<option value="2">人员图</option>
							<option value="3">价格图</option>
							<option value="4">门店活动图</option>
							<option value="5">销量上报拍照</option>
							<option value="6">业务员对新品拍照</option>
							<option value="7">柜台陈列图</option>
						</select>
					</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="imgSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-save icon-gray',plain:true" onclick="downloadFun();">批量打包下载</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
 
    <div data-options="region:'center',border:false">
        <table id="imgGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="border: 1px dashed #ccc;display: table-cell; vertical-align: middle;text-align: center;">
      <img id="simg"  height="500px"></img>
    </div> 
</div>