package com.my.home.backbusiness.po;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 刘浩 新闻类型
 */
@ApiModel(value="com.my.home.backbusiness.po.NewsType",description="新闻类型实体")
public class NewsType {

	@ApiModelProperty("主键id")
	private Integer id;
	@ApiModelProperty("类型名称")
	private String typeName;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("是否删除 0:未删除 1:已删除")
	private String isdel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCreateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		if (createTime != null) {

			return dateFormat.format(createTime);
		}

		return null;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	@ApiModelProperty("修改比较参数用")
	private String copytypeName;

	public String getCopytypeName() {
		return copytypeName;
	}

	public void setCopytypeName(String copytypeName) {
		this.copytypeName = copytypeName;
	}
	

}
