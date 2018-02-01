package cn.xm.exam.service.impl.employee.out;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.employee.out.Unit;
import cn.xm.exam.bean.employee.out.UnitExample;
import cn.xm.exam.bean.haul.Haulunit;
import cn.xm.exam.bean.haul.HaulunitExample;
import cn.xm.exam.bean.haul.Haulunitproject;
import cn.xm.exam.bean.haul.HaulunitprojectExample;
import cn.xm.exam.bean.haul.HaulunitprojectExample.Criteria;
import cn.xm.exam.mapper.employee.out.UnitMapper;
import cn.xm.exam.mapper.employee.out.custom.UnitCustomMapper;
import cn.xm.exam.mapper.haul.HaulunitMapper;
import cn.xm.exam.mapper.haul.HaulunitprojectMapper;
import cn.xm.exam.mapper.haul.custom.HaulemployeeoutCustomMapper;
import cn.xm.exam.mapper.haul.custom.HaulunitCustomMapper;
import cn.xm.exam.service.employee.out.EmployeeOutService;
import cn.xm.exam.service.employee.out.UnitService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;

@Service
@SuppressWarnings("all")
public class UnitServiceImpl implements UnitService {

	@Resource
	private UnitCustomMapper unitCustomMapper;// 自己写的mapper
	@Resource
	private UnitMapper unitMapper;// 存在的mapper
	@Resource
	private HaulunitMapper haulunitMapper;// 单位大修mapper
	@Autowired
	private HaulemployeeoutCustomMapper haulemployeeoutCustomMapper;
	@Autowired
	private EmployeeOutService employeeOutService;
	@Autowired
	private HaulunitCustomMapper haulunitCustomMapper;
	@Autowired
	private HaulunitprojectMapper haulunitprojectMapper;

	@Override
	public String getNextUnitId(String upUnitId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUnit(Unit unit, String bigID, Haulunit haulunit, String projectids) throws Exception {
		// 1.根据单位姓名判断是否存在,如果存在加中间表，不存在加两个表
		Unit unitByUnitName = this.getUnitByUnitName(unit.getName());
		if (unitByUnitName != null) {// 存在修改信息
			unit.setUnitid(unitByUnitName.getUnitid());// 设置ID
			int updateRow = unitMapper.updateByPrimaryKeySelective(unit);// 修改信息
			// 中间表添加数据
			haulunit.setBigid(bigID);
			haulunit.setUnitid(unitByUnitName.getUnitid());
			haulunit.setUnitbigid(UUIDUtil.getUUID2());
			haulunit.setUnitminismum(0);
			haulunit.setIsnewbig("1");
			//
			// 3.添加中间表工程信息
			List<String> projectIds = Arrays.asList(projectids.split(","));
			Haulunitproject haulunitproject = null;
			for (String projectId : projectIds) {
				haulunitproject = new Haulunitproject();
				haulunitproject.setBigid(bigID);// 大修ID
				haulunitproject.setProjectid(projectId);// 工程id
				haulunitproject.setUnitid(unitByUnitName.getUnitid());// 设置单位ID
				haulunitprojectMapper.insert(haulunitproject);
			}
			return haulunitMapper.insert(haulunit) > 0 ? true : false;
		}
		// 不存在证明是新的就添加两个表
		// 1.添加单位基本信息
		String unitId = UUIDUtil.getUUID2();// 产生一个单位ID
		unit.setUnitid(unitId);
		boolean addUnitResult = unitMapper.insert(unit) > 0 ? true : false;
		if (!addUnitResult) {
			throw new SQLException();
		}
		// 2.添加中间表
		haulunit.setBigid(bigID);
		haulunit.setUnitid(unitId);
		String haulUnitId = UUIDUtil.getUUID2();
		haulunit.setUnitbigid(haulUnitId);
		haulunit.setUnitminismum(0);
		haulunit.setIsnewbig("1");
		// 3.添加中间表工程信息
		List<String> projectIds = Arrays.asList(projectids.split(","));
		Haulunitproject haulunitproject = null;
		for (String projectId : projectIds) {
			haulunitproject = new Haulunitproject();
			haulunitproject.setBigid(bigID);// 大修ID
			haulunitproject.setProjectid(projectId);// 工程id
			haulunitproject.setUnitid(unitId);// 单位ID
			haulunitprojectMapper.insert(haulunitproject);
		}

		return haulunitMapper.insert(haulunit) > 0 ? true : false;
	}
	
	@Override
	public boolean addUnit2(Unit unit, String bigID, Haulunit haulunit) throws Exception {
		// 1.根据单位姓名判断是否存在,如果存在加中间表，不存在加两个表
		Unit unitByUnitName = this.getUnitByUnitName(unit.getName());
		if (unitByUnitName != null) {// 存在修改信息
			unit.setUnitid(unitByUnitName.getUnitid());// 设置ID
			int updateRow = unitMapper.updateByPrimaryKeySelective(unit);// 修改信息
			// 中间表添加数据
			haulunit.setBigid(bigID);
			haulunit.setUnitid(unitByUnitName.getUnitid());
			haulunit.setUnitbigid(UUIDUtil.getUUID2());
			haulunit.setUnitminismum(0);
			haulunit.setIsnewbig("1");
				
			return haulunitMapper.insert(haulunit) > 0 ? true : false;
		}
		// 不存在证明是新的就添加两个表
		// 1.添加单位基本信息
		String unitId = UUIDUtil.getUUID2();// 产生一个单位ID
		unit.setUnitid(unitId);
		boolean addUnitResult = unitMapper.insert(unit) > 0 ? true : false;
		if (!addUnitResult) {
			throw new SQLException();
		}
		// 2.添加中间表
		haulunit.setBigid(bigID);
		haulunit.setUnitid(unitId);
		String haulUnitId = UUIDUtil.getUUID2();
		haulunit.setUnitbigid(haulUnitId);
		haulunit.setUnitminismum(0);
		haulunit.setIsnewbig("1");
		
		return haulunitMapper.insert(haulunit) > 0 ? true : false;
	}
	
	@Override
	public boolean deleteUnitByBigIdAndHaulId(Map bididAndUnitid) throws Exception {
		// 1.查出所有的身份证号ID
		List<Map<String, Object>> bigemployeeids = haulemployeeoutCustomMapper
				.getBigEmployeeoutIdcardsByBigidAndUnitid(bididAndUnitid);
		// 2.循环遍历ID进行删除员工
		if (bigemployeeids != null && bigemployeeids.size() > 0) {
			Map condition = null;
			for (int i = 0; i < bigemployeeids.size(); i++) {
				condition = new HashMap();
				condition.put("bigId", bididAndUnitid.get("bigId"));
				condition.put("employeeOutIdCard", bigemployeeids.get(i).get("empoutIdcard"));
				condition.put("bigEmployeeOutId", bigemployeeids.get(i).get("bigEmployeeoutId"));
				employeeOutService.deleteHaulEmployeeOutInfoByCondition(condition);
			}
		}

		/**** 调用雷龙的方法删除员工 **/
		// 3.删除单位项目
		// 3.1查出这一条大修单位
		HaulunitExample haulunitExample = new HaulunitExample();
		HaulunitExample.Criteria criteria_1 = haulunitExample.createCriteria();
		criteria_1.andUnitidEqualTo((String) bididAndUnitid.get("unitId"));
		criteria_1.andBigidEqualTo((String) bididAndUnitid.get("bigId"));
		List<Haulunit> unitHaul = haulunitMapper.selectByExample(haulunitExample);
		Haulunit haulunit = null;
		if (unitHaul != null && unitHaul.size() > 0) {
			haulunit = unitHaul.get(0);
			// 4.删除单位大修表
			haulunitMapper.deleteByPrimaryKey(haulunit.getUnitbigid());
		}
		return true;
	}

	@Override
	public boolean updateUnit(Unit unit, Haulunit haulUnit, String projectids) throws Exception {
		//1.修改基本信息
		boolean result = unitMapper.updateByPrimaryKeySelective(unit) > 0
				&& haulunitMapper.updateByPrimaryKeySelective(haulUnit) > 0 ? true : false;
		//2.修改检修单位工程信息(删掉重加)
		HaulunitprojectExample haulunitprojectExample = new HaulunitprojectExample();
		HaulunitprojectExample.Criteria createCriteria = haulunitprojectExample.createCriteria();
		createCriteria.andBigidEqualTo(haulUnit.getBigid());
		createCriteria.andUnitidEqualTo(unit.getUnitid());
		//2.1删掉记录
		haulunitprojectMapper.deleteByExample(haulunitprojectExample);
		//重新添加
		// 3.添加中间表工程信息
		List<String> projectIds = Arrays.asList(projectids.split(","));
		Haulunitproject haulunitproject = null;
		for (String projectId : projectIds) {
			haulunitproject = new Haulunitproject();
			haulunitproject.setBigid(haulUnit.getBigid());// 大修ID
			haulunitproject.setProjectid(projectId);// 工程id
			haulunitproject.setUnitid(unit.getUnitid());// 设置单位ID
			haulunitprojectMapper.insert(haulunitproject);
		}
		return true;
	}

	@Override
	public Unit getUnitById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUnitIdByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getUnitTree() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.xm.exam.service.employee.out.UnitService#findUnitsWithCondition(int,
	 * int, java.util.Map)
	 */
	@Override
	public PageBean<Map<String, Object>> findUnitsWithCondition(int currentPage, int currentCount,
			Map<String, Object> condition) throws Exception {
		PageBean<Map<String, Object>> pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);// 1.设置当前页
		pageBean.setCurrentCount(currentCount);// 2. 设置页大小
		int totalCount = unitCustomMapper.getHaulunitTotalByCondition(condition);
		pageBean.setTotalCount(totalCount);// 3. 设置总记录数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);// 4.设置总页数
		/******
		 * 1 0 8 2 8 16 3 16 24
		 * 
		 */
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> haulunits = unitCustomMapper.getHaulunitByCondition(condition);
		pageBean.setProductList(haulunits);// 5.设置数据
		return pageBean;
	}

	@Override
	public List<Map<String, Object>> getUnitTreeForExam() throws SQLException {
		// TODO Auto-generated method stub
		return unitCustomMapper.getUnitTreeForExam();
	}

	@Override
	public List<String> getUnitsName(String name) throws SQLException {
		// TODO Auto-generated method stub
		return unitCustomMapper.getUnitsName(name);
	}

	@Override
	public Unit getUnitByUnitName(String unitName) throws SQLException {
		UnitExample unitExample = new UnitExample();
		UnitExample.Criteria criteria = unitExample.createCriteria();
		criteria.andNameEqualTo(unitName);
		List<Unit> selectByExample = unitMapper.selectByExample(unitExample);
		if (selectByExample != null && selectByExample.size() > 0) {
			return selectByExample.get(0);
		}
		return null;
	}

	@Override
	public PageBean<Map<String, Object>> getEmployeeOutsByUaulIdAndUnitId(int currentPage, int currentCount,
			Map<String, Object> haulIdAndUnitId) throws SQLException {

		PageBean<Map<String, Object>> pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);// 1.设置当前页
		pageBean.setCurrentCount(currentCount);// 2. 设置页大小
		int totalCount = unitCustomMapper.getEmployeeOutsTotalByUaulIdAndUnitId(haulIdAndUnitId);
		pageBean.setTotalCount(totalCount);// 3. 设置总记录数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);// 4.设置总页数
		/******
		 * 1 0 8 2 8 16 3 16 24
		 * 
		 */
		int index = (currentPage - 1) * currentCount;
		haulIdAndUnitId.put("index", index);
		haulIdAndUnitId.put("currentCount", currentCount);
		List<Map<String, Object>> empls = unitCustomMapper.getEmployeeOutsByUaulIdAndUnitId(haulIdAndUnitId);
		pageBean.setProductList(empls);// 5.设置数据
		return pageBean;
	}

	@Override
	public PageBean<Map<String, Object>> getEmployeeOutsBreakrulesByUaulIdAndUnitId(int currentPage, int currentCount,
			Map<String, Object> condition) throws SQLException {

		PageBean<Map<String, Object>> pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);// 1.设置当前页
		pageBean.setCurrentCount(currentCount);// 2. 设置页大小
		int totalCount = unitCustomMapper.getEmployeeOutsBreakrulesTotal(condition);
		pageBean.setTotalCount(totalCount);// 3. 设置总记录数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);// 4.设置总页数
		/******
		 * 1 0 8 2 8 16 3 16 24
		 * 
		 */
		int index = (currentPage - 1) * currentCount;
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> haulunits = unitCustomMapper.getEmployeeOutsBreakrulesByUaulIdAndUnitId(condition);
		pageBean.setProductList(haulunits);// 5.设置数据
		return pageBean;
	}

	@Override
	public List<Map<String, Object>> getUnitidsAndNamesByHaulId(String haulId, String departmentId)
			throws SQLException {
		// 1.根据大修ID查出大秀部门Id
		List<String> haulUnitIds = haulunitCustomMapper.selectUnitidsByHaulId(haulId, departmentId);
		List<Map<String, Object>> unitidsAndNames = null;
		// 2.根据部门ID查出部门名称
		if (haulUnitIds != null && haulUnitIds.size() > 0) {
			unitidsAndNames = unitCustomMapper.getUnitidsAndNamesByUnitids(haulUnitIds);
		}
		return unitidsAndNames;
	}

}
