package cn.xm.exam.service.exam.examPaper;

import java.sql.SQLException;
import java.util.List;

import cn.xm.exam.bean.exam.Exampaperoption;

/**
 * 试卷选项Service
 * 
 * @author QiaoLiQiang
 * @time 2017年9月18日上午8:28:54
 */
public interface ExamPaperOptionService {

	/**
	 * 增加考试选项
	 * 
	 * @param exampaperoption
	 * @return
	 * @throws SQLException
	 */
	public boolean addPaperOption(Exampaperoption exampaperoption) throws SQLException;

	/**
	 * 批量添加选项
	 * 
	 * @param exampaperoptions
	 * @return
	 * @throws SQLException
	 */
	public boolean addPaperOptionBatch(List<Exampaperoption> exampaperoptions) throws SQLException;

	/**
	 * 根据试题的id删除试题选项
	 * 
	 * @param paperQuestionId
	 * @return
	 * @throws SQLException
	 */
	public boolean deletePaperOptionByQuestionId(String paperQuestionId) throws SQLException;

	/**
	 * 根据试卷ID删除选项
	 * 
	 * @param paperId
	 * @return
	 * @throws SQLException
	 */
	public boolean deletePaperOptionByPaperId(String paperId) throws SQLException;
}
