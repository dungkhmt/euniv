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
			<h1 class="page-header">Danh sách giảng viên ngoài trường</h1>
			<p>
				<button type="button" class="btn btn-primary btn-xs add">Thêm mới</button>
			</p>
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
				<div class="panel-heading"><h4>Danh sách giảng viên ngoài trường</h4></div>
					
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th title="Name">Họ và tên</th>									
									<th title="AcademicRank">Học hàm học vị</th>
									<th title="University">Trường</th>
									<th title="Email">Email</th>
									<th>Chỉnh sửa</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${externalprofessorList}" var="professor">
									<tr class="gradeX">
										 <td><c:out value="${professor.EXTSTAF_Name}"/></td>
										 	<td><c:out value="${professor.academicRank.academicRank_VNAbbr}"/></td>
										 	<td><c:out value="${professor.university.university_Name}"/></td>
										 	<td><c:out value="${professor.EXTSTAF_Email}"/></td>
										 <td class="center">
											<button type="button" onclick="v_fViewDetailAProfessor(${professor.EXTSTAF_ID});" class="btn btn-info btn-xs" title="Edit">Sửa thông tin</button>
											<br/>
											<button type="button" id="removeProfessor" onclick="v_fRemoveAProfessor(${professor.EXTSTAF_ID});" class="btn btn-warning btn-xs" title="Remove">Xóa thông tin</button>
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
                             { 'bSortable': false, 'aTargets': [4] }
                          ]
    });
    
    $('.add').click(function(){
    	window.location = baseUrl+"/mm/add-an-externalprofessor.html";
    });
    
});

function v_fViewDetailAProfessor(iProfessorId){
	var sViewDetailUrl = baseUrl + "/mm/externalprofessor-detail/"+iProfessorId+".html";
	window.location = sViewDetailUrl;
}

function v_fRemoveAProfessor(iProfessorId){
	var r = confirm("Bạn có muốn xóa giảng viên này ?");
	if (r == true) {
		var sRemoveAProfessorUrl = baseUrl + "/mm/remove-an-externalprofessor/"+iProfessorId+".html";
		window.location = sRemoveAProfessorUrl;
	} else {
	    return false;
	}
}
</script>

