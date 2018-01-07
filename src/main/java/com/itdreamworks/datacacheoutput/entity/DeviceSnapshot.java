package com.itdreamworks.datacacheoutput.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 设备信息快照实体
 */
public class DeviceSnapshot {
    private String deviceNo;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    private String nickName;
    private int focusItem1, focusItem2, focusItem3;
    private Date comeDate;
    //燃料类型
    private int power;

    /**
     * 获取燃料类型
     *
     * @return power
     */
    public int getPower() {
        return power;
    }

    /**
     * 设置燃料类型
     *
     * @param data
     */
    public void setPower(int data) {
        power = data;
    }

    //介质类型
    private int media;

    /**
     * 获取介质类型
     *
     * @return power
     */
    public int getMedia() {
        return media;
    }

    /**
     * 设置介质类型
     *
     * @param data
     */
    public void setMedia(int data) {
        media = data;
    }

    //运行状态
    private int runstatus;

    /**
     * 获取运行状态
     *
     * @return power
     */
    public int getRunstatus() {
        return runstatus;
    }

    /**
     * 设置运行状态
     *
     * @param data
     */
    public void setRunstatus(int data) {
        runstatus = data;
    }

    public Date getComeDate() {
        return comeDate;
    }

    public void setComeDate(Date comeDate) {
        this.comeDate = comeDate;
    }

    public int getAlarm() {
        return alarm;
    }

    public void setAlarm(int alarm) {
        this.alarm = alarm;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    //报警状态
    private int alarm;
    //故障状态
    private int error;

    //设备运行天数
    private int runDays;
    //设备运行小时数
    private int runHours;

    public int getRunDays() {
        return runDays;
    }

    public void setRunDays(int runDays) {
        this.runDays = runDays;
    }

    public int getRunHours() {
        return runHours;
    }

    public void setRunHours(int runHours) {
        this.runHours = runHours;
    }

    public static List<DeviceSnapshot> getDeviceSnapshotList(List<Device> devices){
        ArrayList<DeviceSnapshot> ls = new ArrayList<>(devices.size());
        for (Device d : devices){
            ls.add(d.getSnapshot());
        }
        return ls;
    }
}
