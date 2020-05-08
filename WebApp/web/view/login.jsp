<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="h-100" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>SmartWHS</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="../../assets/images/favicon.png">
    <!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous"> -->
    <link href="css/style.css?2dsds" rel="stylesheet">
    <!-- JQuery -->
	<script src="https://code.jquery.com/jquery-latest.js"></script>
    <style>
    body {
    	background-image: url('images/warehouse.png');
    	background-repeat: no-repeat;
    	background-position: center;
    	background-size : cover;
    }
    .card .card-body {
		box-shadow: 3px 3px 3px black;
	}
    .card-main-title{
    	text-align: center;
    }
    
    </style>
</head>

<body class="h-100">
    
    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="loader">
            <svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
            </svg>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->

    
    <div class="login-form-bg h-100">
        <div class="container h-100">
            <div class="row justify-content-center h-100">
                <div class="col-xl-6">
                    <div class="form-input-content">
                        <div class="card login-form mb-0">
                            <div class="card-body pt-5">
                                <div class="card-main-title">
                                	<h3><img src="images/MySWM.png" alt="" height="43px"> </h3>
        						</div>
                                <form class="mt-5 mb-5 login-input" method="post" action="login.pc">
                                    <div class="form-group">
                                        <input type="text" name="empno" id="empno" class="form-control" placeholder="Employee ID">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="emppw" id="emppw" class="form-control" placeholder="Password">
                                    </div>
                                    <span class="error-msg text-danger">
                                    	<c:choose>
                                    		<c:when test="${error == null }"> </c:when>
                                    		<c:otherwise>${error}</c:otherwise>
                                    	</c:choose>
                                    </span>
                                    <button class="btn login-form__btn submit w-100">Sign In</button>
                                    
                                </form>
                                <p class="mt-5 login-form__footer">비밀번호가 기억나지 않는다면  <a href="page-register.html" class="text-primary">관리자</a> 에게 문의해주세요.</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    

    <!--**********************************
        Scripts
    ***********************************-->
    <script src="plugins/common/common.min.js"></script>
    <script src="js/custom.min.js"></script>
    <script src="js/settings.js"></script>
    <script src="js/gleek.js"></script>
    <script src="js/styleSwitcher.js"></script>
    <script>
	    $(document).ready(function() {
			history.replaceState({}, null, location.pathname);
		});
    </script>
</body>
</html>





