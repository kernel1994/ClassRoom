<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>选课</title>
</head>
<body>

  <jsp:include page="/student/comp_nav.jsp"></jsp:include>

  <jsp:include page="/student/comp_search_form.jsp"></jsp:include>
  <jsp:include page="/student/comp_course_display.jsp"></jsp:include>

  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/choose-course.js"></script>

</body>
</html>
