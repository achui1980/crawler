package com.achui.crawler.spider.example.github;

import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.PageProcessor;
import com.achui.crawler.spider.core.SpiderPage;
import com.google.common.collect.Lists;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Queue;

/**
 * @author portz
 * @date 2020/8/3 16:07
 */
public class GithubPageProcessor implements PageProcessor {
    private Queue<Request> requests = Lists.newLinkedList();
    @Override
    public void login(SpiderPage page) {
        WebDriver webDriver = page.getWebDriverEngine();
        String userName = "admin";
        String password = "D*u4HlX4P8ey";
        String userInput = "//*[@id=\"login_field\"]";
        String passwordInput = "//*[@id=\"password\"]";
        String loginBtn = "//*[@id=\"login\"]/form/div[4]/input[9]";
        WebElement usernameEle = ((ChromeDriver) webDriver).findElementByXPath(userInput);
        WebElement passwordEle = ((ChromeDriver) webDriver).findElementByXPath(passwordInput);
        WebElement loginEle = ((ChromeDriver) webDriver).findElementByXPath(loginBtn);
        usernameEle.sendKeys("achui_1980@163.com");
        passwordEle.sendKeys("achui_19800724");
        loginEle.click();
    }

    @Override
    public void processMainPage(SpiderPage page) throws Exception {
        WebDriver webDriver = page.getWebDriverEngine();
        //*[@id="repos-container"]/ul/li[1]
        List<WebElement> projectList = webDriver.findElements(By.xpath("//*[@id=\"dashboard-repos-container\"]/div[@id=\"repos-container\"]/ul/li"));
        for (WebElement element : projectList) {
            WebElement projectUri = element.findElement(By.cssSelector("div>a"));
            System.out.println(projectUri.getAttribute("href"));
            Request request = new Request();
            request.setUrl(projectUri.getAttribute("href"));
            request.setCallback(this);
            request.setOpenInNewTab(true);
            request.setPageHandler(new ProjectDetailHanlder());
            requests.offer(request);
        }
    }

    @Override
    public Queue<Request> getRequestQueue() {
        return requests;
    }


}
