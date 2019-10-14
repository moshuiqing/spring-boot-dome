package com.my.home.system.service;

import java.util.List;

import com.my.home.other.util.LayuiPage;
import com.my.home.system.po.SysUser;

public interface UserService {
	
	/**
	 * @param user
	 * @return
	 * info: 新增用户
	 */
	public Integer insert(SysUser user);
	
	/**
	 * @param user
	 * @return
	 * info:登录
	 */
	public SysUser login(SysUser user);
	
	/**
	 * 根据姓名登录
	 * @param username
	 * @return
	 */
	public SysUser login(String username);
	
	/**
	 * 修改
	 * @param user
	 * @return
	 */
	Integer update(SysUser user);
	
	
	/**
	 * 简易查询 
	 * @param user
	 * @return
	 */
	List<SysUser> simpleFound(SysUser user);
	
	
	/**
	 * 分页查询
	 * @param s
	 * @param start
	 * @param end
	 * @return
	 */
	List<SysUser> pageFound(SysUser user,LayuiPage page);
	
	/**
	 * 查询数量
	 * @param s
	 * @return
	 */
	Integer pageCount(SysUser s);
	
	/**
	 * 删除用户
	 * @param s
	 * @return
	 */
	Integer deleteUser(SysUser s);
	
	/**
	 * 批量删除
	 * @param user
	 */
	Integer deletesUser(String[] uids);
	
	
	
	void out(String falg);

}
