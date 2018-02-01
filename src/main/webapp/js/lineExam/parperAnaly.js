$(function () {
    /*回到顶部*/
    $(window).scroll(function () {
        if ($(window).scrollTop() > 500) {
            $('#el_returnTop').css('display', 'block');
        }
        else {
            $('#el_returnTop').css('display', 'none');
        }
    });
});