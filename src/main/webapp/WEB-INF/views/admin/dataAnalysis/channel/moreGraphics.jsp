<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	//基于准备好的dom，初始化echarts实例
	var myChartMore = echarts.init(document.getElementById('moreMain'));
	var roundChartMore = echarts.init(document.getElementById('moreRound'));
	var jsonData = eval('(${jsonData})');
	var rateList = eval('(${jsonRate})');
	var categoryData = [];
	var storeName = {};
	var lineData = [];
	var series = [];
	var seriesData = [];
	var magicType = [];
	var totleMax = 10;
	var fansMax = 10;
	if(rateList != null && rateList.length > 0){
		for(var i=0;i<rateList.length;i++){
			seriesData[i] = {value:rateList[i].balancesRate, name:rateList[i].storeName};
		}
	}
	if(jsonData != null && jsonData.length > 0){
		var fans = 0;
		var balances = 0;
		for(var i=0;i<jsonData.length;i++) {
			if(fans < jsonData[i].fans){
				fans = jsonData[i].fans;
			}
			lineData[i] = jsonData[i].fans;
			categoryData[i] = jsonData[i].dateTime;
			var storeIndex = [];
			for(var json in jsonData[i]){
				if(json.indexOf('storeName') >= 0){
					var index = json.substring(9,json.length);
					index = Number(index);
					storeIndex[storeIndex.length] = index;
				}
			}
			sort(storeIndex);
			for(var n=0;n<storeIndex.length;n++){
				var storeName_ = jsonData[i]['storeName'+storeIndex[n]];
				var balances_ = jsonData[i]['balances'+storeIndex[n]];
				if(balances < balances_){
					balances = balances_;
				}
				if(storeName[storeName_] == null){
					storeName[storeName_] = [];
				}
				storeName[storeName_][i] = balances_;
			}
			
		}
		
		for(var i=0;i<String(fans).length-1;i++){
			fansMax += "0";
		}
		for(var i=0;i<String(parseInt(balances)).length-1;i++){
			totleMax += "0";
		}
		magicType[magicType.length] = 'line';
		for(var key in storeName){
			magicType[magicType.length] = 'bar';
			series[series.length] = { name:key, type:'bar', data:storeName[key] };
		}
		series[series.length] = {
            name:'粉丝量',
            type:'line',
            yAxisIndex: 1,
            data:lineData
        };
	}
	totleMax = Number(totleMax);
	fansMax = Number(fansMax);
	// 指定图表的配置项和数据
	var optionMore = {
	    title: {
	    	left: 'center',
	        text: '多渠道统计图表'
	    },
	    tooltip: {
	        trigger: 'axis',
	        formatter: function (params,ticket,callback) {
	        	var ret = "";
	        	if(jsonData != null && jsonData.length > 0){
	        		for(var i=0;i<jsonData.length;i++){
	        			if(params[0].name == jsonData[i].dateTime){
	        				ret += "粉丝数："+jsonData[i].fans+"<br/>";
	        	        	ret += "新增粉丝数："+jsonData[i].newFans+"<br/>";
	        	        	for(var key in storeName){
	        	        		var index = 0;
	        	        		for(var json in jsonData[i]){
		        	        		if(key == jsonData[i][json]){
		        	        			index = json.substring(9,json.length);
		        	        			break;
		        	        		}
		        	        	}
	        	        		index = Number(index);
	        	        		var storeName_ = jsonData[i]['storeName'+index];
	        					var balances_ = jsonData[i]['balances'+index];
	        					var balancesRate_ = jsonData[i]['balancesRate'+(index+1)];
	        					if(storeName_ != null && storeName_ != undefined && storeName_ != 'undefined'){
	        						ret += storeName_+"<br/>销售/占比："+balances_+" / "+balancesRate_+"<br/>";
	        					}
	        	        	}
	        	        	
	        	        	
	        			}
	        		}
	        	}
	        	return ret;
	        },
	        axisPointer: {
	            type: 'cross',
	            crossStyle: {
	                color: '#999'
	            }
	        }
	    },
	    toolbox: {
	        feature: {
	            dataView: {show: true, readOnly: false},
	            magicType: {show: true, type: ['line', 'bar']},
	            restore: {show: true},
	            saveAsImage: {show: true}
	        }
	    },
	    xAxis: [
	        {
	            type: 'category',
	            data: categoryData,
	            axisPointer: {
	                type: 'shadow'
	            }
	        }
	    ],
	    yAxis: [
	        {
	            type: 'value',
	            name: '销售额',
	            min: 0,
	            max: totleMax,
	            interval: totleMax/10,
	            axisLabel: {
	                formatter: '{value}'
	            }
	        },
	        {
	            type: 'value',
	            name: '粉丝量',
	            min: 0,
	            max: fansMax,
	            interval: fansMax/10,
	            axisLabel: {
	                formatter: '{value}'
	            }
	        }
	    ],
	    series: series
	};
	
	// 指定图表的配置项和数据
	var roundOptionMore = {
		    title : {
		        text: '多渠道销售额占比',
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item'
		    },
		    series : [
		        {
		            name: '门店占比',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:seriesData,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};
	
	// 使用刚指定的配置项和数据显示图表。
	myChartMore.setOption(optionMore);
	roundChartMore.setOption(roundOptionMore);
	
	
	function sort(arr){
	    for(var j=0;j<arr.length-1;j++){
	    //两两比较，如果前一个比后一个大，则交换位置。
	       for(var i=0;i<arr.length-1-j;i++){
	            if(arr[i]>arr[i+1]){
	                var temp = arr[i];
	                arr[i] = arr[i+1];
	                arr[i+1] = temp;
	            }
	        } 
	    }
	}
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="padding: 3px;" >
        <div id="moreMain" style="width: 1000px;height:500px;"></div>
        <div id="moreRound" style="width: 1000px;height:500px;"></div>
    </div>
</div>