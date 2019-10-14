package com.my.home.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.my.home.system.po.Role;

/**
 * @author ai996
 * 角色dao
 */
@Mapper
public interface RoleMapper {
	
	/**
	 * @param role
	 * @return
	 * 查询角色
	 */
	 Role simpleFoundRole(Role role);
	 
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
	List<Role> pageFoundRole(@Param("r")Role r,@Param("start")Integer start,@Param("end")Integer end);
	
	/**
	 * 查询数量
	 * @param r
	 * @return
	 */
	Integer pageCountRole(@Param("r")Role r);
}
