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
			<h1 class="page-header">Danh sách chuyên đề</h1>
			<p>
				<button type="button" class="btn btn-primary btn-xs add">Thêm mới</button>
				<!-- <button type="button" class="btn btn-primary btn-xs gen">Kết xuất danh sách</button> -->
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh sách chuyên đề</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover"
							id="dataTables-example">
							<thead>
								<tr>
									<th title="Name of project">Tên</th>
									<th title="Category of project">Nhóm</th>
									<th title="Status">Trạng thái</th>
									<th title="Budget">Quỹ</th>
									<th title="Start year">Năm thực hiện</th>
									<th>Edit</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${productsList}" var="product">
									<tr class="gradeX">
										<td><c:out value="${product.PROD_Name}"/></td>
										<td><c:out value="${product.thread.PROJ_Name}"/></td>
										<td><c:out value="${product.PROD_Status_Code}"/></td>
										<td><c:out value="${product.PROD_Budget}"/></td>
										<td><c:out value="${product.PROD_StartDate} - ${product.PROD_EndDate}"/></td>
										<td class="center">
											<button type="button" onclick="v_fViewDetailAProduct(${product.PROD_ID});" class="btn btn-info btn-xs" title="Edit">Info</button>
											<br/>
											<button type="button" id="removeTopic" onclick="v_fRemoveAProduct(${product.PROD_ID});" class="btn btn-danger btn-xs" title="Remove">Remove</button>
											<br>
											<c:choose>
					                            <c:when test="${product.PROD_SourceFile != null && product.PROD_SourceFile != '' }">
					                               <a  href="<c:url value="${baseUrl}/cp/download-thread/${product.PROD_ID}.html"/>" title="Download file xác thực" class="btn btn-success btn-xs">Download</a>
					                            </c:when>
					                            <c:otherwise>
					                                <!-- <button title="File download không tồn tại" class="btn btn-success btn-xs disabled">Download</button> -->
					                            </c:otherwise>
					                        </c:choose>
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
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
$(document).ready(function() {
    $('#dataTables-example').DataTable({
            responsive: false,
            "aoColumnDefs": [
                             { 'bSortable': false, 'aTargets': [5] }
                          ]
    });
    
    $('.add').click(function(){
    	window.location = baseUrl+"/cp/add-a-product.html";
    });
    
});

function v_fViewDetailAProduct(iProductId){
	var sViewDetailUrl = baseUrl + "/cp/productdetail/"+iProductId+".html";
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

function v_fRemoveAProduct(iProductId){
	var r = confirm("Do you really want to remove this ?");
	if (r == true) {
		var sRemoveAProductUrl = baseUrl + "/cp/remove-a-product/"+iProductId+".html";
		window.location = sRemoveAProductUrl;
	} else {
	    return false;
	}
}
</script>
