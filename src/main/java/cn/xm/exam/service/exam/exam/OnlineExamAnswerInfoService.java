package cn.xm.exam.service.exam.exam;


import java.util.List;
import java.util.Map;

import cn.xm.exam.bean.exam.Onlineexamanswerinfor;

/**
 * 在线答题信息Service(考生答题答案)
 * 
 * @author QiaoLiQiang
 * @time 2017年8月10日下午4:58:20
 */
public interface OnlineExamAnswerInfoService {

	
	/**
	 * 保存考生在线答题答案(得分置空)
	 * 
	 * @param onlineExamAnswerinfors
	 *            交回的在线答题信息
	 * @return
	 * @throws Exception
	 */
	public boolean saveOnlineExamAnswerInfo(List<Onlineexamanswerinfor> onlineExamAnswerinfors) throws Exception;

	/**
	 * 修改考生在线答题答案(批量修改，根据员工id，试卷编号，题号修改答案与得分情况)
	 * 
	 * @param onlineExamAnswerinfors
	 *            交回的在线答题信息
	 * @return
	 * @throws Exception
	 */
	public boolean updateOnlineExamAnswerInfoBatch(List<Onlineexamanswerinfor> onlineExamAnswerinfors) throws Exception;

	/**
	 * 根据用户的考号即身份证号，试卷编号批量删除信息(删除后重新添加传上来的信息)
	 * 
	 * @param conditon
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOnlineExamAnswerInfoBatch(Map<String, Object> condition) throws Exception;

	// 意义不大
	/**
	 * 修改单个答题信息
	 * 
	 * @param onlineExamAnswerinfors
	 * @return
	 * @throws Exception
	 */
	public boolean updateOnlineExamAnswerInfo(Onlineexamanswerinfor onlineExamAnswerinfors) throws Exception;

	/******************************************leilong添加的***********************************************/
	
	/**
	 * 根据试卷编号和员工考号批量修改在线考试员工的试题得分
	 * @param conditon
	 * @return
	 * @throws Exception
	 */
	public boolean updateEmployeeInScoreBatch(Map<String, Object> condition) throws Exception;
}
