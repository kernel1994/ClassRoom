<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>学生主页</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/css/kernel-app.css">
</head>
<body>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-2.1.3.min.js"></script>
<jsp:include page="/student/comp_nav.jsp"></jsp:include>

<div class="row">
    <div class="col s2">
        <div class="card hoverable">
            <div class="card-image waves-effect waves-block waves-light">
                <img class="activator" src="${pageContext.request.contextPath }/images/office.jpg" alt="photo">
            </div>
            <div class="card-content">
                <span class="card-title activator grey-text text-darken-4">${sessionScope.user.nick }</span>
                <p>${sessionScope.user.role.name}</p>
            </div>
            <div class="card-reveal">
                <span class="card-title grey-text text-darken-4">个人资料</span>
                <p>邮箱：${sessionScope.user.email}</p>
                <p>性别：${sessionScope.user.gender}</p>
                <p>生日：${sessionScope.user.birthday}</p>
                <p>住址：${sessionScope.user.address}</p>
            </div>
        </div>
    </div>

    <div class="col s10">
        <div class="row">
            <div class="announcement card-panel hoverable">
                <div class="container">
                    <div class="title">公告</div>
                    <div class="content" id="announcement-content"></div>
                </div>
            </div>

            <div class="card-panel hoverable" id="chartsContainer" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
        </div>
    </div>

</div>

<script src="${pageContext.request.contextPath }/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath }/js/data.js"></script>
<script src="${pageContext.request.contextPath }/js/drilldown.js"></script>
<script>
    $(function () {

//        var data1 = {
//            "courses" : [
//                {
//                    "name": ["语文"],
//                    "score": [70],
//                    "tasks": [
//                        {
//                            "name": "语文作业一",
//                            "score": 35
//                        }, {
//                            "name": "语文作业er",
//                            "score": 25
//                        }, {
//                            "name": "语文作业3",
//                            "score": 10
//                        }
//                    ]
//                }, {
//                    "name": ["数学"],
//                    "score": [60],
//                    "tasks": [
//                        {
//                            "name": "数学作业一",
//                            "score": 15
//                        }, {
//                            "name": "数学作业er",
//                            "score": 25
//                        }, {
//                            "name": "数学作业3",
//                            "score": 20
//                        }
//                    ]
//                }, {
//                    "name": ["英语"],
//                    "score": [90],
//                    "tasks": [
//                        {
//                            "name": "英语作业一",
//                            "score": 60
//                        }, {
//                            "name": "英语作业er",
//                            "score": 15
//                        }, {
//                            "name": "英语作业3",
//                            "score": 15
//                        }
//                    ]
//                }
//            ]
//        };

        $.post("createIndexChartData.stu", {data: new Date()}, function (result) {
            // console.log(result);
            var data = JSON.parse(result);
            // console.log(data);

            $.each(data.courses, function (i, c) {
                var array = [];
                for (var j = 0; j < (c.tasks.length / 2); j++) {
                    array[j] = {
                        "name": c.tasks[2 * j],
                        "score": Number(c.tasks[2 * j + 1])
                    };
                }
                c.tasks = array;
            });

            var chartData = {
                chart: {
                    type: 'column'
                },
                title: {
                    text: '我的成绩'
                },
                subtitle: {
                    text: '当前各门课的总成绩(点击可看每次作业成绩)'
                },
                xAxis: {
                    type: 'category'
                },
                yAxis: {
                    title: {
                        text: '分数'
                    }

                },
                legend: {
                    enabled: false
                },
                plotOptions: {
                    series: {
                        borderWidth: 0,
                        dataLabels: {
                            enabled: true,
                            format: '{point.y:.1f}'
                        }
                    }
                },

                tooltip: {
                    headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                    pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.1f}分</b><br/>'
                },

                series: [{
                    name: '课程',
                    colorByPoint: true,
                    data: []
                }],

                drilldown: {
                    series: []
                }
            };

            // 推荐将重复的jQuery 选择使用变量存储起来
            var chartsContainer = $('#chartsContainer');
            chartsContainer.highcharts(chartData);
            $.each(data.courses, function (i, c) {
                chartData.series[0].data[i] = {
                    name: c.name[0],
                    y: Number(c.score[0]),
                    drilldown: c.name[0]
                };

                chartData.drilldown.series[i] = {
                    name: c.name[0],
                    id: c.name[0],
                    /*
                     * 这里的data 属性是一个二维数组，
                     * 开始尝试使用 data: [[]] 的方式
                     * 直接定义一个二维数组，但是在读写其第二维的时候失败了，
                     * 错误信息是：Uncaught TypeError: Cannot set property '0' of undefined
                     * 于是解决办法是先定义一个一位数组，然后在循环中把一位数组的每一个元素定义成一个一位数组，
                     * 那么data 这个属性就成为了一个二维数组
                     * */
                    data: []
                };

                $.each(c.tasks, function (j, t) {
                    // 在这里把数组中每一个元素定义成一个以为数组
                    chartData.drilldown.series[i].data[j] = [];

                    chartData.drilldown.series[i].data[j][0] = t.name;
                    chartData.drilldown.series[i].data[j][1] = t.score;
                });

            });
            chartsContainer.highcharts(chartData);

        });

        $.post("createIndexAnnouncementData.stu", {data: new Date()}, function (result) {
            let ann = $('#announcement-content');
            let data = JSON.parse(result);

            $.each(data, function (i, e) {
                ann.append(
                        "<div class='announcement-item'>" +
                            "<span class='user blue lighten-2'>" + e.user.name + "</span>" +
                            "<span class='course blue lighten-2'>" + e.courseName + "</span>" +
                            "<span class='time blue lighten-3'>" + e.time + "</span>" +
                            "<span class='content blue lighten-4'>" + e.content + "</span>" +
                         "</div>"
                );
            });
        });

    });
</script>
</body>
</html>