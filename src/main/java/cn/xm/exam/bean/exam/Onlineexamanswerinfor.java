package cn.xm.exam.bean.exam;

public class Onlineexamanswerinfor {
    private String answerinfoid;

    private String questionid;

    private String paperid;

    private String questiontype;

    private String employeeid;

    private String answer;

    private String selectoption;

    private Float questionscore;

    private Float score;

    private String onlineanswerexamid;

    public String getAnswerinfoid() {
        return answerinfoid;
    }

    public void setAnswerinfoid(String answerinfoid) {
        this.answerinfoid = answerinfoid == null ? null : answerinfoid.trim();
    }

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

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype == null ? null : questiontype.trim();
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getSelectoption() {
        return selectoption;
    }

    public void setSelectoption(String selectoption) {
        this.selectoption = selectoption == null ? null : selectoption.trim();
    }

    public Float getQuestionscore() {
        return questionscore;
    }

    public void setQuestionscore(Float questionscore) {
        this.questionscore = questionscore;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getOnlineanswerexamid() {
        return onlineanswerexamid;
    }

    public void setOnlineanswerexamid(String onlineanswerexamid) {
        this.onlineanswerexamid = onlineanswerexamid == null ? null : onlineanswerexamid.trim();
    }
}