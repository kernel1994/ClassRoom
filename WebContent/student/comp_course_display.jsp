<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div>
  <c:if test="${not empty requestScope.courses }">
    <table>
      <thead>
      <tr>
        <th>课程</th>
        <th>教师</th>
        <th>限选人数</th>
        <th>描述</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${requestScope.courses}" var="course">
        <tr>
          <!-- 转至课程主页(对于已选和未选的学生展示有区别) -->
          <td><a href="viewCourseIndex.stu?courseId=${course.id}">${course.name}</a></td>
          <!-- 转至教师展示信息主页 -->
          <td><a href="javascript:void(0);">${course.teacher.nick }</a></td>
          <td>${course.limitperson }</td>
          <td>${course.description }</td>
          <c:choose>
            <c:when test="${course.haveOwn == 1 }">
              <td><button onclick="unchoose(this, '${course.id }', '${sessionScope.user.id }')">退选</button></td>
            </c:when>

            <c:when test="${course.haveOwn == 0 }">
              <td><button onclick="choose(this, '${course.id }', '${sessionScope.user.id }')">选课</button></td>
            </c:when>
          </c:choose>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>
</div>
