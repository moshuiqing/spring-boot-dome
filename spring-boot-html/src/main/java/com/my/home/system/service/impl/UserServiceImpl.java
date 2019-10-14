package com.my.home.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.home.other.util.Global;
import com.my.home.other.util.LayuiPage;
import com.my.home.other.util.systemutil.SystemUtils;
import com.my.home.system.mapper.MenusMapper;
import com.my.home.system.mapper.UserMapper;
import com.my.home.system.po.BigMenu;
import com.my.home.system.po.Menus;
import com.my.home.system.po.SysUser;
import com.my.home.system.service.UserService;

@Service(value="userService")
public class UserServiceImpl implements UserService {
//@Transactional     //事物注解
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private EhCacheManager ehCacheManager;

	@Autowired
	private MenusMapper menusMapper;

	@Override
	public Integer insert(SysUser user) {

		if (SystemUtils.isEmpty(user.getUsername(), user.getPassword(), user.getSalt())) {
			return 0;
		}
		return userMapper.insert(user);
	}

	@Override
	public SysUser login(SysUser user) {
		SysUser u = userMapper.login(user);
		if (u == null) {
			return null;
		}
		// 存入缓存
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");

		@SuppressWarnings("unchecked")
		List<BigMenu> bigMenus = (List<BigMenu>) sysCache.get(Global.BIGMENU);

		String[] menuids = null;
		if (u.getMenuid() != null) {
			menuids = u.getMenuid().split(",");
		}
		String[] bigmenuids = null;
		if (u.getBigmenuid() != null) {
			bigmenuids = u.getBigmenuid().split(",");
		}

		Map<String, Object> menumap = new HashMap<>();
		List<BigMenu> bms = new ArrayList<>();
		// 减去大菜单
		for (BigMenu bigMenu : bigMenus) {
			if (ArrayUtils.contains(bigmenuids, bigMenu.getId().toString())) {
				bms.add(bigMenu);
				Menus ms = new Menus();
				ms.setMenu1(bigMenu.getMenu1());
				List<Menus> menuss = menusMapper.simleFound(ms);
				Iterator<Menus> iterator = menuss.iterator();
				while (iterator.hasNext()) {
					Menus menus = iterator.next();
					if (ArrayUtils.contains(menuids, menus.getId().toString())) {
						Menus men = new Menus();
						men.setParent(menus.getId() + "");
						List<Menus> list = menusMapper.simleFound(men);
						Iterator<Menus> iterator2 = list.iterator();
						while (iterator2.hasNext()) {
							Menus menus2 = iterator2.next();
							if (!ArrayUtils.contains(menuids, menus2.getId().toString())) {
								iterator2.remove();
							}
						}
						menus.setChildren(list);

					} else {
						iterator.remove();
					}

				}
				menumap.put(bigMenu.getMenu1(), menuss);
			}
		}
		sysCache.put(u.getUsername(), menumap);
		sysCache.put(Global.USERBIGMENU, bms);
		
		
		
		

		return u;
	}

	@Override
	public SysUser login(String username) {

		return userMapper.login(username);
	}

	@Override
	public Integer update(SysUser user) {

		if (user.getUid() == null) {
			return -2;
		}
		return userMapper.update(user);
	}

	@Override
	public List<SysUser> simpleFound(SysUser user) {

		return userMapper.simpleFound(user);
	}

	@Override
	public List<SysUser> pageFound(SysUser user, LayuiPage page) {

		return userMapper.pageFound(user, page.getStart(), page.getEnd());
	}

	@Override
	public Integer pageCount(SysUser s) {

		return userMapper.pageCount(s);
	}

	@Override
	public Integer deleteUser(SysUser s) {
		if (s.getUid() == null) {
			return -2;
		}
		return userMapper.deleteUser(s);
	}

	@Override
	public Integer deletesUser(String[] uids) {

		if (uids == null) {
			return -2;
		}

		try {
			userMapper.deletesUser(uids);
			return 1;
		} catch (Exception e) {

			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public void out(String f) {

	}

}
