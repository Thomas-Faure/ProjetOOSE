package Facade;

import BuisnessLogic.Ticket.AbstractTicket;

import java.util.List;

public interface ITicketFacade {

     boolean getAllTickets();
    List<AbstractTicket> getListTickets();
}
