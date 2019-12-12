package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import Announcement.Announcement;
import Task.Task;
import User.User;

public class AnnouncementDAOMySQL implements AnnouncementDAO  {


	protected Connection connect = null;
	private static final String INSERT = "INSERT INTO announcement (title, message, date, user) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE announcement SET title=?, message=?, date=?, user=? WHERE id=?";
    private static final String DELETE = "DELETE FROM announcement WHERE id=?";
	
	public AnnouncementDAOMySQL() {

		this.connect=MySQLConnector.getInstance().getConnection();
	}
	@Override
	public Announcement createAnnouncementById(int id) {
		 Announcement announcement=null;
		    try {
		    String query = "SELECT * FROM announcement WHERE id="+id;
		      ResultSet result = this.connect.createStatement(
			      ResultSet.TYPE_SCROLL_INSENSITIVE,
			      ResultSet.CONCUR_READ_ONLY).executeQuery(query);
		      if(result.first()) {
		    		  System.out.println("correct");
		    		  //à changer
		    		  announcement= new Announcement( 
		    				  result.getInt("id"),
		    		          result.getString("title"),
		    		          result.getString("message"),
		    		          result.getDate("date").toLocalDate(),
		    		          new User()); 
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return announcement;
		
	}

	@Override
	public boolean save(Announcement a) {
		try {
			 
            PreparedStatement ps = connect.prepareStatement(INSERT);
 
            ps.setString(1, a.getTitle());
            ps.setString(2, a.getMessage());
            ps.setDate(3, java.sql.Date.valueOf( a.getDate() ));
            ps.setInt(4, a.getUser().getId());
            ps.executeUpdate();
            ps.close();
 
            System.out.println("Nouvel Announcement dans la base: " + a.toString());
            return true;
        } catch (SQLException e) {
            
        	System.out.println(e);
            return false;
        }
	}

	@Override
	public boolean update(Announcement a) {
		try {
			 
            PreparedStatement ps = connect.prepareStatement(UPDATE);

            ps.setString(1, a.getTitle());
            ps.setString(2, a.getMessage());
            ps.setDate(3, java.sql.Date.valueOf( a.getDate() ));
            ps.setInt(4, a.getUser().getId());
            ps.setInt(5, a.getId());
             
            ps.executeUpdate();
            ps.close();
 
            System.out.println("L'announcement " + a.getId() + " contient maintenant: " + a.toString());
 
            return true;
        } catch (SQLException e) {
           
            System.out.println(e);
            return false;
        }
	}
	@Override
    public void delete(int id) {
 
        try {
 
            PreparedStatement ps = connect.prepareStatement(DELETE);
 
            ps.setInt(1, id);
 
            ps.executeUpdate();
            ps.close();
 
            System.out.println("Announcement with id: " + id + " was sucesfully deleted from DB.");
 
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
 
    }
	
	public static void main(String[] args) {
		User user = new User(3,"thomas","faure","faure","faure");
		Announcement announcement = new Announcement(0,"commencement2","c'est le commencement",LocalDate.now(),user);
		AnnouncementDAOMySQL sql = new AnnouncementDAOMySQL();
		//sql.delete(0);
		sql.save(announcement);
	}

}
