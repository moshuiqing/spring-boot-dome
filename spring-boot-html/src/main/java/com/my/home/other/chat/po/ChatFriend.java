package com.my.home.other.chat.po;

import java.util.List;

/**
 * @author ai996
 *  好友 分组
 */
public class ChatFriend {
	
	/**
	 * 好友分组名
	 */
	private String groupname;
	
	/**
	 * 分组id
	 */
	private Integer id;
	
	/**
	 * 分组下的好友列表
	 */
	private List<ChatUser> list;
	
	/**
	 * 分组所属用户
	 */
	private String goupUserid;
	

	

	public String getGoupUserid() {
		return goupUserid;
	}

	public void setGoupUserid(String goupUserid) {
		this.goupUserid = goupUserid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ChatUser> getList() {
		return list;
	}

	public void setList(List<ChatUser> list) {
		this.list = list;
	}
	
	

}
