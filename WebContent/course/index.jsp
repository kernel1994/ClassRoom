<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>${requestScope.course.name}</title>
</head>
<body>
    <!-- 这个页面展示作业、公共、每节链接、评论 -->
    <jsp:include page="/course/nav.jsp"></jsp:include>
</body>
</html>
