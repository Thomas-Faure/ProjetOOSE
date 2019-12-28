package Facade;

import BuisnessLogic.Announcement.AbstractAnnouncement;
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

    @Override
    public boolean deleteTicket(AbstractTicket ticket) {
        if(dao.delete(ticket.getId())){
            return true;
        }else {
            return false;
        }
    }

    public List<AbstractTicket> getListTickets() {
        return this.tickets;
    }
}
