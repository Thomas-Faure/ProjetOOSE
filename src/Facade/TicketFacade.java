package Facade;

import BuisnessLogic.Ticket.AbstractTicket;
import DAO.AbstractDAOFactory;

public class TicketFacade implements ITicketFacade {
    private AbstractTicket[] tickets;
    private AbstractDAOFactory daoFactory;
}
