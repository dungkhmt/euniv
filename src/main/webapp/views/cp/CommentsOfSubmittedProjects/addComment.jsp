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
            <h1 class="page-header">Nhận xét đề tài</h1>
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
	                	
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/add-comments-of-submitted-projects.html"/>" class="alert-link">Trở lại</a>.
	                    </div>
                    </c:if>
                    <c:if test="${err != null}">
	                	<div class="alert alert-warning">${err}</div>
                    </c:if>     
                    <form:form action="${baseUrl}/cp/save-comments-of-submitted-projects.html" method="POST" commandName="commentsOfSubmittedProjectsFormAdd" role="form" >
	                    <div class="row">
	                        <div class="col-lg-12">
	                        
	                                <div class="form-group">
	                                    <label>Lý do đề tài </label>
										<br> 
										${project.PROJ_Motivation}
	                                </div>
	                        
	                                <div class="form-group">
	                                    <label>Nội dung </label>
	                                    <br>
	                                    ${project.PROJ_Content}
	                                </div>
	                        
	                                <div class="form-group">
	                                    <label>Kết quả</label>
	                                    <br>
	                                    ${project.PROJ_Result}
	                             
	                                </div>
	                                
	                                 <input type="hidden" value="${project.PROJ_Code}" name="COMPROJ_PRJCODE" id="COMPROJ_PRJCODE" />
	                                
	                                <div class="form-group">
                                	<label for="projectResult">Nhận xét phản biện </label>
                                
	                                	<textarea path="COMPROJ_COMMENT"  name="COMPROJ_COMMENT" id="COMPROJ_COMMENT" class="form-control">${sComment}</textarea>
	                                	<form:errors path="COMPROJ_COMMENT" class="alert-danger"></form:errors>
                            		</div>
                            		
	                                <button type="submit" class="btn btn-primary" name="action" value = "save"> Lưu </button>
	                                <button type="submit" class="btn btn-primary" name="action" value = "submit"> Gửi </button> 
	                                <button type="reset" class="btn btn-primary cancel"> Hủy </button>
	                        </div>
	                        
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
		window.location = baseUrl+"/cp/add-comments-of-submitted-projects.html";
	});
});

</script>

