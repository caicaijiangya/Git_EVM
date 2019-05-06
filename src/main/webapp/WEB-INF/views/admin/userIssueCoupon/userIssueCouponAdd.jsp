<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var userIssueCouponAddDataGrid;
    $(function() {
    	userIssueCouponAddDataGrid = $('#userIssueCouponAddDataGrid').datagrid({
        url : '${path}/userIssueCoupon/userDataGrid',
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
            width : '60',
            title : 'ID',
            align : 'center',
            field : 'id',
            hidden:	true
        },{
            width : '15%',
            title : '门店名称',
            align : 'center',
            field : 'storeName'
        },{
            width : '8%',
            title : '编号',
            align : 'center',
            field : 'code'
        },{
        	field:'headImgUrl',
        	title:'头像',
        	width:'8%',
        	align:'center',
        	formatter:function(value,row,index){
        		if(value!=null && value!='' && value !='/static/images/headImgUrl.png'){
        			return '<img style="border-radius:50%" width="50px;" height="50px;" src='+value+'>';
        		}else{
        			return '<font color="blue">未授权</font>';
        		}
			}},{
            width : '10%',
            title : '微信昵称',
            field : 'nickName',
            align : 'center'
        },{
            width : '10%',
            title : '姓名',
            field : 'userName',
            align : 'center'
        },{
            width : '10%',
            title : '手机号码',
            field : 'mobileNo',
            align : 'center',
            formatter:function(value,row,index){
            	return value;
			}
        },{
            width : '5%',
            title : '性别',
            field : 'sex',
            align : 'center',
            formatter:function(value,row,index){
            	switch (value) {
                case 0:
                    return '保密';
                    break;
                case 1:
                    return '男';
                    break;
                case 2:
                    return '女';
                    break;
                default:
                    return '无';
                	break;
                }
			}
        },{
            width : '10%',
            title : '用户类型',
            field : 'userType',
            align : 'center',
            formatter : function(value, row, index) {
                switch (value) {
                case 1:
                    return '店主';
                    break;
                case 2:
                    return '店长';
                    break;
                case 3:
                    return '店员';
                    break;
                case 4:
                    return '消费者';
                    break;    
                default:
                    return '未认证';
                	break;
                }
            }
        },
        {
            width : '15%',
            title : '区域位置',
            field : 'country',
            align : 'center'
        }, {
            width : '15%',
            title : '加入日期',
            field : 'createdTime',
            align : 'center'
        } ] ],
        onLoadSuccess:function(data){
        },
        toolbar : '#userIssueCouponAddToolbar'
    });
    	
    	
   	$("#couponId").combogrid({
   		panelWidth: 400,
   		panelHeight: 300,
   		idField: 'id',
   		textField:	'title',
   		url: 'coupon/combogridDataGrid',
   		method: 'post',
   		columns: [[
   			{field:'id',title:'id',width:80,hidden:true},
   			{field:'title',title:'优惠券标题',width:200,align:'center'},
   			{field:'money',title:'优惠金额',width:60,align:'center'},
   			{field:'fullMoney',title:'使用门槛',width:60,align:'center'},
   			{field:'couponType',title:'优惠券类型',width:70,align:'center',
   				formatter : function(value, row, index) {
   	           	 if(value == 0){
   	            		return '满减';
   	            	 }else if(value == 1){
   	            		return '打折';
   	            	 }
   	      		}},
   			{field:'type',title:'分类',width:80,align:'center',
   				formatter : function(value, row, index) {
   		           	 if(value == 0){
   		            		return '普通优惠券';
   		            	 }else if(value == 1){
   		            		return '活动优惠券';
   		            	 }
   		      		}}
   		]],
   		fitColumns: true
   	});
});
    /**
	 * 提交人工发券
	 */
    function submitUserIssueCouponAddFun(){
    	var title = $('#uiaddTitle').val();
    	var storeId = $('#uiaddStoreId').combobox('getValue');
    	var type = $('#uiaddType').combobox('getValue');
    	var userNum = $('#userNum').val();
    	var couponId = $('#couponId').combobox('getValue');
    	if(userNum == null || userNum.length == 0){
    		$.messager.alert('提示','请输入每人发放券数量');
    		return;
    	}
    	if(couponId == null || couponId.length == 0){
    		$.messager.alert('提示','请选择要发放的优惠券');
    		return;
    	}
    	parent.$.messager.confirm('温馨提示', '您确定要发放优惠券吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/userIssueCoupon/submit', {
					title : title,
					storeId : storeId,
					type:type,
					userNum:userNum,
					couponId:couponId
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						userIssueCouponAddDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
    }
	$('#userIssueCouponAddSearchForm').submit(function() {
		progressLoad();
		$(this).ajaxSubmit({
			type: 'post',
			url:'${path }/excel/issueCoupon',
			success:function(result){
				result = JSON.parse(result);
				showMsg(result.msg);
				userIssueCouponAddDataGrid.datagrid('reload');
				progressClose();
			}
		}); 
		return false; //阻止表单默认提交 
	});
	$("#userIssueCouponExcel").change(function(){
		var userNum = $('#userNum').val();
    	var couponId = $('#couponId').combobox('getValue');
    	if(userNum == null || userNum.length == 0){
    		$.messager.alert('提示','请输入每人发放券数量');
    		return;
    	}
    	if(couponId == null || couponId.length == 0){
    		$.messager.alert('提示','请选择要发放的优惠券');
    		return;
    	}
		$('#userIssueCouponAddSearchForm').submit();
	});

	/**
	 * 清除
	 */
	function cleanUserIssueCouponAddFun() {
		$('#userIssueCouponAddSearchForm input').val('');
		userIssueCouponAddDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function userIssueCouponAddSearchFun() {
		userIssueCouponAddDataGrid.datagrid('load', $.serializeObject($('#userIssueCouponAddSearchForm')));
	}
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 70px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="userIssueCouponAddSearchForm">
        	<input type="file" name="userIssueCouponExcel" id="userIssueCouponExcel" style="display: none;"/>
            <table>
                <tr>
                    <th>姓名：</th>
                    <td><input id="uiaddTitle" name="title" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>门店：</th>
                    <td>
                    	<select id="uiaddStoreId" name="storeId" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<option value="">全部</option>
                    		<c:forEach items="${storeList }" var="item">
	                    		<option value="${item.id }">${item.storeName }</option>
	                    	</c:forEach>
                    	</select>
                    </td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>类型：</th>
                    <td>
                    	<select id="uiaddType" name="type" style="width: 140px; height: 29px;" class="easyui-combobox">
                    		<option value="">全部</option>
                    		<option value="0">普通用户（未授权）</option>
                    		<option value="1">粉丝（已授权）</option>
                    		<option value="2">会员（完善资料）</option>
                    	</select>
                    </td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="userIssueCouponAddSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanUserIssueCouponAddFun();">清空</a>
                    </td>
                </tr>
                <tr>
                    <th>每人发放数量：</th>
                    <td><input type="number" id="userNum" name="userNum" placeholder="请输入"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th>选择优惠券：</th>
                    <td>
                    	<select name="couponId" id="couponId" class="easyui-combogrid" style="width: 140px; height: 29px;" ></select>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="userIssueCouponAddDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="userIssueCouponAddToolbar" style="display: none;">
	<a onclick="submitUserIssueCouponAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">提交</a>
	<a onclick="$('#userIssueCouponExcel').click();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-upload icon-green'">导入</a>
</div>