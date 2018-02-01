/**
 * Created by yorge on 2017/9/20.
 */
$().ready(function() {
    
 /*验证码*/
    var verifyCode = new GVerify("v_container");

    $("#my_button").click(function () {
        var res = verifyCode.validate(document.getElementById("code_input").value);
        if (res) {
            /*如果正确，判断登录的用户类型
             * 如果checkText是1，用户是员工
             * 如果checkText是2，用户是管理员
             * */
            var checkText = $("#el_userType").find("option:selected").val();
            if (checkText == 1) {
                $("#el_form").prop("action","");
                $("#el_form").prop("action","../lineExam/examInterface.jsp");
            } else {
                $("#el_form").prop("action","");
                $("#el_form").prop("action","..//examParper/examManage.jsp");
                //$("#el_loginA").css("href", "lineExam/examInterface.html")
            }
        } else {
            alert("验证码错误")
        }
    })

});
