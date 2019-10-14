package com.my.home.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.my.home.system.po.Module;

/**
 * @author ai996
 * 权限dao
 */
@Mapper
public interface ModuleMapper {
	
	/**
	 * @param m
	 * @return
	 * 简易查询
	 */
	public List<Module> simpleFoundModule(Module m);
	
	/**
	 * @param modules
	 * @return
	 * 根据id级查询权限
	 */
	List<Module> FoundModuleByIds(@Param("modules")String[] modules);
	

}
