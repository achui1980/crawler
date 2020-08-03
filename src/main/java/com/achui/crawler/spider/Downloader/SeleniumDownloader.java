package com.achui.crawler.spider.Downloader;

import com.achui.crawler.spider.Request;
import com.achui.crawler.spider.page.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

/**
 * @author portz
 * @date 2020/7/31 17:07
 */
public class SeleniumDownloader implements Downloader {
    @Override
    public Page download(Request request) {
        Page page = new Page();
        WebDriver webDriver = initWebDriver();
        webDriver.get(request.getUrl());
        page.setWebDriverEngine(webDriver);
        return page;
    }
    private WebDriver initWebDriver() {
        System.setProperty("webdriver.chrome.driver", "conf/chromedriver_v84.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
