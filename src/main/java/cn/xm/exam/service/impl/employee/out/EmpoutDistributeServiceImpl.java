package cn.xm.exam.service.impl.employee.out;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.employee.out.Employeeoutdistribute;
import cn.xm.exam.bean.haul.Haulemployeeout;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.mapper.employee.out.EmployeeoutdistributeMapper;
import cn.xm.exam.mapper.employee.out.custom.EmpoutDistributeCustomMapper;
import cn.xm.exam.mapper.haul.HaulemployeeoutMapper;
import cn.xm.exam.service.employee.out.EmpoutDistributeService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;

@Service
@SuppressWarnings("all")
public class EmpoutDistributeServiceImpl implements EmpoutDistributeService {
	@Autowired
	private EmpoutDistributeCustomMapper empoutDistributeCustomMapper;

	@Override
	public List<Map<String, Object>> getHaulunitTreeByDepartmentId(Map condition) throws SQLException {
		return empoutDistributeCustomMapper.getHaulunitTreeByDepartmentId(condition);
	}

	@Override
	public PageBean<Map<String, Object>> getDistributeInfoWithCondition(int currentPage, int currentCount,
			Map condition) throws SQLException {
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		// 1.设置当前页
		pageBean.setCurrentPage(currentPage);
		// 2.设置页大小
		pageBean.setCurrentCount(currentCount);
		// 3.查询总数并设置总数
		int totalCount = empoutDistributeCustomMapper.getEmpoutDistributeInfoCountByCondition(condition);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 4.计算索引并请求数据
		int index = (currentPage - 1) * currentCount;
		// 5.将索引放入条件查询数据
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> productList = empoutDistributeCustomMapper.getEmpoutDistributeInfo(condition);
		pageBean.setProductList(productList);
		return pageBean;
	}

	@Override
	public Map<String, Object> getUintInfoByHaulIdAndUnitId(String bigId, String unitId) throws SQLException {
		return empoutDistributeCustomMapper.getUintInfoByHaulIdAndUnitId(bigId, unitId);
	}

	@Override
	public List<Map<String, Object>> getDepartmentTreeForFenpei(String departmentId) throws SQLException {
		return empoutDistributeCustomMapper.getDepartmentTreeForFenpei(departmentId);
	}

	@Override
	public boolean addFenpeiInfoBatch(List<Employeeoutdistribute> list) throws SQLException {
		if (list == null) {
			return false;
		}
		for (int i = 0, length_1 = list.size(); i < length_1; i++) {
			if (list.get(i).getDepartmentid().length() == 5 && !"01002".equals(list.get(i).getDepartmentid())) {
				list.get(i).setEmpoutexamstatus("0");
				list.get(i).setEmpouttraingrade("2");
			}
			if (list.get(i).getDepartmentid().length() == 8) {
				list.get(i).setEmpoutexamstatus("0");
				list.get(i).setEmpouttraingrade("3");
			}
		}
		return empoutDistributeCustomMapper.addFenpeiInfoBatch(list) > 0 ? true : false;
	}

	@Override
	public boolean updateFenpeiInfo(String departmentIds, Employeeoutdistribute employeeoutdistribute)
			throws SQLException {
		String[] departmentId_str = null;
		if (ValidateCheck.isNotNull(departmentIds)) {
			departmentId_str = departmentIds.split(",");
		}
		int departmentLength = 0;
		if (departmentId_str != null && departmentId_str.length > 0) {
			departmentLength = departmentId_str[0].length();
		}
		String departmentLevel = departmentLength == 5 ? "2" : "3";
		// 删掉
		Map condition = new HashMap();
		condition.put("haulempid", employeeoutdistribute.getHaulempid());
		condition.put("deplevel", departmentLevel);
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		condition.put("departmentId", departmentIdSession);
		empoutDistributeCustomMapper.deleteFenpeiInfoByHaulempIdAndDepartmentLevel(condition);
		// 重新添加
		for (int i = 0; i < departmentId_str.length; i++) {
			employeeoutdistribute.setDepartmentid(departmentId_str[i]);
			List fenpeis = new ArrayList();
			fenpeis.add(employeeoutdistribute);
			this.addFenpeiInfoBatch(fenpeis);
		}
		return true;
	}
	/**************二次分配信息***************/
	@Autowired
	private HaulemployeeoutMapper haulemployeeoutMapper;
	@Autowired
	private EmployeeoutdistributeMapper employeeoutdistributeMapper;
	@Override
	public boolean addSecondFenpeiInfoBatch(List<Haulemployeeout> haulemployeeouts,
			List<Employeeoutdistribute> employeeoutdistributes) throws SQLException{
		if(haulemployeeouts==null || employeeoutdistributes==null){
			return false;
		}
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		Haulemployeeout haulemployeeout=null;
		Employeeoutdistribute employeeoutdistribute=null;
		String haulEmpId = null;
		for(int i=0,length=haulemployeeouts.size();i<length;i++){
			haulemployeeout = haulemployeeouts.get(i);
			employeeoutdistribute=employeeoutdistributes.get(i);
			haulEmpId = UUIDUtil.getUUID2();
			haulemployeeout.setBigemployeeoutid(haulEmpId);
			haulemployeeout.setTrainstatus("0");
			
			//1.插入大修员工信息
			haulemployeeoutMapper.insert(haulemployeeout);
			//2.插入分配信息
			employeeoutdistribute.setHaulempid(haulEmpId);
			//2.1判断分配的部门等级
			//2.1.1如果充分的是三级部门
			if (employeeoutdistribute.getDepartmentid().length() == 8) {
				//添加三级
				employeeoutdistribute.setEmpoutexamstatus("0");
				employeeoutdistribute.setEmpouttraingrade("3");
				employeeoutdistributeMapper.insert(employeeoutdistribute);
				//添加2级
				employeeoutdistribute.setEmpoutexamstatus("1");
				employeeoutdistribute.setEmpouttraingrade("2");
				employeeoutdistribute.setDepartmentid(departmentIdSession);
				employeeoutdistributeMapper.insert(employeeoutdistribute);
			}
			//2.1.2如果是二级部门
			if (departmentIdSession.length() == 5 && "01002".equals(departmentIdSession)) {
				//添加2级
				employeeoutdistribute.setEmpoutexamstatus("0");
				employeeoutdistribute.setEmpouttraingrade("2");
				employeeoutdistributeMapper.insert(employeeoutdistribute);
			}
			//添加1级
			employeeoutdistribute.setEmpoutexamstatus("1");
			employeeoutdistribute.setEmpouttraingrade("1");
			employeeoutdistribute.setDepartmentid("01002");
			employeeoutdistributeMapper.insert(employeeoutdistribute);
		}
		return  true;
	}

	
	/*****二次分配班组信息*********/
	@Override
	public boolean addSecondFenpeiUnitBatch(List<Haulemployeeout> haulemployeeouts,
			List<Employeeoutdistribute> employeeoutdistributes) throws SQLException {
		if(haulemployeeouts==null || employeeoutdistributes==null){
			return false;
		}
		Haulemployeeout haulemployeeout=null;
		Employeeoutdistribute employeeoutdistribute=null;
		String haulEmpId="";
		for(int i=0,length=haulemployeeouts.size();i<length;i++){
			haulemployeeout = haulemployeeouts.get(i);
			employeeoutdistribute=employeeoutdistributes.get(i);
			//1.向检修单位员工中添加一条记录
			haulEmpId = UUIDUtil.getUUID2();
			haulemployeeout.setBigemployeeoutid(haulEmpId);//设置大修员工ID
			haulemployeeout.setTrainstatus("0");
			haulemployeeoutMapper.insert(haulemployeeout);
			//2.分配表中加一条记录
			employeeoutdistribute.setHaulempid(haulEmpId);
			employeeoutdistribute.setEmpoutexamstatus("1");
			employeeoutdistribute.setEmpouttraingrade("1");
			employeeoutdistribute.setDepartmentid("01002");
			employeeoutdistributeMapper.insert(employeeoutdistribute);
		}
		return true;
		
	}

}
