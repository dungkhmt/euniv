<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Thêm giảng viên</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/mm/save-a-professor.html" method="POST" commandName="staffFormAdd" role="form" accept-charset="UTF-8">
		<div class="row">
		<c:if test="${status != null}">	              
		<div class="alert alert-success">
			${status}
		</div>
		</c:if>
			<div class="col-lg-4">
				<div class="form-group">
					<label>Họ tên</label>
					<div class='form'>
						<form:input path="staffName" class="form-control" name="staffName" type="text" placeholder="Name" value=""></form:input>
	    				<form:errors path="staffName" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<div class="form-group">
					<label>Khoa/viện </label>
					<div class='form'>
						<select id="staffFaculty" class="form-control">
							<c:forEach items="${facultyList}" var="faculty">
	                       		<option value="${faculty.faculty_Code}">${faculty.faculty_Name}</option>
	                       	</c:forEach>							
	                    </select>	                    
                    </div>
				</div>
				
				<div class="form-group">
					<label>Email</label>
					<div class='form'>
						<form:input path="staffEmail" class="form-control" name="staffEmail" type="text" placeholder="Email" value=""></form:input>
	    				<form:errors path="staffEmail" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<!-- /.col-lg-4 -->
			</div>
	
			<div class="col-lg-4">		
			
				<div class="form-group">
					<label>Học hàm học vị</label>
					<div class='form'>
						<form:select id="staffAcademicRank" path="staffAcademicRank" class="form-control" name="staffAcademicRank">
							<c:forEach items="${academicRankList}" var="academicRank">
	                       		<option value="${academicRank.academicRank_Code}">${academicRank.academicRank_VNName}</option>
	                       	</c:forEach>							
	                    </form:select>
	                    <form:errors path="staffAcademicRank" class="alert-danger"></form:errors>
                    </div>
				</div>	
			
				<div class="form-group" id="DepartmentGroup" style="">
					<label>Bộ môn</label>
					<div class='form'>
						<form:select path="staffDepartment" class="form-control" name="staffDepartment">
							<c:forEach items="${departmentList}" var="department">
	                       		<option value="${department.department_Code}" <c:if test="${staffDepartementCode eq department.department_Code}">selected</c:if>>${department.department_Name}</option>
	                       	</c:forEach>
	                    </form:select>
	                    <form:errors path="staffDepartment" class="alert-danger"></form:errors>
                    </div>
				</div>				
				
				<div class="form-group">
					<label>Điện thoại</label>
					<div class='form'>
						<form:input path="staffPhone" class="form-control" name="staffPhone" type="text" placeholder="Phone" value="${staffPhone}"></form:input>
	    				<form:errors path="staffPhone" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<!-- /.col-lg-4 -->
			</div>
		</div>
		<!-- /.row -->
		
		<div class="panel panel-default">
			<div class="panel-heading"><h4>Từ khóa chuyên ngành</h4></div>
			<div class="panel-body">
			<div id="data-keyword">
				<c:forEach items="${specializationKeywords}" var="speKw">
					<div class="btn btn-default keyword" onclick="rem('${speKw.KW_VNName}','${speKw.KW_Code}')">
						${speKw.KW_VNName}
						<i class="fa fa-close" style="margin-left:5px;"></i>
						<input class="keyword-code" type="hidden" value="${speKw.KW_Code}">
					</div>
				</c:forEach>
								
				<form:errors path="staffKeywords" class="alert-danger"></form:errors>					
			</div>
			</div>
				
		</div>		
	
		
		<div class="panel panel-default">
				<div class="panel-heading"><h4>Thêm từ khóa chuyên ngành</h4></div>
					
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="" id="message">
							
						</div>
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th title="Name">Từ khóa</th>
									<th title="Department">Chuyên ngành</th>
									<th>Thêm</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${specializationKeywordList}" var="speKw">
									<tr class="gradeX">
										 <td><c:out value="${speKw.KW_VNName}"/></td>
										 <td><c:out value="${speKw.scientificField.SCIF_VNName}"/></td>
										 <td class="center">
											<button type="button" onclick="add('${speKw.KW_Code}','${speKw.KW_VNName}')" class="btn btn-info btn-xs" title="Edit">Thêm</button>
											
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->

				</div>
				</div>
				
		
		<div class="row" style="padding-bottom:20px;">
			<div class="col-lg-4">
				<button type="button" class='form btn btn-primary' id="save">Lưu</button>
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
		$('#dataTables-example').DataTable({
	            responsive: true,
	            "aoColumnDefs": [
	                             { 'bSortable': false, 'aTargets': [2] }
	                          ]
	    });
		
		$('#staffUniversity').change(function(){
			
			if($('#staffUniversity').val() == 'HUST'){
				$('#DepartmentGroup').attr("style","");
			}else{
				$('#DepartmentGroup').attr("style","display:none;");
			}
			
		});
	    
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
			window.location = baseUrl + "/mm/professors.html";
		});	
		
	});
	function add(kwCode, kwName){		
		row = $('<div class="btn btn-default keyword" onclick="rem(\''+kwName+'\',\''+kwCode+'\')">'+kwName+'<i class="fa fa-close" style="margin-left:5px;"></i><input class="keyword-code" type="hidden" value="'+kwCode+'"></div>');
		keyword = $('#data-keyword').children();
		
		var isDuplicate = 0;
		keyword.each(function(){
			code = $(this).children(".keyword-code").val();
			if($.trim(kwCode) == $.trim(code))
			{
				isDuplicate = 1;
			}	
		});
		
		if(isDuplicate == 0){
			$('#data-keyword').append(row);			
			$("#message").attr("class", "alert alert-success");
			$("#message").html('Đã thêm từ khóa "'+kwName+'" thành công');
			
		}else{
			$("#message").attr("class", "alert alert-danger");
			$("#message").html('Từ khóa "'+kwName+'" đã có');
		}
	};
	function rem(kwName, kwCode){
		keyword = $('#data-keyword').children();
		keyword.each(function(){
			code = $(this).children(".keyword-code").val();
			if($.trim(kwCode) == $.trim(code))
			{
				$(this).remove();
			}	
		});
		
		$("#message").attr("class", "alert alert-warning");
		$("#message").html('Đã loại bỏ từ khóa "'+kwName+'" thành công');		
	};	
	


</script>