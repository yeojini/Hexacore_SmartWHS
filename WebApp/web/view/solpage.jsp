<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>
<style>
#dateformBox{
	display: flex;
}
.dateInfo{
	font-weight: bold;
	font-size : 1.1rem;
	padding-top : 10px;
	padding-right : 20px;
	
}
#rchartBody {
	display: inline-block;
	align-content: center;
}

#rchartBody #before {
	margin-right: 15px;
}
.card .card-body{
	padding-bottom: 20px;
}

.search-after{
	display: none;
}

</style>
</head>
<body>
	<div class="content-body">
		<div class="container-fluid mt-3">
			<div class="row">
						<div class="col-lg-12 search-before">
							<div class="card">
								<div class="card-body">
									<div class="card-title">
										<h4>Solution</h4>
										<div data-toggle="tooltip" data-placement="right"
											title="기간 별 솔루션 제공 페이지입니다.">
											<button class="fas fa-question-circle"></button>
										</div>
									</div>
									<hr></hr>
										
										<div class="col-lg-12" id="dateformBox">
											<div class="input-group col-lg-5">
												<span class="dateInfo">Start Date : </span>
												<input type="text" id="startdate" name="startdate" 
													class="form-control mydatepicker" placeholder="yyyy-mm-dd"> 
													<span class="input-group-append">
													<span class="input-group-text">
													<i class="mdi mdi-calendar-check"></i></span></span>
											</div>
											<div class="input-group col-lg-5">
												<span class="dateInfo">End Date : </span>
												<input type="text" id="enddate" name="enddate"
													class="form-control mydatepicker"
													placeholder="yyyy-mm-dd"> 
													<span class="input-group-append">
													<span class="input-group-text">
													<i class="mdi mdi-calendar-check"></i></span></span>
											</div>
											<div class="input-group col-lg-2">
												<button type="button" class='btn mb-1 btn-primary' id="submitDate">SUBMIT</button>
											</div>
										</div>
								</div>
							</div>
						</div>


						<div class="col-lg-12 search-after">
							<div class="card">
								<div class="card-body">
									<div class="card-title">
										<h4>Solution</h4>
										<div data-toggle="tooltip" data-placement="right"
											title="창고 좌표 당 입/출고 빈도수를 나타낸 그래프입니다.">
											<button class="fas fa-question-circle"></button>
										</div>
									</div>
									<hr></hr>
									<div id="rchartBody">
										<img id="before" src="images/rChart/before.jpg" alt="">
										<img id="after" src="images/rChart/after.jpg" alt="">
									</div>
								</div>
							</div>
						</div>

						<div class="col-lg-12 search-after">
							<div class="card">
								<div class="card-body">
									<div class="card-title">
										<h4>Single Bar Chart</h4>
										<div data-toggle="tooltip" data-placement="right"
											title="솔루션 적용 시의 효율성 증가를 나타낸 그래프입니다.">
											<button class="fas fa-question-circle"></button>
										</div>
									</div>
									<hr></hr>
									<div id="chartScript" style="display: inline-block; position: relative;"></div>

									<canvas id="singelBarChart" width="500" height="250"></canvas>
								</div>
							</div>
						</div>
			</div><!-- row end -->
		</div>
	</div>
	<script>
		function display(data) {
			// single bar chart
			var ctx = document.getElementById("singelBarChart");
			ctx.height = 200;
			var myChart = new Chart(ctx, {
				type : 'horizontalBar',
				data : {
					labels : [ "Before", "After" ],
					datasets : [ {
						label : "Total Battery Efficiency",
						data : data,
						borderColor : "#F2AB39",
						borderWidth : "0",
						backgroundColor : "#F2AB39"
					} ]
				},
				options : {
					scales : {
						xAxes : [ {
							display : true,
							scaleLable : {
								display : true,
							},
							ticks : {
								beginAtZero : true
							}

						} ],
						yAxes : [ {
							display : true,
							scaleLable : {
								display : true,
							},
							ticks : {
								beginAtZero : true
							}
						} ]
					}
				}
			});

		};
		
		jQuery('#submitDate').click(function () {
			setTimeout(function() {
			    if($(".search-after").css("display") == "none"){   
			        jQuery('.search-after').show();  
			        jQuery('.search-before').hide();  
			    }
			}, 3000);

		});  



		$(document.body).delegate("#applyBtn", "click", function() {
			alert("솔루션이 적용되었습니다.");
			if($(".search-before").css("display") == "none"){   
		        jQuery('.search-before').show();  
		        jQuery('.search-after').hide(); 
		    }
			$('body').scrollTop(0);

		});

		$(document)
				.ready(
						function() {
							$.ajax({
										url : 'rchartdata.pc',
										success : function(data) {
											var chartdata = Array(data[0],
													data[1]);
											var html = '';
											display(chartdata);

											html += "<span style ='font-size:1.15rem;'>Solution 적용 시, 배터리 효율성 </span>"

											html += "<span class = 'text-danger' style ='font-weight:bold; font-size:1.15rem;'>"
													+ data[2] + "%</span>";
											html += "<span style ='font-size:1.15rem;'> 증가</span>";
											html += "<button class='btn mb-1 btn-primary' id='applyBtn' style='padding-bottom:3px; padding-top:3px; margin-top:0px; float:right;'>APPLY</button>";

											$('#chartScript').html(html);
										}
									})

						});
	</script>
</body>
</html>