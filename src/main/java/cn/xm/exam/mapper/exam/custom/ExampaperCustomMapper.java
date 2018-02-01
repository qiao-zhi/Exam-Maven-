package cn.xm.exam.mapper.exam.custom;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.exam.Exampaper;

public interface ExampaperCustomMapper {
	/**
	 * 查询满足条件的试卷总数
	 * 
	 * @param condition
	 * @return
	 * @throws SQLException
	 */
	public Integer getPaperTotalByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 分页查询试卷信息
	 * 
	 * @param condition
	 *            应包含页号，每页大小，以及查询条件
	 * @return
	 * @throws SQLException
	 */
	public List<Exampaper> findPapersByCondition(Map<String, Object> condition) throws SQLException;

	/**
	 * 查询试卷基本信息，大题信息，题信息，选项信息
	 * 
	 * @param paperId
	 *            试卷ID
	 * @return 试卷
	 * @throws SQLException
	 */
	public Exampaper findExamPaperAllInfoByPaperId(String paperId) throws SQLException;

	/**
	 * 根据试卷ID增加试卷的使用次数
	 * 
	 * @param paperId
	 * @return
	 * @throws SQLException
	 */
	public int addExampaperUsetimes(String paperId) throws SQLException;

	/**
	 * 根据试卷ID减一试卷的使用次数
	 * 
	 * @param paperId
	 *            试卷ID
	 * @return
	 * @throws SQLException
	 */
	public int minusExampaperUsetimes(String paperId) throws SQLException;
	/**
	 * 动态修改试卷的归档与未归档状态(试卷答案用来标记归档与未归档)
	 * @param paperId
	 * @param paperStatus
	 * @return
	 * @throws SQLException
	 */
	public int updateExampaperPaperanswer(@Param("paperId")String paperId,@Param("paperStatus")String paperStatus) throws SQLException;

}
