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
			<h1 class="page-header">Thống kê danh sách đề tài</h1>
			<!-- <p>
				<button type="button" class="btn btn-primary btn-xs add">Thêm mới</button> 
			</p> -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh sách đợt gọi đề tài</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
				
				<%-- 	 <form:form action="${baseUrl}/cp/projectExcell-statistics" method="POST" commandName="threadExcellForm" role="form">
							
							<div class="form-group input-group">
								<form:select class="form-control" id="project-call" style="width:200px;" path="projectCallCode">
		                        	<c:forEach items="${projectCallsList}" var="projectCall">
	                                 	<option value="${projectCall.PROJCALL_CODE}">${projectCall.PROJCALL_NAME}</option>
	                               	</c:forEach>
		                        </form:select>
		                        
		                        <button type="button" id="filter" class="btn btn-primary filter">Chọn</button>
								<button type="submit" class="btn btn-primary">Kết xuất Excel</button>
								<button type="button" id="generateCode" class="btn btn-primary generateCode">Sinh Mã cho đề tài</button>
								
 							</div>
						</form:form>
				 --%>
				  
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th>STT</th>
									<th>Mã đề tài</th>
									<th>Tên đề tài</th>
									<th>Chủ nhiệm</th>
									<th>Đợt gọi</th>
									<th>Trạng thái</th>
									<th>Chi tiết</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="0" scope="page" />
								<c:forEach items="${projectsList}" var="project">
									<tr class="gradeX">
										<c:set var="count" value="${count + 1}" scope="page"/> 
										<td><c:out value="${count}"/></td>
										<td><c:out value="${project.PROJ_Code}"/></td>
										<td><c:out value="${project.PROJ_Name}"/></td>
										<td><c:out value="${project.PROJ_User_Code}"/></td>
										<td><c:out value="${project.PROJ_PRJCall_Code}"/></td>
										<td><c:out value="${project.PROJ_Status_Code}"/></td>
										
										<td class="center">
											<%-- 
											<button type="button" onclick="v_fViewDetailAProject(${project.PROJ_ID});" class="btn ${project.PROJ_Locked1 == 1 ? "btn-warning" : "btn-info"} btn-xs" title="${project.PROJ_Locked1 == 1 ? 'Đã gửi' : 'Chỉnh sửa'}">View PDF</button>
											 --%>
											<%-- 
											<button type="button" onclick="v_fViewDetailAProject(${project.PROJ_ID});" class="btn btn-info">Thuyết minh PDF</button> 
											 --%>
											<a href="<c:url value="${baseUrl}/cp/download-proposal/${project.PROJ_ID}.html"/>" title="Download file xác thực" class="btn btn-success btn-xs">Tải thuyết minh</a>
											
											<br/>
										<%-- 	
											<button type="button" id="removeTopic" onclick="v_fRemoveAProject(${project.PROJ_ID});" class="btn btn-danger btn-xs" title="Xóa đề tài">Xoá</button>
										 --%>
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
            //"aoColumnDefs": [
             //                { 'bSortable': false, 'aTargets': [2] }
               //           ]
    });
    
    $('.add').click(function(){
    	window.location = baseUrl+"/cp/add-a-newproject.html";
    });
});

function v_fViewDetailAProject(iProjectId){
	//var sViewDetailUrl = baseUrl + "/cp/aprojectdetail/"+iProjectId+".html";
	//window.location = sViewDetailUrl;
	
	var sGeneratePdfUrl = baseUrl + "/cp/generatepdf/"+iProjectId+".html";
	window.location = sGeneratePdfUrl;
}


function v_fRemoveAProject(iProjectId){
	var r = confirm("Bạn có muốn xóa bản ghi này ?");
	if (r == true) {
		var sRemoveUrl = baseUrl + "/cp/remove-a-project/"+iProjectId+".html";
		window.location = sRemoveUrl;
	} else {
	    return false;
	}
}


</script>
