package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Announcement.Announcement;
import Idea.Idea;
import User.User;

public class IdeaDAOMySQL implements IdeaDAO {
	protected Connection connect = null;
	
	public IdeaDAOMySQL() {
		this.connect=MySQLConnector.getInstance().getConnection();
	}

	@Override
	public Idea createIdeaById(int id) {
		 Idea idea=null;
		    try {
		    String query = "SELECT * FROM idea WHERE id="+id;
		      ResultSet result = this.connect.createStatement(
			      ResultSet.TYPE_SCROLL_INSENSITIVE,
			      ResultSet.CONCUR_READ_ONLY).executeQuery(query);
		      if(result.first()) {
		    		  System.out.println("correct");
		    		  //à changer
		    		  idea= new Idea(    
		    		          result.getString("name"),
		    		          result.getString("description"),
		    		          result.getString("subject")); 
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return idea;
	}

	@Override
	public boolean save() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

}
