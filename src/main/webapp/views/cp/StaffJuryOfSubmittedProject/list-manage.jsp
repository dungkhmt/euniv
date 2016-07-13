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
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>
<script src="<c:url value="/assets/js/jquery.form-validator.js"/>"></script>		   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thêm một thành viên cho hội đồng phản biện đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm một thành viên cho hội đồng phản biện đề tài
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
                    
                    <form:form action="${baseUrl}/cp/save-staff-jury-of-submitted-project.html" method="POST" commandName="staffJuryOfSubmittedProjectFormAdd" role="form" >
	                    <div class="row">
	                        <div class="col-lg-12">
	                        	<div class="form-group">
			                        <label>Đợt gọi Đề tài: ${projectCall.PROJCALL_NAME} </label>
			                        <select type="text" class="form-control" style="visibility:hidden" readonly name="PROJCALL_CODE" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc">
			                        	<option value="${projectCall.PROJCALL_CODE}"><c:out value="${projectCall.PROJCALL_NAME}" /></option>
			                        </select>
			                    </div>
			                    <div class="form-group">
			                        <label>Hội đồng xét duyệt Đề tài: ${jury.JURPRJ_Name} </label>
			                        <select type="text" class="form-control" style="visibility:hidden" readonly name="JURY_CODE" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc">
			                        	<option value="${jury.JURPRJ_Code}"><c:out value="${jury.JURPRJ_Name}" /></option>
			                        </select>
			                    </div>
			                    
                                <div class="form-group">
                                    <label >Chọn Thành viên phản biện</label>
                                    <div id="staff-list">
	                                    <select path="STFJUPRJ_STAFFJURCODE" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" class="form-control" name="STFJUPRJ_STAFFJURCODE" id="STFJUPRJ_STAFFJURCODE">
	                                        <option value="">Chọn</option>	 
	                                        <c:forEach items = "${staffList}" var="staff">
	                                        	<option value="${staff.staff_Code}">${staff.staff_Name}</option>
	                                        </c:forEach>                                	        	                                    	
	                                    </select>
                                    </div>
                                    <form:errors path="STFJUPRJ_STAFFJURCODE" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label>Chọn Đề tài (Nhấn giữ phím Ctrl và dùng chuột click để chọn nhiều đề tài)</label>
                                    <div id="project-list">
	                                    <select path="STFJUPRJ_PRJCODE" size="10" multiple="true" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" class="form-control" name="STFJUPRJ_PRJCODE" id="STFJUPRJ_PRJCODE">
	                                        <c:forEach items="${projectList}" var="project">
	                                        	<option value="${project.PROJ_Code}">${project.PROJ_Name}</option>
	                                        </c:forEach>                                   	        	                                    	
	                                    </select>
                                    </div>
                                    <form:errors path="STFJUPRJ_PRJCODE" class="alert-danger"></form:errors>
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
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Danh sách phân công phản biện đề tài
                </div>
                <div class="panel-body">
                	<%-- 
                    <div class="form-group">
                        <label>Đợt gọi Đề tài</label>
                        <input class="form-control" disabled="true"  value="${projectCallName}" ></input>
                    </div>
                     --%>
                    <div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th title="Name of project call " > Đề tài </th>
									<th title="Name of staff ">Thành viên phản biện</th>
									<th>Edit</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${staffJuryOfSubmittedProjectList}" var="jury">
									<tr class="gradeX">
										<td><c:out value="${jury.STFJUPRJ_PRJCODE}"/></td>
										<td><c:out value="${jury.STFJUPRJ_STAFFJURCODE}"/></td>
										<td class="center">
											<button type="button" id="removeStaffJuryOfSubmittedProject" onclick="v_fRemoveStaffJuryOfSubmittedProject(${jury.STFJUPRJ_ID});" class="btn btn-danger btn-xs" title="Remove">Xoá</button>
											<br>
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
                             { 'bSortable': true, 'aTargets': [2] }
                          ]
    });
    
    $('button.cancel').click(function(){
		window.location = baseUrl+"/cp/assign-jury-submitted-projects.html";
	});
    
    // Validate in client 
    $.validate();
});
</script>

<script>
function v_fRemoveStaffJuryOfSubmittedProject(iJuryOfAnnouncedProjectCallId){
	var r = confirm("Bạn có thực sự muốn xoá không ?");
	if (r == true) {
		var sDeleteJuryOfAnnouncedProjectCallUrl = baseUrl + "/cp/delete-staff-jury-of-submitted-project/"+ iJuryOfAnnouncedProjectCallId +".html";
		window.location = sDeleteJuryOfAnnouncedProjectCallUrl;
	} else {
	    return false;
	}
}

function showProjectsOfThisCall(projectCall) {
	var projectCallCode = projectCall.options[projectCall.selectedIndex].value;
	var sGeneratingUrl = "${baseUrl}/cpservice/getprojectslist.html";
	if(projectCallCode.length > 0){
		$.ajax({
			type: "POST",
			url: sGeneratingUrl,
			data: "sProjectCallCode="+projectCallCode,
			cache: false,
			beforeSend: function () { 
				//$('#staff').html('<img src="loader.gif" alt="" width="24" height="24">');
			},
			success: function(html) {    
				$("#project-list").html( html );
			}
		});
	}else{
		var html = '<select path="STFJUPRJ_PRJCODE" class="form-control" name="STFJUPRJ_PRJCODE" id="STFJUPRJ_PRJCODE">';
        html += '<option value="">Chọn</option>';
    	html += '</select>';
		$("#project-list").html(html);
	}
}

function showStaffsOfThisCall(projectCall) {
	var projectCallCode = projectCall.options[projectCall.selectedIndex].value;
	var sGeneratingUrl = "${baseUrl}/cpservice/getstaffslist.html";
	if(projectCallCode.length > 0){
		$.ajax({
			type: "POST",
			url: sGeneratingUrl,
			data: "sProjectCallCode="+projectCallCode,
			cache: false,
			beforeSend: function () { 
				//$('#staff').html('<img src="loader.gif" alt="" width="24" height="24">');
			},
			success: function(html) {    
				$("#staff-list").html( html );
			}
		});
	}else{
		var html = '<select path="STFJUPRJ_STAFFJURCODE" class="form-control" name="STFJUPRJ_STAFFJURCODE" id="STFJUPRJ_STAFFJURCODE">';
        html += '<option value="">Chọn</option>';
    	html += '</select>';
		$("#staff-list").html(html);
	}
}
</script>

