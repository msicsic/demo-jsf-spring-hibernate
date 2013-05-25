package com.tentelemed.jsftest1.business;

import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 12/02/13
 * Time: 21:10
 */
@Entity
public class Center extends BaseBO {
    String name;

    @Override
    public String toString() {
        return getName().toLowerCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
