package com.my.home.system.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.home.other.util.Global;
import com.my.home.other.util.JsonMap;
import com.my.home.other.util.LayuiPage;
import com.my.home.other.util.po.LayuiTree;
import com.my.home.other.util.po.LayuiXtree;
import com.my.home.other.util.po.Menu;
import com.my.home.other.util.po.OnlineUser;
import com.my.home.other.util.systemutil.DateUtils;
import com.my.home.other.util.systemutil.SimpleUtils;
import com.my.home.other.util.systemutil.SystemUtils;
import com.my.home.system.po.BigMenu;
import com.my.home.system.po.Menus;
import com.my.home.system.po.Module;
import com.my.home.system.po.Role;
import com.my.home.system.po.SysUser;
import com.my.home.system.po.Syslog;
import com.my.home.system.service.MenusService;
import com.my.home.system.service.RoleService;
import com.my.home.system.service.SysLogService;
import com.my.home.system.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author ai996 系统控制
 */
@RequestMapping("/backsystem/system/")
@Controller
@Api(value = "SystemController", tags = "系统相关设置")
public class SystemController {

	/**
	 * service 注入
	 */
	@Autowired
	private SysLogService sysLogService;

	@Autowired
	private UserService userService;

	@Autowired
	private EhCacheManager ehCacheManager;

	@Autowired
	private MenusService menusService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SessionDAO sessionDAO;

	/**
	 * 开发登陆时加载菜单
	 */
	@Value("${lg}")
	private String lg;

	/**
	 * @return 进入系统设置
	 */
	@RequestMapping(value = "toSystemParameter", method = RequestMethod.GET)
	@ApiOperation(value = "跳转系统设置", notes = "跳转系统设置")
	public String toSystemParameter() {
		return "systemsetup/system/basicParameter";
	}

	/**
	 * @return
	 */
	@RequestMapping(value = "toLogs", method = RequestMethod.GET)
	@ApiOperation(value = "跳转系统日志", notes = "进入查看系统日志")
	public String toLogs() {
		return "systemsetup/system/logs";
	}

	@RequestMapping(value = "toSysUser", method = RequestMethod.GET)
	@ApiOperation(value = "跳转个人资料", notes = "进入系统个人资料页面")
	public String toSysUser(HttpSession session) {

		return "systemsetup/userinfo/userInfo";

	}

	@RequestMapping(value = "toMenus", method = RequestMethod.GET)
	@ApiOperation(value = "跳转菜单页面", notes = "进入菜单管理页面")
	public String toMenus() {

		return "systemsetup/system/menu";

	}

	/**
	 * @return
	 */
	@RequestMapping(value = "toChangePwd", method = RequestMethod.GET)
	@ApiOperation(value = "跳转修改密码页", notes = "跳转修改密码页")
	public String toChangePwd() {
		return "systemsetup/system/changePwd";
	}

	/**
	 * 分页查询
	 * 
	 * @param page   分页参数
	 * @param syslog 实体
	 * @return
	 */
	@RequestMapping(value = "foundLogs", method = RequestMethod.GET)
	@ApiOperation(value = "分页查询系统日志", notes = "分页查询系统日志")
	@ResponseBody
	public String foundLogs(LayuiPage page, Syslog syslog) {
		JSONObject obj = new JSONObject();
		List<Syslog> syslogs = sysLogService.pageFound(syslog, page);
		Integer count = sysLogService.pageCount(syslog);
		obj.put("code", 0);
		obj.put("msg", "");
		obj.put("count", count);
		obj.put("data", syslogs);
		return obj.toString();
	}

	/**
	 * 自己修改系统用户信息
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "insertSysUser", method = RequestMethod.POST)
	@ApiOperation(value = "新增自己的用户信息", notes = "新增自己的用户信息")
	@ResponseBody
	public JsonMap insertSysUser(SysUser user, HttpSession session) {

		Integer result = userService.update(user);
		if (result > 0) {
			SysUser su = new SysUser();
			su.setUid(user.getUid());
			List<SysUser> sysUsers = userService.simpleFound(su);
			if (!sysUsers.isEmpty()) {
				user = sysUsers.get(0);
				session.setAttribute(Global.sysUser, user);
				session.setAttribute(Global.sysUserStr, JSONObject.fromObject(user));
			}
		}

		return SimpleUtils.addOruPdate(result, user.getHeadImg(), null);

	}

	/**********************************************
	 * 菜单管理
	 *****************************************************/
	/**
	 * 获取全部菜单
	 * 
	 * @return
	 */
	@RequestMapping(value = "getTrees", method = RequestMethod.POST)
	@ApiOperation(value = "获取菜单", notes = "获取全部菜单")
	@ResponseBody
	public JSONArray getTrees() {
		///// 开发时使用，正式时不调用
		if (lg.equals("1")) {
			menusService.cacheMenuModule();
		}

		/////

		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
		@SuppressWarnings("unchecked")
		List<LayuiTree> xtrees = (List<LayuiTree>) sysCache.get(Global.ALLMENUS);
		if (xtrees == null) {

			List<LayuiTree> LayuiTrees = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<BigMenu> bigMenus = (List<BigMenu>) sysCache.get(Global.BIGMENU);
			for (BigMenu bmu : bigMenus) {
				List<LayuiTree> data = new ArrayList<>();
				LayuiTree layuiTree = new LayuiTree();
				layuiTree.setName(bmu.getTitle());
				layuiTree.setMenu1(bmu.getMenu1());
				layuiTree.setIcon(bmu.getIcon());
				layuiTree.setSort(bmu.getSort());
				layuiTree.setJibie("1");
				layuiTree.setSpread(true);
				layuiTree.setId(bmu.getId());
				Menus ms = new Menus();
				ms.setMenu1(bmu.getMenu1());
				List<Menus> menuss = menusService.simleFound(ms);
				for (Menus m : menuss) {
					List<LayuiTree> list1 = new ArrayList<>();
					LayuiTree xtree = new LayuiTree();
					xtree.setName(m.getTitle());
					xtree.setId(m.getId());
					xtree.setIcon(m.getIcon());
					xtree.setSort(m.getSort());
					xtree.setMenu1(m.getMenu1());
					xtree.setSpread(true);
					xtree.setHreftext(m.getHref());
					xtree.setJibie("2");
					Menus men = new Menus();
					men.setParent(m.getId() + "");
					List<Menus> list = menusService.simleFound(men);
					for (Menus mn : list) {
						LayuiTree le = new LayuiTree();
						le.setName(mn.getTitle());
						le.setId(mn.getId());
						le.setIcon(mn.getIcon());
						le.setChildren(null);
						le.setHreftext(mn.getHref());
						le.setJibie("3");
						list1.add(le);
					}
					xtree.setChildren(list1);
					data.add(xtree);
				}

				layuiTree.setChildren(data);
				LayuiTrees.add(layuiTree);

			}
			xtrees = LayuiTrees;
			sysCache.put(Global.ALLMENUS, LayuiTrees);

		}
		System.out.println(JSONArray.fromObject(xtrees));
		return JSONArray.fromObject(xtrees);

	}

	@RequestMapping(value = "addBigMenu", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "新增大菜单一级菜单", notes = "新增一级菜单")
	public JsonMap addBigMenu(BigMenu m) {
		Integer result = null;
		// 判断标题不能重复 和 名称不能重复
		BigMenu b = new BigMenu();
		b.setMenu1(m.getMenu1());
		Integer count = menusService.pageCount(b);
		if (count > 0) {
			return SimpleUtils.addOruPdate(-3, null, "名称重复");
		}
		b.setMenu1(null);
		b.setTitle(m.getTitle());
		count = menusService.pageCount(b);
		if (count > 0) {
			return SimpleUtils.addOruPdate(-3, null, "标题重复");
		}

		// 1.获取subject
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getPrincipal();
		result = menusService.insertMenu(m, user.getRoleid());
		if (result > 0) {
			Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
			sysCache.put(Global.ALLMENUS, null);
		}
		return SimpleUtils.addOruPdate(result, null, null);
	}

	/**
	 * 删除
	 * 
	 * @param jibie
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "deleteMenu", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除菜单", notes = "删除菜单，根据级别的不同删除")
	public JsonMap deleteMenu(String jibie, Integer id) {
		Integer result = null;
		if (jibie.equals("1")) {
			// 删除大菜单
			BigMenu bigMenu = new BigMenu();
			bigMenu.setId(id);
			result = menusService.deleteMenu(bigMenu);

		} else {
			// 删除二级三级菜单
			Menu menu = new Menu();
			menu.setId(id);
			menu.setParent(id + "");
			result = menusService.deleteMenus(menu);

		}
		if (result > 0) {
			Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
			sysCache.put(Global.ALLMENUS, null);
		}

		return SimpleUtils.addOruPdate(result, null, null);

	}

	/**
	 * 新增二级菜单
	 * 
	 * @param menus
	 * @return
	 */
	@RequestMapping(value = "addMenu", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "新增二级菜单", notes = "新增二级菜单")
	public JsonMap addMenu(Menu menu) {
		Integer result = null;
		Menu m = new Menu();
		m.setTitle(menu.getTitle());
		result = menusService.countNum(m);
		if (result > 0) {
			return SimpleUtils.addOruPdate(-3, null, "标题重复");
		}

		// 1.获取subject
		Subject subject = SecurityUtils.getSubject();
		SysUser user = (SysUser) subject.getPrincipal();
		result = menusService.insertMenus(menu, user.getRoleid());
		if (result > 0) {
			Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
			sysCache.put(Global.ALLMENUS, null);
		}

		return SimpleUtils.addOruPdate(result, null, null);
	}

	/**
	 * 修改一级菜单
	 * 
	 * @param bigMenu
	 * @return
	 */
	@RequestMapping(value = "updateBigMenu", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "修改一级菜单", notes = "修改一级菜单")
	public JsonMap updateBigMenu(BigMenu bigMenu) {

		Integer result = menusService.updateMenu(bigMenu);
		if (result > 0) {
			Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
			sysCache.put(Global.ALLMENUS, null);
		}

		return SimpleUtils.addOruPdate(result, bigMenu, null);

	}

	/**
	 * 修改二级菜单
	 * 
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "updateMenu", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "修改二级菜单", notes = "修改二级菜单")
	public JsonMap updateMenu(Menu menu) {

		Integer result = menusService.updateMenus(menu);
		if (result > 0) {
			Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
			sysCache.put(Global.ALLMENUS, null);
		}
		return SimpleUtils.addOruPdate(result, menu, null);
	}

	/******************************************
	 * 系统用户管理
	 **************************************************/

	/**
	 * 进入系统用户管理页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "toUserPage", method = RequestMethod.GET)
	@ApiOperation(value = "跳转系统用户管理", notes = "进入系统用户管理页面")
	public String toUserPage(Model m) {

		List<Role> roles = roleService.FoundRole(new Role());
		m.addAttribute("roles", roles);
		return "systemsetup/userinfo/userList";
	}

	/**
	 * 分页查询系统用户
	 * 
	 * @param user
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "getSysUsers", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "分页查询系统用户", notes = "分页查询系统用户")
	public String getSysUsers(SysUser user, LayuiPage page) {

		JSONObject obj = new JSONObject();
		List<SysUser> sysUsers = userService.pageFound(user, page);
		Integer count = userService.pageCount(user);
		obj.put("code", 0);
		obj.put("msg", "");
		obj.put("count", count);
		obj.put("data", sysUsers);
		return obj.toString();
	}

	/**
	 * 启用 禁用
	 * 
	 * @param sysUser
	 * @return
	 */
	@RequestMapping(value = "change", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "启用或者禁用系统用户账号", notes = "启用或者禁用系统用户账号")
	public JsonMap change(SysUser sysUser) {
		Integer code = userService.update(sysUser);
		return SimpleUtils.addOruPdate(code, null, null);
	}

	/**
	 * 删除用户
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "删除系统用户", notes = "删除系统用户")
	public JsonMap delete(SysUser user) {
		Integer code = userService.deleteUser(user);
		return SimpleUtils.addOruPdate(code, null, null);
	}

	/**
	 * 批量删除
	 * 
	 * @param uids
	 * @return
	 */
	@RequestMapping(value = "deletes", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "批量删除系统用户", notes = "批量删除系统用户")
	public JsonMap deletes(String uids) {
		Integer code = -2;
		if (uids != null && !uids.equals("")) {
			String[] ids = uids.split(",");
			code = userService.deletesUser(ids);
		}
		return SimpleUtils.addOruPdate(code, null, null);

	}

	/**
	 * @return
	 */
	@ApiOperation(value = "修改自己的登录密码", notes = "修改自己的登录密码")
	@RequestMapping(value = "upMyPwd", method = RequestMethod.POST)
	@ResponseBody
	public JsonMap upMyPwd(SysUser user, @ApiParam("旧的密码") String oldpwd) {
		Integer code=null;
		String msg = null;
		SysUser sysUser = userService.login(user.getUsername());
		if (sysUser != null) {
			String pwd = SystemUtils.MD5(Global.type, oldpwd, sysUser.getSalt(), Global.iterations);
			if (pwd.equals(sysUser.getPassword())) {
				String salt = DateUtils.backNum("");
				String newpwd = SystemUtils.MD5(Global.type, user.getPassword(), salt, Global.iterations);
				SysUser su = new SysUser();
				su.setUid(sysUser.getUid());
				su.setPassword(newpwd);
				su.setSalt(salt);
				code= userService.update(su);
			}else {
				code=-3;
				msg = "原密码错误";
			}

		} else {
			code=-3;
			msg = "没有该用户";
		}

		return SimpleUtils.addOruPdate(code, null, msg);
	}

	/*************************************
	 * 系统角色管理
	 ***************************************/
	/**
	 * 跳转系统角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "toRole", method = RequestMethod.GET)
	@ApiOperation(value = "跳转系统角色", notes = "跳转系统角色")
	public String toRole() {
		return "systemsetup/system/role";
	}

	/**
	 * 分页查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "pageFoundRole", method = RequestMethod.GET)
	@ApiOperation(value = "跳转系统角色", notes = "跳转系统角色")
	public String pageFoundRole(Role role, LayuiPage page) {
		JSONObject obj = new JSONObject();
		List<Role> roles = roleService.pageFoundRole(role, page);
		Integer count = roleService.pageCountRole(role);
		obj.put("code", 0);
		obj.put("msg", "");
		obj.put("count", count);
		obj.put("data", roles);
		return obj.toString();
	}

	/**
	 * @param info
	 * @return 查询权限
	 */
	@ResponseBody
	@RequestMapping(value = "getModel", method = RequestMethod.POST)
	@ApiOperation(value = "查询权限", notes = "查询权限")
	public JSONArray getModel(String info) {
		if (info == null) {
			return null;
		}
		String[] ids = info.split(",");
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
		@SuppressWarnings("unchecked")
		List<Module> modules = (List<Module>) sysCache.get(Global.MODULESCACHKEY);
		List<LayuiTree> layuiTrees = new ArrayList<>();
		for (Module m : modules) {
			if (ArrayUtils.contains(ids, m.getMid() + "")) {
				LayuiTree lt = new LayuiTree();
				lt.setId(m.getMid());
				lt.setName(m.getMname());
				layuiTrees.add(lt);
			}
		}
		return JSONArray.fromObject(layuiTrees);
	}

	/**
	 * @param info
	 * @return 获取竖菜单
	 */
	@RequestMapping(value = "getMenus", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取竖菜单", notes = "获取竖菜单")
	public JSONArray getMenus(String info, String binfo) {
		if (binfo == null) {
			return null;
		}
		if (info == null) {
			return null;
		}

		String[] ids = info.split(",");
		String[] bids = binfo.split(",");
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
		@SuppressWarnings("unchecked")
		List<LayuiTree> xtrees = (List<LayuiTree>) sysCache.get(Global.ALLMENUS);
		List<LayuiTree> layuiTrees = new ArrayList<>();
		for (LayuiTree lt : xtrees) {
			if (ArrayUtils.contains(bids, lt.getId() + "")) {

				List<LayuiTree> layuiTrees2 = lt.getChildren();
				List<LayuiTree> ltres2 = new ArrayList<>();
				for (LayuiTree lt2 : layuiTrees2) {
					if (ArrayUtils.contains(ids, lt2.getId() + "")) {
						List<LayuiTree> layuiTrees3 = lt2.getChildren();
						List<LayuiTree> ltres3 = new ArrayList<>();
						for (LayuiTree lt3 : layuiTrees3) {
							if (ArrayUtils.contains(ids, lt2.getId() + "")) {
								ltres3.add(lt3);
							}
						}
						lt2.setChildren(layuiTrees3);
						ltres2.add(lt2);
					}
				}
				lt.setChildren(ltres2);
				layuiTrees.add(lt);
			}

		}
		return JSONArray.fromObject(layuiTrees);
	}

	/**
	 * @param r
	 * @return 查询编辑
	 */
	@ResponseBody
	@RequestMapping(value = "updateFoundRole", method = RequestMethod.GET)
	@ApiOperation(value = "查询编辑", notes = " 查询编辑")
	public String updateFoundRole(Role r) {
		if (r.getModelid() == null) {
			return null;
		}
		String[] ids = r.getModelid().split(",");
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
		@SuppressWarnings("unchecked")
		List<Module> modules = (List<Module>) sysCache.get(Global.MODULESCACHKEY);
		List<LayuiXtree> layuiXtrees = new ArrayList<>();
		for (Module md : modules) {
			LayuiXtree lx = new LayuiXtree();
			if (md.getMname().equals("查找")) {
				lx.setDisabled(true);
			} else {
				lx.setDisabled(false);
			}
			lx.setTitle(md.getMname());
			lx.setValue(md.getMid() + "");
			if (ArrayUtils.contains(ids, md.getMid() + "")) {
				lx.setChecked(true);
			}
			layuiXtrees.add(lx);
		}
		return JSONArray.fromObject(layuiXtrees).toString();
	}

	/**
	 * @param r
	 * @return 修改角色
	 */
	@ResponseBody
	@RequestMapping(value = "updateRole", method = RequestMethod.POST)
	@ApiOperation(value = "修改角色", notes = "修改角色")
	public JsonMap updateRole(Role r) {
		Integer code = roleService.updateRole(r);
		return SimpleUtils.addOruPdate(code, null, null);
	}

	/**
	 * @param r
	 * @return 查询竖菜单编辑
	 */
	@RequestMapping(value = "updateFoundMenu", method = RequestMethod.GET)
	@ApiOperation(value = "查询竖菜单编辑", notes = "查询竖菜单编辑")
	@ResponseBody
	public String updateFoundMenu(Role r) {
		List<LayuiXtree> layuiXtrees = new ArrayList<>();
		if (r.getBigmenuid() == null || r.getBigmenuid().equals("")) {
			//return JSONArray.fromObject(layuiXtrees).toString();
		}
		String[] ids = null;
		if (r.getMenuid() != null && !r.getMenuid().equals("")) {
			ids = r.getMenuid().split(",");
		}
		String[] bids = null;
		if (r.getBigmenuid() != null && !r.getBigmenuid().equals("")) {
			bids = r.getBigmenuid().split(",");
		}
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");
		@SuppressWarnings("unchecked")
		List<LayuiTree> xtrees = (List<LayuiTree>) sysCache.get(Global.ALLMENUS);

		for (LayuiTree lt : xtrees) {
			LayuiXtree x1 = new LayuiXtree();
			x1.setValue(lt.getId() + "");
			x1.setTitle(lt.getName());
			x1.setJibie(lt.getJibie());
			if (ArrayUtils.contains(bids, lt.getId() + "")) {
				x1.setChecked(true);
			}
			if (lt.getChildren() != null) {
				List<LayuiTree> trees2 = lt.getChildren();
				List<LayuiXtree> xtrees2 = new ArrayList<>();
				for (LayuiTree lt2 : trees2) {
					LayuiXtree x2 = new LayuiXtree();
					x2.setValue(lt2.getId() + "");
					x2.setDisabled(false);
					x2.setJibie(lt2.getJibie());
					x2.setTitle(lt2.getName());
					if (ArrayUtils.contains(ids, lt2.getId() + "")) {
						x2.setChecked(true);
					}
					if (lt2.getChildren() != null) {
						List<LayuiTree> trees3 = lt2.getChildren();
						List<LayuiXtree> xtrees3 = new ArrayList<>();
						for (LayuiTree lt3 : trees3) {
							LayuiXtree x3 = new LayuiXtree();
							x3.setValue(lt3.getId() + "");
							x3.setJibie(lt3.getJibie());
							x3.setTitle(lt3.getName());
							if (ArrayUtils.contains(ids, lt3.getId() + "")) {
								x3.setChecked(true);
							}
							x3.setDisabled(false);
							xtrees3.add(x3);
						}
						x2.setData(xtrees3);
					}
					xtrees2.add(x2);
				}
				x1.setData(xtrees2);
			}
			layuiXtrees.add(x1);
		}
		return JSONArray.fromObject(layuiXtrees).toString();
	}

	/**
	 * 启用或禁用
	 * 
	 * @param r
	 * @return
	 */
	@RequestMapping(value = "updateDisable", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "启用或禁用", notes = "启用或禁用")
	public JsonMap updateDisable(Role r) {
		if (r.getRid() == null) {
			return SimpleUtils.addOruPdate(-2, null, null);
		}
		Integer code = roleService.updateRole(r);
		return SimpleUtils.addOruPdate(code, null, null);
	}

	/**
	 * @param r
	 * @return 删除
	 */
	@ResponseBody
	@RequestMapping(value = "deleteRole", method = RequestMethod.POST)
	@ApiOperation(value = "删除角色", notes = "删除角色")
	public JsonMap deleteRole(Role r) {
		if (r.getRid() == null) {
			return SimpleUtils.addOruPdate(-2, null, null);
		}
		Integer code = roleService.deleteRole(r);
		return SimpleUtils.addOruPdate(code, null, null);
	}

	/**
	 * @param r
	 * @return 修改角色信息
	 */
	@RequestMapping(value = "updateRoleById", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "修改角色信息", notes = "修改角色信息")
	public JsonMap updateRoleById(Role r) {
		Integer code = 0;

		if ((r.getRname() == null || r.getRname().equals("")) && (r.getRemake() == null || r.getRemake().equals(""))) {
			return SimpleUtils.addOruPdate(-3, null, "修改成功");
		}
		if (r.getRname() != null && !r.getRname().equals("")) {
			Role role = new Role();
			role.setRname(r.getRname());
			List<Role> roles = roleService.FoundRole(role);
			if (!roles.isEmpty()) {
				return SimpleUtils.addOruPdate(-3, null, "用户名已存在");
			}
		}
		if (r.getRid() != null && !r.getRid().equals("")) {
			code = roleService.updateRole(r);
		} else {
			r.setModelid("4");
			code = roleService.insertRole(r);
		}

		return SimpleUtils.addOruPdate(code, null, null);
	}

	//////////////////////////////////////////////////////////// 在线用户管理////////////////////////////////////////////////////////////////

	/**
	 * @return 跳转在线用户管理
	 */
	@RequestMapping(value = "toOnlinUser", method = RequestMethod.GET)
	@ApiOperation(value = "跳转在线用户管理", notes = "跳转在线用户管理")
	public String toOnlinUser() {

		return "systemsetup/system/onlineUser";
	}

	/**
	 * @return 获取在线后台用户
	 */
	@ResponseBody
	@RequestMapping(value = "getOnlineUser", method = RequestMethod.GET)
	@ApiOperation(value = "获取在线后台用户", notes = "获取在线后台用户")
	public String getOnlineUser() {
		Collection<Session> sessions = sessionDAO.getActiveSessions();
		List<OnlineUser> onlineUsers = new ArrayList<OnlineUser>();
		String[] ips = new String[sessions.size()];
		String[] names = new String[sessions.size()];
		int i = 0;
		for (Iterator iterator = sessions.iterator(); iterator.hasNext();) {
			try {
				Session session = (Session) iterator.next();
				OnlineUser onlineUser = new OnlineUser();
				Object key = session.getAttribute(Global.sysUser);
				SysUser user = new SysUser();
				try {
					BeanUtils.copyProperties(user, key);
				} catch (Exception e) {
					// e.printStackTrace();
					// logger.info("错误");
				}
				if (/* (!ArrayUtils.contains(ips, session.getHost())) && */user.getUsername() != null) {

					if (session.getAttribute(Global.BLOOENKEY) == null) {
						onlineUser.setSessionId(session.getId().toString());
						onlineUser.setUserIp(session.getHost());
						onlineUser.setUserName(user.getUsername());
						Date date = session.getLastAccessTime();
						onlineUser.setDate(DateUtils.getStrDateTime(date));
						onlineUser.setUserId(user.getUid() + "");
						onlineUsers.add(onlineUser);
						ips[i] = session.getHost();
						names[i] = user.getUsername();
						i++;
					}
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}

		}

		JSONObject obj = new JSONObject();
		obj.put("code", 0);
		obj.put("msg", "");
		obj.put("count", 0);
		obj.put("data", onlineUsers);

		return obj.toString();
	}

	/**
	 * @param sessionId
	 * @return 踢掉用户
	 */
	@RequestMapping(value = "deleteoutUser", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = " 踢掉用户", notes = " 踢掉用户")
	public JsonMap deleteoutUser(String sessionId) {

		try {
			Session session = sessionDAO.readSession(sessionId);
			if (session != null) {
				session.setAttribute(Global.BLOOENKEY, Boolean.TRUE);
			}
		} catch (Exception e) {
			return SimpleUtils.addOruPdate(-1, null, null);
		}

		return SimpleUtils.addOruPdate(1, null, null);
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 是否单点登录
	 * 
	 * @param isSingle
	 * @return
	 */
	@RequestMapping(value = "isSingle", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "是否单点登录", notes = "是否单点登录")
	public JsonMap isSingle(@ApiParam("是否单点登录参数") String isSingle, @ApiParam("用户id") Integer uid, HttpSession session) {
		boolean flag = SimpleUtils.isEmpty(isSingle, uid.toString());
		if (flag) {
			return SimpleUtils.addOruPdate(-2, null, null);
		}
		SysUser user = new SysUser();
		user.setIsSingle(isSingle);
		user.setUid(uid);
		Integer code = userService.update(user);
		if (code > 0) {
			user = (SysUser) session.getAttribute(Global.sysUser);
			user.setIsSingle(isSingle);
			session.setAttribute(Global.sysUser, user);
		}
		return SimpleUtils.addOruPdate(code, null, null);

	}

}
