package cn.xm.exam.service.impl.log;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.log.Logtable;
import cn.xm.exam.mapper.log.LogtableMapper;
import cn.xm.exam.service.log.LogtableService;

@Service
public class LogtableServiceImpl implements LogtableService {
	@Autowired
	private LogtableMapper logtableMapper;
	@Override
	public boolean addLog(Logtable log) throws SQLException {
		return logtableMapper.insert(log) > 0 ? true : false;
	}

}
