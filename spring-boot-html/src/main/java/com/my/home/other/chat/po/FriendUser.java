package com.my.home.other.chat.po;

/**
 * @author ai996
 *  分组人员关联表
 */
public class FriendUser {
	
	private Integer id;
	
	/**
	 * 用户id
	 */
	private String userid;
	
	/**
	 * 分组id
	 */
	private String Friendid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFriendid() {
		return Friendid;
	}

	public void setFriendid(String friendid) {
		Friendid = friendid;
	}
	
	
	
}
