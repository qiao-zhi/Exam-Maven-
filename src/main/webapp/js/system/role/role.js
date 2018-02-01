/**
 * Created by yorge on 2017/9/15.
 */


var selectedDepartmentID;
var selectFindMethod;
var depTree;
var select_permissionid=[];
var select_departmentid=[];
var selectFindMethod_Now;
$(function () {
	initialTreeSelect(); //初始化部门树	
	findRoleByDepId();//初始化页面信息
	initialRoleSelect();//初始化组合查询的角色下拉列表框
    $("#el_checkQuanxuan").change(function () {
        if ($(this).prop("checked") == true) {
            $(".el_checks").prop("checked", "true");
        } else {
            $(".el_checks").removeAttr("checked");
        }
    })
})


function configPermission(obj){
	var roleid=$(obj).parent("td").find(".hidden_roleid").val();
	$("#updatepermissioninfo_roleid").val(roleid);

	$.ajax({
		url:"role_getisHavePermission.action",
		data:{"roleid":roleid},
		type:"POST",
		datatype:"json",
		success:function(data){
			/*<!--修改单个角色选择权限-->*/
			var setting13 = {
			    view: {
			        selectedMulti: false
			    },
			    check: {
			        enable: true,
			        chkboxType: { "Y": "ps", "N": "ps" }
			    },
			    data: {
			    	simpleData : {
						enable : true,
						idKey: "permissionid",
						pIdKey: "parentid",
						rootPId : null,
					},
					key : {
						name : "name",
					}
			    },
			    callback: {
			      onCheck: onCheck_1
			    }
			};
			
			var zNodes13 = data.permissionTree;
			
			var el_chooseDepart13, className13 = "dark", el_id="";
			
		    function onCheck_1(event,treeId, treeNode) {
		    	
				var treeObj=$.fn.zTree.getZTreeObj("treeDemo_permission"); 
				var nodes=treeObj.getCheckedNodes(true);  
			    v=""; 
			    select_permissionid=[];
			    for(var i=0;i<nodes.length;i++){  
			    	select_permissionid.push(nodes[i].permissionid);  	
			    }  
			    $("#hidden_rolepermissionids").val(select_permissionid);
			    className13 = (className13 === "dark" ? "" : "dark");
			    
			    return (treeNode.doCheck !== false);
		    }
			

			function checkNode13(e) {
			    var zTree = $.fn.zTree.getZTreeObj("treeDemo_permission"),
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
			    var zTree = $.fn.zTree.getZTreeObj("treeDemo_permission");
			    zTree.setting13.check.autoCheckTrigger = $("#autoCallbackTrigger").attr("checked");
			    $("#autoCheckTriggerValue").html(zTree.setting13.check.autoCheckTrigger ? "true" : "false");
			}

			$(document).ready(function () {
			    $.fn.zTree.init($("#treeDemo_permission"), setting13, zNodes13);
			    $("#checkTrue").bind("click", {type: "checkTrue"}, checkNode13);
			    $("#checkFalse").bind("click", {type: "checkFalse"}, checkNode13);
			    $("#toggle").bind("click", {type: "toggle"}, checkNode13);
			    $("#checkTruePS").bind("click", {type: "checkTruePS"}, checkNode13);
			    $("#checkFalsePS").bind("click", {type: "checkFalsePS"}, checkNode13);
			    $("#togglePS").bind("click", {type: "togglePS"}, checkNode13);
			    $("#checkAllTrue").bind("click", {type: "checkAllTrue"}, checkNode13);
			    $("#checkAllFalse").bind("click", {type: "checkAllFalse"}, checkNode13);
			    $("#autoCallbackTrigger").bind("change", {}, setAutoTrigger13);

			});
			$('#el_configPermissions').modal();
		}
})
}
//显示用户信息
function showRoleInfo(data,obj){
	var result = data;
	//从数据库中查询出来的数据集合
	var roleinfo = result.pageBean.productList;
	var showRoleInfo = "";
	if(roleinfo !=null){
		for(var i=0;i<roleinfo.length;i++){
			var index = i+1;
			var permissionname='';
			roleinfo[i].permissions.length>0?permissionname="<td class='success' onclick='open_permissioninfo("+JSON.stringify(roleinfo[i].permissions)+")'>"
					+roleinfo[i].permissions[0].name+"...</td>":permissionname="<td>未分配任何权限</td>";
			var rolestatus='';
			roleinfo[i].rolestatus==1?rolestatus="<td>启用</td>":rolestatus="<td style='color:red'>禁用</td>";
			var rolestatusOperate='';
			roleinfo[i].rolestatus==1?rolestatusOperate="<a href='javascript:void(0)' class='el_delButton' onClick='closerole(this)'>关闭</a>":
				rolestatusOperate="<a href='javascript:void(0)' class='el_delButton' onClick='openrole(this)'>开启</a>";
			showRoleInfo += "<tr><td><input type='checkbox' class='el_checks' name='roleids' id='checkbox_roleids' value='"+roleinfo[i].roleid+"'></td><td>"+(index + (result.pageBean.currentPage - 1) * 8) 						
					+"</td><td>"
					+roleinfo[i].rolename+"</td>"
					+rolestatus
					+permissionname+"<td>"
					+roleinfo[i].description+"</td><td>"
					+"<input class='hidden_roleid' type='hidden' value='"+roleinfo[i].roleid +"'/>"
					+"<input class='hidden_departmentid' type='hidden' value='"+roleinfo[i].departmentid +"'/>"	
					+rolestatusOperate+"<a href='javascript:void(0)' class='el_delButton' onclick='updateroleinfo(this)'>修改</a>"+
					 "<a href='javascript:void(0)' class='el_delButton' onclick='configPermission(this)'>配置权限</a>" 
					+"</td></tr>";
		}
		//清空表格
		$("#table_roleinfo").empty();
		//添加信息
		$("#table_roleinfo").append(showRoleInfo);
		
		//当前页
		var currentPage = result.pageBean.currentPage;
		//总条数
		var totalCount = result.pageBean.totalCount;
		//每页的记录数
		var currentCount= result.pageBean.currentCount;
		//调用分页函数
		deproleinfo_page(currentPage, totalCount,obj,currentCount);
		
    	$("#currentPage").val("");
    	$("#currentCount").val("");
	}
}

function updateroleinfo(obj){
	var rolename=$(obj).parents("tr").children("td").eq("2").html();
	var description=$(obj).parents("tr").children("td").eq("5").html();
	var roleid=$(obj).parent("td").find(".hidden_roleid").val();
	
	$("#updateroleinfo_rolename").val(rolename);
	$("#updateroleinfo_description").val(description);
	$("#hidden_update_roleid").val(roleid);
	$("#modifyUserInfo").modal();
}

function updateroleinfo_submit(){
	$.ajax({
		url:"role_updateRoleInfo.action",
		data:$("#form_updateroleinfo").serialize(),
		type:"POST",
		datatype:"json",
		success:function(data){
			alert("修改成功");
			if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
			$("#modifyUserInfo").modal('hide');
		}
	})
}

/*配置权限*/
function el_configPermission(obj,obj1) {
	var permissionOFrole=obj1;
	var roleid=$(obj).parent("td").find(".hidden_roleid").val();
	$.ajax({
		url:"role_getIsUsePermission.action",
		type:"POST",
		datatype:"json",
		success:function(data){
			var permissions=data.permissions;
			if(permissions.length>0){
			var showpermissionsinfo='';
			for(var i=0;i<permissions.length;i++){
				var checked='';
				for(var j=0;j<permissionOFrole.length;j++){
					if(permissions[i].permissionid==permissionOFrole[j].permissionid){
						checked='checked';
					}
				}
				showpermissionsinfo+="<label class='el_radioBox el_radioBox2'>"+
                "<input type='checkbox' name='configPermissionForRole' "+checked+" value="+permissions[i].permissionid+">"+permissions[i].name+"</label>"
			}
			$("#updatepermissioninfo_roleid").val(roleid);
			$("#checkbox_selectpermission").empty();
			$("#checkbox_selectpermission").append(showpermissionsinfo);
			$('#el_configPermissions').modal();
		   }else{
			   alert("暂无可用权限")
		   }
		},	
	})
}

function addRoleinfo(){
	var isNotNull = $("#form_addroleinfo").validate({
		rules : {
			"role.rolename":"required"
			
		},
		messages : {
			"role.rolename":{
				required : "不能为空"
				}
		}
	});
	if(isNotNull.form()){
	
	$("#hidden_departmentid").val(selectedDepartmentID);
	$.ajax({
		url:"role_addRoleinfo.action",
		data:$("#form_addroleinfo").serialize(),
		type:"POST",
		datatype:"json",
		success:function(){
			alert("添加成功");
			findRoleByDepId();
			$('#el_addrole').modal('hide');
			$("#form_rolename").val('');
			$("#form_roledescription").val('');
			$("#rolestatus_use").prop("checked",true);
		}
	})
	}
}

function updatePermissioninfo(){
	var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
	$.ajax({
		url:"role_updatePermissioninfoForRole.action",
		data:$("#updatePermissioninfo").serialize(),
		datatype:"json",
		type:"POST",
		success:function(){
			alert("修改成功");
			if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
			$('#el_configPermissions').modal('hide');
		},
		error:function(){
			alert("配置失败");
			if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
			$('#el_configPermissions').modal('hide');
		}
	})
}

/*********************************分页****************************************/

function deproleinfo_page(currentPage, totalCount,obj,currentCount) {
	if(obj !=null && obj !=""){
		selectFindMethod=obj;
	}
	$('#paginationIDU').pagination({
        //组件属性
        "total": totalCount,//数字 当分页建立时设置记录的总数量 
        "pageSize": currentCount,//数字 每一页显示的数量 10
        "pageNumber": currentPage,//数字 当分页建立时，显示的页数 1
        "pageList": [8, 10],//数组 用户可以修改每一页的大小，
        //功能
        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
        "onSelectPage": function (pageNumber, b) {
            //alert("pageNumber=" + pageNumber);            
           // alert("pageSize=" + b);
        	//向隐藏域中设置值
        	$("#currentPage").val(pageNumber);
        	$("#currentCount").val(b);
        	//调用查找函数
        	if("1"==selectFindMethod){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod){
        		getRoleByCondition();
        	}
        	
        }
    });
}

/****根据部门编号 查询用户*****/
function findRoleByDepId(){	
	var i='1';
	selectFindMethod_Now='1';
	$("#departmentid").val(selectedDepartmentID);
	$.ajax({
		url : "role_getRolesByDepartmentId.action",
		data:  $("#form_conditionfind_role").serialize(),
		type : 'POST',
		dataType : 'json',
		success :  function(data){showRoleInfo(data,i)},
		error: function(){
			alert("请求失败！");
		}
		});
}

/****根据组合条件 查询角色*****/
function getRoleByCondition(){
	var i='2';
	selectFindMethod_Now='2';
	$.ajax({
		url : "role_getRoleByCondition.action",
		data:  $("#form_conditionfind_role").serialize(),
		type : 'POST',
		dataType : 'json',
		success : function(data){showRoleInfo(data,i)},
		error: function(){
			alert("请求失败！");
		}
		});	
}

//初始化页面的部门树信息
function initialTreeSelect(){
	$.ajax({
		url:"user_getDepTreeInfo.action",
		type :"POST",
		dataType:"json",
		success:showTreeSelect,
		error:function(){
			alert("初始化部门树失败，请重新登录")
		}
	})
}

//初始化组合查询的 角色 下拉列表框
function initialRoleSelect(){
	$.ajax({
		url:"user_getRolenameAndRoleidByDepartid.action",
		type :"POST",
		dataType:"json",
		success:function(data){
			//alert(data.questionBankNameList.length)
			var result=data.roles;
			var optionRole_select = "<option value=''>--请选择--</option>";
			for(var i=0;i<result.length;i++){
				/*if(i==0)
					//设置默认选中第一条  value值设置题库ID，标签中间设置题库名称
					optionRole_select += "<option value='" + result[i].questionBankId+"' selected>"+result[i].questionBankName+"</option>";
				else	
				*/	optionRole_select += "<option value="+result[i].rolename+">"+result[i].rolename+"</option>";
			}
			//先将下拉列表清空
			$("#select_rolename").empty();
			$("#select_rolename").append(optionRole_select);
		}	
	})
}

function open_permissioninfo(obj){
	  $('#modal_permissioninfo').modal();
	   var showpermissioninfo='';
	   if (obj!=null) {
		for(var i=0;i<obj.length;i++){
			var permissionstatus='';
			obj[i].status==1?permissionstatus='开启':permissionstatus='关闭';
			showpermissioninfo+="<tr><td>" +obj[i].name+"</td><td>"
						  			 +permissionstatus+"</td>"
						  +"</tr>" 	;
			}
	   }
	 //清空表格
		$("#permissionsinfo").empty();
		//添加信息
		$("#permissionsinfo").append(showpermissioninfo);
}

/*显示部门树*/
function showTreeSelect(data){
	depTree=data.DepTree;
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
				//onClick : onClick
				beforeClick: beforeClick
			}
	};
	var zNodes = depTree;
	//添加 树节点的 点击事件；
	var log, className = "dark";
	function onClick(event, treeId, treeNode, clickFlag) {
		clickOnTree(event, treeId, treeNode, clickFlag);
	}
	$.fn.zTree.init($("#tree_systemmanage_department"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("tree_systemmanage_department");  
	treeObj.expandAll(false);
}
var clickRes = 0;
function beforeClick(treeId, treeNode, clickFlag) {
	
	var log, className = "dark";
    clickRes = 1;
    className = (className === "dark" ? "":"dark");
    var selectedDepartmentName = treeNode.departmentname;
    selectedDepartmentID = treeNode.departmentid;
    $.ajax({
		url : "role_getRolesByDepartmentId.action",
		data:  {"departmentid":selectedDepartmentID},
		type : 'POST',
		dataType : 'json',
		success : function(data){showRoleInfo(data)},
		error: function(){
			alert("请求失败！");
		}
		});
}

/*添加角色*/
function el_addrole() {
    //判断是否已经选择了树,跟据上边的clickRes
    if (clickRes == 0) {
        alert("请选选择一个树部门！")
    } else {
        /*给模态框中，添加默认部门*/
        //$("#addDefaultDepart").prop("value", getName);
        $('#el_addrole').modal();
        //获得的树的名字： getName
    }
}


/*信息删除*/
function delcfm() {
    $('#delcfmModel').modal();
}
function urlSubmit() {
    var url = $.trim($("#url").val());//获取会话中的隐藏属性URL
    window.location.href = url;
}


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
function urlSubmit2() {
    var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
    window.location.href = url;
}



/*查看权限*/
function searchauth() {
    //累计选择的个数，若等于1，才执行，否则提示
    var nums = 0;
    $.each($(".el_checks"), function (i, el_check) {
        if ($(this).prop("checked")) {
            nums++;
        }
    });
    if (nums == 1) {
        $('#searchAuth').modal();
    } else {
        alert("请至选择一个角色！")
    }
}


/************批量删除角色**************/
function deleteRoles() {
	 //累计选择的个数，若大于1，才执行，否则提示
    var nums = 0;
    $.each($(".el_checks"), function (i, el_check) {
        if ($(this).prop("checked")) {
            nums++;
        }
    });
    if (nums > 0) {
    	if(confirm("确认要删除吗？")){
    	var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
       	var chk_value =[];   
       	  $('input[name=roleids]:checked').each(function(){   
       	   chk_value.push($(this).val());
       	  });
       $.ajax({
   		url:"role_deleteRoleById.action",
   		data:{'roleids':chk_value},
   		type :"POST",
   		dataType:"json",
   		traditional:true,
   		success:function(){
   			alert("删除成功");
   			if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
   		},	
       	error:function(){
       		alert("删除失败");
       		if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
       	}
   	})}
    } else {
        alert("请至少选择1列，才能执行此操作！")
    }
	
 }

/************关闭/开启用户**************/
function closerole(data) {
	
    var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
    var roleid=$(data).parent("td").find(".hidden_roleid").val();
    $.ajax({
		url:"role_updateRoleStatus.action",
		data:{"roleid":roleid,"rolestatus":'0'},
		type :"POST",
		dataType:"json",
		success:function(){
			alert("角色已关闭");
			if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
		},	
    	error:function(){
    		alert("角色关闭失败");
    		if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
    	}
	})
	
}

function openrole(data) {
	
    var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
    var roleid=$(data).parent("td").find(".hidden_roleid").val();
    $.ajax({
		url:"role_updateRoleStatus.action",
		data:{"roleid":roleid,"rolestatus":'1'},
		type :"POST",
		dataType:"json",
		success:function(){
			alert("角色已开启");
			if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
		},	
    	error:function(){
    		alert("角色开启失败");
    		if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
    	}
	})
	
}

function modal_addmorerole(){
	$("#treeDemo10").empty();
	$("#treeDemo13").empty();
	$("#treeDemo10").css("display","block !important");
	$("#treeDemo13").css("display","block !important");
	$('#batchAddRoles').modal();
	
	showDepartTree();
	getPermissionTree();
}


function showDepartTree() {
    var setting10 = {
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
        view: {
            selectedMulti: false
        },
        check: {
            enable: true,
            chkboxType: { "Y": "", "N": "" }//
        },
        callback: {
            onCheck: onCheck
        }
    };

    var zNodes10 = depTree;

    var el_chooseDepart1, className10 = "dark", el_id;

    //点击事件
    function onCheck(event,treeId, treeNode) {
    	var treeObj=$.fn.zTree.getZTreeObj("treeDemo10"); 
    	var nodes=treeObj.getCheckedNodes(true);  
        v=""; 
        select_departmentid=[];
        for(var i=0;i<nodes.length;i++){  
        	select_departmentid.push(nodes[i].departmentid);  	
        }  
      
        $("#hidden_departmentids").val(select_departmentid);
    	
        className10 = (className10 === "dark" ? "" : "dark");
        el_id = treeNode.departmentid;
        
        //判断点击的节点是否被选中，返回false 和 true 
        if (treeNode.checked) {//选中
            showLog10(treeNode.departmentname+",",el_id);
        } else {               //点击选中，让其未选中
            noshowLog10(treeNode.departmentname+",",el_id);
            var parentzTree = treeNode.getParentNode();
        }
        return (treeNode.doCheck !== false);
    }

    //选中，执行
    function showLog10(str, el_id) {
        if (!el_chooseDepart1) 
        	el_chooseDepart1 = $("#el_chooseDepart1");
        
        el_chooseDepart1.append("<li class='" + className10 + "' id='" + el_id + "'>" + str + "</li>");
        
    }
    
    //取消选中，执行
    function noshowLog10(str, el_id) {
    	
        if (!el_chooseDepart1) 
        	el_chooseDepart1 = $("#el_chooseDepart1");
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
        
       /* $("#el_chooseDepart1").click(function () {
        	
        	     	
            //$('#treeDemo10').toggle();
        })*/
    });
}
function treeClick(){
	if($("#treeDemo10").is(":hidden")) {
		$("#treeDemo10").show();
	} else{
		$("#treeDemo10").hide();
	}  
}

function addMoreRoleWithPermission(){
	
	var isNotNull = $("#form_addmorerole").validate({
		rules : {
			"role.rolename":"required"
			
		},
		messages : {
			"role.rolename":{
				required : "不能为空"
				}
		}
	});
	
	if(select_departmentid.length<=0){
		alert("请至少选择一个部门")
	}else if(select_permissionid.length<=0){
		alert("请分配权限")
	}
	
	
	if(isNotNull.form() && select_permissionid.length>0 && select_departmentid.length>0 ){
	
	$.ajax({
		url:"role_addMoreRole.action",
		data:$("#form_addmorerole").serialize(),
		type :"POST",
		dataType:"json",
		success:function(data){
			alert("添加成功");
			if("1"==selectFindMethod_Now){
        		findRoleByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getRoleByCondition();
        	}
			$("#batchAddRoles").modal('hide');
			$('#form_addmorerole').form("clear");
			$("#el_chooseDepart13").find("li").remove(); 
			$("#el_chooseDepart1").find("li").remove();
		}
	})
	}
}


function getPermissionTree(){
	$.ajax({
		url:"permission_getPermissionTree.action",
		type :"POST",
		dataType:"json",
		success:function(data){showPermissionTree(data)}
	})
}

function showPermissionTree(data){
	/*<!--修改单个角色选择权限-->*/
	var setting13 = {
	    view: {
	        selectedMulti: false
	    },
	    check: {
	        enable: true,
	        chkboxType: { "Y": "ps", "N": "ps" }
	    },
	    data: {
	    	simpleData : {
				enable : true,
				idKey: "permissionid",
				pIdKey: "parentid",
				rootPId : null,
			},
			key : {
				name : "name",
			}
	    },
	    callback: {
	      onCheck: onCheck_1
	    }
	};
	
	var zNodes13 = data.permissionTree;
	
	var el_chooseDepart13, className13 = "dark", el_id="";
	
    function onCheck_1(event,treeId, treeNode) {
    	
		var treeObj=$.fn.zTree.getZTreeObj("treeDemo13"); 
		var nodes=treeObj.getCheckedNodes(true);  
	    v=""; 
	    select_permissionid=[];
	    for(var i=0;i<nodes.length;i++){  
	    	select_permissionid.push(nodes[i].permissionid);  	
	    }  
	    console.log(select_permissionid);
	    $("#hidden_permissionids").val(select_permissionid);
	    var name='';
	    for(var i=0;i<nodes.length;i++){  
	    	name+=nodes[i].name;  	
	    }  

	    className13 = (className13 === "dark" ? "" : "dark");
	    el_id = treeNode.permissionid;
	    
	    if (treeNode.checked) {//选中
	        showLog13(treeNode.name+",");
	    } else {                //点击选中，向让其未选中
	        noshowLog13(treeNode.name+",");
	        var parentzTree = treeNode.getParentNode();
	        
	    }
	    return (treeNode.doCheck !== false);
    }
	
	function showLog13(str) {
	    if (!el_chooseDepart13) el_chooseDepart13 = $("#el_chooseDepart13");
	    
	    el_chooseDepart13.append("<li class='" + className13 + "' id='" + el_id + "'>" + str + "</li>");
	    
	}
	function noshowLog13(str) {
	    if (!el_chooseDepart13) el_chooseDepart13 = $("#el_chooseDepart13");
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
	    $.fn.zTree.init($("#treeDemo13"), setting13, zNodes13);
	    $("#checkTrue").bind("click", {type: "checkTrue"}, checkNode13);
	    $("#checkFalse").bind("click", {type: "checkFalse"}, checkNode13);
	    $("#toggle").bind("click", {type: "toggle"}, checkNode13);
	    $("#checkTruePS").bind("click", {type: "checkTruePS"}, checkNode13);
	    $("#checkFalsePS").bind("click", {type: "checkFalsePS"}, checkNode13);
	    $("#togglePS").bind("click", {type: "togglePS"}, checkNode13);
	    $("#checkAllTrue").bind("click", {type: "checkAllTrue"}, checkNode13);
	    $("#checkAllFalse").bind("click", {type: "checkAllFalse"}, checkNode13);
	    $("#autoCallbackTrigger").bind("change", {}, setAutoTrigger13);

	    $("#treeDemo13").hide();
	});
/*	$("#el_chooseDepart13").click(function () {
        $('#treeDemo13').toggle();
    })*/
}
function treeClick13(){
	if($("#treeDemo13").is(":hidden")) {
		$("#treeDemo13").show();
	} else{
		$("#treeDemo13").hide();
	}  
}