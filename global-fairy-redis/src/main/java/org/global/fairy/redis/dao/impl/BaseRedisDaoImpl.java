package org.global.fairy.redis.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.global.fairy.redis.dao.IBaseRedisDao;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

/**
 
 * @CreateDate：14:56 2018/3/8
 */
/**
 * 
 * @ClassName: BaseRedisDao 
*  
 * @Description：
 * --------------------- 
 *	作者：大漠知秋 
 *	来源：CSDN 
 *	原文：https://blog.csdn.net/wo18237095579/article/details/80925586 
 *	版权声明：本文为博主原创文章，转载请附上博文链接！
 * 操作Redis Dao接口
 * 增删改 -不能在这里面抓取异常 -因为可能有事务处理
 * @Author：大漠知秋  整理：jiaozongguan
 * @date: 2019年7月3日 下午10:49:27
 */
@Repository
public class BaseRedisDaoImpl implements IBaseRedisDao<String, Object> {
	private static final Logger logger = LogManager.getLogger("BaseRedisDaoImpl.class");
	
	@Autowired
    RedisTemplate clusterRedisTemplate;

    /**  出异常，重复操作的次数 */
    private static final Integer TIMES = 3;

    @Override
    public Set<String> getAllKeys() {
        return clusterRedisTemplate.keys("*");
    }

    @Override
    public Map<String, Object> getAllString() {
        Set<String> stringSet = getAllKeys();
        Map<String, Object> map = new HashMap<String, Object>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.STRING) {
                map.put(k, get(k));
            }
        }
        return map;
    }

    @Override
    public Map<String, Set<Object>> getAllSet() {
        Set<String> stringSet = getAllKeys();
        Map<String, Set<Object>> map = new HashMap<String, Set<Object>>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.SET) {
                map.put(k, getSet(k));
            }
        }
        return map;
    }

    @Override
    public Map<String, Set<Object>> getAllZSetRange() {
        Set<String> stringSet = getAllKeys();
        Map<String, Set<Object>> map = new HashMap<String, Set<Object>>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.ZSET) {
            	logger.debug("k:" + k);
                map.put(k, getZSetRange(k));
            }
        }
        return map;
    }

    @Override
    public Map<String, Set<Object>> getAllZSetReverseRange() {
        Set<String> stringSet = getAllKeys();
        Map<String, Set<Object>> map = new HashMap<String, Set<Object>>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.ZSET) {
                map.put(k, getZSetReverseRange(k));
            }
        }
        return map;
    }

    @Override
    public Map<String, List<Object>> getAllList() {
        Set<String> stringSet = getAllKeys();
        Map<String, List<Object>> map = new HashMap<String, List<Object>>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.LIST) {
                map.put(k, getList(k));
            }
        }
        return map;
    }

    @Override
    public Map<String, Map<String, Object>> getAllMap() {
        Set<String> stringSet = getAllKeys();
        Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String k = iterator.next();
            if (getType(k) == DataType.HASH) {
                map.put(k, getMap(k));
            }
        }
        return map;
    }

    @Override
    public void addList(String key, List<Object> objectList) {
        for (Object obj : objectList) {
            addList(key, obj);
        }
    }

    @Override
    public long addList(String key, Object obj) {
        return clusterRedisTemplate.boundListOps(key).rightPush(obj);
    }

    @Override
    public long addList(String key, Object... obj) {
        return clusterRedisTemplate.boundListOps(key).rightPushAll(obj);
    }

    @Override
    public List<Object> getList(String key, long s, long e) {
        return clusterRedisTemplate.boundListOps(key).range(s, e);
    }

    @Override
    public List<Object> getList(String key) {
        return clusterRedisTemplate.boundListOps(key).range(0, getListSize(key));
    }

    @Override
    public long getListSize(String key) {
        return clusterRedisTemplate.boundListOps(key).size();
    }

    @Override
    public long removeListValue(String key, Object object) {
        return clusterRedisTemplate.boundListOps(key).remove(0, object);
    }

    @Override
    public long removeListValue(String key, Object... objects) {
        long r = 0;
        for (Object object : objects) {
            r += removeListValue(key, object);
        }
        return r;
    }

    @Override
    public void remove(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                remove(key[0]);
            } else {
                clusterRedisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    @Override
    public void removeBlear(String... blears) {
        for (String blear : blears) {
            removeBlear(blear);
        }
    }

    @Override
    public Boolean renameIfAbsent(String oldKey, String newKey) {
        return clusterRedisTemplate.renameIfAbsent(oldKey, newKey);
    }

    @Override
    public void removeBlear(String blear) {
        clusterRedisTemplate.delete(clusterRedisTemplate.keys(blear));
    }

    @Override
    public void removeByRegular(String... blears) {
        for (String blear : blears) {
            removeBlear(blear);
        }
    }

    @Override
    public void removeByRegular(String blear) {
        Set<String> stringSet = getAllKeys();
        for (String s : stringSet) {
            if (Pattern.compile(blear).matcher(s).matches()) {
                clusterRedisTemplate.delete(s);
            }
        }
    }

    @Override
    public void removeMapFieldByRegular(String key, String... blears) {
        for (String blear : blears) {
            removeMapFieldByRegular(key, blear);
        }
    }

    @Override
    public void removeMapFieldByRegular(String key, String blear) {
        Map<String, Object> map = getMap(key);
        Set<String> stringSet = map.keySet();
        for (String s : stringSet) {
            if (Pattern.compile(blear).matcher(s).matches()) {
                clusterRedisTemplate.boundHashOps(key).delete(s);
            }
        }
    }

    @Override
    public Long removeZSetValue(String key, Object... value) {
        return clusterRedisTemplate.boundZSetOps(key).remove(value);
    }

    @Override
    public void removeZSet(String key) {
        removeZSetRange(key, 0L, getZSetSize(key));
    }

    @Override
    public void removeZSetRange(String key, Long start, Long end) {
        clusterRedisTemplate.boundZSetOps(key).removeRange(start, end);
    }

    @Override
    public void setZSetUnionAndStore(String key, String key1, String key2) {
        clusterRedisTemplate.boundZSetOps(key).unionAndStore(key1, key2);
    }

    @Override
    public Set<Object> getZSetRange(String key) {
        return getZSetRange(key, 0, getZSetSize(key));
    }

    @Override
    public Set<Object> getZSetRange(String key, long s, long e) {
        return clusterRedisTemplate.boundZSetOps(key).range(s, e);
    }

    @Override
    public Set<Object> getZSetReverseRange(String key) {
        return getZSetReverseRange(key, 0, getZSetSize(key));
    }

    @Override
    public Set<Object> getZSetReverseRange(String key, long start, long end) {
        return clusterRedisTemplate.boundZSetOps(key).reverseRange(start, end);
    }

    @Override
    public Set<Object> getZSetRangeByScore(String key, double start, double end) {
        return clusterRedisTemplate.boundZSetOps(key).rangeByScore(start, end);
    }

    @Override
    public Set<Object> getZSetReverseRangeByScore(String key, double start, double end) {
        return clusterRedisTemplate.boundZSetOps(key).reverseRangeByScore(start, end);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> getZSetRangeWithScores(String key, long start, long end) {
        return clusterRedisTemplate.boundZSetOps(key).rangeWithScores(start, end);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> getZSetReverseRangeWithScores(String key, long start, long end) {
        return clusterRedisTemplate.boundZSetOps(key).reverseRangeWithScores(start, end);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> getZSetRangeWithScores(String key) {
        return getZSetRangeWithScores(key, 0, getZSetSize(key));
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> getZSetReverseRangeWithScores(String key) {
        return getZSetReverseRangeWithScores(key, 0, getZSetSize(key));
    }

    @Override
    public long getZSetCountSize(String key, double sMin, double sMax) {
        return clusterRedisTemplate.boundZSetOps(key).count(sMin, sMax);
    }

    @Override
    public long getZSetSize(String key) {
        return clusterRedisTemplate.boundZSetOps(key).size();
    }

    @Override
    public double getZSetScore(String key, Object value) {
        return clusterRedisTemplate.boundZSetOps(key).score(value);
    }

    @Override
    public double incrementZSetScore(String key, Object value, double delta) {
        return clusterRedisTemplate.boundZSetOps(key).incrementScore(value, delta);
    }

    @Override
    public Boolean addZSet(String key, double score, Object value) {
        return clusterRedisTemplate.boundZSetOps(key).add(value, score);
    }

    @Override
    public Long addZSet(String key, TreeSet<Object> value) {
        return clusterRedisTemplate.boundZSetOps(key).add(value);
    }

    @Override
    public Boolean addZSet(String key, double[] score, Object[] value) {
        if (score.length != value.length) {
            return false;
        }
        for (int i = 0; i < score.length; i++) {
            if (addZSet(key, score[i], value[i]) == false) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void remove(String key) {
        if (exists(key)) {
            clusterRedisTemplate.delete(key);
        }
    }

    @Override
    public void removeZSetRangeByScore(String key, double s, double e) {
        clusterRedisTemplate.boundZSetOps(key).removeRangeByScore(s, e);
    }

    @Override
    public Boolean setSetExpireTime(String key, Long time) {
        return clusterRedisTemplate.boundSetOps(key).expire(time, TimeUnit.SECONDS);
    }

    @Override
    public Boolean setZSetExpireTime(String key, Long time) {
        return clusterRedisTemplate.boundZSetOps(key).expire(time, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {
        return clusterRedisTemplate.hasKey(key);
    }

    @Override
    public Object get(String key) {
        return clusterRedisTemplate.boundValueOps(key).get();
    }

    @Override
    public List<Object> get(String... keys) {
        List<Object> list = new ArrayList<Object>();
        for (String key : keys) {
            list.add(get(key));
        }
        return list;
    }

    @Override
    public List<Object> getByRegular(String regKey) {
        Set<String> stringSet = getAllKeys();
        List<Object> objectList = new ArrayList<Object>();
        for (String s : stringSet) {
            if (Pattern.compile(regKey).matcher(s).matches() && getType(s) == DataType.STRING) {
                objectList.add(get(s));
            }
        }
        return objectList;
    }

    @Override
    public void set(String key, Object value) {
        clusterRedisTemplate.boundValueOps(key).set(value);
    }

    @Override
    public void set(String key, Object value, Long expireTime) {
        clusterRedisTemplate.boundValueOps(key).set(value, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean setExpireTime(String key, Long expireTime) {
        return clusterRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }


    @Override
    public DataType getType(String key) {
        return clusterRedisTemplate.type(key);
    }


    @Override
    public void removeMapField(String key, Object... field) {
        clusterRedisTemplate.boundHashOps(key).delete(field);
    }

    @Override
    public Long getMapSize(String key) {
        return clusterRedisTemplate.boundHashOps(key).size();
    }

    @Override
    public Map<String, Object> getMap(String key) {
        return clusterRedisTemplate.boundHashOps(key).entries();
    }

    @Override
    public <T> T getMapField(String key, String field) {
        return (T) clusterRedisTemplate.boundHashOps(key).get(field);
    }

    @Override
    public Boolean hasMapKey(String key, String field) {
        return clusterRedisTemplate.boundHashOps(key).hasKey(field);
    }

    @Override
    public List<Object> getMapFieldValue(String key) {
        return clusterRedisTemplate.boundHashOps(key).values();
    }

    @Override
    public Set<Object> getMapFieldKey(String key) {
        return clusterRedisTemplate.boundHashOps(key).keys();
    }

    @Override
    public void addMap(String key, Map<String, Object> map) {
        clusterRedisTemplate.boundHashOps(key).putAll(map);
    }

    @Override
    public void addMap(String key, String field, Object value) {
        clusterRedisTemplate.boundHashOps(key).put(field, value);
    }

    @Override
    public void addMap(String key, String field, Object value, long time) {
        clusterRedisTemplate.boundHashOps(key).put(field, value);
        clusterRedisTemplate.boundHashOps(key).expire(time, TimeUnit.SECONDS);
    }

    @Override
    public void addSet(String key, Object... obj) {
        clusterRedisTemplate.boundSetOps(key).add(obj);
    }

    @Override
    public long removeSetValue(String key, Object obj) {
        return clusterRedisTemplate.boundSetOps(key).remove(obj);
    }

    @Override
    public long removeSetValue(String key, Object... obj) {
        if (obj != null && obj.length > 0) {
            return clusterRedisTemplate.boundSetOps(key).remove(obj);
        }
        return 0L;
    }

    @Override
    public long getSetSize(String key) {
        return clusterRedisTemplate.boundSetOps(key).size();
    }

    @Override
    public Boolean hasSetValue(String key, Object obj) {
        Boolean boo = null;
        int t = 0;
        while (true) {
            try {
                boo = clusterRedisTemplate.boundSetOps(key).isMember(obj);
                break;
            } catch (Exception e) {
            	logger.error("key[" + key + "],obj[" + obj + "]判断Set中的值是否存在失败,异常信息:" + e.getMessage());
                t++;
            }
            if (t > TIMES) {
                break;
            }
        }
        logger.info("key[" + key + "],obj[" + obj + "]是否存在,boo:" + boo);
        return boo;
    }

    @Override
    public Set<Object> getSet(String key) {
        return clusterRedisTemplate.boundSetOps(key).members();
    }

    @Override
    public Set<Object> getSetUnion(String key, String otherKey) {
        return clusterRedisTemplate.boundSetOps(key).union(otherKey);
    }

    @Override
    public Set<Object> getSetUnion(String key, Set<Object> set) {
        return clusterRedisTemplate.boundSetOps(key).union(set);
    }

    @Override
    public Set<Object> getSetIntersect(String key, String otherKey) {
        return clusterRedisTemplate.boundSetOps(key).intersect(otherKey);
    }

    @Override
    public Set<Object> getSetIntersect(String key, Set<Object> set) {
        return clusterRedisTemplate.boundSetOps(key).intersect(set);
    }

}
