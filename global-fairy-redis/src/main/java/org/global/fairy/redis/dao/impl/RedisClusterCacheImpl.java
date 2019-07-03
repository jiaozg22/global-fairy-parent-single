package org.global.fairy.redis.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.global.fairy.redis.dao.IRedisClusterCache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * 
 * @ClassName: RedisClusterCache
 * @Description:
 * @author: jiao_
 * @date: 2019年7月3日 下午10:25:34
 */
@Repository
public class RedisClusterCacheImpl extends BaseRedisDaoImpl implements IRedisClusterCache {
	
	@Override
	public void put(Object key, Object value) {
		if (null == value) {
			return;
		}

		if (value instanceof String) {
			if (StringUtils.isEmpty(value.toString())) {
				return;
			}
		}

		// TODO Auto-generated method stub
		final String keyf = key + "";
		final Object valuef = value;
		final long liveTime = 86400;

		clusterRedisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyb = keyf.getBytes();
				byte[] valueb = toByteArray(valuef);
				connection.set(keyb, valueb);
				if (liveTime > 0) {
					connection.expire(keyb, liveTime);
				}
				return 1L;
			}
		});
	}

	/**
	 * 描述 : <byte[]转Object>. <br>
	 * <p>
	 * <使用方法说明>
	 * </p>
	 *
	 * @param bytes
	 * @return
	 */
	@Override
	public Object toObject(byte[] bytes) {
		Object obj = null;
		try {
			ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bis);
			obj = ois.readObject();
			ois.close();
			bis.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
	
	
	@Override
	public byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}
}