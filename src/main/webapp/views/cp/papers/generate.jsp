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

<style>.ui-datepicker-calendar {display: none;}​</style>

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>		   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Kết xuất danh sách</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Kết xuất danh sách
                </div>
                <div class="panel-body">
                    <form:form action="${baseUrl}/cp/downloadExcel" method="POST" commandName="paperExcellForm" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
                                <%-- <div class="form-group">
                                    <label>Select a year</label>
                                    <form:input path="paperYear" class="form-control year" name="paperYear" readonly="true" placeholder="YYYY"></form:input>
                                    <form:errors path="paperYear" class="alert-danger"></form:errors>
                                </div> --%>
                                <div class="form-group">
                                	<label for="reportingAcademicDate">Năm kê khai*</label>
	                                <form:select path="reportingAcademicDate" class="form-control" name="reportingAcademicDate" >
	                                	<c:forEach items="${reportingAcademicDate}" var="reportingDate">
	                                     <option value="${reportingDate.ACAYEAR_Code}">${reportingDate.ACAYEAR_Code}</option>
	                                   	</c:forEach>
	                                </form:select>
	                                <form:errors path="reportingAcademicDate" class="alert-danger"></form:errors>
                            	</div>
                                <button type="submit" class="btn btn-primary">Kết xuất</button>
                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
                                <!-- <button type="submit" class="btn btn-default">Submit</button> -->
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
    	window.location = baseUrl+"/cp/downloadExcel";
    });
    
    $('button.cancel').click(function(){
		window.location = baseUrl+"/cp/papers.html";
	});
    
    $('.year').datepicker({
		changeMonth: false,
        changeYear: true,
        showButtonPanel: true,
        dateFormat : 'yy',
        stepMonths: 12,
		onClose: function() {
	        var iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	        $(this).datepicker('setDate', new Date(iYear, 1));
     	},
     	beforeShow: function() {
     		if ((selDate = $(this).val()).length > 0)
 	        {
 	          iYear = selDate.substring(selDate.length - 4, selDate.length);
 	          $(this).datepicker('option', 'defaultDate', new Date(iYear, 1));
 	          $(this).datepicker('setDate', new Date(iYear, 1));
 	        }
  	    }
  	});
	// Hide month when choose a year
	$("#paperYear").click(function () {
		$(".ui-datepicker-month").hide();
	});
	
	$("#paperYear").focus(function () {
        $(".ui-datepicker-calendar").hide();
        $(".ui-datepicker-month").hide();
    });
});
</script>
