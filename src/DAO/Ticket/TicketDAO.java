package DAO.Ticket;

import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.User.AbstractUser;

import java.util.List;

public interface TicketDAO {

    List<AbstractTicket> getAllTickets();
    List<AbstractTicket> getMyTickets(AbstractUser user);
    boolean delete(int id);
    boolean save(AbstractTicket t);
}
