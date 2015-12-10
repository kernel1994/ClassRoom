<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet"
      href="${pageContext.request.contextPath }/css/materialize.min.css"
      type="text/css" media="screen,projection"/>

<div class="navbar-fixed">
    <nav class="teal darken-1">
        <div class="nav-wrapper">
            <a href="${pageContext.request.contextPath}/servlet/logoutServlet" class="right">注销登录</a>
            <ul class="left hide-on-med-and-down">
                <li><a href="${pageContext.request.contextPath}/student/index.jsp">我的主页</a></li>
                <li><a href="viewCourseIndex.stu?courseId=${requestScope.course.id}">课程主页</a></li>
                <li><a href="viewCoursewares.stu?courseId=${requestScope.course.id}">课程章节</a></li>
                <li><a href="viewCourseTasks.stu?courseId=${requestScope.course.id}">课程作业</a></li>
                <%--<li><a href="javascript:void(0);">课程公告</a></li>--%>
            </ul>
        </div>
    </nav>
</div>

<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/materialize.min.js"></script>
