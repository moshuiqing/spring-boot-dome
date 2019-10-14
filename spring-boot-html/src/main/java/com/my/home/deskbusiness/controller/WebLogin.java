package com.my.home.deskbusiness.controller;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.home.app.po.AppUser;
import com.my.home.other.util.Global;
import com.my.home.other.util.JsonMap;
import com.my.home.other.util.systemutil.SimpleUtils;
import com.my.home.system.config.shiro.UserToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ai996 跳转前台页面
 */
@RequestMapping("/webdesk/login/")
@Api(value = "WebLogin", tags = "前台登录控制页")
@Controller
public class WebLogin {
	

	@Autowired
	private EhCacheManager ehCacheManager;

	/**
	 * @return
	 */
	@RequestMapping(value="toWebLogin",method=RequestMethod.GET)
	@ApiOperation(value="进入前台登录页",notes="进入前台登录页")
	public String toWebLogin() {

		return "deskbusiness/login";
	}
	
	/**
	 * 手机端用户登录
	 * 
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "webLogin",method=RequestMethod.POST)
	@ApiOperation(value = "前台用户登录", notes = "前台用户登录")
	@ResponseBody
	public JsonMap appLogin(AppUser appUser,HttpSession session) {
		JsonMap jm = new JsonMap();
		boolean falg = SimpleUtils.isEmpty(appUser.getUserName(), appUser.getPassword());
		if (falg) {
			jm.setCode(-2);
			jm.setMsg("用户名密码不能为空");
		} else {

			Cache<String, AtomicInteger> passwordRetryCache = ehCacheManager.getCache("appEacache");
			// 1.获取subject
			Subject subject = SecurityUtils.getSubject();
			// 2.封装用户数据
			UserToken token = new UserToken(appUser.getUserName(), appUser.getPassword(), "AppUserRealm",appUser.isRememberMe());
			try {
				subject.login(token);
				AppUser user = (AppUser) subject.getPrincipal();
				Serializable sessionId = subject.getSession().getId();
				user.setSessionId(sessionId);
				jm.setCode(1);
				jm.setMsg("登录成功！");
				jm.setObject(user);
				session.setAttribute(Global.webUser, user);
				session.setAttribute(Global.loginType, Global.web);
				passwordRetryCache.clear();
			} catch (UnknownAccountException e) {
				// 登录失败:用户名不存在
				jm.setCode(-1);
				jm.setMsg("用户名不存在");
			} catch (IncorrectCredentialsException e) {
				// 登录失败：密码错误
				jm.setCode(-2);
				jm.setMsg("密码错误" + passwordRetryCache.get(appUser.getUserName()+"app") + "次，超过5次后锁定！");
			}catch(ExcessiveAttemptsException e) {
				jm.setCode(0);
				jm.setMsg(e.getMessage());
			}
		}
		return jm;

	}
	
	/**
	 * @return
	 */
	@RequestMapping(value="toWebMain",method=RequestMethod.GET)
	public String toWebMain() {
		
		System.out.println("1111");
		return "deskbusiness/main";
	}


}
