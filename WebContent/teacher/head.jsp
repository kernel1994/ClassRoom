<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教师主页</title>
</head>
<body>
	<div style=" float: left">
		<a href="${pageContext.request.contextPath}/servlet/TeacherIndexServlet?method=getAll">教师主页</a>
	</div>
	<div style="text-align: right;">
		${sessionScope.user.nick}_${sessionScope.user.role.name } 
		<a href="${pageContext.request.contextPath }/servlet/logoutServlet">注销登录</a> 
	</div>
</body>
</html>