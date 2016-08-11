<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>发布课件</title>
    <jsp:include page="../head.jsp"></jsp:include>
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
  	<style type="text/css">
  			#content {
      		margin-top: 20px;
      		margin-left: 50px;
      		margin-right: 50px;
      	}
  	</style>
  </head>
  
  <body>
  	<h2 style="text-align: center;">指定题目生成</h2>
  	<div id="content">
  	<br/>
  	<div style="text-align: right;">
  		<button type="button" class="btn btn-info" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${courseId}'">返回</button>
   	</div>
  
  	 <form action="${pageContext.request.contextPath}/servlet/ExamsServlet?method=createExam" method="post">
			<div class="panel-body">
           
		          <table class="table">
		            <tbody>
		            <tr>
		                <td>主题</td>
		                <td><input type="text" name="name" value="${task.name}" size="50%"></td>
		              </tr>
		              <tr>
		                <td>备注</td>
		                <td><textarea rows="2" cols="100%" name="description">${task.description}</textarea><br/></td>
		              </tr>
		              <tr>
		                <td>章节</td>
		                <td>
							<select name="chapter">
							<option value ="0">请选择操作</option>
							  <option value ="1">第一章</option>
							  <option value ="2">第二章</option>
							  <option value="3">第三章</option>
							  <option value="4">第四章</option>
							</select>
						</td>
		              </tr>
		              <tr>
		                <td>难易程度</td>
		                <td>
							<select name="degree">
							<option value ="0">请选择操作</option>
							  <option value ="1">简单</option>
							  <option value ="2">适中</option>
							  <option value="3">困难</option>
							</select>
						</td>
		              </tr>
		              <tr>
		                <td>知识点</td>
		                <td>
		                	<input type="text" name="knowledgepoint">
		                </td>
		              </tr>
		              <tr>
		                <td>题目类型</td>
		                <td>
		                	<select name="type">
		                		<option value ="0">请选择操作</option>
							  <option value ="1">选择</option>
							  <option value ="2">判断</option>
							  <option value="3">问答</option>
							</select>
		                </td>
		              </tr>
		              <tr>
		                <td>题目数量</td>
		                <td>
		                	<input type="text" name="examcount">
		                </td>
		              </tr>
		              
		              <tr>
		                <td><input class="btn btn-info" type="submit" value="生成"/></td>
		              </tr>
		             
		            </tbody>
		          </table>
		      
		        
            </div>
	</form>
		
	
	</div>
  </body>
</html>
