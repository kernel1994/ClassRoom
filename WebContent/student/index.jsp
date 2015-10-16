<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生主页</title>
</head>
<body>
	<h1>欢迎, ${sessionScope.user.nick }_${sessionScope.user.role.name }
		<font size="3px">
			<a href="${pageContext.request.contextPath }/servlet/logoutServlet">注销登录</a>
		</font>
	</h1>
	
	<form action="queryCourse.studentdo" method="post">
		<input type="text" name="courseName" placeholder="课程名"><br>
		<input type="text" name="teacherName" placeholder="教师名"><br>
		<input type="text" name="limitperson" placeholder="限选人数"><br>
		<input type="text" name="description" placeholder="描述"><br>
		
		<input type="submit" value="查询">
	</form>
	<a href="getMyCourses.studentdo">我的选课</a>
	
	<div>
		<c:if test="${not empty requestScope.courses }">
			<table>
				<thead>
					<tr>
						<th>课程</th>
						<th>教师</th>
						<th>限选人数</th>
						<th>描述</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${requestScope.courses }" var="course">
						<tr>
							<td>${course.name }</td>
							<td>${course.teacher.nick }</td>
							<td>${course.limitperson }</td>
							<td>${course.description }</td>
							<c:choose>
								<c:when test="${course.haveOwn == 1 }">
									<td><button onclick="unchoose(this, '${course.id }', '${sessionScope.user.id }')">退选</button></td>
								</c:when>

								<c:when test="${course.haveOwn == 0 }">
									<td><button onclick="choose(this, '${course.id }', '${sessionScope.user.id }')">选课</button></td>
								</c:when>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
	<script >
		function choose(obj, courseId, studentId) {
			$.post("chooseCourseAjax.studentdo", {courseId: courseId, studentId: studentId, temp: new Date()}, function (result) {
				
				if (result == "OK") {
					$(obj).html("选课成功").attr('disabled', true).removeAttr('onclick');
				} else {
					alert("发生某种错误");

				}
			});
		}

		function unchoose(obj, courseId, studentId) {
			$.post("unchooseCourseAjax.studentdo", {courseId: courseId, studentId: studentId, temp: new Date()}, function (result) {
				
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