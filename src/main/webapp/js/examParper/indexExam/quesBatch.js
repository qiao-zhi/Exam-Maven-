/**
 * 随机生成试卷的上下移动 Created by yorge on 2017/9/27.
 */

$(function (e) {

    // 试题，鼠标移上去显示按钮。这里放着 不可编辑的试题
    $(".el_addEPtikuang").children().children(".movie_box").hover(function () {
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
        $(this).css({"border": "1px solid #fff",
            "border-bottom": "1px solid #eee"});
        $(this).children(".kzqy_czbut").remove();
    });

    /*<!--标题移动,删除-->*/
    /*
	 * 标题的上下移动和删除
	 */
    // 下移
    $(".el_addEPtikuang").on("click", ".el_tiBoxMainDelBox .bigxy", function () {
        // 获取标题的个数
         var leng = $(".el_addEPtikuang").length;
        /* dqgs题号 */
        var dqgs = $(this).parents(".el_addEPtikuang").index();
        if (dqgs < leng) {
            var cur_ques_box = $(this).parents(".el_addEPtikuang");
            $(this).parents(".el_addEPtikuang").next().after(cur_ques_box);
            if(dqgs == 1){
                $(this).parents(".el_addEPtikuang").find(".bigNum").text("二");
                $(this).parents(".el_addEPtikuang").prev().find(".bigNum").text("一");
            }
            if(dqgs == 2){
                $(this).parents(".el_addEPtikuang").find(".bigNum").text("三");
                $(this).parents(".el_addEPtikuang").prev().find(".bigNum").text("二");
            }
        } else {
            alert("到底了");
        }

    });

    // 上移
    $(".el_addEPtikuang").on("click", ".el_tiBoxMainDelBox .bigsy", function () {
        // var leng = $(".el_addEPtikuang").length;
        /* dqgs题号 */
        var dqgs = $(this).parents(".el_addEPtikuang").index();

        if (dqgs != 1) {
            var cur_ques_box = $(this).parents(".el_addEPtikuang");
            $(this).parents(".el_addEPtikuang").prev().before(cur_ques_box);
            if(dqgs == 2){
                $(this).parents(".el_addEPtikuang").find(".bigNum").text("一");
                $(this).parents(".el_addEPtikuang").next().find(".bigNum").text("二");
            }
            if(dqgs == 3){
                $(this).parents(".el_addEPtikuang").find(".bigNum").text("二");
                $(this).parents(".el_addEPtikuang").next().find(".bigNum").text("三");
            }
        } else {
            alert("到头了");
        }
    });

    // 删除
    $(".el_addEPtikuang").on("click", ".el_tiBoxMainDelBox .el_tiBoxMainDel", function () {
        var res = confirm("确定删除所有吗？");
        if (res) {
            $(this).parents(".el_addEPtikuang").children(".yd_box").children(".movie_box").remove();
            
            $(this).parents(".el_addEPtikuang").find(".numTotal").text("");
            $(this).parents(".el_addEPtikuang").find(".numTotal").text("0分/");
            $(this).parents(".el_addEPtikuang").find(".numQues").text("");
            $(this).parents(".el_addEPtikuang").find(".numQues").text("0题)");
        }
    })
    // 折叠与展开
    $(".el_addEPtikuang").on("click", ".el_tiBoxMainDelBox .bigss", function () {
    	var dqgs = $(this).parents(".el_addEPtikuang");//获取到大题题干
    	//如果箭头是向下(证明点击是展开)
    	if($(this).children("span").hasClass("glyphicon-chevron-down")){
    		$(this).children("span").removeClass().addClass("glyphicon glyphicon-chevron-up").prop("title","点击收起题目");
    		dqgs.find(".movie_box").css("display","block");
    	}else{//如果箭头是向上(点击是收缩)
    		var tishu=dqgs.find(".movie_box").length;//获取其下面的题数，根据题数判断是否可以折叠
    		if(tishu>0){
    			//更换图标
    			$(this).children("span").removeClass().addClass("glyphicon glyphicon-chevron-down").prop("title","您折叠了"+tishu+"道题，点击展开");
    			//隐藏题目
    			dqgs.find(".movie_box").css("display","none");
    		}else{
    			alert("您没有题目可以隐藏");
    		}
    	}
    })
});
