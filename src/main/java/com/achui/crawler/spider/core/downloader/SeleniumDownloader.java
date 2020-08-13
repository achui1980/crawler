package com.achui.crawler.spider.core.downloader;

import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.SpiderPage;
import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author portz
 * @date 2020/7/31 17:07
 */
public class SeleniumDownloader implements Downloader {
    private ChromeDriver driver;

    @SneakyThrows
    @Override
    public SpiderPage download(Request request) {
        SpiderPage page = new SpiderPage();
        WebDriver webDriver = initWebDriver();
        if (request.isOpenInNewTab()) {
//            Robot robot = new Robot();
//            robot.keyPress(KeyEvent.VK_CONTROL);
//            robot.keyPress(KeyEvent.VK_T);
//            robot.keyRelease(KeyEvent.VK_CONTROL);
//            robot.keyRelease(KeyEvent.VK_T);
            //Switch focus to new tab
            ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(0));
        }
        webDriver.get(request.getUrl());
        page.setWebDriverEngine(webDriver);
        //TimeUnit.SECONDS.sleep(3);

        return page;
    }
    private WebDriver initWebDriver() {
        if (driver != null) {
            return driver;
        }
        System.setProperty("webdriver.chrome.driver", "conf/chromedriver_v84.exe");
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        options.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
