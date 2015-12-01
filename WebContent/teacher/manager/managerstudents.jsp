<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理学生</title>
<jsp:include page="../head.jsp"></jsp:include>
</head>
    
<body>
	<br/>
	<br/>
	<center>
  		<table width="80%" frame="border">
  			<tr>
  				<td>学生姓名</td>
  				<td>邮箱</td>
  				<td>学习情况</td>
  			</tr>
  			<c:forEach var="student" items="${requestScope.students}">
  				<tr>
  					<td>
  						<a
  							href="#">
  							${student.name }
  						</a>
  					</td>
  					<td>
  					    ${student.email }&nbsp;
  						<a href="#">联系</a>
  					</td>
  					<td>
  						<a href="#">学习情况</a>
  					</td>
  				</tr>
  			</c:forEach>
  		</table>
  	</center>
</body>
</html>