<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tXyUserIntegralDetailDataGrid;
    $(function() {
        tXyUserIntegralDetailDataGrid = $('#tXyUserIntegralDetailDataGrid').datagrid({
        url : '${path}/wxUserIntegral/detailDataGrid?openId=${openId}',
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
            width : '20%',
            title : '用户昵称',
            align : 'center',
            field : 'nickName'
        },{
            width : '30%',
            title : '积分标题',
            align : 'center',
            field : 'title'
        },{
            width : '15%',
            title : '积分',
            align : 'center',
            field : 'integral'
        },{
            width : '15%',
            title : '类型',
            field : 'type',
            align : 'center',
            formatter:function(value,row,index){
            	if(value == 0){
            		return '签到';
            	}else if(value == 1){
            		return '兑换';
            	}else if(value == 2){
            		return '会员注册';
            	}else if(value == 3){
            		return '个人资料完善';
            	}else if(value == 4){
            		return '消费';
            	}else if(value == 5){
            		return '分享';
            	}else if(value == 6){
            		return '邀请好友';
            	}else if(value == 10){
            		return '系统赠送';
            	}
			}
        },{
            width : '8%',
            title : '状态',
            align : 'center',
            field : 'status',
            formatter:function(value,row,index){
            	if(value == 0){
            		return '正常';
            	}else if(value == 1){
            		return '清零';
            	}else if(value == 2){
            		return '异常';
            	}
			}
        },{
            width : '15%',
            title : '创建日期',
            align : 'center',
            field : 'createdTime'
        },{
            width : '30%',
            title : '备注',
            align : 'center',
            field : 'note'
        } ] ],
        onLoadSuccess:function(data){
        },
        toolbar : '#userIntegralDetailToolbar'
    });
});
</script>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <table id="tXyUserIntegralDetailDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>