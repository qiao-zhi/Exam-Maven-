/* Created by yorge on 2017/9/15.*/

/*<!-- 、查看权限-->*/
var setting3 = {
    check: {
        enable: true
    },
    data: {
        simpleData: {
            enable: true
        }
    }
};

var zNodes3 =[
    { id:1, pId:0, name:"随意勾选 1", open:true,chkDisabled:true},
    { id:11, pId:1, name:"随意勾选 1-1", open:true,chkDisabled:true},
    { id:111, pId:11, name:"随意勾选 1-1-1",chkDisabled:true},
    { id:112, pId:11, name:"随意勾选 1-1-2",chkDisabled:true},
    { id:12, pId:1, name:"随意勾选 1-2", open:true,chkDisabled:true},
    { id:121, pId:12, name:"随意勾选 1-2-1",chkDisabled:true},
    { id:122, pId:12, name:"随意勾选 1-2-2",chkDisabled:true},
    { id:2, pId:0, name:"随意勾选 2", checked:true, open:true,chkDisabled:true},
    { id:21, pId:2, name:"随意勾选 2-1",chkDisabled:true},
    { id:22, pId:2, name:"随意勾选 2-2", open:true,chkDisabled:true},
    { id:221, pId:22, name:"随意勾选 2-2-1", checked:true,chkDisabled:true},
    { id:222, pId:22, name:"随意勾选 2-2-2",chkDisabled:true},
    { id:23, pId:2, name:"随意勾选 2-3",chkDisabled:true}
];

var code;

function setCheck3() {
    var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
        py = $("#py").attr("checked")? "p":"",
        sy = $("#sy").attr("checked")? "s":"",
        pn = $("#pn").attr("checked")? "p":"",
        sn = $("#sn").attr("checked")? "s":"",
        type = { "Y":py + sy, "N":pn + sn};
    zTree.setting.check.chkboxType = type;
    showCode3('setting3.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
}
function showCode3(str) {
    if (!code) code = $("#code");
    code.empty();
    code.append("<li>"+str+"</li>");
}

$(document).ready(function(){
    $.fn.zTree.init($("#treeDemo3"), setting3, zNodes3);
    setCheck3();
    $("#py").bind("change", setCheck3);
    $("#sy").bind("change", setCheck3);
    $("#pn").bind("change", setCheck3);
    $("#sn").bind("change", setCheck3);
});
