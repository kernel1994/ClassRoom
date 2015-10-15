<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>列举老师课程</title>
  </head>
  	
 <body>
   	<br/>	<br/>
   	<center>
	   	<table width="80%">
	   		<tr>
	   			<td></td>
	   			<td></td>
	   			<td align="right">
	   				<a href="${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=addUI">添加课程</a>
	   			</td>
	   		</tr>
	   	</table>
   	
   	
	   	<table width="80%" frame="border">
	   		<tr>
	   			<td>课程名称</td>
	   			<td>课程描述</td>
	   			<td>操作</td>
	   		</tr>
	   		
	   		<c:forEach var="course" items="${requestScope.courses}">
	   			<tr>
		   			<td>
		   				<a href="#">${course.name }</a>
		   			</td>
		   			<td>${course.description }</td>
		   			<td>
		   				<a href="#">删除</a>
		   				<a href="#">修改</a>
		   			</td>
	   			</tr>
	   		</c:forEach>
	   	</table>
   	</center>
  </body>
</html>
