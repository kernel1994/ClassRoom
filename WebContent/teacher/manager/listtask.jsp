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
      <link href="../css/bootstrap.min.css" rel="stylesheet">
      <style type="text/css">
      	#content {
      		margin-top: 20px;
      		margin-left: 50px;
      		margin-right: 50px;
      	}
      </style>
  </head>
  
  <body>
  
  	<div id="content">
  	<div style="text-align: right;">
  		 <button type="button" class="btn btn-info" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${course.id}'">返回</button>
  		  <button type="button" class="btn btn-info" onclick="location.href='${pageContext.request.contextPath}/servlet/TaskServlet?method=addTaskUI&courseId=${course.id}'">发布作业</button>
   	</div>
   	
   	<div class="row">
   		
    	<c:forEach var="task" items="${requestScope.tasks}">
    	
        <div class="col-sm-4">
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${task.name}</h3>
            </div>
            <div class="panel-body">
               <div>
		          <table class="table">
		            <tbody>
		              <tr>
		                <td>说明</td>
		                <td>${task.description}</td>
		              </tr>
		              <tr>
		                <td>上传时间</td>
		                <td>${task.resource.uploadtime}</td>
		              </tr>
		              <tr>
		                <td>操作</td>
		                <td>
		                	<button type="button" class="btn btn-xs btn-success" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TaskServlet?method=taskProgress&taskid=${task.id}'">学生进展</button>
		                	<button type="button" class="btn btn-xs btn-success" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TaskServlet?method=checkTask&taskid=${task.id}'">查看</button>
		                	<button type="button" class="btn btn-xs btn-success">修改</button>
		                	<button type="button" class="btn btn-xs btn-success" onclick="del('${task.id}')">删除</button>
		                </td>
		              </tr>
		             
		            </tbody>
		          </table>
		        </div>
		        
            </div>
          </div>
          
        </div><!-- /.col-sm-4 -->
        </c:forEach>
        
      </div>
     
    <!-- 
   	
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
						<a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=taskProgress&taskid=${task.id}">学生进展</a>
						<a href="${pageContext.request.contextPath}/servlet/TaskServlet?method=checkTask&taskid=${task.id}">查看</a>
						<a href="#">修改</a>
						<a href="javascript:void(0)" onclick="del('${task.id}')">删除</a>
					</td>
				</tr>
		</c:forEach>
	</table>
	</div>
	 -->
	 
	</div>
  </body>
</html>
