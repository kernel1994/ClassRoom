<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>列举老师课程</title>
    <jsp:include page="../head.jsp"></jsp:include>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
      <style type="text/css">
      	#content {
      		margin-top: 20px;
      		margin-left: 50px;
      		margin-right: 50px;
      	}
      	.jumbotron {
		    padding-top: 5px;
		    padding-bottom: 2px;
		}
		.alert {
			width: 50%;
		    padding: 10px;
		    margin-bottom: 2px;
		    border: 1px solid transparent;
		    border-radius: 4px;
		}
		.h1, .h2, .h3, h1, h2, h3 {
		    margin-top: 5px;
		    margin-bottom: 2px;
		}
		#right {
			float: right;
		}
      </style>
  </head>
  <body>
  	<div id="content">
  	
  
        <h2>作业</h2>
        <div id="homework">
        	 <div id="right">
		      		<img alt="统计" src="${pageContext.request.contextPath }/servlet/MyChart?method=scoreChart&taskid=${task.id}">
		      </div>
	        <div id="left">
		        <div class="alert alert-success">
		       		 <strong>提交已经批改：</strong> ${requestScope.noNeedMarkGradeStudentCount}
		     	 </div>
		     	 <div class="alert alert-warning">
			        <strong>提交未批改：</strong> ${requestScope.needMarkGradeStudentCount}
			      </div>
			      <div class="alert alert-danger">
			        <strong>未提交：</strong> ${requestScope.notHaveFinishStudentCount}
			      </div>
		      </div>
	     </div>
	      
	       
	 <c:if test="${requestScope.noNeedMarkGradeStudentCount != 0}">
		 <div class="page-header">
	        <h1>已阅</h1>
	      </div>
	      <div class="row">
	        <div class="col-md-6">
	          <table class="table">
	            <thead>
	              <tr>
	                <th>#</th>
	                <th>姓名</th>
	                <th>成绩</th>
	              </tr>
	            </thead>
	            <tbody>
	             <c:forEach var="student" items="${requestScope.noNeedMarkGradeStudents}" varStatus="status">
	              <tr>
	                <td>${status.count}</td>
	                <td>${student.name}</td>
	                <td><a href="#">查看</a></td>
	              </tr>
	             </c:forEach>
	            </tbody>
	          </table>
	        </div>
	       </div>
	    </c:if>
	    
	<c:if test="${requestScope.needMarkGradeStudentCount!=0}">
		 <div class="page-header">
	        <h1>待阅</h1>
	      </div>
	      <div class="row">
	        <div class="col-md-6">
	          <table class="table">
	            <thead>
	              <tr>
	                <th>#</th>
	                <th>昵称</th>
	                <th>批改</th>
	              </tr>
	            </thead>
	            <tbody>
	             <c:forEach var="student" items="${requestScope.needMarkGradeStudents}" varStatus="status">
	              <tr>
	                <td>${status.count}</td>
	                <td>${student.name}</td>
	                <td>
						<a href="${pageContext.request.contextPath }/servlet/TaskServlet?method=markGrade&studentid=${student.id}&taskid=${requestScope.task.id}">批改</a>
					</td>
	              </tr>
	             </c:forEach>
	            </tbody>
	          </table>
	        </div>
	       </div>
	    </c:if>
     	 
  	
  	<c:if test="${requestScope.notHaveFinishStudentCount!=0}">
		 <div class="page-header">
	        <h1>未提交</h1>
	      </div>
	      <div class="row">
	        <div class="col-md-6">
	          <table class="table">
	            <thead>
	              <tr>
	                <th>#</th>
	                <th>昵称</th>
	                <th>提醒</th>
	              </tr>
	            </thead>
	            <tbody>
	             <c:forEach var="student" items="${requestScope.notHaveFinishStudents}" varStatus="status">
	              <tr>
	                <td>${status.count}</td>
	                <td>${student.name}</td>
	                <td>
						<a href="#">提醒</a>
					</td>
	              </tr>
	             </c:forEach>
	             <tr>
	              <td><a href="#">全部提醒</a></td>
	              </tr>
	             	
	            </tbody>
	          </table>
	        </div>
	       </div>
	    </c:if>
 
  	</div>
  </body>
</html>
