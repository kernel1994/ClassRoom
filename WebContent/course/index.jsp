<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.course.name}</title>
    <style>
        .brand {
            /* 上下4px，左右0 */
            padding: 7px 5px;
            margin: 10px 10px;
            box-shadow: 0px 1px 0px rgba(255, 255, 255, 0.2) inset, 0px 1px 5px rgba(0, 0, 0, 0.35);
        }
    </style>
</head>
<body>
<!-- 这个页面展示作业、公共、每节链接、评论 -->
<jsp:include page="/course/nav.jsp"></jsp:include>

<div class="brand">
    <div>最新公告
        <span style="font-size: 4px;"><a href="javascript:void(0);">查看全部</a></span>
    </div>
    <c:choose>
        <c:when test="${not empty null}">
            <ul>
                <li><a href="javascript:void(0);">${notice.name}</a></li>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有公告</em>
        </c:otherwise>
    </c:choose>
</div>

<div class="brand">
    <div>最新作业
        <span style="font-size: 4px;"><a href="viewCourseTasks.stu?courseId=${requestScope.course.id}">查看全部</a></span>
    </div>
    <c:choose>
        <c:when test="${not empty requestScope.course.tasks}">
            <ul>
                <li><a href="javascript:void(0);">${requestScope.course.tasks[0].name}</a></li>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有作业</em>
        </c:otherwise>
    </c:choose>
</div>

<div class="brand">
    <div>最新章节
        <span style="font-size: 4px;"><a href="viewCoursewares.stu?courseId=${requestScope.course.id}">查看全部</a></span>
    </div>
    <c:choose>
        <c:when test="${not empty requestScope.course.coursewares}">
            <ul>
                <li><a href="javascript:void(0);">${requestScope.course.coursewares[0].name}</a></li>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有章节</em>
        </c:otherwise>
    </c:choose>
</div>

<div class="brand">
    <div>课程评论</div>
</div>

</body>
</html>
