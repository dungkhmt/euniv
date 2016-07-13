<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Jquery UI CSS -->
<link href="<c:url value="/assets/css/jquery-ui.css" />" rel="stylesheet">

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>
<script src="<c:url value="/assets/libs/ckeditor/ckeditor.js"/>"></script>	   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <!-- <h1 class="page-header">Chỉnh sửa thuyết minh đề tài sau phản biện</h1> -->
            <h1 class="page-header"></h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
        <form:form action="${baseUrl}/cp/edit-a-project.html" method="POST" commandName="projectFormEdit" role="form" enctype="multipart/form-data">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Chỉnh sửa đề tài
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/list-projects.html"/>" class="alert-link">Quay lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <div class="row">
                        <div class="col-lg-6">
                       		<div class="form-group">
                                   <label>Chọn đợt đề tài*</label>
                                   <form:select path="projectCallCode" class="form-control" name="projectCallCode" disabled="${projectEdit.PROJ_Locked1 == 1 ? 'true' : ''}">
                                   	<c:forEach items="${projectCallsList}" var="projectCall">
                                        <option value="${projectCall.PROJCALL_CODE}" <c:if test="${projectEdit.PROJ_PRJCall_Code == projectCall.PROJCALL_CODE}">selected</c:if> >${projectCall.PROJCALL_NAME}</option>
                                      	</c:forEach>
                                   </form:select>
                                   <form:errors path="projectCallCode" class="alert-danger"></form:errors>
                               </div>
                               <div class="form-group">
	                                    <label>Chọn lĩnh vực đề tài*</label>
	                                    <form:select path="projectResearchFieldCode" class="form-control" name="projectResearchFieldCode">
	                                    	<c:forEach items="${projectResearchFieldList}" var="proj_field">
		                                        <option value="${proj_field.PRJRSHF_Code}" <c:if test="${projectEdit.PROJ_ResearchFieldCode == proj_field.PRJRSHF_Code}">selected</c:if> >${proj_field.PRJRSHF_Name}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="projectResearchFieldCode" class="alert-danger"></form:errors>
	                                </div>
                               <div class="form-group">
                                   <label for="projectName">Tên đề tài*</label>
                                   <form:input path="projectName" class="form-control" name="projectName" disabled="${projectEdit.PROJ_Locked1 == 1 ? 'true' : ''}" value="${projectEdit.PROJ_Name}" placeholder="Project Name"></form:input>
   								<form:errors path="projectName" class="alert-danger"></form:errors>
                               </div>
                               <c:choose>
									<c:when test="${projectEdit.PROJ_Locked1 != 1}">
										<div class="form-group">
		                                   <label for="projectOtherFees">Kinh phí vật tư, vật liệu,…</label>
		                                   <form:input path="budgetMaterial" class="form-control" name="budgetMaterial" disabled="${projectEdit.PROJ_Locked1 == 1 ? 'true' : ''}" value="${projectEdit.PROJ_BudgetMaterial}" placeholder="Other Fees"></form:input>
		  									<form:errors path="budgetMaterial" class="alert-danger"></form:errors>
		                              	</div>
		                            </c:when>
	                              	<c:otherwise>
	                              		<div class="panel panel-default">
					                        <div class="panel-heading">
					                            <label for="projectResult">Kinh phí vật tư, vật liệu,…</label>
					                        </div>
					                        <div class="panel-body">
					                            <div class="tab-content">
					                                <div class="tab-pane fade in active">${projectEdit.PROJ_BudgetMaterial}</div>
					                            </div>
					                        </div>
					                    </div>
	                              	</c:otherwise>
	                           </c:choose>
                        </div>
                        <div class="col-lg-6">
                              <div class="form-group">
                                   <label for="projectLeader">Chủ nhiệm đề tài</label>
                                   <input path="projectLeader" class="form-control" disabled name="projectLeader" value="${currentUserName}" />
                               </div>
                               <div class="form-group">
                                   <label>Đơn vị*</label>
                                   <form:select path="falcutyAddress" class="form-control" name="falcutyAddress">
                                   	<c:forEach items="${listFaculty}" var="faculty">
                                       	<option value="${faculty.faculty_Code}" <c:if test="${faculty.faculty_Code == projectEdit.PROJ_FacultyCode}">selected</c:if> >${faculty.faculty_Name}</option>
                                     		</c:forEach>
                                   </form:select>
                                   <form:errors path="falcutyAddress" class="alert-danger"></form:errors>
                               </div>
								<div class="form-group">
                                   	<label for="projectStartDate">Thời gian bắt đầu</label>
                                   	<form:input path="projectStartDate" class="form-control" readonly="true" name="projectStartDate" disabled="${projectEdit.PROJ_Locked1 == 1 ? 'true' : ''}" value="${projectEdit.PROJ_StartDate}" placeholder="Project Start Date"></form:input>
   									<form:errors path="projectStartDate" class="alert-danger"></form:errors>
                                </div>
                               <div class="form-group">
                                   	<label for="projectEndDate">Thời gian hoàn thành</label>
                                   	<form:input path="projectEndDate" class="form-control" readonly="true" name="projectEndDate" disabled="${projectEdit.PROJ_Locked1 == 1 ? 'true' : ''}" value="${projectEdit.PROJ_EndDate}" placeholder="Project End Date"></form:input>
   									<form:errors path="projectEndDate" class="alert-danger"></form:errors>
                               </div>
                               	<%-- <div class="form-group">
                                   <label for="projectBudget">Kinh phí (triệu VNĐ)</label>
                                   <form:input path="projectBudget" class="form-control" name="projectBudget" disabled="${projectEdit.PROJ_Locked1 == 1 ? 'true' : ''}" value="${projectEdit.PROJ_TotalBudget}" placeholder="Budget"></form:input>
   								<form:errors path="projectBudget" class="alert-danger"></form:errors>
                               </div> --%>
                        </div>
                        <!-- /.col-lg-6 (nested) -->
                    </div>
                    <!-- /.row (nested) -->
                </div>
                <!-- /.panel-body -->
                <div class="panel-body">
                	<c:choose>
						<c:when test="${projectEdit.PROJ_Locked1 != 1}">
							<div class="form-group">
                                   <label for="projectSurvey">6. Tổng quan tình hình nghiên cứu thuộc lĩnh vực của đề tài ở trong và ngoài nước</label>
                                   <textarea path="projectSurvey" name="projectSurvey" id="projectSurvey" class="form-control textarea">${projectEdit.PROJ_Survey}</textarea>
  									<form:errors path="projectSurvey" class="alert-danger"></form:errors>
                              	</div>
                               <div class="form-group">
                                   <label for="projectMotivation">7. Tính cấp thiết đề tài</label>
                                   <textarea path="projectMotivation" name="projectMotivation" id="projectMotivation" class="form-control textarea">${projectEdit.PROJ_Motivation}</textarea>
   								<form:errors path="projectMotivation" class="alert-danger"></form:errors>
                               </div>
                               <div class="form-group">
                                   <label for="projectObjective">8. Mục tiêu của đề tài</label>
                                   <textarea path="projectObjective" name="projectObjective" id="projectObjective" class="form-control textarea">${projectEdit.PROJ_Objective}</textarea>
  									<form:errors path="projectObjective" class="alert-danger"></form:errors>
                              	</div>
					    </c:when>    
					    <c:otherwise>
					    	<div class="panel panel-default">
		                        <div class="panel-heading">
		                            <label for="projectResult">6. Tổng quan tình hình nghiên cứu thuộc lĩnh vực của đề tài ở trong và ngoài nước</label>
		                        </div>
		                        <div class="panel-body">
		                            <div class="tab-content">
		                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_Survey}</div>
		                            </div>
		                        </div>
		                    </div>
					        <div class="panel panel-default">
		                        <div class="panel-heading">
		                            <label for="projectResult">7. Tính cấp thiết đề tài</label>
		                        </div>
		                        <div class="panel-body">
		                            <div class="tab-content">
		                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_Motivation}</div>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="panel panel-default">
		                        <div class="panel-heading">
		                            <label for="projectResult">8. Mục tiêu của đề tài</label>
		                        </div>
		                        <div class="panel-body">
		                            <div class="tab-content">
		                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_Objective}</div>
		                            </div>
		                        </div>
		                    </div>
					    </c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${projectEdit.PROJ_Locked1 != 1}">
                              	<div class="form-group">
                               	<label for="projectResult">9. Nội dung nghiên cứu</label>
                                <textarea path="projectContent"  name="projectContent" id="projectContent" class="form-control textarea">${projectEdit.PROJ_Content}</textarea>
                                <form:errors path="projectContent" class="alert-danger"></form:errors>
                           	</div>
                           	<div class="form-group">
                                  <label for="projectResult">10. Sản phẩm, chuyển giao kết quả nghiên cứu và đia chỉ ứng dụng</label>
                                  <textarea path="projectResult"  name="projectResult" id="projectResult" class="form-control textarea">${projectEdit.PROJ_Result}</textarea>
 									<form:errors path="projectResult" class="alert-danger"></form:errors>
                             	</div>
					    </c:when>    
					    <c:otherwise>
					        <div class="panel panel-default">
		                        <div class="panel-heading">
		                            <label for="projectResult">9. Nội dung nghiên cứu</label>
		                        </div>
		                        <div class="panel-body">
		                            <div class="tab-content">
		                                <div class="tab-pane fade in active">${projectEdit.PROJ_Content}</div>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="panel panel-default">
		                        <div class="panel-heading">
		                            <label for="projectResult">10. Sản phẩm, chuyển giao kết quả nghiên cứu và đia chỉ ứng dụng</label>
		                        </div>
		                        <div class="panel-body">
		                            <div class="tab-content">
		                                <div class="tab-pane fade in active">${projectEdit.PROJ_Result}</div>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="panel panel-default">
		                        <div class="panel-heading">
		                            <label for="projectResult">Kinh phí vật tư, vật liệu,…</label>
		                        </div>
		                        <div class="panel-body">
		                            <div class="tab-content">
		                                <div class="tab-pane fade in active">${projectEdit.PROJ_BudgetMaterial}</div>
		                            </div>
		                        </div>
		                    </div>
					    </c:otherwise>
					</c:choose>
                </div>
            </div>
                
            <div class="panel panel-default">
            	<div class="panel-heading">
                    Thành viên tham gia
                </div>
                <div class="panel-body">
                	<c:if test="${projectEdit.PROJ_Locked1 != 1}">
	                    <div class="row">
	                        <div class="col-lg-6">
	                        	<label for="threadResult">Thành viên tham gia*</label>
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
                                    <%-- <div id="staff">
	                                    <select class="form-control" name="staff" multiple="multiple" id="staff">
											<option value="">Chọn thành viên</option>
											<c:forEach items="${listStaffs}" var="aStaff">
	                                        	<option value="${aStaff.staff_Code}" selected="selected">${aStaff.staff_Name}</option>
                                       		</c:forEach>
	                                    </select>
                                    </div> --%>
                                    <br>
                                    
		                        	<div class="form-group">
		                                <label for="projectMembers">Thành viên</label>
		                                <div id="staff">
		                                <select class="form-control" id="members">
		                                	<c:forEach items="${staffList}" var="aStaff">
		                                    	<option value="${aStaff.staff_Code}">${aStaff.staff_Name}</option>
		                                   	</c:forEach>
		                                </select>
		                                </div>
		                            </div>
		                            
	                            <div class="form-group">
	                               	<label for="taskContent">Nội dung công việc</label>
	                                <textarea id="taskContent" class="form-control"></textarea>
	                           	 </div>
	                             <button type="button" class="btn btn-primary btn-xs" onclick="v_fAddMember();">Thêm</button>
	                        </div>
	                        <div class="col-lg-6">
	                        	<div class="form-group">
	                                <label for="memberRole">Vai trò</label>
	                                <select class="form-control" id="memberRole">
	                                	<c:forEach items="${memberRolesList}" var="memberRole">
	                                     <option value="${memberRole.PROJPARTIROLE_Code}">${memberRole.PROJPARTIROLE_Description}</option>
	                                   	</c:forEach>
	                                </select>
	                            </div>
	                           	 <div class="form-group">
	                                <label for="memberWorkingDays">*Số ngày công</label>
	                                <input class="form-control" id="memberWorkingDays" value="0" placeholder="Working days" />
	                             </div>
	                             <div class="form-group">
	                                <label for="taskBudget">*Thành tiền</label>
	                                <input class="form-control" id="taskBudget" value = "0" placeholder="Fee" />
	                             </div>
	                        </div>
	                        <!-- /.col-lg-6 (nested) -->
	                    </div>
                    </c:if>
                    <!-- /.row (nested) -->
                    <div class="panel-body">
                    	<div class="panel panel-default">
	                        <div class="panel-heading">
	                            Thành viên
	                        </div>
	                        <!-- /.panel-heading -->
	                        <div class="panel-body">
	                            <div class="table-responsive">
	                                <table class="table table-striped table-bordered table-hover" id="projectMemberList">
	                                    <thead>
	                                        <tr>
	                                            <th>Tên</th>
	                                            <th>Vai trò</th>
	                                            <th>Nội dung</th>
	                                            <th>Số ngày công</th>
	                                            <th>Thành tiền</th>
	                                            <c:if test="${projectEdit.PROJ_Locked1 != 1}">
	                                            <th>Hủy</th>
	                                            </c:if>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
		                                    <c:choose>
												<c:when test="${projectTasks != null}">
			                                    	<c:forEach items="${projectTasks}" var="task">
			                                    		<tr>
				                                            <td>
				                                            	<c:out value="${task.staffProject.staff_Name}"/>
				                                            	<input name='projectMembers' type='hidden' value="${task.PRJTSK_StaffCode}" />
				                                            </td>
				                                            <td>
				                                            	<c:out value="${task.participationRoles.PROJPARTIROLE_Description}"/>
				                                            	<input name='projectMemberRole' type='hidden' value="${task.PRJTSK_RoleCode}" />
				                                            </td>
				                                            <td>
				                                            	<c:out value="${task.PRJTSK_Task}"/>
				                                            	<input name='projectMemberTasks' type='hidden' value="${task.PRJTSK_Task}" />
				                                            </td>
				                                            <td>
				                                            	<c:out value="${task.PRJTSK_NRBDay}"/>
				                                            	<input name='projectMemberWorkingDays' type='hidden' value="${task.PRJTSK_NRBDay}" />
				                                            </td>
				                                            <td>
				                                            	<c:out value="${task.PRJTSK_Cost}"/>
				                                            	<input name='projectMemberBudget' type='hidden' value="${task.PRJTSK_Cost}" />
				                                            </td>
				                                            <c:if test="${projectEdit.PROJ_Locked1 != 1}">
				                                            <td><button type='button' onclick='v_fClearMember(this);' class='btn btn-warning btn-xs' title='Hủy' >Clear</button></td>
				                                            </c:if>
				                                        </tr>
			                                    	</c:forEach>
		                                    	</c:when>    
									    		<c:otherwise>
			                                        <tr class="no-records-found">
			                                        	<td colspan="6" align="center">Chưa có thành viên</td>
			                                        </tr>
			                                    </c:otherwise>
		                                    </c:choose>
	                                    </tbody>
	                                </table>
	                            </div>
	                            <!-- /.table-responsive -->
	                        </div>
	                        <!-- /.panel-body -->
	                    </div>
                    </div>
                   	
                </div>
                <!-- /.panel-body -->
	        </div>
	        <div class="panel panel-default">
	        	<!-- Buttons -->
               	 <c:if test="${projectEdit.PROJ_Locked1 != 1}">
                 	<button type="submit" class="btn btn-primary">Lưu</button>
                 </c:if>
                 <input type="hidden" value="${projectEdit.PROJ_ID}" name="projectId" id="projectId" />
                 <input type="hidden" value="${projectEdit.PROJ_Code}" name="currentProjectCode" id="currentProjectCode" />
                 <button type="reset" class="btn btn-info cancel">Hủy</button>
                 <%-- <c:if test="${projectEdit.PROJ_Locked1 == 1}">
                  --%>
                 <button type="reset" class="btn btn-success" onclick="v_fGeneratePDF(${projectEdit.PROJ_ID})">View PDF</button>
                 <%-- </c:if> --%>
                 <c:if test="${projectEdit.PROJ_Locked1 != 1}">
                 	<button type="reset" class="btn btn-danger" onclick="v_fSendProject(${projectEdit.PROJ_ID})">Gửi đề xuất đề tài</button>
                 </c:if>
	        </div>
            <!-- /.panel -->
            </form:form>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

<script type="text/javascript">

$('.textarea').each( function() {
    CKEDITOR.replace( $(this).attr('id') );
});
 
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/list-projects.html";
	});
	
	$('#projectStartDate').datepicker({
		changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat : 'dd/mm/yy',
        stepMonths: 12});
	
	$('#projectEndDate').datepicker({
		changeMonth: true,
        changeYear: true,
        showButtonPanel: true,
        dateFormat : 'dd/mm/yy',
        stepMonths: 12});
});

function v_fGeneratePDF(iProjectId){
	var sGeneratePdfUrl = baseUrl + "/cp/generatepdf/"+iProjectId+".html";
	window.location = sGeneratePdfUrl;
}

function v_fSendProject(iProjectId){
	if(confirm("Chú ý ! Đề tài sau khi gửi sẽ không thể chỉnh sửa được.")){
		var sSubmitProjectUrl = baseUrl + "/cp/sendproject/"+iProjectId+".html";
		window.location = sSubmitProjectUrl;
    }
    else{
        return false;
    }
}

function v_fAddMember(){
	var patt = new RegExp("^[0-9]*$");
	
	
	var sMemberCode = $("select#members").find(":selected").val();
	var sMemberName = $("select#members").find(":selected").text();
	var sMemberRoleCode = $("#memberRole").find(":selected").val();
	var sMemberRoleDescription = $("#memberRole").find(":selected").text();
	var sTask = $("#taskContent").val();
	var iMemberWorkingDays = $("#memberWorkingDays").val();
	var iBudget = $("#taskBudget").val();
	
	if(iMemberWorkingDays == '' || iMemberWorkingDays == null){
		alert("Bạn phải nhập số ngày công"); return;
	}
	
	if(!patt.test(iMemberWorkingDays)){
		alert("Số ngày công " + iMemberWorkingDays + " sai định dạng: chỉ chấp nhận chữ số"); return;
	}
	
	if(iBudget == '' || iBudget == null){
		alert("Bạn phải nhập thông tin Thành tiền"); return;
	}
	
	if(!patt.test(iBudget)){
		alert("Thành tiền " + iBudget + " sai định dạng: chỉ chấp nhận chữ số"); return;
	}
	
	var sAddedMember = "";
	if(sMemberName != "" && sMemberCode != "")
	{
		// Remove no records found column
		$("table#projectMemberList tbody tr.no-records-found").remove();
		$("#taskContent").val("");
		$("#memberWorkingDays").val("");
		$("#taskBudget").val("");
		
		sAddedMember 	+= "<tr>";
		sAddedMember 	+= "<td><span>"+sMemberName+"</span><input name='projectMembers' type='hidden' value='"+sMemberCode+"'/></td>";
		sAddedMember 	+= "<td><span>"+sMemberRoleDescription+"</span><input name='projectMemberRole' type='hidden' value='"+sMemberRoleCode+"'/></td>";
		sAddedMember 	+= "<td><span>"+sTask+"</span><input name='projectMemberTasks' type='hidden' value='"+sTask+"'/></td>";
		sAddedMember 	+= "<td><span>"+iMemberWorkingDays+"</span><input name='projectMemberWorkingDays' type='hidden' value='"+iMemberWorkingDays+"'/></td>";
		sAddedMember 	+= "<td><span>"+iBudget+"</span><input name='projectMemberBudget' type='hidden' value='"+iBudget+"'/></td>";
		sAddedMember 	+= "<td><button type='button' onclick='v_fClearMember(this);' class='btn btn-warning btn-xs' title='Hủy' >Clear</button></td>";
		sAddedMember 	+= "</tr>";
		$("table#projectMemberList tbody").append(sAddedMember);
	}
}

function v_fClearMember(the_oElement){
	$(the_oElement).parents("tr").remove();
}

function showDepartment(sFaculty)
{
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
					$("#staff").html( html );
				}
			});
	}
}

</script>

