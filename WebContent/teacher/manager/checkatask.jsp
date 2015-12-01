<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看作业</title>
     <jsp:include page="../head.jsp"></jsp:include>
  </head>
  
  <body>
    <div style="text-align: right;">
  		<a href="/ClassRoom/servlet/TaskServlet?method=listTask">返回</a>
   	</div>
   	<div>
   		<p>一、选择</p>
   		<c:forEach var="select" items="${homeWork.selects}" varStatus="status">
   			<dl>
   				<dt>${status.count}.${select.title}</dt>
   				<dd>(A)&nbsp;${select.a}</dd>
   				<dd>(B)&nbsp;${select.b}</dd>
   				<dd>(C)&nbsp;${select.c}</dd>
   				<dd>(D)&nbsp;${select.d}</dd>
   				<dt>答案: ${select.answer }</dt>
   				<dt>备注: ${select.description}</dt>
			</dl>
   		</c:forEach>
   		
   		<p>二、选择</p>
   		<c:forEach var="trueorfalse" items="${homeWork.trueOrFalses}" varStatus="status">
   			<dl>
   				<dt>${status.count}.${trueorfalse.title}</dt>
   				<dt>答案: 
   					<c:if test="${trueorfalse.answer=='true'}">对</c:if>
   					<c:if test="${trueorfalse.answer=='false'}">错</c:if>
   				<dt>备注: ${trueorfalse.description}</dt>
			</dl>
   		</c:forEach>
   		
   		<p>三、简单</p>
   		<c:forEach var="shortquestion" items="${homeWork.shortQuestions}" varStatus="status">
   			<dl>
   				<dt>${status.count}.${shortquestion.title}</dt>
   				<dt>备注: ${shortquestion.description}</dt>
			</dl>
   		</c:forEach>
   	</div>
  </body>
</html>
