<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/blog.css" rel="stylesheet">
 <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<title>教师主页</title>
</head>
<body>
	<div class="blog-masthead">
      <div class="container">
        <nav class="blog-nav">
          <a class="blog-nav-item active" href="${pageContext.request.contextPath}/servlet/TeacherIndexServlet?method=getAll">教师主页</a>
          <a class="blog-nav-item" href="#">${sessionScope.user.nick}_${sessionScope.user.role.name } </a>
          <a class="blog-nav-item" href="#">关于</a>
          <a class="blog-nav-item" href="${pageContext.request.contextPath }/servlet/logoutServlet">注销登录</a>
        </nav>
      </div>
    </div>
</body>
</html>