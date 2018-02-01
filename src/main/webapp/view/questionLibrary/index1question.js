/**
 * Created by yorge on 2017/7/24.
 */
$(document).ready(function (e) {
    $('#addquerstions').click(function () {

        var index = $('input:radio:checked').val(); //选择添加问题的类型

        switch (index) {
            case "0": //单选
            case "1": //多选
            case "2": //问答
                var wjdc_list = '<ul class="wjdc_list"></ul>'; //问答 单选 多选
                var danxuan = "";
                if (index == "0") {
                    danxuan = '【单选】';
                } else if (index == "1") {
                    danxuan = '【多选】';
                } else if (index == "2") {
                    danxuan = '【判断】'
                }
                break;
        }

        $(movie_box).hover(function () {
            var html_cz = "<div class='kzqy_czbut'><a href='javascript:void(0)'  class='bianji'>编辑</a></div>";
            $(this).css({
                "border": "1px solid #0099ff"
            });
            $(this).children(".wjdc_list").after(html_cz);
        }, function () {
            $(this).css({
                "border": "1px solid #fff"
            });
            $(this).children(".kzqy_czbut").remove();
            //$(this).children(".dx_box").hide();
        });
        $(".yd_box").append(movie_box);

    });

    //增加选项
    $(".zjxx").live("click", function () {
        var zjxx_html = $(this).prev(".title_itram").children(".kzjxx_iteam").html();
        $(this).prev(".title_itram").append("<div class='kzjxx_iteam'>" + zjxx_html + "</div>");
    });

    //删除一行
    $(".del_xm").live("click", function () {
        //获取编辑题目的个数
        var zuxxs_num = $(this).parent(".kzjxx_iteam").parent(".title_itram").children(".kzjxx_iteam").length;
        if (zuxxs_num > 1) {
            $(this).parent(".kzjxx_iteam").remove();
        } else {
            alert("手下留情");
        }
    });

});

