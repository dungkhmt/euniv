<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/assets/css/sb-admin-2.css" />" rel="stylesheet" type="text/css" media="all" />

<!-- Jquery UI CSS -->
<link href="<c:url value="/assets/css/jquery-ui.css" />" rel="stylesheet">
<link href="<c:url value="/assets/css/new-style.css" />" rel="stylesheet">

<!-- Custom CSS -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>
<script src="<c:url value="/assets/js/jquery.form-validator.js"/>"></script>	
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thêm một thành viên cho hội đồng tuyển chọn đợt gọi đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm mới một thành viên cho hội đồng tuyển chọn đợt gọi đề tài 
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                	
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/add-a-jury-member-for-an-announced-project-call.html"/>" class="alert-link">Trở lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>     
                    <form:form action="${baseUrl}/cp/save-jury-of-announced-project-call.html" method="POST" commandName="juryOfAnnouncedProjectCallFormAdd" role="form" >
	                    <div class="row">
	                        <div class="col-lg-12">
	                                <div class="form-group">
	                                    <label>Đợt gọi đề tài: ${projectCall.PROJCALL_NAME} </label>
	                                    <input class="form-control" disabled="true"  style="visibility:hidden" value="${projectCall.PROJCALL_CODE}" ></input>
	                                    <form:hidden path="JUSUPRJ_PRJCALLCODE" class="form-control" name="JUSUPRJ_PRJCALLCODE" value="${projectCall.PROJCALL_CODE}" ></form:hidden>
	                                    <form:errors path="JUSUPRJ_PRJCALLCODE" class="alert-danger"></form:errors>
	                                </div>
	                        		<div class="form-group">
	                                    <label>Hội đồng xét duyệt đề tài: ${jury.JURPRJ_Name}</label>
	                                    <input class="form-control" disabled="true" style="visibility:hidden"  value="${jury.JURPRJ_Code}" ></input>
	                                    <form:hidden path="JUSUPRJ_JURYRESEARCHPROJECTCODE" class="form-control" name="JUSUPRJ_JURYRESEARCHPROJECTCODE" value="${jury.JURPRJ_Code}" ></form:hidden>
	                                    <form:errors path="JUSUPRJ_JURYRESEARCHPROJECTCODE" class="alert-danger"></form:errors>
	                                </div>
	                        
	                        
	                                <div class="form-group">
	                                	<select class="form-control" name="faculty" onchange="showDepartment(this);" >
                                 		<option value="">Chọn Khoa/Viện</option>
                                 		<c:forEach items="${listFaculty}" var="faculty">
                                      	<option value="${faculty.faculty_Code}">${faculty.faculty_Name}</option>
                                    	</c:forEach>
                                 		</select>
                                    	<br>
                                    	<div id="department">
	                                    	<select class="form-control" name="department">
												<option value="">Chọn Bộ môn</option>
	                                    	</select>
                                    	</div>
                                    	<br>
                                    
	                                    <label >Thành viên*</label>
	                                    
                                    	
	                                    <form:select path="JUSUPRJ_STAFFCODE" class="form-control" data-validation="required" id="staff" data-validation-error-msg="Trường thông tin này là bắt buộc" name="JUSUPRJ_STAFFCODE">
	                                        <option value="">chọn</option>	                                    	                                    
	                                    	<%-- 
	                                    	<c:forEach items="${staffList}" var="iStaff">
		                                        <option value="${iStaff.staff_Code}">${iStaff.staff_Name}</option>
	                                       	</c:forEach>
	                                       	 --%>
	                                    </form:select>
	                                    <form:errors path="JUSUPRJ_STAFFCODE" class="alert-danger"></form:errors>
	                                    
	                                </div>
	                        
	                                <div class="form-group">
	                                    <label for="paperJConfName">Vai trò*</label>
	                                    <form:select path="JUPSURJ_ROLECODE" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="JUPSURJ_ROLECODE">
	                                        <option value="">chọn</option>	                                    	        	                                    
	                                    	<c:forEach items="${juryRoleOfSubmittedProjecsList}" var="iRole">
		                                        <option value="${iRole.JUPRJROL_CODE}">${iRole.JUPRJROL_NAME}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="JUPSURJ_ROLECODE" class="alert-danger"></form:errors>
	                                </div>
	                                
	                                <div class="form-group">
		                                <button type="submit" class="btn btn-primary">Lưu</button>
		                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                                </div>
	                        </div>
	                    </div>
	                    <!-- /.row (nested) -->
                    </form:form>
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    Danh sách
                </div>
                <div class="panel-body">
                	<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th title="Name of project call " > Đợt gọi đề tài </th>
									<th title="Name of staff ">Thành viên </th>
									<th title="Name of role ">Vai trò</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${juryOfAnnouncedProjectCallList}" var="jury">
									<tr class="gradeX">
										<td><c:out value="${jury.JUSUPRJ_PRJCALLCODE}"/></td>
										<td><c:out value="${jury.JUSUPRJ_STAFFCODE}"/></td>
										<td><c:out value="${jury.JUPSURJ_ROLECODE}"/></td>
										<td class="center">
											<button type="button" id="removeJuryOfAnnouncedProjectCall" onclick="v_fRemoveJuryOfAnnouncedProjectCall(${jury.JUSUPRJ_ID});" class="btn btn-danger btn-xs" title="Remove">Xoá</button>
											<br>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
            	</div>
            </div>
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
<script type="text/javascript">
	
	$(document).ready(function () {
		 $('#dataTables-example').DataTable({
	            responsive: false,
	            "aoColumnDefs": [
	                             { 'bSortable': false, 'aTargets': [3] }
	                          ]
	    });
		 
		var o_Settings = {
							"bProcessing": true,
					        "bServerSide": true,
					        "sort": "position",
					        "aaSorting"	: [[ 1, "asc" ]],
					        "aoColumnDefs": [
					     					{"sWidth" : "10px", "bSortable" : true, "bSearchable" : false, "aTargets" : [ 0 ]},
					     					{"sWidth": null, "bSearchable" : false, "bVisible":true, "aTargets" : [ 1, 2 ]}
					     				],
					        //bStateSave variable you can use to save state on client cookies: set value "true" 
					        "bStateSave": false,
					        //Default: Page display length
					        "iDisplayLength": 20,
					        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
					        "iDisplayStart": 0,
					        "oLanguage": {
								"sSearch": "Search all columns:"
							},
					        "fnDrawCallback": function () {
					            //Get page numer on client. Please note: number start from 0 So
					            //for the first page you will see 0 second page 1 third page 2...
					            //Un-comment below alert to see page number
					        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
					        },         
					        "sAjaxSource": baseUrl + "/cpservice/jury-submitted-projects.html",
					        "fnServerData"		: function ( sSource, aoData, fnCallback ) {
								aoData.push({"name": "projectCallCode", "value": $("#JUSUPRJ_PRJCALLCODE").val()});
								$.ajax( {
									"type"		: "POST",
									//"contentType": "application/json; charset=utf-8",
									"dataType"	: 'json',
									"url"		: sSource,
									"data"		: aoData,
									"success"	: fnCallback,
									"error" : function (xhr, textStatus, errorThrown) {
								        $("#error").html(xhr.responseText);
								    }
								} );
							},
					        "aoColumns": [
					            { "mData": "projectcall_name", "bSortable": true, "sWidth": "20%" },
					            { "mData": "member_name", "sWidth": "100px" },
					            { "mData": "role", "sWidth": "100px" },
					            { "mData": "id", "sWidth": "50px", "bSortable": false, 'mRender': function(resObj) {
					                	var res = '<button type="button" id="removeTopic" onclick="v_fRemoveJuryOfAnnouncedProjectCall('+resObj+');" class="btn btn-danger btn-xs" title="Remove">Xóa</button><br>';
					                	return res;
					              } },
					        ],
					        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					            $('td:eq(0)', nRow).addClass( "avo-lime-h avo-heading-white" );
					            $('td:eq(1),td:eq(2),td:eq(3)', nRow).addClass( "avo-light" );
					          }
						};
		
	    //var oTable = $('#dataTables-example').dataTable(o_Settings);
	    
	    // Validate in client 
	    $.validate();
	    
		$('.add').click(function(){
			window.location = baseUrl+"/cp/add-a-projectcall.html";
		});
		
		$('.cancel').click(function(){
			window.location = baseUrl+"/cp/add-jury-submitted-projects.html";
		});
	});

	function v_fRemoveJuryOfAnnouncedProjectCall(iJuryOfAnnouncedProjectCallId){
		var r = confirm("Bạn có thực sự muốn xoá không ?");
		if (r == true) {
			var sDeleteJuryOfAnnouncedProjectCallUrl = baseUrl + "/cp/delete-jury-of-announced-project-call/"+ iJuryOfAnnouncedProjectCallId +".html";
			window.location = sDeleteJuryOfAnnouncedProjectCallUrl;
		} else {
		    return false;
		}
	}
	
	function showDepartment(sFaculty)
	{
		//alert('showDepartment ' + sFaculty);
		var sGeneratingUrl = "${baseUrl}/cpservice/getdepartments.html";
		var sFacultyCode = sFaculty.options[sFaculty.selectedIndex].value;  
		if (sFacultyCode.length > 0 ) { 
			 $.ajax({
					type: "POST",
					url: sGeneratingUrl,
					data: "sFacultyCode="+sFacultyCode,
					cache: false,
					beforeSend: function () { 
						//$('#department').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {    
						console.log('html : ' + html);
						$("#department").html( html );
					}
				});
		}else{
			var sDepartment = '<select class="form-control" name="department">';
				sDepartment += '<option value="">Chọn Bộ môn</option>';
				sDepartment +=  '</select>';
			$("#department").html( sDepartment );
			
			var sStaffs = '<select class="form-control" name="staff">';
				sStaffs += '<option value="">Chọn Thành viên</option>';
				sStaffs +=  '</select>';
			$("#staff").html( sStaffs );
		}
	}
	
	function showStaff(sDepartment) {
		var sGeneratingUrl = "${baseUrl}/cpservice/getsinglestaffs.html";
		var sDepartmentCode = sDepartment.options[sDepartment.selectedIndex].value;
		if (sDepartmentCode.length > 0 ) { 
			 $.ajax({
					type: "POST",
					url: sGeneratingUrl,
					data: "sDepartmentCode="+sDepartmentCode,
					cache: false,
					beforeSend: function () { 
						//$('#staff').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {   
						//alert(html);
						$("#staff").html( html );
					}
				});
		}else{
			$("#staff").html("");
		}
	}
	
	function changeProjectCall(projectCallSelect){
		var projectCallCode = projectCallSelect.options[projectCallSelect.selectedIndex].value;
		//alert(projectCallCode);
		$.ajax({
			type:'POST',
			url:"${baseUrl}/cp/getJuryOfProjectCall.html",
			data: "projectCallCode=" + projectCallCode,
			success: function(result){
				alert(result);
					$("#dataTables-example").html(result);
			}
		});
	}

</script>

