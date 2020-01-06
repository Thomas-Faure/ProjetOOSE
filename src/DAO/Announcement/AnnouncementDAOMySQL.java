package DAO.Announcement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BuisnessLogic.Announcement.AbstractAnnouncement;
import BuisnessLogic.Announcement.Announcement;
import BuisnessLogic.Task.AbstractTask;
import BuisnessLogic.User.User;

import DAO.MySQLConnector;


public class AnnouncementDAOMySQL implements AnnouncementDAO {



	private static final String INSERT = "INSERT INTO announcement (title, message, date, user) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE announcement SET title=?, message=?, date=?, user=? WHERE id=?";
    private static final String DELETE = "DELETE FROM announcement WHERE id=?";
	private static final String ALL = "SELECT * from announcement";
	private static final String ANNOUNCEMENTBYID = "SELECT * from announcement where id=?";
	private static final String ANNOUNCEMENTBYTITLE = "SELECT * from task where title=?";
	
	public AnnouncementDAOMySQL() {

	}

	@Override
	public boolean save(AbstractAnnouncement a) {
		try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getMessage());
            ps.setDate(3, java.sql.Date.valueOf( a.getDate() ));
            ps.setInt(4, a.getUser().getId());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (SQLException e) {
			e.printStackTrace();
            return false;
        }
	}

	@Override
	public boolean update(AbstractAnnouncement a) {
		try {
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getMessage());
            ps.setDate(3, java.sql.Date.valueOf( a.getDate() ));
            ps.setInt(4, a.getUser().getId());
            ps.setInt(5, a.getId());
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
	public List<AbstractAnnouncement> getAllAnnouncements() {
		List<AbstractAnnouncement> list = new ArrayList<>();
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				AbstractAnnouncement announcement = new Announcement(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("message"),
						rs.getDate("date").toLocalDate(),
						null
				);

				list.add(announcement);
			}
			ps.close();
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public AbstractAnnouncement getAnnouncementById(int id) {
		Announcement announcement = null;
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ANNOUNCEMENTBYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				announcement = new Announcement(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("message"),
						rs.getDate("date").toLocalDate(),
						new User(3,"thomas","faure","faure","faure"));
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return announcement;
	}

	@Override
	public List<AbstractAnnouncement> getAnnouncementByTitle(String title) {
		List<AbstractAnnouncement> announcements = null;
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ANNOUNCEMENTBYTITLE);
			ps.setString(1, title);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				announcements.add(new Announcement(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("message"),
						rs.getDate("date").toLocalDate(),
						new User(3,"thomas","faure","faure","faure")));
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return announcements;
	}
}
