package cn.xm.exam.bean.exam;

import java.util.List;

public class Exampaperquestion {
	private String questionid;

	private String paperid;

	private String bigquertionid;

	private String questioncontent;

	private Integer questionsequence;

	private String type;

	private String answer;

	private String analysis;

	private Float score;

	private String questionsource;

	private List<Exampaperoption> options;// 选项集合
	
	private String employeeanswer;//在线考试员工的答案
	
	public String getQuestionid() {
		return questionid;
	}

	public void setQuestionid(String questionid) {
		this.questionid = questionid == null ? null : questionid.trim();
	}

	public String getPaperid() {
		return paperid;
	}

	public void setPaperid(String paperid) {
		this.paperid = paperid == null ? null : paperid.trim();
	}

	public String getBigquertionid() {
		return bigquertionid;
	}

	public void setBigquertionid(String bigquertionid) {
		this.bigquertionid = bigquertionid == null ? null : bigquertionid.trim();
	}

	public String getQuestioncontent() {
		return questioncontent;
	}

	public void setQuestioncontent(String questioncontent) {
		this.questioncontent = questioncontent == null ? null : questioncontent.trim();
	}

	public Integer getQuestionsequence() {
		return questionsequence;
	}

	public void setQuestionsequence(Integer questionsequence) {
		this.questionsequence = questionsequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer == null ? null : answer.trim();
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis == null ? null : analysis.trim();
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getQuestionsource() {
		return questionsource;
	}

	public void setQuestionsource(String questionsource) {
		this.questionsource = questionsource == null ? null : questionsource.trim();
	}

	public List<Exampaperoption> getOptions() {
		return options;
	}

	public void setOptions(List<Exampaperoption> options) {
		this.options = options;
	}
	
	
	
	public String getEmployeeanswer() {
		return employeeanswer;
	}

	public void setEmployeeanswer(String employeeanswer) {
		this.employeeanswer = employeeanswer;
	}

	@Override
	public String toString() {
		return "Exampaperquestion [questionid=" + questionid + ", paperid=" + paperid + ", bigquertionid="
				+ bigquertionid + ", questioncontent=" + questioncontent + ", questionsequence=" + questionsequence
				+ ", type=" + type + ", answer=" + answer + ", analysis=" + analysis + ", score=" + score
				+ ", questionsource=" + questionsource + ", options=" + options + ", employeeanswer=" + employeeanswer
				+ "]";
	}


}