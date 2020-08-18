package com.achui.crawler.controller;

import com.achui.crawler.util.WebDriverPool;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @author portz
 * @date 2020/7/16 11:05
 */
@RestController
@Slf4j
public class DemoControll {

    @Autowired
    private WebDriverPool pool;

    @GetMapping("/demo")
    public String echo() throws Exception {
        WebDriver webDriver = pool.getWebDriverFromPool();
        webDriver.get("https://agentviewcigna.com/web/login");
        //Thread.sleep(3000);
        WebElement username = ((ChromeDriver) webDriver).findElementByXPath("//*[@id=\"username\"]");
        WebElement password = ((ChromeDriver) webDriver).findElementByXPath("//*[@id=\"password\"]");
        WebElement login = ((ChromeDriver) webDriver).findElementByXPath("/html/body/broker-root/div/main/broker-login/div/section/broker-login-form/div/div[1]/div/form/div[3]/button");
        username.sendKeys("TAMIDOYLE");
        password.sendKeys("L6ba0137");
        login.click();
        //Actions actions = new Actions(webDriver);
        //actions.moveToElement(login).click().perform();
        //Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        System.out.println(webDriver.getTitle());
        webDriver.get("https://agentviewcigna.com/GasbAgent/Home.aspx");
        wait.until(d -> d.getTitle().indexOf("AgentView") > -1);
        WebElement identify = ((ChromeDriver) webDriver).findElementById("ctl00_lblagentname");
        //WebElement name = ((ChromeDriver)webDriver).findElementByXPath("//*[@id=\"components-table-demo-editable-rows\"]/section[1]/div/div/div/div/div/div/table/tbody/tr[1]/td[1]/div");
        //webDriver.get("https://agentviewcigna.com/GasbAgent/Home.aspx");
        System.out.println(identify.getText());
        return "demo: " + webDriver.getTitle();
    }

    @GetMapping("/demo2")
    public String echo2() throws Exception {
        WebDriver driver = pool.getWebDriverFromPool();
        driver.get("https://news.ycombinator.com/login?goto=news");

        // Search for username / password input and fill the inputs
        driver.findElement(By.xpath("//input[@name='acct']")).sendKeys("achui_1980");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("12345678");

        // Locate the login button and click on it
        driver.findElement(By.xpath("//input[@value='login']")).click();
//        pool.getWebDriverFactory().getWait().until(d -> !d.getCurrentUrl().contains("login"));
//        if(driver.getCurrentUrl().equals("https://news.ycombinator.com/login")){
//            System.out.println("Incorrect credentials");
//            driver.quit();
//            System.exit(1);
//        }else{
//            System.out.println("Successfuly logged in");
//        }
        System.out.println("Successfuly logged in");
        // Take a screenshot of the current page
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //FileUtils.copyFile(screenshot, new File("screenshot.png"));

        // Logout
        //driver.findElement(By.id("logout")).click();
        return "demo: " + driver.getTitle();
    }
}
