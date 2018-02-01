package cn.xm.exam.service.exam.exam;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.employee.in.Department;
import cn.xm.exam.bean.exam.Exam;
import cn.xm.exam.bean.grade.Employeeexam;
import cn.xm.exam.utils.PageBean;

/**
 * 考试service接口
 * 
 * @author QiaoLiQiang
 * @time 2017年8月9日下午9:26:29
 */
public interface ExamService {
	/**
	 * 获取下次要插入的考试的id
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getNextExamId() throws Exception;

	/**
	 * 添加一次考试信息，同时将参考员工加到员工考试表
	 * 
	 * @param exam
	 *            考试基本信息
	 * @param employeeExam
	 *            参考的内部员工
	 * @return
	 * @throws Exception
	 */
	public boolean addExam(Exam exam,String examMethod) throws Exception;

	/**
	 * 根据考试的id删除考试信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteExamById(String id) throws Exception;

	/**
	 * 修改考试信息，主要是修改考试的状态
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean updateExamById(Exam exam,String examMethod) throws Exception;

	// 修改考试员工(主要是增加与删除考试的员工 )
	/**
	 * 先根据考试的id删除原来的考试的员工然后用上面的service重新添加(新增加的id是原来删除的考试的id)
	 * 
	 * @param examId
	 *            考试id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteExamEmployeeByExamId(String examId) throws Exception;

	/**
	 * 分页查询所有的考试信息(只查询考试与创建人员信息)
	 * 
	 * @param currentPage
	 *            当前页(默认第一页)
	 * @param currentCount
	 *            当前页大小
	 * @param condition
	 *            组装查询考试的条件，可以跟据考试的时间等
	 * @return 一个map封装考试的基本信息与创建考试的员工的信息
	 * @throws Exception
	 */
	public PageBean<Map<String, Object>> findExamsWithCondition(int currentPage, int currentCount,
			Map<String, Object> condition) throws Exception;

	/**
	 * 获取一次考试的基本信息
	 * 
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public Exam getExamInfoByExamId(String examId) throws Exception;

	/**
	 * 根据考试的id获取一次考试的参考人员的详细考试信息(只存放成绩与员工基本信息)
	 * 
	 * @param examId
	 *            考试的id
	 * @return 考试的参考人员的基本信息与成绩
	 * @throws Exception
	 */
	public List<Map<String, Object>> getExamDetailInfoById(String examId) throws Exception;
	
	
	/**
	 * 根据试卷编号查询考试编号
	 * 
	 * @param paperId
	 * @return
	 * @throws Exception
	 */
	public String getExamIdByPaperId(String paperId) throws Exception;

	/**
	 * 根据试卷ID查询使用次数
	 * 
	 * @param paperId
	 * @return
	 * @throws SQLException
	 */
	public int getPaperUseTimesByPaperId(String paperId) throws SQLException;

	/**
	 * 根据试卷ID查询状态为未考试但是使用试卷的总数，判断试卷能否修改
	 * 
	 * @param paperId
	 *            试卷ID
	 * @return 考试条数
	 * @throws SQLException
	 */
	public int getExamCountByPaperIdAndStatus(String paperId) throws SQLException;
	/**
	 * 根据考试名称模糊查询考试级别和名称
	 * 
	 * @param nameWord
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getExamNameAndLevelByName(String nameWord) throws SQLException;
	
	/******** S zwy ******/
	// 获取一个员工的所有考试信息
		/**
		 * 根据员工id获取考试信息
		 * 
		 * @param employeeId
		 *            员工id
		 * @return 考试信息(每个map应该包括考试的基本信息与相关的成绩信息)
		 * @throws Exception
		 */
		public List<Map<String, Object>> getExamInfoByEmployeeId(String employeeId) throws Exception;
		PageBean<Map<String, Object>> getExamInfoByCondition(Map<String, Object> condition) throws Exception;
	/******** E zwy ******/
}
