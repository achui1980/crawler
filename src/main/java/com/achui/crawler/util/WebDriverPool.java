package com.achui.crawler.util;

import com.achui.crawler.conf.WebDriverFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author portz
 * @date 2020/7/13 17:09
 */
@Component
@Slf4j
public class WebDriverPool {

    @Autowired
    private ObjectPool<ChromeDriver> pool;

    public WebDriver getWebDriverFromPool() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            log.error("Exception while get webdriver from pool", e);
        }
        return null;
    }
    public WebDriverFactory getWebDriverFactory() {
        PooledObjectFactory<ChromeDriver> pooledObjectFactory = ((GenericObjectPool<ChromeDriver>)pool).getFactory();
        return (WebDriverFactory) pooledObjectFactory;
    }
}
