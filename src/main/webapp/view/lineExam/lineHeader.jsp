<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ include file="/public/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>头</title>

<!--头-->
<style>
#el_header {
	width: 100%;
	height: 60px;
	background-color: rgb(51, 122, 183);
	color: white;
	position:
}

#el_headerLeftLogo {
	float: left;
	width: 240px;
	height: 60px;
	margin-left: 50px;
	font-size: 22px;
	padding-top: 7px;
}
#el_headerLeftLogo2 {
	float: left;
	width: 240px;
	height: 60px;
	margin-left: -43px;
	font-size: 22px;
	padding-top: 17px;
}

#el_headerLeftLogo img {
	position: relative;
	top: 5px;
}

#el_headerLeftLogo a {
	display: block;
	height: 100%;
	float: right;
	width: 198px;
	text-decoration: none;
	font-size: 16px;
	color: white;
	text-align: center;
	text-shadow:0px 1px 0px #000;
}
#el_headerLeftLogo2 a {
	display: block;
	height: 100%;
	float: right;
	width: 198px;
	text-decoration: none;
	font-size: 21px;
	color: white;
	text-align: center;
}



#el_headerRightinfo {
	float: right;
	height: 55px;
	position: relative;
}

#el_systemTime {
	width: 200px;
	height: 25px;
	font-size: 10px;
	position: absolute;
	right: 15px;
	top: 3px;
}

#el_opration {
	width: 350px;
	position: absolute;
	bottom: 4px;
	right: 0px;
}

#el_opration ul li {
	float: left;
	list-style: none;
	font-size: 12px;
	margin-left: 20px;
}

.el_indexPage {
	display: inline-block;
	color: white;
	text-decoration: none;
}
</style>
<!--时钟-->
<script>
        /*获得系统当前时间*/
        $(function () {
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
    </script>

</head>
<body>

	<!--头-->
	<div id="el_header">
		<div id="el_headerLeftLogo">

			<img src="<%=path%>/image/logo.png" width="37" /> 
			<a href="<%=path%>/view/index/studyMainpage2.jsp"> 阳城国际发电有限责任公司 大唐阳城发电有限责任公司  </a>
		</div>
		<div id="el_headerLeftLogo2">
			<a href="<%=path%>/view/index/studyMainpage2.jsp">安全培训管理系统</a>

		</div>
		
		<div id="el_headerRightinfo">
			<div id="el_systemTime">
				<P id="time"></P>
			</div>

			<div id="el_opration">
				<ul>
					<li><img
						src="${pageContext.request.contextPath }/image/user_2.png"
						width="15" alt=""> <span>当前用户：</span> <span
						id="el_currentUser">${userinfo.username }</span></li>
					<li><a class="el_indexPage" href="javascript:void(0)"
						onclick="returnPerson()">返回个人首页</a></li>
					<li><a class="el_indexPage" href="javascript:void(0)"
						onclick="logout1()">退出</a></li>

				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript">
function returnPerson(){
		self.location="<%=path%>/view/lineExam/examInterface.jsp";
		}
function logout1(){
	if(confirm("确定退出?")){
		self.location="<%=path%>/logout.action";
			}
		}
	</script>
</body>
</html>
