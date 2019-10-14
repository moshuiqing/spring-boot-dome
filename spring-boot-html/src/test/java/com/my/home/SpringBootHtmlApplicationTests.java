package com.my.home;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.my.home.other.util.emalutil.EamilComment;

import net.sf.json.JSONArray;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootHtmlApplicationTests {

	
	@Autowired
	private EamilComment eamilComment;
	
	@Test
	public void contextLoads() {
		
		//eamilComment.sendTemplateMail("javaliuhao@126.com");
		//eamilComment.sendSimpleEmail("javaliuhao@126.com", "1111", "哈哈哈哈");
		List<String> list  = new ArrayList<>();
		list.add("one");
		System.out.println(JSONArray.fromObject(list).toString());
		
		

	}

}
