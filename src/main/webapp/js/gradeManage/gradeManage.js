/***************************页面加载函数***************************/

$(function(){
	//调用按照考试查看成绩的方法
	//findExamGradeInfo();
	//调用按照部门查看成绩
	findUnitExamGradeInfo();
});

/****************************组合条件查询***************************/

//点击页面的查询按钮执行的操作
function searchExamGradeInfo(){
	//执行查询操作前先将隐藏域中的值清空当前页清空或改为第一页
	$("#currentPage").val("");
	var type = $("#el_findExamGradeType").val();
	if(type==0){		
		//调用按照考试查看成绩的方法
		findExamGradeInfo();
	}else{
		//调用按照部门查看成绩
		findUnitExamGradeInfo();
	}
}

function findExamGradeInfo(){
	$("#operation").show();
	$("#departmentName").hide();
	$(".dept").hide();
	$(".exam").show();
	$.ajax({
		url : 'examGrade_findExamGradesInfoWithCondition.action',
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
			var parameterStr =  '{"name":"'+examGradesInfoList[i].examname+'","examId":"'+examGradesInfoList[i].examid+'"}';
			if(examGradesInfoList[i].exammethod=="线下"){
				showExamGradesListInfo +="<tr style='background:#EEEEEE'>";
			}else{
				showExamGradesListInfo +="<tr>";
			}
			showExamGradesListInfo += "<td><input type='radio' name='el_exam' onclick='showGradeImportButton(this)' class='el_checks' value='"+examGradesInfoList[i].examid+"'/></td><td>"
								+(index + (result.pageBean.currentPage - 1) * currentCount)+"</td><td>"
							    +examGradesInfoList[i].examname+"</td><td>"			
				                +examGradesInfoList[i].level.toString().replace("1","厂级").replace("2","部门级").replace("3","班组级") +"</td><td>"												                
				                +examGradesInfoList[i].sumperson+"</td><td>"
								+examGradesInfoList[i].countpassperson+"</td><td>"
								+examGradesInfoList[i].status+"</td><td>"
								+examGradesInfoList[i].paperscore+"</td><td>"
								+Format(new Date(examGradesInfoList[i].starttime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")
								+"到"+Format(new Date(examGradesInfoList[i].endtime.replace(/T/g," ").replace(/-/g,"/")),"yyyy-MM-dd HH:mm")+"</td><td>"									
								+" <a href='javascript:void(0)' onclick='el_scoreExport(this)'>导出成绩单</a>"
								//+" <a href='gradeEmpInfo.jsp?name="+examGradesInfoList[i].examid+"'>详细信息</a>"
								+"<a href=javascript:doPost('gradeEmpInfo.jsp','"+parameterStr+"')>详细信息</a>"
								+"</td></tr>";			
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
        	var type = $("#el_findExamGradeType").val();
        	if(type==0){		
        		//调用按照考试查看成绩的方法
        		findExamGradeInfo();
        	}else{
        		//调用按照部门查看成绩
        		findUnitExamGradeInfo();
        	}        	
        }
    });

}



/*********************************导出成绩单****************************************/
function el_scoreExport(obj) {	
	var examName = $(obj).parents("tr").children("td").eq(2).html();
	var examId = $(obj).parents("tr").children("td").eq(0).find(".el_checks").val();
	//设置a标签的属性
	$("#exportExcelEmGrade").prop("href","exportGrade_getEmployeeGradesToExport.action?examId="+examId);
	//设置考试的名称
	$("#export_ExamName").text(examName);
    $('#el_scoreExport').modal();
}
//关闭导出成绩的模态框
function exportModelcolse(){
	$("#el_scoreExport").modal("hide");
}



/*********************************成绩分析****************************************/
//点击页面的成绩分析执行的操作
function gradeAnaly() {
    //累计选择的个数，若等于1，才执行，否则提示
    var nums = 0;
    $.each($(".el_checks"), function (i, el_check) {
        if ($(this).prop("checked")) {
        	//将隐藏域中的考试ID传入成绩分析的模态框中
        	$("#gradeAnalyse_examId").val($(this).val());
        	var unitId = $(this).parents("tr").find(".query_unitid").val();
        	$("#gradeAnalyse_unitId").val(unitId);
            nums++;
        }
    });
    if (nums == 1) {
    	//先将图表的div清空
    	$("#main").empty();
    	
        $('#el_scoreStatistisModel').modal();
    } else {
        alert("请先选择一个考试！")
    }
}

//点击成绩分析模态框中的确定按钮执行的操作
function examGradeAnalyse(){
	
	$.ajax({
		url : 'examGrade_findExamGradeAnalyseInfoByCondition.action',
		data : $("#form_gradeAnalyse").serialize(),
		type : 'POST',
		dataType : 'json',
		async:true,
		success : showGradeAnalyseChart,
		error: function(){
			alert("请求失败！");
		}
		});
}

//ajax的回调函数显示分析图表
function showGradeAnalyseChart(data){
	//获取用户输入的优良差的分值
	 var score = new Array(6);
	 var i = 0;
     $(".el_setGradeInput").each(function () {
         score[i] = $(this).val();
         i++;
     })
	
	var ExamGradeAnalyseInfo = data.ExamGradeAnalyseInfo;
	//console.log(ExamGradeAnalyseInfo)
	var countFailedPerson = ExamGradeAnalyseInfo.countFailedPerson;
	var countExcellentPerson = ExamGradeAnalyseInfo.countExcellentPerson
	var countGoodPerson = 	ExamGradeAnalyseInfo.countGoodPerson;
	//输入信息校验
	if(((score[1]-score[0])>0&&(score[0]-score[3])>0&&(score[3]-score[2])>0)){
		//调用显示统计信息图表的函数,传入从后台查询的优良差的人数
		Charts(countFailedPerson,countExcellentPerson,countGoodPerson,score);
	}else{
		alert("设置的配置参数有误，请重新输入！");
	}
}
//对成绩分析的图表进行初始化操作
function Charts(countFailedPerson,countExcellentPerson,countGoodPerson,score) {

    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: [score[2] + '-' + score[3], score[3] + '-' + score[0], score[0] + '-' + score[1]]
        },
        series: [
            {
                name: '访问来源',
                type: 'pie',
                selectedMode: 'single',
                radius: [0, '30%'],

                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [
                    {value: countFailedPerson, name: '差', selected: true},
                    {value: countGoodPerson, name: '良'},
                    {value: countExcellentPerson, name: '优'}
                ]
            },
            {
                name: '访问来源',
                type: 'pie',
                radius: ['40%', '55%'],
                data: [
                    {value: countFailedPerson, name: score[2] + '-' + score[3]},
                    {value: countGoodPerson, name: score[3] + '-' + score[0]},
                    {value: countExcellentPerson, name: score[0] + '-' + score[1]}
                ]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
}


/*********************************成绩导入****************************************/
//判断是否显示成绩导入按钮
function showGradeImportButton(obj){
	
	//先将成绩导入按钮隐藏
	$("#gradeImportButton").hide();
	var examId = $(obj).val();	
	var type = $("#el_findExamGradeType").val();
	if(type==0){
		var countPerson = $(obj).parents("tr").children("td").eq(5).text();
		if(countPerson==0){
			$.ajax({
				url:"examGrade_getEmployeeOutInfoByExamId.action",
				data:{"examId":examId},
				dataType:"json",
				type:"post",
				success:function(data){
					var employeeOutInfoList = data.employeeOutInfoList;
					if(employeeOutInfoList.length>0){
						$("#gradeImportButton").show();
					}
				}
			});
		}		
	}
	
}

function gradeInput() {
    //累计选择的个数，若等于1，才执行，否则提示
    var nums = 0;
    var examId = "";
    var passBoolean = false; 
    $.each($(".el_checks"), function (i, el_check) {
        if ($(this).prop("checked")) {
        	//从当前行中获取考试名称设置到成绩导入的模态框中
        	var examName = $(this).parents("tr").children("td").eq(2).html();
        	var passPerson = $(this).parents("tr").children("td").eq(5).html();
        	if(passPerson==0){
        		passBoolean = true;
        	}
        	$("#into_examName").text(examName);
        	//将考试ID设置到成绩导入的模态框中
        	$("#input_examId").val($(this).val());
        	examId = $(this).val();
            nums++;
        }
    });
    if (nums == 1&&passBoolean) {
    	$.ajax({
    		url:"examGrade_getEmployeeOutInfoByExamId.action",
    		data:{"examId":examId},
    		dataType:"json",
    		type:"post",
    		success:function(data){
    			var employeeOutInfoList = data.employeeOutInfoList;
    			if(employeeOutInfoList.length>0){
    				var employeeOutInfoStr = "";
    				for(var i=0;i<employeeOutInfoList.length;i++){
    					employeeOutInfoStr += "<tr><td>"+employeeOutInfoList[i].employeeid+"</td><td>"
    											+employeeOutInfoList[i].employeename+"</td></tr>";
    				}
    				$("#employeeOutInfoList").empty();
    				$("#excelGradeInfoList").empty();
    				$("#employeeOutInfoList").append(employeeOutInfoStr);
    				$("#count_lift").text(employeeOutInfoList.length);
    				//将统计的信息设置为空
    				$("#count_right").text("");
    				//将选择的文件名删除
    				$("#exampleInputEmail12").val("");
    				//将表格中的信息清空
    				$("#excelGradeInfoList").empty();
    				//将导入按钮删除
    				$("#confirmImportButton").remove();
    				//将上传按钮设置为可以操作
    				$("#importButton").prop("disabled",false);
    				$('#myModal').modal();
    			}else{
    				alert("该考试为在线考试，不能进行成绩导入,请您重新选择考试！");
    			}
    		}
    	});
    } else if(nums == 1&&!passBoolean){
        alert("该考试已经成功导入了成绩，不能再次导入！")
    }else{
    	alert("请先选择一个考试！");
    }
}

//点击成绩导入的模态框的导入执行的操作
function inputEmployeeGrades(excelFileInfo){
	
	var excelFileInfoList = "";
	excelFileInfoList = JSON.stringify(excelFileInfo);
	var input_examId = $("#input_examId").val();
	$.ajax({
		url:"examGrade_importEmployeeGrade.action",
		type:"post",
		dataType:"json",
		data:{"employeeOutGradeInfoStr":excelFileInfoList,"examId":input_examId},
		traditional:true,
		success:function(data){
			alert(data.result);
			//关闭模态框
			$('#myModal').modal("hide");	
			//调用查询方法
			findExamGradeInfo();
		}	
	});
	 
}
//点击成绩导入模态框的上传文件执行的操作
function importExcelFile(){
	if($("#exampleInputEmail12").val().length>0){
		//将上传按钮设置为disable
		$("#importButton").prop("disabled",true);
		var formData = new FormData($("#form_inputEmployeeGrade")[0]); 
		$.ajax({
			url:"examGrade_getExcelEmployeeGradeInfo.action",
			type:"post",
			dataType:"json",
			/**
	         *必须false才会自动加上正确的Content-Type
	         */
	         contentType: false,
	         /**
	         * 必须false才会避开jQuery对 formdata 的默认处理
	         * XMLHttpRequest会对 formdata 进行正确的处理
	         */
	        processData: false,
			data:formData,
			success:function(data){
				var excelFileInfo = data.ExcelGradeInfo;
				if(excelFileInfo.length>0){
					var excelFileInfoStr = "";
					for(var i=0;i<excelFileInfo.length;i++){
						excelFileInfoStr += "<tr><td>"+excelFileInfo[i].employeeid+"</td><td>"
						+excelFileInfo[i].employeename+"</td><td>"
						+excelFileInfo[i].grade+"</td></tr>";
					}
					$("#excelGradeInfoList").empty();
					$("#excelGradeInfoList").append(excelFileInfoStr);
					//将导入按钮删除
    				$("#confirmImportButton").remove();
					//显示导入成绩的按钮，传递读取出来的员工信息
					var buttonInfo = "<button type='button'  class='btn btn-primary' id='confirmImportButton' onclick='inputEmployeeGrades("+JSON.stringify(excelFileInfo)+")' >导入</button>"
					$("#importEmployeeOutButton").append(buttonInfo);
					$("#count_right").text(excelFileInfo.length);
				}
			}
		});
	}else{
		alert("请选择要上传的文件！")
	}
	 
}

/*********************************查看成绩的两种方式****************************************/
$(function(){
	//当查看成绩类型发生变化后执行的操作
	$("#el_findExamGradeType").change(function(){
		//执行查询操作前先将隐藏域中的值清空当前页清空或改为第一页
		$("#currentPage").val("");
		//先将成绩导入按钮隐藏
		$("#gradeImportButton").hide();
		var type = $("#el_findExamGradeType").val();
		if(type==0){		
			//调用按照考试查看成绩的方法
			findExamGradeInfo();
		}else{
			//调用按照部门查看成绩
			findUnitExamGradeInfo();
		}
	})
});

function findUnitExamGradeInfo(){
	$("#departmentName").show();
	$("#operation").hide();	
	$(".dept").show();
	$(".exam").hide();
	$.ajax({
		url : basePathUrl+'/examGrade_getUnitExamGradesByCondition.action',
		data : $("#form_findExamGradesInfo").serialize(),
		type : 'POST',
		dataType : 'json',
		async:true,
		success : showUnitExamGradesInfo,
		error: function(){
			alert("请求失败！");
		}
		});
}

function showUnitExamGradesInfo(data){
	var result = data;
	//console.log(result);
	//当前页显示条数
	var currentCount = result.pageBean.currentCount;
	//从数据库中查询出来的数据集合
	var examGradesInfoList = result.pageBean.productList;
	var showExamGradesListInfo = "";
	if(examGradesInfoList != null){		
		for(var i=0,j='FFFFFF';i<examGradesInfoList.length;i++){
			var index = i+1;
			if(i==0){
				showExamGradesListInfo +="<tr style='background:#"+j+"'>";
			}else if(i>0&&examGradesInfoList[i].examid==examGradesInfoList[i-1].examid){
				showExamGradesListInfo +="<tr style='background:#"+j+"'>";
			}else{
				j = (j=='FFFFFF'?'EEEEEE':'FFFFFF');	
				showExamGradesListInfo +="<tr style='background:#"+j+"'>";
			}
			showExamGradesListInfo += "<td><input type='radio' name='el_exam' onclick='showGradeImportButton(this)' class='el_checks' value='"+examGradesInfoList[i].examid+"'/></td><td>"
								+(index + (result.pageBean.currentPage - 1) * currentCount)
								+"<input type='hidden' class='query_examid' value='"+examGradesInfoList[i].examid+"'/>"
								+"<input type='hidden' class='query_unitid' value='"+examGradesInfoList[i].unitid+"'/>"
								+"</td><td>"
							    +examGradesInfoList[i].examname+"</td><td>"			
				               	+examGradesInfoList[i].examlevel.toString().replace("1","厂级").replace("2","部门级").replace("3","班组级") +"</td><td>"								
								+examGradesInfoList[i].unitname+"</td><td onclick='findEmployeeGradeInfo(this)' title='点击查看具体的考试人员信息' class='el_delButton' style='color:darkblue;'>"
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

/*************************a标签post提交方法******************************/
//to:提交动作（action）,p:向后台传递的数据
function doPost(to,p) { 
	//将json字符串转换为json对象
	p = eval("(" + p + ")");
	var myForm = document.createElement("form");
	myForm.method = "post";
	myForm.action = to;
	myForm.target="_self"
	//遍历json对象
	for ( var i in p) {
		var myInput = document.createElement("input");
		myInput.setAttribute("name", i); // 为input对象设置name  
		myInput.setAttribute("value", p[i]); // 为input对象设置value  
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
	document.body.removeChild(myForm); // 提交后移除创建的form  
}