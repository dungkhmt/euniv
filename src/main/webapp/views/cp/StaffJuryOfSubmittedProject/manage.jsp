<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Custom CSS -->
<link href="<c:url value="/assets/css/sb-admin-2.css" />" rel="stylesheet" type="text/css" media="all" />
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Chọn đợt gọi đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    
                </div>
                <div class="panel-body">
                    
	                                
                    <form:form action="${baseUrl}/cp/list-assign-jury-submitted-projects.html" method="POST" role="form" >
	                    <div class="row">
	                        <div class="col-lg-12">
		                        <div class="form-group">
			                       <%-- 
			                        <label>Đợt gọi Đề tài </label>
			                        <select class="form-control" name="PROJCALL_CODE">
			                        	<c:forEach items="${projectCallList}" var="iProjectCall">
			                            <option value="${iProjectCall.PROJCALL_CODE}">${iProjectCall.PROJCALL_NAME}</option>
			                           	</c:forEach>
			                        <select>
			                        --%> 
			                    </div>
			                    <div class = "form-group">
	                                	<label>Hội đồng xét duyệt đề tài</label>
	                                	<select path="JURPRJ_Code" class = "form-control" name="JURPRJ_Code">
	                                		<c:forEach items="${juries}" var="jury">
	                                			<option value="${jury.JURPRJ_Code}">${jury.JURPRJ_Name}</option>
	                                		</c:forEach>
	                                	</select>
	                                </div>
			                    
                                <div class="form-group">                                	
	                            <button type="submit" class="btn btn-primary">Tiếp</button>
                                </div>
	                        </div>
	                    </div>
	                    <!-- /.row (nested) -->
                    </form:form>
					<!-- /.table-responsive -->
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
<script>
$(document).ready(function() {
    $('button.cancel').click(function(){
		window.location = baseUrl+"/cp/assign-jury-submitted-projects.html";
	});
    
    $('.add').click(function(){
    	window.location = baseUrl+"/cp/add-a-projectcall.html";
    });
});
</script>
