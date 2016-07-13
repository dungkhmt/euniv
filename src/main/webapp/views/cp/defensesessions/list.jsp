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
			<h1 class="page-header">Danh sách đợt bảo vệ</h1>
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
				<div class="panel-heading"><h4>Danh sách đợt bảo vệ</h4></div>
					
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th title="Name">Mã</th>
									<th title="Code">Ngày</th>
									<th title="Class">Kích hoạt</th>
									<th>Chỉnh sửa</th>
									<th>Phân công</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${defenseSessionList}" var="defenseSession">
									<tr class="gradeX">
										 <td><c:out value="${defenseSession.DEFSESS_Code}"/></td>
										 <td><c:out value="${defenseSession.DEFSESS_Name}"/></td>
										 <td class="center">
										 	<c:choose>
					                            <c:when test="${defenseSession.DEFSESS_Enabled == 1}">
					                                <button type="button" class="btn btn-success btn-circle "><i class="fa fa-check"></i></button>
					                            </c:when>
					                            <c:otherwise>
					                                <button type="button" class="btn btn-danger btn-circle"><i class="fa fa-times"></i></button>
					                            </c:otherwise>
					                        </c:choose>
										 </td>
										 <td class="center">
											<button type="button" onclick="v_fViewDetailADefenseSession(${defenseSession.DEFSESS_ID});" class="btn btn-info btn-xs" title="Edit">Sửa thông tin</button>
											<br/>
											<button type="button" id="removeStudent" onclick="v_fRemoveADefenseSession(${defenseSession.DEFSESS_ID});" class="btn btn-warning btn-xs" title="Remove">Xóa thông tin</button>
										</td>
										<td>
											<button type="button" onclick="Student(${defenseSession.DEFSESS_ID});" class="btn btn-info btn-xs" title="Edit">Học viên</button>
											<button type="button" onclick="Member(${defenseSession.DEFSESS_ID});" class="btn btn-info btn-xs" title="Edit">Thành viên trong trường</button>
											<button type="button" onclick="ExternalMember(${defenseSession.DEFSESS_ID});" class="btn btn-info btn-xs" title="Edit">Thành viên ngoài trường</button>											
											<button type="button" onclick="Room(${defenseSession.DEFSESS_ID});" class="btn btn-info btn-xs" title="Edit">Phòng</button>
											<button type="button" onclick="Slot(${defenseSession.DEFSESS_ID});" class="btn btn-info btn-xs" title="Edit">Kíp</button>											
										</td>
										<td>
											<button type="button" onclick="Schedule(${defenseSession.DEFSESS_ID});" class="btn btn-info btn-xs" title="Edit">Xếp lịch</button>
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
                             { 'bSortable': false, 'aTargets': [2, 3] }
                          ]
    });
    
    $('.add').click(function(){
    	window.location = baseUrl+"/mm/add-a-defensesession.html";
    });
    
});

function v_fViewDetailADefenseSession(idefenseSessionId){
	var sViewDetailUrl = baseUrl + "/mm/defensesessiondetail/"+idefenseSessionId+".html";
	window.location = sViewDetailUrl;
}

function Student(idefenseSessionId){
	var sViewDetailUrl = baseUrl + "/mm/scheduling/"+idefenseSessionId+".html";
	//var sViewDetailUrl = baseUrl + "/cp/scheduling.html";
	window.location = sViewDetailUrl;
}

function Member(idefenseSessionId){
	var sViewDetailUrl = baseUrl + "/mm/jurymembers/"+idefenseSessionId+".html";
	//var sViewDetailUrl = baseUrl + "/cp/jurymembers.html";
	window.location = sViewDetailUrl;
}

function ExternalMember(idefenseSessionId){
	var sViewDetailUrl = baseUrl + "/mm/juryexternalmembers/"+idefenseSessionId+".html";
	window.location = sViewDetailUrl;
}

function Room(idefenseSessionId){
	var sViewDetailUrl = baseUrl + "/mm/juryrooms/"+idefenseSessionId+".html";
	//var sViewDetailUrl = baseUrl + "/cp/juryrooms.html";
	window.location = sViewDetailUrl;
}

function Slot(idefenseSessionId){
	var sViewDetailUrl = baseUrl + "/mm/juryslots/"+idefenseSessionId+".html";
	//var sViewDetailUrl = baseUrl + "/cp/juryslots.html";
	window.location = sViewDetailUrl;
}

function Schedule(idefenseSessionId){
	var sViewDetailUrl = baseUrl + "/mm/settingjuries/"+idefenseSessionId+".html";
	//var sViewDetailUrl = baseUrl + "/cp/settingjuries.html";
	window.location = sViewDetailUrl;
}

function v_fRemoveADefenseSession(idefenseSessionId){
	var r = confirm("Bạn có muốn xóa thông tin này ?");
	if (r == true) {
		var sRemoveAStudentUrl = baseUrl + "/mm/remove-a-defensesession/"+idefenseSessionId+".html";
		window.location = sRemoveAStudentUrl;
	} else {
	    return false;
	}
}
</script>

