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
			<h1 class="page-header">Chỉnh sửa thông tin</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-edit" action="${baseUrl}/mm/edit-a-partneruniversity.html" method="POST" commandName="universityFormEdit" role="form" accept-charset="UTF-8">
		<div class="row">
		<c:if test="${status != null}">	              
		<div class="alert alert-success">
			${status}
		</div>
		</c:if>
			<div class="col-lg-4">
				<div class="form-group">
					<label>Tên trường</label>
					<div class='form'>
						<form:input path="universityName" class="form-control" name="universityName" type="text" placeholder="Name" value="${universityName}"></form:input>
						<form:errors path="universityName" class="alert-danger"></form:errors>
						<form:input path="universityID" name="universityID" type="hidden" value="${universityID}"></form:input>
    				</div>
				</div>
				
				<!-- /.col-lg-4 -->
			</div>
	
			<div class="col-lg-4">
			
				<div class="form-group">
					<label>Ký hiệu</label>
						<div class='form'>
						<form:input path="universityCode" class="form-control" name="universityCode" type="text" placeholder="Name" value="${universityCode}"></form:input>
	    				<form:errors path="universityCode" class="alert-danger"></form:errors>
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
			document.getElementById('form-edit').submit();
		});
		
		
		$('#cancel').click(function() {
			window.location = baseUrl + "/mm/partnerUniversities.html";
		});	
		
	});
</script>