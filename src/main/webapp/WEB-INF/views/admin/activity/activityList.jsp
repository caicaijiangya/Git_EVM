<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var activityDataGrid;
    $(function () {
    	$('.chzn-select').combobox();
    	activityDataGrid = $('#activityDataGrid').datagrid({
            url: '${path }/activity/dataGrid',
            striped: true,
            nowrap:false,
            pagination: true,
            rownumbers : true,
            singleSelect: true,
            idField: 'id',
            sortName : 'id',
            sortOrder : 'desc',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '5%',
                title: '序号',
                align: 'center',
                field: 'id',
                hidden:true
            },{
                width: '20%',
                title: '活动标题',
                align : 'center',
                field: 'title'
            },{
                width: '10%',
                title: '活动类型',
                align : 'center',
                field: 'activityType',
                formatter : function(value, row, index) {
                	if(value == 1){
                		return "秒杀";
                	}else if(value==2){
                		return "拼团";
                	}else if(value == 3){
                		return "特价";
                	}else if(value == 4){
                		return "砍价";
                	}else if(value == 5){
                		return "满减";
                	}else if(value == 6){
                		return "满赠";
                	}else if(value == 7){
                		return "打折";
                	}
                }
            },{
                width: '10%',
                title: '活动状态',
                align : 'center',
                field: 'status',
                formatter : function(value, row, index) {
                	if(value == 0){
                		return '<font color="red">待审核</font>';
                	}else if(value==1){
                		return '<font color="blue">已开始</font>';
                	}else{
                		return "已结束";
                	}
                }
            },{
                width: '15%',
                title: '活动开始时间',
                align : 'center',
                field: 'activityStartTime'
            },{
                width: '15%',
                title: '活动结束时间',
                align : 'center',
                field: 'activityEndTime'
            },{
                width: '10%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '20%',
                align : 'center',
                formatter : function(value, row, index) {
                	var str="";
                	var endTime=row.activityEndTime;
                	if(row.status==0){
                		<shiro:hasPermission name="/activity/review">
	                		str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-review" data-options="plain:true,iconCls:\'glyphicon-ok icon-blue\'" onclick="reviewFun(\'{0}\');" >审核</a>',row.id);
                    	</shiro:hasPermission>
                	}else{
                		str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-reviewed" data-options="plain:true,iconCls:\'glyphicon-ok icon-red\'" >已审核</a>');
                	}
                	str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-label" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editActivityLabel(\'{0}\');" >编辑活动标签</a>', row.id);
                	str += '<br/>';
                	if(row.activityType == 5 || row.activityType == 6 || row.activityType == 7){
                		str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-image" data-options="plain:true,iconCls:\'glyphicon-hand-right icon-blue\'" onclick="editActivityImgFun(\'{0}\');" >活动图片</a>', row.id);
                		str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                	}
                	if(row.activityType == 5){
                		str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-full" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editActivityFull(\'{0}\');" >编辑优惠条件</a>', row.id);
                    	str += '<br/>';
                	}
                	if(row.activityType == 7){
                		str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-discount" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editActivityDiscount(\'{0}\');" >编辑折扣条件</a>', row.id);
                    	str += '<br/>';
                	}
                	str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-store" data-options="plain:true,iconCls:\'glyphicon-search icon-blue\'" onclick="searcheditActivityStoreFun(\'{0}\',\'{1}\');" >编辑活动门店</a>',row.id,row.activityType);
                	str += '<br/>';
                	<shiro:hasPermission name="/activity/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editActivityFun(\'{0}\');" >编辑</a>', row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/activity/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="activity-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteActivityFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
            	$('.activity-easyui-linkbutton-image').linkbutton({text:'活动图片'});
            	$('.activity-easyui-linkbutton-label').linkbutton({text:'编辑活动标签'});
            	$('.activity-easyui-linkbutton-full').linkbutton({text:'编辑优惠条件'});
            	$('.activity-easyui-linkbutton-discount').linkbutton({text:'编辑折扣条件'});
            	 $('.activity-easyui-linkbutton-review').linkbutton({text:'审核'});
            	 $('.activity-easyui-linkbutton-reviewed').linkbutton({text:'已审核'});
                $('.activity-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.activity-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.activity-easyui-linkbutton-store').linkbutton({text:'编辑活动门店'});
            },
            toolbar : '#activityToolbar'
        });
    });
    
  //添加
    function addActivityFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '40%',
            height : '60%',
            href : '${path }/activity/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#activityAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //编辑
    function editActivityFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '60%',
            href : '${path }/activity/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#activityEditForm');
                    f.submit();
                }
            } ]
        });
    }
     //编辑活动标签
     function editActivityLabel(id){
    	 parent.$.modalDialog({
             title : '编辑活动标签',
             width : '30%',
             height : '50%',
             href : '${path }/activity/editLabelPage?id='+id,
             buttons : [ {
                 text : '确定',
                 handler : function() {
                     parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                     var f = parent.$.modalDialog.handler.find('#activityLabelEditForm');
                     f.submit();
                 }
             } ]
         });
     }
     
     function editActivityImgFun(id){
    	 parent.$.modalDialog({
             title : '编辑活动图片',
             width : '40%',
             height : '50%',
             href : '${path }/activity/editImgPage?id='+id,
             buttons : [ {
                 text : '确定',
                 handler : function() {
                     parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                     var f = parent.$.modalDialog.handler.find('#activityImgEditForm');
                     f.submit();
                 }
             } ]
         });
     }
   
    /**
	 * 删除
	 */
	function deleteActivityFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = activityDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			activityDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您确定要删除吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/activity/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						activityDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
    
    
	/**
	 * 审核
	 */
	function reviewFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = activityDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			activityDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您确定要审核通过吗？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/activity/review', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						activityDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
	//编辑优惠条件
    function editActivityFull(id){
    	parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '60%',
            href : '${path }/activity/fullPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#fullParameterEditForm');
                    f.submit();
                }
            } ]
        });
    }
	//编辑折扣条件
	function editActivityDiscount(id){
		parent.$.modalDialog({
            title : '编辑',
            width : '40%',
            height : '60%',
            href : '${path }/activity/discountPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = activityDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#discountParameterEditForm');
                    f.submit();
                }
            } ]
        });
	}
	
    /**
	 * 编辑活动门店
	 */
	    function searcheditActivityStoreFun(id,activityType) {
	        var opts = {
	       		title : '活动门店列表',
	       		border : false,
	       		closable : true,
	       		fit : true,
	       	};
	       	var url = '/activity/storeManager?id='+id+'&activityType='+activityType;
	       	if (url && url.indexOf("http") == -1) {
	       		url = '${path }' + url;
	       	}
	       	opts.href = url;
	       	addCloseTab(opts);
	    }
	 
    
	/**
	 * 清除
	 */
	function cleanActivityFun() {
		$('#tActivitySearchForm input').val('');
		activityDataGrid.datagrid('load', {});
	}
	
	/**
	 * 搜索
	 */
	function searchActivityFun() {
		activityDataGrid.datagrid('load', $.serializeObject($('#tActivitySearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 70px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tActivitySearchForm">
            <table>
                <tr>
                    <th>条件搜索：</th>
                    <td><input name="title" placeholder="输入条件进行搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	
                	<th>开始日期：</th>
                    <td>
                    	 <input type="text" class="easyui-validatebox"  id="dateStart"
					 		name="dateStart" placeholder="选择开始时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',onpicked:function(){dateEnd.focus();}})" readonly="readonly"/>
					</td>	     
					<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>              	
                	<th>结束日期：</th>	
                    <td>
				 		<input type="text" class="easyui-validatebox"  id="dateEnd"
				 		name="dateEnd" placeholder="选择结束时间" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'dateStart\')}'})" readonly="readonly"/>
                    </td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <td>
                         <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchActivityFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanActivityFun();">清空</a>
                    </td>
                </tr>
                <tr>
                	<th> 活动类型: </th>	 
                   	<td>
                   		<select name="activityType" id="activityType">
                   			<option value="">请选择</option>
                   			<option value="1">秒杀</option>
                   			<option value="2">拼团</option>
                   			<option value="3">特价</option>
                   			<option value="4">砍价</option>
                   			<option value="5">满减</option>
                   			<option value="6">满赠</option>
                   			<option value="7">打折</option>
                   		</select>
                   	</td>
                   	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                   	<th> 活动状态: </th>	 
                   	<td>
                   		<select name="status" id="status">
                   			<option value="">请选择</option>
                   			<option value="0">待审核</option>
                   			<option value="1">已开始</option>
                   			<option value="2">已结束</option>
                   		</select>
                   	</td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="activityDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="activityToolbar" style="display: none;">
	<shiro:hasPermission name="/activity/add">
	  <a onclick="addActivityFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>