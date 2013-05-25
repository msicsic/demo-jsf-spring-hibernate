package com.tentelemed.jsftest1.web.model;

import com.tentelemed.jsftest1.business.User;
import com.tentelemed.jsftest1.service.UserService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 12/02/13
 * Time: 00:33
 */
@Component
@Scope(value = "request")
public class LazyUserModel extends LazyDataModel<User> {

    @Autowired
    UserService service;

    @Override
    public User getRowData(String rowKey) {
        return service.findByID(rowKey);
    }

    @Override
    public Object getRowKey(User object) {
        return object.getId();
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        setRowCount(service.getNbUsers());
        List<User> result = service.getUsers(first, pageSize);
        return result;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        /*
         * The following is in ancestor (LazyDataModel):
         * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
         */
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        }
        else
            super.setRowIndex(rowIndex % getPageSize());
    }

}
