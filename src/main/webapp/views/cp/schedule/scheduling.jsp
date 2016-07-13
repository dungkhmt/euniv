<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<div id="${(disableHeader != null) ? 'page-wrapper-no-layout' : 'page-wrapper'}">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Tạo danh sách bảo vệ</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/mm/save-a-defense.html" method="POST" role="form" accept-charset="UTF-8">
		<div class="row">
			<div id="status" class="">
			</div>
			<div class="col-lg-6">
				<div class="well">
					<h4>Đợt bảo vệ</h4>
						<p class="value">${defenseSession.DEFSESS_Name}</p>
						<input id="defenseSession" type="hidden" value="${defenseSession.DEFSESS_Code}">					  
				</div>
				<!-- <div class="well">
					<h4>Danh sách học viên bảo vệ</h4>
					<c:if test="${listMasterThesis != null}">
						<select class="form-control" name="masterThesis" id="masterThesis" >
							<c:forEach items="${listMasterThesis}" var="masterThesis">
			               		<option value="${masterThesis.thesis_Code}" >${masterThesis.student.student_Name} : ${masterThesis.thesis_Name} : ${masterThesis.supervisor.staff_Name}</option>
			               	</c:forEach>
                    	</select>
                    </c:if>   				
				</div>
				-->
				<!-- /.col-lg-4 -->
			</div>
	
			<div class="col-lg-6">
				<div class="well">
					<h4>Bộ môn</h4>
					<p class="value">${department}</p>                    
				</div>
			</div>
		</div>
		<!-- /.row -->
				
		<div class="panel panel-default">
				<div class="panel-heading"><h4>Chọn học viên tham gia vào hội đồng</h4> 
				    
				</div>
					
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="col-md-6">
						<div class="dataTable_wrapper">
						<div class="" id="message">
							
						</div>
						<h4>Danh sách học viên chưa có trong hội đồng</h4>
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-1">
							<thead>
								<tr>
									<th title="Name">Tên học viên</th>
									<th title="Thesis">Đề tài</th>
									<th title="Mentor">GV Hướng dẫn</th>
									<th title="Remove"></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${listMasterThesis != null}">
									<c:forEach items="${listMasterThesis}" var="masterThesis">															
										<tr class="gradeX">
										 	<td><c:out value="${masterThesis.student.student_Name}"/></td>
										 	<td><c:out value="${masterThesis.thesis_Name}"/></td>
										 	<td><c:out value="${masterThesis.supervisor.staff_Name}"/></td>
										 	<td class="center"><button type="button" id="addMasterThesis" onclick="addMasterDefense('${masterThesis.thesis_Code}')" class="btn btn-info btn-xs" title="Remove">Thêm</button></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->
					</div>
					
					<div class="col-md-6">
						<div class="dataTable_wrapper">
						<div class="" id="message">
							
						</div>
						<h4>Danh sách học viên đã có trong hội đồng</h4>
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-2">
							<thead>
								<tr>
									<th title="Name">Tên học viên</th>
									<th title="Thesis">Đề tài</th>
									<th title="Mentor">GV Hướng dẫn</th>
									<th title="Remove"></th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${listMasterDefenseJuryThesis != null}">
									<c:forEach items="${listMasterDefenseJuryThesis}" var="aMasterDefenseJuryThesis">															
										<tr class="gradeX">
										 	<td><c:out value="${aMasterDefenseJuryThesis.masterThesis.student.student_Name}"/></td>
										 	<td><c:out value="${aMasterDefenseJuryThesis.masterThesis.thesis_Name}"/></td>
										 	<td><c:out value="${aMasterDefenseJuryThesis.masterThesis.supervisor.staff_Name}"/></td>
										 	<td class="center"><button type="button" id="removeMasterThesis" onclick="v_fRemoveMasterThesis('${aMasterDefenseJuryThesis.masterThesis.thesis_Code}')" class="btn btn-danger btn-xs" title="Remove">Loại bỏ</button></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->
					</div>

				</div>
			</div>
		
		<div class="row" style="padding-bottom:20px;">
			<div class="col-lg-4">
				<button type="button" class='form btn btn-primary' id="cancel" >Quay về</button>				
			</div>
			<!-- /.col-lg-4 -->
		</div>
		
		<!-- /.row -->
	</form:form>
</div>
<!-- /#page-wrapper -->

<!-- DataTables JavaScript -->
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>
	

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script type="text/javascript">
	$(document).ready(function() {
		$('#cancel').click(function() {
			window.location = baseUrl + "/mm/defensesession.html";
		});	
		
	});
	
	 $('#dataTables-1').DataTable({
         responsive: true,
         "aoColumnDefs": [
                          { 'bSortable': false, 'aTargets': [3] }
                       ]
	 });
	 
	 $('#dataTables-2').DataTable({
         responsive: true,
         "aoColumnDefs": [
                          { 'bSortable': false, 'aTargets': [3] }
                       ]
	 });

	
	function addMasterDefense(sMasterThesisCode)
	{
		
		//var sMasterThesisCode = $("select#masterThesis option:selected").val();
		var sDefenseSessionCode = $("#defenseSession").val();		
		var savingMasterThesisUrl = "${baseUrl}/mmservice/savemasterthesis.html";
		
		if (sMasterThesisCode.length > 0 ) {
			 $.ajax({
					type: "POST",
					url: savingMasterThesisUrl,
					data: "sMasterThesisCode="+sMasterThesisCode+"&sDefenseSessionCode="+sDefenseSessionCode,
					cache: true,
					beforeSend: function () { 
						//$('#department').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) { 
						$("#status").html( html );
						window.location = baseUrl + "/mm/scheduling/"+${defenseSession.DEFSESS_ID}+".html";
					}
				});
		}else{
			var sDepartment = '<select class="form-control" style="width:200px;" name="threadDepartment" id="threadDepartment">';
				sDepartment += '<option value="">Chọn Bộ môn</option>';
				sDepartment +=  '</select>';
			$("#list-department").html( sDepartment );
			
			var sStaffs = '<select class="form-control" style="width:200px;" name="threadStaff" id="threadStaff">';
				sStaffs += '<option value="">Chọn cán bộ</option>';
				sStaffs +=  '</select>';
			$("#list-staff").html( sStaffs );
		}
	}
	
	function v_fRemoveMasterThesis(sMasterThesisCode){
		
		//var sMasterThesisCode = $("select#masterThesis option:selected").val();
		var sDefenseSessionCode = $("#defenseSession").val();		
		var savingMasterThesisUrl = "${baseUrl}/mmservice/removemasterthesis.html";
		
		if (sMasterThesisCode.length > 0 ) {
			 $.ajax({
					type: "POST",
					url: savingMasterThesisUrl,
					data: "sMasterThesisCode="+sMasterThesisCode+"&sDefenseSessionCode="+sDefenseSessionCode,
					cache: true,
					beforeSend: function () { 
						//$('#department').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {    
						$("#status").html( html );
						window.location = baseUrl + "/mm/scheduling/"+${defenseSession.DEFSESS_ID}+".html";
					}
				});
		}else{
			var sDepartment = '<select class="form-control" style="width:200px;" name="threadDepartment" id="threadDepartment">';
				sDepartment += '<option value="">Chọn Bộ môn</option>';
				sDepartment +=  '</select>';
			$("#list-department").html( sDepartment );
			
			var sStaffs = '<select class="form-control" style="width:200px;" name="threadStaff" id="threadStaff">';
				sStaffs += '<option value="">Chọn cán bộ</option>';
				sStaffs +=  '</select>';
			$("#list-staff").html( sStaffs );
		}
		
	}
	
</script>