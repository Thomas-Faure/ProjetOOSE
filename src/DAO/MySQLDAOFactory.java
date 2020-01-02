package DAO;


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
import DAO.User.UserDAO;
import DAO.User.UserDAOMySQL;

public  class MySQLDAOFactory extends AbstractDAOFactory {

	private static MySQLDAOFactory instance;
	private MySQLDAOFactory() {
		
	}
	public static MySQLDAOFactory getInstance() {
		if(instance == null) {
			instance = new MySQLDAOFactory();
		}
		return instance;
	}
	
	public static UserDAO getUserDAO() {
		return getInstance().createUserDAO();
	}

    public static IdeaDAO getIdeaDAO() {
		return getInstance().createIdeaDAO();
    }
	public static RoleDAO getRoleDAO() {
		return getInstance().createRoleDAO();
	}
	
	public static AnnouncementDAO getAnnouncementDAO() {
		return getInstance().createAnnouncementDAO();
	}

	public static TicketDAO getTicketDAO() {
		return getInstance().createTicketDAO();
	}

	public static RessourceDAO getResourceDAO(){ return getInstance().createRessourceDAO();};

	public static SprintDAO getSprintDAO(){ return getInstance().createSprintDAO();};

	protected UserDAO createUserDAO() {
		return new UserDAOMySQL();
	}

	@Override
	protected IdeaDAO createIdeaDAO() {
		return new IdeaDAOMySQL();
	}

	@Override
	protected RoleDAO createRoleDAO() {
		return new RoleDAOMySQL();
	}

	@Override
	protected AnnouncementDAO createAnnouncementDAO() {
		// TODO Auto-generated method stub
		return new AnnouncementDAOMySQL();
	}

	@Override
	protected ChatDAO createChatDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MeetingDAO createMeetingDAO() {
		// TODO Auto-generated method stub
		return new MeetingDAOMySQL();
	}

	public static MeetingDAO getMeetingDAO(){
		return getInstance().createMeetingDAO();
	}

	@Override
	protected MemberDAO createMemberUDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ProjectDAO createProjectDAO() {
		return new ProjectDAOMySQL();
	}

	public static ProjectDAO getProjectDAO(){
		return getInstance().createProjectDAO();
	}

	@Override
	protected RessourceDAO createRessourceDAO() {
		// TODO Auto-generated method stub
		return new RessourceDAOMySQL();
	}


	@Override
	protected SprintDAO createSprintDAO() {
		// TODO Auto-generated method stub
		return new SprintDAOMySQL();
	}

	public static TaskDAO getTaskDAO() {
		return getInstance().createTaskDAO();
	}
	@Override
	protected TaskDAO createTaskDAO() {
		// TODO Auto-generated method stub
		return new TaskDAOMySQL();
	}

	@Override
	protected TicketDAO createTicketDAO() {
		return new TicketDAOMySQL();
	}



	
	
	

}
