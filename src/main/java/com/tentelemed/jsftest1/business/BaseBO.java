package com.tentelemed.jsftest1.business;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.web.context.ContextLoaderListener;

import javax.persistence.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 10/02/13
 * Time: 18:58
 */
@MappedSuperclass
public class BaseBO {

    private String id;

    public BaseBO() {
    }

    @Id
    @GenericGenerator(
            name = "id_generator",
            strategy = "com.tentelemed.jsftest1.business.EntityIDGenerator",
            parameters = {
                    @Parameter(name = "table", value = "T_HIBERNATE_UNIQUE_KEY"),
                    @Parameter(name = "column", value = "NEXT_HI"),
                    @Parameter(name = "max_lo", value = "100")
            }
    )
    @GeneratedValue(generator = "id_generator")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    @Transient
//    protected EntityManager getEntityManager() {
//        EntityManagerHolder holder = (EntityManagerHolder) ContextLoaderListener.getCurrentWebApplicationContext().getBean("emholder");
//        return holder.getEntityManager();
//    }


//    @Transient
//    protected static EntityManager getSessionFactory() {
//        return (EntityManager) ContextLoaderListener.getCurrentWebApplicationContext().getBean("sessionFactory");
//    }

    @Transient
    protected static Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Transient
    protected static SessionFactory getSessionFactory() {
        return (SessionFactory) ContextLoaderListener.getCurrentWebApplicationContext().getBean("sessionFactory");
    }

    public void delete() {
        getSession().delete(this);
    }

    public void save() {
        if (this.isPersistent()) {
            getSession().merge(this);
        } else {
            getSession().saveOrUpdate(this);
        }
    }

    @Transient
    private boolean isPersistent() {
        return this.getId() != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseBO)) return false;

        BaseBO baseBO = (BaseBO) o;

        if (this.getId() == null || baseBO.getId() == null) {
            return super.equals(baseBO);
        }
        return this.getId().equals(baseBO.getId());
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : super.hashCode();
    }

    public List<Object> getAll(Class<? extends BaseBO> type) {
        return getSession().createQuery("from " + type.getSimpleName()).list();
    }
}
