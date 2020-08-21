package com.achui.crawler.spider.example;

import com.achui.crawler.spider.example.bo.BOScriptErrorCallable;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author portz
 * @date 2020/8/13 14:23
 */
@Slf4j
public class SpiderMultipleThread {
    public static void main(String[] args) {
        ThreadPoolExecutor fixedThreadPoolExecutor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                if (r instanceof Thread) {
                    if (t != null) {
                        //处理捕获的异常
                        log.error("Exception:", t);
                    }
                } else if (r instanceof FutureTask) {
                    FutureTask futureTask = (FutureTask) r;
                    try {
                        futureTask.get();
                    } catch (InterruptedException e) {
                        log.error("Exception:", e);
                    } catch (ExecutionException e) {
                        //处理捕获的异常
                        log.error("Exception:", e);
                    }
                }

            }
        };
        ExecutorService executorService = Executors.newCachedThreadPool();
//        IntStream.range(1, 10)
//                .forEach(i -> fixedThreadPoolExecutor.submit(new BOScriptErrorCallable()));
        fixedThreadPoolExecutor.submit(new BOScriptErrorCallable());
        if (!fixedThreadPoolExecutor.isShutdown()) {
            fixedThreadPoolExecutor.shutdown();
        }
    }
}
