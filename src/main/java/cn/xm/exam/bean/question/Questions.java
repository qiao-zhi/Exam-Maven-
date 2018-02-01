package cn.xm.exam.bean.question;

import java.util.Date;
import java.util.List;

public class Questions {
    private String questionid;

    private String questionbankid;

    private String question;

    private String questionwithtag;

    private String answer;

    private String analysiswithtag;

    private String analysis;

    private String type;

    private String level;

    private String employeeid;

    private String emplloyeename;

    private Date uploadtime;

    private String status;

    private String knowledgetype;

    private String haspicture;
    
    private List<Options> options;

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    public String getQuestionbankid() {
        return questionbankid;
    }

    public void setQuestionbankid(String questionbankid) {
        this.questionbankid = questionbankid == null ? null : questionbankid.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }

    public String getQuestionwithtag() {
        return questionwithtag;
    }

    public void setQuestionwithtag(String questionwithtag) {
        this.questionwithtag = questionwithtag == null ? null : questionwithtag.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAnalysiswithtag() {
        return analysiswithtag;
    }

    public void setAnalysiswithtag(String analysiswithtag) {
        this.analysiswithtag = analysiswithtag == null ? null : analysiswithtag.trim();
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis == null ? null : analysis.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getEmplloyeename() {
        return emplloyeename;
    }

    public void setEmplloyeename(String emplloyeename) {
        this.emplloyeename = emplloyeename == null ? null : emplloyeename.trim();
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getKnowledgetype() {
        return knowledgetype;
    }

    public void setKnowledgetype(String knowledgetype) {
        this.knowledgetype = knowledgetype == null ? null : knowledgetype.trim();
    }

    public String getHaspicture() {
        return haspicture;
    }

    public void setHaspicture(String haspicture) {
        this.haspicture = haspicture == null ? null : haspicture.trim();
    }

	public List<Options> getOptions() {
		return options;
	}

	public void setOptions(List<Options> options) {
		this.options = options;
	}
    
}