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
			<h1 class="page-header">Tạo danh sách thành viên hội đồng</h1>
		</div>
		
		<!-- /.col-lg-12 -->
	</div>
	
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/mm/save-a-jury-members.html" method="POST" role="form" accept-charset="UTF-8">
		<div class="row">
			<div class="col-lg-6">
				<div class="well">
					<h4>Chọn đợt bảo vệ</h4>
					  <p class="value">${defenseSession.DEFSESS_Name}</p>
						<input id="defenseSession" type="hidden" value="${defenseSession.DEFSESS_Code}">
				</div>
				<div class="well">
					<h4>Danh sách phòng</h4>
					<c:if test="${listRooms != null}">
						<select class="form-control" name="room" id="room" >
							<c:forEach items="${listRooms}" var="aRoom">
			               		<option value="${aRoom.r_Code}" >${aRoom.r_Code}</option>
			               	</c:forEach>
                    	</select>
                    </c:if>   				
                    <h4>Số thứ tự</h4>
                    <input class="form-control" type="text" id="Order" name="Order" value="0" />
                    
                    <button type="button" class="form btn btn-primary" onclick="addJuryMember();" id="choose" style="margin-top:20px;">Thêm</button>
				</div>
			</div>
		
		<!-- /.row -->
		<div class="col-lg-6">
			<div class="well">
					<h4>Bộ môn</h4>
					<p class="value">${department}</p>                    
				</div>
		<div class="panel panel-default">
				<div class="panel-heading">
				    <h4>Danh sách các phòng đã dùng trong đợt bảo vệ</h4>
				</div>
					
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="" id="message">
							
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-professor">
							<thead>
								<tr>
									<th title="Index">Số thứ tự</th>
									<th title="Code">Tên phòng</th>
									<th title="Remove">Remove</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${listJuryRooms != null}">
									<c:forEach items="${listJuryRooms}" var="aJuryRoom">															
										<tr class="gradeX">
										 	<td><c:out value="${aJuryRoom.juryRoom_Index}"/></td>
										 	<td><c:out value="${aJuryRoom.juryRoom_Code}"/></td>
										 	<td class="center"><button type="button" id="removeJuryRoom" onclick="v_fRemoveJuryRoom('${aJuryRoom.juryRoom_Code}');" class="btn btn-danger btn-xs" title="Remove">Remove</button></td>
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
		$('#save').click(function(){
			keyword = $('#data-keyword').children();
			var index = 0;
			keyword.each(function(){
				code = $(this).children(".keyword-code").val();
				input = $('<input path="staffKeywords" class="keyword-code" name="staffKeywords['+index+']" type="hidden" value="'+code+'">');
				$('#data-keyword').append(input);	
				index++;		
			});
			
			document.getElementById('form-add').submit();
		});
		
		
		$('#cancel').click(function() {
			window.location = baseUrl + "/mm/defensesession.html";
		});	
		
	});

	
	function addJuryMember()
	{
		var sRoomCode = $("select#room option:selected").val();
		var sDefenseSessionCode = $("#defenseSession").val();
		var savingJuryMemberUrl = "${baseUrl}/mmservice/savejuryaroom.html";
		var sOrder  = $("#Order").val();
		if (sRoomCode.length > 0 && sDefenseSessionCode.length > 0 ) {
			 $.ajax({
					type: "POST",
					url: savingJuryMemberUrl,
					data: "sRoomCode="+sRoomCode+"&sDefenseSessionCode="+sDefenseSessionCode+"&sOrder="+sOrder,
					cache: true,
					beforeSend: function () { 
						//$('#department').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {    
						$("#status").html( html );
						//window.location = baseUrl + "/mm/juryrooms.html";
						window.location = baseUrl + "/mm/juryrooms/"+${defenseSession.DEFSESS_ID}+".html";
					}
				});
		}else{
			/*
			var sDepartment = '<select class="form-control" style="width:200px;" name="threadDepartment" id="threadDepartment">';
				sDepartment += '<option value="">Chọn Bộ môn</option>';
				sDepartment +=  '</select>';
			$("#list-department").html( sDepartment );
			
			var sStaffs = '<select class="form-control" style="width:200px;" name="threadStaff" id="threadStaff">';
				sStaffs += '<option value="">Chọn cán bộ</option>';
				sStaffs +=  '</select>';
			$("#list-staff").html( sStaffs );
			*/
		}
	}
	
	function v_fRemoveJuryRoom(sRoomCode){
		
		var sDefenseSessionCode = $("#defenseSession").val();
		var savingJuryMemberUrl = "${baseUrl}/mmservice/removejuryaroom.html";
		if (sRoomCode.length > 0 && sDefenseSessionCode.length > 0 ) {
			 $.ajax({
					type: "POST",
					url: savingJuryMemberUrl,
					data: "sRoomCode="+sRoomCode+"&sDefenseSessionCode="+sDefenseSessionCode,
					cache: true,
					beforeSend: function () { 
						//$('#department').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {    
						$("#status").html( html );
						//window.location = baseUrl + "/mm/juryrooms.html";
						window.location = baseUrl + "/mm/juryrooms/"+${defenseSession.DEFSESS_ID}+".html";
					}
				});
		}else{
			/*
			var sDepartment = '<select class="form-control" style="width:200px;" name="threadDepartment" id="threadDepartment">';
				sDepartment += '<option value="">Chọn Bộ môn</option>';
				sDepartment +=  '</select>';
			$("#list-department").html( sDepartment );
			
			var sStaffs = '<select class="form-control" style="width:200px;" name="threadStaff" id="threadStaff">';
				sStaffs += '<option value="">Chọn cán bộ</option>';
				sStaffs +=  '</select>';
			$("#list-staff").html( sStaffs );
			*/
		}
		
	}
	
</script>