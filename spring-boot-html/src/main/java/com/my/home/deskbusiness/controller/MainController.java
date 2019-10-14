package com.my.home.deskbusiness.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author ai996 前台首页
 */
@RequestMapping("/webdesk/main/")
@ResponseBody
@Api(value="MainController",tags="前台主页口控制器")
public class MainController {
	
	/**
	 * @return
	 */
	@RequestMapping(value="toWebMain",method=RequestMethod.GET)
	@ApiOperation(value="进入前台首页",notes="进入前台首页")
	public String toWebMain() {
		
		
		return "";
	}

}
