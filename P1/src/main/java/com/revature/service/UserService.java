package com.revature.service;

import com.revature.persistence.UserDao;
import com.revature.pojos.User;

import java.util.Set;

public class UserService {
    private UserDao dao;
    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public void registerNewUser(User user) {
        dao.create(user);
    }

    public Set<User> getAllUsers() {
        return dao.getAllUsers();
    }

}
