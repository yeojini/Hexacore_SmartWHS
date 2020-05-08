<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Admin Dashboard</title>

<!-- JQuery -->
<script src="https://code.jquery.com/jquery-latest.js"></script>

<!-- popper & tooltip -->
<script src="https://unpkg.com/popper.js/dist/umd/popper.min.js"></script>
<script src="https://unpkg.com/@popperjs/core@2"></script>
<script src="https://unpkg.com/tooltip.js/dist/umd/tooltip.min.js"></script>

<!-- Bootstrap -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
	
	
<link rel="stylesheet" href="css/hamburger.css" />

<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="images/favicon.png">
<!-- Pignose Calender -->
<link href="./plugins/pg-calendar/css/pignose.calendar.min.css"
	rel="stylesheet">
<!-- Chartist -->
<link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
<link rel="stylesheet"
	href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">

<!-- Custom Stylesheet -->
<link href="css/style.css?qwefsdsfddsfs" rel="stylesheet">


<!-- Page plugins css -->
<link href="./plugins/clockpicker/dist/jquery-clockpicker.min.css"
	rel="stylesheet">
<!-- Color picker plugins css -->
<link href="./plugins/jquery-asColorPicker-master/css/asColorPicker.css"
	rel="stylesheet">
<!-- Date picker plugins css -->
<link href="./plugins/bootstrap-datepicker/bootstrap-datepicker.min.css"
	rel="stylesheet">
<link
	href="./plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet">
<!-- Table -->
<link
	href="./plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<style type="text/css">
body {
	background-color: lightgray;
}

.header-right ul>li span {
	font-family: CenturyGothic;
	color: #E7E5DA;
}

.header-right ul>li i {
	color: #E7E5DA;
	font-size: 1.3rem;
}

.header-right ul>li #loginUserN {
	font-family: CenturyGothic;
	color: #EFC638;
}

.nav-header .brand-logo a {
	padding: 1.7rem 2.5rem;
	display: block;
}


@media ( min-width: 1300px ) {
	.nav-header{
		width : 15.1234rem;
	}
}


/*
.header-menu a{
	font-family: CenturyGothic;
}*/
/*
.header-menu span{
	width: 60%;
}
.header-menu span a{
	width: 50px;
	margin : 0px;
	padding-left: 30px;
	padding-right: 30px;
	padding-top:20px;
	padding-bottom:20px;
	border : 1px solid lightgray;
}*/
</style>
</head>

<body>

	<!--*******************
        Preloader start
    ********************-->
	<div id="preloader">
		<div class="loader">
			<svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none"
					stroke-width="3" stroke-miterlimit="10" />
            </svg>
		</div>
	</div>
	<!--*******************
        Preloader end
    ********************-->


	<!--**********************************
        Main wrapper start
    ***********************************-->
	<div id="main-wrapper">

		<!--**********************************
            Nav header start
        ***********************************-->

		<div class="nav-header">
			<div class="brand-logo">
				<a href="index.html"> <b class="logo-abbr"><img
						src="images/MySWM01_i.png" alt=""> </b> <span class="logo-compact" height="35px"><img
						src="images/MySWM01_i.png" alt="" height="35px"></span> <span class="brand-title">
						<img src="images/MySWM01_i.png" alt="" height="35px">
				</span>
				</a>
			</div>
		</div>

		<!--**********************************
            Nav header end
        ***********************************-->

		<!--**********************************
            Header start
        ***********************************-->
		<div class="header">
			<div class="spinner-master">
			    <input type="checkbox" id="spinner-form" />
			    <label for="spinner-form" class="spinner-spin">
			    <div class="spinner diagonal part-1"></div>
			    <div class="spinner horizontal"></div>
			    <div class="spinner diagonal part-2"></div>
			    </label>
			  </div>

			<!--**********************************
		           Menu start
		        ***********************************-->

			<div id="menu" class="header-menu menu">
				 <!-- 상품 입출고 --> <a href="itpage.pc"
					aria-expanded="false" class="header-menu-list-first"> <i
						class="icon-note menu-icon"></i> <span class="nav-text">STOCK</span></a>
						
				 <!-- 지게차 관리 --> <a href="flpage.pc" aria-expanded="false"
					class="header-menu-list"> <i class="icon-speedometer menu-icon"></i>
						<span class="nav-text">FORKLIFT</span></a>
						
						<!-- 솔루션 --> <a href="solpage.pc" aria-expanded="false"
					class="header-menu-list"> <i class="icon-globe-alt menu-icon"></i>
						<span class="nav-text">SOLUTION</span></a>
				
				<!-- 직원 관리 페이지 --> <c:choose>
						<c:when test="${empjob eq '관리자'}">
							<a href="emppage.pc" aria-expanded="false"
								class="header-menu-list-last"> <i
								class="icon-user menu-icon"></i> <span class="nav-text">EMPLOYEE</span>
							</a>
						</c:when>
				
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				
			</div>

			<!--**********************************
		            Menu end
		        ***********************************-->


			<div class="header-right">
				<ul class="clearfix">
					<!-- 메일박스 -->
					<li class="icons dropdown"><i class="mdi mdi-email-outline"></i>
						<!-- <span class="badge badge-pill gradient-1">3</span> --></li>
					<!-- 알림 -->
					<li class="icons dropdown"><i class="mdi mdi-bell-outline"></i>
						<!-- <span class="badge badge-pill gradient-2">3</span> --></li>

					<li class="icons">
						<c:choose>
							<c:when test="${empno != null}">
								<span id="loginUserN">${empname}</span>
								<span>&nbsp;님 환영합니다.</span>
							</c:when>

							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</li>
					<!-- 개인 아이콘 -->
					<li class="icons">
						<div class="user-img c-pointer">
							<span class="activity active"></span> <img
								src="images/user/1.png" height="40" width="40"
								alt="dropdown-toggle" data-toggle="dropdown">
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<!--**********************************
            Header end ti-comment-alt
        ***********************************-->
	
	<!--**********************************
            Content body start <Center>
        ***********************************-->
	<c:choose>

		<c:when test="${center == null }">
			<jsp:include page="itpage.jsp" />
		</c:when>
		<c:otherwise>
			<jsp:include page="${center }.jsp" />
		</c:otherwise>



	</c:choose>
	<!--**********************************
            Content body end
        ***********************************-->


	<!--**********************************
            Footer start
        ***********************************-->
	<div class="footer">
		<div class="copyright">
			<p>
				Copyright &copy; Designed & Developed by <a
					href="https://themeforest.net/user/quixlab">Quixlab</a> 2018
			</p>
		</div>
	</div>
	<!--**********************************
            Footer end
        ***********************************-->
	<!--**********************************
        Main wrapper end
    ***********************************-->

	<!--**********************************
        Scripts
    ***********************************-->


	<script src="plugins/common/common.min.js"></script>
	<script src="js/custom.min.js"></script>
	<script src="js/settings.js"></script>
	<script src="js/gleek.js?1s"></script>
	<script src="js/styleSwitcher.js"></script>
	<!-- font awesome -->
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<!-- Chartjs -->
	<script src="./plugins/chart.js/Chart.bundle.min.js"></script>
	<!-- Circle progress 
	<script src="./plugins/circle-progress/circle-progress.min.js"></script>-->


	<!-- Morrisjs -->
	<script src="./plugins/raphael/raphael.min.js"></script>
	<script src="./plugins/morris/morris.min.js"></script>
	<!-- Pignose Calender -->
	<!-- <script src="./plugins/moment/moment.min.js"></script> -->
	<!-- <script src="./plugins/pg-calendar/js/pignose.calendar.min.js"></script> -->
	<!-- ChartistJS -->
	<script src="./plugins/chartist/js/chartist.min.js"></script>
	<script
		src="./plugins/chartist-plugin-tooltips/js/chartist-plugin-tooltip.min.js"></script>

	<script src="./js/dashboard/dashboard-1.js"></script>



	<script src="./plugins/moment/moment.js"></script>

	<!-- Clock Plugin JavaScript -->
	<script src="./plugins/clockpicker/dist/jquery-clockpicker.min.js"></script>
	<!-- Color Picker Plugin JavaScript -->
	<script
		src="./plugins/jquery-asColorPicker-master/libs/jquery-asColor.js"></script>
	<script
		src="./plugins/jquery-asColorPicker-master/libs/jquery-asGradient.js"></script>
	<script
		src="./plugins/jquery-asColorPicker-master/dist/jquery-asColorPicker.min.js"></script>


	<!-- Date Picker Plugin JavaScript -->
	<script
		src="./plugins/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script
		src="./plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>
	<script src="./js/plugins-init/form-pickers-init.js"></script>
	\


	<!-- Table -->
	<script src="./plugins/tables/js/jquery.dataTables.min.js"></script>
	<!--<script src="./plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>  -->
	<script
		src="./plugins/tables/js/datatable-init/datatable-basic.min.js?12"></script>
	<script src="./plugins/tables/js/datatable-init/datatable-api.min.js"></script>
	<script
		src="./plugins/tables/js/datatable-init/datatable-styling.min.js"></script>


	<!-- Modal -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>

</html>