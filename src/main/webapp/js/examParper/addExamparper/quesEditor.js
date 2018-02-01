/**
 * Created by yorge on 2017/9/15.
 */
/*<!--试题编辑框-->*/
$(document).ready(function (e) {

    //试题，鼠标移上去显示按钮
    $(".movie_box").hover(function () {
        var html_cz;
        if($(this).parent().hasClass("el_danxuan")) {
            html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='sy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xy'>下移</a>" +
                "<a href='javascript:void(0)' class='del' >删除</a>" +
                "<a href='javascript:void(0)' class='tianjia' value='0' >添加</a>" +
                "</div>";
        }
        if($(this).parent().hasClass("el_duoxuan")) {
            html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='sy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xy'>下移</a>" +
                "<a href='javascript:void(0)' class='del' >删除</a>" +
                "<a href='javascript:void(0)' class='tianjia' value='1' >添加</a>" +
                "</div>";
        }
        if($(this).parent().hasClass("el_panduan")) {
            html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='sy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xy'>下移</a>" +
                "<a href='javascript:void(0)' class='del' >删除</a>" +
                "<a href='javascript:void(0)' class='tianjia' value='2' >添加</a>" +
                "</div>";
        }
        $(this).css({"border": "1px solid #0099ff"});
        $(this).children(".wjdc_list").after(html_cz);
    }, function () {
        $(this).css({"border": "1px solid #fff"});
        $(this).children(".kzqy_czbut").remove();
    });
/*
    /!*标题 鼠标移上去，显示按钮*!/
    $(".elaquesTitle_father").hover(function () {
        var html_cz = "<div class='kzqy_czbut'>" +
            "<a href='javascript:void(0)' class='bigsy'>上移</a>" +
            "<a href='javascript:void(0)'  class='bigxy'>下移</a>" +
            "<a href='javascript:void(0)' class='del_title' >删除</a>" +
            "</div>";
        $(this).children().children(".el_BigTitle").after(html_cz);
    }, function () {
        $(this).children().children(".kzqy_czbut").remove();
    });*/

    /*试题的上下移动和删除*/
    //下移
    $(".movie_box").on("click", ".xy", function () {
        //文字的长度  改为  获取试题数量
        var leng = $(this).parent().parent().parent(".yd_box").children(".movie_box").length;
        /*dqgs题号*/
        var dqgs = $(this).parent(".kzqy_czbut").parent(".movie_box").index();

        if (dqgs < leng - 1) {
            //获取整道试题对象
            var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");
            //获取下一道题
            var xyghtml = czxx.next().html();
            //获取整道试题
            var syghtml = czxx.html();
            //把该试题，插入到下一道试题中
            czxx.next().html(syghtml);
            //把下一道试题插入到该试题对象中
            czxx.html(xyghtml);
            //题号变化
            czxx.find(".nmb").text(dqgs + 1);
            czxx.next().find(".nmb").text(dqgs + 2);
        } else {
            alert("到底了");
        }
    });

    //上移
    $(".movie_box").on("click", ".sy", function () {
        var dqgs = $(this).parent(".kzqy_czbut").parent(".movie_box").index();
        if (dqgs > 0) {
            var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");
            var xyghtml = czxx.prev().html();
            var syghtml = czxx.html();
            czxx.prev().html(syghtml);
            czxx.html(xyghtml);
            //序号
            czxx.find(".nmb").text(dqgs + 1);
            czxx.prev().find(".nmb").text(dqgs);

        } else {
            alert("到头了");
        }
    });

    //删除
    $(".movie_box").on("click", ".del", function () {
        /*如果点击确定，则则执行下边*/
        var res = confirm("确认删除吗？");
        if (res) {
        	alert("删除成哥")
            var czxx = $(this).parent(".kzqy_czbut").parent(".movie_box");
            var zgtitle_gs = czxx.parent(".yd_box").find(".movie_box").length;
            var xh_num = 0;
            //重新编号
            czxx.parent(".yd_box").find(".movie_box").each(function () {
                $(".yd_box").children(".movie_box").eq(xh_num).find(".nmb").text(xh_num);
                xh_num++;
                //alert(xh_num);
            });
            czxx.remove();
        }
    });
/*
    /!*
     标题的上下移动和删除
     * *!/
    //下移
    $(".elaquesTitle_father").on("click", ".bigxy", function () {
        //获取标题的个数
        var leng = $(".movie_box_title").length;
        /!*dqgs题号*!/
        var dqgs = $(this).parent(".kzqy_czbut").parent().parent().index();
        if (dqgs < leng) {
            var czxx = $(this).parent().parent().parent();
            var syghtml = czxx.html();       //当前值，可以获取
            var xyghtml = czxx.next().html();//下一个值
            czxx.next().html(syghtml);
            czxx.html(xyghtml);
            if(dqgs == 1) {
                czxx.find(".bigNum").text("一");
                czxx.next().find(".bigNum").text("二");
            }
            if(dqgs == 2) {
                czxx.find(".bigNum").text("二");
                czxx.next().find(".bigNum").text("三");
            }
        } else {
            alert("到底了");
        }
    });

    //上移
    $(".elaquesTitle_father").on("click", ".bigsy", function () {
        //获取题号
        var dqgs = $(this).parent().parent().parent().index();
        if (dqgs > 1) {
            var czxx = $(this).parent().parent().parent();
            var xyghtml = czxx.prev().html();
            var syghtml = czxx.html();
            czxx.prev().html(syghtml);
            czxx.html(xyghtml);

            //序号
            if(dqgs == 2) {
                czxx.find(".bigNum").text("二");
                czxx.prev().find(".bigNum").text("一");
            }
            if(dqgs == 3) {
                czxx.find(".bigNum").text("三");
                czxx.prev().find(".bigNum").text("二");
            }
        } else {
            alert("到头了");
        }
    });

    $(".elaquesTitle_father").on("click", ".del_title", function () {
        /!*如果点击确定，则则执行下边*!/
        var res = confirm("确认删除所有题吗？");
        if (res) {
            $(this).parent().parent().parent().children(".all_660").remove();
        }
    });*/
});