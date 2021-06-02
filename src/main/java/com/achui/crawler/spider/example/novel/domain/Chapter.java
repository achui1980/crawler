package com.achui.crawler.spider.example.novel.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author portz
 * @date 2021/6/2 14:36
 */
@Data
@Builder
public class Chapter {
    private String name;
    private String link;
    private String content;
}
