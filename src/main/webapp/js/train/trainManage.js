/**
 * Created by yorge on 2017/9/14.
 */
/*
* examManage
* */
/*<!--索引中选择部门-->*/
var setting2 = {
    view: {
        selectedMulti: false
    },
    check: {
        enable: true,
        chkStyle: "radio",
        radioType: "all"
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        onCheck: onCheck
    }
};

var zNodes2 = [
    {id: 1, pId: 0, name: "随意勾选 1", open: true},
    {id: 11, pId: 1, name: "无 radio 1-1", nocheck: true},
    {id: 12, pId: 1, name: "随意勾选 1-2", open: true},
    {id: 121, pId: 12, name: "随意勾选 1-2-1"},
    {id: 122, pId: 12, name: "随意勾选 1-2-2"},
    {id: 123, pId: 12, name: "无 radio 1-2-3", nocheck: true},
    {id: 13, pId: 1, name: "随意勾选 1-3"},
    {id: 2, pId: 0, name: "禁止勾选 2", open: true},
    {id: 21, pId: 2, name: "禁止勾选 2-1", doCheck: false},
    {id: 22, pId: 2, name: "禁止勾选 2-2", open: true},
    {id: 221, pId: 22, name: "禁止勾选 2-2-1"},
    {id: 222, pId: 22, name: "禁止勾选 2-2-2", doCheck: false},
    {id: 223, pId: 22, name: "禁止勾选 2-2-3", doCheck: false},
    {id: 23, pId: 2, name: "禁止勾选 2-3", doCheck: false}
];

var code, log, className = "dark";

function onCheck(e, treeId, treeNode) {
    showLog(treeNode.name);
}

function showLog(str) {
    if (!log) log = $("#log");
    /*清空内部的东西*/
    if ($("#log > li").length > 0) {
        $("#log").children("li").remove();
    }
    log.append("<li class='" + className + "'>" + str + "</li>");

    /*判断是否插入进入，若插入进入，关闭树框*/
    if ($("#log > li").length > 0) {
        $("#treeDemo2").hide();
    }
}

function checkNode(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo2"),
        type = e.data.type,
        nodes = zTree.getSelectedNodes();
    if (type.indexOf("All") < 0 && nodes.length == 0) {
        alert("请先选择一个节点");
    }

    if (type == "checkAllTrue") {
        zTree.checkAllNodes(true);
    } else if (type == "checkAllFalse") {
        zTree.checkAllNodes(false);
    } else {
        var callbackFlag = $("#callbackTrigger").attr("checked");
        for (var i = 0, l = nodes.length; i < l; i++) {
            if (type == "checkTrue") {
                zTree.checkNode(nodes[i], true, null, callbackFlag);
            } else if (type == "checkFalse") {
                zTree.checkNode(nodes[i], false, null, callbackFlag);
            } else if (type == "checkTruePS") {
                zTree.checkNode(nodes[i], true, true, callbackFlag);
            } else if (type == "checkFalsePS") {
                zTree.checkNode(nodes[i], false, true, callbackFlag);
            }
        }
    }
}

$(document).ready(function () {
    $.fn.zTree.init($("#treeDemo2"), setting2, zNodes2);
    $("#checkTrue").bind("click", {type: "checkTrue"}, checkNode);
    $("#checkFalse").bind("click", {type: "checkFalse"}, checkNode);

    $("#treeDemo2").hide();
    $("#log").click(function () {
        $('#treeDemo2').toggle();
    })
});


/*
 左侧的部门树
 */
var setting = {
    view: {
        showIcon: showIconForTree
    },
    data: {
        simpleData: {
            enable: true
        }
    },
    callback: {
        beforeClick: beforeClick
    }
};
var zNodes =[
    { id:1, pId:0, name:"父节点1 - 展开", open:true},
    { id:11, pId:1, name:"父节点11 - 折叠"},
    { id:111, pId:11, name:"叶子节点111"},
    { id:112, pId:11, name:"叶子节点112"},
    { id:113, pId:11, name:"叶子节点113"},
    { id:114, pId:11, name:"叶子节点114"},
    { id:12, pId:1, name:"父节点12 - 折叠"},
    { id:121, pId:12, name:"叶子节点121"},
    { id:122, pId:12, name:"叶子节点122"},
    { id:123, pId:12, name:"叶子节点123"},
    { id:124, pId:12, name:"叶子节点124"},
    { id:13, pId:1, name:"父节点13 - 没有子节点", isParent:true},
    { id:2, pId:0, name:"父节点2 - 折叠"},
    { id:21, pId:2, name:"父节点21 - 展开", open:true},
    { id:211, pId:21, name:"叶子节点211"},
    { id:212, pId:21, name:"叶子节点212"},
    { id:213, pId:21, name:"叶子节点213"},
    { id:214, pId:21, name:"叶子节点214"},
    { id:22, pId:2, name:"父节点22 - 折叠"},
    { id:221, pId:22, name:"叶子节点221"},
    { id:222, pId:22, name:"叶子节点222"},
    { id:223, pId:22, name:"叶子节点223"},
    { id:224, pId:22, name:"叶子节点224"},
    { id:23, pId:2, name:"父节点23 - 折叠"},
    { id:231, pId:23, name:"叶子节点231"},
    { id:232, pId:23, name:"叶子节点232"},
    { id:233, pId:23, name:"叶子节点233"},
    { id:234, pId:23, name:"叶子节点234"},
    { id:3, pId:0, name:"父节点3 - 没有子节点", isParent:true}
];

function showIconForTree(treeId, treeNode) {
    return !treeNode.isParent;
}

var clickRes = 0;
var getName;
function beforeClick(treeId, treeNode, clickFlag) {
    clickRes = 1;
    className = (className === "dark" ? "":"dark");
    getName = treeNode.name;
    showLog2(treeNode.name );
    return (treeNode.click != false);
}

//获取节点名
function showLog2(str) {
    console.log(str)
    /*if (!log) log = $("#log");
     log.append("<li class='"+className+"'>"+str+"</li>");
     if(log.children("li").length > 8) {
     log.get(0).removeChild(log.children("li")[0]);
     }*/
}

$(document).ready(function() {
    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
});



/*
* 添加培训资料的部门树
* */
//***********************************
function searchDepartmentTree_2() {
	$.ajax({
		type : "post",
		target : "#treeDemo10",
		dataType : "json",
		url : contextPath+"/train_initDepartment.action",
		success : getTree_2,
		error : function() {
			alert("请求树失败！");
		}
	});
}

/***********************生成树信息**********************/
function getTree_2(treeList2) {
	var treeList3 = treeList2.departmentTree;
	var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey: "departmentid",
					pIdKey: "updepartmentid",
					rootPId : null,
				},
				key : {
					name : "departmentname",
				}
			},
			callback : {
				
				beforeClick: beforeClick2
			}
	};
	var zNodes = treeList3;
	$.fn.zTree.init($("#treeDemo10"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo10");  
	treeObj.expandAll(false);
}


//***********************************



var el_chooseDepart, className10 = "dark", el_id;
function beforeClick2(treeId, treeNode, clickFlag) {
    className10 = (className10 === "dark" ? "" : "dark");
    el_id = treeNode.departmentid;
    //判断点击的节点是否被选中，返回false 和 true
    if (!treeNode.checked) {//选中
        showLog10(treeNode.departmentname);
        
    } else {                //未选中
        noshowLog10(treeNode.departmentname);
    }
    return (treeNode.doCheck !== false);
}
function showLog10(str) {
    if (!el_chooseDepart) el_chooseDepart = $("#el_chooseDepart");
    /*清空内部的东西*/
    if ($("#el_chooseDepart > li").length > 0) {
        $("#el_chooseDepart").children("li").remove();
    }
    el_chooseDepart.append("<li class='" + className10 + "' id='" + el_id + "'>" + str + "</li>");
    //lixinayuan start
    $("#trainDeptName").prop("value",str);
    //lixianyuan end
    /*判断是否插入进入，若插入进入，关闭树框*/
    if (el_chooseDepart.children("li").length > 0) {
        $("#treeDemo10").hide();
    }
}
function noshowLog10(str) {
    if (!el_chooseDepart) el_chooseDepart = $("#el_chooseDepart");
    el_chooseDepart.children("#" + el_id).remove();
}

function checkNode10(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo10"),
        type = e.data.type,
        nodes = zTree.getSelectedNodes();
    if (type.indexOf("All") < 0 && nodes.length == 0) {
        alert("请先选择一个节点");
    }

    if (type == "checkAllTrue") {
        zTree.checkAllNodes(true);
    } else if (type == "checkAllFalse") {
        zTree.checkAllNodes(false);
    } else {
        var callbackFlag = $("#callbackTrigger").attr("checked");
        for (var i = 0, l = nodes.length; i < l; i++) {
            if (type == "checkTrue") {
                zTree.checkNode10(nodes[i], true, false, callbackFlag);
            } else if (type == "checkFalse") {
                zTree.checkNode10(nodes[i], false, false, callbackFlag);
            } else if (type == "toggle") {
                zTree.checkNode10(nodes[i], null, false, callbackFlag);
            } else if (type == "checkTruePS") {
                zTree.checkNode10(nodes[i], true, true, callbackFlag);
            } else if (type == "checkFalsePS") {
                zTree.checkNode10(nodes[i], false, true, callbackFlag);
            } else if (type == "togglePS") {
                zTree.checkNode10(nodes[i], null, true, callbackFlag);
            }
        }
    }
}
function setAutoTrigger10(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo10");
    zTree.setting10.check.autoCheckTrigger = $("#autoCallbackTrigger").attr("checked");
    $("#autoCheckTriggerValue").html(zTree.setting10.check.autoCheckTrigger ? "true" : "false");
}

$(document).ready(function () {
   // $.fn.zTree.init($("#treeDemo10"), setting10, zNodes10);
	searchDepartmentTree_2()
	
	$("#checkTrue").bind("click", {type: "checkTrue"}, checkNode10);
    $("#checkFalse").bind("click", {type: "checkFalse"}, checkNode10);
    $("#toggle").bind("click", {type: "toggle"}, checkNode10);
    $("#checkTruePS").bind("click", {type: "checkTruePS"}, checkNode10);
    $("#checkFalsePS").bind("click", {type: "checkFalsePS"}, checkNode10);
    $("#togglePS").bind("click", {type: "togglePS"}, checkNode10);
    $("#checkAllTrue").bind("click", {type: "checkAllTrue"}, checkNode10);
    $("#checkAllFalse").bind("click", {type: "checkAllFalse"}, checkNode10);
    $("#autoCallbackTrigger").bind("change", {}, setAutoTrigger10);

    $("#treeDemo10").hide();
    $("#el_chooseDepart").click(function () {
        $('#treeDemo10').toggle();
    })
});

///////////////**********以上是和树有关的js代码**********////////////////



//==***********************初始化资料级别 和 初始化知识点 start *********************===
//初始化知识点
function initKnowledgeType(){
	$.ajax({
		url:contextPath+"/train_initKnowledgeType.action",
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
		
			var opStr = "";
			for(var i=0;i<data.dictionaryList.length;i++){
				var opName =data.dictionaryList[i].dictionaryName;//字典名称
				//将数据按照DOM规则添加到select标签中作为其子标签
				opStr += "<option>"+opName+"</option>";
			}
			$("#trainKnowledge").append(opStr);
		}
	});
}

//初始化资料级别
function initTrainLevel(){
	$.ajax({
		url:contextPath+"/train_initTrainLevel.action",
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
			var opStr = "";
			for(var i=0;i<data.dictionaryList.length;i++){
				var opName =data.dictionaryList[i].dictionaryname;//字典名称
				//将数据按照DOM规则添加到select标签中作为其子标签
				opStr += "<option>"+opName+"</option>";
			}
			$("#trainLevel").append(opStr);
		}
	});
}

//==***********************初始化资料级别 和 初始化知识点 end *********************============






///////////////////以下是删除和批量删除的操作  start//////////////

//--第一列，复选框基本事件-->
$(function () {
    $("#el_checkQuanxuan").change(function () {
        if ($(this).prop("checked") == true) {
            $(".el_checks").prop("checked", "true");
        } else {
            $(".el_checks").removeAttr("checked");
        }
    })
})


/*删除*/
/*function delcfm() {
    $('#delcfmModel').modal();
}*/

/*function urlSubmit() {
    var url = $.trim($("#url").val());//获取会话中的隐藏属性URL
    window.location.href = url;
}*/
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
/*function urlSubmit2() {
    var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
    window.location.href = url;
}*/
///////////////////以下是删除和批量删除的操作  end//////////////

//新版的分页条(最底部的那个)  start
//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
function queryFy(resultCount, currentPage, currentTotal) {
	//分页栏  start
	$('#paginationIDU').pagination(
			{
				//			组件属性
				"total" : resultCount,//数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentTotal,//数字 每一页显示的数量 10
				"pageNumber" : currentPage,//数字 当分页建立时，显示的页数 1
				"pageList" : [ 8, 15, 20 ],//数组 用户可以修改每一页的大小，   
				//功能
				"layout" : [ 'list', 'sep', 'first', 'prev','manual', 'next', 'last', 'links' ],
				"onSelectPage" : function(pageNumber, b) {
					//alert("pageNumber=" + pageNumber);//当前页页号
					// alert("pageSize=" + b);//每页显示的记录数
					//为两个隐藏域赋值  当前页页号   每页显示的记录数
					$("#yeHao").val(pageNumber);//当前页页号
					$("#jiLuShu").val(b);//每页显示的记录数

					//直接调用分页查询的方法
					btnFindFy();
				}
			});
}
//新版的分页条(最底部的那个) end


//清空按钮的事件
function clearBtn() {
	//部门的那个框
	$(".qlqClear").val("");
	$(".curSelectedNode").removeClass("curSelectedNode");
}

//查询按钮的事件
function btnFindFy() {
	$.ajax({
		url : "train_findTrainByFYCondiction.action",
		data : $("#findForm").serialize(),
		dataType : "json",
		type : "POST",
		async : true,
		success : function(data) {
			//数据显示之前 要先清空表格中的所有数据
			$("#tBody tr").remove();
			var list = data.pageInfo.list;//数据
			var tdStr = "";
			for (var i = 0; i < list.length; i++) {
				//培训资料的id
				var documentid = list[i].documentid;
				//资料名称
				var documentname = list[i].documentname;
				//资料类别
				var typename = list[i].typename;
				//资料类型
				var traintype = list[i].traintype;
				//文件大小
				var size = list[i].size;
				//浏览量
				var browsetimes = list[i].browsetimes;
				//所属部门
				var departmentname = list[i].departmentname;
				//上传时间
				var uptime = Format(new Date(list[i].uptime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm");
				//上传人
				var employeename = list[i].employeename;
				
				
				
				//
				tdStr += "<tr>";
				//按照DOM规则动态的添加到表格中
				//隐藏域 隐藏培训资料的主键id
				tdStr += "<input type='hidden' name='documentId' value="+documentid+" class='docID'/>";
				tdStr += "<td><input type='checkbox' class='el_checks'/></td>";
				tdStr += "<td>"	+ documentname+ "</td>";
				tdStr += "<td>" + typename+ "</td>";
				tdStr += "<td>"	+ traintype	+ "</td>";
				tdStr += "<td>" + size	+ "</td>";
				tdStr += "<td>"	+ browsetimes+ "</td>";
				tdStr += "<td>"	+ departmentname+ "</td>";
				tdStr += "<td>"	+ uptime+ "</td>";
				tdStr += "<td>"	+ employeename+ "</td>";
				//
				tdStr += "<td>";
				tdStr += "<a title='查看详情' href='train_findDetail.action?trainContentId="+ documentid+ "'"	+ "><span class='glyphicon glyphicon-search'></span></a>"
				if(hasTrainContentManager){
					tdStr += "<a title='修改信息' href='train_modifyButton.action?trainContentId="+ documentid+ "'"+ "><span class='glyphicon glyphicon-pencil'></span></a>"
					tdStr += "<a href='javascript:void(0)' class='el_delButton' onClick='delcfm(this)' title='删除培训资料'><span class='glyphicon glyphicon-trash'></span></a>";
				}
				tdStr += "</td>";
				//
				tdStr += "</tr>";
				
				//修改
				//删除
				//查看详情
			}
			$("#tBody").append(tdStr);
			

			

			//参数一：总记录数   参数二：当前页页号  参数三：每页显示的记录条数
			queryFy(data.pageInfo.total,data.pageInfo.pageNum,data.pageInfo.pageSize);

			//alert(data.traincontentList.length)
			//alert("执行查询的回掉函数")
		},
		error : function() {
			alert("查询失败")
		}
	});

}

//单条信息删除的点击事件
function delcfm(obj) {
	//alert("哈哈")
	//alert("培训资料的主键id:"+$(obj).parent().siblings("input").val())
	//当前选中的培训资料的主键id
	//docId = $(obj).parent().siblings("input").val();
	$("#dicId").val($(obj).parent().siblings("input").val());
	//alert($(".docID").val())
	$('#delcfmModel').modal();
}

//单条信息删除的确认事件
function urlSubmit() {
	$.ajax({
		url : "${pageContext.request.contextPath}/train_delByTrainID.action",
		data : {
			"documentId" : $("#dicId").val()
		},
		dataType : "json",
		type : "POST",
		async : true,
		success : function(data) {
			alert(data.result);
			//删除成功之后重新重新按照当前的条件进行分页查询数据，就相当于刷新页面的效果了了(注意：这里不能直接刷新页面window.location.reload();)
			//直接调用查询的方法
			btnFindFy();
		},
		error : function() {
			alert("删除操作失败")
		}

	});
}

//批量删除的确定事件
function urlSubmit2() {
	var checkArray = $(".el_checks:checked");//获取到了所有被选中的复选框
	//alert("数组长度:"+checkArray.length)
	//定义一个数组，用来存放获取到的主键id

	//思路：将所有获取到的培训资料的id弄成一个字符串，然后在后台进行将id分割出来,每个id之间用","逗号分隔
	var ids = "";
	checkArray.each(function() {
		ids += $(this).parent().parent().children("input").val()+ ",";
	});
	
	$("#idsDel").val(ids);

	//将获取到的ids字符串发送到后台进行分割
	$.ajax({
		url : "${pageContext.request.contextPath}/train_delByTrainIDsBatch.action",
		data : {
			"ids" : $("#idsDel").val()
		},
		dataType : "json",
		type : "POST",
		async : true,
		success : function(data) {
			alert(data.result);
			//删除成功之后重新重新按照当前的条件进行分页查询数据，就相当于刷新页面的效果了了(注意：这里不能直接刷新页面window.location.reload();)
			//直接调用查询的方法
			btnFindFy();
		},
		error : function() {
			alert("批量删除操作失败")
		}

	});
}







/***********S  QLQ*********************/
var zTree;
var setting = {
	view : {
		addHoverDom : addHoverDom,
		removeHoverDom : removeHoverDom,
		selectedMulti : false
	},
	edit : {
		enable : true,
		editNameSelectAll : true,
		removeTitle : '删除',
		renameTitle : '重命名'
	},
	data : {
		key : {
			name:"typeName"
		},
		/* keep:{
		    parent:true,
		    leaf:true
		}, */
		simpleData : {
			enable : true,
			idKey: "typeId",
			pIdKey: "upId",
			rootPId: 1
		}
	},
	callback : {
		beforeRemove : beforeRemove,//点击删除时触发，用来提示用户是否确定删除
		beforeEditName : beforeEditName,//点击编辑时触发，用来判断该节点是否能编辑
		beforeRename : beforeRename,//编辑结束时触发，用来验证输入的数据是否符合要求
		onRemove : onRemove,//删除节点后触发，用户后台操作
		onRename : onRename,//编辑后触发，用于操作后台
		onClick : clickNode
	//点击节点触发的事件
	}
};
function geneTypeTree(){
	$.getJSON(contextPath+"/trainacontentType_getTraincontenttypeTree.action",function(response){
		var zNodes = response.traincontenttypeTree;
		zTree = $.fn.zTree.init($("#tree"),setting,zNodes);
	});
}

$(document).ready(function() {
	geneTypeTree();
				});


/******S  删除*******/
function beforeRemove(treeId, treeNode) {
	if(confirm("确认删除?\n将会删除下面的所有视频！")){
		if(treeNode.isParent){
			alert("该目录下面还有子目录，请从最底层目录开始删除!");
			return false;
		}
		return true;
	}
	return false;
}
function onRemove(e, treeId,treeNode) {
	var typeId = treeNode.typeId;
	$.post(contextPath+"/trainacontentType_deleteTrainContentTypeById.action",
			{"typeId":typeId},
			function(repsonse){
				alert(repsonse.result);
				if("删除成功"==repsonse.result)//删除成功之后执行查询
					btnFindFy();
			}
			,'json')
	
	
}
/******E  删除*******/

function beforeEditName(treeId,treeNode) {
	/* if(treeNode.isParent){
	    alert("不准编辑非叶子节点！");
	    return false;
	} */
	return true;
}

function beforeRename(treeId,treeNode, newName,isCancel) {
	if (newName.length < 3) {
		alert("名称不能少于3个字符！");
		return false;
	}
	return true;
}
function onRename(e, treeId,treeNode, isCancel) {
	if(confirm("您确认修改类别名称?")){
		$.post(contextPath+"/trainacontentType_updateTraincontenttypeName.action",
				{
			"traincontenttype.typeid":treeNode.typeId,
			"traincontenttype.typename":treeNode.typeName
				},
				function(response){
					if(response != null){
						if("修改成功"==response.result){
							alert(response.result);
						}
					}
				}
				,
		'json');
	}
}

/************S   点击事件*********/
function clickNode(e, treeId,treeNode) {
	$("#trainContentTypeId").val(treeNode.typeId);//向隐藏的类别编号赋值
	$("[name='typeId']").val(treeNode.typeId);//向隐藏的类别编号赋值
	$("#yeHao").val("1");
	btnFindFy();
}
/************E   点击事件*********/


function addHoverDom(treeId,treeNode) {
	var sObj = $("#"+ treeNode.tId+ "_span");
	if (treeNode.editNameFlag|| $("#addBtn_"+ treeNode.tId).length > 0)
		return;
	var addStr = "<span class='button add' id='addBtn_"+ treeNode.tId+ "' title='添加子节点' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+ treeNode.tId);
	if (btn)btn.bind("click",function() {
							if(confirm("确认在该目录下面添加培训内容类别?")){
								var typeName = prompt("请输入类别名称");//获取到的名字
								if(typeName != null){//点击确定
									if(typeName.length>1){
										var upId = treeNode.typeId;//上级编号
										$.post(contextPath+"/trainacontentType_addTraincontenttype.action",
												{
													"traincontenttype.upid":upId,
													"traincontenttype.typename":typeName
												},
												function(response){
													if(response!=null){
														alert(response.result);
													}
													if(response.result == "添加成功" ){
														var traincontenttype = response.traincontenttype;//获取返回来的数据
														zTree.addNodes(treeNode, {typeId:traincontenttype.typeid, upId:treeNode.id, typeName:typeName});
													}
//													geneTypeTree();
												},
											'json');
									}else{
										alert("请输入正确的类别名称")
									}
								}
							}
							//在这里向后台发送请求保存一个新建的叶子节点，父id为treeNode.id,让后将下面的100+newCount换成返回的id
							//zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"新建节点" + (newCount++)});
							return false;
						});
}
function removeHoverDom(treeId,treeNode) {
	$("#addBtn_" + treeNode.tId).unbind().remove();
}


/*(function(){
	alert("sssssssss")
})();
*/


/***********E  QLQ*********************/


/**S   增加培训内容****/
function goToAdd(){
	if($("#trainContentTypeId").val()==null || ""==$("#trainContentTypeId").val()){
		alert("请先选择培训类别!");
		return;
	}
	var form = $("<form action='"+contextPath+"/trainacontentType_forwardToAddTraincontent.action"+"' method='post'></form>")
	form.append("<input type='hidden' name='typeId' value='"+$("#trainContentTypeId").val()+"'>");
	$(document.body).append(form);
	form.submit();
}
/**E   增加培训内容****/