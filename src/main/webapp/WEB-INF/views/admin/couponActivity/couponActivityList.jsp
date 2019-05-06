<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var couponActivityDataGrid;
    $(function() {
    	couponActivityDataGrid = $('#couponActivityDataGrid').datagrid({
        url : '${path}/couponActivity/dataGrid',
        striped : true,
        rownumbers : true,
        nowrap:false,
        pagination : true,
        singleSelect : true,
        idField : 'id',
        sortName : 'id',
        sortOrder : 'desc',
        pageSize : 20,
        pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
        columns : [ [ {
            width : '5%',
            title : '序号',
            align : 'center',
            field : 'id',
            hidden:true
        },{
            width : '20%',
            title : '活动名称',
            align : 'center',
            field : 'title'
        },{
            width : '20%',
            title : '优惠券名称',
            align : 'center',
            field : 'couponName'
        },{
            width: '15%',
            title: '活动内容',
            align: 'center',
            field: 'likeUrl',
            formatter:function(value,row,index){
            	if(row.type == 0){
            		if(value==null){
                		var html = $.formatString('<a href="javascript:void(0)" class="couponActivity-easyui-linkbutton-qrcode" data-options="plain:true,iconCls:\'glyphicon-repeat icon-blue\'" onclick="createLikeUrl(\'{0}\',\'{1}\',\'{2}\');" >生成二维码</ a>', row.id,row.couponId,0);
                		return html;
                	}else{
                		var html = '<img src="'+value+'"  width="70px" height="70px"/>';
                    	html += $.formatString('<a href="javascript:void(0)" class="couponActivity-easyui-linkbutton-big" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="bigImgFun(\'{0}\');" >查看大图</ a>', row.likeUrl);
                    	return html; 
                	}
            	} else if(row.type==1){
            		if(value==null){
                		var html = $.formatString('<a href="javascript:void(0)" class="couponActivity-easyui-linkbutton-like" data-options="plain:true,iconCls:\'glyphicon-repeat icon-blue\'" onclick="createLikeUrl(\'{0}\',\'{1}\',\'{2}\');" >生成链接</ a>', row.id,row.couponId,1);
                		return html;
                	}else{
                    	return value; 
                	}
            	} else if(row.type==2){
            		return "新用户注册";
            	}
			}
        },{
            width : '10%',
            title : '每人每天限领次数',
            align : 'center',
            field : 'userDayNum'
        },{
            width : '10%',
            title : '每人限领次数',
            align : 'center',
            field : 'userNum'
        },{
            width : '10%',
            title : '发放总量',
            field : 'couponNum',
            align : 'center',
            formatter : function(value, row, index) {
           	 if(row.isCouponNum == 0){
           		return '不限量';
           	 }else{
           		return value;
           	 }
     		}
        },{
            width : '10%',
            title : '类型',
            field : 'type',
            align : 'center',
            formatter : function(value, row, index) {
           	 if(value == 0){
           		return '扫码领券';
           	 }else if(value == 1){
           		return '券链接';
           	 }else if(value == 2){
           		return '新用户注册';
           	 }
     		}
        },{
            width : '10%',
            title : '开始时间',
            align : 'center',
            field : 'startTime'
        },{
            width : '10%',
            title : '结束时间',
            field : 'endTime',
            align : 'center'
        },{
            width : '15%',
            title : '创建时间',
            field : 'createdTime',
            align : 'center'
        },{
            width : '5%',
            title : '状态',
            field : 'status',
            align : 'center',
            formatter : function(value, row, index) {
            	if(value==0){
            		return '<font color="blue">正常</font>'
            	}else{
            		return '<font color="red">禁用</font>';
            	}
     		}
        },{
            field : 'action',
            title : '操作',
            width : '15%',
            align : 'center',
            formatter : function(value, row, index) {
            	var str = '';
            	if(row.status == 0){
            		str += $.formatString('<a href="javascript:void(0)" class="couponActivity-easyui-linkbutton-status0" data-options="plain:true,iconCls:\'glyphicon-pencil icon-red\'" onclick="editCouponActivityStatus(\'{0}\',\'{1}\');" >禁用</a>', row.id,1);
            	}else{
            		str += $.formatString('<a href="javascript:void(0)" class="couponActivity-easyui-linkbutton-status1" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editCouponActivityStatus(\'{0}\',\'{1}\');" >启用</a>', row.id,0);
            	}
            	str += $.formatString('<a href="javascript:void(0)" class="couponActivity-easyui-linkbutton-detail" data-options="plain:true,iconCls:\'glyphicon-download icon-green\'" onclick="couponActivityDownFun(\'{0}\',\'{1}\');" >领取详情</a>', row.id,row.type);
            	str += '<br/>';
            	str += $.formatString('<a href="javascript:void(0)" class="couponActivity-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editCouponActivityFun(\'{0}\');" >编辑</a>', row.id);
     			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
     			str += $.formatString('<a href="javascript:void(0)" class="couponActivity-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteCouponActivityFun(\'{0}\');" >删除</a>', row.id);
     			return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.couponActivity-easyui-linkbutton-del').linkbutton({text:'删除'});
            $('.couponActivity-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.couponActivity-easyui-linkbutton-big').linkbutton({text:'查看大图'});
            $('.couponActivity-easyui-linkbutton-qrcode').linkbutton({text:'生成二维码'});
            $('.couponActivity-easyui-linkbutton-like').linkbutton({text:'生成链接'});
            $('.couponActivity-easyui-linkbutton-status0').linkbutton({text:'禁用'});
            $('.couponActivity-easyui-linkbutton-status1').linkbutton({text:'启用'});
            $('.couponActivity-easyui-linkbutton-detail').linkbutton({text:'领取详情'});
        },
        toolbar : '#couponActivityToolbar'
    });
});
    
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(simg){
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
    
 	/**
	 * 生成链接
	 */
	function createLikeUrl(id,couponId,type) {
		progressLoad();
		$.post('${path }/couponActivity/likeUrl', {
			id : id,
			couponId : couponId,
			type : type
		}, function(result) {
			if (result.success) {
				showMsg(result.msg);
				couponActivityDataGrid.datagrid('reload');
			}
			progressClose();
		}, 'JSON');
	}
 	
	/**
     * 修改状态
     */
    function editCouponActivityStatus(id,status){
    	progressLoad();
		$.post('${path }/couponActivity/editStatus', {
			id : id,
			status:status
		}, function(result) {
			if (result.success) {
				showMsg(result.msg);
				couponActivityDataGrid.datagrid('reload');
			}
			progressClose();
		}, 'JSON');
    }
    
    //添加内容
    function addCouponActivityFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '60%',
            href : '${path }/couponActivity/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = couponActivityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#couponActivityAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改内容
    function editCouponActivityFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '60%',
            height : '70%',
            href : '${path }/couponActivity/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = couponActivityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#couponActivityEditForm');
                    f.submit();
                }
            } ]
        });
    }

	/**
	 * 删除
	 */
	function deleteCouponActivityFun(id) {
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/couponActivity/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						couponActivityDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	/**
	 * 优惠券活动下载
	 */
	function couponActivityDownFun(id,type){
		 var param ='?sourceId='+id+'&sourceType='+type;
		window.location.href = '${path }/excel/couponActivity'+param;
	}
	/**
	 * 清除
	 */
	function cleanCouponActivityFun() {
		$('#couponActivitySearchForm input').val('');
		couponActivityDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function couponActivitySearchFun() {
		couponActivityDataGrid.datagrid('load', $.serializeObject($('#couponActivitySearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="couponActivitySearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="title" placeholder="条件搜索"/></td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="couponActivitySearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanCouponActivityFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="couponActivityDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
<div id="couponActivityToolbar" style="display: none;">
	<a onclick="addCouponActivityFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
</div>