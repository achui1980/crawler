package com.achui.crawler.controller;

import com.achui.crawler.util.WebDriverPool;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author portz
 * @date 2020/7/24 15:44
 */
@RestController
@Slf4j
public class DemoSpider {
    @Autowired
    public WebDriverPool pool;

    @GetMapping("/spider")
    private String spider() throws Exception {
        String url = "https://molina.vuesoftware.com/PORTAL.STS/Pages/MolinaPP/Login.aspx?ReturnUrl=%2fPORTAL.STS%2fdefault.aspx%3fwa%3dwsignin1.0%26wtrealm%3dhttps%253a%252f%252fmolina.vuesoftware.com%252fPORTAL%252f%26wct%3d2018-09-20T22%253a43%253a34Z%26wcrt%3dMjAvU2VwLzIwMTggMjI6NDM6MzQgUE0%25253d&wa=wsignin1.0&wtrealm=https%3a%2f%2fmolina.vuesoftware.com%2fPORTAL%2f&wct=2018-09-20T22%3a43%3a34Z&wcrt=MjAvU2VwLzIwMTggMjI6NDM6MzQgUE0%253d";
        String userName = "AA0075";
        String password = "D*u4HlX4P8ey";
        String userInput = "//*[@id=\"txtUserName\"]";
        String passwordInput = "//*[@id=\"txtPassword\"]";
        String loginBtn = "//*[@id=\"btnLogin\"]";

        //*[@id="egSmartVUEGrid"]/div[4]/ul/li[7]/a
        WebDriver webDriver = pool.getWebDriverFromPool();
        webDriver.get(url);
        WebElement usernameEle = ((ChromeDriver) webDriver).findElementByXPath(userInput);
        WebElement passwordEle = ((ChromeDriver) webDriver).findElementByXPath(passwordInput);
        WebElement loginEle = ((ChromeDriver) webDriver).findElementByXPath(loginBtn);
        usernameEle.sendKeys(userName);
        passwordEle.sendKeys(password);
        loginEle.click();
//        Thread.sleep(3000);
//        webDriver.get("https://molina.vuesoftware.com/portal/Common/GlobalSearch");
        WebElement menu = pool.getWebDriverFactory().getWait().until(d -> d.findElement(By.xpath("//*[@id=\"leftPanel\"]/li[5]")));
        Actions action = new Actions(webDriver);
        action.moveToElement(menu).perform();
        WebElement btn = ((ChromeDriver) webDriver).findElementByXPath("//*[@id=\"leftPanel\"]/li[5]/ul/li[2]");
        //action.moveToElement(btn).click().perform();
        //action.click(btn);
        btn.click();
        System.out.println(btn.getAttribute("data-uid"));
        WebElement searchBtl = pool.getWebDriverFactory().getWait().until(d -> d.findElement(By.xpath("//*[@id=\"SmartVUEPanel_AdvSearch_btnSearch\"]")));
        WebElement agentName = ((ChromeDriver) webDriver).findElementByXPath("//*[@id=\"SmartVUEPanel_AdvSearch_4_2_0\"]")
        agentName.sendKeys("");
        System.out.println(searchBtl.getText());

        return webDriver.getTitle();
    }
}
