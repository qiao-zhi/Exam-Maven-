/**
 * 职务下拉菜单
 */
function selectDuty() {
	var yincangzhiwu = $("#yincangzhiwu").val();
	$.ajax({
		url : '/Exam/employeein_getDuty.action',
		data : {
			"dictionaryid" : "300"
		},
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(result) {

			var dictionarys = result.dictionarys;

			var duty = $("#EmployeeInDuty");

			for (var i = 0; i < dictionarys.length; i++) {

				if (dictionarys[i].dictionaryname == yincangzhiwu) {

					var str = "<option selected value='"
							+ dictionarys[i].dictionaryname + "'>"
							+ dictionarys[i].dictionaryname + "</option>";
				} else {
					var str = "<option value='" + dictionarys[i].dictionaryname
							+ "'>" + dictionarys[i].dictionaryname
							+ "</option>";

				}

				duty.append(str);
			}

		}
	});
}

$(function() {

	searchDepartmentTree_1();

	$("#el_chooseDepart").bind('click', function() {
		$("#treeDemo9").toggle();
	});

	var hiV = $("#updateEmployeeInDepartmentName").val();
	if ($("#el_chooseDepart > li").length == 0) {
		$("#el_chooseDepart").append("<li class='dark'>" + hiV + "</li>");
	}
	selectDuty();
});

/***********************请求树信息**********************/

function searchDepartmentTree_1() {
	$.ajax({
		type : "post",
		dataType : "json",
		url : "/Exam/department_getDepartmentTree.action",
		success : getTree_1,
		error : function() {
			alert("请求树失败！");
		}
	});
}

/***********************生成树信息**********************/
function getTree_1(result) {
	var treeList = result.treeList;
	var setting = {
		data : {
			simpleData : {
				enable : true,
				idKey : "departmentid",
				pIdKey : "updepartmentid",
				rootPId : null,
			},
			key : {
				name : "departmentname",
			}
		},
		callback : {
			onClick : function(event, treeId, treeNode) {

				$("#updateEmployeeInDepartmentName").val(
						treeNode.departmentname);

				$("#updateEmployeeInDepartmentId").val(treeNode.departmentid);
				var hiV = $("#updateEmployeeInDepartmentName").val();
				if ($("#el_chooseDepart > li").length > 0) {//先清空
					$("#el_chooseDepart").children("li").remove();
				}
				//插入值
				$("#el_chooseDepart").append(
						"<li class='" + className + "'>" + hiV + "</li>");

				if ($("#el_chooseDepart").children("li").length > 0) {

					$("#treeDemo9").hide();
				}
			}
		}
	};
	var zNodes = treeList;
	//添加 树节点的 点击事件；
	var log, className = "dark";
	function onClick(event, treeId, treeNode, clickFlag) {
		clickOnTree(event, treeId, treeNode, clickFlag);
	}
	$.fn.zTree.init($("#treeDemo9"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo9");
	treeObj.expandAll(false);

}
