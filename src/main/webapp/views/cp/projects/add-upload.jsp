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
<link href="<c:url value="/assets/css/new-style.css" />" rel="stylesheet">

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>
<script src="<c:url value="/assets/libs/ckeditor/ckeditor.js"/>"></script>
<script src="<c:url value="/assets/js/jquery.form-validator.js"/>"></script>	   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thêm mới đề xuất đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
        	<form:form action="${baseUrl}/cp/save-a-project.html" method="POST" commandName="projectsAddForm" role="form" enctype="multipart/form-data">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    Thông tin chung
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
                                    <form:select path="projectCallCode" class="form-control" name="projectCallCode">
                                    	<c:forEach items="${projectCallsList}" var="projectCall">
	                                        <option value="${projectCall.PROJCALL_CODE}">${projectCall.PROJCALL_NAME}</option>
                                       	</c:forEach>
                                    </form:select>
                                    <form:errors path="projectCallCode" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="projectName">Tên đề tài*</label>
                                    <form:input path="projectName" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" name="projectName" placeholder="Project Name"></form:input>
    								<form:errors path="projectName" class="alert-danger"></form:errors>
                                </div>
                                
                                <div class="form-group">
                                    <label>Đơn vị*</label>
                                    <form:select path="falcutyAddress" class="form-control" name="falcutyAddress">
                                    	<c:forEach items="${listFaculty}" var="faculty">
                                        	<option value="${faculty.faculty_Code}" <c:if test='${ currentUserFaculty != "" && faculty.faculty_Code == currentUserFaculty}'>selected="selected"</c:if> >${faculty.faculty_Name}</option>
                                      	</c:forEach>
                                    </form:select>
                                    <form:errors path="falcutyAddress" class="alert-danger"></form:errors>
                                </div>
                                
                                <%-- <div class="form-group">
                                    <label for="projectBudget">Kinh phí (triệu VNĐ)</label>
                                    <form:input path="projectBudget" class="form-control" name="projectBudget" placeholder="Budget"></form:input>
    								<form:errors path="projectBudget" class="alert-danger"></form:errors>
                                </div> --%>
                               	<%-- <div class="form-group">
                                    <label for="budgetMaterial">Kinh phí vật tư, vật liệu,…</label>
                                    <form:input path="budgetMaterial" class="form-control" name="budgetMaterial" placeholder="Other Fees"></form:input>
   									<form:errors path="budgetMaterial" class="alert-danger"></form:errors>
                               	</div> --%>
                               	<%-- <div class="form-group">
                                    <label for="projectFileUpload">Upload File thuyết minh đề tài*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(File size is 20 MB maximum)</i></label>
                                    <form:input path="projectFileUpload" name="projectFileUpload" type="file" placeholder="Source File"></form:input>
    								<form:errors path="projectFileUpload" class="alert-danger"></form:errors>
                                </div> --%>
                                
                                
	                        </div>
	                        <div class="col-lg-6">
	                        	<div class="form-group">
                                    <label for="projectLeader">Chủ nhiệm đề tài</label>
                                    <input path="projectLeader" class="form-control" disabled name="projectLeader" value="${currentUserName}" />
                                </div>
                                
                                <div class="form-group">
                                    <label for="projectStartDate">Thời gian bắt đầu <i class="hint-text"> (Click vào để chọn ngày bắt đầu)</i></label>
                                    <form:input path="projectStartDate" class="form-control" name="projectStartDate" readonly="true" placeholder="Project Start Date"></form:input>
    								<form:errors path="projectStartDate" class="alert-danger"></form:errors>
                                </div>
                                
                                <div class="form-group">
                                    <label for="projectEndDate">Thời gian hoàn thành <i class="hint-text"> (Click vào để chọn ngày kết thúc)</i></label>
                                    <form:input path="projectEndDate" class="form-control" name="projectEndDate" readonly="true" placeholder="Project End Date"></form:input>
    								<form:errors path="projectEndDate" class="alert-danger"></form:errors>
                                </div>
	                        </div>
	                        <!-- /.col-lg-6 (nested) -->
	                    </div>
	                    <!-- /.row (nested) -->
	                </div>
	                <div class="panel-body hidden">
		                <div class="row">
		                	<div class="col-lg-12">
		                		<div class="form-group">
                                    <label for="projectSurvey">6. Tổng quan tình hình nghiên cứu thuộc lĩnh vực của đề tài ở trong và ngoài nước</label>
                                    <form:textarea path="projectSurvey" name="projectSurvey" value="" class="form-control textarea hidden" />
   									<form:errors path="projectSurvey" class="alert-danger"></form:errors>
                               	 </div>
                                <div class="form-group">
                                    <label for="projectMotivation">7. Tính cấp thiết đề tài</label>
                                    <form:textarea path="projectMotivation" name="projectMotivation" class="form-control textarea hidden"/>
    								<form:errors path="projectMotivation" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="projectObjective">8. Mục tiêu của đề tài</label>
                                    <form:textarea path="projectObjective" name="projectObjective" value="" class="form-control textarea hidden" />
   									<form:errors path="projectObjective" class="alert-danger"></form:errors>
                               	 </div>
                               	 <div class="form-group">
                                	<label for="projectResult">9. Nội dung nghiên cứu</label>
	                                <form:textarea path="projectContent"  name="projectContent" class="form-control textarea hidden"/>
	                                <form:errors path="projectContent" class="alert-danger"></form:errors>
                            	 </div>
                                 
		                	</div>
		                </div>
	                </div>
	                <!-- /.panel-body -->
	            </div>
	            
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    Chọn lĩnh vực nghiên cứu của đề tài*  <i class="hint-text">(Giữ phím Ctrl và nhấn chuột để chọn nhiều lĩnh vực nghiên cứu)</i>
	                </div>
	                <div class="panel-body">
	                    <div class="row">
	                        <div class="col-lg-12">
                                <div class="form-group">
                                    <select multiple="true" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc" size="5" class="form-control" id="projectResearchFieldCodeList" name="projectResearchFieldCodeList">
                                    	<c:forEach items="${projectResearchFieldList}" var="proj_field">
	                                        <option value="${proj_field.PRJRSHF_Code}">${proj_field.PRJRSHF_Name}</option>
                                       	</c:forEach>
                                    </select>
                                </div>
	                        </div>
	                        <!-- /.col-lg-12 (nested) -->
	                    </div>
	                    <!-- /.row (nested) -->
	                </div>
	            </div>
	            
	            <div class="panel panel-default">
	            	<div class="panel-heading">
	                    Thành viên tham gia, vai trò, nội dung thực hiện, số ngày công và kinh phí tương ứng
	                </div>
	            	<div class="panel-body">
	                    <div class="row">
	                        <div class="col-lg-6">
	                        	<label for="threadResult">Thành viên tham gia <i class="hint-text">(Chọn Khoa/Viện và Bộ môn để lọc tìm thành viên)</i></label>
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
	                                <label for="projectMembers">Chọn Thành viên*</label>
	                                <div id="staff">
	                                <select class="form-control" id="members" >
	                                	<option value="">Chọn thành viên</option>
	                                	<c:forEach items="${staffList}" var="aStaff">
	                                    	<option value="${aStaff.staff_Code}">${aStaff.staff_Name}</option>
	                                   	</c:forEach>
	                                </select>
	                                <div id="error-members"></div>
	                                </div>
	                            </div>
		                            
	                            <div class="form-group">
	                                <label for="memberRole">Vai trò</label>
	                                <select class="form-control" id="memberRole">
	                                	<c:forEach items="${memberRolesList}" var="memberRole">
	                                     <option value="${memberRole.PROJPARTIROLE_Code}">${memberRole.PROJPARTIROLE_Description}</option>
	                                   	</c:forEach>
	                                </select>
                            	</div>
	                            <button type="button" class="btn btn-primary btn-xs" onclick="v_fAddMember();">Thêm</button>
	                        </div>
	                        
	                        <div class="col-lg-6" id="add-member">
	                            <div class="form-group">
	                               	<label for="taskContent">Nội dung công việc</label>
	                                <!-- <textarea id="taskContent" class="form-control textarea"></textarea> -->
									<textarea id="taskContent" class="form-control"></textarea>
	                           	 </div>
	                           	 <div class="form-group">
	                                <label for="memberWorkingDays">Số ngày công*</label>
	                                <input class="form-control" data-validation="custom" data-validation-optional="true" data-validation-regexp="^[0-9]*[1-9][0-9]*$" data-validation-error-msg="Giá trị phải là số nguyên" id="memberWorkingDays" value="" placeholder="Working days" />
	                                <div id="error-workingdays"></div>
	                             </div>
	                             <div class="form-group">
	                                <label for="taskBudget">Thành tiền*<i class="hint-text">(VNĐ - chỉ nhập các chữ số, không nhập dấu chấm, phảy)</i></label>
	                                <input class="form-control" data-validation="custom" data-validation-optional="true" data-validation-regexp="^[0-9]*[1-9][0-9]*$" data-validation-error-msg="Giá trị phải là số nguyên" id="taskBudget" value="" placeholder="Fee" />
	                                <div id="error-budget"></div>
	                             </div>
	                        </div>
	                        <!-- /.col-lg-6 (nested) -->
	                    </div>
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
		                                            <th>Hủy</th>
		                                        </tr>
		                                    </thead>
		                                    <tbody>
		                                        <tr class="no-records-found">
		                                        	<td colspan="6" align="center">Chưa có thành viên</td>
		                                        </tr>
		                                    </tbody>
		                                </table>
		                                
		                                <div id="error-member" >
			                               <input type="text" style="opacity: 0.0;" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc"  />
			                            </div>
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
	             	<div class="panel-body">
		             	<div class="row">
		             		<div class="col-lg-12">
		             		
		             		<div class="form-group">
                                    <label for="projectResult">Sản phẩm, chuyển giao kết quả nghiên cứu và đia chỉ ứng dụng</label>
                                    <form:textarea path="projectResult" name="projectResult" value="" class="form-control textarea" />
   									<form:errors path="projectResult" class="alert-danger"></form:errors>
                            </div>
                               	 
			             	<div class="form-group">
                            	<label for="budgetMaterial">Kinh phí vật tư, vật liệu,… <i class="hint-text">(VNĐ - chỉ nhập các chữ số, không nhập dấu chấm, phảy)</i></label>
                            	<form:input path="budgetMaterial" value="" data-validation="custom" data-validation-optional="true" data-validation-regexp="^[0-9]*[1-9][0-9]*$" data-validation-error-msg="Giá trị phải là số nguyên"  class="form-control" name="budgetMaterial" placeholder="Other Fees"></form:input>
								<form:errors path="budgetMaterial" class="alert-danger"></form:errors>
		                    </div>
			             	<div class="form-group">
                            	<label for="projectFileUpload">Upload File thuyết minh đề tài* <i class="hint-text">(File chỉ cho phép định dạng doc, docx, pdf, xls và xlsx. Kích thước không vượt quá 20MB)</i></label>
                            	<form:input path="projectFileUpload" name="projectFileUpload" type="file" placeholder="Source File" data-validation="required extension size" data-validation-error-msg-required="File upload là bắt buộc" data-validation-allowing="doc,docx,pdf,xls,xlsx" data-validation-error-msg-extension="Định dạng file không đúng" data-validation-error-msg-mime="Định dạng file không đúng" data-validation-max-size="20M" data-validation-error-msg-size="Kích thước file không được vượt quá 20MB"></form:input>
								<form:errors path="projectFileUpload" class="alert-danger"></form:errors>
		                 	</div>
		                 	</div>
	                 	</div>
                 	</div>
	             </div>
	             
	            <div class="panel">
	            	<button type="submit" class="btn btn-primary">Lưu</button>
                    <button type="reset" class="btn btn-primary cancel">Hủy</button>
	            </div>
            </form:form>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

<script type="text/javascript">

$('.textarea').each( function() {
    //CKEDITOR.replace( $(this).attr('id') );
});
 
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/list-projects.html";
	});
	
	/* $('#projectStartDate').datepicker({
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
        stepMonths: 12}); */
	
    // Validate in client 
    $.validate({
    	modules : 'file'
    });
        
	$('#projectStartDate').datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 1,
	      dateFormat : 'dd/mm/yy',
	      onClose: function( selectedDate ) {
	        $( "#projectEndDate" ).datepicker( "option", "minDate", selectedDate );
	      }
	});
  	$("#projectEndDate").datepicker({
	     defaultDate: "+1w",
	     changeMonth: true,
	     numberOfMonths: 1,
	     dateFormat : 'dd/mm/yy',
	     onClose: function( selectedDate ) {
	       $( "#projectStartDate" ).datepicker( "option", "maxDate", selectedDate );
	     }
   	});
  
});

function v_fAddMember(){
	var patt = new RegExp("^[0-9]*$");
	
	var sMemberCode = $("select#members").find(":selected").val();
	var sMemberName = $("select#members").find(":selected").text();
	var sMemberRoleCode = $("#memberRole").find(":selected").val();
	var sMemberRoleDescription = $("#memberRole").find(":selected").text();
	var sTask = $("#taskContent").val();
	var iMemberWorkingDays = $("#memberWorkingDays").val();
	var iBudget = $("#taskBudget").val();
	var isVerified = true;
	if(sMemberCode == "")
	{
		$("div#error-members").addClass("has-error").html("<span class='help-block form-error'>Trường thông tin này là bắt buộc</span>");	
		isVerified &= false;
	}else{
		$("div#error-members").removeClass("has-error").html("");
	}
	
	if(iMemberWorkingDays == '' || iMemberWorkingDays == null){
		$("div#error-workingdays").addClass("has-error").html("<span class='help-block form-error'>Trường thông tin này là bắt buộc</span>");
		isVerified &= false;
	}else{
		$("div#error-workingdays").removeClass("has-error").html("");
	}
	
	if(iBudget == '' || iBudget == null){
		$("div#error-budget").addClass("has-error").html("<span class='help-block form-error'>Trường thông tin này là bắt buộc</span>");
		isVerified &= false;
	}else{
		$("div#error-budget").removeClass("has-error").html("");
	}
	
	var isErrorExisted= $("#add-member").find("span.form-error").text();
	if(isErrorExisted != ''){
		isVerified &= false;
	}
	if(isVerified == false){
		return ;
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
		
		//Remove required block
		$("#error-member").html("");
	}
}

function v_fClearMember(the_oElement){
	var oNextSiblings = $(the_oElement).parents("tr").siblings();
	var sCurrentElement = "";
	var sHtml = "";
	var sErrorHtml = "";
	if(oNextSiblings.length > 0)
	{
		$(oNextSiblings).each(function(){
			if($(this).children('td:first').html() != null || $(this).children('td:first').html() != "undefined" || $(this).children('td:first').html() != undefined)
			{
				// Do nothing
			}else{
				sHtml = "<tr class='no-records-found'>";
				sHtml += "<td colspan='6' align='center'>Chưa có thành viên</td>";
				sHtml += "</tr>";
				
				sErrorHtml = "<input type='text' style='opacity: 0.0;' data-validation='required' data-validation-error-msg='Trường thông tin này là bắt buộc'  />";
				
				$("#error-member").html(sErrorHtml);
				$("#projectMemberList tbody").html(sHtml);
			}
		});	
	}else{
		sHtml = "<tr class='no-records-found'>";
		sHtml += "<td colspan='6' align='center'>Chưa có thành viên</td>";
		sHtml += "</tr>";
		
		sErrorHtml = "<input type='text' style='opacity: 0.0;' data-validation='required' data-validation-error-msg='Trường thông tin này là bắt buộc'  />";
		
		$("#error-member").html(sErrorHtml);
		$("#projectMemberList tbody").html(sHtml);
	}
	// Remove current row
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

