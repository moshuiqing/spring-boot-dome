
package com.my.home.other.util;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Global {
	
	
	
	/**
	 * 标志用户类型
	 */
	public static  final String loginType="loginType";
	
	public static  final String web="web";
	public static  final String app="app";
	public static  final String back="back";

	/**
	 * 星期字符串
	 */
	public static final String[] WEEKS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	/**
	 * 后台用户sessionkey
	 */
	public static String sysUser="user";
	public static String sysUserStr="userstr";
	
	//前台用户sessionkey
	public static final String webUser="webuser"; 
	
	public static final String appUser = "appUser";
	
	public static final String BLOOENKEY= "blooenkey";
	
	/**
	 * 路劲配置文件名称
	 */
	public final static String SOURCFILE="filepath.json";
	
	
	/**
	 * ftp上传路劲缓存key
	 */
	public final static String FILEPATHKEY="FTPFILEPATH";
	
	/**
	 * js端配置文件夹路劲key
	 */
	public final static String INDEXPATH="INDEX";
	/**
	 * 全局变量
	 */
	public static ServletContext context;

	/**
	 * 算法类型
	 */
	public static String type;

	/**
	 * 迭代次数
	 */
	public static Integer iterations;
	
	/**
	 * 系统菜单缓存key
	 */
	public final static String USERMENU="usermenu";
	
	/**
	 * 系统大菜单缓存key
	 */
	public final static String USERBIGMENU="userbigmenu";
	
	/**
	 * 系统菜单
	 */
	public final static String MODULESCACHKEY="module";
	
	public final static String BIGMENU="bigmenu";
	
	public final static String MENUS="menus";
	
	public final static String ALLMENUS="allmenus";
	
	
	/**
	 * 域名
	 */

	public static String yuming;
	/**
	 * ftp的ip地址
	 */

	public static String ftpip;

	/**
	 * ftp的端口
	 */

	public static int ftpport;

	/**
	 * ftp的用户名
	 */

	public static String ftpuser;

	/**
	 * ftp的用户名
	 */

	public static String ftppwd;

	
	

	@Value("${type}")
	public  void setType(String type) {
		Global.type = type;
	}
	@Value("${iterations}")
	public  void setIterations(Integer iterations) {
		Global.iterations = iterations;
	}

	@Value("${systemuser}")
	public void setSysUser(String sysUser) {
		Global.sysUser = sysUser;
	}
	
	
	@Value("${yuming}")
	public  void setYuming(String yuming) {
		Global.yuming = yuming;
	}

	@Value("${ftphost}")
	public  void setFtpip(String ftphost) {
		Global.ftpip = ftphost;
	}

	@Value("${ftpport}")
	public  void setFtpport(int ftpport) {
		Global.ftpport = ftpport;
	}

	@Value("${ftpname}")
	public  void setFtpuser(String ftpname) {
		Global.ftpuser = ftpname;
	}

	@Value("${ftppwd}")
	public  void setFtppwd(String ftppwd) {
		Global.ftppwd = ftppwd;
	}
	
	////////////////////////////////////////////////////////////////////////////聊天 layim/////////////////////////
	
	public final static String MINE= "mine";
	public final static String FRIEND= "friend";
	public final static String GROUP= "group";
	
	/**
	 * 登录刷新标识
	 */
	public static String LOGINFLAG;
	
	
	/**
	 * 聊天图片地址
	 */
	public final static String LAYIMUPIMGPATH="imgPath/";
	
	/**
	 * 聊天文件地址
	 */
	public final static String LAYIMUPFILEPATH="filePath/";

	
	

}
