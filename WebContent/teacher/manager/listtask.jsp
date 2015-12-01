<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>作业管理</title>
     <script type="text/javascript">
	    function del(taskid) {
			if (window.confirm("确认删除")) {
				location.href = "${pageContext.request.contextPath }/servlet/TaskServlet?method=deleteTask&taskid="+taskid;
			}
		}
    </script>
     <jsp:include page="../head.jsp"></jsp:include>
  </head>
  
  <body style="background: #EEEEEE">
  	<div style="text-align: right;">
  		<a href="${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${course.id}">返回</a>
   		<a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=addTaskUI&courseId=${course.id}">发布作业</a>
   	</div>
   	<div>
		<table width="100%"  frame="border">
			<tr>
	   			<td>作业名称</td>
	   			<td>作业描述</td>
	   			<td>上传时间</td>
	   			<td>操作</td>
	   		</tr>
		<c:forEach var="task" items="${requestScope.tasks}">
				<tr>
					<td>${task.name}</td>
					<td>${task.description}</td>
					<td>${task.resource.uploadtime}</td>
					<td>
						<a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=checkTask&taskid=${task.id}">查看</a>
						<a href="#">修改</a>
						<a href="javascript:void(0)" onclick="del('${task.id}')">删除</a>
					</td>
				</tr>
		</c:forEach>
	</table>
	</div>
  </body>
</html>
