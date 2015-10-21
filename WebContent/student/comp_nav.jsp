<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav>
    <a href="createIndex.stu">我的主页</a>
    <a href="${pageContext.request.contextPath}/student/choose_course.jsp">选课/退选</a>
    <a href="getStudentCourses.stu">我的课程</a>
    <a href="viewStudentAllCoursesTasks.stu">我的作业</a>
    <a href="javascript:void(0);">查看公告</a>
    <a href="${pageContext.request.contextPath}/servlet/logoutServlet">注销登录</a>
</nav>
