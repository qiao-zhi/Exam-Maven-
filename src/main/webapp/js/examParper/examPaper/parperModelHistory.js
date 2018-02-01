/**
 * 历史试卷预览
 * Created by yorge on 2017/9/15.
 */
/*回到顶部*/
$(function () {
    $(window).scroll(function () {
        if ($(window).scrollTop() > 300) {
            $('#el_returnTop').css('display', 'block');
        }
        else {
            $('#el_returnTop').css('display', 'none');
        }
    });
});