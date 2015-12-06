<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>课件管理</title>
    <script type="text/javascript">
	    function del(coursewareid) {
			if (window.confirm("确认删除")) {
				location.href = "${pageContext.request.contextPath }/servlet/CoursewareServlet?method=deleteCourseware&coursewareid="+coursewareid;
			}
		}
    </script>
     <jsp:include page="../head.jsp"></jsp:include>
  </head>
  
  <body style="background: #EEEEEE">
  	<div style="text-align: right;">
  		<a href="${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${courseId}">返回</a>
   		<a href="${pageContext.request.contextPath}/servlet/CoursewareServlet?method=addCoursewareUI">发布课件</a>
   	</div>
   	<div>
		<table width="100%"  frame="border">
			<tr>
	   			<td>课件名称</td>
	   			<td>课件描述</td>
	   			<td>上传时间</td>
	   			<td>操作</td>
	   		</tr>
		<c:forEach var="courseware" items="${requestScope.coursewares}">
				<tr>
					<td>${courseware.name}</td>
					<td>${courseware.description}</td>
					<td>${courseware.resource.uploadtime}</td>
					<td>
						<a href="javascript:void(0)" onclick="del('${courseware.id}')">删除</a>
					</td>
				</tr>
		</c:forEach>
	</table>
	</div>
  </body>
</html>
