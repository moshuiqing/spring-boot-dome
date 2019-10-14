package com.my.home.other.util.po;

import java.util.List;

/**
 * @author ai996
 * layui tree
 */
public class LayuiTree {
	
	private Integer id;
	
	private String name;
	
	private boolean spread;
	
	private String href;
	private String hreftext;
	
	private String icon;
	
	private Integer sort;
	
	private String menu1;
	
	private List<LayuiTree> children;
	
	private String jibie;
	
	
	

	public String getJibie() {
		return jibie;
	}

	public void setJibie(String jibie) {
		this.jibie = jibie;
	}

	public String getHreftext() {
		return hreftext;
	}

	public void setHreftext(String hreftext) {
		this.hreftext = hreftext;
	}

	public String getMenu1() {
		return menu1;
	}

	public void setMenu1(String menu1) {
		this.menu1 = menu1;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public boolean isSpread() {
		return spread;
	}

	public void setSpread(boolean spread) {
		this.spread = spread;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<LayuiTree> getChildren() {
		return children;
	}

	public void setChildren(List<LayuiTree> children) {
		this.children = children;
	}
	
	

}
