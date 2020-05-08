<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<span id="invdtlInfo"></span>
	<div class="table-responsive">
		<table id="invdtlListBody" class="table table-striped table-bordered">
			<thead id="invdtlListTHead">
				<tr>
					<th>Item ID</th>
					<th>Item Name</th>
					<th>Warehouse ID</th>
					<th>Warehouse Name</th>
					<th>Status</th>
					<th>Qty</th>
				</tr>
			</thead>
			<tbody id="invdtlListTBody"></tbody>
		</table>
	</div>
	
	
	
	<script>
		var selectedInvId = $('#selectedInvId').val();
		var selectedInvDate = $('#selectedInvDate').val();
		console.log('invid : ' + selectedInvId);
		console.log('invdate : ' + selectedInvDate);
		$(document).ready(
				function() {
					//https://datatables.net/
					var invTable = $('#invdtlListBody').DataTable(
							{
								searching : false,
								paging : false,
								ordering : true,
								lengthChange : false,
								ajax : {
									url : 'invoicedtlsearch.pc?invoiceid='+selectedInvId,
									dataSrc : '',
								},
								columns : [
									{data : 'itemid'}, 
									{data : 'itemname'}, 
									{data : 'wareid'}, 
									{data : 'warename'}, 
									{data : 'invoicestat'},
									{data : 'invoicedtlqty'} 
								]
							});
					var infoHtml = selectedInvDate+ " 에 발주된 " + selectedInvId + "번 주문서 내역입니다.";
					$('#invdtlInfo').html(infoHtml);
				});
				
	</script>
</body>
</html>