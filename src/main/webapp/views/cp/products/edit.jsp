<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
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
            <h1 class="page-header">Cập nhật thông tin đăng ký chuyên đề</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Cập nhật thông tin đăng ký chuyên đề
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/products.html"/>" class="alert-link">Quay lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/edit-a-product.html" method="POST" commandName="productFormEdit" role="form" enctype="multipart/form-data">
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label>Đề tài*</label>
	                                    <form:select path="productCatCode" class="form-control" name="productCatCode">
	                                    	<c:forEach items="${threadsList}" var="aThread">
		                                        <option value="${aThread.PROJ_Code}" <c:if test="${aThread.PROJ_Code == productThreadCode}">selected</c:if>>${aThread.PROJ_Name}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="productCatCode" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="productName">Tên chuyên đề*</label>
	                                    <form:input path="productName" class="form-control" value="${productName}" name="productName" placeholder="Project Name"></form:input>
	    								<form:errors path="productName" class="alert-danger"></form:errors>
	                                </div>
	                            	<div class="form-group">
	                                    <label for="productStatus">Trạng thái</label>
	                                    <form:select path="productStatus" class="form-control" name="productStatus">
	                                    	<c:forEach items="${projectStatuses}" var="productStat">
		                              			<c:if test="${productStat.PROJSTAT_Code=='SUBMIT'}">
		                                        <option value="${productStat.PROJSTAT_Code}" <c:if test="${productStat.PROJSTAT_Code == productStatus}">selected</c:if>>${productStat.PROJSTAT_Description}</option>
	                                       		</c:if>
	                                       	</c:forEach>
	                                    </form:select>
	    								<form:errors path="productStatus" class="alert-danger"></form:errors>
                                	</div>
                                	<div class="form-group">
	                                    <label for="productBudget">Kinh phí (triệu VNĐ)</label>
	                                    <form:input path="productBudget" class="form-control" value="${productBudget}" name="productBudget" placeholder="Budget"></form:input>
	    								<form:errors path="productBudget" class="alert-danger"></form:errors>
                                	</div>
                                	<form:hidden path="productId" name="productId" value="${productId}"/>
	                            	<form:hidden path="productCode" name="productCode" value="${productCode}"/>
	                                <button type="submit" class="btn btn-primary" id="editAProduct">Lưu</button>
	                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                        </div>
	                        <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="productStartDate">Ngày bắt đầu*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
                                    <form:input path="productStartDate" class="form-control year" value="${productStartDate}" name="productStartDate" readonly="true" placeholder="Start date"></form:input>
    								<form:errors path="productStartDate" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="productEndDate">Ngày kết thúc*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
                                    <form:input path="productEndDate" class="form-control year" value="${productEndDate}" name="productEndDate" readonly="true" placeholder="End date"></form:input>
    								<form:errors path="productEndDate" class="alert-danger"></form:errors>
                                </div>
                                
                                <!--  
                                <div class="form-group">
                                    <label for="productSourceFile">File chứng thực<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(File size is 20 MB maximum)</i></label>
                                    <form:input path="productSourceFile" name="productSourceFile" type="file" placeholder="Source File"></form:input>
    								<form:errors path="productSourceFile" class="alert-danger"></form:errors>
                                </div>
                                -->
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
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/products.html";
	});
	
	$('#productStartDate').datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 1,
	      onClose: function( selectedDate ) {
	        $( "#productEndDate" ).datepicker( "option", "minDate", selectedDate );
	      }
  	});
    $("#productEndDate").datepicker({
       defaultDate: "+1w",
       changeMonth: true,
       numberOfMonths: 1,
       onClose: function( selectedDate ) {
         $( "#productStartDate" ).datepicker( "option", "maxDate", selectedDate );
       }
    });
});

</script>

