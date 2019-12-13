<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<script src="https://www.chartjs.org/dist/2.9.2/Chart.min.js"></script>
	<script src="https://www.chartjs.org/samples/latest/utils.js"></script>
	<style>
	canvas {
		-moz-user-select: none;
		-webkit-user-select: none;
		-ms-user-select: none;
	}
	</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
	
let salData = [
	{sum:0, count:0}, //미래전략
	{sum:0, count:0}, //영업
	{sum:0, count:0}, //총무
	{sum:0, count:0} //홍보
  ];
	
let m = 0; //미래전략
let y = 0; //영업
let c = 0; //총무
let h = 0; //해외홍보

		$.ajax({
	        url : 'Chart',
	        type : 'post',
	        dataType : 'json',
	        success : function(data) {        
	     	
	     	$.each(data, function(index, element) {
	     		if(element.dName=='미래전략실'){		               
		        	salData[0].sum += element.sal;
		        	salData[0].count ++;  
		        	
		    	}else if(element.dName=='영업1팀'||(element.dName=='영업1팀')||(element.dName=='영업1팀')){		               
		        	salData[1].sum += element.sal;
		        	salData[1].count ++;
		        	
		    	}else if(element.dName=='총무팀'){		               
		        	salData[2].sum += element.sal;
		        	salData[2].count ++;
		        	
		    	}else if(element.dName=='해외홍보팀'){		               
		        	salData[3].sum += element.sal;
		        	salData[3].count ++;
		        	
		    	}
	     	});
	     	
	     	 m = (salData[0].sum/salData[0].count).toFixed(2);
	     	 console.log(m);
	     	 
	     	 y = (salData[1].sum/salData[1].count).toFixed(2);
	     	 console.log(y);
	
	     	 c = (salData[2].sum/salData[2].count).toFixed(2);
	     	 console.log(c);
	     	 
	     	 h = (salData[3].sum/salData[3].count).toFixed(2);
	     	 console.log(h);
	     	 
	     	 
	     	 salChart();
	        }
		});
		
		function salChart(){
			var barChartData = {
					labels: ['미래전략실', '영업팀', '총무팀', '해외홍보팀'],
					datasets: [{
						label: '부서별 연봉 (단위 : 만 원)',
						backgroundColor: [
							window.chartColors.red,
							window.chartColors.yellow,
							window.chartColors.green,
							window.chartColors.blue,
						],
						yAxisID: 'y-axis-1',
						data: [m, y, c, h]
					}]

				};
					var ctx = document.getElementById('canvas').getContext('2d');
					window.myBar = new Chart(ctx, {
						type: 'bar',
						data: barChartData,
						options: {
							responsive: true,
							title: {
								display: true,
								text: '부서별 평균 연봉'
							},
							tooltips: {
								mode: 'index',
								intersect: true
							},
							scales: {
								yAxes: [{
									type: 'linear', 
									display: true,
									position: 'left',
									id: 'y-axis-1',
									ticks: {
							            beginAtZero: true,
							            min: 2000
							          }
								}],
							}
						}
					});

		}	
		
	});
</script>	
	<div style="width: 75%">
		<canvas id="canvas"></canvas>
	</div>
