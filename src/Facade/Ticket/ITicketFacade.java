package Facade.Ticket;

import BusinessLogic.Ticket.AbstractTicket;
import BusinessLogic.User.AbstractUser;

import java.util.List;

public interface ITicketFacade {

     boolean getAllTickets();
    boolean getMyTickets(AbstractUser user);
    List<AbstractTicket> getListTickets();
    AbstractTicket getTicketById(int id);
    boolean addTicket(AbstractTicket ticket);
    boolean deleteTicket(AbstractTicket ticket);
    boolean answer(AbstractTicket ticket,String answer);
}
