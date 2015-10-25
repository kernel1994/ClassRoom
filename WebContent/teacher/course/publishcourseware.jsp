<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>发布课件</title>
    <jsp:include page="../head.jsp"></jsp:include>
  </head>
  
  <body style="background: #EEEEEE">
  	<br/>
  	<div style="text-align: right;">
  		<a href="${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${courseId}">返回</a>
  	</div>
  
    <fieldset style="margin:10px;padding: 2px 30px;" >
		<legend >编辑课件</legend>
			<form action="${pageContext.request.contextPath}/servlet/CoursewareServlet?method=publishCourseware" method="post" enctype="multipart/form-data">
				<label for="name">主题：</label>
				<input type="text" name="name" value="${courseware.name}" size="50%"><br/>

				<label for="description">备注：</label>
				<textarea rows="2" cols="100%" name="description">${courseware.description}</textarea><br/>
		
				添加附件：<input type="file" name="file" value="添加附件">${message}
				<input type="submit" value="提交"/>
		</form>
		
	</fieldset><br>
  </body>
</html>
