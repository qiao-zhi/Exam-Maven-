/*<!--修改单个角色选择权限-->*/
var setting13 = {
    view: {
        selectedMulti: false
    },
    check: {
        enable: true
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

var zNodes13 = [
    {id: 1, pId: 0, name: "mafei", open: true},
    {id: 11, pId: 1, name: "随意勾选 1-1", open: true},
    {id: 111, pId: 11, name: "随意勾选 1-1-1"},
    {id: 112, pId: 11, name: "随意勾选 1-1-2"},
    {id: 12, pId: 1, name: " 1-2", open: true},
    {id: 121, pId: 12, name: "随意勾选 1-2-1"},
    {id: 122, pId: 12, name: "随意勾选 1-2-2"},
    {id: 2, pId: 0, name: "一级权限二级权限 2", open: true},
    {id: 21, pId: 2, name: "随意勾选 2-1"},
    {id: 22, pId: 2, name: "随意勾选 2-2", open: true},
    {id: 221, pId: 22, name: "随意勾选 2-2-1"},
    {id: 222, pId: 22, name: "随意勾选 2-2-2"},
    {id: 23, pId: 2, name: "随意勾选 2-3"}
];

var el_chooseDepart13, className13 = "dark", el_id;
function beforeCheck(treeId, treeNode) {
    className13 = (className13 === "dark" ? "" : "dark");
    el_id = treeNode.id;
    //判断点击的节点是否被选中，返回false 和 true
    if (!treeNode.checked) {//选中
        showLog13(treeNode.name);
    } else {                //未选中
        noshowLog13(treeNode.name);
    }
    return (treeNode.doCheck !== false);
}
function showLog13(str) {
    if (!el_chooseDepart13) el_chooseDepart13 = $(".el_chooseDepart13");
    el_chooseDepart13.append("<li class='" + className13 + "' id='" + el_id + "'>" + str + "</li>");
    if (el_chooseDepart13.children("li").length > 6) {
        el_chooseDepart13.get(0).removeChild(el_chooseDepart13.children("li")[0]);
    }
}
function noshowLog13(str) {
    if (!el_chooseDepart13) el_chooseDepart13 = $(".el_chooseDepart13");
    el_chooseDepart13.children("#" + el_id).remove();
}

function checkNode13(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo13"),
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
                zTree.checkNode13(nodes[i], true, false, callbackFlag);
            } else if (type == "checkFalse") {
                zTree.checkNode13(nodes[i], false, false, callbackFlag);
            } else if (type == "toggle") {
                zTree.checkNode13(nodes[i], null, false, callbackFlag);
            } else if (type == "checkTruePS") {
                zTree.checkNode13(nodes[i], true, true, callbackFlag);
            } else if (type == "checkFalsePS") {
                zTree.checkNode13(nodes[i], false, true, callbackFlag);
            } else if (type == "togglePS") {
                zTree.checkNode13(nodes[i], null, true, callbackFlag);
            }
        }
    }
}
function setAutoTrigger13(e) {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo13");
    zTree.setting13.check.autoCheckTrigger = $("#autoCallbackTrigger").attr("checked");
    $("#autoCheckTriggerValue").html(zTree.setting13.check.autoCheckTrigger ? "true" : "false");
}

$(document).ready(function () {
    $.fn.zTree.init($(".treeDemo13"), setting13, zNodes13);
    $("#checkTrue").bind("click", {type: "checkTrue"}, checkNode13);
    $("#checkFalse").bind("click", {type: "checkFalse"}, checkNode13);
    $("#toggle").bind("click", {type: "toggle"}, checkNode13);
    $("#checkTruePS").bind("click", {type: "checkTruePS"}, checkNode13);
    $("#checkFalsePS").bind("click", {type: "checkFalsePS"}, checkNode13);
    $("#togglePS").bind("click", {type: "togglePS"}, checkNode13);
    $("#checkAllTrue").bind("click", {type: "checkAllTrue"}, checkNode13);
    $("#checkAllFalse").bind("click", {type: "checkAllFalse"}, checkNode13);
    $("#autoCallbackTrigger").bind("change", {}, setAutoTrigger13);

    $(".treeDemo13").hide();
    $(".el_chooseDepart13").click(function () {
        $('.treeDemo13').toggle();
    })
});
