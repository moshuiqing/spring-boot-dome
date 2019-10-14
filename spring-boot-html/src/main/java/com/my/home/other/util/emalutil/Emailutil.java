package com.my.home.other.util.emalutil;

import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

public class Emailutil{
	
	 /**
    *
    * @param dataMap 渲染数据原
    * @param TemplatesName 模板名
    * @param templateEngine   模板操作类
    * @return
    */
   public static String createTemplates(Map<String,String> dataMap,String TemplatesName,TemplateEngine templateEngine){

       //context 对象用于注入要在模板上渲染的信息
       Context context = new Context();
       context.setVariables(dataMap);
       String emailText = templateEngine.process("emailTemplates",context);
       System.out.println(emailText);
       
       //返回模板源代码 String 类型
       return emailText;
   }

}
