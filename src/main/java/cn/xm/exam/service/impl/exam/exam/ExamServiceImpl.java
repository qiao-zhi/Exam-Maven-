package cn.xm.exam.service.impl.exam.exam;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.exam.Exam;
import cn.xm.exam.bean.exam.OnlineexamanswerinforExample;
import cn.xm.exam.bean.exam.OnlineexaminforExample;
import cn.xm.exam.bean.grade.Employeeexam;
import cn.xm.exam.bean.grade.EmployeeexamExample;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.mapper.exam.ExamMapper;
import cn.xm.exam.mapper.exam.OnlineexamanswerinforMapper;
import cn.xm.exam.mapper.exam.OnlineexaminforMapper;
import cn.xm.exam.mapper.exam.custom.ExamCustomMapper;
import cn.xm.exam.mapper.exam.custom.ExampaperCustomMapper;
import cn.xm.exam.mapper.grade.EmployeeexamMapper;
import cn.xm.exam.mapper.grade.custom.EmployeeexamCustomMapper;
import cn.xm.exam.mapper.haul.HaulinfoMapper;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;

@Service
@SuppressWarnings("all")
public class ExamServiceImpl implements ExamService {
	@Resource
	private EmployeeexamCustomMapper employeeexamCustomMapper;// 成绩mapper(初始化成绩表)
	@Resource
	private ExampaperCustomMapper exampaperCustomMapper;// （试卷Mapper,修改试卷使用次数）
	@Resource
	private EmployeeexamMapper employeeexamMapper;// 成绩mapper(初始化成绩表)
	@Resource
	private ExamMapper examMapper;// 考试服务(添加考试基本信息)
	@Autowired
	private ExamCustomMapper examCustomMapper;// 考试服务(查询考试信息基本信息)
	@Resource
	private HaulinfoMapper haulinfoMapper;

	@Override
	public String getNextExamId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addExam(Exam exam,String examMethod) throws Exception {
		if (exam == null) {
			return false;
		}
		// 获取内外部员工
		List<Employeeexam> employeeInExams = exam.getEmployeeInExams();// 内部员工
		List<Employeeexam> employeeOutExams = exam.getEmployeeOutExams();// 外部员工
		boolean insertEmployeeResult = false;
		int in_size, out_size;
		in_size = employeeInExams != null ? employeeInExams.size() : 0;
		out_size = employeeOutExams != null ? employeeOutExams.size() : 0;
		// 获取参考人数
		Integer employeeNum = in_size + out_size;
		String examId = exam.getExamid();
		// 如果考试的id为空就UUID生成一个ID(与修改考试用的同一个Action)
		if (ValidateCheck.isNull(examId)) {
			examId = UUIDUtil.getUUID2();// 考试ID
			exam.setExamid(examId);
		}
		// 如果部门ID为空就获取当前session中的用户的部门ID并设置考试的部门ID
		if (ValidateCheck.isNull(exam.getDepartmentid())) {
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
			String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到部门ID
			exam.setDepartmentid(departmentIdSession);
		}
		if (ValidateCheck.isNull(exam.getExamtype())) {
			exam.setExamtype("外部考试");
		}
		// 创建时间为空就创建一个创建时间
		if (exam.getCreatetime() == null) {
			exam.setCreatetime(new Date());// 设置创建时间
		}
		// 大修ID不为空的话证明是外部的单位参加的考试，调用大修ID去获取大修状态(为空或者不为无证明是外部员工)
		if (ValidateCheck.isNotNull(exam.getBigid()) && !"无".equals(exam.getBigid())) {
			exam.setBigstatus(haulinfoMapper.selectByPrimaryKey(exam.getBigid()).getBigstatus());// 设置考试的大修状态
		}
		// 大修ID为空的话证明是内部的单位参加的考试。设置一个大修ID的默认值与大修状态
		if (ValidateCheck.isNull(exam.getBigid()) || "无".equals(exam.getBigid())) {
			exam.setBigstatus("无");
			exam.setBigid("无");
			exam.setExamtype("内部考试");
		}
		exam.setStatus("未开始");// 设置考试状态为未考
		exam.setEmployeenum(employeeNum);// 设置参考人数
		insertEmployeeResult = examMapper.insert(exam) > 0 ? true : false;
		if (employeeInExams != null && employeeInExams.size() > 0) {
			for (Employeeexam employeeIn : employeeInExams) {
				employeeIn.setExamid(examId);
				employeeIn.setExammethod(examMethod);
				employeeIn.setGrade(0f);
				//将外部员工独有的置为空
				employeeIn.setDistributeid(0);
				employeeIn.setBigemployeeoutid("0");
//				employeeIn.setUnitid("无");
				employeeIn.setEmployeetype("0");// 0代表内部员工
			}
			// 插入内部员工
			insertEmployeeResult = employeeexamCustomMapper.addEmployeeExam(employeeInExams) > 0 ? true : false;
		}

		if (employeeOutExams != null && employeeOutExams.size() > 0) {
			for (Employeeexam employeeOut : employeeOutExams) {
				employeeOut.setExamid(examId);
				employeeOut.setExammethod(examMethod);
				employeeOut.setGrade(0f);
				employeeOut.setEmployeetype("1");// 1代表外部员工
			}
			// 插入外部员工
			insertEmployeeResult = employeeexamCustomMapper.addEmployeeExam(employeeOutExams) > 0 ? true : false;
		}
		// 试卷的使用次数增加一
		exampaperCustomMapper.addExampaperUsetimes(exam.getPaperid());// 试卷使用次数增加一次
		return insertEmployeeResult;
	}
	@Resource
	private OnlineexaminforMapper onlineexaminforMapper;
	@Resource
	private OnlineexamanswerinforMapper onlineexamanswerinforMapper;
	@Override
	public boolean deleteExamById(String id) throws Exception {
		//0.刪除在線考試信息
		OnlineexamanswerinforExample onlineexamanswerinforExample = new OnlineexamanswerinforExample();
		OnlineexamanswerinforExample.Criteria criteria_1 = onlineexamanswerinforExample.createCriteria(); 
		criteria_1.andOnlineanswerexamidEqualTo(id);
		onlineexamanswerinforMapper.deleteByExample(onlineexamanswerinforExample);
		//删除在线考试的详细信息
		OnlineexaminforExample onlineexaminforExample = new OnlineexaminforExample();
		OnlineexaminforExample.Criteria  criteria_2=onlineexaminforExample.createCriteria();
		criteria_2.andExamidEqualTo(id);
		onlineexaminforMapper.deleteByExample(onlineexaminforExample);
		// 1.先删除成绩表
		EmployeeexamExample employeeexamExample = new EmployeeexamExample();
		EmployeeexamExample.Criteria criteria = employeeexamExample.createCriteria();
		criteria.andExamidEqualTo(id);
		int deleteByExample = employeeexamMapper.deleteByExample(employeeexamExample);
		// 2.试卷使用次数减一
		// 2.1获取到考试的试卷ID
		Exam exam = examMapper.selectByPrimaryKey(id);
		exampaperCustomMapper.minusExampaperUsetimes(exam.getPaperid());

		// 3.删除考试表
		return examMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean updateExamById(Exam exam,String examMethod) throws Exception {
		// 修改试卷的使用次数
		int minusPaperUsetimes = exampaperCustomMapper
				.minusExampaperUsetimes(examMapper.selectByPrimaryKey(exam.getExamid()).getPaperid());
		// 根据考试的id删除考试,删除成功重新添加
		boolean updateExamResult = false;
		if (this.deleteExamById(exam.getExamid())) {
			// 根据考试的id重新添加考试
			updateExamResult = this.addExam(exam,examMethod);
		}
		return updateExamResult;
	}

	@Override
	public boolean deleteExamEmployeeByExamId(String examId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageBean<Map<String, Object>> findExamsWithCondition(int currentPage, int currentCount,
			Map<String, Object> condition) throws Exception {

		PageBean pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentCount(currentCount);// 页大小
		pageBean.setCurrentPage(currentPage);// 当前页
		Integer totalCount = examCustomMapper.getExamBaseTotalByCondition(condition);// 查询总数
		pageBean.setTotalCount(totalCount);// 总数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);// 计算总页数
		pageBean.setTotalPage(totalPage);// 总页数
		/**
		 * 页数 大小 第一页 0 8 第二页 8 8
		 */
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> exams = examCustomMapper.getExamBaseInfoByCondition(condition);
		pageBean.setProductList(exams);
		return pageBean;
	}

	@Override
	public Exam getExamInfoByExamId(String examId) throws Exception {
		return examMapper.selectByPrimaryKey(examId);
	}

	@Override
	public List<Map<String, Object>> getExamDetailInfoById(String examId) throws Exception {
		return null;
	}

	@Override
	public String getExamIdByPaperId(String paperId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPaperUseTimesByPaperId(String paperId) throws SQLException {
		return examCustomMapper.getPaperUseTimesByPaperId(paperId);
	}

	@Override
	public List<Map<String, Object>> getExamNameAndLevelByName(String nameWord) throws SQLException {
		// TODO Auto-generated method stub
		return examCustomMapper.getExamNameAndLevelByName(nameWord);
	}
	/******** S zwy ******/
	@Override
	public List<Map<String, Object>> getExamInfoByEmployeeId(String employeeId) throws Exception {
		return examCustomMapper.getExamInfoByEmployeeId(employeeId);
	}

	@Override
	public PageBean<Map<String, Object>> getExamInfoByCondition(Map<String, Object> condition) throws Exception {

		// 目的：就是想办法封装一个PageBean 并返回
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();

		// 1、当前页private int currentPage;
		String strcurrentPage = (String) condition.get("currentPage");
		int currentPage = Integer.valueOf(strcurrentPage);

		pageBean.setCurrentPage(currentPage);
		/*
		 * Map<String,Object> currentPagemap = new HashMap<String,Object>();
		 * currentPagemap.put("currentPage", currentPage);
		 * list.add(currentPagemap);
		 */
		// 2、当前页显示的条数private int currentCount;
		String strcurrentCount = (String) condition.get("currentCount");
		int currentCount = Integer.valueOf(strcurrentCount);

		pageBean.setCurrentCount(currentCount);
		/*
		 * Map<String,Object> currentCountmap = new HashMap<String,Object>();
		 * currentPagemap.put("currentCount", currentCount);
		 * list.add(currentCountmap);
		 */

		// 3、总条数private int totalCount;
		int totalCount = 0;
		String hiddenidcode = (String) condition.get("hiddenidcode");
		// 调用dao层的方法
		totalCount = examCustomMapper.getExamCountByCondition(hiddenidcode);
		pageBean.setTotalCount(totalCount);
		/*
		 * Map<String,Object> totalCountmap = new HashMap<String,Object>();
		 * currentPagemap.put("totalCount", totalCount);
		 * list.add(totalCountmap); System.out.println("totalCount" +
		 * totalCount);
		 */
		// 4、总页数private int totalPage;
		/*
		 * 总条数 当前页显示的条数 总页数 10 4 3 11 4 3 12 4 3 13 4 4
		 * 
		 * 公式：总页数=Math.ceil(总条数/当前显示的条数)
		 * 
		 */
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);
		/*
		 * Map<String,Object> totalPagemap = new HashMap<String,Object>();
		 * currentPagemap.put("totalPage", totalPage); list.add(totalPagemap);
		 */

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
		// List<EmployeeIn> employeeInList =
		// ExamCustomMapper.findExamByCondition(condition);
		// System.out.println("employeeInList" + employeeInList);
		// pageBean.setProductList(employeeInList);
		List<Map<String, Object>> exammap = examCustomMapper.getExamInfoByCondition(condition);
		pageBean.setProductList(exammap);

		return pageBean;

		// return examCustomMapper.getExamInfoByEmployeeId(employeeid);

	}

	/******** E zwy ******/

	@Override
	public int getExamCountByPaperIdAndStatus(String paperId) throws SQLException {
		// TODO Auto-generated method stub
		return examCustomMapper.getExamCountByPaperIdAndStatus(paperId);
	}
}
