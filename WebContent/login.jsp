<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用ClassRoom - 登录、注册或详细了解</title>
</head>
<script type="text/javascript">
	
</script>

<body>
	参考链接:
	<a href="https://www.facebook.com/">facebook</a>
	<hr />
	<!-- 登陆 -->
	<fieldset style="width: 50%;margin:10px;padding: 5px;">
		<legend>登陆</legend>
		<form action="loginServlet" method="post">
			邮箱: <input type="text" name="email" value="t_wang@cr.com" /> <br /> 
			密码: <input type="password" name=password value="123" /> <br />
			<input type="checkbox" name="loginstatus" />保持登陆状态<a href="">忘记密码</a> <br />
			<input type="submit" value="登陆">
		</form>

	</fieldset>

</body>
</html>