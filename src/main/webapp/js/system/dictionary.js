/**
 * Created by yorge on 2017/9/15.
 */

/** ************以下是 左侧的字典树 start**************** */

/**
 * 初始化左侧的部门树
 */

/** ***********请求树信息************* */
function findAllDicMsg() {
	$.ajax({
		type : "post",
		target : "#treeDemo5",
		dataType : "json",
		url : "${pageContext.request.contextPath}/dic_findAllDicMsg.action",
		success : getTree_1,
		error : function() {
			alert("请求树失败！");
		}
	});
}
/** *************生成树信息*********** */
var clickRes = 0;
function getTree_1(treeList2) {
	// document.write(treeList2)
	// alert(treeList2)
	var treeList3 = treeList2.dictionaryTree;// List<Map<String, Object>>
	// alert(treeList3)
	var setting = {
		/*
		 * view: { showIcon: showIconForTree },
		 */
		data : {
			simpleData : {
				enable : true,
				idKey : "dictionaryId",
				pIdKey : "upDictionaryId",
				rootPId : 10,
			},
			key : {
				name : "dictionaryName",
			}
		},
		callback : {
			beforeClick : beforeClick
		}
	};
	var zNodes2 = treeList3;

	// 添加 树节点的 点击事件；

	var getName;
	var className = "dark";
	function beforeClick(treeId, treeNode, clickFlag) {
		/**
		 * 判断是否是电厂负责部门， 若是，隐藏操作按钮，添加字典和批量删除
		 * 
		 */
		$("#yeHao").val("1");
		if (treeNode.dictionaryId == '400') {
			$(".el_MainxiaoMain").hide();
		} else {
			$(".el_MainxiaoMain").show();
		}
		;

		clickRes = 1;
		className = (className === "dark" ? "" : "dark");
		getName = treeNode.dictionaryName;
		// alert("您点击的字典内容为:" + getName)
		// lixianyuan start
		// 将选中的树的内容赋值给对应的隐藏域
		$("#addUpDicName").val(getName);
		// 设置字典点的等级
		$("#dicTreeLevel").val(treeNode.level);

		// 通过用户点击的那棵树的名称(字典名称)获取其所有下一级的字典信息(把其显示在表格中)
		showAllDicmsgByDicName($("#addUpDicName").val(), treeNode.dictionaryId);// 在这里$("#addUpDicName").val()不能用getName去替换
		// lixianyuan end)

		showLog2(treeNode.name);
		return (treeNode.click != false);
	}

	// 加载页面的时候初始化 显示字典列表的所有下一级字典信息 start
	$(function() {
		showAllDicmsgByDicName("阳城电厂字典","10");
	});
	// lixinayuan start
	// 通过用户点击的那棵树的名称(字典名称)获取其所有下一级的字典信息(把其显示在表格中)
	function showAllDicmsgByDicName(dicName, dicId) {
		$("#menu400Name").val(dicName)
		var treeLevel = $("#dicTreeLevel").val();
		// 当前页页号
		$
				.ajax({
					url : "${pageContext.request.contextPath}/dic_getDownDicMsgByDicName.action",
					data : {
						"dicName" : dicName,
						"curPage" : $("#yeHao").val(),
						"perCount" : $("#jiLuShu").val()
					},
					dataType : "json",
					type : "POST",
					async : true,
					success : function(data) {
						// alert("进入回掉函数")
						// 数据显示之前 要先清空表格中的所有数据
						$("#tBody tr").remove();
						var tdStr = "";
						// 隐藏与显示按钮
						if (treeLevel == 1) {
							$("#dynamicBtn").css("display", "");// 显示按钮
							$("#updateDicDiv").css("display", "");// 模态框启用与否

						} else {
							$("#dynamicBtn").css("display", "none");
							$("#updateDicDiv").css("display", "none");
						}

						// 当前页
						var totalCount = data.sumCount;
						var currentPage = data.curPage;
						var currentCount = data.perCount;
						
						for (var i = 0; data.dictionaryList != null
								&& i < data.dictionaryList.length; i++) {
							var index = (currentPage - 1) * currentCount + i
									+ 1;
							// 字典id dictionaryId
							var dictionaryid = data.dictionaryList[i].dictionaryid;
							// 字典名称 dictionaryName
							var dictionaryname = data.dictionaryList[i].dictionaryname;
							// 上级字典id upDictionaryId
							var updictionaryid = data.dictionaryList[i].updictionaryid;
							// 是否可用 isUse
							var isuse = data.dictionaryList[i].isuse;
							// 描述 discription
							var discription = data.dictionaryList[i].discription;
							//
							tdStr += "<tr>";
							// 按照DOM规则动态的添加到表格中
							// 隐藏域 隐藏字典的主键id
							tdStr += "<input type='hidden' name='documentId' value="
									+ dictionaryid + " class='docID'/>";
							tdStr += "<td><input type='checkbox' class='el_checks'/></td>";
							tdStr += "<td>" + index + "</td>";// 序号
							tdStr += "<td>" + dictionaryname + "</td>";// 字典名称
							tdStr += "<td>" + isuse + "</td>";// 字典状态(是否可用)
							tdStr += "<td>" + discription + "</td>";// 字典描述
							//
							tdStr += "<td>";
							/**
							 * 判断是那种修改。 若是，修改调用modifyUserInfoTree2事件，字典名称为下拉树
							 * else 条用一般事件
							 */
							if (dicId == "400") {
								tdStr += "<a href='javascript:void(0)' onclick='modifyUserInfoTree2(this)' >修改</a>";
							} else {
								tdStr += "<a href='javascript:void(0)' onclick='modifyUserInfoTree(this)' >修改</a>";
							}

							if (treeLevel == 1 && dicId!="400" ) {
								tdStr += "<a href='javascript:void(0)' class='el_delButton' onClick='delcfmTree(this)'>删除</a>";
							}
							tdStr += "</td>";
							//
							tdStr += "</tr>";
						}
						$("#tBody").append(tdStr);
						queryFy2(data.sumCount, data.curPage, data.perCount);
					},
					error : function() {
						alert("查询失败，数据库中没有该数据");
					}
				})
	}

	// 分页的方法
	// 最新的分页 start
	// 参数1：总记录数 参数2：当前页页号 参数3：每页显示的记录条数
	function queryFy2(resultCount, currentPage, currentTotal) {
		$('#paginationIDU').pagination(
				{
					// 组件属性
					"total" : resultCount,// 数字 当分页建立时设置记录的总数量 1
					"pageSize" : currentTotal,// 数字 每一页显示的数量 10
					"pageNumber" : parseInt(currentPage),// 数字 当分页建立时，显示的页数 1
					"pageList" : [ 8, 15, 20 ],// 数组 用户可以修改每一页的大小，
					// 功能
					"layout" : [ 'list', 'sep', 'first', 'prev', 'manual',
							'next', 'last', 'links' ],
					"onSelectPage" : function(pageNumber, b) {
						// 为两个隐藏域赋值 当前页页号 每页显示的记录数
						$("#yeHao").val(pageNumber);
						$("#jiLuShu").val(b);
						// 分页查询 关键点(如果不加上下面一行代码的话，导航栏中分页的 当前页页号、每页显示的记录数
						// 只改变这两个就不会进行换页显示数据了)
						showAllDicmsgByDicName($("#addUpDicName").val(), "");

					}
				});

	}

	// 最新的分页 end
	// lixinayuan end

	// 获取节点名
	function showLog2(str) {
		// console.log(str)
		/*
		 * if (!log) log = $("#log"); log.append("<li class='"+className+"'>"+str+"</li>");
		 * if(log.children("li").length > 8) {
		 * log.get(0).removeChild(log.children("li")[0]); }
		 */
	}

	$.fn.zTree.init($("#treeDemo5"), setting, zNodes2);

}
/*
 * function zTreeOnClick(event, treeId, treeNode) { alert(treeNode.dictionaryId + ", " +
 * treeNode.name); };
 */
$(document).ready(function() {
	findAllDicMsg();
});

/** **左侧的字典树 end*** */

/** *********************************************** */
$(function() {
	// --第一列，复选框基本事件
	$("#el_checkQuanxuan").change(function() {
		if ($(this).prop("checked") == true) {
			$(".el_checks").prop("checked", "true");
		} else {
			$(".el_checks").removeAttr("checked");
		}
	})
})

/* 添加字典 */
function el_addDictinary() {
	// 判断是否已经选择了树,跟据上边的NodeNums
	if (clickRes == 0) {
		alert("请选择左侧的字典列表的名称！")
	} else {
		// 给模态框中，添加默认部门
		// alert("进入添加字典")
		// $("#addDefaultDepart").prop("value", getName);
		// $("#addUpDicName").attr("value", getName);
		// alert($("#addUpDicName"),val())

		$('#myModal').modal();
		// 获得的树的名字： getName
	}
}

/*
 * 批量删除
 */
function piliangdelcfm() {
	// 累计选择的个数，若大于1，才执行，否则提示
	var nums = 0;
	$.each($(".el_checks"), function(i, el_check) {
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
