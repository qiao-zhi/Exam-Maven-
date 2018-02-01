package cn.xm.exam.service.impl.exam.examPaper;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.exam.Exampaperoption;
import cn.xm.exam.mapper.exam.ExampaperoptionMapper;
import cn.xm.exam.mapper.exam.custom.ExampaperoptionCustomMapper;
import cn.xm.exam.service.exam.examPaper.ExamPaperOptionService;

/**
 * 试卷试题选项服务实现
 * 
 * @author QiaoLiQiang
 * @time 2017年10月11日上午8:38:44
 */
@Service
public class ExamPaperOptionServiceImpl implements ExamPaperOptionService {

	@Autowired
	private ExampaperoptionMapper exampaperoptionMapper;
	@Autowired
	private ExampaperoptionCustomMapper exampaperoptionCustomMapper;

	@Override
	public boolean addPaperOption(Exampaperoption exampaperoption) throws SQLException {
		int result = exampaperoptionMapper.insert(exampaperoption);
		return result > 0 ? true : false;
	}

	@Override
	public boolean addPaperOptionBatch(List<Exampaperoption> exampaperoptions) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePaperOptionByQuestionId(String paperQuestionId) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deletePaperOptionByPaperId(String paperId) throws SQLException {
		return exampaperoptionCustomMapper.deleteOptionsBypaperId(paperId) > 0 ? true : false;
	}

}
