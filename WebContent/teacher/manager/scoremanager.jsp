<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>成绩管理统计</title>
     <link href="../css/bootstrap.min.css" rel="stylesheet">
     <style type="text/css">
     	#content {
     		margin-top: 20px;
     		margin-left: 50px;
     		margin-right: 50px;
     	}
     </style>
  </head>
  
  <body>
 	<jsp:include page="../head.jsp"></jsp:include>
 	<div id="content">
 		<c:forEach var="task" items="${tasks}">
 			<div class="page-header">
		        <h1>${task.name }</h1>
		    </div>
		    <img alt="统计" src="${pageContext.request.contextPath }/servlet/MyChart?method=scoreChart2&taskid=${task.id}">
 		
 		</c:forEach>
	
	
  	</div>
  </body>
</html>
