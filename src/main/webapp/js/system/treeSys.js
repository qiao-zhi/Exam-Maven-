/**
 * Created by yorge on 2017/9/15.
 */
/*
左侧的菜单树
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
    { name:"试卷及考试管理", open:true,
        children: [
            { name:"考试管理"},
            { name:"试卷管理"},
            { name:"创建试卷"},
            { name:"监考中心"}
        ]
    },
    { name:"题库管理", open:false,
        children: [
            { name:"试题管理"},
            { name:"题库信息管理"}
        ]
    },
    { name:"成绩管理", open:false,
        children: [
            { name:"成绩信息管理"},
            { name:"员工成绩"}
        ]
    },
    { name:"外来单位管理", open:false,
        children: [
            { name:"外来单位信息管理"},
            { name:"外来单位员工管理"},
            { name:"违章管理"},
            { name:"工程管理"}
        ]
    },
    { name:"部门管理", open:false,
        children: [
            { name:"部门信息管理"},
            { name:"员工管理"}
        ]
    },
    { name:"培训管理", open:false,
        children: [
            { name:"培训内容管理"}
        ]
    },
    { name:"系统管理", open:false ,
        children: [
            { name:"用户管理"},
            { name:"角色管理"},
            { name:"权限管理"},
            { name:"权限管理"},
            { name:"日志管理"}
        ]
    }
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
* 字典树
* */
var zNodes2 =[
    { name:"试卷及考试管理", open:true,
        children: [
            { name:"考试管理"},
            { name:"试卷管理"},
            { name:"创建试卷"},
            { name:"监考中心"}
        ]
    },
    { name:"题库管理", open:false,
        children: [
            { name:"试题管理"},
            { name:"题库信息管理"}
        ]
    },
    { name:"成绩管理", open:false,
        children: [
            { name:"成绩信息管理"},
            { name:"员工成绩"}
        ]
    },
    { name:"外来单位管理", open:false,
        children: [
            { name:"外来单位信息管理"},
            { name:"外来单位员工管理"},
            { name:"违章管理"},
            { name:"工程管理"}
        ]
    },
    { name:"部门管理", open:false,
        children: [
            { name:"部门信息管理"},
            { name:"员工管理"}
        ]
    },
    { name:"培训管理", open:false,
        children: [
            { name:"培训内容管理"}
        ]
    },
    { name:"系统管理", open:false ,
        children: [
            { name:"用户管理"},
            { name:"角色管理"},
            { name:"权限管理"},
            { name:"权限管理"},
            { name:"日志管理"}
        ]
    }
];

$(document).ready(function() {
    $.fn.zTree.init($("#treeDemo5"), setting, zNodes2);
});




/*
* 模态框上的字典
*
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

