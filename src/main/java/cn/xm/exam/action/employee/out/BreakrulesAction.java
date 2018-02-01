package cn.xm.exam.action.employee.out;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.employee.out.Blacklist;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.service.employee.out.BreakrulesService;
import cn.xm.exam.utils.DateHandler;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.ValidateCheck;
/**
 * 外部员工违章管理
 * @author 贤元
 *
 */
@Controller
@Scope("prototype")
public class BreakrulesAction extends ActionSupport {

	@Autowired
	private BreakrulesService breakrulesService;

	// @Autowired
	// private ProjectService projectService;// 用来加载左侧的树

	// 要转成json的map集合
	private Map<String, Object> map;

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	// javabean
	private Breakrules breakrules;

	public Breakrules getBreakrules() {
		return breakrules;
	}

	public void setBreakrules(Breakrules breakrules) {
		this.breakrules = breakrules;
	}

	// 部门id(unitid) \姓名、省份证、违章记分、性别、黑名单,当前页页号，每页显示的记录数
	private String funitBigHaulBH;// 单位大修编号
	private String funitId;// 部门id(单位编号)
	private String fName;// 姓名
	private String fIdCard;// 身份证
	private String fBreakScore;// 违章记分
	private String fSex;// 性别
	private String fIsBreak;// 是否黑名单
	private String fcurpage;// 当前页页号
	private String fcurtotal;// 每页显示的记录数

	// 添加操作的 大修单位表的id
	private String addHaulBigId;
	// 职工的违章id
	private String addempBreakId;
	// 单位编号
	private String addUnitidM;
	
	//违章信息类型
	private String empBreakInfoType;
	
	
	public String getEmpBreakInfoType() {
		return empBreakInfoType;
	}

	public void setEmpBreakInfoType(String empBreakInfoType) {
		this.empBreakInfoType = empBreakInfoType;
	}

	public String getAddUnitidM() {
		return addUnitidM;
	}

	public void setAddUnitidM(String addUnitidM) {
		this.addUnitidM = addUnitidM;
	}

	public String getUnitBigHaulId() {
		return unitBigHaulId;
	}

	public void setUnitBigHaulId(String unitBigHaulId) {
		this.unitBigHaulId = unitBigHaulId;
	}

	// 单位大修编号
	private String unitBigHaulId;
	// 修改前的单条违章记分
	private String upQianBreakScore;
	// 大修id 修改操作的
	private String upBigid;

	public String getUpBigid() {
		return upBigid;
	}

	public void setUpBigid(String upBigid) {
		this.upBigid = upBigid;
	}

	// ==修改操作的end
	public String getAddempBreakId() {
		return addempBreakId;
	}

	public void setAddempBreakId(String addempBreakId) {
		this.addempBreakId = addempBreakId;
	}

	public String getFunitBigHaulBH() {
		return funitBigHaulBH;
	}

	public void setFunitBigHaulBH(String funitBigHaulBH) {
		this.funitBigHaulBH = funitBigHaulBH;
	}

	public String getAddHaulBigId() {
		return addHaulBigId;
	}

	public void setAddHaulBigId(String addHaulBigId) {
		this.addHaulBigId = addHaulBigId;
	}

	public String getUpQianBreakScore() {
		return upQianBreakScore;
	}

	public void setUpQianBreakScore(String upQianBreakScore) {
		this.upQianBreakScore = upQianBreakScore;
	}

	public String getFunitId() {
		return funitId;
	}

	public void setFunitId(String funitId) {
		this.funitId = funitId;
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

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getfIdCard() {
		return fIdCard;
	}

	public void setfIdCard(String fIdCard) {
		this.fIdCard = fIdCard;
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

	// 用于详细信息的 start
	private String detailName;// 姓名
	private String detailSex;// 性别
	private String detailIsBreak;// 是否黑名单
	private String detailIdCard;// 身份证
	private String detailUnitName;// 部门名称
	private String detailBigId;// 大修id bigid
	private String detailEmployeeId;// 员工id
	private String detailBigEmployeeoutId;// 参加大修的员工共编号 detailBigEmployeeoutId
	private String detailUnitBigHual;// 大修单位
	// 用于详细信息的 end

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getDetailSex() {
		return detailSex;
	}

	public void setDetailSex(String detailSex) {
		this.detailSex = detailSex;
	}

	public String getDetailIsBreak() {
		return detailIsBreak;
	}

	public void setDetailIsBreak(String detailIsBreak) {
		this.detailIsBreak = detailIsBreak;
	}

	public String getDetailIdCard() {
		return detailIdCard;
	}

	public void setDetailIdCard(String detailIdCard) {
		this.detailIdCard = detailIdCard;
	}

	public String getDetailUnitName() {
		return detailUnitName;
	}

	public void setDetailUnitName(String detailUnitName) {
		this.detailUnitName = detailUnitName;
	}

	public String getDetailBigId() {
		return detailBigId;
	}

	public void setDetailBigId(String detailBigId) {
		this.detailBigId = detailBigId;
	}

	public String getDetailEmployeeId() {
		return detailEmployeeId;
	}

	public void setDetailEmployeeId(String detailEmployeeId) {
		this.detailEmployeeId = detailEmployeeId;
	}

	public String getDetailBigEmployeeoutId() {
		return detailBigEmployeeoutId;
	}

	public void setDetailBigEmployeeoutId(String detailBigEmployeeoutId) {
		this.detailBigEmployeeoutId = detailBigEmployeeoutId;
	}

	public String getDetailUnitBigHual() {
		return detailUnitBigHual;
	}

	public void setDetailUnitBigHual(String detailUnitBigHual) {
		this.detailUnitBigHual = detailUnitBigHual;
	}

	/**
	 * 加载树
	 * 
	 * @return
	 */
	public String unloadTree() {
		List<Map<String, Object>> leftTree = breakrulesService.findLeftTree();
		map = new HashMap<String, Object>();
		map.put("leftTree", leftTree);
		return "ok";
	}

	/**
	 * 根据大修id(haulunitid)和部门id(unitid也就是单位id)获取该部门下的所有外来职工信息
	 * 
	 * 思路： 根据外来单位表unit的单位编号(也就是树的编号)在参加大修的外来职工表haulEmployeeOut中
	 * 找到所有职工id，在将找到的所有职工的id去外来职工表employee_out中根据职工id查询出所有外来职工信息。
	 */
	public String selEmployeeOutByUnitid() throws Exception {
		// 实例化要转成json的map集合
		map = new LinkedHashMap<String, Object>();

		HttpServletRequest request = ServletActionContext.getRequest();
		// 单位大修编号
		String unitBigHual = request.getParameter("unitBigHual");
		// 接收从后台传过来的部门id(单位id)
		String unitid = request.getParameter("unitBH");
		// 接收当前页页号
		String curPage2 = request.getParameter("curPage");
		// 接收每页显示的记录数
		String curTota2 = request.getParameter("curTotal");

		

		int curPage = (Integer.parseInt(curPage2) - 1) * (Integer.parseInt(curTota2));
		int curTotal = Integer.parseInt(curTota2);

		//
		Map<String, Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("unitbigid", unitBigHual);// 大修单位编号
		map1.put("unitid", unitid);// 单位编号(项目编号)
		map1.put("curPage", curPage);// 当前页页号
		map1.put("curTotal", curTotal);// 每页显示的记录数
		
		//增加违章记录类型条件
		map1 = generateConditionForbreakInfoType(map1);
		
		List<Map<String, Object>> selectLeftToTable = breakrulesService.selectLeftToTable(map1);
		

		// 查询总条数
		Map<String, Object> map2 = new LinkedHashMap<String, Object>();
		map2.put("unitbigid", unitBigHual);// 大修单位编号
		map2.put("unitid", unitid);// 单位编号(项目编号)
		int count = breakrulesService.selectLeftToTableCount(map2);
		

		if (selectLeftToTable != null) {
			// 封装要显示的数据
			map.put("selectLeftToTable", selectLeftToTable);
			// 封装总记录数
			map.put("count", count);
			// 当前页页号
			map.put("curPage", Integer.parseInt(curPage2));
			// 每页显示的记录数
			map.put("curTotal", Integer.parseInt(curTota2));
		}

		return "ok";
	}

	/**
	 * 修改违章记录 另外要实现的功能:（如果是单词》=12分，则这个都算是永久进入黑名单）
	 * 1、就是当当前员工的违章总积分《12的时候，并且不是永久黑名单，应该还要把其从黑名单中删除,当员工的违章总积分》
	 * 12的时候应该把该员工信息添加到黑名单中 3、如果该员工在黑名单中的状态是永久的，则不让他删除操作
	 * 
	 * 状态不为永久状态的
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateBreakrules() throws Exception {
		// 实例化要转成json的map集合
		map = new LinkedHashMap<String, Object>();
		// 1、接收从jsp页面传过来的数据
	

		// 1.计算出该大修下的该职工的违章总积分 (修改前)
		Map<String, Object> mapSum = new LinkedHashMap<String, Object>();
		mapSum.put("employeeid", breakrules.getEmployeeid());// 职工id
		mapSum.put("bigemployeeoutid", breakrules.getBigemployeeoutid());// 大修员工id
		Integer sumBreakScoreBefore = breakrulesService.selSumBreakScoreByEmpId(breakrules.getEmployeeid());
		if (sumBreakScoreBefore == null) {
			sumBreakScoreBefore = 0;
		}
		
		// 2、将违章信息更新到违章表中
		int res = breakrulesService.updateBreakrules(breakrules);//
		
		
		//计算出修改后的违章总积分（修改后）
		// 3.计算出该大修下的该职工的违章总积分 (修改后)
		Map<String, Object> mapSum2 = new LinkedHashMap<String, Object>();
		mapSum2.put("employeeid", breakrules.getEmployeeid());// 职工id
		mapSum2.put("bigemployeeoutid", breakrules.getBigemployeeoutid());// 大修员工id
		Integer sumBreakScoreNow = breakrulesService.selSumBreakScoreByEmpId(breakrules.getEmployeeid());
		if (sumBreakScoreNow == null) {
			sumBreakScoreNow = 0;
		}
		
		//***8
		//修改前违章总积分<12,不在黑名单，
		if(sumBreakScoreBefore<12){
			//如果本次记分>=12,加入黑名单，永久状态
			if(breakrules.getMinusnum()>=12){//如果本次记分>=12,加入黑名单，永久状态
				EmployeeOut employeeoutBean = breakrulesService.selEmployeeOutByEmployeeId(breakrules.getEmployeeid());
				Blacklist blacklist = new Blacklist();
				blacklist.setBlackidcard(employeeoutBean.getIdcode());// 设置身份证
				blacklist.setEmployeeid(breakrules.getEmployeeid());// 员工id
				blacklist.setTime(new Date());// 加入黑名单的时间
				blacklist.setTemporaryinstatus("1");// 设置为永久
				blacklist.setDescription("该职工被永久列入黑名单");// 描述
				blacklist.setEmployeestatus("1");// 表示外部员工
				// 将黑名单保存在黑名单表中
				int addBlackListRes = breakrulesService.addBlackList(blacklist);
			}else if(breakrules.getMinusnum()<12){//本次违章记分<12
				if(sumBreakScoreNow>=12){//修改后违章记分>=12,加入黑名单，临时状态
					EmployeeOut employeeoutBean = breakrulesService.selEmployeeOutByEmployeeId(breakrules.getEmployeeid());
					Blacklist blacklist = new Blacklist();
					blacklist.setBlackidcard(employeeoutBean.getIdcode());// 设置身份证
					blacklist.setEmployeeid(breakrules.getEmployeeid());// 员工id
					blacklist.setTime(new Date());// 加入黑名单的时间
					blacklist.setTemporaryinstatus("0");// 设置为临时
					blacklist.setDescription("该职工临时列入黑名单");// 描述
					blacklist.setEmployeestatus("1");// 表示外部员工
					// 将黑名单保存在黑名单表中
					int addBlackListRes = breakrulesService.addBlackList(blacklist);
				}else if(sumBreakScoreNow<12){//修改后违章总记分《12，删除该员工的黑名单
					// 将该员工的信息从黑名单表中删除(根据职工id进行删除),只删除外部员工的
					int resultBlackList = breakrulesService.delBlacklistByEmployeeId(breakrules.getEmployeeid());
				}
			}
			
			
		}else if(sumBreakScoreBefore>=12){//修改前违章总积分>=12,在黑名单中了，临时状态
			if(breakrules.getMinusnum()>=12){//本次记分>=12,将其黑名单状态修改成永久状态，只修改状态
				Blacklist selBlackListByEmpId3 = breakrulesService.selBlackListByEmpId(breakrules.getEmployeeid());
				selBlackListByEmpId3.setTemporaryinstatus("1");
				selBlackListByEmpId3.setDescription("该员工被永久进入黑名单");
				// 更新黑名单信息
				int updateBalcklistRes = breakrulesService.updateBalcklist(selBlackListByEmpId3);
			}else if(breakrules.getMinusnum()<12){//本次记分《12，且修改后违章总积分《12，将其从黑名单中删除
				//如果修改后违章总积分《12，z
				if(sumBreakScoreNow<12){
					//将其从黑名单中删除
					// 0.1将该员工的信息从黑名单表中删除(根据职工id进行删除),只删除外部员工的
					int resultBlackList = breakrulesService.delBlacklistByEmployeeId(breakrules.getEmployeeid());
				}else if(sumBreakScoreNow>=12){//本次记分《12，修改改后违章总积分仍》=12，部队黑名单操作
					
				}
				
			}
			
		}

		// 5、

		if (res > 0) {
			map.put("result", "修改成功！");
		} else {
			map.put("result", "修改失败");
		}

		return "ok";

	}

	/**
	 * 删除违章记录 另外要实现的功能: 1、修改对应的大修单位表中的总积分 2、就是当当前员工的违章总积分《12的时候，应该还要把其从黑名单中删除
	 * //只有不为永久状态的才能删除
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delBreakrules() throws Exception {
		// 实例化要转成map的集合
		map = new LinkedHashMap<String, Object>();

		// 1、接收从jsp页面上传过来的数据
		// 职工id
		String delEmployeeId = ServletActionContext.getRequest().getParameter("delEmployeeId");
		// 违章id
		String employeeoutBreakId = ServletActionContext.getRequest().getParameter("delEmployeeBreakId");
		// 接收大修单位编号
		String delbigid = ServletActionContext.getRequest().getParameter("delbigid");
		// 接收大修员工ID
		String delBigEmployeeOutId = ServletActionContext.getRequest().getParameter("delBigEmployeeOutId");
		

		// 2.计算出该大修下的该职工的违章总积分 (删除前)
		Map<String, Object> mapSum = new LinkedHashMap<String, Object>();
		mapSum.put("employeeid", delEmployeeId);// 职工id
		mapSum.put("bigemployeeoutid", delBigEmployeeOutId);// 大修员工id
		Integer sumBreakScoreBefore = breakrulesService.selSumBreakScoreByEmpId(delEmployeeId);
		if (sumBreakScoreBefore == null) {
			sumBreakScoreBefore = 0;
		}

		// 如果删除前违章记分>=12,删除后违章记分<12则将黑名单信息删除
		if (sumBreakScoreBefore >= 12) {//删除前违章记分》=12，在黑名单中，且为临时状态
			// 3、删除违章记录
			// 根据违章id删除该条违章信息
			int res = breakrulesService.delBreakrules(Integer.parseInt(employeeoutBreakId));
			// 计算出删除后的违章记分
			Map<String, Object> mapSum2 = new LinkedHashMap<String, Object>();
			mapSum2.put("employeeid", delEmployeeId);// 职工id
			mapSum2.put("bigemployeeoutid", delBigEmployeeOutId);// 大修员工id
			Integer sumBreakScoreNow = breakrulesService.selSumBreakScoreByEmpId(delEmployeeId);//删除后的违章总记分
			if (sumBreakScoreNow < 12) {
				// 则将黑名单信息删除
				// 0.1将该员工的信息从黑名单表中删除(根据职工id进行删除),只删除外部员工的
				int resultBlackList = breakrulesService.delBlacklistByEmployeeId(delEmployeeId);
				if (res > 0) {
					map.put("result", "删除成功");
				} else {
					map.put("result", "删除失败，请重新操作");
				}
				return "ok";
			} else {
				// 删除后违章记分仍大于12,则不对黑名单操作
				if (res > 0) {
					map.put("result", "删除成功");
				} else {
					map.put("result", "删除失败，请重新操作");
				}
				return "ok";
			}
		} else if (sumBreakScoreBefore <= 12) {// 如果删除前违章记分<12,只需删除违章信息，不对黑名单进行任何操作
			// 3、删除违章记录
			// 根据违章id删除该条违章信息
			int res = breakrulesService.delBreakrules(Integer.parseInt(employeeoutBreakId));
			if (res > 0) {
				map.put("result", "删除成功");
			} else {
				map.put("result", "删除失败，请重新操作");
			}
			return "ok";
		}

		return "ok";

	}

	/**
	 * 查看详情的操作，根据员工id在违章表中查询该员工的所有违章记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public String detailOp() throws Exception {
		// 实例化要转成json的map集合

	
		HttpServletRequest request = ServletActionContext.getRequest();
		// 1.接收传过来的数据
		// 要对接收的数据进行转码，转成UTF—8
		// 姓名
		String name = detailName;
		// 性别
		String sex = detailSex;
		// 身份证
		String idCard = detailIdCard;
		// 大修id  这个没用到
		String bigid = detailBigId;
		// 所属单位
		String unitName = detailUnitName;
		// 员工id employeeid
		String employeeId = detailEmployeeId;
		// 大修外来职工id
		String BigEmployeeoutId = detailBigEmployeeoutId;
		// 大修单位id 没用到这个
		String unitBigHual = detailUnitBigHual;

		// 2.根据大修id和职工id计算出总积分
		Map<String, Object> mapSum = new LinkedHashMap<String, Object>();
		mapSum.put("employeeid", employeeId);// 职工id
		mapSum.put("bigemployeeoutid", BigEmployeeoutId);// 大修员工id
		/**S  QLQ**/
		if (ValidateCheck.isNotNull(fstarttime)) {
			mapSum.put("fstarttime", fstarttime);
		}
		if (ValidateCheck.isNotNull(fendtime)) {
			mapSum.put("fendtime", fendtime);
		}
		//增加违章记录类型条件
		mapSum = generateConditionForbreakInfoType(mapSum);
		/***S QLQ封装查询条件*****/
		Integer sumBreakScore = breakrulesService.selSumBreakScoreByEmpId(mapSum);
		if (sumBreakScore == null) {
			sumBreakScore = 0;
		}
		int sumBreakGrade = sumBreakScore;

		/*
		 * //黑名单状态 String isBreak = ""; if(sumBreakScore!=null){
		 * if(sumBreakGrade>=12){ isBreak = "是"; } }else{ if(sumBreakGrade<12){
		 * isBreak ="否"; } }
		 */

		// 3.根据员工id去外来职工表中获取headaddress，也就是该职工的照片
		EmployeeOut employeeoutBean = breakrulesService.selEmployeeOutByEmployeeId(employeeId);
		
		// 4、获取headAddress也就是头像的地址
		String headaddress = employeeoutBean.getHeadaddress();

		// 5、根据BigEmployeeoutId、职工id employeeid 去违章表中查询该大修下该职工的所有违章记录
		Map<String, Object> mapbr = new LinkedHashMap<String, Object>();
		mapbr.put("bigemployeeoutid", BigEmployeeoutId);// 参加大修员工编号
		mapbr.put("employeeid", employeeId);// 职工编号
		/**S  QLQ**/
		if (ValidateCheck.isNotNull(fstarttime)) {
			mapbr.put("fstarttime", fstarttime);
		}
		if (ValidateCheck.isNotNull(fendtime)) {
			mapbr.put("fendtime", fendtime);
		}
		/***S QLQ封装查询条件*****/
		//增加违章记录类型条件
		mapbr = generateConditionForbreakInfoType(mapbr);
		List<Breakrules> breakruleList = breakrulesService.selBreakRulesByEmployeeId(mapbr);

		// 根据外来职工id获取黑名单状态
		String blackStatus = breakrulesService.getBlackStatusByEmpoutId(employeeId);

		
		// 将数据保存在request域转发给显示详情的页面
		request.setAttribute("name", name);// 姓名
		request.setAttribute("sex", sex);// 性别
		request.setAttribute("idCard", idCard);// 身份证
		request.setAttribute("bigid", bigid);// 大修id 没用到
		request.setAttribute("unitName", unitName);// 所属单位
		request.setAttribute("breakSumScore", sumBreakGrade);// 违章总积分
		request.setAttribute("blackStatus", blackStatus);// 黑名单状态
		request.setAttribute("employeeId", employeeId);// 职工id 待会儿要用，修改和删除的时候
		request.setAttribute("headaddress", headaddress);// 员工的头像路径
		request.setAttribute("BigEmployeeoutId", BigEmployeeoutId);// 大修外来职工id
																	// 待会儿要用，修改和删除的时候
		request.setAttribute("unitBigHual", unitBigHual);// 大修单位id 没用到
		// request.setAttribute("isBreak", isBreak);//黑名单状态
		request.setAttribute("breakruleList", breakruleList);// 违章信息，一个list集合

		return "detailOp";// 跳转到查看详情的页面

	}

	/**
	 * 添加违章记录 另外要实现的功能: 1、就是当当前员工的违章总积分》12的时候应该把该员工信息添加到黑名单中 2、修改对应的大修单位表中的总积分
	 * 
	 * 不是永久状态的才能添加
	 * @return
	 * @throws Exception
	 */
	public String addBreakrule() throws Exception {
		// 是计划要转成json的map集合
		map = new LinkedHashMap<String, Object>();

		
		// 大修id bigid 职工id

		// 0.计算出该外部员工原来的违章记分
		Map<String, Object> mapSumBefore = new LinkedHashMap<String, Object>();
		mapSumBefore.put("employeeid", breakrules.getEmployeeid());// 职工id
		mapSumBefore.put("bigemployeeoutid", breakrules.getBigemployeeoutid());// 大修员工id
		Integer sumBreakScoreBefore = breakrulesService.selSumBreakScoreByEmpId(breakrules.getEmployeeid());// 违章记分
		if (sumBreakScoreBefore == null) {
			sumBreakScoreBefore = 0;
		}
		
		//888
		//如果原来违章记分<12,也就是没进黑名单的
		if(sumBreakScoreBefore<12){
			// 1.将违章信息添加到违章表中
			int addResult = breakrulesService.addBreakrules(breakrules);
			
			// 2.计算出违章信息添加到违章表之后该大修下的该职工的违章总积分（添加后的违章记分）当前
			Map<String, Object> mapSum = new LinkedHashMap<String, Object>();
			mapSum.put("employeeid", breakrules.getEmployeeid());// 职工id
			mapSum.put("bigemployeeoutid", breakrules.getBigemployeeoutid());// 大修员工id
			Integer sumBreakScore = breakrulesService.selSumBreakScoreByEmpId(breakrules.getEmployeeid());
			//本次违章记分>=12,
			if((sumBreakScore-sumBreakScoreBefore)>=12){//加入黑名单，永久状态
				// 添加到黑名单
				// 根据职工id获取外来职工表中的信息
				EmployeeOut employeeoutBean = breakrulesService.selEmployeeOutByEmployeeId(breakrules.getEmployeeid());
				Blacklist blacklist = new Blacklist();
				blacklist.setBlackidcard(employeeoutBean.getIdcode());// 设置身份证
				blacklist.setDescription("该职工被临时列入黑名单");// 描述
				blacklist.setEmployeeid(breakrules.getEmployeeid());// 员工id
				blacklist.setTime(new Date());// 加入黑名单的时间
				blacklist.setTemporaryinstatus("1");// 永久状态
				blacklist.setEmployeestatus("1");// 表示外部员工表中的员工
				// 将黑名单信息保存在黑名单中
				int addBlackListRes = breakrulesService.addBlackList(blacklist);
				if (addResult > 0) {
					map.put("result", "添加成功");
				} else {
					map.put("result", "添加失败，请重试");
				}
			}else if((sumBreakScore-sumBreakScoreBefore)<12){//本次违章记分小于12分情况，
				//若当前违章总积分》=12，加入黑名单，临时状态。若当前违章总积分《12，则不对黑名单操作
				if(sumBreakScore>=12){
					//加入黑名单，临时状态
					// 添加到黑名单
					// 根据职工id获取外来职工表中的信息
					EmployeeOut employeeoutBean = breakrulesService.selEmployeeOutByEmployeeId(breakrules.getEmployeeid());
					Blacklist blacklist = new Blacklist();
					blacklist.setBlackidcard(employeeoutBean.getIdcode());// 设置身份证
					blacklist.setDescription("该职工被临时列入黑名单");// 描述
					blacklist.setEmployeeid(breakrules.getEmployeeid());// 员工id
					blacklist.setTime(new Date());// 加入黑名单的时间
					blacklist.setTemporaryinstatus("0");// 临时状态
					blacklist.setEmployeestatus("1");// 表示外部员工表中的员工
					// 将黑名单信息保存在黑名单中
					int addBlackListRes = breakrulesService.addBlackList(blacklist);
					if (addResult > 0) {
						map.put("result", "添加成功");
					} else {
						map.put("result", "添加失败，请重试");
					}
				}else{//不对黑名单操作
					if (addResult > 0) {
						map.put("result", "添加成功");
					} else {
						map.put("result", "添加失败，请重试");
					}
				}
			}
		}else if(sumBreakScoreBefore>=12){//在黑名单，临时状态
			// 1.将违章信息添加到违章表中
			int addResult = breakrulesService.addBreakrules(breakrules);
			// 2.计算出违章信息添加到违章表之后该大修下的该职工的违章总积分（添加后的违章记分）当前
			Map<String, Object> mapSum = new LinkedHashMap<String, Object>();
			mapSum.put("employeeid", breakrules.getEmployeeid());// 职工id
			mapSum.put("bigemployeeoutid", breakrules.getBigemployeeoutid());// 大修员工id
			Integer sumBreakScore = breakrulesService.selSumBreakScoreByEmpId(breakrules.getEmployeeid());
			
			//本次违章记分>=12
			if((sumBreakScore-sumBreakScoreBefore)>=12){//将黑名单状态设置为永久状态，只更新黑名单状态
				Blacklist selBlackListByEmpId3 = breakrulesService.selBlackListByEmpId(breakrules.getEmployeeid());
				selBlackListByEmpId3.setTemporaryinstatus("1");
				selBlackListByEmpId3.setDescription("该员工被永久进入黑名单");
				// 更新黑名单信息
				int updateBalcklistRes = breakrulesService.updateBalcklist(selBlackListByEmpId3);
				if (addResult > 0) {
					map.put("result", "添加成功");
				} else {
					map.put("result", "添加失败，请重试");
				}
			}else if((sumBreakScore-sumBreakScoreBefore)<12){//已经在黑名单了，本次违章记分小于12分，不对黑名单进行操作
				if (addResult > 0) {
					map.put("result", "添加成功");
				} else {
					map.put("result", "添加失败，请重试");
				}
			}
			
		}
		// 6.返回信息给用户
		
		return "ok";
	}

	/**
	 * 与左侧的树绑定之后再根据条件进行分页查询
	 * 
	 * @return
	 * @throws Exception
	 */
	private String fstarttime;// 违章时间的开始时间
	private String fendtime;// 违章时间的结束时间
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

	public String findMsgByCondictionFy() throws Exception {
		// 实例化要转成json的map集合
		map = new LinkedHashMap<String, Object>();
		// 部门id(unitid) \姓名、省份证、违章记分、性别、黑名单,当前页页号，每页显示的记录数
		// 单位大修编号
		String unitHaulUnit = funitBigHaulBH;
		// 部门id(单位编号)
		String unitid = funitId;
		// 姓名funitId
		String name = fName;
		// 身份证
		String idcard = fIdCard;
		// 违章记分
		String breakscoreF = fBreakScore;

		// 性别
		String sex = "";
		if (fSex == null) {
			sex = "";
		} else {
			sex = fSex;
		}

		// 是否是黑名单 暂时先不处理黑名单
		String isBreak = fIsBreak;
		// 当前页页号
		String curPage = fcurpage;
		// 每页显示的记录数
		String curTotal = fcurtotal;

		int curpage = (Integer.parseInt(curPage) - 1) * Integer.parseInt(curTotal);
		int cuttotal = Integer.parseInt(curTotal);

		// 如果isBreak==null 则说明是没有点击黑名单的情况
		if ((isBreak == null) || (isBreak.equals("否")) || (isBreak.equals("是"))) {
			// =====》》》》》====》》》》没有点击黑名单的情况 start
			if (isBreak != null) {
				if (isBreak.equals("否")) {// 黑名单状态为否的情况
					// 执行"否"的条件查询
					Map<String, Object> mapF1 = new LinkedHashMap<String, Object>();
					if(ValidateCheck.isNotNull(fendtime)){
						mapF1.put("fendtime", fendtime);// 单位大修编号
					}
					if(ValidateCheck.isNotNull(fstarttime)){
						mapF1.put("fstarttime", fstarttime);// 单位大修编号
					}
					mapF1.put("unitbigid", unitHaulUnit);// 单位大修编号
					mapF1.put("unitid", unitid);// 单位编号(部门编号)
					mapF1.put("name", name);// 职工姓名
					mapF1.put("idcode", idcard);// 身份证
					mapF1.put("sex", sex);// 性别
					mapF1.put("curPage", curpage);// （当前页页号-1）*每页显示的记录数 每页显示的记录数
					mapF1.put("curTotal", cuttotal);// 每页显示的记录数
					
					//增加违章记录类型条件
					mapF1 = generateConditionForbreakInfoType(mapF1);
					
					List<Map<String, Object>> selectMsgByFyCondition = breakrulesService.blackStatusFalse(mapF1);

					// 总条数数
					Map<String, Object> mapF5 = new LinkedHashMap<String, Object>();
					mapF5.put("unitbigid", unitHaulUnit);// 单位大修编号
					mapF5.put("unitid", unitid);// 单位编号(部门编号)
					mapF5.put("name", name);// 职工姓名
					mapF5.put("idcode", idcard);// 身份证
					mapF5.put("sex", sex);// 性别
					int count = breakrulesService.blackStatusFalseCount(mapF5);
					if (selectMsgByFyCondition != null) {
						// 封装要显示的数据
						map.put("selectLeftToTable", selectMsgByFyCondition);
						// 封装总记录数
						map.put("count", count);
						// 当前页页号
						map.put("curPage", Integer.parseInt(curPage));
						// 每页显示的记录数
						map.put("curTotal", Integer.parseInt(curTotal));
						// 封装一个是否点击了黑名单的标记
						map.put("isBreak", "no");
					} // 第三个if的括号
					return "ok";
				} else if (isBreak.equals("是")) {
					
					// 黑名单状态为"是"的情况，
					// str = breakscoreF;
					// 黑名单状态为"是"的情况
					// 查询点击了黑名单之后当前页要显示的数据
					Map<String, Object> mapblack = new LinkedHashMap<String, Object>();
					if(ValidateCheck.isNotNull(fendtime)){
						mapblack.put("fendtime", fendtime);// 单位大修编号
					}
					if(ValidateCheck.isNotNull(fstarttime)){
						mapblack.put("fstarttime", fstarttime);// 单位大修编号
					}
					mapblack.put("name", name);// 姓名
					mapblack.put("idcode", idcard);// 身份证
					mapblack.put("sex", sex);// 性别
					mapblack.put("unitbigid", unitHaulUnit);// 大修单位编号
					mapblack.put("unitid", unitid);// 单位编号(部门编号)
					mapblack.put("curPage", curpage);// 当前页页号
					mapblack.put("curTotal", Integer.parseInt(curTotal));// 每页显示的记录数
					
					//增加违章记录类型条件
					mapblack = generateConditionForbreakInfoType(mapblack);				
					
					List<Map<String, Object>> selLeftTreeAndConditionBlack = breakrulesService
							.selLeftTreeAndConditionBlack(mapblack);
					
					// 查询总记录数
					Map<String, Object> mapblackCount = new LinkedHashMap<String, Object>();

					mapblackCount.put("name", name);// 姓名
					mapblackCount.put("idcode", idcard);// 身份证
					mapblackCount.put("sex", sex);// 性别
					mapblackCount.put("unitbigid", unitHaulUnit);// 大修单位编号
					mapblackCount.put("unitid", unitid);// 单位编号(部门编号)

					
					int count = breakrulesService.selLeftTreeAndConditionBlackCount(mapblackCount);
					

					if (selLeftTreeAndConditionBlack != null) {
						// 封装要显示的数据
						map.put("selectLeftToTable", selLeftTreeAndConditionBlack);
						// 封装总记录数
						map.put("count", count);
						// 当前页页号
						map.put("curPage", curPage);
						// 每页显示的记录数
						map.put("curTotal", curTotal);
						// 封装一个是否点击了黑名单的标记
						map.put("isBreak", "yes");
					} // if的括号
				}
			} else {
				// 没有点击黑名单的情况

				// 违章记分的区间
				String str = breakscoreF;// "1,1000";

				String[] split = str.split(",");
				int startminusNum = Integer.parseInt(split[0]);
				int endminusNum = Integer.parseInt(split[1]);
				
				Map<String, Object> map6 = new LinkedHashMap<String, Object>();
				map6.put("unitbigid", unitHaulUnit);// 单位大修编号
				if(ValidateCheck.isNotNull(fendtime)){
					map6.put("fendtime", fendtime);// 单位大修编号
				}
				if(ValidateCheck.isNotNull(fstarttime)){
					map6.put("fstarttime", fstarttime);// 单位大修编号
				}
				map6.put("unitid", unitid);// 单位编号(部门编号)
				map6.put("name", name);// 职工姓名
				map6.put("idcode", idcard);// 身份证
				map6.put("minusnumstart", startminusNum);// 违章记分的区间 开始
				map6.put("minusnumend", endminusNum);// 违章记分的区间 结束
				map6.put("sex", sex);// 性别
				map6.put("curPage", curpage);// （当前页页号-1）*每页显示的记录数 每页显示的记录数
				map6.put("curTotal", cuttotal);// 每页显示的记录数
				
				//增加违章记录类型条件
				map6 = generateConditionForbreakInfoType(map6);
							
				List<Map<String, Object>> selectMsgByFyCondition = breakrulesService.selectMsgByFyCondition(map6);

				// 查询总条数
				Map<String, Object> mapCount = new LinkedHashMap<String, Object>();
				mapCount.put("unitbigid", unitHaulUnit);// 单位大修编号
				mapCount.put("unitid", unitid);// 单位编号(部门编号)
				mapCount.put("name", name);// 职工姓名
				mapCount.put("idcode", idcard);// 身份证
				mapCount.put("minusnumstart", startminusNum);// 违章记分的区间 开始
				mapCount.put("minusnumend", endminusNum);// 违章记分的区间 结束
				mapCount.put("sex", sex);// 性别

				int count = breakrulesService.selectMsgByFyConditionCount(mapCount);

				if (selectMsgByFyCondition != null) {
					// 封装要显示的数据
					map.put("selectLeftToTable", selectMsgByFyCondition);
					// 封装总记录数
					map.put("count", count);
					// 当前页页号
					map.put("curPage", Integer.parseInt(curPage));
					// 每页显示的记录数
					map.put("curTotal", Integer.parseInt(curTotal));
					// 封装一个是否点击了黑名单的标记
					map.put("isBreak", "no");
				} // if的括号
					// =====》》》》》====》》》》没有点击黑名单的情况 end

			} // else的括号
				
		} // 第一个if的括号
		return "ok";
	}// 方法的括号

	/**
	 * 根据参加大修的外来职工表的 参加大修员工编号和大修id（bigid）去违章表中获取该大修下的该职工的违章总积分
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findSumScoreByEmployeeId() throws Exception {
		// 实例化要转成json的map集合
		map = new LinkedHashMap<String, Object>();

		// 接收从jsp传过来的职工id
		String findEmpId = ServletActionContext.getRequest().getParameter("findEmpId");
		// 根据职工id去违章表中查询该职工的违章总积分
		int sumBreakScore = 0;// breakrulesService.selSumBreakScoreByEmpId(findEmpId);
		if (sumBreakScore >= 12) {
			map.put("result", "是");
		} else {
			map.put("result", "否");
		}

		return "ok";
	}

	/**
	 * 初始化页面的数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String initPage() throws Exception {
		// 初始化要转成json的map集合
		map = new LinkedHashMap<String, Object>();
		// 接收jsp页面传过来的数据
		HttpServletRequest request = ServletActionContext.getRequest();
		String curPage = request.getParameter("curPage");// 当前页页号
		String curTotal = request.getParameter("curTotal");// 每页显示的记录数
		Map<String, Object> mapInit = new LinkedHashMap<String, Object>();
		mapInit.put("curPage", (Integer.parseInt(curPage) - 1) * (Integer.parseInt(curTotal)));// (当前页页号-1)*每页显示的记录数
		mapInit.put("curTotal", Integer.parseInt(curTotal));// 每页显示的记录数
		//增加违章记录类型条件
		mapInit = generateConditionForbreakInfoType(mapInit);
		List<Map<String, Object>> selectLeftToTable = breakrulesService.initPage(mapInit);
		// 查询记录总条数
		int count = breakrulesService.initPageCount();

		if (selectLeftToTable != null) {
			// 封装要显示的数据
			map.put("selectLeftToTable", selectLeftToTable);
			// 封装总记录数
			map.put("count", count);
			// 当前页页号
			map.put("curPage", curPage);
			// 每页显示的记录数
			map.put("curTotal", curTotal);
		}

		return "ok";
	}
	
	
	
	/**
	 * 根据大修id获取大修名称
	 */
	public String getHaulInfoNameByBigId() {
		//实例化要转成json的map集合
		map = new LinkedHashMap<String,Object>();
		
		//接收从jsp传过来的数据
		String haulInfoBigId = ServletActionContext.getRequest().getParameter("bigid");
		
		String haulInfoName = breakrulesService.selHaulInfoNameByBigId(haulInfoBigId);
		
		map.put("haulInfoName", haulInfoName);
		
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
		return condition;
	}
	
}
