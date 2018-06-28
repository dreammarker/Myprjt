<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<script src="//www.google.com/jsapi"></script>
<script src="${pageContext.request.contextPath}/resources/scripts/jquery-3.2.1.min.js"> </script>
<script>
	var options = {
		title : '부서별 사원정보',
		width : 800,
		height : 500,
		colors: ['#e0550e'],
		annotations: {
		    textStyle: {
		      fontName: 'Times-Roman',
		      fontSize: 18,
		      bold: true,
		      italic: true,
		      color: '#871b47',
		      auraColor: '#d799ae',
		      opacity: 0.8
		    }
		  }
			  
	};
	google.load('visualization', '1.0', {'packages' : [ 'corechart' ]});
	google.setOnLoadCallback(function(){

		var chart_data =[['부서','인원수']];
		//data(ajax)
		$.ajax({
			url : "${pageContext.request.contextPath}/emp/getDeptCnt",
			dataType : 'json',
			success : function(res_data){
				for(i=0;i<res_data.length;i++){
					var deptNm = res_data[i].department_name;
					var cnt = res_data[i].cnt;
					var temp = [deptNm,cnt];
					chart_data.push(temp);
				}
			
					var chart = new google.visualization.ColumnChart(document
							.querySelector('#chart_div'));
					chart.draw(google.visualization.arrayToDataTable(chart_data), options);
			}
		});
	});
	
	
</script>
</head>
<body>
	<h3>차트연습</h3>
	<div id="chart_div"></div>
</body>
</html>