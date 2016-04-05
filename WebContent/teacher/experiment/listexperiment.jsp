<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>实验管理</title>
    <script type="text/javascript">
	    function del(experimentid) {
			if (window.confirm("确认删除")) {
				location.href = "${pageContext.request.contextPath }/servlet/ExperimentServlet?method=deleteExperiment&experimentid="+experimentid;
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
  		<button type="button" class="btn btn-info" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${courseId}'">返回</button>
 		<button type="button" class="btn btn-info" onclick="location.href='${pageContext.request.contextPath}/servlet/ExperimentServlet?method=addExperimentUI'">发布实验</button>
   	</div>
   	
   	<div class="row">
   		
    	<c:forEach var="experiment" items="${requestScope.experiments}">
    	
        <div class="col-sm-4">
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title">${experiment.name}</h3>
            </div>
            <div class="panel-body">
               <div>
		          <table class="table">
		            <tbody>
		              <tr>
		                <td>说明</td>
		                <td>${experiment.description}</td>
		              </tr>
		              <tr>
		                <td>操作</td>
		                <td>
		                	<button type="button" class="btn btn-xs btn-success" onclick="del('${experiment.id}')">删除</button>
		                </td>
		              </tr>
		             
		            </tbody>
		          </table>
		        </div>
		        
            </div>
          </div>
          
        </div>
        </c:forEach>
        
      </div>
   	
	
	</div>
  </body>
</html>
