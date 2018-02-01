package cn.xm.exam.action.question;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.jsoup.Jsoup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import cn.xm.exam.bean.question.Options;
import cn.xm.exam.bean.question.Questions;
import cn.xm.exam.service.question.QuestionbankService;
import cn.xm.exam.service.question.QuestionsService;
import cn.xm.exam.utils.DefaultValue;
import cn.xm.exam.utils.PageBean;
import cn.xm.exam.utils.UUIDUtil;
import cn.xm.exam.utils.ValidateCheck;
import cn.xm.exam.vo.question.QuestionsQueryVo;


/**   
*    
* 项目名称：Exam   
* 类名称：QuestionsAction   
* 类描述： 试题管理的action
* 创建人：LL   
* 创建时间：2017年10月8日 下午5:50:26      
* @version    
*    
*/
@Controller
@Scope("prototype")
public class QuestionsAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private QuestionbankService questionBankService;
	@Resource 
	private QuestionsService questionService;
	private Questions question;
	private List<Options> options;
	private String departmentId;
	private String questionId;
	private List<String> questionIds;
	//试题和选项信息
	private QuestionsQueryVo questionOptionsInfo;
	//关键字
	private String keyWord;
	//题库ID
	private String questionBankId;
	//从题库管理页面传递过来的题库ID
	private String hiddenQuestionBankId;
	//试题类型
	private String type;
	//知识点
	private String knowledge;
	//当前页
	private String currentPage;
	//当前页显示的条数
	private String currentCount;
	
	private Map<String, Object> result;

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}
	
	//获取所有题库名称和ID，初始化下拉列表
	public String getQuestionBankNameList() {
		
		try {
			result = new HashMap<String,Object>();
			
			List<Map<String, Object>> questionBankNameList = questionBankService.getQuestionBankNameList();
			result.put("questionBankNameList", questionBankNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//根据部门ID获取题库的名称和ID
	public String getQeustionBankNameListByDeptId(){
		try {
			result = new HashMap<String,Object>();
			//Subject currentUser = SecurityUtils.getSubject();
			// 获取Session中的用户信息
			/*User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
			String departmentIdSession = user.getDepartmentid();// 获取部门ID
			boolean permitted = currentUser.isPermitted("bankmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
			String departmentId = permitted ? null : departmentIdSession;
			*/	
			List<Map<String, Object>> questionBankNameList = questionBankService.getQuestionBankNameListByDeptId(null);
			result.put("questionBankNameList", questionBankNameList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//添加单个试题
	public String addQuestion(){
			
		try {
			//根据题库ID查询出部门ID
			//Questionbank questionBankInfo = questionBankService.getQuestionbankById(question.getQuestionbankid());
			//departmentId = questionBankInfo.getDepartmentid();
		//1、根据部门ID判断试题的等级
		//根据部门的命名规则，长度为2代表厂级，5代表部门级，8代表班组级
		/*int length = departmentId.length();
		switch(length){
			case 2 : question.setLevel("1");
					break;
			case 5 : question.setLevel("2");
					break;
			case 8 : question.setLevel("3");
					break;
			default:question.setLevel("3");
		}*/
		//2、对带标签的属性值进行处理
		//设置不带标签的答案解析
		/*question.setAnalysis(Jsoup.parse(question.getAnalysiswithtag()).text());
		question.setAnalysiswithtag(question.getAnalysiswithtag().replace("\r\n", "").replace("\"","\'"));*/
		//设置不带标签的试题内容
		question.setQuestion(Jsoup.parse(question.getQuestionwithtag()).text());
		question.setQuestionwithtag(question.getQuestionwithtag().replace("\r\n", "").replace("\"","\'"));
			
		//3、设置试题的id
		String questionId = questionService.getNextQuestionId(question.getQuestionbankid());
		question.setQuestionid(questionId);
		//4、设置选项的id，选项对应题目的id
		for (Options option : options) {
			option.setOptionid(UUIDUtil.getUUID2());
			option.setQuestionid(questionId);
		}
		//5、对试题的答案进行处理，去除多选题的答案中间的","以及空格
		question.setAnswer(question.getAnswer().replace(",","").replace(" ",""));
		//6、默认没有图片
		question.setHaspicture("0");
		
		boolean isInsert = questionService.saveQuestionsAndOptions(question, options);
		result = new HashMap<String, Object>();
		if (isInsert) {
			result.put("result", "添加成功！");
		} else {
			result.put("result", "添加失败！");
			
		}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	//删除单个试题
	public String deleteQuestionById(){
		
		try {
			
			boolean isDelete = questionService.deleteQuestionById(questionId);
			result = new HashMap<String, Object>();
			if (isDelete) {
				result.put("result", "删除成功！");
			} else {
				result.put("result", "删除失败！");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//根据ID集合批量删除试题
	public String deleteQuestionByIds(){
		try {			
			int countDelete = questionService.deleteQuestionBatch(questionIds);
			result = new HashMap<String, Object>();
			if (countDelete==questionIds.size()) {
				result.put("result", "删除成功！");
			} else {
				result.put("result", "删除失败！");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据试题ID查询试题与选项的信息,显示试题的详细信息
	public String getQuestionAndOptionsInfo(){
		try {
			QuestionsQueryVo questionInfo = questionService.getQuestionById(questionId);			
			result = new HashMap<String,Object>();
			result.put("questionInfo", questionInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	//根据条件查询试题信息
	public String findQuestionsInfo(){
		
		try {
			Map<String,Object> condition = new HashMap<String,Object>();
			result = new HashMap<String,Object>();
			condition = generationCondition(condition);
			PageBean<QuestionsQueryVo> pageBean = questionService.findQuestionWithCondition(Integer.valueOf(currentPage), Integer.valueOf(currentCount), condition);
		
			result.put("pageBean", pageBean);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return SUCCESS;
	} 
	
	//根据试题ID查询试题信息,用于向前台的修改试题界面回显试题相关信息，采用向值栈中放数据的形式
	public String modifyQuestionInfoById(){
		try {
			questionOptionsInfo = questionService.getQuestionById(questionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "ok";
	}
	
	//修改试题信息,修改试题信息时先将该试题的选项信息全部删除，重新添加该试题的选项
	public String modifyQuestionInfo(){
		
		try {
			//先将试题中的选项信息全部删除
			boolean isDeleteOptionsOld = questionService.deleteOptionsByQuestionId(question.getQuestionid());
			
			result = new HashMap<String,Object>();
			//1、对带标签的属性值进行处理
			if(question.getAnalysiswithtag()!=null && !"".equals(question.getAnalysiswithtag())){				
				//设置不带标签的答案解析
				question.setAnalysis(Jsoup.parse(question.getAnalysiswithtag()).text());
				question.setAnalysiswithtag(question.getAnalysiswithtag().replace("\r\n", "").replace("\"","\'"));
			}else{
				question.setAnalysiswithtag(null);
			}
			if(question.getQuestionwithtag()!=null && !"".equals(question.getQuestionwithtag())){				
				//设置不带标签的试题内容
				question.setQuestion(Jsoup.parse(question.getQuestionwithtag()).text());
				question.setQuestionwithtag(question.getQuestionwithtag().replace("\r\n", "").replace("\"","\'"));				
			}else{
				question.setQuestionwithtag(null);
			}
			//2、设置选项的id，选项对应题目的id
			for (Options option : options) {
				option.setOptionid(UUIDUtil.getUUID2());
				option.setQuestionid(question.getQuestionid());
			}
			//3、对试题的答案进行处理，去除多选题的答案中间的","以及空格
			question.setAnswer(question.getAnswer().replace(",","").replace(" ",""));	
						
			if(isDeleteOptionsOld){
				boolean isUpdate = questionService.updateQuestion(question, options);
				if(isUpdate){
					result.put("result","修改成功！");
				}else{
					result.put("result","修改失败！");
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	//根据试题的id集合以及题库ID批量移动试题
	public String batchMoveQuestionsByIds(){
		try {
			boolean isUpdate = questionService.updateQuestionBatch(questionIds, questionBankId);
			result = new HashMap<String, Object>();
			if (isUpdate) {
				result.put("result", "批量移动成功！");
			} else {
				result.put("result", "批量移动失败！");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	//组装查询条件
	private Map<String,Object> generationCondition(Map<String,Object> condition){
		
		//对当前页信息进行设置
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		//对当前页显示的信息进行设置
		if (currentCount == null || "".equals(currentCount.trim())) {
			currentCount = DefaultValue.PAGE_SIZE;
		}
			
		/*User user = (User) ServletActionContext.getRequest().getSession().getAttribute("userinfo");
		String departmentIdSession = user == null ? null : user.getDepartmentid();// 获取到session部门ID
		// 获取用户信息
		Subject currentUser = SecurityUtils.getSubject();
		boolean permitted = currentUser.isPermitted("bankmanager:factory");// 判断是否有全厂管理的权限,有就不添加部门ID，没有就设为当前Session中的部门ID
		String departmentId = permitted ? null : departmentIdSession;
		condition.put("department_Id", departmentId);*/
		
		if(ValidateCheck.isNotNull(keyWord)){
			condition.put("question_Content", keyWord);
			result.put("question_Content", keyWord);
		}
		
		if(ValidateCheck.isNotNull(questionBankId)){
			condition.put("questionBank_Id", questionBankId);
		}
		
		if(ValidateCheck.isNotNull(hiddenQuestionBankId)){
			condition.put("questionBank_Id", hiddenQuestionBankId);
		}
		
		if(ValidateCheck.isNotNull(type)){
			condition.put("question_Type", type);
		}
		
		if(ValidateCheck.isNotNull(knowledge)){
			condition.put("question_knowledge", knowledge);
		}
		
		return condition;
	}
	
	
	public Questions getQuestion() {
		return question;
	}

	public void setQuestion(Questions question) {
		this.question = question;
	}

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getQuestionBankId() {
		return questionBankId;
	}

	public void setQuestionBankId(String questionBankId) {
		this.questionBankId = questionBankId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentCount() {
		return currentCount;
	}

	public void setCurrentCount(String currentCount) {
		this.currentCount = currentCount;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public QuestionsQueryVo getQuestionOptionsInfo() {
		return questionOptionsInfo;
	}

	public List<String> getQuestionIds() {
		return questionIds;
	}

	public void setQuestionIds(List<String> questionIds) {
		this.questionIds = questionIds;
	}

	public String getHiddenQuestionBankId() {
		return hiddenQuestionBankId;
	}

	public void setHiddenQuestionBankId(String hiddenQuestionBankId) {
		this.hiddenQuestionBankId = hiddenQuestionBankId;
	}

	

	
	
}
