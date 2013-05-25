package com.tentelemed.jsftest1.web.action;

import com.tentelemed.jsftest1.business.User;
import com.tentelemed.jsftest1.service.UserService;
import com.tentelemed.jsftest1.web.model.DynaFormBuilder;
import com.tentelemed.jsftest1.web.model.LazyUserModel;
import com.tentelemed.jsftest1.web.model.ModelDynaForm;
import org.primefaces.context.RequestContext;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 24/02/13
 * Time: 21:18
 */
@Controller
@Scope("session")
public class EditUserPanel {

    ModelDynaForm<User> model;

    @Autowired
    PrivatePage parent;

    @Autowired
    DynaFormBuilder builder;

    @Autowired
    UserService service;

    private User getSelectedUser() {
        return parent.getSelectedUser();
    }

    @Transactional
    public DynaFormModel getModel() {
        if (model == null || getSelectedUser() != null && ! getSelectedUser().equals(model.getModel())) {
            model = builder.build(parent.getSelectedUser() == null ? new User() : service.findByID(getSelectedUser().getId()));
        }
        return model.getDynaForm();
    }

    public void actionCreate() {
    }

    public boolean getActionCreateDisabled() {
        return false;
    }

    public void actionDelete() {
        service.delete(getSelectedUser());
        parent.setSelectedUser(null);
    }

    public boolean getActionDeleteDisabled() {
        return getSelectedUser() == null;
    }

    public void actionEdit() {
    }

    public boolean getActionEditDisabled() {
        return getSelectedUser() == null;
    }

    public void actionClearSelection() {
        parent.setSelectedUser(null);
    }

    public boolean getActionClearSelectionDisabled() {
        return getSelectedUser() == null;
    }

    // Attention : on ne peut pas utiliser "getSelectedUser()" car ce n'est plus la meme instance que celle
    // en cours de modification
    public void actionSave() {
        service.save(model.getModel());
    }

    public boolean getActionSaveDisabled() {
        return getSelectedUser() == null;
    }

    public void actionError() {
        /*FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", "Blah"));

        // TODO : voir pq ceci ne fonctionne pas
        RequestContext.getCurrentInstance().execute("PrimeFaces.info('Exception');");

        RequestContext.getCurrentInstance().update("form");*/
        throw new RuntimeException("ERROR");
    }

    public void execute(String action) {
        try {
            getClass().getMethod("action"+action.substring(0,1).toUpperCase() + action.substring(1)).invoke(this);
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("ERROR", e.getMessage()));
        } finally {
            RequestContext.getCurrentInstance().update("refresh");
        }
    }


}
