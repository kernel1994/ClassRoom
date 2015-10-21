<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发布作业</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <jsp:include page="../head.jsp"></jsp:include>
    <style type="text/css">
    	.select,.trueorfalse,.shortquestion{
    		width: 70%;
    		margin-top: 15px;
    		padding: 2px 15px;
    		background: white;
    		border: 1px solid;
    	}
    	
    </style>
  </head>
  
  <script type="text/javascript">
  	$(function(){
  		//单选题
  		$("#task_select").click(function(){
  			var $task = $("#task_select");
  			var $div = $("<div class='select'><div>");
  			
  			$task.before($div);
  			createTaskSelect();
  		});
  		
  		function createTaskSelect(){
  			var $temp = $(".select").last();
  			$temp.append("<label for='title'>选择题：</label><textarea rows='1' cols='90%' name='title'></textarea><br/>");
  			$temp.append("<label for='A'>A：</label><input name='A'/><br/>");
  			$temp.append("<label for='B'>B：</label><input name='B'/><br/>");
  			$temp.append("<label for='C'>C：</label><input name='C'/><br/>");
  			$temp.append("<label for='D'>D：</label><input name='D'/><br/>");
  			$temp.append("<label for='description'>备注：</label><textarea rows='1' cols='90%' name='description'></textarea><br/>");
  			$temp.append("<label for='answer'>答案：</label><input name='answer'/>");
  			$temp.append("<input type='button' value='删除'/>");
  			
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  		}
  		
  		//判断题
  		$("#task_trueorfalse").click(function(){
  			var $task = $("#task_select");
  			var $div = $("<div class='trueorfalse'><div>");
  			
  			$task.before($div);
  			createTaskSelectTrueOrFalse();
  		});
  		
  		function createTaskSelectTrueOrFalse(){
  			var $temp = $(".trueorfalse").last();
  			$temp.append("<label for='title'>判断题：</label><textarea rows='1' cols='90%' name='title'></textarea><br/>");
  			$temp.append("<label for='description'>备注：</label><textarea rows='1' cols='90%' name='description'></textarea><br/>");
  			$temp.append("<label for='answer'>答案：</label>");
  			$temp.append("<label for='right'>对</label><input type='radio' name='answer' value='true'/>");
  			$temp.append("<label for='false'>错</label><input type='radio' name='answer' value='false'/>");
  			$temp.append("<input type='button' value='删除'/>");
  			
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  		}
  		//简答题task_shortquestion
  		$("#task_shortquestion").click(function(){
  			var $task = $("#task_select");
  			var $div = $("<div class='shortquestion'><div>");
  			
  			$task.before($div);
  			createTaskSelectShortQuestion();
  		});
  		
  		function createTaskSelectShortQuestion(){
  			var $temp = $(".shortquestion").last();
  			$temp.append("<label for='title'>简答题：</label><textarea rows='1' cols='90%' name='title'></textarea><br/>");
  			$temp.append("<label for='description'>备注：</label><textarea rows='1' cols='90%' name='description'></textarea><br/>");
  			$temp.append("<input type='button' value='删除'/>");
  			
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  		}
  	});
  </script>
  
  <body style="background: #EEEEEE">
  	<br/>
  	<div style="text-align: right;">
  		<a href="${pageContext.request.contextPath}/servlet/TeacherCoreServlet?method=coreIndexUI&id=${course.id}">返回</a>
  	</div>
	    <fieldset style="margin:10px;padding: 2px 30px;" >
			<legend >编辑作业</legend>
			<form action="${pageContext.request.contextPath}/servlet/TaskServlet?method=publishTask" method="post">
				<label for="name">主题：</label>
				<input type="text" name="name" size="50%"><br/>
	
				<label for="description">备注：</label>
				<textarea rows="2" cols="100%" name="description"></textarea><br/>
				<input type="button" id="task_select" name="task" value="添加单选">
				<input type="button" id="task_trueorfalse" name="task" value="添加判断">
				<input type="button" id="task_shortquestion" name="task" value="简答题"><br/>
				<input type="submit" value="提交"/>
			</form>
			
			<!-- <input type="button" name="courseware" value="添加附件">
			 -->
		</fieldset><br>
  </body>
</html>
