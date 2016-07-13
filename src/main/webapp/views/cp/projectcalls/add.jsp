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

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>	   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thêm mới đợt gọi đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm mới đợt gọi đề tài
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/project-call-open.html"/>" class="alert-link">Quay lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/save-a-projectcall.html" method="POST" commandName="projectCallFormAdd" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label for="topicName">Tên*</label>
	                                    <form:input path="projectCallName" class="form-control" name="projectCallName" placeholder="Project Call Name"></form:input>
	    								<form:errors path="projectCallName" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                	<label for="topicName">Mã đợt gọi*</label>
	                                	<form:input path="projectCallCode" class="form-control" name="projectCallCode" placeholder="Project Call Code"></form:input>
	    								<form:errors path="projectCallCode" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group" style="visibility:hidden">
	                                    <label>Nhóm*</label>
	                                    <form:select path="projectCallCatCode" class="form-control" name="projectCallCatCode">
	                                    	<c:forEach items="${topicCategory}" var="topicCat">
		                                        <option value="${topicCat.PROJCAT_Code}">${topicCat.PROJCAT_Name}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="projectCallCatCode" class="alert-danger"></form:errors>
	                                </div>
	                                <form:hidden path="projectCallStatus" name="projectCallStatus" value="OPEN_FOR_SUBMISSION"></form:hidden>
	                                <button type="submit" class="btn btn-primary" id="addANewProjectCall">Lưu</button>
	                                <!-- <button type="reset" class="btn btn-primary">Clear</button> -->
	                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                        </div>
	                        <div class="col-lg-6">
                            	<div class="form-group">
                                    <label for="projectCallDate">Ngày gọi*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
                                    <form:input path="projectCallDate" class="form-control year" name="projectCallDate" readonly="true" placeholder="Project Call Date"></form:input>
    								<form:errors path="projectCallDate" class="alert-danger"></form:errors>
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
		window.location = baseUrl+"/cp/project-call-open.html";
	});
	
	$('#projectCallDate').datepicker({
		changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat : 'dd/mm/yy',
        stepMonths: 12});
});

</script>

