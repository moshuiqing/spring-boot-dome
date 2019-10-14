package com.my.home.backbusiness.po;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 刘浩
 * 新闻
 */
@ApiModel(value="com.my.home.backbusiness.po.News",description="新闻实体")
public class News {
	
	/**
	 * 主键
	 */
	@ApiModelProperty("主键id")
	private Integer id;
	
	/**
	 * 标题
	 */
	@ApiModelProperty("新闻标题")
	private String title;
	
	/**
	 * 新闻类型
	 */
	@ApiModelProperty("新闻类型id")
	private String typeid;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 作者
	 */
	@ApiModelProperty("作者")
	private String author;
	
	/**
	 * 内容
	 */
	@ApiModelProperty("内容")
	private String content;
	
	/**
	 * 
	 */
	@ApiModelProperty("是否删除")
	private String isdel;
	
	/**
	 * 
	 */
	@ApiModelProperty("新闻文本")
	private String wenben;
	@ApiModelProperty("类型名称")
	private String typeName;
	
	public String getWenben() {
		return wenben;
	}

	public void setWenben(String wenben) {
		this.wenben = wenben;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getCreateTime() {
		
		if(createTime!=null){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getTypeId() {
		return Integer.parseInt(typeid);
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	@ApiModelProperty("转换值用")
	private int typeId;
	
	
	
	

}
