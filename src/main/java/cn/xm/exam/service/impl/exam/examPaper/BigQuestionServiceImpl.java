package cn.xm.exam.service.impl.exam.examPaper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.exam.Bigquestion;
import cn.xm.exam.bean.exam.BigquestionExample;
import cn.xm.exam.mapper.exam.BigquestionMapper;
import cn.xm.exam.mapper.exam.custom.BigQuestionCustomMapper;
import cn.xm.exam.service.exam.examPaper.BigQuestionService;

/**
 * 试卷大题实现类实现类
 * 
 * @author QiaoLiQiang
 * @time 2017年9月23日下午6:19:52
 */

@Service
public class BigQuestionServiceImpl implements BigQuestionService {

	@Autowired
	private BigquestionMapper bigquestionMapper;
	@Autowired
	private BigQuestionCustomMapper bigQuestionCustomMapper;

	@Override
	public boolean addBigQuestion(Bigquestion bigQuestion) throws SQLException {
		// 增加大题.这里应该调用UUID产生一个唯一的ID
		int result = bigquestionMapper.insert(bigQuestion);
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteBigQuestionById(String bigQuestionId) throws SQLException {
		int result = bigquestionMapper.deleteByPrimaryKey(bigQuestionId);
		return result > 0 ? true : false;
	}

	@Override
	public boolean deleteBigQuestionByPaperId(String paperId) throws SQLException {
		return bigQuestionCustomMapper.deleteBigQuestionsBypaperId(paperId) > 0 ? true : false;
	}

	@Override
	public boolean updateBigQuestionById(Bigquestion bigQuestion) throws SQLException {
		int result = bigquestionMapper.updateByPrimaryKeySelective(bigQuestion);
		return result > 0 ? true : false;
	}

	@Override
	public Bigquestion getBigQuestionById(String bigQuestionId) throws SQLException {
		return bigquestionMapper.selectByPrimaryKey(bigQuestionId);
	}

	@Override
	public List<Bigquestion> getBigQuestionsByPaperId(String papaerId) throws SQLException {
		BigquestionExample bigquestionExample = new BigquestionExample();
		BigquestionExample.Criteria criteria = bigquestionExample.createCriteria();
		criteria.andPaperidEqualTo(papaerId);
		return bigquestionMapper.selectByExample(bigquestionExample);
	}

}
