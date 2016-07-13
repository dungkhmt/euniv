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
            <h1 class="page-header">Chỉnh sửa hội đồng xét duyệt đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Chỉnh thông tin hội đồng xét duyệt đề tài
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/jury-of-research-project-management.html"/>" class="alert-link">Trở lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/edit-a-jury-research-project.html" method="POST" commandName="juryResearchProjectFormEdit" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label for="juryName">Tên*</label>
	                                    <form:input path="juryResearchProjectName" class="form-control" name="juryResearchProjectName" value="${jury.JURPRJ_Name}" placeholder="Jury Ò Research Project Name"></form:input>
	    								<form:errors path="juryResearchProjectName" class="alert-danger"></form:errors>
	                                </div>
	                                                               
	                                <div class="form-group">
	                                    <label>Đợt gọi đề tài*</label>
	                                    <form:select path="projectCallCode" class="form-control" name="projectCallCode">
	                                    	<c:forEach items="${projectCalls}" var="projectCall">
		                                        <option value="${projectCall.PROJCALL_CODE}" <c:if test="${projectCall.PROJCALL_CODE == jury.JURPRJ_PROJCall_Code}">selected</c:if>  >${projectCall.PROJCALL_NAME}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="projectCallCode" class="alert-danger"></form:errors>
	                                </div>
	                                <form:hidden path="juryResearchProjectID" name="juryResearchProjectID" value="${juryResearchProjectId}"/>
	                                <button type="submit" class="btn btn-primary">Lưu</button>
	                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
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

