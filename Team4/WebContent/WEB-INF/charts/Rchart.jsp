<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
@import 'https://code.highcharts.com/css/highcharts.css';

#container {
	height: 400px;
	max-width: 800px;
	min-width: 320px;
	margin: 0 auto;
}
.highcharts-pie-series .highcharts-point {
	stroke: #EDE;
	stroke-width: 2px;
}
.highcharts-pie-series .highcharts-data-label-connector {
	stroke: silver;
	stroke-dasharray: 2, 2;
	stroke-width: 2px;
}
</style>
<script type="text/javascript">
$(function() {
let y1 = 0; //영업1팀에 속한 사원 수 
let y2 = 0; //영업2팀에 속한 사원 수 
let y3 = 0; //영업3팀에 속한 사원 수 
let m = 0; //미래전략팀에 속한 사원 수 
let c = 0; //총무팀에 속한 사원 수 
let h = 0; //해외홍보팀에 속한 사원 수 
let g = 0; //기타팀에 속한 사원 수

	$.ajax({
        url : 'Chart',
        type : 'post',
        dataType : 'json',
        success : function(data) {        
     	
     	$.each(data, function(index, element) {
	        if(element.dName=='영업1팀'){		               
	        	y1++;  
	    	}else if(element.dName=='영업2팀'){		               
	        	y2++;  
	        	
	    	}else if(element.dName=='영업3팀'){		               
	        	y3++;  
	        	
	    	}else if(element.dName=='미래전략실'){		               
	        	m++;
	        	
	    	}else if(element.dName=='총무팀'){		               
	        	c++;
	        	
	    	}else if(element.dName=='해외홍보팀'){		               
	        	h++;
	        	
	    	}else{ //기타
	    		g++;
	    	}

     	});
        dNameChart();
        }
	});
	
	function dNameChart(){
	Highcharts.chart('container', {

	    chart: {
	        styledMode: true
	    },

	    title: {
	        text: '부서별 인원 규모'
	    },
	    series: [{
	        type: 'pie',
	        allowPointSelect: true,
	        keys: ['name', 'y', 'selected', 'sliced'],
	        data: [
	            ['영업1팀', y1, false],
	            ['영업2팀', y2, false],
	            ['영업3팀', y3, false],
	            ['미래전략실', m, false],
	            ['총무팀', c, false],
	            ['해외홍보팀', h, false],
	            ['기타', g, false]
	        ],
	        showInLegend: true
	    }]
	});

	}	
	
});
</script>

<div id="container"></div>
