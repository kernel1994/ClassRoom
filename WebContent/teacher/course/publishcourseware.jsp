<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>发布课件</title>
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
  	<h2 style="text-align: center;">课件上传</h2>
  	<div id="content">
  	<br/>
  	<div style="text-align: right;">
  		<button type="button" class="btn btn-info" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${courseId}'">返回</button>
   	</div>
  
  	 <form action="${pageContext.request.contextPath}/servlet/CoursewareServlet?method=publishCourseware" method="post" enctype="multipart/form-data">
			<div class="panel-body">
           
		          <table class="table">
		            <tbody>
		              <tr>
		                <td>主题</td>
		                <td><input type="text" name="name" value="${courseware.name}" size="50%"></td>
		              </tr>
		              <tr>
		                <td>备注</td>
		                <td><textarea rows="2" cols="100%" name="description">${courseware.description}</textarea><br/></td>
		              </tr>
		              <tr>
		                <td>添加附件</td>
		                <td>
		                	<input type="file" name="file" value="添加附件">${message}
		                </td>
		              </tr>
		              <tr>
		                <td><input class="btn btn-info" type="submit" value="提交"/></td>
		              </tr>
		             
		            </tbody>
		          </table>
		      
		        
            </div>
	</form>
		
	
	</div>
  </body>
</html>
