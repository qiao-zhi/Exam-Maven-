package cn.xm.exam.service.impl.employee.in;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.employee.in.EmployeeIn;
import cn.xm.exam.bean.employee.in.EmplyinBreakrulesExample;
import cn.xm.exam.bean.exam.OnlineexamanswerinforExample;
import cn.xm.exam.bean.exam.OnlineexaminforExample;
import cn.xm.exam.bean.grade.EmployeeexamExample;
import cn.xm.exam.mapper.employee.in.DepartmentMapper;
import cn.xm.exam.mapper.employee.in.EmployeeInMapper;
import cn.xm.exam.mapper.employee.in.EmplyinBreakrulesMapper;
import cn.xm.exam.mapper.employee.in.custom.DepartmentCustomMapper;
import cn.xm.exam.mapper.employee.in.custom.EmployeeInCustomMapper;
import cn.xm.exam.mapper.exam.OnlineexamanswerinforMapper;
import cn.xm.exam.mapper.exam.OnlineexaminforMapper;
import cn.xm.exam.mapper.exam.custom.ExamCustomMapper;
import cn.xm.exam.mapper.grade.EmployeeexamMapper;
import cn.xm.exam.mapper.system.UserMapper;
import cn.xm.exam.service.employee.in.EmployeeInService;
import cn.xm.exam.service.system.UserService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.exam.ExamEmployeeInQueryVo;

/**
 * 内部员工服务实现类
 * 
 * @author QiaoLiQiang
 * @time 2017年10月16日下午12:25:52
 */
@Service
public class EmployeeInServiceImpl implements EmployeeInService {
	/*************** S zwy *************/
	private EmployeeIn employeeIn;

	public EmployeeIn getEmployeeIn() {
		return employeeIn;
	}

	public void setEmployeeIn(EmployeeIn employeeIn) {
		this.employeeIn = employeeIn;
	}

	@Autowired
	private EmployeeInCustomMapper employeeInCustomMapper;

	public EmployeeInCustomMapper getEmployeeInCustomMapper() {
		return employeeInCustomMapper;
	}

	public void setEmployeeInCustomMapper(EmployeeInCustomMapper employeeInCustomMapper) {
		this.employeeInCustomMapper = employeeInCustomMapper;
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

	// 删除员工的考试记录
	@Autowired
	private ExamCustomMapper examCustomMapper;

	public ExamCustomMapper getExamCustomMapper() {
		return examCustomMapper;
	}

	public void setExamCustomMapper(ExamCustomMapper examCustomMapper) {
		this.examCustomMapper = examCustomMapper;
	}

	@Override
	public String getNextEmployeeInId(String departmentId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addEmployeeIn(EmployeeIn employeeIn) throws Exception {

		System.out.println(employeeIn);
		int flag;
		if (employeeIn != null) {

			// 设置部门id
			String departmentid = departmentCustomMapper.getIdByDepartmentName(employeeIn.getDepartmentid());
			System.out.println(departmentid);
			employeeIn.setDepartmentid(departmentid);

			// 这里需要设置他自己的id
			String employeeid = UUID.randomUUID().toString().replaceAll("-", "");
			employeeIn.setEmployeeid(employeeid);
			employeeIn.setEmployeenumber("01001");

			// 按时间排序
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");
			employeeIn.setSort(sdf.format(new Date()));
			employeeIn.setTrainstatus(0);
			flag = employeeInMapper.insertSelective(employeeIn);
		} else {
			return false;
		}
		return flag == 1 ? true : false;
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

	// employeeInId员工ID
	@Override
	public boolean deleteEmployeeInById(String employeeInId) throws Exception {
		int flag;
		// employeeInId员工ID
		if (employeeInId != null) {
			// 0.获取身份证号与员工ID
			EmployeeIn employeein = employeeInCustomMapper.findEmployeeInById(employeeInId);
			String idCode = employeein.getIdcode();
			if (ValidateCheck.isNull(idCode)) {
				return false;
			}
			// 1 成绩 根据身份证号
			EmployeeexamExample employeeexamExample = new EmployeeexamExample();
			EmployeeexamExample.Criteria criteria = employeeexamExample.createCriteria();
			criteria.andEmployeeidEqualTo(idCode);
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

		} else {
			return false;
		}
		return flag == 1 ? true : false;
	}

	@Override
	public boolean updateEmployeeIn(EmployeeIn employeeIn) throws Exception {

		// 1.根据传上来的部门ID判断是否修改部门,false为未修改
		EmployeeIn employeeSour = employeeInMapper.selectByPrimaryKey(employeeIn.getEmployeeid());
		boolean isUpDepartment = employeeSour.getDepartmentid() == employeeIn.getDepartmentid() ? false : true;

		// 2.如果未修改部门，直接修改员工信息，权限不用该
		if (employeeIn != null && !isUpDepartment) {
			// 按时间排序
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");
			employeeIn.setSort(sdf.format(new Date()));
			return employeeInMapper.updateByPrimaryKeySelective(employeeIn) > 0 ? true : false;
		}
		// 3.如果修改部门，修改员工信息，调用飞哥的接口删除用户的角色
		if (employeeIn != null && isUpDepartment) {
			// 修改基本信息
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");
			employeeIn.setSort(sdf.format(new Date()));
			employeeInMapper.updateByPrimaryKeySelective(employeeIn);
			// 调用飞哥的接口删除角色
			userService.updateUserInfo(employeeSour.getIdcode());
			return true;
		}
		return true;
	}

	@Override
	public EmployeeIn getEmployeeInById(String employeeInId) throws Exception {
		System.out.println("employeeInIdService" + employeeInId);
		if (employeeInId != null) {

			EmployeeIn employeeIn = employeeInCustomMapper.findEmployeeInById(employeeInId);

			return employeeIn;
		} else {
			return null;
		}

	}

	@Override
	public PageBean<EmployeeIn> findEmployeeInWithCondition(Map<String, Object> condition) throws Exception {
		// 目的：就是想办法封装一个PageBean 并返回
		PageBean<EmployeeIn> pageBean = new PageBean<EmployeeIn>();
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
		totalCount = employeeInCustomMapper.getEmployeeInCountByCondition(condition);

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
		 * 页数与limit起始索引的关系 例如 每页显示4条 页数 其实索引 每页显示条数 1 0 4 2 4 4 3 8 4 4 12 4
		 * 
		 * 索引index = (当前页数-1)*每页显示的条数
		 * 
		 */
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<EmployeeIn> employeeInList = employeeInCustomMapper.findEmployeeInByCondition(condition);
		pageBean.setProductList(employeeInList);

		return pageBean;
	}

	@Override
	public boolean batchImportEmployeeIn(List<EmployeeIn> list) {
		System.out.println("调用service的方法");
		System.out.println("List<EmployeeIn>" + list);
		int flag = 1;
		for (EmployeeIn employeeIn : list) {
			System.out.println("EmployeeIn" + employeeIn);
			// flag =
			// employeeInCustomMapper.batchImportEmployeeIn(employeeInInfo);
			flag = employeeInMapper.insertSelective(employeeIn);
			if (flag == 0) {
				return false;
			}
		}
		return true;
	}

	public List<String> getALLEmployeeInByDepartmentId(String departmentid) {
		List<String> employeeInList = null;
		if (departmentid != null) {
			employeeInList = employeeInCustomMapper.getALLEmployeeInByDepartmentId(departmentid);
		}
		System.out.println("employeeInListService" + employeeInList);
		return employeeInList;
	}

	@Override
	public boolean isIdCode(String myIdcode) {
		// 判断输入的身份证号在数据库是否已经存在
		List<String> idcodeList = departmentCustomMapper.getIdcodeListAll();
		System.out.println("idcodeList" + idcodeList);

		for (int i = 0; i < idcodeList.size(); i++) {
			if (myIdcode.equals(idcodeList.get(i))) {

				return false;
			}
		}
		return true;

	}

	@Override
	public boolean addEmployeeInBatch(List<EmployeeIn> employeeInList) throws Exception {

		//员工编号集合
				List<String> employeeInIds = new ArrayList<String>();
				int flag = 0;
				if (employeeInList != null && employeeInList.size() > 0) {

					for (EmployeeIn employeein : employeeInList) {

						// 这里需要设置他自己的id
						// String employeeid =
						// UUID.randomUUID().toString().replaceAll("-", "");
						employeein.setEmployeeid(UUIDUtil.getUUID2());
						//将员工ID添加到员工ID集合中
						employeeInIds.add(employeein.getEmployeeid());
						
						// 员工编号
						employeein.setEmployeenumber(employeein.getIdcode());
						if (ValidateCheck.isNull(employeein.getPhone())) {
							employeein.setPhone("无");
						}

						// 按时间排序
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ms");
						employeein.setSort(sdf.format(new Date()));
						// 设置培训状态
						employeein.setTrainstatus(0);

						// 设置图片名称
						String name = employeein.getIdcode() + ".jpg";
						employeein.setPhoto(name);

					}
					// 批量导入内部部门员工的基本信息
					flag = employeeInCustomMapper.addEmployeeInBatch(employeeInList);
				} else {
					return false;
				}
				if (flag > 0) {
					//批量插入后为员工创建个人账户，调用非哥的service接口		
					boolean addUsers = userService.addUsers(employeeInIds);			
					if(addUsers){				
						return true;
					}else{
						return false;
					}
				} else {
					return false;
				}
	}

	@Override
	public String isBlackList(String idcode) {
		System.out.println("idcode" + idcode);
		String temporaryInStatus = null;
		if (idcode != null) {
			temporaryInStatus = employeeInCustomMapper.isBlackList(idcode);

		}
		return temporaryInStatus;
	}

	// 判断是否是内部部门
	@Override
	public boolean isNeibu(String yincangbumenid) {
		String flag = null;

		if (yincangbumenid != null) {

			flag = employeeInCustomMapper.isNeibu(yincangbumenid);
		}
		if (flag.equals("0")) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public PageBean<Map<String, Object>> getEmpCase(Map<String, Object> condition) {
		// 目的：就是想办法封装一个PageBean 并返回
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();

		// 1、当前页private int currentPage;
		String strcurrentPage = (String) condition.get("currentPage");
		int currentPage = Integer.valueOf(strcurrentPage);
		System.out.println("currentPage" + currentPage);

		pageBean.setCurrentPage(currentPage);

		// 2、当前页显示的条数private int currentCount;
		String strcurrentCount = (String) condition.get("currentCount");
		int currentCount = Integer.valueOf(strcurrentCount);
		System.out.println("currentCount" + currentCount);

		pageBean.setCurrentCount(currentCount);

		// 3、总条数private int totalCount;
		int totalCount = 0;
		String departmentid = (String) condition.get("empbumen");
		System.out.println(departmentid);
		// 调用dao层的方法
		// totalCount = examCustomMapper.getExamCountByCondition(hiddenidcode);

		totalCount = employeeInCustomMapper.getCountByDepartmentId(departmentid);
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

		List<Map<String, Object>> empCasemap = employeeInCustomMapper.getEmpCaseInfoByCondition(condition);
		pageBean.setProductList(empCasemap);

		return pageBean;
	}

	// 通过员工身份证得到员工信息

	@Override
	public EmployeeIn getEmployeeInByIdcode(String idcode) {

		return employeeInCustomMapper.getEmployeeInByIdcode(idcode);
	}

	/*************** E zwy *************/
	/*************** S QLQ *************/
	@Override
	public List<Map<String,Object>> getExamEmployeeIns(Map condition) throws SQLException {
		return employeeInCustomMapper.getExamEmployeeIns(condition);
	}
	/*************** E QLQ *************/

}
