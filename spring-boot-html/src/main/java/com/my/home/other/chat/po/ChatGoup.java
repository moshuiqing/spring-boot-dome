package com.my.home.other.chat.po;

import java.util.List;

/**
 * @author ai996
 * 群组 实体
 */
public class ChatGoup {
	
	/**
	 * 群组id
	 */
	private Integer id;
	
	/**
	 * 群组名
	 */
	private String groupname;
	
	/**
	 * 群头像
	 */
	private String avatar;
	
	/**
	 * 群所属人
	 */
	private String userid;
	
	
	/**
	 * 群说明
	 */
	private String content;
	
	
	private List<ChatUser> list;
	
	
	
	
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<ChatUser> getList() {
		return list;
	}

	public void setList(List<ChatUser> list) {
		this.list = list;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	

}
