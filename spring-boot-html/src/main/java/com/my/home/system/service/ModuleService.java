package com.my.home.system.service;


import java.util.List;

import com.my.home.system.po.Module;



public interface ModuleService {
	
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
	List<Module> FoundModuleByIds(String[] modules);

}
