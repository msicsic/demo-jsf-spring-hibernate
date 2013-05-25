package com.tentelemed.jsftest1.web.model;

import com.tentelemed.jsftest1.business.BaseBO;
import com.tentelemed.jsftest1.business.User;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.helpers.LogLog;
import org.primefaces.extensions.component.dynaform.DynaForm;
import org.primefaces.extensions.model.dynaform.DynaFormControl;
import org.primefaces.extensions.model.dynaform.DynaFormLabel;
import org.primefaces.extensions.model.dynaform.DynaFormModel;
import org.primefaces.extensions.model.dynaform.DynaFormRow;
import org.springframework.stereotype.Component;
import sun.misc.CompoundEnumeration;

import javax.validation.constraints.NotNull;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 17/02/13
 * Time: 01:55
 */
@Component
public class DynaFormBuilder {
    static String[] skip = {"id", "class"};

    public <T extends BaseBO> ModelDynaForm<T> build(T bo) {
        ModelDynaForm<T> res = new ModelDynaForm<>();
        DynaFormModel model = new DynaFormModel();
        res.setModel(bo);
        res.setDynaForm(model);
        for (PropertyDescriptor desc : PropertyUtils.getPropertyDescriptors(bo)) {
            if (desc.getReadMethod() == null) continue;
            if (Arrays.asList(skip).contains(desc.getName())) continue;
            String name = desc.getName();
            Class type = desc.getPropertyType();
            DynaFormRow row = model.createRegularRow();
            String stype = "String";
            List<Object> values = null;
            if (String.class.isAssignableFrom(type)) {
                stype = "String";
            } else if (Date.class.isAssignableFrom(type)) {
                stype = "Date";
            } else if (BaseBO.class.isAssignableFrom(type)) {
                stype = "Link";
                try {
                    BaseBO link = (BaseBO) desc.getReadMethod().invoke(bo);
                    values = link==null?null:link.getAll(type);
                } catch (Exception e) {
                    LogLog.error(null, e);
                }
            } else if (Enum.class.isAssignableFrom(type)) {
                stype = "Enum";
                values = Arrays.asList(type.getEnumConstants());
            }
            boolean required = desc.getReadMethod().isAnnotationPresent(NotNull.class);
            DynaFormLabel label = row.addLabel(name, 1, 1);
            DynaFormControl control = row.addControl(new BeanProperty(name, required, res, desc.getName(), values), stype, 1, 1);
            label.setForControl(control);
        }
        return res;
    }

}
