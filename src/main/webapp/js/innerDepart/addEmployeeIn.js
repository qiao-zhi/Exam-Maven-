


//验证表单 
function check(form){
	var flag0 = true;
	var name = document.getElementById("previewImg").value;
	
	if(name==""){
		 $("#message0").text("必须添加你的头像!");
	     $("#message0").show();
	     flag0= false; 
	}else{
	      flag0= true;
	      }
	
	
	
	var flag = true;
	var name = document.getElementById("addEmployeeInName").value;
	
	var myname = /^[\u4E00-\u9FA5A-Za-z]+$/;
	
	if(name==""){
		 $("#message1").text("必须输入姓名!");
	     $("#message1").show();
	     flag = false; 
	}/*else if(!myname.exec(name)){
		alert("nihao");
	    	$("#message1").text("只能输入中文和英文，请重新输入!");
		    $("#message1").show();
	//document.getElementById("updateEmployeeInName").onfocus(); //强制性获取焦点,当然可以根据需求自己来定制
	      flag= false;   
	      }*/
	var flag2 = true;
	var name2 = document.getElementById("addEmployeeInPhone").value;

	//var isPhone = /(^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$)|(^((\(\d{3}\))|(\d{3}\-))?(1[358]\d{9})$)/;
	
	var isPhone = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	var tel = /^\d{3,4}-?\d{7,9}$/; 
	var tel2 = /^\d{7,9}$/;
	if(name2==""){
		$("#message3").text("必须输入联系方式!");
        $("#message3").show();
        flag2 = false; 
	}else if(!isPhone.exec(name2)){
		
		if(!tel.exec(name2)){
			
			if(!tel2.exec(name2)){
				 $("#message3").text("请正确填写您的联系方式!");
				 $("#message3").show();
				 flag2 = false; 
			}
			
		}
	     
	      
	//document.getElementById("updateEmployeeInPhone").onfocus(); //强制性获取焦点,当然可以根据需求自己来定制
	      //flag2 = false;   
	      }
	var flag3 = true;
	var name3 = document.getElementById("test2").value;
	
	var birthday = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;  
	if(name3==""){
		$("#message2").text("必须输入出生日期!");
	       $("#message2").show();
	   flag3 = false; 
	}else if(!birthday.exec(name3)){
	       
	       $("#message2").text("日期的格式不正确!");
		       $("#message2").show();
		       
	//document.getElementById("updateEmployeeInBirthday").onfocus(); //强制性获取焦点,当然可以根据需求自己来定制
	      flag3 = false;   
	      }
	
	
	var flag4 = true;
	var name4 = document.getElementById("addEmployeeIdCode").value;

	var isIDCard=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
	
	if(name4==""){
		 $("#message4").text("必须输入你的身份证");
		 $("#message4").show();
		 flag4= false; 
	}else if(!isIDCard.exec(name4)){
	      
	       $("#message4").text("身份证号格式错误，请重新输入!");
		   $("#message4").show();
	//document.getElementById("updateEmployeeInIdcode").onfocus(); //强制性获取焦点,当然可以根据需求自己来定制
	      flag4= false;   
	}else if(isIDCard.exec(name4)){
    	  
  	  	$.ajax({
  			url : '/Exam/employeein_isIdCode.action',
  			data : {"myIdcode":name4},
  			type : 'POST',
  			dataType : 'json',
  			async:true,
  			success : function(result) {
  				
  				if(false==result.flag){
  					$("#message4").text("该员工已存在，不能再次添加");
  					$("#message4").show();
  					  flag4 =false;  
  				}
  			}
  		});
  	  
    }
	
	var flag5 = true;
	var name5 = document.getElementById("addEmployeeInDepartment").value;

	
	if(name5==""){
		 $("#message5").text("必须选择员工所在的部门");
		 $("#message5").show();
		 flag5= false; 
	}


	if(flag0){
	if(flag){
		
		
		if(flag2){
		
			if(flag3){
			
				if(flag4){
					if(flag5){
						return true;
					}
					
				}
			}
		}
		
		
	}
	}
	/*if(flag||!flag2||flag3||flag4){
		return true;
	}*/
	return false;
	
}


$(function(){

	searchDepartmentTree_1();
	
	$("#el_chooseDepart").bind('click',function(){
		$("#treeDemo9").toggle();
	});
	
	/*var hiV = $("#addEmployeeInDepartment").val();
	if ($("#el_chooseDepart > li").length > 0) {//先清空
		$("#el_chooseDepart").children("li").remove();
	}
	$("#el_chooseDepart").append("<li class='dark'>" + hiV + "</li>");
	*/
	
	var hiV = $("#addEmployeeInDepartment").val();
	if($("#el_chooseDepart > li").length == 0){
		$("#el_chooseDepart").append("<li class='dark'>" + hiV + "</li>");
	}
});



/***********************请求树信息**********************/

function searchDepartmentTree_1() {
		$.ajax({
			type : "post",
			dataType : "json",
			url : "/Exam/department_getDepartmentTree.action",
			success : getTree_1,
			error : function() {
				alert("请求树失败！");
			}
		});
	}
	
/***********************生成树信息**********************/
function getTree_1(result) {
		var treeList = result.treeList;
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
					onClick : function(event, treeId, treeNode){
						
						$("#addEmployeeInDepartment").val(treeNode.departmentname);
	
						
						
						var hiV = $("#addEmployeeInDepartment").val();
						if ($("#el_chooseDepart > li").length > 0) {//先清空
							$("#el_chooseDepart").children("li").remove();
						}
						//插入值
						$("#el_chooseDepart").append("<li class='" + className + "'>" + hiV + "</li>");
						 if ($("#el_chooseDepart").children("li").length > 0) {
								
						        $("#treeDemo9").hide();
						    }
					}
				}
		};
		var zNodes = treeList;
		//添加 树节点的 点击事件；
		var log, className = "dark";
		function onClick(event, treeId, treeNode, clickFlag) {
			clickOnTree(event, treeId, treeNode, clickFlag);
		}
		$.fn.zTree.init($("#treeDemo9"), setting, zNodes);
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo9");  
		treeObj.expandAll(false);
	
	}
/**
 * 验证
 */

//姓名验证

 function checkName(){   
	
	var name = document.getElementById("updateEmployeeInName").value;
	
	var myname = /^[\u4E00-\u9FA5A-Za-z]+$/;
	if(name==""){
		 $("#message1").text("必须输入姓名!");
	     $("#message1").show();
	     return false; 
	}else if(!myname.exec(name)){
	    	$("#message1").text("只能输入中文和英文，请重新输入!");
		       $("#message1").show();
	//document.getElementById("updateEmployeeInName").onfocus(); //强制性获取焦点,当然可以根据需求自己来定制
	      return false;   
	      }else{
	      return true;
	      }
}

function yincang1(){
    	$("#message1").hide();
    } 
//出生日期验证
function checkBirthday(){                                                            
	var name = document.getElementById("updateEmployeeInBirthday").value;
	
	var birthday = /^(19|20)\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$/;  
	if(name==""){
		$("#message2").text("必须输入出生日期!");
	       $("#message2").show();
	    return false; 
	}else if(!birthday.exec(name)){
	       
	       $("#message2").text("日期的格式不正确!");
		       $("#message2").show();
	//document.getElementById("updateEmployeeInBirthday").onfocus(); //强制性获取焦点,当然可以根据需求自己来定制
	      return false;   
	      }else{
	      return true;
	      }
}

 function yincang2(){
	$("#message2").hide();
} 

//手机号验证
function checkPhone(){                                                            
	var name = document.getElementById("updateEmployeeInPhone").value;
	
	var isPhone = /^1[35789]\d{9}$/;
	if(name==""){
		$("#message3").text("必须输入联系方式!");
	       $("#message3").show();
	       return false; 
	}else if(!isPhone.exec(name)){
	     
	       $("#message3").text("手机号码格式错误，请重新输入!");
		       $("#message3").show();
	//document.getElementById("updateEmployeeInPhone").onfocus(); //强制性获取焦点,当然可以根据需求自己来定制
	      return false;   
	      }else{
	    	  $("#message3").show(); 
	      return true;
	      }
}

function yincang3(){
	$("#message3").hide();
}
//身份证验证
function checkIdcode(){                                                            
var name = document.getElementById("updateEmployeeInIdcode").value;

var isIDCard=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/;
if(name==""){
	 $("#message4").text("必须输入你的身份证");
	     $("#message4").show();
	    return false; 
}else if(!isIDCard.exec(name)){
      
       $("#message4").text("身份证号格式错误，请重新输入!");
	       $("#message4").show();
//document.getElementById("updateEmployeeInIdcode").onfocus(); //强制性获取焦点,当然可以根据需求自己来定制
      return false;   
      }else{
      return true;
      }
    } 
function yincang4(){
	$("#message4").hide();
}

