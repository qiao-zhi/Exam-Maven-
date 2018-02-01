/**
 * Created by yorge on 2017/9/15.
 */

/***************************页面加载函数***************************/
var selectedDepartmentID;
var selectFindMethod;
var selectFindMethod_Now;
var findEmp;
$(function () {
	initialTreeSelect(); //初始化部门树	
	findUserByDepId();	//初始化用户信息
	initialRoleSelect(); //初始化组合查询的角色下拉列表框
    $("#el_checkQuanxuan").change(function () {
        if ($(this).prop("checked") == true) {
            $(this).parents("table").children("tbody").find(".el_checks").prop("checked", "true");
        } else {
        	$(this).parents("table").children("tbody").find(".el_checks").removeAttr("checked");
        }
    })
    $("#el_checkQuanxuan2").change(function () {
        if ($(this).prop("checked") == true) {
        	$(this).parents("table").children("tbody").find(".el_checks").prop("checked", "true");
        } else {
        	$(this).parents("table").children("tbody").find(".el_checks").removeAttr("checked");
        }
    })
})

/*********************************分页****************************************/

function depuserinfo_page(currentPage, totalCount,obj,currentCount) {
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
        		findUserByDepId();
        	}else if("2"==selectFindMethod){
        		getUserByCondition();
        	}
        	
        }
    });
}

/****根据部门编号 查询用户*****/
function findUserByDepId(){	
	var i='1';
	selectFindMethod_Now='1';
	$("#departmentid").val(selectedDepartmentID);
	$.ajax({
		url : "user_getUserByDepartId.action",
		data:  $("#userinfo_select").serialize(),
		type : 'POST',
		dataType : 'json',
		success :  function(data){showUserInfo(data,i)},
		error: function(){
			alert("请求失败！");
		}
		});
}



/****根据组合条件 查询用户*****/
function getUserByCondition(){
	var i='2';
	selectFindMethod_Now='2';
	$.ajax({
		url : "user_getUserByCondition.action",
		data:  $("#userinfo_select").serialize(),
		type : 'POST',
		dataType : 'json',
		success : function(data){showUserInfo(data,i)},
		error: function(){
			alert("请求失败！");
		}
		});	
}

function loginAgain(){
	alert("长时间未操作，请重新登录");
	window.location.href="/";
}

//显示用户信息
function showUserInfo(data,obj){
	var result = data;
	//从数据库中查询出来的数据集合
	var userinfo = result.pageBean.productList;
	var showUserInfo = "";
	if(userinfo !=null){
		for(var i=0;i<userinfo.length;i++){
			var index = i+1;
			var rolename='';
			userinfo[i].roles.length>0?rolename="<td class='success' onclick='searchAuth("+JSON.stringify(userinfo[i].roles)+")'>"
					+userinfo[i].roles[0].rolename+"...</td>":rolename="<td>未分配任何角色</td>";
			showUserInfo += "<tr><td><input type='checkbox' class='el_checks' name='userids' id='checkbox_userids' value='"+userinfo[i].userid+"'></td><td>"
							+(index + (result.pageBean.currentPage - 1) * 8) 						
							+"</td><td>"
					+userinfo[i].username+"</td><td class='find_useridcard'>"
					+userinfo[i].useridcard+"</td><td>"
					+userinfo[i].departmentname+"</td><td>"
					+userinfo[i].phone+"</td>"+rolename+"<td>"
					+"<input class='hidden_userid' type='hidden' value='"+userinfo[i].userid +"'/>"
					+"<input class='hidden_departmentid' type='hidden' value='"+userinfo[i].departmentid +"'/>"	
					+"<input class='hidden_password' type='hidden' value='"+userinfo[i].password +"'/>"	
					+"<a href='javascript:void(0)' class='el_delButton' onClick='deleteuser(this)'>删除</a>" +
					 "<a href='javascript:void(0)' class='el_delButton' onclick='el_configRoles(this,"+JSON.stringify(userinfo[i].roles)+")'>配置角色</a>" 
					+"</td></tr>";
		}
		//清空表格
		$("#table_userinfo").empty();
		//添加信息
		$("#table_userinfo").append(showUserInfo);
		
		//当前页
		var currentPage = result.pageBean.currentPage;
		//总条数
		var totalCount = result.pageBean.totalCount;
		//每页的记录数
		var currentCount =result.pageBean.currentCount;
		//调用分页函数
		depuserinfo_page(currentPage, totalCount,obj,currentCount);
		
    	$("#currentPage").val("");
    	$("#currentCount").val("");
	}
}


function showemployeeinfo(data){
	var result = data;
	//从数据库中查询出来的数据集合
	var employeeInfo = result.employees;
	var showEmployeeInfo = "";
	var sex ;
	if(employeeInfo.length>0 || "1"==findEmp){
		for(var i=0;i<employeeInfo.length;i++){
			employeeInfo[i].sex == '1' ? sex='男':sex='女';
			showEmployeeInfo += "<tr><td><input type='checkbox' class='el_checks' name='employeeids' id='checkbox_userids' value='"+employeeInfo[i].employeeid+"'></td><td>"
					+employeeInfo[i].name+"</td><td class='find_useridcard'>"
					+sex+"</td><td>"
					+employeeInfo[i].idcode+"</td></tr>";
		}
		//清空表格
		$("#employeeinfo_without_user").empty();
		//添加信息
		$("#employeeinfo_without_user").append(showEmployeeInfo);
		$('#el_adduse').modal();
		}else{
				alert("该部门的员工已全部注册账户");
			}		
	}

/*添加用户*/
function adduser(){
	var url = $.trim($("#url2").val());
	$.ajax({
		url:"user_addUser.action",
		data:$("#form_adduser").serialize(),
		type : 'POST',
		dataType : 'json',
		success : function(data){
			alert("添加成功！");
			$("#el_adduse").modal('hide');
			if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}			
		}
		
	})
}

function adduser_findempbyname(){
	var employeename=$("#form_find_employeename").val();
	$.ajax({
		url:"user_getEmployeeBynameAnddepartmentid.action",
		data:{"employeename":employeename,departmentid:selectedDepartmentID},
		type : 'POST',
		dataType : 'json',
		success : function(data){
			findEmp='1';
			showemployeeinfo(data);
		}
	})
}
//显示用户的详细角色信息
function searchAuth(obj) {	

	   $('#searchAuth').modal();
	   var showroleinfo='';
	   if (obj!=null) {
		for(var i=0;i<obj.length;i++){
			var rolestatus='';
			obj[i].rolestatus==1?rolestatus='开启':rolestatus='关闭';
			showroleinfo+="<tr><td>" +obj[i].rolename+"</td><td>"
						  			 +rolestatus+"</td>"
						  +"</tr>" 	;
			}
	   }
	 //清空表格
		$("#rolesinfo").empty();
		//添加信息
		$("#rolesinfo").append(showroleinfo);
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
/*显示部门树*/
function showTreeSelect(data){
	var depTree=data.DepTree;
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

function beforeClick(treeId, treeNode, clickFlag) {
    clickRes = 1;
    className = (className === "dark" ? "":"dark");
    var selectedDepartmentName = treeNode.departmentname;
    selectedDepartmentID = treeNode.departmentid;
    findUserByDepId();
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
				*/	optionRole_select += "<option value="+result[i].roleid+">"+result[i].rolename+"</option>";
			}
			//先将下拉列表清空
			$("#select_rolename").empty();
			$("#select_rolename").append(optionRole_select);
		}	
	})
}

/*添加用户-初始化未注册账户的员工信息*/
function el_adduse() {
	findEmp='0';
    //判断是否已经选择了树,跟据上边的clickRes
    if (clickRes == 0) {
        alert("请选选择一个部门！")
    } else {
        /*给模态框中，添加默认部门*/
        $("#addDefaultDepart").prop("value", getName);
        $.ajax({
        	url:"user_getEmployeeWithoutUser.action",
        	data:{"departmentid":selectedDepartmentID},
    		type :"POST",
    		dataType:"json",
    		success:showemployeeinfo
    		/*error:function(jqXHR, textStatus, errorThrown){
    			console.log("jq"+jqXHR.status);
    			console.log("text"+textStatus);
    			console.log("error"+errorThrown);
    			alert("初始化未注册账户的员工信息失败");
    			//location.href="/Exam/unauthorized.jsp";    		
    			}*/
        });
        
        //获得的树的名字： getName
    }
}

/*创建账户*/
function addAcc() {
    var nums = 0;
    $.each($(".el_checks"), function (i, el_check) {
        if ($(this).prop("checked")) {
            nums++;
        }
    });
    if (nums == 1) {
        $("#el_addAccount").modal();
    } else {
        alert("只能选择一个试卷，才能执行此操作！")
    }
}


/*查看/修改账户*/
function modifyAcc() {
    var nums = 0;
    $.each($(".el_checks"), function (i, el_check) {
        if ($(this).prop("checked")) {
            nums++;
        }
    });
 
    if (nums == 1) {
    	var useridcard = $('input[name=userids]:checked').parents("tr").find(".find_useridcard").text();
    	var password=$('input[name=userids]:checked').parents("tr").find(".hidden_password").val();
    	$('#form_update_userinfo_username').val(useridcard);
    	$('#form_update_userinfo_password').val(password);
        $("#el_modifyAccount").modal();
    } else {
        alert("只能选择一个用户，才能执行此操作！")
    }
}

function modify_password(){
	//非空校验
	var isNotNull = $("#form_update_userinfo").validate({
		rules : {
			password:"required"
			
		},
		messages : {
			password:{
				required : "不能为空"
				}
		}
	});
	if(isNotNull.form()){
	var url = $.trim($("#url2").val());
	$.ajax({
		url:"user_updatePassword.action",
		data:$("#form_update_userinfo").serialize(),
		type:"POST",
		datatype:"json",
		success:function(){
			alert("密码修改成功");
			if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
			$("#el_modifyAccount").modal("hide");
		},
		error:function(){
			alert("修改失败");
			if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
		}
	})
	}
}


/*校正用户信息*/
function modify_userinfo(){
	var url = $.trim($("#url2").val());
	$.ajax({
		url:"user_updateUserInfo.action",
		data:$("#form_update_userinfo").serialize(),
		type:"POST",
		datatype:"json",
		success:function(){
			alert("用户信息校正成功");
			if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
			$("#el_modifyAccount").modal("hide");
		},
		error:function(){
			alert("校正失败");
			findUserByDepId();
		}
	})
}


/*配置角色*/
function el_configRoles(obj,obj1) {
	var rolesofuser=obj1;
	var departmentid=$(obj).parent("td").find(".hidden_departmentid").val();
	var userid=$(obj).parent("td").find(".hidden_userid").val();
	$.ajax({
		url:"user_getIsUseRoleByDepartId.action",
		data:{'departmentid':departmentid},
		type:"POST",
		datatype:"json",
		success:function(data){
			var roles=data.roles;
			if(roles.length>0){
			var showrolesinfo='';
			for(var i=0;i<roles.length;i++){
				var checked='';
				for(var j=0;j<rolesofuser.length;j++){
					if(roles[i].roleid==rolesofuser[j].roleid){
						checked='checked';
					}
				}
				showrolesinfo+="<label class='el_radioBox el_radioBox2'>"+
                "<input type='checkbox' name='configRoleForUser' "+checked+" value="+roles[i].roleid+">"+roles[i].rolename+"</label>"
			}
			$("#updateroleinfo_userid").val(userid);
			$("#checkbox_selectrole").empty();
			$("#checkbox_selectrole").append(showrolesinfo);
			$('#el_configRoles').modal();
		   }else{
			   alert("请先为该部门配置角色")
		   }
		},	
	})
}

function updateroleinfo(){
	var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
	$.ajax({
		url:"user_updateRoleinfoForUser.action",
		data:$("#updateRoleinfo").serialize(),
		datatype:"json",
		type:"POST",
		success:function(){
			alert("修改成功");
			$('#el_configRoles').modal('hide');
			if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
		},
		error:function(){
			alert("配置失败");
			$('#el_configRoles').modal('hide');
			if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
		}
	})
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

/************批量删除用户**************/
function deleteusers() {
	//累计选择的个数，若大于1，才执行，否则提示
    var nums = 0;
    $.each($(".el_checks"), function (i, el_check) {
        if ($(this).prop("checked")) {
            nums++;
        }
    });
    if (nums > 1) {
    	if(confirm("确认要删除吗？")){
    	  var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
      	var chk_value =[];   
      	  $('input[name=userids]:checked').each(function(){   
      	   chk_value.push($(this).val());
      	  });
      $.ajax({
  		url:"user_deleteUsersById.action",
  		data:{'userids':chk_value},
  		type :"POST",
  		dataType:"json",
  		traditional:true,
  		success:function(){
  			alert("删除成功");
  			if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
  		},	
      	error:function(){
      		alert("删除失败");
      		if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
      	}
  	})}
    } else {
        alert("请至少选择两列，才能执行此操作！")
    }
	
  }

/************删除用户**************/
function deleteuser(data) {
	if (confirm("确认要删除吗？")){
    var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
    var userid=$(data).parent("td").find(".hidden_userid").val();
    $.ajax({
		url:"user_deleteUsersById.action",
		data:{"userid":userid},
		type :"POST",
		dataType:"json",
		success:function(){
			alert("删除成功");
			if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
		},	
    	error:function(){
    		alert("删除失败");
    		if("1"==selectFindMethod_Now){
        		findUserByDepId();
        	}else if("2"==selectFindMethod_Now){
        		getUserByCondition();
        	}	
    	}
	})
	}
}

/*$(function(){  
    // 设置jQuery Ajax全局的参数  
    $.ajaxSetup({  
        type: "POST",  
        error: function(jqXHR, textStatus, errorThrown){  
            switch (jqXHR.status){  
                case(500):  
                    alert("服务器系统内部错误");  
                    break;  
                case(401):  
                    alert("未登录");  
                    break;  
                case(403):  
                    alert("无权限执行此操作");  
                    break;  
                case(408):  
                    alert("请求超时");  
                    break;  
                default:  
                    alert("未知错误");  
            }  
        },   
        success: function(data){  
            alert("操作成功");  
        }  
    });  
});  */
    