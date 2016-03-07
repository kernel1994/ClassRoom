<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>列举老师课程</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
	
	<style>
	body{
		font: 120% "Trebuchet MS", sans-serif;
	}
	.demoHeaders {
		margin-top: 2em;
	}
	#dialog-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	#icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 4px;
	}
	.fakewindowcontain .ui-widget-overlay {
		position: absolute;
	}
	#content {
		margin-top: 20px;
		margin-left: 50px;
		margin-right: 50px;
	}
	.col-md-6 {
		margin-left: 10px;
	}
	</style>
    <script type="text/javascript">
	    function del(courseid) {
			if (window.confirm("确认删除")) {
				location.href = "${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=delete&courseid="+courseid ;
			}
		}
	    
	    function addcourse() {
	    	location.href = "${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=addUI";
	    }
    
	    //在浏览器完成对象的装载后立即触发。
    	window.onload = function(){
    		
    	}
    </script>
  </head>
  <body>
  	<div id="content">
  	
  	<div>
		<button class="btn btn-primary" id="button" onclick="addcourse()">增加课程</button>
	</div>
		
	<c:forEach var="course" items="${requestScope.courses}" varStatus="status">
			
				
				<!-- 显示内容 -->
				 <div class="page-header">
			        <h1>${course.name }</h1>
			      </div>
			      <div class="row">
					<div class="col-md-6">
				          <table class="table table-striped">
				            <tbody>
				              <tr>
				                <td>课程名称</td>
				                <td>
				                	<a
			  							href="${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${course.id}">
			  							${course.name }
			  						</a>
				                </td>
				              </tr>
				              <tr>
				                <td>限选人数</td>
				                <td>${course.limitperson }</td>
				              </tr>
				              <tr>
				                <td>课程描述</td>
				                <td>${course.description }</td>
				              </tr>
				              <tr>
				                <td>操作</td>
				                <td>
				                	<a href="${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=managerStudents&courseid=${course.id}">管理学生</a>
			  						<a href="javascript:void(0)" onclick="del('${course.id}')">删除</a>
			  						<a href="${pageContext.request.contextPath }/servlet/TeacherCourseServlet?method=updateUI&courseid=${course.id}">修改</a>
			  					</td>
				              </tr>
				            </tbody>
				          </table>
				        </div>
				       </div>
	</c:forEach>

		
	</div>
	
	
	
	<!-- 
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
  	
  	 -->
  

  </body>
</html>
