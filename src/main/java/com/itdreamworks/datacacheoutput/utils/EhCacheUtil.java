package com.itdreamworks.datacacheoutput.utils;

import com.itdreamworks.datacacheoutput.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EhCacheUtil {
    public static final String CACHE_DEVICE_INFO = "cache_device_info";

    @Autowired
    CacheManager cacheManager;


    public EhCacheCache getCache(String cacheName){
        return (EhCacheCache)cacheManager.getCache(cacheName);
    }

    public Device getDevice(String key){
        EhCacheCache cache = getCache(CACHE_DEVICE_INFO);
        Cache.ValueWrapper element = cache.get(key);
        if(null != element){
            return (Device) element.get();
        }
        return null;
    }

    public void putDevice(Device device){
        EhCacheCache cache = getCache(CACHE_DEVICE_INFO);
        cache.put(device.getDeviceNo(),device);
    }

    public List<Device> getDevices(String[] keys){
        ArrayList<Device> ls = new ArrayList<>(keys.length);
        Cache cache = getCache(CACHE_DEVICE_INFO);
        for(String key : keys){
            Cache.ValueWrapper element = cache.get(key);
            if(null != element){
                ls.add((Device) element.get());
            }
        }
        return ls;
    }
}
