package com.achui.crawler.conf;

import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author portz
 * @date 2020/7/16 10:08
 */
@Configuration
@EnableConfigurationProperties(WebDriverProperties.class)
public class WebDriverConfigure {

    @Autowired
    private WebDriverProperties webDriverProperties;

    @Bean
    public WebDriverFactory getWebDriverFactory() {
        return new WebDriverFactory(webDriverProperties);
    }

    @Bean
    public ObjectPool<ChromeDriver> generatePool() {
        GenericObjectPoolConfig<ChromeDriver> conf = new GenericObjectPoolConfig();
        conf.setMaxTotal(webDriverProperties.getPoolSize());
        conf.setMaxIdle(webDriverProperties.getPoolMaxIdle());
        return new GenericObjectPool<>(new WebDriverFactory(webDriverProperties), conf);
    }
}
