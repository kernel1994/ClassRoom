<%--
  Created by IntelliJ IDEA.
  User: kernel
  Date: 2016/7/17
  Time: 9:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>查看公告</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/kernel-app.css">
</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<div class="container">
    <c:choose>
        <c:when test="${not empty requestScope.courses}">
            <ul class="collapsible collapsible-accordion" data-collapsible="expandable">
                <c:forEach items="${requestScope.courses}" var="course">
                    <li class="active">
                        <div class="collapsible-header active">
                            <h5>
                                <a href="viewCourseIndex.stu?courseId=${course.id}" class="orange-text">${course.name} 的公告</a>
                            </h5>
                        </div>
                        <c:choose>
                            <c:when test="${not empty course.announcements}">
                                <div class="collapsible-body">
                                    <div class="collection">
                                        <c:forEach items="${course.announcements}" var="announcement">
                                            <div class="collection-item">
                                                <span class='time blue lighten-3'>${announcement.time}</span>
                                                <span class="blue lighten-4">${announcement.content}</span>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="collapsible-body">
                                    <em>没有公告</em>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有选课，就没有公告</em>
        </c:otherwise>
    </c:choose>
</div>

<script>
    $(document).ready(function () {
        $('.collapsible').collapsible({
            accordion: false
        });
    });
</script>

</body>
</html>
