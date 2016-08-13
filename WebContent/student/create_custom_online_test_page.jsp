<%--
  Created by IntelliJ IDEA.
  User: kernel
  Date: 2016/8/13
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>自定义测试</title>
</head>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>

<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<div class="container">
    <form action="createCustomOnlineTest.stu" method="post">
        <table>
            <tbody>
            <tr>
                <td>章节</td>
                <td>
                    <select name="chapter">
                        <option value="0">默认所有章节</option>
                        <option value="1">第一章</option>
                        <option value="2">第二章</option>
                        <option value="3">第三章</option>
                        <option value="4">第四章</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>难度</td>
                <td>
                    <select name="degree">
                        <option value="0">默认所有难度</option>
                        <option value="1">简单</option>
                        <option value="2">适中</option>
                        <option value="3">困难</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>知识点</td>
                <td>
                    <input type="text" name="knowledgepoint" placeholder="输入知识点">
                </td>
            </tr>
            <tr>
                <td>题目类型</td>
                <td>
                    <select name="type">
                        <option value="0">默认所有类型</option>
                        <option value="1">选择</option>
                        <option value="2">判断</option>
                        <option value="3">问答</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>题目数量</td>
                <td>
                    <select name="examcount">
                        <option value="5">默认5道题</option>
                        <option value="2">2道题</option>
                        <option value="5">5道题</option>
                        <option value="10">10道题</option>
                        <option value="15">15道题</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    <button class="btn waves-effect waves-light" type="submit">生成</button>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<script>
    $(function () {
        $('select').material_select();
        $('i').css('display', 'none');
    });
</script>
</body>
</html>
