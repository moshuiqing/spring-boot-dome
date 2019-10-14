package com.my.home.system.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.home.other.chat.po.ChatFriend;
import com.my.home.other.chat.po.ChatGoup;
import com.my.home.other.chat.po.ChatUser;
import com.my.home.other.chat.service.ChatFriendService;
import com.my.home.other.chat.service.ChatGroupService;
import com.my.home.other.util.Global;
import com.my.home.other.util.JsonMap;
import com.my.home.other.util.systemutil.DateUtils;
import com.my.home.other.util.systemutil.SimpleUtils;
import com.my.home.other.util.systemutil.SystemUtils;
import com.my.home.system.comment.redis.RedisComment;
import com.my.home.system.config.shiro.UserToken;
import com.my.home.system.po.SysUser;
import com.my.home.system.service.MenusService;
import com.my.home.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * @author ai996
 * @EnableAutoConfiguration 自动配置 扫
 */
@Controller
@RequestMapping("/backsystem/index/")
@Api(value = "LoginController", tags = "后台登录控制类")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	/**
	 * 开发登陆时加载菜单
	 */
	@Value("${lg}")
	private String lg;

	@Autowired
	private UserService userService;
	@Autowired
	private EhCacheManager ehCacheManager;
	@Autowired
	private RedisComment redisCommnet;

	@Autowired
	private MenusService menusService;

	@Autowired
	private ChatGroupService chatGroupService;
	@Autowired
	private ChatFriendService chatFriendService;

	/**
	 * @param bookUser
	 * @param session
	 * @return 1 普通登录
	 */
	@RequestMapping(value = "tologin", method = RequestMethod.GET)
	@ApiOperation(value = "跳转回登录页", notes = "跳转回登录页")
	public String toLogin(String tologin, Model m) {
		log.info("跳转回登录页");
		if (tologin != null && !tologin.equals("")) {
			m.addAttribute("warn", tologin);

		}

		return "systemsetup/index/login";
	}

	/**
	 * @param bookUser
	 * @return shiro 登录逻辑处理
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "登录逻辑处理", notes = "登录逻辑处理")
	public JsonMap login(SysUser u, HttpSession session, HttpServletRequest request) {

		///// 开发时使用，正式时不调用
		if (lg.equals("1")) {
			menusService.cacheMenuModule();
		}
		/////

		JsonMap jMap = new JsonMap();
		if (request.getAttribute("shiroLoginFailure") != null) {
			// 登录失败:用户名不存在
			jMap.setCode(-1);
			jMap.setMsg("验证码错误");
			return jMap;
		}

		Cache<String, AtomicInteger> passwordRetryCache = ehCacheManager.getCache("myEacache");
		/*
		 * info：使用shiro 编写认证操作
		 */
		// 1.获取subject
		Subject subject = SecurityUtils.getSubject();
		// 2.封装用户数据
		UserToken token = new UserToken(u.getUsername(), u.getPassword(), "BackUserRealm", u.isRememberMe());
		// 3.执行登录方法
		try {
			subject.login(token);
			SysUser user = (SysUser) subject.getPrincipal();
			session.setAttribute(Global.sysUser, user);
			session.setAttribute(Global.sysUserStr, JSONObject.fromObject(user));
			session.setAttribute(Global.loginType, Global.back);
			jMap.setCode(1);
			jMap.setMsg("登录成功！");
			log.info("登录成功");
			passwordRetryCache.remove(u.getUsername()+"back");
			// 记录登陆时间
			SysUser sysUser = new SysUser();
			sysUser.setUid(user.getUid());
			sysUser.setUserEndTime("1");// 表示不为空插入时间
			userService.update(sysUser);
			ChatUser chatUser = new ChatUser();
			chatUser.setId(user.getUid() + "");
			List<ChatGoup> chatGoups = chatGroupService.foundChatGroup(chatUser);
			String groups = "";
			for (ChatGoup c : chatGoups) {
				groups += c.getId() + ",";
			}
			session.setAttribute("groups", groups);// 存入用户所在的群
				
		} catch (UnknownAccountException e) {
			// 登录失败:用户名不存在
			jMap.setCode(-1);
			jMap.setMsg("用户名不存在");
		} catch (IncorrectCredentialsException e) {
			// 登录失败：密码错误
			jMap.setCode(-2);
			jMap.setMsg("密码错误" + passwordRetryCache.get(u.getUsername() + "back") + "次，超过5次后锁定！");
		}

		return jMap;

	}

	@RequestMapping(value = "loginOut", method = RequestMethod.GET)
	@ApiOperation(value = "退出系统", notes = "退出系统清空缓存")
	public String loginOut() {
		Subject subject = SecurityUtils.getSubject();
		Object key = subject.getPrincipal();
		SysUser user = new SysUser();
		try {
			BeanUtils.copyProperties(user, key);
		} catch (Exception e) {
			log.info("错误");
		}
		redisCommnet.del(user.getUsername());

		SysUser sysUser = new SysUser();
		sysUser.setUid(user.getUid());
		sysUser.setStatus("offline");// 表示离线状态
		userService.update(sysUser);
		subject.logout();
		return "redirect:/";
	}

	/**
	 * 设置值
	 * 
	 * @param flag
	 * @return
	 */
	@RequestMapping(value = "setFlag", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "设置刷新标识", notes = "设置刷新标识")
	public void setFlag(String flag) {

		Global.LOGINFLAG = flag;
	}

	@RequestMapping(value = "out", method = RequestMethod.POST)
	@ApiOperation(value = "退出", notes = "退出")
	@ResponseBody
	public void out(String flag) {
		System.out.println(1111);
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
	}

	/**
	 * @param bookUser
	 * @return 新增用户
	 */
	@SuppressWarnings("unlikely-arg-type")
	@RequestMapping(value = "addSystemUser", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "后台管理新增系统用户", notes = "md5+盐+迭代 生成密码")
	public JsonMap addSystemUser(SysUser user) {

		// 判断用户名是否重名
		if (user.getUsername() != null && !user.getUsername().equals("")) {
			SysUser s = new SysUser();
			s.setUsername(user.getUsername());
			Integer count = userService.simpleFound(s).size();
			if (count > 0) {
				return SimpleUtils.addOruPdate(-3, null, "用户名已存在！");
			}
		}

		Integer code = -1;
		String salt = DateUtils.backNum("");
		if (user.getPassword() != null) {
			String pwd = SystemUtils.MD5(Global.type, user.getPassword(), salt, Global.iterations);
			user.setPassword(pwd);
		}

		user.setSalt(salt);

		if (user.getUid() == null || user.getUid().equals("")) {
			// 新增
			code = userService.insert(user);
			ChatFriend chatFriend = new ChatFriend();
			chatFriend.setGroupname("我的好友");
			chatFriend.setGoupUserid(user.getUid() + "");
			chatFriendService.addChatFriend(chatFriend);// 新增用户时默认给一个我的好友的分组

		} else {
			// 修改
			code = userService.update(user);
		}

		return SimpleUtils.addOruPdate(code, null, null);
	}

	@RequestMapping(value = "tothis", method = RequestMethod.GET)
	@ApiOperation(value = "跳转后台首页", notes = "跳转后台首页")
	public String tothis() {
		// 进入后台登录页

		System.out.println("111");
		return "redirect:/";
	}

	@RequestMapping(value = "toother", method = RequestMethod.GET)
	@ApiOperation(value = "跳转提示页面", notes = "跳转提示页面")
	public String toother() {
		return "other/error/sqtishi";
	}

}
