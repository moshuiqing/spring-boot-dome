package com.my.home.app.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.my.home.app.po.AppUser;

/**
 * @author ai996
 * 手机端用户接口
 */
@Mapper
public interface AppUserMapper {
	
	/**
	 * 手机端登录
	 * @param appUser
	 * @return
	 */
	AppUser appLogin(AppUser appUser);
	
	/**
	 * 手机用户注册
	 * @param appUser
	 * @return
	 */
	Integer appInsert(AppUser appUser);

}
