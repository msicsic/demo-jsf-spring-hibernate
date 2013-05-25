package com.tentelemed.jsftest1.service;

import com.tentelemed.jsftest1.business.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 11/02/13
 * Time: 00:33
 */
@Service
@Transactional
public class UserService extends AbstractService {

    public User doLogin(String login, String password) {
        User user = findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            getAppSession().setUser(user);
            return user;
        }
        return null;
    }

    public User getCurrentUser() {
        return getAppSession().getUser();
    }

    public User findByID(String id) {
        return (User) getSession().createQuery("from User u where u.id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public User findByLogin(String login) {
        return (User) getSession().createQuery("from User u where u.login=:login")
                .setParameter("login", login)
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<User> getUsers(int first, int pageSize) {
         return getSession().createQuery("from User u left join fetch u.center order by u.id")
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .list();
    }

    public void save(User user) {
        user.save();
    }

    public void delete(User user) {
        if (user != null) {
            user.delete();
        }
    }

    public int getNbUsers() {
        return ((Long)getSession().createQuery("select count(*) from User")
                .uniqueResult()
        ).intValue();
    }
}
