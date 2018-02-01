/**
 * Created by yorge on 2017/9/15.
 */

var select_topmenuid;
var selectFindMethod;
/***************************页面加载函数***************************/
$(function(){
	initialTreeSelect(); //初始化菜单树	
	findPermissionByMenutop();	//初始化权限信息
})

//显示用户信息
function showPermissionInfo(data,obj){
	var result = data;
	//从数据库中查询出来的数据集合
	var permissioninfo = result.pageBean.productList;
	var showPermissioninfo = "";
	if(permissioninfo !=null){
		for(var i=0;i<permissioninfo.length;i++){
			var index = i+1;
			var permissionstatus='';
			permissioninfo[i].status==1?permissionstatus="<td>启用</td>":permissionstatus="<td style='color:red'>禁用</td>";
			var permissionstatusOperate='';
			permissioninfo[i].status==1?permissionstatusOperate="<a href='javascript:void(0)' class='el_delButton' onClick='closepermission(this)'>关闭</a>":
				permissionstatusOperate="<a href='javascript:void(0)' class='el_delButton' onClick='openpermission(this)'>开启</a>";
			showPermissioninfo += "<tr><td>"+(index + (result.pageBean.currentPage - 1) * 8) 						
					+"</td><td>"
					+permissioninfo[i].name+"</td>"
					+permissionstatus+"<td>"
					+permissioninfo[i].description+"</td><td>"	
					+"<input class='hidden_permissionid' type='hidden' value='"+permissioninfo[i].permissionid +"'/>"	
					+ permissionstatusOperate
					+"</td></tr>";
		}
		//清空表格
		$("#table_permissioninfo").empty();
		//添加信息
		$("#table_permissioninfo").append(showPermissioninfo);
		
		//当前页
		var currentPage = result.pageBean.currentPage;
		//总条数
		var totalCount = result.pageBean.totalCount;
		//每页的记录数
		var currentCount = result.pageBean.currentCount;
		//调用分页函数
		depuserinfo_page(currentPage, totalCount,obj,currentCount);
		
    	$("#currentPage").val("");
    	$("#currentCount").val("");
	}
}


function initialTreeSelect(){
	//初始化页面的菜单树信息
	
		$.ajax({
			url:"permission_getmenuTreeInfo.action",
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
	var MenuTree=data.MenuTree;
	var setting = {
			data : {
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
			callback : {
				//onClick : onClick
				beforeClick: beforeClick
			}
	};
	var zNodes = MenuTree;
	//添加 树节点的 点击事件；
	var log, className = "dark";
	function onClick(event, treeId, treeNode, clickFlag) {
		clickOnTree(event, treeId, treeNode, clickFlag);
	}
	
	$.fn.zTree.init($("#tree_systemmanage_menu"), setting, zNodes);
	var treeObj = $.fn.zTree.getZTreeObj("tree_systemmanage_menu");  
	treeObj.expandAll(false);
}
function beforeClick(treeId, treeNode, clickFlag) {
    clickRes = 1;

    var selectedDepartmentName = treeNode.departmentname;
    select_topmenuid = treeNode.permissionid;
    findPermissionByMenutop();
}
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
        		findPermissionByMenutop();
        	}else if("2"==selectFindMethod){
        		getPermissionByCondition();
        	}
        	
        }
    });
}

/****根据部门编号 查询用户*****/
function findPermissionByMenutop(){	
	var i='1';
	selectFindMethod='1';
	$("#topmenuid").val(select_topmenuid);
	$.ajax({
		url : "permission_getPermissionBymenutop.action",
		data:  $("#form_permissioninfo").serialize(),
		type : 'POST',
		dataType : 'json',
		success :  function(data){showPermissionInfo(data,i)},
		error: function(){
			alert("请求失败！");
		}
		});
}



/****根据组合条件 查询用户*****/
function getPermissionByCondition(){
	var i='2';
	selectFindMethod='2';
	$.ajax({
		url : "permission_getPermissionByCondition.action",
		data:  $("#form_permissioninfo").serialize(),
		type : 'POST',
		dataType : 'json',
		success : function(data){showPermissionInfo(data,i)},
		error: function(){
			alert("请求失败！");
		}
		});	
}

/************关闭/开启用户**************/
function closepermission(data) {
	
    var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
    var permissionid=$(data).parent("td").find(".hidden_permissionid").val();
    $.ajax({
		url:"permission_updatePermissionStatus.action",
		data:{"permissionid":permissionid,"permissionstatus":'0'},
		type :"POST",
		dataType:"json",
		success:function(){
			alert("权限已关闭");
			if(selectFindMethod=='1'){
				findPermissionByMenutop();
			}else if (selectFindMethod=='2') {
				getPermissionByCondition();
			}
			
		},	
    	error:function(){
    		alert("权限关闭失败");
    		if(selectFindMethod=='1'){
				findPermissionByMenutop();
			}else if (selectFindMethod=='2') {
				getPermissionByCondition();
			}
    	}
	})
}

function openpermission(data) {
	
    var url = $.trim($("#url2").val());//获取会话中的隐藏属性URL
    var permissionid=$(data).parent("td").find(".hidden_permissionid").val();
    $.ajax({
		url:"permission_updatePermissionStatus.action",
		data:{"permissionid":permissionid,"permissionstatus":'1'},
		type :"POST",
		dataType:"json",
		success:function(){
			alert("权限已开启");
			if(selectFindMethod=='1'){
				findPermissionByMenutop();
			}else if (selectFindMethod=='2') {
				getPermissionByCondition();
			}
		},	
    	error:function(){
    		alert("权限开启失败");
    		if(selectFindMethod=='1'){
				findPermissionByMenutop();
			}else if (selectFindMethod=='2') {
				getPermissionByCondition();
			}
    	}
	})
}
