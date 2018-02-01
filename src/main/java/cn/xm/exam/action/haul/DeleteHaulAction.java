package cn.xm.exam.action.haul;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.service.haul.HaulinfoService;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 删除大修的Action
 * 
 * @author QiaoLiQiang
 * @time 2017年11月15日下午6:14:34
 */
@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class DeleteHaulAction extends ActionSupport {
	private Logger logger = Logger.getLogger(DeleteHaulAction.class);
	@Autowired
	private HaulinfoService haulinfoService;// 单位服务
	private Map response;// 用于包装回显结果的map
	private String bigId;// 大修ID

	public String execute() {
		response = new HashMap();
		String deleteResult = "";
		try {
			if (ValidateCheck.isNotNull(bigId)) {
				// 删除大修数据
				deleteResult = haulinfoService.deleteHaulinfoByHaulId(bigId) ? "删除成功!" : "删除失败!";
			}
		} catch (Exception e) {
			logger.error("删除大修出错!", e);
			deleteResult = "删除失败!";
		}
		response.put("deleteResult", deleteResult);
		return SUCCESS;
	}

	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	public String getBigId() {
		return bigId;
	}

	public void setBigId(String bigId) {
		this.bigId = bigId;
	}

}
