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
    
    
    //页面加载的时候隐藏文件上传的时候转圈圈的图
    //让上传转圈圈的隐藏
   // $("#mode_tips_v2").hide();//隐藏
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
					idKey: "departmentId",
					pIdKey: "upDepartmentId",
					rootPId : null,
				},
				key : {
					name : "departmentName",
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
    el_id = treeNode.departmentId;
    //alert(treeNode.departmentName)
    //判断点击的节点是否被选中，返回false 和 true
    if (!treeNode.checked) {//选中
        showLog10(treeNode.departmentName);
        
    } else {                //未选中
        noshowLog10(treeNode.departmentName);
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
	//初始化部门树
	searchDepartmentTree_2();
	
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


//****************lixianyuan  start******'
//初始化资料类型
function initTrainType(){
	$.ajax({
		url:"${pageContext.request.contextPath}/train_initTrainType.action",
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
			$("#trainType").append(opStr);
		}
	});
}


//初始化知识点
function initKnowledgeType(){
	$.ajax({
		url:contextPath+"/train_initKnowledgeType.action",
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
			//alert("进入回掉函数")
			//alert(data.dictionaryList.length)
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


	//文件上传的方法
	function btnSave(){
		//让上传转圈圈的显示
		//$("#mode_tips_v2").show();//隐藏
		
		//alert("开始进入保按钮存的方法")
		var isNotNull = $("#trainForm").validate({
			ignore : [],
			rules : {
				"traincontent.documentname" : "required",//资料名称
				"traincontent.traintype" : {//资料类型
					required : true
				},//验证下拉框的
				"traincontent.knowledgetype" : {//知识点
					required : true
				},//验证下拉框的
				"traincontent.employeename":"required",//上传人
				"traincontent.departmentname" : "required",  //所属部门 这是一个树     验证文本框的，前面是name 属性
				"attach":"required" //文件
			},
			messages : {
				"traincontent.documentname" : {//资料名称
					required : "不能为空"
				},//下边与上边对应
				"traincontent.traintype" : {//资料类型
					required : "必须选择"
				},
				"traincontent.knowledgetype" : {//知识点
					required : "必须选择"
				},
				"traincontent.employeename" : {//上传人
					required : "不能为空"
				},
				"traincontent.departmentname" : {//所属部门  这是一个部门树
					required : "必须选择"
				},
				"attach":{
					required:"必须选择文件"
				}
			}

		});
		
		
		//以上是用于表单校验的
		//以下是和添加培训资料相关的操作
	if (isNotNull.form()) {
		//文件正在上传的时候让 "保存" "取消" 按钮暂时隐藏
		$("#myUploadFile").hide();//隐藏
		//让上传转圈圈的显示
		$("#mode_tips_v2").show();//显示
		
		console.log(isNotNull);
    		//alert("进入方法")
    		var formData = new FormData($("#trainForm")[0]);
    		 $.ajax({
    			type:"POST",
    			url:"train_addTrainContent.action",
    			data:formData,
    			//必填  
    	        processData: false,  
    	       //必填  
    	        contentType: false,
    			dataType:"json",
    			success:function(data){
    				//进入回掉函数
    				//让上传转圈圈的隐藏
    				$("#mode_tips_v2").hide();//隐藏
    				
    				alert(data.result);
    				
    				//文件上传完成的时候让 "保存" "取消" 按钮恢复 addCancleBtn addSaveBtn
    				$("#myUploadFile").show();//显示 var baseUrlPath = "${pageContext.request.contextPath}";
    				//返回到配许资料管理的界面
    				var url = baseUrlPath+"/view/train/trainManage.jsp";
    				window.location.href =url;
    			},
    			error:function(){
    				alert("增加培训资料失败，请重试");
    				$("#myUploadFile").show();//让保存取消按钮显示
    				var url = baseUrlPath+"/view/train/trainManage.jsp";
    				window.location.href =url;
    			}
    		 }); //ajax的括号
    		
		}//if的括号
		 }//方法的括号



//*******lixianyuan end ***********
