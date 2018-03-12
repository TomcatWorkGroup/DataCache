package com.itdreamworks.datacacheoutput.Runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itdreamworks.datacacheoutput.client.SellDeviceFeignClient;
import com.itdreamworks.datacacheoutput.config.DeviceFocusConfig;
import com.itdreamworks.datacacheoutput.entity.Device;
import com.itdreamworks.datacacheoutput.utils.EhCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitRunner implements ApplicationRunner {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    EhCacheUtil cacheUtil;

    @Autowired
    DeviceFocusConfig deviceFocusConfig;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        Device device = Device.getInstance("123456");
//        device.setNickName("厂房锅炉1");
//        //报警信息
//        device.setPaiYanWenDuGao(1);
//        device.setLuShuiWenDuGao(0);
//        device.setChuKouWenDuGao(0);
//        device.setChaoYa(1);
//        //传感器故障
//        device.setLuShuiWenDuError(1);
//        //开关量输入
//        device.setGaoShuiWeiBaoJingDianJi(1);
//        device.setLouDianBaoJingXinHao(1);
//        //开关量输出
//        device.setRanShaoQiQiTing(1);
//        device.setChuZhaJi(1);
//        device.setPaiWuFa(1);
//        //模拟量输入
//        device.setLuShuiWenDu(96);
//        device.setRanLiaoWenDu(312);
//        device.setJiShuiYaLi(512);
//        //模拟量输出
//        device.setXunHuanShuChuLiangSetting(200);
//        //设定参数
//        device.setPower(Device.POWER_GAS);
//        device.setMedia(Device.MEDIA_WATER);
//        device.setLuShuiWenDu(95);
//        device.setJiShuiWenDu(3);
//        device.setZhengQiYaLi(10);
//        device.setKaiBengWenDuSetting(21);
//        device.setGuanBengWenDuSetting(90);
//        device.setLuBiBaoJingWenDuSetting(200);
//        device.setComeDate(new Date());
//        device.initFocusItems(deviceFocusConfig);
//        Cache cache = cacheUtil.getCache(EhCacheUtil.CACHE_DEVICE_INFO);
//        cache.put(device.getDeviceNo(),device);
    }

}
