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
			<h1 class="page-header">Danh sách đề tài</h1>
			<p>
				<!-- <button type="button" class="btn btn-primary btn-xs gen">Kết xuất danh sách</button> -->
			</p>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Danh sách đề tài</div>
				<p>
					<button type="button" class="btn btn-primary add">Thêm mới</button>
				</p>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div>
						<div class="form-group input-group">
							<select class="form-control" style="width:200px;" name="threadCatCode" id="threadCatCode">
								<option value="" >Chọn nhóm</option>
	                        	<c:forEach items="${threadCategory}" var="threadCat">
                                 	<option value="${threadCat.PROJCAT_Code}">${threadCat.PROJCAT_Name}</option>
                               	</c:forEach>
	                        </select>
	                        <select class="form-control hidden" style="width:200px;" name="threadStatus" id="threadStatus">
	                        	<!-- <option value="" >Chọn trạng thái</option>  -->
	                        	<c:forEach items="${threadStatuses}" var="threadStat">
                                 	<c:if test="${threadStat.PROJSTAT_Code=='SUBMIT'}">
                                 	<option value="${threadStat.PROJSTAT_Code}">${threadStat.PROJSTAT_Description}</option>
                               		</c:if>
                               	</c:forEach>
	                        </select>
	                        <select class="form-control" style="width:200px;" name="threadYear" id="threadYear">
	                        	<option value="" >Chọn năm bắt đầu</option>
	                        	<c:forEach items="${threadAcademicYears}" var="threadAcadYear">
                                 	<option value="${threadAcadYear.ACAYEAR_Code}">${threadAcadYear.ACAYEAR_Code}</option>
                               	</c:forEach>
	                        </select>
	                        <select class="form-control hidden" style="width:200px;" name="threadFaculty" id="threadFaculty">
	                        	<option value="" >Chọn viện</option>
	                        	<c:forEach items="${threadFaculties}" var="threadFaculty">
                                 	<option value="${threadFaculty.faculty_Code}">${threadFaculty.faculty_Name}</option>
                               	</c:forEach>
	                        </select>
	                        <select class="form-control hidden" style="width:200px;" name="threadDepartment" id="threadDepartment">
	                        	<option value="" >Chọn bộ môn</option>
	                        	<c:forEach items="${threadDepartments}" var="threadDepartment">
                                 	<option value="${threadDepartment.department_Code}">${threadDepartment.department_Name}</option>
                               	</c:forEach>
	                        </select>
	                        <select class="form-control hidden" style="width:200px;" name="threadStaff" id="threadStaff">
	                        	<option value="" >Chọn cán bộ</option>
	                        	<c:forEach items="${threadStaffs}" var="threadStaff">
                                 	<option value="${threadStaff.staff_Code}">${threadStaff.staff_Name}</option>
                               	</c:forEach>
	                        </select>
							<button type="button" id="filter" class="btn btn-primary filter">Chọn</button>
						</div>
					</div>
					<div class="dataTable_wrapper">
						<table class="table table-striped table-bordered table-hover" id="dataTables">
							<thead>
								<tr>
									<th title="Name of project">Tên</th>
									<th title="Category of project">Loại hình</th>
									<th title="Description">Nội dung</th>
									<th title="Status">Trạng thái</th>
									<th title="Result">Kết quả</th>
									<th title="Start year">Năm thực hiện</th>
									<th title="Budget">Kinh phí</th>
									<th></th>
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
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>

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
	        "sAjaxSource": baseUrl + "/cpservice/threads.html",
	        "fnServerData"		: function ( sSource, aoData, fnCallback ) {
				aoData.push({"name": "threadCatCode", "value": $("#threadCatCode").val()});
				aoData.push({"name": "threadStatus", "value": $("#threadStatus").val()});
				aoData.push({"name": "threadYear", "value": $("#threadYear").val()});
				aoData.push({"name": "threadFaculty", "value": $("#threadFaculty").val()});
				aoData.push({"name": "threadDepartment", "value": $("#threadDepartment").val()});
				aoData.push({"name": "threadStaff", "value": $("#threadStaff").val()});
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
	            { "mData": "content", "sWidth": "100px" },
	            { "mData": "status", "sWidth": "100px" },
	            { "mData": "result", "sWidth": "100px" },
	            { "mData": "start_date", "sWidth": "80px" },
	            //{ "mData": "end_date", "sWidth": "80px", "bSortable": false },
	            { "mData": "budget", "sWidth": "30px", "bSortable": false },
	            { "mData": "id", "sWidth": "50px", "bSortable": false, 'mRender': function(resObj) {
	                	var res = '<button type="button" onclick="v_fViewDetailAThread('+resObj+');" class="btn btn-info btn-xs" title="Edit">Info</button><br>';
	                	res += '<a href="'+baseUrl+'/cp/download-thread/' + resObj + '.html" title="Download file xác thực" class="btn btn-success btn-xs" >Download</a>';
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
    var oTable = $('#dataTables').dataTable(o_Settings);
    $("#filter").click(function(){
    	oTable.api().ajax.reload(); 
    });
   
    $('.add').click(function(){
    	window.location = baseUrl+"/cp/add-a-thread.html";
    });
});

function v_fViewDetailAThread(iThreadId){
	//var sViewDetailUrl = baseUrl + "/cp/threadshow/"+iThreadId+".html";
	var sViewDetailUrl = baseUrl + "/cp/threadshowtoupdate/"+iThreadId+".html";
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

function v_fRemoveAThread(iThreadId){
	var r = confirm("Do you really want to remove this ?");
	if (r == true) {
		var sRemoveAThreadUrl = baseUrl + "/cp/remove-a-thread/"+iThreadId+".html";
		window.location = sRemoveAThreadUrl;
	} else {
	    return false;
	}
}
</script>
