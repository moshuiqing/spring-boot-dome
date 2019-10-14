package com.my.home.system.po;

/**
 * @author ai996
 * 角色表
 */
public class Role {
	
	/**
	 * 主键id
	 */
	private String rid;
	
	/**
	 * 角色名称
	 */
	private String rname;
	
	/**
	 * 描述
	 */
	private String remake;
	
	/**
	 * 权限id
	 */
	private String modelid;
	
	/**
	 * 竖菜单
	 */
	private String menuid;
	
	/**
	 * 横菜单
	 */
	private String bigmenuid;
	
	/**
	 * 是否删除
	 */
	private String isdel;
	
	/**
	 * 是否禁用
	 */
	private String isdisable;
	

	public String getRemake() {
		return remake;
	}

	public void setRemake(String remake) {
		this.remake = remake;
	}

	public String getIsdisable() {
		return isdisable;
	}

	public void setIsdisable(String isdisable) {
		this.isdisable = isdisable;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getBigmenuid() {
		return bigmenuid;
	}

	public void setBigmenuid(String bigmenuid) {
		this.bigmenuid = bigmenuid;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	
	
	

}
