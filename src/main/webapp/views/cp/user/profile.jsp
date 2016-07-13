<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Jquery UI CSS -->
<link href="<c:url value="/assets/css/jquery-ui.css" />" rel="stylesheet">

<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Thông tin người dùng</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form action="${baseUrl}/cp/edit-staff-detail.html" method="POST" commandName="staffFormEdit" role="form" accept-charset="UTF-8">
		<div class="row">
			<div class="col-lg-5">
				<div class="well">
					<h4>Họ tên</h4>
					<p class="value">${staffName}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"}>
						<form:input path="staffName" class="form-control" name="staffName" type="text" placeholder="Name" value="${staffName}"></form:input>
	    				<input type="hidden" value="${staffName}" name="staffOldName" />
	    				<form:errors path="staffName" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<div class="well">
					<h4>Email</h4>
					<p class="value">${staffEmail}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"}>
						<form:input path="staffEmail" class="form-control" name="staffEmail" type="text" placeholder="Email" value="${staffEmail}"></form:input>
	    				<input type="hidden" value="${staffEmail}" name="staffOldEmail" />
	    				<form:errors path="staffEmail" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<div class="well">
					<h4>Ngày sinh</h4>
					<p class="value">${staffDateOfBirth}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"}>
						<form:input path="staffDateOfBirth" readonly="true" id="staffDateOfBirth" class="form-control" name="staffDateOfBirth" type="text" placeholder="Ngày sinh" value="${staffDateOfBirth}"></form:input>
	    				<form:errors path="staffDateOfBirth" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<!-- /.col-lg-4 -->
				<div class="well">
					<h4>Giới tính</h4>
					<p class="value">${staffGender != "" ? (staffGender == "male" ? "Nam" : "Nữ") : "N/A"}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"} >
						<form:select path="staffGender" class="form-control" name="staffGender">
	                       	<option value="male" <c:if test='${staffGender == "male"}'> selected </c:if> >Nam</option>
	                       	<option value="female" <c:if test='${staffGender == "female"}'> selected </c:if> >Nữ</option>
	                    </form:select>
	                    <form:errors path="staffGender" class="alert-danger"></form:errors>
	                </div>
				</div>
			</div>
			
			<div class="col-lg-5">
				<div class="well">
					<h4>Khoa/Viện</h4>
					<p class="value">${staffFacultyName}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"} >
						<form:select path="staffFaculty" class="form-control" name="staffFaculty" onchange="showDepartment(this);">
							<option <c:if test="${resetFaculty eq 1}"> selected </c:if> value="">Chọn Khoa/Viện</option>
							<c:forEach items="${facultyList}" var="faculty">
	                       		<option value="${faculty.faculty_Code}" <c:if test="${staffFacultyCode eq faculty.faculty_Code && resetFaculty != 1}">selected</c:if>>${faculty.faculty_Name}</option>
	                       	</c:forEach>
	                    </form:select>
	                    <input type="hidden" value="${staffFacultyName}" name="staffOldFacultyName" />
	                    <form:errors path="staffFaculty" class="alert-danger"></form:errors>
                    </div>
				</div>
				
				<div class="well">
					<h4>Bộ môn</h4>
					<p class="value">${staffDepartmentName}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"} >
						<div id="default_department">
							<form:select path="staffDepartment" class="form-control" name="staffDepartment">
								<option value="">Chọn Bộ môn</option>
								<c:forEach items="${departmentList}" var="department">
		                       		<option value="${department.department_Code}" <c:if test="${staffDepartmentCode eq department.department_Code}">selected</c:if>>${department.department_Name}</option>
		                       	</c:forEach>
		                    </form:select>
		                    <input type="hidden" value="${staffDepartmentName}" name="staffOldDepartmentName" />
		                    <form:errors path="staffDepartment" class="alert-danger"></form:errors>
	                    </div>
	                    <div id="department"></div>
                    </div>
				</div>
				
				<div class="well">
					<h4>Học hàm học vị</h4>
					<%-- <p class="value">${academicRank.academicRank_VNName}</p> --%>
					<p class="value">${academicRankName}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"} >
						<div id="default_academicRank">
							<form:select id="staffAcademicRank" path="staffAcademicRank" class="form-control" name="staffAcademicRank">
							<c:forEach items="${academicRankList}" var="acaRank">
	                       		<option value="${acaRank.academicRank_Code}" <c:if test="${academicRankCode eq acaRank.academicRank_Code}">selected</c:if>>${acaRank.academicRank_VNName}</option>
	                       	</c:forEach>							
	                    </form:select>
	                    <input type="hidden" value="${academicRankName}" name="staffOldAcademicRank" />
	                    <form:errors path="staffAcademicRank" class="alert-danger"></form:errors>
	                    </div>
	                    <div id="academicRank"></div>
                    </div>
				</div>
				
				<div class="well">
					<h4>Điện thoại</h4>
					<p class="value">${staffPhone}</p>
					<div ${error == 1 ? "class='form'" : "class='form hidden'"} >
						<form:input path="staffPhone" class="form-control" name="staffPhone" type="text" placeholder="Phone" value="${staffPhone}"></form:input>
	    				<form:errors path="staffPhone" class="alert-danger"></form:errors>
	    				<input type="hidden" value="${staffPhone}" name="staffOldPhone" />
    				</div>
				</div>
				
				
				<!-- /.col-lg-4 -->
			</div>
		</div>
		<!-- /.row -->
	
		<div class="row">
			<div class="col-lg-4">
				<form:hidden path="staffId" name="staffId" value="0"/>
				<button type="button" ${error == 1 ? "class='btn btn-primary value hidden'" : "class='btn btn-primary value'"} onclick="v_fEditTheStaffInfo(1);">Chỉnh sửa</button>
				<button type="submit" ${error == 1 ? "class='form btn btn-primary'" : "class='form btn btn-primary hidden'"} >Lưu</button>
				<button type="button" ${error == 1 ? "class='form btn btn-primary'" : "class='form btn btn-primary hidden'"} onclick="v_fEditTheStaffInfo(0);">Hủy</button>
			</div>
			<!-- /.col-lg-4 -->
		</div>
		<!-- /.row -->
	</form:form>
</div>
<!-- /#page-wrapper -->
<script type="text/javascript">
	$(document).ready(function() {
		$('button.cancel').click(function() {
			window.location = baseUrl + "/cp/users.html";
		});
		
		$('#staffDateOfBirth').datepicker({
			maxDate : 0,
			changeMonth: true,
	        changeYear: true,
	        showButtonPanel: true,
	        dateFormat : 'dd/mm/yy',
	        stepMonths: 12,
	        yearRange: "-50:+0"
	    });
	});
	
	function showDepartment(sFaculty)
	{
		var sGeneratingUrl = "${baseUrl}/cpservice/loaddepartments.html";
		var sFacultyCode = sFaculty.options[sFaculty.selectedIndex].value;  
		console.log(sFacultyCode);
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
						$("#default_department").html("");
					}
				});
		}else{
			var sDepartment = '<select class="form-control" name="staffDepartment" id="staffDepartment">';
				sDepartment += '<option value="">Chọn Bộ môn</option>';
				sDepartment +=  '</select>';
			$("#department").html( sDepartment );
		}
	}
	
	function v_fEditTheStaffInfo(the_i_Mode)
    {
		if(the_i_Mode == 1){
			$("#staffFormEdit").find(".form").each(function(){
				$(this).removeClass("hidden");
			});
			
			$("#staffFormEdit").find(".value").each(function(){
				$(this).addClass("hidden");
			});
		}else{
			$("#staffFormEdit").find(".form").each(function(){
				$(this).addClass("hidden");
			});
			
			$("#staffFormEdit").find(".value").each(function(){
				$(this).removeClass("hidden");
			});
		}
		
		
        /* var sz_AddUrl = "${baseUrl}/cpservice/editthestaff.json";
	      $.ajax({
	          type: "POST",
	          url: sz_AddUrl,
	          data: "id=" + the_i_StaffId,
	          success: function(response) {
	              if (response.status) {
	                  alert(response.message);
	                  //window.location = "${baseUrl}/cp/coursing.html";
	              } else {
	                  alert(response.message);
	              }
	          },
	          error: function(e) {
	              alert('Error: ' + e);
	          }
	      }); */
    }
</script>