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
			<h1 class="page-header">Danh sách đề tài phản biện</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh sách đề tài phản biện </div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>STT</th>
									<th>Tên đề tài</th>
									<th>Edit</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="0" scope="page" />
								<c:forEach items="${projectList}" var="project">
									<tr class="gradeX">
										<c:set var="count" value="${count + 1}" scope="page"/>  
										<td><c:out value="${count}"/></td>
										<td><c:out value="${project.PROJ_Name}"/></td>
										<td class="center">
											<button type="button" onclick="v_fViewDetailAProjectCall(${project.PROJ_ID});" class="btn btn-info btn-xs" title="Edit"> Xem </button>
											<br>
											<button type="button" onclick="v_fViewDetailPDFAProject(${project.PROJ_ID});" class="btn btn-warning btn-xs" title="Edit"> PDF </button>
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

function v_fViewDetailAProjectCall(iProjectCallId){
	var sViewDetailUrl = baseUrl + "/cp/add-comments-of-submitted-projects/"+iProjectCallId+".html";
	window.location = sViewDetailUrl;
}
function v_fViewDetailPDFAProject(iProjectId){
	var sViewDetailUrl = baseUrl + "/cp/generatepdf/"+iProjectId+".html";
	window.location = sViewDetailUrl;
}


function v_fRemoveAProjectCall(iProjectCallId){
	var r = confirm("Do you really want to remove this ?");
	if (r == true) {
		var sRemoveUrl = baseUrl + "/cp/remove-a-projectcall/"+iProjectCallId+".html";
		window.location = sRemoveUrl;
	} else {
	    return false;
	}
}
</script>
