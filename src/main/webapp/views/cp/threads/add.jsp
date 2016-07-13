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
            <h1 class="page-header">Thêm mới đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm mới đề tài
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/threads.html"/>" class="alert-link">Quay lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/save-a-thread.html" method="POST" commandName="threadFormAdd" role="form" enctype="multipart/form-data">
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label>Loại hình*</label>
	                                    <form:select path="threadCatCode" class="form-control" name="threadCatCode">
	                                    	<c:forEach items="${threadCategory}" var="threadCat">
		                                        <option value="${threadCat.PROJCAT_Code}">${threadCat.PROJCAT_Name}</option>
	                                       	</c:forEach>
	                                    </form:select>
	                                    <form:errors path="threadCatCode" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="threadName">Tên đề tài*</label>
	                                    <form:input path="threadName" class="form-control" name="threadName" placeholder="Project Name"></form:input>
	    								<form:errors path="threadName" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                	<label for="threadReportingAcademicDate">Năm đăng ký*</label>
		                                <form:select path="threadReportingAcademicDate" class="form-control" name="threadReportingAcademicDate" >
		                                	<c:forEach items="${threadReportingAcademicDate}" var="threadDate">
		                                     <option value="${threadDate.ACAYEAR_Code}">${threadDate.ACAYEAR_Code}</option>
		                                   	</c:forEach>
		                                </form:select>
		                                <form:errors path="threadReportingAcademicDate" class="alert-danger"></form:errors>
	                            	</div>
	                            	<div class="form-group hidden">
	                                    <label for="threadStatus">Trạng thái</label>
	                                    <form:hidden path="threadResult" value="" class="form-control" />
	                                    <form:select path="threadStatus" class="form-control" name="threadStatus">
	                                    	<c:forEach items="${projectStatuses}" var="threadStat">
	                                    		<c:if test="${threadStat.PROJSTAT_Code == 'SUBMIT'}">
		                                        <option value="${threadStat.PROJSTAT_Code}">${threadStat.PROJSTAT_Description}</option>		                                       
	                                       		</c:if>
	                                       	</c:forEach>
	                                    </form:select>
	    								<form:errors path="threadStatus" class="alert-danger"></form:errors>
                                	</div>
                                	<div class="form-group">
	                                    <label for="threadStartDate">Ngày bắt đầu*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
	                                    <form:input path="threadStartDate" class="form-control year" name="threadStartDate" readonly="true" placeholder="Start date"></form:input>
	    								<form:errors path="threadStartDate" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="threadEndDate">Ngày kết thúc*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
	                                    <form:input path="threadEndDate" class="form-control year" name="threadEndDate" readonly="true" placeholder="End date"></form:input>
	    								<form:errors path="threadEndDate" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="threadBudget">Kinh phí (triệu VNĐ)</label>
	                                    <form:input path="threadBudget" class="form-control" name="threadBudget" placeholder="Budget"></form:input>
	    								<form:errors path="threadBudget" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="threadMotivation">Lý do thực hiện đề tài</label>
	                                    <!-- <form:input path="threadMotivation" class="form-control" name="threadMotivation" placeholder="Motivation"></form:input> -->
	                                    <form:textarea path="threadMotivation" class="form-control"/>
	    								<form:errors path="threadMotivation" class="alert-danger"></form:errors>
	                                </div>
	                                <!-- 
	                                <button type="button" class="btn btn-primary" id="addANewTopic">Lưu</button>
	                                 -->
	                                 <button type="submit" class="btn btn-primary">Lưu</button>
	                                <!-- <button type="reset" class="btn btn-primary">Clear</button> -->
	                                <button type="reset" class="btn btn-primary cancel">Hủy</button>
	                        </div>
	                        <div class="col-lg-6">
                                <div class="form-group">
                                    <label for="threadSourceFile">File thuyết minh<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(File size is 20 MB maximum)</i></label>
                                    <form:input path="threadSourceFile" name="threadSourceFile" type="file" placeholder="Source File"></form:input>
    								<form:errors path="threadSourceFile" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label class="hidden" for="threadResult">Kết quả đánh giá</label>
                                    <form:hidden path="threadResult" value="" class="form-control" />
    								<form:errors path="threadResult" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                	<label for="threadContent">Nội dung</label>
	                                <form:textarea path="threadContent" class="form-control"/>
	                                <form:errors path="threadContent" class="alert-danger"></form:errors>
                            	</div>
                                <div class="form-group">
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
                                    <div id="staff">
	                                    <select class="form-control" name="staff">
											<option value="">Chọn thành viên</option>
	                                    </select>
                                    </div>
                                   <br>
                                   <label for="threadResult">Vai trò của thành viên*</label>
                                   <select class="form-control" name="staff_role" id="staff_role">
                                    	<!-- <option value="PROJECT_LEADER">Chủ nhiệm đề tài</option> -->
	                               		<option value="PROJECT_TECHNICAL">Kỹ thuật viên</option>
                                       	<option value="PROJECT_SECRETARY">Thư ký đề tài</option>
                                       	<option value="PROJECT_RESEARCHER">Nghiên cứu viên</option>
                                    </select>
                                </div>
                                <button type="button" class="btn btn-primary" id="choose_members" onclick="v_fChooseMembers();">Chọn</button>
                                <div class="form-group">
                                    <div class="ui-widget-content" style="height: 200px; overflow: auto;" id="list_staffs">
                                    </div>
                                </div>
	                        </div>
	                        <!-- /.col-lg-6 (nested) -->
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
</div>
<!-- /#page-wrapper -->

<script type="text/javascript">

$('textarea').each( function() {
    CKEDITOR.replace( $(this).attr('id') );
});
 
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/threads.html";
	});
	
	$('#threadStartDate').datepicker({
	      defaultDate: "+1w",
	      changeMonth: true,
	      numberOfMonths: 1,
	      onClose: function( selectedDate ) {
	        $( "#threadEndDate" ).datepicker( "option", "minDate", selectedDate );
	      }
  	});
    $("#threadEndDate").datepicker({
       defaultDate: "+1w",
       changeMonth: true,
       numberOfMonths: 1,
       onClose: function( selectedDate ) {
         $( "#threadStartDate" ).datepicker( "option", "maxDate", selectedDate );
       }
     });
});

function showDepartment(sFaculty)
{
	var sGeneratingUrl = "${baseUrl}/cpservice/getdepartments.html";
	var sFacultyCode = sFaculty.options[sFaculty.selectedIndex].value;  
	//$("#department").html( "" );
	//$("#staff").html( "" );
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
	} 
}

function showStaff(sDepartment) {
	var sGeneratingUrl = "${baseUrl}/cpservice/getstaffs.html";
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

function v_fChooseMembers() {
	var sz_ChosenStaffs = ""; 
	var selectedStaffs = [];
	var selectedStaffRole = [];
    $("select#staff :selected").each(function(){
    	if($(this).val() != null && $(this).val() != undefined)
   		{
    		sz_ChosenStaffs += "<div class=''><span>" +$(this).text()+" : "+$("#staff_role :selected").text()+"</span> <button type='button' onclick='v_fRemoveChosenStaff(this.parentElement);' >Remove</button>";
    		sz_ChosenStaffs += "<input type='hidden' value='"+$(this).val()+"' name='staffsRole' />";
    		sz_ChosenStaffs += "<input type='hidden' value='"+$("#staff_role").val()+"' name='RoleList' /></div>";
    		
   		}
    });
    
    $("#list_staffs").append(sz_ChosenStaffs);
    return false;
}

function v_fRemoveChosenStaff(the_o_Object)
{
	the_o_Object.parentNode.removeChild(the_o_Object);
}
</script>

