package cn.xm.exam.bean.question;

public class Options{
   
	private String optionid;

    private String questionid;

    private String optioncontent;

    private String optionwithtag;

    private String optionsequence;

    private String isanswer;

    private String description;

    public String getOptionid() {
        return optionid;
    }

    public void setOptionid(String optionid) {
        this.optionid = optionid == null ? null : optionid.trim();
    }

    public String getQuestionid() {
        return questionid;
    }

    public void setQuestionid(String questionid) {
        this.questionid = questionid == null ? null : questionid.trim();
    }

    public String getOptioncontent() {
        return optioncontent;
    }

    public void setOptioncontent(String optioncontent) {
        this.optioncontent = optioncontent == null ? null : optioncontent.trim();
    }

    public String getOptionwithtag() {
        return optionwithtag;
    }

    public void setOptionwithtag(String optionwithtag) {
        this.optionwithtag = optionwithtag == null ? null : optionwithtag.trim();
    }

    public String getOptionsequence() {
        return optionsequence;
    }

    public void setOptionsequence(String optionsequence) {
        this.optionsequence = optionsequence == null ? null : optionsequence.trim();
    }

    public String getIsanswer() {
        return isanswer;
    }

    public void setIsanswer(String isanswer) {
        this.isanswer = isanswer == null ? null : isanswer.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}