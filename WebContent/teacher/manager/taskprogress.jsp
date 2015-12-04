<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>列举老师课程</title>
    <jsp:include page="../head.jsp"></jsp:include>
  </head>
  <body style="background: #E8F2FE">
  	
  	<center>
  		<p align="left">作业</p>
  		<table width="80%" frame="border">
  			<tr>
  				<td>作业名称</td>
  				<td>作业描述</td>
  				<td>作业完成情况完成</td>
  			</tr>
			<tr>
				<td>${requestScope.task.name}</td>
				<td>${requestScope.task.description}</td>
				<td>提交已经批改：${requestScope.noNeedMarkGradeStudentCount},提交未批改：${requestScope.needMarkGradeStudentCount},未提交：${requestScope.notHaveFinishStudentCount}</td>
			</tr>
  		</table>
  	</center>
  	
  	<center>
  		<p align="left">完成</p>
  		<table width="80%" frame="border">
  			<tr>
  				<td>昵称</td>
  				<td>批改</td>
  			</tr>
		
				<c:forEach var="student" items="${requestScope.noNeedMarkGradeStudents}">
						<tr>
					<td>${student.name}</td>
  					<td>
  						<a href="#">查看</a>
  					</td>
  					</tr>
				</c:forEach>
			<tr>
				<td colspan="2"><a href="#">统计已提交作业</a></td>
			</tr>
  		</table>
  	</center>
  	
  	<center>
  		<p align="left">完成</p>
  		<table width="80%" frame="border">
  			<tr>
  				<td>昵称</td>
  				<td>批改</td>
  			</tr>
		
				<c:forEach var="student" items="${requestScope.needMarkGradeStudents}">
						<tr>
					<td>${student.name}</td>
  					<td>
  						<a href="${pageContext.request.contextPath }/servlet/TaskServlet?method=markGrade&studentId=${student.id}&taskId=${requestScope.task.id}">批改</a>
  					</td>
  					</tr>
				</c:forEach>
			
  		</table>
  	</center>
  
  <center>
  		<p align="left">未提交</p>
  		<table width="80%" frame="border">
  			<tr>
  				<td>昵称</td>
  				<td>提醒</td>
  			</tr>
			
			<c:forEach var="student" items="${requestScope.notHaveFinishStudents}">
				<tr>
					<td>${student.name}</td>
  					<td>
  						<a href="#">提醒</a>
  					</td>
  				</tr>
			</c:forEach>
			
			<tr>
				<td colspan="2"><a href="#">全部提醒</a></td>
			</tr>
  		</table>
  	</center>
  </body>
</html>
