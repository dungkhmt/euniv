<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>

<script
	src="<c:url value="/assets/js/source/pdf/exportPdfProfile.js"/>"></script>

<!-- Custom CSS -->
<link href="<c:url value="/assets/css/sb-admin-2.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/assets/css/source/pdf/exportPdfProfile.css" />" rel="stylesheet" type="text/css" media="all" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Lý lịch khoa học</h1>
			<p>
				<i class="fa fa-cog" aria-hidden="true"></i>
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="viewPDF" style="float: right">
			<iframe class="preview-pane" type="application/pdf" width="100%" height="600px" frameborder="0" style="position:relative;z-index:999"></iframe>
		</div>
		
		<div id="html-profile">
			<div class="main">
				<div class="title">Lý lịch khoa học <br> CHUYÊN GIA KHOA HỌC VÀ CÔNG NGHỆ</div>
				<div class="header"></div>
				<div class="body">
					<div class="content"></div>
					<div class="sign"></div>
				</div>
				<div class="footer"></div>
			</div>
		</div>
	
		
	</div>
	<!-- /.row -->
</div>
<!-- /#page-wrapper -->



	