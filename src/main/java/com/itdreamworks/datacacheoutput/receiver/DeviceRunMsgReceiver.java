package com.itdreamworks.datacacheoutput.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itdreamworks.datacacheoutput.config.DeviceFocusConfig;
import com.itdreamworks.datacacheoutput.config.MQSetting;
import com.itdreamworks.datacacheoutput.entity.Device;
import com.itdreamworks.datacacheoutput.entity.DeviceSnapshot;
import com.itdreamworks.datacacheoutput.sender.CacheStorageMsgSender;
import com.itdreamworks.datacacheoutput.utils.EhCacheUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;

@Component
public class DeviceRunMsgReceiver extends BaseReceiver {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    EhCacheUtil cacheUtil;
    @Autowired
    DeviceFocusConfig deviceFocusConfig;
    @Autowired
    CacheStorageMsgSender sender;

    @RabbitListener(queues = "${listeningQueue}")
    @RabbitHandler
    public void process(byte[] msg) {
        String jsonStr = new String(msg, CHAR_SET);
        try {
            LinkedHashMap jsonObj = (LinkedHashMap)mapper.readValue(jsonStr,Object.class);
            if(jsonObj.keySet().contains(Device.KEY_DEVICE_NO)){
                Object key = jsonObj.get(Device.KEY_DEVICE_NO);
                Cache cache = cacheUtil.getCache(EhCacheUtil.CACHE_DEVICE_INFO);
                Device device;
                if(null == cache.get(key)){
                    device = Device.getInstance(key.toString());
                    cache.put(key,device);
                    modifyDeviceFields(jsonObj,device);
                    device.initFocusItems(deviceFocusConfig);
                    sender.send(DeviceSnapshot.getDeviceSnapshot(device).toJson());
                }else{
                    Object o = cache.get(key);
                    device = (Device) cache.get(key).get();
                    modifyDeviceFields(jsonObj,device);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyDeviceFields(LinkedHashMap map,Device device){
        map.remove(Device.KEY_DEVICE_NO);
        Field[] fs = device.getClass().getDeclaredFields();
        for(Field f : fs){
            String key = f.getName();
            if(map.containsKey(key)){
                int value = Integer.parseInt(map.get(key).toString());
                f.setAccessible(true);
                try {
                    f.set(device,value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
