<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加角色界面</title>
  	<jsp:include page="../head.jsp"></jsp:include>
  	<link href="../css/bootstrap.min.css" rel="stylesheet">
  	<style type="text/css">
  		.col-md-6{
  			margin-left: 27%;
  		}
  	
  	</style>
  </head>
  
  <body>
  
    <h2 style="text-align: center;">修改课程</h2>
  
   	<form action="${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=update" method="post">
	   	<input type="hidden" name="id" value="${course.id}"> 
		   <div class="col-md-6">
				  <table class="table">
		   		<tr>
		   			<td>课程名称</td>
		   			<td>
		   				<input type="text" name="name" value="${course.name}">
		   			</td>
		   		</tr>
		   		<tr>
		   			<td>限选人数</td>
		   			<td>
		   				<input type="text" name="limitperson" value="${course.limitperson}">
		   			</td>
		   		</tr>
		   		<tr>
		   			<td>课程描述</td>
		   			<td>
						<textarea rows="5" cols="50" name="description">${course.description}</textarea>
		   			</td>
		   		</tr>
		   		
		   		<tr>
		   			<td>
						<input type="submit" class="btn btn-sm btn-info" value="确认修改课程">
		   			</td>
		   		</tr>
		   	</table>
		   	</div>
   	</form>
  </body>
</html>
