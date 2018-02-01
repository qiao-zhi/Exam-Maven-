/**
 * Created by yorge on 2017/9/15.
 */
$(function () {
    $("#test").jeDate({
        format:"YYYY-MM",
        isTime:false,
        minDate:"2004-09-19"
    })

    $("#test2").jeDate({
        format:"YYYY-MM-DD",
        isTime:true,
        minDate:"2000-01-01",
        isinitVal:true
    })
    $("#test21").jeDate({
        format:"YYYY-MM-DD",
        isTime:true,
        minDate:"2000-01-01",
        isinitVal:true
    })
    //出生日期
    $("#test20").jeDate({
        format:"YYYY-MM-DD",
        isTime:true,
        minDate:"1940-01-01",
        maxDate:$.nowDate({YYYY:"-10"}),
        isinitVal:true
    })
      //出生日期
    $("#updateEmployeeInBirthday").jeDate({
        format:"YYYY-MM-DD",
        isTime:true,
        minDate:"1940-01-01",
        maxDate:$.nowDate({YYYY:"-10"}),
        isinitVal:true
    })

    $("#test4").jeDate({
        format:"YYYY-MM-DD hh:mm:ss",
        isTime:true,
        minDate:"2000-01-01 00:00:00",
        isinitVal:true
    })
    $("#test41").jeDate({
        format:"YYYY-MM-DD hh:mm:ss",
        isTime:true,
        minDate:"2000-01-01 00:00:00",
        isinitVal:true
    })
    $("#test42").jeDate({
        format:"YYYY-MM-DD hh:mm:ss",
        isTime:true,
        minDate:"2000-01-01 00:00:00",
        isinitVal:true
    })

});