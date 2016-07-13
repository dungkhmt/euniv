<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">
	   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Kết xuất excel mẫu 01CN-02CN</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Kết xuất
                </div>
                <div class="panel-body">
                    <form:form action="${baseUrl}/cp/generateExcel01CN02CN" method="POST" commandName="generateExcelForm01CN02CN" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
	                        	<div class="form-group">
                            		<label for="staffCode">Cán bộ</label>
                            	    <form:select path="staffCode" class="form-control" name="staffCode" >
	                                	<c:forEach items="${listStaffs}" var="aStaff">
	                                     <option value="${aStaff.staff_Code}">${aStaff.staff_Name}</option>
	                                   	</c:forEach>
	                                </form:select>
	                                <form:errors path="staffCode" class="alert-danger"></form:errors>
                            	</div>
                            	<div class="form-group">
                            		<label for="reportingAcademicDate">Năm kê khai*</label>
	                                <form:select path="reportingAcademicDate" class="form-control" name="reportingAcademicDate" >
	                                	<c:forEach items="${reportingYear}" var="reportingDate">
	                                     <option value="${reportingDate.ACAYEAR_Code}">${reportingDate.ACAYEAR_Code}</option>
	                                   	</c:forEach>
	                                </form:select>
	                                <form:errors path="reportingAcademicDate" class="alert-danger"></form:errors>
                            	</div>
                                <button type="submit" class="btn btn-primary">Kết xuất</button>
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

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
$(document).ready(function() {
    $('.generate').click(function(){
    	window.location = baseUrl+"/cp/summaryExcelKV03";
    });
    
    $('button.cancel').click(function(){
		window.location = baseUrl+"/cp/papers.html";
	});
});
</script>
