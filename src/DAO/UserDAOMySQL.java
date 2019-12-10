package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import user.User;

public class UserDAOMySQL implements UserDAO {

	protected Connection connect = null;
	
	public UserDAOMySQL() {
		MySQLConnector connector = new MySQLConnector();
		connector.openConnection();
		this.connect=connector.getConnection();
	}
	@Override
	public User createUser(String username, String password) { 
	     User user=null;
	    try {
	    String query = "SELECT * FROM user WHERE username =\""+username+"\" and password=\""+password+"\";";
	      ResultSet result = this.connect.createStatement(
		      ResultSet.TYPE_SCROLL_INSENSITIVE,
		      ResultSet.CONCUR_READ_ONLY).executeQuery(query);
	      if(result.first()) {
	    		  System.out.println("correct");
	    		  user= new User(    
	    		          result.getString("username"),
	    		          result.getString("firstname"),
	    		          result.getString("lastname")); 
	      }
	    } catch (SQLException e) {
	      e.printStackTrace();
	    }
	    return user;
	}

	//later
	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}
	//later
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
	



	
}
