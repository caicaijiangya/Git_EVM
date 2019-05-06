<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
	//基于准备好的dom，初始化echarts实例
	var myChartOdd = echarts.init(document.getElementById('oddMain'));
	var jsonData = eval('(${jsonData})');
	var categoryData = [];
	var barData = [];
	var lineData = [];
	var totleMax = 10;
	var oddMax = 10;
	if(jsonData != null && jsonData.length > 0){
		var transBalances = 0.0;
		var userOddPrice = 0.0;
		for(var i=0;i<jsonData.length;i++){
			if(transBalances < jsonData[i].transBalances){
				transBalances = jsonData[i].transBalances;
			}
			if(userOddPrice < jsonData[i].userOddPrice){
				userOddPrice = jsonData[i].userOddPrice;
			}
			categoryData[i] = jsonData[i].dateTime;
			barData[i] = jsonData[i].transBalances;
			lineData[i] = jsonData[i].userOddPrice;
		}
		for(var i=0;i<String(parseInt(transBalances)).length-1;i++){
			totleMax += "0";
		}
		for(var i=0;i<String(parseInt(userOddPrice)).length-1;i++){
			oddMax += "0";
		}
	}
	totleMax = Number(totleMax);
	oddMax = Number(oddMax);
	// 指定图表的配置项和数据
	var optionOdd = {
	    title: {
	    	left: 'center',
	        text: '单渠道统计图表'
	    },
	    tooltip: {
	        trigger: 'axis',
	        formatter: function (params,ticket,callback) {
	        	var data = {transBalances:0,transGoodsNum:0,orderNum:0,userNum:0,userOddNum:0,userOddPrice:0,discount:0,refundRate:0};
	        	if(jsonData != null && jsonData.length > 0){
	        		for(var i=0;i<jsonData.length;i++){
	        			if(params[0].name == jsonData[i].dateTime){
	        				data = jsonData[i];
	        				break;
	        			}
	        		}
	        	}
	        	var ret = "销售数据 : <br/>";
	        	ret += "实付金额："+data.transBalances+"<br/>";
	        	ret += "销量："+data.transGoodsNum+"<br/>";
	        	ret += "订单量："+data.orderNum+"<br/>";
	        	ret += "客人数："+data.userNum+"<br/>";
	        	ret += "客单件："+data.userOddNum+"<br/>";
	        	ret += "客单价："+data.userOddPrice+"<br/>";
	        	ret += "实付折扣："+data.discount+"<br/>";
	        	ret += "退货率："+data.refundRate+"<br/>";
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
	            name: '总金额',
	            min: 0,
	            max: totleMax,
	            interval: totleMax/10,
	            axisLabel: {
	                formatter: '{value} RMB'
	            }
	        },
	        {
	            type: 'value',
	            name: '单价',
	            min: 0,
	            max: oddMax,
	            interval: oddMax/10,
	            axisLabel: {
	                formatter: '{value} RMB'
	            }
	        }
	    ],
	    series: [
	        {
	            name:'实付总金额',
	            type:'bar',
	            data:barData
	        },
	        {
	            name:'客单价',
	            type:'line',
	            yAxisIndex: 1,
	            data:lineData,
	        }
	    ]
	};
	
	// 使用刚指定的配置项和数据显示图表。
	myChartOdd.setOption(optionOdd);
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <div id="oddMain" style="width: 1000px;height:500px;"></div>
    </div>
</div>