<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加角色界面</title>
  </head>
  
  <body>
   	<form action="${pageContext.request.contextPath }/servlet/RoleServlet?method=add" method="post">
   	<table>
   		<tr>
   			<td>角色名称</td>
   			<td>
   				<input type="text" name="name">
   			</td>
   		</tr>
   		<tr>
   			<td>角色描述</td>
   			<td>
				<textarea rows="5" cols="50" name="description"></textarea>
   			</td>
   		</tr>
   		
   		<tr>
   			<td></td>
   			<td>
				<input type="submit" value="添加角色">
   			</td>
   		</tr>
   	</table>
   	</form>
  </body>
</html>
