package cn.xm.exam.bean.exam;

public class Exampaperoption {
    private Integer optionid;

    private String questionid;

    private String optioncontent;

    private String optionsequence;

    private String description;

    private String isanswer;

    public Integer getOptionid() {
        return optionid;
    }

    public void setOptionid(Integer optionid) {
        this.optionid = optionid;
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

    public String getOptionsequence() {
        return optionsequence;
    }

    public void setOptionsequence(String optionsequence) {
        this.optionsequence = optionsequence == null ? null : optionsequence.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getIsanswer() {
        return isanswer;
    }

    public void setIsanswer(String isanswer) {
        this.isanswer = isanswer == null ? null : isanswer.trim();
    }
}