/**
 * Created by yorge on 2017/9/14.
 */

/*<!--参考部门  复选框-->*/
$(function () {
    var setting10 = {
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

    var zNodes10 = [
        {id: 1, pId: 0, name: "一级权限 1", open: true},
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

    var el_chooseDepart1, className10 = "dark", el_id;

    function beforeCheck(treeId, treeNode) {
        className10 = (className10 === "dark" ? "" : "dark");
        el_id = treeNode.id;
        //判断点击的节点是否被选中，返回false 和 true
        if (!treeNode.checked) {//选中
            showLog10(treeNode.name);
        } else {                //点击选中，向让其未选中
            noshowLog10(treeNode.name);

            /*若是点击的该子节点是他父节点下的所有子节点中最后一个子节点
             * 则把这个父节点也取消掉
             * */
            var parentzTree = treeNode.getParentNode();
            /*parentzTree.getSelectedNodes.each(function () {
             alert("a")
             })*/
            /* var childNodes = zTree.transformToArray(parentzTree);
             alert(childNodes)
             var nodes = new Array();
             for(i = 0; i < childNodes.length; i++) {
             nodes[i] = childNodes[i].id;
             }*/
            //treeNode.getParentNode().getAllChildNodes().each(function () {
            //    alert("b")
            //})
            //alert(treeNode.getParentNode().name)
        }
        return (treeNode.doCheck !== false);
    }

    function showLog10(str) {
        if (!el_chooseDepart1) el_chooseDepart1 = $("#el_chooseDepart1");
        el_chooseDepart1.append("<li class='" + className10 + "' id='" + el_id + "'>" + str + "</li>");
        if (el_chooseDepart1.children("li").length > 6) {
            el_chooseDepart1.get(0).removeChild(el_chooseDepart1.children("li")[0]);
        }
    }

    function noshowLog10(str) {
        if (!el_chooseDepart1) el_chooseDepart1 = $("#el_chooseDepart1");
        el_chooseDepart1.children("#" + el_id).remove();

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
        $("#el_chooseDepart1").click(function () {
            $('#treeDemo10').toggle();
        })
    });
})