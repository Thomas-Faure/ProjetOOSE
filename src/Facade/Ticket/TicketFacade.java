package Facade.Ticket;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.User.AbstractUser;
import DAO.MySQLDAOFactory;
import DAO.Ticket.TicketDAO;

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

    @Override
    public boolean getAllTickets() {
        this.tickets = dao.getAllTickets();
        return true;
    }

    @Override
    public boolean getMyTickets(AbstractUser user) {
        this.tickets = dao.getMyTickets(user);
        return true;
    }



    @Override
    public boolean addTicket(AbstractTicket ticket){
        if(dao.save(ticket)){
            //on ajouter la nouvelle tache à la liste
            this.tickets.add(ticket);
            return true;
        }else {
            return false;
        }
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

    @Override
    public AbstractTicket getTicketById(int id) {
        AbstractTicket ticket;
        ticket = dao.getTicketById(id);
        return ticket;
    }
}
