package com.my.home.system.config.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.my.home.app.po.AppUser;
import com.my.home.app.service.AppUserService;

public class AppUserRealm extends AuthorizingRealm {

	private static final Logger log = LoggerFactory.getLogger(AppUserRealm.class);

	@Autowired
	private AppUserService appUserService;
	@Autowired
	private EhCacheManager ehCacheManager;
	@Value("${number}")
	private Integer number;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		return null;
	}

	@SuppressWarnings("unused")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

		log.info("开始认证");
		AppUser appUser = new AppUser();
		String password = null;
		AppUser u = null;
		UserToken userToken = (UserToken) token;
		if (userToken.getUsername() != null && !userToken.getUsername().equals("")) {
			appUser.setUserName(userToken.getUsername());
			u = appUserService.appLogin(appUser);
			u.setLoginType(userToken.getLoginType());
			if (u != null && u.getIsdisable().equals("1")) {
				throw new ExcessiveAttemptsException("账号: " + u.getUserName() + "已被禁用，请联系管理员！");
			}
			if (u != null && (!u.getIsdisable().equals("0"))) {
				throw new ExcessiveAttemptsException("账号: " + u.getUserName() + "的账户被禁用，请联系管理员！");
			}

			if (u == null) {
				// 用户名不存在
				return null;// shiro 会自己判断
			} else {
				Cache<String, AtomicInteger> passwordRetryCache = ehCacheManager.getCache("appEacache");
				// retry count + 1 自增
				AtomicInteger retryCount = passwordRetryCache.get(u.getUserName() + "app");
				if (null == retryCount) {
					retryCount = new AtomicInteger(0);
					passwordRetryCache.put(u.getUserName() + "app", retryCount);
				}
				if (retryCount.incrementAndGet() > number) {
					log.warn("用户[{}]进行登录验证..失败验证超过{}次", u.getUserName(), number);
					throw new ExcessiveAttemptsException("账号: " + u.getUserName() + "尝试次数超出" + number + "次.，请一小时后再试！");
				}
				password = u.getPassword();
			}
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(u, password, getName());
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(u.getSalt()));
		return authenticationInfo;
	}

}
