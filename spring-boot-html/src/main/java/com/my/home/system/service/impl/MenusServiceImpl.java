package com.my.home.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.home.other.util.Global;
import com.my.home.other.util.po.LayuiTree;
import com.my.home.other.util.po.Menu;
import com.my.home.other.util.systemutil.SimpleUtils;
import com.my.home.system.mapper.BigMenuMapper;
import com.my.home.system.mapper.MenusMapper;
import com.my.home.system.mapper.ModuleMapper;
import com.my.home.system.mapper.RoleMapper;
import com.my.home.system.po.BigMenu;
import com.my.home.system.po.Menus;
import com.my.home.system.po.Module;
import com.my.home.system.po.Role;
import com.my.home.system.service.MenusService;

@Service
public class MenusServiceImpl implements MenusService {

	@Autowired
	private ModuleMapper meduleMapper;

	@Autowired
	private EhCacheManager ehCacheManager;

	@Autowired
	private MenusMapper menusMapper;

	@Autowired
	private BigMenuMapper bigMenuMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Menus> simleFound(Menus menus) {

		return menusMapper.simleFound(menus);
	}

	@Override
	public void cacheMenuModule() {
		List<Module> modules = meduleMapper.simpleFoundModule(new Module());
		// 存入缓存
		Cache<String, Object> sysCache = ehCacheManager.getCache("menuEacache");

		sysCache.put(Global.MODULESCACHKEY, modules);
		List<BigMenu> bgm = bigMenuMapper.simpleFound(new BigMenu());
		sysCache.put(Global.BIGMENU, bgm);
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
			List<Menus> menuss = menusMapper.simleFound(ms);
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
				List<Menus> list = menusMapper.simleFound(men);
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
		sysCache.put(Global.ALLMENUS, LayuiTrees);

	}

	@Override
	@Transactional
	public Integer insertMenu(BigMenu bigMenu, String roleId) {

		boolean flag = SimpleUtils.isEmpty(bigMenu.getIcon(), bigMenu.getMenu1(), bigMenu.getTitle());
		if (flag) {
			return -2;
		}
		Integer result = bigMenuMapper.insertMenu(bigMenu);
		// 将新增的id放入用户里面
		Role role = new Role();
		role.setRid(roleId);
		Role role2 = roleMapper.simpleFoundRole(role);
		role.setBigmenuid(role2.getBigmenuid() + "," + bigMenu.getId());
		result = roleMapper.updateRole(role);
		return result;
	}

	@Override
	public Integer updateMenu(BigMenu bigMenu) {
		if (bigMenu.getId() == null) {
			return -1;
		}

		return bigMenuMapper.updateMenu(bigMenu);
	}

	@Override
	@Transactional
	public Integer deleteMenu(BigMenu bigMenu) {
		if (bigMenu.getId() == null) {
			return -1;
		}
		// 查询一级菜单
		List<BigMenu> bigMenus = bigMenuMapper.simpleFound(bigMenu);
		if (bigMenus.isEmpty()) {
			return -1;
		}
		String menu1 = bigMenus.get(0).getMenu1();// 根据名称删除全部二级菜单
		Menu menu = new Menu();
		menu.setMenu1(menu1);
		Integer result = menusMapper.deleteMenus(menu);
		if (result < 0) {
			return -1;
		}

		return bigMenuMapper.deleteMenu(bigMenu);
	}

	@Override
	public Integer deleteMenus(Menu menus) {
		if (menus.getId() == null) {
			return -1;
		}

		return menusMapper.deleteMenus(menus);
	}

	@Override
	@Transactional
	public Integer insertMenus(Menu menus, String roleId) {
		boolean flag = SimpleUtils.isEmpty(menus.getIcon(), menus.getMenu1(), menus.getTitle());
		if (flag) {
			return -2;
		}

		Integer result = menusMapper.insertMenus(menus);
		if (result < 0) {
			return -1;
		}
		// 将新增的id放入角色里面
		Role role = new Role();
		role.setRid(roleId);
		Role role2 = roleMapper.simpleFoundRole(role);
		role.setMenuid(role2.getMenuid() + "," + menus.getId());
		result = roleMapper.updateRole(role);

		return result;
	}

	@Override
	public Integer updateMenus(Menu menus) {
		if (menus.getId() == null) {
			return -1;
		}
		return menusMapper.updateMenus(menus);
	}

	@Override
	public Integer pageCount(BigMenu bigMenu) {

		if (bigMenu.getMenu1() == null && bigMenu.getTitle() == null) {
			return -2;
		}

		return bigMenuMapper.pageCount(bigMenu);
	}

	@Override
	public Integer countNum(Menu menu) {

		if (menu.getTitle() == null && menu.getMenu1() == null) {
			return -2;
		}

		return menusMapper.countNum(menu);
	}

	@Override
	public List<BigMenu> foundBigMenus(String[] bigMenus) {

		return bigMenuMapper.foundBigMenus(bigMenus);
	}

}
