package cn.xm.exam.service.impl.common;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.xm.exam.bean.common.Message;
import cn.xm.exam.mapper.common.custom.MessageCustomMapper;
import cn.xm.exam.service.common.MessageService;

@Service
@SuppressWarnings("all")
public class MessageServiceImpl implements MessageService {
	@Resource // 自动注入
	private MessageCustomMapper messageCustomMapper;

	@Override
	public List<Message> getMessageByEmptype(String empType) throws SQLException {
		return messageCustomMapper.getMessageByEmptype(empType);
	}

	@Override
	public boolean updateMessageStatusByMessageId(String messageId) throws SQLException {
		return messageCustomMapper.updateMessageStatusByMessageId(messageId) > 0 ? true : false;
	}

}
