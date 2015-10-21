/*
* 选课和退选的js 函数
* */


/* 选课的js 函数。采用Ajax 方法
 * 参数：button 按钮对象
 * 要选课的课程id
 * 进行选课的学生id
  * */
function choose(obj, courseId, studentId) {
    $.post("chooseCourseAjax.stu", {courseId: courseId, studentId: studentId, temp: new Date()}, function (result) {

        if (result == "OK") {
            $(obj).html("选课成功").attr('disabled', true).removeAttr('onclick');
        } else {
            alert("发生某种错误");

        }
    });
}

/* 退选的js 函数。采用Ajax 方法
 * 参数：button 按钮对象
 * 要退选的课程id
 * 进行退选的学生id
  * */
function unchoose(obj, courseId, studentId) {
    $.post("unchooseCourseAjax.stu", {courseId: courseId, studentId: studentId, temp: new Date()}, function (result) {

        if (result == "OK") {
            $(obj).html("退选成功").attr('disabled', true).removeAttr('onclick');
        } else {
            alert("发生某种错误");

        }
    });
}