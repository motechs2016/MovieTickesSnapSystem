/**
 *
 * @authors cpt
 * @date    2016-12-11 22:05:11
 * @version
 */

var ticket_module = {
    // 管理url
    url: {
        "snapUrl": "",
        "movieMd5Url": "",
    },
    // 电影详情页
    detail: {
        // 初始化参数
        init: function (parems) {
            this.start = parems.starttime;
            this.end = parems.endtime;
            this.id = parems.itemid;
            this.now = parems.nowtime
            this.add = parems.addtime;
            this.num = parems.num;
            this.name = parems.name;
            this.md5 = parems.md5;
            this.show();
            console.log(this);
        },
        // 详情页显示
        show: function () {
            var vm = new Vue({
                el: '#movie_item',
                data: {
                    name: this.name,
                    num: this.num,
                    add: new Date(this.add).Format("yyyy/MM/dd hh:mm:ss"),
                    start: new Date(this.start).Format("yyyy/MM/dd hh:mm:ss"),
                    end: new Date(this.end).Format("yyyy/MM/dd hh:mm:ss"),
                }
            });
            ticket_module.countdown();
        }
    },
    countdown: function () {
        var now = ticket_module.detail.now && parseInt(ticket_module.detail.now, 10);
        var start = ticket_module.detail.start && parseInt(ticket_module.detail.start, 10);
        var end = ticket_module.detail.end && parseInt(ticket_module.detail.end, 10);
        var md5 = ticket_module.detail.md5;
        console.log("当前时间" + now)

        if (md5) {
            ticket_module.url.snapUrl="/snap/"+ticket_module.detail.id+"/"+md5;
            $("#button").html("点击秒杀").attr("disabled", false).addClass("btn-success");
            $("#info").html("活动进行中。。");
            if(changenum){
                clearInterval(changenum);
            }
            var changenum = setInterval(function () {
                ticket_module.updateMovieNum($("#movieTickeId").val());
            },3000);
        } else {
            // 活动未开始，显示倒计时
            if (now < start) {
                // var stime = new Date(start).Format("yyyy/MM/dd hh:mm:ss");
                $("#button").attr("disabled", true).countdown(start, function (event) {
                    $(this).html(
                        event.strftime("剩余时间:%D 天 %H 时 %M 分 %S 秒")
                    ).addClass("btn-info");
                    // 时间完成后执行再次获取电影详情
                }).on('finish.countdown',function () {
                    ticket_module.getMovieDetail();
                });
                $("#info").html("活动未开始");
            } else {
                $("#button").html("已结束").attr("disabled", true).addClass("btn-danger");
                $("#info").html("活动已结束");
            }
        }
    },
    // 秒杀操作
    snap: function () {
        $.ajax({
            url: ticket_module.url.snapUrl,
            success: function (d) {
                if(d.status) {
                    var data = d.data;
                    if (data.status) {
                        //抢购成功
                        var vm = new Vue({
                            el: "#ResultInfo",
                            data: {
                                message: data.message
                            }
                        });
                    } else {
                        // 重复抢购
                        var vm =  new Vue({
                            el: "#ResultInfo",
                            data: {
                                message: data.message
                            }
                        });
                    }
                }
                else{
                    if(d.errorCode=1){
                        // 未登录
                        var vm = new Vue({
                            el: "#ResultInfo",
                            data: {
                                message: d.data
                            }
                        });
                        window.location.href = "/";
                    }
                }
                $("#ResultInfo").modal("show");
            },
            error: function (data) {

            }
        });
    },
    // 获取电影详情
    getMovieDetail: function (callback) {
        $.ajax({
            url: ticket_module.url.movieMd5Url,
            success: function (data) {
                var movie = data.data.movieTicke;
                var parems = {
                    starttime: movie.startTime,
                    endtime: movie.endTime,
                    addtime: movie.addTime,
                    nowtime: data.data.nowTime,
                    num: movie.num,
                    itemid: movie.id,
                    name: movie.name,
                    md5: data.data.md5
                }
                if (movie) {
                    ticket_module.detail.init(parems);
                }
                else{
                    window.location.href = "/";
                }
                callback && callback();
            },
            error: function (data) {
                alert(data.exception);
                window.location.href = "/";
            }
        })
    },
    // 更新电影数量
    updateMovieNum:function (movieId) {
        $.get(
            "/movieNum/"+movieId,
            function (data) {
                $("#m_num").html(data.data);
            }
        )
    }
}
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
