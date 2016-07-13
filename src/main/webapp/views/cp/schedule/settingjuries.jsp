<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- DataTables CSS -->
<link href="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css"/>" rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="<c:url value="/assets/libs/datatables-responsive/css/dataTables.responsive.css" />" rel="stylesheet">

<!-- Jquery UI CSS -->
<link href="<c:url value="/assets/css/jquery-ui.css" />" rel="stylesheet">
<!-- jQuery UI -->
<script src="<c:url value="/assets/js/jquery-ui.js"/>"></script>
  <style>
  .custom-combobox {
    position: relative;
    display: inline-block;
  }
  .custom-combobox-toggle {
    position: absolute;
    top: 0;
    bottom: 0;
    margin-left: -1px;
    padding: 0;
  }
  .custom-combobox-input {
    margin: 0;
    padding: 5px 10px;
  }
  </style>
<div id="${(disableHeader != null) ? 'page-wrapper-no-layout' : 'page-wrapper'}">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Sắp xếp hội đồng phản biện</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
		<div class="row">		
			<div class="col-lg-3">				
				<div class="well">
					<h4>Thông tin đợt bảo vệ</h4>	
					<div class="input-group">
						<span class="input-group-addon">Đợt bảo vệ</span>
						<p class="form-control">${defenseSession.DEFSESS_Name}</p>
						<input id="defenseSession" type="hidden" value="${defenseSession.DEFSESS_Code}" name="defenseSession">										 
					</div>
					<div class="input-group">						
						<span class="input-group-addon">Bộ môn</span>
						<p class="form-control">${department}</p>
					</div>	    	
					<div class="row" style="margin-top:10px;margin-left:0px;">
						
							<button type="button" class="btn btn-primary" id="save">Lưu</button>
							<button type="button" class='form btn btn-primary' id="cancel" >Quay về</button>				
						<!-- /.col-lg-4 -->
					</div>				  
				</div>
			
				<!-- /.col-lg-4 -->
			</div>
	
			<div class="col-lg-4">
					<div class="well">
						<h4>Xếp tự động</h4>
						<div class="input-group">
							<span class="input-group-addon">Thuật toán</span> <select type="check"
								class="form-control" id="filter-algorithm">
								<option value="local search" selected>Local search</option>
								<option value="Backtracking search">Backtracking search</option>
							</select>								
						</div>
						<div style="margin-top:10px;">
							<button id="autoSchedule" class="btn btn-success" ><i class="fa fa-table"></i>&nbspXếp tự động</button>
							<button id="checkSchedule" class="btn btn-info"><i class="fa fa-search"></i>&nbspKiểm tra</button>
							<button id="reloadSchedule" class="btn btn-warning" ><i class="fa fa-undo"></i>&nbspKhởi tạo lại</button>
						</div>			
					</div>					
				</div>		
				
			<div class="col-lg-4">
				<div class="well">
				<h4>Lưu ra file excel</h4>
				<div class="input-group">
					<span class="input-group-addon">Tên file</span>
					<input type="text" class="form-control" id="file-name" placeholder="Nhập tên file" value="HỘI ĐỒNG BẢO VỆ CAO HỌC ${defenseSession.DEFSESS_Name}">					 
				</div>		
				<div style="margin-top:10px;">
					<button class="btn btn-success" id="excel" ><i class="fa fa-floppy-o"></i>&nbspLưu</button>
				</div>			
				</div>				
			</div>		
					
		
		</div>
		<!-- /.row -->
		
		<div id ="status" ></div>
		
		<div class="panel panel-default">
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="" id="message">
							
						</div>
						<table class="table table-striped table-bordered table-hover" id="dataTables-Juries">
						<input type="hidden" value="${listMasterDefenseJuryThesis.size()}" id="listMasterDefenseJuryThesisSize">
						<caption><h2>HỘI ĐỒNG BẢO VỆ CAO HỌC ${defenseSession.DEFSESS_Name}</caption>
							<thead>
								<tr>
									<th title="Name">Học viên</th>
									<th title="Department">Đề tài</th>
									<th title="Faculty">GV Hướng dẫn</th>
									<th title="Faculty">Phản biện 1</th>
									<th title="Faculty">Phản biện 2</th>
									<th title="Faculty">Chủ tịch</th>
									<th title="Faculty">Thư ký</th>
									<th title="Faculty">Ủy viên</th>
									<th title="Faculty">Thời gian</th>
									<th title="Faculty">Địa điểm</th>
									<th title="Faculty"></th>
									<!-- <th title="Faculty">HĐ</th>
									<th title="Faculty">Lớp-khóa</th>-->
								</tr>
							</thead>
							<tbody>
								<c:if test="${listMasterDefenseJuryThesis.size() != 0}">								
									<c:forEach var="i" begin="0" end="${listMasterDefenseJuryThesis.size()-1}">
										<tr class="gradeX">
										 	<td class="studentNameCol"><c:out value="${listMasterDefenseJuryThesis.get(i).masterThesis.student.student_Name}"/></td>
										 	<td class="thesisNameCol"><c:out value="${listMasterDefenseJuryThesis.get(i).masterThesis.thesis_Name}"/></td>
										 	<input type="hidden" class="thesisCode" value="${listMasterDefenseJuryThesis.get(i).masterThesis.thesis_Code}">
										 	<td class="mentorNameCol"><div id="val-supervisor-${i}"><c:out value="${listMasterDefenseJuryThesis.get(i).masterThesis.supervisor.academicRank.academicRank_VNAbbr} ${listMasterDefenseJuryThesis.get(i).masterThesis.supervisor.staff_Name}"/></div></td>
										 	<td class="examiner1Col"><div id="val-examiner1-${i}"><c:out value="${listMasterDefenseJuryThesis.get(i).examiner1.academicRank.academicRank_VNAbbr} ${listMasterDefenseJuryThesis.get(i).examiner1.EXTSTAF_Name}"/> </div><div onclick="editJury('dialog-form-master-outer', 'examiner1-${i}');" class="btn btn-info btn-xs" title="Edit"><i class="fa fa-pencil pointer"></i></div><div class="btn btn-warning btn-xs" onclick="deleteJury('examiner1-${i}');" title="Delete"><i class="fa fa-trash"></i></div></td>
										 	<td class="examiner2Col"><div id="val-examiner2-${i}"><c:out value="${listMasterDefenseJuryThesis.get(i).examiner2.academicRank.academicRank_VNAbbr} ${listMasterDefenseJuryThesis.get(i).examiner2.staff_Name}"/>  </div> <div onclick="editJury('dialog-form-master-inner', 'examiner2-${i}');" class="btn btn-info btn-xs" title="Edit"><i class="fa fa-pencil pointer"></i></div><div onclick="deleteJury('examiner2-${i}');" class="btn btn-warning btn-xs" title="Delete"><i class="fa fa-trash"></i></div></td>
										 	<td class="presidentCol"><div id="val-president-${i}"><c:out value="${listMasterDefenseJuryThesis.get(i).president.academicRank.academicRank_VNAbbr} ${listMasterDefenseJuryThesis.get(i).president.staff_Name}"/> </div> <div onclick="editJury('dialog-form-master-inner', 'president-${i}');" class="btn btn-info btn-xs" title="Edit"><i class="fa fa-pencil pointer"></i></div><div onclick="deleteJury('president-${i}');" class="btn btn-warning btn-xs" title="Delete"><i class="fa fa-trash"></i></div></td>
										 	<td class="secretaryCol"><div id="val-secretary-${i}"><c:out value="${listMasterDefenseJuryThesis.get(i).secretary.academicRank.academicRank_VNAbbr} ${listMasterDefenseJuryThesis.get(i).secretary.staff_Name}"/>  </div> <div onclick="editJury('dialog-form-master-inner', 'secretary-${i}');" class="btn btn-info btn-xs" title="Edit"><i class="fa fa-pencil pointer"></i></div><div onclick="deleteJury('secretary-${i}');" class="btn btn-warning btn-xs" title="Delete"><i class="fa fa-trash"></div></td>
										 	<td class="commissionerCol"><div id="val-commissioner-${i}"><c:out value="${listMasterDefenseJuryThesis.get(i).member.academicRank.academicRank_VNAbbr} ${listMasterDefenseJuryThesis.get(i).member.EXTSTAF_Name}"/>  </div> <div onclick="editJury('dialog-form-master-outer', 'commissioner-${i}');" class="btn btn-info btn-xs" title="Edit"><i class="fa fa-pencil pointer"></i></div><div onclick="deleteJury('commissioner-${i}');" class="btn btn-warning btn-xs" title="Delete"><i class="fa fa-trash"></i></div></td>
										 	<td class="slotCol"><div id="val-slot-${i}"><c:out value="${listMasterDefenseJuryThesis.get(i).slot.jurySlot_Name}"/> </div> <div onclick="editJury('dialog-form-slot', 'slot-${i}');" class="btn btn-info btn-xs" title="Edit"><i class="fa fa-pencil pointer"></i></div><div onclick="deleteJury('slot-${i}');" class="btn btn-warning btn-xs" title="Delete"><i class="fa fa-trash"></i></div></td>
										 	<td class="roomCol"><div id="val-room-${i}"><c:out value="${listMasterDefenseJuryThesis.get(i).room.r_Code}"/> </div> <div onclick="editJury('dialog-form-room', 'room-${i}');" class="btn btn-info btn-xs" title="Edit"><i class="fa fa-pencil pointer"></i></div><div onclick="deleteJury('room-${i}');" class="btn btn-warning btn-xs" title="Delete"><i class="fa fa-trash"></i></div></td>
										 	
										 	<!-- <td><div id="val-no-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.coucilNo}"/> <input type="hidden" value="${aMasterDefenseJuryThesis.coucilNo}" name="no" /> </div> <span onclick="editJury('dialog-form-info', 'no-${aMasterDefenseJuryThesis.iKey}');" class="fa fa-pencil pointer" title="Edit"></span></td>
										 	<td><div id="val-sclass-${aMasterDefenseJuryThesis.iKey}"><c:out value="${aMasterDefenseJuryThesis.classCode}"/></td>-->
										 	<td class="utility">
										 		<div class="btn btn-success generatePDF"><i class="fa fa-print"></i></div>
										 		<div class="btn btn-warning clear-row"><i class="fa fa-trash"></i></div>
										 		<div class="uti" id="uti-${i}">
										 			<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).masterThesis.supervisor.staff_Code}" name="supervisor[${i}]" class="supervisor" />
											 		<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).masterThesis.thesis_Code}" name="masterDefenseThesis[${i}]" class="thesis" />
											 		<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).examiner1.EXTSTAF_Code}" name="examiner1[${i}]" class="examiner1"/> 
											 		<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).examiner2.staff_Code}" name="examiner2[${i}]" class="examiner2"/>
											 		<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).president.staff_Code}" name="president[${i}]" class="president"/> 
											 		<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).secretary.staff_Code}" name="secretary[${i}]" class="secretary"/>
											 		<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).member.EXTSTAF_Code}" name="commissioner[${i}]" class="commissioner" />
											 		<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).slot.jurySlot_Code}" name="slot[${i}]" class="slot"/>
											 		<input type="hidden" value="${listMasterDefenseJuryThesis.get(i).room.r_Code}" name="room[${i}]" class="room" />
											 	</div>
										 	</td>
										 	
										 	
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
					</div>
					<!-- /.table-responsive -->

				</div>
			</div>

		
		
		<!-- /.row -->
	
</div>

<div class="hide">
	<div id="dialog-form-master-inner"  title="Chỉnh sửa thông tin">
	  <form>
		  <div class="form-group" >
			  <select name="master-name-inner" id="master-name-inner" class="form-control">
			  	<c:if test="${listInnerJuryMembers != null}">
	               	<c:forEach items="${listInnerJuryMembers}" var="staff">
	               		<option value="${staff.staff_Code}" >${staff.academicRank.academicRank_VNAbbr} ${staff.staff_Name}</option>
	               	</c:forEach>
                </c:if>
			  </select>
		 </div>
	  </form>
	</div>
	
	<div id="dialog-form-master-outer"  title="Chỉnh sửa thông tin">
	  <form>
		  <div class="form-group" >
			  <select name="master-name-outer" id="master-name-outer" class="form-control">
			  	<c:if test="${listOuterJuryMembers != null}">
	               	<c:forEach items="${listOuterJuryMembers}" var="staff">
	               		<option value="${staff.EXTSTAF_Code}" >${staff.academicRank.academicRank_VNAbbr} ${staff.EXTSTAF_Name}</option>
	               	</c:forEach>
                </c:if>
			  </select>
		 </div>
	  </form>
	</div>
	
	<div id="dialog-form-room" title="Chỉnh sửa thông tin">
	  <form>
		  <div class="form-group" >
			  <select name="room-add" id="room-add" class="form-control">
				  	<c:if test="${listJuryRooms != null}">
		            	<c:forEach items="${listJuryRooms}" var="room">
		            		<option value="${room.juryRoom_Code}" >${room.juryRoom_Code}</option>
		            	</c:forEach>
	            	</c:if>
			  </select>
		 </div>
	  </form>
	</div>
	
	<div id="dialog-form-slot" title="Chỉnh sửa thông tin">
	  <form>
		  <div class="form-group" >
			  <select name="slot-add" id="slot-add" class="form-control">
				  	<c:if test="${listJurySlot != null}">
		            	<c:forEach items="${listJurySlot}" var="slot">
		            		<option value="${slot.jurySlot_Code}" >${slot.jurySlot_Name}</option>
		            	</c:forEach>
	            	</c:if>
			  </select>
		 </div>
	  </form>
	</div>
	
	<div id="dialog-form-info" title="Chỉnh sửa thông tin">
	  <form>
		  <div class="form-group" >
			  <input name="jury-info" id="jury-info" class="form-control" />
		 </div>
	  </form>
	</div>
</div>

 	

<!-- /#page-wrapper -->

<!-- DataTables JavaScript -->
<script
	src="<c:url value="/assets/libs/datatables/media/js/jquery.dataTables.js"/>"></script>
<script
	src="<c:url value="/assets/libs/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"/>"></script>
	

 <script type="text/javascript">
var tableToExcel = (function() {
  var uri = 'data:application/vnd.ms-excel;base64,'
    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--><meta http-equiv="content-type" content="text/plain; charset=UTF-8"/></head><body><table>{table}</table></body></html>'
    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
  return function(table, name) {
    if (!table.nodeType) table = document.getElementById(table)
    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
    //window.location.href = uri + base64(format(template, ctx))
    var link = document.createElement("a");
    link.download = $('#file-name').val()+".xls";
    link.href = uri + base64(format(template, ctx));
    link.click();
  }
})()
</script>
<script src="<c:url value="/assets/js/source/mastermanagement/settingjuries.js"/>"></script>