/**
 * Created by yorge on 2017/9/14.
 */
/**
 * Created by yorge on 2017/9/14.
 */
/*
 * examManage
 * */
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
 左侧的题库树
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
 * 模态框中的题库树
 * */
var setting10 = {
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
        beforeCheck: beforeCheck
    }
};

var zNodes10 = [
    {id: 1, pId: 0, name: "部门 1", open: true},
    {id: 101, pId: 1, name: "角色1 1-1", open: true},
    {id: 102, pId: 1, name: "角色2 1-1", open: true},
    {id: 103, pId: 1, name: "角色2 1-1", open: true},
    {id: 11, pId: 1, name: "部门2 1-1", open: true},
    {id: 111, pId: 11, name: "角色1 1-1", open: true},
    {id: 112, pId: 11, name: "角色2 1-1", open: true},
    {id: 113, pId: 11, name: "角色2 1-1", open: true},
    {id: 113, pId: 11, name: "角色3 1-1", open: true},
    {id: 12, pId: 1, name: " 部门3 1-2", open: true},
    {id: 121, pId: 12, name: "角色2 1-1", open: true},
    {id: 122, pId: 12, name: "角色2 1-1", open: true},
    {id: 123, pId: 12, name: "角色3 1-1", open: true},
    {id: 2, pId: 0, name: "一级权限二级权限 2", open: false},
    {id: 21, pId: 2, name: "随意勾选 2-1"},
    {id: 22, pId: 2, name: "随意勾选 2-2", open: true},
    {id: 221, pId: 22, name: "随意勾选 2-2-1"},
    {id: 222, pId: 22, name: "随意勾选 2-2-2"},
    {id: 23, pId: 2, name: "随意勾选 2-3"}
];

var el_chooseDepart, className10 = "dark", el_id;
function beforeCheck(treeId, treeNode) {
    className10 = (className10 === "dark" ? "" : "dark");
    el_id = treeNode.id;
    //判断点击的节点是否被选中，返回false 和 true
    if (!treeNode.checked) {//选中
        showLog10(treeNode.name);
    } else {                //未选中
        noshowLog10(treeNode.name);
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
    $.fn.zTree.init($("#treeDemo10"), setting10, zNodes10);
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

