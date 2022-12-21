package com.revature.service;

import com.revature.persistence.TicketDao;
import com.revature.pojos.Ticket;


import java.util.Set;


public class TicketService {
        private TicketDao dao;

        public TicketService(TicketDao dao) {
            this.dao = dao;
        }

        public void createNewTicket(Ticket ticket) {
            dao.create(ticket);
        }

        public Ticket getTicket(Integer ticketId) {
            return dao.read(ticketId);
        }

        public Set<Ticket> getAllTickets() {
        return dao.getAllTickets();
    }

        public void updateTicket(Ticket ticket) {
            dao.update(ticket);
        }

        public void deleteTicket(Integer id) {
            dao.delete(id);
        }



}
