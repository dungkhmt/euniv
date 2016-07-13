<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="main_menu">

    <div class="main">
        <ul class="menu clearfix">
            <li <c:if test="${nav == 0}">class="active"</c:if>>
                <a href="<c:url value="index.html"/>">Users</a>
                <span class="arrow"></span>
            </li>
            <li <c:if test="${nav == 1}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/classes.html"/>">Classes</a>
                <span class="arrow"></span>
            </li>
            <li <c:if test="${nav == 2}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/teachers.html"/>">Teachers</a>
                <span class="arrow"></span>
            </li>
            <li <c:if test="${nav == 3}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/courses.html"/>">Courses</a>
                <span class="arrow"></span>
            </li>
<!--            <li <c:if test="${nav == 4}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/students.html"/>">Students</a>
                <span class="arrow"></span>
            </li>-->
            <li <c:if test="${nav == 5}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/rooms.html"/>">Rooms</a>
                <span class="arrow"></span>
            </li>
            <li <c:if test="${nav == 6}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/sessions.html"/>">Sessions</a>
                <span class="arrow"></span>
            </li>

            <li <c:if test="${nav == 8}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/coursing.html"/>">Coursing</a>
                <span class="arrow"></span>
            </li>

            <li <c:if test="${nav == 9}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/timetabling.html"/>">Timetabling</a>
                <span class="arrow"></span>
            </li>

            <li <c:if test="${nav == 10}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/viewing.html"/>">Viewing</a>
                <span class="arrow"></span>
            </li>
            <!--
            <li <c:if test="${nav == 10}">class="active"</c:if>>
                <a href="<c:url value="${baseUrl}/cp/testing.html"/>">Testing</a>
                <span class="arrow"></span>
            </li>
            -->
        </ul>
    </div>
</div>