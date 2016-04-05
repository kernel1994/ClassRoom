<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加实验</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <jsp:include page="../head.jsp"></jsp:include>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
  </head>
  
  <body>
    <h2 style="text-align: center;">课件上传</h2>
  	<div class="container">
	  	<div style="text-align: right;">
	  		<button type="button" class="btn btn-info" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${courseId}'">返回</button>
	   	</div>
	  
	  	 <form action="${pageContext.request.contextPath}/servlet/ExperimentServlet?method=addExperiment" method="post">
				<div class="panel-body">
	           
			          <table class="table">
			            <tbody>
			              <tr>
			                <td>主题</td>
			                <td><input type="text" name="name" value="" size="50%"></td>
			              </tr>
			              <tr>
			                <td>备注</td>
			                <td><textarea rows="2" cols="100%" name="description"></textarea><br/></td>
			              </tr>
			              <tr>
			                <td>输入输出</td>
			                <td>
			              		<input type="radio" name="flag" value="0" checked="checked">字符
			              		<input type="radio" name="flag" value="1">数字
							</td>
			              </tr>
			             
			              <tr>
			                <td><label for='input'>输入项目</label></td>
			              </tr>
			              <tr>
			                <td><input type="button" id="input" name="input" value="添加输入项"></td>
			              </tr>
			              
			              <tr>
			                <td><label for='output'>输出项</label></td>
			                
			              </tr>
			              <tr>
			                <td><input type="button" id="output" name="output" value="添加输出项"></td>
			              </tr>
						
						   <tr>
			                <td><input class="btn btn-info" type="submit" value="提交"/></td>
			              </tr>
			              
			            </tbody>
			          </table>
			      
			        
	            </div>
		</form>
			
	
	</div>
	
	  <script type="text/javascript">
  	$(function(){
  		//输入项
  		$("#input").click(function(){
  			var $input = $("#input");
  			var $div = $("<div class='input'><div>");
  			$input.before($div);
  			createInput();
  		});
  		
  		function createInput(){
  			var $temp = $("div[class='input']").last();
  			
  			$temp.append("<input name='input'/><br/>");
  			$temp.append("<input type='button' value='删除'/>");
  			
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  		}
  		
  		//输出项目
  		$("#output").click(function(){
  			var $output = $("#output");
  			var $div = $("<div class='output'><div>");
  			
  			$output.before($div);
  			createOutPut();
  		});
  		
  		function createOutPut(){
  			
  			var $temp = $(".output").last();
  
  			$temp.append("<input name='output'/><br/>");
  			$temp.append("<input type='button' value='删除'/>");
  		
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  		}
  	});
  </script>
  </body>
</html>
