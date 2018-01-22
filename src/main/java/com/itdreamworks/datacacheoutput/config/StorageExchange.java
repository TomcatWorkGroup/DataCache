package com.itdreamworks.datacacheoutput.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "storageExchange")
public class StorageExchange {
    private String name;
    private String queue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getRoutingkey() {
        return routingkey;
    }

    public void setRoutingkey(String routingkey) {
        this.routingkey = routingkey;
    }

    private String routingkey;

}
