package com.tentelemed.jsftest1.web;

import com.tentelemed.jsftest1.business.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 11/02/13
 * Time: 23:02
 */
@Component
@Scope(value = "session")
public class AppSession {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
