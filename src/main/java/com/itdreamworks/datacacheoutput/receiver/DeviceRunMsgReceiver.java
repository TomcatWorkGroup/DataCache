package com.itdreamworks.datacacheoutput.receiver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itdreamworks.datacacheoutput.config.DeviceFocusConfig;
import com.itdreamworks.datacacheoutput.entity.Device;
import com.itdreamworks.datacacheoutput.entity.DeviceSnapshot;
import com.itdreamworks.datacacheoutput.sender.CacheStorageMsgSender;
import com.itdreamworks.datacacheoutput.utils.EhCacheUtil;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
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


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitListener(queues = "${listeningQueue}")
    @RabbitHandler
    public void process(byte[] msg) {
        String jsonStr = new String(msg, CHAR_SET);
        logger.error("DeviceRunMsgReceiver receiver msg");
        try {
            LinkedHashMap jsonObj = (LinkedHashMap) mapper.readValue(jsonStr, Object.class);
            String key = jsonObj.get(Device.KEY_DEVICE_NO).toString();
            String nickName = jsonObj.get(Device.KEY_NICK_NAME).toString();
            jsonObj.remove(Device.KEY_DEVICE_NO);
            jsonObj.remove(Device.KEY_NICK_NAME);
            Device device = cacheUtil.getDevice(key);
            if (null == device) {
                device = Device.getInstance(key);
                device.setDeviceNo(key);
                device.setNickName(nickName);
                modifyDeviceFields(jsonObj, device);
                device.setComeDate(new Date());
                cacheUtil.putDevice(device);
                //sender.send(DeviceSnapshot.getDeviceSnapshot(device).toJson());
                logger.error("DeviceRunMsgReceiver put cache and send storage msg");
            } else {
                modifyDeviceFields(jsonObj, device);
                logger.error("DeviceRunMsgReceiver modify device fields");
            }
            device.initFocusItems(deviceFocusConfig);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void modifyDeviceFields(LinkedHashMap map, Device device) {
        map.remove(Device.KEY_DEVICE_NO);
        Field[] fs = device.getClass().getDeclaredFields();
        for (Field f : fs) {
            String key = f.getName();
            if (map.containsKey(key)) {
                int value = Integer.parseInt(map.get(key).toString());
                f.setAccessible(true);
                try {
                    f.set(device, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

