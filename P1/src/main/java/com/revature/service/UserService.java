package com.revature.service;

import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UsernameTaken;
import com.revature.persistence.UserDao;
import com.revature.pojos.User;

import java.util.Set;

public class UserService {
    private UserDao dao;
    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public void registerNewUser(User user) throws UsernameTaken {
        dao.create(user);
    }

    public Set<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public User getUser (Integer userId) { return dao.getUser(userId); }

    public User authenticate(User user) throws UserNotFoundException, IncorrectPasswordException {
        return dao.authenticate(user.getUsername(), user.getPassword());
    }

    public void update(User user) throws UsernameTaken {
        dao.update(user);
    }

    public void delete(User user) {
        dao.delete(user);
    }
}
