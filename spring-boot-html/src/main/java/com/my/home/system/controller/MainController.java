package com.my.home.system.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.home.other.util.Global;
import com.my.home.system.po.BigMenu;
import com.my.home.system.po.SysUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;;

@Controller
@RequestMapping("/backsystem/back/")
@Api(value = "MainController", tags = "主页控制器")
public class MainController {

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private EhCacheManager ehCacheManager;


	@ApiOperation(value = "跳转", notes = "跳转主页")
	@RequestMapping(value = "toMain", method = RequestMethod.GET)
	public String toMain(Model m) {
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");

		// 1.获取subject
		Subject subject = SecurityUtils.getSubject();
		Object key = subject.getPrincipal();
		SysUser user = new SysUser();
		try {
			BeanUtils.copyProperties(user, key);
		} catch (Exception e) {
			log.info("错误");
		}
		@SuppressWarnings("unchecked")
		List<BigMenu> bigMenus = (List<BigMenu>) sysCache.get(Global.USERBIGMENU);
		String[] bigIds = user.getBigmenuid().split(",");
		Iterator<BigMenu> iterator = (Iterator<BigMenu>)bigMenus.iterator();
		while (iterator.hasNext()) {
			Object o= iterator.next();
			BigMenu bigMenu = new BigMenu();
			try {
				BeanUtils.copyProperties(bigMenu, o);
			} catch (Exception e) {
				log.info("错误");
			}
			
			if(!ArrayUtils.contains(bigIds, bigMenu.getId()+"")) {
				iterator.remove();
			}
		}
		m.addAttribute("bigMenus", bigMenus);
		m.addAttribute("user", user);
		return "systemsetup/main/main";
	}

	@ApiOperation(value = "获取顶部菜单", notes = "获取顶部菜单从缓存里")
	@RequestMapping(value = "getTopMenu", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getTopMenu() {
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
		@SuppressWarnings("unchecked")
		List<BigMenu> bigMenus = (List<BigMenu>) sysCache.get(Global.USERBIGMENU);
		JSONArray array = JSONArray.fromObject(bigMenus);
		return array;
	}

	@ApiOperation(value = "获取左侧菜单", notes = "从缓存中获取用户的左侧菜单")
	@RequestMapping(value = "foundLeftMenu", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getLeftMenu() {
		Subject subject = SecurityUtils.getSubject();
		Object key = subject.getPrincipal();
		SysUser u = new SysUser();
		try {
			BeanUtils.copyProperties(u, key);
		} catch (Exception e) {
			log.info("错误");
		}
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (Map<String, Object>) sysCache.get(u.getUsername());
		JSONObject array = JSONObject.fromObject(map);
		return array;
	}

	
	/**
	 * @return 测试百度
	 */
	@RequestMapping(value = "toBaiDu", method = RequestMethod.GET)
	@ApiOperation(value = "跳转修改页面", notes = "跳转修改页面")
	public String toBaiDu() {
		return "systemsetup/systemBusess/baidu";
	}

}
