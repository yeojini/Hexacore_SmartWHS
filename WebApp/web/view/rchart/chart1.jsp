<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<style>
#container {
	height: 400px;
}

.highcharts-figure, .highcharts-data-table table {
	min-width: 310px;
	max-width: 800px;
	margin: 1em auto;
}

.highcharts-data-table table {
	font-family: Verdana, sans-serif;
	border-collapse: collapse;
	border: 1px solid #EBEBEB;
	margin: 10px auto;
	text-align: center;
	width: 100%;
	max-width: 500px;
}

.highcharts-data-table caption {
	padding: 1em 0;
	font-size: 1.2em;
	color: #555;
}

.highcharts-data-table th {
	font-weight: 600;
	padding: 0.5em;
}

.highcharts-data-table td, .highcharts-data-table th,
	.highcharts-data-table caption {
	padding: 0.5em;
}

.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even)
	{
	background: #f8f8f8;
}

.highcharts-data-table tr:hover {
	background: #f1f7ff;
}
</style>
</head>
<body>
	<div id="container"></div>

	<script>
		function display(data) {
			Highcharts
					.chart(
							'container',
							{
								chart : {
									type : 'column',
									options3d : {
										enabled : true,
										alpha : 10,
										beta : 25,
										depth : 70
									}
								},
								title : {
									text : '3D chart with null values'
								},
								subtitle : {
									text : 'Notice the difference between a 0 value and a null point'
								},
								plotOptions : {
									column : {
										depth : 25
									}
								},
								xAxis : {
									categories : Highcharts.getOptions().lang.shortMonths,
									labels : {
										skew3d : true,
										style : {
											fontSize : '16px'
										}
									}
								},
								yAxis : {
									title : {
										text : null
									}
								},
								series : [ {
									name : 'Sales',
									data : data
								} ]
							});
		};

		$(document).ready(function() {
			$.ajax({
				url : 'chart1data.mc',
				success : function(data) {
					//array 안에 data 들어가 잇다,,,
					alert(data);
					display(data);
				}
			})

		});
	</script>
</body>
</html>