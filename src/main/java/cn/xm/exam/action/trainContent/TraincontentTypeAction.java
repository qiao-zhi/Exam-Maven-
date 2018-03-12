package cn.xm.exam.action.trainContent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.trainContent.Traincontenttype;
import cn.xm.exam.service.trainContent.TraincontenttypeService;
import cn.xm.exam.utils.ValidateCheck;

@Controller // 控制层
@Scope("prototype") // 多例模式
@SuppressWarnings("all") // 压制所有警告
public class TraincontentTypeAction extends ActionSupport {
	private Logger logger = Logger.getLogger(TraincontentTypeAction.class);// 日志记录器
	@Autowired
	private TraincontenttypeService traincontenttypeService;

	private Map response;// 携带返回数据的map

	/**
	 * 获取类别树
	 * 
	 * @return
	 */
	public String getTraincontenttypeTree() {
		response = new HashMap();
		List<Map<String, Object>> traincontenttypeTree = null;
		try {
			traincontenttypeTree = traincontenttypeService.getTraincontenttypeTree();
		} catch (SQLException e) {
			logger.error("查询培训内容类别出错", e);
		}
		if (traincontenttypeTree != null) {
			response.put("traincontenttypeTree", traincontenttypeTree);
		}
		return SUCCESS;
	}

	private Traincontenttype traincontenttype;

	/**
	 * 添加类别
	 * 
	 * @return
	 */
	public String addTraincontenttype() {
		response = new HashMap();
		boolean result = false;
		try {
			result = traincontenttypeService.addTraincontenttype(traincontenttype);
		} catch (SQLException e) {
			logger.error("增加培训内容类别出错", e);
		}
		if (result) {
			response.put("result", "添加成功");
			response.put("traincontenttype", traincontenttype);// 将带ID的数据返回到前台进行显示
		} else {
			response.put("result", "添加失败");
		}
		return SUCCESS;
	}

	/**
	 * 修改培训内容类别名称
	 * 
	 * @return
	 */
	public String updateTraincontenttypeName() {
		response = new HashMap();
		boolean result = false;
		try {
			result = traincontenttypeService.updateTraincontenttypeById(traincontenttype);
		} catch (SQLException e) {
			logger.error("修改培训内容类别出错", e);
		}
		if (result) {
			response.put("result", "修改成功");
		} else {
			response.put("result", "修改失败");
		}
		return SUCCESS;
	}

	private String typeId;
	/**
	 * 转发到增加培训内容界面
	 * @return
	 */
	public String forwardToAddTraincontent() {
		if(ValidateCheck.isNotNull(typeId)){
			try {
				traincontenttype = traincontenttypeService.getTraincontenttypeById(typeId);
			} catch (SQLException e) {
				logger.error("查询培训内容类别出错", e);
			}
		}
		return "toAdd";
	}
	
	public String deleteTrainContentTypeById(){
		response = new HashMap();
		boolean result = false;
		if(ValidateCheck.isNotNull(typeId)){
			try {
				result = traincontenttypeService.deleteTraincontenttypeById(typeId);
			} catch (SQLException e) {
				logger.error("删除培训内容类别出错",e);
			}
		}
		if(result){
			response.put("result","删除成功");
		}else{
			response.put("result","删除失败");
		}
		return SUCCESS;
	}
	
	public Map getResponse() {
		return response;
	}

	public void setResponse(Map response) {
		this.response = response;
	}

	public Traincontenttype getTraincontenttype() {
		return traincontenttype;
	}

	public void setTraincontenttype(Traincontenttype traincontenttype) {
		this.traincontenttype = traincontenttype;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

}
