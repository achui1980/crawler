package com.achui.crawler.spider.core.pool;

import com.achui.crawler.conf.WebDriverProperties;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

/**
 * @author portz
 * @date 2020/7/13 17:21
 */
@Slf4j
@Getter
public class WebDriverFactory extends BasePooledObjectFactory<ChromeDriver> {
    private WebDriverProperties config;
    //private WebDriverWait wait;
    //private FluentWait<ChromeDriver> fluentWait;

    public WebDriverFactory(WebDriverProperties config) {
        this.config = config;
    }

    @Override
    public ChromeDriver create() throws Exception {
        System.setProperty("webdriver.chrome.driver", config.getPath());
        log.info("Driver path: {}", config.getPath());
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        ChromeDriver driver = new ChromeDriver(options);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //this.wait = new WebDriverWait(driver, config.getTimeout());
        //this.fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(config.getTimeout())).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
        return driver;
    }

    @Override
    public PooledObject<ChromeDriver> wrap(ChromeDriver chromeDriver) {
        return new DefaultPooledObject<>(chromeDriver);
    }

    @Override
    public void destroyObject(PooledObject<ChromeDriver> driverPooled) throws Exception {
        if (driverPooled == null) {
            return;
        }
        ChromeDriver driver = driverPooled.getObject();
        driver.quit();
        driver.close();
    }
}
