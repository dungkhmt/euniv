<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hệ thống hỗ trợ quản lý Khoa học và Công nghệ, ĐHBK Hà Nội</title>

	<link rel="Shortcut Icon" href="<c:url value="/assets/img/icon.ico"/>" />

    <!-- Bootstrap Core CSS -->
    <link href="<c:url value="/assets/libs/bootstrap/dist/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" media="all" />

    <!-- MetisMenu CSS -->
    <link href="<c:url value="/assets/libs/metisMenu/dist/metisMenu.min.css" />" rel="stylesheet" type="text/css" media="all" />

    <!-- Custom CSS -->
    <link href="<c:url value="/assets/css/sb-admin-2.css" />" rel="stylesheet" type="text/css" media="all" />

    <!-- Custom Fonts -->
    <link href="<c:url value="/assets/libs/font-awesome/css/font-awesome.min.css" />" rel="stylesheet" type="text/css" type="text/css" media="all">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
  <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Hệ thống hỗ trợ quản lý Khoa học và Công nghệ, ĐHBK Hà Nội</a>
    </div>
</nav>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Thông tin đăng nhập</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" name="form" action="<c:url value="/j_spring_security_check" />" method="POST">
                            <fieldset>
                            	<c:if test="${1 == failed}">
							        <div class="has-error">
							        	<span class='help-block form-error' style="text-align: center;">Thông tin đăng nhập không chính xác.<br> Vui lòng thử lại !</span>
							        </div>
							    </c:if>
                                <div class="form-group">
									<input class="form-control" type="text" name="j_username"  value="" placeholder="Tên đăng nhập" autofocus />
                                </div>
                                <div class="form-group">
                                	<input class="form-control" type="password" name="j_password"  placeholder="Mật khẩu" />
                                </div>
                                <!-- <div class="checkbox">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                    </label>
                                </div>-->
                                <!-- Change this to a button or input when using this as a form -->
                                <input class="btn btn-lg btn-success btn-block" name="submit" type="submit" value="Đăng nhập"/>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="<c:url value="/assets/libs/jquery/dist/jquery.min.js" />" ></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/assets/libs/bootstrap/dist/js/bootstrap.min.js" />" ></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="<c:url value="/assets/libs/metisMenu/dist/metisMenu.min.js" />" ></script>

    <!-- Custom Theme JavaScript -->
    <script src="<c:url value="/assets/js/sb-admin-2.js" />" ></script>

</body>

</html>
