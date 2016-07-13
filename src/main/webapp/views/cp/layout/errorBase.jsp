<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> 
<%@ page session="false"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <meta name="description" content="">
	    <meta name="author" content="">
	
	    <title>Hệ thống hỗ trợ quản lý Khoa học và Công nghệ, ĐHBK Hà Nội</title>
	    
		<link rel="Shortcut Icon" href="<c:url value="/assets/img/icon.ico"/>" />
		
	    <!-- Bootstrap Core CSS -->
	    <link href="<c:url value="/assets/libs/bootstrap/dist/css/bootstrap.css" />" rel="stylesheet" type="text/css" media="all" />
		
	    <!-- MetisMenu CSS -->
	    <link href="<c:url value="/assets/libs/metisMenu/dist/metisMenu.css" />" rel="stylesheet" type="text/css" media="all" />
	
	    <!-- Timeline CSS -->
	    <link href="<c:url value="/assets/css/timeline.css" />" rel="stylesheet" type="text/css" media="all" />
	
	    <!-- Custom CSS -->
	    <link href="<c:url value="/assets/css/sb-admin-2.css" />" rel="stylesheet" type="text/css" media="all" />
	
	    <!-- Custom Fonts -->
	    <link href="<c:url value="/assets/libs/font-awesome/css/font-awesome.css" />" rel="stylesheet" type="text/css" media="all">
		
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
	    <!--[if lt IE 9]>
	        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
	    <![endif]-->
	    
    	<!-- jQuery -->
    	<script src="<c:url value="/assets/libs/jquery/dist/jquery.min.js"/>"></script>    
	    
	    <!-- Metis Menu Plugin JavaScript -->
	    <script src="<c:url value="/assets/libs/metisMenu/dist/metisMenu.min.js"/>"></script>
    </head>   
    <body>
    	<div id="wrapper">
	        <tiles:insertAttribute name="content" />
        </div>
    	<!-- /#wrapper -->
    	
	    <!-- Bootstrap Core JavaScript -->
	    <script src="<c:url value="/assets/libs/bootstrap/dist/js/bootstrap.js"/>"></script>
	
	    <!-- Custom Theme JavaScript -->
	    <script src="<c:url value="/assets/js/sb-admin-2.js"/>"></script>
	    
		 		
  		<!-- Set base url -->     
        <script>
            var baseUrl = '${baseUrl}';
            var assetsUrl = '${assetsUrl}';
            ${clientScript}
        </script>
    </body>
</html>