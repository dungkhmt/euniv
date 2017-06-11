<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/assets/css/sb-admin-2.css" />" rel="stylesheet" type="text/css" media="all" />
<link href="<c:url value="/assets/css/source/appliedprojects/appliedprojects.css" />" rel="stylesheet" type="text/css" media="all" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Công trình nghiên cứu</h1>
			<p>
				<button type="button" class="btn btn-primary btn-xs add" onclick="newAppliedProjects()"  data-toggle="modal" data-target="#edit-appliedprojects">Thêm mới</button>
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		
		<!-- Modal -->
		<div class="modal fade" id="edit-appliedprojects" role="dialog">
		  <div class="modal-dialog">
		  
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Modal Header</h4>
		      </div>
		      <div class="modal-body">
		        <p>Some text in the modal.</p>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		    
		  </div>
		</div>
		
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading"><h4>Công trình nghiên cứu</h4></div>
					
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-view-appliedprojects">
							<thead>
								<tr>
									<th>ID</th>
									<th>User</th>
									<th>Tên công trình</th>
									<th>Hình thức, quy mô</th>
									<th>Thời gian</th>
									<th>StaffCode</th>
									<th style="display: -webkit-inline-box"></th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
					<!-- /.table-responsive -->

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

<!-- Custom JS -->
<script
	src="<c:url value="/assets/js/source/appliedprojects/appliedprojects.js"/>"></script>

<!-- DataTables JavaScript -->
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

	