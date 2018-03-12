package cn.xm.exam.service.impl.employee.out;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.EmployeeOut;
import cn.xm.exam.bean.employee.out.EmployeeOutExample;
import cn.xm.exam.bean.employee.out.Employeeoutdistribute;
import cn.xm.exam.bean.employee.out.EmployeeoutdistributeExample;
import cn.xm.exam.bean.grade.EmployeeexamExample;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.mapper.employee.out.BlacklistMapper;
import cn.xm.exam.mapper.employee.out.BreakrulesMapper;
import cn.xm.exam.mapper.employee.out.EmployeeOutMapper;
import cn.xm.exam.mapper.employee.out.EmployeeoutdistributeMapper;
import cn.xm.exam.mapper.employee.out.custom.EmployeeOutCustomMapper;
import cn.xm.exam.mapper.grade.EmployeeexamMapper;
import cn.xm.exam.mapper.haul.HaulemployeeoutMapper;
import cn.xm.exam.service.employee.out.EmployeeOutService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.vo.employee.out.EmployeeOutBaseInfo;

/**
 * 外部员工服务实现类
 * 
 * @author leilong
 * @time 2017年10月16日下午12:19:13
 */
@Service
public class EmployeeOutServiceImpl implements EmployeeOutService {
	@Resource
	private EmployeeoutdistributeMapper employeeOutDistributeMapper;
	@Resource
	private EmployeeOutCustomMapper employeeOutCustomMapper;
	@Resource
	private EmployeeOutMapper employeeOutMapper;
	@Resource
	private BreakrulesMapper breakRulesMapper;
	@Resource
	private HaulemployeeoutMapper haulEmployeeOutMapper;
	@Resource
	private EmployeeexamMapper employeeExamMapper;
	@Resource
	private BlacklistMapper blacklistMapper;
	@Override
	public String getNextEmployeeOutId(String unitId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 添加外部员工
	 * 
	 * @param emplyeeOut
	 *            员工对象
	 * @return
	 * @throws Exception
	 */
	public boolean addEmployeeOut(EmployeeOut emplyeeOut) throws Exception {
		int insert = employeeOutMapper.insert(emplyeeOut);
		return insert>0?true:false;
	}

	@Override
	public boolean deleteEmployeeOutById(String employeeOutId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployeeOut(EmployeeOut emplyeeOut) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EmployeeOut getEmployeeOutById(String employeeOutId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

	/**
	 * 分页查询:按照姓名、身份证、性别、累积积分和是否进入“黑名单”等条件的组合查询，
	 * 并以列表显示查询结果。
	 *//*
	public PageBean<EmployeeOutBaseInfo> findEmployeeOutWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception {
		PageBean<EmployeeOutBaseInfo> pageBean = new PageBean<EmployeeOutBaseInfo>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentTotal);
		int totalCount = 0 ;
		totalCount = employeeOutCustomMapper.findEmployeeOutWithCondition(condition);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount*1.0/currentTotal);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage-1)*currentTotal;
		condition.put("index", index);
		condition.put("currentCount",currentTotal);
		List<EmployeeOutBaseInfo> employeeOutInfo = employeeOutCustomMapper.findEmployeeOutBaseInfoEWithCondition(condition);
				
		pageBean.setProductList(employeeOutInfo);
		return pageBean;
	}*/
	
	/**
	 * 分页查询:按照姓名、身份证、性别、累积积分和是否进入“黑名单”等条件的组合查询，
	 * 并以列表显示查询结果。
	 */
	@Override
	public PageBean<Map<String, Object>> findEmployeeOutWithCondition(int currentPage, int currentTotal,
			Map<String, Object> condition) throws Exception {
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentTotal);
		int totalCount = 0 ;
		totalCount = employeeOutCustomMapper.findEmployeeOutWithCondition(condition);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount*1.0/currentTotal);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage-1)*currentTotal;
		condition.put("index", index);
		condition.put("currentCount",currentTotal);
		List<Map<String, Object>> findEmployeeOutBaseInfoEWithCondition = employeeOutCustomMapper.findEmployeeOutBaseInfoEWithCondition(condition);
		
		pageBean.setProductList(findEmployeeOutBaseInfoEWithCondition);
		return pageBean;
	}
	
	
	@Override
	public PageBean<EmployeeOut> findEmployeeOutByProjectId(int currentPage, int currentTotal, String projectId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 查询大修和部门的树结构
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDepartmentAndOverHaulTree(Map<String,Object> condition) throws Exception {
		List<Map<String, Object>> departAndOverHaulInfoTree = employeeOutCustomMapper.getDepartAndOverHaulInfoTree(condition);
		return departAndOverHaulInfoTree;
	}

	/**
	 * 根据外来单位员工的身份证号查询该员工所有的考试信息
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getExamsInfoByEmployeeOutIdCard(String idCard) throws Exception {	
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("idCard", idCard);
		return employeeOutCustomMapper.getExamsInfoByEmployeeOutIdCard(condition);
	}
	
	/**
	 * 根据身份证号查询外来单位员工的所有考试信息，分页显示
	 * @param currentPage
	 * @param currentCount
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public PageBean<Map<String,Object>> getExamsInfoByEmployeeOutIdCard(int currentPage, int currentCount,String idCard) throws Exception{
		
		PageBean<Map<String,Object>> pageBean = new PageBean<Map<String,Object>>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		int totalCount = 0;
		totalCount = employeeOutCustomMapper.getCountExamsInfoByEmployeeOutIdCard(idCard);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount*1.0/currentCount);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage-1)*currentCount;
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("idCard", idCard);
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> examsInfo = employeeOutCustomMapper.getExamsInfoByEmployeeOutIdCard(condition);
		pageBean.setProductList(examsInfo);		
		return pageBean;
	};

	
	/**
	 * 根据员工ID和参加员工大修ID查询员工的违章信息
	 * employeeOutId bigEmployeeOutId
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<Breakrules> getBreakRulesInfoByCondition(Map<String, Object> condition) throws Exception {
		
		return employeeOutCustomMapper.getBreakRulesInfoByCondition(condition);
	}

	/**
	 * 根据条件查询符合条件的员工信息用于生成工作证
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<EmployeeOutBaseInfo> getEmpInfoForCertificateWithCondition(Map<String, Object> condition)
			throws Exception {				
		
		//return employeeOutCustomMapper.getEmpInfoForCertificateWithCondition(condition);
		//修改为根据大修员工ID集合查询符合条件的员工信息
		return employeeOutCustomMapper.getEmpInfoForCertificateWithCondition1(condition);
	}

	/**
	 * 批量加入外部员工的基本信息及参加大修外部员工的基本信息
	 * @param employeeOutList
	 * @param haulemployeeoutList
	 * @return
	 * @throws Exception
	 */
	public int addEmployeeOutBatch(List<EmployeeOut> employeeOutList,List<Haulemployeeout> haulemployeeoutList) throws Exception {
		//创建一个员工分配表的集合
		List<Employeeoutdistribute> distributeList = new ArrayList<Employeeoutdistribute>();
		
		if(employeeOutList !=null&&employeeOutList.size()>0){
			//批量导入外来单位的员工的基本信息	
			employeeOutCustomMapper.addEmployeeOutBatch(employeeOutList);
		}
		//对集合中的元素按照身份证号进行排序
		Collections.sort(haulemployeeoutList,new Comparator<Haulemployeeout>(){
			@Override
			public int compare(Haulemployeeout o1, Haulemployeeout o2) {
				//身份证号最后一位是校验位，可能出现X的情况，将最后一位剔除后进行对比
				String str1 = o1.getEmpoutidcard().substring(0,17);
				Long first = Long.valueOf(str1);
				String str2 = o2.getEmpoutidcard().substring(0,17);
				Long second = Long.valueOf(str2);
				Long i = first - second;
				if(i>0){
					return 1;
				}else{					
					return -1;
				}
			}
		});
		List<String> employeeOutIds = findEmployeeOutIdByIdCards(haulemployeeoutList);
		//设置参加大修员工表的员工ID
		for(int i=0;i<haulemployeeoutList.size();i++){
			haulemployeeoutList.get(i).setEmployeeid(employeeOutIds.get(i));
			Employeeoutdistribute employeeOutDistribute = new Employeeoutdistribute();
			
			//初始化员工分配
			employeeOutDistribute.setBigid(haulemployeeoutList.get(i).getBigid());//大修ID
			employeeOutDistribute.setHaulempid(haulemployeeoutList.get(i).getBigemployeeoutid());//大修员工ID
			employeeOutDistribute.setUnitid(haulemployeeoutList.get(i).getUnitid());//外来单位ID
			employeeOutDistribute.setEmpoutidcard(haulemployeeoutList.get(i).getEmpoutidcard());//身份证号
			employeeOutDistribute.setEmpoutexamstatus("0");//考试状态
			employeeOutDistribute.setEmpouttraingrade("1");//考试等级
			//添加对象到集合中
			distributeList.add(employeeOutDistribute);
			
		}
		
		//批量导入参加大修外部员工的基本信息
		int empOutCount = employeeOutCustomMapper.addHaulEmployeeOutBatch(haulemployeeoutList);
		
		//调用初始化分配表的方法对其进行初始化
		addEmpOutDistributeInfoList(distributeList);		
		return empOutCount;
	}

	/**
	 * 根据身份证号查询该员工的状态
	 * 返回：1、表示没有来过   2、表示来过 3、表示进入黑名单 4、表示已经添加到这次大修的这个单位中,5表示临时进入黑名单
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	public int findEmployeeOutStatus(String idCard,String bigId,String unitId) throws Exception {
		EmployeeOutExample employeeOutExample = new EmployeeOutExample();
		EmployeeOutExample.Criteria criteria = employeeOutExample.createCriteria();
		criteria.andIdcodeEqualTo(idCard);
		List<EmployeeOut> employeeOutInfo = employeeOutMapper.selectByExample(employeeOutExample);
		if(employeeOutInfo.size()>0){
			List<EmployeeOutBaseInfo> employeeOutBaseInfoList = employeeOutCustomMapper.getEmployeeOutBaseInfo(idCard);
			if(employeeOutBaseInfoList!=null&&employeeOutBaseInfoList.size()>0){
				if(employeeOutBaseInfoList.get(0).getIsinblacklist().equals("是")){					
					if(employeeOutBaseInfoList.get(0).getIsblacklist().equals("是")){
						return 3;
					}
					return 5;
				}
				
				for (EmployeeOutBaseInfo employeeOutBaseInfo : employeeOutBaseInfoList) {
					if(employeeOutBaseInfo.getBigid().equals(bigId)&&employeeOutBaseInfo.getUnitid().equals(unitId)){
						return 4;
					}
				}
			}
			return 2;
			
		}else{			
			return 1;
		}
		
	}

	/**
	 * 根据参加大修员工身份证集合查询员工的id集合
	 * @param idCards
	 * @return
	 * @throws Exception
	 */
	public List<String> findEmployeeOutIdByIdCards(List<Haulemployeeout> haulemployeeoutList) throws Exception {
		
		return employeeOutCustomMapper.findEmployeeOutIdByIdCards(haulemployeeoutList);
	}

	/**
	 * 根据员工身份证号和大修ID，大修员工ID删除一次大修中的员工信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public boolean deleteHaulEmployeeOutInfoByCondition(Map<String, Object> condition) throws Exception {
		//获取到员工的基本信息
		EmployeeOutBaseInfo employeeOutBaseInfo = employeeOutCustomMapper.getEmployeeOutInfoByCondition(condition);
		
		//根据大修ID和身份证号查询这次大修下的员工信息
		/*HaulemployeeoutExample haulEmployeeOutExample = new HaulemployeeoutExample();
		HaulemployeeoutExample.Criteria criteriaHaulEmpOut = haulEmployeeOutExample.createCriteria();
		criteriaHaulEmpOut.andBigidEqualTo(condition.get("bigId").toString());
		criteriaHaulEmpOut.andEmpoutidcardEqualTo(condition.get("employeeOutIdCard").toString());
		List<Haulemployeeout> listInfo = haulEmployeeOutMapper.selectByExample(haulEmployeeOutExample);*/
		//判断若集合中的个数为1表示该员工在这次大修下只参加了一个单位将违章信息和黑名单记录删除，否则不删除这些信息
		/*		
  		if(listInfo.size()==1){			
			//判断该员工是否已经进入黑名单
			if(employeeOutBaseInfo.getIsinblacklist().equals("是")){
				Integer minusnum = Integer.valueOf(employeeOutBaseInfo.getMinusnum());
			Integer minusnumSum = Integer.valueOf(employeeOutBaseInfo.getMinusnumsum());
			//判断删除员工后该员工的违章积分是否够12，如果不够将其从黑名单中移除
			if((minusnumSum-minusnum)<12){
				BlacklistExample blackListExample = new BlacklistExample();
				BlacklistExample.Criteria criteriaBlackList = blackListExample.createCriteria();
				criteriaBlackList.andBlackidcardEqualTo(employeeOutBaseInfo.getIdcard());
				blacklistMapper.deleteByExample(blackListExample);
			}
				BlacklistExample blackListExample = new BlacklistExample();
				BlacklistExample.Criteria criteriaBlackList = blackListExample.createCriteria();
				criteriaBlackList.andBlackidcardEqualTo(employeeOutBaseInfo.getIdcard());
				blacklistMapper.deleteByExample(blackListExample);
			}
			
			//删除违章表中的信息		
			BreakrulesExample breakRulesExample = new BreakrulesExample();
			BreakrulesExample.Criteria criteriaBreakRules = breakRulesExample.createCriteria();
			criteriaBreakRules.andBigemployeeoutidEqualTo(employeeOutBaseInfo.getBigemployeeoutid());
			breakRulesMapper.deleteByExample(breakRulesExample);
		}*/
				
		
		//根据大修员工ID删除成绩表中的信息
		EmployeeexamExample employeeExamExample = new EmployeeexamExample();
		EmployeeexamExample.Criteria criteriaEmployeeExamInfo = employeeExamExample.createCriteria();
		criteriaEmployeeExamInfo.andBigemployeeoutidEqualTo(employeeOutBaseInfo.getBigemployeeoutid());
		employeeExamMapper.deleteByExample(employeeExamExample);
		
		
		//根据大修员工ID删除分配表的信息
		EmployeeoutdistributeExample  employeeOutDistributeExample = new EmployeeoutdistributeExample();
		EmployeeoutdistributeExample.Criteria criteriaEmpOut = employeeOutDistributeExample.createCriteria();
		criteriaEmpOut.andHaulempidEqualTo(employeeOutBaseInfo.getBigemployeeoutid());
		employeeOutDistributeMapper.deleteByExample(employeeOutDistributeExample);
		
		//删除参加大修员工表中的信息
		int deleteHaulEmployeeOutInfo = haulEmployeeOutMapper.deleteByPrimaryKey(employeeOutBaseInfo.getBigemployeeoutid());
		
		return deleteHaulEmployeeOutInfo>0?true:false;
	}

	/**
	 * 根据大修员工ID修改外来单位员工的信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public boolean updateHaulEmployeeOutInfoByCondition(Map<String, Object> condition) throws Exception {
		int update = employeeOutCustomMapper.updateHaulEmployeeOutInfoByCondition(condition);
		return update>0?true:false;
	}
	/**
	 * 初始化外来单位员工分配表
	 * @param distributeInfoList
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	@Override
	public int addEmpOutDistributeInfoList(List<Employeeoutdistribute> distributeInfoList) throws SQLException{
		//查询安排一级考试的部门ID
		String departmentId = employeeOutCustomMapper.getDictionaryInfoById(DefaultValue.DICTIONARY_ID);
		//设置外来单位员工分配部门ID
		for (Employeeoutdistribute employeeoutdistribute : distributeInfoList) {
			employeeoutdistribute.setDepartmentid(departmentId);
		}
		int count = employeeOutCustomMapper.addEmpOutDistributeInfoList(distributeInfoList);
		//设置员工分配表的员工姓名
		employeeOutCustomMapper.updateEmployeeOutNameByIdCard();
		return count;
	}
	
	@Override
	public List<Map<String,Object>> getExamEmployeeOuts(Map condition) throws SQLException {
		return employeeOutCustomMapper.getExamEmployeeOuts(condition);
	}
	
	/**
	 * 根据大修员工ID集合和状态码修改大修员工表中的培训状态字段
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@Override
	public int updateHaulEmployeeOutTrainStatusByCondition(Map<String, Object> condition) throws SQLException {
		return employeeOutCustomMapper.updateHaulEmployeeOutTrainStatusByCondition(condition);
	}



	

}
