<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<div id="container" style="height: 100%"></div>
<script type="text/javascript">
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var jsonData = eval('(${jsonData})');
var clickNumData = [];
var saleNumData = [];
var goodsNameData = [];
var app = {};
option = null;
app.title = '正负条形图';

if(jsonData != null && jsonData.length > 0){
	var clickNum = 0;
	var saleNum = 0;
	for(var i=0;i<9;i++) {
		if(saleNum < jsonData[i].saleNum){
			saleNum = jsonData[i].saleNum;
		}
		saleNumData[i] = jsonData[i].saleNum;
		clickNumData[i] =0-jsonData[i].clickNum;
		goodsNameData[i]=jsonData[i].goodsName+"("+jsonData[i].specName+")";
	}
	
	
	for(var i=0;i<saleNumData.length-1;i++){
        for(var j=0;j<saleNumData.length-1-i;j++){
            if(saleNumData[j]>saleNumData[j+1]){
                var tmp=saleNumData[j+1];
                saleNumData[j+1]=saleNumData[j];
                saleNumData[j]=tmp;
                for(var m=0;m<goodsNameData.length-1;m++){
            		for(var n=0;n<goodsNameData.length-1-m;n++){
            			var tmp2 = goodsNameData[n+1];
            			goodsNameData[n+1]=goodsNameData[n];
            			goodsNameData[n]=tmp2;
            		}
            	}
                
                for(var x=0;x<clickNumData.length-1;x++){
            		for(var y=0;y<clickNumData.length-1-x;y++){
            			var tmp3 = clickNumData[y+1];
            			clickNumData[y+1]=clickNumData[y];
            			clickNumData[y]=tmp3;
            		}
            	}
            }
        }
	} 
}

option = {
		title: {
	    	left: 'center',
	        text: 'TOP10商品统计图'
	    },
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'line'        // 默认为直线，可选为：'line' | 'shadow'
	        },
	        formatter: function (params,ticket,callback) {
	        	console.log(params);
	        	var ret = "";
	        	if(jsonData != null && jsonData.length > 0){
	        			for(var i=0;i<jsonData.length;i++){
	        				var goodsName = jsonData[i].goodsName+"("+jsonData[i].specName+")";
	        				if(params[0].name == goodsName){
	        					ret += goodsName+":"+"<br/>";
	        					ret += "销量："+jsonData[i].saleNum+"<br/>";
		        	        	ret += "销售额："+jsonData[i].salePrice+"<br/>";
		        	        	ret += "点击次数："+jsonData[i].clickNum+"<br/>";
		        	        	ret += "点击人数："+jsonData[i].clickManNum+"<br/>";
		        	        	ret += "购买人数："+jsonData[i].buyManNum+"<br/>";
		        	        	ret += "收藏量："+jsonData[i].collectNum+"<br/>";
		        	        	ret += "加购数量："+jsonData[i].cartGoodsNum+"<br/>";
	        				}
	        				
	        		}
	        	}
	        	return ret;
	        },
	    },
	    legend: {
	    	bottom: '3%',
	        data:['点击次数', '销量']
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '8%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'value',
	            axisLabel:{
	                formatter:function(value){
	                    if (value<0) {
	                        return -value;
	                    }else{
	                    	return value;
	                    }
	                }
	            }
	        }
	     
	    ],
	    yAxis : [
	        {
	            type : 'category',
	            axisTick : {show: false},
	        	data:goodsNameData
	        }
	    ],
	    series : [
	        {
	            name:'点击次数',
	            type:'bar',
	            stack: '总量',
				color:['#5b9bd5'],
				barWidth : 30,
				itemStyle: {
					normal: {
	                		label : {show: true, position: 'inside',
		                        //控制负数显示正值
		                        formatter:function(value){
		                            return -value.data;
		                        }
	                		}
	            	}
	        	},
	            data:clickNumData
	        },
			 {
	            name:'销量',
	            type:'bar',
	            stack: '总量',
				color:['#ed7d3e'],
				barWidth : 30,
	            label: {
	                normal: {
	                    show: true
	                }
	            },
	            data:saleNumData
	        }
	    ]
	};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}
</script>