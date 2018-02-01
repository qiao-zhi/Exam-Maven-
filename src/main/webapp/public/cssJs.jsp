<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<script type="text/javascript" src="${baseurl}/js/jquery.min.js"></script>
<script type="text/javascript" src="${baseurl}/bs/js/bootstrap.js"></script>
<link rel="stylesheet" href="${baseurl}/bs/css/bootstrap1.css"/>
<link rel="stylesheet" href="${baseurl}/bs/css/bootstrap.css"/>

<!--åé¡µ-->
<link rel="stylesheet" type="text/css" href="${baseurl}/controls/easyUI/themes/default/easyui.css"/>
<link rel="stylesheet" href="${baseurl}/controls/easyUI/themes/icon.css"/>
<script type="text/javascript" src="${baseurl}/controls/easyUI/jquery.easyui.min.js"></script>
<script src="${baseurl}/controls/easyUI/easyui-lang-zh_CN.js"></script>

<!--æ¥å-->
<script type="text/javascript" src="${baseurl}/controls/calendar/jedate/jquery.jedate.js"></script>
<link type="text/css" rel="stylesheet" href="${baseurl}/controls/calendar/jedate/skin/jedate.css">
<script src="${baseurl}/controls/calendar/calendar.js"></script>
<script src="${baseurl}/controls/calendar/calSE.js"></script><!-- è¿æå¤ææ¥å --> 

 <!--éæ©æ -->
<link rel="stylesheet" href="${baseurl}/controls/selectDropTree/demo.css" type="text/css">
<link rel="stylesheet" href="${baseurl}/controls/selectDropTree/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${baseurl}/controls/selectDropTree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${baseurl}/controls/selectDropTree/jquery.ztree.excheck.js"></script>

<!--sonå¬å±-->
<link rel="stylesheet" href="${baseurl}/css/public/publicSon_style.css"/>
<!--å¬ç¨æ ·å¼-->
<link rel="stylesheet" href="${baseurl}/css/public/public_style.css"/>

<!-- HomeMain -->
<link rel="stylesheet" href="${baseurl}/css/home/home.css"/>
<!--èå-->
<link rel="stylesheet" href="${baseurl}/css/home/menu.css"/><!--å¤´-->
<link rel="stylesheet" href="${baseurl}/css/home/header.css"/>
<!--æ¶é è¦æ¹æè·åæå¡å¨æ¶é´-->
<script type="text/javascript" src="${baseurl}/js/home/header.js"></script>
<!--è-->
<link rel="stylesheet" href="${baseurl}/css/home/footer.css"/>
    
<script src="${baseurl}/controls/calendar/noCal.js"></script>

<!-- <script>
// 设置jQuery Ajax全局错误处理
$(document).ajaxError( 
   function(event, jqXHR, options, errorMsg){  
/* 	   	console.log("event:"+ event)
	   	console.log("jqXHR:"+jqXHR.status)
	   	console.log("options:"+options.url)
	   	console.log("errorMsg:"+errorMsg) */
        switch (jqXHR.status){  
            case(500):  
                alert("服务器系统内部错误");  
                break;  
            case(401):  
                alert("未登录");  
                break;  
            case(403):  
                alert("无权限执行此操作");  
/*             	window.location.href=""; */
                break;  
            case(408):  
                alert("请求超时");  
                break;  
            default:  
                alert("未知错误");  
        }  
    }  
);
</script> -->
