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
			<h1 class="page-header">Danh sách bằng sáng chế</h1>
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
				<div class="panel-heading">Danh sách bằng sáng chế</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th title="User">Người kê khai</th>
									<th title="user">Năm kê khai</th>
									<th title="Name of patent">Tên</th>
									<th title="Number of patent">Số hiệu</th>
									<th title="Type of patent">Loại</th>
									<th title="Publication converted hours">Giờ quy đổi</th>
									<th title="Authors converted hours">Giờ quy đổi của người kê khai</th>
									<th title="Authors of patent">Tác giả</th>
									<th title="Date">Ngày xuất bản</th>
									<th>Edit</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${patentsList}" var="patent">
									<tr class="gradeX">
										<td><c:out value="${patent.PAT_User_Code}"/></td>
										<td><c:out value="${patent.PAT_ReportingAcademicDate}"/></td>
										<td><c:out value="${patent.PAT_Name}"/></td>
										<td><c:out value="${patent.PAT_Number}"/></td>
										<td><c:out value="${patent.PAT_Type}"/></td>
										<td><c:out value="${patent.PAT_ConvertedHours}"/></td>
										<td><c:out value="${patent.PAT_AuthorConvertedHours}"/></td>
										<td><c:out value="${patent.PAT_Authors}"/></td>
										<td><c:out value="${patent.PAT_DateOfIssue}"/></td>
										<td class="center">
											<button type="button" onclick="v_fViewDetailAPatent(${patent.PAT_ID});" class="btn btn-info btn-xs" title="Edit">Info</button>
											<br/>
											<button type="button" id="removePatent" onclick="v_fRemoveAPatent(${patent.PAT_ID});" class="btn btn-danger btn-xs" title="Remove">Remove</button>
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
            responsive: false,
            "aoColumnDefs": [
                             { 'bSortable': false, 'aTargets': [7] }
                          ]
    });
    
    $('.add').click(function(){
    	window.location = baseUrl+"/cp/add-a-patent.html";
    });
    
});

function v_fViewDetailAPatent(iPatentId){
	var sViewDetailUrl = baseUrl + "/cp/patentdetail/"+iPatentId+".html";
	window.location = sViewDetailUrl;
}

function v_fGenerate(){
	var sGeneratingUrl = "${baseUrl}/cpservice/generate.json";
    $.ajax({
        type: "POST",
        url: sGeneratingUrl,
        //data: "id=" + the_i_StaffId,
        success: function(response) {
            if (response.status) {
                alert(response.message);
                //window.location = "${baseUrl}/cp/coursing.html";
            } else {
                alert(response.message);
            }
        },
        error: function(e) {
            alert('Error: ' + e);
        }
    });
}

function v_fRemoveAPatent(iPatentId){
	var r = confirm("Do you really want to remove this ?");
	if (r == true) {
		var sRemoveAPatentUrl = baseUrl + "/cp/remove-a-patent/"+iPatentId+".html";
		window.location = sRemoveAPatentUrl;
	} else {
	    return false;
	}
}
</script>
