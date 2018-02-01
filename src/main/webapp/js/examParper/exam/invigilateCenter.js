/**
 * 监考中心
 * Created by yorge on 2017/9/14.
 */

/*索引考试时间*/
$(function () {
    /*日历*/
    $("#search_begindate").simpleCanleder();

    /*考试详情查看*/
    $("#el_lookEmp").click(function () {
        var nums = 1;
        $.each($(".el_checks"), function (i, el_check) {
            if ($(this).prop("checked")) {
                nums = 0;
                return true;
            }
        });
        if (nums == 1) {
            alert("请先选择一门考试！");
            return false;
        }
    })
});

/*<!-- 模态框 查看参考员工信息-->*/
function examPersons() {
    $('#examPersons').modal();
}

