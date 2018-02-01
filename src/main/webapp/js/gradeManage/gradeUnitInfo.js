/***************************页面加载函数***************************/

$(function(){
	
	findExamGradeInfo();
	
});

/****************************组合条件查询***************************/

//点击页面的查询按钮执行的操作
function searchExamGradeInfo(){
	//执行查询操作前先将隐藏域中的值清空当前页清空或改为第一页
	$("#currentPage").val("");
	//调用查询方法
	findExamGradeInfo();
}

function findExamGradeInfo(){
	$.ajax({
		url : basePathUrl+'/examGrade_getUnitExamGradesByCondition.action',
		data : $("#form_findExamGradesInfo").serialize(),
		type : 'POST',
		dataType : 'json',
		async:true,
		success : showExamGradesInfo,
		error: function(){
			alert("请求失败！");
		}
		});
}

function showExamGradesInfo(data){
	var result = data;
	//console.log(result);
	//当前页显示条数
	var currentCount = result.pageBean.currentCount;
	//从数据库中查询出来的数据集合
	var examGradesInfoList = result.pageBean.productList;
	var showExamGradesListInfo = "";
	if(examGradesInfoList != null){
		for(var i=0;i<examGradesInfoList.length;i++){
			var index = i+1;
			showExamGradesListInfo += "<tr><td>"
								+(index + (result.pageBean.currentPage - 1) * currentCount)
								+"<input type='hidden' class='query_examid' value='"+examGradesInfoList[i].examid+"'/>"
								+"<input type='hidden' class='query_unitid' value='"+examGradesInfoList[i].unitid+"'/>"
								+"</td><td>"
							    +examGradesInfoList[i].examname+"</td><td>"			
				               	+examGradesInfoList[i].examlevel.toString().replace("1","厂级").replace("2","部门级").replace("3","班组级") +"</td><td>"								
								+examGradesInfoList[i].unitname+"</td><td onclick='findEmployeeGradeInfo(this)' class='success'>"
								+examGradesInfoList[i].joinexam+"</td><td>"
								+examGradesInfoList[i].passexam+"</td><td>"	
								+examGradesInfoList[i].status+"</td><td>"
								+examGradesInfoList[i].paperscore+"</td><td>"
								+Format(new Date(examGradesInfoList[i].starttime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")
								+"到"+Format(new Date(examGradesInfoList[i].endtime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")+"</td></tr>";
								
		}
		
		//清空表格
		$("#examGradesListInfo").empty();
		//添加信息
		$("#examGradesListInfo").append(showExamGradesListInfo);
		
		//当前页
		var currentPage = result.pageBean.currentPage;
		//总条数
		var totalCount = result.pageBean.totalCount;
		
		//调用分页函数
		gradeManage_page(currentPage, totalCount,currentCount);
	}
}

/*********************************分页****************************************/

function gradeManage_page(currentPage, totalCount,currentCount) {
	$('#gradeManage_paginationIDU').pagination({
        //组件属性
        "total": totalCount,//数字 当分页建立时设置记录的总数量 
        "pageSize": currentCount,//数字 每一页显示的数量 10
        "pageNumber": currentPage,//数字 当分页建立时，显示的页数 1
        "pageList": [8,15,20],//数组 用户可以修改每一页的大小，
        //功能
        "layout": ['list', 'sep', 'first', 'prev', 'manual', 'next', 'last', 'links'],
        "onSelectPage": function (pageNumber, b) {
        	//向隐藏域中设置值
        	$("#currentPage").val(pageNumber);
        	$("#currentCount").val(b);
        	//调用查找函数
        	findExamGradeInfo();
        }
    });

}

//点击考试信息中的人数执行的操作
function findEmployeeGradeInfo(obj){
	var examId = $(obj).parents("tr").find(".query_examid").val();
	var unitId = $(obj).parents("tr").find(".query_unitid").val();
	$.ajax({
		url:basePathUrl+"/examGrade_getEmployeeGradeInfosByIds.action",
		data:{"examId":examId,"unitId":unitId},
		type:"post",
		dataType:"json",
		success:showUnitEmployeeGradeInfo
	})
}

function showUnitEmployeeGradeInfo(data){
	console.log(data)
	var listInfo = data.employeeGradeInfos;
	var unitEmployeeGradeListInfoStr = "";
	for(var i=0 , length = listInfo.length;i<length;i++){
		var index = i+1;
		unitEmployeeGradeListInfoStr += "<tr><td>"
			+index+"</td><td>"
		    +listInfo[i].employeename+"</td><td>"			
           	+listInfo[i].grade+"</td>"											
			+(listInfo[i].grade>=90?'<td>是':'<td style=color:red>否')+"</td></tr>";				
	}
	$("#unitEmployeeGradeListInfo").empty();
	$("#unitEmployeeGradeListInfo").append(unitEmployeeGradeListInfoStr);
	$("#unitEmployeeInfos").modal();
}


