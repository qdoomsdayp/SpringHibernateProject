package com.stage.service;

import com.stage.dao.UserDAO;
import com.stage.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wital on 03.02.2017.
 */
@Service("userService")

public class UserServiceImpl implements UserService {

    private UserDAO userDao;


    public UserServiceImpl(UserDAO userDao) {
        this.userDao = userDao;
    }




    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }


    @Transactional
    public void updateUser(User user) {
this.userDao.updateUser(user);
    }


    @Transactional
    public void removeUser(int id) {
this.userDao.removeUser(id);
    }


    @Transactional
    public User getUserId(int id) {

        return this.userDao.getUserId(id);
    }


    @Transactional
    public List<User> listUser() {
        return this.userDao.listUser();
    }
}
