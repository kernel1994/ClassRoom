<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>题库</title>
    <jsp:include page="../head.jsp"></jsp:include>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
  	<style type="text/css">
  			#content {
      		margin-top: 20px;
      		margin-left: 50px;
      		margin-right: 50px;
      	}
  	</style>
  </head>
  
  <body>
  	<h2 style="text-align: center;">题库</h2>
  	<div id="content">
	  	<br/>
	  	<div style="text-align: right;">
	  		<button type="button" class="btn btn-info" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${courseId}'">返回</button>
	   	</div>
  
  		<ul class="list-group">
		  <li class="list-group-item"><a href="${pageContext.request.contextPath}/teacher/exams/addexam.jsp">添加题库</a></li>
		  <li class="list-group-item"><a href="${pageContext.request.contextPath}/teacher/exams/createexam.jsp">生成指定题库</a></li>
		</ul>
	
	</div>
  </body>
</html>
