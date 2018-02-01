/**
 * Created by yorge on 2017/9/14.
 */
//****************开始结束日历  start
$(function(){
	// 联系电话(手机/电话皆可)验证 start
	//联系电话(手机/电话皆可)验证 
	jQuery.validator.addMethod("isPhone", function(value,element) { 
	  var length = value.length; 
	  var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/; 
	  var tel = /^(?:(?:0\d{2,3})-)?(?:\d{7,8})(-(?:\d{3,}))?$/;
	  return this.optional(element) || (tel.test(value) || mobile.test(value)); 
	}, "请正确填写您的联系电话"); 

	 		
	//验证联系电话 end
	
	//页面初始化的时候加载一些数据出来
	initPage();
	
    	//添加
		var start = {
			isinitVal:true,
		    format: 'YYYY-MM-DD',
		    minDate: '2014-06-16', //设定最小日期为当前日期
		    initDate:[{hh:0,mm:00,ss:00},false],
		    isinitVal:true,
		    okfun: function(obj){
		        end.minDate = obj.val; //开始日选好后，重置结束日的最小日期
		        endDates();
		    }
		};
		var end = {
				isinitVal:true,
		    format: 'YYYY-MM-DD',
		    maxDate: '2099-06-16', //最大日期
		    initDate:[{MM:"+1"},false],
		    okfun: function(obj){
		        start.maxDate = obj.val; //将结束日的初始值设定为开始日的最大日期
		    }
		};
		//这里是日期联动的关键        
		function endDates() {
		    //将结束日期的事件改成 false 即可
		    end.trigger = false;
		    $("#inpend").jeDate(end);
		}
		$('#inpstart0').jeDate(start);
		$('#inpend').jeDate(end);
		
		//修改
		var start2 = {
			    format: 'YYYY-MM-DD',
			    minDate: '2014-06-16', //设定最小日期为当前日期
			    isinitVal:true,initDate:[{MM:"+1"},false],
			    okfun: function(obj){
			        end2.minDate = obj.val; //开始日选好后，重置结束日的最小日期
			        endDates2();
			    }
			};
			var end2 = {
			    format: 'YYYY-MM-DD',
			    maxDate: '2099-06-16', //最大日期
			    isinitVal:true,
			    okfun: function(obj){
			        start2.maxDate = obj.val; //将结束日的初始值设定为开始日的最大日期
			    }
			};
			//这里是日期联动的关键        
			function endDates2() {
			    //将结束日期的事件改成 false 即可
			    end2.trigger = false;
			    $("#inpend2").jeDate(end2);
			}

			$('#inpstart2').jeDate(start2);
			$('#inpend2').jeDate(end2);

   		})
//**********开始结束日历  end

/*添加工程*/
function el_addDictinary() {
    //判断是否已经选择了树,跟据上边的NodeNums
    if (clickRes == 0) {
        alert("请选择大修或部门!")
    } else {
        /*给模态框中，添加默认部门*/
        $("#addDefaultDepart").text(getName);
        
        //单位编号$("#unitBH").val();
       // alert($("#unitID").val()+"  "+$("#unitBigHual").val())
        /*if($("#unitID").val()!=$("#unitBigHual").val()){
        	alert("可以添加")
        }else{
        	alert("不能添加")
        }*/
       
       // if( $("#unitID").val()==$("#unitBigHual").val()){//如果选中的不是部门，则不能添加
        if($("#unitBH").val()==""){	
        	alert("请先选中部门之后再添加")
        }else{
        //根据单位编号获取单位信息
        //	alert("进入ajax")//能进来
        $.ajax({
			url:"${pageContext.request.contextPath}/project_findUnitByUnitName.action",
			data:{"unitID":$("#unitID").val()},
			dataType:"json",
			type:"POST",
			async:true,
			success:function(data){
				
				
				//联系人
				$("#proConcatPerson").text(data.unit.address);
				//联系方式
				$("#proPhone").text(data.unit.phone);
				//上级单位
				$("#proUpUnit").text(data.unit.upunitid);
				//违章记分
				$("#proBreakScore").text(data.unitminismum);
				//单位地址
				$("#proUnitAddress").text(data.unit.address);
				
				
				//初始化添加模态框
				$("#addInitProName").val("");//工程名称
				
				$("#addInitPerson").val("");//负责人
				$("#addInitPhone").val("");//联系方式
				$("#addInitContent").val("");//描述
				
				//打开模态框
		        $('#el_addProject').modal();
		        //获得的树的名字： getName
			},
			error:function(){
				//alert("添加失败，请从新添加")
				alert("请在选择部门之后再添加工程")
			}
		});
        }
        
    }
}

/*删除
function delcfm() {
    $('#delcfmModel').modal();
}
function urlSubmit() {
    var url = $.trim($("#url").val());//获取会话中的隐藏属性URL
    window.location.href = url;
}
*/

/*修改工程信息*/
/*function el_modifyProject() {
    $('#el_modifyProject').modal();
}*/



/*********** lixianyuan  start **********/


/****************************************树的相关方法*****************************************************************/

$(function(){
	searchDepartmentAndOverHualTree();
})

/***********************请求树信息**********************/

function searchDepartmentAndOverHualTree() {
		$.ajax({
			type : "post",
			target : "#treeDemo",
			dataType : "json",
			url : "project_unloadTree.action",
			success : getTree_1,
			error : function() {
				alert("请求树失败！");
			}
		});
	}
			
			
/***********************生成树信息**********************/
function getTree_1(treeList2) {
		var treeList3 = treeList2.leftTree;
		var setting = {
				data : {
					simpleData : {
						enable : true,
						idKey: "id",
						pIdKey: "upid",
						rootPId : null,
					},
					key : {
						name : "name",
					}
				},
				callback : {
					beforeClick: beforeClick
				}
		};
		var zNodes = treeList3;
		//添加 树节点的 点击事件；
		var log, className = "dark";
		function onClick(event, treeId, treeNode, clickFlag) {
			clickOnTree(event, treeId, treeNode, clickFlag);
		}
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		//var treeObj = $.fn.zTree.getZTreeObj("#treeDemo");  
		//treeObj.expandAll(false);
	}


function showIconForTree(treeId, treeNode) {
    return !treeNode.isParent;
}

var clickRes = 0;
var getName;
var selectedDepartmentID;
var selectedOverHaulId;
function beforeClick(treeId, treeNode, clickFlag) {
    clickRes = 1;
    
    getName = treeNode.name;//当前选中的树的名字
    selectedDepartmentID = treeNode.id;//当前选中的树的id
    selectedOverHaulId = treeNode.upid;//当前选中的数的上级id(大修id)
    
    //为隐藏域赋值
    /*$("#unitBigHual").val(treeNode.upid);//大修的id
    $("#unitBH").val(treeNode.id);//单位编号(所属单位的编号)
*/    $("#leftTreeName").val(getName); //给树的隐藏域赋值 (当前选中的树的名字)
    
    $("#unitID").val(treeNode.id);//初始化表单用的单位id
    
    /*alert("上级id"+treeNode.upid)
    alert("选中的id"+treeNode.id)
    alert("选中的名字"+treeNode.name)*/
    if(treeNode.upid==null){
    	//大修无上级id
    	//alert("您选中的是大修")
    	/*<!-- 隐藏域  隐藏单位大修编号 -->
        <input id="unitBigHual" type="hidden"  value=""/>
        <!-- 隐藏域  隐藏单位编号(所属单位的单位编号) -->
        <input id="unitBH" type="hidden"  value=""/>"*/ 
        
    	//当前选中的id就是大修的id
    	$("#unitBigHual").val(treeNode.id);//alert("大修id"+$("#unitBigHual").val())
    	$("#unitBH").val("");//alert("部门id"+$("#unitBH").val())
        		
    	//将该大修下的每个部门的每一个工程显示出来
    	//步骤：通过大修id找到该大修下的所有部门，在找该大修下的每个部门下的所有工程
    }else{
    	//选中的是部门，只需要将当前部门下的所有工程显示出来
    	//alert("您选中的是部门")
    	//上级编号id就是大修的id
    	$("#unitBigHual").val(treeNode.upid);//alert("大修id"+$("#unitBigHual").val())
    	//当前选中的id就是部门的id
    	$("#unitBH").val(treeNode.id);//alert("部门id"+$("#unitBH").val())
    	
    }
    
    //显示旗下对应的所有工程信息
    leftTree();
    
    console.log(selectedOverHaulId);
    return (treeNode.click != false);
}





//***************页面加载的时候初始化一些数据 start***********************
function initPage(){
	$.ajax({
		url:"${pageContext.request.contextPath}/project_initPage.action",
		data:{ 
			"curPage":$("#yeHao").val(),//当前页页号
			"curTotal":$("#jiLuShu").val()//每页显示的记录数
			},
		dataType:"json",
		type:"POST",
		async:true,
		success:function(data){
			//alert("进入回掉函数")
			//alert(data.selProjectByLeftTreeList.length+"当前页显示的总条数")
			
			//数据显示之前 要先清空表格中的所有数据
			$("#tBody tr").remove();
			var tdStr = "";
			for(var i=0;i<data.projectList.length;i++){
			   /*  private String projectid;
			    private String unitbigid;//单位大修id
			    private String unitid;//所属单位编号
			    private String name;
			    private Date startdate;
			    private Date enddate;
			    private String contactperson;
			    private String phone;
			    private String status;
			    private String description; */
				//工程名称(项目名称)
				var fproName = data.projectList[i].name;
			    //项目id
			    var fproId = data.projectList[i].projectId;
				//开工时间
				var fproStartTime = data.projectList[i].startDate;
				//完工时间
				var fendTime = data.projectList[i].endDate;
				//联系人
				var fContactPerson = data.projectList[i].contactPerson;
				//联系方式
				var fPhone = data.projectList[i].phone;
				//工程状态
				var fStatus = data.projectList[i].status;
				//部门(单位id)名称
				var fUnitName = data.projectList[i].unitId;//部门id
				//部门名称
				var unitName = data.projectList[i].unitname;//部门名称
				//var fUnitName2 = null;
				//
				//通过部门(单位)id获取部门名称
				//$("#findUnitId").val(fUnitName);
				/* $.ajax({
					url:"${pageContext.request.contextPath}/project_selUnitNameByUnitId.action",
					data:{"findUnitId":$("#findUnitId").val()},
					dataType:"json",
					type:"POST",
					async:false,
					success:function(data2){
						$("#findUnitName").val(data2.unitName);//为单位名称的隐藏域赋值
					}
				}); */
				
				tdStr +="<tr>";
				//tdStr +="<td>"+fproId+"</td>";//隐藏一个项目id
				tdStr +="<td class='my'>"+fproName+"</td>";//项目名称
				tdStr +="<td class='my'>"+fproStartTime+"</td>";//开工时间
				tdStr +="<td class='my'>"+fendTime+"</td>";//完工时间
				tdStr +="<td class='my'>"+unitName+"</td>";//单位名称(这里是个单位编号)
				tdStr +="<td class='my'>"+fContactPerson+"</td>";//联系人
				tdStr +="<td class='my'>"+fPhone+"</td>";//联系方式
				tdStr +="<td class='my'>"+fStatus+"</td>";//项目状态
				//隐藏一个项目id
				//tdStr +="<input type='hidden' value="+fproId+"/>";
				tdStr +="<td>";
				//隐藏域，隐藏标记=》左侧的树的操作
				tdStr +="<input type='hidden' value='initPage'/>"
				tdStr +="<a href='javascript:void(0)' onclick='el_modifyProject(this)'><input type='hidden' value="+fproId+">修改</a>";
				tdStr +="<a class='el_delButton' onClick='delcfm(this)'><input type='hidden' value="+fproId+">删除</a>"
				tdStr +="</td>";
				
				tdStr +="</tr>"
			}
			
			$("#tBody").append(tdStr);
			
			/* //当前页页要显示的数据
			map.put("selProjectByLeftTreeList", selProjectByLeftTreeList);
			//当前页页号
			map.put("curPage", "curPage");
			//每页显示的记录数
			map.put("curTotal", "curTotal");
			//总记录数
			map.put("count", count); */
		     //当前页页号
			var curPage = data.curPage;
			//每页显示的记录数
			var curTotal = data.curTotal;
			//总记录数
			var count = data.count;
			//alert("当前页页号"+curPage+" 每页显示的记录数"+curTotal+"  总记录数"+count) 
			//参数1：总记录数   参数2：当前页页号  参数3：每页显示的记录条数
	        queryFy4(count,curPage,curTotal);
			
			
		},
		error:function(){
			alert("查询失败，请从新查询")
		}
	});  
	
	
	
}



//***************页面加载的时候初始化一些数据 end***********************
/*********** lixianyuan  end **********/
