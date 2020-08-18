package com.achui.crawler.spider.core.pool;

import com.achui.crawler.conf.WebDriverProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author portz
 * @date 2020/8/15 18:13
 */
@Slf4j
public class SeleniumPool {
    private WebDriverProperties initWebDriverProperty() {
        WebDriverProperties webDriverProperties = new WebDriverProperties();
        webDriverProperties.setPath("conf/chromedriver_v84.exe");
        webDriverProperties.setPoolMaxIdle(10);
        webDriverProperties.setPoolSize(20);
        return webDriverProperties;
    }

    private ObjectPool<ChromeDriver> generatePool() {
        WebDriverProperties webDriverProperties = initWebDriverProperty();
        GenericObjectPoolConfig<ChromeDriver> conf = new GenericObjectPoolConfig();
        conf.setMaxTotal(webDriverProperties.getPoolSize());
        conf.setMaxIdle(webDriverProperties.getPoolMaxIdle());
        return new GenericObjectPool<>(new WebDriverFactory(webDriverProperties), conf);
    }

    public WebDriver getWebDriverFromPool() {
        try {
            return generatePool().borrowObject();
        } catch (Exception e) {
            log.error("Exception while get webdriver from pool", e);
        }
        return null;
    }
}
