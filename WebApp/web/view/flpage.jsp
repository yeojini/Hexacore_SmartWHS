<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Custom Stylesheet -->
<link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
<link rel="stylesheet"
	href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
<link href="css/style.css" rel="stylesheet">

<style>
.card-title {
	display: flex;
	text-align: center;
	align-items: baseline;
}
.container-fluid{
	background-color: #E0E0E0;
}
</style>
</head>
<body>
	<div class="content-body">
		<div class="container-fluid mt-3">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Forklift Daily Mileage</h4>
								<div data-toggle="tooltip" data-placement="right"
									title="지게차의 일별 주행거리를 표시합니다.">
									<button class="fas fa-question-circle"></button>
								</div>
							</div>
							<hr></hr>
							<div id="multi-line-chart" class="ct-chart ct-golden-section"></div>
						</div>
					</div>
				</div>
			</div>


			<div class="row">
				<!-- Table -->
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Forklift List</h4>
								<div data-toggle="tooltip" data-placement="right"
									title="창고별 지게차의 리스트와 현재 상태를 표시합니다.">
									<button class="fas fa-question-circle"></button>
								</div>
							</div>
							<hr></hr>
							<div class="table-responsive">
								<table class="table table-xs mb-0">
									<thead>
										<tr>
											<th>Forklift ID</th>
											<th>Warehouse ID</th>
											<th>Purchase Date</th>
											<th>Forklift Model</th>
											<th>Last Check Date</th>
											<th>Status</th>
											<th>Total Driven Distance</th>
										</tr>
									</thead>

									<tbody>
										<c:forEach var="fl" varStatus="status" items="${fllist }">
											<tr>
												<td>${fl.forkid }</td>
												<td>${fl.wareid}</td>
												<td><span>${fl.forkpurdate }</span></td>
												<td>${fl.forkmodel}</td>
												<td>${fl.forklastcheckdate}</td>
												<td id="flstatus${status.count}"><span class="badge badge-success">WAITING</span></td>
												<td id="fldist${status.count}"><span id="fldi${status.count}">${fl.forkdist}</span><span>m</span></td>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="plugins/common/common.min.js"></script>
	<script src="js/custom.min.js"></script>
	<script src="js/settings.js"></script>
	<script src="js/gleek.js"></script>
	<script src="js/styleSwitcher.js"></script>

	<script src="./plugins/chartist/js/chartist.min.js"></script>

	<script
		src="./plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>
	<script>

    
   var forkdata;
 
  
  //Status Update
  
  	function forkDistanceDriven(){
	  	
		$.ajax({
			url : "/WebApp/distance.pc", //URL 주소
			//contentType : "/json; charset=UTF-8",
			success : function(fork) { //응답이 성공 상태 코드를 반환하면 호출되는 함수

			  	console.log(fork);
			
				var JsonFork = eval('('+fork+')');
				console.log(eval('('+JsonFork.forklift1+')'));
				
			  	 new Chartist.Bar('#multi-line-chart', {
			  	      labels: ['7 days ago', '6 days ago', '5 days ago', '4 days ago', '3 days ago', '2 days ago', 'yesterday'],
			  	      series: [
			  	    	eval('('+JsonFork.forklift1+')'),
			  	    	eval('('+JsonFork.forklift2+')'),
			  	    	eval('('+JsonFork.forklift3+')'),
			  	    	eval('('+JsonFork.forklift4+')')
			  	    	
			  	      ]
			  	    }, {
			  	      seriesBarDistance: 10,
			  	      axisX: {
			  	        offset: 60
			  	      },
			  	      axisY: {
			  	        offset: 80,
			  	        labelInterpolationFnc: function(value) {
			  	          return (value/10) + ' m'
			  	        },
			  	        scaleMinSpace: 15
			  	      },
			  	      plugins: [
			  	        Chartist.plugins.tooltip()
			  	      ]
			  	    });
	
			},
			error : function(e) { // 이곳의 ajax에서 에러나면 콘솔창으로 에러 메시지 출력
				console.log("에러는"   +e.responseText);
			}
		});
	}
  	forkDistanceDriven();
  
	function forkliftstatus(){
	  	var flstatus;
	  	var forklift1 = {};
	  	var forklift2 = {};
	  	var forklift3 = {};
	  	var forklift4 = {};
	  	
	  	
	  	
	  	console.log("ajax called :");
	  	
		$.ajax({
			url : "/WebApp/sendfl.pc", //URL 주소
			//contentType : "/json; charset=UTF-8",
			success : function(data) { //응답이 성공 상태 코드를 반환하면 호출되는 함수
				
				console.log(data);
			  	var json = eval('('+data+')');
			  	console.log(eval('('+json.forklift1+')').status);
			  	$('#flstatus1').html(printFLStatus(eval('('+json.forklift1+')').status));
			  	console.log(eval('('+json.forklift2+')').status);
			  	$('#flstatus2').html(printFLStatus(eval('('+json.forklift2+')').status));
			  	console.log(eval('('+json.forklift3+')').status);
			  	$('#flstatus3').html(printFLStatus(eval('('+json.forklift3+')').status));
			  	console.log(eval('('+json.forklift4+')').status);
			  	$('#flstatus4').html(printFLStatus(eval('('+json.forklift4+')').status));
			  	 
			  	//$('#flDistanceDriven').html(eval('('+json.forklift1+')').distanceDriven)
			  	
			},
			error : function(e) { 
				console.log("에러는"   +e.responseText);
			}
			
		});
		
	}
	

	function forkliftdistance(){
	  	var flstatus;
	  	var forklift1 = {};
	  	var forklift2 = {};
	  	var forklift3 = {};
	  	var forklift4 = {};
	  	
		$.ajax({
			url : "getDist.pc", //URL 주소
			//contentType : "/json; charset=UTF-8",
			success : function(data) { //응답이 성공 상태 코드를 반환하면 호출되는 함수
				
			  	
			  	 
			  	console.log("**dist1 : " + data.forklift1 );
			  	console.log("**dist2 : " + data.forklift2 );
			  	console.log("**dist3 : " + data.forklift3 );
			  	console.log("**dist4 : " + data.forklift4 );
			  	
			  	console.log(data.forklift1);
			  	$('#fldi1').html(data.forklift1);
			  	console.log(data.forklift2);
			  	$('#fldi2').html(data.forklift2);
			  	console.log(data.forklift3);
			  	$('#fldi3').html(data.forklift3);
			  	console.log(data.forklift4);
			  	$('#fldi4').html(data.forklift4);
			  	//$('#flDistanceDriven').html(eval('('+json.forklift1+')').distanceDriven)
			  	
			},
			error : function(e) { 
				console.log("에러는"   +e.responseText);
			}
			
		});
		
	}
	

	function printFLStatus(num) {
		console.log("!!");
		var text = '';
		if(num==0) {
			text = '<span class="badge badge-danger">WORKING</span>';
		} else if (num==1) {
			text = '<span class="badge badge-success">WAITING</span>';
		} else if (num==2) {
			text = '<span class="badge badge-info">CHARGING</span>';
		}
		
		return text;
	}
	

	$(document).ready(function() {
		setInterval(function() {
			forkliftstatus()
		}, 3000);
		
		setInterval(function() {
			forkliftdistance()
		}, 3000);
		
	});

  	
  	

    </script>

</body>
</html>