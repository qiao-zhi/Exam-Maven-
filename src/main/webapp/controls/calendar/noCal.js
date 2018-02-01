/**
 * jinxu 2017-12-05
 */

$(function() {
	/**
	 * 通过判断是否有class，去处理 添加初始化值
	 */
	$(".wicon").each(function() {
		if (!$(this).hasClass("el_noVlaue")) {
			var myDate = new Date();
			
			var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
			var month = myDate.getMonth() + 1;  //获取当前月份(0-11,0代表1月)
			if(month <= 9) {
				month = "0" + month;
			}
			var day = myDate.getDate();			//日
			if(day <= 9) {
				day = "0" + day;
			}
		
			var hour = myDate.getHours();       //获取当前小时数(0-23)
			if(hour <= 9) {
				hour = "0" + hour;
			}
			var minute = myDate.getMinutes();   //获取当前分钟数(0-59)
			if(minute <= 9) {
				minute = "0" + minute;
			}
			var second = myDate.getSeconds();	//秒
			if(second <= 9) {
				second = "0" + second;
			}
			
			var d = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second
		
			$(this).val(d)
		}
	})
})