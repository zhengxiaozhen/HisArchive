package com.ttwb.historyArchive.web.filter;


import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Classname SessionFilter
 * @Description
 * @Date 2019/2/11 22:08
 * @Created by zhoulq
 */

public class SessionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 不过滤的uri
        String[] notFilter = new String[]  {"/login","/json",".js",".css",".ico",".jpg",".png"};

        // 请求的uri
        String uri = request.getRequestURI();
        // 是否过滤
        boolean doFilter = true;


        //默认是rest风格
        for (String s : notFilter) {
            if (uri.indexOf(s) != -1) {
                // 如果uri中包含不过滤的uri，则不进行过滤
                doFilter = false;
                break;
            }
        }
        //如果有后缀  则不进行过滤
        if(uri.indexOf(".") > 0)
        {
            doFilter=false;
        }



        if (doFilter) {
            // 执行过滤
            // 从session中获取登录者实体
            Object obj = request.getSession().getAttribute("username");
            if (null == obj) {
                boolean isAjaxRequest = isAjaxRequest(request);
                if (isAjaxRequest) {
                    response.setCharacterEncoding("UTF-8");
                    response.sendError(HttpStatus.UNAUTHORIZED.value(), "您已经太长时间没有操作,请刷新页面");
                    return;
                }
                response.sendRedirect("/login");
                return;
            } else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        } else {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 判断是否为Ajax请求 <功能详细描述>
     *
     * @param request
     * @return 是true, 否false
     * @see [类、类#方法、类#成员]
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        if (header != null && "XMLHttpRequest".equals(header))
            return true;
        else
            return false;
    }
}