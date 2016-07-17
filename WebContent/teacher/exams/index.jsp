<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>发布作业</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <jsp:include page="../head.jsp"></jsp:include>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
  	<style type="text/css">
  			#content {
      		margin-top: 20px;
      		margin-left: 50px;
      		margin-right: 50px;
      	}
  	</style>
    <style type="text/css">
    	.select,.trueorfalse,.shortquestion, .generator{
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
  		var select_item = 1;
  		$("#task_select").click(function(){
  			var $task = $("#task_select");
  			var $div = $("<div class='select'><div>");
  			
  			$task.before($div);
  			createTaskSelect();
  		});
  		
  		function createTaskSelect(){
  			var $temp = $("div[class='select']").last();
  			
  			$temp.append("<input type='hidden' name='select_id' value='"+select_item+"'/>");
  			$temp.append("<label for='stitle'>题目：</label><textarea rows='1' cols='90%' name='stitle"+select_item+"'></textarea><br/>");
  			$temp.append("<label for='schapter'>章节：</label><select name='schapter"+select_item+"'><option value='1'>第一章</option><option value='2'>第二章</option><option value='3'>第三章</option><option value='4'>第四章</option><option value='5'>第五章</option><option value='6'>第六章</option><option value='7'>第七章</option><option value='8'>第八章</option><option value='9'>第九章</option><option value='10'>第十章</option></select><br/>");
  			$temp.append("<label for='sdegree'>难度：</label><select name='sdegree"+select_item+"'><option value='1'>简单</option><option value='2'>一般</option><option value='3'>困难</option></select><br/>");
  			$temp.append("<label for='sknowledge'>知识点：</label><input name='sknowledge"+select_item+"'/><br/>");
  			$temp.append("<label for='sscore'>分数：</label><input name='sscore"+select_item+"'/><br/>");
  			$temp.append("<input type='hidden' name='stypes"+select_item+"' value='1'/>");
  			$temp.append("<label for='sA'>A：</label><input name='sA"+select_item+"'/><br/>");
  			$temp.append("<label for='sB'>B：</label><input name='sB"+select_item+"'/><br/>");
  			$temp.append("<label for='sC'>C：</label><input name='sC"+select_item+"'/><br/>");
  			$temp.append("<label for='sD'>D：</label><input name='sD"+select_item+"'/><br/>");
  			$temp.append("<label for='sdescription'>解析：</label><textarea rows='1' cols='90%' name='sdescription"+select_item+"'></textarea><br/>");
  			$temp.append("<label for='sanswer'>答案：</label>&nbsp;");
  			$temp.append("<label for='sanswer'>A</label><input type='radio' name='sanswer"+select_item+"' value='A'/>&nbsp;");
  			$temp.append("<label for='sanswer'>B</label><input type='radio' name='sanswer"+select_item+"' value='B'/>&nbsp;");
  			$temp.append("<label for='sanswer'>C</label><input type='radio' name='sanswer"+select_item+"' value='C'/>&nbsp;");
  			$temp.append("<label for='sanswer'>D</label><input type='radio' name='sanswer"+select_item+"' value='D'/><br/>");
  			$temp.append("<input type='button' value='删除'/>");
  			
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  			select_item++;
  		}
  		
  		//判断题
  		$("#task_trueorfalse").click(function(){
  			var $task = $("#task_select");
  			var $div = $("<div class='trueorfalse'><div>");
  			
  			$task.before($div);
  			createTaskTrueOrFalse();
  		});
  		
  		function createTaskTrueOrFalse(){
  			var $temp = $(".trueorfalse").last();
  			$temp.append("<input type='hidden' name='trueorfalse_id' value='"+select_item+"'/>");
  			$temp.append("<label for='ttitle'>判断题：</label><textarea rows='1' cols='90%' name='ttitle"+select_item+"'></textarea><br/>");
  			
  			$temp.append("<label for='tchapter'>章节：</label><select name='tchapter"+select_item+"'><option value='1'>第一章</option><option value='2'>第二章</option><option value='3'>第三章</option><option value='4'>第四章</option><option value='5'>第五章</option><option value='6'>第六章</option><option value='7'>第七章</option><option value='8'>第八章</option><option value='9'>第九章</option><option value='10'>第十章</option></select><br/>");
  			$temp.append("<label for='tdegree'>难度：</label><select name='tdegree"+select_item+"'><option value='1'>简单</option><option value='2'>一般</option><option value='3'>困难</option></select><br/>");
  			$temp.append("<label for='tknowledge'>知识点：</label><input name='tknowledge"+select_item+"'/><br/>");
  			$temp.append("<label for='tscore'>分数：</label><input name='tscore"+select_item+"'/><br/>");
  			$temp.append("<input type='hidden' name='ttypes"+select_item+"' value='2'/>");
  			$temp.append("<label for='tdescription'>备注：</label><textarea rows='1' cols='90%' name='tdescription"+select_item+"'></textarea><br/>");
  			
  			$temp.append("<label for='tanswer'>答案：</label>&nbsp;");
  			$temp.append("<label for='tanswer'>对</label><input type='radio' name='tanswer"+select_item+"' value='true'/>&nbsp;");
  			$temp.append("<label for='tanswer'>错</label><input type='radio' name='tanswer"+select_item+"' value='false'/>&nbsp;");
  			$temp.append("<input type='button' value='删除'/>");
  			
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  			select_item++;
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
  			$temp.append("<label for='qtitle'>简答题：</label><textarea rows='1' cols='90%' name='qtitle'></textarea><br/>");
  			
  			$temp.append("<label for='qchapter'>章节：</label><select name='qchapter'><option value='1'>第一章</option><option value='2'>第二章</option><option value='3'>第三章</option><option value='4'>第四章</option><option value='5'>第五章</option><option value='6'>第六章</option><option value='7'>第七章</option><option value='8'>第八章</option><option value='9'>第九章</option><option value='10'>第十章</option></select><br/>");
  			$temp.append("<label for='qdegree'>难度：</label><select name='qdegree'><option value='1'>简单</option><option value='2'>一般</option><option value='3'>困难</option></select><br/>");
  			$temp.append("<label for='qknowledge'>知识点：</label><input name='qknowledge'/><br/>");
  			$temp.append("<label for='qscore'>分数：</label><input name='qscore'/><br/>");
  			$temp.append("<input type='hidden' name='qtypes' value='3'/>");
  			
  			$temp.append("<label for='qdescription'>备注：</label><textarea rows='1' cols='90%' name='qdescription'></textarea><br/>");
  			$temp.append("<input type='button' value='删除'/>");
  			
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  		}
  		
  		//作业描述信息封装task_generate
  		$("#task_generate").click(function(){
  			var $task = $("#task_select");
  			var $div = $("<div class='generator'><div>");
  			
  			$task.before($div);
  			createTaskGenerator();
  		});
  		
  		function createTaskGenerator(){
  			var $temp = $(".generator").last();
  			$temp.append("<label for='name'>主题：</label><input type='text' name='name' size='50%'><br/>");
  			$temp.append("<label for='description'>备注：</label><textarea rows='2' cols='100%' name='description'></textarea>&nbsp;&nbsp;&nbsp;");
  			$temp.append("<input type='hidden' name='flag' value='1'/>");
  			$temp.append("<input type='button' value='删除'/>");
  			
  			//添加事件按钮
  			$temp.children().last().click(function(){
  				$temp.empty();
  				$temp.remove();
  			});
  		}
  	});
  </script>
  
  <body>
  	<h2 style="text-align: center;">添加题库</h2>
  	<div id="content">
  	<br/>
  	<div style="text-align: right;">
  		<button type="button" class="btn btn-info" onclick="window.location.href='${pageContext.request.contextPath}/servlet/TaskServlet?method=listTask&courseId=${courseId}'">返回</button>
   	</div>
   
	    <fieldset style="margin:10px;padding: 2px 30px;" >
			<form action="${pageContext.request.contextPath}/servlet/ExamsServlet?method=addExams" method="post">
				<!-- 
					<label for="name">主题：</label>
					<input type="text" name="name" size="50%"><br/>
		
					<label for="description">备注：</label>
					<textarea rows="2" cols="100%" name="description"></textarea><br/>
				 -->
				<input type="button" id="task_select" name="task" value="添加单选">
				<input type="button" id="task_trueorfalse" name="task" value="添加判断">
				<input type="button" id="task_shortquestion" name="task" value="简答题"><br/>
				<input type="button" id="task_generate" name="task" value="生成作业"><br/>
				<input type="submit" value="提交"/>
			</form>
			
		</fieldset><br>
		
	</div>
  </body>
</html>
