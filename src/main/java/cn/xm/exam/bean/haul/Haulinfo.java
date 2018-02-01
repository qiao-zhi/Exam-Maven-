package cn.xm.exam.bean.haul;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

public class Haulinfo {
	private String bigid;// 大修ID

	private String bigname;// 大修名称

	private String bigdescription;// 大修描述

	private Date bigcreatedate;// 大修创建时间

	private Date bigbegindate;// 大修开始时间

	private Date bigenddate;// 大修结束时间

	private String bigstatus;// 大修状态

	public String getBigid() {
		return bigid;
	}

	public void setBigid(String bigid) {
		this.bigid = bigid == null ? null : bigid.trim();
	}

	public String getBigname() {
		return bigname;
	}

	public void setBigname(String bigname) {
		this.bigname = bigname == null ? null : bigname.trim();
	}

	public String getBigdescription() {
		return bigdescription;
	}

	public void setBigdescription(String bigdescription) {
		this.bigdescription = bigdescription == null ? null : bigdescription.trim();
	}

	public Date getBigcreatedate() {
		return bigcreatedate;
	}

	public void setBigcreatedate(Date bigcreatedate) {
		this.bigcreatedate = bigcreatedate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBigbegindate() {
		return bigbegindate;
	}

	public void setBigbegindate(Date bigbegindate) {
		this.bigbegindate = bigbegindate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBigenddate() {
		return bigenddate;
	}

	public void setBigenddate(Date bigenddate) {
		this.bigenddate = bigenddate;
	}

	public String getBigstatus() {
		return bigstatus;
	}

	public void setBigstatus(String bigstatus) {
		this.bigstatus = bigstatus == null ? null : bigstatus.trim();
	}
}