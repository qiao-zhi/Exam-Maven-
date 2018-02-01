package cn.xm.exam.action.common;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.xm.exam.bean.common.Message;
import cn.xm.exam.service.common.MessageService;
import cn.xm.exam.utils.ValidateCheck;

@Controller
@Scope("prototype")
@SuppressWarnings("all")
public class MessageAction extends ActionSupport {

	private String empType;
	private String messageid;
	private Map response;//用于回显数据的map
	private Logger logger = Logger.getLogger(MessageAction.class);
	@Autowired
	private MessageService messageService; 
	/**
	 * 查询未读的消息
	 * @return
	 */
	public String getUnreadMessageByEmpType(){
		response = new HashMap();
		List<Message> messages = null;
		try {
			if(ValidateCheck.isNotNull(empType)){
				messages = messageService.getMessageByEmptype(empType);
			}
		} catch (SQLException e) {
			logger.error("查询消息失败!",e);
		}
		if(messages!=null&&messages.size()>0){
			response.put("messages",messages);
		}
		return SUCCESS;
		
	}
	public String updateMessageByMessageId(){
		response = new HashMap();
		String result=null;
		try {
			result = messageService.updateMessageStatusByMessageId(messageid)?"读取成功":"读取失败";
		} catch (SQLException e) {
			result="读取失败";
			logger.error("修改消息失败!",e);
		}
		response.put("result", result);
		return SUCCESS;
		
	}
	
	
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getMessageid() {
		return messageid;
	}
	public void setMessageid(String messageid) {
		this.messageid = messageid;
	}
	public Map getResponse() {
		return response;
	}
	public void setResponse(Map response) {
		this.response = response;
	}
	
}
