<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- Jquery UI CSS -->
<link href="<c:url value="/assets/css/jquery-ui.css" />" rel="stylesheet">

<style>.ui-datepicker-calendar {display: none;}​</style>

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>		
<script src="<c:url value="/assets/js/jquery.form-validator.js"/>"></script>   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thêm mới sách giáo trình</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <form:form action="${baseUrl}/cp/save-a-book.html" method="POST" commandName="bookFormAdd" role="form" enctype="multipart/form-data">
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm mới sách giáo trình
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/books.html"/>" class="alert-link">Trở lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
	                    <div class="row">
	                        <div class="col-lg-6">
	                                <div class="form-group">
	                                    <label for="bookName">Tên sách giáo trình*</label>
	                                    <form:input path="bookName" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc"  name="bookName" placeholder="Book Name"></form:input>
	    								<form:errors path="bookName" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="bookPublisher">Nhà xuất bản*</label>
	                                    <form:input path="bookPublisher" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc"  name="bookPublisher" placeholder="Book Publisher Name"></form:input>
	    								<form:errors path="bookPublisher" class="alert-danger"></form:errors>
	                                </div>
	                                 <div class="form-group">
	                                    <label for="bookYear">Năm xuất bản*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(format : YYYY)</i></label>
	                                    <form:input  path="bookYear" class="form-control year" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc"  name="bookYear" readonly="true" placeholder="YYYY"></form:input>
	    								<form:errors path="bookYear" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                	<label for="bookMonth">Tháng xuất bản*</label>
		                                <form:select path="bookMonth" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc"  name="bookMonth" >
		                                	<% for(int i = 1; i<=12; i++) { %>
		                                    	<option value="<%= i %>">Tháng <%= i %></option>
		                                   	<% } %>
		                                </form:select>
		                                <form:errors path="bookMonth" class="alert-danger"></form:errors>
	                            	</div>
	                        </div>
	                        <div class="col-lg-6">
	                        		<div class="form-group">
	                                	<label for="bookReportingAcademicDate">Năm kê khai*</label>
		                                <form:select path="bookReportingAcademicDate" class="form-control" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc"  name="bookReportingAcademicDate">
		                                	<c:forEach items="${bookReportingAcademicDateList}" var="bookDate">
		                                     <option value="${bookDate.ACAYEAR_Code}">${bookDate.ACAYEAR_Code}</option>
		                                   	</c:forEach>
		                                </form:select>
		                                <form:errors path="bookReportingAcademicDate" class="alert-danger"></form:errors>
	                            	</div>
	                        		<div class="form-group">
	                                    <label for="bookAuthorList">Tác giả (danh sách tác giả cách nhau bởi dấu phảy)</label>
	                                    <form:input path="bookAuthorList" class="form-control" name="bookAuthorList" placeholder="Authors"></form:input>
	    								<form:errors path="bookAuthorList" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                	<label for="bookISBN">Chỉ số ISBN</label>
	                                	<form:input path="bookISBN" class="form-control" name="bookISBN" placeholder="ISBN"></form:input>
	                                	<form:errors path="bookISBN" class="alert-danger"></form:errors>
	                                </div>
	                                <div class="form-group">
	                                    <label for="bookFileUpload">File sách giáo trình*<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;">(File size is 20 MB maximum)</i></label>
	                                    <form:input path="bookFileUpload" name="bookFileUpload" type="file" placeholder="Source File" data-validation="required extension size" data-validation-error-msg-required="File upload là bắt buộc" data-validation-allowing="doc,docx,pdf,xls,xlsx" data-validation-error-msg-extension="Định dạng file không đúng" data-validation-error-msg-mime="Định dạng file không đúng" data-validation-max-size="20M" data-validation-error-msg-size="Kích thước file không được vượt quá 20MB"></form:input>
	    								<form:errors path="bookFileUpload" class="alert-danger"></form:errors>
	                                </div>
	                        </div>
	                        <!-- /.col-lg-6 (nested) -->
	                    </div>
	                    <!-- /.row (nested) -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
            
            <div class="panel panel-default">
            	<div class="panel-heading">
                    Thêm tác giả của sách giáo trình là cán bộ ĐHBK
                </div>
            	<div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                        	 	<label for="projectMembers">Chọn Khoa/Viện</label>
                                <select class="form-control" name="faculty" onchange="showDepartment(this);" >
                                	<option value="">Chọn Khoa/Viện</option>
                                	<c:forEach items="${listFaculty}" var="faculty">
                                     	<option value="${faculty.faculty_Code}">${faculty.faculty_Name}</option>
                                   </c:forEach>
                                </select>
                                
                                <br>
                                <div id="department">
                                 <label for="projectMembers">Chọn Bộ môn</label>
                                 <select class="form-control" name="department">
									<option value="">Chọn Bộ môn</option>
                                 </select>
                                </div>
                                
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
	                                </div>
	                                <div id="error-members"></div>
	                            </div>
                            	<button type="button" class="btn btn-primary btn-xs" onclick="v_fAddMember();">Thêm</button>
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
	                                            <th>Xóa</th>
	                                        </tr>
	                                    </thead>
	                                    <tbody>
	                                        <tr class="no-records-found">
	                                        	<td colspan="6" align="center">Chưa có thành viên</td>
	                                        </tr>
	                                    </tbody>
	                                </table>
	                                <!--  
	                                <div id="selected-error-member" >
		                               <input type="text" style="opacity: 0.0;" data-validation="required" data-validation-error-msg="Trường thông tin này là bắt buộc"  />
		                            </div>-->
	                            </div>
	                            <!-- /.table-responsive -->
	                        </div>
	                        <!-- /.panel-body -->
	                    </div>
                    </div>
                </div>
                <!-- /.panel-body -->
                
              	<button type="submit" class="btn btn-primary" id="addANewBook">Lưu</button>
                <button type="reset" class="btn btn-primary cancel">Hủy</button>
            </div>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    </form:form>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

<script type="text/javascript">
<!--
//-->
//var paperConvertedHours = ${paperConvertedHours};

$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/books.html";
	});
	
	// Validate in client 
    $.validate({
    	modules : 'file'
    });
	
	$('.year').datepicker({
		changeMonth: false,
        changeYear: true,
        showButtonPanel: true,
        dateFormat : 'yy',
        stepMonths: 12,
		onClose: function() {
	        var iYear = $("#ui-datepicker-div .ui-datepicker-year :selected").val();
	        $(this).datepicker('setDate', new Date(iYear, 1));
     	},
     	beforeShow: function() {
     		if ((selDate = $(this).val()).length > 0)
 	        {
 	          iYear = selDate.substring(selDate.length - 4, selDate.length);
 	          $(this).datepicker('option', 'defaultDate', new Date(iYear, 1));
 	          $(this).datepicker('setDate', new Date(iYear, 1));
 	        }
  	    }
  	});
	// Hide month when choose a year
	$("#bookYear").click(function () {
		$(".ui-datepicker-month").hide();
	});
	
	$("#bookYear").focus(function () {
        $(".ui-datepicker-calendar").hide();
        $(".ui-datepicker-month").hide();
    });
	
	// Init changing country
	//v_fChangeCategory();
});

/*
function v_fChangeCategory(){
	var sCateCode = $("#paperCatCode").val();
	var sYearCode = $("#patentReportingAcademicDate").val();
	var iConvertedHours = 0;
	if((paperConvertedHours != undefined || paperConvertedHours != "undefined") && paperConvertedHours != ""){
		for(var key in paperConvertedHours)
		{
			if(key == sYearCode){
				var aPaperHours = paperConvertedHours[key];
				for(var sCat in aPaperHours){
					if(sCat == sCateCode){
						iConvertedHours = aPaperHours[sCat];
					}
				}

			}
		}
	}
	$("#paperPubConHours").val(iConvertedHours);
}*/

function b_fCheckExistsMember(the_sz_StaffCode){
	if(the_sz_StaffCode != "")
	{
		var b_Existed = false;
		$("table#projectMemberList tbody tr").each(function(){
			if(the_sz_StaffCode == $(this).children('td:first').find("input").val()){
				b_Existed = true;
				return true;
			}
		});
		return b_Existed;
	}else{
		return false;	
	}
}

var iCounter = 0;
function v_fAddMember(){
	var sMemberCode = $("select#members").find(":selected").val();
	var sMemberName = $("select#members").find(":selected").text();
	var isVerified = true;
	if(sMemberCode == "")
	{
		$("div#error-members").addClass("has-error").html("<span class='help-block form-error'>Trường thông tin này là bắt buộc</span>");	
		isVerified &= false;
	}else{
		var isMemberExisted = b_fCheckExistsMember(sMemberCode);
		if(isMemberExisted == true){
			$("div#error-members").addClass("has-error").html("<span class='help-block form-error'>Thành viên đã tồn tại</span>");
			return ;
		}else{
			$("div#error-members").removeClass("has-error").html("");
		}
	}
	
	var isErrorExisted= $("#add-member").find("span.form-error").text();
	if(isErrorExisted != ''){
		isVerified &= false;
	}
	if(isVerified == false){
		return ;
	}

	var sAddedMember = "";
	iCounter++;
	if(sMemberName != "" && sMemberCode != "")
	{
		// Remove no records found column
		$("table#projectMemberList tbody tr.no-records-found").remove();
		$("#taskContent").val("");
		$("#memberWorkingDays").val("");
		$("#taskBudget").val("");
		
		sAddedMember 	+= "<tr>";
		sAddedMember 	+= "<td><span>"+sMemberName+"</span><input name='projectMembers' type='hidden' value='"+sMemberCode+"'/></td>";		
		sAddedMember 	+= "<td><button type='button' onclick='v_fClearMember(this);' class='btn btn-warning btn-xs' title='Hủy' >Xóa</button></td>";
		sAddedMember 	+= "</tr>";
		$("table#projectMemberList tbody").append(sAddedMember);
		
		//Remove required block
		$("#selected-error-member").html("");
		$("select#members option[value='"+sMemberCode+"']").remove();
	}
}

function v_fClearMember(the_oElement){
	var oNextSiblings = $(the_oElement).parents("tr").siblings();
	var currentMemberCode = $(the_oElement).parents("tr").find("input").val();
	var currentMemberText = $(the_oElement).parents("tr").find("span").text();
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
				
				$("#selected-error-member").html(sErrorHtml);
				$("#projectMemberList tbody").html(sHtml);
			}
		});	
	}else{
		sHtml = "<tr class='no-records-found'>";
		sHtml += "<td colspan='6' align='center'>Chưa có thành viên</td>";
		sHtml += "</tr>";
		
		sErrorHtml = "<input type='text' style='opacity: 0.0;' data-validation='required' data-validation-error-msg='Trường thông tin này là bắt buộc'  />";
		
		$("#selected-error-member").html(sErrorHtml);
		$("#projectMemberList tbody").html(sHtml);
	}
	var optionHtml = "<option value='"+currentMemberCode+"'>"+currentMemberText+"</option>";
	console.log(optionHtml);
	$("select#members").append(optionHtml);
	
	// Remove current row
	iCounter--;
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

