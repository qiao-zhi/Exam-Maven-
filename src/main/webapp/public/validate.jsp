<style type="text/css">
/*validate中不成功显示的样式设置*/
label.error {
	background: url(${baseurl }/controls/validate/unchecked.gif) no-repeat
		10px 3px;
	padding-left: 30px;
	font-family: georgia;
	font-size: 15px;
	font-style: normal;
	color: red;
}

label.success {
	background: url(${baseurl }/controls/validate/checked.gif) no-repeat
		10px 3px;
	padding-left: 30px;
}
</style>
<script src="${baseurl }/controls/validate/jquery.validate.js"
	type="text/javascript" charset="utf-8"></script>
<script src="${baseurl }/controls/validate/messages_zh.js"
	type="text/javascript" charset="utf-8"></script>