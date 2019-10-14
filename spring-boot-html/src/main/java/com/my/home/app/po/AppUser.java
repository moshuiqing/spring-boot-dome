package com.my.home.app.po;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author ai996
 * 手机端用户
 *
 */
@ApiModel(value = "com.my.home.app.po.AppUser", description = "手机端用户")
public class AppUser implements Serializable {
	
		/**
		 * 主键id
		 */
		@ApiModelProperty(value = "主键唯一id")
		private  String id;
		
		/**
		 * 生日
		 */
		@ApiModelProperty(value = "生日")
		private String birthday;
		
		/**
		 * 头像
		 */
		@ApiModelProperty(value = "头像")
		private String HeadImg;
		
		/**
		 * 手机号
		 */
		@ApiModelProperty(value = "手机号")
		private String phone;
		
		/**
		 * 用户名称
		 */
		@ApiModelProperty(value="用户名称")
		private String userName;
		
		/**
		 * 用户密码
		 */
		@ApiModelProperty(value="用户密码")
		private String password;
		
		/**
		 * 盐
		 */
		@ApiModelProperty(value="盐")
		private String salt;
		
		/**
		 * 是否单点登录0不是  1 是
		 */
		@ApiModelProperty(value="是否单点登录 0：不是 1：是")
		private String isSingle;
		
		/**
		 * 0 未删除  1 已删除
		 */
		@ApiModelProperty(value="是否删除 0：未删除 1：已删除")
		private String isdel ;
		
		/**
		 * 0 启用  1 禁用
		 */
		@ApiModelProperty(value="是否禁用 0：启用 1：禁用")
		private String isdisable;
		
		/**
		 * 描述
		 */
		@ApiModelProperty(value="用户描述")
		private String remake;
		
		/**
		 * 唯一会话标识
		 */
		@ApiModelProperty(value="唯一会话标识")
		private Serializable sessionId;
		
		/**
		 * 记住我
		 */
		@ApiModelProperty(value="记住我")
		private boolean rememberMe;
		
		/////非数据库///仅仅标识
		@ApiModelProperty(value="登陆类型")
		private String loginType;

		public String getLoginType() {
			return loginType;
		}

		public void setLoginType(String loginType) {
			this.loginType = loginType;
		}

		

		public boolean isRememberMe() {
			return rememberMe;
		}

		public void setRememberMe(boolean rememberMe) {
			this.rememberMe = rememberMe;
		}
		
		

		public Serializable getSessionId() {
			return sessionId;
		}

		public void setSessionId(Serializable sessionId) {
			this.sessionId = sessionId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getIsdel() {
			return isdel;
		}

		public void setIsdel(String isdel) {
			this.isdel = isdel;
		}

		public String getIsdisable() {
			return isdisable;
		}

		public void setIsdisable(String isdisable) {
			this.isdisable = isdisable;
		}

		public String getRemake() {
			return remake;
		}

		public void setRemake(String remake) {
			this.remake = remake;
		}

		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

		public String getIsSingle() {
			return isSingle;
		}

		public void setIsSingle(String isSingle) {
			this.isSingle = isSingle;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getHeadImg() {
			return HeadImg;
		}

		public void setHeadImg(String headImg) {
			HeadImg = headImg;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		
		
		
		
		
		

}
