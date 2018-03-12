package cn.xm.exam.action.employee.out;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.xm.exam.bean.employee.out.Unit;
import cn.xm.exam.bean.haul.Haulunit;
import cn.xm.exam.service.employee.out.UnitService;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 修改外来单位Action
 * 
 * @author QiaoLiQiang
 * @time 2017年11月13日下午3:14:36
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class UpdateUnitAction extends ActionSupport implements ModelDriven<Unit> {
	private Map response;// 用于返回结果的map
	private Logger logger = Logger.getLogger(UpdateUnitAction.class);// 日志记录器
	@Autowired
	private UnitService unitService;// 外来单位服务
	private Unit unit = new Unit();// 模型驱动的单位
	private Haulunit haulUnit;
	private  String projectids;
	/**
	 * 修改单位信息到数据库
	 * 
	 * @return
	 */
	public String execute() {
		response = new HashMap();
		String updateResult = null;
		try {
			if (unit != null && haulUnit !=null &&ValidateCheck.isNotNull(projectids)) {
				updateResult = unitService.updateUnit(unit,haulUnit,projectids) ? "修改成功!" : "修改失败!";
			}
		} catch (Exception e) {
			logger.error("修改单位信息失败!",e);
			updateResult = "修改失败!";
		}
		// 传到Service进行保存
		response.put("updateResult", updateResult);
		return SUCCESS;
	}
	
	/**
	 * 修改培训单位信息到数据库
	 * 
	 * @return
	 */
	public String updateUnitInfo() {
		response = new HashMap();
		String updateResult = null;
		try {
			if (unit != null && haulUnit !=null) {
				updateResult = unitService.updateUnit(unit,haulUnit) ? "修改成功!" : "修改失败!";
			}
		} catch (Exception e) {
			logger.error("修改单位信息失败!",e);
			updateResult = "修改失败!";
		}
		// 传到Service进行保存
		response.put("updateResult", updateResult);
		return SUCCESS;
	}

	// get,set
	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	@Override
	public Unit getModel() {
		return unit;
	}

	public Haulunit getHaulUnit() {
		return haulUnit;
	}

	public void setHaulUnit(Haulunit haulUnit) {
		this.haulUnit = haulUnit;
	}

	public String getProjectids() {
		return projectids;
	}

	public void setProjectids(String projectids) {
		this.projectids = projectids;
	}

}
