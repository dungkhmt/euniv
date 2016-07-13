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
			<h1 class="page-header">Danh sách học viên cao học chưa được phân đề tài</h1>
			
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<c:if test="${status != null}">	              
				<div class="alert alert-success">
					${status}
				</div>
			</c:if>
			<div class="panel panel-default">
				<div class="panel-heading"><h4>Danh sách học viên</h4></div>
					
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th title="Name">Họ và tên</th>
									<th title="Code">MSSV</th>
									<th title="Department">Lớp</th>
									<th title="Email">Viện</th>
									<th>Chỉnh sửa</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${studentList}" var="student">
									<tr class="gradeX">
										 <td><c:out value="${student.student_Name}"/></td>
										 <td><c:out value="${student.student_Code}"/></td>
										 <td><c:out value="${student.masterClass.classes_Name}"/></td>
										 <td><c:out value="${student.masterClass.faculty.faculty_Name}"/></td>
										 <td class="center">
											<button type="button" onclick="AssignThesis(${student.student_ID});" class="btn btn-info btn-xs" title="Assign">Phân đề tài</button>											
										</td>
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
    $('#dataTables-example').DataTable({
            responsive: true,
            "aoColumnDefs": [
                             { 'bSortable': false, 'aTargets': [3] }
                          ]
    });  
    
});

function AssignThesis(studentId){
	var sViewDetailUrl = baseUrl + "/mm/assignthesis/"+studentId+".html";
	window.location = sViewDetailUrl;
}


</script>

