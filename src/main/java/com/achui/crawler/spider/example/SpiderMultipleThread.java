package com.achui.crawler.spider.example;

import com.achui.crawler.spider.example.github.GithubPageCallable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author portz
 * @date 2020/8/13 14:23
 */
public class SpiderMultipleThread {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(new GithubPageCallable());
        executorService.submit(new GithubPageCallable());
        executorService.submit(new GithubPageCallable());
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
