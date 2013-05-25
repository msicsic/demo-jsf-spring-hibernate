package com.tentelemed.jsftest1.web;

import com.tentelemed.jsftest1.service.InitBean;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 11/02/13
 * Time: 23:43
 */
public class InitServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext()).getBean(InitBean.class).initDB();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
