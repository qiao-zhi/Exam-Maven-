package cn.xm.exam.action.employee.out;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.xm.exam.bean.employee.out.Unit;
import cn.xm.exam.bean.haul.Haulunit;
import cn.xm.exam.service.employee.out.UnitService;
import cn.xm.exam.service.haul.ProjectService;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 添加外来单位Action
 * 
 * @author QiaoLiQiang
 * @time 2017年11月13日下午3:14:36
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class AddUnitAction extends ActionSupport implements ModelDriven<Unit> {
	private Map response;// 用与返回结果的map
	private Logger logger = Logger.getLogger(AddUnitAction.class);// 日志记录器
	@Autowired
	private UnitService unitService;// 外来单位服务
	private Unit unit = new Unit();// 模型驱动的单位
	
	private String bigid;// 大修ID
	private String projectnames;// 工程名字
	private String nameWord; // 模糊查询的name字符串
	private String unitName; // 根据单位名称查询单位信息

	private Haulunit haulUnit;
	
	public Haulunit getHaulUnit() {
		return haulUnit;
	}

	public void setHaulUnit(Haulunit haulUnit) {
		this.haulUnit = haulUnit;
	}

	// 模糊查询部门名称
	public String getNamesByName() {
		response = new HashMap();
		List<String> unitsName = null;
		try {
			if (ValidateCheck.isNotNull(nameWord)) {
				unitsName = unitService.getUnitsName(nameWord);
			}
		} catch (Exception e) {
			logger.error("模糊查询部门树出错", e);
		}
		response.put("unitsName", unitsName);
		return SUCCESS;
	}
	@Resource
	private ProjectService projectService;
	public String getProjectInfoByHaulId() {
		response = new HashMap();
		List<Map<String,Object>> projects = null;
		try {
			if (ValidateCheck.isNotNull(bigid)) {
				projects = projectService.getProjectInfoByHaulId(bigid);
			}
		} catch (Exception e) {
			logger.error("查询检修标段出错", e);
		}
		response.put("projects", projects);
		return SUCCESS;
	}
	
	
	
	
	
	
	

	/**
	 * 根据单位名称查询单位信息
	 * 
	 * @return
	 */
	public String getUnitByUnitName() {
		response = new HashMap();
		Unit unit = null;
		try {
			if (ValidateCheck.isNotNull(unitName)) {
				unit = unitService.getUnitByUnitName(unitName);
			}
		} catch (Exception e) {
			logger.error("根据部门名称模糊查询部门出错", e);
		}
		response.put("unit", unit);
		return SUCCESS;
	}

	
		
	private String projectids;
	/**
	 * 保存单位信息到数据库
	 * 
	 * @return
	 */
	public String addUnit() {
		response = new HashMap();
		String addResult = null;
		// 传到Service进行保存
		if (unit != null && ValidateCheck.isNotNull(bigid)) {
			try {
				addResult = unitService.addUnit(unit, bigid,haulUnit,projectids) ? "添加成功!" : "添加失败!";
			} catch (Exception e) {
				logger.error("添加单位失败!", e);
				addResult = "添加失败!";
			}
		}
		response.put("addResult", addResult);
		return SUCCESS;
	}
	
	public String addUnit2(){
		response = new HashMap();
		String addResult = null;
		// 传到Service进行保存
		if (unit != null && ValidateCheck.isNotNull(bigid)) {
			try {
				addResult = unitService.addUnit2(unit, bigid,haulUnit) ? "添加成功!" : "添加失败!";
			} catch (Exception e) {
				logger.error("添加单位失败!", e);
				addResult = "添加失败!";
			}
		}
		response.put("addResult", addResult);
		return SUCCESS;
	}
	
	// get,set
	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	public String getNameWord() {
		return nameWord;
	}

	public void setNameWord(String nameWord) {
		this.nameWord = nameWord;
	}

	public String getBigid() {
		return bigid;
	}

	public void setBigid(String bigid) {
		this.bigid = bigid;
	}

	@Override
	public Unit getModel() {
		return unit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getProjectnames() {
		return projectnames;
	}

	public void setProjectnames(String projectnames) {
		this.projectnames = projectnames;
	}

	public String getProjectids() {
		return projectids;
	}

	public void setProjectids(String projectids) {
		this.projectids = projectids;
	}

}
