/**
 * Created by yorge on 2017/9/12.
 */

/*获得系统当前时间*/
$(function () {
	getUsername();
    var d = new Date(), str = '';
    str += d.getFullYear() + '年'; //获取当前年份
    str += d.getMonth() + 1 + '月'; //获取当前月份（0——11）
    str += d.getDate() + '日' + ' ';
    str += d.getHours() + '时';
    str += d.getMinutes() + '分';
    str += d.getSeconds() + '秒';
    $("#time").html(str);

    function current() {
        var d = new Date(), str = '';
        str += d.getFullYear() + '年'; //获取当前年份
        str += d.getMonth() + 1 + '月'; //获取当前月份（0——11）
        str += d.getDate() + '日' + ' ';
        str += d.getHours() + '时';
        str += d.getMinutes() + '分';
        str += d.getSeconds() + '秒';
        return str;
    }

    setInterval(function () {
        $("#time").html(current)
    }, 1000);
});

function getUsername(){
	$.ajax({
		url:"user_getUsernameBySession.action",
		type :"POST",
		dataType:"json",
		success:function(data){
			if(data.error_sesssion!=null){
				alert("长时间未操作，请重新登录");
				window.location.href='/';
			}
			var username=data.username;
			$("#el_currentUser").empty();
			$("#el_currentUser").append(username);
		}
	})
}


