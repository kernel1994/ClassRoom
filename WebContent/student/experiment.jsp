<%-- 学生做作业的页面 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.experiment.name}</title>
</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<div class="container">
    <h4>做练习</h4>
    <div>题目：${experiment.name }</div>
    <div>内容：${experiment.description }</div>
    <h4>检测数据</h4>
    <div>输入：${experiment.input }</div>
    <div>输出：${experiment.output }</div>
    
    
    <form action="${pageContext.request.contextPath}/servlet/StudentExperimentServlet?method=checkCode&experimentId=${experiment.id }" method="post" enctype="multipart/form-data">
			<div class="panel-body">
           
		          <table class="table">
		            <tbody>
		              <tr>
		              	<td>上传程序</td>
		              </tr>
		            
		              <tr>
		                <td>
		                	<input type="file" name="file" value="添加附件">
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
