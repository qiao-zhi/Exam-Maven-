package cn.xm.exam.service.impl.haul;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.exam.Exam;
import cn.xm.exam.bean.exam.ExamExample;
import cn.xm.exam.bean.haul.Haulinfo;
import cn.xm.exam.bean.haul.Haulproject;
import cn.xm.exam.bean.haul.HaulprojectExample;
import cn.xm.exam.bean.haul.Haulunitproject;
import cn.xm.exam.bean.haul.HaulunitprojectExample;
import cn.xm.exam.bean.haul.HaulunitprojectExample.Criteria;
import cn.xm.exam.bean.haul.Project;
import cn.xm.exam.mapper.exam.ExamMapper;
import cn.xm.exam.mapper.haul.HaulinfoMapper;
import cn.xm.exam.mapper.haul.HaulprojectMapper;
import cn.xm.exam.mapper.haul.HaulunitprojectMapper;
import cn.xm.exam.mapper.haul.ProjectMapper;
import cn.xm.exam.mapper.haul.custom.HaulinfoCustomMapper;
import cn.xm.exam.service.employee.out.UnitService;
import cn.xm.exam.service.exam.exam.ExamService;
import cn.xm.exam.service.haul.HaulinfoService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;

@Service
public class HaulinfoServiceImpl implements HaulinfoService {

	@Autowired
	private HaulinfoMapper haulinfoMapper;// 大修mapper
	@Autowired
	private HaulinfoCustomMapper haulinfoCustomMapper;// 大修mapper
	@Resource
	private ExamMapper examMapper;// 考试mapper
	@Resource
	private ExamService examService;// 考试mapper
	@Autowired
	private UnitService unitService;
	@Resource
	private ProjectMapper projectMapper;
	@Resource
	private HaulprojectMapper haulprojectMapper;
	@Resource
	private HaulunitprojectMapper haulunitprojectMapper;

	@Override
	public boolean addHaulinfo(Haulinfo haulInfo,List<String> list) throws SQLException {
		String bigId = UUIDUtil.getUUID2();
		if (ValidateCheck.isNull(haulInfo.getBigid())) {// 设置ID
			haulInfo.setBigid(bigId);
		}
		haulInfo.setBigcreatedate(new Date());// 设置创建日期
		haulInfo.setBigstatus("未开始");// 设置状态
		//0.插入大修基本信息
		haulinfoMapper.insert(haulInfo);
		Project project = null;
		Haulproject haulproject = null ;
		//1.插入工程信息,插入大修工程表
		for(String projectName:list){
			project = new Project();
			project.setProjectname(projectName);
			String projectId = UUIDUtil.getUUID2();
			project.setProjectid(projectId);
			//插入工程
			projectMapper.insert(project);
			//插入检修工程
			haulproject = new Haulproject();
			haulproject.setBigid(bigId);
			haulproject.setProjectid(projectId);
			haulprojectMapper.insert(haulproject);
		}
		return true;
	}

	@Override
	public boolean deleteHaulinfoByHaulId(String haulId) throws Exception {
		// 1.根据大修ID查出部门ID
		List<String> unitIds = this.getHaulUnitByHaulid(haulId);
		// 2.根据大修ID与部门ID删除大修部门(循环删除)
		if (unitIds != null && unitIds.size() > 0) {
			Map<String, Object> bigidAndUnitid = null;
			for (int i = 0, length_1 = unitIds.size(); i < length_1; i++) {
				bigidAndUnitid = new HashMap<String, Object>();
				bigidAndUnitid.put("bigId", haulId);
				bigidAndUnitid.put("unitId", unitIds.get(i));
				unitService.deleteUnitByBigIdAndHaulId(bigidAndUnitid);// 删除部门
			}
		}
		// 3.根据大修ID删除考试信息
		ExamExample examExample = new ExamExample();
		ExamExample.Criteria criteria = examExample.createCriteria();
		criteria.andBigidEqualTo(haulId);
		List<Exam> exams = examMapper.selectByExample(examExample);// 查出所有的考试
		for (int i = 0; exams != null && i < exams.size(); i++) {
			examService.deleteExamById(exams.get(i).getExamid());// 循环删除考试
		}
		// 4.删除本条大修记录
		return haulinfoMapper.deleteByPrimaryKey(haulId) > 0 ? true : false;
	}

	@Override
	public Haulinfo getHaulinfoByHaulId(String haulId) throws SQLException {
		return haulinfoMapper.selectByPrimaryKey(haulId);
	}

	@Override
	public boolean updateHaulinfoById(Haulinfo haulinfo) throws SQLException {
		// 修改的影响行数大于零说明修改成功
		return haulinfoMapper.updateByPrimaryKeySelective(haulinfo) > 0 ? true : false;
	}

	@Override
	public PageBean<Map<String, Object>> getHaulinfoPageByCondition(int currentPage, int currentCount,
			Map<String, Object> condition) throws SQLException {
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentCount(currentCount);// 设置页大小
		pageBean.setCurrentPage(currentPage);// 设置当前页
		int total = 0;
		int totalCount = haulinfoCustomMapper.getHaulinfoTotalByCondition(condition);// 查询满足条件的总数
		pageBean.setTotalCount(totalCount);// 总记录数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);// 总页数

		/******
		 * 计算起始值 页数 起始值 页大小 1 0 8 2 8 16
		 *******/
		int index = (currentPage - 1) * currentCount;// 起始值
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> haulinfos = haulinfoCustomMapper.getHaulinfoslByCondition(condition);
		pageBean.setProductList(haulinfos);
		return pageBean;
	}

	@Override
	public Map<String, Object> getHaulinfoWithUnitInfo(String haulId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getDepartmentAndOverHaulTree() throws SQLException {
		List<Map<String, Object>> departInfoTree = (List<Map<String, Object>>) haulinfoCustomMapper.getDepartInfoTree();
		List<Map<String, Object>> overHaulInfoTree = (List<Map<String, Object>>) haulinfoCustomMapper
				.getOverHaulInfoTree();
		departInfoTree.addAll(overHaulInfoTree);
		return departInfoTree;
	}

	@Override
	public List<String> getHaulUnitByHaulid(String haulId) throws SQLException {
		return haulinfoCustomMapper.getHaulUnitByHaulid(haulId);
	}

	@Override
	public List<Map<String, Object>> getHaulNameAndIdsForExam(String departmentId) throws SQLException {
		return haulinfoCustomMapper.getHaulNameAndIdsForExam(departmentId);
	}

	@Override
	public PageBean<Map<String, Object>> getProjectInfoByBigId(int currentPage,int currentCount,Map<String,Object> condition) throws SQLException {
		String bigId=(String) condition.get("bigId");
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentCount(currentCount);// 设置页大小
		pageBean.setCurrentPage(currentPage);// 设置当前页
		int total = 0;
		int totalCount = haulinfoCustomMapper.getCountProjectInfoByBigId(bigId);// 查询满足条件的总数
		pageBean.setTotalCount(totalCount);// 总记录数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);// 总页数

		/******
		 * 计算起始值 页数 起始值 页大小 1 0 8 2 8 16
		 *******/
		int index = (currentPage - 1) * currentCount;// 起始值
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> biaoduaninfo = haulinfoCustomMapper.getProjectInfoByBigId(condition);
		pageBean.setProductList(biaoduaninfo);
		return pageBean;
	}

	@Override
	public PageBean<Map<String, Object>> getProjectUnitPerNumInfoByBigId(int currentPage,int currentCount,Map<String,Object> condition) throws SQLException {
		// TODO Auto-generated method stub
		String bigId=(String) condition.get("bigId");
		PageBean<Map<String, Object>> pageBean = new PageBean<Map<String, Object>>();
		pageBean.setCurrentCount(currentCount);// 设置页大小
		pageBean.setCurrentPage(currentPage);// 设置当前页
		int total = 0;
		int totalCount = haulinfoCustomMapper.getCountProjectUnitPerNumInfoByBigId(bigId);// 查询满足条件的总数
		pageBean.setTotalCount(totalCount);// 总记录数
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);// 总页数

		/******
		 * 计算起始值 页数 起始值 页大小 1 0 8 2 8 16
		 *******/
		int index = (currentPage - 1) * currentCount;// 起始值
		condition.put("index", index);
		condition.put("currentCount", currentCount);
		List<Map<String, Object>> projeunitper = haulinfoCustomMapper.getProjectUnitPerNumInfoByBigId(condition);
		pageBean.setProductList(projeunitper);
		return pageBean;
	}

	@Override
	public boolean addOnebiaoduan(String bigId, String projectname) throws SQLException {
		Project pro = new Project();
		String projectId = UUIDUtil.getUUID2();
		pro.setProjectid(projectId);
		pro.setProjectname(projectname);
		//1.添加标段
		projectMapper.insert(pro);
		//2.添加检修表短
		Haulproject hau = new Haulproject();
		hau.setBigid(bigId);
		hau.setProjectid(projectId);
		haulprojectMapper.insert(hau);
		return true;
	}

	@Override
	public boolean updateOnebiaoduan(String projectId, String projectname) throws SQLException {
		Project pro = new Project();
		pro.setProjectid(projectId);
		pro.setProjectname(projectname);
		return projectMapper.updateByPrimaryKeySelective(pro)>0?true:false;
	}

	@Override
	public boolean daleteOnebiaoduan(String projectId) throws SQLException {
		//1.根据标段ID判断是否有单位
		HaulunitprojectExample haulunitprojectExample = new HaulunitprojectExample();
		HaulunitprojectExample.Criteria criteria = haulunitprojectExample.createCriteria();
		criteria.andProjectidEqualTo(projectId);
		List<Haulunitproject> selectByExample = haulunitprojectMapper.selectByExample(haulunitprojectExample);
		if(selectByExample.size()>0){//1.2如果有单位提醒用户不能删除该标段
			return false;
		}else{//1.1没有单位可以删除
					//1.删除大修工程管理即可
			HaulprojectExample examp = new HaulprojectExample();
			HaulprojectExample.Criteria criteria_1 = examp.createCriteria();
			criteria_1.andProjectidEqualTo(projectId);
			haulprojectMapper.deleteByExample(examp);
			return true;
		}
	}

	@Override
	public Map getAllHaulInfo(Map condition) throws SQLException {
		return haulinfoCustomMapper.getAllHaulInfo(condition);
	}

}
