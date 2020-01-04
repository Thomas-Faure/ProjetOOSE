package DAO.Idea;

import BuisnessLogic.Idea.AbstractIdea;
import BuisnessLogic.Idea.Idea;
import BuisnessLogic.User.User;
import DAO.MySQLConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class IdeaDAOMySQL implements IdeaDAO {

	private static final String INSERT = "INSERT INTO idea (name, description, subject, creator, state) VALUES (?, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE idea SET name=?, description=?, subject=?,  state=? WHERE id=?";
	private static final String DELETE = "DELETE FROM idea WHERE id=?";
	private static final String ALL = "SELECT * from idea";
	private static final String IDEABYID = "SELECT * from idea where id=?";

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
				 			  result.getInt("id"),
						      result.getString("name"),
		    		          result.getString("description"),
		    		          result.getString("subject"),
							  new User()
							  );
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return idea;
	}

	@Override
	public boolean save(AbstractIdea idea) {
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(INSERT);
			ps.setString(1, idea.getName());
			ps.setString(2, idea.getDescription());
			ps.setString(3, idea.getSubject());
			ps.setInt(4, idea.getCreator().getId());
			ps.setString(5, idea.getState());
			ps.executeUpdate();
			ps.close();


			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(AbstractIdea idea) {

		try {

			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
			ps.setString(1, idea.getName());
			ps.setString(2, idea.getDescription());
			ps.setString(3, idea.getSubject());
			//System.out.println(idea.getCreator());
			//ps.setInt(4, idea.getCreator().getId());
			ps.setString(4, idea.getState());
			ps.setInt(5, idea.getId());

			int i = ps.executeUpdate();
			ps.close();
			return i > 0;

		} catch (SQLException e) {
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
			return i > 0;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<AbstractIdea> getAllIdeas() {

		List<AbstractIdea> list = new ArrayList<>();
		try {

			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				Idea idea = new Idea(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("subject"),
						null
				);
				list.add(idea);
			}
			ps.close();
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
		return list;
	}

	@Override
	public AbstractIdea getIdeaById(int id) {
		Idea idea = null;
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(IDEABYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){

				idea = new Idea(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("subject"),
						new User(3,"lauren","unq","unq","unq"));
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idea;
	}

	//Peut être non nécessaire
	@Override
	public List<AbstractIdea> getIdeaByName(String name) {
		return null;
	}


}
