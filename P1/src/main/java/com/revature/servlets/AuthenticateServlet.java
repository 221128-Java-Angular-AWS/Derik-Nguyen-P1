package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.persistence.UserDao;
import com.revature.pojos.User;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticateServlet extends HttpServlet {

    UserService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
        this.mapper = new ObjectMapper();
    }


    //authenticate is throwing a 500 error
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = mapper.readValue(req.getInputStream(), User.class);
        try {
            User authenticatedUser = service.authenticate(user);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(authenticatedUser));
            Cookie authCookie = new Cookie("user_id", user.getUserId().toString());

        } catch (UserNotFoundException e) {
            resp.setStatus(401);
        } catch (IncorrectPasswordException e) {
            resp.setStatus(401);
        }


    }


}
