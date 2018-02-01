/**
 * Created by yorge on 2017/9/14.
 */

/*
* examparperManage 修改试卷，创建时间
*
* */
$(function(){
    var picker = new Pikaday(
        {
            field: document.getElementById('datepicker2'),
            firstDay: 1,
            minDate: new Date('1995-01-01'),
            maxDate: new Date('2050-12-31'),
            yearRange: [1995, 2050]
        });
    //获取当前日期
    var mydate = new Date();
    var year = mydate.getFullYear();
    var month = mydate.getMonth() + 1;
    if (month < 9) {
        month = "0" + month;
    }
    var day = mydate.getDate();
    if (day < 9) {
        day = "0" + day;
    }
    $("#datepicker2").val(year + "-" + month + "-" + day);

    var picker3 = new Pikaday(
        {
            field: document.getElementById('datepicker3'),
            firstDay: 1,
            minDate: new Date('1995-01-01'),
            maxDate: new Date('2050-12-31'),
            yearRange: [1995, 2050]
        });
    $("#datepicker3").val(year + "-" + month + "-" + day);

    /*第二个*/
    var picker1 = new Pikaday(
        {
            field: document.getElementById('datepicker'),
            firstDay: 1,
            minDate: new Date('1995-01-01'),
            maxDate: new Date('2050-12-31'),
            yearRange: [1995, 2050]
        });
    //获取当前日期
    $("#datepicker").val(year + "-" + month + "-" + day);
})
