package DAO.Ticket;

import BusinessLogic.Ticket.AbstractTicket;
import BusinessLogic.User.AbstractUser;

import java.util.List;

public interface TicketDAO {

    List<AbstractTicket> getAllTickets();
    List<AbstractTicket> getMyTickets(AbstractUser user);
    AbstractTicket getTicketById(int id);
    boolean delete(int id);
    boolean save(AbstractTicket t);
    boolean update(AbstractTicket ticket);
}
