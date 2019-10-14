package com.my.home.other.chat.po;

/**
 * @author ai996
 *  通知消息
 */
public class ChatNotice {
	
	/**
	 * 主键id
	 */
	private Integer id;
	
	/**
	 * 申请名称
	 */
	private String content;
	
	/**
	 * 接收用户id
	 */
	private String uid;
	
	/**
	 * 发送用户id
	 */
	private String from;
	
	/**
	 * 通知类型 1 加好友  ，2 加群 3:系统消息
	 */
	private String type;
	
	
	/**
	 * 通知消息
	 */
	private String remark;
	
	/**
	 * 分组id(或群id)
	 */
	private String from_group;
	
	/**
	 * 
	 */
	private String href;
	
	/**
	 *  0未读  1 已读 
	 */
	private String read;
	
	/**
	 * 时间
	 */
	private String time;
	
	private ChatUser user;

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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
	public String getFrom_group() {
		return from_group;
	}

	public void setFrom_group(String from_group) {
		this.from_group = from_group;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getRead() {
		return read;
	}

	public void setRead(String read) {
		this.read = read;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public ChatUser getUser() {
		return user;
	}

	public void setUser(ChatUser user) {
		this.user = user;
	}

	


}
