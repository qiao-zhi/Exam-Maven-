package cn.xm.exam.mapper.exam.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.grade.Employeeexam;

/**
 * 考试mapper
 * 
 * @author QiaoLiQiang
 * @time 2017年10月23日下午12:18:06
 */
public interface ExamCustomMapper {

	/**
	 * 组合条件查询满足条件总数
	 * 
	 * @param condition
	 *            组合条件(包括考试名称，考试级别，考试状态，所属部门，考试时间)
	 * @return
	 * @throws SQLException
	 */
	public Integer getExamBaseTotalByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 组合条件查询试卷的基本信息
	 * 
	 * @param condition
	 *            组合条件(包括考试名称，考试级别，考试状态，所属部门，考试时间)
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> getExamBaseInfoByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 根据试卷的ID查询试卷的使用次数
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

	/** S zwy ***/
	List<Map<String, Object>> getExamInfoByEmployeeId(String employeeId);

	public List<Map<String, Object>> getExamInfoByCondition(Map<String, Object> condition);

	public int getExamCountByCondition(Map<String, Object> condition);

	public int getExamCountByCondition(String idcode);

	public void deleteGradeByEmployeeInIdcode(String idcode);

	public List<Employeeexam> getExamByEmployeeInIdcode(String idcode);

	public void deleteOnlineexaminforByEmployeeInIdcode(Employeeexam employeeexam);

	public void deleteOnlineexamanswerinforByEmployeeInIdcode(Employeeexam employeeexam);
	/** E zwy ***/

}
