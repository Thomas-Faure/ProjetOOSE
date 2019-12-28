package Facade;

import BuisnessLogic.Ticket.AbstractTicket;
import DAO.MySQLDAOFactory;
import DAO.TicketDAO;

import java.util.ArrayList;
import java.util.List;

public class TicketFacade implements ITicketFacade {
    private List<AbstractTicket> tickets;
    private TicketDAO dao;
    public static TicketFacade instance;


    private TicketFacade(){
        dao = MySQLDAOFactory.getTicketDAO();
        this.tickets = new ArrayList<>();
    }

    public static TicketFacade getInstance(){
        if(instance == null){
            instance =new TicketFacade();
        }
        return instance;
    }

    public boolean getAllTickets() {
        this.tickets = dao.getAllTickets();
        return true;
    }

    public List<AbstractTicket> getListTickets() {
        return this.tickets;
    }
}
