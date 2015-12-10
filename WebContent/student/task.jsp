<%-- 学生做作业的页面 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${requestScope.task.name}</title>
</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<div class="container">
    <h4>做练习</h4>
    <c:if test="${not empty requestScope.homeWork}">
        <form action="javascript:void(0);" method="post" id="sheet1">
            <!-- 隐藏域存储作业id 用于检查的时候用 -->
            <input type="hidden" name="taskId" value="${requestScope.taskId}">

                <%-- 单选题 --%>
            <c:forEach items="${requestScope.homeWork.selects}" var="select">
                <div class="row">
                    <div class="col s12 m6">
                        <div class="card teal darken-3">
                            <div class="card-content white-text">
                                <span class="card-title">${select.title} <span id="${select.id}Info"></span></span>
                            </div>
                            <div class="card-action">
                                <input type="radio" name="${select.id}" id="${select.id}1" value="A"/>
                                <label for="${select.id}1" class="white-text">A. ${select.a}</label>
                                <br/>
                                <input type="radio" name="${select.id}" id="${select.id}2" value="B"/>
                                <label for="${select.id}2" class="white-text">B. ${select.b}</label>
                                <br/>
                                <input type="radio" name="${select.id}" id="${select.id}3" value="C"/>
                                <label for="${select.id}3" class="white-text">C. ${select.b}</label>
                                <br/>
                                <input type="radio" name="${select.id}" id="${select.id}4" value="D"/>
                                <label for="${select.id}4" class="white-text">D. ${select.d}</label>
                                <br/>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

                <%-- 判断题 --%>
            <c:forEach items="${requestScope.homeWork.trueOrFalses}" var="trueOrFalse">
                <div class="row">
                    <div class="col s12 m6">
                        <div class="card cyan darken-3">
                            <div class="card-content white-text">
                                <span class="card-title">${trueOrFalse.title} <span
                                        id="${trueOrFalse.id}Info"></span></span>
                            </div>
                            <div class="card-action">
                                <input type="radio" name="${trueOrFalse.id}" id="${trueOrFalse.id}T" value="True"/>
                                <label for="${trueOrFalse.id}T" class="white-text">True</label>
                                <input type="radio" name="${trueOrFalse.id}" id="${trueOrFalse.id}F" value="False"/>
                                <label for="${trueOrFalse.id}F" class="white-text">False</label>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>

                <%-- 简答题 --%>
            <c:forEach items="${requestScope.homeWork.shortQuestions}" var="shortQuestion">
                <div class="row">
                    <div class="col s12 m6">
                        <div class="card light-blue darken-3">
                            <div class="card-content white-text">
                                <span class="card-title">${shortQuestion.title} <span
                                        id="${shortQuestion.id}Info"></span></span>
                            </div>
                            <div class="card-action white-text">
                                <div class="row">
                                    <div class="input-field col s12">
                                        <textarea name="${shortQuestion.id}" id="textarea1" class="materialize-textarea"></textarea>
                                        <label for="textarea1">请作答</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </form>
        <a type="button" class="btn waves-effect waves-light" id="submitBtn">提交</a>

    </c:if>
</div>

<script>
    function form2json(frm) {
        var o = {};
        var a = frm.serializeArray();
        $.each(a, function () {
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

    $('#submitBtn').click(function () {
        var data = form2json($('#sheet1'));
        var jData = JSON.stringify(data);

        $.post("submitTask.stu", {data: jData}, function (result) {
            // alert(result);
            var wrong = JSON.parse(result);

            for (var wtfukk in wrong) {
                console.log(wrong[wtfukk]);
                $('#' + wtfukk + 'Info')
                        .html('正确答案: ' + wrong[wtfukk])
                        .css('color', 'red');
            }
        });

        $(this).addClass('disabled');
    });

</script>

</body>
</html>
