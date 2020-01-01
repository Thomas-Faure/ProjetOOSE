package DAO.Ticket;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.User.AbstractUser;

import java.util.List;

public interface TicketDAO {

    List<AbstractTicket> getAllTickets();
    List<AbstractTicket> getMyTickets(AbstractUser user);
    AbstractTicket getTicketById(int id);
    boolean delete(int id);
    boolean save(AbstractTicket t);
    boolean update(AbstractTicket ticket);
}
