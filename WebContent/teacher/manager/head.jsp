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
		菜单
	</div>
	<div style="text-align: right;">
		${sessionScope.user.nick}_${sessionScope.user.role.name } 
		<a href="${pageContext.request.contextPath }/logoutServlet">注销登录</a> 
	</div>
</body>
</html>