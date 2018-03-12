package cn.xm.exam.service.impl.employee.in;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.employee.in.Department;
import cn.xm.exam.bean.employee.in.DepartmentExample;
import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrulesExample;
import cn.xm.exam.bean.exam.OnlineexamanswerinforExample;
import cn.xm.exam.bean.exam.OnlineexaminforExample;
import cn.xm.exam.bean.grade.EmployeeexamExample;
import cn.xm.exam.bean.question.Questionbank;
import cn.xm.exam.bean.question.QuestionbankExample;
import cn.xm.exam.mapper.employee.in.DepartmentMapper;
import cn.xm.exam.mapper.employee.in.EmployeeInMapper;
import cn.xm.exam.mapper.employee.in.EmplyinBreakrulesMapper;
import cn.xm.exam.mapper.employee.in.custom.DepartmentCustomMapper;
import cn.xm.exam.mapper.employee.in.custom.EmployeeInCustomMapper;
import cn.xm.exam.mapper.exam.OnlineexamanswerinforMapper;
import cn.xm.exam.mapper.exam.OnlineexaminforMapper;
import cn.xm.exam.mapper.grade.EmployeeexamMapper;
import cn.xm.exam.mapper.question.QuestionbankMapper;
import cn.xm.exam.mapper.system.UserMapper;
import cn.xm.exam.service.employee.in.DepartmentService;
import cn.xm.exam.service.question.QuestionbankService;
import cn.xm.exam.service.system.UserService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.ValidateCheck;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	/**** S zwy *****/
	@Autowired
	private DepartmentCustomMapper departmentCustomMapper;

	public DepartmentCustomMapper getDepartmentCustomMapper() {
		return departmentCustomMapper;
	}

	public void setDepartmentCustomMapper(DepartmentCustomMapper departmentCustomMapper) {
		this.departmentCustomMapper = departmentCustomMapper;
	}

	@Autowired
	private DepartmentMapper departmentMapper;

	public DepartmentMapper getDepartmentMapper() {
		return departmentMapper;
	}

	public void setDepartmentMapper(DepartmentMapper departmentMapper) {
		this.departmentMapper = departmentMapper;
	}

	@Autowired
	private EmployeeInMapper employeeInMapper;

	public EmployeeInMapper getEmployeeInMapper() {
		return employeeInMapper;
	}

	public void setEmployeeInMapper(EmployeeInMapper employeeInMapper) {
		this.employeeInMapper = employeeInMapper;
	}

	@Autowired
	private EmployeeInCustomMapper employeeInCustomMapper;

	public EmployeeInCustomMapper getEmployeeInCustomMapper() {
		return employeeInCustomMapper;
	}

	public void setEmployeeInCustomMapper(EmployeeInCustomMapper employeeInCustomMapper) {
		this.employeeInCustomMapper = employeeInCustomMapper;
	}

	@Override
	public String getNextDepartmentId(String upDepartmentId) throws Exception {

		// int
		// i=departmentCustomMapper.getDepartmentCountByUpId(upDepartmentId);
		String str = null;
		// 得到最大的下级部门编号
		String maxDepartmentId = departmentCustomMapper.getMaxDepartmentId(upDepartmentId);
		System.out.println("最大下级部门编号" + maxDepartmentId);

		// 将该编号转换为数字
		if (maxDepartmentId == null) {
			maxDepartmentId = upDepartmentId + "000";
		}
		System.out.println("之前的maxDepartmentId" + maxDepartmentId);
		// 将部门编号的后三位截下来
		String housanwei = maxDepartmentId.substring(maxDepartmentId.length() - 3, maxDepartmentId.length());

		// 将maxDepartmentId转换为StringBuffer类型
		StringBuffer sb = new StringBuffer(maxDepartmentId);
		System.out.println("sb" + sb);

		System.out.println("之后的maxDepartmentId" + maxDepartmentId);
		System.out.println("housanwei" + housanwei);
		int maxDepartmentIdNumber = Integer.parseInt(housanwei);
		// 将数字加1
		maxDepartmentIdNumber = maxDepartmentIdNumber + 1;

		// 将数字变为字符串
		str = String.format("%03d", maxDepartmentIdNumber);
		System.out.println("得到的编号的后三位" + str);

		// 将后三位拼接上去
		maxDepartmentId = sb.replace(maxDepartmentId.length() - 3, maxDepartmentId.length(), str).toString();
		System.out.println("拼接之后的" + maxDepartmentId);

		/*
		 * if ("0".equals(upDepartmentId)) { str = String.format("%02d",
		 * maxDepartmentIdNumber);
		 * 
		 * } else if (upDepartmentId.length() == 2) { str =
		 * String.format("%05d", maxDepartmentIdNumber);
		 * 
		 * System.out.println("得到的编号" + str);
		 * 
		 * } else if (upDepartmentId.length() == 5) { str =
		 * String.format("%08d", maxDepartmentIdNumber);
		 * 
		 * System.out.println("得到的编号" + str);
		 * 
		 * }
		 */
		return maxDepartmentId;
	}

	@Override
	public String addDepartment(Department department) throws Exception {
		String message = null;
		int flag;

		System.out.println("department" + department);
		if (department != null) {

			// 通过上级部门名称设置上级部门id
			/*
			 * String updepartmentname = department.getUpdepartmentid();
			 * 
			 * System.out.println(updepartmentname); String updepartmentid =
			 * getIdByDepartmentName(updepartmentname);
			 * 
			 * if (updepartmentid == null) { updepartmentid = "0";
			 * 
			 * } department.setUpdepartmentid(updepartmentid);
			 */
			// department.setUpdepartmentid("01001");
			// 给内部部门设置id
			String nextDepartmentId = getNextDepartmentId(department.getUpdepartmentid());
			department.setDepartmentid(nextDepartmentId);
			// flag = departmentMapper.insert(department);

			// 设置当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");
			department.setSort(sdf.format((new Date())));

			flag = departmentMapper.insertSelective(department);
			if (flag == 1) {
				return "添加成功";
			} else if (flag == 2) {
				return "添加失败";
			}

		} else {
			return "添加失败";
		}
		return "添加失败";
	}

	private String getIdByDepartmentName(String updepartmentname) {
		return departmentCustomMapper.getIdByDepartmentName(updepartmentname);
	}

	@Resource
	private EmployeeexamMapper employeeexamMapper;// 成绩Mapper
	@Resource
	private OnlineexamanswerinforMapper onlineexamanswerinforMapper;// 成绩Mapper
	@Resource
	private OnlineexaminforMapper onlineexaminforMapper;// 成绩Mapper
	@Resource
	private UserMapper userMapper;// 成绩Mapper
	@Resource
	private UserService userService;// 成绩Mapper
	@Resource
	private EmplyinBreakrulesMapper emplyinBreakrulesMapper;// 成绩Mapper

	@Resource
	private QuestionbankMapper questionBankMapper;// 题库的mapper
	@Resource
	private QuestionbankService questionbankService;// 题库的mapper

	public String deleteDepartmentById(String departmentId) throws Exception {
		String message = null;
		int flag;
		// Map<String,Object> mapMessage = new HashMap<String,Object>();
		if (departmentId == null) {
			return "删除失败";
		}
		int i = departmentCustomMapper.getDepartmentCountByUpId(departmentId);
		if (i != 0) {
			return "该部门还有下级部门，不能删除";
		}

		int count = employeeInCustomMapper.getCountByDepartmentId(departmentId);
		if (count != 0) {
			// message="该部门还有员工，确定删除吗";
			// mapMessage.put("1", count);

			// count不等于0,说明还有员工，就把他删了
			List<String> employeeids = employeeInCustomMapper.getALLEmployeeInByDepartmentId(departmentId);
			for (int j = 0; j < employeeids.size(); j++) {
				String employeeInId = employeeids.get(j);
				if (employeeInId != null) {
					// 0.获取身份证号与员工ID
					EmployeeIn employeein = employeeInCustomMapper.findEmployeeInById(employeeInId);
					String idCode = employeein.getIdcode();
					// 1 成绩 根据身份证号
					EmployeeexamExample employeeexamExample = new EmployeeexamExample();
					EmployeeexamExample.Criteria criteria = employeeexamExample.createCriteria();
					criteria.andEmployeeidEqualTo(idCode);
					criteria.andEmployeetypeEqualTo("0");// 删除内部考试的信息，保留(短委培训时的信息)
					employeeexamMapper.deleteByExample(employeeexamExample);
					// 2 在线考试信息 在线考试详细信息表 在线答题信息
					// 2.1删除在线答题信息
					OnlineexamanswerinforExample onlineexamanswerinforExample = new OnlineexamanswerinforExample();
					OnlineexamanswerinforExample.Criteria criteria_1 = onlineexamanswerinforExample.createCriteria();
					criteria_1.andEmployeeidEqualTo(idCode);
					onlineexamanswerinforMapper.deleteByExample(onlineexamanswerinforExample);
					// 2.2删除在线信息
					OnlineexaminforExample onlineexaminforExample = new OnlineexaminforExample();
					OnlineexaminforExample.Criteria criteria_2 = onlineexaminforExample.createCriteria();
					criteria_2.andEmployeeidEqualTo(idCode);
					onlineexaminforMapper.deleteByExample(onlineexaminforExample);

					// 3 违章信息 (根据员工ID)
					EmplyinBreakrulesExample emplyinBreakrulesExample = new EmplyinBreakrulesExample();
					EmplyinBreakrulesExample.Criteria criteria_3 = emplyinBreakrulesExample.createCriteria();
					criteria_3.andEmpinemployeeidEqualTo(employeeInId);
					emplyinBreakrulesMapper.deleteByExample(emplyinBreakrulesExample);
					// 4 用户信息(调用飞哥的删除用户信息)
					String userId = userMapper.getUseridByIdcard(idCode);
					if (ValidateCheck.isNotNull(userId)) {// 不为空的话证明存在账号就删除账号
						List<String> userids = new ArrayList<String>();
						userids.add(userId);
						userService.deleteUserById(userids);
					}
					// 5员工进入黑名单的记录(根据员工身份证编号删除)
					employeeInCustomMapper.deleteBlackrulesById(idCode);
					// 6 员工基本信息（根据员工编号删除）
					flag = employeeInMapper.deleteByPrimaryKey(employeeInId);
				}

			}
		}
		flag = departmentMapper.deleteByPrimaryKey(departmentId);
		return flag > 0 ? "删除成功" : "删除失败";
	}

	/***** S leilong *****/
	/**
	 * 修改部门信息 (如果移动了部门即修改了部门的上级部门ID需要修改相关的部门ID)
	 * 
	 * @param department
	 * 
	 * @return 是否修改成功
	 * @throws Exception
	 */
	@Override
	public boolean updateDepartment(Department department) throws Exception {
		int flag;
		// 判断部门信息是否为null
		if (department != null) {
			// 设置当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");
			department.setSort(sdf.format((new Date())));
			// 1、先查询出数据库中原有的部门信息
			Department OldDepartmentInfo = departmentMapper.selectByPrimaryKey(department.getDepartmentid());
			// 2、判断部门信息中的上级部门编号是否一致,若不一致需要修改相应的部门ID
			if (!OldDepartmentInfo.getUpdepartmentid().equals(department.getUpdepartmentid())) {
				// 3、根据参数中的上级部门编号查询新部门下的新增部门ID
				String newDepartmentId = getNextDepartmentId(department.getUpdepartmentid());
				// 4、将当前部门ID的编号修改成新部门下的部门编号,以及部门其他信息的修改
				department.setDepartmentid(newDepartmentId);
				// 先将原来的部门信息删除，然后将新的部门信息插入进去
				departmentMapper.deleteByPrimaryKey(OldDepartmentInfo.getDepartmentid());
				flag = departmentMapper.insert(department);
				// 5、调用存储过程，传入参数（原来的部门编号，修改后的部门编号）修改相关的部门ID
				// 封装条件
				Map<String, Object> condition = new HashMap<String, Object>();
				condition.put("oldDepartmentId", OldDepartmentInfo.getDepartmentid());
				condition.put("upDepartmentId", newDepartmentId);
				departmentCustomMapper.updateDepartmentIds(condition);
			} else {
				// 否则没有进行部门移动的操作，只修改部门的基本信息
				flag = departmentMapper.updateByPrimaryKeySelective(department);
			}

		} else {
			return false;
		}
		return flag == 1 ? true : false;
	}

	/*************** E leilong *****************/

	@Override
	public Department getDepartmentById(String departmentId) throws Exception {

		return departmentMapper.selectByPrimaryKey(departmentId);
	}

	// 通过部门名称得到部门id
	@Override
	public String getDepartmentIdByName(String departmentname) throws Exception {
		System.out.println("departmentname" + departmentname);
		return departmentCustomMapper.getIdByDepartmentName(departmentname);
	}

	@Override
	public List<Department> getDepartmentByUpDepartmentId(String upDepartmentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Map<String, Object>> findDepartmentsWithCondition(Map<String, Object> condition) throws Exception {
		// 目的：就是想办法封装一个PageBean 并返回
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();

		// 1、当前页private int currentPage;
		String currentPage = (String) condition.get("currentPage");
		int currentPageInt = Integer.valueOf(currentPage);
		pageBean.setCurrentPage(currentPageInt);

		// 2、当前页显示的条数private int currentCount;
		String currentCount = (String) condition.get("currentCount");
		int currentCountInt = Integer.valueOf(currentCount);
		pageBean.setCurrentCount(currentCountInt);

		// 3、总条数private int totalCount;
		int totalCount = 0;
		// 调用dao层的方法
		totalCount = departmentCustomMapper.getCountByCondition(condition);
		pageBean.setTotalCount(totalCount);
		// 4、总页数private int totalPage;
		/*
		 * 总条数 当前页显示的条数 总页数 10 4 3 11 4 3 12 4 3 13 4 4
		 * 
		 * 公式：总页数=Math.ceil(总条数/当前显示的条数)
		 * 
		 */
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCountInt);
		pageBean.setTotalPage(totalPage);

		// 5、每页显示的数据private List<T> productList = new ArrayList<T>();
		/*
		 * 页数与limit起始索引的关系 例如 每页显示4条 页数 其实索引 每页显示条数 1 0 4 2 4 4 3 8 4 4 12 4
		 * 
		 * 索引index = (当前页数-1)*每页显示的条数
		 * 
		 */
		int index = (currentPageInt - 1) * currentCountInt;
		condition.put("index", index);
		condition.put("currentCount", Integer.valueOf(currentCount));
		List<Map<String, Object>> departmentList = departmentCustomMapper.getDepartmentInfoByCondition(condition);
		pageBean.setProductList(departmentList);

		return pageBean;
	}

	@Override
	public List<String> getDepartmentIdsByNames(List<String> departmentNames) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDepartmentNameById(String departmentid) {
		return departmentCustomMapper.getDepartmentNameById(departmentid);

	}

	@Override
	public int getEmployeeInCountsById(String departmentid) {

		return departmentCustomMapper.getEmployeeInCountsById(departmentid);
	}

	@Override
	public List<Map<String, Object>> getDepartmentTree(String departmentId) throws Exception {
		return departmentCustomMapper.getDepartmentTree(departmentId);
	}

	/**
	 * 判断是否是内部部门
	 */
	@Override
	public boolean isDepartment(String departmentid) {
		System.out.println("departmentidService" + departmentid);
		// return departmentCustomMapper.isDepartment(departmentid);
		Department department = departmentMapper.selectByPrimaryKey(departmentid);
		System.out.println("department" + department);
		if (department == null) {
			return false;
		} else {
			return true;
		}

	}

	// 通过部门id得到该部门下的所有员工id
	@Override
	public List<String> getEmpIdByDepartmentid(String departmentid) {
		return employeeInCustomMapper.getALLEmployeeInByDepartmentId(departmentid);
	}

	// 显示部门违章积分信息
	@Override
	public PageBean<Map<String, Object>> getBreakrulesCase(Map<String, Object> condition) {
		// 目的：就是想办法封装一个PageBean 并返回
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();

		// 1、当前页private int currentPage;
		String strcurrentPage = (String) condition.get("currentPage");
		int currentPage = Integer.valueOf(strcurrentPage);

		pageBean.setCurrentPage(currentPage);

		// 2、当前页显示的条数private int currentCount;
		String strcurrentCount = (String) condition.get("currentCount");
		int currentCount = Integer.valueOf(strcurrentCount);

		pageBean.setCurrentCount(currentCount);

		// 3、总条数private int totalCount;
		int totalCount = 0;
		// 调用dao层的方法

		totalCount = departmentCustomMapper.getBreakrulesCountByDepartmentId(condition);
		pageBean.setTotalCount(totalCount);

		// 4、总页数private int totalPage;
		/*
		 * 总条数 当前页显示的条数 总页数 10 4 3 11 4 3 12 4 3 13 4 4
		 * 
		 * 公式：总页数=Math.ceil(总条数/当前显示的条数)
		 * 
		 */
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 5、每页显示的数据private List<T> productList = new ArrayList<T>();

		/*
		 * * 页数与limit起始索引的关系 例如 每页显示4条 页数 其实索引 每页显示条数 1 0 4 2 4 4 3 8 4 4 12 4
		 * 
		 * 索引index = (当前页数-1)*每页显示的条数
		 * 
		 */
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);

		List<Map<String, Object>> breakrulesCasemap = departmentCustomMapper
				.getBreakrulesCaseInfoByCondition(condition);
		pageBean.setProductList(breakrulesCasemap);

		return pageBean;
	}

	/**** E zwy *****/
	/**** S QLQ *****/

	/*
	 * @Override public List<Map<String, Object>>
	 * getDepartmentTreeForExam(String departmentId) throws SQLException {
	 * return departmentCustomMapper.getDepartmentTreeForExam(departmentId); }
	 */
	/**** E QLQ *****/

	/** S ll *********/
	/**
	 * 统计内部正式单位的信息分页显示
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean<Map<String, Object>> getDepartmentInFormalCountInfo(int currentPage, int currentCount,
			Map<String, Object> condition) throws Exception {
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		int totalCount = 0;
		totalCount = departmentCustomMapper.getDepartmentInFormalCount(condition);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> list = departmentCustomMapper.getDepartmentInFormalCountInfo(condition);
		pageBean.setProductList(list);
		return pageBean;
	}

	/**
	 * 统计内部长委单位的信息分页显示
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@Override
	public PageBean<Map<String, Object>> getDepartmentInToDoCountInfo(int currentPage, int currentCount,
			Map<String, Object> condition) throws Exception {

		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		int totalCount = 0;
		totalCount = departmentCustomMapper.getDepartmentInToDoCount(condition);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount * 1.0 / currentCount);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> list = departmentCustomMapper.getDepartmentInToDoCountInfo(condition);
		pageBean.setProductList(list);
		return pageBean;
	}

	/**
	 * 公共树的查询
	 * 
	 * @param departmentId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> getDepartmentTreeCommon(String departmentId) throws SQLException {

		return departmentCustomMapper.getDepartmentTreeCommon(departmentId);
	}

	/** E ll *********/

	@Override
	public Map<String, Object> getFormalDepartmentAndEmpNum() throws Exception {

		return departmentCustomMapper.getFormalDepartmentAndEmpNum();
	}

	@Override
	public Map<String, Object> getToDoDepartmentAndEmpNum() throws Exception {

		return departmentCustomMapper.getToDoDepartmentAndEmpNum();
	}

	@Override
	public List<String> getChangWeiDepartment(String depNameWords) throws SQLException {
		return departmentCustomMapper.getChangWeiDepartment(depNameWords);
	}

	@Override
	public String deleteCWDepartmentById(String name) throws Exception {
		String message = null;
		int flag;
		// Map<String,Object> mapMessage = new HashMap<String,Object>();
		if (name == null) {
			return "删除失败";
		}
		// 1.根据名称查出所有的单位编号(直属编号)
		List<String> ids = departmentCustomMapper.getCWDepartmentIdsByName(name);
		for (int i = 0; ids != null && ids.size() > 0 && i < ids.size(); i++) {
			String departmentId = ids.get(i);// 获取到直属编号
			// 1.删除人
			List<String> employeeids = employeeInCustomMapper.getALLEmployeeInByDepartmentId2(departmentId);
			for (int j = 0; employeeids != null && j < employeeids.size(); j++) {
				String employeeInId = employeeids.get(j);
				if (employeeInId != null) {
					// 0.获取身份证号与员工ID
					EmployeeIn employeein = employeeInCustomMapper.findEmployeeInById(employeeInId);
					String idCode = employeein.getIdcode();
					// 1 成绩 根据身份证号
					EmployeeexamExample employeeexamExample = new EmployeeexamExample();
					EmployeeexamExample.Criteria criteria = employeeexamExample.createCriteria();
					criteria.andEmployeeidEqualTo(idCode);
					criteria.andEmployeetypeEqualTo("0");// 删除内部考试的信息，保留(短委培训时的信息)
					employeeexamMapper.deleteByExample(employeeexamExample);
					// 2 在线考试信息 在线考试详细信息表 在线答题信息
					// 2.1删除在线答题信息
					OnlineexamanswerinforExample onlineexamanswerinforExample = new OnlineexamanswerinforExample();
					OnlineexamanswerinforExample.Criteria criteria_1 = onlineexamanswerinforExample.createCriteria();
					criteria_1.andEmployeeidEqualTo(idCode);
					onlineexamanswerinforMapper.deleteByExample(onlineexamanswerinforExample);
					// 2.2删除在线信息
					OnlineexaminforExample onlineexaminforExample = new OnlineexaminforExample();
					OnlineexaminforExample.Criteria criteria_2 = onlineexaminforExample.createCriteria();
					criteria_2.andEmployeeidEqualTo(idCode);
					onlineexaminforMapper.deleteByExample(onlineexaminforExample);

					// 3 违章信息 (根据员工ID)
					EmplyinBreakrulesExample emplyinBreakrulesExample = new EmplyinBreakrulesExample();
					EmplyinBreakrulesExample.Criteria criteria_3 = emplyinBreakrulesExample.createCriteria();
					criteria_3.andEmpinemployeeidEqualTo(employeeInId);
					emplyinBreakrulesMapper.deleteByExample(emplyinBreakrulesExample);
					// 4 用户信息(调用飞哥的删除用户信息)
					String userId = userMapper.getUseridByIdcard(idCode);
					if (ValidateCheck.isNotNull(userId)) {// 不为空的话证明存在账号就删除账号
						List<String> userids = new ArrayList<String>();
						userids.add(userId);
						userService.deleteUserById(userids);
					}
					// 5员工进入黑名单的记录(根据员工身份证编号删除)
					employeeInCustomMapper.deleteBlackrulesById(idCode);
					// 6 员工基本信息（根据员工编号删除）
					flag = employeeInMapper.deleteByPrimaryKey(employeeInId);
				}
			}
			// 2.删除部门
			int result = departmentCustomMapper.deleteDepartmentByUpId(ids.get(i));
			message = result > 0 ? "删除成功" : "删除失败";
		}
		return message;
	}

}
