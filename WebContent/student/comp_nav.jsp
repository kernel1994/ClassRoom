<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet"
      href="${pageContext.request.contextPath }/css/materialize.min.css"
      type="text/css" media="screen,projection"/>

<div class="navbar-fixed">
    <nav class="teal darken-1">
        <div class="nav-wrapper">
            <a href="${pageContext.request.contextPath}/servlet/logoutServlet" class="right">注销登录</a>
            <ul class="left hide-on-med-and-down">
                <li><a href="createIndex.stu">我的主页</a></li>
                <li><a href="${pageContext.request.contextPath}/student/choose_course.jsp">选课/退选</a></li>
                <li><a href="getStudentCourses.stu">我的课程</a></li>
                <li><a href="viewStudentAllCoursesTasks.stu">我的作业</a></li>
                
                <li><a href="${pageContext.request.contextPath}/servlet/StudentExperimentServlet?method=getAddCourseExperiments">我的实验</a></li>
                <%--<li><a href="javascript:void(0);">查看公告</a></li>--%>
            </ul>
        </div>
    </nav>
</div>

<script type="text/javascript"
        src="${pageContext.request.contextPath }/js/materialize.min.js"></script>