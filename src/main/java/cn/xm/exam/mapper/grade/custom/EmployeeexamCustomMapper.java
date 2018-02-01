package cn.xm.exam.mapper.grade.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import cn.xm.exam.bean.grade.Employeeexam;
import cn.xm.exam.vo.grade.EmployeeExamGrade;
import cn.xm.exam.vo.grade.ExamEmployeeexamExampaper;
import cn.xm.exam.vo.grade.OnlineExamEmployeeInfo;

/**
 * 员工成绩mapper
 * 
 * @author QiaoLiQiang
 * @time 2017年10月22日下午10:03:44
 */
public interface EmployeeexamCustomMapper {

	/**
	 * 组合条件查询考试的成绩信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<ExamEmployeeexamExampaper> getExamGradesInfoWithCondition(Map<String, Object> condition)
			throws SQLException;

	/**
	 * 根据条件查询满足条件的考试数量
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public Integer getExamGradesInfoCountWithCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据条件对考试的成绩进行分析，查询优良差的人数
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getExamAnalyseInfoByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 组合条件查询员工成绩的相关信息,分页显示
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<EmployeeExamGrade> getEmployeeGradesInfoWithCondition(Map<String, Object> condition)
			throws SQLException;

	/**
	 * 组合条件查询满足条件的员工成绩记录总数
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public Integer getEmployeeGradesInfoCountWithCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 组合条件查询满足条件的员工成绩，用于员工成绩的导出
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<EmployeeExamGrade> findEmployeeGradesInfoByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 批量插入外部员工的成绩信息
	 * 
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public int insertEmployeeGradeBantch(List<Employeeexam> list) throws SQLException;

	/**
	 * 根据考试ID批量删除外部员工成绩
	 * 
	 * @param examId
	 * @return
	 * @throws SQLException
	 */
	public int deleteEmployeeOutGradeBantch(String examId) throws SQLException;

	/**
	 * 根据考试ID查询员工的考试成绩信息
	 * 
	 * @param examId
	 * @return
	 * @throws SQLException
	 */
	public List<EmployeeExamGrade> getEmployeeGradeByExamId(String examId) throws SQLException;

	/**
	 * 根据考试编号,试卷编号和身份证号修改内部员工的考试成绩
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int updateEmployeeInScoreByIdCard(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据考试编号和身份证号查询在线考试员工成绩
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public OnlineExamEmployeeInfo getExamGardeByEmployeeIdAndExamId(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据考试编号查询导入的成绩中通过这次考试的员工身份证号集合以及大修ID用于批量设置员工的培训状态
	 * 
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public List<EmployeeExamGrade> getEmployeeIdCardsAndBigIdByExamId(String examId) throws SQLException;

	/**
	 * 根据大修ID和员工身份证号集合批量设置线下考试员工的培训情况
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int updateEmployeeOutTrainStatus(Map<String, Object> condition) throws SQLException;
	
	
	/**
	 * 根据考试ID和身份证号设置外来单位员工的考试成绩
	 * 条件：考试编号，身份证号，成绩
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int updateEmployeeOutGradeInfo(Map<String,Object> condition) throws SQLException;
	
	/**
	 * 根据考试ID查询通过线下考试的员工的分配员工表的主键，和大修员工ID
	 * @param examId
	 * @return map中的键 distributeid，bigEmployeeOutId
	 * @throws SQLException
	 */
	public List<Map<String,Object>> selectPassOutExamDistributeIds(String examId) throws SQLException;
	
	/**
	 * 根据员工分配表的主键批量修改考试状态
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public int updateEmpDistributeExamStatusByIds(List<String> list) throws SQLException;
	
	/**
	 * 根据大修员工ID集合批量修改培训状态
	 * @param list
	 * @return
	 * @throws SQLException
	 */
	public int updateHaulEmployeeOutTrainStatusByIds(List<String> list) throws SQLException;
	
	/**
	 * 根据条件统计部门考试信息数量
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int getUnitExamInfoCountByCondition(Map<String,Object> condition) throws SQLException;
	
	/**
	 * 根据条件查询部门考试信息
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String,Object>> getUnitExamInfosByCondition(Map<String,Object> condition) throws SQLException; 
		
	/****** S QLQ ***********/
	/**
	 * 添加员工成绩(用于初始化成绩表)
	 * 
	 * @param employeeexam
	 * @return
	 * @throws SQLException
	 */
	public int addEmployeeExam(List<Employeeexam> employeeexam) throws SQLException;

	/**
	 * 根据考试ID查询考试内部参考员工信息
	 * 
	 * @param examId
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getEmployeeexamsInByExamId(String examId) throws SQLException;

	/**
	 * 根据考试ID查询考试外部参考员工信息
	 * 
	 * @param examId
	 * @return
	 * @throws SQLException
	 */
	List<Map<String, Object>> getEmployeeexamsOutByExamId(String examId) throws SQLException;
	/****** E QLQ ***********/
}
