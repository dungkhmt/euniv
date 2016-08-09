<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/assets/css/sb-admin-2.css" />" rel="stylesheet" type="text/css" media="all" />

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Chọn tiêu chí thống kê</h1>
			<p>
				<!-- <button type="button" class="btn btn-primary btn-xs gen">Kết xuất danh sách</button> -->
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Chọn tiêu chí thống kê</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<form:form action="${baseUrl}/cp/papersSummary" method="POST" id="formSubmit" commandName="paperSummaryForm" role="form">
					<div class="row">
                       <div class="col-lg-6">
                       	<div class="panel panel-default">
	                        <div class="panel-heading">
	                            <b>Chọn thông tin bài báo</b>
	                        </div>
		                        <!-- /.panel-heading -->
		                        <div class="panel-body">
		                        	<div>
			                        	<label for="topicCategory">Nhóm bài báo</label>
		                        		<div class="form-group">
		                                   	<form:select class="form-control"  path="paperCategory">
		                                   		<option value="">Tất cả</option>
					                        	<c:forEach items="${paperCategory}" var="paperCate">
				                                 	<option value="${paperCate.PCAT_Code}">${paperCate.PCAT_Name}</option>
				                               	</c:forEach>
					                        </form:select>
		                                </div>
	                                </div>
	                                
	                                <div>
		                                <label for="paperReportingAcademicYear">Năm kê khai</label>
		                                <div class="form-group">
		                                   	 <form:select class="form-control" path="paperReportingAcademicYear">
					                        	<c:forEach items="${paperReportingAcademicYearList}" var="paperReportingAcademicYear">
				                                 	<option value="${paperReportingAcademicYear.ACAYEAR_Code}">${paperReportingAcademicYear.ACAYEAR_Code}</option>
				                               	</c:forEach>
					                        </form:select>
		                                </div>
	                                </div>
	                           </div>
	                      </div>
                       </div>
                       <div class="col-lg-6">
                       	<div class="panel panel-default">
	                        <div class="panel-heading">
	                            <b>Chọn thông tin người kê khai</b>
	                        </div>
		                        <!-- /.panel-heading -->
		                        <div class="panel-body">
		                        	<div>
			                        	<label for="CMTSUBPRJ_Eval_RearchMethodology">Khoa/Viện</label>
		                        		<div class="form-group">
		                                   	<form:select class="form-control" path="paperFaculty" onchange="showDepartment(this);">
					                        	<option value="" >Khoa/Viện</option>
					                        	<c:forEach items="${threadFaculties}" var="threadFaculty">
				                                 	<option value="${threadFaculty.faculty_Code}">${threadFaculty.faculty_Name}</option>
				                               	</c:forEach>
					                        </form:select>
		                                </div>
	                                </div>

									<div>
										<label for="CMTSUBPRJ_Eval_ResearchContent">Bộ môn</label>			                                
		                                <div class="form-group ">
		                                   	<div id="list-department">
		                                   		<span class="">N/A</span>
		                                   	</div>
		                                </div>
	                                </div>
	                                <div>
										<label for="CMTSUBPRJ_Eval_ResearchContent">Người kê khai</label>			                                
		                                <div class="form-group ">
		                                   	<div id="list-staff">
		                                   		<span class="">N/A</span>
		                                   	</div>
		                                </div>
	                                </div>
	                            </div>
	                    </div>
                       </div>
                   </div>
                   <!-- /.row (nested) -->
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<button type="submit" id="filter" class="btn btn-primary filter">View</button>
								<!-- <button type="submit" class="btn btn-primary">Kết xuất Excel</button> -->
							</div>
						</div>
					</div>
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

<!-- DataTables JavaScript -->
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>

var baseUrl = "${baseUrl}";
//Plug-in to fetch page data 

// Datatable serverside handling
$(document).ready(function () {
    
    $("#generateCode").click(function(){
    	var data = document.getElementById("project-call").value;
    	//var json = "{\"projectCallCode\":\"" + data + "\"}";
    	var json = "projectCallCode=" + data + "";
    	//alert(json);
    	
    	$.ajax({
			type : "POST",
			//contentType : "application/json",
			url : "${baseUrl}/cpservice/generate-project-codes.html",
			data : json,
			//dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				alert("Sinh mã thành công");
				//windows.location ${baseUrl}
			},
			error : function(e) {
				console.log("ERROR: ", e);
				
			},
			done : function(e) {
				console.log("DONE");
			}
		});
    });
});

function v_fSubmitFilter(){
	var sAction = "${baseUrl}/cp/list-projects-statisitcs";
	//alert(sAction);
	// Reset action url
	$("form#formSubmit").attr("action",sAction);
	$("form#formSubmit").submit();
}


function v_fViewDetailAThread(iThreadId){
	var sViewDetailUrl = baseUrl + "/cp/threadshow/"+iThreadId+".html";
	window.location = sViewDetailUrl;
}

function v_fGenerate(){
	var sGeneratingUrl = "${baseUrl}/cpservice/generate.json";
    $.ajax({
        type: "POST",
        url: sGeneratingUrl,
        //data: "id=" + the_i_StaffId,
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
    });
}

function v_fRemoveAThread(iThreadId){
	var r = confirm("Do you really want to remove this ?");
	if (r == true) {
		var sRemoveAThreadUrl = baseUrl + "/cp/remove-a-thread/"+iThreadId+".html";
		window.location = sRemoveAThreadUrl;
	} else {
	    return false;
	}
}

function showDepartment(sFaculty)
{
	var sGeneratingUrl = "${baseUrl}/cpservice/loadthreaddepartments.html";
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
					$("#list-department").html( html );
				}
			});
	}else{
		var sDepartment = '<select class="form-control" style="width:200px;" name="threadDepartment" id="threadDepartment">';
			sDepartment += '<option value="">Chọn Bộ môn</option>';
			sDepartment +=  '</select>';
		$("#list-department").html( sDepartment );
		
		var sStaffs = '<select class="form-control" style="width:200px;" name="threadStaff" id="threadStaff">';
			sStaffs += '<option value="">Chọn cán bộ</option>';
			sStaffs +=  '</select>';
		$("#list-staff").html( sStaffs );
	}
}

function v_fGeneratePDF(iProjectId){
	var sGeneratePdfUrl = baseUrl + "/cp/generatepdf/"+iProjectId+".html";
	window.location = sGeneratePdfUrl;
}

function showStaff(sDepartment) {
	var sGeneratingUrl = "${baseUrl}/cpservice/getthreadstaffs.html";
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
					$("#list-staff").html( html );
				}
			});
	}
}
</script>
