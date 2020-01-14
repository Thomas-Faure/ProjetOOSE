package DAO.Idea;

import BusinessLogic.Idea.AbstractIdea;
import BusinessLogic.Idea.Idea;
import BusinessLogic.User.AbstractUser;
import DAO.MySQLConnector;
import Facade.Session.SessionFacade;
import Facade.User.GlobalUser.GlobalUserFacade;
import Facade.User.GlobalUser.IGlobalUserFacade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lauren Unquera - Polytech Montpellier IG4
 * @Description Cette Classe correspond au DAO qui gère les idées.
 * IL est en lien avec la base de données sur laquelle il fait des
 * requetes pour récupérer des données.
 */
public class IdeaDAOMySQL implements IIdeaDAO {

	private static final String INSERT = "INSERT INTO idea (name, description, subject, idUser, state) VALUES (?, ?, ?, ?,?)";
	private static final String UPDATE = "UPDATE idea SET name=?, description=?, subject=?, idUser = ?, state=? WHERE idIdea=?";
	private static final String DELETE = "DELETE FROM idea WHERE idIdea=?";
	private static final String ALL = "SELECT * from idea";
	private static final String IDEABYID = "SELECT * from idea where idIdea=?";

	public IdeaDAOMySQL() {
	
	}

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Fonction non utile pour cette version de l'application
	 * Cette fonction premet de créer et retourner
	 * une idée par rapport à une idée de la base de donnée
	 * dont l'id est passé en paramètre.
	 * @Param id : L'id de l'idée qu'on veut récuperer de la base de donnée
	 */
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
				 			  result.getInt("idIdea"),
						      result.getString("name"),
		    		          result.getString("description"),
		    		          result.getString("subject"),
							  SessionFacade.getInstance().getUser()
							  );
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		    return idea;
	}

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Permet d'insérer dans la base de donnée une idée passée
	 * en paramètre
	 * @Param idea : AbstractIdea - idée qu'on veut insérer
	 */
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

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Permet de modifier dans la base de donnée une idée passée
	 * en paramètre
	 * @Param idea : Idée qu'on veut modifier
	 */
	@Override
	public boolean update(AbstractIdea idea) {

		try {

			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(UPDATE);
			ps.setString(1, idea.getName());
			ps.setString(2, idea.getDescription());
			ps.setString(3, idea.getSubject());
			ps.setInt(4, idea.getCreator().getId());
			ps.setString(5, idea.getState());
			ps.setInt(6, idea.getId());

			int i = ps.executeUpdate();
			ps.close();
			return i > 0;

		} catch (SQLException e) {
			return false;
		}
	}

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Permet de supprimer dans la base de donnée une idée dont
	 * l'id est passé en paramètree
	 * @Param id : ID de l'idée qu'on veut supprimer
	 */
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

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Retourne la liste de toutes les idées présentes dans
	 * la base de données
	 */
	@Override
	public List<AbstractIdea> getAllIdeas() {

		List<AbstractIdea> list = new ArrayList<>();
		try {

			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(ALL);
			ResultSet rs = ps.executeQuery();

			IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();


			while(rs.next()){
				AbstractUser user = SessionFacade.getInstance().getUser();
				for (int i = 0; i < userFacade.getListUsers().size(); i++){
					if (userFacade.getListUsers().get(i).getId() == rs.getInt("idUser")){
						user = userFacade.getListUsers().get(i);
					}
				}
				Idea idea = new Idea(
						rs.getInt("idIdea"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("subject"),
						user,
						rs.getString("state")

				);
				list.add(idea);
			}
			ps.close();
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
		return list;
	}

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Retourne l'idée dont l'id est passé en paramètre
	 */
	@Override
	public AbstractIdea getIdeaById(int id) {
		Idea idea = null;
		IGlobalUserFacade userFacade = GlobalUserFacade.getInstance();
		try {
			PreparedStatement ps = MySQLConnector.getSQLConnection().prepareStatement(IDEABYID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				AbstractUser user = SessionFacade.getInstance().getUser();
				for (int i = 0; i < userFacade.getListUsers().size(); i++){
					if (userFacade.getListUsers().get(i).getId() == rs.getInt("idUser")){
						user = userFacade.getListUsers().get(i);
					}
				}
				idea = new Idea(
						rs.getInt("idIdea"),
						rs.getString("name"),
						rs.getString("description"),
						rs.getString("subject"),
						user,
						rs.getString("state")
				);
			}
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idea;
	}

	/**
	 * @author Lauren Unquera - Polytech Montpellier IG4
	 * @Description Fonction non nécessaire et non implémentée
	 * dans cette version de l'application
	 * mais pourra par exemple trouver son utilité pour une fonction de recherche
	 * dans une future version
	 */
	public List<AbstractIdea> getIdeaByName(String name) {
		return null;
	}


}
