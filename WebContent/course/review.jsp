<%--
  Created by IntelliJ IDEA.
  User: kernel
  Date: 2016/9/20
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程留言板</title>
</head>
<body>
<jsp:include page="/course/nav.jsp"></jsp:include>

<div class="container">
    <form action="addCourseReview.rev" method="post" style="margin-top: 20px;">
        <input type="hidden" name="courseID" value="${requestScope.course.id}">
        <textarea name="review_content"></textarea><br/>
        <button type="submit" class="btn waves-effect waves-light">发表评论</button>
    </form>
    <c:choose>
        <c:when test="${not empty requestScope.course.reviews}">
            <ul>
                <c:forEach items="${requestScope.course.reviews}" var="review">
                    <li>
                        <div class="chip">${review.user.nick} ${review.time} <br/> ${review.content}</div>
                    </li>
                    <hr>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有评论</em>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
