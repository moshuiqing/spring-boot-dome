package com.my.home.system.service;

import java.util.List;

import com.my.home.other.util.po.Menu;
import com.my.home.system.po.BigMenu;
import com.my.home.system.po.Menus;

public interface MenusService {
	/**
	 * 简易查询
	 * @param menus
	 * @return
	 */
	List<Menus> simleFound(Menus menus);

	/**
	 * 缓存菜单和权限
	 */
	public void cacheMenuModule();
	
	
	
	/**
	 * 新增菜单同时赋值最高权限admin
	 * @param bigMenu
	 * @return
	 */
	Integer insertMenu(BigMenu bigMenu,String roleId);
	
	/**
	 * 修改
	 * @param bigMenu
	 * @return
	 */
	Integer updateMenu(BigMenu bigMenu);
	
	/**
	 * 删除
	 * @param bigMenu
	 * @return
	 */
	Integer deleteMenu(BigMenu bigMenu);
	
	Integer deleteMenus(Menu menu);
	
	Integer insertMenus(Menu menu,String roleId);
	
	Integer updateMenus(Menu menu);
	/**
	 * 查询数量
	 * @param bigMenu
	 * @return
	 */
	Integer pageCount(BigMenu bigMenu);
	
	
	/**
	 * 查询数量
	 * @param menu
	 * @return
	 */
	Integer countNum(Menu menu);
	
	
	/**
	 * @param bigMenus
	 * @return
	 * 根据id集查询
	 */
	List<BigMenu> foundBigMenus(String[] bigMenus);
	

}
