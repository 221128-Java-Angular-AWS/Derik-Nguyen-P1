package com.revature.service;

import com.revature.persistence.TicketDao;
import com.revature.pojos.Ticket;

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

        public void updateTicket(Ticket ticket) {
            dao.update(ticket);
        }

        public void deleteTicket(Integer id) {
            dao.delete(id);
        }



}
