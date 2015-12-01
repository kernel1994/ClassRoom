<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>列举老师课程</title>
    <script type="text/javascript">
	    function del(courseid) {
			if (window.confirm("确认删除")) {
				location.href = "${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=delete&courseid="+courseid ;
			}
		}
    
	    //在浏览器完成对象的装载后立即触发。
    	window.onload = function(){
    		
    	}
    </script>
  </head>
  <body style="background: #E8F2FE">
  	<br />
  	<br />
  	<center>
  		<table width="80%">
  			<tr>
  				<td></td>
  				<td></td>
  				<td align="right">
  					<a
  						href="${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=addUI">
  						添加课程
  					</a>
  				</td>
  			</tr>
  		</table>
  		<table width="80%" frame="border">
  			<tr>
  				<td>课程名称</td>
  				<td>课程描述</td>
  				<td>操作</td>
  			</tr>
  			<c:forEach var="course" items="${requestScope.courses}">
  				<tr>
  					<td>
  						<a
  							href="${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${course.id}">
  							${course.name }
  						</a>
  					</td>
  					<td>${course.description }</td>
  					<td>
  						<a href="${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=managerStudents&courseid=${course.id}">管理学生</a>
  						<a href="javascript:void(0)" onclick="del('${course.id}')">删除</a>
  						<a href="${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=updateUI&courseid=${course.id}">修改</a>
  					</td>
  				</tr>
  			</c:forEach>
  		</table>
  	</center>
  </body>
</html>
