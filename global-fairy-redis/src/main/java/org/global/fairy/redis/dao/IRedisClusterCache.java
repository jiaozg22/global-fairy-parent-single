package org.global.fairy.redis.dao;

/**
 * 
 * @ClassName: IRedisClusterCache 
 * @Description: 缓存业务处理
 * @author: jiao_zg22@163.com
 * @date: 2019年7月3日 下午11:08:36
 */
public interface IRedisClusterCache extends IBaseRedisDao<String,Object> {
	void put(Object key, Object value);
	
	Object toObject(byte[] bytes) ;
	
	byte[] toByteArray(Object obj) ;
}
