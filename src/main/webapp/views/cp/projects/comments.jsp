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
            <h1 class="page-header">Tổng hợp comments phản biện đề tài</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
        	<form:form action="${baseUrl}/cp/edit-a-projectcomment.html" method="POST" commandName="projectFormEdit" role="form" id="confirm-comment" enctype="multipart/form-data">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    Tổng hợp comments phản biện đề tài
	                </div>
	                <div class="panel-body">
	                    <div class="row">
	                        <div class="col-lg-12">
	                        	<div class="panel panel-default">
			                        <div class="panel-heading">
			                            <label for="projectResult">Tên đề tài</label>
			                        </div>
			                        <div class="panel-body">
			                            <div class="tab-content">
			                                <div class="tab-pane fade in active" >${projectEdit.PROJ_Name}</div>
			                            </div>
			                        </div>
			                    </div>
	                        </div>
	                    </div>
	                    <!-- /.row (nested) -->
	                    
	                    <div class="row">
	                        <div class="col-lg-12">
	                        	<c:forEach items="${listDetailCommentSubmittedProjects}" var="detailCommentSubmittedProjects">
				                    <div class="panel panel-default">
				                        <div class="panel-heading">
				                            <label for="projectResult">Ý kiến phản biện - ${detailCommentSubmittedProjects.CMTSUBPRJ_StaffCode}</label>
				                        </div>
				                        <div class="panel-body">
				                            <div class="tab-content">
				                                <div class="tab-pane fade in active" ><b>Mục tiêu :</b></div>
				                                <ul>
				                                	<li>Tính cấp thiết : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Motivation}</b></li>
				                                	<li>Tính mới : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Innovation}</b></li>
				                                	<li>Khả năng áp dụng/phát triển : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Applicability}</b> </li>
				                                </ul>
				                            </div>
				                            <div class="tab-content">
				                                <div class="tab-pane fade in active" ><b>Nội dung :</b></div>
				                                <ul>
				                                	<li>Phương pháp nghiên cứu : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_RearchMethodology}</b></li>
				                                	<li>Các nội dung nghiên cứu chính : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_ResearchContent}</b></li>
				                                </ul>
				                            </div>
				                            <div class="tab-content">
				                                <div class="tab-pane fade in active" ><b>Sản phẩm cụ thể :</b></div>
				                                <ul>
				                                	<li>Bài báo: Trong nước; Scopus, ISI (khuyến khích) : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Paper}</b></li>
				                                	<li>Sản phẩm/thiết bị có địa chỉ ứng dụng cụ thể : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Product}</b></li>
				                                	<li>SHTT : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Patent}</b></li>
				                                </ul>
				                            </div>
				                            <div class="tab-content">
				                                <div class="tab-pane fade in active" ><b>Các ưu tiên khác :</b></div>
				                                <ul>
				                                	<li>Học viên sau đại học, NCS  (cùng hướng NC luận văn) : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Graduate_Student}</b></li>
				                                	<li>Cán bộ trẻ <= 35 : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Young_Rearcher}</b></li>
				                                	<li>Tham gia đào tạo NCS, ThS : <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Education_Graduate}</b></li>
				                                	<li>Kinh phí phù hợp: <b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Reasonable_Budget}</b></li>
				                                </ul>
				                            </div>
				                            
				                            <div class="tab-content">
				                                <div class="tab-pane fade in active" ><b>Kết luận :</b></div>
				                                <ul>
				                                	<li><b>${detailCommentSubmittedProjects.CMTSUBPRJ_Eval_Conclusion}</b></li>
				                                	
				                                </ul>
				                            </div>
				                        </div>
				                    </div>
			                    </c:forEach>
	                        </div>
	                        <!-- /.col-lg-6 (nested) -->
	                    </div>
	                    <!-- /.row (nested) -->
	                   <div class="row">
	                   		<div class="col-lg-12">
	                   			<div class="form-group">
                               		<label for="summaryComment">Tổng hợp nhận xét phản biện</label>
                                	<textarea name="summaryComment" id="summaryComment" class="form-control">
			                            <c:if test='${summaryComment != ""}'>
			                                ${summaryComment}
			                            </c:if>
                                	</textarea>
                           		</div>
                       		</div>
	                   </div>
	                    <div class="row">
	                    	<div class="col-lg-12">
		                    	<%-- <c:choose>
									<c:when test="${projectEdit.PROJ_Status_Code != 'APPROVED' && projectEdit.PROJ_Status_Code != 'REJECT'}"> --%>
					                    <div class="form-group">
		                                   <label>Chọn trạng thái phê duyệt</label>
		                                   <form:select path="projectStatusCode" class="form-control" name="projectStatusCode" id="projectStatusCode">
		                                        <option value="ACCEPT_REVISION" <c:if test='${projectEdit.PROJ_Status_Code == "ACCEPT_REVISION"}'> selected </c:if> >Phê duyệt nhưng cần chỉnh sửa</option>
		                                        <option value="APPROVED" <c:if test='${projectEdit.PROJ_Status_Code == "APPROVED"}'> selected </c:if> >Phê duyệt</option>
		                                        <option value="REJECT" <c:if test='${projectEdit.PROJ_Status_Code == "REJECT"}'> selected </c:if> >Từ chối</option>
		                                   </form:select>
		                                   <form:errors path="projectStatusCode" class="alert-danger"></form:errors>
		                                </div>
	                               	<%-- </c:when>    
								    <c:otherwise>
					                    <div class="panel panel-default">
					                        <div class="panel-heading">
					                            <label for="projectResult">Trạng thái</label>
					                        </div>
					                        <div class="panel-body">
					                            <div class="tab-content">
					                                <div class="tab-pane fade in active">${(projectEdit.PROJ_Status_Code == 'APPROVED') ? 'Phê duyệt' : ((projectEdit.PROJ_Status_Code == 'REJECT') ? 'Từ chối' : ((projectEdit.PROJ_Status_Code == 'ACCEPT_REVISION') ? 'Phê duyệt nhưng cần chỉnh sửa' : ''))}</div>
					                            </div>
					                        </div>
					                    </div>
								    </c:otherwise>
								</c:choose> --%>
							</div>
							<div class="col-lg-12">
		                    	 <!-- Buttons -->
			                     <input type="hidden" value="0" name="confirmed" id="confirmed" />
	                             <button type="submit" class="btn btn-primary">Lưu</button>
	                             <input type="hidden" value="${projectEdit.PROJ_ID}" name="projectId" id="projectId" />
	                             <input type="hidden" value="${projectEdit.PROJ_PRJCall_Code}" name="projectCallCode" id="projectCallCode" />
	                             <input type="hidden" value="${projectEdit.PROJ_Name}" name="projectName" id="projectName" />
	                             <button type="reset" class="btn btn-info cancel">Hủy</button>
	                             <%-- <button type="reset" class="btn btn-danger" onclick="v_fConfirmCommentProject(${projectEdit.PROJ_ID})">Xác nhận</button> --%>
                             </div>
	                    </div>
	                </div>
	                <!-- /.panel-body -->
	            </div>
	            </form:form>
	            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
</div>
<!-- /#page-wrapper -->

<script type="text/javascript">

$('textarea').each( function() {
    CKEDITOR.replace( $(this).attr('id') );
});
 
$(document).ready(function(){
	$('button.cancel').click(function(){
		window.location = baseUrl+"/cp/collect-comments.html";
	});
});

function v_fConfirmCommentProject(iProjectId){
	if(confirm("Chú ý ! Sau khi gửi comments thì không thể chỉnh sửa được.")){
		$("input#confirmed").val(1);
		$("form#confirm-comment").submit();
    }
    else{
        return false;
    }
}
</script>

