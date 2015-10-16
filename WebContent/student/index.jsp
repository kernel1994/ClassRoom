<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
<title>学生主页</title>
</head>


<body>
	
	<script type="text/javascript">
		
			
			function choose(obj, courseId, studentId) {
			$.get("chooseCourseAjax.studentdo", {courseId: courseId, studentId: studentId, temp: new Date()}, function (result) {
				// 如果返回时 OK 则表示成功添加了喜欢。按钮状态应该改为 取消喜欢 。响应的click函数应该改为不喜欢
				if (result == "OK") {
					// 改变按钮文字
					obj.innerHTML = "退选";
					// 移除按钮事件监听器
					obj.removeEventListener('click', choose);
					// 添加新的监听
					obj.addEventListener('click', unchoose, false);
					// 在这里应该还需要设置旁边那个查看按钮的haveOwn状态。思路是获取父节点和兄弟节点来定位该元素，但是我不想改了。
				} else {
					alert("发送某种错误");
				}
			});
		}
		
		function unchoose(obj, courseId, studentId) {
			$.get("unchooseCourseAjax.studentdo", {courseId: courseId, studentId: studentId, temp: new Date()}, function (result) {
				// 如果返回时 OK 则表示成功添加了喜欢。按钮状态应该改为 取消喜欢 。响应的click函数应该改为不喜欢
				if (result == "OK") {
					// 改变按钮文字
					obj.innerHTML = "选课";
					// 移除按钮事件监听器
					obj.removeEventListener('click', unchoose);
					// 添加新的监听
					obj.addEventListener('click', choose, false);
					// 在这里应该还需要设置旁边那个查看按钮的haveOwn状态。思路是获取父节点和兄弟节点来定位该元素，但是我不想改了。
				} else {
					alert("发送某种错误");
				}
			});
		}
</script>
	<h1>欢迎, ${sessionScope.user.nick }_${sessionScope.user.role.name }</h1>
	<a href="${pageContext.request.contextPath }/servlet/logoutServlet">注销登录</a>
	
	<form action="queryCourse.studentdo" method="post">
		<input type="text" name="courseName" placeholder="课程名"><br>
		<input type="text" name="teacherName" placeholder="教师名"><br>
		<input type="text" name="limitperson" placeholder="限选人数"><br>
		<input type="text" name="description" placeholder="描述"><br>
		
		<input type="submit" value="查询"><br>
	</form>
	
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
	
	
</body>
</html>