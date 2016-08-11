<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发布作业</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <jsp:include page="../head.jsp"></jsp:include>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
  	<style type="text/css">
  			#content {
      		margin-top: 20px;
      		margin-left: 50px;
      		margin-right: 50px;
      	}
  	</style>
    <style type="text/css">
    	.select,.trueorfalse,.shortquestion{
    		width: 70%;
    		margin-top: 15px;
    		padding: 2px 15px;
    		background: white;
    		border: 1px solid;
    	}
    </style>
  </head>
  
  <body>
  	<h2 style="text-align: center;">发布作业</h2>
  	<div id="content">
  	<br/>
  	<div style="text-align: right;">
  		<button type="button" class="btn btn-info" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TaskServlet?method=listTask&courseId=${courseId}'">返回</button>
   	</div>
   
   		<div class="row">
   			<div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">发布作业</div>
			  <div class="panel-body">
			    <p>提示：有三种方式可以发布作业</p>
			     <p>1.直接生成题型</p>
			     <p>2.文件上传,如doc等</p>
			     <p>3.在线编辑</p>
			  </div>
			
			  <!-- List group -->
			  <ul class="list-group">
			   	<li class="list-group-item"><a href="${pageContext.request.contextPath }/teacher/exams/createexam.jsp">生成作业</a></li>
			   	<li class="list-group-item"><a href="${pageContext.request.contextPath }/teacher/course/addtask.jsp">上传作业</a></li>
			    <li class="list-group-item"><a href="${pageContext.request.contextPath }/servlet/ExamsServlet?method=listExams">编辑作业</a></li>
			  </ul>
			</div>
   		</div>
	   
		
	</div>
  </body>
</html>
