<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>老师管理主页</title>
     <link href="../css/bootstrap.min.css" rel="stylesheet">
     <style type="text/css">
     	#content {
     		margin: 50px;
     	}
     	.chip {
		    display: inline-block;
		    height: 32px;
		    font-size: 13px;
		    font-weight: 500;
		    color: rgba(0,0,0,0.6);
		    line-height: 32px;
		    padding: 0 12px;
		    border-radius: 16px;
		    background-color: #e4e4e4;
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
            <a href="${pageContext.request.contextPath}/servlet/ExamsServlet?method=listExams" class="list-group-item">题库</a>
            <a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=listTask" class="list-group-item">作业</a>
            <a href="${pageContext.request.contextPath}/servlet/CoursewareServlet?method=listCourseware" class="list-group-item">课件</a>
            <a href="${pageContext.request.contextPath}/servlet/ExperimentServlet?method=listExperiment" class="list-group-item">实验</a>
            <a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=scoreManager" class="list-group-item" class="list-group-item">成绩管理</a>
            <a href="#" class="list-group-item">其他</a>
          </div>
        </div>
        
        <div class="col-sm-4 col-md-offset-1">
    		 <h3 id="review">学生评论</h3>
	       	 <ul>
                    <c:forEach items="${requestScope.course.reviews}" var="review">
                        <li><div class="chip">${review.user.nick} ${review.time} <br/> ${review.content}</div></li>
                        <hr>
                    </c:forEach>
             </ul>
        
        </div>
	</div>
  	
  </body>
</html>
