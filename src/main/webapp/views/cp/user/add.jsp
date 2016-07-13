<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">
<style>

.tree li {
    list-style-type:none;
    margin:0;
    padding:10px 5px 0 5px;
    position:relative
}
.tree li::before, .tree li::after {
    content:'';
    left:-30px;
    position:absolute;
    right:auto
}
.tree li::before {
    border-left:1px solid #999;
    bottom:50px;
    height:100%;
    top:0;
    width:1px
}
.tree li::after {
    border-top:1px solid #999;
    height:20px;
    top:25px;
    width:25px
}
.tree li span {
    -moz-border-radius:5px;
    -webkit-border-radius:5px;


    display:inline-block;
    padding:3px 8px;
    text-decoration:none
}
.tree li.parent_li>span {
    cursor:pointer
}

.tree li:last-child::before {
    height:30px
}
.tree li.parent_li>span:hover, .tree li.parent_li>span:hover+ul li span {
    background:#eee;
    border:1px solid #94a0b4;
    color:#000
}
</style>	   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thêm mới User</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thêm mới User
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/users.html"/>" class="alert-link">Quay lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>
                    <form:form action="${baseUrl}/cp/save-an-user.html" method="POST" commandName="userFormAdd" role="form">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="username">Tên User</label>
                                <form:input path="username" class="form-control" name="username" type="text" placeholder="Username"></form:input>
   								<form:errors path="username" class="alert-danger"></form:errors>
                            </div>
                            <div class="form-group">
                                <label for="password">Mật khẩu</label>
                                <form:input path="password" class="form-control" name="password" type="password" placeholder="Password"></form:input>
								<form:errors path="password" class="alert-danger"></form:errors>
                           	</div>
                           	<div class="form-group">
								<label>Khoa/Viện</label>
								<form:select path="staffFaculty" class="form-control" name="staffFaculty" onchange="showDepartment(this);">
									<c:forEach items="${facultyList}" var="faculty">
		                       			<option value="${faculty.faculty_Code}">${faculty.faculty_Name}</option>
		                       		</c:forEach>
			                    </form:select>
			                    <form:errors path="staffFaculty" class="alert-danger"></form:errors>
							</div>
                           	<div class="form-group">
								<label>Bộ môn</label>
								<div id="default_department">
									<form:select path="staffDepartment" class="form-control" name="staffDepartment">
										<c:forEach items="${departmentList}" var="department">
				                       		<option value="${department.department_Code}" <c:if test="${staffDepartementCode eq department.department_Code}">selected</c:if>>${department.department_Name}</option>
				                       	</c:forEach>
				                    </form:select>
			                    </div>
			                    <div id="department"></div>
			                    <form:errors path="staffDepartment" class="alert-danger"></form:errors>
							</div>
							<div class="tree form-group">
								<label>Chức năng</label>
					            <ul>
						            	<c:forEach items="${funcsParentsPermissionList}" var="showedParentPermission">	
							                <li class="checkbox">
							                	<label><input type="checkbox" name="functions" value="${showedParentPermission.FUNC_CODE}" /> <span>${showedParentPermission.FUNC_NAME}</span></label>
							                	<ul>
							                	<c:forEach items="${funcsChildrenPermissionList}" var="showedChildrenPermission">
							                		<c:if test="${showedChildrenPermission.FUNC_PARENTID == showedParentPermission.FUNC_ID}">
							                			<li>
														<label><input type="checkbox" name="functions" value="${showedChildrenPermission.FUNC_CODE}" /> <span>${showedChildrenPermission.FUNC_NAME}</span></label>
														</li>									                	
								                    </c:if>
							                	</c:forEach>
							                	</ul>
							                </li>
						                </c:forEach>
						                <!-- <li>
					                	<span><i class="icon-minus-sign"></i>Quản lý thực hiện đề tài</span>
					                    <ul>
					                        <li>
						                        <input type="checkbox" name="function1" value="" /> <span><i class="icon-leaf"></i> Quản lý chuyên đề</span> 
					                        </li>
					                    </ul>
					                </li> -->
					            </ul>
							</div>
                            
                            <button type="submit" class="btn btn-primary">Lưu</button>
                            <!-- <button type="reset" class="btn btn-primary">Xóa</button> -->
                            <button type="reset" class="btn btn-primary cancel">Hủy</button>
                        </div>
                        <!-- /.col-lg-6 (nested) -->
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="email">Email</label>
                                <form:input path="email" class="form-control" name="email" placeholder="Email"></form:input>
								<form:errors path="email" class="alert-danger"></form:errors>
                            </div>
                            <div class="form-group">
                               <label>Role</label>
                               <form:select path="role" class="form-control" name="role">
                                    <c:if test="${currentUserRole eq 'ROLE_ADMIN' || currentUserRole eq 'SUPER_ADMIN'}">
                                    	<option value="ROLE_ADMIN">Quản trị</option>
                                    </c:if>
                                   	<option value="ROLE_USER" selected>Người dùng</option>
                               </form:select>
                           	</div>
                           	<div class="form-group">
                               <label>Kích hoạt ?</label>
                               <div class="checkbox">
                                   <label>
                                       <form:checkbox path="activated" name="activated" checked="checked" value="1"></form:checkbox>Kích hoạt
                                   </label>
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
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/users.html";
	})
});
</script>