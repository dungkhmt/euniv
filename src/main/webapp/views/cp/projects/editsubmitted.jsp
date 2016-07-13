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
            <h1 class="page-header">Chỉnh sửa thuyết minh đề tài sau phản biện</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
        	<c:if test="${projectEdit.PROJ_Locked2 != 1}">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    Thông tin đề tài trước khi phản biện
	                </div>
	                <div class="panel-body">
	                    <div class="row">
	                        <div class="col-lg-6">
	                           <div class="panel panel-default">
			                        <div class="panel-heading">
			                            <label for="projectResult">Tên đề tài</label>
			                        </div>
			                        <div class="panel-body">
			                            <div class="tab-content">
			                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_Name}</div>
			                            </div>
			                        </div>
			                    </div>
						        <div class="panel panel-default">
			                        <div class="panel-heading">
			                            <label for="projectResult">Nội dung</label>
			                        </div>
			                        <div class="panel-body">
			                            <div class="tab-content">
			                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_Content}</div>
			                            </div>
			                        </div>
			                    </div>
			                    <div class="panel panel-default">
			                        <div class="panel-heading">
			                            <label for="projectResult">Kết quả đánh giá</label>
			                        </div>
			                        <div class="panel-body">
			                            <div class="tab-content">
			                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_Result}</div>
			                            </div>
			                        </div>
			                    </div>
	                        </div>
	                        <div class="col-lg-6">
	                               <div class="panel panel-default">
			                        <div class="panel-heading">
			                            <label for="projectResult">Kinh phí (triệu VNĐ)</label>
			                        </div>
			                        <div class="panel-body">
			                            <div class="tab-content">
			                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_TotalBudget}</div>
			                            </div>
			                        </div>
			                    </div>
						        <div class="panel panel-default">
			                        <div class="panel-heading">
			                            <label for="projectResult">Lý do thực hiện đề tài</label>
			                        </div>
			                        <div class="panel-body">
			                            <div class="tab-content">
			                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_Motivation}</div>
			                            </div>
			                        </div>
			                    </div>
	                        </div>
	                        <!-- /.col-lg-6 (nested) -->
	                    </div>
	                    <!-- /.row (nested) -->
	                </div>
	                <!-- /.panel-body -->
	            </div>
	            <!-- /.panel -->
            </c:if>

			<div class="panel panel-default">
				<div class="panel-body">
				${comments}
				</div>
			</div>
            
            <div class="panel panel-default">
                <div class="panel-heading">
                	<c:choose>
						<c:when test="${projectEdit.PROJ_Locked2 != 1}">
	                    	Chỉnh sửa đề tài trước khi nộp
	                    </c:when>    
					    <c:otherwise>
					    	Nội dung thuyết minh đã nộp
					    </c:otherwise>
					</c:choose>
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
                    <form:form action="${baseUrl}/cp/edit-a-submittedproject.html" method="POST" commandName="projectFormEdit" role="form" enctype="multipart/form-data">
	                    <div class="row">
	                        <div class="col-lg-6">
	                        	<c:choose>
									<c:when test="${projectEdit.PROJ_Locked2 != 1}">
		                               	<div class="form-group">
		                                	<label for="projectResult">Nội dung</label>
			                                <textarea path="projectContent"  name="projectContent" id="projectContent" class="form-control">${projectEdit.PROJ_ContentChanged}</textarea>
			                                <form:errors path="projectContent" class="alert-danger"></form:errors>
		                            	</div>
		                                <div class="form-group">
		                                    <label for="projectResult">Kết quả đánh giá</label>
		                                    <textarea path="projectResult"  name="projectResult" id="projectResult" class="form-control">${projectEdit.PROJ_ResultChanged}</textarea>
		   									<form:errors path="projectResult" class="alert-danger"></form:errors>
		                               	</div>
								    </c:when>    
								    <c:otherwise>
								    	<div class="panel panel-default">
					                        <div class="panel-heading">
					                            <label for="projectResult">Tên đề tài</label>
					                        </div>
					                        <div class="panel-body">
					                            <div class="tab-content">
					                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_Name}</div>
					                            </div>
					                        </div>
					                    </div>
					                    <div class="panel panel-default">
					                        <div class="panel-heading">
					                            <label for="projectResult">Nội dung</label>
					                        </div>
					                        <div class="panel-body">
					                            <div class="tab-content">
					                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_ContentChanged}</div>
					                            </div>
					                        </div>
					                    </div>
					                    <div class="panel panel-default">
					                        <div class="panel-heading">
					                            <label for="projectResult">Kết quả đánh giá</label>
					                        </div>
					                        <div class="panel-body">
					                            <div class="tab-content">
					                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_ResultChanged}</div>
					                            </div>
					                        </div>
					                    </div>
								    </c:otherwise>
								</c:choose>
	                        	
                               	<!-- Buttons -->
                               	 <c:if test="${projectEdit.PROJ_Locked2 != 1}">
	                                 <button type="submit" class="btn btn-primary">Lưu</button>
	                                 <input type="hidden" value="${projectEdit.PROJ_ID}" name="projectId" id="projectId" />
	                                 <input type="hidden" value="${projectEdit.PROJ_PRJCall_Code}" name="projectCallCode" id="projectCallCode" />
	                                 <input type="hidden" value="${projectEdit.PROJ_Name}" name="projectName" id="projectName" />
                                 </c:if>
                                 <button type="reset" class="btn btn-info cancel">Hủy</button>
                                 <c:if test="${projectEdit.PROJ_Locked2 == 1}">
                                 	<%-- <button type="reset" class="btn btn-success" onclick="v_fGeneratePDF(${projectEdit.PROJ_ID})">Xuất PDF</button> --%>
                                 </c:if>
                                 <c:if test="${projectEdit.PROJ_Locked2 != 1}">
                                 	<button type="reset" class="btn btn-danger" onclick="v_fSubmitProject(${projectEdit.PROJ_ID})">Nộp đề tài</button>
                                 </c:if>
	                        </div>
	                        <div class="col-lg-6">
                                <c:choose>
									<c:when test="${projectEdit.PROJ_Locked2 != 1}">
										<div class="form-group">
		                                    <label for="projectBudget">Kinh phí (triệu VNĐ)</label>
		                                    <form:input path="projectBudget" class="form-control" name="projectBudget" value="${projectEdit.PROJ_BudgetChanged}" placeholder="Budget" ></form:input>
		    								<form:errors path="projectBudget" class="alert-danger"></form:errors>
		                                </div>
								        <div class="form-group">
		                                    <label for="projectMotivation">Lý do thực hiện đề tài</label>
		                                    <textarea path="projectMotivation" name="projectMotivation" id="projectMotivation" class="form-control">${projectEdit.PROJ_MotivationChanged}</textarea>
		    								<form:errors path="projectMotivation" class="alert-danger"></form:errors>
		                                </div>
								    </c:when>    
								    <c:otherwise>
								    	<div class="panel panel-default">
					                        <div class="panel-heading">
					                            <label for="projectResult">Kinh phí (triệu VNĐ)</label>
					                        </div>
					                        <div class="panel-body">
					                            <div class="tab-content">
					                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_BudgetChanged}</div>
					                            </div>
					                        </div>
					                    </div>
								        <div class="panel panel-default">
					                        <div class="panel-heading">
					                            <label for="projectResult">Lý do thực hiện đề tài</label>
					                        </div>
					                        <div class="panel-body">
					                            <div class="tab-content">
					                                <div class="tab-pane fade in active" id="home">${projectEdit.PROJ_MotivationChanged}</div>
					                            </div>
					                        </div>
					                    </div>
								    </c:otherwise>
								</c:choose>
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
		window.location = baseUrl+"/cp/modify-submitted-projects.html";
	});
});

function v_fGeneratePDF(iProjectId){
	var sGeneratePdfUrl = baseUrl + "/cp/generatepdf/"+iProjectId+".html";
	window.location = sGeneratePdfUrl;
}

function v_fSubmitProject(iProjectId){
	if(confirm("Chú ý ! Đề tài sau khi nộp sẽ không thể chỉnh sửa được.")){
		var sSubmitProjectUrl = baseUrl + "/cp/submitproject/"+iProjectId+".html";
		window.location = sSubmitProjectUrl;
    }
    else{
        return false;
    }
}
</script>

