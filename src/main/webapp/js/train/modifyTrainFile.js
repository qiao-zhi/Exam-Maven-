/**
 * Created by yorge on 2017/9/14.
 */
/*
* examManage
* */
/*<!--索引中选择部门-->*/


//====================lixianyuan start===========================
//初始化资料类型
function initTrainType(){
	$.ajax({
		url:contextPath+"/train_initTrainType.action",
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
			
			var opStr = "";
			for(var i=0;i<data.dictionaryList.length;i++){
				var opName =data.dictionaryList[i].dictionaryname;//字典名称
			
				//将数据按照DOM规则添加到select标签中作为其子标签
				opStr += "<option>"+opName+"</option>";
			}
			$("#trainType").append(opStr);
		}
	});
}


//初始化知识点
function initKnowledgeType(){
	$.ajax({
		url:contextPath+"/train_initKnowledgeType.action",
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
		
			var opStr = "";
			for(var i=0;i<data.dictionaryList.length;i++){
				var opName =data.dictionaryList[i].dictionaryName;//字典名称
				//将数据按照DOM规则添加到select标签中作为其子标签
				opStr += "<option>"+opName+"</option>";
			}
			$("#trainKnowledge").append(opStr);
		}
	});
}

//初始化表单中的所有数据  也就是将未修改之前的数据在表单中显示
function initFormData(){
	//培训资料名称
	$("#trainDocName").val($("#docName").val());
	//所属级别
	var gradeStr = $("#grade").val();//从数据库中获取的所属级别的值
	
	$(".el_radioBox").each(function(){
		var checkValue = $(this).children("input").attr("value");
		
		if(gradeStr == checkValue){
			$(this).children("input").prop("checked","true");
		}
	});
	
	 
	//class=el_radioBox
	
	//所属部门  一个树
	$("#trainDeptName").val($("#deptName").val());//为隐藏域赋值
	$("#el_chooseDepart").text('');
	$("#el_chooseDepart").append($("#deptName").val());
	
	
	//资料类型 select下拉框
	$("#trainType").text($("#docType").val());
	
	
	
	//知识点 select下拉框
	$("#trainKnowledge").text($("#knowledge").val());
	
	
	//资料说明
	$("#trainDocDetail").val($("#docDesc").val());
	//上传人
	$("#uploadPerson").val($("#upPersonName").val());
	//上传时间
	$("#test4").val($("#uploadTime").val())
}


//修改按钮的方法--实现
function btnSave(){
	//让转圈圈的显示
	//$("#mode_tips_v2").show();
	
	var isNotNull = $("#trainForm").validate({
		ignore : [],
		rules : {
			"traincontent.documentname" : "required",//资料名称
			"traincontent.traintype" : {//资料类型
				required : true
			},//验证下拉框的
			"traincontent.knowledgetype" : {//知识点
				required : true
			},//验证下拉框的
			"traincontent.employeename":"required",//上传人
			"traincontent.departmentname" : "required",  //所属部门 这是一个树     验证文本框的，前面是name 属性
		},
		messages : {
			"traincontent.documentname" : {//资料名称
				required : "不能为空"
			},//下边与上边对应
			"traincontent.traintype" : {//资料类型
				required : "必须选择"
			},
			"traincontent.knowledgetype" : {//知识点
				required : "必须选择"
			},
			"traincontent.employeename" : {//上传人
				required : "不能为空"
			},
			"traincontent.departmentname" : {//所属部门  这是一个部门树
				required : "必须选择"
			},
		}

	});
	//以上是和表单校验有关的操作
	//以下是和修改有关的操作
	if (isNotNull.form()) {
		
		//点击修改的时候隐藏修改和取消按钮
		$("#modifyCancleAndSaveBtn").hide();
		//让转圈圈的显示
		$("#mode_tips_v2").show();
		//显示文件上传的条条
		var formData = new FormData($("#trainForm")[0]);
		 $.ajax({
			type:"POST",
			url:contextPath+"/train_modifyTrain.action",
			data:formData,
			//必填  
	        processData: false,  
	       //必填  
	        contentType: false,
			dataType:"json",
			success:function(data){
				//进入回掉函数
				
				alert(data.result);
				
				//让上传转圈圈的隐藏
    			$("#mode_tips_v2").hide();//隐藏
				
    			//显示修改和取消按钮
    			$("#modifyCancleAndSaveBtn").show();
				
				//修改成功之后跳转到培训资料管理界面
				var url = baseUrlPath+"/view/train/trainManage.jsp";
				window.location.href =url;
			},
			error:function(){
				alert("修改培训资料失败，请重试");
				//修改失败之后跳转到培训资料管理界面，让用户自己去重新修改
				var url = baseUrlPath+"/view/train/trainManage.jsp";
				window.location.href =url;
			}
		}); //ajax的括号
	}//if的括号
}


//====================lixianyuan end===========================






/******S   QLQ**********************/
var zTree;
var setting = {
	 check:{
		    enable:true,
		    chkStyle :"radio",
		    radioType: "all"
		},
	data : {
		key : {
			name:"typeName"
		},
		simpleData : {
			enable : true,
			idKey: "typeId",
			pIdKey: "upId",
			rootPId: 1
		}
	},
	callback : {
		onCheck : onCheck
		//点击节点触发的事件
	}
};
function geneTypeTree(){
	$.getJSON(contextPath+"/trainacontentType_getTraincontenttypeTree.action",function(response){
		var zNodes = response.traincontenttypeTree;
		zTree = $.fn.zTree.init($("#tree"),setting,zNodes);
	});
}

/************S   点击事件*********/
function onCheck(e, treeId,treeNode) {
	$("#knowledgeType").val(treeNode.typeName);
	$("[name='traincontent.knowledgetype']").val(treeNode.typeId);
}
/************E   点击事件*********/


function openModal(){
	$("#el_empTrainDoc").modal("show");
}

/******E   QLQ**********************/