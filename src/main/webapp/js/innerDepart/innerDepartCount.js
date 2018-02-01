

/** *************************分页******************************* */
function fenye(currentPage,totalCount,currentCount) {
	$('#paginationIDU').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [4],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(currentPage, b) {					
					departmentInFormalInfoQuery(currentPage,b);
				}
			});
}

function fenye2(currentPage,totalCount,currentCount) {
	$('#paginationIDU2').pagination(
			{
				// 组件属性
				"total" : totalCount,// 数字 当分页建立时设置记录的总数量 1
				"pageSize" : currentCount,// 数字 每一页显示的数量 10
				"pageNumber" : currentPage,// 数字 当分页建立时，显示的页数 1
				"pageList" : [4],// 数组 用户可以修改每一页的大小，
				// 功能
				"layout" : [ 'list', 'sep', 'first', 'prev', 'manual', 'next',
						'last', 'links' ],
				"onSelectPage" : function(currentPage, b) {					
					departmentInToDoInfoQuery(currentPage,b);
				}
			});
}


/*
 * 页面初始化
 */

$(function() {
	//内部部门信息查询
	departmentInFormalInfoQuery(1,4);
	//内部长委信息查询
	departmentInToDoInfoQuery(1,4);
	
	//内部部门统计
	formalCountInfo();
	//长委单位统计
	toDoCountInfo();
});

function departmentInFormalInfoQuery(currentPage,currentCount) {

	$.ajax({
		url : basePathUrl+'/departmentCount_findDepartmentInFormalCountInfo.action',
		data:{"currentPage":currentPage,"currentCount":currentCount},
		type : 'POST',
		dataType : 'json',
		async : true,
		success : showDepartmentInFormalInfo,
	});

}

function showDepartmentInFormalInfo(data) {

		var result = data;
		//当前页显示条数
		var currentCount = result.pageBean.currentCount;
		//从数据库中查询出来的数据集合
		var departmentInFormalInfo = result.pageBean.productList;
		var showDepartmentInFormalInfo = "";
		if(departmentInFormalInfo != null){
			for(var i=0;i<departmentInFormalInfo.length;i++){
				showDepartmentInFormalInfo +="<tr><td>"
										    +departmentInFormalInfo[i].departmentName+"</td><td>"									
											+departmentInFormalInfo[i].employeeName+"</td><td>"
											+departmentInFormalInfo[i].phone+"</td><td>"																		
											+departmentInFormalInfo[i].employeeNum+"</td><td>"
											+departmentInFormalInfo[i].breakNum+"</td><td>"																		
											+departmentInFormalInfo[i].jiaquan
											+"</td></tr>";	
					
			}
			//清空表格
			$("#departmentInFormalInfolist").empty();
			//添加信息
			$("#departmentInFormalInfolist").append(showDepartmentInFormalInfo);
			
			//当前页
			var currentPage = result.pageBean.currentPage;
			//总条数
			var totalCount = result.pageBean.totalCount;
			
			//调用分页函数
			fenye(currentPage,totalCount,currentCount);
		}

}


/***************************************/
function departmentInToDoInfoQuery(currentPage,currentCount) {

	$.ajax({
		url : basePathUrl+'/departmentCount_findDepartmentInToDoCountInfo.action',
		data:{"currentPage":currentPage,"currentCount":currentCount},
		type : 'POST',
		dataType : 'json',
		async : true,
		success : showDepartmentInToDoInfo,
	});
}

function showDepartmentInToDoInfo(data){
	var result = data;
	//当前页显示条数
	var currentCount = result.pageBean.currentCount;
	//从数据库中查询出来的数据集合
	var departmentInToDoInfo = result.pageBean.productList;
	var showDepartmentInToDoInfo = "";
	if(departmentInToDoInfo != null){
		for(var i=0;i<departmentInToDoInfo.length;i++){
			showDepartmentInToDoInfo +="<tr><td>"
									    +departmentInToDoInfo[i].departmentName+"</td><td>"									
										+departmentInToDoInfo[i].employeeName+"</td><td>"
										+departmentInToDoInfo[i].phone+"</td><td>"																		
										+departmentInToDoInfo[i].employeeNum+"</td><td>"
										+departmentInToDoInfo[i].breakNum+"</td><td>"																		
										+departmentInToDoInfo[i].jiaquan
										+"</td></tr>";	
				
		}
		//清空表格
		$("#departmentInToDoInfolist").empty();
		//添加信息
		$("#departmentInToDoInfolist").append(showDepartmentInToDoInfo);
		
		//当前页
		var currentPage = result.pageBean.currentPage;
		//总条数
		var totalCount = result.pageBean.totalCount;
		
		//调用分页函数
		fenye2(currentPage,totalCount,currentCount);
	}

}


/***************************************/
function formalCountInfo() {
	$.ajax({
		url : basePathUrl+'/departmentCount_findFormalDepartmentAndEmpNum.action',		
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(data){
			$("#formalDepCountInfo").text(data.map.departmentNum);
			$("#formalEmpCountInfo").text(data.map.employeeNum);
		},
	});
}

function toDoCountInfo() {
	$.ajax({
		url : basePathUrl+'/departmentCount_findToDoDepartmentAndEmpNum.action',		
		type : 'POST',
		dataType : 'json',
		async : true,
		success : function(data){
			$("#toDoDepCountInfo").text(data.map.departmentNum);
			$("#toDoEmpCountInfo").text(data.map.employeeNum);
		},
	});
}




