package com.itdreamworks.datacacheoutput.controller;

import com.itdreamworks.datacacheoutput.annotation.Permission;
import com.itdreamworks.datacacheoutput.entity.Device;
import com.itdreamworks.datacacheoutput.entity.DeviceSnapshot;
import com.itdreamworks.datacacheoutput.utils.EhCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("output")
public class DataOutputController {

    @Autowired
    EhCacheUtil cacheUtil;

    /**
     * 获取设备快照信息-快照信息可以缓存几分钟以减少服务器计算压力
     *
     * @param devicesIds
     * @return
     */
    @Permission
    @PostMapping(value = "/devices")
    public List<DeviceSnapshot> getDevicesSnapshot(@RequestParam(name = "ids") String devicesIds) {
        String[] deviceKeys = devicesIds.split(",");
        return DeviceSnapshot.getDeviceSnapshotList(cacheUtil.getDevices(deviceKeys));
    }

    /**
     * 获取设备详细信息
     *
     * @param deviceId
     * @return
     */
    @Permission
    @PostMapping(value = "/device/{id}")
    public Device getDeviceInfo(@PathVariable(name = "id") String deviceId) {
        return cacheUtil.getDevice(deviceId);
    }
}
