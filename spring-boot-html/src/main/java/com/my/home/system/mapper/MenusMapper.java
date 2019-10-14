package com.my.home.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.my.home.other.util.po.Menu;
import com.my.home.system.po.Menus;
@Mapper
public interface MenusMapper {
	
	/**
	 * 查询菜单
	 * @param menus
	 * @return
	 */
	List<Menus> simleFound(Menus menus); 
	
	/**
	 * 删除菜单
	 * @param menu
	 * @return
	 */
	Integer deleteMenus(Menu menu);
	
	/**
	 * 新增菜单
	 * @param menu
	 * @return
	 */
	Integer insertMenus(Menu menu);
	
	/**
	 * 修改菜单
	 * @param menu
	 * @return
	 */
	Integer updateMenus(Menu menu);
	
	/**
	 * 查询数量
	 * @param menu
	 * @return
	 */
	Integer countNum(Menu menu);
	

	

}
