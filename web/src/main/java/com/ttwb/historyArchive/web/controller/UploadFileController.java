package com.ttwb.historyArchive.web.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by user on 2016/8/12.
 */
@Controller
public class UploadFileController
{

    private final Logger logger = LoggerFactory.getLogger(getClass());



    /**
     * 返回登陆界面
     * @return
     */
    @RequestMapping("/uploadFile")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("daxt/uploadFile");
        return mav;
    }


}
