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
			<h1 class="page-header">Hội đồng xét duyệt  đề tài</h1>
			<p>
				<button type="button" class="btn btn-primary btn-xs add">Thêm mới</button>
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh sách hội đồng xét duyệt đề tài</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>Tên hội đồng</th>
									<th>Đợt gọi đề tài</th>
									<th>Người lập</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${juries}" var="jury">
									<tr class="gradeX">
										<td><c:out value="${jury.JURPRJ_Name}"/></td>
										<td><c:out value="${jury.JURPRJ_PROJCall_Code}"/></td>
										<td><c:out value="${jury.JURPRJ_UserCode}"/></td>
										
										<td class="center">
											<button type="button" onclick="v_fViewDetailAJuryResearchProject(${jury.JURPRJ_ID});" class="btn btn-info btn-xs" title="Edit">Chi tiết</button>
											<br/>
											<button type="button" id="removeTopic" onclick="v_fRemoveAJuryResearchProject(${jury.JURPRJ_ID});" class="btn btn-danger btn-xs" title="Remove">Xoá</button>
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
                             { 'bSortable': false, 'aTargets': [3] }
                          ]
    });
    
    $('.add').click(function(){
    	window.location = baseUrl+"/cp/add-a-jury-research-project.html";
    });
});

function v_fViewDetailAJuryResearchProject(Id){
	var sViewDetailUrl = baseUrl + "/cp/jury-research-project-detail/"+Id+".html";
	window.location = sViewDetailUrl;
}


function v_fRemoveAJuryResearchProject(Id){
	var r = confirm("Bạn có muốn xóa bản ghi này ?");
	if (r == true) {
		var sRemoveUrl = baseUrl + "/cp/remove-a-jury-research-project/"+Id+".html";
		window.location = sRemoveUrl;
	} else {
	    return false;
	}
}
</script>
