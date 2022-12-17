package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.UsernameTaken;
import com.revature.persistence.UserDao;
import com.revature.pojos.User;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class UserServlet extends HttpServlet {
    private UserService service;
     private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
        this.mapper = new ObjectMapper();
    }

    //Gets all users in database
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<User> users = service.getAllUsers();
        String json = mapper.writeValueAsString(users);
        resp.getWriter().println(json);
        resp.setStatus(200);
    }

    //creates a new user in database
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder jsonBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while(reader.ready()) {
            jsonBuilder.append(reader.readLine());
        }


        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(jsonBuilder.toString(), User.class);
        try {
            service.registerNewUser(user);
        } catch (UsernameTaken e) {
            resp.getWriter().print("Username already taken");
            resp.setStatus(401);
        }

        System.out.println(user);

        resp.setStatus(201);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
