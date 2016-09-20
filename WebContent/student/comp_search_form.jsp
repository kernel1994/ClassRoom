<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <form action="queryCourse.stu" method="post" class="col l6 s12">
        <%--<div class="row">--%>
            <%--<div class="input-field col l6 s12">--%>
                <%--<input type="text" name="courseName" id="courseName"/> <label for="courseName">课程名</label>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="row">
            <div class="input-field col l6 s12">
                <input type="text" name="teacherName" id="teacherName"/> <label for="teacherName">教师名</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col l6 s12">
                <input type="text" name="limitperson" id="limitperson"/> <label for="limitperson">限选人数</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col l6 s12">
                <input type="text" name="description" id="description"/> <label for="description">描述</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col l3 s6">
                <button class="btn waves-effect waves-light" type="submit">查询</button>
            </div>
        </div>
    </form>
</div>
