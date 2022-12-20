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


public class UserUpdateServlet  extends HttpServlet {

    private UserService service;
    private ObjectMapper mapper;

    public void init() throws ServletException {
        this.service = new UserService(new UserDao());
        this.mapper = new ObjectMapper();


    }

    //Get a single user
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("user_id"));
        User user = service.getUser(id);
        String json = mapper.writeValueAsString(user);
        resp.getWriter().println(json);
        resp.setStatus(200);
    }


    //Updates a user
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        while(reader.ready()) {
            json.append(reader.readLine());
        }

        User user = mapper.readValue(json.toString(), User.class);
        try {
            service.update(user);
        } catch (UsernameTaken e) {
            resp.getWriter().print("Username already taken");
            resp.setStatus(401);
        }

        resp.setStatus(201);
    }
}
