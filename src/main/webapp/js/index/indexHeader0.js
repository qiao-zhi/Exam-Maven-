/**
 * Created by yorge on 2017/9/20.
 */
var login_number = 0, denglu = false;
var verifyCode;
$(function() {
	// 记住密码功能
	var str = getCookie("logininfo");
	str = str.substring(1, str.length - 1);
	var username = str.split(",")[0];
	var password = str.split(",")[1];
	// 自动填充用户名和密码
	$("#form_username").val(username);
	$("#form_password").val(password);
	if (str != '') {
		$("#form_isRememberme").attr("checked", true);
	}
	// 初始化的时候打开模态框
	initModal();
})

function modal_login() {
	if (login_number == 0) {
		$("#validate_code").hide();
	} else {
		$("#validate_code").show();
	}
	// 判断是否是直接访问主页
	$("#myModal").modal("show");
}

/**
 * 初始化的时候打开模态框
 */
function initModal() {
	if (login_number == 0) {
		$("#validate_code").hide();
	} else {
		$("#validate_code").show();
	}
	// 判断是否是直接访问主页
	var u = location.pathname;
	u = u.substring(6, u.length);
	if (!denglu && (u.length == 29 || u == "")) {
		if ($("#el_userType").find("[value='3']").length == 0) {
			$("#el_userType").append('<option value="3">学员</option>');
		}
	}
}
function Enter_login() {
	if (event.keyCode == 13) // 回车键的键值为13
		document.getElementById("my_button").click(); // 调用登录按钮的登录事件
}
function login_error() {
	verifyCode = new GVerify("v_container");
	login_number += 1;
	$("#code_input").val('');
	$("#validate_code").show();
	// 登录框的高
	$("#myModal").find(".modal-content").css("height", "500");
}

function login() {
	// 如果登录是以学员的身份登录的
	if ($("#el_userType").val() == "3") {
		var username_q = $("#form_username").val();
		var password_q = $("#form_password").val();
		if (username_q == "" || password_q == "") {
			alert("用户名或密码不能为空!")
			return;
		}
		// ajax从字典获取账号密码
		$.ajax({
			url : contextPath + '/dic_getDicNamesAndIdByUpid.action',
			data : {
				"upId" : "500"
			},
			type : "post",
			success : function(response) {
				var users = response.names;
				if (users == null||users.length==0) {
					alert("账号未开放，请联系管理员!");
					return;
				}
				for (var i = 0, length_1 = users.length; i < length_1; i++) { // 登录成功
					if (username_q == users[i].dictionaryname
							&& password_q == users[i].discription) {
						$("#myModal").modal("hide");
						$("#el_userType option[value='3']").remove();
						$("#closeModal").css("display", "block");
						
						initKnowledgeType();
						// 页面加载的时候初始化页面的数据
						initPageData();
						
						$(".el_navUL").css("display", "none");
						$("#showMessage").css("display", "none");
						return;
					}
				}
				alert("账户或者密码错误!");
			},
			dataType : 'json'
		});

	} else {
		var res = true;
		if (login_number > 0) {
			res = verifyCode
					.validate(document.getElementById("code_input").value);
		}
		if (res) {
			$.ajax({
				url : "/Exam/user_login.action",
				data : $("#el_form").serialize(),
				type : "POST",
				dataType : "json",
				success : function(data) {
					var login_result = data.login_result;
					var user_type = data.user_type;
					var login_url;
					if ('2' == user_type) {
						login_url = data.login_url;
					}
					switch (login_result) {
					case 'error001':
						alert("该账号不存在");
						login_error();
						break;
					case 'error002':
						alert("密码错误");
						login_error();
						break;
					case 'error003':
						alert("该账号没有任何权限，不能使用该系统，请先分配角色");
						login_error();
						break;
					case 'error':
						alert("未知错误");
						login_error();
						break;
					case 'success_employee':
						window.location.href = baseUrlPath
								+ "/view/lineExam/examInterface.jsp";
						break;
					case 'success_manager':
						window.location.href = baseUrlPath + "/view/"
								+ login_url;
						break;
					}
				}
			})
		} else {
			alert("验证码错误")
			$("#code_input").val('');
		}
	}
}

// 获取cookie
function getCookie(cname) {
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ')
			c = c.substring(1);
		if (c.indexOf(name) != -1)
			return c.substring(name.length, c.length);
	}
	return "";
}
