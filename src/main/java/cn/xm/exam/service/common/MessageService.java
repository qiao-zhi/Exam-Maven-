package cn.xm.exam.service.common;

import java.sql.SQLException;
import java.util.List;

import cn.xm.exam.bean.common.Message;

/**
 * 消息接口
 * 
 * @author QiaoLiQiang
 * @time 2018年1月25日下午4:21:08
 */
public interface MessageService {

	/**
	 * 根据员工类型查询超过55岁的人
	 * 
	 * @return
	 * @param empType:员工类型：0短委，1内部
	 * @throws SQLException
	 */
	public List<Message> getMessageByEmptype(String empType) throws SQLException;

	/**
	 * 修改消息的状态(设置为已读 1)
	 * 
	 * @param messageId
	 *            消息ID
	 * @return
	 * @throws SQLException
	 */
	public boolean updateMessageStatusByMessageId(String messageId) throws SQLException;
}
