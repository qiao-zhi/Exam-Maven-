package cn.xm.exam.service.exam.exam;


import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.exam.Onlineexaminfor;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.vo.grade.OnlineExamEmployeeInfo;

/**
 * 在线考试service接口
 * 
 * @author QiaoLiQiang
 * @time 2017年8月10日下午4:38:15
 */
public interface OnlineExamService {

	// 一条一条添加
	/**
	 * 添加一条在线考试员工的在线考试信息
	 * 
	 * @param onlineExamInfo
	 * @return
	 * @throws Exception
	 */
	public boolean saveOnlineExamInfo(Onlineexaminfor onlineExamInfo) throws Exception;

	// 批量添加
	/**
	 * 批量添加在线考试员工的在线考试信息
	 * 
	 * @param onlineExamInfo
	 * @return
	 * @throws Exception
	 */
	public boolean saveOnlineExamInfoBatch(List<Onlineexaminfor> onlineExamInfo) throws Exception;

	/**
	 * 根据组合条件分页查询当前员工的考试信息 
	 * @param currentPage
	 * @param currentTotal
	 * @param condition 考试名称，考试级别，考试状态，考试时间，身份证号
	 * @return 在线考试信息
	 * @throws Exception
	 */
	public PageBean<OnlineExamEmployeeInfo> findOnlineExamInfoWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception;

	/**
	 * 根据考试的id获取距离考试的时间
	 * 
	 * @param examId
	 *            考试的id
	 * @return 距离考试开考的分钟数
	 * @throws Exception
	 */
	public Integer getTimeToExamStart(String examId) throws Exception;

	/**
	 * 根据考试的id获取距离考试结束时间的分钟数
	 * 
	 * @param examId
	 *            考试的id
	 * @return 距离考试结束的分钟数
	 * @throws Exception
	 */
	public Integer getTimeToExamEnd(String examId) throws Exception;

	// 监考中心
	/**
	 * 根据考试编号获取在线考试的员工信息(监考中心)
	 * 
	 * @param examId
	 *            考试的id
	 * @return 一个map代表一个在线考试的员工的基本信息，应该包括员工的基本信息以及在线考试基本信息
	 * @throws Exception
	 */
	public PageBean<Map<String, Object>> findOnlineExamInfoByExamId(String examId) throws Exception;
	
	/******************************************leilong添加的***********************************************/
	
	/**
	 * 根据考生的考号即身份证号获取当前考生的所有未开始的考试信息
	 * @return
	 * @throws Exception
	 */
	public List<OnlineExamEmployeeInfo> getNotStartExamInfoByIdCard(String idCard) throws Exception;
	
	
	/**
	 * 根据考试编号查询在线考试的相关信息
	 * 查询信息：考试编号，考试名称，试卷编号，开始结束时间
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> findExamInfoByExamId(String examId) throws Exception;
	
	/**
	 * 根据试卷的id和员工的身份证号查询试卷的所有信息，包括在线考试员工的试题答案
	 * @param paperId
	 * @return
	 * @throws Exception
	 */
	public Exampaper findExamPaperInfoByPaperId(Map<String,Object> condition) throws Exception;
	
}