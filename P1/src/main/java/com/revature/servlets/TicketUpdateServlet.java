package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.persistence.TicketDao;
import com.revature.pojos.Ticket;
import com.revature.pojos.User;
import com.revature.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Set;

public class TicketUpdateServlet extends HttpServlet {
    private TicketService service;
    private ObjectMapper mapper;


    @Override
    public void init() throws ServletException {
        this.service = new TicketService(new TicketDao());
        this.mapper = new ObjectMapper();


    }

    //gets all the tickets that are still pending
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Ticket> tickets = service.getAllTickets();
        String json = mapper.writeValueAsString(tickets);
        resp.getWriter().println(json);
        resp.setStatus(200);
    }

    //updates a single ticket
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder json = new StringBuilder();
        while(reader.ready()) {
            json.append(reader.readLine());
        }

        Ticket ticket = mapper.readValue(json.toString(), Ticket.class);
        service.updateTicket(ticket);

        resp.setStatus(201);
    }
}
