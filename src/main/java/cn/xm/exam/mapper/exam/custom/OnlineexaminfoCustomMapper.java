package cn.xm.exam.mapper.exam.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.exam.Onlineexamanswerinfor;
import cn.xm.exam.vo.grade.OnlineExamEmployeeInfo;

/**   
*    
* 项目名称：Exam   
* 类名称：OnlineexaminfoMapper   
* 类描述：在线考试的相关操作的mapper
* 创建人：Leilong  
* 创建时间：2017年10月28日 下午4:29:11     
* @version    
*    
*/
public interface OnlineexaminfoCustomMapper {
	
	/**
	 * 根据内部员工的身份证号查询该内部员工尚未参加的线上考试信息
	 * @param idCard
	 * @return
	 * @throws SQLException
	 */
	public List<OnlineExamEmployeeInfo> getNotStartExamInfoByIdCard(String idCard) throws SQLException;
	
	/**
	 * 根据考试ID查询考试的相关信息
	 * 查询信息：考试编号，考试名称，试卷编号，开始结束时间，答题时间
	 * @param examId
	 * @return
	 * @throws SQLException
	 */
	public Map<String,Object> findExamInfoByExamId(String examId) throws SQLException;
	
	
	/**
	 * 根据试卷的ID和员工的身份证号查询试卷的全部信息，包括员工的答案
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public Exampaper findExamPaperInfoByPaperId(Map<String,Object> condition) throws SQLException;
	
	/**
	 * 批量插入在线考试员工的答题信息
	 * @param conditon
	 * @return
	 * @throws SQLException
	 */
	public int saveOnlineExamAnswerInfo(List<Onlineexamanswerinfor> onlineExamAnswerinfors) throws SQLException;
	
	/**
	 * 根据考生身份证和试卷编号删除在线考试答案表中的信息
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int deleteOnlineExamAnswerInfoBatch(Map<String,Object> condition) throws SQLException;
	
	/**
	 * 根据身份证号和试卷编号批量设置在线考试员工的得分
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int updateEmployeeInScoreBatch(Map<String,Object> condition) throws SQLException;
	
	/**
	 * 根据组合条件统计内部员工参加在线考试的次数
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public int findOnlineExamInfoCountWithCondition(Map<String,Object> condition) throws SQLException;
	
	/**
	 * 根据组合条件查询内部员工参加在线考试的信息
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public List<OnlineExamEmployeeInfo> findOnlineExamInfoWithCondition(Map<String,Object> condition) throws SQLException;
}
