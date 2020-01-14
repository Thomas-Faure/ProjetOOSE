package Facade.Ticket;


import BusinessLogic.Ticket.AbstractTicket;
import BusinessLogic.User.AbstractUser;
import DAO.MySQLDAOFactory;
import DAO.Ticket.ITicketDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * Cette facade permet de gérer les tickets
 * @author Rémi Salmi
 */
public class TicketFacade implements ITicketFacade {
    private List<AbstractTicket> tickets;
    private ITicketDAO dao;
    public static TicketFacade instance;


    private TicketFacade(){
        dao = MySQLDAOFactory.getInstance().getTicketDAO();
        this.tickets = new ArrayList<>();
    }

    /**
     * Retourne l'instance de la facade
     * @author Rémi Salmi
     * @return instance de la facade
     */
    public static TicketFacade getInstance(){
        if(instance == null){
            instance =new TicketFacade();
        }
        return instance;
    }

    @Override
    /**
     * Permet de récupérer tous les tickets
     * @author Rémi Salmi
     */
    public boolean getAllTickets() {
        this.tickets = dao.getAllTickets();
        return true;
    }

    @Override
    /**
     * Permet de recupérer les tickets de l'user connecté
     * @author Rémi Salmi
     */
    public boolean getMyTickets(AbstractUser user) {
        this.tickets = dao.getMyTickets(user);
        return true;
    }



    @Override
    /**
     * Permet de créer un ticket
     * @author Rémi Salmi
     */
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
    /**
     * Permet de supprimer un ticket
     * @author Rémi Salmi
     */
    public boolean deleteTicket(AbstractTicket ticket) {
        if(dao.delete(ticket.getId())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    /**
     * Permet de répondre à un ticket
     * @author Rémi Salmi
     */
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

    /**
     * Permet de retourner la liste de ticket de l'instance
     * @author Rémi Salmi
     */
    public List<AbstractTicket> getListTickets() {
        return this.tickets;
    }

    @Override
    /**
     * Permet de récupérer un ticket par son id
     * @author Rémi Salmi
     */
    public AbstractTicket getTicketById(int id) {
        AbstractTicket ticket;
        ticket = dao.getTicketById(id);
        return ticket;
    }
}
