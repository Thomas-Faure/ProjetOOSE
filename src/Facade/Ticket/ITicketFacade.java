package Facade.Ticket;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Ticket.AbstractTicket;

import java.util.List;

public interface ITicketFacade {

     boolean getAllTickets();
    List<AbstractTicket> getListTickets();
    boolean deleteTicket(AbstractTicket ticket);
}
