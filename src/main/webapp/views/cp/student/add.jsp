<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Thêm học viên</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/mm/save-a-student.html" method="POST" commandName="studentFormAdd" role="form" accept-charset="UTF-8">
		<div class="row">
		<c:if test="${status != null}">	              
		<div class="alert alert-success">
			${status}
		</div>
		</c:if>
			<div class="col-lg-4">
				<div class="form-group">
					<label>Họ tên</label>
					<div class='form'>
						<form:input path="studentName" class="form-control" name="studentName" type="text" placeholder="Name" value="${studentName}"></form:input>
	    				<form:errors path="studentName" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<div class="form-group">
					<label>Mã học viên</label>
					<div class='form'>
						<form:input path="studentCode" class="form-control" name="studentCode" type="text" placeholder="Name" value="${studentCode}"></form:input>
	    				<form:errors path="studentCode" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<div class="form-group">
					<label>Email</label>
					<div class='form'>
						<form:input path="studentEmail" class="form-control" name="studentEmail" type="text" placeholder="Email" value="${studentEmail}"></form:input>
	    				<form:errors path="studentEmail" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<!-- /.col-lg-4 -->
			</div>
	
			<div class="col-lg-4">
				<div class="form-group">
					<label>Lớp</label>
					<div class='form'>
						<form:select path="studentClasses" class="form-control" name="studentClasses">
							<c:forEach items="${classesList}" var="classes">
	                       		<option value="${classes.classes_Code}" >${classes.classes_Name}</option>
	                       	</c:forEach>
	                    </form:select>
	                    <form:errors path="studentClasses" class="alert-danger"></form:errors>
                    </div>
				</div>
				<div class="form-group">
					<label>Điện thoại</label>
					<div class='form'>
						<form:input path="studentPhone" class="form-control" name="studentPhone" type="text" placeholder="Phone" value="${studentPhone}"></form:input>
	    				<form:errors path="studentPhone" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<!-- /.col-lg-4 -->
			</div>
		</div>
		<!-- /.row -->
		
					
		
		<div class="row" style="padding-bottom:20px;">
			<div class="col-lg-4">
				<button type="button" class='form btn btn-primary' id="save">Lưu</button>
				<button type="button" class='form btn btn-primary' id="cancel" >Quay về</button>				
				
			</div>
			<!-- /.col-lg-4 -->
		</div>
		
		<!-- /.row -->
	</form:form>
</div>
<!-- /#page-wrapper -->

<!-- DataTables JavaScript -->
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>
	

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#dataTables-example').DataTable({
	            responsive: true,
	            "aoColumnDefs": [
	                             { 'bSortable': false, 'aTargets': [2] }
	                          ]
	    });
	    
		$('#save').click(function(){
			
			document.getElementById('form-add').submit();
		});
		
		
		$('#cancel').click(function() {
			window.location = baseUrl + "/mm/students.html";
		});	
		
	});	


</script>