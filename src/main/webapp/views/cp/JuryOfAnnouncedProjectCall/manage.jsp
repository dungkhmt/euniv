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
                    <form action="${baseUrl}/cp/list-jury-submitted-projects.html" method="POST" role="form" >
	                    <div class="row">
	                        <div class="col-lg-12">
	                                <div class="form-group">
	                                   <%--  
	                                   <label>Đợt gọi đề tài </label>
	                                    <select path="JUSUPRJ_PRJCALLCODE" class="form-control" name="JUSUPRJ_PRJCALLCODE" >
	                                    	<c:forEach items="${projectCallList}" var="iProjectCall">
		                                        <option value="${iProjectCall.PROJCALL_CODE}">${iProjectCall.PROJCALL_NAME}</option>
	                                       	</c:forEach>
	                                    </select>
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
		                                <button type="submit" class="btn btn-primary" id="addANewPaper">Tiếp</button>
	                                </div>
	                        </div>
	                    </div>
	                    <!-- /.row (nested) -->
                    </form>
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
<script type="text/javascript">
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
	
	$(document).ready(function () {
		var o_Settings = {
							"bProcessing": true,
					        "bServerSide": true,
					        "sort": "position",
					        "aaSorting"	: [[ 1, "asc" ]],
					        "aoColumnDefs": [
					     					{"sWidth" : "10px", "bSortable" : true, "bSearchable" : false, "aTargets" : [ 0 ]},
					     					{"sWidth": null, "bSearchable" : false, "bVisible":true, "aTargets" : [ 1, 2 ]}
					     				],
					        //bStateSave variable you can use to save state on client cookies: set value "true" 
					        "bStateSave": false,
					        //Default: Page display length
					        "iDisplayLength": 20,
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
					        "sAjaxSource": baseUrl + "/cpservice/jury-submitted-projects.html",
					        "fnServerData"		: function ( sSource, aoData, fnCallback ) {
								aoData.push({"name": "projectCallCode", "value": $("#JUSUPRJ_PRJCALLCODE").val()});
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
					            { "mData": "projectcall_name", "bSortable": true, "sWidth": "20%" },
					            { "mData": "member_name", "sWidth": "100px" },
					            { "mData": "role", "sWidth": "100px" },
					            { "mData": "id", "sWidth": "50px", "bSortable": false, 'mRender': function(resObj) {
					                	var res = '<button type="button" id="removeTopic" onclick="v_fRemoveJuryOfAnnouncedProjectCall('+resObj+');" class="btn btn-danger btn-xs" title="Remove">Xóa</button><br>';
					                	return res;
					              } },
					        ],
					        "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
					            $('td:eq(0)', nRow).addClass( "avo-lime-h avo-heading-white" );
					            $('td:eq(1),td:eq(2),td:eq(3)', nRow).addClass( "avo-light" );
					          }
						};
		
	    var oTable = $('#dataTables-example').dataTable(o_Settings);
	    
	    $("#JUSUPRJ_PRJCALLCODE").change(function(){
	    	oTable.api().ajax.reload(); 
	    });
		
	    oTable.api().ajax.reload();
	    
		$('.add').click(function(){
			window.location = baseUrl+"/cp/add-a-projectcall.html";
		});
	});

	function v_fRemoveJuryOfAnnouncedProjectCall(iJuryOfAnnouncedProjectCallId){
		var r = confirm("Bạn có thực sự muốn xoá không ?");
		if (r == true) {
			var sDeleteJuryOfAnnouncedProjectCallUrl = baseUrl + "/cp/delete-jury-of-announced-project-call/"+ iJuryOfAnnouncedProjectCallId +".html";
			window.location = sDeleteJuryOfAnnouncedProjectCallUrl;
		} else {
		    return false;
		}
	}
	
	function showDepartment(sFaculty)
	{
		var sGeneratingUrl = "${baseUrl}/cpservice/getdepartments.html";
		var sFacultyCode = sFaculty.options[sFaculty.selectedIndex].value;  
		if (sFacultyCode.length > 0 ) { 
			 $.ajax({
					type: "POST",
					url: sGeneratingUrl,
					data: "sFacultyCode="+sFacultyCode,
					cache: false,
					beforeSend: function () { 
						//$('#department').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {    
						console.log('html : ' + html);
						$("#department").html( html );
					}
				});
		}else{
			var sDepartment = '<select class="form-control" name="department">';
				sDepartment += '<option value="">Chọn Bộ môn</option>';
				sDepartment +=  '</select>';
			$("#department").html( sDepartment );
			
			var sStaffs = '<select class="form-control" name="staff">';
				sStaffs += '<option value="">Chọn Thành viên</option>';
				sStaffs +=  '</select>';
			$("#staff").html( sStaffs );
		}
	}
	
	function showStaff(sDepartment) {
		var sGeneratingUrl = "${baseUrl}/cpservice/getsinglestaffs.html";
		var sDepartmentCode = sDepartment.options[sDepartment.selectedIndex].value;
		if (sDepartmentCode.length > 0 ) { 
			 $.ajax({
					type: "POST",
					url: sGeneratingUrl,
					data: "sDepartmentCode="+sDepartmentCode,
					cache: false,
					beforeSend: function () { 
						//$('#staff').html('<img src="loader.gif" alt="" width="24" height="24">');
					},
					success: function(html) {    
						$("#staff").html( html );
					}
				});
		}
	}
	
	function changeProjectCall(projectCallSelect){
		var projectCallCode = projectCallSelect.options[projectCallSelect.selectedIndex].value;
		//alert(projectCallCode);
		$.ajax({
			type:'POST',
			url:"${baseUrl}/cp/getJuryOfProjectCall.html",
			data: "projectCallCode=" + projectCallCode,
			success: function(result){
				alert(result);
					$("#dataTables-example").html(result);
			}
		});
	}

</script>

