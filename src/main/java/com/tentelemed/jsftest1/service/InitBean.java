package com.tentelemed.jsftest1.service;

import com.tentelemed.jsftest1.business.Center;
import com.tentelemed.jsftest1.business.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: Mael
 * Date: 11/02/13
 * Time: 22:49
 */
@Component
public class InitBean {
    Logger log = Logger.getLogger(InitBean.class);

    @Transactional
    public void initDB() {
        log.info("Init DB");
        Center center1 = new Center();
        center1.setName("Center 1");
        center1.save();
        Center center2 = new Center();
        center2.setName("Center 2");
        center2.save();

        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setCenter(i%2==0?center1:center2);
            user.setFirstName("firstName" + i);
            user.setLastName("lastName" + i);
            user.setLogin("login" + i);
            user.setDob(new Date());
            user.setPassword("password" + i);
            user.setSex(i%2==0? User.Sex.MALE: User.Sex.FEMALE);
            user.save();
        }
        log.info("DB initialized");
    }

}
