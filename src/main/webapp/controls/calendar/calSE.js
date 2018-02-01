/**
 * Created by yorge on 2017/9/15.
 */
$(function () {
var start = {
    format: 'YYYY-MM-DD hh:mm:ss',
    minDate: '2014-06-16 23:59:59', //设定最小日期为当前日期
    isinitVal:true,
    festival:false,
    ishmsVal:false,
    maxDate: $.nowDate({DD:0}), //最大日期
    choosefun: function(elem, val, date){
        end.minDate = date; //开始日选好后，重置结束日的最小日期
        endDates();
    }
};
var end = {
    format: 'YYYY-MM-DD hh:mm:ss',
    minDate: $.nowDate({DD:0}), //设定最小日期为当前日期
    festival:false,
    maxDate: '2099-06-16 23:59', //最大日期
    choosefun: function(elem, val, date){
        start.maxDate = date; //将结束日的初始值设定为开始日的最大日期
    }
};
//这里是日期联动的关键
function endDates() {
//将结束日期的事件改成 false 即可
    end.trigger = false;
    $("#inpend").jeDate(end);
}
$('#inpstart').jeDate(start);
$('#inpend').jeDate(end);

//或者是
$.jeDate('#inpstart',start);
$.jeDate('#inpend',end);
})
