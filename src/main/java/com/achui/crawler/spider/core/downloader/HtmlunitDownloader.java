package com.achui.crawler.spider.core.downloader;

import com.achui.crawler.spider.core.Request;
import com.achui.crawler.spider.core.SpiderPage;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;

/**
 * @author portz
 * @date 2020/8/18 16:31
 */
public class HtmlunitDownloader implements Downloader {


    public WebClient getWebClient(boolean flag) {


        WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.getOptions().setAppletEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(flag);
        webClient.getOptions().setTimeout(60000);
        webClient.getOptions().setPrintContentOnFailingStatusCode(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        return webClient;
    }

    @Override
    public SpiderPage download(Request request) throws Exception {
        WebClient webClient = getWebClient(false);
        SpiderPage spiderPage = new SpiderPage();
        spiderPage.setWebClient(webClient);
        return spiderPage;
    }
}
