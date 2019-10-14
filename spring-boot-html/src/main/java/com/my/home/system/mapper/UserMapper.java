package com.my.home.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.my.home.system.po.SysUser;


/**
 * @author ai996
 * 用户dao
 */
@Mapper
public interface UserMapper {
	
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
	 * 登录查询
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
	List<SysUser> pageFound(@Param("s")SysUser s,@Param("start")Integer start,@Param("end")Integer end);
	
	/**
	 * 查询数量
	 * @param s
	 * @return
	 */
	Integer pageCount(@Param("s")SysUser s);
	
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
	void deletesUser(@Param("uids")String[] uids);

}
