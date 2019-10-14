package com.my.home.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.home.system.mapper.ModuleMapper;
import com.my.home.system.po.Module;
import com.my.home.system.service.ModuleService;

@Service
public class ModuleServiceImpl implements ModuleService {
	
	@Autowired
	private ModuleMapper moduleMapper;

	@Override
	public List<Module> simpleFoundModule(Module m) {
		List<Module> modules = moduleMapper.simpleFoundModule(m);		
		return modules;
	}

	@Override
	public List<Module> FoundModuleByIds(String[] modules) {
		
		return moduleMapper.FoundModuleByIds(modules);
	}
	
	

}
