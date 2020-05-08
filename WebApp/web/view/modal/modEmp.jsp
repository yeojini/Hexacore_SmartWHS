<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.modal-footer button{
	margin-left: 5px;
}

</style>
</head>
<body>
	<div class="modal-header">
		<h5 class="modal-title">Modify Employee Information</h5>
		<button type="button" class="close" data-dismiss="modal" aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	
	<form name="modEmployee" method="post" action="empmodify.pc">
		<div class="modal-body">
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<tr>
							<td><input type="text" class="form-control" id="empno"
								name="empno" placeholder="ID" readonly></td>
							<td><input type="text" class="form-control" id="empname"
								name="empname" placeholder="Name" required></td>
							<td><select class="form-control" id="empjob" name="empjob" required>
									<option value="">Position</option>
									<option value="관리자">관리자</option>
									<option value="일반">일반</option>

							</select></td>

						</tr>
						<tr>
							<td><input type="email" class="form-control" id="empemail"
								name="empemail" placeholder="Email" required></td>
							<td><input type="tel" class="form-control" id="empphone"
								name="empphone" placeholder="Phone" required></td>

							<td><div class="whInfo">
									<input type="text" class="form-control" id="wareid"
										name="wareid" placeholder="창고ID" readonly required> 
									<select
										class="form-control" id="warename" name="warename"
										style="width: 150px;" onchange="setWareID(this)" required>
										<option>창고명</option>
										<option value="ware00">이천 제1물류창고</option>
										<option value="ware01">천안 제1물류창고</option>
										<option value="ware02">덕평 제1물류창고</option>
										<option value="ware03">이천 제2물류창고</option>
									</select>
								</div></td>
							<td></td>
						</tr>
					</tbody>
				</table>

			</div>
			<span id="info">&emsp;* 수정을 완료한 후 "Confirm" 버튼을 눌러 주세요. </span>
		</div>

		<div class="modal-footer">
			<button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
			<button type="submit" class="btn btn-primary">Confirm</button>
		</div>
	</form>
	<script>
		// warehouse id list
		var wareIdList = [ "wh1111", "wh1112", "wh1113", "wh1114" ];

		function setWareID(w) {
			var index = w.value.substring(5, 6);
			index *= 1;
			$(".whInfo").find("input[name=wareid]").val(wareIdList[index]);
		};
		
		var setDefaultValue = function () {
			var empno = $(".modal-dialog").find("input[name=tempno]").val();
			var empname = $(".modal-dialog").find("input[name=tempname]").val();
			var empjob = $(".modal-dialog").find("input[name=tempjob]").val();
			var empemail = $(".modal-dialog").find("input[name=tempemail]").val();
			var empphone = $(".modal-dialog").find("input[name=tempphone]").val();
			var wareid = $(".modal-dialog").find("input[name=twareid]").val();
			
			$(".modal-body").find("input[name=empno]").val(empno);
			$(".modal-body").find("input[name=empname]").val(empname);
			$(".modal-body").find("select[name=empjob]").val(empjob).attr("selected", "selected");
			$(".modal-body").find("input[name=empemail]").val(empemail);
			$(".modal-body").find("input[name=empphone]").val(empphone);
			$(".modal-body").find("input[name=wareid]").val(wareid);
		};
		
		$(document).ready( function() {
			setDefaultValue();
		});
	</script>
</body>
</html>