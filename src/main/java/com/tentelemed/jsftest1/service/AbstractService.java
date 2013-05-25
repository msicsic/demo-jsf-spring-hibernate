package com.tentelemed.jsftest1.service;

import com.tentelemed.jsftest1.web.AppSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.web.context.ContextLoaderListener;


/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 12/02/13
 * Time: 00:03
 */
public class AbstractService {

    protected Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

    protected SessionFactory getSessionFactory() {
        return (SessionFactory) ContextLoaderListener.getCurrentWebApplicationContext().getBean("sessionFactory");
    }

    protected AppSession getAppSession() {
        return ContextLoaderListener.getCurrentWebApplicationContext().getBean(AppSession.class);
    }
}
