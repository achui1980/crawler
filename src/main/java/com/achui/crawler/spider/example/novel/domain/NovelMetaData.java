package com.achui.crawler.spider.example.novel.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

/**
 * @author portz
 * @date 2021/5/31 15:16
 */
@Getter
@Setter
@ToString
@Builder
public class NovelMetaData {
    private String title;
    private String author;
    private String image;
    private String introduce;
    private String status;
    private String lastUpdate;
    @ToString.Include
    private Set<String> chapterLinks;
    private Set<Chapter> chapters;
}
