package com.tentelemed.jsftest1.web.action;

import com.tentelemed.jsftest1.business.User;
import com.tentelemed.jsftest1.service.UserService;
import com.tentelemed.jsftest1.web.model.DynaFormBuilder;
import com.tentelemed.jsftest1.web.model.LazyUserModel;
import org.apache.log4j.Logger;
import org.primefaces.component.outputpanel.OutputPanel;
import org.primefaces.context.RequestContext;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 11/02/13
 * Time: 22:00
 */
@Controller
@Scope("session")
public class PrivatePage {
    Logger log = Logger.getLogger(PrivatePage.class);

    User selectedUser;
    String currentPanel = "editUser.xhtml";

    @Autowired
    UserService service;

    @Autowired
    LazyUserModel userModel;

    public LazyDataModel<User> getUserModel() {
        return userModel;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        if (!Objects.equals(this.selectedUser, selectedUser)) {
            this.selectedUser = selectedUser;
            refresh();
        }
    }

    public User getUser() {
        return service.getCurrentUser();
    }

    public String getActivePage() {
        return currentPanel;
    }

    public void panel1() {
        currentPanel = "editUser.xhtml";
        refresh();
    }

    public void panel2() {
        currentPanel = "editUserManual.xhtml";
        refresh();
    }

    public void refresh() {
        RequestContext.getCurrentInstance().update("refresh");
    }
}