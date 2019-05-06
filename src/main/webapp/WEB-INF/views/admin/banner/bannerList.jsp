<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	var bannerDataGrid;
    $(function () {
    	bannerDataGrid = $('#bannerDataGrid').datagrid({
            url: '${path }/banner/dataGrid',
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
                width: '15%',
                title: '标题',
                align: 'center',
                field: 'title'
            },{
                width: '15%',
                title: '类型',
                align: 'center',
                field: 'type',
                formatter:function(value,row,index){
                	if(value==0){
                		return "首页";
                	}else if(value==1){
                		return "优惠券";
                	}else if(value==2){
                		return "活动专区";
                	}else if(value==3){
                		return "积分商城";
                	}       	
    			}
            },{
                width: '15%',
                title: '图片宽度',
                align: 'center',
                field: 'width'
            },{
                width: '15%',
                title: '图片高度',
                align : 'center',
                field: 'height'
            },{
                width: '15%',
                title: '创建时间',
                align : 'center',
                field: 'createdTime'
            },{
                field : 'action',
                title : '操作',
                width : '25%',
                align : 'center',
                formatter : function(value, row, index) {
                    var str = '';
                    <shiro:hasPermission name="/banner/lookImg">
	                    str += $.formatString('<a href="javascript:void(0)" class="banner-easyui-linkbutton-img" data-options="plain:true,iconCls:\'glyphicon-list icon-blue\'" onclick="lookImgFun(\'{0}\');" >查看图片</a>', row.id);
	                    str += '<br/>';
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/banner/edit">
	                	str += $.formatString('<a href="javascript:void(0)" class="banner-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editBannerFun(\'{0}\');" >编辑</a>', row.id);
	        			str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
        			</shiro:hasPermission>
        			<shiro:hasPermission name="/banner/delete">
        				str += $.formatString('<a href="javascript:void(0)" class="banner-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="bannerDeleteFun(\'{0}\');" >删除</a>', row.id);
        			</shiro:hasPermission>
        			return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.banner-easyui-linkbutton-del').linkbutton({text:'删除'});
                $('.banner-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.banner-easyui-linkbutton-img').linkbutton({text:'查看图片'});
            },
            toolbar : '#bannerToolbar'
        });
    });
    
    //查看banner图片
    function lookImgFun(id) {
        parent.$.modalDialog({
            title : '查看宣传广告图片',
            width : '40%',
            height : '60%',
            href : '${path }/banner/bannerImg?id='+id,
        });
    }
    
    //添加字段内容
    function addBannerFun() {
        parent.$.modalDialog({
            title : '添加',
            width : '50%',
            height : '60%',
            href : '${path }/banner/addPage',
            buttons : [{
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = bannerDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#bannerAddForm');
                    f.submit();
                }
            } ]
        });
    }
    
     //修改字段内容
    function editBannerFun(id) {
        parent.$.modalDialog({
            title : '编辑',
            width : '50%',
            height : '60%',
            href : '${path }/banner/editPage?id='+id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = bannerDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#bannerEditForm');
                    f.submit();
                }
            } ]
        });
    }
    
    /**
	 * 删除
	 */
	function bannerDeleteFun(id) {
		if (id == undefined) {//点击右键菜单才会触发这个
			var rows = bannerDataGrid.datagrid('getSelections');
			id = rows[0].id;
		} else {//点击操作里面的删除图标会触发这个
			bannerDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
		}
		parent.$.messager.confirm('温馨提示', '您是否确定要删除？', function(b) {
			if (b) {
				progressLoad();
				$.post('${path }/banner/delete', {
					id : id
				}, function(result) {
					if (result.success) {
						showMsg(result.msg);
						bannerDataGrid.datagrid('reload');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}

	/**
	 * 清除
	 */
	function cleanBannerFun() {
		$('#tBannerSearchForm input').val('');
		bannerDataGrid.datagrid('load', {});
	}
	/**
	 * 搜索
	 */
	function bannerSearchFun() {
		bannerDataGrid.datagrid('load', $.serializeObject($('#tBannerSearchForm')));
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	 <div data-options="region:'north',border:false" style="height: 35px; overflow: hidden;background-color: #fff;padding: 5px;">
        <form id="tBannerSearchForm">
             <table>
                <tr>
                    <th>关键字：</th>
                    <td><input name="title" placeholder="条件搜索"/></td>
                    <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                    <th> 类型：</th>
                    <td>
                   		<select name="type" id="type">
                   			<option value="">请选择</option>
                   			<option value="0">首页</option>
                   			<option value="1">优惠券</option>
                   			<option value="2">活动专区</option>
                   		</select>
                   	</td>
                    <td>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="bannerSearchFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanBannerFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
     </div>
    <div data-options="region:'center',border:false">
        <table id="bannerDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div id="bigImg" data-options="region:'center',border:false" style="overflow: hidden;background-color: #fff;padding: 5px;">
      <img id="simg"  width="100%" height="100%"></img>
    </div>
</div>
<div id="bannerToolbar" style="display: none;">
	<shiro:hasPermission name="/banner/add">
	  <a onclick="addBannerFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
	</shiro:hasPermission>
</div>
