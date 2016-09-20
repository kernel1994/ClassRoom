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
    <style>
        .c-eee {
            background-color: #eee !important;
        }
    </style>
</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<div class="container">
    <h4>查看答案</h4>
    <c:if test="${not empty requestScope.homeWork}">
        <%-- 单选题 --%>
        <c:forEach items="${requestScope.homeWork.selects}" var="select">
            <script>
                $(function () {
                    setStudentAnswer('${select.id}', '${select.stuAnswer}', '${select.answer}');
                });
            </script>
            <div class="row">
                <div class="col s12 m6">
                    <div class="card c-eee">
                        <div class="card-content black-text">
                            <span class="card-title">${select.title}</span>
                        </div>
                        <div class="card-action">
                            <input type="radio" name="${select.id}" id="${select.id}1" value="A"/>
                            <label for="${select.id}1" class="black-text">A. ${select.a}</label>
                            <br/>
                            <input type="radio" name="${select.id}" id="${select.id}2" value="B"/>
                            <label for="${select.id}2" class="black-text">B. ${select.b}</label>
                            <br/>
                            <input type="radio" name="${select.id}" id="${select.id}3" value="C"/>
                            <label for="${select.id}3" class="black-text">C. ${select.b}</label>
                            <br/>
                            <input type="radio" name="${select.id}" id="${select.id}4" value="D"/>
                            <label for="${select.id}4" class="black-text">D. ${select.d}</label>
                            <br/>
                        </div>
                    </div>
                </div>
                <div class="col s12 m6">
                    <div class="card c-eee">
                        <div class="card-content black-text">
                            <span class="card-title">正确答案</span>
                        </div>
                        <div class="card-action">
                            <span id="${select.id}Info"></span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <%-- 判断题 --%>
        <c:forEach items="${requestScope.homeWork.trueOrFalses}" var="trueOrFalse">
            <script>
                $(function () {
                    setStudentAnswer('${trueOrFalse.id}', '${trueOrFalse.stuAnswer}', '${trueOrFalse.answer}');
                });
            </script>
            <div class="row">
                <div class="col s12 m6">
                    <div class="card c-eee">
                        <div class="card-content black-text">
                                <span class="card-title">${trueOrFalse.title}</span>
                        </div>
                        <div class="card-action">
                            <input type="radio" name="${trueOrFalse.id}" id="${trueOrFalse.id}T" value="True"/>
                            <label for="${trueOrFalse.id}T" class="black-text">True</label>
                            <input type="radio" name="${trueOrFalse.id}" id="${trueOrFalse.id}F" value="False"/>
                            <label for="${trueOrFalse.id}F" class="black-text">False</label>
                        </div>
                    </div>
                </div>
                <div class="col s12 m6">
                    <div class="card c-eee">
                        <div class="card-content black-text">
                                <span class="card-title">正确答案</span>
                        </div>
                        <div class="card-action">
                            <span id="${trueOrFalse.id}Info"></span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>

        <%-- 简答题 --%>
        <c:forEach items="${requestScope.homeWork.shortQuestions}" var="shortQuestion">
            <div class="row">
                <div class="col s12 m6">
                    <div class="card c-eee">
                        <div class="card-content black-text">
                                <span class="card-title">${shortQuestion.title}</span>
                        </div>
                        <div class="card-action black-text">
                            <div class="row">
                                <div class="input-field col s12">
                                    <textarea class="materialize-textarea black-text">${shortQuestion.stuAnswer}</textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col s12 m6">
                    <div class="card c-eee">
                        <div class="card-content black-text">
                            <span class="card-title">参考答案</span>
                        </div>
                        <div class="card-action">
                            <span id="${shortQuestion.id}Info">${shortQuestion.answer}</span>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:if>
</div>

<script>

    function setStudentAnswer(id, stuAnswer, answer) {
        $('#' + id + 'Info').html(answer).css('color', 'black');

        $("input[name='" + id + "']").each(function () {
            if ($(this).val() == stuAnswer) {
                $(this).attr('checked', 'checked');
            }
        });

        $('input').each(function () {
            $(this).attr('disabled', 'disabled');
        });

        $('textarea').each(function () {
            $(this).attr('disabled', 'disabled');
        });
    }

</script>

</body>
</html>

