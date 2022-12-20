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
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class AuthenticateServlet extends HttpServlet {

    UserService service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
        this.mapper = new ObjectMapper();
    }


    //authenticate username
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        while(reader.ready()) {
            json.append(reader.readLine());
        }

        User user = mapper.readValue(json.toString(), User.class);
        try {
            User authenticatedUser = service.authenticate(user);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(authenticatedUser));
            //Cookie authCookie = new Cookie("userId", user.getUserId().toString());
            //resp.addCookie(authCookie);

        } catch(UserNotFoundException e) {
            resp.getWriter().print("Username not recognized");
            resp.setStatus(401);
        } catch(IncorrectPasswordException e) {
            resp.getWriter().print("Wrong password");
            resp.setStatus(401);
        }



    }


}
