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

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tổng hợp danh sách sách giáo trình</h1>
			<p>
				<button type="button" class="btn btn-primary btn-xs back">Quay lại</button>
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Tổng hợp danh sách sách giáo trình</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>Người kê khai</th>
									<th>Năm kê khai</th>
									<th>Tên sách</th>
									<th>Nhà xuất bản</th>
									<th>Năm xuất bản</th>
									<th>Tác giả</th>
									<th>ISBN</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${bookList}" var="book">
									<tr class="gradeX">
										<td><c:out value="${book.BOK_UserCode}"/></td>
										<td><c:out value="${book.BOK_ReportingAcademicDate }"/></td>
										<td><c:out value="${book.BOK_BookName}"/></td>
										<td><c:out value="${book.BOK_Publisher}"/></td>
										<td><c:out value="${book.BOK_PublishedMonth}"/> - <c:out value="${book.BOK_PublishedYear}"/></td>
										<td><c:out value="${book.BOK_Authors}"/></td>
										<td><c:out value="${book.BOK_ISBN}"/></td>
									</tr>
								</c:forEach>
							</tbody>
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

<!-- DataTables JavaScript -->
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable();
    
    $('.back').click(function(){
    	window.location = baseUrl+"/cp/summary-books.html";
    });
});
</script>
