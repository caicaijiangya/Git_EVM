<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var couponDataGrid;
    $(function() {
    	couponDataGrid = $('#couponDataGrid').datagrid({
        url : '${path}/coupon/dataGrid',
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
            title : 'ID',
            align : 'center',
            field : 'id',
            hidden:	true
        },{
            width : '10%',
            title : '优惠券类型',
            field : 'type',
            align : 'center',
            formatter : function(value, row, index) {
           	 if(value == 0){
           		return '日常券';
           	 }else{
           		return '活动券';
           	 }
     		}
        },{
            width : '10%',
            title : '优惠券形式',
            field : 'couponType',
            align : 'center',
            formatter : function(value, row, index) {
           	 if(value == 0){
           		return '满减券';
           	 }else if(value == 1){
           		return '打折券';
           	 }
     		}
        },{
            width : '20%',
            title : '优惠券名称',
            align : 'center',
            field : 'title'
        },{
            width : '10%',
            title : '优惠金额',
            align : 'center',
            field : 'moneyName'
        },{
            width : '10%',
            title : '满额条件',
            align : 'center',
            field : 'fullMoney'
        },{
            width : '5%',
            title : '是否隐藏',
            field : 'isShow',
            align : 'center',
            formatter : function(value, row, index) {
           	 if(value == 0){
           		return '否';
           	 }else{
           		return '是';
           	 }
     		}
        },{
            width : '10%',
            title : '发放总量',
            field : 'couponNum',
            align : 'center'
        },{
            width : '10%',
            title : '是否限正价',
            field : 'isOriginal',
            align : 'center',
            formatter : function(value, row, index) {
           	 if(value == 0){
           		return '是';
           	 }else{
           		return '否';
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
            width : '10%',
            title : '有效期',
            field : 'dueTime',
            align : 'center',
            formatter : function(value, row, index) {
            	var dueType = '';
            	if(row.dueType == 0){
            		dueType = '日期';
            	}else if(row.dueType == 1){
            		dueType = '天数';
            	}
              	 return dueType+":"+value;
        		}
        },{
            width : '10%',
            title : '每人每天限领次数',
            field : 'userDayNum',
            align : 'center'
        },{
            width : '10%',
            title : '每人限领次数',
            field : 'userNum',
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
            width : '20%',
            align : 'center',
            formatter : function(value, row, index) {
            	var str = '';
            	
            	if(row.status == 0){
            		str += $.formatString('<a href="javascript:void(0)" class="coupon-easyui-linkbutton-status0" data-options="plain:true,iconCls:\'glyphicon-pencil icon-red\'" onclick="editCouponStatus(\'{0}\',\'{1}\');" >禁用</a>', row.id,1);
            	}else{
            		str += $.formatString('<a href="javascript:void(0)" class="coupon-easyui-linkbutton-status1" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editCouponStatus(\'{0}\',\'{1}\');" >启用</a>', row.id,0);
            	}
            	str += $.formatString('<a href="javascript:void(0)" class="coupon-easyui-linkbutton-detail" data-options="plain:true,iconCls:\'glyphicon-download icon-green\'" onclick="couponDetailDownFun(\'{0}\');" >领取详情</a>', row.id);
            	str += '<br/>';
     			str += $.formatString('<a href="javascript:void(0)" class="coupon-easyui-linkbutton-store" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editCouponStoreFun(\'{0}\');" >限制门店</a>', row.id);
     			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
     			str += $.formatString('<a href="javascript:void(0)" class="coupon-easyui-linkbutton-goods" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editCouponGoodsFun(\'{0}\');" >限制商品</a>', row.id);
            	<shiro:hasPermission name="/coupon/edit">
	            	str += $.formatString('<a href="javascript:void(0)" class="coupon-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editCouponFun(\'{0}\');" >编辑</a>', row.id);
	     			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
     			</shiro:hasPermission>
     			<shiro:hasPermission name="/coupon/delete">
     				str += $.formatString('<a href="javascript:void(0)" class="coupon-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteCouponFun(\'{0}\');" >删除</a>', row.id);
     			</shiro:hasPermission>
     			return str;
            }
        } ] ],
        onLoadSuccess:function(data){
        	$('.coupon-easyui-linkbutton-store').linkbutton({text:'限制门店'});
        	$('.coupon-easyui-linkbutton-goods').linkbutton({text:'限制商品'});
        	$('.coupon-easyui-linkbutton-del').linkbutton({text:'删除'});
            $('.coupon-easyui-linkbutton-edit').linkbutton({text:'编辑'});
            $('.coupon-easyui-linkbutton-status0').linkbutton({text:'禁用'});
            $('.coupon-easyui-linkbutton-status1').linkbutton({text:'启用'});
            $('.coupon-easyui-linkbutton-look').linkbutton({text:'查看大图'});
            $('.coupon-easyui-linkbutton-detail').linkbutton({text:'领取详情'});
        },
        toolbar : '#couponToolbar'
    });
});
    
    /**
	 * 查看大图
	 */			 
 	function bigImgFun(index){
		var row = couponDataGrid.datagrid('getRows')[index];
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
    
    /**
     * 修改状态
     */
    function editCouponStatus(id,status){
    	progressLoad();
		$.post('${path }/coupon/editStatus', {
			id : id,
			status:status
		}, function(result) {
			if (result.success) {
				showMsg(result.msg);
				couponDataGrid.datagrid('reload');
			}
			progressClose();
		}, 'JSON');
    }
    
    //添加内容
    function addCouponFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '60%',
            height : '70%',
            href : '${path }/coupon/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = couponDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#couponAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
  //编辑优惠券门店
    function editCouponStoreFun(id) {
        parent.$.modalDialog({
            title : '限制门店',
            width : '40%',
            height : '60%',
            href : '${path }/coupon/couponStore?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = couponDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#couponStoreEditForm');
                    f.submit();
                }
            } ]
        });
    }
  //编辑优惠券商品
    function editCouponGoodsFun(id) {
        parent.$.modalDialog({
            title : '限制商品',
            width : '40%',
            height : '60%',
            href : '${path }/coupon/couponGoods?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = couponDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#couponGoodsEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改内容
    function editCouponFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '60%',
            height : '70%',
            href : '${path }/coupon/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = couponDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#couponEditForm');
                    f.submit();
                }
            } ]
        });
    }

	/**
	 * 删除
	 */
	function deleteCouponFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = couponDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			couponDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/coupon/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						couponDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	/**
	 * 优惠券领取详情下载
	 */
	function couponDetailDownFun(id){
		 var param ='?id='+id;
		window.location.href = '${path }/excel/couponDetail'+param;
	}
	/**
	 * 清除
	 */
	function cleanCouponFun() {
		$('#couponSearchForm input').val('');
		couponDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function couponSearchFun() {
		couponDataGrid.datagrid('load', $.serializeObject($('#couponSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 105px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="couponSearchForm">
            <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="title" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>优惠券类型：</th>
                    <td>
                    	<select name="type" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<option value="">全部</option>
                    		<option value="0">日常券</option>
                    		<option value="1">活动券</option>
                    	</select>
                    </td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>优惠券形式：</th>
                    <td>
                    	<select name="couponType" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<option value="">全部</option>
                    		<option value="0">满减券</option>
                    		<option value="1">折扣券</option>
                    	</select>
                    </td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>优惠金额：</th>
                    <td><input type="number" name="money" placeholder=""/></td>
                    
                </tr>
                <tr>
                    <th>开始时间：</th>
                    <td><input type="text" name="startTime" value=""  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>结束时间：</th>
                    <td><input type="text" name="endTime" value=""  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>是否限正价：</th>
                    <td>
                    	<select name="isOriginal" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<option value="">全部</option>
                    		<option value="0">限正价</option>
                    		<option value="1">不限正价</option>
                    	</select>
                    </td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>满额条件：</th>
                    <td><input type="number" name="fullMoney" placeholder=""/></td>
                </tr>
                <tr>
                	<th></th>
                	<td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="couponSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanCouponFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="couponDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
<div id="couponToolbar" style="display: none;">
	<shiro:hasPermission name="/coupon/add">
  		<a onclick="addCouponFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>