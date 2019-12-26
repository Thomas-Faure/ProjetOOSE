package DAO;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOMySQL implements TicketDAO {

    private static final String INSERT = "INSERT INTO ticket (subject, problem, status, dateCreation, creator, answer) VALUES (?, ?, ?, ?, ?, ?)";
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
                        rs.getDate("dateCreation"),
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
}
