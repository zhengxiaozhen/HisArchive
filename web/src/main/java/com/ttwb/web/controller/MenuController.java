package com.ttwb.web.controller;


import org.apache.shiro.SecurityUtils;
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
public class MenuController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


   //index返回用户信息
    @RequestMapping("/index")
    public ModelAndView showIndex() {
        ModelAndView mav = new ModelAndView("main");
        Object currentUserObj = SecurityUtils.getSubject().getPrincipal();
        if(currentUserObj!=null && !(currentUserObj instanceof String)) {
            //User currentUser = (User) currentUserObj;
            //mav.addObject("currentUser", currentUser);
        }
        return mav;
    }

    @RequestMapping("/")
    public ModelAndView showMain() {
        ModelAndView mav = new ModelAndView("main");
        Object currentUserObj = SecurityUtils.getSubject().getPrincipal();
        if(currentUserObj!=null && !(currentUserObj instanceof String)) {
           // User currentUser = (User) currentUserObj;
           // mav.addObject("currentUser", currentUser);
        }
        return mav;
    }

    @RequestMapping("/main.html")
    public ModelAndView main(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("main");
        return mav;
    }

    /**
     * 菜单主界面 moduleId 子系统Id resourceId 一级菜单Id
     * @return
     */


    @RequestMapping("/error403")
    public ModelAndView error403() {
        ModelAndView mav = new ModelAndView("menu/error403");
        return mav;
    }

    @RequestMapping("/error404")
    public ModelAndView error404() {
        ModelAndView mav = new ModelAndView("menu/error404");
        return mav;
    }

    @RequestMapping("/error405")
    public ModelAndView error405() {
        ModelAndView mav = new ModelAndView("menu/error405");
        return mav;
    }

    @RequestMapping("/error500")
    public ModelAndView error500() {
        ModelAndView mav = new ModelAndView("menu/error500");
        return mav;
    }

}
