<%-- 学生做作业的页面 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.task.name}</title>
</head>
<body>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<c:if test="${not empty requestScope.homeWork}">
    <form action="javascript:void(0);" method="post" id="sheet1">
        <!-- 隐藏域存储作业id 用于检查的时候用 -->
        <input type="hidden" name="taskId" value="${requestScope.taskId}">

        <ul>
        <%-- 单选题 --%>
        <c:forEach items="${requestScope.homeWork.selects}" var="select">
        <li>
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
                ${trueOrFalse.title} <span id="${trueOrFalse.id}Info"></span><br/>
                <input type="radio" name="${trueOrFalse.id}" value="True" />True
                <input type="radio" name="${trueOrFalse.id}" value="False" />False
              </li>
          </c:forEach>

           <%-- 简答题 --%>
           <c:forEach items="${requestScope.homeWork.shortQuestions}" var="shortQuestion">
           <li>
            ${shortQuestion.title} <span id="${shortQuestion.id}Info"></span><br/>
            <textarea name="${shortQuestion.id}"></textarea>
           </li>
           </c:forEach>

        </ul>

    </form>
    <a type="button" id="submitBtn"
       style="background-color:#eee;
          border:1px solid #d5d5d5;
          border-radius:3px;
          cursor:pointer;
          padding:5px 12px;">提交
    </a>
</c:if>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.2.min.js"></script>
<script >
    function form2json (frm) {
        var o = {};
        var a = frm.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });

        return o;
    }

    $('#submitBtn').click(function() {
        var data = form2json($('#sheet1'));
        var jData = JSON.stringify(data);

        $.post("submitTask.stu", {data: jData}, function(result) {
            // alert(result);
            var wrong = JSON.parse(result);

            for (var wtfukk in wrong) {
                console.log(wrong[wtfukk]);
                $('#' + wtfukk + 'Info')
                        .html('正确答案: ' + wrong[wtfukk])
                        .css('color', 'red');
            }
        });
    }).attr('disabled', true);

</script>

</body>
</html>
