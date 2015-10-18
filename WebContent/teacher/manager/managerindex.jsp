<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>老师管理主页</title>
    <style type="text/css">
    	div#title{
    		width: 100%;
    		height: 120px;
    		text-align: center;
    		line-height: 30px;
    	}
    	div#option{
    		width: 100%;
    		height: 50px;
    		text-align: center;
    		line-height: 50px;
    		background: #8DC5FE;
    	}
    	
    	.option_title{
    		font-size:24px;
    		font-family : cursive;
    	}
    	
    	
    	div#content{
    		width: 100%;
    		height: 70%;
    		background: #E1E9EB;
    	}
    	
    	#content_mod1{
    		width: 40%;
    		height: 100%;
    		border: 1px red solid;
    	}
    	
    	
    	
    	a:link {
    		text-decoration: none;
    	}
    	a:hover { 
			text-decoration: underline ;
		} 
    </style>
  </head>
  
  <body>
 	<div id="title">
 		<jsp:include page="../head.jsp"></jsp:include>
 		<div style="font-size:32px;">
 			${course.name}
 		</div>
 			
 		<div>
 			<a href="#">${sessionScope.user.nick}</a>&nbsp;
 			<a href="#">消息</a>
 		</div>
 	</div>	
 	
 	<div id="option">
 		<span class="option_title">
 			<a href="#">
 				通知
 			</a>
 		</span>&nbsp;&nbsp;
 		<span class="option_title">
 			<a href="#">
 				作业
 			</a>
 		</span>&nbsp;&nbsp;
 		<span class="option_title">
 			<a href="#">
 				课件
 			</a>
 		</span>&nbsp;&nbsp;
 		<span class="option_title">
 			<a href="#">
 				关于
 			</a>
 		</span>
 	</div>
 	<div id="content">
 		<div id="content_mod1">
 		
 		</div>
 		<div id="content_mod2">
 		
 		</div>
 	</div>
  	
  </body>
</html>
