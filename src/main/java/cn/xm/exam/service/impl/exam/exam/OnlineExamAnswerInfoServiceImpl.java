package cn.xm.exam.service.impl.exam.exam;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xm.exam.bean.exam.Onlineexamanswerinfor;
import cn.xm.exam.mapper.exam.OnlineexamanswerinforMapper;
import cn.xm.exam.mapper.exam.custom.OnlineexaminfoCustomMapper;
import cn.xm.exam.service.exam.exam.OnlineExamAnswerInfoService;

/**   
*    
* 项目名称：Exam   
* 类名称：OnlineExamAnswerInfoServiceImpl   
* 类描述：在线考试考生答案保存和判断的service的实现类 
* 创建人：Leilong   
* 创建时间：2017年10月28日 下午4:09:16    
* @version    
*    
*/
@Service
@Transactional
public class OnlineExamAnswerInfoServiceImpl implements OnlineExamAnswerInfoService {
	
	@Resource
	private OnlineexaminfoCustomMapper onlineExamInfoCustomMapper;
	@Resource
	private OnlineexamanswerinforMapper onlineExamAnswerInforMapper;
	/**
	 * 保存考生在线答题答案(得分置空)
	 * 
	 * @param onlineExamAnswerinfors
	 *            交回的在线答题信息
	 * @return
	 * @throws Exception
	 */
	public boolean saveOnlineExamAnswerInfo(List<Onlineexamanswerinfor> onlineExamAnswerinfors) throws Exception {
		int isSave = onlineExamInfoCustomMapper.saveOnlineExamAnswerInfo(onlineExamAnswerinfors);
		return isSave>0 ? true:false;
	}

	@Override
	public boolean updateOnlineExamAnswerInfoBatch(List<Onlineexamanswerinfor> onlineExamAnswerinfors)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据用户的考号即身份证号，试卷编号,考试编号批量删除信息(删除后重新添加传上来的信息)
	 * 
	 * @param conditon
	 * @return
	 * @throws Exception
	 */
	public boolean deleteOnlineExamAnswerInfoBatch(Map<String, Object> condition) throws Exception {
		int isDelete = onlineExamInfoCustomMapper.deleteOnlineExamAnswerInfoBatch(condition);
		return isDelete>0 ? true:false;
	}

	@Override
	public boolean updateOnlineExamAnswerInfo(Onlineexamanswerinfor onlineExamAnswerinfors) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据试卷编号和员工考号批量修改在线考试员工的试题得分
	 * @param conditon
	 * @return
	 * @throws Exception
	 */
	public boolean updateEmployeeInScoreBatch(Map<String, Object> condition) throws Exception {
		int isUpdate = onlineExamInfoCustomMapper.updateEmployeeInScoreBatch(condition);
		return isUpdate>0 ? true:false;
	}

}
