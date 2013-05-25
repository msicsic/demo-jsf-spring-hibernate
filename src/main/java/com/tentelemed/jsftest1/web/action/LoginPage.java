package com.tentelemed.jsftest1.web.action;

import com.tentelemed.jsftest1.business.User;
import com.tentelemed.jsftest1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 10/02/13
 * Time: 15:41
 */

@ManagedBean
@Controller
@Scope("request")
public class LoginPage {
    @Autowired
    UserService userService;

    String login = "login1";
    String password = "password1";

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Transactional
    public String doLogin() {
        User user = userService.doLogin(login, password);
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Bad authentication", null));
            return null;
        } else {
            return "privatePage?faces-redirect=true";
        }
    }
}
