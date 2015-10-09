<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用ClassRoom - 登录、注册或详细了解</title>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<script type="text/javascript">
	$().ready(function(){
		//文档加载完毕后
		//1.填充生日需要的数据
		//年
		$("#year").append($("<option></option>"));
		$("#year").append($("<option>年</option>"));
		for(var i=new Date().getFullYear();i > new Date().getFullYear() - 150;i--){
			var $optionElement = $("<option value='"+i+"'>"+i+"</option>");
			$("#year").append($optionElement);
		}
		//月
		$("#month").append($("<option></option>"));
		$("#month").append($("<option>月</option>"));
		for(var i=1;i <= 12;i++){
			var $optionElement = $("<option value='"+i+"'>"+i+"</option>");
			$("#month").append($optionElement);
		}
		//日
		$("#day").append($("<option></option>"));
		$("#day").append($("<option>日</option>"));
		for(var i=1;i <= 31;i++){
			var $optionElement = $("<option value='"+i+"'>"+i+"</option>");
			$("#day").append($optionElement);
		}
		
		//2.异步注册用户名称
		$("#nick").blur( function () { 
			$.post("${pageContext.request.contextPath}/servlet/CheckUserServlet?time="+new Date().getTime(),{nick:$(this).val()},function(data,textStatus){
				$("#nickspan").html(data);
				$("#nickspan").css("color", "blue");
			});
		} );
		
		//3.其余属性校验规则
		$("#form").validate({
		rules: {
			password: {
				required: true,
				minlength: 5
			},
			confirm_password: {
				required: true,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			gender: {
				required: true
			},
			year:{
				required: true
			},
			month:{
				required: true
			},
			day:{
				required: true
			}
		},
		messages: {
			password: {
				required: "Please provide a password",
				minlength: "Your password must be at least 5 characters long"
			},
			confirm_password: {
				required: "Please provide a password",
				equalTo: "Please enter the same password as above"
			},
			email: {
				required: "Please provide a email",
				email: "Please enter a valid email address"
			},
			year:{
				required: "Please provide a year"
			},
			month:{
				required: "Please provide a month"
			},
			day:{
				required: "Please provide a day"
			}
		}
		});
	});
</script>

<body>
	参考链接:
	<a
		href="https://www.facebook.comu">facebook</a>
	<hr />

	<br />
	<!-- 注册 -->
	<fieldset style="width: 50%;margin:10px;padding: 5px;">
		<legend>注册</legend>
		<form id="form" action="${pageContext.request.contextPath}/servlet/RegisterServlet" method="post">
			<p>
				<label for="nick">昵称:</label>
				<input name="nick" id="nick" />
				<span id="nickspan"></span> 
			</p>
			<p>
				<label for="password">密码:</label>
				<input id="password" name="password" type="password" />
			</p>
			<p>
				<label for="confirm_password">确认密码:</label>
				<input id="confirm_password" name="confirm_password" type="password" />
			</p>
			<p>
				<label for="email">邮箱: </label>
				<input id="email" name="email" />
			</p>
			<p>
				<label for="birthday">生日: </label><br/>
				<select style="width: 80px" name="year" id="year"></select> 
				<select style="width: 80px" name="month" id="month"></select> 
				<select style="width: 80px" name="day" id="day"></select><br /> 
			</p>
			
			<p>
				<label for="gender">性别: </label><br/>
				<label for="male">男</label>
					<input type="radio" name="gender" value="male">
				<label for="female">女</label>
					<input type="radio" name="gender" value="female">
			</p>
			<p>
				点击注册，即表示你同意我们的条款，且已阅读过我们的<a href="">《使用政策》</a>，包括《Cookie 使用政策》。
			</p>
			<p>
				<input type="submit" value="注册">
			</p>
		</form>
	</fieldset>
	<br />
</body>
</html>