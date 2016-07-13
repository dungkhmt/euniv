<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/webapp/cp/home.html">Hệ thống hỗ trợ quản lý Khoa học và Công nghệ, ĐHBK Hà Nội</a>
    </div>
    <!-- /.navbar-header -->

    <ul class="nav navbar-top-links navbar-right">
        <!-- /.dropdown -->
        <li class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#">
            	${currentUserName}
                <i class="fa fa-user fa-fw"></i>  
                <i class="fa fa-caret-down"></i>
            </a>
            <ul class="dropdown-menu dropdown-user">
                <li><a href="<c:url value="${baseUrl}/cp/profile.html"/>"><i class="fa fa-user fa-fw"></i> Thông tin người dùng</a>
                </li>
                <li><a href="<c:url value="${baseUrl}/cp/changepass.html"/>"><i class="fa fa-gear fa-fw"></i> Cài đặt</a>
                </li>
                <li class="divider"></li>
                <li><a href="<c:url value="/j_spring_security_logout" />"><i class="fa fa-sign-out fa-fw"></i> Đăng xuất</a>
                </li>
            </ul>
            <!-- /.dropdown-user -->
        </li>
        <!-- /.dropdown -->
    </ul>
    <!-- /.navbar-top-links -->

	<c:if test="${disableHeader == null}">
	<div class="navbar-default sidebar" id="sidebar" role="navigation">
        <div class="sidebar-nav navbar-collapse">
            <ul class="nav" id="side-menu">
            	<c:forEach items="${funcsParentsList}" var="showedParentPermission">
            		<c:if test="${showedParentPermission.SELECTED == 1}">	
		                <li>
		                	<a class="${showedParentPermission.FUNC_SELECTED_CLASS}" href="<c:url value="${baseUrl}${showedParentPermission.FUNC_URL}"/>"><i class="${showedParentPermission.FUNC_TITLE_CLASS}"></i> ${showedParentPermission.FUNC_NAME} <c:if test="${showedParentPermission.FUNC_HAS_CHILDREN == 1}"><span class="fa arrow"></span></c:if></a>
		                	<c:if test="${showedParentPermission.FUNC_HAS_CHILDREN == 1}">
		                	<ul class="nav nav-second-level">
		                	<c:forEach items="${funcsChildrenList}" var="showedChildrenPermission">
		                		<c:if test="${showedChildrenPermission.FUNC_PARENTID == showedParentPermission.FUNC_ID}">
		                			<c:if test="${showedChildrenPermission.SELECTED == 1}">
			                			<li>
											<a class="${showedChildrenPermission.FUNC_SELECTED_CLASS}" href="<c:url value="${baseUrl}${showedChildrenPermission.FUNC_URL}"/>"><i class="${showedChildrenPermission.FUNC_TITLE_CLASS}"></i> ${showedChildrenPermission.FUNC_NAME}</a>
										</li>
									</c:if>									                	
			                    </c:if>
		                	</c:forEach>
		                	</ul>
		                	</c:if>
		                </li>
	                </c:if>
                </c:forEach>
                
                
                
                <!-- OLD LIST -->
                <c:if test="${iMANAGEUSERS eq 1 && (currentUserRole eq 'ROLE_ADMIN' || currentUserRole eq 'SUPER_ADMIN')}">
	                <%-- <li>
	                    <a class="${users}" href="<c:url value="${baseUrl}/cp/users.html"/>"><i class="fa fa-user fa-fw"></i> Quản lý Users</a>
	                </li> --%>
                </c:if>
                
                <%-- <li>
                    <a href="#"><i class="fa fa-tasks fa-fw"></i> ++++++ QLKK +++++++<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                    	<c:if test="${iMANAGEPAPERS eq 1}">
			                <li>
			                    <a class="${papers}" href="<c:url value="${baseUrl}/cp/papers.html"/>"><i class="fa fa-book"></i> Quản lý Bài báo</a>
			                </li>
		                </c:if>
		                <c:if test="${iMANAGETOPICS eq 1}">
			                <li>
			                    <a class="${topics}" href="<c:url value="${baseUrl}/cp/topics.html"/>"><i class="fa fa-list-alt fa-fw"></i> Quản lý Đề tài</a>
			                </li>
		                </c:if>
		                <c:if test="${iMANAGEPATENTS eq 1}">
			                <li>
			                    <a class="${patents}" href="<c:url value="${baseUrl}/cp/patents.html"/>"><i class="fa fa-file fa-fw"></i> Quản lý Bằng sáng chế</a>
			                </li>
		                </c:if>
		                <c:if test="${currentUserRole eq 'ROLE_ADMIN'}">
			                <li>
			                    <a class="${summary}" href="<c:url value="${baseUrl}/cp/summary.html"/>"><i class="fa fa-dashboard fa-fw"></i> Tổng hợp</a>
			                </li>
		                </c:if>
		           </ul>
		        </li> --%>
		        
		        <c:if test="${iMANAGEPRODUCTS eq 1}">
	                <%-- <li>
	                    <a href="#"><i class="fa fa-th-list fa-fw"></i> Quản lý thực hiện Đề tài<span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                    	<c:if test="${iMANAGEPROJECTCALLS eq 1}">
				                <li>
				                    <a href="<c:url value="${baseUrl}/cp/project-call-open.html"/>"><i class="fa fa-briefcase"></i> Mở đợt gọi Đề tài</a>
				                </li>
			                </c:if>
			                
			                <c:if test="${iPROJECTSIGNUP eq 1}">
				                <li>
				                    <a href="<c:url value="${baseUrl}/cp/threads-listadd.html"/>"><i class="fa fa-briefcase"></i> Đăng ký Đề tài</a>
				                    <a href="<c:url value="${baseUrl}/cp/list-projects.html"/>"><i class="fa fa-briefcase"></i> Đăng ký Đề tài</a>
				                </li>
			                </c:if>
			                <!-- 
			                <li>
			                    <a href="<c:url value="${baseUrl}/cp/products.html"/>"><i class="fa fa-file-text"></i> Đăng ký Chuyên đề</a>
			                </li>
			                 -->
			                <c:if test="${iADDJURYSUBMITTEDPROJECTS eq 1}">
				                <li>
				                    <a href="<c:url value="${baseUrl}/cp/add-jury-submitted-projects.html"/>"><i class="fa fa-file-text"></i> Thành lập hội đồng xét đề tài</a>
				                </li>
			                </c:if>
			                
			                <c:if test="${iASSIGNJURYSUBMITTEDPROJECTS eq 1}">
				                <li>
				                    <a href="<c:url value="${baseUrl}/cp/assign-jury-submitted-projects.html"/>"><i class="fa fa-file-text"></i> Phân công phản biện đề tài</a>
				                </li>
			                </c:if>
			                
			                <c:if test="${iMODIFYSUBMITTEDPROJECTS eq 1}">
				                <li>
				                    <a href="<c:url value="${baseUrl}/cp/review-submitted-projects.html"/>"><i class="fa fa-file-text"></i> Phản biện đề tài</a>
				                </li>
			                </c:if>
			                
			                <c:if test="${iREVIEWSUBMITTEDPROJECTS eq 1}">
				                <li>
				                    <a href="<c:url value="${baseUrl}/cp/modify-submitted-projects.html"/>"><i class="fa fa-file-text"></i> Chỉnh sửa thuyết minh đề tài sau phản biện</a>
				                </li>
			                </c:if>
			                <li>
			                    <a href="<c:url value="${baseUrl}/cp/collect-comments.html"/>"><i class="fa fa-briefcase"></i> Tổng hợp comments</a>
			                </li>
			                <c:if test="${currentUserRole eq 'ROLE_ADMIN' || currentUserRole eq 'SUPER_ADMIN'}">
				                <li>
				                    <a href="<c:url value="${baseUrl}/cp/approve-projects.html"/>"><i class="fa fa-briefcase"></i> Phê duyệt đề tài</a>
				                </li>
				                
				                <li>
				                    <a href="<c:url value="${baseUrl}/cp/threads-evaluate.html"/>"><i class="fa fa-briefcase"></i> Nghiệm thu đề tài</a>
				                </li>
			                </c:if>
			                
			                <li>
			                    <a href="<c:url value="${baseUrl}/cp/threads.html"/>"><i class="fa fa-briefcase"></i> Thống kê đề tài</a>
			                </li>
			             </ul>
	                </li> --%>
                </c:if>
                 
                <%-- <li>
                    <a href="#"><i class="fa fa-university fa-fw"></i> Quản lý danh mục<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
                            <a class="${falcuties}" href="#"><i class="fa fa-edit fa-fw"></i> Quản lý Khoa/Viện</a>
                        </li>
                        <li>
                            <a class="${departments}" href="#"><i class="fa fa-table fa-fw"></i> Quản lý Bộ môn</a>
                        </li>
                        <li>
	                    	<a class="${settings}" href="#"><i class="fa fa-wrench fa-fw"></i> Settings</a>
	                	</li>
                    </ul>
                    
                </li> --%>
                
                <%-- <li>
                    <a href="#"><i class="fa fa-graduation-cap fa-fw"></i> Quản lý đào tạo cao học<span class="fa arrow"></span></a>
                    <ul class="nav nav-second-level">
                        <li>
		                    <a class="${professor}" href="<c:url value="${baseUrl}/mm/professors.html"/>"><i class="fa fa-dashboard fa-fw"></i> Quản lý giảng viên</a>
		                </li>
		                <li>
		                    <a class="${externalprofessor}" href="<c:url value="${baseUrl}/mm/externalprofessors.html"/>"><i class="fa fa-dashboard fa-fw"></i> Quản lý giảng viên ngoài trường</a>
		                </li>
		                <li>
		                    <a class="${university}" href="<c:url value="${baseUrl}/mm/partnerUniversities.html"/>"><i class="fa fa-dashboard fa-fw"></i> Quản lý trường đại học đối tác</a>
		                </li>
		                 <li>
		                    <a class="${student}" href="<c:url value="${baseUrl}/mm/students.html"/>"><i class="fa fa-list fa-fw"></i> Quản lý học viên</a>
		                </li>
						<li>
		                    <a class="${classes}" href="<c:url value="${baseUrl}/mm/classes.html"/>"><i class="fa fa-dashboard fa-fw"></i> Quản lý lớp học</a>
		                </li>
		                <li>
		                    <a class="${thesis}" href="<c:url value="${baseUrl}/mm/listStudentToAssignThesis.html"/>"><i class="fa fa-credit-card fa-fw"></i> Phân đề tài cao học</a>
		                </li>
		                
		                <li>
		                    <a class="${thesis}" href="<c:url value="${baseUrl}/mm/listThesis.html"/>"><i class="fa fa-book fa-fw"></i> Danh sách đề tài cao học</a>
		                </li>
		                
			            <li>
		                    <a class="${scheduling}" href="<c:url value="${baseUrl}/mm/defensesession.html"/>"><i class="fa fa-calendar-o fa-fw"></i> Quản lý đợt bảo vệ cao học</a>		                  
		                </li>
                      
                    </ul>
                    
                </li> --%>
               
	                 
                <%-- <li>
                    <a class="${departments}" href="#"><i class="fa fa-table fa-fw"></i> Quản lý Bộ môn</a>
                </li>
                <li>
                    <a class="${falcuties}" href="#"><i class="fa fa-edit fa-fw"></i> Quản lý Khoa/Viện</a>
                </li>
                <li>
                    <a class="${settings}" href="#"><i class="fa fa-wrench fa-fw"></i> Settings</a>
                </li> --%>
                
                <c:if test="${iMANAGESUMMARY eq 1}">
	                <%-- <li>
	                    <a class="${summary}" href="#"><i class="fa fa-wrench fa-fw"></i> Tổng hợp<span class="fa arrow"></span></a>
	                    <ul class="nav nav-second-level">
	                        <li>
	                            <a class="${summary}" href="<c:url value="${baseUrl}/cp/generate-excel-01cn-02cn.html"/>"><i class="fa fa-dashboard fa-fw"></i> Mẫu 01CN-02CN</a>
	                        </li>
	                        <c:if test="${currentUserRole eq 'ROLE_ADMIN' || currentUserRole eq 'SUPER_ADMIN'}">
		                        <li>
		                            <a class="${summary}" href="<c:url value="${baseUrl}/cp/summary.html"/>"><i class="fa fa-dashboard fa-fw"></i> Mẫu 01BM-02BM-03BM</a>
		                        </li>
		                        <li>
		                            <a class="${summary}" href="<c:url value="${baseUrl}/cp/summary-kv01.html"/>"><i class="fa fa-dashboard fa-fw"></i> Mẫu 01 KV</a>
		                        </li>
		                        <li>
		                            <a class="${summary}" href="<c:url value="${baseUrl}/cp/summary-isi02.html"/>"><i class="fa fa-dashboard fa-fw"></i> Mẫu 02 ISI</a>
		                        </li>
		                        <li>
		                            <a class="${summary}" href="<c:url value="${baseUrl}/cp/summary-kv03.html"/>"><i class="fa fa-dashboard fa-fw"></i> Mẫu 03 KV</a>
		                        </li>
		                        
		                        <!--  
		                        <li>
		                            <a class="${summary}" href="<c:url value="${baseUrl}/cp/summary-kv-list-papers.html"/>"><i class="fa fa-dashboard fa-fw"></i> Danh sách tất cả bài báo</a>
		                        </li>
	                        </c:if>
	                    </ul>
	                    <!-- /.nav-second-level -->
	                </li> --%>
                </c:if>
            </ul>
            
        </div>
        <!-- /.sidebar-collapse -->
    </div>
    <!-- /.navbar-static-side -->
    </c:if>
</nav>