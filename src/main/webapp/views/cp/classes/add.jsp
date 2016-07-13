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
			<h1 class="page-header">Thêm lớp</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/mm/save-a-class.html" method="POST" commandName="ClassesFormAdd" role="form" accept-charset="UTF-8">
		<div class="row">
		<c:if test="${status != null}">	              
		<div class="alert alert-success">
			${status}
		</div>
		</c:if>
			<div class="col-lg-4">
				<div class="form-group">
					<label>Tên</label>
					<div class='form'>
						<form:input path="className" class="form-control" name="className" type="text" placeholder="Name"></form:input>
	    				<form:errors path="className" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				
			</div>
			
			<div class="col-lg-4">
				<div class="form-group">
					<label>Mã</label>
					<div class='form'>
						<form:input path="classCode" class="form-control" name="classCode" type="text" placeholder="Code"></form:input>
	    				<form:errors path="classCode" class="alert-danger"></form:errors>
    				</div>
				</div>
			</div>
			
			<div class="col-lg-4">
				<div class="form-group">
					<label>Năm bắt đầu</label>
					<div class='form'>
						<form:input path="classYear" class="form-control" name="classYear" type="text" placeholder="Year"></form:input>
	    				<form:errors path="classYear" class="alert-danger"></form:errors>
    				</div>
				</div>
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
			window.location = baseUrl + "/mm/classes.html";
		});	
		
	});	


</script>