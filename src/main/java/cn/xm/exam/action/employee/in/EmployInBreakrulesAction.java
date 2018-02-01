package cn.xm.exam.action.employee.in;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.employee.out.Blacklist;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.employee.in.EmployeeInBreakrulesService;
import cn.xm.exam.utils.DateHandler;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 处理内部员工的违章记录
 * 
 * @author 贤元
 *
 */
@Controller
@Scope("prototype")
public class EmployInBreakrulesAction extends ActionSupport {

	@Resource
	private EmployeeInBreakrulesService employeeInBreakrulesService;

	// @Resource
	// private DepartmentCustomMapper departmentCustomMapper;

	// 实例化要转成json的map集合
	private Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	// 违章的javabean
	private EmplyinBreakrules emplyinBreakrules;

	public EmplyinBreakrules getEmplyinBreakrules() {
		return emplyinBreakrules;
	}

	public void setEmplyinBreakrules(EmplyinBreakrules emplyinBreakrules) {
		this.emplyinBreakrules = emplyinBreakrules;
	}

	// 添加违章信息用的身份证
	private String addIdCard;

	public String getAddIdCard() {
		return addIdCard;
	}

	public void setAddIdCard(String addIdCard) {
		this.addIdCard = addIdCard;
	}

	// 按条件查询要用到的
	private String fName;// 姓名
	private String fIdCode;// 身份证
	private String fBreakScore;// 违章记分范围
	private String fSex;// 性别
	private String fIsBreak;// 是否是黑名单
	private String fdepartmentid;// 部门id
	private String fcurpage;// 当前页页号
	private String fcurtotal;// 每页显示的记录数
	private String fstarttime;// 违章时间段的开始时间
	private String fendtime;// 违章时间段的结束时间
	private String empBreakInfoType;//员工违章记录类型
	private String isOnlyManager;
	
	//员工部门类型
	private String employeeDepartmentType;	
	//家庭住址
	private String address;
	//年龄段左侧数据
	private String ageLeft;
	//年龄段右侧数据
	private String ageRight;
	
	
	
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmployeeDepartmentType() {
		return employeeDepartmentType;
	}

	public void setEmployeeDepartmentType(String employeeDepartmentType) {
		this.employeeDepartmentType = employeeDepartmentType;
	}

	public String getIsOnlyManager() {
		return isOnlyManager;
	}

	public void setIsOnlyManager(String isOnlyManager) {
		this.isOnlyManager = isOnlyManager;
	}

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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfIdCode() {
		return fIdCode;
	}

	public void setfIdCode(String fIdCode) {
		this.fIdCode = fIdCode;
	}

	public String getfBreakScore() {
		return fBreakScore;
	}

	public void setfBreakScore(String fBreakScore) {
		this.fBreakScore = fBreakScore;
	}

	public String getfSex() {
		return fSex;
	}

	public void setfSex(String fSex) {
		this.fSex = fSex;
	}

	public String getfIsBreak() {
		return fIsBreak;
	}

	public void setfIsBreak(String fIsBreak) {
		this.fIsBreak = fIsBreak;
	}

	public String getFdepartmentid() {
		return fdepartmentid;
	}

	public void setFdepartmentid(String fdepartmentid) {
		this.fdepartmentid = fdepartmentid;
	}

	public String getFcurpage() {
		return fcurpage;
	}

	public void setFcurpage(String fcurpage) {
		this.fcurpage = fcurpage;
	}

	public String getFcurtotal() {
		return fcurtotal;
	}

	public void setFcurtotal(String fcurtotal) {
		this.fcurtotal = fcurtotal;
	}

	// 与详细信息相关的 start
	private String detailemployeeId;// 内部员工id
	private String detailunitName;// 部门名称

	public String getDetailemployeeId() {
		return detailemployeeId;
	}

	public void setDetailemployeeId(String detailemployeeId) {
		this.detailemployeeId = detailemployeeId;
	}

	public String getDetailunitName() {
		return detailunitName;
	}

	public void setDetailunitName(String detailunitName) {
		this.detailunitName = detailunitName;
	}

	// 与详细信息相关的 end

	/**
	 * 初始化左侧的部门树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initDepartment() throws Exception {
		// 实例化要转成json的map集合
		map = new LinkedHashMap<String, Object>();
		// 将部门树的所有信息查询出来
		/***** S QLQ添加权限 *******/
		Subject currentUser = SecurityUtils.getSubject();
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user.getDepartmentid();// 获取部门ID
		boolean permitted = currentUser.isPermitted("departmentmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		/***** S QLQ添加权限 *******/

		List<Map<String, Object>> departmentTree = employeeInBreakrulesService.initLeftDepartmentTree(departmentId);
		// struts2自动将map集合转成json
		map.put("departmentTree", departmentTree);
		return "ok";
	}

	/**
	 * 根据部门id获取当前部门旗下的所有员工及其违章信息
	 */
	public String getEmpInMsgByDepIdLeft() {
		// 初始化要转成json的map集合
		map = new LinkedHashMap<String, Object>();

		// 接收从jsp页面穿过来的数据
		HttpServletRequest request = ServletActionContext.getRequest();
		// 部门id
		String departmentid = request.getParameter("");// 部门id
		// 当前页页号
		String curpage = request.getParameter("");
		// 每页显示的记录数
		String curtotal = request.getParameter("");

		int curPage = (Integer.parseInt(curpage) - 1) * (Integer.parseInt(curtotal));// (当前页页号-1)*每页显示的记录数
		int curTotal = Integer.parseInt(curtotal);

		Map<String, Object> mapLeft = new LinkedHashMap<String, Object>();
		mapLeft.put("departmentid", departmentid);
		mapLeft.put("curPage", curPage);// 当前页页号 (当前页页号-1)*每页显示的记录数
		mapLeft.put("curTotal", curTotal);// 每页显示的记录数
		
		/******************zwy*********************/
		mapLeft.put("isManager", isOnlyManager);
		
		
		List<Map<String, Object>> empInMsgByDepIdLeft = employeeInBreakrulesService.getEmpInMsgByDepIdLeft(mapLeft);

		// 获取总记录数
		Map<String, Object> mapLeft2 = new LinkedHashMap<String, Object>();
		mapLeft2.put("departmentid", departmentid);
		int count = employeeInBreakrulesService.getEmpInMsgByDepIdLeftCount(mapLeft2);

		if (empInMsgByDepIdLeft != null) {
			
			// 当前页要显示的数据
			map.put("empInMsgByDepIdLeft", empInMsgByDepIdLeft);
			// 当前页页号
			map.put("curPage", curpage);
			// 每页显示的记录数
			map.put("curTotal", curtotal);
			// 总记录数
			map.put("count", count);
		}

		return "ok";
	}

	/**
	 * 初始化页面的信息
	 * 
	 * @return
	 */
	public String initPageDate() {
		// 实例化要转成josn的map集合
		map = new LinkedHashMap<String, Object>();

		HttpServletRequest request = ServletActionContext.getRequest();
		// 部门id
		// String departmentid = request.getParameter("");// 部门id
		// 当前页页号
		String curpage = request.getParameter("curPage");
		// 每页显示的记录数
		String curtotal = request.getParameter("curTotal");

		int curPage = (Integer.parseInt(curpage) - 1) * (Integer.parseInt(curtotal));// (当前页页号-1)*每页显示的记录数
		int curTotal = Integer.parseInt(curtotal);

		/* S QLQ权限管理 */
		Subject currentUser = SecurityUtils.getSubject();
		// 获取Session中的用户信息
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user.getDepartmentid();// 获取部门ID
		boolean permitted = currentUser.isPermitted("departmentmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		/* S QLQ权限管理 */
		Map<String, Object> mapInit = new LinkedHashMap<String, Object>();
		mapInit.put("curPage", curPage);// 当前页页号 (当前页页号-1)*每页显示的记录数
		mapInit.put("curTotal", curTotal);// 每页显示的记录数
		mapInit.put("departmentId", departmentId);// 部门ID
		
		
		mapInit.put("isManager", isOnlyManager);
		
		//封装违章信息显示类型的条件
		mapInit = generateConditionForbreakInfoType(mapInit);
				
		List<Map<String, Object>> initPageDate = employeeInBreakrulesService.initPageDate(mapInit);

		// 获取总记录数
		int count = employeeInBreakrulesService.initPageDateCount(departmentId);

		if (initPageDate != null) {
			
			// 当前页要显示的数据
			map.put("empInMsgByDepIdLeft", initPageDate);
			// 当前页页号
			map.put("curPage", curpage);
			// 每页显示的记录数
			map.put("curTotal", curtotal);
			// 总记录数
			map.put("count", count);
		}
		return "ok";
	}

	/**
	 * 添加内部员工的违章信息
	 */
	public String addEmpInBreakrules() {
		map = new LinkedHashMap<String, Object>();
		

		// 根据职工id去内部员工违章记录表中查询该职工的违章总积分(添加前的违章总积分)
		Integer sumBreakScoreBefore = employeeInBreakrulesService
				.selSumBreakScoreByEmpId(emplyinBreakrules.getEmpinemployeeid());
		if (sumBreakScoreBefore == null) {
			sumBreakScoreBefore = 0;
		}

		// 添加内部员工的违章信息
		int res = employeeInBreakrulesService.addEmpInBreakrules(emplyinBreakrules);

		// 根据职工id去内部员工违章记录表中查询该职工的违章总积分(添加后的违章总积分)
		Integer sumBreakScoreNow = employeeInBreakrulesService
				.selSumBreakScoreByEmpId(emplyinBreakrules.getEmpinemployeeid());

		// 如果添加前违章总积分<12,不在黑名单中
		if (sumBreakScoreBefore < 12) {
			if (emplyinBreakrules.getEmpinminusnum() >= 12 && employeeDepartmentType.equals("1")) {// 本次记分》=12，加入黑名单，并且状态设置为永久状态
				// 如果是一次的违章记分大于12分的，则标记为永久进入黑名单的1 内部员工0
				Blacklist blacklist = new Blacklist();
				blacklist.setEmployeeid(emplyinBreakrules.getEmpinemployeeid());// 员工id
				blacklist.setDescription("该内部员工永久进入黑名单");
				blacklist.setBlackidcard(addIdCard);// 身份证
				blacklist.setTime(new Date());//
				blacklist.setEmployeestatus("0");// 内部员工
				blacklist.setTemporaryinstatus("1");// 永久进入黑名单
				// 将黑名单信息添加到黑名单中
				int result = employeeInBreakrulesService.addBlacklist(blacklist);
			} else {// 本次记分小于12分
				if (sumBreakScoreNow >= 12) {// 添加后总记分》=12，加入黑名单，记为临时状态
					// 如果是一次的违章记分大于12分的，则标记为永久进入黑名单的1 内部员工0
					Blacklist blacklist = new Blacklist();
					blacklist.setEmployeeid(emplyinBreakrules.getEmpinemployeeid());// 员工id
					blacklist.setDescription("该内部员工临时进入黑名单");
					blacklist.setBlackidcard(addIdCard);// 身份证
					blacklist.setTime(new Date());//
					blacklist.setEmployeestatus("0");// 内部员工
					blacklist.setTemporaryinstatus("0");// 临时进入黑名单
					// 将黑名单信息添加到黑名单中
					int result = employeeInBreakrulesService.addBlacklist(blacklist);
				} else if (sumBreakScoreNow < 12) {// 添加后，总积分，《12，不对黑名单操作

				}

			}

		} else if (sumBreakScoreBefore >= 12) {// 在黑名单中,状态为临时状态
			if (emplyinBreakrules.getEmpinminusnum() >= 12 && employeeDepartmentType.equals("1")) {// 本次记分》=12，将其状态设置为永久状态，只修改状态
				// 2.1将其在黑名单中的状态设置为永久状态
				Blacklist blacklist = employeeInBreakrulesService
						.getBlacklistByEmpInId(emplyinBreakrules.getEmpinemployeeid());
				blacklist.setTemporaryinstatus("1");// 将其状态设置为永久状态
				int rs = employeeInBreakrulesService.updateBlacklist(blacklist);// 更新黑名单信息
				map.put("result", "修改成功");
			} else if (emplyinBreakrules.getEmpinminusnum() < 12) {// 本次记分《12，不对黑名单进行操作

			}
		}

		if (res > 0) {
			map.put("result", "违章信息添加成功");
		} else {
			map.put("result", "违章信息添加失败");
		}
		return "ok";

	}

	/**
	 * 详细信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detailOp() throws Exception {

		// 1.接收从jsp传过来的数据
		// unitName="+unitName+"&employeeId="+employeeId+"
		HttpServletRequest request = ServletActionContext.getRequest();
		// 2.部门名称
		String departmentName = detailunitName;
		// 3.职工id
		String employeeid = detailemployeeId;
		// 4.根据职工id获取职工信息
		EmployeeIn employeeIn = employeeInBreakrulesService.selEmployeeInByEmpId(employeeid);

		/***S QLQ封装查询条件*****/
		Map<String, Object> condition = new LinkedHashMap();
		condition.put("employeeid", employeeid);
		if (ValidateCheck.isNotNull(fstarttime)) {
			condition.put("fstarttime", fstarttime);
		}
		if (ValidateCheck.isNotNull(fendtime)) {
			condition.put("fendtime", fendtime);
		}
					
		/***S QLQ封装查询条件*****/
		
		//封装违章信息显示类型的条件
		condition = generateConditionForbreakInfoType(condition);
		
		// 5.违章总积分
		// 根据职工id去内部员工违章记录表中查询该职工的违章总积分
		Integer sumBreakScore = employeeInBreakrulesService.selSumBreakScoreByEmpId(condition);
		if (sumBreakScore == null) {
			sumBreakScore = 0;
		}

		// 6.根据职工id获取该职工的所有违章信息
		List<EmplyinBreakrules> emplyinBreakrulesList = employeeInBreakrulesService
				.selEmpInBreakrulesByEmpId(condition);

		// 6.1 根据内部员工id获取黑名单状态
		String blackStatus = employeeInBreakrulesService.getBlackStatusByEmpInId(employeeid);

		// 7.将要穿给详细信息页面的数据保存在request域中转发
		request.setAttribute("departmentName", departmentName);// 部门名称
		request.setAttribute("employeeid", employeeid);// 职工id
		request.setAttribute("sumBreakScore", sumBreakScore);// 违章总积分
		request.setAttribute("blackStatus", blackStatus);// 黑名单状态
		request.setAttribute("employeeIn", employeeIn);// 职工信息
		request.setAttribute("employeeDepartmentType", employeeDepartmentType);//员工部门类型
		request.setAttribute("emplyinBreakrulesList", emplyinBreakrulesList);// 该职工的违章信息

		return "detailOp";// 视图的名称
	}

	/**
	 * 删除违章信息,只有不是永久状态的才能删除
	 * 
	 * @return
	 */
	public String delEmpInBreakrules() {
		// 实例化要转成json的map集合
		map = new LinkedHashMap<String, Object>();

		HttpServletRequest request = ServletActionContext.getRequest();
		String delEmployeeId = request.getParameter("delEmployeeId");// 职工id
		String delEmployeeBreakId = request.getParameter("delEmployeeBreakId");// 违章id

		// 计算出删除前的违章总积分
		// 根据职工id去内部员工违章记录表中查询该职工的违章总积分
		Integer sumBreakScoreBefore = employeeInBreakrulesService.selSumBreakScoreByEmpId(delEmployeeId);
		if (sumBreakScoreBefore == null) {
			sumBreakScoreBefore = 0;
		}

		// 1.根据违章id删除违章信息
		int result = employeeInBreakrulesService.deleteByPrimaryKey(Integer.parseInt(delEmployeeBreakId));

		// 2.计算出删除该条违章信息后，职工现在的违章记分
		// 根据职工id去内部员工违章记录表中查询该职工的违章总积分
		Integer sumBreakScoreNow = employeeInBreakrulesService.selSumBreakScoreByEmpId(delEmployeeId);
		if (sumBreakScoreNow == null) {
			sumBreakScoreNow = 0;
		}

		// 删除前违章记分》=12，并且删除后违章记分《12,则将其从黑名单中删除
		if (sumBreakScoreBefore >= 12) {// 删除前违章总积分》=12
			if (sumBreakScoreNow < 12) {// 删除后违章记分《12
				// 该职工在黑名单是临时的，从黑名单删除
				// 3.根据职工id从黑名单中删除该职工的黑名单信息
				int res = employeeInBreakrulesService.delBlacklistByEmpId(delEmployeeId);// 根据职工id删除该职工的黑名单信息
			}
		}

		if (result > 0) {
			map.put("result", "删除成功");
		} else {
			map.put("result", "删除失败，请重新操作");
		}
		return "ok";
	}

	/**
	 * 修改内部员工的违章id
	 * 
	 * @return
	 */
	public String updateEmpInBreakrules() {
		map = new LinkedHashMap<String, Object>();

		

		// 1.计算修改前的违章记分
		// 根据职工id去内部员工违章记录表中查询该职工的违章总积分
		Integer sumBreakScoreBefore = employeeInBreakrulesService
				.selSumBreakScoreByEmpId(emplyinBreakrules.getEmpinemployeeid());
		if (sumBreakScoreBefore == null) {
			sumBreakScoreBefore = 0;
		}

		if (sumBreakScoreBefore >= 12) {// 黑名单中，临时状态
			if (emplyinBreakrules.getEmpinminusnum() >= 12 && employeeDepartmentType.equals("1")) {// 修改黑名单状态
				// 2.2添加违章信息
				int res = employeeInBreakrulesService.updateByPrimaryKeySelective(emplyinBreakrules);
				// 2.1将其在黑名单中的状态设置为永久状态
				Blacklist blacklist = employeeInBreakrulesService
						.getBlacklistByEmpInId(emplyinBreakrules.getEmpinemployeeid());
				blacklist.setTemporaryinstatus("1");// 将其状态设置为永久状态
				int rs = employeeInBreakrulesService.updateBlacklist(blacklist);// 更新黑名单信息
				map.put("result", "修改成功");
				return "ok";
			} else {// 如果修改后违章总积分<12,将该员工从黑名单删除
				// 不操作黑名单,直接添加违章信息
				int res = employeeInBreakrulesService.updateByPrimaryKeySelective(emplyinBreakrules);

				// 根据职工id去内部员工违章记录表中查询该职工的违章总积分
				Integer sumBreakScoreNow = employeeInBreakrulesService
						.selSumBreakScoreByEmpId(emplyinBreakrules.getEmpinemployeeid());
				if (sumBreakScoreNow == null) {
					sumBreakScoreNow = 0;
				}

				if (sumBreakScoreNow < 12) {// 如果修改后违章总积分<12,将该员工从黑名单删除
					// 根据职工id从黑名单中删除该职工的黑名单信息
					int resDelBlack = employeeInBreakrulesService
							.delBlacklistByEmpId(emplyinBreakrules.getEmpinemployeeid());// 根据职工id删除该职工的黑名单信息
				}

				map.put("result", "修改成功");
				return "ok";
			}
		} else if (sumBreakScoreBefore < 12) {// 不在黑名单中
			if (emplyinBreakrules.getEmpinminusnum() >= 12 && employeeDepartmentType.equals("1") ) {// 本次违章记分>=12,添加到黑名单，永久状态
				// 2.2添加违章信息
				int res = employeeInBreakrulesService.updateByPrimaryKeySelective(emplyinBreakrules);
				// 2.1将其在黑名单中的状态设置为永久状态
				// 临时进入黑名单的0
				Blacklist blacklist = new Blacklist();
				blacklist.setEmployeeid(emplyinBreakrules.getEmpinemployeeid());// 员工id
				blacklist.setDescription("该内部员工临时进入黑名单");
				// 根据员工id获取员工信息，从员工信息中获取身份证
				EmployeeIn employeeinMSG = employeeInBreakrulesService
						.selEmployeeInByEmpId(emplyinBreakrules.getEmpinemployeeid());

				blacklist.setBlackidcard(employeeinMSG.getIdcode());// 身份证
				blacklist.setTime(new Date());//
				blacklist.setEmployeestatus("0");// 内部员工
				blacklist.setTemporaryinstatus("1");// 永久进入黑名单
				// .将黑名单信息添加到黑名单中
				int result = employeeInBreakrulesService.addBlacklist(blacklist);
				map.put("result", "修改成功");
				return "ok";
			} else {// 修改后违章总积分>=12,添加到黑名单，临时状态；修改后违章总积分《12，不进入黑名单

				// 2.2添加违章信息
				int res = employeeInBreakrulesService.updateByPrimaryKeySelective(emplyinBreakrules);

				// 根据职工id去内部员工违章记录表中查询该职工的违章总积分
				Integer sumBreakScoreNow = employeeInBreakrulesService
						.selSumBreakScoreByEmpId(emplyinBreakrules.getEmpinemployeeid());
				if (sumBreakScoreNow == null) {
					sumBreakScoreNow = 0;
				}

				//// 修改后违章总积分>=12,添加到黑名单，临时状态；修改后违章总积分《12，不进入黑名单
				if (sumBreakScoreNow >= 12) {// 修改后违章总积分>=12,添加到黑名单，临时状态
					// 添加到黑名单，临时状态
					// 临时进入黑名单的0
					Blacklist blacklist = new Blacklist();
					blacklist.setEmployeeid(emplyinBreakrules.getEmpinemployeeid());// 员工id
					blacklist.setDescription("该内部员工临时进入黑名单");
					// 根据员工id获取员工信息，从员工信息中获取身份证
					EmployeeIn employeeinMSG = employeeInBreakrulesService
							.selEmployeeInByEmpId(emplyinBreakrules.getEmpinemployeeid());
					blacklist.setBlackidcard(employeeinMSG.getIdcode());// 身份证
					blacklist.setTime(new Date());//
					blacklist.setEmployeestatus("0");// 内部员工
					blacklist.setTemporaryinstatus("0");// 临时进入黑名单
					// 将黑名单信息添加到黑名单中
					int result = employeeInBreakrulesService.addBlacklist(blacklist);
					map.put("result", "修改成功");
					return "ok";
				} else if (sumBreakScoreNow < 12) {// 修改前不在黑名单中，但修改后违章总积分<12,不对黑名单进行操作
					map.put("result", "修改成功");
					return "ok";
				}

			}

		}

		return "ok";

	}

	/**
	 * 点击左侧的部门之后，将其及其子孙部门下的内部员工的信息及违章信息显示出来
	 * 
	 * @return
	 * @throws Exception
	 */
	public String clickLeftTreeShowMsg() throws Exception {
		map = new LinkedHashMap<String, Object>();

		HttpServletRequest request = ServletActionContext.getRequest();
		String departmentid = request.getParameter("departmentid");// 接收部门id
		String curpage = request.getParameter("curPage");// 当前页页号
		String curtotal = request.getParameter("curTotal");// 每页显示的记录数

		int curPage = (Integer.parseInt(curpage) - 1) * (Integer.parseInt(curtotal));// (当前页页号-1)*每页显示的记录数
		int curTotal = Integer.parseInt(curtotal);

		Map<String, Object> mapLeft = new LinkedHashMap<String, Object>();
		mapLeft.put("departmentid", departmentid);// 部门id
		mapLeft.put("curPage", curPage);//// (当前页页号-1)*每页显示的记录数
		mapLeft.put("curTotal", curTotal);// 每页显示的记录数
		//封装违章信息显示类型的条件
		mapLeft = generateConditionForbreakInfoType(mapLeft);
		
		List<Map<String, Object>> clickLeftShowEmpInMsgList = employeeInBreakrulesService
				.clickLeftShowEmpInMsg(mapLeft);

		// 总记录数
		Map<String, Object> mapLeftCount = new LinkedHashMap<String, Object>();
		mapLeftCount.put("departmentid", departmentid);// 部门id
		int count = employeeInBreakrulesService.clickLeftShowEmpInMsgCount(mapLeftCount);

		if (count > 0 && clickLeftShowEmpInMsgList != null) {
			// 当前页要显示的数据
			map.put("empInMsgByDepIdLeft", clickLeftShowEmpInMsgList);
			// 当前页页号
			map.put("curPage", curpage);
			// 每页显示的记录数
			map.put("curTotal", curtotal);
			// 总记录数
			map.put("count", count);
		}

		return "ok";
	}

	/**
	 * 左侧的树和条件绑定之后的查询
	 * 
	 * @return
	 */
	public String leftTreeAndConditionShowMsg() {
		map = new LinkedHashMap<String, Object>();

		String blackStatus = "";// 用于标记黑名单状态是否被选中

		// 接收穿过来的数据
		
		
		String isManager = isOnlyManager;//是否是管理员
		if(isManager == null){
			isManager ="";
		}
		
		String name = fName;// 姓名
		if (name == null) {
			name = "";
		}
		String idcode = fIdCode;// 身份证
		if (idcode == null) {
			idcode = "";
		}
		String breakscoreRange = fBreakScore;// 违章记分范围
		if (breakscoreRange == null) {
			breakscoreRange = "0,100";
		}
		String sex = fSex;// 性别
		if (sex == null) {
			sex = "";
		}
		String isBreak = fIsBreak;// 是否是黑名单

		String departmentid = fdepartmentid;// 部门id
		if (departmentid == null) {
			departmentid = "";
		}
		String curpage = fcurpage;// 当前页页号
		String curtotal = fcurtotal;// 每页显示的记录数

		String str = breakscoreRange;// "1,1000";

		/*
		 * if(isBreak!=null){ if("是".fins(isBreak)){ blackStatus = "是"; }else
		 * if("否".equals(isBreak)){ blackStatus = "否"; } }else
		 * if(isBreak==null){ blackStatus = "无"; }
		 */

		String[] split = str.split(",");
		int startminusNum = Integer.parseInt(split[0]);
		int endminusNum = Integer.parseInt(split[1]);

		int curPage = (Integer.parseInt(curpage) - 1) * (Integer.parseInt(curtotal));// （当前页页号-1）*每页显示的记录数
		int curTotal = Integer.parseInt(curtotal);

		// 1. 没有选中黑名单状态的情况
		if (isBreak == null) {
			/***** S QLQ权限 **********/
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
			String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
			// 获取用户信息
			Subject currentUser = SecurityUtils.getSubject();
			boolean permitted = currentUser.isPermitted("departmentmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
			String departmentId = permitted ? null : departmentIdSession;
			/***** S QLQ权限 **********/
			// 1.1没有选中黑名单 状态的情况
			// 1.2根据条件查询数据
			Map<String, Object> mapLeftAndCondition = new LinkedHashMap<String, Object>();
			mapLeftAndCondition.put("name", name);// 姓名
			mapLeftAndCondition.put("idcode", idcode);// 身份证
			if (ValidateCheck.isNotNull(fstarttime)) {
				mapLeftAndCondition.put("fstarttime", fstarttime);// 开始时间
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				mapLeftAndCondition.put("fendtime", fendtime);// 结束时间
			}
			mapLeftAndCondition.put("sex", sex);// 性别
			mapLeftAndCondition.put("minumsStart", startminusNum);// 违章记分的起始分数
			mapLeftAndCondition.put("minumsEnd", endminusNum);// 违章记分的结末尾分数
			mapLeftAndCondition.put("departmentid", departmentid);// 部门id
			mapLeftAndCondition.put("curPage", curPage);// 当前页页号
			mapLeftAndCondition.put("curTotal", curTotal);// 每页显示的记录数
			
			/****************************zwy*********************/
			mapLeftAndCondition.put("isManager", isManager);
			
			if (ValidateCheck.isNotNull(departmentId)) {
				mapLeftAndCondition.put("departmentId", departmentId);
			}
			
			//封装违章信息显示类型的条件
			mapLeftAndCondition = generateConditionForbreakInfoType(mapLeftAndCondition);
			
			List<Map<String, Object>> leftTreeAndConditionMsgList = employeeInBreakrulesService
					.leftTreeAndConditionMsg(mapLeftAndCondition);

			// 1.3查询总记录数
			Map<String, Object> mapLeftAndConditionCount = new LinkedHashMap<String, Object>();
			mapLeftAndConditionCount.put("name", name);// 姓名
			mapLeftAndConditionCount.put("idcode", idcode);// 身份证
			mapLeftAndConditionCount.put("sex", sex);// 性别
			mapLeftAndConditionCount.put("minumsStart", startminusNum);// 违章记分的起始分数
			mapLeftAndConditionCount.put("minumsEnd", endminusNum);// 违章记分的结末尾分数
			mapLeftAndConditionCount.put("departmentid", departmentid);// 部门id
			if (ValidateCheck.isNotNull(fstarttime)) {
				mapLeftAndConditionCount.put("fstarttime", fstarttime);// 开始时间
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				mapLeftAndConditionCount.put("fendtime", fendtime);// 结束时间
			}

			if (ValidateCheck.isNotNull(departmentId)) {
				mapLeftAndConditionCount.put("departmentId", departmentId);
			}
			/******************zwy*********************/
			mapLeftAndConditionCount.put("isManager", isManager);
			
			
			//封装违章信息显示类型的条件
			mapLeftAndConditionCount = generateConditionForbreakInfoType(mapLeftAndConditionCount);
			
			
			int count = employeeInBreakrulesService.leftTreeAndConditionMsgCount(mapLeftAndConditionCount);
			// 1.4返回结果给jsp
			if (count > 0 && leftTreeAndConditionMsgList != null) {
				// 当前页要显示的数据
				map.put("empInMsgByDepIdLeft", leftTreeAndConditionMsgList);
				// 当前页页号
				map.put("curPage", curpage);
				// 每页显示的记录数
				map.put("curTotal", curtotal);
				// 总记录数
				map.put("count", count);
			}
		} else if ("是".equals(isBreak)) {
			/***** S QLQ权限 **********/
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
			String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
			// 获取用户信息
			Subject currentUser = SecurityUtils.getSubject();
			boolean permitted = currentUser.isPermitted("departmentmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
			String departmentId = permitted ? null : departmentIdSession;
			/***** S QLQ权限 **********/
			// 2 选中的黑名单状态为"是"的情况
			Map<String, Object> mapLeftAndConditionIsBlack = new LinkedHashMap<String, Object>();
			if (ValidateCheck.isNotNull(fstarttime)) {
				mapLeftAndConditionIsBlack.put("fstarttime", fstarttime);// 开始时间
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				mapLeftAndConditionIsBlack.put("fendtime", fendtime);// 结束时间
			}
			mapLeftAndConditionIsBlack.put("name", name);// 姓名
			mapLeftAndConditionIsBlack.put("idcode", idcode);// 身份证
			mapLeftAndConditionIsBlack.put("sex", sex);// 性别
			// 是否选中黑名单状态的标记
			mapLeftAndConditionIsBlack.put("blackstatus", "1");
			mapLeftAndConditionIsBlack.put("departmentid", departmentid);// 部门id
			mapLeftAndConditionIsBlack.put("curPage", curPage);// 当前页页号
			mapLeftAndConditionIsBlack.put("curTotal", curTotal);// 每页显示的记录数
			mapLeftAndConditionIsBlack.put("departmentId", departmentId);// 每页显示的记录数
			
			/******************zwy*********************/
			mapLeftAndConditionIsBlack.put("isManager", isManager);
			
			
			
			if (ValidateCheck.isNotNull(fstarttime)) {
				mapLeftAndConditionIsBlack.put("fstarttime", fstarttime);// 开始时间
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				mapLeftAndConditionIsBlack.put("fendtime", fendtime);// 结束时间
			}
			//封装违章信息显示类型的条件
			mapLeftAndConditionIsBlack = generateConditionForbreakInfoType(mapLeftAndConditionIsBlack);
		
			
			List<Map<String, Object>> leftTreeAndConditionIsBlacklistMsg = employeeInBreakrulesService
					.leftTreeAndConditionIsBlacklistMsg(mapLeftAndConditionIsBlack);
			// 2.2查询总记录数
			Map<String, Object> mapLeftAndConditionIsBlackCount = new LinkedHashMap<String, Object>();

			mapLeftAndConditionIsBlackCount.put("name", name);// 姓名
			mapLeftAndConditionIsBlackCount.put("idcode", idcode);// 身份证
			mapLeftAndConditionIsBlackCount.put("sex", sex);// 性别
			// 是否选中黑名单状态的标记
			mapLeftAndConditionIsBlackCount.put("blackstatus", "1");
			mapLeftAndConditionIsBlackCount.put("departmentid", departmentid);// 部门id
			mapLeftAndConditionIsBlackCount.put("departmentId", departmentId);// 部门id
			
			/******************zwy*********************/
			mapLeftAndConditionIsBlackCount.put("isManager", isManager);
			
			
			
			if (ValidateCheck.isNotNull(fstarttime)) {
				mapLeftAndConditionIsBlackCount.put("fstarttime", fstarttime);// 开始时间
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				mapLeftAndConditionIsBlackCount.put("fendtime", fendtime);// 结束时间
			}
			
			//封装违章信息显示类型的条件
			mapLeftAndConditionIsBlackCount = generateConditionForbreakInfoType(mapLeftAndConditionIsBlackCount);
			
			
			int count = employeeInBreakrulesService
					.leftTreeAndConditionIsBlacklistMsgCount(mapLeftAndConditionIsBlackCount);
			// 2.3返回结果给jsp
			if (count > 0 && leftTreeAndConditionIsBlacklistMsg != null) {
				// 当前页要显示的数据
				map.put("empInMsgByDepIdLeft", leftTreeAndConditionIsBlacklistMsg);
				// 当前页页号
				map.put("curPage", curpage);
				// 每页显示的记录数
				map.put("curTotal", curtotal);
				// 总记录数
				map.put("count", count);
			}

		} else if ("否".equals(isBreak)) {
			/***** S QLQ权限 **********/
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
			String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
			// 获取用户信息
			Subject currentUser = SecurityUtils.getSubject();
			boolean permitted = currentUser.isPermitted("departmentmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
			String departmentId = permitted ? null : departmentIdSession;
			/***** S QLQ权限 **********/
			// 3 选中的黑名单状态为"否"的情况
			Map<String, Object> mapLeftAndConditionNoBlack = new LinkedHashMap<String, Object>();
			if (ValidateCheck.isNotNull(fstarttime)) {
				mapLeftAndConditionNoBlack.put("fstarttime", fstarttime);// 开始时间
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				mapLeftAndConditionNoBlack.put("fendtime", fendtime);// 结束时间
			}
			mapLeftAndConditionNoBlack.put("name", name);// 姓名
			mapLeftAndConditionNoBlack.put("idcode", idcode);// 身份证
			mapLeftAndConditionNoBlack.put("sex", sex);// 性别
			// 是否选中黑名单状态的标记
			mapLeftAndConditionNoBlack.put("blackstatus", "1");
			mapLeftAndConditionNoBlack.put("departmentid", departmentid);// 部门id
			mapLeftAndConditionNoBlack.put("curPage", curPage);// 当前页页号
			mapLeftAndConditionNoBlack.put("curTotal", curTotal);// 每页显示的记录数
			mapLeftAndConditionNoBlack.put("departmentId", departmentId);// 每页显示的记录数
			
			/******************zwy*********************/
			mapLeftAndConditionNoBlack.put("isManager", isManager);
			
			
			
			
			if (ValidateCheck.isNotNull(fstarttime)) {
				mapLeftAndConditionNoBlack.put("fstarttime", fstarttime);// 开始时间
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				mapLeftAndConditionNoBlack.put("fendtime", fendtime);// 结束时间
			}
			//封装违章信息显示类型的条件
			mapLeftAndConditionNoBlack = generateConditionForbreakInfoType(mapLeftAndConditionNoBlack);
			
			
			List<Map<String, Object>> leftTreeAndConditionNoBlacklistMsg = employeeInBreakrulesService
					.leftTreeAndConditionNoBlacklistMsg(mapLeftAndConditionNoBlack);

			// 3.2查询总记录数
			Map<String, Object> mapLeftAndConditionNoBlackCount = new LinkedHashMap<String, Object>();

			mapLeftAndConditionNoBlackCount.put("name", name);// 姓名
			mapLeftAndConditionNoBlackCount.put("idcode", idcode);// 身份证
			mapLeftAndConditionNoBlackCount.put("sex", sex);// 性别
			// 是否选中黑名单状态的标记
			mapLeftAndConditionNoBlackCount.put("blackstatus", "1");
			mapLeftAndConditionNoBlackCount.put("departmentid", departmentid);// 部门id
			mapLeftAndConditionNoBlackCount.put("departmentId", departmentId);// 部门id
			
			/******************zwy*********************/
			mapLeftAndConditionNoBlackCount.put("isManager", isManager);
			
			
			
			if (ValidateCheck.isNotNull(fstarttime)) {
				mapLeftAndConditionNoBlackCount.put("fstarttime", fstarttime);// 开始时间
			}
			if (ValidateCheck.isNotNull(fendtime)) {
				mapLeftAndConditionNoBlackCount.put("fendtime", fendtime);// 结束时间
			}
			//封装违章信息显示类型的条件
			mapLeftAndConditionNoBlackCount = generateConditionForbreakInfoType(mapLeftAndConditionNoBlackCount);					
			int count = employeeInBreakrulesService
					.leftTreeAndConditionNoBlacklistMsgCount(mapLeftAndConditionNoBlackCount);
			// 3.3返回结果给jsp
			if (count > 0 && leftTreeAndConditionNoBlacklistMsg != null) {
				// 当前页要显示的数据
				map.put("empInMsgByDepIdLeft", leftTreeAndConditionNoBlacklistMsg);
				// 当前页页号
				map.put("curPage", curpage);
				// 每页显示的记录数
				map.put("curTotal", curtotal);
				// 总记录数
				map.put("count", count);
			}
		} // 最大的if的括号

		return "ok";
	}
	
	/**
	 * 查询单个内部员工今年的违章总积分
	 * @return
	 */
	public String getSingleEmplyInBreakScoreSum() {
		//实例化要转成json的map集合
		map = new LinkedHashMap<String,Object>();
		
		//接收从jsp传过来的员工id
		String employeeid = ServletActionContext.getRequest().getParameter("employeeid");
		
		Map<String, Object> map5 = new LinkedHashMap<String,Object>();
		map5.put("empinemployeeid", employeeid);//内部员工id
		int sumBreakScore = employeeInBreakrulesService.getSingleEmplyInBreakScoreSum(map5);
		
		
		map.put("result", sumBreakScore);
		
		return "ok";
	}
	
	/*****leilong******/
	//组装查询条件,封装违章信息显示类型的条件
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
		
		//家庭住址
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
		
		return condition;
	}
}


