<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>作业</title>
</head>
<body>

<jsp:include page="/course/nav.jsp"></jsp:include>

<div class="container">
    <c:choose>
        <c:when test="${not empty requestScope.course.tasks}">
            <ul class="collection with-header">
                <li class="collection-header"><h4>课程作业</h4></li>
                <c:forEach items="${requestScope.course.tasks}" var="task">
                    <li>
                        <a href="doTask.stu?taskId=${task.id}" class="collection-item">${task.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有作业</em>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
