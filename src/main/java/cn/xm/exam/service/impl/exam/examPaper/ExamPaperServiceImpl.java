package cn.xm.exam.service.impl.exam.examPaper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xm.exam.bean.exam.Bigquestion;
import cn.xm.exam.bean.exam.BigquestionExample;
import cn.xm.exam.bean.exam.Exampaper;
import cn.xm.exam.bean.exam.Exampaperoption;
import cn.xm.exam.bean.exam.Exampaperquestion;
import cn.xm.exam.bean.exam.ExampaperquestionExample;
import cn.xm.exam.bean.system.User;
import cn.xm.exam.mapper.exam.BigquestionMapper;
import cn.xm.exam.mapper.exam.ExampaperMapper;
import cn.xm.exam.mapper.exam.ExampaperquestionMapper;
import cn.xm.exam.mapper.exam.custom.ExampaperCustomMapper;
import cn.xm.exam.service.exam.examPaper.BigQuestionService;
import cn.xm.exam.service.exam.examPaper.ExamPaperOptionService;
import cn.xm.exam.service.exam.examPaper.ExamPaperQuestionService;
import cn.xm.exam.service.exam.examPaper.ExamPaperService;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;

/**
 * 试卷服务实现类
 * 
 * @author QiaoLiQiang
 * @time 2017年9月27日下午2:42:26
 */
@Service
public class ExamPaperServiceImpl implements ExamPaperService {
	@Autowired
	private ExampaperMapper exampaperMapper;// 试卷mapper
	@Autowired
	private ExampaperCustomMapper exampaperCustomMapper;// 试卷mapper(自己写的)
	@Autowired
	private BigQuestionService bigQuestionService;// 大题服务(添加大题)
	@Autowired
	private ExamPaperQuestionService examPaperQuestionService;// 试题服务(添加试题)
	@Autowired
	private ExamPaperOptionService examPaperOptionService;// 选项服务(用于添加选项)

	@Override
	public String getNextPaperId() throws SQLException {
		return null;
	}

	@Override
	public boolean addExamPaperBaseInfo(Exampaper exampaper) throws SQLException {
		int result = exampaperMapper.insert(exampaper);
		return result > 0 ? true : false;
	}

	@Override
	public boolean addExamPaper(Exampaper examPaper, Map<String, Object> bigQues) throws SQLException {
		if (examPaper == null) {
			return false;
		}
		// 获取试卷ID，如果为空产生一个ID
		String paperId = examPaper.getPaperid();
		if (ValidateCheck.isNull(paperId)) {
			paperId = UUIDUtil.getUUID2();// 产生试卷ID
			examPaper.setPaperid(paperId);
			examPaper.setUsetimes(0);// 第一次增加的时候将试卷使用次数设置为0
		}
		// 修改试卷的答案，用来存放试卷的归档与否:0代表未归档，1代表归档
		if(ValidateCheck.isNull(examPaper.getPaperanswer())){
			examPaper.setPaperanswer("0");
		}
		// 如果部门的ID为空的话就设置部门ID
		if (ValidateCheck.isNull(examPaper.getDepartmentid())) {
			User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
			String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到部门ID
			examPaper.setDepartmentid(departmentIdSession);
		}
		// 如果试卷描述没写就写一个空的试卷描述
		if (ValidateCheck.isNull(examPaper.getDescription())) {
			examPaper.setDescription("无");
		}
		boolean result = this.addExamPaperBaseInfo(examPaper);// 添加试卷的基本信息
		if (result) {
			Bigquestion danxuanDati = (Bigquestion) bigQues.get("danxuanDati");// 获取单选大题
			Bigquestion duoxuanDati = (Bigquestion) bigQues.get("duoxuanDati");// 获取单选大题
			Bigquestion panduanDati = (Bigquestion) bigQues.get("panduanDati");// 获取单选大题
			// 添加单选题
			if (danxuanDati != null) {
				danxuanDati.setPaperid(paperId);
				String danxuanDatiId = UUIDUtil.getUUID2();// 产生单选大题ID
				danxuanDati.setBigquertionid(danxuanDatiId);
				String bigTitle = danxuanDati.getBigquestionname();// 获取到单选大题的题干
				Document doc = Jsoup.parse(bigTitle);// 转为HTML
				String val = doc.select(".el_modifiedGrade").get(0).val();// 获取到分值
				// 添加单选大题
				if (bigQuestionService.addBigQuestion(danxuanDati)) {
					// 添加题与选项
					List<Exampaperquestion> questions = danxuanDati.getQuestions();
					for (int i = 0, size = questions.size(); i < size; i++) {
						Exampaperquestion question = questions.get(i);
						question.setType("单选题");
						String questionid = UUIDUtil.getUUID2();
						question.setQuestionid(questionid);
						question.setPaperid(paperId);
						question.setBigquertionid(danxuanDatiId);
						question.setScore(Float.valueOf(val));// 设置每道题的分值
						// 添加试题
						if (examPaperQuestionService.addExamPaperQuestion(question)) {
							List<Exampaperoption> options = question.getOptions();
							for (int j = 0, optionSize = options.size(); j < optionSize; j++) {
								Exampaperoption exampaperoption = options.get(j);
								exampaperoption.setQuestionid(questionid);
								// 添加选项
								examPaperOptionService.addPaperOption(exampaperoption);
							}
						}
					}
				}
			}
			// 添加多选题
			if (duoxuanDati != null) {
				duoxuanDati.setPaperid(paperId);
				String duoxuanDatiId = UUIDUtil.getUUID2();// 产生单选大题ID
				duoxuanDati.setBigquertionid(duoxuanDatiId);
				String bigTitle = duoxuanDati.getBigquestionname();// 获取到多选大题的题干
				Document doc = Jsoup.parse(bigTitle);// 转为HTML
				String val = doc.select(".el_modifiedGrade").get(0).val();// 获取到每道题分值
				// 添加单选大题
				if (bigQuestionService.addBigQuestion(duoxuanDati)) {
					// 添加题与选项
					List<Exampaperquestion> questions = duoxuanDati.getQuestions();
					for (int i = 0, size = questions.size(); i < size; i++) {
						Exampaperquestion question = questions.get(i);
						question.setType("多选题");
						String questionid = UUIDUtil.getUUID2();
						question.setQuestionid(questionid);
						question.setPaperid(paperId);
						question.setBigquertionid(duoxuanDatiId);
						question.setScore(Float.valueOf(val));// 设置每道题的分值
						// 添加试题
						if (examPaperQuestionService.addExamPaperQuestion(question)) {
							List<Exampaperoption> options = question.getOptions();
							for (int j = 0, optionSize = options.size(); j < optionSize; j++) {
								Exampaperoption exampaperoption = options.get(j);
								exampaperoption.setQuestionid(questionid);
								// 添加选项
								examPaperOptionService.addPaperOption(exampaperoption);
							}
						}
					}
				}
			}
			// 添加判断题
			if (panduanDati != null) {
				panduanDati.setPaperid(paperId);
				String panduanDatiId = UUIDUtil.getUUID2();// 产生单选大题ID
				panduanDati.setBigquertionid(panduanDatiId);
				String bigTitle = panduanDati.getBigquestionname();// 获取到单选大题的题干
				Document doc = Jsoup.parse(bigTitle);// 转为HTML
				String val = doc.select(".el_modifiedGrade").get(0).val();
				// 添加单选大题
				if (bigQuestionService.addBigQuestion(panduanDati)) {
					// 添加题与选项
					List<Exampaperquestion> questions = panduanDati.getQuestions();
					for (int i = 0, size = questions.size(); i < size; i++) {
						Exampaperquestion question = questions.get(i);
						question.setType("判断题");
						String questionid = UUIDUtil.getUUID2();
						question.setQuestionid(questionid);
						question.setPaperid(paperId);
						question.setBigquertionid(panduanDatiId);
						question.setScore(Float.valueOf(val));// 设置每道题的分值
						// 添加试题
						if (examPaperQuestionService.addExamPaperQuestion(question)) {
							List<Exampaperoption> options = question.getOptions();
							for (int j = 0, optionSize = options.size(); j < optionSize; j++) {
								Exampaperoption exampaperoption = options.get(j);
								exampaperoption.setOptionsequence(String.valueOf(j));
								exampaperoption.setQuestionid(questionid);
								// 添加选项
								examPaperOptionService.addPaperOption(exampaperoption);
							}
						}
					}
				}
			}
		}

		return true;
	}

	@Override
	public boolean deleteExamPaperBaseInfoByPaperId(String examPaperId) throws SQLException {
		return exampaperMapper.deleteByPrimaryKey(examPaperId) > 0 ? true : false;
	}

	@Resource
	private ExampaperquestionMapper exampaperquestionMapper;
	@Resource
	private BigquestionMapper bigquestionMapper;

	@Override
	public boolean deleteExamPaperAllInfoByPaperId(String paperId) throws SQLException {
		boolean result = true;
		// 1.删除试卷的选项
		result = examPaperOptionService.deletePaperOptionByPaperId(paperId);
		// 2.删除试卷的题干信息
		ExampaperquestionExample exampaperquestionExample = new ExampaperquestionExample();
		ExampaperquestionExample.Criteria criteria_2 = exampaperquestionExample.createCriteria();
		criteria_2.andPaperidEqualTo(paperId);
		List<Exampaperquestion> questions = exampaperquestionMapper.selectByExample(exampaperquestionExample);
		if (questions != null && questions.size() > 0) {
			result = examPaperQuestionService.deleteExamPaperQuestionByPaperId(paperId);
		}
		if (!result) {
			return result;
		}
		// 3.删除试卷的大题信息
		BigquestionExample bigquestionExample = new BigquestionExample();
		BigquestionExample.Criteria criteria_3 = bigquestionExample.createCriteria();
		criteria_3.andPaperidEqualTo(paperId);
		List<Bigquestion> bigQuestions = bigquestionMapper.selectByExample(bigquestionExample);
		if (bigQuestions != null && bigQuestions.size() > 0) {
			result = bigQuestionService.deleteBigQuestionByPaperId(paperId);
		}
		// 4.删除上面成功后删除试卷自己
		if (!result) {
			return result;
		}
		result = this.deleteExamPaperBaseInfoByPaperId(paperId);
		return result;
	}

	@Override
	public boolean deleteExamPaperBatch(List<String> examPaperIds) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateExamPaperAllInfoById(Exampaper examPaper, Map<String, Object> bigQues) throws SQLException {
		// 先根据ID删除试卷
		this.deleteExamPaperAllInfoByPaperId(examPaper.getPaperid());
		// 重新添加试卷
		return this.addExamPaper(examPaper, bigQues);
	}

	@Override
	public boolean updateExamPaperById(Exampaper examPaper) throws SQLException {
		return false;
	}

	@Override
	public boolean updateExamPaperQuestionById(String examPaperId, List<Map<String, Object>> paperAndQuestion)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PageBean<Exampaper> findExampapersWithCondition(int currentPage, int currentCount,
			Map<String, Object> condition) throws SQLException {
		PageBean<Exampaper> pageBean = new PageBean<Exampaper>();
		pageBean.setCurrentPage(currentPage);// 当前页
		pageBean.setCurrentCount(currentCount);// 页大小
		// 1.查询总数
		Integer totalCount = exampaperCustomMapper.getPaperTotalByCondition(condition);
		pageBean.setTotalCount(totalCount);
		/*
		 * 总条数 当前页显示的条数 总页数 10 4 3 11 4 3 12 4 3 13 4 4
		 * 
		 * 公式：总页数=Math.ceil(总条数/当前显示的条数)
		 * 
		 */
		int totalPage = (int) Math.ceil(1.0 * totalCount / currentCount);
		pageBean.setTotalPage(totalPage);

		// 2.查询数据
		int index = (currentPage - 1) * currentCount;// 起始索引值
		condition.put("index", index);
		condition.put("currentCount", currentCount);// 页大小
		List<Exampaper> papers = exampaperCustomMapper.findPapersByCondition(condition);
		pageBean.setProductList(papers);
		return pageBean;
	}

	@Override
	public Exampaper getPaperAllInfoByPaperId(String paperId) throws SQLException {
		return exampaperCustomMapper.findExamPaperAllInfoByPaperId(paperId);
	}

	@Override
	public Map<String, Object> getExamPapeInfo(String paperId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getExamPaperByPaperId(String paperId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> getExamPaperAnswer(String papaerId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public boolean updateExampaperPaperanswer(String paperId, String paperStatus) throws SQLException {
		// TODO Auto-generated method stub
		return exampaperCustomMapper.updateExampaperPaperanswer(paperId, paperStatus)>0?true:false;
	}

}
