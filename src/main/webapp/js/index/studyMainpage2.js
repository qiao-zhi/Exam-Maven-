/**
 * Created by yorge on 2017/9/20.
 */

//页面加载时的初始化
$(function () {
    /* 下载专区*/
    $("#el_loaddowndiv").hide();

    $("#el_videoLi").click(function () {
        $("#el_loaddowndiv").hide();
        $("#el_videodiv").show();
        $("#el_loaddownLi").removeClass("active");
        $(this).addClass("active");
    });
    $("#el_loaddownLi").click(function () {
        $("#el_loaddowndiv").show();
        $("#el_videodiv").hide();
        $("#el_videoLi").removeClass("active");
        $(this).addClass("active");
    })


    /*
     * 菜单动态
     */
    $(".box").children("li").eq(1).hide();
    $(".box").children("li").eq(2).hide();
	$("#txBox").children(".tx").eq(0).css({"background-color":"#ccc","color":"black"});
	
    $("#txBox").children(".tx").hover(function(){
    	var index = $(this).index();
    	
    	for(var i = 0; i < 3; i ++) {
    		if(i == index) {
    			$(".box").children("li").eq(i).show();
    			$("#txBox").children(".tx").eq(i).css({"background-color":"#ccc","color":"black"});
    		} else{
    			$(".box").children("li").eq(i).hide();
    			$("#txBox").children(".tx").eq(i).css({"background-color":"#111","color":"white"});
    		}
    	}
    })
})


//*************************现在的知识点 start
$(function(){
//注视到这	


//一级
  $('#baseMenuArrow').click(function(){
      $(this).selectMenu({
          regular : true,
          arrow : true,
          data : baseMenuData
      });
  });
  //二级
  $('#baseMenuCenter').click(function(){
	  $(this).selectMenu({
		  regular : true,
		  arrow : true,
		  position : 'center',
		  data : baseMenuData1
	  });
  });
  //三级
  $('#baseMenuRight').click(function(){
      $(this).selectMenu({
          regular : true,
          arrow : true,
          position : 'right',
          data : baseMenuData2
      });
  });
});
//***************************现在的知识点 end
var baseMenuData = null;
var baseMenuData1 = null;
var baseMenuData2 = null;
//********现在的初始化知识点
function initKnowledgeType(){
	$.ajax({
		url:"${pageContext.request.contextPath}/train_initKnowledgeType.action",
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
			
	
			//一级
			var baseMenuDatas = "[";
			/*baseMenuDatas +="{content:'<span onclick=\"el_query51(this)\">全部</span>',url:'javascript:void(0)'},";*/
			baseMenuDatas +="{content:'sm_divider'},";
//			baseMenuDatas +="{content:'<span onclick=\"el_query(this)\">安全生产基础知识</span>',url:'javascript:void(0)'},";
			
			//二级
			var baseMenuDatas1 = "[";
			/*baseMenuDatas1 +="{content:'<span onclick=\"el_query52(this)\">全部</span>',url:'javascript:void(0)'},";*/
			baseMenuDatas1 +="{content:'sm_divider'},";
//			baseMenuDatas1 +="{content:'<span onclick=\"el_query1(this)\">安全生产基础知识</span>',url:'javascript:void(0)'},";
			
			
			//三级
			
			var baseMenuDatas2 = "[";
			/*baseMenuDatas2 +="{content:'<span onclick=\"el_query53(this)\">全部</span>',url:'javascript:void(0)'},";*/
			baseMenuDatas2 +="{content:'sm_divider'},";
//			baseMenuDatas2 +="{content:'<span onclick=\"el_query2(this)\">安全生产基础知识</span>',url:'javascript:void(0)'},";
			
			for(var i=0;i<data.dictionaryList.length;i++){
				//alert(data.dictionaryList[i].dictionaryname)
				//一级
				baseMenuDatas +="{content:'<span onclick=\"el_query(this)\">"+data.dictionaryList[i].dictionaryName+"</span>',url:'javascript:void(0)'},";
				//二级
				baseMenuDatas1 +="{content:'<span onclick=\"el_query1(this)\">"+data.dictionaryList[i].dictionaryName+"</span>',url:'javascript:void(0)'},";
				//三级
				baseMenuDatas2 +="{content:'<span onclick=\"el_query2(this)\">"+data.dictionaryList[i].dictionaryName+"</span>',url:'javascript:void(0)'},";
			}
			
			//一级
			baseMenuDatas +="]";
			baseMenuData = eval("(" +baseMenuDatas + ")"); 
			
			
			//二级
			baseMenuDatas1 +="]";
			baseMenuData1 = eval("(" +baseMenuDatas1 + ")"); 
			//三级
			baseMenuDatas2 +="]";
			baseMenuData2 = eval("(" +baseMenuDatas2 + ")"); 
			
			
		}
	});
}

/************S   QLQ加的登录后台的功能********************************/
$(function(){
//	$("#myModal").modal("show");
});