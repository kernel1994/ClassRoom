<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>实验</title>
</head>
<body>

<jsp:include page="/course/nav.jsp"></jsp:include>

<div class="container">
    <c:choose>
        <c:when test="${not empty requestScope.course.experiments}">
            <ul class="collection with-header">
                <li class="collection-header"><h4>课程实验</h4></li>
                <c:forEach items="${requestScope.course.experiments}" var="experiment">
                    <li>
                        <a href="${pageContext.request.contextPath}/servlet/StudentExperimentServlet?method=doExperiment&experimentId=${experiment.id}" class="collection-item">${experiment.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有实验</em>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
