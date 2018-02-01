package cn.xm.exam.service.exam.examPaper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.utils.PageBean;

//完

/**
 * 考试试卷Service
 * 
 * @author QiaoLiQiang
 * @time 2017年8月9日下午5:23:29
 */
public interface ExamPaperService {

	/**
	 * 查询下次插入的试卷的id
	 * 
	 * @param unitId
	 * @return
	 * @throws Exception
	 */
	public String getNextPaperId() throws SQLException;

	/**
	 * 增加试卷基本信息
	 * 
	 * @param exampaper
	 *            试卷基本信息
	 * @return 添加结果
	 * @throws SQLException
	 */
	public boolean addExamPaperBaseInfo(Exampaper exampaper) throws SQLException;

	/**
	 * 添加试卷(同时添加试卷的大题,题与选项)
	 * 
	 * @param examPaper
	 *            试卷基本信息
	 * @param bigQues
	 *            考试大题(有试题与选项集合)
	 * @return
	 * @throws Exception
	 */
	public boolean addExamPaper(Exampaper examPaper, Map<String, Object> bigQues) throws SQLException;

	/**
	 * 根据试卷ID删除试卷的基本信息
	 * 
	 * @param examPaperId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteExamPaperBaseInfoByPaperId(String examPaperId) throws SQLException;

	/**
	 * 删除一份试卷的所有信息(选项，题，大题，试卷基本信息)
	 * 
	 * @param examPaperId
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteExamPaperAllInfoByPaperId(String examPaperId) throws SQLException;

	/**
	 * 批量删除试卷
	 * 
	 * @param examPaperIds
	 * @return
	 * @throws Exception
	 */
	public boolean deleteExamPaperBatch(List<String> examPaperIds) throws SQLException;

	/**
	 * 根据试卷ID修改试卷(基本信息与大题信息同时修改)
	 * 
	 * @param examPaper
	 * @param bigQues
	 * @return
	 * @throws SQLException
	 */
	public boolean updateExamPaperAllInfoById(Exampaper examPaper, Map<String, Object> bigQues) throws SQLException;

	/**
	 * 修改考试试卷的基本信息
	 * 
	 * @param examPaper
	 * @return
	 * @throws Exception
	 */

	public boolean updateExamPaperById(Exampaper examPaper) throws SQLException;

	/**
	 * 根据试卷的ID修改试卷题与选项
	 * 
	 * @param examPaperId
	 * @param paperAndQuestion
	 * @return
	 * @throws SQLException
	 */
	public boolean updateExamPaperQuestionById(String examPaperId, List<Map<String, Object>> paperAndQuestion)
			throws SQLException;

	/**
	 * 分页查询试卷的基本信息(只查询试卷基本信息)
	 * 
	 * @param currentPage
	 *            当前页
	 * @param currentTotal
	 *            当前页数量
	 * @param condition
	 *            组合条件，可以根据当前的用户的employeeId查询他自己创建的
	 * @return 包括试卷基本信息、创建试卷人的基本信息
	 * @throws Exception
	 */
	public PageBean<Exampaper> findExampapersWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws SQLException;

	/**
	 * 根据试卷ID查询试卷的所有信息(试卷基本信息，大题，题，选项)
	 * 
	 * @param paperId
	 *            试卷ID
	 * @return
	 * @throws SQLException
	 */
	public Exampaper getPaperAllInfoByPaperId(String paperId) throws SQLException;

	/**
	 * 根据Id获取单个的试卷的基本信息及其创建人的信息(只查询试卷与创建人的基本信息)
	 * 
	 * @param paperId
	 *            试卷id
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getExamPapeInfo(String paperId) throws SQLException;

	/**
	 * 根据试卷id查询试卷的详细信息
	 * 
	 * @param paperId
	 *            试卷id
	 * @return 试卷的题目(包含选项)
	 * @throws Exception
	 */
	public List<Map<String, Object>> getExamPaperByPaperId(String paperId) throws SQLException;

	// dao应该查询的是examPaperQuestion表
	/**
	 * 根据试卷的id导出试卷的答案与解析
	 * 
	 * @param papaerId
	 *            试卷id
	 * @return 一个map为一道题的序号，答案，解析
	 * @throws Exception
	 */
	public List<Map<String, Object>> getExamPaperAnswer(String papaerId) throws SQLException;

	// 复制试卷
	
	
	/**
	 * 动态修改试卷的状态(归档与未归档)
	 * @param paperId	试卷编号
	 * @param paperStatus	要修改的状态
	 * @return	
	 * @throws SQLException
	 */
	public boolean updateExampaperPaperanswer(String paperId,String paperStatus) throws SQLException;
}
