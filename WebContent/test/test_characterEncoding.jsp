<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>测试CharacterEncodingFilter功能是否可行</title>
    
  </head>
  <body>
  	get方法提交 <br/>
    <form action="${pageContext.request.contextPath}/servlet/CharacterEncodingFilterTest">
    	<input type="text" name="username"><br/>
    	<input type="submit" name="提交">
    </form>
    
    <br/>post方法提交 <br/>
    <form action="${pageContext.request.contextPath}/servlet/CharacterEncodingFilterTest" method="post">
    	<input type="text" name="username"><br/>
    	<input type="submit" name="提交">
    </form>
    
    <font color="red">测试通过--测试数据: abc张三</font>
  </body>
</html>
