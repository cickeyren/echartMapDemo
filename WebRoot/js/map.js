function fn(x, n){
    return parseInt(x/n + 0.5) * n;
}
function getMax1(array){
	var max = Math.max.apply(null,array);
	return fn(max,10);
};
function mapload(jsonarray){
//地图数据
/*var mydatas = [
	{id: "1",name: '崇川区',value:7},{id: "2",name: '港闸区',value:0},
	{id: "3",name: '海安市',value:0},{id: "4",name: '海门市',value:0},
	{id: "5",name: '启东市',value:0},{id: "6",name: '如东县',value:0},
	{id: "7",name: '如皋市',value:0},{id: "8",name: '通州区',value:0}
];*/
var mydatas=[];
var valuesarray=[];
for (var i=0; i < jsonarray.length; i++) {
	var obj = new Object(); 
	obj.name = jsonarray[i].FCITY;
	obj.value = parseInt(jsonarray[i].COUNT);
	mydatas[i] = obj; 
	valuesarray[i] = parseInt(jsonarray[i].COUNT);
}
var mapcolors = ['#80F1BE', '#fec42c', '#dd4444'];
var mapcolors1 = ['lightskyblue','yellow', 'orangered'];
var mapcolors2 = ['#74d5fb','#04AFF2', '#036186'];
//绘制地图
var myChart = echarts.init(document.getElementById("main"));
option = {
	  title: {
	      text: "资产地图",
	      left: 'center'
	  },
	  tooltip: {
	      trigger: 'item'
	  },
 	  visualMap: {
	      min: 0,
	      max: getMax1(valuesarray),
	      left: 'left',
	      top: 'bottom',
	      text: ['高','低'],           // 文本，默认为数值文本
	      calculable: true
	  },
	  series: [
	      {
	    	  itemStyle: {
                  normal: {
                              color: function (params) {
                                  return mapcolors[params.dataIndex];
                              },
                              label:{show:true}
                          }
	    	  },
	          name: "资产数量",
	          type: 'map',
	          mapType: "nantong",
	          roam: true,
	          label: {
	              normal: {
	                  show: true
	              },
	              emphasis: {
	                  show: true
	              }
	          },
	          data:mydatas
	      }
	  ]
};                
myChart.clear();
//使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
//下钻其实就是点击事件，切换脚本 
myChart.on('click', function (chinaParam) {
	if(chinaParam.value){
		window.location.href ="assetsDetail.html?cityName="+chinaParam.name;
    }  
});
}
