package com.my.home.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.home.app.mapper.AppUserMapper;
import com.my.home.app.po.AppUser;
import com.my.home.app.service.AppUserService;
import com.my.home.other.util.systemutil.SimpleUtils;

/**
 * @author ai996 手机用户service
 */
@Service
public class AppUserServiceImpl implements AppUserService {

	/**
	 * dao注入
	 */
	@Autowired
	private AppUserMapper appUserMapper;

	@Override
	public AppUser appLogin(AppUser appUser) {
		if (appUser.getUserName() == null || appUser.getUserName().equals("")) {

			return null;
		}
		return appUserMapper.appLogin(appUser);
	}
	@Override
	public Integer appInsert(AppUser appUser) {
		boolean flag = SimpleUtils.isEmpty(appUser.getUserName(), appUser.getId(), appUser.getPassword(),
				appUser.getSalt());
		if (flag) {
			return -2;
		}
		return appUserMapper.appInsert(appUser);
	}

}
