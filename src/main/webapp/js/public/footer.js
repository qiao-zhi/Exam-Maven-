/**
 * Qlq 2018.1.17缩进菜单(菜单的缩回去与显示效果)
 */
$(function() {

	// 菜单栏的缩放
	$("#toggleMenu").click(function() {
		toggleMenu();
	});

});
// 图标的点击事件(缩进菜单)
function toggleMenu() {
//	将菜单显示出来
	if ($(".el_left").css('display') == 'none') {
		//1.先将右面缩回去。2.右边完成之后左边菜单显示
		$(".el_right").animate({
			width : "87%"
		}, {
			duration : 1000,
			complete : function() {
				$(".el_left").show(1000);
				//刪除向左的class属性
				$("#toggleMenu span").removeClass();
				//添加向右的class属性
				$("#toggleMenu span").addClass("glyphicon glyphicon-arrow-left");
				//更改提示消息
				$("#toggleMenu").prop("title","点此隐藏菜单栏");
			}
		});
	} else {
		// 	将菜单隐藏掉
		//1.先将菜单缩回去。2.左边完成之后右边界面占满全屏
		$(".el_left").hide(1000, function() {
			$(".el_right").animate({
				width : "100%"
			}, 1000);
			//刪除向左的属性
			$("#toggleMenu span").removeClass();
//			添加向右的属性
			$("#toggleMenu span").addClass("glyphicon glyphicon-arrow-right");
			//更改提示消息
			$("#toggleMenu").prop("title","点此显示菜单栏");
		});

	}
}
