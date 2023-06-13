package com.hzlx.filter;

import com.hzlx.component.BgmsConfig;
import com.hzlx.entity.UserInfo;
import com.hzlx.utils.PropertiesUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/api/*"})
public class Filter_A_CheckSession implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //将 servletRequest 转为 HttpServletRequest对象，方便获取session对象
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (filterRequest(request)){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            UserInfo userInfo = (UserInfo) request.getSession().getAttribute(BgmsConfig.SESSION_USER_KEY);
            if (userInfo==null){
                //用户没有登录，重定向到登录页
                response.sendRedirect("/background_management_system/");
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    /**
     * 过滤请求
     * @return 结果为ture 就放行 为false就拦截
     * 匹配url如果配置文件内有就放行，没有就拦截
     */
    private  boolean filterRequest(HttpServletRequest request){
        PropertiesUtil.load("config");
        String excludeurls=PropertiesUtil.getValue("exclude.urls");
        String[] urls = excludeurls.split(",");
        for (String url : urls) {
            if (request.getRequestURI().equals(request.getContextPath()+url)) {
                return true;
            }
        }
        return false;
    }
}