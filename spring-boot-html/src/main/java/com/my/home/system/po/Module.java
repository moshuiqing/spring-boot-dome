package com.my.home.system.po;

import java.io.Serializable;

/**
 * @author ai996
 * 权限配置表
 */
public class Module implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer mid;
	
	private String mname;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}
	
	
}
