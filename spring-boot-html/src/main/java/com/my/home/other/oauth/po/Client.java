package com.my.home.other.oauth.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ai996
 * 客户端信息
 */
@ApiModel(value="com.my.home.other.oauth.po.Client",description="授权客户端实体")
public class Client {
	
	@ApiModelProperty("主键id")
	private String id;
	
	@ApiModelProperty("客户端名称")
	private String clientName;
	
	@ApiModelProperty("认证Id")
	private String clientId;
	
	@ApiModelProperty("赋予的key")
	private String clientSecret;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	

}
