<%--
  Created by IntelliJ IDEA.
  User: kernel
  Date: 2015/10/29
  Time: 15:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>查看作业</title>
</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<c:if test="${not empty requestScope.homeWork}">
    <ul>
        <%-- 单选题 --%>
      <c:forEach items="${requestScope.homeWork.selects}" var="select">
        <li>
          <script>
            $(function() {
              setStudentAnswer('${select.id}', '${select.stuAnswer}', '${select.answer}');
            });
          </script>
            ${select.title} <span id="${select.id}Info"></span><br/>
          <input type="radio" name="${select.id}" value="A" />A. ${select.a}<br/>
          <input type="radio" name="${select.id}" value="B" />B. ${select.b}<br/>
          <input type="radio" name="${select.id}" value="C" />C. ${select.c}<br/>
          <input type="radio" name="${select.id}" value="D" />D. ${select.d}<br/>
        </li>
      </c:forEach>

        <%-- 判断题 --%>
      <c:forEach items="${requestScope.homeWork.trueOrFalses}" var="trueOrFalse">
        <li>
          <script>
            $(function() {
              setStudentAnswer('${trueOrFalse.id}', '${trueOrFalse.stuAnswer}', '${trueOrFalse.answer}');
            });
          </script>
            ${trueOrFalse.title} <span id="${trueOrFalse.id}Info"></span><br/>
          <input type="radio" name="${trueOrFalse.id}" value="True" />True
          <input type="radio" name="${trueOrFalse.id}" value="False" />False
        </li>
      </c:forEach>

        <%-- 简答题 --%>
      <c:forEach items="${requestScope.homeWork.shortQuestions}" var="shortQuestion">
        <li>
            ${shortQuestion.title} <span id="${shortQuestion.id}Info"></span><br/>
          <textarea name="${shortQuestion.id}" disabled="disabled">${shortQuestion.stuAnswer}</textarea>
        </li>
      </c:forEach>

    </ul>

</c:if>


<script >

  function setStudentAnswer(id, stuAnswer, answer) {
    $('#' + id + 'Info').html('正确答案: ' + answer).css('color', 'red');

    $("input[name='" + id + "']").each(function() {
      if ($(this).val() == stuAnswer) {
        $(this).attr('checked', 'checked');
      }
    });

    $('input').each(function() {
      $(this).attr('disabled', 'disabled');
    });
  }

</script>

</body>
</html>

