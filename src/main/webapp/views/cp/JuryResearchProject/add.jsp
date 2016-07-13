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
            <h1 class="page-header">Thêm mới hội đồng xét duyệt đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm mới hội đồng xét duyệt đề tài
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/jury-of-research-project-management.html"/>" class="alert-link">Quay lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/save-a-jury-research-project.html" method="POST" commandName="juryResearchProjectFormAdd" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label for="topicName">Tên*</label>
	                                    <form:input path="juryResearchProjectName" class="form-control" name="juryResearchProjectName" placeholder="Jury Of Research Project Name"></form:input>
	    								<form:errors path="juryResearchProjectName" class="alert-danger"></form:errors>
	                                </div>
	                               
	                               
	                                <button type="submit" class="btn btn-primary" id="addAJuryOfResearchProject">Lưu</button>
	                                <!-- <button type="reset" class="btn btn-primary">Clear</button> -->
	                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                        </div>
	                        <div class="col-lg-6">
                            	<div class="form-group">
                                    <label for="projectCall">Đợt gọi đề tài*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
                                    <form:select path="projectCallCode" class="form-control" name="projectCallCode">
	                                    	<c:forEach items="${projectCalls}" var="projectCall">
		                                        <option value="${projectCall.PROJCALL_CODE}">${projectCall.PROJCALL_NAME}</option>
	                                       	</c:forEach>
	                                    </form:select>
    								<form:errors path="projectCallCode" class="alert-danger"></form:errors>
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

