package DAO.Meeting;

import BuisnessLogic.Meeting.AbstractMeeting;
import BuisnessLogic.Meeting.Meeting;
import BuisnessLogic.Ticket.AbstractTicket;
import BuisnessLogic.Ticket.Ticket;
import DAO.Meeting.MeetingDAO;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;

public class MeetingDAOMySQL implements MeetingDAO {

    private static final String INSERT = "INSERT INTO meeting (date, place, idProject) VALUES (?, ?, ?)";
    private static final String UPDATE = "UPDATE meeting SET date=?, place=? WHERE id=?";
    private static final String DELETE = "DELETE FROM meeting WHERE id=?";
    private static final String ALL = "SELECT * from meeting";
    private static final String MEETINGBYIDPROJECT = "SELECT * from meeting where idProject=?";

    @Override
    public List<AbstractMeeting> getMeetingByProject(int idProject) {
        List<AbstractMeeting> list = new ArrayList<>();
        try {

            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(MEETINGBYIDPROJECT);
            ps.setInt(1,idProject);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){


                Meeting meeting = new Meeting(
                        rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("place"),
                        rs.getInt("idProject")
                );

                list.add(meeting);
            }
            ps.close();
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        return list;
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

    @Override
    public boolean save(AbstractMeeting meeting) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setDate(1, java.sql.Date.valueOf(meeting.getDate().plusDays(1)));
            ps.setString(2, meeting.getPlace());
            ps.setInt(3, meeting.getIdProject());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(AbstractMeeting meeting) {
        try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setDate(1, java.sql.Date.valueOf(meeting.getDate()));
            ps.setString(2, meeting.getPlace());
            ps.setInt(3, meeting.getId());
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
}
