package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.persistence.TicketDao;
import com.revature.persistence.UserDao;
import com.revature.pojos.Ticket;
import com.revature.pojos.User;
import com.revature.service.TicketService;
import com.revature.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class TicketUpdateServlet extends HttpServlet {
    private TicketService service;
    private UserService uservice;
    private ObjectMapper umapper;
    private ObjectMapper tmapper;


    @Override
    public void init() throws ServletException {
        this.service = new TicketService(new TicketDao());
        this.uservice = new UserService(new UserDao());
        this.umapper = new ObjectMapper();
        this.tmapper = new ObjectMapper();


    }

    //gets all the tickets that are still pending
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Ticket> tickets = service.getAllTickets();
        String json = tmapper.writeValueAsString(tickets);
        resp.getWriter().println(json);
        resp.setStatus(200);
    }

    //updates a single ticket
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        StringBuilder json2 = new StringBuilder();

        json.append(reader.readLine());
        json.append(reader.readLine());
        json.append(reader.readLine());
        //json.append(reader.readLine());
        //System.out.println(json);

        json.deleteCharAt(json.lastIndexOf(","));
        json.append("}");
        System.out.println(json);
        //while(reader.ready()) {
        //json.append(reader.readLine());
        //}
        //System.out.println(json);

        json2.append('{');
        json2.append(reader.readLine());
        json2.append(reader.readLine());
        json2.append(reader.readLine());
        json2.append(reader.readLine());
        System.out.println(json2);
        Ticket ticket = tmapper.readValue(json2.toString(), Ticket.class);
        User user = umapper.readValue(json.toString(), User.class);
        try {
            User authenticatedUser = uservice.authenticate(user);
              if(authenticatedUser.getIsManager()) {
                service.updateTicket(ticket);
                resp.getWriter().println(umapper.writeValueAsString(authenticatedUser));
            } else {resp.getWriter().println("You don't have the credentials to approve/deny this.");
            }
            resp.setStatus(200);


        } catch(UserNotFoundException e) {
            resp.getWriter().print("Username not recognized");
            resp.setStatus(401);
        } catch(IncorrectPasswordException e) {
            resp.getWriter().print("Wrong password");
            resp.setStatus(401);
        }



    }
}
