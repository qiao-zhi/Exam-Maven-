/**
 * Created by yorge on 2017/9/15.
 */
$(function () {
    var qContent = $("#el_choose");
    /*qContent.children(".danxuan").hide();*/
    qContent.children(".panduan").hide();
    qContent.children(".duoxuan").hide();

    $(".el_addquestions").click(function () {

        var index = $(this).val();

        if (index == "0") {
            qContent.children(".panduan").hide();
            qContent.children(".duoxuan").hide();
            $(".el_bigBlock2").css("height", "300px");
            qContent.children(".danxuan").show();
        } else if (index == "1") {
            qContent.children(".danxuan").hide();
            qContent.children(".panduan").hide();
            $(".el_bigBlock2").css("height", "300px");
            qContent.children(".duoxuan").show();
            $(this).children("danxuan").css("checked", "false");
        } else if (index == "2") {
            qContent.children(".danxuan").hide();
            qContent.children(".duoxuan").hide();
            $(".el_bigBlock2").css("height", "130px");
            qContent.children(".panduan").show();
        }
    });

    //增加选项
    $(".zjxx").live("click", function () {
        var zjxx_html = $(this).prev(".title_itram").html();
        var zjxx_html2 = "<div class='title_itram'>" + zjxx_html + "</div>";
        $(this).before(zjxx_html2);

        /*为了动态的显示内容*/
        $(".xxk_xzqh_box .title_itram").hover(function () {
            var html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='sy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xy'>下移</a>" +
                "<a href='javascript:void(0)' class='del' >删除</a></div>";
            $(this).css({
                "border": "1px solid #0099ff"
            });
            $(this).children(".kzjxx_iteam").after(html_cz)
        }, function () {
            $(this).css({
                "border": "1px solid #fff"
            });
            $(this).children(".kzqy_czbut").remove();
        })
    });

    //删除一行
    $(".del").live("click", function () {
        /*如果点击确定，则则执行下边*/
        var res = confirm("确认删除吗？");
        if (res) {
            //获取编辑题目的个数
            var zuxxs_num = $(this).parent(".kzqy_czbut").parent(".title_itram").parent(".xxk_xzqh_box").children(".title_itram").length;
            if (zuxxs_num > 0) {
                $(this).parent(".kzqy_czbut").parent(".title_itram").remove();
            } else {
                alert("手下留情");
            }
        }
    });

    /*
     * 选项上边增加操作
     */
    $(".xxk_xzqh_box .title_itram").hover(function () {
        var html_cz = "<div class='kzqy_czbut'>" +
            "<a href='javascript:void(0)' class='sy'>上移</a>" +
            "<a href='javascript:void(0)'  class='xy'>下移</a>" +
            "<a href='javascript:void(0)' class='del' >删除</a></div>";
        $(this).css({
            "border": "1px solid #0099ff"
        });
        $(this).children(".kzjxx_iteam").after(html_cz)
    }, function () {
        $(this).css({
            "border": "1px solid #fff"
        });
        $(this).children(".kzqy_czbut").remove();
    })

    //下移
    $(".xy").live("click", function () {
        //获取选项数量
        var leng = $(this).parent(".kzqy_czbut").parent(".title_itram").parent("").children(".title_itram").length;
        var dqgs = $(this).parent(".kzqy_czbut").parent(".title_itram").index();

        if (dqgs < leng - 1) {
            //本框的值
            var v1 = $(this).parent(".kzqy_czbut").siblings(".kzjxx_iteam").children("input");
            var v1Value = v1.val();
            //下边框的值
            var v2 = $(this).parent(".kzqy_czbut").parent(".title_itram").next().children(".kzjxx_iteam").children("input");
            var v2Value = v2.val();

            /*获取本选项*/
            var c1 = $(this).parent(".kzqy_czbut").parent(".title_itram");
            //获取前一道
            var c2 = $(this).parent(".kzqy_czbut").parent(".title_itram").next();
            c1.children(".kzjxx_iteam").children(".input_wenbk").val(v2Value);
            c2.children(".kzjxx_iteam").children(".input_wenbk").val(v1Value);
        } else {
            alert("到底了");
        }
    });

    //上移
    $(".sy").live("click", function () {
        var dqgs = $(this).parents(".title_itram").index();
        if (dqgs > 0) {
            //本框的值
            var v1 = $(this).parent(".kzqy_czbut").siblings(".kzjxx_iteam").children("input");
            var v1Value = v1.val();
            //上边框的值
            var v2 = $(this).parent(".kzqy_czbut").parents(".title_itram").prev().children(".kzjxx_iteam").children("input");
            var v2Value = v2.val();

            /*获取本选项*/
            var c1 = $(this).parent(".kzqy_czbut").parent(".title_itram");
            //获取前一道
            var c2 = $(this).parent(".kzqy_czbut").parent(".title_itram").prev();
            c1.children(".kzjxx_iteam").children(".input_wenbk").val(v2Value);
            c2.children(".kzjxx_iteam").children(".input_wenbk").val(v1Value);

        } else {
            alert("到头了");
        }
    });

})
/*
<!--设置-->*/
