package com.my.home.other.ftp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/FtpDome/")
@Controller
@ApiIgnore
public class FtpDome {
	
	
/*	@RequestMapping("upfile")
	@ResponseBody
	@ApiOperation(value="上传测试",notes="上传测试")
	public String upfile() throws FileNotFoundException {
		boolean flag = FtpUtils.loginFtp();
		System.out.println(flag);
		// 开始上传
		File file = new File("F:\\\\aaa.jpg");
		String name = file.getName();
		String hz = name.substring(name.lastIndexOf("."), name.length());
		InputStream in = new FileInputStream(file);

		String fileName = UUID.randomUUID().toString() + hz;
		String uploadPath = "/domeUpload/dome/";
		boolean result = FtpUtils.uploadFileFtp(in, fileName, uploadPath);
		System.out.println(result);
		FtpUtils.loginOut();
		return uploadPath+fileName;
	}
*/
}
