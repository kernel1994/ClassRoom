<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生主页</title>
</head>
<body>
	<jsp:include page="/student/nav.jsp"></jsp:include>
	<h1>欢迎, ${sessionScope.user.nick }_${sessionScope.user.role.name}</h1>
	
	<jsp:include page="search_course.jsp"></jsp:include>

	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
	<script>
		function choose(obj, courseId, studentId) {
			$.post("chooseCourseAjax.stu", {courseId: courseId, studentId: studentId, temp: new Date()}, function (result) {
				
				if (result == "OK") {
					$(obj).html("选课成功").attr('disabled', true).removeAttr('onclick');
				} else {
					alert("发生某种错误");

				}
			});
		}

		function unchoose(obj, courseId, studentId) {
			$.post("unchooseCourseAjax.stu", {courseId: courseId, studentId: studentId, temp: new Date()}, function (result) {
				
				if (result == "OK") {
					$(obj).html("退选成功").attr('disabled', true).removeAttr('onclick');
				} else {
					alert("发生某种错误");

				}
			});
		}
	</script>
	
</body>
</html>