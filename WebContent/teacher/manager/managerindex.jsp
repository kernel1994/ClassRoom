<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>老师管理主页</title>
     <link href="../css/bootstrap.min.css" rel="stylesheet">
     <style type="text/css">
     	#content {
     		margin: 50px;
     	}
     </style>
  </head>
  
  <body>
 	<jsp:include page="../head.jsp"></jsp:include>
 	<div id="content">
 	 <div class="page-header">
        <h1>${course.name}</h1>
      </div>
 	 <div class="col-sm-4">
          <div class="list-group">
            <a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=listTask" class="list-group-item">作业</a>
            <a href="${pageContext.request.contextPath}/servlet/CoursewareServlet?method=listCourseware" class="list-group-item">课件</a>
            <a href="#" class="list-group-item">交流</a>
            <a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=scoreManager" class="list-group-item" class="list-group-item">成绩管理</a>
            <a href="#" class="list-group-item">其他</a>
          </div>
        </div><!-- /.col-sm-4 -->
	    <!-- 
	 	<div id="option">
	 		<span class="option_title">
	 			<a href="#">
	 				stream
	 			</a>
	 		</span>&nbsp;&nbsp;
	 		<span class="option_title">
	 			<a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=listTask">
	 				作业
	 			</a>
	 		</span>&nbsp;&nbsp;
	 		<span class="option_title">
	 			<a href="${pageContext.request.contextPath}/servlet/CoursewareServlet?method=listCourseware">
	 				课件
	 			</a>
	 		</span>&nbsp;&nbsp;
	 		<span class="option_title">
	 			<a href="#">
	 				关于
	 			</a>
	 		</span>
	 		
	 	</div>
	 	 -->
	</div>
  	
  </body>
</html>
