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
  
  <h2 style="text-align: center;">添加课程</h2>
  
  <div id="content">
   	<form action="${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=add" method="post">
	   	
		   	<div class="col-md-6">
				  <table class="table">
				        <tbody>
	
		   		<tr>
		   			<td>课程名称</td>
		   			<td>
		   				<input type="text" name="name">
		   			</td>
		   		</tr>
		   		<tr>
		   			<td>限选人数</td>
		   			<td>
		   				<input type="text" name="limitperson">
		   			</td>
		   		</tr>
		   		<tr>
		   			<td>课程描述</td>
		   			<td>
						<textarea rows="5" cols="50" name="description"></textarea>
		   			</td>
		   		</tr>
		   		
		   		<tr>
		   			<td></td>
		   			<td>
						<input type="submit"  class="btn btn-sm btn-info" value="添加课程">
		   			</td>
		   		</tr>
		   		</tbody>
		   	</table>
		   	
		   	</div>
   	</form>
   	
   	</div>
  </body>
</html>
