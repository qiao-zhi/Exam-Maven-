/**
 * Created by yorge on 2017/9/20.
 */
$(function () {
      //视频
    $(".con ul li").hover(function () {
        $(this).find(".txt").stop().animate({height: "150px"}, 400);
        $(this).find(".txt h3").stop().animate({paddingTop: "25px"}, 400);
        $(this).children(".txt").children(".el_liulanliang").show();
    }, function () {
        $(this).find(".txt").stop().animate({height: "45px"}, 400);
        $(this).find(".txt h3").stop().animate({paddingTop: "10px"}, 400);
        $(this).children(".txt").children(".el_liulanliang").hide();
    })
})


//视频专区的
function Fy(){
	$.ajax({
		url:contextPath+"/train_findStudyTraincontentByFy.action",
		data:{
			"knowledge":"",
			"dataLevel":"",
			"currentPage":"1",
			"resultCount":"10"
		},
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
			//data.traincontentList[i]代表视频的   data.traincontentListDoc[i]代表非视频也就是文档的
			//---====视频专区
			//数据显示之前 要先清空视频专区的的所有数据
			$("#videoList li").remove();
			var videoStr = "";
			//alert("视频的数量:"+data.traincontentList.length)
			for(var i =0;i<data.traincontentList.length;i++){
				
				//培训资料的id
				var documentid = data.traincontentList[i].documentid;
				//资料名称
				var documentname = data.traincontentList[i].documentname;
				//资料级别
				var level = data.traincontentList[i].level;
				//资料类型
				var traintype = data.traincontentList[i].traintype;
				//文件大小
				var size = data.traincontentList[i].size;
				//浏览量
				var browsetimes = data.traincontentList[i].browsetimes;
				//所属部门
				var departmentname = data.traincontentList[i].departmentname;
				//上传时间
				var uptime = Format(new Date(data.traincontentList[i].uptime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH-mm");
				
				//上传人
				var employeename = data.traincontentList[i].employeename;
				//资料描述
				var description = data.traincontentList[i].description;
				//当前文件名
				var currentname = data.traincontentList[i].currentname;
				
				
				videoStr +="<li>";
				videoStr +="<div class='pricing pricing--yama'>";
				videoStr +="<div class='pricing__item'>";
				videoStr +="<ul class='ul'>";
				videoStr +="<li class='li_xuhao'>"+(i+1)+"</li>";
				videoStr +="<li>";
				videoStr +="<div class='pricing__price'>"+documentname+"</div>";
				videoStr +="</li>";
				videoStr +="<li class='pricing__feature'>"+departmentname+"</li>";
				videoStr +="<li class='pricing__feature'>"+level+"</li>";
				videoStr +="<li class='pricing__feature'>"+traintype+"</li>";
				videoStr +="<li class='pricing__feature'>"+size+"</li>";
				videoStr +="<li class='pricing__feature'>"+"上传时间:"+uptime+"</li>";
		
				videoStr +="<li class='el_optionButton'>";
				//videoStr +="<a class='pricing__action' href='${pageContext.request.contextPath}/train_videoPlay.action?trainContentId="+documentid+"' target='_blank'>播放</a>";
				videoStr +="<a href='"+contextPath+"/train_videoPlay.action?trainContentId="+documentid+"' target='_blank'>"+"<img src='"+contextPath+"/image/bofang.png' width='30'/></a>";
				videoStr +="</li>";
				
				videoStr +="</li>";
				videoStr +="</ul>";
				videoStr +="</div>";
				videoStr +="</div>"
				videoStr +="</li>";
				
				if(i>=4){
					i=data.traincontentList.length;
				}
			}
			$("#videoList").append(videoStr + "<li><a href='"+contextPath+"/view/index/studyMainpage2.jsp'>更多</a></li>");//添加到视频的列表中
			},
			error : function() {
					alert("查询失败")
			}
		});
}//Fy()方法的括号
		
