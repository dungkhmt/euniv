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
            <h1 class="page-header">Chỉnh sửa đợt gọi đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Chỉnh sửa đợt đề tài
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/project-call-open.html"/>" class="alert-link">Trở lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/edit-a-projectcall.html" method="POST" commandName="projectCallFormEdit" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label for="topicName">Tên*</label>
	                                    <form:input path="projectCallName" class="form-control" name="projectCallName" value="${projectCalls.PROJCALL_NAME}" placeholder="Project Call Name"></form:input>
	    								<form:errors path="projectCallName" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group" style="visibility:hidden">
	                                    <label for="topicName">Mã đợt gọi đề tài*</label>
	                                    <form:input path="projectCallCode" class="form-control" name="projectCallCode" value="${projectCalls.PROJCALL_CODE}" placeholder="Project Call Code"></form:input>
	    								<form:errors path="projectCallCode" class="alert-danger"></form:errors>
	                                </div>
	                                
	                                <div class="form-group" style="visibility:hidden">
	                                    <label>Nhóm*</label>
	                                    <form:select path="projectCallCatCode" class="form-control" name="projectCallCatCode">
	                                    	<c:forEach items="${topicCategory}" var="topicCat">
		                                        <option value="${topicCat.PROJCAT_Code}" <c:if test="${topicCat.PROJCAT_Code == projectCalls.PROJCALL_PROJCATCODE}">selected</c:if>  >${topicCat.PROJCAT_Name}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="projectCallCatCode" class="alert-danger"></form:errors>
	                                </div>
	                                <form:hidden path="projectCallId" name="projectCallId" value="${projectCallId}"/>
	                                <button type="submit" class="btn btn-primary">Lưu</button>
	                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                        </div>
	                        <div class="col-lg-6">
                            	<div class="form-group">
                                    <label for="projectCallDate">Ngày gọi*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
                                    <form:input path="projectCallDate" class="form-control year" name="projectCallDate" value="${sProjectCallDate}" readonly="true" placeholder="Project Call Date"></form:input>
    								<form:errors path="projectCallDate" class="alert-danger"></form:errors>
                                </div>
	                        </div>
	                        <div class="col-lg-6">
		                        <div class="form-group">
	                                 <label>Trạng thái*</label>
	                                 <form:select path="projectCallStatus" class="form-control" name="projectCallStatus">
	                                      <option value="OPEN_FOR_SUBMISSION" <c:if test="${'OPEN_FOR_SUBMISSION' == projectCalls.PROJCALL_STATUS}">selected</c:if>  >Mở cho phép đăng ký mới</option>
	                                      <option value="SUBMISSION_CLOSED" <c:if test="${'SUBMISSION_CLOSED' == projectCalls.PROJCALL_STATUS}">selected</c:if>  >Đóng, không thể đăng ký mới</option>
	                                      <option value="OPEN_FOR_REVISE" <c:if test="${'OPEN_FOR_REVISE' == projectCalls.PROJCALL_STATUS}">selected</c:if>  >Mở, cho phép chủ nhiệm đề tài chỉnh sửa</option>
	                                      <option value="REVISE_CLOSED" <c:if test="${'REVISE_CLOSED' == projectCalls.PROJCALL_STATUS}">selected</c:if>  >Đóng, không cho phép chủ nhiệm đề tài chỉnh sửa đề tài</option>
	                                 </form:select>
	                                 <form:errors path="projectCallStatus" class="alert-danger"></form:errors>
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

