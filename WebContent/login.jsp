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
		<form action="" method="post">
			邮箱: <input type="text" name="email" id="email" value="t_wang@cr.com" /> <span id="loginInfo"></span> <br /> 
			密码: <input type="password" name="password" id="password" value="123" /> <br />
			<input type="checkbox" name="loginstatus" />保持登陆状态<a href="">忘记密码</a> <br /><br />
			<a type="button" id="loginBtn" style="background-color:#eee;border:1px solid #d5d5d5;border-radius:3px;cursor:pointer;padding:5px 12px;">登录</a>
		</form>
	</fieldset>

	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
	<script >
		$(function () {
			$("#loginBtn").click(function () {
				var email = $('#email').val();
				var password = $('#password').val();
				
				$.get("loginServlet", {email: email, password: password}, function (result) {
					if (result == "NO") {
						$("#loginInfo").html("<font color='red'>邮箱或密码错误</font>");
					} else {
						window.location.replace(result);
					}
				});
			});
		});
	</script>
</body>
</html>