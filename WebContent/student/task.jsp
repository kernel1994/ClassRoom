<%-- 学生做作业的页面 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.task.name}</title>
</head>
<body>

<c:if test="${not empty requestScope.homeWork}">
    <form action="javascript:void(0);" method="post">
        <ul>
        <%-- 单选题 --%>
        <c:forEach items="${requestScope.homeWork.selects}" var="select">
        <li>
            ${select.title}<br/>
            <input type="radio" name="select" value="A" />A. ${select.a}<br/>
            <input type="radio" name="select" value="B" />B. ${select.b}<br/>
            <input type="radio" name="select" value="C" />C. ${select.c}<br/>
            <input type="radio" name="select" value="D" />D. ${select.d}<br/>
        </li>
        </c:forEach>

          <%-- 判断题 --%>
          <c:forEach items="${requestScope.homeWork.trueOrFalses}" var="trueOrFalse">
              <li>
                ${trueOrFalse.title}<br/>
                <input type="radio" name="trueOrFalse" value="True" />True
                <input type="radio" name="trueOrFalse" value="False" />False
              </li>
          </c:forEach>

           <%-- 简答题 --%>
           <c:forEach items="${requestScope.homeWork.shortQuestions}" var="shortQuestion">
           <li>
            ${shortQuestion.title}<br/>
            <textarea name="shortQuestion"></textarea>
           </li>
           </c:forEach>

        </ul>
    </form>
</c:if>

</body>
</html>
