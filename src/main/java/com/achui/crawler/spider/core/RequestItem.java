package com.achui.crawler.spider.core;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * @author portz
 * @date 2020/7/31 15:16
 */
@Data
public class RequestItem {
    private Map<String, String> item = Maps.newHashMap();

    public void put(String key, String value) {
        item.put(key, value);
    }
}
