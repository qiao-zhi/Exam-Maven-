package cn.xm.exam.service.impl.employee.out;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.bean.employee.in.EmplyinBreakrules;
import cn.xm.exam.bean.employee.in.EmplyinBreakrulesExample;
import cn.xm.exam.bean.employee.out.Blacklist;
import cn.xm.exam.bean.employee.out.BlacklistExample;
import cn.xm.exam.bean.employee.out.Breakrules;
import cn.xm.exam.bean.employee.out.BreakrulesExample;
import cn.xm.exam.mapper.employee.in.EmplyinBreakrulesMapper;
import cn.xm.exam.mapper.employee.out.BlacklistMapper;
import cn.xm.exam.mapper.employee.out.BreakrulesMapper;
import cn.xm.exam.mapper.employee.out.custom.BlackListCustomMapper;
import cn.xm.exam.service.employee.out.BlackListEmpOutService;
import cn.xm.exam.utils.PageBean;

 /*      
  * 项目名称：Exam   
  * 类名称：BlackListEmpOutServiceImpl   
  * 类描述：  外部员工黑名单的实现类
  * 创建人：LL   
  * 创建时间：2018年1月25日 下午7:26:58     
  * @version    
  *    
  */
@Service
public class BlackListEmpOutServiceImpl implements BlackListEmpOutService {
	
	@Resource 
	private BlacklistMapper blackListMapper;
	@Resource
	private BlackListCustomMapper blackListCustomMapper;
	@Resource
	private BreakrulesMapper breakRulesMapper;
	@Resource
	private EmplyinBreakrulesMapper emplyInBreakRulesMapper;
	
	
	@Override
	public PageBean<Map<String, Object>> getBlackEmployeePage(int currentPage, int currentCount) throws SQLException {
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentPage(currentPage);
		pageBean.setCurrentCount(currentCount);
		
		BlacklistExample blackListExample = new BlacklistExample();
		BlacklistExample.Criteria criteria = blackListExample.createCriteria();
		//表示永久进入黑名单
		criteria.andTemporaryinstatusEqualTo("1");
		//表示外来短委员工
		criteria.andEmployeestatusEqualTo("1");	
		int totalCount = blackListMapper.countByExample(blackListExample);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(totalCount*1.0/currentCount);
		pageBean.setTotalPage(totalPage);
		int index = (currentPage-1)*currentCount;
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("index", index);
		condition.put("currentCount",currentCount);
		List<Map<String,Object>> productList = blackListCustomMapper.getBlackEmployeeListInfo(condition);
		pageBean.setProductList(productList);
		return pageBean;
	}

	@Override
	public boolean deleteBlackListInfoById(String id) throws SQLException {
		Blacklist blackListInfo = blackListMapper.selectByPrimaryKey(Integer.valueOf(id));
		String employeeid = blackListInfo.getEmployeeid();
		
		if(blackListInfo.getEmployeestatus().equals("1")){			
			//短委 删除当年的违章信息
			blackListCustomMapper.deleteBreakRulesInfo(employeeid);
		}else{
			//长委 删除当年的违章信息
			blackListCustomMapper.deleteEmpInBreakRulesInfo(employeeid);
		}		
		return blackListMapper.deleteByPrimaryKey(Integer.valueOf(id))>0?true:false;
	}

	@Override
	public Map<String, Object> getBreakRulesInfoList(String employeeId, String employeeType) throws SQLException {
		
		Map<String,Object> map = new HashMap<String,Object>();
			
		if(employeeType.equals("0")){
			/*
			EmplyinBreakrulesExample emplyinBreakrules = new EmplyinBreakrulesExample();
			EmplyinBreakrulesExample.Criteria emplyInCriteria = emplyinBreakrules.createCriteria();
			emplyInCriteria.andEmpinemployeeidEqualTo(employeeId);			
			List<EmplyinBreakrules> list = emplyInBreakRulesMapper.selectByExample(emplyinBreakrules);*/
			List<EmplyinBreakrules> list = blackListCustomMapper.getEmplyInBreakrulesById(employeeId);
			
			map.put("list", list);
			return map;
		}else{
			/*BreakrulesExample breakRulesExample = new BreakrulesExample();
			BreakrulesExample.Criteria emplyOutCriteria = breakRulesExample.createCriteria();
			emplyOutCriteria.andEmployeeidEqualTo(employeeId);
			List<Breakrules> list = breakRulesMapper.selectByExample(breakRulesExample);
			*/
			List<Breakrules> list = blackListCustomMapper.getEmplyOutBreakrulesById(employeeId);
			map.put("list", list);
			return map;
		}
						
	}

}
