<!DOCTYPE script PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<%@ include file="/commons/basejs.jsp" %>

<form id="echarSearchForm">
    <table>
          <tr>
             <th> 查询格式: </th>	 
               	<td>
               		<select name="format" id="chartsformat">
               			<option value="%Y" selected>年</option>
               			<option value="%Y-%m">月</option>
               			<option value="%Y-%m-%d">日</option>
               		</select>
               	</td>
               	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
            	<th> 开始日期: </th>	 
               	<td>
               		<input type="text" name="startDate"  value=""  required="required" 
               		onclick="let dateCharts=randomDateCharts();WdatePicker({dateFmt:dateCharts,readOnly:true})">
               	</td>
               	<th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
            	<th> 结束日期: </th>	
               	<td>
               		<input type="text" name="endDate"  value=""  required="required" 
               		onclick="let dateCharts=randomDateCharts();WdatePicker({dateFmt:dateCharts,readOnly:true})">
               	</td>
             <td>
                 <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" 
                 onclick="searchAnalyticsCharts();">查询</a>
                 <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" 
                 onclick="cleanAnalyticsCharts();">清空</a>
             </td>
        </tr>
    </table>
</form>

<div id="one" style="height: 100%;"></div>
<div id="two" style="height: 100%"></div>
<div id="three" style="height: 100%"></div>
<div id="four" style="height: 100%"></div>

<script type="text/javascript">

	$(function () {
		loadData();
	})
	
	let oneDom = document.getElementById("one");
	let oneChart = echarts.init(oneDom);
	let optionOne = {
	    title: {
	        text: '访问流量统计'
	    },
	    tooltip: {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['访问次数','打开次数','访问人数','人均停留时长(秒)','次均停留时长(秒)']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    toolbox: {
	        feature: {
	            saveAsImage: {}
	        }
	    },
	    xAxis: {
	        type: 'category',
	        boundaryGap: false,
	        data: []
	    },
	    yAxis: {
	        type: 'value'
	    },
	    series: [
	        {
	            name:'访问次数',
	            type:'line',
	            stack: '总量',
	            data:[0]
	        },
	        {
	            name:'打开次数',
	            type:'line',
	            stack: '总量',
	            data:[1]
	        },
	        {
	            name:'访问人数',
	            type:'line',
	            stack: '总量',
	            data:[2]
	        },
	        {
	            name:'人均停留时长(秒)',
	            type:'line',
	            stack: '总量',
	            data:[3]
	        },
	        {
	            name:'次均停留时长(秒)',
	            type:'line',
	            stack: '总量',
	            data:[4]
	        }
	    ]
	};
	
	var twoDom = document.getElementById("two");
	var twoChart = echarts.init(twoDom);

	let optionTwo = {
	    title: {
	        text: '城市流量排行',
	        subtext: '数据来自微信'
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    legend: {
	        data: []
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    yAxis: {
	        type: 'category',
	        data: []
	    },
	    series: [
	        {
	            name: '流量',
	            type: 'bar',
	            data: []
	        }
	    ]
	};
	
	let threeDom = document.getElementById("three");
	let threeChart = echarts.init(threeDom);

	let optionThree = {
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a} <br/>{b}: {c} ({d}%)"
	    },
	    legend: {
	        orient: 'vertical',
	        x: 'left',
	        data:['直接访问','搜索引擎','分享','其他']
	    },
	    series: [
	        {
	            name:'访问来源',
	            type:'pie',
	            radius: ['50%', '70%'],
	            avoidLabelOverlap: false,
	            label: {
	                normal: {
	                    show: false,
	                    position: 'center'
	                },
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '30',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[
	                {name:'直接访问'},
	                {name:'搜索引擎'},
	                {name:'分享'},
	                {name:'其他'}
	            ]
	        }
	    ]
	};
	
	var fourDom = document.getElementById("four");
	var fourChart = echarts.init(fourDom);
	var optionFour = {
	    color: ['#3398DB'],
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {           
	            type : 'shadow'        
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : [],
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'直接访问',
	            type:'bar',
	            barWidth: '60%',
	            data:[]
	        }
	    ]
	};
	
	function loadData(params) {  
		oneChart.clear();  
		oneChart.showLoading({text: '正在努力的读取数据中...'});  
	    $.get('${path}/analytics/loadOne', params,function (data) { 
	    	let obj = JSON.parse(data);
	    	let i=0;
	    	for (let k of Object.keys(obj)) {
	    		if(k=='refDate'){
	    			optionOne.xAxis.data = obj[k];
	    			continue;
	    		}
	    		optionOne.series[i].data=obj[k];i++;
	    	}
	    	oneChart.setOption(optionOne); 
	    	oneChart.hideLoading();
	    });  
	    
	    twoChart.clear();  
	    twoChart.showLoading({text: '正在努力的读取数据中...'});  
	    $.get('${path}/analytics/loadTwo', params,function (data) { 
	    	let obj = JSON.parse(data);
			optionTwo.legend.data.push(obj.date);
			optionTwo.yAxis.data=obj.cityList;
			optionTwo.series[0].data=obj.numList;
	    	twoChart.setOption(optionTwo); 
	    	twoChart.hideLoading();
	    });  
	    
	    threeChart.clear();  
	    threeChart.showLoading({text: '正在努力的读取数据中...'});  
	    $.get('${path}/analytics/loadThree', params,function (data) { 
	    	let obj = JSON.parse(data);
			optionThree.series[0].data[0].value=obj.session;
			optionThree.series[0].data[1].value=obj.search;
			optionThree.series[0].data[2].value=obj.share;
			optionThree.series[0].data[3].value=obj.other;
			threeChart.setOption(optionThree); 
			threeChart.hideLoading();
	    });  
	    
	    fourChart.clear();  
	    fourChart.showLoading({text: '正在努力的读取数据中...'});  
	    $.get('${path}/analytics/loadFour', params,function (data) { 
	    	let obj = JSON.parse(data);
	    	optionFour.xAxis[0].data=obj.dateList;
	    	optionFour.series[0].data=obj.numList;
			fourChart.setOption(optionFour); 
			fourChart.hideLoading();
	    }); 
	    
	} 
	
	function cleanAnalyticsCharts() {
		$('#echarSearchForm input').val('');
		loadData();
	}
	
	function searchAnalyticsCharts() {
		loadData($.serializeObject($('#echarSearchForm')));
	}

	let date_format = 'yyyy-MM-dd';
	
	function randomDateCharts(){
		return date_format;
	}
	
	$("#chartsformat").change(function(){
		if(this.value == '%Y'){
			date_format = 'yyyy';
		}else if(this.value == '%Y-%m'){
			date_format = 'yyyy-MM';
		}else if(this.value == '%Y-%m-%d'){
			date_format = 'yyyy-MM-dd';
		}
	});
	
</script>

