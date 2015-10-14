<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>后台管理页面</title>
  </head>
  
    <frameset rows="12%,*">
  		<frame name="head" src="${pageContext.request.contextPath}/admin/manager/head.jsp">
  		<frameset cols="15%,*">
  			<frame name="left" src="${pageContext.request.contextPath}/admin/manager/left.jsp">
  			<frame name="main" src="">
  		</frameset>
  </frameset>
  
</html>
