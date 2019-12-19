package DAO;

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


	
	protected UserDAO createUserDAO() {
		return new UserDAOMySQL();
	}
	
	public static AnnouncementDAO getAnnouncementDAO() {
		return getInstance().createAnnouncementDAO();
	}
	

	@Override
	protected AnnouncementDAO createAnnouncementDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ChatDAO createChatDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected IdeaDAO createIdeaDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MeetingDAO createMeetingDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected MemberDAO createMemberUDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ProjectDAO createProjectDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RessourceDAO createRessourceDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RoleDAO createRoleDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected SprintDAO createSprintDAO() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}



	
	
	

}
