package com.achui.crawler.spider.core;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author portz
 * @date 2020/7/31 15:16
 */
@Data
public class RequestItem {
    private Map<String, Object> item = Maps.newHashMap();

    public void put(String key, Object value) {
        item.put(key, value);
    }

    @Override
    public String toString() {
        String result = item.entrySet().stream().map(entry -> entry.getKey() + ":" + entry.getValue())
                .collect(Collectors.joining(" ,"));
        return "RequestItem{" + result + "}";
    }
}
