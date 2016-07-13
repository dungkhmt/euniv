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
					<div>
						<div class="form-group input-group">
							<select class="form-control" style="width:200px;" name="productCatCode" id="productCatCode">
								<option value="" >Chọn đề tài</option>
	                        	<c:forEach items="${threadsList}" var="aThread">
	                             	<option value="${aThread.PROJ_Code}" >${aThread.PROJ_Name}</option>
	                           	</c:forEach>
	                        </select>
	                        <select class="form-control" style="width:200px;" name="productStatus" id="productStatus">
	                        	<option value="" >Chọn trạng thái</option>
	                        	<c:forEach items="${projectStatuses}" var="productStat">
	                             	<option value="${productStat.PROJSTAT_Code}" >${productStat.PROJSTAT_Description}</option>
	                           	</c:forEach>
	                        </select>
							<button type="button" id="filter" class="btn btn-primary filter">Chọn</button>
						</div>
					</div>
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables-example">
							<thead>
								<tr>
									<th title="Name of project">Tên</th>
									<th title="Category of project">Đề tài</th>
									<th title="Status">Trạng thái</th>
									<th title="Budget">Bắt đầu</th>
									<th title="Budget">Kết thúc</th>
									<th title="Budget">Kinh phí (Triệu VNĐ)</th>
									<th>Edit</th>
								</tr>
							</thead>
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
<%-- <script src="<c:url value="/assets/js/_dataTable.js"/>"></script> --%>
<script src="<c:url value="/assets/js/dataTableFilter.js"/>"></script>
<script src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>
<%-- <script src="<c:url value="/assets/js/yadcf.js"/>"></script> --%>
<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
var baseUrl = "${baseUrl}";
//Plug-in to fetch page data 
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
{
	return {
		"iStart":         oSettings._iDisplayStart,
		"iEnd":           oSettings.fnDisplayEnd(),
		"iLength":        oSettings._iDisplayLength,
		"iTotal":         oSettings.fnRecordsTotal(),
		"iFilteredTotal": oSettings.fnRecordsDisplay(),
		"iPage":          oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
		"iTotalPages":    oSettings._iDisplayLength === -1 ?
			0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
	};
};

// Datatable serverside handling
$(document).ready(function () {
	var o_Settings = {
			"bProcessing": true,
	        "bServerSide": true,
	        "sort": "position",
	        "aaSorting"	: [[ 1, "asc" ]],
	        "aoColumnDefs": [
	     					{"sWidth" : "10px", "bSortable" : true, "bSearchable" : false, "aTargets" : [ 0 ]},
	     					{"sWidth": null, "bSearchable" : false, "bVisible":true, "aTargets" : [ 3, 4 ]}
	     				],
	        //bStateSave variable you can use to save state on client cookies: set value "true" 
	        "bStateSave": false,
	        //Default: Page display length
	        "iDisplayLength": 10,
	        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
	        "iDisplayStart": 0,
	        "oLanguage": {
				"sSearch": "Search all columns:"
			},
	        "fnDrawCallback": function () {
	            //Get page numer on client. Please note: number start from 0 So
	            //for the first page you will see 0 second page 1 third page 2...
	            //Un-comment below alert to see page number
	        	//alert("Current page number: "+this.fnPagingInfo().iPage);    
	        },         
	        "sAjaxSource": baseUrl + "/cpservice/products.html",
	        "fnServerData"		: function ( sSource, aoData, fnCallback ) {
				aoData.push({"name": "productCatCode", "value": $("#productCatCode").val()});
				aoData.push({"name": "productStatus", "value": $("#productStatus").val()});
				$.ajax( {
					"type"		: "POST",
					//"contentType": "application/json; charset=utf-8",
					"dataType"	: 'json',
					"url"		: sSource,
					"data"		: aoData,
					"success"	: fnCallback,
					"error" : function (xhr, textStatus, errorThrown) {
				        $("#error").html(xhr.responseText);
				    }
				} );
			},
	        "aoColumns": [
	            { "mData": "name", "bSortable": true, "sWidth": "20%" },
	            { "mData": "category", "sWidth": "100px" },
	            { "mData": "status", "sWidth": "100px" },
	            { "mData": "start_date", "sWidth": "80px" },
	            { "mData": "end_date", "sWidth": "80px", "bSortable": false },
	            { "mData": "budget", "sWidth": "30px", "bSortable": false },
	            { "mData": "id", "sWidth": "50px", "bSortable": false, 'mRender': function(resObj) {
	                	var res = '<button type="button" onclick="v_fViewDetailAProduct('+resObj+');" class="btn btn-info btn-xs" title="Edit">Info</button><br>';
	                	res += '<button type="button" id="removeTopic" onclick="v_fRemoveAProduct('+resObj+');" class="btn btn-danger btn-xs" title="Remove">Remove</button><br>';
	                	//res += '<a href="'+baseUrl+'/cp/download-product/' + resObj + '.html" title="Download file xác thực" class="btn btn-success btn-xs" >Download</a>';
	                	return res;
	              } },
	        ],
	        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
	            $('td:eq(0)', nRow).addClass( "avo-lime-h avo-heading-white" );
	            $('td:eq(1),td:eq(2),td:eq(3)', nRow).addClass( "avo-light" );
	          }
		};
	
	/* $('select#industries').change( function() {
		alert("Hello : " + $(this).val());
        oTable.fnFilter( $(this).val(), 1 );
    } ); */
    var oTable = $('#dataTables-example').dataTable(o_Settings);
    $("#filter").click(function(){
    	oTable.api().ajax.reload(); 
    });
    
    // Add a product action
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
