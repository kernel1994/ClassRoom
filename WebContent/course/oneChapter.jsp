<%--
  Created by IntelliJ IDEA.
  User: kernel
  Date: 2015/12/4
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${requestScope.courseware.name}</title>
</head>
<body>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<div class="container">
    <a href="downloadFile.stu?uri=${requestScope.courseware.resource.uri}">${requestScope.courseware.name} 下载</a>
</div>

</body>
</html>
