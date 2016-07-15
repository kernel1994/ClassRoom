<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>首页</title>
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <style type="text/css">
    	body {
    		padding:0px;
    		margin:0px;
    		background-color: #D8DCEA;
    		background-image: url(images/login_register_bg.png);
    		color: white;
    	}
    	
    	#head {
    		width:100%;
    		height:15%;
    		background-color: #3A5795;
    	}
    	#main {
    		width:100%;
    		height:85%;
    		color: black;
    	}
    	
    	#head1{
    		width:55%;
    		height:100px;
    		float: left;
    	}
    	#head2{
    		width:15%;
    		height:100%;
    		float: left;
    	}
    	#head3{
    		width:15%;
    		height:100%;
    		float: left;
    	}
    	#head4{
    	    margin-top: 52px;
    		width:5%;
    		height:100%;
    		float: left;
    	}
    	
    	#content1{
    		width:54%;
    		height:100%;
    		float: left;
    	}
    	#content2{
    		width:45%;
    		height:100%;
    		float: left;
    		
    	}
    	#title {
    		
    		font-family: sans-serif;
    		font-size: 36px;
    		margin-left: 25%;
    	}
    	
    	#loginBtn {
    		margin-top: 70px;
    	}
    	
    	#text1 {
    		font-family: monospace;
    		font-size: 24px;
    		margin-left: 26%;
    		margin-top: 5%;
    	}
    
    </style>
  </head>
  
  <body>
<<<<<<< HEAD
  	<div id="head">
  		<div id="head1">
  			<p id="title">教学辅助系统</p>
  		</div>
  		<form action="loginServlet" method="post">
	  		<div id="head2">
	  			<p id="emaillable">邮箱</p>
	  			<input type="text" name="email" id="email" value="xiaoming@cr.com" /><br/>
	  			<span id="loginInfo"></span>
	  		</div>
	  		<div id="head3">
	  			<p id="passwordlable">密码</p>
	  			<input type="password" name="password" id="password" value="123" />
	  		</div>
	  		<div id="head4">
	  			<a type="button" id="loginBtn" style="background-color:#eee;border:1px solid #d5d5d5;border-radius:3px;cursor:pointer;padding:5px 12px;">登录</a>
	  		</div>
	  	</form>
  		
  	</div>
  	
  	<div id="main">
  		<div id="content1">
  			<p id="text1">网上学习，方便快捷，尽在ClassRoom</p>
  		</div>
  		<div id="content2">
  			<p style="font-size: 36px;margin-top: 20px">注册</p>
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
				<label for="name">姓名: </label>
				<input id="name" name="name" />
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
  		</div>
  	</div>
	  
  </body>
  
  <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
	<script >
		$(function () {
			$("#loginBtn").click(function () {
				var email = $('#email').val();
				var password = $('#password').val();
				
				$.post("servlet/loginServlet", {email: email, password: password, temp: new Date()}, function (result) {
					if (result == "NO") {
						$("#loginInfo").html("<font color='red'>邮箱或密码错误</font>");
						$('#email,#password').css({
							"border" : "1px solid red"
						});
					} else {
						
						window.location.replace(result);
					}
				});
			});
		});
	</script>
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
			name: {
				required: true
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
			name: {
				required: "Please provide a name"
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
</html>
