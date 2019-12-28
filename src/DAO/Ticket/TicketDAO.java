package DAO;

import BuisnessLogic.Ticket.AbstractTicket;

import java.util.List;

public interface TicketDAO {

    List<AbstractTicket> getAllTickets();
    boolean delete(int id);
}
