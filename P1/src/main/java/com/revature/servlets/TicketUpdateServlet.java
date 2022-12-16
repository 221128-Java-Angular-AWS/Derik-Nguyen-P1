package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.persistence.TicketDao;
import com.revature.pojos.Ticket;
import com.revature.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class TicketUpdateServlet extends HttpServlet {
    private TicketService service;
    private ObjectMapper mapper;
    //private StringBuilder jsonBuilder;

    @Override
    public void init() throws ServletException {
        this.service = new TicketService(new TicketDao());
        this.mapper = new ObjectMapper();
        //this.jsonBuilder = new StringBuilder();

    }

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
