package com.tentelemed.jsftest1.web.model;

import com.tentelemed.jsftest1.business.BaseBO;
import org.primefaces.extensions.model.dynaform.DynaFormModel;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 24/02/13
 * Time: 21:33
 */
public class ModelDynaForm<T extends BaseBO> {
    DynaFormModel dynaForm;
    T model;

    public DynaFormModel getDynaForm() {
        return dynaForm;
    }

    public void setDynaForm(DynaFormModel dynaForm) {
        this.dynaForm = dynaForm;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
