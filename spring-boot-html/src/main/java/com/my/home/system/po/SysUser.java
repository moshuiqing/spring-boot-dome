package com.my.home.system.po;

import java.io.Serializable;
import java.util.List;

public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private Integer uid;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 用户密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;
	
	/**
	 * 是否单点登录0不是  1 是
	 */
	private String isSingle;

	/**
	 * 是否删除
	 */
	private String isdel;

	/**
	 * 角色ID
	 */
	private String roleid;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 *  性别
	 */
	private String sex;
	
	/**
	 * 手机号
	 */
	private String userPhone;
	
	/**
	 * 生日
	 */
	private String userBirthday;
	
	/**
	 * 省
	 */
	private String province;
	
	/**
	 * 市
	 */
	private String city;
	
	/**
	 * 县
	 */
	private String area;
	
	/**
	 * 邮箱
	 */
	private String userEmail;
	
	/**
	 * 自我评价
	 */
	private String myself;
	
	
	/**
	 * 头像
	 */
	private String headImg;
	
	/**
	 * 是否禁用
	 */
	private String isdisable;
	
	
	/**
	 * 最后登录时间
	 */
	private String userEndTime;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 登录状态
	 */
	private String status;
	
	/**
	 * 签名
	 */
	private String sign;
	
	
	/**
	 * 记住我
	 */
	private boolean rememberMe;
	
	/////非数据库///仅仅标识
	
	private String loginType;
	
	private String strmsg;
	
	public String getStrmsg() {
		return strmsg;
	}

	public void setStrmsg(String strmsg) {
		this.strmsg = strmsg;
	}

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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserEndTime() {
		return userEndTime;
	}

	public void setUserEndTime(String userEndTime) {
		this.userEndTime = userEndTime;
	}

	public String getIsdisable() {
		return isdisable;
	}

	public void setIsdisable(String isdisable) {
		this.isdisable = isdisable;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getMyself() {
		return myself;
	}

	public void setMyself(String myself) {
		this.myself = myself;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getUid() {

		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	private List<Module> modules;

	
	
	private String rname;

	private String modelid;

	private String menuid;
	
	private String bigmenuid;
	
	private String del;
	
	private String rdis;
	
	
	
	

	public String getRdis() {
		return rdis;
	}

	public void setRdis(String rdis) {
		this.rdis = rdis;
	}

	public String getDel() {
		return del;
	}

	public void setDel(String del) {
		this.del = del;
	}

	public String getBigmenuid() {
		return bigmenuid;
	}

	public void setBigmenuid(String bigmenuid) {
		this.bigmenuid = bigmenuid;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getMenuid() {
		return menuid;
	}

	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public String getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
	}


	
	

	
}
