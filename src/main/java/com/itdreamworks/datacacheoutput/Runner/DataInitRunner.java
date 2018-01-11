package com.itdreamworks.datacacheoutput.Runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.itdreamworks.datacacheoutput.client.FeignClient;
import com.itdreamworks.datacacheoutput.entity.Device;
import com.itdreamworks.datacacheoutput.utils.EhCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

@Component
public class DataInitRunner implements ApplicationRunner {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    FeignClient client;
    @Autowired
    EhCacheUtil cacheUtil;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        String s = client.getString();
        ArrayType javaType = mapper.getTypeFactory().constructArrayType(Device.class);
        Device[] devices = (Device[])mapper.readValue(s,javaType);
        Cache cache = cacheUtil.getCache(EhCacheUtil.CACHE_DEVICE_INFO);
        for(Device d : devices){
            cache.put(d.getDeviceNo(),d);
        }
        System.out.println("数据初始化完成");
    }
}
