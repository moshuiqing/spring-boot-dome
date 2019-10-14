package com.my.home.system.po;

import java.io.Serializable;

/**
 * @author ai996
 *  大菜单类
 */
public class BigMenu implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	private  Integer id;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 菜单data-menu名称
	 */
	private String menu1;
	
	/**
	 * 图标
	 */
	private String icon;
	
	/**
	 * 是否删除
	 */
	private String isdel;
	
	private Integer sort;
	
	
	

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public String getMenu1() {
		return menu1;
	}

	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	
	
	
}
