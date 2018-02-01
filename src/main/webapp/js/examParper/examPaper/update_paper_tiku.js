/**
 * 题库选中右边题 Created by yorge on 2017/10/1.
 */
$(function () {
    /* 注意：试题查询试题的时候，需要判断一下左侧有哪些试题，已经在当前题库列表中了，并把这些存在的试题默认选中 */

    /* <!--从题库抽题--> */
    /**
	 * 点击复选框 判断复选框是否选中 若选中 1、判断复选框所属试题的类型 2、在相应试题框中生成该类型试题 3、复选框所属li 背景变色
	 * 若取消选中/或者删除相应从题库拉去的试题，选中按钮都会取消选中 1、判断该试题与右侧题库试题之间的对应关系 2、删除试题/取消选中，同时同步操作。
	 * 3、序号重新排列,试题后边的试题序号 - 1
	 */
	

    /* 题库隐藏 */
    $("#show_tiku").hide();
    $("#hide_tiku").click(function () {
        $(".el_addEPtiku").animate({right: '-37%'}, 800);
        $(".el_addEPleft").stop().animate({width: '99%'}, 600);
        $("#show_tiku").show();
    })
    $("#show_tiku").click(function () {
        $(".el_addEPleft").stop().animate({width: '55%'}, 600);
        $(".el_addEPtiku").stop().animate({right: '15px'}, 800);
        $("#show_tiku").hide();
    });


    /* 右侧下拉，上移 */
    $(window).scroll(function () {
        if ($(window).scrollTop() > 50) {
            $('.el_addEPtiku').stop().animate({top: '2px'}, 300);
        }
        else {
            $('.el_addEPtiku').stop().animate({top: '103px'}, 300);
        }
    });

    /*
	 * <!-- 试题区域， 在该区域鼠标移到试题上边会显示整到试题内容 -->
	 */
// 事件委托机制
    $("body").on("mouseover mouseout",'.el_unflod',function(event){
    		if(event.type == "mouseover"){
    			 $(this).parent(".el_drag").css("height", "auto");
    		}else if(event.type == "mouseout"){
    			$(this).parent(".el_drag").css("height", "30px");
    			}
    });
    
    
})





function el_tiku_checkedButtonF(obj){
		//
        // 获取试题块
        var ques = $(obj).parent().children(".movie_box");
        // 获取id
        var ques_id = $(obj).parent().find(".ques_id").prop("id");
        // 获取试题解析
//        var ques_analy = $(obj).parent().find(".ques_analy").val();
        // 获取答案
        var ques_answer = $(obj).parent().find(".ques_answer").val();
        // 获取试题标题
        var ques_title = ques.children(".tm_btitlt").text();
        // 获取试题选项
        var ques_options = ques.children(".wjdc_list").find("span");

        if ($(obj).is(":checked")) { // 选中执行
            // 判断试题类型并在相应试题框中，生成该试题
            if (ques.hasClass("dan")) {// 单选
            	
                // 1、获取试题编号
                var ques_num = $(".el_danxuan").children(".movie_box").length;
                ques_num = ques_num + 1;
                // 2、根据试题编号，给选项设置名字

                // 需要改变的是试题编号，题干，选项，
                // 需要添加的是 答案 解析 id
                var quesLeft = '<div class="movie_box">' +
                    '<div class="tm_btitlt"><i class="nmb">' + ques_num + '</i>. <i class="btwenzi">' + ques_title + '</i></div>' +
                    '<ul class="wjdc_list">';// 试题编号和题干
                var quesMiddle = "";
                ques_options.each(function () {
                    quesMiddle = quesMiddle +
                        '<li><label><input name="a' + ques_num + '" type="radio"><span>' + $(this).text() + '</span></label></li>';
                });// 选项
                var quesRight = '</ul>'+
                	"<input type='hidden' id='" + ques_id + "' class='ques_id'> <!--放 id -->" +
                	'答案: <input type="hidden" class="ques_answer" value="'+ques_answer+'">' + updateAnswerFormat(ques_answer,"12345","ABCDE") +'<br/>'+
					/*'解析:<input type="hidden" class="ques_analy" value="'+ques_analy+'">' +　ques_analy;*/
                    '</div>';
                // 3、生成试题，把试题放到左边区域中
                var quesFull = quesLeft + quesMiddle + quesRight;

                $(".el_danxuan").append(quesFull);
                
                // 获取题的总数
                var ques_length = $(".el_danxuan").children(".movie_box").length;
            	// 修改大标题中试题数量
            	 $(".danxuanBigTitle").find(".numQues").text(ques_length + "题)")// 设置题总数
            	 var everyQuesScore = $(".danxuanBigTitle").find(".el_modifiedGrade").val()// 获取每道题分值
            	 $(".danxuanBigTitle").find(".numTotal").text(
            				parseFloat(everyQuesScore) * parseFloat(ques_length) + "分;共")// 设置分总数

            }
            if (ques.hasClass("duo")) {// 多选
                // 1、获取试题编号
                var ques_num = $(".el_duoxuan").children(".movie_box").length;
                ques_num = ques_num + 1;
                // 2、根据试题编号，给选项设置名字

                // 需要改变的是试题编号，题干，选项，
                // 需要添加的是 答案 解析 id
                var quesLeft = '<div class="movie_box">' +
                    '<div class="tm_btitlt"><i class="nmb">' + ques_num + '</i>. <i class="btwenzi">' + ques_title + '</i></div>' +
                    '<ul class="wjdc_list">';
                var quesMiddle = "";
                ques_options.each(function () {
                    quesMiddle = quesMiddle +
                        '<li><label><input name="b' + ques_num + '" type="checkbox"><span>' + $(this).text() + '</span></label></li>';
                });
                var quesRight = '</ul>'+
            	"<input type='hidden' id='" + ques_id + "' class='ques_id'> <!--放 id -->" +
            	'答案: <input type="hidden" class="ques_answer" value="'+ques_answer+'">' + updateAnswerFormat(ques_answer,"12345","ABCDE") +'<br/>'+
				/*'解析:<input type="hidden" class="ques_analy" value="'+ques_analy+'">' +　ques_analy;*/
                '</div>';

                // 3、生成试题，把试题放到左边区域中
                var quesFull = quesLeft + quesMiddle + quesRight;
                $(".el_duoxuan").append(quesFull);
                

                // 获取题的总数
                var ques_length = $(".el_duoxuan").children(".movie_box").length;
            	// 修改大标题中试题数量
            	 $(".duoxuanBigTitle").find(".numQues").text(ques_length + "题)")// 设置题总数
            	 var everyQuesScore = $(".duoxuanBigTitle").find(".el_modifiedGrade").val()// 获取每道题分值
            	 $(".duoxuanBigTitle").find(".numTotal").text(
            				parseFloat(everyQuesScore) * parseFloat(ques_length) + "分;共")// 设置分总数
            }
            if (ques.hasClass("pan")) {// 判断
                // 1、获取试题编号
                var ques_num = $(".el_panduan").children(".movie_box").length;
                ques_num = ques_num + 1;
                // 2、根据试题编号，给选项设置名字

                // 需要改变的是试题编号，题干，选项，
                // 需要添加的是 答案 解析 id
                var quesFull = '<div class="movie_box">' +
                    '<div class="tm_btitlt"><i class="nmb">' + ques_num + '</i>. <i class="btwenzi">' + ques_title + '</i></div>' +
                    '<ul class="wjdc_list">' +
                    '<li><label><input name="c' + ques_num + '" type="radio"><span>正确</span></label></li>'+
                    '<li><label><input name="c' + ques_num + '" type="radio"><span>错误</span></label></li>'+
                    '</ul>'+
                	"<input type='hidden' id='" + ques_id + "' class='ques_id'> <!--放 id -->" +
                	'答案: <input type="hidden" class="ques_answer" value="'+ques_answer+'">' + updateAnswerFormat(ques_answer,"12345","ABCDE") +'<br/>'+
					/*'解析:<input type="hidden" class="ques_analy" value="'+ques_analy+'">' +　ques_analy;*/
                    '</div>';

                $(".el_panduan").append(quesFull);
                

                // 获取题的总数
                var ques_length = $(".el_panduan").children(".movie_box").length;
            	// 修改大标题中试题数量
            	 $(".panduanBigTitle").find(".numQues").text(ques_length + "题)")// 设置题总数
            	 var everyQuesScore = $(".panduanBigTitle").find(".el_modifiedGrade").val()// 获取每道题分值
            	 $(".panduanBigTitle").find(".numTotal").text(
            				parseFloat(everyQuesScore) * parseFloat(ques_length) + "分;共")// 设置分总数
            }
        } else {  // 取消选中执行
            if (ques.hasClass("dan")) {// 单选
                var cancelQues = $(".el_danxuan").children(".movie_box").find(".btwenzi");
                cancelQues.each(function () {
                    if($(this).text()== ques.children(".tm_btitlt").text()){
                        var thisCancelQues = $(this).parents(".movie_box");

                        // 试题排序
                        thisCancelQues.nextAll().each(function () {
                            var n = $(this).find(".nmb").text();
                            n = parseInt(n) - 1;
                            $(this).find(".nmb").text(n);
                        })

                        // 删除
                        thisCancelQues.remove();
                    }
                })
                
                // 获取题的总数
                var ques_length = $(".el_danxuan").children(".movie_box").length;
            	// 修改大标题中试题数量
            	 $(".danxuanBigTitle").find(".numQues").text(ques_length + "题)")// 设置题总数
            	 var everyQuesScore = $(".danxuanBigTitle").find(".el_modifiedGrade").val()// 获取每道题分值
            	 $(".danxuanBigTitle").find(".numTotal").text(
            				parseFloat(everyQuesScore) * parseFloat(ques_length) + "分;共")// 设置分总数

            }
            if (ques.hasClass("duo")) {// 多选
                var cancelQues = $(".el_duoxuan").children(".movie_box").find(".btwenzi");
                cancelQues.each(function () {
                    if($(this).text()== ques.children(".tm_btitlt").text()){
                        var thisCancelQues = $(this).parents(".movie_box");

                        // 试题排序
                        thisCancelQues.nextAll().each(function () {
                            var n = $(this).find(".nmb").text();
                            n = parseInt(n) - 1;
                            $(this).find(".nmb").text(n);
                        })

                        // 删除
                        thisCancelQues.remove();
                    }
                })
                
                
                // 获取题的总数
                var ques_length = $(".el_duoxuan").children(".movie_box").length;
            	// 修改大标题中试题数量
            	 $(".duoxuanBigTitle").find(".numQues").text(ques_length + "题)")// 设置题总数
            	 var everyQuesScore = $(".duoxuanBigTitle").find(".el_modifiedGrade").val()// 获取每道题分值
            	 $(".duoxuanBigTitle").find(".numTotal").text(
            				parseFloat(everyQuesScore) * parseFloat(ques_length) + "分;共")// 设置分总数
            }
            if (ques.hasClass("pan")) {// 判断
                var cancelQues = $(".el_panduan").children(".movie_box").find(".ques_id");
                cancelQues.each(function () {
                    if($(this).text()== ques.children(".tm_btitlt").text()){

                        var thisCancelQues = $(this).parents(".movie_box");

                        // 试题排序
                        thisCancelQues.nextAll().each(function () {
                            var n = $(this).find(".nmb").text();
                            n = parseInt(n) - 1;
                            $(this).find(".nmb").text(n);
                        })

                        // 删除
                        thisCancelQues.remove();
                    }
                })
                
                
                // 获取题的总数
                var ques_length = $(".el_panduan").children(".movie_box").length;
            	// 修改大标题中试题数量
            	 $(".panduanBigTitle").find(".numQues").text(ques_length + "题)")// 设置题总数
            	 var everyQuesScore = $(".panduanBigTitle").find(".el_modifiedGrade").val()// 获取每道题分值
            	 $(".panduanBigTitle").find(".numTotal").text(
            				parseFloat(everyQuesScore) * parseFloat(ques_length) + "分;共")// 设置分总数
            }
        }

        // 移动上，显示操作内容
        $(".el_addEPtikuang").children().children(".movie_box").hover(function () {
        	if($(this).parent(".yd_box").hasClass("el_danxuan")){
        		html_cz = "";
        		html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='sy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xy'>下移</a>" +
                "<a href='javascript:void(0)' class='del' >删除</a>" +
                "<a href='javascript:void(0)' class='tianjia' value='0' >添加</a>" +
                "</div>";
        	}
        	if($(this).parent(".yd_box").hasClass("el_duoxuan")){
        		html_cz = "";
        		html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='sy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xy'>下移</a>" +
                "<a href='javascript:void(0)' class='del' >删除</a>" +
                "<a href='javascript:void(0)' class='tianjia' value='1' >添加</a>" +
                "</div>";
        	}
        	if($(this).parent(".yd_box").hasClass("el_panduan")){
        		html_cz = "";
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
	}




/**
 * 将source字符串按照s1-s2替换，例如:s1:1234,s2:ABCD则为将source中1换为A，2换为B```
 */
function updateAnswerFormat(source, s1, s2) {
	for (var i = 0, length_1 = s1.length; i < length_1; i++) {
		source = source.replace(s1.charAt(i), s2.charAt(i));
	}
	return source;
}
/**
 * 大题顺序
 */
function replaceBigNum(){
	bigNum_q++;
	if(bigNum_q==1){
		return "一";
	}
	if(bigNum_q==2){
		return "二";
	}
	if(bigNum_q==3){
		return "三";
	}
	
}