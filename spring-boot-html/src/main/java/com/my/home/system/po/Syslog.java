package com.my.home.system.po;

/**
 * @author ai996
 * 系统日志
 */
public class Syslog {
	
	/**
	 * 主键id
	 */
	private Integer logId;
	
	/**
	 * 请求地址
	 */
	private String url;
	
	/**
	 * 操作方式
	 */
	private String method;
	
	/**
	 * 操作ip
	 */
	private String ip;
	
	/**
	 * 耗时
	 */
	private String timeConsuming;
	
	/**
	 * 是否异常
	 */
	private String isAbnormal;
	
	/**
	 * 操作人
	 */
	private String operator;
	
	/**
	 * 操作时间
	 */
	private String operatingTime;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(String timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	public String getIsAbnormal() {
		return isAbnormal;
	}

	public void setIsAbnormal(String isAbnormal) {
		this.isAbnormal = isAbnormal;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOperatingTime() {
		return operatingTime;
	}

	public void setOperatingTime(String operatingTime) {
		this.operatingTime = operatingTime;
	}

	
	
	
	
}
