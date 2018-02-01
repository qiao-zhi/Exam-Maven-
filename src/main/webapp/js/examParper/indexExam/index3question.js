/**
 * 手动编辑试卷试题(添加，上下移动)
 * Created by yorge on 2017/7/24.
 */
//var $7 = $.noConflict(true);
$7(function () {
    /*添加试题*/
    $7('.tianjia').live('click', function () {
        /**
         * 添加试题后，当前试题后边的试题的标题号都加 1
         * 1 获取试题数量，
         * 2 获取当前试题的 题号
         * 3 得知 后边有几道题
         * for 循环这几道题，给他们的标题号 + 1
         * 二
         * 直接使用next() ,判断，next的hasClass 是否有movie_box 类
         */
        var thisQuestion = $7(this).parents(".movie_box");
        thisQuestion.nextAll().each(function () {
            var n = $(this).find(".nmb").text();
            n = parseInt(n) + 1;
            $(this).find(".nmb").text(n);
        })

        var index = $7(this).attr("value");
        //定义一个试题盒
        var movie_box = '<div class="movie_box"></div>';

        /**
         * 判断点击添加的试题题号
         */
        var titleNumber = $7(this).parents(".movie_box").children(".tm_btitlt").children(".nmb").text();
        titleNumber = parseInt(titleNumber) + 1;

        switch (index) {
            case "0": //单选
            case "1": //多选
            case "2": //判断
                //试题题干
                movie_box = $7(movie_box).append('<div class="tm_btitlt"><i class="nmb">' + titleNumber + '</i>. ' +
                    '<i class="btwenzi">请编辑问题</i></div>');
                //放试题内容，包括试题选项等
                var wjdc_list = '<ul class="wjdc_list"></ul>';
                var danxuan = "";
                if (index == "0") {
                    danxuan = '【单选】';
                } else if (index == "1") {
                    danxuan = '【多选】';
                } else if (index == "2") {
                    danxuan = '【判断】'
                }
                //把题干插入到试题盒中
                movie_box = $7(movie_box).append(wjdc_list);
                //给试题添加试题类型
                movie_box = $7(movie_box).append('<div class="dx_box" data-t="' + index + '"></div>');
                break;
        }

        //移动上，显示操作内容
        $7(movie_box).hover(function () {
            var html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='sy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xy'>下移</a>" +
                "<a href='javascript:void(0)'  class='bianji'>编辑</a>" +
                "<a href='javascript:void(0)' class='del' >删除</a></div>";
            $7(this).css({
                "border": "1px solid #0099ff"
            });
            $7(this).children(".wjdc_list").after(html_cz);
        }, function () {
            $7(this).css({
                "border": "1px solid #fff",
                "border-bottom": "1px solid #eee"
            });
            $7(this).children(".kzqy_czbut").remove();
        });

        /**
         * 把movie_box 试题盒放到当前试题的后边
         */
        $7(this).parents(".movie_box").after(movie_box);
    });

    //下移
    $7(".yd_box").on("click", ".movie_box .xy", function () {
        //文字的长度  改为  获取试题数量
        var leng = $7(this).parent().parent().parent(".yd_box").children(".movie_box").length;
        /*dqgs题号*/
        var dqgs = $7(this).parent(".kzqy_czbut").parent(".movie_box").index();

        if (dqgs < leng - 1) {
            //获取整道试题对象
            var czxx = $7(this).parent(".kzqy_czbut").parent(".movie_box");
            //获取下一道题

            //可编辑的试题框,把可编辑的框，移动到不可编辑框的前边
            czxx.next().after(czxx);

            /*
            题号变化
             */
            czxx.find(".nmb").text(czxx.index() + 1);
            czxx.prev().find(".nmb").text(czxx.index());
        } else {
            alert("到底了");
        }
    });

    //上移
    $7(".yd_box").on("click", ".movie_box .sy", function () {
        //获取试题位置
        var dqgs = $7(this).parent(".kzqy_czbut").parent(".movie_box").index();
        if (dqgs > 0) {

            var czxx = $7(this).parent(".kzqy_czbut").parent(".movie_box");

            //可编辑的试题框,把可编辑的框，移动到不可编辑框的前边
            czxx.prev().before(czxx);

            //序号
            czxx.find(".nmb").text(czxx.index() + 1);
            czxx.next().find(".nmb").text(czxx.index() + 2);
        } else {
            alert("到头了");
        }
    });

    //删除
    $7(".yd_box").on("click", ".movie_box .del", function () {
        /*如果点击确定，则则执行下边*/
        var res = confirm("确认删除吗？");
        if (res) {
        	
			var el_chooseV = $7(this).parent(".kzqy_czbut").parent(".movie_box").children(".wjdc_list").children("li").length;
			
			if(el_chooseV == 0){ //没编辑的
				if(addQues > 0) {
					addQues --;
				}
			} 
            var czxx = $7(this).parent(".kzqy_czbut").parent(".movie_box");
            var xh_num = 1;

            /**
             * 循环所有试题，并重新排序
             * 判断是哪类试题，如果是单选，
             */
            czxx.index();
            czxx.nextAll().each(function () {
                var n = $7(this).find(".nmb").text();
                n = parseInt(n) - 1;
                $7(this).find(".nmb").text(n);
            })

            //取消题库区的复选框选择,遍历试题框中的试题，找对应的 id
            var ques_id = czxx.children(".ques_id").prop("id");
            $7(".el_drag").each(function () {
                var ques_id_tiku = $7(this).children(".ques_id").prop("id");
                if(ques_id == ques_id_tiku) {
                    $7(this).children(".el_tiku_checkedButton").removeAttr("checked");
                }
            })

            czxx.remove();
        }
    });


    //富文本框
    var editor;
    var editor1;
    var editor2;
    var chooseOption = false;
    //编辑
    $7(".yd_box").on("click", ".movie_box .bianji", function () {
        //编辑的时候禁止其他操作
        $7(this).siblings().hide();

        //获取单选，多选，判断题的编辑框子
        var dxtm = $7(".danxuan").html();
        var duoxtm = $7(".duoxuan").html();
        var tktm = $7(".panduan").html();
        //接受编辑内容的容器
        var dx_rq = $7(this).parent(".kzqy_czbut").parent(".movie_box").find(".dx_box");

        //从前边导过来的索引值，用来判断显示那个上边的编辑框子
        var title = dx_rq.attr("data-t");

        //题目选项的个数
        var timlrxm = $7(this).parent(".kzqy_czbut").parent(".movie_box").children(".wjdc_list").children("li").length;

        //单选题目
        if (title == 0) {
            dx_rq.show().html(dxtm);
            //模具题目选项的个数
            var bjxm_length = dx_rq.find(".title_itram").children(".kzjxx_iteam").length;
            /*选项的内容*/
            var dxtxx_html = dx_rq.find(".title_itram").children(".kzjxx_iteam").html();

            //添加选项
            for (var i_tmxx = bjxm_length; i_tmxx < timlrxm - 1; i_tmxx++) {
                dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>" + dxtxx_html + "</div>");
            }

            //赋值文本框,  获得已经在界面上的题目的内容
            var texte_bt_val = $7(this).parent(".kzqy_czbut").parent(".movie_box").children(".tm_btitlt").children(".btwenzi").text();

            //把获取的题目放到btwen_text中，也就是编辑文本框中
            dx_rq.find(".btwen_text").val(texte_bt_val);

            //遍历题目选项的内容
            var bjjs = 0;
            $7(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").each(function () {
                /*
                * 判断是否被选中
                * */
                if($7(this).find(".el_box_choose").is(":checked")){
                   dx_rq.children(".title_itram").children(".kzjxx_iteam").eq(bjjs-1).find(".el_answerRadio").prop("checked","true");
                }

                //可选框
                var ktksfcz = $7(this).find("input").hasClass("wenb_input");
                if (ktksfcz) {
                    var jsxz_kk = $7(this).index();
                    dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(jsxz_kk - 1).find("label").remove();
                }
                //题目选项
                var texte_val = $7(this).find("span").text();
                dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(bjjs - 1).find(".input_wenbk").val(texte_val);
                bjjs++
            });
            
            //获取解析内容，把解析内容给解析框
            /*    先删除解析和内容框
             * 1、获取解析框
             * 2、获取解析id
             * */
            /*删除原有答案和解析*/
            var el_analyUp_content = dx_rq.parent().children(".el_answer_analy_box").children(".el_box_analy").text();
            dx_rq.parent().children(".el_answer_analy_box").hide();
            dx_rq.parent().children(".dx_box").children(".el_quesionAnaly").children().text("");
            dx_rq.parent().children(".dx_box").children(".el_quesionAnaly").children().text(el_analyUp_content);

            /*富文本框*/
            editor = KindEditor.create('#editor_id1', {
                resizeType: 1,
                allowPreviewEmoticons: false,
                items: [
                    'table', '|', 'fontsize', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image']
            });

        }

        //多选题目
        if (title == 1) {
            dx_rq.show().html(duoxtm);
            //模具题目选项的个数
            var bjxm_length = dx_rq.find(".title_itram").children(".kzjxx_iteam").length;
            var dxtxx_html = dx_rq.find(".title_itram").children(".kzjxx_iteam").html();
            //添加选项题目
            for (var i_tmxx = bjxm_length; i_tmxx < timlrxm - 1; i_tmxx++) {
                dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>" + dxtxx_html + "</div>");
            }

            //赋值文本框
            //题目标题
            var texte_bt_val = $7(this).parent(".kzqy_czbut").parent(".movie_box").children(".tm_btitlt").children(".btwenzi").text();
            dx_rq.find(".btwen_text").val(texte_bt_val);

            //遍历题目选项的内容
            var bjjs = 0;
            $7(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").each(function () {
                /*
                 * 判断是否被选中
                 * */
                if($7(this).find(".el_box_choose").is(":checked")){
                    dx_rq.children(".title_itram").children(".kzjxx_iteam").eq(bjjs - 1).find(".el_answerCheck").prop("checked","true");
                }
                //可选框
                var ktksfcz = $7(this).find("input").hasClass("wenb_input");
                if (ktksfcz) {
                    var jsxz_kk = $7(this).index();
                    dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(jsxz_kk - 1).find("label").remove();
                }
                //题目选项
                var texte_val = $7(this).find("span").text();
                dx_rq.find(".title_itram").children(".kzjxx_iteam").eq(bjjs - 1).find(".input_wenbk").val(texte_val);
                bjjs++
            });

            //获取解析内容，把解析内容给解析框
            /*    先删除解析和内容框
             * 1、获取解析框
             * 2、获取解析id
             * */
            /*删除原有答案和解析*/
            var el_analyUp_content = dx_rq.parent().children(".el_answer_analy_box").children(".el_box_analy").text();
            dx_rq.parent().children(".el_answer_analy_box").hide();
            dx_rq.parent().children(".dx_box").children(".el_quesionAnaly").children().text("");
            dx_rq.parent().children(".dx_box").children(".el_quesionAnaly").children().text(el_analyUp_content);

            /*富文本框*/
            editor1 = KindEditor.create('#editor_id2', {
                //themeType: 'simple',
                resizeType: 1,
                allowPreviewEmoticons: false,
                items: [
                    'table', '|', 'fontsize', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image']
            });
        }
        //判断题
        if (title == 2) {
            dx_rq.show().html(tktm);
            //赋值文本框
            //题目标题
            var texte_bt_val = $7(this).parent(".kzqy_czbut").parent(".movie_box").children(".tm_btitlt").children(".btwenzi").text();
            dx_rq.find(".btwen_text").val(texte_bt_val);

            //遍历题目选项的内容
            var i = 0;
            var si = 0;
            $7(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").each(function () {
                /*
                 * 判断是否被选中
                 * */
                if(si > 0) {
                    if($7(this).find(".el_box_choose").is(":checked")){
                        dx_rq.children(".title_itram").children(".kzjxx_iteam").children("#el_panduanChoose").children("li").eq(i).find(".el_answerPanduan").prop("checked","true");
                    }
                    i++
                }
                si ++

            });

            //获取解析内容，把解析内容给解析框
            /*    先删除解析和内容框
             * 1、获取解析框
             * 2、获取解析id
             * */
            /*删除原有答案和解析*/
            var el_analyUp_content = dx_rq.parent().children(".el_answer_analy_box").children(".el_box_analy").text();
            dx_rq.parent().children(".el_answer_analy_box").hide();
            dx_rq.parent().children(".dx_box").children(".el_quesionAnaly").children().text("");
            dx_rq.parent().children(".dx_box").children(".el_quesionAnaly").children().text(el_analyUp_content);

            /*富文本框*/
            editor2 = KindEditor.create('#editor_id3', {
                themeType: 'simple',
                resizeType: 1,
                allowPreviewEmoticons: false,
                items: [
                    'table', '|', 'fontsize', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image']
            });
        }

        /*选项触发*/
        $7(".kzjxx_iteam").hover(function () {
            var html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='xsy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xxy'>下移</a>" +
                "<a href='javascript:void(0)' class='xdel' >删除</a></div>";

            if (!($7(this).hasClass("el_panduan_ti"))) {
                $7(this).css({
                    "border": "1px solid #0099ff"
                });
                $7(this).append(html_cz)
            }
        }, function () {
            $7(this).css({
                "border": "1px solid rgb(242,242,242)"
            });
            $7(this).children(".kzqy_czbut").remove();
        })
    });

    //增加选项
    $7(".zjxx").live("click", function () {
        var zjxx_html = $7(this).prev(".title_itram").children(".kzjxx_iteam").html();
        $7(this).prev(".title_itram").append("<div class='kzjxx_iteam'>" + zjxx_html + "</div>");

        /*选项触发*/
        $7(".kzjxx_iteam").hover(function () {
            var html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)'  class='xxy'>下移</a>" +
                "<a href='javascript:void(0)' class='xsy'>上移</a>" +
                "<a href='javascript:void(0)' class='xdel' >删除</a></div>";
            $7(this).css({
                "border": "1px solid #0099ff"
            });
            $7(this).append(html_cz)
        }, function () {
            $7(this).css({
                "border": "1px solid rgb(242,242,242)"
            });
            $7(this).children(".kzqy_czbut").remove();
        })
    });

    //删除一行
    $7(".xdel").live("click", function () {
        /*如果点击确定，则则执行下边*/
        var res = confirm("确认删除吗？");
        if (res) {
            //获取编辑题目的个数
            var zuxxs_num = $7(this).parents(".title_itram").children(".kzjxx_iteam").length;
            if (zuxxs_num > 0) {
                //修改序号
                $7(this).parents(".kzjxx_iteam").nextAll().each(function () {
                    var option_num = $(this).children(".option_num").val();
                    option_num = parseInt(option_num) - 1;
                    $(this).children(".option_num").val(option_num);
                });

                $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").remove();
            } else {
                alert("手下留情");
            }
        }
    });

    //下移
    $7(".xxy").live("click", function () {
        //获取选项数量
        var leng = $7(this).parents(".title_itram").children(".kzjxx_iteam").length;
        //当前选项的位置
        var dqgs = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").index();

        if (dqgs < leng - 1) {
            //本框
            var v1 = $7(this).parents(".kzjxx_iteam");

            //下边框
            $7(this).parents(".kzjxx_iteam").next().after(v1);

            //修改序号
            var cur_option_num = $7(this).parents(".kzjxx_iteam").children(".option_num").val();
            $7(this).parents(".kzjxx_iteam").prev().children(".option_num").val(cur_option_num);
            cur_option_num = parseInt(cur_option_num) + 1;
            $7(this).parents(".kzjxx_iteam").children(".option_num").val(cur_option_num);

        } else {
            alert("到底了");
        }
    });

    //上移
    $7(".xsy").live("click", function () {
        var dqgs = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").index();

        if (dqgs > 0) {
            //本框
            var v1 = $7(this).parents(".kzjxx_iteam");

            //上边框
            $7(this).parents(".kzjxx_iteam").prev().before(v1);

            //修改序号
            var cur_option_num = $7(this).parents(".kzjxx_iteam").children(".option_num").val();
            $7(this).parents(".kzjxx_iteam").next().children(".option_num").val(cur_option_num);
            cur_option_num = parseInt(cur_option_num) - 1;
            $7(this).parents(".kzjxx_iteam").children(".option_num").val(cur_option_num);
        } else {
            alert("到头了");
        }
    });

    //取消编辑
    $7(".dx_box .qxbj_but").live("click", function () {
        $7(this).parent(".bjqxwc_box").parent(".dx_box").parent(".movie_box").children(".el_answer_analy_box").show();
        //alert($7(this).parents(".movie_box").html());
        $7(this).parent(".bjqxwc_box").parent(".dx_box").empty().hide();

        $7(".movie_box").css({
            "border": "1px solid #fff"
        });
        $7(".kzqy_czbut").remove();
    });

    //完成编辑（编辑）
    $7(".swcbj_but").live("click", function () {

        var jcxxxx = $7(this).parent(".bjqxwc_box").parent(".dx_box"); //编辑题目区
        var querstionType = jcxxxx.attr("data-t"); //获取题目类型

        //判断是否选有选项
        var a = false;
        //获取答案号码
        var b = "";
        //遍历选项的文字
        var bjjs_bj = 0;
        
        switch (querstionType) {

            case "0": //单选
                //编辑题目选项的个数
                var bjtm_xm_length = jcxxxx.find(".title_itram").children(".kzjxx_iteam").length; //编辑选项的 选项个数
                var xmtit_length = jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").length - 1; //题目选择的个数

                //赋值文本框,获取html标签中的题目内容
                var el_texte_bt_val_bj = $7("#editor_id1").text();

                /*
                 	要在这里获取编辑后的文本内容
                 */
                editor.sync();
                var html = $7('#editor_id1').val();//原生API
                //将修改过的问题题目展示
                var eltexte_bt_val_bj = jcxxxx.parent(".movie_box").children(".tm_btitlt").children(".btwenzi").text(html);

                //删除选项
                for (var toljs = xmtit_length; toljs > 0; toljs--) {
                    jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(toljs).remove();
                }

                //遍历题目选项的文字
                jcxxxx.children(".title_itram").children(".kzjxx_iteam").each(function () {
                    //选项选择情况和内容
                    var texte_choose = $7(this).children(".el_chooseButton").find(".el_answerRadio").is(":checked");
                    var texte_val_bj = $7(this).find(".input_wenbk").val();

                    var inputType = 'radio';

                    if (texte_choose == true) {
                        var li = '<li>' +
                            '<label><input name="a" class="el_box_choose" type="' + inputType + '" checked disabled value="">' +
                            '<span>' + texte_val_bj + '</span></label>' +
                            '</li>';
                        a = true;
                        b = bjjs_bj;
                    } else {
                        var li = '<li>' +
                            '<label><input name="a" class="el_box_choose" type="' + inputType + '" disabled value="">' +
                            '<span>' + texte_val_bj + '</span></label>' +
                            '</li>';
                        //alert(li)
                    }
                    jcxxxx.parent(".movie_box").children(".wjdc_list").append(li);
                    bjjs_bj++;
                });

                if (!a) {
                    alert("请选择一个选项作为答案！");
                    return;
                } else{
                    //生成答案和解析信息
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").remove();
                    jcxxxx.parent(".movie_box").append('<div class="el_answer_analy_box"></div>');
                    //生成解析信息
                    var el_analy_content = jcxxxx.children(".el_quesionAnaly").children().val();
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").after('解析:'+
                    		'<input class="ques_analy" value="' + el_analy_content + '" type="hidden">'+
                    		'<span class="el_box_analy">'+ el_analy_content +'</span>');
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").after('答案:' +
                    		'<input class="ques_answer" value="' + b + '" type="hidden">' +
                    		'<span class="el_box_answer">' + b + '</span><br/>');

                }
                break;

            case "1": //多选
                //编辑题目选项的个数
                var bjtm_xm_length = jcxxxx.find(".title_itram").children(".kzjxx_iteam").length; //编辑选项的 选项个数
                var xmtit_length = jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").length - 1; //题目选择的个数

                //赋值文本框,获取html标签中的题目内容
                var el_texte_bt_val_bj = $7("#editor_id2").text();

                /*要在这里获取编辑后的文本内容*/
                editor1.sync();
                var html = $7('#editor_id2').val(); //原生API
                //将修改过的问题题目展示
                var eltexte_bt_val_bj = jcxxxx.parent(".movie_box").children(".tm_btitlt").children(".btwenzi").text(html);

                //删除选项
                for (var toljs = xmtit_length; toljs > 0; toljs--) {
                    jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(toljs).remove();
                }

                //遍历题目选项的文字
                jcxxxx.children(".title_itram").children(".kzjxx_iteam").each(function () {
                    //选项选择情况和内容
                    var texte_choose = $7(this).children(".el_chooseButton").find(".el_answerCheck").is(":checked");
                    var texte_val_bj = $7(this).find(".input_wenbk").val();

                    var inputType = 'checkbox';

                    if (texte_choose == true) {
                        var li = '<li>' +
                            '<label><input name="a" class="el_box_choose" type="' + inputType + '" checked disabled value="">' +
                            '<span>' + texte_val_bj + '</span></label>' +
                            '</li>';
                        a = true;
                        b = b + " " + bjjs_bj;
                    } else {
                        var li = '<li><label><input name="a" class="el_box_choose" type="' + inputType + '" disabled value=""><span>' + texte_val_bj + '</span></label></li>';
                    }
                    jcxxxx.parent(".movie_box").children(".wjdc_list").append(li);
                    bjjs_bj++
                });

                if (!a) {
                    alert("请选择一个选项作为答案！");
                    return;
                } else{
                    //生成答案和解析信息
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").remove();
                    jcxxxx.parent(".movie_box").append('<div class="el_answer_analy_box"></div>');
                    
                    //生成解析信息
                    var el_analy_content = jcxxxx.children(".el_quesionAnaly").children().val();
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").after('解析:'+
                    		'<input class="ques_analy" value="' + el_analy_content + '" type="hidden">'+
                    		'<span class="el_box_analy">'+ el_analy_content +'</span>');
                    
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").after('答案:' +
                    		'<input class="ques_answer" value="' + b + '" type="hidden">' +
                    		'<span class="el_box_answer">' + b + '</span><br/>');
                }
                break;


            case "2"://判断

                //赋值文本框,获取html标签中的题目内容
                var el_texte_bt_val_bj = $7("#editor_id3").text();

                /*要在这里获取编辑后的文本内容*/
                editor2.sync();
                var html = $7('#editor_id3').val();//原生API
                //将修改过的问题题目展示,从编辑框转移到界面
                var eltexte_bt_val_bj = jcxxxx.parent(".movie_box").children(	".tm_btitlt").children(".btwenzi").text(html);

                /*清空试题框中的选项*/
                var j = 0;
                jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").each(function () {
                    if(j > 0) {
                        $7(this).remove()
                    }
                    j ++;
                })

                var i = 1;
                var li1,li2;
                $7("#el_panduanChoose").children("li").each(function (){

                    if($7(this).find(".el_answerPanduan").is(":checked")){
                        if(i == 1) {
                            li1 = '<li><label><input name="a" class="el_box_choose" checked type="radio" value=""><span>正确</span></label></li>';
                            li2 = '<li><label><input name="a" class="el_box_choose" type="radio" value=""><span>错误</span></label></li>';
                            b = 0;
                        } else{
                            li1 = '<li><label><input name="a" class="el_box_choose" type="radio" value=""><span>正确</span></label></li>';
                            li2 = '<li><label><input name="a" class="el_box_choose" checked type="radio" value=""><span>错误</span></label></li>';
                            b = 1;
                        }
                        a = true;
                    }
                    i ++;
                })

                //选项选择情况和内容
                jcxxxx.parent(".movie_box").children(".wjdc_list").append(li1);
                jcxxxx.parent(".movie_box").children(".wjdc_list").append(li2);

                jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text(eltexte_bt_val_bj); //将修改过的问题题目展示

                if (!a) {
                    alert("请选择一个选项作为答案！");
                    return;
                } else{
                    //生成答案和解析信息
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").remove();
                    jcxxxx.parent(".movie_box").append('<div class="el_answer_analy_box"></div>');
                    //生成解析信息
                    var el_analy_content = jcxxxx.children(".el_quesionAnaly").children().val();
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").after('解析:'+
                    		'<input class="ques_analy" value="' + el_analy_content + '" type="hidden">'+
                    		'<span class="el_box_analy">'+ el_analy_content +'</span>');
                    
                    jcxxxx.parent(".movie_box").children(".el_answer_analy_box").after('答案:' +
                    		'<input class="ques_answer" value="' + b + '" type="hidden">' +
                    		'<span class="el_box_answer">' + b + '</span><br/>');
                }
                break;
        }
        //清除
        $7(this).parent(".bjqxwc_box").parent(".dx_box").empty().hide();

        /*删除原有内容的函数*/
        function delYuanContent() {
            var czxx = $7(this).parent(".kzqy_czbut").parent(".movie_box");
            var zgtitle_gs = czxx.parent(".yd_box").find(".movie_box").length;
            var xh_num = 1;
            //重新编号
            czxx.parent(".yd_box").find(".movie_box").each(function () {
                $7(".yd_box").children(".movie_box").eq(xh_num).find(".nmb").text(xh_num);
                xh_num++;
                //alert(xh_num);
            });
            czxx.remove();
        };
    });

});
