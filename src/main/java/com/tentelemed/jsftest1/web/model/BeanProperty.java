package com.tentelemed.jsftest1.web.model;

import com.tentelemed.jsftest1.business.BaseBO;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 12/02/13
 * Time: 21:21
 */
public class BeanProperty implements Serializable {
    Logger log = Logger.getLogger(BeanProperty.class);

    private String name;
    private ModelDynaForm bean;
    private String path;
    private boolean required;
    private List<Object> values = new ArrayList<>();

    public BeanProperty(String name, boolean required, ModelDynaForm bean, String path, List<Object> values) {
        this.name = name;
        this.required = required;
        this.values = values;
        this.bean = bean;
        this.path = path;
    }

    public Object getValue() {
        try {
            return PropertyUtils.getProperty(bean, "model."+path);
        } catch (Exception e) {
            return null;
        }
    }

    public void setValue(Object value) {
        try {
            PropertyUtils.setProperty(bean, "model."+path, value);
        } catch (Exception e) {
            log.error(null, e);
        }
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<SelectItem> getItems() {
        List<SelectItem> result = new ArrayList<>();
        if (values != null) {
            for (Object value : values) {
                SelectItem item = new SelectItem();
                if (value == null) {
                    item = new SelectItem(null, "???");
                } else if (value instanceof BaseBO) {
                    BaseBO bo = (BaseBO) value;
                    item = new SelectItem(bo.getId(), bo.toString());
                } else if (value instanceof Enum) {
                    item = new SelectItem(value, value.toString());
                }
                result.add(item);
            }
        }
        return result;
    }
}
