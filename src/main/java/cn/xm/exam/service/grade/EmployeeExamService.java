package cn.xm.exam.service.grade;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.grade.Employeeexam;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.vo.grade.EmployeeExamGrade;
import cn.xm.exam.vo.grade.ExamEmployeeexamExampaper;
import cn.xm.exam.vo.grade.OnlineExamEmployeeInfo;

/**
 * 员工考试service接口(成绩管理接口)
 * 
 * @author QizoLiQiang
 * @time 2017年8月10日下午3:44:43
 */
public interface EmployeeExamService {

	
	// id用int型自增，没用

		/**
		 * 单个导入内部或外部员工成绩
		 * 
		 * @param mployeeOutGrades
		 * @return
		 * @throws Exception
		 */
		public boolean addEmployeGrade(Employeeexam employeeOutGrades) throws Exception;

		/**
		 * 批量加入外部员工的成绩
		 * 
		 * @param mployeeOutGrades
		 *            外部员工的基本信息
		 * @return 影响行数
		 * @throws Exception
		 */
		public int addEmployeeOutGradeBatch(List<Employeeexam> employeeOutGrades) throws Exception;

		/*** 第一种(根据考试编号与员工id删除记录后重加) **************************/
		/**
		 * 根据考试编号与员工id批量删除员工考试记录
		 * 
		 * @param examId
		 *            考试id
		 * @param employeeInId
		 *            内部员工id
		 * @return
		 * @throws Exception
		 */
		public boolean deleteEmployeeInByExamIdBatch(String examId, List<String> employeeInId) throws Exception;;

		/**
		 * 录入内部员工考试的成绩
		 * 
		 * @param mployeeInGrades
		 *            一个map封装内部员工id，考试编号，分数,考试方式
		 * @return
		 * @throws Exception
		 */
		public boolean addEmployeeInGradeOnlineBatch(List<Map<String, Object>> mployeeInGrades) throws Exception;

		/**
		 * 删除单个员工的成绩
		 * 
		 * @param employeeExamId
		 *            删除考试成绩
		 * @return
		 * @throws Exception
		 */
		public boolean deleteEmployeeGradeById(Integer employeeExamId) throws Exception;

		/**
		 * 批量删除外部员工考试成绩（根据考试ID）
		 * 
		 * @param ExamId
		 *            需要删除的考试id
		 * @return
		 * @throws Exception
		 */
		public boolean deleteEmployeeOutGradeBatch(String ExamId) throws Exception;

		/**
		 * 组合条件查询
		 * 按照考试名称，考试等级，考试时间，员工的相关信息查询员工成绩的相关信息
		 * @param currentPage
		 * @param currentTotal
		 * @param condition
		 * @return
		 * @throws Exception
		 */
		public PageBean<EmployeeExamGrade> findExamGradesWithCondition(int currentPage, int currentTotal,
				Map<String, Object> condition) throws Exception;

		
		/**
		 * 根据考试编号与员工Id查询在线考试员工的成绩信息
		 * @param condition
		 * @return
		 * @throws Exception
		 */
		public OnlineExamEmployeeInfo getExamGardeByEmployeeIdAndExamId(Map<String,Object> condition) throws Exception;
		
		/******************************************leilong添加的***********************************************/
		/**
		 * 组合条件查询
		 * 根据考试名称，考试级别，考试时间查询考试的相关信息
		 * @param currentPage
		 * @param currentTotal
		 * @param condition
		 * @return
		 * @throws Exception
		 */
		public PageBean<ExamEmployeeexamExampaper> getExamGradesInfoWithCondition(int currentPage, int currentTotal,
				Map<String, Object> condition) throws Exception;

		
		/**
		 * 根据条件对考试成绩进行分析，查询优良差的人数
		 * @param condition
		 * @return
		 * @throws Exception
		 */
		public Map<String,Object> getExamGradeAnalyseInfoByCondition(Map<String,Object> condition) throws Exception;
		
		/**
		 * 根据考试ID查询员工的考试成绩信息，用于导出成绩单
		 * @param examId
		 * @return
		 * @throws Exception
		 */
		public List<EmployeeExamGrade> getEmployeeGradeByExamId(String examId) throws Exception;
		
		/**
		 * 根据组合条件查询员工的成绩信息，用于导出员工成绩
		 * @param condition
		 * @return
		 * @throws Exception
		 */
		public List<EmployeeExamGrade> findEmployeeGradeByCondition(Map<String,Object> condition) throws Exception;
		
		/**
		 * 根据考试ID查询线下考试的员工信息，获取员工的考号和姓名用于对比
		 * @param examId
		 * @return
		 * @throws Exception
		 */
		public List<Employeeexam> getEmployeeOutInfoByExamId(String examId) throws Exception;
		
		/**
		 * 根据考试ID，试卷ID和员工身份证号修改内部员工在线考试的成绩信息
		 * @param condition
		 * @return
		 * @throws Exception
		 */
		public boolean updateEmployeeInScoreByIdCard(Map<String,Object> condition) throws Exception;
		
		/**
		 * 根据条件查询考试部门信息分页显示
		 * @param currentPage
		 * @param currentTotal
		 * @param condition
		 * @return
		 * @throws Exception
		 */
		public PageBean<Map<String,Object>> getUnitExamInfosByCondition(int currentPage, int currentTotal,Map<String,Object> condition) throws Exception;
	
		/**
		 * 根据考试ID和部门ID查询该部门参加这次考试的员工成绩信息
		 * @param condition
		 * @return
		 * @throws Exception
		 */
		public List<Employeeexam> getEmployeeGradeInfosByIds(Map<String,Object> condition) throws Exception;
	
	
	
	
	/*********** S qlq **********/
	/**
	 * 添加员工成绩(用于初始化成绩表)
	 * 
	 * @param employeeexam
	 * @return
	 * @throws SQLException
	 */
	public int addEmployeeExam(List<Employeeexam> employeeexam) throws SQLException;

	/**
	 * 根据考试的ID查询参考人员基本信息
	 * 
	 * @param examId
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getEmployeeexamsByExamId(String examId) throws SQLException;

	/*********** E qlq **********/
}
