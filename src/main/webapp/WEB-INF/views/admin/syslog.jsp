<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function () {
        $('#sysLogDataGrid').datagrid({
            url: '${path }/sysLog/dataGrid',
            striped: true,
            nowrap:false,
            pagination: true,
            singleSelect: true,
            idField: 'id',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            columns: [[{
                width: '100',
                title: '登录名',
                field: 'loginName',
                align: 'center',
                sortable: true
            }, {
                width: '100',
                title: '用户名',
                align: 'center',
                field: 'roleName'
            }, {
                width: '140',
                title: 'IP地址',
                align: 'center',
                field: 'clientIp',
                sortable: true
            }, {
                width: '600',
                title: '内容',
                field: 'optContent'
            }, {
                width: '140',
                title: '创建时间',
                align: 'center',
                field: 'createTime'
            }]]
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false">
        <table id="sysLogDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>