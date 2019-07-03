package org.global.fairy.redis.service;

import org.global.fairy.redis.dao.IRedisClusterCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisClusterService {

	@Autowired
	IRedisClusterCache redisClusterCache;

	public boolean clusterSave()  {
		try {
			redisClusterCache.put("token", "token");
		}catch(Exception e) {
			
			return false;
		}
		return true;
	}

	public Object getCluster(String key) {
		Object val = redisClusterCache.get(key);
		return val;
	}
	
	public boolean clusterUpdate(String key,Object value) {
		if (!redisClusterCache.exists(key)) {
			return false;
		}else {
			redisClusterCache.put(key, value);
			return true;
		}
	}
}