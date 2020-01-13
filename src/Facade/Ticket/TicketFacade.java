package Facade.Ticket;

import BusinessLogic.Ticket.AbstractTicket;
import BusinessLogic.User.AbstractUser;
import DAO.MySQLDAOFactory;
import DAO.Ticket.TicketDAO;

import java.util.ArrayList;
import java.util.List;

public class TicketFacade implements ITicketFacade {
    private List<AbstractTicket> tickets;
    private TicketDAO dao;
    public static TicketFacade instance;


    private TicketFacade(){
        dao = MySQLDAOFactory.getInstance().getTicketDAO();
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

    @Override
    public boolean answer(AbstractTicket ticket, String answer) {
        ticket.setAnswer(answer);
        if(dao.update(ticket)){
            int i = 0;
            while(this.tickets.get(i).getId() != ticket.getId()){
                i++;
            }
            this.tickets.set(i, ticket);
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
