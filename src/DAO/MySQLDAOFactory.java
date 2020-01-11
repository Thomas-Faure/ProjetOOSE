package DAO;
/**
 *
 * @author Thomas Faure
 */
import BuisnessLogic.Chat.Chat;
import DAO.Announcement.AnnouncementDAO;
import DAO.Announcement.AnnouncementDAOMySQL;
import DAO.Idea.IdeaDAO;
import DAO.Idea.IdeaDAOMySQL;
import DAO.Meeting.MeetingDAO;
import DAO.Meeting.MeetingDAOMySQL;
import DAO.Project.ProjectDAO;
import DAO.Project.ProjectDAOMySQL;
import DAO.Role.RoleDAO;
import DAO.Role.RoleDAOMySQL;
import DAO.Task.TaskDAO;
import DAO.Task.TaskDAOMySQL;
import DAO.Ticket.TicketDAO;
import DAO.Ticket.TicketDAOMySQL;
import DAO.User.GlobalUser.GlobalUserDAO;
import DAO.User.GlobalUser.GlobalUserDAOMySQL;
import DAO.User.Member.MemberDAO;
import DAO.User.Member.MemberDAOMySQL;

/**
 * This class is the MySQLDAOFactory, to create different MySQLDAO
 */
public  class MySQLDAOFactory extends AbstractDAOFactory {

	private static MySQLDAOFactory instance;
	private MySQLDAOFactory() {
		
	}

	/**
	 * Method to get the static instance of MySQLDAOFactory
	 * @return the static instance of MySQLDAOFactory
	 */
	public static MySQLDAOFactory getInstance() {
		if(instance == null) {
			instance = new MySQLDAOFactory();
		}
		return instance;
	}


	public  GlobalUserDAO getUserDAO() {
		return new GlobalUserDAOMySQL();
	}


    public  IdeaDAO getIdeaDAO() {
		return new IdeaDAOMySQL();
    }

	@Override
	public MeetingDAO getMeetingDAO() {
		return new MeetingDAOMySQL();
	}


	public  RoleDAO getRoleDAO() {
		return new RoleDAOMySQL();
	}
	public  AnnouncementDAO getAnnouncementDAO() {
		return new AnnouncementDAOMySQL();
	}

	public  TicketDAO getTicketDAO() {
		return new TicketDAOMySQL();
	}

	public  RessourceDAO getResourceDAO(){
		return new RessourceDAOMySQL();
	};

	public  ChatDAO getChatDAO(){ return new ChatDAOMySQL();}

	public  SprintDAO getSprintDAO(){ return new SprintDAOMySQL();}

	@Override
	public TaskDAO getTaskDAO() {
		return new TaskDAOMySQL();
	}

	;

    public  MemberDAO getMemberDAO() {
    	return new MemberDAOMySQL();
    }

	@Override
	public ProjectDAO getProjectDAO() {
		return new ProjectDAOMySQL();
	}

	;

	
	

}
