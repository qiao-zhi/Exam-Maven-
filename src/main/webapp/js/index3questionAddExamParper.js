/**
 * Created by yorge on 2017/7/24.
 */
$7(function () {
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
        var res = true;
        while (res) {
            thisQuestion = thisQuestion.next();
            if (thisQuestion.hasClass("movie_box")) {
                var titleNum = thisQuestion.children(".tm_btitlt").children(".nmb").text();
                titleNum = parseInt(titleNum) + 1;
                thisQuestion.children(".tm_btitlt").children(".nmb").text(titleNum)
            } else {
                res = false;
            }
        }

        var index = $7(this).attr("value");

        //定义一个试题盒
        var quesBox = '<li class="el_tiBoxli"></li>';//放试题内容
        var quesBigBox = $(".el_ul2[value= " + index + "]");//放每个题的大框,根据index 来判断是那种试题类框

        switch (index) {
            case "0": //单选
            case "1": //多选
            case "2": //判断
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
                //试题题干
                var ques = '<ul class="el_drop" style="height:200px;">' +
                    '<li class="el_BoxliScore el_addEPBoxlili">分值：<input type="text" value="0" size="3"/></li>' +
                    '<li class="el_BoxliContent el_addEPBoxlili" style="overflow-y:scroll;">' +
                    '<div class="tm_btitlt">' +
                    '<i class="btwenzi">请编辑sadf问题</i><span class="tip_wz">' + danxuan + '</span>' +
                    '</div>' +
                    '<ul class="el_Options"></ul>' +
                    '</li>' +
                    '<li class="el_BoxliEditor"><a href="javascript:void(0)" class="bianji">编辑</a></li>' +
                    '<li class="el_BoxliAdd el_addEPBoxlili">' +
                    '<a href="javascript:void(0)" class="tianjia" value="0"><span class="el_tiBoxAdd">添加</span></a>' +
                    '</li>' +
                    '<li class="el_BoxliDel el_addEPBoxlili"><span class="el_tiBoxDel">删除</span></li>' +
                    '</ul>';

                /*把内容放到试题盒中*/
                quesBox = $7(quesBox).append(ques);

                //给试题添加试题类型,点击编辑后，编辑区的内容就放到 dx_box 这个div区域中
                quesBox = $7(quesBox).append('<div class="dx_box" data-t="' + index + '"></div>');
                break;
        }

        /**
         * 放到类盒子
         * 把movie_box 试题盒放到当前试题的后边
         */
        $7(this).parents(".el_tiBoxli").after(quesBox);
    });

    //富文本框
    var editor;
    var editor1;
    var editor2;
    //编辑,点击编辑按钮，触发事件，弹出编辑框
    $7(".el_BoxliEditor").live("click", function () {

        //编辑的时候禁止编辑后边的其他操作,包括编辑，所有内容都隐藏
        $7(this).nextAll().hide();
        $7(this).hide();

        //获取单选，多选，判断题的编辑框子,类别的大框子
        var dxtm = $7(".danxuan").html();
        var duoxtm = $7(".duoxuan").html();
        var tktm = $7(".panduan").html();

        //接受编辑内容的容器
        var dx_rq = $7(this).parents(".el_tiBoxli").find(".dx_box");

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

            //添加选项题目
            for (var i_tmxx = bjxm_length; i_tmxx < timlrxm - 1; i_tmxx++) {
                dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>" + dxtxx_html + "</div>");
            }

            //赋值文本框,  获得已经在界面上的题目的内容
            //题目标题
            var texte_bt_val = $7(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text();

            //把获取的题目放到btwen_text中，也就是编辑文本框中
            dx_rq.find(".btwen_text").val(texte_bt_val);

            //遍历题目的文字,遍历已有题目,放到kindeditor中
            var texte_bt_val = $7(this).prev().text();
            dx_rq.find(".btwen_text").val(texte_bt_val);

            /* 遍历题目选项的个数和内容，放到编辑框中。
             * 需要判断是否是第一次，根据试题库框中是否有选项来判断
             * 第一次，试题内容没有选项，
             * 之后需要先删除编辑框内的所有选项。再去根据试题框中选项个数，去循环生成。
             */
            //判断是否有选项
            var liNums = $(this).parent(".el_drop").children(".el_BoxliContent ").children(".el_Options").children("li").length;
            if (liNums != 0) {
                //删除
                dx_rq.children(".title_itram").children(".kzjxx_iteam").remove();

                //再从试题框中给编辑区添加选项
                $7(this).parents(".el_drop").children(".el_BoxliContent").children(".el_Options").children("li").each(function () {
                    var liText = $7(this).text();
                    dx_rq.children(".title_itram").append(
                        '<div class="kzjxx_iteam">' +
                        '<span class="el_chooseButton">' +
                            '<label><input name="el_answerRaido" type="radio"/>设置为答案</label>' +
                        '</span>' +
                        '<input name="" type="text" class="input_wenbk" ' +
                        'value="' + liText + '" placeholder="选项">' +
                        '<input type="file" value="图片上传" name=图片上传" accept=".jpg,.png" class="el_loadPictrue"/>' +
                        '</div>');
                    $(".title_itram").children(".kzjxx_iteam").find(".input_wenbk").val()
                });
            }

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
            /*选项的内容*/
            var dxtxx_html = dx_rq.find(".title_itram").children(".kzjxx_iteam").html();
            //添加选项题目
            for (var i_tmxx = bjxm_length; i_tmxx < timlrxm - 1; i_tmxx++) {
                dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>" + dxtxx_html + "</div>");
            }

            //赋值文本框,  获得已经在界面上的题目的内容
            //题目标题
            var texte_bt_val = $7(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text();

            //把获取的题目放到btwen_text中，也就是编辑文本框中
            dx_rq.find(".btwen_text").val(texte_bt_val);

            //遍历题目的文字,遍历已有题目,放到kindeditor中
            var texte_bt_val = $7(this).prev().text();
            dx_rq.find(".btwen_text").val(texte_bt_val);


            /* 遍历题目选项的个数和内容，放到编辑框中。
             * 需要判断是否是第一次，根据试题库框中是否有选项来判断
             * 第一次，试题内容没有选项，
             * 之后需要先删除编辑框内的所有选项。再去根据试题框中选项个数，去循环生成。
             */
            //判断是否有选项
            var liNums = $(this).parent(".el_drop").children(".el_BoxliContent ").children(".el_Options").children("li").length;
            if (liNums != 0) {
                //删除
                dx_rq.children(".title_itram").children(".kzjxx_iteam").remove();

                //再从试题框中给编辑区添加选项
                $7(this).parents(".el_drop").children(".el_BoxliContent").children(".el_Options").children("li").each(function () {
                    var liText = $7(this).text();
                    dx_rq.children(".title_itram").append(
                        '<div class="kzjxx_iteam">' +
                        '<span class="el_chooseButton">' +
                        '<label><input name="el_answerRaido" type="checkbox"/>设置为答案</label>' +
                        '</span>' +
                        '<input name="" type="text" class="input_wenbk" ' +
                        'value="' + liText + '" placeholder="选项">' +
                        '<input type="file" value="图片上传" name=图片上传" accept=".jpg,.png" class="el_loadPictrue"/>' +
                        '</div>');
                    $(".title_itram").children(".kzjxx_iteam").find(".input_wenbk").val()
                });
            }

            /*富文本框*/
            editor1 = KindEditor.create('#editor_id2', {
                resizeType: 1,
                allowPreviewEmoticons: false,
                allowImageUpload: false,
                items: [
                    'table', '|', 'fontsize', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image']
            });
        }

        //判断题
        if (title == 2) {
            dx_rq.show().html(tktm);
            //模具题目选项的个数
            var bjxm_length = dx_rq.find(".title_itram").children(".kzjxx_iteam").length;
            /*选项的内容*/
            var dxtxx_html = dx_rq.find(".title_itram").children(".kzjxx_iteam").html();
            //添加选项题目
            for (var i_tmxx = bjxm_length; i_tmxx < timlrxm - 1; i_tmxx++) {
                dx_rq.find(".title_itram").append("<div class='kzjxx_iteam'>" + dxtxx_html + "</div>");
            }

            //赋值文本框,  获得已经在界面上的题目的内容
            //题目标题
            var texte_bt_val = $7(this).parent(".kzqy_czbut").parent(".movie_box").find(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text();

            //把获取的题目放到btwen_text中，也就是编辑文本框中
            dx_rq.find(".btwen_text").val(texte_bt_val);

            //遍历题目的文字,遍历已有题目,放到kindeditor中
            var texte_bt_val = $7(this).prev().text();
            dx_rq.find(".btwen_text").val(texte_bt_val);

            /* 遍历题目选项的个数和内容，放到编辑框中。
             * 需要判断是否是第一次，根据试题库框中是否有选项来判断
             * 第一次，试题内容没有选项，
             * 之后需要先删除编辑框内的所有选项。再去根据试题框中选项个数，去循环生成。
             */
            //判断是否有选项
            var liNums = $(this).parent(".el_drop").children(".el_BoxliContent ").children(".el_Options").children("li").length;
            if (liNums != 0) {
                //删除
                dx_rq.children(".title_itram").children(".kzjxx_iteam").remove();

                //再从试题框中给编辑区添加选项
                $7(this).parents(".el_drop").children(".el_BoxliContent").children(".el_Options").children("li").each(function () {
                    var liText = $7(this).text();
                    dx_rq.children(".title_itram").append(
                        '<div class="kzjxx_iteam">' +
                        '<ul id="el_panduanChoose">'+
                        '<li>'+
                            '<span><label><input name="el_answerPanduan" type="radio"/> 设置为答案</label></span>'+
                            '<span>&nbsp;&nbsp;正确</span>'+
                        '</li>'+
                        '<li>'+
                            '<span><label><input name="el_answerPanduan" type="radio"/> 设置为答案</label></span>'+
                            '<span>&nbsp;&nbsp;错误</span>'+
                        '</li>'+
                        '</ul>'+
                        '</div>');
                    $(".title_itram").children(".kzjxx_iteam").find(".input_wenbk").val()
                });
            }a

            /*富文本框*/
            editor2 = KindEditor.create('#editor_id3', {
                themeType: 'simple',
                resizeType: 1,
                allowPreviewEmoticons: false,
                allowImageUpload: false,
                items: [
                    'table', '|', 'fontsize', 'bold', 'italic', 'underline',
                    'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', '|', 'image']
            });
        }

        /*选项触发,显示操作*/
        $7('.kzjxx_iteam').mouseenter(function () {
            var html_cz = "<div class='kzqy_czbut'>" +
                "<a href='javascript:void(0)' class='xsy'>上移</a>" +
                "<a href='javascript:void(0)'  class='xxy'>下移</a>" +
                "<a href='javascript:void(0)' class='xdel' >删除</a></div>";
            $(this).css({
                "border": "1px solid #0099ff"
            });
            $(this).append(html_cz)
        });
        $7('.kzjxx_iteam').mouseleave(function () {
            $(this).css({
                "border": "1px solid rgb(242,242,242)"
            });
            $(this).children(".kzqy_czbut").remove();
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

    //删除一行，选项删除
    $7(".xdel").live("click", function () {
        /*如果点击确定，则则执行下边*/
        var res = confirm("确认删除吗？");
        if (res) {
            //获取编辑题目的个数
            var zuxxs_num = $7(this).parents(".title_itram").children(".kzjxx_iteam").length;
            if (zuxxs_num > 0) {
                $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").remove();
            } else {
                alert("手下留情");
            }
        }
    });

    //选项下移
    $7(".xxy").live("click", function () {
        //获取选项数量
        var leng = $7(this).parents(".title_itram").children(".kzjxx_iteam").length;
        //当前选项的位置
        var dqgs = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").index();

        if (dqgs < leng - 1) {
            //本框的值
            var v1 = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").children(".input_wenbk");
            var v1Value = v1.val();
            //下边框的值
            var v2 = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").next().children(".input_wenbk");
            var v2Value = v2.val();

            /*获取本选项*/
            var c1 = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam");
            //获取前一道
            var c2 = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").next();
            c1.children(".input_wenbk").val(v2Value);
            c2.children(".input_wenbk").val(v1Value);
        } else {
            alert("到底了");
        }
    });

    //选项上移
    $7(".xsy").live("click", function () {
        var dqgs = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").index();
        if (dqgs > 0) {
            //本框的值
            var v1 = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").children(".input_wenbk");
            var v1Value = v1.val();
            //上边框的值
            var v2 = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").prev().children(".input_wenbk");
            var v2Value = v2.val();

            /*获取本选项*/
            var c1 = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam");
            //获取前一道
            var c2 = $7(this).parent(".kzqy_czbut").parent(".kzjxx_iteam").prev();
            c1.children(".input_wenbk").val(v2Value);
            c2.children(".input_wenbk").val(v1Value);

        } else {
            alert("到头了");
        }
    });

    //取消编辑
    $7(".dx_box .qxbj_but").live("click", function () {
        $7(this).parent(".bjqxwc_box").parent(".dx_box").empty().hide();

    });




    /* 在选项中，选择一个选项作为答案
     * 判断是否有选项被设定为答案
     * 如果有，则设置chooseOption = true;
     * */
    /*单选*/
    var chooseOption = false;
    $7(".el_chooseButton").live("click",function () {
        chooseOption = true;
    })


    //完成编辑（编辑）
    $7(".swcbj_but").live("click", function () {

        var jcxxxx = $7(this).parent(".bjqxwc_box").parent(".dx_box"); //编辑题框对象
        var querstionType = jcxxxx.attr("data-t"); //获取题目类型

        switch (querstionType) {
            case "0": //单选
                //编辑题目选项的个数
                var bjtm_xm_length = jcxxxx.find(".title_itram").children(".kzjxx_iteam").length;

                //题目选择的个数,已有的题目
                var xmtit_length = jcxxxx.parent(".el_tiBoxli").children(".el_drop").children(".el_BoxliContent").children(".el_Options").children("li").length;

                /*
                 要在这里获取编辑后的文本内容
                 异步加载
                 */
                editor.sync();
                var html = $7('#editor_id1').val();//原生API
                //将修改过的问题题目展示,展示到tm——btitle标题中的btwenzi，后边还有个试题类型。
                var eltexte_bt_val_bj = jcxxxx.parent(".el_tiBoxli").children(".el_drop").find(".tm_btitlt").children(".btwenzi").text(html);

                //删除试题框原有选项
                if (xmtit_length > 0) {
                    jcxxxx.parent(".el_tiBoxli").children(".el_drop").children(".el_BoxliContent").children(".el_Options").children("li").remove();
                }

                //遍历编辑区的所有选项，同时给试题显示区域显示编辑的选项
                var bjjs_bj = 0;
                jcxxxx.children(".title_itram").children(".kzjxx_iteam").each(function () {
                    //题目选项
                    var texte_val_bj = $7(this).find(".input_wenbk").val(); //获取填写信息

                    var inputType = 'radio';

                    //选项，定义选项内容
                    var li = '<li><label><input name="a" type="' + inputType + '" value=""><span>' + texte_val_bj + '</span></label></li>';
                    jcxxxx.parent(".el_tiBoxli").children(".el_drop").children(".el_BoxliContent").children(".el_Options").append(li);

                    bjjs_bj++;
                });

                /* 如果chooseOption 为 true，则设置好了答案。
                 * 否则，提示，设置答案，并不能提交。
                 * */
                if(!chooseOption){
                    alert("请选择一个选项作为答案！");
                    return;
                }
                break;

                /*

         case "1": //多选
         //编辑题目选项的个数
         var bjtm_xm_length = jcxxxx.find(".title_itram").children(".kzjxx_iteam").length; //编辑选项的 选项个数
         var xmtit_length = jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").length - 1; //题目选择的个数

         //赋值文本框,获取html标签中的题目内容
         //题目标题
         //var texte_bt_val_bj = jcxxxx.find(".btwen_text").val(); //获取问题题目
         var el_texte_bt_val_bj = $7("#editor_id2").text();

         /!*
         要在这里获取编辑后的文本内容
         *!/
         editor1.sync();
         var html = $7('#editor_id2').val(); //原生API
         //将修改过的问题题目展示
         var eltexte_bt_val_bj = jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text(html);

         //删除选项
         for (var toljs = xmtit_length; toljs > 0; toljs--) {
         jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(toljs).remove();
         }

         //遍历题目项目的文字
         var bjjs_bj = 0;
         jcxxxx.children(".title_itram").children(".kzjxx_iteam").each(function () {
         //题目选项
         var texte_val_bj = $7(this).find(".input_wenbk").val(); //获取填写信息

         var inputType = 'radio';
         //jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(bjjs_bj + 1).find("span").text(texte_val_bj);
         if (querstionType == "1") {
         inputType = 'checkbox';
         }
         var li = '<li><label><input name="a" type="' + inputType + '" value=""><span>' + texte_val_bj + '</span></label></li>';
         jcxxxx.parent(".movie_box").children(".wjdc_list").append(li);

         bjjs_bj++
         //可填空
         var kxtk_yf = $7(this).find(".fxk").is(':checked');
         if (kxtk_yf) {
         //第几个被勾选
         var jsxz = $7(this).index();
         //alert(jsxz);
         jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(jsxz + 1).find("span").after("<input name='' type='text' class='wenb_input'>");
         }
         });

         break;
         case "2"://判断

         //赋值文本框,获取html标签中的题目内容
         //题目标题
         //var texte_bt_val_bj = jcxxxx.find(".btwen_text").val(); //获取问题题目
         var el_texte_bt_val_bj = $7("#editor_id3").text();

         /!*
         要在这里获取编辑后的文本内容
         *!/
         editor2.sync();
         var html = $7('#editor_id3').val();//原生API
         //将修改过的问题题目展示,从编辑框转移到界面
         var eltexte_bt_val_bj = jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text(html);


         /!* //遍历题目项目的文字
         var bjjs_bj = 0;
         jcxxxx.children(".title_itram").children(".kzjxx_iteam").each(function () {
         //题目选项
         var texte_val_bj = $7(this).find(".input_wenbk").val(); //获取填写信息

         var inputType = 'radio';
         //jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(bjjs_bj + 1).find("span").text(texte_val_bj);
         if (querstionType == "1") {
         inputType = 'checkbox';
         }
         var li = '<li><label><input name="a" type="' + inputType + '" value=""><span>' + texte_val_bj + '</span></label></li>';
         jcxxxx.parent(".movie_box").children(".wjdc_list").append(li);

         bjjs_bj++
         //可填空
         var kxtk_yf = $7(this).find(".fxk").is(':checked');
         if (kxtk_yf) {
         //第几个被勾选
         var jsxz = $7(this).index();
         //alert(jsxz);
         jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(jsxz + 1).find("span").after("<input name='' type='text' class='wenb_input'>");
         }
         });*!/

         jcxxxx.parent(".movie_box").children(".wjdc_list").children("li").eq(0).find(".tm_btitlt").children(".btwenzi").text(eltexte_bt_val_bj); //将修改过的问题题目展示
         break;*/
        }

        //清除
        $7(this).parent(".bjqxwc_box").parent(".dx_box").empty().hide();

        /*显示操作按钮*/
        $7(".el_BoxliEditor").show();
        $7(".el_BoxliEditor").nextAll().show();
    });
});


