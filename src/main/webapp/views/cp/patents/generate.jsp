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
            <h1 class="page-header">Generate to excell</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Generating a paper
                </div>
                <div class="panel-body">
                    <form:form action="${baseUrl}/cp/downloadExcel" method="POST" commandName="paperExcellForm" role="form">
	                    <div class="row">
	                        <div class="col-lg-6">
                                <div class="form-group">
                                    <label>Select a year</label>
                                    <form:select path="paperYear" class="form-control" name="paperYear">
	                                	<option value="2000">2000</option>
	                                	<option value="2001">2001</option>
	                                	<option value="2002">2002</option>
	                                	<option value="2003">2003</option>
	                                	<option value="2004">2004</option>
	                                	<option value="2005">2005</option>
	                                	<option value="2006">2006</option>
	                                	<option value="2007">2007</option>
	                                	<option value="2008">2008</option>
	                                	<option value="2009">2009</option>
	                                	<option value="2010">2010</option>
	                                	<option value="2011">2011</option>
	                                	<option value="2012">2012</option>
	                                	<option value="2013">2013</option>
	                                	<option value="2014">2014</option>
	                                	<option value="2015">2015</option>
                                    </form:select>
                                </div>
                                <button type="submit" class="btn btn-primary">Generate</button>
                                <button type="reset" class="btn btn-primary cancel">Cancel</button>
                                <!-- <button type="submit" class="btn btn-default">Submit</button> -->
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

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
$(document).ready(function() {
    $('.generate').click(function(){
    	window.location = baseUrl+"/cp/downloadExcel";
    });
    
    $('button.cancel').click(function(){
		window.location = baseUrl+"/cp/papers.html";
	});
});
</script>
