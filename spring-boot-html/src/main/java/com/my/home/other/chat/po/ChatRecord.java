package com.my.home.other.chat.po;

import java.util.Date;

/**
 * @author ai996
 * 聊天记录
 */
public class ChatRecord {
	
	/**
	 * 记录id
	 */
	private Integer id;

	
	/**
	 * 发送者ID
	 */
	private String sendId;
	
	/**
	 * 接受者id  或群id
	 */
	private String toId;
	
	/**
	 * 信息
	 */
	private String  content;
	
	/**
	 * 时间轴
	 */
	private Date timestamp;
	
	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 聊天信息状态，0未读 1 已读
	 */
	private String state;

	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

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
	
	


}
