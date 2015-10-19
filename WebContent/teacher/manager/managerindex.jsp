<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>老师管理主页</title>
    <style type="text/css">
    	a:link {
    		text-decoration: none;
    	}
    	a:hover { 
			text-decoration: underline ;
		} 
    	body{
    		margin:0px;
    		padding: 0px;
    		width: 100%;
    	}
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
    		background: #D9F5FE;
    	}
    	
    	.option_title{
    		font-size:24px;
    		font-family : cursive;
    	}
    	#content_mod1{
    		width: 20%;
    		float: left;
    		margin-left: 5%;
    		margin-top: 10px;
    	}
    	
    	#content_mod2{
    		width: 59%;
    		height: 100%;
    		float: left;
    		margin-left: 5%;
    		margin-top: 10px;
    		
    		padding: 10px 3%;
    	}
    	#content_mod1_style1{
    		width: 100%;
    		height: 100px;
    		text-align: center;
    		background-color: #F6F6F6;
    	}
    	#content_mod1_style2{
    		width: 100%;
    		height: 100px;
    		text-align: center;
    		margin-top: 30px;
    		background-color: #F6F6F6;
    	}
    	#content_mod1_style3{
    		width: 100%;
    		height: 100px;
    		text-align: center;
    		margin-top: 30px;
    		background-color: #F6F6F6;
    	}
    	
    	#content_mod2_style1{
    		
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
 				stream	
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
 			<div id="content_mod1_style1">
 				<p>最新公告&nbsp;<a href="#">发布公告</a></p>
 				<p>暂无</p>
 				
 			</div>
 			<div id="content_mod1_style2">
 				<p>设置权限</p>
 				<font color="green">仅老师可回复<br/>(可设置为下拉或多选)</font>
 				<input type="checkbox" name="isTrue">
 			</div>
 			<div id="content_mod1_style3">
 				<p>课程要求码(为以后非本班人访问留扩展)</p>
 				<p>要求码:&nbps;<font color="red">J1fA</font></p>
 			</div>
 		</div>
 		<div id="content_mod2">
 			<div id="content_mod2_style1">
 				<a href="#">发布动态</a>
 			</div>
 		</div>
 	</div>
  	
  </body>
</html>
