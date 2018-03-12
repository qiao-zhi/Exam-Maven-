package cn.xm.exam.action.employee.out;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.service.employee.out.EmployeeOutService;
import cn.xm.exam.utils.BSASE64;
import cn.xm.exam.utils.DateHandler;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ResourcesUtil;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.employee.out.EmployeeOutBaseInfo;

/**   
*    
* 项目名称：Exam   
* 类名称：EmployeeOutPersonAction   
* 类描述：外来单位员工管理的action
* 创建人：Leilong  
* 创建时间：2017年11月12日 下午9:34:44   
* @version    
*    
*/
@Controller
@Scope("prototype")
public class EmployeeOutPersonAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Resource
	private EmployeeOutService employeeOutService;
	
	private Map<String,Object> result;
	//当前页
	private String currentPage;
	//当前页显示的条数
	private String currentCount;
	//姓名
	private String employeeOutName;
	//性别
	private String employeeOutSex;
	//身份证号
	private String employeeOutIdCard;
	//违章积分分数段
	private String minusNum;
	//黑名单信息
	private String blackListInfo;
	//培训情况
	private String trainStatus;
	//工种
	private String employeeType;
	//外来单位员工ID
	private String employeeOutId;
	//参加大修员工ID
	private String bigEmployeeOutId;
	//单位ID
	private String unitId;
	//大修ID
	private String bigId;
	//外来员工基本信息集合
	private List<EmployeeOut> employeeOutList;
	//参加大修的员工信息
	private List<Haulemployeeout> haulEmployeeOutList;
	//图片
	private String photoStr;
	//标记培训类型
	private String markTrainType;
	//家庭住址
	private String address;
	//大修状态标记 1表示查看所有检修
	private String bigStatusMark;
	//年龄段左侧
	private String ageLeft;
	//年龄端右侧
	private String ageRight;
	
	
	//初始化大修部门树
	public String getDepartmentAndOverHaulTree(){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generationCondition(condition);
		result = new HashMap<String,Object>();
		List<Map<String, Object>> departmentAndOverHaulTree = null ;				
		try {
			departmentAndOverHaulTree = employeeOutService.getDepartmentAndOverHaulTree(condition);
		} catch (Exception e) {
			e.printStackTrace();
		}
		result.put("departmentAndOverHaulTree", departmentAndOverHaulTree);
		return SUCCESS;
	}
	
	//组合条件查询分页显示外来单位的员工信息 (原先的还没有 时间段 与 违章类型这两个条件)
	public String getEmployeeOutBaseInfoList(){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generationCondition(condition);
		
		result = new HashMap<String,Object>();
		try {
			PageBean<Map<String,Object>> pageBean = employeeOutService.findEmployeeOutWithCondition(Integer.valueOf(currentPage), Integer.valueOf(currentCount), condition);
			result.put("pageBean", pageBean);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//根据身份证号查询员工的培训信息
	public String getExamsInfoByEmployeeOutIdCard(){
		result = new HashMap<String,Object>();
		try {
			List<Map<String, Object>> examInfos = employeeOutService.getExamsInfoByEmployeeOutIdCard(employeeOutIdCard);
			result.put("examInfos", examInfos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据身份证号查询员工的培训信息,分页显示
	public String getExamsInfoByEmployeeOutIdCardLimit(){
		result = new HashMap<String,Object>();
		try {
			PageBean<Map<String, Object>> pageBean = employeeOutService.getExamsInfoByEmployeeOutIdCard(Integer.valueOf(currentPage), Integer.valueOf(currentCount),employeeOutIdCard);
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据条件查询外来单位员工的违章信息
	public String getBreakRulesByCondition(){
		result = new HashMap<String,Object>();
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generationCondition(condition);
		try {
			List<Breakrules> breakRules = employeeOutService.getBreakRulesInfoByCondition(condition);
			result.put("breakRules", breakRules);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据条件查询员工的基本信息用于生成工作证
	public String getEmpInfoforCertificate(){
		result = new HashMap<String,Object>();
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generationCondition(condition);
		try {
			List<EmployeeOutBaseInfo> employeeOutInfoList = employeeOutService.getEmpInfoForCertificateWithCondition(condition);
			result.put("employeeOutInfoList",employeeOutInfoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//判断录入员工信息的状态,是否来过，是否进入黑名单
	public String checkAddEmployeeOutStatuss(){
	
		result = new HashMap<String,Object>();
		try {
			int status = employeeOutService.findEmployeeOutStatus(employeeOutIdCard,bigId,unitId);
			result.put("status", status);			
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//批量导入外来单位员工的基本信息
	public String addEmployeeOutBatch(){
		result = new HashMap<String,Object>();
		try {
			//员工信息有可能为空
			if(employeeOutList !=null&&employeeOutList.size()>0){
				employeeOutList.removeAll(Collections.singleton(null));
				//设置员工的id和头像路径
				for (EmployeeOut employeeOut : employeeOutList) {
					employeeOut.setEmployeeid(UUIDUtil.getUUID2());
					//employeeOut.setHeadaddress("/image/employeePhoto/"+employeeOut.getIdcode()+".jpg");
					employeeOut.setHeadaddress("/employeeOutPhotos/"+employeeOut.getIdcode()+".jpg");
				}
			}
			//去除集合中的所有null元素			
			haulEmployeeOutList.removeAll(Collections.singleton(null));
			
			//设置员工大修ID和培训状态
			for (Haulemployeeout haulEmployeeOut : haulEmployeeOutList) {
				haulEmployeeOut.setBigemployeeoutid(UUIDUtil.getUUID2());
				haulEmployeeOut.setTrainstatus("0");
			}						
			
			//批量导入外来单位员工的基本信息
			int count = employeeOutService.addEmployeeOutBatch(employeeOutList,haulEmployeeOutList);
			if(count==haulEmployeeOutList.size()){
				result.put("result", "添加成功！");
			}else{
				result.put("result", "添加失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//图片信息的处理
	public String saveEmployeePhoto(){
		// 获取工程下的路径
		//String path = ServletActionContext.getServletContext().getRealPath("image/employeePhoto");
		//String path = "D:/images";
		String path = ResourcesUtil.getValue("path", "employeeOutPhoto");
		String filepath = path + "\\" + employeeOutIdCard + ".jpg";
		BSASE64.generateImage(photoStr, filepath);
		return NONE;
	}
	
	//修改参加大修员工的工种信息
	public String updateHaulEmployeeOutInfo(){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generationCondition(condition);
		result = new HashMap<String,Object>();
		try {
			boolean update = employeeOutService.updateHaulEmployeeOutInfoByCondition(condition);
			if(update){
				result.put("result", "修改成功！");
			}else{
				result.put("result", "修改失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//删除大修员工的信息
	public String deleteHaulEmployeeOutInfo(){
		Map<String,Object> condition = new HashMap<String,Object>();
		condition = generationCondition(condition);
		result = new HashMap<String,Object>();
		try {
			boolean delete = employeeOutService.deleteHaulEmployeeOutInfoByCondition(condition);
			if(delete){
				result.put("result", "删除成功！");
			}else{
				result.put("result", "删除失败！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//组装查询条件
	private Map<String,Object> generationCondition(Map<String,Object> condition){
		
		//对当前页信息进行设置
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		//对当前页显示的信息进行设置
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if(ValidateCheck.isNotNull(employeeOutName)){
			condition.put("employeeOutName", employeeOutName);
		}
		
		if(ValidateCheck.isNotNull(employeeOutSex)){
			condition.put("employeeOutSex", employeeOutSex);
		}
		
		if(ValidateCheck.isNotNull(employeeOutIdCard)){
			condition.put("employeeOutIdCard", employeeOutIdCard);
		}
		
		if(ValidateCheck.isNotNull(minusNum)){
			String[] points = minusNum.split(",");
			if(points.length>1){				
				condition.put("minusNumLeft", points[0]);
				condition.put("minusNumRight", points[1]);
			}else{
				condition.put("minusNumLeft", points[0]);
			}
		}
		
		if(ValidateCheck.isNotNull(blackListInfo)){
			condition.put("blackListInfo", blackListInfo);
		}
		
		
		if(ValidateCheck.isNotNull(trainStatus)){
			condition.put("trainStatus", trainStatus);
		}
		
		if(ValidateCheck.isNotNull(employeeType)){
			condition.put("employeeType", employeeType);
		}
		
		if(ValidateCheck.isNotNull(employeeOutId)){
			condition.put("employeeOutId", employeeOutId);
		}
		
		if(ValidateCheck.isNotNull(bigEmployeeOutId)){
			condition.put("bigEmployeeOutId", bigEmployeeOutId);
		}
		
		if(ValidateCheck.isNotNull(unitId)){
			condition.put("unitId", unitId);
		}
		
		if(ValidateCheck.isNotNull(bigId)){
			condition.put("bigId", bigId);
		}
		
		if(ValidateCheck.isNotNull(address)){
			condition.put("address", address);
		}
		
		//年龄段左侧数据
		if(ValidateCheck.isNotNull(ageLeft)){
			condition.put("age_left", ageLeft);
		}
		//年龄段右侧数据
		if(ValidateCheck.isNotNull(ageRight)){
			condition.put("age_right", ageRight);
		}
		
		if(ValidateCheck.isNotNull(bigStatusMark)){
			//1表示查询已结束的检修，0代表查询进行中的检修
			if(bigStatusMark.equals("1")){				
				condition.put("bigStatus", "已结束");
			}else{
				condition.put("bigStatus", "进行中");
			}
		}
		
		//培训类型标记
		if(ValidateCheck.isNotNull(markTrainType)){
			//判断标记字段的值，0表示内部正式员工和长委，1表示外来单位
			if(markTrainType.equals("0")){
				condition.put("markTrainType_In", markTrainType);				
			}else{
				condition.put("markTrainType_Out", markTrainType);
			}
			//正式新员工培训大修ID
			condition.put("regular_train", DefaultValue.REGULAR_EMPLOYEE_TRAIN);
			//长委新员工培训大修ID
			condition.put("longterm_train", DefaultValue.LONGTERM_EMPLOYEE_TRAIN);
		}
		
		//组装查询条件,封装违章信息显示类型的条件(即当前年还是历史的违章)lixianyuan
		Map<String, Object> condition2 = generateConditionForbreakInfoType(condition);
		
		return condition2;
		
	}
	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}
	
	public String getEmployeeOutName() {
		return employeeOutName;
	}

	public void setEmployeeOutName(String employeeOutName) {
		this.employeeOutName = employeeOutName;
	}

	public String getEmployeeOutSex() {
		return employeeOutSex;
	}

	public void setEmployeeOutSex(String employeeOutSex) {
		this.employeeOutSex = employeeOutSex;
	}

	public String getEmployeeOutIdCard() {
		return employeeOutIdCard;
	}

	public void setEmployeeOutIdCard(String employeeOutIdCard) {
		this.employeeOutIdCard = employeeOutIdCard;
	}

	public String getMinusNum() {
		return minusNum;
	}

	public void setMinusNum(String minusNum) {
		this.minusNum = minusNum;
	}

	public String getBlackListInfo() {
		return blackListInfo;
	}

	public void setBlackListInfo(String blackListInfo) {
		this.blackListInfo = blackListInfo;
	}

	public String getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}

	public String getEmployeeOutId() {
		return employeeOutId;
	}

	public void setEmployeeOutId(String employeeOutId) {
		this.employeeOutId = employeeOutId;
	}

	public String getBigEmployeeOutId() {
		return bigEmployeeOutId;
	}

	public void setBigEmployeeOutId(String bigEmployeeOutId) {
		this.bigEmployeeOutId = bigEmployeeOutId;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

	public List<EmployeeOut> getEmployeeOutList() {
		return employeeOutList;
	}

	public void setEmployeeOutList(List<EmployeeOut> employeeOutList) {
		this.employeeOutList = employeeOutList;
	}

	public List<Haulemployeeout> getHaulEmployeeOutList() {
		return haulEmployeeOutList;
	}

	public void setHaulEmployeeOutList(List<Haulemployeeout> haulEmployeeOutList) {
		this.haulEmployeeOutList = haulEmployeeOutList;
	}

	public String getPhotoStr() {
		return photoStr;
	}

	public void setPhotoStr(String photoStr) {
		this.photoStr = photoStr;
	}

	public String getMarkTrainType() {
		return markTrainType;
	}

	public void setMarkTrainType(String markTrainType) {
		this.markTrainType = markTrainType;
	}
	
	
	//lixianyuan start
	private String empBreakInfoType;//标记是当前年的还是历史的违章
	private String fstarttime;//违章时间开始
	private String fendtime;//违章时间结束
	
	public String getEmpBreakInfoType() {
		return empBreakInfoType;
	}
	public void setEmpBreakInfoType(String empBreakInfoType) {
		this.empBreakInfoType = empBreakInfoType;
	}
	
	public String getFstarttime() {
		return fstarttime;
	}
	public void setFstarttime(String fstarttime) {
		this.fstarttime = fstarttime;
	}
	
	public String getFendtime() {
		return fendtime;
	}
	public void setFendtime(String fendtime) {
		this.fendtime = fendtime;
	}

	//组装查询条件,封装违章信息显示类型的条件(即当前年还是历史的违章)并且封装 查询条件的时间段
	private Map<String,Object> generateConditionForbreakInfoType(Map<String,Object> condition){
		/*判断若为0表示显示的是当前违章信息，封装条件获取当前年
		 * 若为1表示的是历史违章信息，封装条件为历史年份
		 * 默认为当前年的违章信息
		 */
		if(empBreakInfoType!=null &&empBreakInfoType.equals("1")){
			//获取系统当前时间为积分周期为一年封装条件
			String currentYear = DateHandler.dateToString(new Date(), "yyyy");			
			Integer currentYear_Int = Integer.valueOf(currentYear);			
			//根据当前年计算历史记录年份
			String historyYear = String.valueOf(currentYear_Int-DefaultValue.YEAR_RANGE);
			if(ValidateCheck.isNotNull(historyYear)){
				condition.put("history_Year", historyYear+"0101");
			}
		}else{
			//获取系统当前时间为积分周期为一年封装条件
			String currentYear = DateHandler.dateToString(new Date(), "yyyy");			
			if(ValidateCheck.isNotNull(currentYear)){
				condition.put("current_Year", currentYear);
			}
		}
		
		if(ValidateCheck.isNotNull(fendtime)){
			condition.put("fendtime", fendtime);// 开始时间
		}
		if(ValidateCheck.isNotNull(fstarttime)){
			condition.put("fstarttime", fstarttime);// 结束时间
		}
		
		return condition;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBigStatusMark() {
		return bigStatusMark;
	}

	public void setBigStatusMark(String bigStatusMark) {
		this.bigStatusMark = bigStatusMark;
	}

	public String getAgeLeft() {
		return ageLeft;
	}

	public void setAgeLeft(String ageLeft) {
		this.ageLeft = ageLeft;
	}

	public String getAgeRight() {
		return ageRight;
	}

	public void setAgeRight(String ageRight) {
		this.ageRight = ageRight;
	}

	
	//lixianyuan end
	
	
	
	
}
