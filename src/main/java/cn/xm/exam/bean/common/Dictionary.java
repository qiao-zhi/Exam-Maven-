package cn.xm.exam.bean.common;

public class Dictionary {
	/**
	 * 字典ID
	 */
	private String dictionaryid;

	/**
	 * 字典名称
	 */
	private String dictionaryname;

	private String updictionaryid;

	private String isuse;

	private String discription;

	public String getDictionaryid() {
		return dictionaryid;
	}

	public void setDictionaryid(String dictionaryid) {
		this.dictionaryid = dictionaryid == null ? null : dictionaryid.trim();
	}

	public String getDictionaryname() {
		return dictionaryname;
	}

	public void setDictionaryname(String dictionaryname) {
		this.dictionaryname = dictionaryname == null ? null : dictionaryname.trim();
	}

	public String getUpdictionaryid() {
		return updictionaryid;
	}

	public void setUpdictionaryid(String updictionaryid) {
		this.updictionaryid = updictionaryid == null ? null : updictionaryid.trim();
	}

	public String getIsuse() {
		return isuse;
	}

	public void setIsuse(String isuse) {
		this.isuse = isuse == null ? null : isuse.trim();
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription == null ? null : discription.trim();
	}
}