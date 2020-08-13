package com.achui.crawler.controller;

import com.achui.crawler.util.WebDriverPool;
import lombok.extern.slf4j.Slf4j;
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
        return "a";
    }
}
