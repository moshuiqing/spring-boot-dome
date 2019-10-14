package com.my.home.other.chat.po;

/**
 * @author ai996
 *  聊天用户信息
 */
public class ChatUser {
	
	private String id;
	
	/**
	 * 用户名
	 */
	private String username;

	
	/**
	 * 登录状态 //在线状态 online：在线、hide：隐身  /若值为offline代表离线
	 */
	private String status;
	
	/**
	 * 签名  
	 */
	private String sign;
	
	/**
	 * 头像
	 */
	private String avatar;
	

	/**
	 * 时间轴
	 */
	private Long timestamp;
	
	/**
	 * 内容
	 */
	private String content;
	
	

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}


	
	/**
	 * 发送者ID
	 */
	private String sendId;
	
	/**
	 * 接受者id  或群id
	 */
	private String toId;


	
	private String type;


	public String getSendId() {
		return sendId;
	}

	public void setSendId(String sendId) {
		this.sendId = sendId;
	}

	public String getToId() {
		return toId;
	}

	public void setToId(String toId) {
		this.toId = toId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	//取数据用 存分组
	private String group;

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}
	
	
	
	
}
