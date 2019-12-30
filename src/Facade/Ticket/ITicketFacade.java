package Facade.Ticket;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.User.AbstractUser;

import java.util.List;

public interface ITicketFacade {

     boolean getAllTickets();
    boolean getMyTickets(AbstractUser user);
    List<AbstractTicket> getListTickets();
    boolean addTicket(AbstractTicket ticket);
    boolean deleteTicket(AbstractTicket ticket);
}
