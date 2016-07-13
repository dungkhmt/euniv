<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Jquery UI CSS -->
<link href="<c:url value="/assets/css/jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/assets/css/new-style.css" />" rel="stylesheet">

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>
<script src="<c:url value="/assets/js/jquery.form-validator.js"/>"></script> 	   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thêm mới thông tin bằng sáng chế</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm mới thông tin bằng sáng chế
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status} <a href="<c:url value="${baseUrl}/cp/patents.html"/>" class="alert-link">Trở lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <spring:htmlEscape defaultHtmlEscape="true" />
                    <form:form action="${baseUrl}/cp/save-a-patent.html" method="POST" commandName="patentFormAdd" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="patentName">Tên*</label>
                                    <form:input path="patentName" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="patentName" htmlEscape="true" placeholder="Patent Name"></form:input>
    								<form:errors path="patentName" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="patentConHours">Giờ quy đổi</label>
                                    <form:input path="patentConHours" class="form-control" data-validation="custom required" data-validation-regexp="^[0-9]*[1-9][0-9]*$" data-validation-error-msg="Giá trị phải là số nguyên" name="patentConHours" placeholder="Publilcation Converted Hours"></form:input>
    								<form:errors path="patentConHours" class="alert-danger"></form:errors>
                                </div>
                              	<div class="form-group">
                                	<label for="patentType">Loại bằng sáng chế*</label>
	                                <form:select path="patentType" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="patentType" >
	                                	<c:forEach items="${patentCategoryList}" var="patentCategory">
	                                     <option value="${patentCategory.PATCAT_Code}">${patentCategory.PATCAT_Name}</option>
	                                   	</c:forEach>
	                                </form:select>
	                                <form:errors path="patentType" class="alert-danger"></form:errors>
	                            </div>
                              	<div class="form-group">
                                    <label for="patentNumber">Số hiệu</label>
                                    <form:input path="patentNumber" class="form-control" name="patentNumber" placeholder="Number"></form:input>
    								<form:errors path="patentNumber" class="alert-danger"></form:errors>
                              	</div>
                                <button type="submit" class="btn btn-primary" id="addANewPatent">Lưu</button>
                                <!-- <button type="reset" class="btn btn-primary">Clear</button> -->
                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                        </div>
	                        <div class="col-lg-6">
                        		<div class="form-group">
                                    <label for="patentAutConHours">Giờ quy đổi của người kê khai</label>
                                    <form:input path="patentAutConHours" class="form-control" data-validation="custom required" data-validation-regexp="^[0-9]*[1-9][0-9]*$" data-validation-error-msg="Giá trị phải là số nguyên" name="patentAutConHours" placeholder="Author Converted Hours"></form:input>
    								<form:errors path="patentAutConHours" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="patentDateOfIssue">Ngày xuất bản* <i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(format : YYYY-MM-DD)</i></label>
                                    <form:input path="patentDateOfIssue" class="form-control datepicker" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="patentDateOfIssue" readonly="true" placeholder="YYYY-MM-DD"></form:input>
    								<form:errors path="patentDateOfIssue" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="patentAuthors">Tác giả</label>
                                    <form:input path="patentAuthors" class="form-control" name="patentAuthors" placeholder="Authors"></form:input>
    								<form:errors path="patentAuthors" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                	<label for="patentAuthors">Năm kê khai</label>
	                                <form:select path="patentReportingAcademicDate" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="patentReportingAcademicDate" >
	                                	<c:forEach items="${patentReportingAcademicDateList}" var="patentDate">
	                                     <option value="${patentDate.ACAYEAR_Code}">${patentDate.ACAYEAR_Code}</option>
	                                   	</c:forEach>
	                                </form:select>
	                                <form:errors path="patentReportingAcademicDate" class="alert-danger"></form:errors>
	                            </div>
	                        </div>
	                        <!-- /.col-lg-6 (nested) -->
	                    </div>
	                    <!-- /.row (nested) -->
                    </form:form>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

<script type="text/javascript">
<!--
//-->
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/patents.html";
	});
	
	$.validate({
    	modules : 'date'
    });
	
	$(".datepicker").datepicker({
		changeMonth: true,
	    changeYear: true,
	    dateFormat : 'yy-mm-dd'
	});
});

</script>

