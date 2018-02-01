/**
 * Created by yorge on 2017/9/14.
 */
/* 工程管理 */


/** **S QLQ************ */
/** **S 查询检修部门树**** */
$(document).ready(function() {
		// 查询单位信息
		queryHaulUnit("1","8");
});

// 验证并且保存单位信息到数据库
function saveBlackUnit() {
	var isNotNull = $("#addUnitForm").validate({
        ignore : [],
        rules : {
            "unitname" : {
            	required:true
            	},// 发现日期
            "addtime" : {
                required : true
            },
          
            "description" : {
            	required:true
            },
            "corporation":{
            	required:true
            }
        },
        messages : {
            "unitname" : {
            	required:"单位名称不能为空"
            	},// 发现日期
            "addtime" : {
                required : "拉黑原因不能为空"
            },
            
            "description" : {
            	required:"拉黑原因不能为空"
            },
            "corporation":{
            	required:"单位法人不能为空"
            }
        }
    });
	
	if(isNotNull.form()){
		if (confirm("确认拉黑此单位?")) {
			$.post(contextPath + "/blackUnit_addBlackUnitInfo.action",// url
			$("#addUnitForm").serialize(), // data
			function(result) {
				if (result != null) {
					alert(result);
				}
				// 添加成功之后重新加载页面
				if (result != null && result == "添加成功!") {
					window.location.reload();
				}
			}, 'json')
		}
	}
}
/** ******E 模态框中操作以及保存单位******************** */
/** S 查询单位信息**************** */
function queryHaulUnit(currentPage,currentCount) {
	$.ajax({
		url:contextPath + '/blackUnit_findBlackUnitInfos.action',
		dataType:"json",
		type:"post",
		data:{"currentPage":currentPage,
			  "currentCount":currentCount
		},
		success:showUnitTale		
	})
}
// 添加信息到表格中
function showUnitTale(response) {
	// 如果为空结束方法
	if (response.pageBean == null) {
		return;
	}
	var currentPage = response.pageBean.currentPage;
	var currentCount = response.pageBean.currentCount;
	var totalCount = response.pageBean.totalCount;
	$("#blackUnitListInfo").html("");// 清空表体
	var units = response.pageBean.productList;// 获取所有的单位
	for (var i = 0, length_1 = units.length; i < length_1; i++) {
		var index = (currentPage - 1) * currentCount + i + 1;		
		// 先列出所有的操作
		var delUpdate="--";
			if(hasBlackUnitOperating){
				delUpdate='<a href="javascript:void(0)" onclick="openUpdateModal(this)" title="修改信息"><span class="glyphicon glyphicon-pencil"></span></a>&nbsp;'
					+ ' <a href="javascript:void(0)" onclick="deleteUnit(this)" title="删除此单位"><span class="glyphicon glyphicon-trash"></span></a><br />';
			}
			
		$("#blackUnitListInfo")
				.append(
						'<tr><td>'								
								+ index+'<input type="hidden" class="blackunitid" value="'+units[i].blackunitid+'"/>'
								+ '</td><td>'
								+ units[i].unitname
								+ '</td><td>'
								+ units[i].corporation
								+ '</td><td>'
								+ units[i].addtime
								+ '</td><td>'
								+ units[i].description
								+ '</td><td>'
								+delUpdate
								+ '</td></tr>');
	}
	// 动态开启分页组件
	page(currentPage, totalCount, currentCount);
}
// 显示分页
function page(currentPage, totalCount, currentCount) {
	// 修改分页的基本属性
	$('#paginationIDU').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [ 8,15,20 ],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(pageNumber, pageSize) {
					$("#currentPage").val(pageNumber);
					$("#currentCount").val(pageSize);
					// 查询检修
					queryHaulUnit(pageNumber,pageSize);
				}
			});
}

/** E 查询单位信息**************** */
/** *S 修改单位信息** */
function openUpdateModal(obj) {
	var tr = $(obj).parent().parent();
	var update_id = tr.find(".blackunitid").val();
	var update_name = tr.find("td:eq(1)").text();
	var update_corporation = tr.find("td:eq(2)").text();
	var update_date = tr.find("td:eq(3)").text();
	var update_description = tr.find("td:eq(4)").text();
	$("#update_id").val(update_id);
	$("#update_name").val(update_name);
	$("#update_corporation").val(update_corporation);
	$("#test41").val(update_date);
	$("#update_description").val(update_description);
	
	$("#myModal2").modal({
		backdrop : 'static',
		keyboard : false
	}); // 手动开启
}
function updateUnit() {
	var isNotNull = $("#updateForm").validate({
        ignore : [],
        rules : {
            "unitname" : {
            	required:true
            	},// 发现日期
            "addtime" : {
                required : true
            },
          
            "description" : {
            	required:true
            }
        },
        messages : {
            "unitname" : {
            	required:"单位名称不能为空"
            	},// 发现日期
            "addtime" : {
                required : "拉黑原因不能为空"
            },
            
            "description" : {
            	required:"拉黑原因不能为空"
            }
        }
    });
	
	if(isNotNull.form()){
		if(!confirm("确认修改?")){
			return;
		}
		$.post(contextPath + '/blackUnit_updateBlackUnitInfo.action', $("#updateForm").serialize(),
				function(result) {
					if (result != null && result != null) {
						alert(result);
						if (result == '修改成功!') {
							window.location.reload();
						}
					}
				}, 'json')
	}
}
/** *E 修改单位信息** */

/** ****S 删除检修单位********* */
// 打开删除模态框
function deleteUnit(obj) {
	var tr = $(obj).parent().parent();
	var blackUnitId = tr.find(".blackunitid").val();//获取到ID
	$("#blackUnitId").val(blackUnitId);
	// 打开删除模态框
	$('#delcfmModel').modal({
		backdrop : 'static',
		keyboard : false
	});
}
// 开始删除
function deleteSubmit() {
	$.post(contextPath + "/blackUnit_deleteBlackUnitInfo.action",
			$("#deleteUnitForm").serialize(), function(response) {
				if(response!=null){
					alert(response);
					if(response == '刪除成功!'){
						window.location.reload();
					}
				}
			}, 'json');
}

//ll
/*****************黑名单员工管理*******************/

$(function(){
	findBlackListEmpInfo(1,8);
	
	// 创建方式按钮效果
	$(".el_createButton").click(function() {
		$(".el_createButton").removeClass("btn-default");
		$(".el_createButton").removeClass("btn-primary");
		$(".el_createButton").addClass("btn-default");
		$(this).addClass("btn-primary");
	})
});

//查询黑名单员工信息
function findBlackListEmpInfo(currentPage,currentCount){
	$.ajax({
		url:contextPath +"/blackListEmpOut_findBlackListEmpOutInfo.action",
		dataType:"json",
		type:"post",
		data:{"currentPage":currentPage,
			  "currentCount":currentCount
		},
		success:showBlackListInfo
	})
}

function showBlackListInfo(data){
	var result = data;
	//当前页显示条数
	var currentCount = result.pageBean.currentCount;
	//当前页
	var currentPage = result.pageBean.currentPage;
	//从数据库中查询出来的数据集合
	var blackListInfo = result.pageBean.productList;
	var showBlackListInfoInfoStr = "";
	if(blackListInfo != null){
		for(var i=0;i<blackListInfo.length;i++){
			var index = (currentPage - 1) * currentCount + i + 1;
			showBlackListInfoInfoStr +="<tr><td>"										
									    +index
									    +"<input type='hidden' class='query_employeeId' value='"+blackListInfo[i].employeeId+"'>"
									    +"<input type='hidden' class='query_employeeType' value='"+blackListInfo[i].employeeStatus+"'>"
									    +"</td><td>"									
										+blackListInfo[i].name+"</td><td>"
										+(blackListInfo[i].sex>1?'女':'男')+"</td><td>"
										+(blackListInfo[i].employeeStatus>0?'短委':'长委')+"</td><td>"
										+blackListInfo[i].time.substring(0,10)+"</td><td onclick='findBreakRulesInfo(this)' title='点击查看具体的违章记录' class='el_delButton' style='color:darkblue;'>"
										+blackListInfo[i].minusnum+"</td><td>"
										+blackListInfo[i].blackIdcard+"</td><td>"
										// 有删除修改权限就显示连接
										if (hasBlackDeleteOperating) {
											showBlackListInfoInfoStr += '<a href="javascript:void(0)" onclick="deleteBlackInfo('+blackListInfo[i].id+')" title="删除此单位"><span class="glyphicon glyphicon-trash"></span></a><br />';
										} else {
											showBlackListInfoInfoStr += "--";
										}
			showBlackListInfoInfoStr+="</td></tr>";	
				
		}
		//清空表格
		$("#blackEmpInfolist").empty();
		//添加信息
		$("#blackEmpInfolist").append(showBlackListInfoInfoStr);
		
		
		//总条数
		var totalCount = result.pageBean.totalCount;
		
		//调用分页函数
		fenye(currentPage,totalCount,currentCount);
	}
}

/** *************************分页******************************* */
function fenye(currentPage,totalCount,currentCount) {
	$('#paginationID').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [8],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(currentPage, b) {					
					findBlackListEmpInfo(currentPage,b);
				}
			});
}

//删除操作
function deleteBlackInfo(blackId){
	var isdelete = confirm("您确定要将这个员工从黑名单中删除掉吗？");
	if(isdelete){
		$.ajax({
			url:contextPath +"/blackListEmpOut_deleteBlackListInfo.action",
			dataType:"json",
			type:"post",
			data:{"blackId":blackId				  
			},
			success:function(data){
				alert(data.result);
				findBlackListEmpInfo(1,8);
			}
		})
	}
}

//违章详情查看
function findBreakRulesInfo(obj){
	var employeeType = $(obj).parents("tr").find(".query_employeeType").val();
	var employeeId = $(obj).parents("tr").find(".query_employeeId").val();
	$.ajax({
		url:contextPath +"/blackListEmpOut_selectBreakRulesInfo.action",
		dataType:"json",
		data:{"employeeType":employeeType,"employeeId":employeeId},
		type:"post",		
		success:function(data){
			showBreakRulesInfo(data,employeeType);
		}
	})
}

function showBreakRulesInfo(data,employeeType){
	var result = data;	
	//从数据库中查询出来的数据集合
	var breakRulesListInfo = result.breakListInfo.list;
	var showBreakRulesInfoStr = "";
	if(breakRulesListInfo != null){
		if(employeeType==1){			
			for(var i=0;i<breakRulesListInfo.length;i++){
				var index = i + 1;
				showBreakRulesInfoStr +="<tr><td>"										
					+index+"</td><td>"																			
					+breakRulesListInfo[i].breaktime.substring(0,10)+"</td><td>"
					+breakRulesListInfo[i].minusnum+"</td><td>"
					+breakRulesListInfo[i].breakcontent+"</td></tr>";					
			}
		}else{
			for(var i=0;i<breakRulesListInfo.length;i++){
				var index = i + 1;
				showBreakRulesInfoStr +="<tr><td>"										
					+index+"</td><td>"																			
					+breakRulesListInfo[i].empinbreaktime.substring(0,10)+"</td><td>"
					+breakRulesListInfo[i].empinminusnum+"</td><td>"
					+breakRulesListInfo[i].empinbreakcontent+"</td></tr>";					
			}
		}
		//清空表格
		$("#employeeBreakRulesInfo").empty();
		//添加信息
		$("#employeeBreakRulesInfo").append(showBreakRulesInfoStr);
		$("#el_empTrainDoc_1").modal();
	}
}
