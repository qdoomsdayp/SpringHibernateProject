package com.stage.dao;


import com.stage.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wital on 03.02.2017.
 */
@Repository("userDao")
public class UserDAOImpl implements UserDAO {
private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

private SessionFactory sessionFactory;


 public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
        logger.info("User ADD: " + user);
    }


    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
        logger.info("User UPDATE: " + user);
    }


    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class,new Integer(id));
        if (user!=null){
            session.delete(user);
        }
        logger.info("User DELETE: " + user);
    }


    public User getUserId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class,new Integer(id));
        logger.info("User GET: " + user);
        return user;
    }


    //@Transactional
    public List<User> listUser() {
        @SuppressWarnings("unchecked")
        List<User> listUser = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

        return listUser;
    }
}
