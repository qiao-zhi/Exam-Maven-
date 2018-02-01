package cn.xm.exam.service.exam.examPaper;

import java.sql.SQLException;
import java.util.List;

import cn.xm.exam.bean.exam.Bigquestion;

/**
 * 大题Service
 * 
 * @author QiaoLiQiang
 * @time 2017年9月18日上午8:20:25
 */
public interface BigQuestionService {

	/**
	 * 增加大题
	 * 
	 * @param bigQuestion
	 * @return
	 * @throws SQLException
	 */
	public boolean addBigQuestion(Bigquestion bigQuestion) throws SQLException;

	/**
	 * 根据大题编号删除大题
	 * 
	 * @param bigQuestionId
	 *            大题编号
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBigQuestionById(String bigQuestionId) throws SQLException;

	/**
	 * 根据试卷ID删除大题
	 * 
	 * @param paperId
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteBigQuestionByPaperId(String paperId) throws SQLException;

	/**
	 * 根据大题编号修改大题
	 * 
	 * @param bigQuestion
	 *            修改后的大题对象
	 * @return
	 * @throws SQLException
	 */
	public boolean updateBigQuestionById(Bigquestion bigQuestion) throws SQLException;

	/**
	 * 根据大题编号查询大题
	 * 
	 * @param bigQuestionId
	 * @return
	 * @throws SQLException
	 */
	public Bigquestion getBigQuestionById(String bigQuestionId) throws SQLException;

	/**
	 * 根据试卷查询大题
	 * 
	 * @param papaerId
	 * @return
	 * @throws SQLException
	 */
	public List<Bigquestion> getBigQuestionsByPaperId(String papaerId) throws SQLException;
}
