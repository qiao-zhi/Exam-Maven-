/**
 * 试卷保存
 * Created by yorge on 2017/10/2.
 */
$(function () {
    /* 试卷保存 */
    /**
	 * 点击保存按钮，生成一个form表单 往form表单中插入试题 三种试题循环插入 判断每类试题有多少道，从而确定循环次数
	 * 每循环一次，获取一次试题内容 生成一道可提交的试题 把试题插入到form 中 id name 等循环生成
	 */
    $("#el_saveModify").click(function () {
        var res = confirm("确定退出，并保存当前信息吗？");
        if (res) {
        var form = $("#saveForm");
         
        /* 单选 */
        var danxuan_length = $(".el_danxuan").find(".movie_box").length;
        // alert("单选题数量"+danxuan_length);
        
        // 放标题的试题内容的
        var dan_title_ques_content = $(".danxuan_content");
        
        // 获取大标题题干内容
        var danxuan_bigTitle_Content = $(".danxuanBigTitle").html();
        var danxuan_bigTitle_num = $(".danxuanBigTitle").children(".bigNum").text();
        if(danxuan_bigTitle_num == "一") {
        	danxuan_bigTitle_num = "1";
        } else if(danxuan_bigTitle_num == "二"){
        	danxuan_bigTitle_num = "2";
        } else {
        	danxuan_bigTitle_num = "3";
        }
        // 1 是标题号，2 是标题所有内容
        var danxuan_bigTitle_Content_input1 = "<input type='hidden' name='danxuanDati.bigquestionsequence' value='" +danxuan_bigTitle_num+ "'/>";
        var danxuan_bigTitle_Content_input = "<input type='hidden' name='danxuanDati.bigquestionname' value='" + danxuan_bigTitle_Content + "'/>";
        // 插入到上边
        dan_title_ques_content.append(danxuan_bigTitle_Content_input1);
        dan_title_ques_content.append(danxuan_bigTitle_Content_input);
        
        // 获取选项
        for(var i = 0; i < danxuan_length; i ++) {
            var dan_ques = $(".el_danxuan").find(".movie_box").eq(i);
            // alert(dan_ques.html())
            var dan_ques_title = dan_ques.find(".btwenzi").text();// 试题标题
            var dan_ques_options = dan_ques.children(".wjdc_list").children("li");// 选项
																					// 的父亲
																					// li
            var dan_ques_answer = dan_ques.children(".ques_answer").val();// 答案
            var dan_ques_analy = dan_ques.children(".ques_analy").val();// 解析
            
            // alert(dan_ques_title)

            // 序号题干
            var dan_ques_left = '<input type="hidden" name="danxuanDati.questions['+ i +'].questionsequence" value="'+ i +'">'+
                '<input type="hidden" name="danxuanDati.questions['+ i +'].questioncontent" value="'+ dan_ques_title + '">';
            // 选项
            /**
			 * 循环遍历选项数组， 每遍历一次，判断一次该选项是否为答案
			 */
            var dan_ques_middle="";
            var j = 0;
            dan_ques_options.each(function () {
                    dan_ques_middle = dan_ques_middle +
                        '<input type="hidden" name="danxuanDati.questions[' + i + '].options[' + j + '].optionsequence" value="' + j + '">' +
                        '<input type="hidden" name="danxuanDati.questions[' + i + '].options[' + j + '].optioncontent" value="' + $(this).find("span").text() + '">';
                    j ++ ;
            })
            // 答案
            var dan_ques_answer = '<input type="hidden" name="danxuanDati.questions['+ i +'].answer" value="'+ dan_ques_answer +'"/>';
            // 解析
            var dan_ques_right = '<input type="hidden" name="danxuanDati.questions['+ i +'].analysis" value="'+ dan_ques_analy + '">';

            var dan_ques = dan_ques_left + dan_ques_middle + dan_ques_answer + dan_ques_right;
            
            // 把试题插入到这个单选div中
            dan_title_ques_content.append(dan_ques);
        }
        
        // 多选
        var duoxuan_length = $(".el_duoxuan").children(".movie_box").length;
        // alert("多选题数量"+duoxuan_length);
       
        // 放标题和试题内容的
        var duo_title_ques_content = $(".duoxuan_content");
        // 获取大标题题干内容
        var duoxuan_bigTitle_Content = $(".duoxuanBigTitle").html();
        var duoxuan_bigTitle_num = $(".duoxuanBigTitle").children(".bigNum").text();
        if(duoxuan_bigTitle_num == "一") {
        	duoxuan_bigTitle_num = "1";
        } else if(duoxuan_bigTitle_num == "二"){
        	duoxuan_bigTitle_num = "2";
        } else {
        	duoxuan_bigTitle_num = "3";
        }
        var duoxuan_bigTitle_Content_input1 = "<input type='hidden' name='duoxuanDati.bigquestionsequence' value='"+duoxuan_bigTitle_num+"'/>";
        var duoxuan_bigTitle_Content_input = "<input type='hidden' name='duoxuanDati.bigquestionname' value='" + duoxuan_bigTitle_Content + "'/>"
        // 插入到上边
        duo_title_ques_content.append(duoxuan_bigTitle_Content_input1);
        duo_title_ques_content.append(duoxuan_bigTitle_Content_input);
        
        for(var i = 0; i < duoxuan_length; i ++) {
            var duo_ques = $(".el_duoxuan").children(".movie_box").eq(i);
            var duo_ques_title = duo_ques.find(".btwenzi").text();// 试题标题
            var duo_ques_options = duo_ques.children(".wjdc_list").children("li");// 选项
																					// 的父亲
																					// li
            var duo_ques_answer = duo_ques.children(".ques_answer").val();// 答案
            var duo_ques_analy = duo_ques.children(".ques_analy").val();// 解析

            // 序号题干
            var duo_ques_left = '<input type="hidden" name="duoxuanDati.questions['+ i +'].questionsequence" value="'+ i +'">'+
                '<input type="hidden" name="duoxuanDati.questions['+ i +'].questioncontent" value="'+ duo_ques_title + '">';
            // 选项
            /**
			 * 循环遍历选项数组， 每遍历一次，判断一次该选项是否为答案
			 */
            var duo_ques_middle="";
            var j = 0;
            duo_ques_options.each(function () {
                    duo_ques_middle = duo_ques_middle +
                        '<input type="hidden" name="duoxuanDati.questions[' + i + '].options[' + j + '].optionsequence" value="' + j + '">' +
                        '<input type="hidden" name="duoxuanDati.questions[' + i + '].options[' + j + '].optioncontent" value="' + $(this).find("span").text() + '">';
                    j ++ ;
            })
            // 答案
            var duo_ques_answer = '<input type="hidden" name="duoxuanDati.questions['+ i +'].answer" value="'+ duo_ques_answer +'"/>';
            // 解析
            var duo_ques_right = '<input type="hidden" name="duoxuanDati.questions['+ i +'].analysis" value="'+ duo_ques_analy + '">';

            var duo_ques = duo_ques_left + duo_ques_middle + duo_ques_answer + duo_ques_right;

            // 把试题插入到这个单选div中
            duo_title_ques_content.append(duo_ques);
        }
        
        
        // 判断
        var panduan_length = $(".el_panduan").children(".movie_box").length;

        // 放标题和试题内容的
        var pan_title_ques_content = $(".panduan_content");
        // 获取大标题题干内容
        var panduan_bigTitle_Content = $(".panduanBigTitle").html();
        var panduan_bigTitle_num = $(".panduanBigTitle").children(".bigNum").text();
        if(panduan_bigTitle_num == "一") {
        	panduan_bigTitle_num = "1";
        } else if(panduan_bigTitle_num == "二"){
        	panduan_bigTitle_num = "2";
        } else {
        	panduan_bigTitle_num = "3";
        }
        var panduan_bigTitle_Content_input1 = "<input type='hidden' name='panduanDati.bigquestionsequence' value='" +panduan_bigTitle_num+"'/>";
        var panduan_bigTitle_Content_input = "<input type='hidden' name='panduanDati.bigquestionname' value='" + panduan_bigTitle_Content + "'/>";         
        // 插入到上边
        pan_title_ques_content.append(panduan_bigTitle_Content_input1);
        pan_title_ques_content.append(panduan_bigTitle_Content_input);
        
        for(var i = 0; i < panduan_length; i ++) {
            var pan_ques = $(".el_panduan").children(".movie_box").eq(i);
            var pan_ques_title = pan_ques.find(".btwenzi").text();// 试题标题
            var pan_ques_options = pan_ques.children(".wjdc_list").children("li");// 选项
																					// 的父亲
																					// li
            var pan_ques_answer = pan_ques.children(".ques_answer").val();// 答案
            var pan_ques_analy = pan_ques.children(".ques_analy").val();// 解析

            // 序号题干
            var pan_ques_left = '<input type="hidden" name="panduanDati.questions['+ i +'].questionsequence" value="'+ i +'">'+
                '<input type="hidden" name="panduanDati.questions['+ i +'].questioncontent" value="'+ pan_ques_title + '">';
            
            // 选项
            /**
			 * 第一个是正确，第二个是错误
			 */
            var pan_ques_middle="";
                    pan_ques_middle = 
                        '<input type="hidden" name="panduanDati.questions[' + i + '].options[0].optionsequence" value="1">' +
                        '<input type="hidden" name="panduanDati.questions[' + i + '].options[0].optioncontent" value="正确">'+
                        '<input type="hidden" name="panduanDati.questions[' + i + '].options[1].optionsequence" value="2">' +
                        '<input type="hidden" name="panduanDati.questions[' + i + '].options[1].optioncontent" value="错误">';
            // 答案
            var pan_ques_answer = '<input type="hidden" name="panduanDati.questions['+ i +'].answer" value="'+ pan_ques_answer +'"/>';
            // 解析
            var pan_ques_right = '<input type="hidden" name="panduanDati.questions['+ i +'].analysis" value="'+ pan_ques_analy + '">';

            var pan_ques = pan_ques_left + pan_ques_middle + pan_ques_answer + pan_ques_right;

            // 把试题插入到这个单选div中
            pan_title_ques_content.append(pan_ques);
            
        }
        /**
		 * 调用保存试卷的函数
		 */
        savePaper();
        }
    });
    
})

// 保存试卷
function savePaper(){
		$.ajax({
			url:'paper_savePaper.action',
			async:true,
			type:'post',
			data:$("#saveForm").serialize(),
			success:function(data){
	// 保存成功跳转到试卷管理页面，保存失败只提示保存失败
				if(data=="试卷保存成功！"){
					alert(data);
					window.location.href=contextPath+'/view/examParper/examPaper/examparperManage.jsp';
				}
				else{
					alert(data);
				}
			},
			error:function(){
				alert("添加试卷出错了！！！");
			}
		});
}




