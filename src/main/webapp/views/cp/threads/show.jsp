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
            <h1 class="page-header">Nội dung đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thông tin đề tài
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
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label>Nhóm</label>
                                <div>${threadCatName}</div>
                            </div>
                            <div class="form-group">
                                <label for="threadName">Tên đề tài</label>
                                <div>${threadName}</div>
                            </div>
                            <div class="form-group">
                                <label for="threadMotivation">Lý do thực hiện đề tài</label>
                                <div>${threadMotivation}</div>
                            </div>
                            <div class="form-group">
                            	<label for="threadReportingAcademicDate">Năm kê khai</label>
                                <div>${threadAcaYearCode}</div>
                        	</div>
                        	<div class="form-group">
                                <label for="threadStatus">Trạng thái</label>
								<div>${threadStatus}</div>
                        	</div>
                        	<div class="form-group">
                            	<label for="threadContent">Nội dung</label>
								<div>${threadContent}</div>
                        	</div>
                        	<form:hidden path="threadId" name="threadId" value="${threadId}"/>
                        	<form:hidden path="threadCode" name="threadCode" value="${threadCode}"/>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <label for="threadStartDate">Ngày bắt đầu<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
                                <div>${threadStartDate}</div>
                            </div>
                            <div class="form-group">
                                <label for="threadEndDate">Ngày kết thúc<i style="font-weight: normal; font-size: .9em; color: #bdbdbd;"></i></label>
                                <div>${threadEndDate}</div>
                            </div>
                            <div class="form-group">
                                <label for="threadBudget">Kinh phí</label>
                                <div>${threadBudget}</div>
                            </div>
                            <div class="form-group">
                            	<label for="threadResult">Thành viên tham gia</label>
                                <div id="staff">
								<c:forEach items="${listStaffs}" var="aStaff">
                                   	<div>${aStaff.staff_Name}</div>
								</c:forEach>
                                </div>
                            </div>
                            <!-- 
                            <button type="button" class="btn btn-primary detail">Sửa</button>
                            <button type="reset" class="btn btn-primary cancel">Hủy</button>
                             -->
                        </div>
                        <!-- /.col-lg-6 (nested) -->
                    </div>
	                    <!-- /.row (nested) -->
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
 
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/threads.html";
	});
	$('button.detail').click(function(){
		var sViewDetailUrl = baseUrl + "/cp/threaddetail/"+"${threadId}"+".html";
		window.location = sViewDetailUrl;
	});
});

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

</script>

