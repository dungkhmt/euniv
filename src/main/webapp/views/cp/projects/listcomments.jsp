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
			<h1 class="page-header">Tổng hợp comments phản biện đề tài</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Tổng hợp comments phản biện đề tài</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>STT</th>
									<th>Tên đề tài</th>
									<th>Chủ nhiệm</th>
									<th>Đợt gọi</th>
									<th>Xem và phê duyệt</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="0" scope="page" />
								<c:forEach items="${projectsList}" var="project">
									<tr class="gradeX">
										<c:set var="count" value="${count + 1}" scope="page"/> 
										<td><c:out value="${count}"/></td>
										<td><c:out value="${project.PROJ_Name}"/></td>
										<td><c:out value="${project.PROJ_User_Code}"/></td>
										<td><c:out value="${project.PROJ_PRJCall_Code}"/></td>
										<td class="center">
											<button type="button" onclick="v_fViewDetailAProject(${project.PROJ_ID});" class="btn ${(project.PROJ_Status_Code == 'APPROVED') ? 'btn-warning' : (project.PROJ_Status_Code == 'REJECT' ? 'btn-danger' : 'btn-primary')} btn-xs" title="${(project.PROJ_Status_Code == 'APPROVED') ? 'Đã chấp nhận' : (project.PROJ_Status_Code == 'REJECT' ? 'Đã bị loại' : 'Chỉnh sửa')}">Xem và phê duyệt</button>
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
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
            responsive: false,
            "aoColumnDefs": [
                             { 'bSortable': false, 'aTargets': [2] }
                          ]
    });
});

function v_fViewDetailAProject(iProjectId){
	var sViewDetailUrl = baseUrl + "/cp/projectcommentsdetail/"+iProjectId+".html";
	window.location = sViewDetailUrl;
}

</script>
