/**
 * Created by yorge on 2017/9/15.
 */
$(function () {

    $("#el_checkQuanxuan").change(function () {
        if ($(this).prop("checked") == true) {
            $(".el_checks").prop("checked", "true");
        } else {
            $(".el_checks").removeAttr("checked");
        }
    })
})

/*批量删除*/
function piliangdelcfm() {
    //累计选择的个数，若大于1，才执行，否则提示
    var nums = 0;
    $.each($(".el_checks"), function (i, el_check) {
        if ($(this).prop("checked")) {
            nums++;
        }
    });
    if (nums > 1) {
        $('#delcfmModel2').modal();
    } else {
        alert("请至少选择两列，才能执行此操作！")
    }
}
function urlSubmit() {
    var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
    window.location.href = url;
}