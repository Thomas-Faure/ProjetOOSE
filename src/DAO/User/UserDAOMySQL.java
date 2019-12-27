package DAO.User;

import BuisnessLogic.User.User;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOMySQL implements UserDAO {

	
    private static final String INSERT = "INSERT INTO user (username, firstname, lastname, password) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET username=?, firstName=?, lastName=?, password=? WHERE id=?";
    private static final String DELETE = "DELETE FROM user WHERE id=?";

    public UserDAOMySQL() {
    	
	}
	@Override
	public User createUser(String username, String password) {
	    User user=null;
	    try {
	    String query = "SELECT * FROM user WHERE username =\""+username+"\" and password=\""+password+"\";";
	      ResultSet result = MySQLConnector.getSQLConnection().createStatement(
		      ResultSet.TYPE_SCROLL_INSENSITIVE,
		      ResultSet.CONCUR_READ_ONLY).executeQuery(query);
	      if(result.first()) {
	    		  System.out.println("correct");
	    		  user= new User( result.getInt("id"),
	    		          result.getString("username"),
	    		          result.getString("firstname"),
	    		          result.getString("lastname"),
	    				  result.getString("password"));
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return user;
	}

	
	@Override
	public boolean save(User user) {
		try {
			 
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
 
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.executeUpdate();
            ps.close();
 
            System.out.println("Nouvel utilisateur dans la base: " + user.toString());
            return true;
        } catch (SQLException e) {
            
        	System.out.println(e);
            return false;
        }
	}
	
	@Override
	public boolean update(User user) {
		try {
			 
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getId());
             
            ps.executeUpdate();
            ps.close();
 
            System.out.println("L'utilisateur " + user.getId() + " contient maintenant: " + user.toString());
 
            return true;
        } catch (SQLException e) {
           
            System.out.println(e);
            return false;
        }
		
	}
	@Override
    public void delete(int id) {
 
        try {
 
            PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(DELETE);
 
            ps.setInt(1, id);
 
            ps.executeUpdate();
            ps.close();
 
            System.out.println("User with id: " + id + " was sucesfully deleted from DB.");
 
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new RuntimeException(e);
        }
 
    }

	
	public static void main(String[] args) {
		User user = new User(3,"toto2","first","last","password");
		UserDAOMySQL sql = new UserDAOMySQL();
		sql.update(user);
			
			
		
	}
	



	
}
