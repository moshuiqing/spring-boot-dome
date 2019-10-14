package com.my.home.app.service;

import com.my.home.app.po.AppUser;

public interface AppUserService {
	
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
