/***************************页面加载函数***************************/

$(function(){	
	$("input[name=examName]").val(selectExamName);
	findEmployeeGradeInfo();
	
});

/****************************组合条件查询***************************/

//点击页面的查询按钮执行的操作
function searchEmployeeGradeInfo(){
	//执行查询操作前先将隐藏域中的值清空当前页清空或改为第一页
	$("#currentPage").val("");
	//设置考试ID的val为空
	$("#examId").val("");
	//调用查询方法
	findEmployeeGradeInfo();
}

function findEmployeeGradeInfo(){
	$.ajax({
		url : 'examGrade_findEmployeeGradeInfoByCondition.action',
		data : $("#form_findEmployeeGradesInfo").serialize(),
		type : 'POST',
		dataType : 'json',
		async:true,
		success :showEmployeeGradeInfo,
		error: function(){
			alert("请求失败！");
		}
		});
}
//查询成功后执行的回调函数
function showEmployeeGradeInfo(data){
	var result = data;
	//console.log(result);
	//当前页显示条数
	var currentCount = result.pageBean.currentCount;
	//从数据库中查询出来的数据集合
	var employeeGradesInfoList = result.pageBean.productList;
	var showEmployeeGradesListInfo = "";
	if(employeeGradesInfoList != null){
		for(var i=0;i<employeeGradesInfoList.length;i++){
			var index = i+1;	
			showEmployeeGradesListInfo += "<tr><td>"
								+(index + (result.pageBean.currentPage - 1) * currentCount)
								+"<input class='find_idcode' type='hidden' value='"+employeeGradesInfoList[i].idcode +"'/>"
							    +"<input class='find_departmentname' type='hidden' value='"+employeeGradesInfoList[i].departmentname +"'/>"
							    +"<input class='find_paperscore' type='hidden' value='"+employeeGradesInfoList[i].paperscore +"'/>"	
							    +"<input class='find_examId' type='hidden' value='"+employeeGradesInfoList[i].examid +"'/>"
								+"</td><td>"
							    +employeeGradesInfoList[i].employeename+"</td><td>"
								+(employeeGradesInfoList[i].sex==1?"男":"女")+"</td><td>"
								+employeeGradesInfoList[i].exammethod+"</td><td>";
							if(employeeGradesInfoList[i].grade!=null){				
								showEmployeeGradesListInfo += employeeGradesInfoList[i].grade+"</td><td>"
								+employeeGradesInfoList[i].ispass+"</td><td>";
							}else{
								showEmployeeGradesListInfo += "--</td><td>--</td><td>";
							}
	            showEmployeeGradesListInfo += employeeGradesInfoList[i].examname+"</td><td>"
								+employeeGradesInfoList[i].level.toString().replace("1","厂级").replace("2","部门级").replace("3","班组级")+"</td><td>"		
							    +Format(new Date(employeeGradesInfoList[i].starttime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")
								+"到"+Format(new Date(employeeGradesInfoList[i].endtime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")+"</td><td>";
				if(employeeGradesInfoList[i].exammethod=="线下"){
					showEmployeeGradesListInfo +="<a href='javascript:void(0)' onclick='el_scoreAllContent2(this)'>详细信息</a>"					
											   +"</td></tr>";
				}else{
					showEmployeeGradesListInfo +="<a href='javascript:void(0)' onclick='el_scoreAllContent(this)'>详细信息</a>"					
						   +"</td></tr>";
				}
		}
		//清空表格
		$("#employeeGradesListInfo").empty();
		//添加信息
		$("#employeeGradesListInfo").append(showEmployeeGradesListInfo);
		
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
	$('#employeeGradeManage_paginationIDU').pagination({
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
        	findEmployeeGradeInfo();
        }
    });

}


/*********************************员工成绩的详细信息--在线答题****************************************/

function el_scoreAllContent(obj) {
	//获取隐藏域中的信息
	var idCard = $(obj).parents("tr").find(".find_idcode").val();
	var examId = $(obj).parents("tr").find(".find_examId").val();
	$.ajax({
		url:"examGrade_getOnlineExamEmployeeInAllInfo.action",
		data:{"employeeIdCard":idCard,"examId":examId},
		dataType:"json",
		type:"post",
		success:function(data){
			var onlineExamEmployeeInAllInfo = data.onlineExamEmployeeInAllInfo;
			var showOnlineEmployeeGradeInfo = "";
			showOnlineEmployeeGradeInfo += "<tr><td>"+onlineExamEmployeeInAllInfo.employeename+"</td><td>"
										+(onlineExamEmployeeInAllInfo.sex==1?"男":"女")+"</td><td>"
										+onlineExamEmployeeInAllInfo.idcode+"</td><td>"
										+onlineExamEmployeeInAllInfo.departmentname+"</td><td>"
										+onlineExamEmployeeInAllInfo.grade+"</td><td>"
										+onlineExamEmployeeInAllInfo.ispass+"</td><td>"
										+onlineExamEmployeeInAllInfo.exammethod+"</td><td>"
			if(onlineExamEmployeeInAllInfo.startanswertime!=null&&onlineExamEmployeeInAllInfo.endanswertime!=null){				
				showOnlineEmployeeGradeInfo += 
					Format(new Date(onlineExamEmployeeInAllInfo.startanswertime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")+"</td><td>"
					+Format(new Date(onlineExamEmployeeInAllInfo.endanswertime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")+"</td><td>";
			}else{
				showOnlineEmployeeGradeInfo +="--</td><td>--</td><td>";										  										 
			}
			if(onlineExamEmployeeInAllInfo.logintime!=null){
				
				showOnlineEmployeeGradeInfo	+= Format(new Date(onlineExamEmployeeInAllInfo.logintime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")+"</td><td>"
				+onlineExamEmployeeInAllInfo.ipaddress+"</td></tr>";
			}else{
				showOnlineEmployeeGradeInfo += "--</td><td>--</td></tr>"
			}				
			$("#online_employeeGradeInfo").empty();
			$("#online_employeeGradeInfo").append(showOnlineEmployeeGradeInfo);
			var showOnlineExamAllScoreInfo = "";
			if(onlineExamEmployeeInAllInfo.singlesum!=null){	
				showOnlineExamAllScoreInfo += "<tr><td>单选题</td><td>"
										+onlineExamEmployeeInAllInfo.singlesum+"/"+onlineExamEmployeeInAllInfo.singlescore+"</td></tr>";
			}
			if(onlineExamEmployeeInAllInfo.multiplesum!=null){
				showOnlineExamAllScoreInfo += "<tr><td>多选题</td><td>"
					+onlineExamEmployeeInAllInfo.multiplesum+"/"+onlineExamEmployeeInAllInfo.multiplescore+"</td></tr>";
			}
			if(onlineExamEmployeeInAllInfo.trueorfalsesum!=null){
				showOnlineExamAllScoreInfo +="<tr><td>判断题</td><td>"
					+onlineExamEmployeeInAllInfo.trueorfalsesum+"/"+onlineExamEmployeeInAllInfo.trueorfalsescore+"</td></tr>";
			}																										
			
			$("#onlineExam_scoreInfo").empty();
			$("#onlineExam_scoreInfo").append(showOnlineExamAllScoreInfo);
			$('#el_scoreAllContent').modal();
		},
		error:function(){
			alert("请求失败！")
		}
		
	})
   
}
/*********************************员工成绩的详细信息--纸质答题****************************************/
function el_scoreAllContent2(obj) {
	//获取隐藏域中的信息
	var idCard = $(obj).parents("tr").find(".find_idcode").val();
	var departmentName = $(obj).parents("tr").find(".find_departmentname").val();
	//获取当前行中的信息
	var tds = $(obj).parents("tr").children("td");
	var showOfflineEmployeeGradeInfo = "";
	showOfflineEmployeeGradeInfo += "<tr><td>"+tds.eq(1).html()+"</td><td>"
								    +tds.eq(2).html()+"</td><td>"
									+idCard+"</td><td>"
									+departmentName+"</td><td>"
									+tds.eq(4).html()+"</td><td>"
									+tds.eq(5).html()+"</td><td>"
									+tds.eq(3).html()+"</td><td>"
									+tds.eq(8).html()+"</td></tr>";
	
	$("#offline_employeeGradeInfo").empty();
	$("#offline_employeeGradeInfo").append(showOfflineEmployeeGradeInfo);
		
    $('#el_scoreAllContent2').modal();
}

/*********************************组合条件查询的员工成绩的导出****************************************/
//点击成绩导出打开模态框
function employeeGradeExportModel(){	
	$("#employGradeExportModal").modal();
}
//点击成绩导出的模态框的确认导出按钮执行的操作
function employeeGradeExportExcel(){
	$("#form_findEmployeeGradesInfo").submit();
	$("#employGradeExportModal").modal("hide");
}

