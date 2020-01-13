package DAO.Ticket;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;
import BuisnessLogic.User.AbstractUser;
import BuisnessLogic.User.GlobalUser;
import BuisnessLogic.User.User;
import DAO.MySQLConnector;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketDAOMySQL implements TicketDAO {

    private IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();

    private static final String INSERT = "INSERT INTO ticket (subject, problem, dateCreation, creator) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE ticket SET subject=?, problem=?, creator=?, answer=? WHERE idTicket=?";
    private static final String DELETE = "DELETE FROM ticket WHERE idTicket=?";
    private static final String ALL = "SELECT * from ticket";
    private static final String MYTICKET = "SELECT * from ticket where creator=?";
    private static final String TICKETBYID = "SELECT * from ticket where idTicket=?";

    @Override
    public List<AbstractTicket> getAllTickets() {
        List<AbstractTicket> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                AbstractUser user = userFacade.getUserById(rs.getInt("creator"));

                Ticket ticket = new Ticket(
                        rs.getInt("idTicket"),
                        rs.getString("subject"),
                        rs.getDate("dateCreation").toLocalDate(),
                        rs.getString("problem"),
                        (User) user,
                        rs.getString("answer")
                );

                list.add(ticket);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }


    @Override
    public List<AbstractTicket> getMyTickets(AbstractUser user) {
        List<AbstractTicket> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(MYTICKET);
            ps.setInt(1,user.getId());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                Ticket ticket = new Ticket(
                        rs.getInt("idTicket"),
                        rs.getString("subject"),
                        rs.getDate("dateCreation").toLocalDate(),
                        rs.getString("problem"),
                        (User) user,
                        rs.getString("answer")
                );

                list.add(ticket);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public AbstractTicket getTicketById(int id) {
        Ticket ticket = null;
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(TICKETBYID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                AbstractUser user = userFacade.getUserById(rs.getInt("creator"));

                 ticket = new Ticket(
                        rs.getInt("idTicket"),
                        rs.getString("subject"),
                        rs.getDate("dateCreation").toLocalDate(),
                        rs.getString("problem"),
                         (User) user,
                        rs.getString("answer")
                );
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticket;
    }


    @Override
    public boolean update(AbstractTicket ticket) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setString(1, ticket.getSubject());
            ps.setString(2, ticket.getProblem());
            ps.setInt(3, ticket.getCreator().getId());
            ps.setString(4, ticket.getAnswer());
            ps.setInt(5, ticket.getId());
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(AbstractTicket t) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, t.getSubject());
            ps.setString(2, t.getProblem());
            ps.setDate(3, java.sql.Date.valueOf(t.getDateCreation()));
            ps.setInt(4, t.getCreator().getId());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
            ps.setInt(1, id);
            int i = ps.executeUpdate();
            ps.close();
            if (i > 0) {
                return true;
            } else {

                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }



    public static void main(String[] args) {
        TicketDAOMySQL sql = new TicketDAOMySQL();
        List<AbstractTicket> list =sql.getAllTickets();
        for(AbstractTicket a : list){
            System.out.println(a.getProblem());
        }

    }
}
