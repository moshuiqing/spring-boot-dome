package com.my.home.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.home.other.util.JsonMap;
import com.my.home.other.util.LayuiPage;
import com.my.home.other.util.systemutil.SimpleUtils;
import com.my.home.other.util.systemutil.SystemUtils;
import com.my.home.system.mapper.RoleMapper;
import com.my.home.system.po.Role;
import com.my.home.system.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public JsonMap simpleFoundRole(String id) {
		JsonMap  jmap = new JsonMap();
		Role r = new Role();
		r.setRid(id);
		if(SystemUtils.isEmpty(id)) {
			jmap.setCode(-3);
			jmap.setMsg("缺少用户id");
		}
		Role role = roleMapper.simpleFoundRole(r);
		if(role!=null) {
			jmap.setCode(1);
			jmap.setMsg("查询成功！");
			jmap.setObject(role);
		}else {
			jmap.setCode(-1);
			jmap.setMsg("该用户没有角色");
		}
		
		return jmap;
	}

	@Override
	public Integer insertRole(Role role) {
		
		boolean result = SimpleUtils.isEmpty(role.getRname());
		if(result) {
			return -2;
		}		
		return roleMapper.insertRole(role);
	}

	@Override
	public Integer updateRole(Role role) {
		if(role.getRid()==null) {
			return -2;
		}
		return roleMapper.updateRole(role);
	}

	@Override
	public Integer deleteRole(Role role) {
		if(role.getRid()==null) {
			return -2;
		}
		return roleMapper.deleteRole(role);
	}

	@Override
	public List<Role> FoundRole(Role role) {
		
		return roleMapper.FoundRole(role);
	}

	@Override
	public List<Role> pageFoundRole(Role r, LayuiPage page) {
		
		return roleMapper.pageFoundRole(r, page.getStart(), page.getEnd());
	}

	@Override
	public Integer pageCountRole(Role r) {
		
		return roleMapper.pageCountRole(r);
	}



}
