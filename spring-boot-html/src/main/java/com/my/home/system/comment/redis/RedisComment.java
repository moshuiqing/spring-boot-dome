package com.my.home.system.comment.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component // 把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
public class RedisComment {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public Integer set(String key, String value) {
		ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
		if (!this.stringRedisTemplate.hasKey(key)) {
			ops.set(key, value);
			System.out.println("set key success");
			return 1;
		} else {
			System.out.println("已存在，先删除");
			del(key);
			ops.set(key, value);
			return 0;
		}
	}

	public String get(String key) {
		return this.stringRedisTemplate.opsForValue().get(key);
	}
	
	public Integer del(String key) {
		try {
			this.stringRedisTemplate.delete(key);
			return 1;
		} catch (Exception e) {
			
			e.printStackTrace();
			return -1;
		}
	}
	

}
