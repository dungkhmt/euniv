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

<!-- <style>.ui-datepicker-calendar {display: none;}​</style> -->

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>	   
<script src="<c:url value="/assets/js/jquery.form-validator.js"/>"></script> 
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Chỉnh sửa đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Chỉnh sửa đề tài
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/topics.html"/>" class="alert-link">Trở lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/edit-a-topic.html" method="POST" commandName="topicFormEdit" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label>Nhóm*</label>
	                                    <form:select path="topicCatCode" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc"  name="topicCatCode">
	                                    	<c:forEach items="${topicCategory}" var="topicCat">
		                                        <option value="${topicCat.PROJCAT_Code}" <c:if test="${topicCat.PROJCAT_Code == topicCatCode}">selected</c:if> >${topicCat.PROJCAT_Name}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="topicCatCode" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="topicName">Tên*</label>
	                                    <form:input path="topicName" class="form-control" value="${topic.PROJDECL_Name}" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="topicName" placeholder="Project Name"></form:input>
	    								<form:errors path="topicName" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="topicConHours">Giờ quy đổi*</label>
	                                    <form:input path="topicConHours" class="form-control" value="${topic.PROJDECL_ConvertedHours}" data-validation="custom required" data-validation-regexp="^[0-9]*[1-9][0-9]*$" data-validation-error-msg="Giá trị phải là số nguyên" name="topicConHours" placeholder="Publilcation Converted Hours"></form:input>
	    								<form:errors path="topicConHours" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                	<label for="topicReportingAcademicDate">Năm kê khai*</label>
		                                <form:select path="topicReportingAcademicDate" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="topicReportingAcademicDate" >
		                                	<c:forEach items="${topicReportingAcademicDate}" var="topicDate">
		                                     <option value="${topicDate.ACAYEAR_Code}" <c:if test="${topicDate.ACAYEAR_Code == reportingDate}">selected</c:if> >${topicDate.ACAYEAR_Code}</option>
		                                   	</c:forEach>
		                                </form:select>
		                                <form:errors path="topicReportingAcademicDate" class="alert-danger"></form:errors>
	                            	</div>
	                            	<div class="form-group">
	                                	<label for="topicMemberRole">Vai trò*</label>
		                                <form:select path="topicMemberRole" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="topicMemberRole" >
		                                	<c:forEach items="${memberRolesList}" var="memberRole">
		                                     <option value="${memberRole.PROJPARTIROLE_Code}" <c:if test="${topicDate.PROJDECL_RoleCode == memberRole.PROJPARTIROLE_Code}">selected</c:if> >${memberRole.PROJPARTIROLE_Description}</option>
		                                   	</c:forEach>
		                                </form:select>
		                                <form:errors path="topicMemberRole" class="alert-danger"></form:errors>
	                            	</div>
	                            	<div class="form-group">
	                                    <label for="topicSponsor">Cơ quan tài trợ*</label>
	                                    <form:input path="topicSponsor" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" value="${topic.PROJDECL_Sponsor}" name="topicSponsor" placeholder="Sponsor"></form:input>
	    								<form:errors path="topicSponsor" class="alert-danger"></form:errors>
	                                </div>
	                                <form:hidden path="topicId" name="topicId" value="${topicId}"/>
	                                <button type="submit" class="btn btn-primary">Lưu</button>
	                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                        </div>
	                        <div class="col-lg-6">
                        		<div class="form-group">
                                    <label for="topicAutConHours">Giờ quy đổi của người kê khai</label>
                                    <form:input path="topicAutConHours" class="form-control" value="${topic.PROJDECL_AuthorConvertedHours}" data-validation="custom required" data-validation-regexp="^[0-9]*[1-9][0-9]*$" data-validation-error-msg="Giá trị phải là số nguyên" name="topicAutConHours" placeholder="Author Converted Hours"></form:input>
    								<form:errors path="topicAutConHours" class="alert-danger"></form:errors>
                                </div>
                                
                                <%-- <div class="form-group">
                                    <label for="topicYear">Năm bắt đầu*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(format : YYYY)</i></label>
                                    <form:input path="topicYear" class="form-control" value="${topic.PROJDECL_Year}" data-validation="required date" data-validation-format="yyyy" data-validation-error-msg="Giá trị phải là năm" name="topicYear" placeholder="YYYY"></form:input>
    								<form:errors path="topicYear" class="alert-danger"></form:errors>
                                </div>
                                 --%>
                                <div class="form-group">
                                    <label for="budget">Kinh phí (triệu VNĐ)</label>
                                    <form:input path="budget" class="form-control" value="${topic.PROJDECL_Budget}" data-validation="custom" data-validation-optional="true" data-validation-regexp="^[0-9]*[1-9][0-9]*$" data-validation-error-msg="Giá trị phải là số nguyên" name="budget" placeholder="Budget"></form:input>
    								<form:errors path="budget" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="topicStartDate">Ngày bắt đầu*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(format : DD/MM/YYYY)</i></label>
                                    <form:input path="topicStartDate" class="form-control " value="${topic.PROJDECL_StartDate}" name="topicStartDate" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" readonly="true" placeholder="DD/MM/YYYY"></form:input>
    								<form:errors path="topicStartDate" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="topicEndDate">Ngày kết thúc*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(format : DD/MM/YYYY)</i></label>
                                    <form:input path="topicEndDate" class="form-control " value="${topic.PROJDECL_EndDate}" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="topicYear" readonly="true" placeholder="DD/MM/YYYY"></form:input>
    								<form:errors path="topicEndDate" class="alert-danger"></form:errors>
                                </div>
                                <%-- <div class="form-group">
                                    <label for="topicApproveUser">Người phê duyệt*</label>
                                    <form:input path="topicApproveUser" class="form-control" value="${topic.PROJDECL_ApproveUserCode}" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="topicApproveUser" placeholder="Approver"></form:input>
    								<form:errors path="topicApproveUser" class="alert-danger"></form:errors>
                                </div> --%>
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
		window.location = baseUrl+"/cp/topics.html";
	});
	
	$.validate({
    	modules : 'date'
    });
        
	$('#topicStartDate').datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 1,
	      dateFormat : 'dd/mm/yy',
	      onClose: function( selectedDate ) {
	        $( "#topicEndDate" ).datepicker( "option", "minDate", selectedDate );
	      }
	});
  	$("#topicEndDate").datepicker({
	     defaultDate: "+1w",
	     changeMonth: true,
	     numberOfMonths: 1,
	     dateFormat : 'dd/mm/yy',
	     onClose: function( selectedDate ) {
	       $( "#topicStartDate" ).datepicker( "option", "maxDate", selectedDate );
	     }
   	});
});
</script>

