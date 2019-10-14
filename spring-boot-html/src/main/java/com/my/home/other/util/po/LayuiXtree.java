package com.my.home.other.util.po;

import java.util.List;

public class LayuiXtree {

	private String value;// id

	private String title;// 内容

	private boolean checked = false; // 是否选中

	private boolean disabled = false; // 是否禁用
	
	private String jibie;

	List<LayuiXtree> data; // 数据
	
	
	public String getJibie() {
		return jibie;
	}

	public void setJibie(String jibie) {
		this.jibie = jibie;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public List<LayuiXtree> getData() {
		return data;
	}

	public void setData(List<LayuiXtree> data) {
		this.data = data;
	}
	
	
	

}
