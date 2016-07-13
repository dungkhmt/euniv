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
			<h1 class="page-header">Phân đề tài cho học viên</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<form:form id="form-add" action="${baseUrl}/mm/save-a-thesis.html" method="POST" commandName="thesisFormAssign" role="form" accept-charset="UTF-8">
		<div class="row">
		<c:if test="${status != null}">	              
		<div class="alert alert-success">
			${status}
		</div>
		</c:if>
			<div class="col-lg-6">
				<div class="well">
					<h4>Họ tên học viên</h4>
					<p class="value">${student.student_Name}</p>  
					<form:input path="studentCode" class="form-control" name="studentCode" type="hidden" value="${student.student_Code}"></form:input>
	    			<form:errors path="studentCode" class="alert-danger"></form:errors> 
					 				
				</div>
				<div class="well">
					<h4>Lớp</h4>
					<p class="value">${student.masterClass.classes_Name}</p>    				
				</div>
				
				<!-- /.col-lg-4 -->
			</div>
	
			<div class="col-lg-6">
				<div class="well">
					<h4>Khoa viện</h4>
					<p class="value">${student.masterClass.faculty.faculty_Name}</p>                    
				</div>
				<div class="well">
					<h4>Tên đề tài</h4>
					<div class='form'>
						<form:input path="thesisName" class="form-control" name="thesisName" type="text" placeholder="Tên đề tài" value="${thesisName}"></form:input>
	    				<form:errors path="thesisName" class="alert-danger"></form:errors>
    				</div>
				</div>
				
				<!-- /.col-lg-4 -->
			</div>
		</div>
		<!-- /.row -->
		
		<div class="panel panel-default">
				<div class="panel-heading"><h4>Chọn giảng viên</h4> 
				    <div class='form'>
				        <input id="supervisorName" class="form-control" type="text" readonly="true" placeholder="Chọn giảng viên" value="">
						<form:input path="supervisor"  id="supervisor" class="form-control" name="supervisor" type="hidden" value=""></form:input>
	    				<form:errors path="supervisor" class="alert-danger"></form:errors>
    				</div>
				
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
									<th title="Name">Tên giảng viên</th>
									<th title="Department">Bộ môn</th>
									<th title="Faculty">Khoa viện</th>
									<th title="University">Trường</th>									
									<th>Thêm</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${professorList}" var="professor">
									<tr class="gradeX">
										 <td><c:out value="${professor.staff_Name}"/></td>
										 <td><c:out value="${professor.department.department_Name}"/></td>
										 <td><c:out value="${professor.department.faculty.faculty_Name}"/></td>
										 <td><c:out value="${professor.department.faculty.university.university_Name}"/></td>
										 <td class="center">
											<button type="button" onclick="chooseProfessor('${professor.staff_Code}', '${professor.staff_Name}')" class="btn btn-info btn-xs" title="Edit">Chọn</button>
											
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->

				</div>
			</div>
				
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
		
		$('#dataTables-professor').DataTable({
            responsive: true,
            "aoColumnDefs": [
                             { 'bSortable': false, 'aTargets': [4] }
                          ]
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
			window.location = baseUrl + "/mm/listStudentToAssignThesis.html";
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
	
	function chooseProfessor(proCode, proName){
		$("#supervisorName").val(proName);
		$("#supervisor").val(proCode);
		
	}


</script>