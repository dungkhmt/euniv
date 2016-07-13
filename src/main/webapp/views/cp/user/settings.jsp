<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">
	   
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Thiết lập</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Thay đổi mật khẩu
                </div>
                <div class="panel-body">
                	<c:if test="${status != null}">
	                	<div class="alert alert-success">
	                        ${status}. <a href="<c:url value="${baseUrl}/cp/home.html"/>" class="alert-link">Back to list</a>.
	                    </div>
                    </c:if>
                    <c:if test="0">
	                	<div class="alert alert-warning">Error</div>
                    </c:if>
                    <div class="row">
                        <div class="col-lg-6">
                        	<form:form action="${baseUrl}/cp/save-settings.html" method="POST" commandName="settingForm" role="form">
                                <div class="form-group">
                                    <label for="password">Mật khẩu</label>
                                    <form:input path="password" class="form-control" name="password" type="password" placeholder="Password"></form:input>
                                    <form:errors path="password" class="alert-danger"></form:errors>
                                </div>
                                <div class="form-group">
                                    <label for="rpassword">Nhập lại mật khẩu</label>
                                    <form:input path="rpassword" class="form-control" name="rpassword" type="password" placeholder="Retype Password"></form:input>
                                    <form:errors path="rpassword" class="alert-danger"></form:errors>
                                </div>
                                <button type="submit" class="btn btn-default">Lưu</button>
                                <!-- <button type="reset" class="btn btn-default">Clear</button> -->
                            </form:form>
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
