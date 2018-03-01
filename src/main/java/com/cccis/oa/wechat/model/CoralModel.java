package com.cccis.oa.wechat.model;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

public class CoralModel implements Serializable {

    private String policy;
    private String desc;
    private Map<String, String> insuredMap = Maps.newHashMap();

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Map<String, String> getInsuredMap() {
        return insuredMap;
    }

    public void setInsuredMap(Map<String, String> insuredMap) {
        this.insuredMap = insuredMap;
    }

    public void addInsuredMap(String key, String value) {
        this.insuredMap.put(key,value);
    }
}
