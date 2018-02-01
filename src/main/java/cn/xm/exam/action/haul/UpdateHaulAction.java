package cn.xm.exam.action.haul;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.xm.exam.bean.haul.Haulinfo;
import cn.xm.exam.service.haul.HaulinfoService;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 修改大修Action
 * 
 * @author QiaoLiQiang
 * @time 2017年11月11日下午6:58:48
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class UpdateHaulAction extends ActionSupport implements ModelDriven<Haulinfo> {
	private Logger logger = Logger.getLogger(UpdateHaulAction.class);// 日志记录器
	private Haulinfo haulinfo = new Haulinfo();
	@Resource
	private HaulinfoService haulinfoService;
	private Map<String, Object> response;
	public String execute() {
		response = new HashMap<String, Object>();
		boolean update_result = false;
		String result = "";
		try {
			update_result = haulinfoService.updateHaulinfoById(haulinfo);
		} catch (SQLException e) {
			response.put("result", "修改失败!");
			logger.error("修改大修基本信息出错!", e);
		}
		result = update_result ? "修改成功!" : "修改失败!";
		response.put("result", result);
		return SUCCESS;
	}

	
	
	
	/***************S   增加单个标段**********/
	private String bigId;
	private String name;
	public String addOneBiaoduan(){
		response = new HashMap<String, Object>();
		String result = "";
		try {
			if(ValidateCheck.isNotNull(bigId)&&ValidateCheck.isNotNull(name)){
				result = haulinfoService.addOnebiaoduan(bigId, name)?"添加成功!":"添加失败!";
			}
		} catch (SQLException e) {
			response.put("result", "修改失败!");
			logger.error("修改大修基本信息出错!", e);
		}
		response.put("result", result);
		return SUCCESS;
	}
	
	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/***************E   增加单个标段**********/
	/**********S    修改单个字段*******/
	private String projectId;
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String updateOneBiaoduan(){
		response = new HashMap<String, Object>();
		String result = "";
		try {
			if(ValidateCheck.isNotNull(projectId)&&ValidateCheck.isNotNull(name)){
				result = haulinfoService.updateOnebiaoduan(projectId, name)?"修改成功!":"修改失败!";
			}
		} catch (SQLException e) {
			response.put("result", "修改失败!");
			logger.error("修改大修标段出错!", e);
		}
		response.put("result", result);
		return SUCCESS;
	}
	/*******************S 删除单个表短**************************/
	public String deleteOneBiaoduan(){
		response = new HashMap<String, Object>();
		String result = "";
		try {
			if(ValidateCheck.isNotNull(projectId)){
				result = haulinfoService.daleteOnebiaoduan(projectId)?"删除成功!":"删除失败，该标段下已经有单位了!";
			}
		} catch (SQLException e) {
			response.put("result", "删除失败!");
			logger.error("删除大修标段出错!", e);
		}
		response.put("result", result);
		return SUCCESS;
	}
	
	
	
	
	@Override
	public Haulinfo getModel() {
		// TODO Auto-generated method stub
		return haulinfo;
	}

	// get,set
	public Map<String, Object> getResponse() {
		return response;
	}

	public void setResponse(Map<String, Object> response) {
		this.response = response;
	}
}
