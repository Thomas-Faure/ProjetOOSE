package DAO.Ticket;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketDAOMySQL implements TicketDAO {

    private static final String INSERT = "INSERT INTO ticket (subject, problem, status, dateCreation, creator) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE ticket SET subject=?, problem=?, status=?, creator=?, answer=? WHERE id=?";
    private static final String DELETE = "DELETE FROM ticket WHERE id=?";
    private static final String ALL = "SELECT * from ticket";
    private static final String TICKETBYID = "SELECT * from ticket where id=?";

    @Override
    public List<AbstractTicket> getAllTickets() {
        List<AbstractTicket> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);


            ResultSet rs = ps.executeQuery();
            while(rs.next()){


                Ticket ticket = new Ticket(
                        rs.getInt("id"),
                        rs.getString("subject"),
                        rs.getBoolean("status"),
                        rs.getDate("dateCreation").toLocalDate(),
                        rs.getString("problem"),
                        null,
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
    public boolean save(AbstractTicket t) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, t.getSubject());
            ps.setString(2, t.getProblem());
            ps.setBoolean(3, t.getStatus());
            ps.setDate(4, java.sql.Date.valueOf(t.getDateCreation()));
            ps.setInt(5, t.getCreator().getId());
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
