package cn.xm.exam.action.employee.out;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.service.employee.out.UnitService;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 删除部门Action
 * 
 * @author QiaoLiQiang
 * @time 2017年11月15日下午1:48:55
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class DeleteUnitAction extends ActionSupport {

	private Logger logger = Logger.getLogger(DeleteUnitAction.class);
	@Autowired
	private UnitService unitService;// 单位服务
	private Map response;// 用于包装回显结果的map
	private String unitId;// 单位ID
	private String bigId;// 大修ID

	public String execute() {
		response = new HashMap();
		Map condition = new HashMap();
		if (ValidateCheck.isNotNull(unitId)) {
			condition.put("unitId", unitId);
		}
		if (ValidateCheck.isNotNull(bigId)) {
			condition.put("bigId", bigId);
		}
		String deleteResult = "";
		try {
			deleteResult = unitService.deleteUnitByBigIdAndHaulId(condition) ? "删除成功!" : "删除失败!";
		} catch (Exception e) {
			logger.error("删除单位出错!", e);
			deleteResult = "删除失败!";
		}
		response.put("deleteResult", deleteResult);
		return SUCCESS;
	}

	// get set
	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

}
