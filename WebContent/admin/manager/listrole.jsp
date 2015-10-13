<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>角色列表</title>
  </head>
  
  <body style="text-align: center;">
   	<br/>	<br/>
   	
   	<table width="80%">
   		<tr>
   			<td></td>
   			<td></td>
   			<td align="right">
   				<a href="${pageContext.request.contextPath }/servlet/RoleServlet?method=addUI">添加角色</a>
   			</td>
   		</tr>
   	</table>
   	<table  style="text-align: center;" width="80%" frame="border">
   		<tr>
   			<td>角色名称</td>
   			<td>角色描述</td>
   			<td>操作</td>
   		</tr>
   		
   		<c:forEach var="role" items="${roles}">
   			<tr>
	   			<td>${role.name }</td>
	   			<td>${role.description }</td>
	   			<td>
	   				<a href="${pageContext.request.contextPath }/servlet/RoleServlet?method=forUpdateRolePrivilegeUI&id=${role.id }">为角色授予权限</a>
	   				<a href="#">删除</a>
	   				<a href="#">修改</a>
	   			</td>
   			</tr>
   		</c:forEach>
   	</table>
   	
  </body>
</html>
