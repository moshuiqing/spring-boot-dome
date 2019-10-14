package com.my.home.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.my.home.system.po.BigMenu;
@Mapper
public interface BigMenuMapper {
	
	/**
	 * 简易查询
	 * @param bigMenu
	 * @return
	 */
	List<BigMenu> simpleFound(BigMenu bigMenu);
	
	
	/**
	 * 新增
	 * @param bigMenu
	 * @return
	 */
	Integer insertMenu(BigMenu bigMenu);
	
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
	
	/**
	 * 查询数量
	 * @param bigMenu
	 * @return
	 */
	Integer pageCount(BigMenu bigMenu);
	
	/**
	 * @param bigMenus
	 * @return
	 * 根据id集查询
	 */
	List<BigMenu> foundBigMenus(@Param("bigMenus")String[] bigMenus);

}
