
$(function () {
    var i0 = $(".el_Menu .el_MenuContent").children("a").attr("href");
    var h0 = $(i0).css("height");
    $(".el_mainContent").css("height", h0);

    $(".el_Menu .el_MenuContent").click(function () {
        var i = $(this).children("a").attr("href");
        var h = $(i).css("height");//获取点击的页面的高

        //把这个高给这个主界面
        $(".el_mainContent").css("height", h);

        h = h.substring(0, h.length - 2);
        if (h < 530) {
            $(".footer").css({"position": "fixed", "bottom": "0"})
        } else {
            $(".footer").css({"position": "relative"})
        }
    })

})

//图片上传预览    IE是用了滤镜。
function previewImage(file) {
    var MAXWIDTH = 75;
    var MAXHEIGHT = 105;
    var div = document.getElementById('preview');
    if (file.files && file.files[0]) {
        div.innerHTML = '<img id=imghead onclick=$("#previewImg").click()>';
        var img = document.getElementById('imghead');
        img.onload = function () {
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            img.width = rect.width;
            img.height = rect.height;
//                 img.style.marginLeft = rect.left+'px';
            img.style.marginTop = rect.top + 'px';
        }
        var reader = new FileReader();
        reader.onload = function (evt) {
            img.src = evt.target.result;
        }
        reader.readAsDataURL(file.files[0]);
    }
    else //兼容IE
    {
        var sFilter = 'filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
        file.select();
        var src = document.selection.createRange().text;
        div.innerHTML = '<img id=imghead>';
        var img = document.getElementById('imghead');
        img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        status = ('rect:' + rect.top + ',' + rect.left + ',' + rect.width + ',' + rect.height);
        div.innerHTML = "<div id=divhead style='width:" + rect.width + "px;height:" + rect.height + "px;margin-top:" + rect.top + "px;" + sFilter + src + "\"'></div>";
    }
}
/*算法，根据实际高宽，算现在需要的高宽*/
function clacImgZoomParam(maxWidth, maxHeight, width, height) {
    var param = {top: 0, left: 0, width: width, height: height};
    if (width > maxWidth || height > maxHeight) {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;

        if (rateWidth > rateHeight) {
            param.width = maxWidth;
            param.height = Math.round(height / rateWidth);
        } else {
            param.width = Math.round(width / rateHeight);
            param.height = maxHeight;
        }
    }
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}


/*修改密码*/
function pwStrength(pwd) {
    O_color = "#eeeeee";
    L_color = "#ffd8b4";
    M_color = "#ffaf56";
    H_color = "#e85959";
    if (pwd == null || pwd == '') {
        Lcolor = Mcolor = Hcolor = O_color;
    } else {
        S_level = checkStrong(pwd);
        switch (S_level) {
            case 0:
                Lcolor = Mcolor = Hcolor = O_color;
            case 1:
                Lcolor = L_color;
                Mcolor = Hcolor = O_color;
                break;
            case 2:
                Lcolor = L_color;
                Mcolor = M_color;
                Hcolor = O_color;
                break;
            default:
                Lcolor = L_color;
                Mcolor = M_color;
                Hcolor = H_color;
        }
    }
    $("#strength_L").css('background-color', Lcolor);
    $("#strength_M").css('background-color', Mcolor);
    $("#strength_H").css('background-color', Hcolor);
    return;
}

//判断输入密码的类型
function CharMode(iN) {
    if (iN >= 48 && iN <= 57) //数字
        return 1;
    if (iN >= 65 && iN <= 90) //大写
        return 2;
    if (iN >= 97 && iN <= 122) //小写
        return 4;
    else return 8;
}
//bitTotal函数
//计算密码模式
function bitTotal(num) {
    modes = 0;
    for (i = 0; i < 4; i++) {
        if (num & 1) modes++;
        num >>>= 1;
    }
    return modes;
}
//返回强度级别
function checkStrong(sPW) {
    if (sPW.length <= 4) return 0; //密码太短
    Modes = 0;
    for (i = 0; i < sPW.length; i++) {
        //密码模式
        Modes |= CharMode(sPW.charCodeAt(i));
    }
    return bitTotal(Modes);
}

/*********************************************************************************************************/

/***************************页面加载函数***************************/

$(function(){
	findNotStartExamInfo();
	findOnlineExamInfo();
	findOnlineExamEmployeeInInfo();
	
});

/***************************当前考试的相关方法***************************/
//全局变量
var timeout;
//设置倒计时的时间范围，暂时设置为10个小时
var indexCount = 36000;

//根据身份证号获取当前用户的未开始的考试信息
function findNotStartExamInfo(){
	$.ajax({
		url:"onlineExam_findAllNotStartExamInfo.action",
		data:{"idCard":employeeInIdCard},
		dataType:"json",
		type:"post",
		success:showNotStartExamInfo,
		error:function(){
			//alert("请求失败！")
		}
	});
}
//显示所有未开始的考试信息
function showNotStartExamInfo(data){
	var notStartExamInfoList = data.notStartExamInfo;
	//当当前用户存在尚未考试的信息时，显示未考试的信息
	if(notStartExamInfoList.length>0){	
		var notStartExamInfoListStr = "";
		for(var i=0;i<notStartExamInfoList.length;i++){
			var startTime = Format(new Date(notStartExamInfoList[i].starttime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm");
			var endTime = Format(new Date(notStartExamInfoList[i].endtime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm");
			//计算的考试时间
			//var lengthOfExam = parseInt((new Date(endTime) - new Date(startTime))/1000/60);
			var danceTime = parseInt((getServerDate() - new Date(notStartExamInfoList[i].starttime.replace(/T/g," ").replace(/-/g,"/"))));
			notStartExamInfoListStr  +="<div class='el_exam'><div class='el_examName'>"
						+"<span>考试名称：<span>"+notStartExamInfoList[i].examname+"</span></span>"
						+"<span class='el_examTip'>提示：将在 <strong style='color:red' id='countDownTime_"+i+"'></strong> 后进入考试，请做好准备</span></div>"
						+"<div class='el_examInfoDiv'><ul><li>"
						+"开考时间：<span class='exam_StartTime'>"+startTime+"</span>"
						+"结束时间：<span>"+endTime+"</span></li>"
						+"<li><p>考试时长：</p><span>"+notStartExamInfoList[i].answertime+"分钟</span></li></ul>";
			/*notStartExamInfoListStr	+= "<a href='onlineExam_findExamAndSpecificPaperInfo.action?examId="+notStartExamInfoList[i].examid+"&idCard="+employeeInIdCard+"' target='_blank'>";*/
			//a标签post方式提交
			var examAndIdCardInfo = '{"idCard":"'+employeeInIdCard+'","examId":"'+notStartExamInfoList[i].examid+'"}';
			notStartExamInfoListStr	+= "<a style='display:none;' href=javascript:doPost('onlineExam_findExamAndSpecificPaperInfo.action','"+examAndIdCardInfo+"') >";
			if(danceTime<0){
				notStartExamInfoListStr +="<input type='button' class='btn btn-info el_examStartButton' value='开始考试' disabled/></a></div></div>";
			}else{
				notStartExamInfoListStr +="<input type='button' class='btn btn-info el_examStartButton' value='开始考试'/></a></div></div>";
			}
								
		}
		$("#showNotStartExamInfoList").empty();
		$("#showNotStartExamInfoList").html(notStartExamInfoListStr);
		//获取所有考试的开始时间
		timeout = setInterval("findAllStrartExamTime()",1000);
	}
}

/***************************所有考试的相关方法***************************/
//点击查询执行的操作
function queryOnlineExamInfo(){
	//执行查询操作前先将隐藏域中的值清空当前页清空或改为第一页
	$("#currentPage").val("");
	//调用查询方法
	findOnlineExamInfo();
}
//查询方法
function findOnlineExamInfo(){
	$.ajax({
		url:"onlineExam_getOnlineExamInfoByCondition.action",
		data:$("#form_onlineExamEmployeeInfo").serialize(),
		type:"post",
		dataType:"json",
		success:showOnlineExamInfo,
		error:function(){
			//alert("请求失败！")
		}
	})
}
//显示考试信息表格
function showOnlineExamInfo(data){
	var result = data;
	//console.log(result)
	//当前页显示条数
	var currentCount = result.pageBean.currentCount;
	var onlineExamInfoList = result.pageBean.productList;
	var showOnlineExamInfoList = "";
	for(var i=0;i<onlineExamInfoList.length;i++){
		var index = i+1;
		showOnlineExamInfoList += "<tr><td>"+(index + (result.pageBean.currentPage - 1) * currentCount)
						+"</td><td>"
						+onlineExamInfoList[i].examname+"</td><td>"
						+Format(new Date(onlineExamInfoList[i].starttime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")
						+"到"+Format(new Date(onlineExamInfoList[i].endtime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")+"</td><td>"
						+onlineExamInfoList[i].level.toString().replace("1","厂级").replace("2","部门级").replace("3","班组级") +"</td><td>"	
						+onlineExamInfoList[i].paperscore+"</td>";
			if(onlineExamInfoList[i].status=="已结束"){
				showOnlineExamInfoList += "<td>"
					+onlineExamInfoList[i].status+"</td><td>";
					if(onlineExamInfoList[i].grade!=null){
					showOnlineExamInfoList +=onlineExamInfoList[i].grade+"</td><td>"
						+onlineExamInfoList[i].ispass+"</td><td>"
					}else{
						showOnlineExamInfoList +="--</td><td>--</td><td>";
					}					
					/*showOnlineExamInfoList += "<a href='onlineExam_echoOnlineExamPaperAndAnswerInfo.action?paperId="+onlineExamInfoList[i].paperid+"&idCard="+onlineExamInfoList[i].idcode+"&examId="+onlineExamInfoList[i].examid+"' target='_blank'>查看试卷</a>"
					+"</td></tr>";	*/
					var parameterStr =  "{'paperId':'"+onlineExamInfoList[i].paperid+"','idCard':'"+onlineExamInfoList[i].idcode+"','examId':'"+onlineExamInfoList[i].examid+"'}"
					var actionUrl = "onlineExam_echoOnlineExamPaperAndAnswerInfo.action";
					//a标签采用post方式传递数据
					showOnlineExamInfoList += '<a href=javascript:doPost("'+actionUrl+'","'+parameterStr+'")>查看试卷</a>'
					+"</td></tr>";						

			}else if(onlineExamInfoList[i].status=="正在答题" && onlineExamInfoList[i].answerstatus=="已交卷"){
				showOnlineExamInfoList += "<td>"
					+onlineExamInfoList[i].answerstatus+"</td><td>"
					+onlineExamInfoList[i].grade+"</td><td>"
					+onlineExamInfoList[i].ispass+"</td><td>"
					+"--</td></tr>";	
			}else{
				showOnlineExamInfoList += "<td class='danger'>"
					+onlineExamInfoList[i].status
					+"</td><td>--</td><td>--</td><td>--</td></tr>";	
			}
	}
	$("#onlineExamInfoList").empty();
	$("#onlineExamInfoList").append(showOnlineExamInfoList);
	//当前页
	var currentPage = result.pageBean.currentPage;
	//总条数
	var totalCount = result.pageBean.totalCount;
	//调用分页的函数
	gradeManage_page(currentPage, totalCount,currentCount);
}

/*********************************分页****************************************/

function gradeManage_page(currentPage, totalCount,currentCount) {
	$('#examInterface_paginationIDU').pagination({
        //组件属性
        "total": totalCount,//数字 当分页建立时设置记录的总数量 
        "pageSize": currentCount,//数字 每一页显示的数量 10
        "pageNumber": currentPage,//数字 当分页建立时，显示的页数 1
        "pageList": [8,15,20],//数组 用户可以修改每一页的大小，
        //功能
        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
        "onSelectPage": function (pageNumber, b) {
        	//向隐藏域中设置值
        	$("#currentPage").val(pageNumber);
        	$("#currentCount").val(b);
        	//调用查找函数
        	findOnlineExamInfo();
        }
    });

}


//倒计时方法
function downTimer(startTime,seq){
	 var ts = (new Date(startTime)) - (getServerDate());//计算剩余的毫秒数      
     var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);//计算剩余的天数      
     var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10);//计算剩余的小时数      
     var mm = parseInt(ts / 1000 / 60 % 60, 10);//计算剩余的分钟数      
     var ss = parseInt(ts / 1000 % 60, 10);//计算剩余的秒数      
     dd = checkTime(dd);      
     hh = checkTime(hh);      
     mm = checkTime(mm);      
     ss = checkTime(ss); 
     if(ts>0&&dd>0){  	 
    	 $("#countDownTime_"+seq).text(dd + "天" + hh + "时" + mm + "分" + ss + "秒"); 
     }else if(ts>0&&hh>0){
    	 $("#countDownTime_"+seq).text(hh + "时" + mm + "分" + ss + "秒");
     }else if(ts>0&&mm>0){
    	 $("#countDownTime_"+seq).text(mm + "分" + ss + "秒");
     }else if(ts>0&&ss>0){
    	 $("#countDownTime_"+seq).text(ss + "秒");
     }else{
    	 var $prompt =  $("#countDownTime_"+seq).parent("span");
    	 $prompt.parent(".el_examName").next(".el_examInfoDiv").children("a").show();
    	 $prompt.parent(".el_examName").next(".el_examInfoDiv").children("a").children("input").prop("disabled",false);
    	 $prompt.css("color","red");
    	 $prompt.html("考试已经开始，请点击开始考试进行答题！"); 
     }  
}
function checkTime(i)        
{        
   if (i < 10) {        
       i = "0" + i;        
    }        
   return i;        
}        
//获取所有的考试开始时间
function findAllStrartExamTime(){
	var $timeList = $(".exam_StartTime");
	for(var i=0;i<$timeList.length;i++){
		var startTime = $timeList.eq(i).text();
		var seq = i;
		startTime = startTime.replace(/-/g,"/");
		downTimer(startTime,seq);
	}
	indexCount--;
	
	if(indexCount<0){
		clearInterval(timeout);
	}
}

/***************************个人资料相关信息的回显和修改***************************/
//查询登录用户的基本信息
function findOnlineExamEmployeeInInfo(){
	$.ajax({
		url:"onlineEmployeeInfo_getOnlineExamEmployeeInInfo.action",
		data:{"employeeInId":employeeInId},
		type:"post",
		dataType:"json",
		success:showEmployeeInInfo,
		error:function(){
			//alert("请求失败！")
		}
	})
}
//显示用户的基本信息
function showEmployeeInInfo(data){
	var employeeBaseInfo = data.employeeBaseInfo;
	var employeeUserInfo = data.employeeUserInfo;
	$("#employeeIn_name").val(employeeBaseInfo.name);
	if(employeeBaseInfo.sex==1){
		$("#employeeIn_sex").val("男");
		$("#employeeIn_sex1").prop("checked",true);
	}else{
		$("#employeeIn_sex").val("女");
		$("#employeeIn_sex2").prop("checked",true);
	}
	$("#employeeIn_birthday").val(Format(new Date(employeeBaseInfo.birthday.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd"));
	$("#employeeIn_phone").val(employeeBaseInfo.phone);
	$(".employeeIn_employeeId").val(employeeBaseInfo.employeeid);
	$("#employeeIn_IdCard").val(employeeBaseInfo.idcode);
	$("#user_oldPassword").val(employeeUserInfo.password);
	$("#imghead").prop("src","/files/EmployeeIn/"+employeeBaseInfo.photo)
}

//验证电话号码
$(function(){
	
	//联系电话(手机/电话皆可)验证 
		jQuery.validator.addMethod("isPhone", function(value,element) { 
		  var length = value.length; 
		  var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/; 
		  var tel = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;
		  return this.optional(element) || (tel.test(value) || mobile.test(value)); 
			
		}, "请正确填写您的联系电话"); 
})

//修改用户的基本信息
function saveEmployeeInfo(){
	var isNotNull = $("#form_employeeInInfo").validate({
		rules : {
			"employeeIn.phone":{
				required:true,
				isPhone:true
				
			}
			
		},	
		messages : {
			"employeeIn.phone":{
				required : "不能为空",
				digits :"电话格式不正确",
				isPhone:"输入的电话格式不正确，请重新输入！"
				}
		}
	});
	if(isNotNull.form()){		
		$.ajax({
			url:"onlineEmployeeInfo_updateOnlineExamEmployeeInInfo.action",
			data:$("#form_employeeInInfo").serialize(),
			type:"post",
			dataType:"json",
			success:function(data){
				alert(data.result)
			},
			error:function(){
				//alert("请求失败！")
			}
		});
	}
}

/***************************个人中心密码的修改***************************/
//判断旧密码是否正确
var oldPassWordTF = false;
//新密码是否一致
var newPasswordTF = false;

//判断原始密码输入是否正确
function existPassword(obj){
	var inputPassword = $(obj).val();
	var oldPassWord = $("#user_oldPassword").val();
	if(inputPassword == oldPassWord){
		$("#isExistPassword").css("color","green");
		$("#isExistPassword").text("密码输入正确！");
		oldPassWordTF = true;
	}else{
		$("#isExistPassword").css("color","red");
		$("#isExistPassword").text("密码输入错误！请重新输入");
	}
}

//新密码验证
function checkNewPassword(obj){
	var checkPassword = $(obj).val(); 
	var newPassword = $("#user_newPassword").val();
	if(checkPassword == newPassword){
		$("#isCheckNewPassword").css("color","green");
		$("#isCheckNewPassword").text("密码输入一致！");
		newPasswordTF = true;
	}else{
		$("#isCheckNewPassword").css("color","red");
		$("#isCheckNewPassword").text("两次密码输入不一致，请重新输入！");
	}
}
//点击确认进行密码的修改，修改后调用查询基本信息的方法对数据进行更新
function changePassword(){
	var isNotNull = $("#form_changePassword").validate({
		rules : {
			newPassword:"required",
			oldPassword : "required",
			checkPassword : "required"
		},
		messages : {
			newPassword:{
				required : "不能为空"
				},
			oldPassword : {
				required : "不能为空"
			},
			checkPassword : {
				required : "不能为空"
			}
		}
	});
	if(isNotNull.form()&&oldPassWordTF&&newPasswordTF){
		$.ajax({
			url:"onlineEmployeeInfo_updateOnlineExamUsersPassword.action",
			data:$("#form_changePassword").serialize(),
			type:"post",
			dataType:"json",
			success:function(data){
				alert(data.result);
				//window.location.reload();
				findOnlineExamEmployeeInInfo();
				clearOldInfo();
			},
			error:function(){
				//alert("请求失败！")
			}
		});
	}
}
//初始化修改密码的页面
function clearOldInfo(){
	$("#isExistPassword").text("");
	$("#isCheckNewPassword").text("");
	$("#user_checkPassword").val("");
	$("#user_inputPassword").val("");
	$("#user_newPassword").val("");
}

/*************************a标签post提交方法******************************/
//to:提交动作（action）,p:向后台传递的数据
function doPost(to,p) { 
	//将json字符串转换为json对象
	p = eval("(" + p + ")");
	var myForm = document.createElement("form");
	myForm.method = "post";
	myForm.action = to;
	myForm.target="_self"
	//遍历json对象
	for ( var i in p) {
		var myInput = document.createElement("input");
		myInput.setAttribute("name", i); // 为input对象设置name  
		myInput.setAttribute("value", p[i]); // 为input对象设置value  
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
	document.body.removeChild(myForm); // 提交后移除创建的form  
}  

/************获取服务器时间**********/
function getServerDate(){
	var nowTimeStr;
	$.ajax({
		url:basePathUrl+"/onlineExam_getNowServerTime.action?date="+Format(new Date(),"HH:mm:ss"),
		async:false,
		datatype:"json",
		success:function(data){			
			nowTimeStr = data.nowTime.replace(/T/g," ").replace(/-/g,"/");
		}
	})	
	return new Date(nowTimeStr);
	//return new Date();
}



/* ------------个人违章信息管理 -----------------*/
$(function() {
	$("#inpstart2").jeDate({
	    isinitVal:false,
	    minDate: '2000-06-16',
	    maxDate: '2225-06-16',
	    format : 'YYYY-MM-DD',
	    zIndex:3000
	})

	$("#inpend2").jeDate({
	    isinitVal:false,
	    minDate: '2000-06-16',
	    maxDate: '2225-06-16',
	    format : 'YYYY-MM-DD',
	    zIndex:3000
	})
	//查询员工的违章信息
	findPersonBreakInfos();
})

//点击个人违章信息查询按钮执行的操作
function selectPersonBreakInfos(){
	//将违章信息的当前页中的值清空
	$("#currentPage_break").val('');
	//调用查询的方法
	findPersonBreakInfos();
}


//个人违章信息查询方法
function findPersonBreakInfos(){
	$.ajax({
		url:"onlineEmployeeInfo_getEmployeeBreakInfoList.action",
		data:$("#form_onlineBreakInfo").serialize(),
		type:"post",
		dataType:"json",
		success:showPersonBreakInfos
	})
}

//显示个人违章信息
function showPersonBreakInfos(data){
	var result = data;
	//当前页显示条数
	var currentCount = result.pageBean.currentCount;
	var personBreakInfoList = result.pageBean.productList;
	var showPersonBreakInfoList = '';
	for(var i=0;i<personBreakInfoList.length;i++){
		var index = i+1;
		showPersonBreakInfoList += "<tr><td>"
								+(index + (result.pageBean.currentPage - 1) * currentCount)+"</td><td>"
								+Format(new Date(personBreakInfoList[i].empinbreaktime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd")+"</td><td>"
								+personBreakInfoList[i].empinminusnum+"</td><td>"
								+personBreakInfoList[i].empinbreakcontent+"</td></tr>";
								
	}
	$("#breakRulesInfoList").empty();
	$("#breakRulesInfoList").append(showPersonBreakInfoList);
	//当前页
	var currentPage = result.pageBean.currentPage;
	//总条数
	var totalCount = result.pageBean.totalCount;
	//调用分页的函数
	breakInfos_page(currentPage, totalCount,currentCount);
}

//违章信息的分页
function breakInfos_page(currentPage, totalCount,currentCount) {
	$('#paginationID').pagination({
        //组件属性
        "total": totalCount,//数字 当分页建立时设置记录的总数量 
        "pageSize": currentCount,//数字 每一页显示的数量 10
        "pageNumber": currentPage,//数字 当分页建立时，显示的页数 1
        "pageList": [8,15,20],//数组 用户可以修改每一页的大小，
        //功能
        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
        "onSelectPage": function (pageNumber, b) {
        	//向隐藏域中设置值
        	$("#currentPage_break").val(pageNumber);
        	$("#currentCount_break").val(b);
        	//调用查找函数
        	findPersonBreakInfos();
        }
    });

}