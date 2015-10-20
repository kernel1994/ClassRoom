<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <nav>
        <a href="${pageContext.request.contextPath}/student/index.jsp">我的主页</a>
        <a href="viewCourseIndex.stu?courseId=${requestScope.course.id}">课程主页</a>
        <a href="viewCoursewares.stu?courseId=${requestScope.course.id}">课程章节</a>
        <a href="viewCourseTasks.stu?courseId=${requestScope.course.id}">课程作业</a>
        <a href="javascript:void(0);">课程公告</a>
        <a href="${pageContext.request.contextPath}/servlet/logoutServlet">注销登录</a>
    </nav>
</body>
</html>
