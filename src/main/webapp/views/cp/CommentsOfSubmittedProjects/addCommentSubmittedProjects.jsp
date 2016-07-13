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
            <h1 class="page-header">Đánh giá thuyết minh đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Nhận xét đề tài  
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}<a href="<c:url value="${baseUrl}/cp/details-comment-submitted-projects.html"/>" class="alert-link">Trở lại</a>
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>     
                    <form:form action="${baseUrl}/cp/save-detail-comments-submitted-projects.html" method="POST" commandName="detailCommentsSubmittedProjectsFormAdd" role="form" >
	                    <div class="row">
	                        <div class="col-lg-6">
	                        	<div class="panel panel-default">
			                        <div class="panel-heading">
			                            <b>Mục tiêu</b>
			                        </div>
				                        <!-- /.panel-heading -->
				                        <div class="panel-body">
				                        	<div>
					                        	<label for="CMTSUBPRJ_Eval_Motivation">Tính cấp thiết</label>
				                        		<div class="form-group input-group">
				                                   	<form:input path="CMTSUBPRJ_Eval_Motivation" class="form-control" name="CMTSUBPRJ_Eval_Motivation" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Motivation}" placeholder="Tính cấp thiết"></form:input>
				                                   	<span class="input-group-addon">điểm</span>
				                                </div>
				                                <form:errors path="CMTSUBPRJ_Eval_Motivation" class="alert-danger"></form:errors>
			                                </div>
			                                
			                                <div>
				                                <label for="CMTSUBPRJ_Eval_Innovation">Tính mới</label>
				                                <div class="form-group input-group">
				                                   	<form:input path="CMTSUBPRJ_Eval_Innovation" class="form-control" name="CMTSUBPRJ_Eval_Innovation" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Innovation}" placeholder="Tính mới"></form:input>
				                                   	<span class="input-group-addon">điểm</span>
				                                </div>
				                                <form:errors path="CMTSUBPRJ_Eval_Innovation" class="alert-danger"></form:errors>
			                                </div>
			                                
			                                <div>
				                                <label for="CMTSUBPRJ_Eval_Applicability">Khả năng áp dụng/phát triển</label>
				                                <div class="form-group input-group">
				                                   	<form:input path="CMTSUBPRJ_Eval_Applicability" class="form-control" name="CMTSUBPRJ_Eval_Applicability" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Applicability}" placeholder="Khả năng áp dụng/phát triển"></form:input>
				                                   	<span class="input-group-addon">điểm</span>
				                                </div>
				                                <form:errors path="CMTSUBPRJ_Eval_Applicability" class="alert-danger"></form:errors>
			                                </div>
			                           </div>
			                      </div>
	                        </div>
	                        <div class="col-lg-6">
	                        	<div class="panel panel-default">
			                        <div class="panel-heading">
			                            <b>Nội dung</b>
			                        </div>
				                        <!-- /.panel-heading -->
				                        <div class="panel-body">
				                        	<div>
					                        	<label for="CMTSUBPRJ_Eval_RearchMethodology">Phương pháp nghiên cứu</label>
				                        		<div class="form-group input-group">
				                                   	<form:input path="CMTSUBPRJ_Eval_RearchMethodology" class="form-control" name="CMTSUBPRJ_Eval_RearchMethodology" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_RearchMethodology}" placeholder="Phương pháp nghiên cứu"></form:input>
				                                   	<span class="input-group-addon">điểm</span>
				                                </div>
				                                <form:errors path="CMTSUBPRJ_Eval_RearchMethodology" class="alert-danger"></form:errors>
			                                </div>

											<div>
												<label for="CMTSUBPRJ_Eval_ResearchContent">Các nội dung nghiên cứu chính</label>			                                
				                                <div class="form-group  input-group">
				                                   	<form:input path="CMTSUBPRJ_Eval_ResearchContent" class="form-control" name="CMTSUBPRJ_Eval_ResearchContent" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_ResearchContent}" placeholder="Các nội dung nghiên cứu chính"></form:input>
				                                   	<span class="input-group-addon">điểm</span>
				                                </div>
				                                <form:errors path="CMTSUBPRJ_Eval_ResearchContent" class="alert-danger"></form:errors>
			                                </div>
			                            </div>
			                    </div>
	                        </div>
	                        
	                    </div>
	                    <!-- /.row (nested) -->
	                    
	                    <div class="row">
	                        <div class="col-lg-6">
	                        	<div class="panel panel-default">
			                        <div class="panel-heading">
			                            <b>Sản phẩm cụ thể</b>
			                        </div>
				                        <!-- /.panel-heading -->
				                        <div class="panel-body">
				                        	<div>
					                        	<label for="CMTSUBPRJ_Eval_Paper">Bài báo: Trong nước; Scopus, ISI (khuyến khích)</label>
				                        		<div class="form-group input-group">
				                                   	<form:input path="CMTSUBPRJ_Eval_Paper" class="form-control" name="CMTSUBPRJ_Eval_Paper" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Paper}" placeholder="Bài báo: Trong nước; Scopus, ISI (khuyến khích)"></form:input>
				                                   	<span class="input-group-addon">điểm</span>
				                                </div>
				                                <form:errors path="CMTSUBPRJ_Eval_Paper" class="alert-danger"></form:errors>
			                                </div>
			                                
			                                <div>
				                                <label for="CMTSUBPRJ_Eval_Product">Sản phẩm/thiết bị có địa chỉ ứng dụng cụ thể</label>
				                                <div class="form-group input-group">
				                                   	<form:input path="CMTSUBPRJ_Eval_Product" class="form-control" name="CMTSUBPRJ_Eval_Product" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Product}" placeholder="Sản phẩm/thiết bị có địa chỉ ứng dụng cụ thể"></form:input>
				                                   	<span class="input-group-addon">điểm</span>
				                                </div>
				                                <form:errors path="CMTSUBPRJ_Eval_Product" class="alert-danger"></form:errors>
			                                </div>
			                                
			                                <div>
				                                <label for="CMTSUBPRJ_Eval_Patent">SHTT</label>
				                                <div class="form-group input-group">
				                                   	<form:input path="CMTSUBPRJ_Eval_Patent" class="form-control" name="CMTSUBPRJ_Eval_Patent" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Patent}" placeholder="SHTT"></form:input>
				                                   	<span class="input-group-addon">điểm</span>
				                                </div>
				                                <form:errors path="CMTSUBPRJ_Eval_Patent" class="alert-danger"></form:errors>
			                                </div>
		                                </div>
		                        </div>
	                       	</div>
	                        <div class="col-lg-6">
	                        	<div class="panel panel-default">
			                        <div class="panel-heading">
			                            <b>Các ưu tiên khác</b>
			                        </div>
			                        <!-- /.panel-heading -->
			                        <div class="panel-body">
			                        	<div>
				                        	<label for="CMTSUBPRJ_Eval_Graduate_Student">Học viên sau đại học, NCS  (cùng hướng NC luận văn)</label>
			                        		<div class="form-group input-group">
			                                   	<form:input path="CMTSUBPRJ_Eval_Graduate_Student" class="form-control" name="CMTSUBPRJ_Eval_Graduate_Student" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Graduate_Student}" placeholder="Học viên sau đại học, NCS  (cùng hướng NC luận văn)"></form:input>
			                                   	<span class="input-group-addon">điểm</span>
			                                </div>
			                                <form:errors path="CMTSUBPRJ_Eval_Graduate_Student" class="alert-danger"></form:errors>
		                                </div>
		                                
		                                <div>
			                                <label for="CMTSUBPRJ_Eval_Young_Rearcher">Cán bộ trẻ <= 35</label>
			                                <div class="form-group input-group">
			                                   	<form:input path="CMTSUBPRJ_Eval_Young_Rearcher" class="form-control" name="CMTSUBPRJ_Eval_Young_Rearcher" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Young_Rearcher}" placeholder="Cán bộ trẻ <= 35"></form:input>
			                                   	<span class="input-group-addon">điểm</span>
			                                </div>
			                                <form:errors path="CMTSUBPRJ_Eval_Young_Rearcher" class="alert-danger"></form:errors>
		                                </div>

										<div>
											<label for="CMTSUBPRJ_Eval_Education_Graduate">Tham gia đào tạo NCS, ThS</label>		                                
			                                <div class="form-group input-group">
			                                   	<form:input path="CMTSUBPRJ_Eval_Education_Graduate" class="form-control" name="CMTSUBPRJ_Eval_Education_Graduate" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Education_Graduate}" placeholder="Tham gia đào tạo NCS, ThS"></form:input>
			                                   	<span class="input-group-addon">điểm</span>
			                                </div>
			                                <form:errors path="CMTSUBPRJ_Eval_Education_Graduate" class="alert-danger"></form:errors>
		                                </div>

										<div>
											<label for="CMTSUBPRJ_Eval_Reasonable_Budget">Kinh phí phù hợp</label>		                                
			                                <div class="form-group input-group">
			                                   	<form:input path="CMTSUBPRJ_Eval_Reasonable_Budget" class="form-control" name="CMTSUBPRJ_Eval_Reasonable_Budget" value="${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Reasonable_Budget}" placeholder="Kinh phí phù hợp"></form:input>
			                                   	<span class="input-group-addon">điểm</span>
			                                </div>
			                                <form:errors path="CMTSUBPRJ_Eval_Reasonable_Budget" class="alert-danger"></form:errors>
		                                </div>
			                        </div>
			                        <!-- /.panel-body -->
		                    	</div>
	                        </div>
	                    </div>
	                    
	                    <div class="row">
	                    	<div class="col-lg-6">
                               	<div class="form-group">
                               		<label for="CMTSUBPRJ_Eval_Conclusion">Kết luận</label>
                                	<textarea path="CMTSUBPRJ_Eval_Conclusion"  name="CMTSUBPRJ_Eval_Conclusion" id="CMTSUBPRJ_Eval_Conclusion" class="form-control">
                                		 
                                		<c:choose>
				                            <c:when test="${conclusion != NULL}">
				                                ${conclusion}
				                            </c:when>
				                            <c:otherwise>
				                                ${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Conclusion}
				                            </c:otherwise>
				                        </c:choose>
                                	</textarea>
                                	<form:errors path="CMTSUBPRJ_Eval_Conclusion" class="alert-danger"></form:errors>
                           		</div>
                           		<input type="hidden" value="${projectId}" name="projectId" />
                                <button type="submit" class="btn btn-primary" name="action" value = "save"> Lưu </button>
                                <button type="reset" class="btn btn-primary cancel"> Hủy </button>
	                        </div>
	                        <div class="col-lg-6">
	                        	<div class="form-group">
                                   <label for="CMTSUBPRJ_Eval_Classification">Phân loại</label>
                                   <form:select path="CMTSUBPRJ_Eval_Classification" class="form-control" name="CMTSUBPRJ_Eval_Classification">
                                        <option value="A" <c:if test='${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Classification == "A"}'> selected </c:if> >A</option>
                                        <option value="B" <c:if test='${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Classification == "B"}'> selected </c:if> >B</option>
                                        <option value="C" <c:if test='${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Classification == "C"}'> selected </c:if> >C</option>
                                        <option value="F" <c:if test='${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Classification == "F"}'> selected </c:if> >Loại</option>
                                   </form:select>
                                   <form:errors path="CMTSUBPRJ_Eval_Classification" class="alert-danger"></form:errors>
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
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->


<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
            responsive: false,
            "aoColumnDefs": [
                             { 'bSortable': false, 'aTargets': [3] }
                          ]
    });
    
    
    
    
});
</script>



<script type="text/javascript">

$('textarea').each( function() {
    CKEDITOR.replace( $(this).attr('id') );
});
 
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/details-comment-submitted-projects.html";
	});
});

</script>

