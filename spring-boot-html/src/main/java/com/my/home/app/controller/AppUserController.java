package com.my.home.app.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jinge.one.entity.ImgUrl;
import com.jinge.one.entity.Shuffling;
import com.jinge.one.entity.Video;
import com.my.home.app.po.AppUser;
import com.my.home.backbusiness.po.News;
import com.my.home.backbusiness.service.NewsService;
import com.my.home.other.util.BaseResponse;
import com.my.home.other.util.Global;
import com.my.home.other.util.JsonMap;
import com.my.home.other.util.LayuiPage;
import com.my.home.other.util.systemutil.SimpleUtils;
import com.my.home.system.config.shiro.UserToken;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * @author ai996 手机用户控制层
 */
@Controller
@RequestMapping("/appdesk/appUser/")
@Api(value = "AppUserController", tags = "手机端用户")
public class AppUserController {

	@Autowired
	private EhCacheManager ehCacheManager;
	
	@Autowired
	private NewsService newsService;

	/**
	 * 手机端用户登录
	 * 
	 * @param appUser
	 * @return
	 */
	@RequestMapping(value = "appLogin")
	@ApiOperation(value = "手机端用户登录", notes = "手机端用户登录")
	@ResponseBody
	public JsonMap appLogin(AppUser appUser, HttpSession session) {
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
			UserToken token = new UserToken(appUser.getUserName(), appUser.getPassword(), "AppUserRealm", false);
			try {
				subject.login(token);
				AppUser user = (AppUser) subject.getPrincipal();
				session.setAttribute(Global.appUser, user);
				Serializable sessionId = subject.getSession().getId();
				user.setSessionId(sessionId);
				jm.setCode(1);
				jm.setMsg("登录成功！");
				jm.setObject(user);
				passwordRetryCache.clear();
			} catch (UnknownAccountException e) {
				// 登录失败:用户名不存在
				jm.setCode(-1);
				jm.setMsg("用户名不存在");
			} catch (IncorrectCredentialsException e) {
				// 登录失败：密码错误
				jm.setCode(-2);
				jm.setMsg("密码错误" + passwordRetryCache.get(appUser.getUserName() + "app") + "次，超过5次后锁定！");
			} catch (ExcessiveAttemptsException e) {
				jm.setCode(0);
				jm.setMsg(e.getMessage());
			}

		}
		return jm;

	}

	@RequestMapping(value = "foundAllInfo")
	@ResponseBody
	@ApiOperation(value = "获取全部信息", notes = "获取全部信息")
	public JsonMap foundAllInfo() {
		JsonMap jm = new JsonMap();
		jm.setMsg("获取成功");
		jm.setCode(1);

		return jm;
	}

	/**
	 * @return 返回提示
	 */
	@RequestMapping(value = "backPrompt", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "手机端返回提示", notes = "手机端返回提示")
	public JsonMap backPrompt() {
		JsonMap jm = new JsonMap();
		jm.setCode(-1);
		jm.setMsg("对不起！你还没有认证，或者认证已过期，请从新登录!");
		return jm;

	}

	@RequestMapping(value = "login")
	@ResponseBody
	public BaseResponse login(@RequestBody AppUser appUser, HttpSession session) {
		BaseResponse br = new BaseResponse();
		boolean falg = SimpleUtils.isEmpty(appUser.getUserName(), appUser.getPassword());
		if (falg) {
			br.setCode(-2);
			br.setMessage("用户名密码不能为空");
		} else {

			Cache<String, AtomicInteger> passwordRetryCache = ehCacheManager.getCache("appEacache");
			// 1.获取subject
			Subject subject = SecurityUtils.getSubject();
			// 2.封装用户数据
			UserToken token = new UserToken(appUser.getUserName(), appUser.getPassword(), "AppUserRealm", false);
			try {
				subject.login(token);
				AppUser user = (AppUser) subject.getPrincipal();
				session.setAttribute(Global.appUser, user);
				Serializable sessionId = subject.getSession().getId();
				user.setSessionId(sessionId);
				br.setCode(1);
				br.setMessage("登录成功！");
				br.setResult(user);
				passwordRetryCache.clear();
			} catch (UnknownAccountException e) {
				// 登录失败:用户名不存在
				br.setCode(-1);
				br.setMessage("用户名不存在");
			} catch (IncorrectCredentialsException e) {
				// 登录失败：密码错误
				br.setCode(-2);
				br.setMessage("密码错误" + passwordRetryCache.get(appUser.getUserName() + "app") + "次，超过5次后锁定！");
			} catch (ExcessiveAttemptsException e) {
				br.setCode(0);
				br.setMessage(e.getMessage());
			}

		}
		return br;
	}

	@RequestMapping("getUrls")
	@ResponseBody
	public BaseResponse getUrls() {
		BaseResponse br = new BaseResponse();
		List<Shuffling> shufflings = new ArrayList<>();
		Shuffling s1 = new Shuffling();
		s1.setId("1");
		s1.setTitle("我是1");
		s1.setUrl("http://img5.imgtn.bdimg.com/it/u=1457704519,3529830056&fm=26&gp=0.jpg");
		shufflings.add(s1);
		Shuffling s2 = new Shuffling();
		s2.setId("2");
		s2.setTitle("我是2");
		s2.setUrl("http://img0.imgtn.bdimg.com/it/u=123807196,3598291508&fm=26&gp=0.jpg");
		shufflings.add(s2);
		Shuffling s3 = new Shuffling();
		s3.setId("3");
		s3.setTitle("我是3");
		s3.setUrl("http://img0.imgtn.bdimg.com/it/u=3057006227,42122077&fm=26&gp=0.jpg");
		shufflings.add(s3);
		
		
		ImgUrl imgUrl = new ImgUrl();
		imgUrl.setShufflings(shufflings);
		br.setCode(1);
		br.setMessage("成功！");
		br.setResult(imgUrl);

		return br;

	}
	
	@RequestMapping("getNews")
	@ResponseBody
	public BaseResponse getNews( LayuiPage p,@RequestBody News n) {
		BaseResponse br = new BaseResponse();
		if(SimpleUtils.isEmpty(p.getLimit()+"",p.getPage()+"")) {
			br.setCode(-2);
			br.setMessage("缺少参数");
		}else {
			Integer count = newsService.pageCount(n);
			List<News> news = newsService.pageFound(n, p);
			JSONObject obj = new JSONObject();
			if(news.isEmpty()) {
				br.setCode(-3);
				br.setMessage("没有数据");
			}else {
				obj.put("count", count);
				obj.put("list", news);
				br.setCode(1);
				br.setMessage("获取成功！");
				br.setResult(obj);
			}
			
		}				
		return br;		
	}
	
	@ResponseBody
	@RequestMapping("getVideos")
	public BaseResponse getVideos(LayuiPage p,@RequestBody Video v) {
		BaseResponse br = new BaseResponse();
		if(SimpleUtils.isEmpty(p.getLimit()+"",p.getPage()+"")) {
			br.setCode(-2);
			br.setMessage("缺少分页参数");
		}else {
			List<Video> videos = new ArrayList<>();
			
			for(int i=0;i<p.getLimit();i++) {
				Video vd= new Video();
				vd.setId(i+"");
				vd.setAnthor("作者"+i);
				vd.setTitle("我是标题"+i);
				vd.setUrl("domeSource/vedio/one.flv");
				vd.setCreateTime("2018-10-20");
				vd.setContent("我是详情"+i);
				videos.add(vd);
			}
			JSONObject obj = new JSONObject();
			if(!videos.isEmpty()) {
				
				obj.put("count", videos.size());
				obj.put("videos", videos);
				br.setCode(1);
				br.setMessage("获取成功！");
				br.setResult(obj);
			}
			
			
		}
		
		
		
		return br;
		
		
	}

}
