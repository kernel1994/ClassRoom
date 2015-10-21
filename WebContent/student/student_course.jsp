<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的选课</title>
</head>
<body>

  <jsp:include page="/student/comp_nav.jsp"></jsp:include>

  <jsp:include page="/student/comp_course_display.jsp"></jsp:include>

  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/choose-course.js"></script>

</body>
</html>
