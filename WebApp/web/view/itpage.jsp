<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">


<style>

.table tbody .whInfo{
	display : inline-flex;
}
.table tbody .whInfo input{
	width: 80px;
}
.table tbody .whInfo select{
	margin-left: 0.5rem;
}
.borderbox{
	border: 1px solid #DEE2E6;
	width : 100%;
	text-align: center;
}
#ivRegister{
	padding-bottom: 0px
}
#invoiceDetailHead{
	border-bottom : 1px solid #DEE2E6;
	background-color : F2F2F2;
}
th{
	font-size : 17px;
}

td{
	height : 55px;
	font-size : 13px;
}
</style>
</head>


<body>
	<div class="content-body">
		<div class="container-fluid mt-3">

			<!-- ************* (1) *************** -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
						
							<div class="card-title">
								<h4>Product Register</h4><div data-toggle="tooltip" data-placement="right" title="입/출고를 진행할 아이템을 등록합니다. Item ID는 자동으로 생성됩니다."><button class="fas fa-question-circle"></button></div>
							</div><hr></hr>
							<div class="table-responsive">
								<div class="form-validation">
									<form name="itemRegister" method="post"
										action="itemregister.pc">
										<table class="table">
											<thead>
												<tr>
													<th>Item ID<span class="text-danger">*</span></th>
													<th>Item Name<span class="text-danger">*</span></th>
													<th>Category<span class="text-danger">*</span></th>
													<th>Price<span class="text-danger">*</span></th>
													<th>Weight<span class="text-danger">*</span></th>
													<th>Qty<span class="text-danger">*</span></th>
													<th>Warehouse<span class="text-danger" >*</span></th>
													<th>Location<span class="text-danger">*</span></th>
												</tr>
											</thead>
											<tbody>

												<tr id = "registeritList">
													<td><input type="text" class="form-control"
														id="itemid" name="itemid" style="width: 120px;" placeholder="Auto-Generated" readonly></td>
													<td><input type="text" class="form-control"
														id="itemname" name="itemname" placeholder="상품명"
														style="width: 115px;" required></td>
													<td><select class="form-control" id="itemcate"
														name="itemcate" style="width: 115px;" required>
															<option value="">카테고리</option>
															<option value="전자제품">전자제품</option>
															<option value="차량부품">차량부품</option>

													</select></td>
													<td><input type="number" class="form-control"
														id="itemprice" name="itemprice" placeholder="단가" required></td>
													<td><input type="number" class="form-control"
														id="itemweightpb" name="itemweightpb" placeholder="무게" required></td>
													<td><input type="number" class="form-control"
														id="itemqtypb" name="itemqtypb" placeholder="개수" required></td>
													<td><div class="whInfo"><input type="text" class="form-control"
														id="wareid" name="wareid" placeholder="창고ID" readonly required>
														<select class="form-control" id="warename"
														name="warename" style="width: 150px;"
														onchange="setWareID(this)">
															<option>창고명</option>
															<option value="ware00">이천 제1물류창고</option>
															<option value="ware01">천안 제1물류창고</option>
															<option value="ware02">덕평 제1물류창고</option>
															<option value="ware03">이천 제2물류창고</option>
													</select></div></td>
													
													<td id = "modalTd" style="height:70px;">
													<input type="text" class="form-control" id="itemloc"
														name="itemloc" placeholder="위치" readonly>
														
														<!-- ----------Modal-------- -->
														<i class="mdi mdi-magnify" id="mdi-search" 
														data-toggle="modal" data-target="#theModal" data-remote="view/modal/itemLoc.jsp"></i>
													
														<div class="modal fade" id="theModal" tabindex="-1" role="dialog"> 
															<div class="modal-dialog modal-lg" role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<h5 class="modal-title" id="exampleModalLabel">New
																			message</h5>
																		<button type="button" class="close" data-dismiss="modal"
																			aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																	</div>
																	<div class="modal-body"></div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-secondary"
																			data-dismiss="modal" style="margin-left : 7px;">Close</button>
																		<button type="button" id="confirmItLoc" class="btn btn-primary">Confirm</button>
																	</div>
																</div>
															</div>
														</div></td>
											<!-- Modal End -->
												
												</tr>
												<tr>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td><button type="submit"
															class="btn mb-1 btn-primary btn-lg"
															id="toastr-success-top-right" >Register</button></td>
												</tr>
											</tbody>
										</table>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- ************* (2) *************** -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Item Table</h4><div data-toggle="tooltip" data-placement="right" title="등록된 아이템들을 확인할 수 있습니다."><button class="fas fa-question-circle"></button></div>
							</div><hr></hr>

							<div class="table-responsive">
								<table
									class="table table-striped table-bordered"
									id="itListBody">

									<thead>
										<tr>
											<th>Item ID</th>
											<th>Item Name</th>
											<th>Category</th>
											<th>Price</th>
											<th>Weight</th>
											<th>Qty</th>
											<th>Warehouse ID</th>
											<th>Warehouse Name</th>
											<th>Location</th>
											<th>Stock</th>
										</tr>
									</thead>

									<tbody id="itListTBody"></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- ************* (3) *************** -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Invoice Register</h4><div data-toggle="tooltip" data-placement="right" title="등록된 아이템에 한하여 입/출고를 진행할 수 있습니다. 위 테이블에서 입/출고를 진행할 아이템을 선택 후 수량과 입/출고를 지정해주세요."><button class="fas fa-question-circle"></button></div>
							</div><hr></hr>
							<div class="table-responsive">
								<!-- (추가) 서버에 올려보낼 때, 로그인id(empno)도 같이 올려 보내야함 -->
								<table class="table">
									<thead>
										<tr>
											<th>Item ID<span class="text-danger">*</span></th>
											<th>Item Name<span class="text-danger">*</span></th>
											<th>Warehouse ID<span class="text-danger">*</span></th>
											<th>Warehouse Name<span class="text-danger">*</span></th>
											<th>Qty<span class="text-danger">*</span></th>
											<th>Status<span class="text-danger">*</span></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<tr id="setItInfo">
											<td><input type="text" class="form-control" id="itemid"
												name="itemid" placeholder="상품 ID" readonly></td>
											<td><input type="text" class="form-control"
												id="itemname" name="itemname" placeholder="상품명" readonly></td>
											<td><input type="text" class="form-control" id="wareid"
												name="wareid" placeholder="창고ID" readonly></td>
											<td><input type="text" class="form-control"
												id="warename" name="warename" placeholder="창고명" readonly></td>
											<td><input type="text" class="form-control"
												id="invoicedtlqty" name="invoicedtlqty" placeholder="개수"
												style="width: 80px;"></td>
											<!-- (추가)출고 시 현재 재고보다 많은 양을 선택하면 에러 띄워야함 -->
											<td><select class="form-control" id="invoicestat"
												name="invoicestat">
													<option value="">입/출고</option>
													<option value="Receiving">Receiving</option>
													<option value="Shipping">Shipping</option>
											</select></td>

											<td><button type="button" id="addItemToInvoice"
													class="btn mb-1 btn-primary btn-lg">Add</button>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
							
							
							<div id= "invoiceDetailTable">
								<div class="table-responsive">
									
									<table class="table">
										<thead id="invoiceDetailHead">
											
										</thead>
										<tbody id="invoiceDetail">
	
										</tbody>
									</table>
								</div>
							</div> <!-- table end --> 
							
						</div>
					</div>
				</div>
			</div>

			<!-- ************* (4) *************** -->
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>Invoice Search</h4><div data-toggle="tooltip" data-placement="right" title="주문서 내역 조회 기능입니다."><button class="fas fa-question-circle"></button></div>
							</div><hr></hr>
							<div class="table-responsive">
								<c:choose>
									<c:when test="${empjob eq '관리자'}">	<!-- 사용자가 관리자일때 -->
										<table class="table">
											<thead>
												<tr>
													<th>Employee ID</th>
													<th>Employee Name</th>
													<th>Start Date</th>
													<th>End Date</th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<tr id = "invSearching">
													<td><input type="text" class="form-control" id="empno"
														name="empno" placeholder="관리자 사번"></td>
														<td><input type="text" class="form-control" id="empname"
														name="empname" placeholder="관리자 이름"></td>
													<td><div class="input-group">
															<input type="text" id="startdate" name="startdate"
																class="form-control mydatepicker"
																placeholder="yyyy-mm-dd"> <span
																class="input-group-append"><span
																class="input-group-text"><i
																	class="mdi mdi-calendar-check"></i></span></span>
														</div></td>
													<td><div class="input-group">
															<input type="text" id="enddate" name="enddate"
																class="form-control mydatepicker"
																placeholder="yyyy-mm-dd"> <span
																class="input-group-append"><span
																class="input-group-text"><i
																	class="mdi mdi-calendar-check"></i></span></span>
														</div></td>
													<td><button type="submit" id="searchInvoice"
															class="btn mb-1 btn-primary btn-lg">Search</button></td>
	
												</tr>
												
											</tbody>
										</table>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
									
							</div>
							<span id="invListInfo"></span>
							<div class="table-responsive">
								<table id ="invListBody" class="table table-striped table-bordered">
									<thead id="invListTHead"></thead>
									<tbody id ="invListTBody"></tbody>
								</table>
								<!-- ///////////// Modal ////////////// -->
													
								<div class="modal fade" id="selectedInvDt" tabindex="-1" role="dialog">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<input type="hidden" id="selectedInvId"> <!-- invoice id -->
											<input type="hidden" id="selectedInvDate"> <!-- invoice date -->
											<div class="modal-header">
												<h5 class="modal-title" id="selectedInvLabel">Invoice Detail</h5>
												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
													<span aria-hidden="true">&times;</span>
												</button>
											</div>
											<div class="modal-body"></div>
											<div class="modal-footer">
												<!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
												<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- / container-fluid mt-3 -->
		<!-- #/ container -->
	</div>


	<script>
		// warehouse id list
		var wareIdList = [ "wh1111", "wh1112", "wh1113", "wh1114" ];

		// invoice detail item list
		var jsonInvoice = Array();
		
		// session Emp Info
		var sessionEmpId = "<%=session.getAttribute("empno") %>"
		var sessionEmpName = "<%=session.getAttribute("empname") %>"
		var sessionEmpJob = "<%=session.getAttribute("empjob") %>"
		var sessionWareId = "<%=session.getAttribute("wareid") %>"

		////////////////////////함수

		function setWareID(w) {
			var target = $("#registeritList").find("input[name=wareid]");
			var index = w.value.substring(5, 6);
			index *= 1;
			target.val(wareIdList[index]);
		}
		
		$('#theModal').on('show.bs.modal', function(e) {

			var button = $(e.relatedTarget);
			var modal = $(this);

			
			modal.find('.modal-body').load(button.data("remote"));
			

		});
		
		
		$( '#confirmItLoc' ).click( function() {
			console.log('data : ' + itemPosition);
			if(itemPosition == null){
				alert("자리를 선택해 주세요!");
			}else{
				$("#registeritList").find("input[name=itemloc]").val(itemPosition.toString());
				$('#theModal').modal("hide");
				$(".modal-backdrop").remove();

			}
			
		} );
/*
		$('#theModal').on('hide.bs.modal', function() {
			console.log('data : ' + itemPosition);
			if(itemPosition == null){
				alert("자리를 선택해 주세요!");
			}else{
				$("#registeritList").find("input[name=itemloc]").val(itemPosition.toString());
			}
			
		})
*/

		//(2)item list에서 데이터 선택하면 (3)의 textinput에 자동으로 띄우기 
		$(function() {
			$(document.body).delegate(".selectedItList", "click", function() {
				var itemid = $(this).find("td").eq(0).text();
				var itemname = $(this).find("td").eq(1).text();
				var wareid = $(this).find("td").eq(6).text();
				var warename = $(this).find("td").eq(7).text();
				$("#setItInfo").find("input[name=itemid]").val(itemid);
				$("#setItInfo").find("input[name=itemname]").val(itemname);
				$("#setItInfo").find("input[name=wareid]").val(wareid);
				$("#setItInfo").find("input[name=warename]").val(warename);
			});
		});

		// Add item to invoice
		$(function() {
			$("#addItemToInvoice").on(
					{
						click : function() {
							var jsonItem = new Object();

							jsonItem.itemid = $("#setItInfo").find(
									"input[name=itemid]").val();
							jsonItem.itemname = $("#setItInfo").find(
									"input[name=itemname]").val();
							jsonItem.wareid = $("#setItInfo").find(
									"input[name=wareid]").val();
							jsonItem.warename = $("#setItInfo").find(
									"input[name=warename]").val();
							jsonItem.invoicedtlqty = $("#setItInfo").find(
									"input[name=invoicedtlqty]").val();
							jsonItem.invoicestat = $("#setItInfo").find(
									"select[name=invoicestat]").val();
							
							jsonInvoice.push(jsonItem);

							updateIvDetail();
							
							$("#setItInfo").find("input[name=itemid]").val("");
							$("#setItInfo").find("input[name=itemname]").val("");
							$("#setItInfo").find("input[name=wareid]").val("");
							$("#setItInfo").find("input[name=warename]").val("");
							$("#setItInfo").find("input[name=invoicedtlqty]").val("");
							$("#setItInfo").find("select[name=invoicestat]").find('option:first').attr('selected', 'selected');
							
					
						},
						error : function(e) { // 이곳의 ajax에서 에러가 나면 얼럿창으로 에러 메시지 출력
							console.log(e.responseText);
						}
					});
		});

		// Delete item from invoice
		$(function() {
			$(document.body).delegate(".deleteIvItem", "click", function() {
				
				var idx = $(this).closest("tr").find("td").eq(0).text();
				var temp = idx.split('');
				jsonInvoice.splice(temp[1], 1);

				updateIvDetail();

			});
		});


		// Item list table
		var getItemlist = function() {
			var url = 'itemsearch.pc?wareid=';
			//관리자 아닐 경우에는 소속 창고의 아이템만 볼 수 있음
			if(sessionEmpJob == "일반"){
				url = 'itemsearch.pc?wareid='+ sessionWareId;
			}
			//https://datatables.net/
			var itTable = $('#itListBody').DataTable({
				lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
				ajax : {
					url : url,
					dataSrc : ''
				},columns : [ 
					{data : 'itemid'}, 
					{data : 'itemname'}, 
					{data : 'itemcate'}, 
					{data : 'itemprice'}, 
					{data : 'itemweight'}, 
					{data : 'itemqtypb'}, 
					{data : 'wareid'}, 
					{data : 'warename'}, 
					{data : 'itemloc'}, 
					{data : 'itemstock'}],
				columnDefs: [
					{ targets: 3 , render: $.fn.dataTable.render.number( ',' ) }]
					

			});
			$(document).on("mouseenter", "#itListTBody", function(){
				$('#itListTBody tr').addClass("selectedItList");

			});
			

		};
		
		// Register Invoice
		$(function() {
			$(document.body).delegate("#ivRegister","click",function() {
					
				
						$.ajax({
							type : "post",
							url : "invoiceregister.pc?empno="+sessionEmpId+"&empname="+sessionEmpName,
							data : JSON.stringify(jsonInvoice),
							dataType : "text",
							contentType : "application/json; charset=UTF-8",
							success : function(response) { //응답이 성공 상태 코드를 반환하면 호출되는 함수
								console.log(response);
								var token = response.responseText;
								if (token = "SUCCESS") {
									alert("발주가 완료되었습니다.");
									jsonInvoice = Array();
									$("#invoiceDetail").html("");
									$("#invoiceDetailHead").html("");
									$("#invoiceDetailTable").removeClass("borderbox");
									// https://datatables.net/blog/2019-01-11#Updating-the-parent-table
									/* dataTable 한 줄씩 업데이트 할 때
									for(var i = 0; i < selectedRow.length; i++){
										var row = selectedRow[i];
										$('#itListBody', row.child()).DataTable().ajax.reload();
									}
									selectedRow = [];
									*/
									/* dataTable 전체 업데이트 */
									$('#itListBody').DataTable().ajax.reload();
									$("#setItInfo").find("select[name=invoicestat]").find('option:first').attr('selected', 'selected');
									
									} else if (token = "ERROR") {
										
									alert("발주실패ㅒㅒㅒㅒ");
								}
								
								
							},
							error : function(request, status, error) { // 이곳의 ajax에서 에러가 나면 얼럿창으로 에러 메시지 출력
								console.log("code:" + request.status + "\n"
										+ "message:" + request.responseText
										+ "\n" + "error:" + error);
							}
						});
					});
			
			
		});



		var updateIvDetail = function() {
			var html = "";
			
			$.each(jsonInvoice, function(index, jsonItemArr) {
								html += "<tr class=\"ivItemList\">";
								html += '<td style = "width : 50px">' + "#" + index + '</td>';
								html += "<td>" + jsonItemArr.itemid + "</td>";
								html += "<td>" + jsonItemArr.itemname + "</td>";
								html += "<td>" + jsonItemArr.wareid + "</td>";
								html += "<td>" + jsonItemArr.warename + "</td>";
								html += "<td>" + jsonItemArr.invoicedtlqty + "</td>";
								html += "<td>" + jsonItemArr.invoicestat + "</td>";
								html += '<td><span class="badge badge-pill badge-danger deleteIvItem"> DELETE </span></td>'
								html += "</tr>";
							});
	
			html += "<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td><button type=\"button\" id =\"ivRegister\" class=\"btn mb-1 btn-primary\">Order</button></td></tr>";
			
			if(jsonInvoice.length == 0){
				$("#invoiceDetailHead").html("");
				$("#invoiceDetail").html("");
				$("#invoiceDetailTable").removeClass("borderbox");
			}else{
				$("#invoiceDetailHead").html("<tr><th></th><th>Item ID</th><th>Item Name</th><th>Warehouse ID</th><th>Warehouse Name</th><th>Qty</th><th>Status</th><th style='width:100px'></th></tr>");
				$("#invoiceDetail").html(html);
				$("#invoiceDetailTable").addClass("borderbox");
			}
		}
		
		var getInvList = function() {
			var empno = '';
			var empname = '';
			var startdate = '';
			var enddate = '';
			
			if(sessionEmpJob == '관리자'){
				empno = $("#invSearching").find("input[name=empno]").val();
				empname = $("#invSearching").find("input[name=empname]").val();
				startdate = $("#invSearching").find("input[name=startdate]").val();
				enddate = $("#invSearching").find("input[name=enddate]").val();
			}else if(sessionEmpJob == '일반'){
				empno = sessionEmpId;
			}
				
			
			
			console.log(startdate + " ~ " + enddate);
			
			$("#invListTHead").html('<tr><th>Invoice ID</th><th>Employee ID</th><th>Employee Name</th><th>Date</th></tr>');
			
			//https://datatables.net/
			var invTable = $('#invListBody').DataTable({
					retrieve: true,
					searching : false, 
					paging : true, 
					ordering : true,
					pageLength : 5,
					lengthChange: false,
					ajax : {
						url : 'invoicesearch.pc?empno='+empno+'&empname='+empname+'&sd='+startdate+'&ed='+enddate,
						dataSrc : '',
					},columns : [ 
						{data : 'invoiceid'}, 
						{data : 'empno'}, 
						{data : 'empname'}, 
						{data : 'invoicedate'}]
			});
			
			$("#invListInfo").html(" * 테이블을 클릭하면 주문서 상세 정보를 볼 수 있습니다.");
			
			// 테이블 클릭 시 모달 창 띄울 수 있도록 attribute 세팅
			$(document).on("mouseenter", "#invListTBody", function(){
				$('#invListTBody tr').addClass("selectedInv");
				$('#invListTBody tr').attr('data-toggle', "modal");
				$('#invListTBody tr').attr('data-target', "#selectedInvDt");
				$('#invListTBody tr').attr('data-remote', "view/modal/invDetail.jsp");
				
			});
		};

		$( '#searchInvoice' ).click( function() {
			getInvList();
			
		});
		
		$(function() {
			$(document.body).delegate(".selectedInv", "click", function() {
				var invoiceid = $(this).find("td").eq(0).text(); //invoiceid
				var invoicedate = $(this).find("td").eq(3).text(); //invoice date
				$('#invListTBody tr').attr('data-invid', invoiceid);
				$('#invListTBody tr').attr('data-invdate', invoicedate);
				
		});
	

		$('#selectedInvDt').on('show.bs.modal', function(e) {

			var button = $(e.relatedTarget);
			var modal = $(this);
			
			var invid = button.data("invid");
			var invdate = button.data("invdate");
			console.log(invid);
			console.log(invdate);
			
			$('.modal-content #selectedInvId').val(invid);
			$('.modal-content #selectedInvDate').val(invdate);
			modal.find('.modal-body').load(button.data("remote"));
			modal.find('#selectedInvLabel').html("#"+invid+" Invoice Detail");
			

		});

		

	});
		$(document).ready(function() {
			getItemlist();
			history.replaceState({}, null, location.pathname);
			
			if(sessionEmpJob == '일반'){
				getInvList();
			}
				
		});
		
		
	</script>


</body>
</html>