package com.ttwb.historyArchive.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by user on 2016/8/12.
 */
@Controller
public class MainController {

    @RequestMapping("/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping("/video")
    public String showVideo() {
        return "video";
    }

}
