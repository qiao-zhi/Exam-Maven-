package cn.xm.exam.vo.question;

import java.util.Date;

/**   
*    
* 项目名称：Exam   
* 类名称：QuestionbankQuestionsDepartment   
* 类描述： 视图questionbank_questions_department的实体类
* 题库相关信息，题库所属部门，题库中试题的统计  
* 创建人：Leilong  
* 创建时间：2017年9月21日 下午12:04:20      
* @version    
*    
*/
public class QuestionbankQuestionsDepartment {
    private String questionbankid;

    private String questionbankname;

    private String departmentid;

    private String employeename;

    private Date createtime;

    private String isuse;

    private String description;
    
    private String categorynameid;
    
    private String typename;
    
    private String uptypeid;
    
    private String departmentname;

    private int sumquestions;

    private int countsingle;

    private int countmultiple;

    private int counttrueorfalse;

    public String getQuestionbankid() {
        return questionbankid;
    }

    public void setQuestionbankid(String questionbankid) {
        this.questionbankid = questionbankid == null ? null : questionbankid.trim();
    }

    public String getQuestionbankname() {
        return questionbankname;
    }

    public void setQuestionbankname(String questionbankname) {
        this.questionbankname = questionbankname == null ? null : questionbankname.trim();
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid == null ? null : departmentid.trim();
    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename == null ? null : employeename.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getIsuse() {
        return isuse;
    }

    public void setIsuse(String isuse) {
        this.isuse = isuse == null ? null : isuse.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname == null ? null : departmentname.trim();
    }

	public int getSumquestions() {
		return sumquestions;
	}

	public void setSumquestions(int sumquestions) {
		this.sumquestions = sumquestions;
	}

	public int getCountsingle() {
		return countsingle;
	}

	public void setCountsingle(int countsingle) {
		this.countsingle = countsingle;
	}

	public int getCountmultiple() {
		return countmultiple;
	}

	public void setCountmultiple(int countmultiple) {
		this.countmultiple = countmultiple;
	}

	public int getCounttrueorfalse() {
		return counttrueorfalse;
	}

	public void setCounttrueorfalse(int counttrueorfalse) {
		this.counttrueorfalse = counttrueorfalse;
	}

	public String getCategorynameid() {
		return categorynameid;
	}

	public void setCategorynameid(String categorynameid) {
		this.categorynameid = categorynameid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getUptypeid() {
		return uptypeid;
	}

	public void setUptypeid(String uptypeid) {
		this.uptypeid = uptypeid;
	}
	
	
    
}