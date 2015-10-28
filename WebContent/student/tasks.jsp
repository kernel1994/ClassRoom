<%-- 学生所有作业页面 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的作业</title>
</head>
<body>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<c:choose>
    <c:when test="${not empty requestScope.courses}">
        <ul>
            <c:forEach items="${requestScope.courses}" var="course">
                <li>
                    <a href="viewCourseIndex.stu?courseId=${course.id}">${course.name}</a>
                    <c:choose>
                        <c:when test="${not empty course.tasks}">
                            <ul>
                                <c:forEach items="${course.tasks}" var="task">
                                    <li>
                                        <a href="doTask.stu?taskId=${task.id}">${task.name}</a>
                                        <c:choose>
                                            <c:when test="${not empty task.score}">
                                                ${task.score}分
                                            </c:when>
                                            <c:otherwise>
                                                赶快做！！
                                            </c:otherwise>
                                        </c:choose>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:when>
                        <c:otherwise>
                            <em>没有作业</em>
                        </c:otherwise>
                    </c:choose>
                </li>
            </c:forEach>
        </ul>
    </c:when>
    <c:otherwise>
        <em>没有选课，就没有作业</em>
    </c:otherwise>
</c:choose>

</body>
</html>
