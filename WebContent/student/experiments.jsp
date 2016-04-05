<%-- 学生所有作业页面 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>我的实验</title>
</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<div class="container">
    <c:choose>
        <c:when test="${not empty requestScope.courses}">
            <ul class="collapsible collapsible-accordion" data-collapsible="expandable">
                <c:forEach items="${requestScope.courses}" var="course">
                    <li class="active">
                        <div class="collapsible-header active">
                            <h5>
                                <a href="viewCourseIndex.stu?courseId=${course.id}" class="orange-text">${course.name} 的实验</a>
                            </h5>
                        </div>
                        <c:choose>
                            <c:when test="${not empty course.experiments}">
                                <div class="collapsible-body">
                                    <div class="collection">
                                        <c:forEach items="${course.experiments}" var="experiment">
                                            <a href="${pageContext.request.contextPath}/servlet/StudentExperimentServlet?method=doExperiment&experimentId=${experiment.id}" class="collection-item">${experiment.name}
                                                <c:choose>
                                                    <c:when test="${not empty experiment.score}">
                                                        <span class="badge blue-text">[已做]${experiment.score}分</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge red-text">[未做]${experiment.score}赶快做！！</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="collapsible-body">
                                    <em>没有实验</em>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <em>没有选课，就没有实验</em>
        </c:otherwise>
    </c:choose>
</div>

<script>
    $(document).ready(function () {
        $('.collapsible').collapsible({
            accordion: false
        });
    });
</script>

</body>
</html>
