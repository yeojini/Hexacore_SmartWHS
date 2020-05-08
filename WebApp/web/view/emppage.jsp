<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<style>
.card-title {
	display: flex;
	text-align: center;
	align-items: baseline;
}

.card-title div button {
	color: #E9EAEC;
	opacity: 0.1; /* Opacity (Transparency) */
	color: rgba(0, 0, 0, 0.5); /* RGBA Color (Alternative Transparency) */
	-webkit-filter: blur(2px);
}

.card .card-body canvas {
	margin-bottom: 20px;
}

#btn-section {
	text-align: right;
	padding-bottom: 14px;
}

#empListTBody td button {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="content-body">
		<div class="container-fluid mt-3">
			<!--<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">Multi-line labels</h4>
							<div id="multi-line-chart" class="ct-chart ct-golden-section"></div>
						</div>
					</div>
				</div>
			</div>  -->
			<div class="row">
				<div class="col-lg-5">
					<div class="card" style="height:424.08px;">
						<div class="card-body">
							<div class="card-title">
								<h4>Donut Chart - 창고 별 직원 수</h4>
							</div>
							<hr></hr>
							<canvas id="doughnutChart"></canvas>
						</div>
					</div>
				</div>
				<div class="col-lg-7">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Employee Register</h4>
								
								<div data-toggle="tooltip" data-placement="right"
									title="직원 정보를 추가할 수 있습니다. 초기 비밀번호는 '000000'으로 설정됩니다. ">
									<button class="fas fa-question-circle"></button>
								</div>
							</div>
							<hr></hr>
							<form name="addEmployee" method="post" action="empregister.pc">
								<div id="empForm">
									<h5>Mandatory Fields</h5>
									<section>
										<div class="row">
											<div class="col-lg-4">
												<div class="form-group">
													<input type="text" id="empno" name="empno"
														class="form-control" placeholder="ID" required>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group">
													<input type="text" id="empname" name="empname"
														class="form-control" placeholder="Name" required>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group">
													<select class="form-control" id="empjob" name="empjob"
														required>
														<option value="">Position</option>
														<option value="관리자">관리자</option>
														<option value="일반">일반</option>
													</select>
												</div>
											</div>
										</div>
									</section>
									<h5>Warehouse Details</h5>
									<section>
										<div class="row">
											<div class="col-6">
												<div class="form-group">
													<input type="text" class="form-control" id="wareid"
														name="wareid" placeholder="창고ID" readonly required>
												</div>
											</div>
											<div class="col-6">
												<div class="form-group">
													<select class="form-control" id="warename" name="warename"
														onchange="setWareID(this)">
														<option>창고명</option>
														<option value="ware00">이천 제1물류창고</option>
														<option value="ware01">천안 제1물류창고</option>
														<option value="ware02">덕평 제1물류창고</option>
														<option value="ware03">이천 제2물류창고</option>
													</select>
												</div>
											</div>
										</div>
									</section>
									<h5>Account Details</h5>
									<section>
										<div class="row">
											<div class="col-lg-6">
												<div class="form-group">
													<input type="email" name="empemail" class="form-control"
														placeholder="Email" required>
												</div>
											</div>
											<div class="col-lg-6">
												<div class="form-group">
													<input type="tel" name="empphone" class="form-control"
														placeholder="Phone Number" required>
												</div>
											</div>
										</div>
									</section>
									<section id="btn-section">
										<button type="button" id="resetEmpForm"
											class="btn mb-1 btn-secondary">Reset</button>
										<button type="submit" id="addEmpForm"
											class="btn mb-1 btn-info">
											Confirm<span class="btn-icon-right"><i
												class="fa fa-check"></i></span>
										</button>
									</section>
								</div>
							</form>
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
								<h4>Employee List</h4>
								<div data-toggle="tooltip" data-placement="right"
									title="직원 조회 테이블입니다.">
									<button class="fas fa-question-circle"></button>
								</div>

							</div>
							<hr></hr>
							<div class="table-responsive">
								<table id="empListBody"
									class="table table-striped table-bordered">
									<thead id="empListTHead">
										<tr>
											<th>ID</th>
											<th>Name</th>
											<th>Position</th>
											<th>Email</th>
											<th>Phone</th>
											<th>Warehouse ID</th>
											<th>Warehouse Name</th>
											<th></th>
										</tr>
									</thead>
									<tbody id="empListTBody">

									</tbody>
								</table>


								<!-- emp 수정 -->
								<div class="modal fade" id="modifyEmp" tabindex="-1"
									role="dialog">
									<div class="modal-dialog modal-lg" role="document">
										<input type="hidden" name="tempno"> <input
											type="hidden" name="tempname"> <input type="hidden"
											name="tempjob"> <input type="hidden" name="tempemail">
										<input type="hidden" name="tempphone"> <input
											type="hidden" name="twareid"> <input type="hidden"
											name="twarename">
										<div class="modal-content"></div>
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
				<!-- table end -->
			</div>
		</div>
	</div>


	<!-- chart.js -->
	<script src="./plugins/chart.js/Chart.bundle.min.js"></script>
	<script src="./js/plugins-init/chartjs-init.js"></script>

	<script>
		// warehouse id list
		var wareIdList = [ "wh1111", "wh1112", "wh1113", "wh1114" ];

		var rowIdxTarget;
		
		function setWareID(w) {
			var index = w.value.substring(5, 6);
			index *= 1;
			$('#wareid').val(wareIdList[index]);

		}

		$( '#resetEmpForm' ).click( function() {
			$('#empForm').find("input[name=empno]").val("");
			$('#empForm').find("input[name=empname]").val("");
			$('#empForm').find("input[name=empjob]").val("");
			$('#empForm').find("input[name=empemail]").val("");
			$('#empForm').find("input[name=empphone]").val("");
			$('#empForm').find("input[name=wareid]").val("");
			$('#empForm').find("input[name=warename]").val("");
		} );
		
		var getEmpList = function() {

			var empTable = $('#empListBody').DataTable({

				ajax : {
					url : 'getemplist.pc',
					dataSrc : '',
				},
				columns : [ {
					data : 'empno'
				}, {
					data : 'empname'
				}, {
					data : 'empjob'
				}, {
					data : 'empemail'
				}, {
					data : 'empphone'
				}, {
					data : 'wareid'
				}, {
					data : 'warename'
				}, {
			        data : null,
			        render : function ( data, type, row, meta ) {
			            return '<button class="far fa-edit" id="modifyEmpBtn" data-toggle="modal" data-target="#modifyEmp" data-remote="view/modal/modEmp.jsp" style="cursor:pointer;"></button>'
			            + '&emsp;<button class="fas fa-user-times" id="deleteEmpBtn" style="cursor:pointer;"></button>&nbsp;';  
			    }}]
			});

		};
		
		$(function() {
			$(document.body).delegate("#modifyEmpBtn","click",function() {
				rowIdxTarget = $(this).closest("tr").find("td");
			});
		});
		
		
		$(function() {
			$(document.body).delegate("#deleteEmpBtn","click",function() {
				
				var result = confirm("정말로 삭제하시겠습니까?");
				if(result){
					var empid = $(this).closest("tr").find("td").eq(0).text();
					
					$.ajax({
						type : "post",
						url : "empdelete.pc?empno="+empid,
						success : function() { 
							$('#empListBody').DataTable().ajax.reload();
							drawPieChart();
						},
						error : function(request, status, error) { 
							console.log("code:" + request.status + "\n"
									+ "message:" + request.responseText
									+ "\n" + "error:" + error);
						}
					});
				}
				
			});
		});
			
		
		$('#modifyEmp').on('show.bs.modal', function(e) {

			var button = $(e.relatedTarget);
			var modal = $(this);
			
			
			
			$(".modal-dialog").find("input[name=tempno]").val(rowIdxTarget.eq(0).text());
			$(".modal-dialog").find("input[name=tempname]").val(rowIdxTarget.eq(1).text());
			$(".modal-dialog").find("input[name=tempjob]").val(rowIdxTarget.eq(2).text());
			$(".modal-dialog").find("input[name=tempemail]").val(rowIdxTarget.eq(3).text());
			$(".modal-dialog").find("input[name=tempphone]").val(rowIdxTarget.eq(4).text());
			$(".modal-dialog").find("input[name=twareid]").val(rowIdxTarget.eq(5).text());
			$(".modal-dialog").find("input[name=twarename]").val(rowIdxTarget.eq(6).text());
			
			modal.find('.modal-content').load(button.data("remote"));

		});
		
		var drawPieChart = function() {
			$.ajax({
				type : "post",
				url : "getempnumbywh.pc",
				dataType : "text",
				contentType : "application/json; charset=UTF-8",
				success : function(response) {

					var obj = $.parseJSON(response);
					
					var ctx = document.getElementById("doughnutChart");
					ctx.height = 200;
					var myChart = new Chart(ctx, {
						type : 'doughnut',
						data : {
							datasets : [ {
								//data : [ 45, 25, 20, 10 ],
								data : obj.data,
								backgroundColor : [ "#69491A",
										"#F2AB39",
										"#E4B660",
										"#FFD954" ],
								hoverBackgroundColor : [ "rgba(117, 113, 249,0.9)",
										"rgba(117, 113, 249,0.7)",
										"rgba(117, 113, 249,0.5)",
										"rgba(144, 104, 190,0.7)" ]

							} ],
							labels : obj.labels
						},
						options : {
							responsive : true
						}
					});

				},
				error : function(request, status, error) {
					console.log("code:" + request.status + "\n"
							+ "message:" + request.responseText + "\n"
							+ "error:" + error);
				}
			});

		};

		$(document).ready(function() {
			history.replaceState({}, null, location.pathname);
			//donut chart 그리기
			drawPieChart();
			getEmpList();
					
		});		
	</script>

</body>
</html>