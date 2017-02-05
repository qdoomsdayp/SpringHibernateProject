package com.stage.dao;

import com.stage.model.User;

import java.util.List;

/**
 * Created by wital on 03.02.2017.
 */
public interface UserDAO {
    public void addUser(User user);

    public void updateUser(User user);

    public void removeUser(int id);

    public User getUserId(int id);

    public List<User> listUser();
}
