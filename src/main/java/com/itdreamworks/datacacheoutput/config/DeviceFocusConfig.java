package com.itdreamworks.datacacheoutput.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties(prefix = "DeviceFocus")
public class DeviceFocusConfig {
    public List<SettingConfig> getSetting() {
        return setting;
    }

    public void setSetting(List<SettingConfig> setting) {
        this.setting = setting;
    }

    private List<SettingConfig> setting = new ArrayList<>();
}
