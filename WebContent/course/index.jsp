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
<div>最新公告</div>
</div>

<div class="brand">
    <div>最新作业</div>
    <c:if test="${not empty requestScope.course.tasks}">
        <ul>
            <c:forEach items="${requestScope.course.tasks}" var="task">
                <li><a href="javascript:void(0);">${task.name}</a></li>
            </c:forEach>
        </ul>
    </c:if>
</div>

<div class="brand">
<div>最新章节</div>
    <c:if test="${not empty requestScope.course.coursewares}">
        <ul>
            <c:forEach items="${requestScope.course.coursewares}" var="courseware">
                <li><a href="javascript:void(0);">${courseware.name}</a></li>
            </c:forEach>
        </ul>
    </c:if>
</div>

<div class="brand">
    <div>课程评论</div>
</div>

</body>
</html>
