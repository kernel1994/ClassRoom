<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>章节</title>
</head>
<body>
    <jsp:include page="/course/nav.jsp"></jsp:include>

    <c:choose>
        <c:when test="${not empty requestScope.course.coursewares}">
            <ul>
                <c:forEach items="${requestScope.course.coursewares}" var="courseware">
                    <li><a href="javascript:void(0);">${courseware.name}</a></li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有章节</em>
        </c:otherwise>
    </c:choose>
</body>
</html>
