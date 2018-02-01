package cn.xm.exam.service.impl.exam.exam;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.exam.Onlineexaminfor;
import cn.xm.exam.bean.exam.OnlineexaminforExample;
import cn.xm.exam.mapper.exam.OnlineexaminforMapper;
import cn.xm.exam.mapper.exam.custom.OnlineexaminfoCustomMapper;
import cn.xm.exam.service.exam.exam.OnlineExamService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.vo.grade.OnlineExamEmployeeInfo;

/**   
*    
* 项目名称：Exam   
* 类名称：OnlineExamServiceImpl   
* 类描述：在线考试service的实现类   
* 创建人：Leilong  
* 创建时间：2017年10月28日 下午4:07:07      
* @version    
*    
*/
@Service
@Transactional
public class OnlineExamServiceImpl implements OnlineExamService {
	
	@Resource
	private OnlineexaminfoCustomMapper onlineExamInfoCustomMapper;
	@Resource
	private OnlineexaminforMapper onlineExamInforMapper;
	
	/**
	 * 添加一条在线考试员工的在线考试信息,先将原来的信息删除
	 * @param onlineExamInfo
	 * @return
	 * @throws Exception
	 */
	public boolean saveOnlineExamInfo(Onlineexaminfor onlineExamInfo) throws Exception {
		OnlineexaminforExample onlineExamInforExample = new OnlineexaminforExample();
		OnlineexaminforExample.Criteria criteria = onlineExamInforExample.createCriteria();
		criteria.andEmployeeidEqualTo(onlineExamInfo.getEmployeeid());
		criteria.andExamidEqualTo(onlineExamInfo.getExamid());
		List<Onlineexaminfor> employeeAnswerInfoList = onlineExamInforMapper.selectByExample(onlineExamInforExample);
		//判断在线考试记录表中是否有值
		if(employeeAnswerInfoList.size()>0){
			//判断参数的结束答题时间是否有值
			if(onlineExamInfo.getEndtime()!=null && !"".equals(onlineExamInfo.getEndtime())){
				//设置开始答题时间和登录时间
				onlineExamInfo.setStarttime(employeeAnswerInfoList.get(0).getStarttime());
				//onlineExamInfo.setLogintime(employeeAnswerInfoList.get(0).getLogintime());
				onlineExamInforMapper.deleteByExample(onlineExamInforExample);
				int isInsert = onlineExamInforMapper.insert(onlineExamInfo);
				return isInsert>0 ? true:false;
			}else{				
				return true;
			}
		}else{			
			int isInsert = onlineExamInforMapper.insert(onlineExamInfo);
			return isInsert>0 ? true:false;
		}
	}

	@Override
	public boolean saveOnlineExamInfoBatch(List<Onlineexaminfor> onlineExamInfo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * 根据组合条件分页查询当前员工的考试信息 
	 * @param currentPage
	 * @param currentTotal
	 * @param condition 考试名称，考试级别，考试状态，考试时间，身份证号
	 * @return 在线考试信息
	 * @throws Exception
	 */
	public PageBean<OnlineExamEmployeeInfo> findOnlineExamInfoWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception {
		
		PageBean<OnlineExamEmployeeInfo> pageBean = new PageBean<OnlineExamEmployeeInfo>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentTotal);
		int totalCount = 0;
		totalCount = onlineExamInfoCustomMapper.findOnlineExamInfoCountWithCondition(condition);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount*1.0/currentTotal);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage-1)*currentTotal;
		condition.put("index", index);
		condition.put("currentCount",currentTotal);
		List<OnlineExamEmployeeInfo> list = onlineExamInfoCustomMapper.findOnlineExamInfoWithCondition(condition);
		pageBean.setProductList(list);
		
		return pageBean;
	}

	@Override
	public Integer getTimeToExamStart(String examId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTimeToExamEnd(String examId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageBean<Map<String, Object>> findOnlineExamInfoByExamId(String examId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据考生的考号即身份证号获取当前考生的所有未开始且尚未交卷的考试信息
	 * @return
	 * @throws Exception
	 */
	public List<OnlineExamEmployeeInfo> getNotStartExamInfoByIdCard(String idCard) throws Exception {
		return onlineExamInfoCustomMapper.getNotStartExamInfoByIdCard(idCard);
	}

	/**
	 * 根据考试编号查询在线考试的相关信息
	 * 查询信息：考试编号，考试名称，试卷编号，开始结束时间
	 * @param examId
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findExamInfoByExamId(String examId) throws Exception {
		return onlineExamInfoCustomMapper.findExamInfoByExamId(examId);
	}

	/**
	 * 根据试卷的id和员工的身份证号查询试卷的所有信息，包括在线考试员工的试题答案
	 * @param paperId
	 * @return
	 * @throws Exception
	 */
	public Exampaper findExamPaperInfoByPaperId(Map<String,Object> condition) throws Exception {
		return onlineExamInfoCustomMapper.findExamPaperInfoByPaperId(condition);
	}

}
