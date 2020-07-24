package com.achui.crawler.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author portz
 * @date 2020/7/13 17:14
 */
@ConfigurationProperties(ignoreUnknownFields = true, prefix = "webdriver.conf")
@Data
public class WebDriverProperties {
    private String path;
    private int timeout;
    private int poolSize;
    private int poolMaxIdle;

}
