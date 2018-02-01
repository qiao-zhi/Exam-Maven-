package cn.xm.exam.action.employee.in;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.common.Dictionary;
import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.service.common.DictionaryService;
import cn.xm.exam.service.employee.in.DepartmentService;
import cn.xm.exam.service.employee.in.EmployeeInService;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.utils.BSASE64;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ResourcesUtil;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;

@SuppressWarnings("all")
public class EmployeeInAction extends ActionSupport {

	private File fileName;
	private String fileNameContentType;
	private String fileNameFileName;

	public File getFileName() {
		return fileName;
	}

	public void setFileName(File fileName) {
		this.fileName = fileName;
	}

	public String getFileNameContentType() {
		return fileNameContentType;
	}

	public void setFileNameContentType(String fileNameContentType) {
		this.fileNameContentType = fileNameContentType;
	}

	public String getFileNameFileName() {
		return fileNameFileName;
	}

	public void setFileNameFileName(String fileNameFileName) {
		this.fileNameFileName = fileNameFileName;
	}

	// 员工培训档案
	@Autowired
	private ExamService examService;

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	// 输入的身份证
	private String myIdcode;

	public String getMyIdcode() {
		return myIdcode;
	}

	public void setMyIdcode(String myIdcode) {
		this.myIdcode = myIdcode;
	}

	// 字典id
	private String dictionaryid;

	public String getDictionaryid() {
		return dictionaryid;
	}

	public void setDictionaryid(String dictionaryid) {
		this.dictionaryid = dictionaryid;
	}

	//

	// 批量导入内部员工集合
	private List<EmployeeIn> employeeInList;

	public List<EmployeeIn> getEmployeeInList() {
		return employeeInList;
	}

	public void setEmployeeInList(List<EmployeeIn> employeeInList) {
		this.employeeInList = employeeInList;
	}

	// 添加员工的图片路径
	private String photoStr;

	private String employeeInIdCard;

	public String getPhotoStr() {
		return photoStr;
	}

	public void setPhotoStr(String photoStr) {
		this.photoStr = photoStr;
	}

	public String getEmployeeInIdCard() {
		return employeeInIdCard;
	}

	public void setEmployeeInIdCard(String employeeInIdCard) {
		this.employeeInIdCard = employeeInIdCard;
	}

	// 培训档案的身份证id
	private String tranDocid;

	public String getTranDocid() {
		return tranDocid;
	}

	public void setTranDocid(String tranDocid) {
		this.tranDocid = tranDocid;
	}

	// 培训档案
	private Map<String, Object> condition2;

	public Map<String, Object> getCondition2() {
		return condition2;
	}

	public void setCondition2(Map<String, Object> condition2) {
		this.condition2 = condition2;
	}

	// 判断是否是内部部门
	private String yincangbumenid;

	public String getYincangbumenid() {
		return yincangbumenid;
	}

	public void setYincangbumenid(String yincangbumenid) {
		this.yincangbumenid = yincangbumenid;
	}

	// 得到部门下的所有员工信息
	private String empbumen;

	public String getEmpbumen() {
		return empbumen;
	}

	public void setEmpbumen(String empbumen) {
		this.empbumen = empbumen;
	}

	// 批量导入外来单位员工的基本信息
	public String addEmployeeInBatch() {
		result = new HashMap<String, Object>();

		// 批量导入外来单位员工的基本信息
		boolean flag = false;
		try {
			flag = employeeInService.addEmployeeInBatch(employeeInList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (flag) {
			result.put("flag", "添加成功！");
		} else {
			result.put("flag", "添加失败！");
		}

		return SUCCESS;
	}

	/*
	 * 判断数据库是否存在这个身份证
	 */
	public String isIdCode() {
		result = new HashMap<String, Object>();
		boolean flag = employeeInService.isIdCode(myIdcode);
		result.put("flag", flag);
		return SUCCESS;
	}

	/*
	 * 判断该员工是否在黑名单中
	 */
	public String isBlackList() {
		result = new HashMap<String, Object>();
		String temporaryInStatus = employeeInService.isBlackList(myIdcode);
		if (temporaryInStatus == null) {
			result.put("flag", "0");
		} else if (temporaryInStatus.equals("1")) {
			result.put("flag", "1");
		} else if (temporaryInStatus.equals("0")) {
			result.put("flag", "2");
		}

		return SUCCESS;
	}

	/*
	 * 查询所有的职务
	 */
	public String getDuty() throws Exception {

		result = new HashMap<String, Object>();
		List<Dictionary> dictionarys = dictionaryService.getDictionaryByUpDicId(dictionaryid);
		result.put("dictionarys", dictionarys);
		return SUCCESS;
	}

	// 判断是否是内部部门
	public String isNeibu() {
		result = new HashMap<String, Object>();
		boolean flag = employeeInService.isNeibu(yincangbumenid);
		result.put("flag", flag);
		return SUCCESS;
	}

	/*
	 * 显示员工信息
	 */
	public String getEmpCase() {
		Map<String, Object> condition3 = new HashMap<String, Object>();
		result = new HashMap<String, Object>();
		condition3 = generateCondition3(condition3);
		PageBean<Map<String, Object>> pageBean = employeeInService.getEmpCase(condition3);
		result.put("pageBean", pageBean);
		return SUCCESS;
	}

	/*
	 * 将条件判断之后放到一个集合中
	 */
	private Map<String, Object> generateCondition3(Map<String, Object> condition3) {
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
			condition3.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
			condition3.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}

		if (ValidateCheck.isNotNull(currentCount)) {
			condition3.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}
		if (ValidateCheck.isNotNull(currentPage)) {
			condition3.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}
		if (ValidateCheck.isNotNull(empbumen)) {
			condition3.put("empbumen", empbumen);
			result.put("empbumen", empbumen);
		}

		return condition3;
	}

	/*
	 * 跳转到修改员工页面
	 */
	public String toUpdateEmployeeIn() throws Exception {
		result = new HashMap<String, Object>();
		HttpServletRequest request = ServletActionContext.getRequest();
		String myEmployeeId = request.getParameter("method");
		EmployeeIn myemployeeIn = employeeInService.getEmployeeInById(myEmployeeId);
		result.put("employeeIn", myemployeeIn);

		String departmentName = departmentService.getDepartmentById(myemployeeIn.getDepartmentid()).getDepartmentname();

		/*
		 * if(employeeIn.getPhoto()!=null){ result.put("photo",
		 * employeeIn.getPhoto()); }
		 */
		result.put("departmentName", departmentName);
		return "modifyEmployee";

	}

	public String saveEmployeePhoto() throws Exception {
		// 得到图片的信息

		// 1.保存图片
		// 2.修改名字
		// 3修改员工属性
		ServletContext servletContext = ServletActionContext.getServletContext();
		// 新名字

		String name = employeeInIdCard + ".jpg";
		String dir = ResourcesUtil.getValue("path", "photo") + name;

		BSASE64.generateImage(photoStr, dir);

		return NONE;
	}

	public boolean addPhoto() throws IOException {
		// 2.修改名字
		// 3修改员工属性
		ServletContext servletContext = ServletActionContext.getServletContext();
		// 新名字
		if (photoFileName != null) {

			String name = UUIDUtil.getUUID2() + photoFileName.substring(photoFileName.lastIndexOf("."));

			String dir = ResourcesUtil.getValue("path", "photo") + name;
			FileOutputStream outputStream = new FileOutputStream(dir);
			FileInputStream inputStream = new FileInputStream(photo);
			byte[] buffer = new byte[1024];
			int len = 0;
			// 从此输入流中将最多 b.length 个字节的数据读入一个 byte
			// 数组中.读入缓冲区的字节总数，如果因为已经到达文件末尾而没有更多的数据，则返回 -1。
			while ((len = inputStream.read(buffer)) != -1) {
				// 将指定 byte 数组中从偏移量 off 开始的 len 个字节写入此文件输出流。
				outputStream.write(buffer, 0, len);
			}
			inputStream.close();
			outputStream.close();
			// 修改员工照片属性:
			if (employeeIn != null) {
				employeeIn.setPhoto(name);
			}

		}
		return true;
	}

	/**
	 * 查看员工培训档案
	 */
	public String getEmpTrainDoc() throws Exception {
		Map<String, Object> condition2 = new HashMap<String, Object>();
		result = new HashMap<String, Object>();
		condition2 = generateCondition2(condition2);
		PageBean<Map<String, Object>> pageBean = examService.getExamInfoByCondition(condition2);
		// List<Map<String, Object>> myexam =
		// examService.getExamInfoByEmployeeId(employeeId);

		result.put("pageBean", pageBean);

		return SUCCESS;
	}

	/*
	 * 将条件判断之后放到一个集合中
	 */
	private Map<String, Object> generateCondition2(Map<String, Object> condition2) {
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
			condition2.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
			condition2.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}

		if (ValidateCheck.isNotNull(currentCount)) {
			condition2.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}
		if (ValidateCheck.isNotNull(currentPage)) {
			condition2.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}
		if (ValidateCheck.isNotNull(hiddenidcode)) {
			condition2.put("hiddenidcode", hiddenidcode);
			result.put("hiddenidcode", hiddenidcode);
		}
		if (ValidateCheck.isNotNull(empbumen)) {
			condition2.put("empbumen", empbumen);
			result.put("empbumen", empbumen);
		}
		
		return condition2;
	}

	/*
	 * 修改内部员工
	 */

	public String updateEmployeeIn() throws Exception {

		// 修改数据库
		result = new HashMap<String, Object>();
		boolean flag = employeeInService.updateEmployeeIn(employeeIn);
		if (flag) {
			result.put("message", "修改成功");
		} else {
			result.put("message", "修改失败");
		}

		return "innerdepartEmpManage";

	}

	/*
	 * 删除内部员工
	 */
	public String deleteEmployeeIn() throws Exception {
		boolean flag2 = true;

		// 删除原有的图片
		ServletContext servletContext = ServletActionContext.getServletContext();

		EmployeeIn MyemployeeIn = employeeInService.getEmployeeInById(employeeId);

		String photopath = MyemployeeIn.getPhoto();

		File file = new File(ResourcesUtil.getValue("path", "photo") + photopath);
		flag2 = file.delete();

		// 删除数据库的数据
		result = new HashMap<String, Object>();

		boolean flag = employeeInService.deleteEmployeeInById(employeeId);

		// 判断
		if (flag) {
			result.put("message", "删除成功");
		} else {
			result.put("message", "删除失败");
		}

		return SUCCESS;

	}

	/*
	 * 按条件查询所有的员工
	 */
	public String findEmployeeIn() {

		Map<String, Object> condition = new HashMap<String, Object>();
		// result是用来返回到jsp中
		result = new HashMap<String, Object>();
		// condition是用来充当查询的条件
		condition = generateCondition(condition);
		try {
			PageBean<EmployeeIn> pageBean = employeeInService.findEmployeeInWithCondition(condition);

			result.put("pageBean", pageBean);

			List<EmployeeIn> employeeIn = pageBean.getProductList();

			// 得到部门名称List
			List<String> departmentnames = new LinkedList<String>();

			List<EmployeeIn> employeeIns = pageBean.getProductList();
			for (int i = 0; i < employeeIns.size(); i++) {
				if (employeeIns.get(i).getDepartmentid() != null) {
					String departmentname = departmentService.getDepartmentById(employeeIns.get(i).getDepartmentid())
							.getDepartmentname();
					departmentnames.add(departmentname);
				} else {
					departmentnames.add("无");
				}
			}
			result.put("departmentnameList", departmentnames);

			// 培训状态
			List<String> trainstatus = new LinkedList<String>();
			for (int i = 0; i < employeeIn.size(); i++) {

				if (employeeIn.get(i).getTrainstatus() != null) {
					int status = employeeIn.get(i).getTrainstatus();

					if (status == 1) {
						trainstatus.add("通过一级考试");

					} else if (status == 2) {
						trainstatus.add("通过二级考试");
					} else if (status == 3) {
						trainstatus.add("通过三级考试");
					} else if (status == 0) {
						trainstatus.add("未通过");
					}
				} else {
					trainstatus.add("未参加培训");
				}
			}

			result.put("trainstatusList", trainstatus);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	/*
	 * 将条件判断之后放到一个集合中
	 */
	private Map<String, Object> generateCondition(Map<String, Object> condition) {
		/** S QLQ 范围管理 */
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		// 获取用户信息
		Subject currentUser = SecurityUtils.getSubject();
		boolean permitted = currentUser.isPermitted("departmentmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		if (ValidateCheck.isNotNull(departmentId)) {
			condition.put("departmentId", departmentId);
		}
		/** S QLQ 范围管理 */
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
		if (ValidateCheck.isNotNull(currentCount)) {
			condition.put("currentCount", currentCount);
			result.put("currentCount", currentCount);
		}
		if (ValidateCheck.isNotNull(currentPage)) {
			condition.put("currentPage", currentPage);
			result.put("currentPage", currentPage);
		}
		if (ValidateCheck.isNotNull(name)) {
			condition.put("name", name);
			result.put("name", name);
		}
		if (ValidateCheck.isNotNull(sex)) {
			condition.put("sex", sex);
			result.put("sex", sex);
		}

		if (ValidateCheck.isNotNull(idcode)) {
			condition.put("idcode", idcode);
			result.put("idcode", idcode);
		}

		if (ValidateCheck.isNotNull(trainstatus)) {
			condition.put("trainstatus", trainstatus);
			result.put("trainstatus", trainstatus);
		}
		if (ValidateCheck.isNotNull(departmentid)) {
			condition.put("departmentid", departmentid);
			result.put("departmentid", departmentid);
		}
		
		if (ValidateCheck.isNotNull(empbumen)) {
			condition.put("empbumen", empbumen);
			result.put("empbumen", empbumen);
		}
		
		if (ValidateCheck.isNotNull(isOnlyManager)) {
			condition.put("isOnlyManager", isOnlyManager);
			result.put("isOnlyManager", isOnlyManager);
		}
		
		return condition;
	}

	// 注入字典service
	@Autowired
	private DictionaryService dictionaryService;

	public DictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(DictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getPhotoFileName() {
		return photoFileName;
	}

	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}

	public String getPhotoContenType() {
		return photoContenType;
	}

	public void setPhotoContenType(String photoContenType) {
		this.photoContenType = photoContenType;
	}

	private File photo;
	private String photoFileName;

	// 将名字UUID产生新名字，然后写到文件夹下，保存UUID名字
	private String photoContenType;
	// 注入的service层对象
	@Autowired
	private EmployeeInService employeeInService;

	public EmployeeInService getEmployeeInService() {
		return employeeInService;
	}

	public void setEmployeeInService(EmployeeInService employeeInService) {
		this.employeeInService = employeeInService;
	}

	@Autowired
	private DepartmentService departmentService;

	public DepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	private EmployeeIn employeeIn;

	public EmployeeIn getEmployeeIn() {
		return employeeIn;
	}

	public void setEmployeeIn(EmployeeIn employeeIn) {
		this.employeeIn = employeeIn;
	}

	// 将action中得到的值返回到jsp中
	private Map<String, Object> result;

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	private String hiddenidcode;

	public String getHiddenidcode() {
		return hiddenidcode;
	}

	public void setHiddenidcode(String hiddenidcode) {
		this.hiddenidcode = hiddenidcode;
	}

	// 查询需要的属性
	private String name;
	private String sex;
	private String currentPage;
	private String currentCount;
	private String idcode;
	private String trainstatus;
	private String departmentid;
	private String isOnlyManager;
	
	public String getIsOnlyManager() {
		return isOnlyManager;
	}

	public void setIsOnlyManager(String isOnlyManager) {
		this.isOnlyManager = isOnlyManager;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
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

	public String getIdcode() {
		return idcode;
	}

	public void setIdcode(String idcode) {
		this.idcode = idcode;
	}

	public String getTrainstatus() {
		return trainstatus;
	}

	public void setTrainstatus(String trainstatus) {
		this.trainstatus = trainstatus;
	}

	public String getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(String departmentid) {
		this.departmentid = departmentid;
	}

	// 删除id
	private String employeeId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	// 添加部门id
	private String Myadddepartmentid;

	public String getMyadddepartmentid() {
		return Myadddepartmentid;
	}

	public void setMyadddepartmentid(String myadddepartmentid) {
		Myadddepartmentid = myadddepartmentid;
	}

	// 判断是否是内部部门
	private String Mydepartmentid;

	public String getMydepartmentid() {
		return Mydepartmentid;
	}

	public void setMydepartmentid(String mydepartmentid) {
		Mydepartmentid = mydepartmentid;
	}

	// private String UUIDphoto = UUID.randomUUID().toString().replaceAll("-",
	// "")+photoFileName;

}
