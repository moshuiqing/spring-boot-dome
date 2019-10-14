package com.my.home.system.service;

import java.util.List;

import com.my.home.other.util.JsonMap;
import com.my.home.other.util.LayuiPage;
import com.my.home.system.po.Role;

public interface RoleService {
	/**
	 * @param role
	 * @return
	 * 查询角色根据用户id
	 */
	public JsonMap simpleFoundRole(String id);
	
	 /**
	  * 查询集合角色
	 * @param role
	 * @return
	 */
	List<Role> FoundRole(Role role);
	
	/**
	 * 新增
	 * @param role
	 * @return
	 */
	Integer insertRole(Role role);
	
	/**
	 * 修改
	 * @param role
	 * @return
	 */
	Integer updateRole(Role role);
	
	/**
	 * 删除
	 * @param role
	 * @return
	 */
	Integer deleteRole(Role role);
	
	/**
	 * 分页
	 * @param r
	 * @return
	 */
	List<Role> pageFoundRole(Role r,LayuiPage page);
	
	/**
	 * 查询数量
	 * @param r
	 * @return
	 */
	Integer pageCountRole(Role r);
	

}
