package com.revature.service;

import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UserNotFoundException;
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

    public User authenticate(String username, String password) throws UserNotFoundException, IncorrectPasswordException {
        return dao.authenticate(username, password);
    }

    public void update(User user) {
        dao.update(user);
    }

    public void delete(User user) {
        dao.delete(user);
    }
}
