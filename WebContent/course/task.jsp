<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>作业</title>
</head>
<body>
    <jsp:include page="/course/nav.jsp"></jsp:include>

    <c:choose>
        <c:when test="${not empty requestScope.course.tasks}">
            <ul>
                <c:forEach items="${requestScope.course.tasks}" var="task">
                    <li><a href="javascript:void(0);">${task.name}</a></li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有作业</em>
        </c:otherwise>
    </c:choose>
</body>
</html>
