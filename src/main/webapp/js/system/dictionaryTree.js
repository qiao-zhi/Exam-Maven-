/**
 * 修改字典，字典名称  下拉树
 */
$(function(){
	$("#dicNameUp2").click(function(){
		$("#treeDemo10").css("display","block");
	})
})
function genedistionaryTree(distionaryTrees) {
	//清空
	$("#treeDemo10").text("");
	
	var setting = {
	    view: {
	        selectedMulti: false
	    },
	    check: {
	        enable: true,
	        chkStyle: "radio",
			radioType: "level"
	    },
	    data : {
			simpleData : {
				enable : true,
				idKey : "departmentId",
				pIdKey : "upDepartmentId",
				rootPId : null
			},
			key : {
				name : "departmentName",
			}
		},
		callback : {
			beforeCheck : beforeCheck
		}
	};

	var treeNodes = distionaryTrees;
	$.fn.zTree.init($("#treeDemo10"), setting, treeNodes);
}

var className10 = "dark", el_id;

// 点击前面的单选框之前的事件
function beforeCheck(treeId, treeNode) {
	
	$("#dicNameUp2").val("");
	$("#dicNameUp2").val(treeNode.departmentName);
	
	//字典描述
	$("#dicDescUp2").val("");
	$("#dicDescUp2").val(treeNode.departmentId);
	
	$("#treeDemo10").css("display","none");
}

