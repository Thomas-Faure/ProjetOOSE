package DAO;

import BuisnessLogic.Idea.Idea;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IdeaDAOMySQL implements IdeaDAO {

	
	public IdeaDAOMySQL() {
	
	}

	@Override
	public Idea createIdeaById(int id) {
		 Idea idea=null;
		    try {
		    String query = "SELECT * FROM idea WHERE id="+id;
		      ResultSet result = MySQLConnector.getSQLConnection().createStatement(
			      ResultSet.TYPE_SCROLL_INSENSITIVE,
			      ResultSet.CONCUR_READ_ONLY).executeQuery(query);
		      if(result.first()) {
		    		  System.out.println("correct");
		    		  //� changer
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
