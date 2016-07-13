<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>F-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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
            <h1 class="page-header">Danh sách Users</h1>
            <p><button type="button" class="btn btn-primary btn-xs add">Thêm mới</button></p>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Show all users ${navigation}
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                                <tr>
                                    <th>Họ Tên</th>
                                    <th>Email</th>
                                    <th>Tên tài khoản (UserName)</th>
                                    <th>Trạng thái</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            	<c:forEach items="${staffsList}" var="staff">
                            		<tr class="gradeX">
	                                    <td><c:out value="${staff.staff_Name}"/></td>
	                                    <td><c:out value="${staff.staff_Email}"/></td>
	                                    <td><c:out value="${staff.staff_User_Code}"/></td>
	                                    <td class="center">
		                                    <c:choose>
					                            <c:when test="${staff.user.enabled == 1}">
					                                <button type="button" class="btn btn-success btn-circle "><i class="fa fa-check"></i></button>
					                            </c:when>
					                            <c:otherwise>
					                                    <button type="button" class="btn btn-danger btn-circle"><i class="fa fa-times"></i></button>
					                            </c:otherwise>
					                        </c:choose>
	                                    </td>
	                                    <td class="center">
	                                    	<button type="button" onclick="v_fViewDetail(${staff.user.user_ID});" class="btn btn-info btn-xs" title="Edit">Chi tiết</button>
	                                    	<br/>
											<button type="button" onclick="v_fRemoveAnUser(${staff.user.user_ID});" class="btn btn-danger btn-xs" title="Remove">Xoá</button>
	                                    </td>
                                	</tr>
								</c:forEach>
                            </tbody>
                        </table>
                    </div>
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

<!-- DataTables JavaScript -->
<script src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
            responsive: false,
            "aoColumnDefs": [
                             { 'bSortable': false, 'aTargets': [3,4] }
                          ]
    });
    $('.add').click(function(){
    	window.location = baseUrl+"/cp/add-an-user.html";
    })
});

function v_fViewDetail(iUserId){
	var sViewDetailUrl = baseUrl + "/cp/userdetail/"+iUserId+".html";
	window.location = sViewDetailUrl;
}

function v_fRemoveAnUser(iUserId){
	var r = confirm("Do you really want to remove this user ?");
	if (r == true) {
		//TODO
		var sRemoveAnUserUrl = baseUrl + "/cp/remove-an-user/"+iUserId+".html";
		window.location = sRemoveAnUserUrl;
	} else {
	    return false;
	}
}
</script>   
    