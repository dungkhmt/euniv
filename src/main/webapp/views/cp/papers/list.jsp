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
			<h1 class="page-header">Danh sách bài báo</h1>
			<p>
				<button type="button" class="btn btn-primary btn-xs add">Thêm mới</button>
				<!-- <button type="button" class="btn btn-primary btn-xs gen">Kết xuất danh sách</button> -->
				<!-- <a href="./downloadExcel">Download Excel</a> -->
				<!-- <a href="./paperExcel2007">Download Excel Document 2007</a>  -->
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh sách bài báo</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th title="user">Người kê khai</th>
									<th title="user">Năm kê khai</th>
									<th title="List all authors">Tác giả</th>
									<th title="Name of a paper">Tên</th>
									<th title="Category of a paper">Nhóm</th>
									<th title="Journal Name of a paper">Tên tạp chí</th>
									<th title="Volumn of a paper">Vol</th>
									<th title="Public Year of a paper">Năm</th>
									<th title="ISSN Index of a paper">ISSN</th>
									<th title="Journal Index of a paper">Index</th>
									<!-- <th title="Publication converted hours">Giờ quy đổi</th> -->
									<th title="Authors converted hours">Giờ quy đồi</th>
									<th>Edit</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${papersList}" var="paper">
									<tr class="gradeX">
										<td><c:out value="${paper.PDECL_User_Code}"/></td>
										<td><c:out value="${paper.PDECL_ReportingAcademicDate}"/></td>
										
										<td><c:out value="${paper.PDECL_AuthorList}"/></td>
										<td><c:out value="${paper.PDECL_PublicationName}"/></td>
										<td><c:out value="${paper.paperCategory.PCAT_Name}"/></td>
										<td><c:out value="${paper.PDECL_JournalConferenceName}"/></td>
										<td><c:out value="${paper.PDECL_Volumn}"/></td>
										<td><c:out value="${paper.PDECL_Year}"/></td>
										<td><c:out value="${paper.PDECL_ISSN}"/></td>
										<td><c:out value="${paper.PDECL_IndexCode}"/></td>
										<%-- <td><c:out value="${paper.PDECL_PublicationConvertedHours}"/></td> --%>
										<td><c:out value="${paper.PDECL_AuthorConvertedHours}"/></td>
										<td class="center">
											<button type="button" onclick="v_fViewDetailAPaper(${paper.PDECL_ID});" class="btn btn-info btn-xs" title="Edit">Chi tiết</button>
											<br>
											<button type="button" id="removePaper" onclick="v_fRemoveAPaper(${paper.PDECL_ID});" class="btn btn-danger btn-xs" title="Remove">Xoá</button>
											<br>
											<c:choose>
					                            <c:when test="${paper.PDECL_SourceFile != '' && paper.PDECL_SourceFile != null}">
					                                <a href="<c:url value="${baseUrl}/cp/download-paper/${paper.PDECL_ID}.html"/>" title="Download file xác thực" class="btn btn-success btn-xs">Download</a>
					                            </c:when>
					                            <c:otherwise>
					                                <a href="#" title="Download file xác thực" class="btn btn-success btn-xs" disabled="disabled">Download</a>
					                            </c:otherwise>
					                        </c:choose>
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
                             { 'bSortable': false, 'aTargets': [9] }
                          ]
    });
    
    $('.add').click(function(){
    	window.location = baseUrl+"/cp/add-a-paper.html";
    });
    
    $('.gen').click(function(){
    	window.location = baseUrl+"/cp/gen-a-paper.html";
    })
    
});

function v_fViewDetailAPaper(iPaperId){
	var sViewDetailUrl = baseUrl + "/cp/paperdetail/"+iPaperId+".html";
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

function v_fRemoveAPaper(iPaperId){
	var r = confirm("Do you really want to remove this ?");
	if (r == true) {
		var sRemoveAPaperUrl = baseUrl + "/cp/remove-a-paper/"+iPaperId+".html";
		window.location = sRemoveAPaperUrl;
	} else {
	    return false;
	}
}
</script>
