package com.my.home.system.service;

import java.util.List;

import com.my.home.other.util.LayuiPage;
import com.my.home.system.po.Syslog;

public interface SysLogService {
	
	/**
	 * 新增
	 * @param syslog 系统日志
	 * @return
	 */
	Integer insert(Syslog syslog);
	

	/**
	 * @param s  实体
	 * @param page  分页参数
	 * @return
	 */
	List<Syslog> pageFound(Syslog s,LayuiPage page);
	
	/**
	 * 查询数量
	 * @param s
	 * @return
	 */
	Integer pageCount(Syslog s);


}
