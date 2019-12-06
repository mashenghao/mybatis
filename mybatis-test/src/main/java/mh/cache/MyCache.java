package mh.cache;

import org.apache.ibatis.cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 缓存针对于单个的命名空间
 *
 * @author: mahao
 * @date: 2019/12/5
 */
public class MyCache implements Cache {

    Map<Object, Object> cache = new HashMap<Object, Object>();

    public MyCache(String name) {
        System.out.println(name);
    }

    @Override
    public String getId() {
        return cache.toString();
    }

    @Override
    public void putObject(Object key, Object value) {
        cache.put(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return cache.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return new ReentrantReadWriteLock();
    }
}
