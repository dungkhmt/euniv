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
<link href="<c:url value="/assets/css/source/supervision/supervision.css" />" rel="stylesheet" type="text/css" media="all" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Danh sách hướng dẫn nghiên cứu sinh/học viên cao học</h1>
			<p>
				<button type="button" class="btn btn-primary btn-xs add" onclick="newSupervision()"  data-toggle="modal" data-target="#edit-supervision">Thêm mới</button>
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		
		<!-- Modal -->
		<div class="modal fade" id="edit-supervision" role="dialog">
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
				<div class="panel-heading"><h4>Danh sách hướng dẫn nghiên cứu sinh/học viên cao học</h4></div>
				
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-view-supervision">
							<thead>
								<tr>
									<th>ID</th>
									<th>Tên nghiên cứu sinh</th>
									<th>Chức danh</th>
									<th>Đơn vị công tác</th>
									<th>Tên đề tài</th>
									<th>Mã chuyên ngành</th>
									<th>Thời gian đào tạo</th>
									<th>Năm bảo vệ</th>
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
	src="<c:url value="/assets/js/source/supervision/supervision.js"/>"></script>

<!-- DataTables JavaScript -->
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

	